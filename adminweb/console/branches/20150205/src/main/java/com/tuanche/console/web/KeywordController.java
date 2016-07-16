package com.tuanche.console.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartException;

import jxl.read.biff.BiffException;

import com.tuanche.bean.che.CarStyle;
import com.tuanche.console.bean.Dimension;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.bean.Keyword;
import com.tuanche.console.dao.InitDao;
import com.tuanche.console.dao.KeywordDao;
import com.tuanche.console.service.KeywordService;
import com.tuanche.console.util.RequestUtils;
import com.tuanche.console.util.Pager;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.console.util.ExcelKeywordImport;
import com.tuanche.console.util.StringUtils;
import com.tuanche.smc.common.page.impl.Pagination;
import com.tuanche.smc.domain.specialSubject.SpecialSubject;
import com.tuanche.smc.domain.specialSubject.Template;
@Controller
@Repository
@RequestMapping(value="/keyword")
public class KeywordController {
	 @Autowired
	 KeywordDao keywordDao;
	 @Autowired
	 private KeywordService keyService;
	 
	@RequestMapping(value="/add")
	public ModelAndView add(Model model,HttpServletRequest request){
		model.addAttribute("levelmap", GlobalConstants.levelMap);
		model.addAttribute("brandList", GlobalConstants.brandList);
		//model.addAttribute("carStyleList", GlobalConstants.carStyleList);
		model.addAttribute("onlinestatusmap", GlobalConstants.onlineStatusMap);
		model.addAttribute("includedstatusmap", GlobalConstants.includedStatusMap);
		model.addAttribute("typemap", GlobalConstants.typeMap);
		model.addAttribute("citymap", GlobalConstants.cityMap);
		return new ModelAndView("/keyword/add");
	}

	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(Model model,HttpServletRequest request){
		String[] words=request.getParameterValues("word");
		String[] level=request.getParameterValues("level");
		String[] pid=request.getParameterValues("pid");
		String[] type=request.getParameterValues("type");
		String[] city=request.getParameterValues("city");
		String[] baiduIndex=request.getParameterValues("baiduIndex");
		String[] rank=request.getParameterValues("rank");
		String[] describe=request.getParameterValues("describe");
		String[] kdId=request.getParameterValues("kdId");
		String[] brandId=request.getParameterValues("brandId");
		String[] carStyleId=request.getParameterValues("carStyleId");
		String online=request.getParameter("online");
		int employeeId=(int)request.getSession().getAttribute("employeeId");
		List<Keyword> keywordList=new ArrayList<Keyword>();
		List<String> wordList=new ArrayList<String>();
		if(words.length>0){
			//wordList=keywordDao.findAllWord();
		}
		for(int i=0;i<words.length;i++){
			if(!wordList.contains(words[i].trim())){
				Keyword keyword=new Keyword();
				keyword.setWord(words[i].trim());
				keyword.setLevel(Integer.parseInt(level[i]));
				if(!StringUtils.isEmpty(pid[i])){
					keyword.setPid(Integer.parseInt(pid[i]));
				}
				if(!StringUtils.isEmpty(city[i])){
					keyword.setCityId(Integer.parseInt(city[i]));
				}
				keyword.setType(Integer.parseInt(type[i]));
				if(!StringUtils.isEmpty(baiduIndex[i])){
					keyword.setBaiduIndex(Integer.parseInt(baiduIndex[i]));
				}
				if(!StringUtils.isEmpty(rank[i])){
					keyword.setRank(Integer.parseInt(rank[i]));
				}
				if(!StringUtils.isEmpty(kdId[i])){
					keyword.setKdId(Integer.parseInt(kdId[i]));
				}
				if(!StringUtils.isEmpty(brandId[i])){
					keyword.setBrandId(brandId[i]);
				}
				if(!StringUtils.isEmpty(carStyleId[i])){
					keyword.setCarStyleId(carStyleId[i]);
				}
				keyword.setDescribe(describe[i]);
				keyword.setOnline(Integer.parseInt(online));
				keyword.setAddUserId(employeeId);
				keywordList.add(keyword);
			}
		}
		if(keywordList!=null&&keywordList.size()>0){
			for(Keyword kw:keywordList){
				keywordDao.addkw(kw);
				if(kw.getLevel()==2){
					GlobalConstants.levelMap.put(kw.getId(), kw.getWord());
				}
			}
		}
		return "redirect:/keyword/list";
	}
	@RequestMapping(value="/insertImport",method=RequestMethod.POST)
	public String insertImport(Model model,HttpServletRequest request){
		String[] words=request.getParameterValues("word");
		String[] kdId=request.getParameterValues("kdId");
		String[] brandId=request.getParameterValues("brandId");
		String[] carStyleId=request.getParameterValues("carStyleId");
		String[] level=request.getParameterValues("level");
		String[] baiduIndex=request.getParameterValues("baiduIndex");
		String[] rank=request.getParameterValues("rank");
		String[] describe=request.getParameterValues("describe");
		String[] type=request.getParameterValues("type");
		int employeeId=(int)request.getSession().getAttribute("employeeId");
		
		List<Keyword> keywordList=new ArrayList<Keyword>();
		List<String> wordList=new ArrayList<String>();
		if(words.length>0){
			wordList=keywordDao.findAllWord();
		}
		for(int i=0;i<words.length;i++){
			Keyword keyword=new Keyword();
			if(!wordList.contains(words[i].trim())){
				keyword.setWord(words[i].trim());
				keyword.setLevel(Integer.parseInt(level[i]));
				keyword.setType(Integer.parseInt(type[i]));
				keyword.setAddUserId(employeeId);
				keyword.setKdId(Integer.parseInt(kdId[i]));
				/*keyword.setBrandId(brandId[i]);
				keyword.setCarStyleId(carStyleId[i]);*/
				if(StringUtils.isNotEmptyString(baiduIndex[i])){
					keyword.setBaiduIndex(Integer.parseInt(baiduIndex[i]));
				}
				if(StringUtils.isNotEmptyString(rank[i])){
					keyword.setRank(Integer.parseInt(rank[i]));
				}
				if(StringUtils.isNotEmptyString(describe[i])){
					keyword.setDescribe(describe[i]);
				}
				if(StringUtils.isNotEmptyString(brandId[i])){
					keyword.setBrandId(brandId[i]);
				}
				if(StringUtils.isNotEmptyString(carStyleId[i])){
					keyword.setCarStyleId(carStyleId[i]);
				}
				keywordList.add(keyword);
			}
		}
		if(keywordList!=null&&keywordList.size()>0){
			keywordDao.addKeyWord(keywordList);
		}
		return "redirect:/keyword/list";
	}
	
