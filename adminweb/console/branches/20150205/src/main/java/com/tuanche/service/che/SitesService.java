package com.tuanche.service.che;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.bean.che.Brand;
import com.tuanche.bean.che.BrandDomain;
import com.tuanche.bean.che.CarstyleDomain;
import com.tuanche.bean.che.CarstyleGroupbuy;
import com.tuanche.commons.util.Resources;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.dao.che.BrandDao;
import com.tuanche.dao.che.CarstyleDao;
import com.tuanche.mapper.che.read.BrandGroupbuyReadMapper;
import com.tuanche.mapper.che.read.CarstyleGroupbuyReadMapper;
import com.tuanche.mapper.che.write.BrandGroupbuyWriteMapper;
import com.tuanche.mapper.che.write.CarstyleGroupbuyWriteMapper;
import com.tuanche.smc.util.ZiXunDateUtil;
import com.tuanche.upload.FtpUtil;
import com.tuanche.util.Pinyin4j;
import com.tuanche.util.SitesUtils;

@Service
public class SitesService {
	@Resource
	private BrandDao brandDao;
	@Resource
	private CarstyleDao carstyleDao;
	@Autowired
	private BrandGroupbuyReadMapper brandGroupbuyReadMapper;
	@Autowired
	private CarstyleGroupbuyReadMapper carstyleGroupbuyReadMapper;
	@Autowired
	private BrandGroupbuyWriteMapper brandGroupbuyWriteMapper;
	@Autowired
	private CarstyleGroupbuyWriteMapper carstyleGroupbuyWriteMapper;
	
	
	
	
	public void brandUpdate(BrandDomain domain, HttpSession session) {
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
			if(sessionUser!=null){
				domain.setUpdateUserId(sessionUser.getId());
			}
			domain.setPid(0);
			domain.setUpdateTime(SitesUtils.getDateToString());
			if(isImageNotNull(session, domain.getLogo())){
				inage(session,domain);
			}
			brandDao.brandOneUpdate(domain);
	}
	public void createBrandSave(HttpSession session, BrandDomain domain) {
		domain.setStatus(1);;
		domain.setSort((byte) 0);
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		if(sessionUser!=null){
			domain.setCreateUserId(sessionUser.getId());
		}
		domain.setEnname("");
		domain.setInitial(domain.getPinyin().substring(0,1).toUpperCase());
		domain.setCreateTime(SitesUtils.getDateToString());
		domain.setTid("0");
		domain.setAddtime(0);
		if(domain.getCname()==null || domain.getCname().length()==0){
			domain.setCname("");
		}
		//处理图片
		inage(session,domain);
		if(domain.getBrndPic()==null && domain.getBrndPic().length()==0){
			domain.setBrndPic("");
		}else{
			inageBrandPic(session, domain);
		}
		//查map
		brandDao.brandSave(domain);
		//添加品牌map中  查品牌
		SitesUtils.brandmap.put(domain.getId(), domain);
		//品牌团购更新
		com.tuanche.bean.che.Brand brand=new Brand();
		brand.setId(domain.getId());
		brand.setName(domain.getName());
		GlobalConstants.brandMap.put(brand.getId(),brand);
		
	}
	private void inage(HttpSession session, BrandDomain domain) {
		String path="";
		String fileName="";
		if (domain.getLogo()!= null && 0 < domain.getLogo().length()) {
			String listpic = domain.getLogo();
			// 保存大图
			String listpicmax = domain.getLogo();
			// 上传
			if(listpic!=null&&listpic.length()>0){
				listpic=listpic.replace("_s.jpg", "").replace("/"+Resources.getString("temfilePath")+"car", "");
				listpicmax=listpicmax.replace("_s.jpg", "").replace("/"+Resources.getString("temfilePath")+"car", "");;
			}
			fileName = listpic.substring(listpic.lastIndexOf("/") + 1,listpic.length());
			path = session.getServletContext().getRealPath("/" + Resources.getString("temfilePath"));
			if(new File(path+"/car"+"/"+ZiXunDateUtil.getEndDir()+"/"+fileName+"_s.jpg").exists()){
			FtpUtil.ftpUpload(Resources.getString("carftp.host"),Resources.getString("carftp.username"),Resources.getString("carftp.password"), path+"car/"+ZiXunDateUtil.getEndDir()+"/",ZiXunDateUtil.getEndDir(), fileName + "_s.jpg");
			domain.setLogo("/"+ZiXunDateUtil.getEndDir()+"/"+fileName);
			}
		}
		
	}
	
