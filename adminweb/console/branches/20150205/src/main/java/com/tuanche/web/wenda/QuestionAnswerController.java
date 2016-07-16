package com.tuanche.web.wenda;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.read.biff.BiffException;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.tuanche.bean.admin.Answer;
import com.tuanche.bean.admin.Question;
import com.tuanche.bean.admin.QuestionKind;
import com.tuanche.bean.admin.QuestionPic;
import com.tuanche.bean.admin.Topic;
import com.tuanche.bean.che.CarstyleDomain;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.service.KeywordService;
import com.tuanche.console.util.ExamExcel07QuestionImport;
import com.tuanche.console.util.Excel07AnswerImport;
import com.tuanche.console.util.Excel07QuestionImport;
import com.tuanche.console.util.ExportExcel;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.console.util.StringUtils;
import com.tuanche.dao.che.BrandDao;
import com.tuanche.dao.che.CarstyleDao;
import com.tuanche.mapper.admin.read.QuestionReadMapper;
import com.tuanche.mapper.admin.write.QuestionWriteMapper;
import com.tuanche.mapper.che.read.BrandkReadMapper;
import com.tuanche.service.admin.AnswerService;
import com.tuanche.service.admin.QuestionKindService;
import com.tuanche.service.admin.QuestionPicService;
import com.tuanche.service.admin.QuestionService;
import com.tuanche.service.admin.QuestionThread;
import com.tuanche.smc.common.page.impl.Pagination;
import com.tuanche.upload.UpLoadUtil;
import com.tuanche.util.KevinUtil;

@Controller
@RequestMapping(value="/questionAnswer")
public class QuestionAnswerController{
	 private static final Integer KIND_PARENT  = 0;
	//类别删除
    private static final Byte KIND_DELETED  = 0;
    //类别上线
    private static final Byte KIND_ONLINE  = 2;
    //删除
    private static final Byte DELETED = 0;
    //上线
    private static final Byte ONLIE = 4;
    //推荐/已解决/采纳
    private static final Byte RECOM = 1;
    //重复标记
    public static String deduplication = null;
    
	@Autowired
	private QuestionService questionService;
	@Autowired
	private QuestionKindService kindService;
	@Autowired
	private AnswerService answerService;
	@Autowired
	private KeywordService keywordService;
	@Autowired
	private QuestionPicService picService;
	@Autowired
	private BrandkReadMapper brandReadMapper;
	@Autowired
	private QuestionKindService questionKindService;
	@Resource
	private BrandDao brandDao;
	@Resource
	private CarstyleDao carstyleDao;
	/**
	 * 进入话题页面
	 * @param model
	 * @param topic
	 * @return
	 */
	@RequestMapping(value="/topicList")
	public String topicsList(Model model,Topic topic){
		Pagination pagination=topic.getPage();
		if(pagination==null){
		pagination = new Pagination();
		}
		Pagination.threadLocal.set(pagination);
		model.addAttribute("page", Pagination.threadLocal.get());
		model.addAttribute("topics",questionService.topicByPage(topic));
		model.addAttribute("brands",brandReadMapper.selectBrandAll() );
		model.addAttribute("top",topic);
		return "question/topicList";
	}
	
	/**
	 * 进入修改话题页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/toUpdateTopic")
	public String toUpdateTopic(Model model,@Param("id")Integer id){		
		model.addAttribute("topic", questionService.selectTopicById(id));
		model.addAttribute("brands",brandReadMapper.selectBrandAll() );
		return "question/toAddTopic";
	}
	/**
	 * 修改话题
	 * @param employe
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/updateTopic")
	public String updateTopic(Topic topic,Model model,HttpSession session,HttpServletRequest request){
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		Integer updateId=employee.getId();
		if(topic.getId()!=null&&topic.getId()>0){
			//修改
			topic.setUpdateEmp(updateId);
			questionService.updateTopic(topic,request);
		}else{
			//新增
			topic.setBuildEmp(updateId);
			topic.setUpdateEmp(updateId);
			questionService.addTopic(topic,request);
		}
		return "redirect:/questionAnswer/topicList";
	}
	/**
	 * 压缩上传图片到本地
	 * @param request
	 * @param delSrc
	 * @return
	 */
	@RequestMapping("/json/uploadImg")
	@ResponseBody
	public String uploadImg(HttpServletRequest request,@Param("delSrc")String delSrc) {
		return KevinUtil.picUploadByWH(request, "topPicFile", null, "recpic", 300, 200);
	}
	