	@RequestMapping(value="/exgetbylevel")
	public Model getParentByLevel(Model model,HttpServletRequest request){
		String plevel = request.getParameter("plevel");
		List<Keyword> parentList=new ArrayList<Keyword>();
		parentList=keywordDao.findParentByLevel(Integer.parseInt(plevel));
		model.addAttribute("infos", parentList);
		return  model;
	}
	@RequestMapping(value="/deleteItems")
	public String deleteItems(Model model,HttpServletRequest request){
		String Ids=request.getParameter("returnStr");
		keywordDao.delByIds(Ids);
		String[] deleteId=Ids.split(",");
		for(String i:deleteId){
			GlobalConstants.levelMap.remove(Integer.parseInt(i));
		}
		return "redirect:/keyword/list";
	}
	@RequestMapping(value="/includedItems")
	public String includedItems(Model model,HttpServletRequest request){
		String Ids=request.getParameter("returnStr");
		keywordDao.includedItems(Ids);
		return "redirect:/keyword/list";
	}
	@RequestMapping(value="/onlineItems")
	public String onlineItems(Model model,HttpServletRequest request){
		String Ids=request.getParameter("returnStr");
		keywordDao.onlineItems(Ids);
		return "redirect:/keyword/list";
	}
	@RequestMapping(value="/list")
	public ModelAndView list(Model model,HttpServletRequest request){
		int selectNum=RequestUtils.getInt(request, "selectNum",0);
		int isPageNumChange=RequestUtils.getInt(request, "isPageNumChange",0);
		Keyword keyword=setKeyword(request);
		int totalRows = RequestUtils.getInt(request, "totalRows",0);
		totalRows=keywordDao.count(keyword);
		if(selectNum==0){
			int cpage = RequestUtils.getInt(request, "cpage",0);
			int start=(cpage>0?cpage-1:cpage);
			keyword.setStart(start*20);
			keyword.setLimit(20);	
			model.addAttribute("pb", new Pager(cpage, totalRows,20));
		}else if(selectNum!=0&&isPageNumChange==0){
			int cpage = RequestUtils.getInt(request, "cpage",0);
			int start=(cpage>0?cpage-1:cpage);
			keyword.setStart(start*selectNum);
			keyword.setLimit(selectNum);
			model.addAttribute("pb", new Pager(cpage, totalRows,selectNum));
		}else if(selectNum!=0&&isPageNumChange==1){
			int cpage = 0;
			keyword.setStart(0);
			keyword.setLimit(selectNum);
			model.addAttribute("pb", new Pager(cpage, totalRows,selectNum));
		}
		model.addAttribute("citys", GlobalConstants.citys);
		int employeeId=-1;
		if(request.getSession().getAttribute("employeeId")!=null){
			 employeeId=(int)request.getSession().getAttribute("employeeId");
		}
		model.addAttribute("selectNum", selectNum);
		model.addAttribute("brandList", GlobalConstants.brandList);
		//model.addAttribute("carStyleList", GlobalConstants.carStyleList);
		model.addAttribute("selectNum", selectNum);
		model.addAttribute("employeeId", employeeId);
		model.addAttribute("users",GlobalConstants.editEmployeeMap);

		model.addAttribute("keyword",keyword);
		//List<Keyword> key=keywordDao.find(keyword);
		model.addAttribute("list",keywordDao.find(keyword));
		model.addAttribute("levelmap", GlobalConstants.levelMap);
		model.addAttribute("onlinestatusmap", GlobalConstants.onlineStatusMap);
		model.addAttribute("includedstatusmap", GlobalConstants.includedStatusMap);
		model.addAttribute("typemap", GlobalConstants.typeMap);
		model.addAttribute("citymap", GlobalConstants.cityMap);
		return new ModelAndView("/keyword/list");
	}
	@RequestMapping(value="/excleBatchImport",method=RequestMethod.POST)
	public ModelAndView excleBatchImport (Model model,HttpServletRequest request)
			throws BiffException,MultipartException,IOException{
			  MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		      MultipartFile fileExcel = multipartRequest.getFile("file");
		      List<Keyword> keywordList=ExcelKeywordImport.readNewExcel(fileExcel.getInputStream());
		      List<String> conditions=new ArrayList<String>();
		      conditions.add("d.status='1'");
			  List<Dimension> dimList=keyService.queryByPage(conditions);
			  model.addAttribute("dimList", dimList);
		      request.setAttribute("list", keywordList);
		      model.addAttribute("levelmap", GlobalConstants.levelMap);
		      model.addAttribute("brandList", GlobalConstants.brandList);
			  model.addAttribute("carStyleList", GlobalConstants.carStyleList);
		      model.addAttribute("onlinestatusmap", GlobalConstants.onlineStatusMap);
		      model.addAttribute("includedstatusmap", GlobalConstants.includedStatusMap);
		      model.addAttribute("typemap", GlobalConstants.typeMap);
		      model.addAttribute("citymap", GlobalConstants.cityMap);
		      return new ModelAndView("/keyword/importList");
		    }
	@RequestMapping(value="/update")
	public ModelAndView update(Model model,@Param(value="id") int id){
		List<String> conditions=new ArrayList<String>();
		conditions.add("d.status='1'");
		model.addAttribute("keyword", keywordDao.findByid(id));
		model.addAttribute("dimList", keyService.queryByPage(conditions));
		model.addAttribute("brandList", GlobalConstants.brandList);
		model.addAttribute("carStyleList", GlobalConstants.carStyleList);
		model.addAttribute("levelmap", GlobalConstants.levelMap);
		model.addAttribute("onlinestatusmap", GlobalConstants.onlineStatusMap);
		model.addAttribute("includedstatusmap", GlobalConstants.includedStatusMap);
		model.addAttribute("typemap", GlobalConstants.typeMap);
		model.addAttribute("citymap", GlobalConstants.cityMap);
		return new ModelAndView("/keyword/updatepage");
	}
	
