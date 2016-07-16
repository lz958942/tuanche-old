package com.tuanche.web.regist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tuanche.bean.admin.DecorateBase;
import com.tuanche.bean.admin.DecorateContent;
import com.tuanche.bean.admin.DecorateTemp;
import com.tuanche.bean.che.Subject;
import com.tuanche.bean.che.SubjectInfo;
import com.tuanche.bean.che.SubjectKind;
import com.tuanche.bean.che.SysConfig;
import com.tuanche.commons.util.Resources;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.mapper.che.read.SysConfigReadMapper;
import com.tuanche.service.che.CarProRegistService;
import com.tuanche.smc.common.page.impl.Pagination;
import com.tuanche.smc.web.controller.BaseController;
import com.tuanche.upload.UpLoadUtil;

@Controller
public class CarProRegistController extends BaseController{
	
	@Autowired
	private CarProRegistService carProRegistService;
	
	@Autowired
	private SysConfigReadMapper sysConfigReadMapper;
	/**
	 * @param subjectInfo
	 * @return
	 * @author liuhg
	 * @Description 页面展示所有报名信息
	 */
	@RequestMapping(value="/regist/toHome")
	public ModelAndView toHome(@ModelAttribute(value="subjectInfo")SubjectInfo subjectInfo){
		
		Map<String,Object> map=new HashMap<String,Object>();
		//分页信息
		Pagination page=subjectInfo.getPage();
		if(page==null){
			page=new Pagination();
		}
		Pagination.threadLocal.set(page);
		List<String> condition=new ArrayList<String>();
		condition.add("1=1");
		if(subjectInfo!=null){
			if(subjectInfo.getUserName()!=null&&!"".equals(subjectInfo.getUserName())){
				condition.add("ti.username like '%"+subjectInfo.getUserName()+"%'");
			}
			if(subjectInfo.getId()!=null&&!"".equals(subjectInfo.getId())){
				condition.add("ti.id='"+subjectInfo.getId()+"'");
			}
			if(subjectInfo.getNewKindId()!=null&&!"".equals(subjectInfo.getNewKindId())){
				condition.add("ti.new_kind_id='"+subjectInfo.getNewKindId()+"'");
			}
			if(subjectInfo.getStyleId()!=null&&!"".equals(subjectInfo.getStyleId())){
				condition.add("tk.id='"+subjectInfo.getStyleId()+"'");
			}
			if(subjectInfo.getPhone()!=null&&!"".equals(subjectInfo.getPhone())){
				condition.add("ti.phone like '%"+subjectInfo.getPhone()+"%'");
			}
			if(subjectInfo.getZtId()!=null&&!"".equals(subjectInfo.getZtId())){
				condition.add("ts.id='"+subjectInfo.getZtId()+"'");
			}
			if(subjectInfo.getId()!=null&&!"".equals(subjectInfo.getId())){
				condition.add("ti.id='"+subjectInfo.getId()+"'");
			}
			if(subjectInfo.getStartTime()!=null&&!"".equals(subjectInfo.getStartTime())){
				condition.add("ti.addtime>='"+subjectInfo.getStartTime()+"'");
			}
			if(subjectInfo.getEndTime()!=null&&!"".equals(subjectInfo.getEndTime())){
				condition.add("ti.addtime<='"+subjectInfo.getEndTime()+"'");
			}
			if(subjectInfo.getStatus()!=null&&!"".equals(subjectInfo.getStatus())){
				condition.add("ti.status='"+subjectInfo.getStatus()+"'");
			}
			map.put("subjectInfo", subjectInfo);
		}
		List<SubjectInfo> subInfList=carProRegistService.queryByPage(condition);
		//加载装饰分类
		List<SubjectKind> subKindList=carProRegistService.styleList();
		//加载所有专题
		List<Subject> subjectList=carProRegistService.ztList(null);
		if(subjectList!=null&&subjectList.size()>0){
			map.put("subjectList", subjectList);
		}
		if(subKindList!=null&&subKindList.size()>0){
			map.put("subKindList", subKindList);
		}
		if(subInfList!=null&&subInfList.size()>0){
			map.put("subInfList", subInfList);
		}
		map.put("page",Pagination.threadLocal.get());
		List<String> list1=new ArrayList<String>();
		list1.add("decorateType");
		GlobalConstants.kindList=sysConfigReadMapper.getCodeByKey(list1);
		map.put("kindList", GlobalConstants.kindList);
		return new ModelAndView("/proregist/list",map);
	}
	
	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description json格式专题列表
	 */
	@RequestMapping(value="/regist/ztList")
	public @ResponseBody Map<String,Object> ztList(String id){
		Map<String,Object> map=new HashMap<String,Object>();
		Subject subject=new Subject();
		Integer kindId=null;
		if(id!=null&&!"".equals(id)){
			kindId=Integer.parseInt(id);
			subject.setKindId(kindId);
		}
		List<Subject> subjectList=carProRegistService.ztList(subject);
		if(subjectList!=null&&subjectList.size()>0){
			map.put("subjectList", subjectList);
		}else{
			map.put("subjectList",null);
		}
		return map;
	}
	
