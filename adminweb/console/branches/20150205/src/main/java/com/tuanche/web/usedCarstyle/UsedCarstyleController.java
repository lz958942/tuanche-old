package com.tuanche.web.usedCarstyle;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tuanche.bean.che.BrandDomain;
import com.tuanche.bean.che.CarstyleDomain;
import com.tuanche.bean.che.UsedCarstyle;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.dao.che.BrandDao;
import com.tuanche.dao.che.CarstyleDao;
import com.tuanche.dao.che.UsedCarstyleDao;
import com.tuanche.smc.common.page.impl.Pagination;

@Controller
public class UsedCarstyleController {
	@Autowired
	private UsedCarstyleDao usedCarstyleDao;
	@Autowired
	private BrandDao brandDao;
	@Autowired
	private CarstyleDao carstyleDao;
	
	@RequestMapping(value = "/used/toListFtype")
	public ModelAndView toListFtype(HttpServletRequest request,HttpServletResponse response,
			Model model,@Param("usedCarstyle")UsedCarstyle usedCarstyle){
		String modelstr = "type";
		if(usedCarstyle!=null){
			Pagination.threadLocal.set(usedCarstyle.getPage());
		}
		List<BrandDomain> pBrands = brandDao.selectBrandAll();
		model.addAttribute("pBrands", pBrands);
		List<UsedCarstyle> list = usedCarstyleDao.getList(usedCarstyle,modelstr);
		model.addAttribute("list", list);
		model.addAttribute("page",Pagination.threadLocal.get());
		model.addAttribute("modelstr", modelstr);
		model.addAttribute("usedCarstyle", usedCarstyle);
		return new ModelAndView("/usedCarstyle/home_"+modelstr);
	}
	@RequestMapping(value = "/used/toListFstyle")
	public ModelAndView toListFstyle(HttpServletRequest request,HttpServletResponse response,
			Model model,@Param("usedCarstyle")UsedCarstyle usedCarstyle){
		String modelstr = "style";
		if(usedCarstyle!=null){
			Pagination.threadLocal.set(usedCarstyle.getPage());
		}
		List<BrandDomain> pBrands = brandDao.selectBrandAll();
		model.addAttribute("pBrands", pBrands);
		List<UsedCarstyle> list = usedCarstyleDao.getList(usedCarstyle,modelstr);
		model.addAttribute("list", list);
		model.addAttribute("page",Pagination.threadLocal.get());
		model.addAttribute("modelstr", modelstr);
		model.addAttribute("usedCarstyle", usedCarstyle);
		return new ModelAndView("/usedCarstyle/home_"+modelstr);
	}
	
	@RequestMapping(value = "/used/toAdd")
	public ModelAndView toAdd(HttpServletRequest request,HttpServletResponse response,
			Model model,@Param("modelstr")String modelstr){
		List<BrandDomain> pBrands = brandDao.selectBrandAll();
		model.addAttribute("brands", pBrands);
		model.addAttribute("modelstr", modelstr);
		return new ModelAndView("/usedCarstyle/addOrUpdate_"+modelstr);
	}
	
	@RequestMapping(value = "/used/add")
	public ModelAndView add(HttpServletRequest request,HttpServletResponse response,@Param("modelstr")String modelstr,
			HttpSession session,Model model,@Param("usedCarstyle")UsedCarstyle usedCarstyle){
		//用户信息
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		if(employee!=null){
			usedCarstyle.setCreateUserId(employee.getId());
			usedCarstyle.setCreateUserName(employee.getEmpName());
		}
		if("type".equals(modelstr)){
			usedCarstyle.setPid(0);
		}
		usedCarstyleDao.add(usedCarstyle);
		if("type".equals(modelstr)){
			return new ModelAndView("redirect:/used/toListFtype");
		}else{
			return new ModelAndView("redirect:/used/toListFstyle");
		}
		
	}
	
