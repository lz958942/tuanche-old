package com.tuanche.web.sites;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.tuanche.bean.che.Brand;
import com.tuanche.bean.che.BrandDomain;
import com.tuanche.bean.che.CarstyleDomain;
import com.tuanche.commons.util.Resources;
import com.tuanche.commons.util.StringUtils;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.dao.che.BrandDao;
import com.tuanche.dao.che.CarstyleDao;
import com.tuanche.service.che.SitesService;
import com.tuanche.smc.common.page.impl.Pagination;
import com.tuanche.smc.util.ImageUtil;
import com.tuanche.tj.service.CommonService;
import com.tuanche.util.Pinyin4j;

@Controller
public class SitesController {
	@Resource
	private BrandDao brandDao;
	@Resource
	private SitesService service;
	@Resource
	private CarstyleDao carstyleDao;
	@Autowired
    private CommonService commonService;
    

	/********** 通过一级品牌查询二级品牌 *********/
	@RequestMapping("/sites/brand/selectToBrandList")
	public ModelAndView selectToBrandList(Integer id, Model model) {
		List<BrandDomain> bListTo = brandDao.selectToBrandList(id);
		// 查询品牌
		List<BrandDomain> pBrands = brandDao.selectBrandAll();
		model.addAttribute("bListTo", bListTo);
		model.addAttribute("pBrands", pBrands);
		model.addAttribute("pid", id);
		return new ModelAndView("sites/brand/toBrand");
	}


	/***** 添加一级品牌 *****/
	@RequestMapping("/sites/brand/brandSave")
	public ModelAndView brandSave(Model model, BrandDomain domain,
			HttpSession session) {
		service.createBrandSave(session, domain);
		return new ModelAndView("sites/brand/addBrand");
	}

	@RequestMapping("/sites/brand/seelTwoBrand")
	public ModelAndView seelTwoBrand(String oneBrand, String twoBrand,
			Model model, int pid) {
		// 记录表单状态
		List<String> conditions = new ArrayList<String>();
		if (oneBrand != null && oneBrand.length() > 0) {
			conditions.add("t.pid=" + oneBrand);
			model.addAttribute("oneBrand", oneBrand);
		} else {
			conditions.add("t.pid>0");
		}
		// "t.keyword like '%" + searchZixun.getKeyword()+ "%' "
		if (twoBrand != null && twoBrand.length() > 0) {
			conditions.add("t.name LIKE '%" + twoBrand + "%'");
			model.addAttribute("twoBrand", twoBrand);
		}
		List<BrandDomain> bListTo = brandDao.selectTwoBrandSeel(conditions);
		List<BrandDomain> pBrands = brandDao.selectBrandAll();
		model.addAttribute("pBrands", pBrands);
		model.addAttribute("pid", pid);
		return new ModelAndView("sites/brand/toBrand", "bListTo", bListTo);
	}

	// //////////////////////////////////////查询品牌new// 新需求///////////////////////////////////////////////////////////////////////////

	@RequestMapping("/sites/brand/newHome")
	public String newbrandHome(HttpSession session, Model model,CarstyleDomain carstyleDomain) {
		Pagination pagination=carstyleDomain.getPage();
		if(pagination==null){
			pagination=new Pagination();
		}
		Pagination.threadLocal.set(pagination);
		
		List<String> conditions = new ArrayList<String>();
		conditions.add("t.status>0");
		List<BrandDomain> bList = brandDao.selectBrandAllByPage(conditions);
		model.addAttribute("page", Pagination.threadLocal.get());
		model.addAttribute("bList", bList);
		return "sites/brand/newHome";
	}

	@RequestMapping("/sites/brand/toAddBrand")
	public ModelAndView toAddBrand(HttpSession session, Model model) {
		
		return new ModelAndView("sites/brand/addBrand");
	}

	@RequestMapping("/sites/brand/addNewBrand")
	public ModelAndView addBrand(HttpSession session, BrandDomain domain) {
		service.createBrandSave(session, domain);
		updateBrandCache(domain);
		return new ModelAndView("redirect:/sites/brand/newHome");
	}

    @RequestMapping("/sites/brand/newAlertBefore")
	public ModelAndView newAlertBefore(BrandDomain domain,Model model) {
    	BrandDomain brand = null;
    	if(domain!=null &&domain.getId()!=null && domain.getId()!=0){
    		brand = brandDao.getBrandBuId(domain.getId());
    		if(domain.getName()!=null){
    			model.addAttribute("staName", domain.getName());
    		}
    		if(domain.getPublicMark()!=null){
    			model.addAttribute("publicMark", domain.getPublicMark());
    		}
    	}
		return new ModelAndView("sites/brand/editBrand", "brand", brand);
	}