	private void inageBrandPic(HttpSession session, BrandDomain domain) {
		String path="";
		String fileName="";
		if (domain.getBrndPic()!= null && 0 < domain.getBrndPic().length()) {
			String listpic = domain.getBrndPic();
			// 上传
			fileName = listpic.substring(listpic.lastIndexOf("/") + 1,listpic.length());
			path = session.getServletContext().getRealPath("/" + Resources.getString("temfilePath"));
			if(new File(path+"/car"+"/"+ZiXunDateUtil.getEndDir()+"/"+fileName+".jpg").exists()){
			FtpUtil.ftpUpload(Resources.getString("carftp.host"),Resources.getString("carftp.username"),Resources.getString("carftp.password"), path+"car/"+ZiXunDateUtil.getEndDir()+"/",ZiXunDateUtil.getEndDir(), fileName + ".jpg");
			domain.setBrndPic(("/"+ZiXunDateUtil.getEndDir()+"/"+fileName));
			}
		}
		
	}
	public void saveTowBrand(BrandDomain domain) {
		brandDao.saveTowBrand(domain);
	}
	public void newBrandUpdate(HttpSession session,BrandDomain domain) {
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		if(sessionUser!=null){
			domain.setUpdateUserId(sessionUser.getId());
		}
		domain.setInitial(domain.getPinyin().substring(0,1));
		domain.setUpdateTime(SitesUtils.getDateToString());
		if(domain.getCname()==null){
			domain.setCname("");
		}
		if(isImageNotNullLogo(session, domain.getLogo())){
			inage(session,domain);
			//删除图片
			ftpDelImg(domain.getOldImage()+"_s.jpg");
		}
		brandDao.newBrandUpdate(domain);
	}
	public void newDelBrand(Integer id,HttpSession session) {
		int updateid=0;
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		if(sessionUser!=null){
			updateid=sessionUser.getId();
		}
		 BrandDomain  domain=brandDao.getBrandBuId(id);
		 //查询关联车型一级车款    查车系
		if(domain!=null){
			List<CarstyleDomain> cars= brandDao.selectCarSeries(id);
			if(cars!=null&&cars.size()>0){
				String[] brandsuffix={"_s.jpg","_m.jpg","_pcb.jpg","_mb.jpg","_o.jpg"};
				for (CarstyleDomain carstyleDomain : cars) {
					for (String string : brandsuffix) {
						ftpDelImg(carstyleDomain.getSpic()+string);
					}
					carstyleGroupbuyWriteMapper.del(carstyleDomain.getId(), updateid, -1);
					brandDao.delStyle(carstyleDomain.getId(),updateid);
				}
			}
			//删除车系
			brandDao.delCarStyle(id,updateid);
			//删除品牌
			String[] brandsuffix={"_s.jpg"};
			for (String string : brandsuffix) {
				ftpDelImg(domain.getLogo()+string);
			}
			brandDao.newDelBrand(id,updateid);
			brandGroupbuyWriteMapper.brandCascadeDelBycar(id);
			//清缓存
			if(GlobalConstants.carstyleMap.containsKey(id)){
				GlobalConstants.carstyleMap.remove(id);
			}
			if(GlobalConstants.brandMap.containsKey(id)){
				GlobalConstants.brandMap.remove(id)	;
			}
			
		}
	}
	
	
	
	
	private  void ftpDelImg(String spic) {
		
		FTPClient	ftpClient = new FTPClient();  
        try{  
            //连接  
            ftpClient.connect(Resources.getString("carftp.host"));  
            ftpClient.login(Resources.getString("carftp.username"),Resources.getString("carftp.password"));
            if(spic!=null&&spic.length()>0){
               ftpClient.deleteFile(spic);
                
                
            }
            System.out.println("success=========================");
            //检测连接是否成功  
        }catch(Exception ex){  
            ex.printStackTrace();  
            //关闭  
        }finally{
        	if(ftpClient !=null){  
                if(ftpClient.isConnected()){  
                    try {  
                        ftpClient.logout();  
                        ftpClient.disconnect();  
                    } catch (IOException e) {  
                        e.printStackTrace();  
                    }   
                }  
            }  
        }

	}
	//车系上传！
	private void image2(CarstyleDomain carstyleDomain,HttpSession session) {
		String path = "";
		String fileName = "";
		// 添加域名保存大图和标准图 _b.jpg
		if (carstyleDomain.getSpic()!= null && 0 < carstyleDomain.getSpic().length()) {
			String listpic = carstyleDomain.getSpic();
			// 保存大图
			String listpicmax = carstyleDomain.getSpic();
			// 上传
			if(listpic!=null&&listpic.length()>0){
				listpic=listpic.replace("_s.jpg", "").replace("/"+Resources.getString("temfilePath")+"car", "");
				listpicmax=listpicmax.replace("_s.jpg", "").replace("/"+Resources.getString("temfilePath")+"car", "");;
			}
			path = session.getServletContext().getRealPath("/" + Resources.getString("temfilePath"));
			fileName = listpic.substring(listpic.lastIndexOf("/") + 1,listpic.length());
			
			//原图大小上传
			FtpUtil.ftpUpload(Resources.getString("carftp.host"),Resources.getString("carftp.username"),Resources.getString("carftp.password"), path+"car/"+ZiXunDateUtil.getEndDir()+"/",ZiXunDateUtil.getEndDir(), fileName + "_o.jpg");
			FtpUtil.ftpUpload(Resources.getString("carftp.host"),Resources.getString("carftp.username"),Resources.getString("carftp.password"), path+"car/"+ZiXunDateUtil.getEndDir()+"/",ZiXunDateUtil.getEndDir(), fileName + "_s.jpg");
			FtpUtil.ftpUpload(Resources.getString("carftp.host"),Resources.getString("carftp.username"),Resources.getString("carftp.password"), path+"car/"+ZiXunDateUtil.getEndDir()+"/",ZiXunDateUtil.getEndDir(), fileName + "_pcb.jpg");
			//移动端
			FtpUtil.ftpUpload(Resources.getString("carftp.host"),Resources.getString("carftp.username"),Resources.getString("carftp.password"), path+"car/"+ZiXunDateUtil.getEndDir()+"/",ZiXunDateUtil.getEndDir(), fileName + "_m.jpg");
			FtpUtil.ftpUpload(Resources.getString("carftp.host"),Resources.getString("carftp.username"),Resources.getString("carftp.password"), path+"car/"+ZiXunDateUtil.getEndDir()+"/",ZiXunDateUtil.getEndDir(), fileName + "_mb.jpg");
			if(new File(path+"car/"+ZiXunDateUtil.getEndDir()+"/"+fileName+"_b.jpg").exists()){
			new File(path+"car/"+ZiXunDateUtil.getEndDir()+"/"+fileName+"_b.jpg").delete();
			}
			carstyleDomain.setSpic(listpic);
			carstyleDomain.setBpic(listpicmax);
		}
	}
	