	/**
	 * 修改话题状态
	 * @param model
	 * @param request
	 * @param session
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/updatetopstatus")
	@ResponseBody
	public String updatetopstatus(Model model,HttpServletRequest request,HttpSession session,@Param("id")Integer id,@Param("status")Byte status){
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		questionService.updatetopstatus(status,employee.getId(),id);
		return "";		
	}
	
	/**
	 * 删除content
	 * @param model
	 * @param request
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteContent")
	@ResponseBody
	public String deleteContent(Model model,HttpServletRequest request,HttpSession session,@Param("id")Integer id){
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		questionService.deleteContent(employee.getId(),id);
		return "";		
	}
	
	/**
	 * 更改content
	 * @param model
	 * @param request
	 * @param session
	 * @param id
	 * @param content
	 * @return
	 */
	@RequestMapping(value="/updateContent")
	@ResponseBody
	public String updateContent(Model model,HttpServletRequest request,HttpSession session,@Param("id")Integer id,@Param("content")String content){
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		questionService.updateContent(employee.getId(),id,content);
		return "";	
	}
	
	
	/**
	 * 推荐话题（取消推荐）
	 * @param model
	 * @param request
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/recom")
	@ResponseBody
	public String recom(Model model,HttpServletRequest request,HttpSession session,@Param(value="id")Integer id,@Param(value="recom")Byte recom){
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		questionService.recom(recom,employee.getId(),id);		
		return "";
	}
	/**
	 * 设置本期话题（取消本期）
	 * @param model
	 * @param request
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/current")
	@ResponseBody
	public String current(Model model,HttpServletRequest request,HttpSession session,@Param(value="id")Integer id,@Param(value="current")Byte current){
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		questionService.current(current,employee.getId(),id);		
		return "";
	}
	
	
	/**
	 * 查询所有一级分类
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/oneKind")
	public String toOneKind(Model model){
		System.out.println("===========");
		model.addAttribute("kind",kindService.selectOneKind());
		return "question/oneKind";
	}
	/**
	 * 修改页面 一级分类
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/toUpdateOne")
	public String toupdateOneKind(Model model,Integer id){
		model.addAttribute("kind",kindService.selectOne(id));
		model.addAttribute("oneId",id);

		return "question/toAddOneKind";
	}
	/**
	 * 删除分类
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteKind")
	public String deleteKind(HttpSession session,Integer id){
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		kindService.deleteOneKind(employee.getId(),id,KIND_DELETED);
		return "redirect:/questionAnswer/oneKind";
	}
	/**
	 * 修改一级分类
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/updateOneKind")
	public String updateOrAddKind(Model model,HttpServletRequest request,HttpSession session,QuestionKind kind){
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		Integer emp=employee.getId();		
		String[] kdIds=null;
		StringBuffer Ids=new StringBuffer();	//关键词维度id
		StringBuffer kds=new StringBuffer();	//关键词维度
		kdIds=(kind.getDimension()+",").split(",");
		List<Integer> keywords= keywordService.findIdsByKdId(kdIds);
		for(Integer id:keywords){
			Ids.append(id+",");
			kds.append(keywordService.findKeyword(id)+",");
			}
		kind.setUpdateEmp(emp);
		kind.setParentId(KIND_PARENT);
		kind.setKdId(Ids.toString());
		kind.setKindStatus(KIND_ONLINE);
		kind.setKeyword(kds.toString());
		if(kind.getId()!=null&&kind.getId()>0){			
			kindService.updateKind(kind);			
		}else{
			kind.setBuildEmp(emp);
			kindService.addKind(kind);
			//新增
			
		}
		return "redirect:/questionAnswer/oneKind ";
	}
	/**
	 * 查询父id下的二级分类
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/twoKind")
	public String toTwoKind(Model model,HttpServletRequest request){
		String oneKindId=null;
		if(!StringUtils.isEmpty(request.getParameter("pid"))){
		    oneKindId=request.getParameter("pid");
			model.addAttribute("kind",kindService.selectTwoKind(Integer.valueOf(oneKindId)));
			model.addAttribute("oneId", Integer.valueOf(oneKindId));
		}
		model.addAttribute("oneKind",kindService.selectOneKind());

		return "question/twoKind";
	}
	/**
	 * 修改二级类别
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/updateTwoKind")
	public String updateTwoKind(Model model,HttpServletRequest request,HttpSession session,QuestionKind kind){
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		Integer emp=employee.getId();
		String[] kdIds=null;
		StringBuffer Ids=new StringBuffer();	//关键词维度id
		StringBuffer kds=new StringBuffer();	//关键词维度	
		Integer pid=null;
		if(kind.getParentId()!=null&&kind.getParentId()>0){
			pid=kind.getParentId();
		}else{
			kind.setParentId(KIND_PARENT);
		}
		kdIds=(kind.getDimension()+",").split(",");
		List<Integer> keywords= keywordService.findIdsByKdId(kdIds);
		for(Integer id:keywords){
			Ids.append(id+",");
			kds.append(keywordService.findKeyword(id)+",");
			}
		kind.setUpdateEmp(emp);
		kind.setParentId(pid);
		kind.setKdId(Ids.toString());
		kind.setKindStatus(KIND_ONLINE);
		kind.setKeyword(kds.toString());
		if(kind.getId()!=null&&kind.getId()>0){			
		kindService.updateKind(kind);
		}else {
			kind.setBuildEmp(emp);
			kindService.addKind(kind);
		}
		
		return "redirect:/questionAnswer/twoKind";
	}
	/**
	 * 修改页面二级分类
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/toUpdateTwo")
	public String toUpdateTwoKind(Model model,HttpServletRequest request,Integer pid,Integer id){
		model.addAttribute("twoKind",kindService.selectTwoKind(pid));
		model.addAttribute("two",kindService.selectOne(id));
		model.addAttribute("oneId",pid);
		model.addAttribute("kind",kindService.selectOneKind());
		return "question/toAddTwoKind";
	}
	/**
	 * 问题列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/toQuestionList")
	public String Questionlist(Model model,HttpServletRequest request ,Question question){
		Pagination pagination=question.getPage();
		
		if(pagination==null){
		pagination = new Pagination();
		}
		
		Pagination.threadLocal.set(pagination);
		List<Question> questions=new ArrayList<Question>();
		List<QuestionKind> oneKinds=new ArrayList<QuestionKind>();
		oneKinds=kindService.selectOneKind();
		questions=questionService.queryByPage(question);
		model.addAttribute("question",question);
		model.addAttribute("questions",questions);
		model.addAttribute("fistKindList", questionKindService.selectOneKind());
		model.addAttribute("pBrands", brandDao.selectBrandAll());
		model.addAttribute("oneKinds",oneKinds);
		model.addAttribute("kinds",kindService.selectAlls());
		model.addAttribute("page", Pagination.threadLocal.get());
		model.addAttribute("deduplication", deduplication);
		return "question/questList";
	}
	/**
	 * 导出问题
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value="/download")
	public void questiondownload(HttpServletRequest request,HttpServletResponse response,ModelMap model,Question question){		
		List<Question> questionList=questionService.selectAlls(question);
		Map<String,String> titleValueMap=new LinkedHashMap<String,String>();
		titleValueMap.put("Id","Id");
		titleValueMap.put("Title","问题");
		ExportExcel.exportExcel(new SimpleDateFormat("yyyy-MM-dd").format(new Date())+".xls",titleValueMap,questionList,request,response);
	} 
	/**
	 * 彻底删除数据
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public void delete(Model model,HttpServletRequest request,@Param("checkeds")String[] ids,HttpSession session){
		questionService.delete(Arrays.asList(ids));
	}
	/**
	 * 批量修改数据(上下线，删除)
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/updatestatus")
	@ResponseBody
	public String questionOnline(Model model,HttpServletRequest request,@Param("checkeds")String[] ids,HttpSession session,@Param("type")Byte type){
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		questionService.updateQuestions(type,employee.getId(),Arrays.asList(ids));
		//修改分类问题数
		List<Integer> kindids=new ArrayList<Integer>();
		for(Integer kind:GlobalConstants.kindsmap.keySet()){
			kindids.add(kind);
		}
		kindService.updateQuestCounts(kindids);
		//update
		return "";
	}
	
	/**
	 * 删除问题
	 * @param model
	 * @param request
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deleteOneQuest")
	@ResponseBody
	public String deleteOne(Model model,HttpServletRequest request,HttpSession session,@Param("id")Integer id){
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		questionService.deleteOneQuestion(DELETED,employee.getId(),id);
		answerService.deleteAnswersWithQ(DELETED,employee.getId(),id);		
		//修改分类问题数
		List<Integer> kindids=new ArrayList<Integer>();
		for(Integer kind:GlobalConstants.kindsmap.keySet()){
			kindids.add(kind);
		}
		kindService.updateQuestCounts(kindids);
		return "";		
	}
	/**
	 * 推荐问题XX（取消推荐）
	 * @param model
	 * @param request
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/recomQuestion")
	@ResponseBody
	public String recomQuestion(Model model,HttpServletRequest request,HttpSession session,@Param(value="id")Integer id,@Param(value="recom")Byte recom){
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("buildEmp", employee.getId());
		map.put("id", id);
		map.put("isRecom",recom);
		questionService.isRecom(map);		
		return "";
	}
	/**
	 * 导入问题
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws BiffException
	 * @throws MultipartException
	 * @throws IOException
	 */
	@RequestMapping(value="/excelImport",method=RequestMethod.POST)
	public String excleImport (Model model,HttpServletRequest request,HttpSession session)
			throws BiffException,MultipartException,IOException{
			  MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		      MultipartFile fileExcel = multipartRequest.getFile("file");
		      List<Integer> questIds=Excel07QuestionImport.readNewExcel(questionService,answerService,keywordService,fileExcel.getInputStream(), session);  
		      questionService.updateReplys(questIds);
		      //修改分类问题数
				List<Integer> kindids=new ArrayList<Integer>();
				for(Integer kind:GlobalConstants.kindsmap.keySet()){
					kindids.add(kind);
				}
				kindService.updateQuestCounts(kindids);
		      return "redirect:/questionAnswer/toQuestionList";
		    }
	/**
	 * 答案导入
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/answerImport")
	public String questionList(Model model,HttpServletRequest request,HttpSession session) throws IOException{
		//从session中获取用户信息
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
	  //获得上传文件
	  MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      MultipartFile fileExcel = multipartRequest.getFile("file");
      List<Answer> answers=new ArrayList<Answer>();
      answers=Excel07AnswerImport.readNewExcel(fileExcel.getInputStream());
      for(Answer answer:answers){
    	  answer.setAnswerStatus(ONLIE);
    	  answer.setBuildEmp(employee.getId());
    	  answer.setUpdateEmp(employee.getId());
      }
      answerService.addAnswers(answers);
      List<Integer> questIds=new ArrayList<Integer>();      
      for(Answer ans:answers){
    	  questIds.add(ans.getQuestionId());
    	  if(ans.getAnswerAdopt()==1){
    	 Integer pid=ans.getQuestionId();
    	 questionService.resolve(RECOM,employee.getId(),pid);
    	  }	
      }
      questionService.updateReplys(questIds);
		return "redirect:/questionAnswer/toQuestionList";
	}
	/*@RequestMapping
	 * 图片页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/toPicList")
	public String toPicList(Model model){
		List<QuestionPic> pics=new ArrayList<QuestionPic>();
		pics=picService.selectPicsByPage();
		model.addAttribute("pics",pics);
		return "question/picList";
	}
	/**
	 * 修改排序
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/toUpdateSort")
	public String toUpdateSort(Model model,HttpServletRequest request,HttpSession session){
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		Integer updateId=employee.getId();
		String [] ids=request.getParameterValues("id");
		String [] sorts=request.getParameterValues("sort");
		for(int i=0;i<sorts.length;i++){
			QuestionPic pic=new QuestionPic();
			pic.setId(Integer.valueOf(ids[i]));
			pic.setSort(Integer.valueOf(sorts[i]));
			pic.setUpdateEmp(updateId);
			picService.updateSort(pic);
		}
		return "redirect:/questionAnswer/toPicList";
	}
	/**
	 * 图片隐藏/显示
	 * @param model
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/deletePic")
	@ResponseBody
	public String deletePic(Model model,@Param("id")Integer id,HttpSession session,@Param("status")Byte status){
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		Integer updateId=employee.getId();
		QuestionPic pic=new QuestionPic();
		pic.setUpdateEmp(updateId);
		pic.setId(id);
		pic.setPicStatus(status);
		picService.updatePicStatus(pic);
		return "";
	}
	/**
	 * 进入修改页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/toUpdatePic")
	public String toUpdatePic(Model model,@Param("id")Integer id){		
		model.addAttribute("pic", picService.selectPicById(id));
		return "question/toAddPic";
	}
	/**
	 * 修改（新增）
	 * @param pic
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/updatePic")
	public String updatePic(QuestionPic pic,Model model,HttpSession session,HttpServletRequest request){
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		Integer updateId=employee.getId();
		if(pic.getId()!=null&&pic.getId()>0){
			//新增
			pic.setUpdateEmp(updateId);
			picService.updatePicAll(pic,request);
		}else{
			//修改
			pic.setBuildEmp(updateId);
			pic.setUpdateEmp(updateId);
			picService.addQuestionPic(pic,request);
		}
		return "redirect:/questionAnswer/toPicList";
	}
	/**
	 * @param request
	 * @param response
	 * @author liuhg
	 * @Description 图片上传
	 */
	@RequestMapping("/uploadPic")
	public void uploadPic(HttpServletRequest request,HttpServletResponse response){
		UpLoadUtil upLoadUtil=new UpLoadUtil();
		upLoadUtil.uploadPicNoFtp(request, response);
	}	
	/**
	 * Excel校验
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws BiffException
	 * @throws MultipartException
	 * @throws IOException
	 */
	@RequestMapping(value="/examExcel",method=RequestMethod.POST)
	@ResponseBody
	public String examExcle(Model model,HttpServletRequest request,HttpSession session,HttpServletResponse response)
			throws BiffException,MultipartException,IOException{
			  MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		      MultipartFile fileExcel = multipartRequest.getFile("file");
		      return ExamExcel07QuestionImport.readNewExcel(fileExcel.getInputStream(), session).trim();
		      }
	/**
	 * 修改分类、品牌
	 * @param question
	 * @param request
	 * @param session
	 * @param response
	 */
	@RequestMapping("/modify")
	@ResponseBody
	public String modify(@Param(value="id")Integer id,@Param(value="firstkindId")Integer firstkindId,@Param(value="secondkindId")Integer secondkindId,@Param(value="brandId")Integer brandId,@Param(value="carstyleId")Integer carstyleId,HttpServletRequest request,HttpSession session,HttpServletResponse response){
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		questionService.modify(employee.getId(),id,firstkindId,secondkindId,brandId,carstyleId);
		return "";
	}
	
