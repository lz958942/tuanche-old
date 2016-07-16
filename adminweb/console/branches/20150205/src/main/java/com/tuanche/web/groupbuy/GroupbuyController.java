package com.tuanche.web.groupbuy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;




import com.tuanche.bean.che.BrandGroupbuy;
import com.tuanche.bean.che.CarstyleDomain;
import com.tuanche.bean.che.CarstyleGroupbuy;
import com.tuanche.bean.che.SearchBean;
import com.tuanche.commons.util.StringUtils;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.read.EmployeeReadMapper;
import com.tuanche.console.util.DateEditor;
import com.tuanche.console.util.Encriptor;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.console.util.Pager;
import com.tuanche.console.util.RequestUtils;
import com.tuanche.console.util.Resources;
import com.tuanche.console.web.AuthUtil;
import com.tuanche.dao.che.CarstyleDao;
import com.tuanche.mapper.che.read.BrandGroupbuyReadMapper;
import com.tuanche.mapper.che.read.CarstyleGroupbuyReadMapper;
import com.tuanche.mapper.che.write.BrandGroupbuyWriteMapper;
import com.tuanche.mapper.che.write.CarstyleGroupbuyWriteMapper;




@Controller
public class GroupbuyController 

{


	
	@Autowired
	private BrandGroupbuyReadMapper brandGroupbuyReadMapper;
	@Autowired
	private CarstyleGroupbuyReadMapper carstyleGroupbuyReadMapper;
	@Autowired
	private BrandGroupbuyWriteMapper brandGroupbuyWriteMapper;
	@Autowired
	private CarstyleGroupbuyWriteMapper carstyleGroupbuyWriteMapper;
	@Autowired
	private EmployeeReadMapper employeeReadMapper;
	@Resource
	private CarstyleDao carstyleDao;
	
	@RequestMapping(value="/groupbuy/list")
	public String list(HttpServletRequest request,ModelMap model,HttpSession session){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("brandId",RequestUtils.getInt(request,"brandId"));
		map.put("cityId",RequestUtils.getInt(request,"cityId"));
		String stateName=request.getParameter("stateName");
		SearchBean searchBean=(SearchBean) session.getAttribute("searchBean");
		int tmpCpage=0;
		//待条件
		if(searchBean!=null){
			if(searchBean.getTmcBrandId()!=null){
				map.put("brandId", searchBean.getTmcBrandId());
			}
			if(searchBean.getTmcCarstyleId()!=null){
				map.put("carstyleId", searchBean.getTmcCarstyleId());
			}
			if(searchBean.getTmcCityId()!=null){
				map.put("cityId", searchBean.getTmcCityId());
			}
			if(searchBean.getTmcStateName()!=null){
				map.put("stateName", searchBean.getTmcStateName());
			}
			if(searchBean.getTmpCpage()!=null){
				tmpCpage=searchBean.getTmpCpage();
			}
			session.removeAttribute("searchBean");
		}
		if(stateName!=null&& stateName.length()>0){
			map.put("stateName",Integer.valueOf(stateName));
		}
		if(StringUtils.isNotEmpty(request.getParameter("carstyleId")))
			map.put("carstyleId",RequestUtils.getInt(request,"carstyleId"));
		int cpage = RequestUtils.getInt(request, "cpage",0);
		if(cpage==0){
			cpage=tmpCpage;
		}
		int totalRows = RequestUtils.getInt(request, "totalRows",0);
		if (totalRows <= 0) {
			totalRows=map.containsKey("carstyleId")?carstyleGroupbuyReadMapper.count(map):brandGroupbuyReadMapper.count(map);
		}
		Pager page = new Pager(cpage, totalRows,GlobalConstants.PAGE_SIZE);
		page.setLinkUrl("/groupbuy/list?totalRows="+ totalRows);
		addCondition(page, request);
		map.put("start",page.getStartRow());
		map.put("pageSize",GlobalConstants.PAGE_SIZE);
		map.put("orderStr","id desc");
		
		List<CarstyleGroupbuy> list1=null;
		List<BrandGroupbuy> list2=null;
		if(map.containsKey("carstyleId"))
		{
			list1=carstyleGroupbuyReadMapper.select(map);
			if(list1!=null&&list1.size()>0)
			{
				SortByGroup sort=new SortByGroup();
				Collections.sort(list1, sort);
			}
		}
		else
			list2=brandGroupbuyReadMapper.select(map);
		model.addAttribute("groupbuyList",map.containsKey("carstyleId")?list1:list2);
		model.addAttribute("citys",GlobalConstants.districtMap);//AuthUtil.checkedCityMap(request));
		model.addAttribute("brands",GlobalConstants.brandMap);//AuthUtil.checkedBrandMap(request));
		model.addAttribute("search",map);
		model.addAttribute("addAction",map.containsKey("carstyleId")?"cadd":"badd");
		model.addAttribute("delAction",map.containsKey("carstyleId")?"cdel":"bdel");
		request.setAttribute("pb",page);
		return "groupbuy/list";
	}
	