	@RequestMapping("/sites/brand/newUpdateBrand")
	public ModelAndView newUpdateBrand(@Param(value = "domainid") BrandDomain domainid,
			@Param(value = "staName") String staName, @Param(value = "publicMarkMy") Integer publicMarkMy,Model model,HttpSession session) {
		service.newBrandUpdate(session, domainid);
		BrandDomain brandDomain=new BrandDomain();
		if(staName!=null && staName.length()>0){
			brandDomain.setName(staName);
		}
		if(publicMarkMy!=null){
			brandDomain.setPublicMark(publicMarkMy);
		}
		return this.newSelectBrand(model,domainid);
	}

	@RequestMapping("/sites/brand/newDelBrand")
	public ModelAndView newDelBrandBrand(@Param(value = "id") Integer id,HttpSession session) {
		service.newDelBrand(id,session);
		return new ModelAndView("redirect:/sites/brand/newHome");
	}

	// 按条件查询
	@RequestMapping("/sites/brand/newSelectBrand")
	public ModelAndView newSelectBrand( Model model,BrandDomain brandDomain) {
		Pagination page=brandDomain.getPage();
		if(page==null){
			page=new Pagination();
		}
		Pagination.threadLocal.set(page);
		if(brandDomain!=null){
			List<String> conditions = new ArrayList<String>();
			if(brandDomain.getName()!=null && brandDomain.getName().length()>0 ){
				conditions.add("t.name LIKE '%" + brandDomain.getName() + "%'");
			}
			if(brandDomain.getPublicMark()!=null){
				conditions.add("t.public_mark =" + brandDomain.getPublicMark() );
			}
		conditions.add("t.status>0");
		List<BrandDomain> bList = brandDao.selectBrandAllByPage(conditions);
		model.addAttribute("bList", bList);
		model.addAttribute("brandDomain", brandDomain);
		}
		model.addAttribute("page", Pagination.threadLocal.get());
		return new ModelAndView("sites/brand/newHome");
	}

	// ///////////////////////车型车款/////////////////////////////////////////////////////
	//
	@RequestMapping("/sites/carstyle/carStyleHome")
	public ModelAndView carStyleHome(CarstyleDomain carstyleDomain, Model model ) {
		Pagination page=carstyleDomain.getPage();
		if(page==null){
			page=new Pagination();
		}
		Pagination.threadLocal.set(page);
		List<BrandDomain> pBrands = brandDao.selectBrandAll();
		List<CarstyleDomain> cList = brandDao.carStyleHome(carstyleDomain.getPid());
		model.addAttribute("page", Pagination.threadLocal.get());
		model.addAttribute("pBrands", pBrands);
		model.addAttribute("mypid", carstyleDomain.getPid());
		model.addAttribute("statusid", carstyleDomain.getPid());
		model.addAttribute("brandName", carstyleDomain.getPid());
		return new ModelAndView("sites/carstyle/carstyleHome", "cList", cList);
	}

	//车型搜索
	@RequestMapping("/sites/carstyle/carStylesearch")
	public ModelAndView carStylesearch(
			CarstyleDomain carstyleDomain,
			@Param(value = "brandName") String brandName,
			@Param(value = "cName") String cName,
			@Param(value = "mypid") String mypid, 
			@Param(value = "publicMark") Integer publicMark, 
			//车款pid
			@Param(value = "statusid") String statusid,
			@Param(value = "show") Integer show,
			Model model) {
		Pagination pagination = carstyleDomain.getPage();
		if(pagination==null){
			pagination=new Pagination();
		}
		Pagination.threadLocal.set(pagination);
		List<String> conditions = new ArrayList<String>();
		// "t.keyword like '%" + searchZixun.getKeyword()+ "%' "
		if (cName != null && cName.length() > 0) {
			conditions.add("t.style LIKE '%" + cName + "%'");
			model.addAttribute("cName", cName);
		}
		if (brandName != null && brandName.length() > 0) {
			conditions.add("t.pid=" + brandName);
			model.addAttribute("brandName", brandName);
			if (mypid == null || mypid.length() == 0) {
				model.addAttribute("mypid", brandName);
			}
		}
		if(statusid!=null&&statusid.length()>0&&brandName==null&&brandName.length()>0){
			conditions.add("t.pid=" + statusid);
			model.addAttribute("statusid", statusid);
		}
		if(publicMark!=null){
			conditions.add("t.public_mark=" + publicMark);
			model.addAttribute("publicMark", publicMark);
		}
		if(show!=null && show !=9){
			conditions.add("t.show=" + show);
			model.addAttribute("show", show);
		}
		conditions.add("t.`ppid`=0");
		List<CarstyleDomain> cList = carstyleDao.carStylesearch(conditions);
		List<BrandDomain> pBrands = brandDao.selectBrandAll();
		model.addAttribute("page", Pagination.threadLocal.get());
		model.addAttribute("pBrands", pBrands);
		model.addAttribute("cList", cList);
		if (mypid != null && mypid.length() > 0) {
			model.addAttribute("mypid", mypid);
		}
		return new ModelAndView("sites/carstyle/carstyleHome");
	}

