package com.tuanche.smc.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tuanche.commons.util.CookieUtil;
import com.tuanche.commons.util.IpUtil;
import com.tuanche.commons.util.Resources;
import com.tuanche.commons.util.StringUtils;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.info.bean.Brand;
import com.tuanche.info.bean.FriendLink;
import com.tuanche.info.bean.GroupBuy;
import com.tuanche.info.bean.News;
import com.tuanche.info.bean.Pager;
import com.tuanche.info.service.FriendLinkService;
import com.tuanche.info.service.IpService;
import com.tuanche.info.service.NewsService;
import com.tuanche.info.service.OnLineService;
import com.tuanche.info.service.SearchGroupBuyService;
import com.tuanche.info.util.Channel;
import com.tuanche.info.util.CommonUtil;
import com.tuanche.smc.common.Globals;
import com.tuanche.smc.common.page.impl.Pagination;
import com.tuanche.smc.domain.base.NewsClassify;
import com.tuanche.smc.domain.specialSubject.SpecialContent;
import com.tuanche.smc.domain.specialSubject.SpecialSubject;
import com.tuanche.smc.domain.specialSubject.Template;
import com.tuanche.smc.service.MemcacheService;
import com.tuanche.smc.service.SpecialSubjectService;
import com.tuanche.smc.util.FMUtils;
import com.tuanche.upload.FtpUtil;
import com.tuanche.upload.UpLoadUtil;

@Controller
@RequestMapping(value="/specialSubject")
public class SpecialSubjectController extends BaseController{
	
	@Autowired
	private SpecialSubjectService specialSubjectService;

	@Autowired
	private MemcacheService memcacheService;
	
	
	/**
	 * @param session
	 * @param model
	 * @return
	 * @author liuhg
	 * @Description 数据展示
	 */
	@RequestMapping(value="/home")
	public String toHome(HttpSession session,Model model){

		Pagination pagination;
		pagination=new Pagination();
		Pagination.threadLocal.set(pagination);
		List<String> conditions =new ArrayList<String>();
		conditions.add("t.sp_status='1'");
		List<SpecialSubject> specialByPage=specialSubjectService.queryByPage(conditions);
		List<Template> tempList=specialSubjectService.selectTemp();
		//为专题填充专题模板名称
		specialSubjectService.fullTpName(specialByPage);
		//查询专题操作人员
		List<SpecialSubject> operaList=specialSubjectService.selectSpecialUser();
		model.addAttribute("page", Pagination.threadLocal.get());
		model.addAttribute("specialByPage", specialByPage);
		model.addAttribute("operaList", operaList);
		model.addAttribute("tempList", tempList);
		return "specialSubject/home";
	}
	
	/**
	 * @param session
	 * @param model
	 * @return
	 * @author liuhg
	 * @Description 模板数据展示
	 */
	@RequestMapping(value="/tempHome")
	public String tempHome(Model model,HttpServletRequest request,Template template){
		Pagination pagination;
		pagination=new Pagination();
		Pagination.threadLocal.set(pagination);
		List<String> conditions =new ArrayList<String>();
		conditions.add("t.delete_flay='1'");
		List<Template> tempByPage=specialSubjectService.queryTempByPage(conditions);
		List<Template> createUser=specialSubjectService.selectTempUser();
		model.addAttribute("createUser", createUser);
		model.addAttribute("page", Pagination.threadLocal.get());
		model.addAttribute("tempByPage", tempByPage);
	
		return "specialSubject/tempHome";
	}
	
	@RequestMapping(value="/toAdd")
	public String toAdd(Model model){
		//添加页面模板下拉列表
		List<Template> tempList= specialSubjectService.selectTemp();
		model.addAttribute("tempList", tempList);
		return "specialSubject/addSpecialSubject";
	}
	
	@RequestMapping(value="/toAddTemp")
	public String toAddTemp(){
		return "specialSubject/addTemplate";
	}
	