	/**************************汽车装饰管理*******************************************/
	/**
	 * @param model
	 * @return
	 * @author liuhg
	 * @Description 装饰基本信息添加页面
	 */
	@RequestMapping("/decorate/toBase")
	public String toBase(Model model){
		List<String> list1=new ArrayList<String>();
		List<String> list2=new ArrayList<String>();
		list1.add("decorateType");
		list2.add("decTitleType");
		GlobalConstants.kindList=sysConfigReadMapper.getCodeByKey(list1);
		GlobalConstants.plateList=sysConfigReadMapper.getCodeByKey(list2);
		model.addAttribute("citys", GlobalConstants.districtMap);
		model.addAttribute("picHost", Resources.getString("zt.picUrl"));
		model.addAttribute("kindList", GlobalConstants.kindList);
		return "proregist/addbase";
	}
	
	/**
	 * @param request
	 * @param response
	 * @author liuhg
	 * @Description 图片上传
	 */
	@RequestMapping("/decorate/uploadPic")
	public void uploadPic(HttpServletRequest request,HttpServletResponse response){
		UpLoadUtil upLoadUtil=new UpLoadUtil();
		upLoadUtil.uploadPicNoFtp(request, response);
	}
	
	/**
	 * @param decorateBase
	 * @param session
	 * @return
	 * @author liuhg
	 * @Description 装饰基本信息添加
	 */
	@RequestMapping("/decorate/addDecorateBase")
	public String addDecorateBase(HttpServletRequest request,DecorateBase decorateBase,HttpSession session){
		Employee employee=(Employee)session.getAttribute(GlobalConstants.SESSION_EMP);
		if(employee!=null){
			decorateBase.setAddUserId(Integer.parseInt(employee.getEmpNo()));
			decorateBase.setAddUserName(employee.getEmpName());
		}
		if(decorateBase.getId()!=null&&!"".equals(decorateBase.getId())){
			carProRegistService.updateBase(decorateBase,request);
		}else{
			carProRegistService.addDecorateBase(decorateBase,request);
		}
		return "redirect:/decorate/toBaselist";
	}
	