	// 导航查询所有车系
	@RequestMapping("/sites/carstyle/carStyleHomeAll")
	public String carStyleHomeAll(Model model, CarstyleDomain carstyleDomain) {
		Pagination page = carstyleDomain.getPage();
		Pagination.threadLocal.set(page);
		List<BrandDomain> pBrands = brandDao.selectBrandAll();
		List<CarstyleDomain> cList = brandDao.queryCarStyleByPage();
		model.addAttribute("pBrands", pBrands);
		model.addAttribute("page", Pagination.threadLocal.get());
		model.addAttribute("cList", cList);
		return "sites/carstyle/carstyleHome";
	}

	/******** 编辑前一步 **************/
	@RequestMapping("/sites/carstyle/carStyleUpdateBefore")
	public ModelAndView carStyleUpdateBefore(@Param(value = "id") int id,
			Model model, @Param(value = "pid") int pid, String brandName) {
		CarstyleDomain cList = carstyleDao.carStyleUpdateBefore(id);
		List<BrandDomain> pBrands = brandDao.selectBrandAll();
		model.addAttribute("pBrands", pBrands);
		model.addAttribute("mypid", pid);
		return new ModelAndView("sites/carstyle/editCarstyle", "cList", cList);
	}

	//车系
	@RequestMapping("/sites/carstyle/carStyleUpdate")
	public ModelAndView carStyleUpdate(@Param(value = "mypid") String mypid,
			@Param(value = "carstyleDomain") CarstyleDomain carstyleDomain,
			HttpSession session) {
		// carstyleDao.carStyleUpdate(carstyleDomain);
		//1为车系，2车款
		System.out.println(carstyleDomain.getInitial());
		service.carStyleUpdate(carstyleDomain, session,1);
		
		return new ModelAndView("redirect:/sites/carstyle/carStyleHome?pid="+ carstyleDomain.getPid());
	}

	/********** 添加前一步 ***********/
	@RequestMapping("/sites/carstyle/carStyleAdd")
	public ModelAndView carStyleAdd(Model model,
			@Param(value = "mypid") String mypid) {
		List<BrandDomain> pBrands = brandDao.selectBrandAll();
		model.addAttribute("pBrands", pBrands);
		model.addAttribute("mypid", mypid);
		return new ModelAndView("sites/carstyle/addCarStyle");
	}

	//保存车型
	@RequestMapping("/sites/carstyle/carStyleSave")
	public ModelAndView carStyleSave(String mypid,CarstyleDomain carstyleDomain, HttpSession session) {
		if (mypid == null) {
			mypid = Integer.toString(carstyleDomain.getPid());
			mypid = Integer.toString(carstyleDomain.getPid());
		}
		service.carStyleSave(session, carstyleDomain);
		updateCarstyleCache(carstyleDomain);
		if(carstyleDomain!=null && carstyleDomain.getPid()!=null){
			return new ModelAndView("redirect:/sites/carstyle/carStyleHome?pid="+carstyleDomain.getPid());
		}else{
		return new ModelAndView("redirect:/sites/carstyle/carStyleHomeAll");
		}
	}

	@RequestMapping("/sites/carstyle/carStyleDel")
	public ModelAndView carStyleDel(@Param(value = "id") int id,
			@Param(value = "mypid") String mypid,HttpSession session) {
		service.carStyleDel(id,session,Integer.valueOf((mypid==null?"0":mypid)));
		return new ModelAndView("redirect:/sites/carstyle/carStyleHome?pid="+ mypid);
	}