	/**
	 * @param session
	 * @param specialSubject
	 * @param model
	 * @return
	 * @author liuhg
	 * @Description 条件查询
	 */
	@RequestMapping(value="/search")
	public String search(HttpSession session,SpecialSubject specialSubject,Model model){
		
		//获得查询条件
		Pagination page=specialSubject.getPage();
		Pagination.threadLocal.set(page);
		/*//获得当前操作用户
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);*/
		List<String> conditions=new ArrayList<String>();
		conditions.add("t.sp_status='1'");
		//组合查询条件
		if(specialSubject.getId()!=null&&!"".equals(specialSubject.getId())){
			 conditions.add("t.id="+specialSubject.getId());
		}
		if(specialSubject.getSpName()!=null&&!"".equals(specialSubject.getSpName())){
			conditions.add("t.sp_name like '%"+specialSubject.getSpName()+"%'");
		}
		if(specialSubject.getKeywords()!=null&&!"".equals(specialSubject.getKeywords())){
			conditions.add("t.keyword like '%"+specialSubject.getKeywords()+"%'");
		}
		if(specialSubject.getSpDesc()!=null&&!"".equals(specialSubject.getSpDesc())){
			conditions.add("t.sp_desc like '%"+specialSubject.getSpDesc()+"%'");
		}
		if(specialSubject.getCityId()!=null&&!"".equals(specialSubject.getCityId())){
			conditions.add("t.city_id="+specialSubject.getCityId());
		}
		if(specialSubject.getSpOnline()!=null&&!"".equals(specialSubject.getSpOnline())){
			conditions.add("t.online="+specialSubject.getSpOnline());
		}
		if(specialSubject.getBeginTime()!=null&&!"".equals(specialSubject.getBeginTime())){
			conditions.add("t.online_date >='"+specialSubject.getBeginTime()+"'");
		}
		if(specialSubject.getEndTime()!=null&&!"".equals(specialSubject.getEndTime())){
			conditions.add("t.online_date <='"+specialSubject.getEndTime()+"'");
		}
		if(specialSubject.getOperateUserId()!=null&&!"".equals(specialSubject.getOperateUserId())){
			conditions.add("t.operate_user_id ='"+specialSubject.getOperateUserId()+"'");
		}
		List<SpecialSubject> specialByPage=specialSubjectService.queryByPage(conditions);
		//为专题填充专题模板名称
		specialSubjectService.fullTpName(specialByPage);
		List<SpecialSubject> operaList=specialSubjectService.selectSpecialUser();
		model.addAttribute("operaList", operaList);
		model.addAttribute("specialByPage", specialByPage);
		model.addAttribute("specialSubject", specialSubject);
		model.addAttribute("page", Pagination.threadLocal.get());
		return "specialSubject/home";
	}
	
	@RequestMapping(value="/searchTemp")
	public String searchTemp(HttpSession session,Template template,Model model){
		
		Pagination.threadLocal.set(template.getPage());
		List<String> conditions=new ArrayList<String>();
		conditions.add("t.delete_flay='1'");
		if(template.getId()!=null&&!"".equals(template.getId())){
			 conditions.add("t.id="+template.getId());
		}
		if(template.getTpName()!=null&&!"".equals(template.getTpName())){
			conditions.add("t.tp_name like '%"+template.getTpName()+"%'");
		}
		if(template.getTpDesc()!=null&&!"".equals(template.getTpDesc())){
			conditions.add("t.tp_desc like '%"+template.getTpDesc()+"%'");
		}
		if(template.getCreateUserId()!=null&&!"".equals(template.getCreateUserId())){
			conditions.add("t.create_user_id="+template.getCreateUserId());
		}
		if(template.getCreateTime()!=null&&!"".equals(template.getCreateTime())){
			conditions.add("t.create_time >='"+template.getCreateTime()+"'");
		}
		List<Template> tempByPage=specialSubjectService.queryTempByPage(conditions);
		List<Template> createUser=specialSubjectService.selectTempUser();
		model.addAttribute("createUser", createUser);
		model.addAttribute("tempByPage", tempByPage);
		model.addAttribute("template", template);
		model.addAttribute("page", Pagination.threadLocal.get());
		return "specialSubject/tempHome";
	}
	