	@RequestMapping(value="/updateSave")
	public String updateSave(Model model,HttpServletRequest request,String carStyleId,String brandId){
		String wordid=request.getParameter("wordid");
		Keyword keyword =new Keyword();
		String word=request.getParameter("word");
		String kdId=request.getParameter("kdId");
		String level=request.getParameter("level");
		String pid=request.getParameter("pid");
		String type=request.getParameter("type");
		String city=request.getParameter("city");
		String baiduIndex=request.getParameter("baiduIndex");
		String rank=request.getParameter("rank");
		String describe=request.getParameter("describe");
		String online=request.getParameter("online");
		keyword.setId(Integer.parseInt(wordid));
		keyword.setWord(word);
		keyword.setLevel(Integer.parseInt(level));
		keyword.setKdId(Integer.parseInt(kdId));

		keyword.setType(Integer.parseInt(type));
		keyword.setDescribe(describe);
		if(!StringUtils.isEmpty(pid)){
			keyword.setPid(Integer.parseInt(pid));
		}
		if(!StringUtils.isEmpty(online)){
			keyword.setOnline(Integer.parseInt(online));
		}
		if(!StringUtils.isEmpty(baiduIndex)){
			keyword.setBaiduIndex(Integer.parseInt(baiduIndex));
		}
		if(!StringUtils.isEmpty(rank)){
			keyword.setRank(Integer.parseInt(rank));
		}
		if(!StringUtils.isEmpty(city)){
			keyword.setCityId(Integer.parseInt(city));
		}
		if(!StringUtils.isEmpty(brandId)){
			keyword.setBrandId(brandId);
		}else{
			keyword.setBrandId(null);
		}
		if(!StringUtils.isEmpty(carStyleId)){
			if(",".equals(carStyleId)){
				keyword.setCarStyleId(null);
			}else{
				keyword.setCarStyleId(carStyleId);
			}
		}else{
			keyword.setCarStyleId(null);
		}
		keywordDao.updateSave(keyword);
		return "redirect:/keyword/list";
	}
	@RequestMapping(value="/saveItems")
	public String saveItems(Model model,HttpServletRequest request){
		String[] idStr=request.getParameterValues("id");
		String[] wordBaiduIndex=request.getParameterValues("wordBaiduIndex");
		String[] wordRank=request.getParameterValues("wordRank");
		for(int m=0;m<idStr.length;m++){
			Keyword keyd =new Keyword();
			keyd.setId(Integer.parseInt(idStr[m]));
			if(!StringUtils.isEmpty(wordBaiduIndex[m])){
				keyd.setBaiduIndex(Integer.parseInt(wordBaiduIndex[m]));
			}
			if(!StringUtils.isEmpty(wordRank[m])){
				keyd.setRank(Integer.parseInt(wordRank[m]));
				
			}
			keywordDao.saveItems(keyd);
		}
		
		return "redirect:/keyword/list";
	}
	