	// 车款删除
	@RequestMapping("/sites/carstyle/carStyleDelMyppid")
	public ModelAndView carStyleDelMyppid(@Param(value = "id") int id,
			@Param(value = "myppid") String myppid,HttpSession session) {
		int updateid=0;
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		
		if(sessionUser!=null){
			updateid=sessionUser.getId();
		}
		carstyleDao.carStyleDel(id,updateid);
		return new ModelAndView("redirect:/sites/carstyle/styleHome?ppid="
				+ myppid);
	}

	// //////////////////车款 入口导航////////////////////////////////////

	@RequestMapping("/sites/carstyle/styleHomeAll")
	public ModelAndView styleHomeAll(Model model,CarstyleDomain carstyleDomain) {

		Pagination pagination=carstyleDomain.getPage();
		if(pagination==null){
		pagination = new Pagination();
		}
		Pagination.threadLocal.set(pagination);

		List<CarstyleDomain> slist = service.selectStyleByPage();
				

		// 品牌
		List<BrandDomain> pBrands = brandDao.selectBrandAll();
		model.addAttribute("pBrands", pBrands);

		// 车系
		// List<CarstyleDomain> series=carstyleDao.selectStyleName();
		model.addAttribute("page", Pagination.threadLocal.get());
		// model.addAttribute("series", series);
		return new ModelAndView("sites/carstyle/styleHome", "slist", slist);
	}

	// 全部车款列表

	@RequestMapping("/sites/carstyle/styleHome")
	public ModelAndView styleHome(@Param("bid")Integer bid,CarstyleDomain carstyleDomain,@Param(value = "statusid")String statusid,Model model) {
		Pagination pagination=carstyleDomain.getPage();
		if(pagination==null){
		pagination = new Pagination();
		}
		Pagination.threadLocal.set(pagination);
		List<CarstyleDomain> slist = service.selectStyleById(carstyleDomain.getPpid());
		if(statusid!=null&&statusid.length()>0){
			if(statusid.split(",").length>1){
				model.addAttribute("statusid",statusid.split(",")[0]);
			}else{
				model.addAttribute("statusid", statusid);
			}
		}
		// 品牌
		List<BrandDomain> pBrands = brandDao.selectBrandAll();
		model.addAttribute("pBrands", pBrands);
		// 车系
		List<CarstyleDomain> series = carstyleDao.selectStyleName();
		model.addAttribute("page", Pagination.threadLocal.get());
		model.addAttribute("series", series);
		model.addAttribute("brandName", bid);
		model.addAttribute("styleNameMy", carstyleDomain.getPpid());
		model.addAttribute("myppid", carstyleDomain.getPpid());
		return new ModelAndView("sites/carstyle/styleHome", "slist", slist);
	}
	// 添加车款
	@RequestMapping("/sites/carstyle/styleAdd")
	public ModelAndView styleAdd(Model model,
			@Param(value = "myppid") String myppid) {
		// 品牌
		List<BrandDomain> pBrands = brandDao.selectBrandAll();
		model.addAttribute("pBrands", pBrands);
		// 车系
		List<CarstyleDomain> series = carstyleDao.selectStyleName();
		if(myppid!=null && myppid.length()>0){
			CarstyleDomain brindId=carstyleDao.getBrandBystyleId(Integer.valueOf(myppid));
			if(brindId!=null){
				model.addAttribute("statusBrand", brindId.getPid());
			}
		}
		model.addAttribute("page", Pagination.threadLocal.get());
		model.addAttribute("myppid", myppid);
		model.addAttribute("series", series);
		return new ModelAndView("sites/carstyle/styleAdd");
	}
	@RequestMapping("/sites/carstyle/styleAddTo")
	public ModelAndView styleAddTo(Model model,
			@Param(value = "carstyleDomain") CarstyleDomain carstyleDomain,
			@Param(value = "myppid") String myppid, HttpSession session) {
		service.stlySave(carstyleDomain, session);
		model.addAttribute("myppid", myppid);
		return new ModelAndView("redirect:/sites/carstyle/styleHome?ppid="+ carstyleDomain.getPpid());
	}
	// 修改车款
	@RequestMapping("/sites/carstyle/styleEditBefore")
	public ModelAndView styleEditBefore(Model model, @Param(value = "myppid")String myppid,
			@Param(value = "id") Integer id,
			@Param(value = "ppid") Integer ppid
			) {
		// 品牌
		List<BrandDomain> pBrands = brandDao.selectBrandAll();
		model.addAttribute("pBrands", pBrands);
		// 车系
		List<CarstyleDomain> series = carstyleDao.selectStyleName();
		if (ppid != null && ppid > 0) {
			model.addAttribute("cLppid", myppid);
		}
		if(myppid!=null && myppid.length()>0){
			CarstyleDomain brindId=carstyleDao.getBrandBystyleId(Integer.valueOf(myppid));
			if(brindId!=null){
				model.addAttribute("statusBrand", brindId.getPid());
			}
		}
		// 修改前查询
		CarstyleDomain sdomain = carstyleDao.carStyleUpdateBefore(id);
		model.addAttribute("page", Pagination.threadLocal.get());
		model.addAttribute("series", series);
		model.addAttribute("myppid", myppid);
		model.addAttribute("sdomain", sdomain);
		model.addAttribute("statusid", sdomain.getPpid());
		return new ModelAndView("sites/carstyle/styleEdit");
	}