	/**
	 * @param decorateBase
	 * @param model
	 * @return
	 * @author liuhg
	 * @Description 装饰信息展示
	 */
	@RequestMapping("/decorate/toBaselist")
	public String tonewDecoratelist(DecorateBase decorateBase,Model model){
		List<String> condition=new ArrayList<String>();
		condition.add("d.isdel='0'");
		Pagination page=decorateBase.getPage();
		if(page==null){
			page=new Pagination();
		}
		Pagination.threadLocal.set(page);
		if(decorateBase.getId()!=null&&!"".equals(decorateBase.getId())){
			condition.add("d.id="+decorateBase.getId());
		}
		if(decorateBase.getKindId()!=null&&!"".equals(decorateBase.getKindId())){
			condition.add("d.kind_id="+decorateBase.getKindId());
		}
		if(decorateBase.getCityId()!=null&&!"".equals(decorateBase.getCityId())){
			condition.add("d.city_id="+decorateBase.getCityId());
		}
		if(decorateBase.getStatus()!=null&&!"".equals(decorateBase.getStatus())){
			condition.add("d.status="+decorateBase.getStatus());
		}
		if(decorateBase.getTitle()!=null&&!"".equals(decorateBase.getTitle())){
			condition.add("d.title like '%"+decorateBase.getTitle()+"%'");
		}
		if(decorateBase.getStartTime()!=null&&!"".equals(decorateBase.getStartTime())){
			condition.add("d.add_time>='"+decorateBase.getStartTime()+"'");
		}
		if(decorateBase.getEndTime()!=null&&!"".equals(decorateBase.getEndTime())){
			condition.add("d.add_time<='"+decorateBase.getEndTime()+"'");
		}
		List<DecorateBase> decBaseList=carProRegistService.selectByPage(condition);
		if(decBaseList!=null&&decBaseList.size()>0){
			model.addAttribute("decBaseList", decBaseList);
		}
		List<String> list1=new ArrayList<String>();
		List<String> list2=new ArrayList<String>();
		list1.add("decorateType");
		list2.add("decTitleType");
		GlobalConstants.kindList=sysConfigReadMapper.getCodeByKey(list1);
		GlobalConstants.plateList=sysConfigReadMapper.getCodeByKey(list2);
		model.addAttribute("page", Pagination.threadLocal.get());
		model.addAttribute("decorateBase", decorateBase);
		model.addAttribute("kindList", GlobalConstants.kindList);
		model.addAttribute("citys", GlobalConstants.districtMap);
		return "proregist/newDecoratelist";
	}
	
	/**
	 * @param id
	 * @author liuhg
	 * @Description 根据id查询 
	 */
	@RequestMapping("/decorate/selectById")
	public String selectById(Integer id,Model model){
		DecorateBase decorateBase= carProRegistService.selectById(id);
		if(decorateBase!=null){
			model.addAttribute("decorateBase", decorateBase);
		}
		List<String> list1=new ArrayList<String>();
		List<String> list2=new ArrayList<String>();
		list1.add("decorateType");
		list2.add("decTitleType");
		GlobalConstants.kindList=sysConfigReadMapper.getCodeByKey(list1);
		GlobalConstants.plateList=sysConfigReadMapper.getCodeByKey(list2);
		model.addAttribute("citys", GlobalConstants.districtMap);
		model.addAttribute("picHost", Resources.getString("zt.picUrl"));
		model.addAttribute("kindList", GlobalConstants.kindList);
		return "proregist/addbase";
	}
	
	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description 扩展属性页面
	 */
	@RequestMapping("/decorate/toExpendList")
	public String toExpendList(Integer bid,DecorateTemp decorateTemp, Model model){
		List<String> condition=new ArrayList<String>();
		condition.add("t.isdel='0'");
		condition.add("t.base_id="+bid);
		Pagination page=decorateTemp.getPage();
		if(page==null){
			page=new Pagination();
		}
		Pagination.threadLocal.set(page);
		if(decorateTemp!=null){
			if(decorateTemp.getPlateId()!=null&&!"".equals(decorateTemp.getPlateId())){
				condition.add("t.plate_id="+decorateTemp.getPlateId());
			}
			if(decorateTemp.getTitle()!=null&&!"".equals(decorateTemp.getTitle())){
				condition.add("t.title like '%"+decorateTemp.getTitle()+"%'");
			}
			if(decorateTemp.getTitleContentStyle()!=null&&!"".equals(decorateTemp.getTitleContentStyle())){
				condition.add("t.title_content_style="+decorateTemp.getTitleContentStyle());
			}
			if(decorateTemp.getTitleShowStyle()!=null&&!"".equals(decorateTemp.getTitleShowStyle())){
				condition.add("t.title_show_style="+decorateTemp.getTitleShowStyle());
			}
			decorateTemp.setBaseId(bid);
			model.addAttribute("decorateTemp", decorateTemp);
		}
		List<String> list1=new ArrayList<String>();
		List<String> list2=new ArrayList<String>();
		list1.add("decorateType");
		list2.add("decTitleType");
		GlobalConstants.kindList=sysConfigReadMapper.getCodeByKey(list1);
		GlobalConstants.plateList=sysConfigReadMapper.getCodeByKey(list2);
		model.addAttribute("baseId", bid);
		model.addAttribute("picHost", Resources.getString("zt.picUrl"));
		model.addAttribute("plateList", GlobalConstants.plateList);
		model.addAttribute("page", Pagination.threadLocal.get());
		List<DecorateTemp> decTempList=carProRegistService.selectTempByPage(condition);
		if(decTempList!=null&&decTempList.size()>0){
			model.addAttribute("decTempList", decTempList);
		}
		return "proregist/expendList";
	}
	