	public Keyword setKeyword(HttpServletRequest request){
		Keyword keyword=new Keyword();
		keyword.setOnline(RequestUtils.getInt(request, "online",-1));
		keyword.setIncluded(RequestUtils.getInt(request, "included",-1));
		keyword.setRank(RequestUtils.getInt(request, "rank",-1));
		keyword.setUv(RequestUtils.getInt(request, "uv",-1));
		keyword.setWord(RequestUtils.getString(request, "word"));
		keyword.setLevel(RequestUtils.getInt(request, "level",-1));
		keyword.setType(RequestUtils.getInt(request, "type",-1));
		keyword.setCityId(RequestUtils.getInt(request, "city",-1));
		keyword.setBrandId(RequestUtils.getString(request,"brandId"));
		keyword.setCarStyleId(RequestUtils.getString(request,"carStyleId"));
		keyword.setStartDate(RequestUtils.getString(request, "startDate"));
		keyword.setEndDate(RequestUtils.getString(request, "endDate"));
		keyword.setAddUserId(RequestUtils.getInt(request, "addEmployeeId",-1));
		return keyword;
	}
	
	/*维度添加*/
	@RequestMapping(value="/toadd")
	public String toAddDimension(){
		return "keyword/addDimension";
	}
	
	/**
	 * @param model
	 * @return
	 * @author liuhg
	 * @Description 列表页
	 */
	@RequestMapping(value="/toList")
	public String toList(Model model){
		
		Pagination pagination;
		pagination=new Pagination();
		Pagination.threadLocal.set(pagination);
		List<String> conditions =new ArrayList<String>();
		conditions.add("d.status='1'");
		List<Dimension> dimList=keyService.queryByPage(conditions);
		model.addAttribute("dimList", dimList);
		List<Dimension> operateList=keyService.selectOperate();
		if(operateList!=null&&operateList.size()>0){
			model.addAttribute("operateList", operateList);
		}
		model.addAttribute("page", Pagination.threadLocal.get());
		return "keyword/dimensionList";
	}
	