	//车款
	@RequestMapping("/sites/carstyle/styleEdit")
	public ModelAndView styleEdit(Model model,
			@Param(value = "myppid") String myppid,
			@Param(value = "carstyleDomain") CarstyleDomain carstyleDomain,
			String statusid,
			HttpSession session, String cName, Integer cLppid, Integer brandName) {
		// service.(session, carstyleDomain);
		service.carStyleUpdate(carstyleDomain, session,2);
		
		if (myppid == null) {
			return new ModelAndView("redirect:/sites/carstyle/styleHome?ppid="
					+ carstyleDomain.getPpid());
		}
		if (cName != null && cName.length() > 0) {
			model.addAttribute("cName", cName);
		}
		if (cLppid != null && cLppid > 0) {
			model.addAttribute("ppid", cLppid);
		}
		if (brandName != null && brandName > 0) {
			model.addAttribute("brandName", brandName);
		}
		if(statusid!=null&&statusid.length()>0){
		model.addAttribute("statusid", statusid);
		}
		return new ModelAndView("redirect:/sites/carstyle/styleHome?ppid="+carstyleDomain.getPpid()+"&statusid="+statusid);
	}

	// 下架
	@RequestMapping("/sites/carstyle/slodOut")
	public ModelAndView slodOut(@Param(value = "ppid") String ppid,
			@Param(value = "id") int id) {
		carstyleDao.slodOut(id);
		return new ModelAndView("redirect:/sites/carstyle/styleHome?ppid="
				+ ppid);
	}

	// 在售
	@RequestMapping("/sites/carstyle/putaway")
	public ModelAndView putaway(@Param(value = "ppid") String ppid,
			@Param(value = "id") int id) {
		carstyleDao.putaway(id);
		return new ModelAndView("redirect:/sites/carstyle/styleHome?ppid="
				+ ppid);
	}
//车款搜索
	@RequestMapping("/sites/carstyle/stylesearch")
	public ModelAndView stylesearch(
			@Param(value = "brandName") String brandName,
			@Param(value = "cName") String cName,
			@Param(value = "pppid") String pppid,
			@Param(value = "publicMark") Integer publicMark,
			@Param(value = "statusid") String statusid,
			CarstyleDomain carstyleDomain,
			Model model) {
		Pagination page=carstyleDomain.getPage();
		if(page==null){
			 page=new Pagination();
		}
		Pagination.threadLocal.set(page);
		List<String> conditions = new ArrayList<String>();
		if (cName != null && cName.length() > 0) {
			conditions.add("t.style LIKE '%" + cName + "%'");
			model.addAttribute("cName", cName);
		}
		if (brandName != null && brandName.length() > 0) {
			model.addAttribute("brandName", brandName);
		}
		if (publicMark != null) {
			conditions.add("t.public_mark =" +publicMark );
			model.addAttribute("publicMark", publicMark);
		}
		if (pppid != null && pppid.length() > 0) {
			conditions.add("t.ppid=" + pppid);
			model.addAttribute("pppid", pppid);
			// 车系更具ip获取名称
			CarstyleDomain carName =carstyleDao.getCarName(Integer.valueOf(pppid));
			 if(carName!=null){
				 model.addAttribute("caename", carName);
			 }
		} else {
			conditions.add("t.`ppid`>0");
		}
		if (statusid != null && statusid.length() > 0&&pppid==null&&pppid.length()>0) {
			conditions.add("t.ppid=" + statusid);
			model.addAttribute("statusid", statusid);
		} else {
			conditions.add("t.`ppid`>0");
		}
		
		
		List<CarstyleDomain> cList = service.carStylesearch(conditions);
				
		// 品牌
		List<BrandDomain> pBrands = brandDao.selectBrandAll();
		model.addAttribute("pBrands", pBrands);
		model.addAttribute("page", Pagination.threadLocal.get());
		model.addAttribute("styleNameMy",pppid );
		model.addAttribute("myppid",pppid );
		// model.addAttribute("series", series);
		return new ModelAndView("sites/carstyle/styleHome", "slist", cList);
	}

