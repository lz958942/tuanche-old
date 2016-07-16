package com.tuanche.web.tg;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.read.biff.BiffException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tuanche.bean.admin.Promotion;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.util.Excel03PromotionImport;
import com.tuanche.console.util.Excel07PromotionImport;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.console.util.StringUtils;
import com.tuanche.service.admin.PromotionService;
import com.tuanche.smc.common.page.impl.Pagination;
import com.tuanche.smc.web.controller.BaseController;


@Controller
@RequestMapping("/promo")
public class PromoController extends BaseController{
	
	@Autowired
	private PromotionService promotionService;
	
	@RequestMapping(value="/tolist")
	public String accountlist(Promotion promotion,HttpSession session,Model model){
		
		//从session中获取用户信息
		//Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		//分页
		Pagination page=promotion.getPage();
		if(page==null){
			page=new Pagination();
		}
		Pagination.threadLocal.set(page);
		List<String> condition =new ArrayList<String>();
		/*if(employee!=null){
			promotion.setAdminId(Integer.parseInt(employee.getEmpNo()));
			model.addAttribute("userName", employee.getEmpName());
		}*/
		if(promotion!=null){
			if(promotion.getCityId()!=null&&!"".equals(promotion.getCityId())){
				condition.add("p.city_id="+promotion.getCityId());
			}
			if(promotion.getBrandId()!=null&&!"".equals(promotion.getBrandId())){
				condition.add("p.brand_id="+promotion.getBrandId());
			}
			if(promotion.getEndTime()!=null&&!"".equals(promotion.getEndTime())){
				condition.add("p.spend_time<='"+promotion.getEndTime()+"'");
			}
			if(promotion.getStartTime()!=null&&!"".equals(promotion.getStartTime())){
				condition.add("p.spend_time>= '"+promotion.getStartTime()+"'");
			}
			model.addAttribute("promotion", promotion);
		}
		//分页条件
		model.addAttribute("page", Pagination.threadLocal.get());
		//城市
		model.addAttribute("citys", GlobalConstants.districtMap);
		//品牌
		model.addAttribute("brands", GlobalConstants.brandList);
		//分页查询
		List<Promotion> promoList=promotionService.queryByPage(condition);
		if(promoList!=null&&promoList.size()>0){
			model.addAttribute("promoList", promoList);
		}
		return "tuiguang/list";
	}
	/**
	 * 上传Excel文件
	 * @param model
	 * @param request
	 * @return 返回准备上传页面
	 * @throws BiffException
	 * @throws MultipartException
	 * @throws IOException
	 */
	@RequestMapping(value="/excleImport",method=RequestMethod.POST)
	public String excleImport (Model model,HttpServletRequest request,HttpSession session)
			throws BiffException,MultipartException,IOException{
				//从session中获取用户信息
				Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
			  //获得上传文件
			  MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		      MultipartFile fileExcel = multipartRequest.getFile("file");
		      String filerelName=fileExcel.getOriginalFilename();
		      String filelastName=filerelName.substring(filerelName.lastIndexOf("."),filerelName.length());
		      List<Promotion> promotionList=null;
		      if(filelastName.equals(".xls")){   
		    	  //03版
		      promotionList=Excel03PromotionImport.readNewExcel(fileExcel.getInputStream());
		      }
		      if(filelastName.equals(".xlsx")){
		    	  //07版
		      promotionList=Excel07PromotionImport.readNewExcel(fileExcel.getInputStream());  	  
		      }
		      
		      String spendTime=(String) request.getParameter("spendTime");
		      Map<String,Object> map=new HashMap<String, Object>();
		      if(spendTime!=null){
		    	  map.put("spendTime",spendTime);
		      }
		      promotionService.deleteByTime(map);
		      for(Promotion p:promotionList){
		    	  p.setSpendTime(spendTime);
		    	  p.setAdminId(Integer.valueOf(employee.getEmpNo()));
		      }	
		      promotionService.addPromotion(promotionList);
		      promotionService.deleteNulls();
		      return "redirect:/promo/tolist";
		    }
	