	/**
	 * @param tempId
	 * @param id
	 * @param model
	 * @return
	 * @author liuhg
	 * @Description 内容修改前查询
	 */
	@RequestMapping(value="/decorate/selectContentById")
	public String selectContentById(Integer id,Integer baseId,String type,Model model){
		Map<String,Object> map=carProRegistService.selectContentById(id);
		List<DecorateContent> wenziList=(List<DecorateContent>) map.get("wenziList");
		List<DecorateContent> picList=(List<DecorateContent>) map.get("picList");
		List<DecorateContent> tuwenList=(List<DecorateContent>) map.get("tuwenList");
		model.addAttribute("baseId", baseId);
		model.addAttribute("tempId", id);
		model.addAttribute("titleType", type);
		model.addAttribute("picHost", Resources.getString("zt.picUrl"));
		if(wenziList!=null&&wenziList.size()>0){
			model.addAttribute("wenziList", wenziList);
		}
		if(picList!=null&&picList.size()>0){
			model.addAttribute("picList", picList);
		}
		if(tuwenList!=null&&tuwenList.size()>0){
			model.addAttribute("tuwenList", tuwenList);
		}
		return "proregist/addContent";
	}
	
	/**
	 * @param request
	 * @param session
	 * @return
	 * @author liuhg
	 * @Description 添加小标题
	 */
	@RequestMapping(value="/decorate/addDecPlate")
	public String addDecPlate(HttpServletRequest request,HttpSession session){
		List<DecorateTemp> list=new ArrayList<DecorateTemp>();
		Employee employee=(Employee)session.getAttribute(GlobalConstants.SESSION_EMP);
		Integer baseId=null;
		Integer plateId=null;
		String baseIdStr=request.getParameter("baseId");
		String idStr=request.getParameter("tempid");
		if(baseIdStr!=null&&!"".equals(baseIdStr)){
			baseId=Integer.parseInt(baseIdStr);
		}
		String plateIdStr=request.getParameter("plateId");
		if(plateIdStr!=null&&!"".equals(plateIdStr)){
			plateId=Integer.parseInt(plateIdStr);
		}
		String[] title=request.getParameterValues("title");
		String[] titleContentStyle=request.getParameterValues("titleContentStyle");
		String[] titleShowStyle=request.getParameterValues("titleShowStyle");
		String[] sort=request.getParameterValues("sort");
		//有小标题的板块
		if(title.length>0){
			for(int x=0;x<title.length;x++){
				DecorateTemp decorateTemp=new DecorateTemp();
				decorateTemp.setTitle(title[x]);
				decorateTemp.setTitleContentStyle(titleContentStyle[x]);
				decorateTemp.setTitleShowStyle(titleShowStyle[x]);
				decorateTemp.setSort(sort[x]);
				decorateTemp.setPlateId(plateId);
				decorateTemp.setBaseId(baseId);
				decorateTemp.setIsDel("0");
				if(employee!=null){
					decorateTemp.setAddUserId(Integer.parseInt(employee.getEmpNo()));
					decorateTemp.setAddUserName(employee.getEmpName());
				}
				list.add(decorateTemp);
			}
		}
		if(idStr==null||"".equals(idStr)){
			carProRegistService.addDecPlate(list);
		}else{
			Integer id=Integer.parseInt(idStr);
			//修改
			updatePlate(request,id);
		}
		return "redirect:/decorate/toExpendList?bid="+baseId+"";
	}
	