	@RequestMapping("/carstyle")
	@ResponseBody 
	public List<CarstyleDomain> ajaxStyle(@Param(value = "brandID") Integer brandID,
			HttpServletResponse response,CarstyleDomain carstyleDomain) {
		if (brandID == 0) {
			return null;
		}
		List<CarstyleDomain> clists = carstyleDao.selectStyleByIdName(brandID);
		return clists;
	}
	
	
	@Autowired
	public  QuestionReadMapper readMapper;
	@Autowired
	public  QuestionWriteMapper writeMapper;
	@RequestMapping("/deduplication")
	public String deduplication(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
//		ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 3, 3, TimeUnit.SECONDS, new LinkedBlockingQueue(20));
//		executor.execute(new QuestionThread(readMapper,writeMapper));
		deduplication = "1";
		QuestionThread qh = new QuestionThread(readMapper, writeMapper);
		Thread thread = new Thread(qh);
		thread.start();
		
		return "redirect:/questionAnswer/toQuestionList";
	}
	
	@RequestMapping("/batchUpdate")
	@ResponseBody
	public String batchUpdate(@Param("checkeds")String[] ids,@Param(value="firstkindId")Integer firstkindId,@Param(value="secondkindId")Integer secondkindId,@Param(value="brandId")Integer brandId,@Param(value="carstyleId")Integer carstyleId,HttpServletRequest request,HttpSession session,HttpServletResponse response){
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		questionService.batchUpdate(Arrays.asList(ids),employee.getId(),firstkindId,secondkindId,brandId,carstyleId);
		return "";
	}
}