	@RequestMapping(value = "/used/toUpdate")
	public ModelAndView toUpdate(HttpServletRequest request,HttpServletResponse response,
			Model model,@Param("id")Integer id,@Param("modelstr")String modelstr){
		UsedCarstyle byId = usedCarstyleDao.getById(id);
		model.addAttribute("obj", byId);
		List<BrandDomain> pBrands = brandDao.selectBrandAll();
		model.addAttribute("brands", pBrands);
		List<CarstyleDomain> rList = carstyleDao.selectStyleByIdName(Integer.valueOf(byId.getBid()));
		model.addAttribute("rList", rList);
		model.addAttribute("modelstr", modelstr);
		if("style".equals(modelstr)){
			UsedCarstyle carstyle = new UsedCarstyle();
			carstyle.setBid(byId.getBid());
			carstyle.setModel(1);
			List<UsedCarstyle> listWhere = usedCarstyleDao.getListWhere(carstyle);
			model.addAttribute("cList", listWhere);
			UsedCarstyle usedCarstyle = usedCarstyleDao.getById(byId.getPid());
			List<CarstyleDomain> carstylePyCid = carstyleDao.getCarstylePyCid(usedCarstyle.getRid());
			model.addAttribute("sList", carstylePyCid);
		}
		return new ModelAndView("/usedCarstyle/addOrUpdate_"+modelstr);
	}
	
	@RequestMapping(value = "/used/update")
	public ModelAndView update(HttpServletRequest request,HttpServletResponse response,@Param("modelstr")String modelstr,
			HttpSession session,Model model,@Param("usedCarstyle")UsedCarstyle usedCarstyle){
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		if(employee!=null){
			usedCarstyle.setUpdateUserId(employee.getId());
			usedCarstyle.setUpdateUserName(employee.getEmpName());
		}
		if("type".equals(modelstr)){
			usedCarstyle.setPid(0);
		}
		usedCarstyleDao.update(usedCarstyle);
		if("type".equals(modelstr)){
			return new ModelAndView("redirect:/used/toListFtype");
		}else{
			return new ModelAndView("redirect:/used/toListFstyle");
		}
		
	}
	
	@RequestMapping(value = "/used/delete")
	public ModelAndView delete(HttpServletRequest request,HttpServletResponse response,
			HttpSession session,Model model,@Param("id")Integer id,@Param("modelstr")String modelstr){
		UsedCarstyle usedCarstyle = new UsedCarstyle();
		usedCarstyle.setId(id);
		usedCarstyle.setIsDelete(2);
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		if(employee!=null){
			usedCarstyle.setUpdateUserId(employee.getId());
			usedCarstyle.setUpdateUserName(employee.getEmpName());
		}
		usedCarstyleDao.update(usedCarstyle);
		if("type".equals(modelstr)){
			return new ModelAndView("redirect:/used/toListFtype");
		}else{
			return new ModelAndView("redirect:/used/toListFstyle");
		}
		
	}
	
	@RequestMapping("/used/ajaxgetUcarstyleBybId")
	public @ResponseBody List<UsedCarstyle> ajaxgetUcarstyleBybId(HttpServletRequest request,HttpServletResponse response,
			Model model,@Param("brandId")Integer brandId){
		UsedCarstyle carstyle = new UsedCarstyle();
		carstyle.setBid(brandId);
		carstyle.setModel(1);
		return usedCarstyleDao.getListWhere(carstyle);
	}
	
	@RequestMapping("/used/ajaxgetcstyleBycId")
	public @ResponseBody List<CarstyleDomain> ajaxgetcstyleBycId(HttpServletRequest request,HttpServletResponse response,
			Model model,@Param("cid")Integer cid){
		return  carstyleDao.getCarstylePyCid(cid);
	}
	
	@RequestMapping("/used/ajaxCheckDouble")
	public @ResponseBody Integer ajaxCheckDouble(HttpServletRequest request,HttpServletResponse response,
			Model model,@Param("rid")Integer rid,@Param("objId")Integer objId){
		UsedCarstyle carstyle = new UsedCarstyle();
		carstyle.setRid(rid);
		List<UsedCarstyle> listWhere = usedCarstyleDao.getListWhere(carstyle);
		if(listWhere != null && listWhere.size() > 0){
			if( listWhere.size()  == 1){
				UsedCarstyle usedCarstyle = listWhere.get(0);
				if(usedCarstyle != null ){
					if(usedCarstyle.getId().equals(objId)){
						return null;
					}
				}else{
					return null;
				}
			}
			return 1;
		}
		return null;
	}
	
}