	/**
	 * @param request
	 * @param id
	 * @author liuhg
	 * @Description 修改
	 */
	private void updatePlate(HttpServletRequest request, Integer id) {
		DecorateTemp decorateTemp=new DecorateTemp();
		Integer baseId=null;
		Integer plateId=null;
		String baseIdStr=request.getParameter("baseId");
		String plateIdStr=request.getParameter("plateId");
		String title=request.getParameter("title");
		String titleContentStyle=request.getParameter("titleContentStyle");
		String titleShowStyle=request.getParameter("titleShowStyle");
		String sort=request.getParameter("sort");
		if(id!=null){
			decorateTemp.setId(id);
		}
		if(baseIdStr!=null&&!"".equals(baseIdStr)){
			baseId=Integer.parseInt(baseIdStr);
		}
		if(plateIdStr!=null&&!"".equals(plateIdStr)){
			plateId=Integer.parseInt(plateIdStr);
			
		}
		decorateTemp.setBaseId(baseId);
		decorateTemp.setPlateId(plateId);
		decorateTemp.setTitle(title);
		decorateTemp.setTitleContentStyle(titleContentStyle);
		decorateTemp.setTitleShowStyle(titleShowStyle);
		decorateTemp.setSort(sort);
		decorateTemp.setIsDel("0");
		carProRegistService.updatePlate(decorateTemp);
	}

	@RequestMapping("/decorate/deletePlate")
	public String deletePlate(Integer id,Integer baseId,HttpServletRequest request){
		carProRegistService.deletePlate(id);
		return "redirect:/decorate/toExpendList?bid="+baseId+"";
	}
	
	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description 根据id查询版板块信息
	 */
	@RequestMapping(value="/decorate/selectPlateById")
	public @ResponseBody Map<String,Object> selectPlateById(String id){
		Map<String,Object> map=new HashMap<String,Object>();
		DecorateTemp decorateTemp=carProRegistService.selectPlateById(Integer.parseInt(id));
		if(decorateTemp!=null){
			map.put("decorateTemp", decorateTemp);
		}
		return map;
	}
	