	/**
	 * @param session
	 * @param specialSubject
	 * @param specialTitles
	 * @param model
	 * @return
	 * @author liuhg
	 * @Description  添加专题和修改
	 */
	@RequestMapping(value="/addSpecialSubject")
	public String addSecialSubject(HttpSession session,SpecialSubject specialSubject,SpecialContent specialContent,HttpServletRequest request,Model model){
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		Integer pageNo=1;
		if(StringUtils.isNotEmpty(request.getParameter("pageNo"))){
			pageNo=Integer.parseInt(request.getParameter("pageNo"));
		}
		if(employee!=null){
			specialSubject.setOperateUserName(employee.getEmpName());
			specialSubject.setOperateUserId(Integer.parseInt(employee.getEmpNo()));
		}
		if(specialSubject.getId()!=null){
			specialSubjectService.updateSpecialSubject(specialSubject,specialContent,request);
		}else{
			specialSubjectService.addSpecialSubject(specialSubject,specialContent,request);
		}
		return "redirect:/specialSubject/search?page.pageNo="+pageNo;
	}
	
	@RequestMapping(value="/deletePic")
	public void deletePic(HttpServletResponse response,Integer id){
		specialSubjectService.deletePic(id);
		sentResponseInfo(response, "已删除");
	}
	
	/**
	 * @param id
	 * @param spOnline
	 * @return
	 * @author liuhg
	 * @Description 专题上下线
	 */
	@RequestMapping(value="/upOrDown")
	public void upOrDown(Integer id,Integer spOnline,PrintWriter out){
		specialSubjectService.upOrDown(id,spOnline);
	}
	
	/**
	 * @param id
	 * @author liuhg
	 * @Description 删除专题
	 */
	@RequestMapping(value="/deleteSp")
	public void deleteSp(Integer id,PrintWriter out){
		specialSubjectService.deleteSp(id);
	}
	
	/**
	 * @param id
	 * @author liuhg
	 * @Description 删除模板
	 */
	@RequestMapping(value="/deleteTp")
	public void deleteTp(Integer id,PrintWriter out){
		specialSubjectService.deleteTp(id);
	}
	
	/**
	 * @param id
	 * @param spOnline
	 * @param out
	 * @author liuhg
	 * @Description 批量删除
	 */
	@RequestMapping(value="/deleteSelect")
	public void deleteSelect(int[] id,Integer spOnline,PrintWriter out){
		if(id.length==1){
			specialSubjectService.deleteSp(id[0]);
		}else{
			specialSubjectService.deleteSelect(id);
		}
	}
	/**
	 * @param out
	 * @param model
	 * @param id
	 * @param type
	 * @author liuhg
	 * @Description 批量上下线
	 */
	@RequestMapping(value="/upOrDownSelect")
	public void upOrDownSelect(PrintWriter out,Model model, int[] id, boolean type) {
	    if(id.length==1){
	    	specialSubjectService.upOrDown(id[0], type?1:2);
	    }
	    if(id.length>1){
	    	specialSubjectService.upOrDownSelect(id, type);
	    }
	}
	
	/**
	 * @param id
	 * @param model
	 * @return
	 * @author liuhg
	 * @Description 修改前查询
	 */
	@RequestMapping(value="/preUpdate")
	public String preUpdate(Integer id,Integer pageNo,Model model){
		
		SpecialSubject specialSubject=specialSubjectService.selectById(id);
		//查询模板
		List<Template> tempList=specialSubjectService.selectTemp();
		//根据id查询关联的标题
		List<SpecialContent> spConList=specialSubjectService.selectContentById(id);
		//根据id查询关联的套图
		List<SpecialContent> spPicsList=specialSubjectService.selectPicsById(id);
		//根据id查询关联的头图
		SpecialContent spOnePic=specialSubjectService.selectOneById(id);
		if(spPicsList!=null&&spPicsList.size()>0){
				if(spPicsList.get(0).getStTitles()!=null){
					model.addAttribute("picTitle", spPicsList.get(0).getStTitles());
				}
		}
		model.addAttribute("spConList", spConList);
		model.addAttribute("spPicsList", spPicsList);
		model.addAttribute("spOnePic", spOnePic);
		model.addAttribute("tempList",tempList);
		model.addAttribute("pageNo",pageNo);
		model.addAttribute("specialSubject", specialSubject);
		
		return "specialSubject/addSpecialSubject";
	}
	
	/**
	 * @param id
	 * @param model
	 * @return
	 * @author liuhg
	 * @Description 根据id查询模板
	 */
	@RequestMapping(value="/preUpdateTemp")
	public String preUpdateTemp(Integer id,Model model){

		Template template=specialSubjectService.preUpdateTemp(id);
		model.addAttribute("template", template);
		return "specialSubject/addTemplate";
		
	}
	