	@RequestMapping(value="/groupbuy/badd")
	public String badd(HttpServletRequest request,ModelMap model,HttpServletResponse response,
			@ModelAttribute(value="id")BrandGroupbuy brandGroupbuy,SearchBean searchBean,HttpSession session) throws ServletException, IOException
			{
		String token=brandGroupbuy.getToken();
		System.out.println(searchBean);
		if(StringUtils.isEmpty(token) || !token.
				equalsIgnoreCase(getToken(brandGroupbuy.getId()>0?brandGroupbuy.getId():brandGroupbuy.getCityId(),brandGroupbuy.getId()>0?0:brandGroupbuy.getBrandId()))
				){
			request.setAttribute(GlobalConstants.ERROR_KEY, "非法操作");
			request.getRequestDispatcher(GlobalConstants.ERROR_PAGE).forward(request, response);
		}
		if(request.getMethod().equalsIgnoreCase("post")){
			Employee emp=(Employee)request.getSession(true).getAttribute(GlobalConstants.SESSION_EMP);
			if(brandGroupbuy.getId()>0){
				brandGroupbuy.setLastUserId(emp.getId());
				brandGroupbuyWriteMapper.update(brandGroupbuy);
				carstyleGroupbuyWriteMapper.updateTime(brandGroupbuy);
				//修改
				return "redirect:/groupbuy/list";
			}
			brandGroupbuy.setAddUserId(emp.getId());
		
			brandGroupbuyWriteMapper.insert(brandGroupbuy);
			List<CarstyleGroupbuy> list=getCarstyleGroupbuyList(brandGroupbuy);
			if(list!=null &&list.size()>0){
				carstyleGroupbuyWriteMapper.insertList(list);
			}
			return "redirect:/groupbuy/list";
			
		}
		session.setAttribute("searchBean", searchBean);
		brandGroupbuy=brandGroupbuy.getId()>0?brandGroupbuyReadMapper.find(brandGroupbuy.getId()):brandGroupbuy;
		brandGroupbuy.setToken(token);
		model.addAttribute("groupbuy",brandGroupbuy);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("roleIds",GlobalConstants.ROLE_SALE_ID);
		map.put("cityCode",GlobalConstants.districtIdCitycodeMap.get(brandGroupbuy.getCityId()));
		model.addAttribute("sales",employeeReadMapper.selectEmployeeInit(map));
		return "groupbuy/badd";
	}
	