	/**
	 * @param dimension
	 * @param model
	 * @return
	 * @author liuhg
	 * @Description 条件查询
	 */
	@RequestMapping(value="/search")
	public String search(Dimension dimension,Model model){
		
		Pagination.threadLocal.set(dimension.getPage());
		List<String> conditions=new ArrayList<String>();
		conditions.add("d.status='1'");
		if(dimension!=null){
			if(dimension.getDieName()!=null&&!"".equals(dimension.getDieName())){
				conditions.add("d.die_name like '%"+dimension.getDieName()+"%'");
			}
			if(dimension.getKeywords()!=null&&!"".equals(dimension.getKeywords())){
				conditions.add("d.keywords like '%"+dimension.getKeywords()+"%'");
			}
			if(dimension.getOperateUserId()!=null&&!"".equals(dimension.getOperateUserId())&&dimension.getOperateUserId()>0){
				conditions.add("d.operate_user_id='"+dimension.getOperateUserId()+"'");
				model.addAttribute("userId", dimension.getOperateUserId());
			}
			if(dimension.getStartTime()!=null&&!"".equals(dimension.getStartTime())){
				conditions.add("d.create_time>= '"+dimension.getStartTime()+"'");
			}
			if(dimension.getEndTime()!=null&&!"".equals(dimension.getEndTime())){
				conditions.add("d.create_time<='"+dimension.getEndTime()+"'");
			}
			List<Dimension> operateList=keyService.selectOperate();
			if(operateList!=null&&operateList.size()>0){
				model.addAttribute("operateList", operateList);
			}
			List<Dimension> dimList=keyService.queryByPage(conditions);
			model.addAttribute("dimList", dimList);
			model.addAttribute("dimension", dimension);
			model.addAttribute("page", Pagination.threadLocal.get());
		}
		
		return "keyword/dimensionList";
	}
	