	/**
	 * @param request
	 * @return
	 * @author liuhg
	 * @Description 添加内容
	 */
	@RequestMapping(value="/decorate/addContent")
	public String addContent(HttpServletRequest request){
		Integer baseId=Integer.parseInt(request.getParameter("baseId"));
		Integer tempId=Integer.parseInt(request.getParameter("tempId"));
		String titleType=request.getParameter("titleType");
		if("1".equals(titleType)){
			String dcTitles[]=request.getParameterValues("dctitle");
			String contents[]=request.getParameterValues("content");
			String sorts[]=request.getParameterValues("dcSort");
			String tids[]=request.getParameterValues("tid");
			for(int x=0;x<dcTitles.length;x++){
				DecorateContent decorateContent=new DecorateContent();
				decorateContent.setBaseId(baseId);
				decorateContent.setTempId(tempId);
				decorateContent.setIsdel("0");
				decorateContent.setDctitle(dcTitles[x]);
				decorateContent.setContent(contents[x]);
				decorateContent.setDcSort(sorts[x]);
				if(tids!=null&&!"".equals(tids)){
					if(tids[x]!=null&&!"".equals(tids[x])){
						decorateContent.setId(Integer.parseInt(tids[x]));
						carProRegistService.updateContent(decorateContent,request);
					}else{
						carProRegistService.addContent(decorateContent,request);
					}
				}else{
					carProRegistService.addContent(decorateContent,request);
				}
			}
		}else if("2".equals(titleType)){
			String picUrls[]=request.getParameterValues("picUrls");
			String sorts[]=request.getParameterValues("picSort");
			String pids[]=request.getParameterValues("pid");
			for(int x=0;x<picUrls.length;x++){
				DecorateContent decorateContent=new DecorateContent();
				decorateContent.setBaseId(baseId);
				decorateContent.setTempId(tempId);
				decorateContent.setDcSort(sorts[x]);
				decorateContent.setIsdel("0");
				decorateContent.setPicUrl(picUrls[x]);
				if(pids!=null&&!"".equals(pids)){
					if(pids[x]!=null&&!"".equals(pids[x])){
						decorateContent.setId(Integer.parseInt(pids[x]));
						carProRegistService.updateContent(decorateContent,request);
					}else{
						carProRegistService.addContent(decorateContent,request);
					}
				}else{
					carProRegistService.addContent(decorateContent,request);
				}
			}
		}else if("3".equals(titleType)){
			String picUrls[]=request.getParameterValues("tpicUrl");
			String sorts[]=request.getParameterValues("tpSort");
			String contents[]=request.getParameterValues("contents");
			String wids[]=request.getParameterValues("wid");
			for(int x=0;x<sorts.length;x++){
				DecorateContent decorateContent=new DecorateContent();
				decorateContent.setIsdel("0");
				decorateContent.setBaseId(baseId);
				decorateContent.setTempId(tempId);
				decorateContent.setPicUrl(picUrls[x]);
				decorateContent.setDcSort(sorts[x]);
				decorateContent.setContent(contents[x]);
				if(wids!=null&&!"".equals(wids)){
					if(wids[x]!=null&&!"".equals(wids[x])){
						decorateContent.setId(Integer.parseInt(wids[x]));
						carProRegistService.updateContent(decorateContent,request);
					}else{
						carProRegistService.addContent(decorateContent,request);
					}
				}else{
					carProRegistService.addContent(decorateContent,request);
				}
			}
		}
		return "redirect:/decorate/toExpendList?bid="+baseId+"";
	}
	
	/**
	 * @param id
	 * @param model
	 * @return
	 * @author liuhg
	 * @Description 内容修改前查询
	 */
	@RequestMapping("/decorate/selectContentByTempId")
	public void selectContentByIdResult(Integer id,Model model,HttpServletResponse response){
		Integer result=carProRegistService.selectContentByIdResult(id);
		sentResponseInfo(response,result+"");
		
	}
	
	/**
	 * @param response
	 * @param id
	 * @author liuhg
	 * @Description 删除内容
	 */
	@RequestMapping("/decorate/deleteContent")
	public void deleteContent(HttpServletResponse response,Integer id){
		carProRegistService.deleteContent(id);
		sentResponseInfo(response,"删除成功！");
		
	}
	
	/**
	 * @param id
	 * @param status
	 * @author liuhg
	 * @Description 上下线
	 */
	@RequestMapping(value="/decorate/changeOnline")
	public void changeOnline(Integer id,String status,HttpServletResponse response){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id",id);
		if("1".equals(status)){
			map.put("status", "2");
		}else{
			map.put("status", "1");
		}
		carProRegistService.changeOnline(map);
	}
	
	/**
	 * @param id
	 * @param response
	 * @author liuhg
	 * @Description 删除装饰文章
	 */
	@RequestMapping("/decorate/deleteDecorate")
	public void deleteDecorate(Integer id,HttpServletResponse response){
		carProRegistService.deleteDecorate(id);
	}
}