	/**
	 * @param id
	 * @param model
	 * @return
	 * @author liuhg
	 * @Description 单条查询
	 */
	@RequestMapping("/preUpdate")
	public String preUpdate(Integer id,Model model){
		Promotion promotion= promotionService.selectOne(id);
		if(promotion!=null){
			model.addAttribute("promotion", promotion);
		}
		//城市
		model.addAttribute("citys", GlobalConstants.districtMap);
		//品牌
		model.addAttribute("brands", GlobalConstants.brandList);
		return "tuiguang/update";
	}
	
	/**
	 * @param promotion
	 * @author liuhg
	 * @Description 更新
	 */
	@RequestMapping("toUpdate")
	public String toUpdate(Promotion promotion,HttpSession session){
		//从session中获取用户信息
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		if(employee!=null){
			promotion.setAdminId(Integer.parseInt(employee.getEmpNo()));
		}
		if(promotion!=null){
			if(promotion.getCityId()!=null&&!"".equals(promotion.getCityId())){
				com.tuanche.bean.che.City c=GlobalConstants.districtMap.get("北京");
				System.out.println(c+"=======================");
			}
			promotionService.toUpdate(promotion);
		}
		return "redirect:/promo/tolist";
	}
	
	/**
	 * @param response
	 * @param cityId
	 * @param brandId
	 * @param spendTime
	 * @param id
	 * @author liuhg
	 * @Description 同一天，一个城市的一个品牌只能有一条记录
	 */
	@RequestMapping("/selectSameCount")
	public void selectSameCount(HttpServletResponse response,Integer cityId,Integer brandId,String spendTime,Integer id){
		Map<String,Object> map=new HashMap<String,Object>();
		if(id!=null){
			map.put("id", id);
		}
		if(cityId!=null){
			map.put("cityId", cityId);
		}
		if(brandId!=null){
			map.put("brandId", brandId);
		}
		if(spendTime!=null&&!"".equals(spendTime)){
			map.put("spendTime", spendTime);
		}
		
		int count=promotionService.selectSameCount(map);
		if(count>0){
			sentResponseInfo(response, "此日期已存在相同记录!");
		}
	}
	
	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description 删除
	 */
	@RequestMapping("/delete")
	public void delete(Integer id,HttpServletResponse response){
		promotionService.delete(id);
		sentResponseInfo(response, "删除成功！");
	}
	
	
	@RequestMapping("/toadd")
	public String toadd(Model model){
		//城市
		model.addAttribute("citys", GlobalConstants.districtMap);
		//品牌
		model.addAttribute("brands", GlobalConstants.brandList);
		return "tuiguang/add";
	}
	/**
	 * @param promotion
	 * @param session
	 * @return
	 * @author liuhg
	 * @Description 添加
	 */
	@RequestMapping("/add")
	public String add(HttpSession session,HttpServletRequest request){
		String[] cityIds=request.getParameterValues("cityId");
		String[] brandIds=request.getParameterValues("brandId");
		String[] moneys=request.getParameterValues("money");
		String[] spendTimes=request.getParameterValues("spendTime");
		String[] cityCodes=request.getParameterValues("cityCode");
		List<Promotion> proList=new ArrayList<Promotion>();
		for(int x=0;x<cityIds.length;x++){
			Promotion promotion=new Promotion();
			if(StringUtils.isNotEmptyString(cityIds[x])){
				promotion.setCityId(Integer.parseInt(cityIds[x]));
			}
			if(StringUtils.isNotEmptyString(brandIds[x])){
				promotion.setBrandId(Integer.parseInt(brandIds[x]));
			}
			if(StringUtils.isNotEmptyString(moneys[x])){
				promotion.setMoney(moneys[x]);
			}
			if(StringUtils.isNotEmptyString(cityCodes[x])){
				promotion.setCityCode(cityCodes[x]);
			}
			if(StringUtils.isNotEmptyString(spendTimes[x])){
				promotion.setSpendTime(spendTimes[x]);
			}
			
			proList.add(promotion);
		}
		if(proList!=null&&proList.size()>0){
			promotionService.addPromotion(proList);
		}
		return "redirect:/promo/tolist";
	}
}