	/**
	 * @param session
	 * @param dimension
	 * @return
	 * @author liuhg
	 * @Description 插入和修改
	 */
	@RequestMapping("/insertDim")
	public String insert(HttpSession session,Dimension dimension){
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		if(employee!=null){
			if(dimension.getId()!=null&&!"".equals(dimension.getId())){
				dimension.setOperateUserId(Integer.parseInt(employee.getEmpNo()));
				dimension.setOperateUserName(employee.getEmpName());
				keyService.update(dimension);
			}else{
				dimension.setOperateUserId(Integer.parseInt(employee.getEmpNo()));
				dimension.setOperateUserName(employee.getEmpName());
				if(dimension!=null){
					keyService.insert(dimension);
				}
			}
		}
		return "redirect:/keyword/toList";
	}
	
	/**
	 * @param id
	 * @param model
	 * @return
	 * @author liuhg
	 * @Description 修改前查询
	 */
	@RequestMapping(value="/preUpdate")
	public String preUpdate(Integer id,Model model){
		Dimension dimension=keyService.selectDimensionById(id);
		if(dimension!=null){
			model.addAttribute("dimension", dimension);
		}
		return "keyword/addDimension";
	}
	
	/**
	 * @return
	 * @author liuhg
	 * @Description 查询操作人员，返回json格式
	 */
	@RequestMapping(value="/selectOperate")
	public @ResponseBody Map<String,Object> selectOperate(){
		
		Map<String,Object> map=new HashMap<String,Object>();
		List<Dimension> operateList=keyService.selectOperate();
		if(operateList!=null&&operateList.size()>0){
			map.put("operateList", operateList);
		}
		return map;
	}
	
	@RequestMapping(value="/selectDim")
	public @ResponseBody Map<String,Object> selectDim(){
		
		Map<String,Object> map=new HashMap<String,Object>();
		List<String> conditions=new ArrayList<String>();
		conditions.add("d.status='1'");
		List<Dimension> dimList=keyService.queryByPage(conditions);
		if(dimList!=null&&dimList.size()>0){
			map.put("dimList", dimList);
		}
		return map;
	}
	
	/**
	 * @param id
	 * @param response
	 * @author liuhg
	 * @Description 单个删除
	 */
	@RequestMapping(value="/deleteDie")
	public void deleteDie(Integer id,HttpServletResponse response){
		keyService.deleteDie(id);
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().print("删除成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param ids
	 * @author liuhg
	 * @Description 批量删除
	 */
	@RequestMapping(value="/deleteAllDim")
	public void deleteAllDim(String ids,HttpServletResponse response){
		System.out.println(ids);
		
		if(ids!=null&&!"".equals(ids)){
			String[] idarr=ids.split(",");
			int[] id=new int[idarr.length];
			for(int x=0;x<idarr.length;x++){
				id[x]=Integer.parseInt(idarr[x]);
			}
			keyService.deleteAllDim(id);
			response.setCharacterEncoding("utf-8");
			try {
				response.getWriter().print("删除成功");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @return
	 * @author liuhg
	 * @Description 查新维度名称，返回json数据，页面判断输入名称是否已存在
	 */
	@RequestMapping(value="/selectDieName")
	public @ResponseBody Map<String,Object> selectDieName(){
		Map<String,Object> map=new HashMap<String,Object>();
		List<Dimension> dimNameList=keyService.selectDieName();
		if(dimNameList!=null){
			map.put("data",dimNameList);
		}
		return map;
	}
	
	@RequestMapping(value="/getCarStyleById")
	public @ResponseBody Map<String,Object> getCarStyleById(Integer pid){
		HashMap<String,Object> map=new HashMap<String,Object>();
		if(pid!=null&&!"".equals(pid)){
			map.put("pid", pid);
		}
		//根据品牌id获得的车型
		List<CarStyle> carStyleList=keyService.getCarStyleById(map);
		if(carStyleList!=null&&carStyleList.size()>0){
			map.put("carStyleList", carStyleList);
		}
		return map;
	}
}