	/////////////////////////车型
	public void carStyleSave(HttpSession session, CarstyleDomain carstyleDomain) {
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		if(sessionUser!=null){
			carstyleDomain.setCreateTimeUserID(sessionUser.getId());
		}
		carstyleDomain.setCreateTime(SitesUtils.getDateToString());
		carstyleDomain.setShow(Integer.valueOf(0));
		carstyleDomain.setSort(Integer.valueOf(0));
		carstyleDomain.setAddtime(Integer.valueOf(0));
		carstyleDomain.setType(Integer.valueOf(0));
		carstyleDomain.setPpid(Integer.valueOf(0));
		carstyleDomain.setDetailUrl("");
		//拼音一级首字母
		if(carstyleDomain!=null && carstyleDomain.getStyle()!=null && carstyleDomain.getStyle().length()>0 ){
			carstyleDomain.setInitial(Pinyin4j.getJianPin(carstyleDomain.getStyle().trim()));
		}else{
			carstyleDomain.setInitial("");
		}
		
		//初始化市场价：1
		if(carstyleDomain.getMarketPrice()==null){
		carstyleDomain.setMarketPrice("10");
		}
		if(carstyleDomain.getId()==null){
			carstyleDomain.setId(Integer.valueOf(0));
		}
		if(carstyleDomain.getEnname()==null){
			carstyleDomain.setEnname("");
		}
		if(carstyleDomain.getInitial()==null){
		carstyleDomain.setInitial("");
		}
	/*	if(carstyleDomain.getUpdateTimeUserID()==0){
			carstyleDomain.setUpdateTimeUserID((Integer) null);
		}*/
		image2(carstyleDomain, session);
		carstyleDao.carStyleSave(carstyleDomain);
	}
	/*private void pinyinInit(CarstyleDomain carstyleDomain) {
		if(carstyleDomain!=null &&carstyleDomain.getStyle()!=null &&carstyleDomain.getStyle().length()>0){
			String name=carstyleDomain.getStyle();
			int length=name.length();
			if(length > 0){
				StringBuilder builder=new StringBuilder(length);
				for (int i = 0; i < length; i++) {
					//builder.append(Pinyin4j.getJianPin(src));
				}
			}
			
		}
	}*/
	public void carStyleUpdate(CarstyleDomain carstyleDomain,HttpSession session,int type) {
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		
		if(sessionUser!=null){
			carstyleDomain.setUpdateTimeUserID(sessionUser.getId());
		}
		carstyleDomain.setUpdateTime(SitesUtils.getDateToString());
		if(type==1){
			//车型  添加车款颜色即可
			carstyleDomain.setColors("");
			carstyleDomain.setPpid(0);
			if(isImageNotNull(session,carstyleDomain.getSpic())){
				image2(carstyleDomain, session);
				String[] brandsuffix={"_s.jpg","_m.jpg","_pcb.jpg","_mb.jpg","_o.jpg"};
						for (String string : brandsuffix) {
							ftpDelImg(carstyleDomain.getOldImage()+string);
						}
			}
			//carstyleDomain.setInitial(Pinyin4j.getJianPin(carstyleDomain.getStyle().trim()));
			//问题
			if(carstyleDomain.getTexts()==null||carstyleDomain.getTexts().length()==0){
				carstyleDomain.setTexts("");
			}
		}else{
			//车款 
			carstyleDomain.setPid(0);
			carstyleDomain.setEnname("");
			carstyleDomain.setSpic("");
			carstyleDomain.setBpic("");
			carstyleDomain.setInitial("");
			if(carstyleDomain.getTexts()==null||carstyleDomain.getTexts().length()==0){
				carstyleDomain.setTexts("");
			}
			if(carstyleDomain.getColors()==null||carstyleDomain.getColors().length()==0){
				carstyleDomain.setColors("");
			}
		}
		
		/*if(carstyleDomain!=null&&!carstyleDomain.getSpic().startsWith("http")){
			image2(carstyleDomain, session);
		}*/
		//imageUpdate(session,carstyleDomain);
		
		carstyleDao.carStyleUpdate(carstyleDomain);
	}
	