	// 异步同互获取车系列表ajax  /*ModelAndView*/
	@RequestMapping("/json/carstyle/ajaxStyle")
	public @ResponseBody List<CarstyleDomain> ajaxStyle(@Param(value = "brandID") String brandID,
			HttpServletResponse response,CarstyleDomain carstyleDomain) {
		List<CarstyleDomain> clists = carstyleDao.selectStyleByIdName(Integer.valueOf(brandID));
		return clists;
	}
	
	// 异步同互获取车系列表ajax  /*ModelAndView*/
	@RequestMapping("/json/carstyle/ajaxCarStyle")
	public @ResponseBody List<CarstyleDomain> ajaxCarStyle(@Param(value = "ppid") Integer ppid,
			HttpServletResponse response,CarstyleDomain carstyleDomain) {
		List<CarstyleDomain> clists = carstyleDao.getCarNamesByPpid(ppid);
		return clists;
	}
	
	
	// 图片上传
	@RequestMapping(value = "/common/brand/uploadImgLogo")
	public void uploadImgForlistPic(HttpServletRequest request,
			HttpServletResponse response) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile imgFile = multipartRequest.getFile("listPicFile");
		String parentPath = Resources.getString("filePath");

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String dailyPath = simpleDateFormat.format(new Date()) + "/";
		String uname = System.currentTimeMillis() + ""
				+ new Random().nextInt(10000);
		String ext = getFileExt(imgFile.getOriginalFilename());
		String imgName = uname + "_s" + ext;
		//String listImgNameM = uname + "_m" + ext;
		String path = parentPath + dailyPath;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String fullPath = path + imgName;