	@RequestMapping(value="/selectTempName")
	public void selectTempName(String tpName,HttpServletResponse response){
		List<Template> tempList=specialSubjectService.selectTemp();
		for(Template t:tempList){
			
			if(tpName.equals(t.getTpName())){
				sentResponseData(response, "模板名称已存在");
				break;
			}
		}
	}
	
	/**
	 * @param template
	 * @param session
	 * @return
	 * @author liuhg
	 * @Description 添加和修改模板
	 */
	@RequestMapping(value="/addTemplate")
	public String addTemplate(Template template,HttpSession session,HttpServletRequest request){
		if(template.getId()!=null){
			specialSubjectService.updateTemp(template);
		}else{
			Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
			if(employee!=null){
				template.setCreateUserName(employee.getEmpName());
				template.setCreateUserId(Integer.parseInt(employee.getEmpNo()));
			}
			specialSubjectService.addTemplate(template);
		}
		if(template!=null){
			if(template.getTpContent()!=null&&template.getTpName()!=null){
				 try {
					 System.out.println(template.getTpContent());
					 System.out.println(request.getSession().getServletContext().getRealPath("/WEB-INF/jsp/special/")+template.getTpName()+".jsp");
					FileUtils.write(new File(request.getSession().getServletContext().getRealPath("/WEB-INF/jsp/special/")+template.getTpName()+".jsp"), template.getTpContent(), "utf-8");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		}
		return "redirect:/specialSubject/tempHome";
	}

	public void getHtmlDate(Model model,int cityId,String keyword){

				//往期团购
				List<News> oldNewsList=new ArrayList<News>();
				//北京热门品牌汽车团购
				List<Brand> hotGroupList=new ArrayList<Brand>();
				//老咨询
				List<News> oldList=new ArrayList<News>();
				NewsService newsService=new NewsService();
				//往期团购
				oldNewsList=newsService.getList("","tg-wq",cityId,0,6);
				if(oldNewsList!=null){
					model.addAttribute("oldNewsList", oldNewsList);
				}
				//北京热门品牌汽车团购
				hotGroupList=OnLineService.getHotBrand(cityId, 13);
					if(hotGroupList!=null){
						model.addAttribute("hotGroupList", hotGroupList);
				}
				//旧模板left
					if(StringUtils.isNotEmpty(keyword)){
						keyword=keyword.split(",|，")[0];
					}
					System.out.println("keyword="+keyword);
				oldList=newsService.getList(keyword, null, cityId, 0, 32);
					if(oldList!=null){
						model.addAttribute("oldList", oldList);
				}
					
	}
	/**
	 * @param id
	 * @param out
	 * @param biaoshi
	 * @param ids
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 * @author liuhg
	 * @Description 浏览
	 */
	@RequestMapping(value="/createHtml")
	public String createHtml(Integer id,HttpServletRequest request,Model model)throws Exception{
		int cityId=0;
		String cityCode="";
		int cookieCid=StringUtils.strToInt(CookieUtil.getCookieByName(request, "city_id"));
		
		if(cookieCid>0){
			 cityId=cookieCid;
		 }else{
			 String strIp=IpUtil.getIpAddr(request);
			 if(StringUtils.isEmpty(strIp)){
				 cityCode=new IpService().getCity(IpUtil.getIpAddr(request));
				 cityId=CommonUtil.getCityId(cityCode);
			 }
		 }
		
		if(cityId<=0){
			 cityId=10;	 
		}
		SearchGroupBuyService searchGroupBuyService=new SearchGroupBuyService();
		NewsService newsService=new NewsService();
		request.setAttribute("isSecond",Globals.isSecond(cityId+""));
		//request.setAttribute("cityCodetj",Globals.cityCodeTj(cityId+""));
		request.setAttribute("cityName", CommonUtil.getCityName(cityId));
		request.setAttribute("cityId",cityId);
		request.setAttribute("cityCode", CommonUtil.getCityCode(cityId));
		request.setAttribute("cityUrl", "http://"+CommonUtil.getCityCode(cityId)+".tuanche.com");
		request.setAttribute("cityHotStyle", OnLineService.getHotSearch(cityId, 6));
		request.setAttribute("changeCitys", OnLineService.getChangeCity());
		request.setAttribute("isHaveNews",Resources.getString("zt.cityCode").indexOf(","+cityId+",")>=0);
		request.setAttribute("Havezhuangshi",Resources.getString("Havezhuangshi").indexOf(","+cityId+",")>=0);
		request.setAttribute("hotNewBuys", searchGroupBuyService.recommend(cityId, -1, 40, "passNum desc"));
		String ftlName=null;//模板名称
		//根据id查询专题内容
		SpecialSubject specialSubject=specialSubjectService.selectById(id);
		getHtmlDate(model,cityId,specialSubject.getKeywords());
		model.addAttribute("specialSubject", specialSubject);
		//相关资讯查询
		List<News> newsList=new ArrayList<News>();
		//相关车型团购
		List<GroupBuy> groupList=new ArrayList<GroupBuy>();
		//根据id查询关联的标题
		List<SpecialContent> spConList=specialSubjectService.selectContentById(id);
		//根据id查询关联的套图
		List<SpecialContent> spPicsList=specialSubjectService.selectPicsById(id);
		//根据id查询关联的头图
		SpecialContent spOnePicList=specialSubjectService.selectOneById(id);
		
		if(spConList!=null){
			model.addAttribute("spConList", spConList);
		}
		if(spPicsList!=null){
			model.addAttribute("spPicsList", spPicsList);
		}
		if(spOnePicList!=null){
			model.addAttribute("spOnePicList", spOnePicList);
		}
		if(specialSubject!=null){
			specialSubject.setCityId(cityId);
			if(specialSubject.getZixunIds()!=null){
				newsList=newsService.getList(specialSubject.getZixunIds());
				if(newsList!=null){
					model.addAttribute("newsList", newsList);
				}
			}
			//老模板
			SpecialContent oldTemp=specialSubjectService.selectOldTemp(specialSubject);
			if(oldTemp!=null){
				model.addAttribute("oldTemp", oldTemp);
			}
			//相关车型团购
			if(specialSubject.getBrandId()!=null&&!"".equals(specialSubject.getBrandId())){
				if(specialSubject.getBrandId()!=null){
					groupList=searchGroupBuyService.recommend(cityId, Integer.parseInt(specialSubject.getBrandId()), 3, null);
					if(groupList!=null){
						model.addAttribute("groupList", groupList);
					}
				}		
			}else{		
				groupList=searchGroupBuyService.recommend(cityId, 0, 3, null);
				if(groupList!=null){
					model.addAttribute("groupList", groupList);
				}
				
			}
			//获得相关模板
			if(specialSubject.getTemplateId()!=null){
				Template template=specialSubjectService.preUpdateTemp(specialSubject.getTemplateId());
				if(template!=null){
					ftlName=template.getTpName();
				}
			}
			link(model, specialSubject.getSpUrl());
		}
		return "special/"+ftlName;
	}
	
	@RequestMapping(value="/tOnline")
	public void tOnline(HttpServletResponse response,String url){
		memcacheService.delete(url);
		sentResponseInfo(response, "操作成功");
	}
	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author liuhg
	 * @Description 上传图片
	 */
	@RequestMapping(value="/uploadPic")
	public void uploadPic(HttpServletRequest request,HttpServletResponse response)throws Exception{
		UpLoadUtil upLoadUtil=new UpLoadUtil();
		upLoadUtil.uploadPicNoFtp(request, response);
		
	}
	
	/**
	 * @param out
	 * @param ids
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 * @author liuhg
	 * @Description 多选同步线上
	 */
	@RequestMapping("/createHtmlBySelect")
	public void createHtmlBySelect(HttpServletResponse response, String ids) throws Exception{
		if(ids!=null&&!"".equals(ids)){
			String [] id=ids.split(",");
			if(id.length>0){
				List<SpecialSubject> specialList=specialSubjectService.selectByIds(id);
				if(specialList!=null&&specialList.size()>0){
					for(SpecialSubject s:specialList){
						if(s.getZtType()!=null&&!"".equals(s.getZtType())){
							String url=s.getSpUrl();
							url=url.substring(url.indexOf("/zt-mc"));
							memcacheService.delete(url);
						}
					}
					sentResponseInfo(response, "操作成功");
				}
			}
		}
	}
}