	@RequestMapping(value="/groupbuy/cadd")
	public String cadd(HttpServletRequest request,ModelMap model,
			@ModelAttribute(value="carstyleGroupbuy")CarstyleGroupbuy carstyleGroupbuy,HttpServletResponse response,SearchBean searchBean,HttpSession session) throws ServletException, IOException{
		String token=carstyleGroupbuy.getToken();
		if(StringUtils.isEmpty(token) || !token.
				equalsIgnoreCase(getToken(carstyleGroupbuy.getId()>0?carstyleGroupbuy.getId():carstyleGroupbuy.getCityId(),carstyleGroupbuy.getId()>0?0:carstyleGroupbuy.getCarstyleId()))
				){
			request.setAttribute(GlobalConstants.ERROR_KEY, "非法操作");
			request.getRequestDispatcher(GlobalConstants.ERROR_PAGE).forward(request, response);
		}
		if(request.getMethod().equalsIgnoreCase("post")){
			Employee emp=(Employee)request.getSession(true).getAttribute(GlobalConstants.SESSION_EMP);
			String sort_old_s=request.getParameter("sort_old");
			if(carstyleGroupbuy.getId()>0)
			{
			carstyleGroupbuy.setLastUserId(emp.getId());
				carstyleGroupbuyWriteMapper.update(carstyleGroupbuy);
				if(sort_old_s!=null&&sort_old_s.length()>0)
				{
					int sort_old=Integer.parseInt(String.valueOf(sort_old_s));
					if(carstyleGroupbuy.getBaseSeq()!=sort_old)
					{
						carstyleGroupbuyWriteMapper.updateCarStyleSort(carstyleGroupbuy.getId(),carstyleGroupbuy.getCityId(),carstyleGroupbuy.getBrandId(),carstyleGroupbuy.getBaseSeq(),sort_old);
					}
					
				}
				return "redirect:/groupbuy/list";
			}
			carstyleGroupbuy.setAddUserId(emp.getId());
			carstyleGroupbuyWriteMapper.insert(carstyleGroupbuy);
			return "redirect:/groupbuy/list";
		}
		session.setAttribute("searchBean", searchBean);
		carstyleGroupbuy=carstyleGroupbuy.getId()>0?carstyleGroupbuyReadMapper.find(carstyleGroupbuy.getId()):carstyleGroupbuy;
		carstyleGroupbuy.setToken(token);
		if(carstyleGroupbuy.getBaseSeq()==0)
		{
			int maxSort=carstyleGroupbuyReadMapper.maxSort(carstyleGroupbuy.getCityId(),carstyleGroupbuy.getBrandId());
			carstyleGroupbuy.setBaseSeq(maxSort);
		}
		model.addAttribute("groupbuy",carstyleGroupbuy);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("roleIds",GlobalConstants.ROLE_SALE_ID);
		map.put("cityCode",GlobalConstants.districtIdCitycodeMap.get(carstyleGroupbuy.getCityId()));
		model.addAttribute("sales",employeeReadMapper.selectEmployeeInit(map));
		return "groupbuy/cadd";
	}
	
	
	@RequestMapping(value="/common/getCarstyle")
	@ResponseBody
	public List<CarstyleDomain> getCarstyle(@RequestParam(value="brandId")int brandId){
		//if(brandId<=0 || !GlobalConstants.carstyleMap.containsKey(brandId)) return null;
		List<CarstyleDomain> clists = carstyleDao.selectStyleByIdName(brandId);
		return clists;
	}
	
	
	@RequestMapping(value="/common/getCarstyleOk")
	@ResponseBody
	public List<CarstyleDomain> getCarstyleOk(@RequestParam(value="brandId")int brandId){
		//if(brandId<=0 || !GlobalConstants.carstyleMap.containsKey(brandId)) return null;
		List<CarstyleDomain> clists = carstyleDao.selectOkStyleByIdName(brandId);
		return clists;
	}
	
	@RequestMapping(value="/common/checkgroupbuy")
	@ResponseBody
	public String checkgroupbuy(@RequestParam(value="cityId")int cityId,
			@RequestParam(value="brandId")int brandId,@RequestParam(value="carstyleId")int carstyleId){
		boolean	flag=(carstyleId>0?(brandGroupbuyReadMapper.check(cityId, brandId)==1&&carstyleGroupbuyReadMapper.check(cityId, carstyleId)==0):
			brandGroupbuyReadMapper.check(cityId, brandId)==0);
		return !flag?"":getToken(cityId,carstyleId>0?carstyleId:brandId);
	}
	
	
	private String getToken(int firstId,int secondId){
		return Encriptor.getMD5(Resources.getWebMessage("update.auth")+firstId+""+(secondId>0?secondId:""));
	}
	
	@SuppressWarnings("unchecked")
	private void addCondition(Pager pager,HttpServletRequest request){
		Enumeration<String> names=request.getParameterNames();
		while(names.hasMoreElements()){
			String s=names.nextElement();
			if(StringUtils.isNotEmpty(request.getParameter(s))){
				pager.addCondition(s,request.getParameter(s));
			}
		}
	}
	
	
	private List<CarstyleGroupbuy> getCarstyleGroupbuyList(BrandGroupbuy brandGroupbuy){
		List<CarstyleGroupbuy> list=new ArrayList<CarstyleGroupbuy>();
		CarstyleGroupbuy carstyleGroupbuy=null;
		if(!GlobalConstants.carstyleOKMap.containsKey(brandGroupbuy.getBrandId())) return null;
		int seq=0;
		for(Entry<Integer,String> entry:GlobalConstants.carstyleOKMap.get(brandGroupbuy.getBrandId()).entrySet())
		{
			seq++;
			carstyleGroupbuy=new CarstyleGroupbuy(0,0,0);
			carstyleGroupbuy.setBrandId(brandGroupbuy.getBrandId());
			carstyleGroupbuy.setCityId(brandGroupbuy.getCityId());
			carstyleGroupbuy.setCarstyleId(entry.getKey());
			carstyleGroupbuy.setTitle(GlobalConstants.districtMap.get(brandGroupbuy.getCityCode()).getName()+entry.getValue()+"团购");
			carstyleGroupbuy.setGroupbuyDate(brandGroupbuy.getGroupbuyDate());
			carstyleGroupbuy.setSalerId(brandGroupbuy.getSalerId());
			carstyleGroupbuy.setAddUserId(brandGroupbuy.getAddUserId());
			carstyleGroupbuy.setGroupbuyLight(brandGroupbuy.getGroupbuyLight());
			carstyleGroupbuy.setBaseSeq(seq);
			carstyleGroupbuy.setCarNice(entry.getValue());
//			carstyleGroupbuy.setSellBaseNum(0);
//			carstyleGroupbuy.setPassBaseNum(0);
//			carstyleGroupbuy.setManBaseNum(0);
			list.add(carstyleGroupbuy);
		}
		/*
		if(list!=null&&list.size()>0)
		{
			SortByGroup sort=new SortByGroup();
			Collections.sort(list, sort);
		}
		*/	
		return list;
	}
	
	
	
	
	@RequestMapping(value="/common/getCarstyleGroupbuy")
	@ResponseBody
	public List<CarstyleGroupbuy> getCarstyleGroupbuy(HttpServletRequest request){
		String data=request.getParameter("data");
	if(StringUtils.isEmpty(data)) return null;
		Map<String,Object> map=new HashMap<String, Object>();
		String[] s=data.split("-");
		map.put("cityId",s[0]);
		map.put("brandId",s[1]);
		
		List<CarstyleGroupbuy> list=carstyleGroupbuyReadMapper.select(map);
		if(list!=null&&list.size()>0)
		{
			SortByGroup sort=new SortByGroup();
			Collections.sort(list, sort);
		}
	
		return list;
	}
	