		String servletPath = request.getContextPath();
		String realPath = request.getSession().getServletContext()
				.getRealPath(servletPath);
		fullPath = new File(realPath) + "/"
				+ Resources.getString("temfilePath") + "car/" + dailyPath
				+ imgName;
		File dir = new File(fullPath).getParentFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}
		try {
			InputStream inputStream = imgFile.getInputStream();
			bis = new BufferedInputStream(inputStream);
			OutputStream out = new FileOutputStream(fullPath);
			bos = new BufferedOutputStream(out);
			byte[] tem = new byte[1024];
			int len = 0;
			while ((len = bis.read(tem)) != -1) {
				bos.write(tem);
			}
		} catch (Exception e) {
			e.printStackTrace();
			sentResponseData(response, "1");
			return;
		} finally {
			if (bos != null) {
				try {
					bos.flush();
					bos.close();
				} catch (IOException e) {
				}
			}
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
				}
			}
		}
		
		 
		sentResponseData(response, "/" + dailyPath + uname);
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @return string
	 */
	private String getFileExt(String fileName) {
		return ".jpg";
	}

	private void sentResponseData(HttpServletResponse response, String string) {
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(string);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}

	@RequestMapping(value ="/common/uploadImgStyle")
	public void uploadImgForlistPicccc(HttpServletRequest request,
			HttpServletResponse response) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile imgFile = multipartRequest.getFile("listPicFile");
		String parentPath = Resources.getString("filePath");

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String dailyPath = simpleDateFormat.format(new Date()) + "/";
		String uname = System.currentTimeMillis() + ""
				+ new Random().nextInt(10000);
		String ext = getFileExt(imgFile.getOriginalFilename());
		String imgName = uname + "_b" + ext;
		String listImgName = uname + "_s" + ext;
		String listImgNamePCb = uname + "_pcb" + ext;
		String listImgNameM = uname + "_m" + ext;
		String listImgNameMb = uname + "_mb" + ext;
		String listImgNameO = uname + "_o" + ext;
		
		String path = parentPath + dailyPath;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String fullPath = path + imgName;

		String servletPath = request.getContextPath();
		String realPath = request.getSession().getServletContext()
				.getRealPath(servletPath);

		// 放到项目根目录下
		fullPath = new File(realPath) + "/"
				+ Resources.getString("temfilePath") + "car/" + dailyPath
				+ imgName;

		File dir = new File(fullPath).getParentFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}

		String listFullPath = new File(realPath) + "/"
				+ Resources.getString("temfilePath") + "car/" + dailyPath
				+ listImgName;
		String listFullPathM = new File(realPath) + "/"
				+ Resources.getString("temfilePath") + "car/" + dailyPath
				+ listImgNameM;
		String listFullPathMb = new File(realPath) + "/"
				+ Resources.getString("temfilePath") + "car/" + dailyPath
				+ listImgNameMb;
		String listFullPathPCB = new File(realPath) + "/"
				+ Resources.getString("temfilePath") + "car/" + dailyPath
				+ listImgNamePCb;
		String listFullPathO = new File(realPath) + "/"
				+ Resources.getString("temfilePath") + "car/" + dailyPath
				+ listImgNameO;
		
		
		
		try {
			InputStream inputStream = imgFile.getInputStream();
			bis = new BufferedInputStream(inputStream);
			OutputStream out = new FileOutputStream(fullPath);
			bos = new BufferedOutputStream(out);
			byte[] tem = new byte[1024];
			int len = 0;
			while ((len = bis.read(tem)) != -1) {
				bos.write(tem);
			}
		} catch (Exception e) {
			e.printStackTrace();
			sentResponseData(response, "1");
			return;
		} finally {
			if (bos != null) {
				try {
					bos.flush();
					bos.close();
				} catch (IOException e) {
				}
			}
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
				}
			}
		}
		try {
			
			//原图
			ImageUtil.cutImage(fullPath, listFullPathO, 600, 400);
			
			//pc小图
			ImageUtil.cutImage(fullPath, listFullPath, 300, 200);
			//pc大图
			ImageUtil.cutImage(fullPath, listFullPathPCB, 413, 275);
			
			//移动端大图
			ImageUtil.cutImage(fullPath, listFullPathM, 230, 153);
			//移动端小图
			ImageUtil.cutImage(fullPath, listFullPathMb, 165, 100);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sentResponseData(response, "/" + dailyPath + uname);
	}

	/************************************* 后期添加通道入口 **********************************/
	
	//车型的停售
	@RequestMapping(value = "/sites/carstyle/slodOut.do")
	public void carSaleStop(@Param("id")int id,HttpServletResponse response,@Param("type") Integer type,HttpSession session) {
		CarstyleDomain car=null;
		Map<Integer,String> map=null;
		if(type!=null && 0!=id ){
			//查数据
			car=service.getCarById(id);
		}
		
		if(type!=null && type==1){
			//停售
			service.operationOrStopOrState(id,1,session);
			//清缓存
			if(GlobalConstants.carstyleMap.containsKey(car.getPid())){
				GlobalConstants.carstyleMap.remove(car.getPid());
			}
		}else if(type!=null && type==0){
			//在售
			service.operationOrStopOrState(id,0,session);
		}else if(type!=null && type==2){
			//找回
			carstyleDao.carSaleState(id,2);
		}
		if(type!=null && car !=null  && (type==0||type==2)){
			if(car!=null){
				map=new ConcurrentHashMap<Integer, String>();
				map.put(car.getId(),car.getStyle());
				GlobalConstants.carstyleMap.put(car.getPid(),map);
			}
		}
		sentResponseData(response,"yes");
	}
	// 查询所有车型
	// 验证名称是否重复
	@RequestMapping(value = "/json/validationName")
	public void validationName(HttpServletRequest request,
			HttpServletResponse response, @Param(value = "type") String type,
			@Param(value = "name") String name) {
		String pinyin="";
		pinyin=Pinyin4j.getPinyin(name);
		String initial=Pinyin4j.getJianPin(name);
		
		if (type != null && type.length() > 0) {
			if ("0".equals(type)) {
				// 验证品牌名称
				List<BrandDomain> bs = brandDao.getBrandByName(name);
				if (bs != null && bs.size() > 0) {
					sentResponseName(response, "no_"+pinyin);
				} else {
					sentResponseName(response, "yes_"+pinyin);
				}
			} else if("1".equals(type)) {
				
				if(name!=null&&name.length()>0){
					if(Pinyin4j.getPinyin(name).split(" ").length>=2){
					pinyin=Pinyin4j.getPinyin(name).split(" ")[1];
					}
					}else{
						pinyin=Pinyin4j.getPinyin(name).split(" ")[0];
					}
				// 验证车系
				List<CarstyleDomain> carstyleDomain = brandDao.getCarstyleByName(name);
				if (carstyleDomain != null && carstyleDomain.size() >= 1) {
					sentResponseName(response, "no_"+pinyin+"_"+initial);
				} else {
					sentResponseName(response, "yes_"+pinyin+"_"+initial);
				}
			}else{
				//判断同意车系下的车款是否相同
				if(type!=null&&type.length()>0){
					String[] types=type.split(",");
					System.out.println(types.length);
					if(types.length>=2){
						List<CarstyleDomain> clist=carstyleDao.styleFindname(types[1],name);
						System.out.println(clist);
						if(clist!=null&&clist.size()>=1){
							sentResponseName(response, "no");
						}else{
							sentResponseName(response, "yes");
						}
					}
				}
				
			}

		}
	}
	//品牌图片
	@RequestMapping(value ="/common/brandPic")
	public void brandPic(HttpServletRequest request,HttpServletResponse response){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile imgFile = multipartRequest.getFile("listPicFileBrand");
		String parentPath = Resources.getString("filePath");

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String dailyPath = simpleDateFormat.format(new Date()) + "/";
		String uname = System.currentTimeMillis() + ""+ new Random().nextInt(10000);
		String ext = getFileExt(imgFile.getOriginalFilename());
		String imgName = uname+ext;
		String path = parentPath + dailyPath;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String fullPath = path + imgName;
		String servletPath = request.getContextPath();
		String realPath = request.getSession().getServletContext().getRealPath(servletPath);
		// 放到项目根目录下
		fullPath = new File(realPath) + "/"+ Resources.getString("temfilePath") + "car/" + dailyPath+ imgName;
		File dir = new File(fullPath).getParentFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}
		try {
			InputStream inputStream = imgFile.getInputStream();
			bis = new BufferedInputStream(inputStream);
			OutputStream out = new FileOutputStream(fullPath);
			bos = new BufferedOutputStream(out);
			byte[] tem = new byte[1024];
			int len = 0;
			while ((len = bis.read(tem)) != -1) {
				bos.write(tem);
			}
		} catch (Exception e) {
			e.printStackTrace();
			sentResponseData(response, "1");
			return;
		} finally {
			if (bos != null) {
				try {
					bos.flush();
					bos.close();
				} catch (IOException e) {
				}
			}
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
				}
			}
		}
		sentResponseData(response, "/" + dailyPath + uname);
	}
	
		//修改品牌图片前
	@RequestMapping(value ="/common/brandPicBeff")
		public ModelAndView updateBrandPicBeff(@Param("id")Integer id,@Param("type") Integer type,Model model) {
			BrandDomain brind=service.updateBrandPicBeff(id);
			if(type==1){
				//添加
				model.addAttribute("type", 1);
			}else{
				//修改
				model.addAttribute("type", 2);
			}
			return new ModelAndView("sites/brand/BrandPic","brand",brind);
		}
	//修改品牌图片
	@RequestMapping(value ="/common/brandPicUpdate")
	public void updateBrandPic(HttpServletResponse response,HttpSession session, @Param("id")Integer id,@Param("path")String path) {
		service.updateBrandPic(session,id,path);
		sentResponseName(response,"1");
	}
	
	
	private void sentResponseName(HttpServletResponse response, String string) {
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(string);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
	
	/*
     * 更新品牌缓存
     * 
     */
    public void updateBrandCache(BrandDomain domain) {
        Brand b = new Brand();
        b.setId(domain.getId());
        b.setVender(domain.getVender()==null?0:domain.getVender());
        b.setName(domain.getName());
        if(StringUtils.isNotEmpty(domain.getPinyin())){
            b.setOrderName(domain.getPinyin().substring(0,1).toUpperCase()+" "+b.getName());
        }else{
            b.setOrderName("");
        }
        GlobalConstants.brandMap.put(domain.getId(),b);
    }
    /*
     * 更新车型缓存
     * 
     */
    public void updateCarstyleCache(CarstyleDomain style) {
            if(GlobalConstants.carstyleMap.containsKey(style.getPid())){
                GlobalConstants.carstyleMap.get(style.getPid()).put(style.getId(),style.getStyle());
            }else{
                Map<Integer,String> map=new ConcurrentHashMap<Integer, String>();
                map.put(style.getId(),style.getStyle());
                GlobalConstants.carstyleMap.put(style.getPid(),map);
            }
    }
    
    
}