	private Boolean isImageNotNull(HttpSession session, String imageName) {
			String path=session.getServletContext().getRealPath("/" + Resources.getString("temfilePath"));
			String pathimage=path+"/car/"+imageName+"_b.jpg";
			//删除
			boolean status=new File(pathimage).exists();
			if(status){
			new File(pathimage).delete();
			}
		return status;
	}
	
	private Boolean isImageNotNullLogo(HttpSession session, String imageName) {
		String path=session.getServletContext().getRealPath("/" + Resources.getString("temfilePath"));
		String pathimage=path+"/car/"+imageName+"_s.jpg";
		//删除
		boolean status=new File(pathimage).exists();
	return status;
}
	//车型级联删除
	public void carStyleDel(int id,HttpSession session,int pid) {
		int updateid=0;
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		//几号
		if(sessionUser!=null){
			updateid=sessionUser.getId();
		}
		//根据id查车系
		CarstyleDomain carstyleDomain= carstyleDao.selectCarStyle(id);
		if(carstyleDomain!=null){
			//删除关联车款
			carstyleDao.styleDel(id,updateid);
			//车型删除
			//删除图片
			String[] brandsuffix={"_s.jpg","_m.jpg","_pcb.jpg","_mb.jpg","_o.jpg"};
			for (String string : brandsuffix) {
					ftpDelImg(carstyleDomain.getSpic()+string);
			}
			carstyleGroupbuyWriteMapper.del(id,updateid,-1);
			brandGroupbuyWriteMapper.brandCascadeDelBycar(pid);
			carstyleDao.carStyleDel(id,updateid);
			//清缓存
			if(GlobalConstants.carstyleMap.containsKey(carstyleDomain.getPid())){
				GlobalConstants.carstyleMap.remove(carstyleDomain.getPid());
			}
	}
	}
	//保存车款
	public void stlySave(CarstyleDomain carstyleDomain, HttpSession session) {
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		if(sessionUser!=null){
			carstyleDomain.setCreateTimeUserID(sessionUser.getId());
		}
		carstyleDomain.setCreateTime(SitesUtils.getDateToString());
		carstyleDomain.setShow(Integer.valueOf(0));
		carstyleDomain.setSort(Integer.valueOf(0));
		carstyleDomain.setAddtime(Integer.valueOf(0));
		carstyleDomain.setType(Integer.valueOf(0));
		if(carstyleDomain.getId()==null){
			carstyleDomain.setId(Integer.valueOf(0));
		}
		if(carstyleDomain.getEnname()==null){
			carstyleDomain.setEnname("");
		}
		carstyleDomain.setMarketPrice("");
		carstyleDomain.setMarketPrice("");
		carstyleDomain.setBos((byte) 0);
		carstyleDomain.setDetailUrl("");
		if(carstyleDomain.getInitial()==null){
		carstyleDomain.setInitial("");
		}/*else{
			carstyleDomain.setInitial(carstyleDomain.getEnname().substring(0,1).toUpperCase());
		}*/
		carstyleDao.carStyleSave(carstyleDomain);
		
	}
	public List<CarstyleDomain> selectStyleByPage() {
		//车系
		 List<CarstyleDomain> clists=carstyleDao.selectStyleByPage();
		 
		 if(clists!=null&&clists.size()>0){
			 //所有车型
			 List<CarstyleDomain> allcar= initcarstyMap();
			 Map<Integer,CarstyleDomain>  cidToBrandIdMap=new HashMap<Integer,CarstyleDomain>();
			 for(CarstyleDomain tmp:allcar){
				 cidToBrandIdMap.put(tmp.getId(), tmp);
				
			 }
			
		   for (CarstyleDomain carstyleDomain : clists) {
			   if(cidToBrandIdMap.containsKey(carstyleDomain.getPpid())){
				   carstyleDomain.setBbip(cidToBrandIdMap.get(carstyleDomain.getPpid()).getPid());
				   carstyleDomain.setCarname(cidToBrandIdMap.get(carstyleDomain.getPpid()).getStyle());
			   }
		   }
		   //车系
		}
		return clists;
	}
	private List<CarstyleDomain> initcarstyMap() {
		//查询所有车系
		return carstyleDao.selectStyleName();
	}
	public List<CarstyleDomain> carStylesearch(List<String> conditions) {
		List<CarstyleDomain> clists=carstyleDao.carStylesearch(conditions);
		 
		 if(clists!=null&&clists.size()>0){
			 //所有车型
			 List<CarstyleDomain> allcar= initcarstyMap();
			 Map<Integer,CarstyleDomain>  cidToBrandIdMap=new HashMap<Integer,CarstyleDomain>();
			 for(CarstyleDomain tmp:allcar){
				 cidToBrandIdMap.put(tmp.getId(), tmp);
				
			 }
			
		   for (CarstyleDomain carstyleDomain : clists) {
			   if(cidToBrandIdMap.containsKey(carstyleDomain.getPpid())){
				   carstyleDomain.setBbip(cidToBrandIdMap.get(carstyleDomain.getPpid()).getPid());
				   carstyleDomain.setCarname(cidToBrandIdMap.get(carstyleDomain.getPpid()).getStyle());
			   }
		   }
		   //车系
		}
		return clists;
	}
	public List<CarstyleDomain> selectStyleById(Integer ppid) {
 List<CarstyleDomain> clists=carstyleDao.selectStyleById(ppid);
		 
 if(clists!=null&&clists.size()>0){
	 //所有车型
	 List<CarstyleDomain> allcar= initcarstyMap();
	 Map<Integer,CarstyleDomain>  cidToBrandIdMap=new HashMap<Integer,CarstyleDomain>();
	 for(CarstyleDomain tmp:allcar){
		 cidToBrandIdMap.put(tmp.getId(), tmp);
		
	 }
	
   for (CarstyleDomain carstyleDomain : clists) {
	   if(cidToBrandIdMap.containsKey(carstyleDomain.getPpid())){
		   carstyleDomain.setBbip(cidToBrandIdMap.get(carstyleDomain.getPpid()).getPid());
		   carstyleDomain.setCarname(cidToBrandIdMap.get(carstyleDomain.getPpid()).getStyle());
	   }
   }
   //车系
}
return clists;
}
	public static void main(String[] args) {
		System.out.println(Pinyin4j.getJianPin("好人"));
	}
	public void updateBrandPic(HttpSession session,Integer id,String path) {
		if(isImageNotNullBrandPic(session,path)){
			String fileName = path.substring(path.lastIndexOf("/") + 1,path.length());
			String path2 = session.getServletContext().getRealPath("/" + Resources.getString("temfilePath"));
			FtpUtil.ftpUpload(Resources.getString("carftp.host"),Resources.getString("carftp.username"),Resources.getString("carftp.password"), path2+"car/"+ZiXunDateUtil.getEndDir()+"/",ZiXunDateUtil.getEndDir(), fileName + ".jpg");
			brandDao.updateBrandPic(id,path);
		}
		
	}
	public BrandDomain updateBrandPicBeff(Integer id) {
		return brandDao.updateBrandPicBeff(id);
	}
	private Boolean isImageNotNullBrandPic(HttpSession session, String imageName) {
		String path=session.getServletContext().getRealPath("/" + Resources.getString("temfilePath"));
		String pathimage=path+"/car/"+imageName+".jpg";
		//删除
		boolean status=new File(pathimage).exists();
	/*	if(status){
		new File(pathimage).delete();
		}*/
	return status;
}
	public void operationOrStopOrState(int id, int type,HttpSession session) {
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
			if(sessionUser!=null && sessionUser.getId()!=null){
				int status=0;
				if(type==1){
					//停售
					carstyleDao.carSaleStop(id);
					status=-1;
				}else if(type==0){
					//在售
					carstyleDao.carSaleState(id,0);
					status=0;
				}
				carstyleGroupbuyWriteMapper.del(id,sessionUser.getId(),status);
				CarstyleDomain  car=carstyleDao.getBrandBystyleId(id);
				if(car!=null && car.getPid()>0){
					if(status==-1){
						brandGroupbuyWriteMapper.brandCascadeDelBycar(car.getPid());
						//brandGroupbuyWriteMapper.brandCascadeRestoreBycar(car.getPid());	
					}/*else{
					}*/
			}
			}
			}
	public int getBrandIdBycarId(int id) {
		return carstyleDao.getBrandBystyleId(id)==null?0:carstyleDao.getBrandBystyleId(id).getPid();
	}
	public CarstyleDomain getCarById(int id) {
		// TODO Auto-generated method stub
		return carstyleDao.getCarById(id);
	}
	}