	@RequestMapping(value="/groupbuy/bdel")
	@ResponseBody
	public int bdel(HttpServletRequest request,
			@RequestParam(value="cityId")int cityId,@RequestParam(value="id")int id,
			@RequestParam(value="brandId")int brandId,@RequestParam(value="state")int state){
		String token=request.getParameter("token");
		if(StringUtils.isEmpty(token)) return 0;
		if(token.equalsIgnoreCase(getToken(id,0))){
			brandGroupbuyWriteMapper.updateState(id,0,0, state);
			if(AuthUtil.checkAuth(request,"/groupbuy/cdel")){
				carstyleGroupbuyWriteMapper.updateState(0,cityId,brandId, state);
			}
		}
		return 0;
	}
	
	@RequestMapping(value="/groupbuy/cdel")
	@ResponseBody
	public int cdel(HttpServletRequest request,@RequestParam(value="id")int id,@RequestParam(value="cityId")int cityId,
			@RequestParam(value="brandId")int brandId,@RequestParam(value="state")int state){
		String token=request.getParameter("token");
		if(StringUtils.isEmpty(token)) return 0;
		if(token.equalsIgnoreCase(getToken(id,0))){
			carstyleGroupbuyWriteMapper.updateState(id,0,0, state);
			if(state==0){
				brandGroupbuyWriteMapper.updateState(0,cityId,brandId,0);
			}
		}
		if(state==-1){
			brandGroupbuyWriteMapper.brandCascadeDelBycar(brandId);
		}else{
			//brandGroupbuyWriteMapper.brandCascadeRestoreBycar(brandId);
		}
		return 0;
	}
	
	@InitBinder
	 public void initBinder(HttpServletRequest request,ServletRequestDataBinder binder) throws Exception {   
		binder.registerCustomEditor(Date.class, new DateEditor()); 
  } 
	@RequestMapping(value="/groupbuy/list.do")
	@ResponseBody
	public String batchUpdate(@Param("stateName")Integer stateName,@Param("cityIdd")Integer cityIdd,@Param("content")String content,HttpSession session) {
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		  if(sessionUser!=null&&sessionUser.getId()!=null){	
			  System.out.println(stateName+"ffff"+cityIdd);
		if(stateName!=null && cityIdd!=null){
			  System.out.println(stateName+"dddd"+cityIdd);
			carstyleGroupbuyWriteMapper.batchUpdate(stateName,content,sessionUser.getId(),cityIdd); 
			brandGroupbuyWriteMapper.batchUpdate(cityIdd,stateName,content,sessionUser.getId());
			}
		  }
		return "1";
	}
	@RequestMapping(value="/common/getContentByCityId")
	@ResponseBody
	public String getContentByCityId(@Param("cityId")Integer cityId,@Param("stateName")Integer stateName) {
		if(cityId!=null && stateName !=null){
			 List<BrandGroupbuy> brands=brandGroupbuyReadMapper.findByCityId(cityId, stateName);
		if(brands!=null && brands.size()!=0 && brands.get(0)!=null ){
			return brands.get(0).getGroupbuyLight();
		}
		}
		return "";
	}
}
