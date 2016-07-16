package com.tuanche.service.che;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tuanche.bean.che.PicAtlasBean;
import com.tuanche.bean.che.PicPic;
import com.tuanche.bean.che.SysConfig;
import com.tuanche.commons.util.Resources;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.console.util.StringUtils;
import com.tuanche.dao.che.PicAtlasDao;
import com.tuanche.dao.che.PicPicDao;
import com.tuanche.mapper.che.read.SysConfigReadMapper;
import com.tuanche.smc.util.ImageUtil;
import com.tuanche.smc.util.ZiXunDateUtil;
import com.tuanche.util.KevinUtil;

@Service
public class PicPicService {
	@Autowired
	private PicPicDao dao;
	@Autowired
	private SysConfigReadMapper configReadMapper;
	@Autowired
	private PicColourService picColourService;
	@Autowired
	private PicAtlasService atlasService;
	
	public List<PicPic> getPicAll(PicPic bean) {
		List<String> condition=initCondition(bean);
		return dao.getPicAll(condition);
	}
	private List<String> initCondition(PicPic bean) {
	List<String> condition=new ArrayList<String>();
		if(bean!=null){
			if(bean.getId()!=null && bean.getId()>0){
				condition.add("pic.id ="+bean.getId());
			}
			if(bean.getDid()!=null && bean.getDid()>0){
				condition.add("pic.did ="+bean.getDid());
			}
			if(bean.getBid()!=null && bean.getBid()>0){
				condition.add("pic.bid ="+bean.getBid());
				bean.setBrind(bean.getBid());
			}
			if(bean.getCid()!=null && bean.getCid()>0){
				condition.add("pic.cid ="+bean.getCid());
				bean.setCarId(bean.getCid());
			}
			if(bean.getPropertyid()!=null && bean.getPropertyid().length()>0 && !"0".equals(bean.getPropertyid()) ){
				condition.add("pic.propertyid ="+bean.getPropertyid());
			}
			if(bean.getClassid()!=null && bean.getClassid()>0 ){
				condition.add("pic.classid ="+bean.getClassid());
			}
			if(bean.getName()!=null && bean.getName().length()>0 ){
				condition.add("pic.name LIKE '%"+bean.getName()+"%'");
			}
			if(bean.getScore()!=null && bean.getScore()>0 ){
				condition.add("pic.score ="+bean.getScore());
			}
			/*if(bean.getCarId()!=null && bean.getCarId()>0 ){
				condition.add("pic.car_id ="+bean.getCarId());
			}*/
			if(bean.getColourName()!=null && bean.getColourName().length()>0 ){
				condition.add("tcc.name LIKE '%"+bean.getColourName()+"%'");
			}
			if(bean.getCollectionId()!=null ){
				condition.add("pic.collection_id="+bean.getCollectionId());
			}
		}
		condition.add("pic.status > -1");
		condition.add("pic.is_new=1");
		return condition;
	}
	public List<SysConfig>  getPropertyId() {
		return configReadMapper.getCodeByKeyFromString("pic_pic_property_id");
	}
	public List<SysConfig>  getClassId() {
		return configReadMapper.getCodeByKeyFromString("pic_pic_class_id");
	}
	
	public void updateBeanOrStatusOrInsert(PicPic bean, Integer type,Integer uid,HttpSession session) {
		//type==-1 删除  ==1 找回 ==3 大修改 ==4 添加
		if(type !=null && uid !=null){
			if(type==-1){
				//删除
				dao.updateBeanOrStatus(bean.getId(),-1,uid);
			}else if(type==1){
				//找回
				dao.updateBeanOrStatus(bean.getId(),1,uid);
			}else if(type==3){
				//大修改
				bean.setOperationUid(uid);
				bean.setOperationTime(ZiXunDateUtil.getDateToString());
				if(bean.getColourName()!=null){
					initBean(bean,uid);
				}
				if(bean.getCollectionName()!=null){
					initBean(bean,uid);
				}
				dao.updateBeanOrStatusOrInsert(bean);
			}else if(type==4){
				//新添
				initBean(bean,uid);
				dao.updateBeanOrStatusOrInsert(bean);
			}
		}
	}
	private void initBean(PicPic bean,int uid) {
		if(bean!=null){
			if(bean.getId()==null){
				bean.setCreateUid(uid);
				bean.setCreateTime(ZiXunDateUtil.getDateToStringMM());
				bean.setIsNew(0);
				bean.setStatus(1);
			}
			if(bean.getColourName()!=null && bean.getColourName().length()>0 ){
				int colid=picColourService.findByName(bean.getColourName().trim());
					bean.setColourId(colid);
			}
			if(bean.getCollectionName()!=null && bean.getCollectionName().length()>0 ){
				int colid=atlasService.findByName(bean,uid);
					bean.setCollectionId(colid);
			}
		}
		
	}
	public PicPic findByidUpdateOrInsert(Integer id) {
		if(id==null){
			//添加
			return null;
		}else{
			//修改
			return dao.findByID(id);
		}
	}
	public void cutImage(String names, File file,String fPath) {
		String pcName=System.getProperty("os.name").toLowerCase();
		File[] files=null;
		 if(file.isDirectory()){
			  files=file.listFiles();
		 }
		  for(int i=0;i<files.length;i++){   
			  String fileNmae=files[i].getName();
			  try {
				  if(KevinUtil.getFrequency(names, fileNmae)==1){
					  if(pcName!=null&&pcName.startsWith("win")){
						  ImageUtil.cutImage(fPath+"\\"+fileNmae,fPath+"\\"+fileNmae.substring(0,fileNmae.length()-4)+"_b.jpg", 1132,724);
						  ImageUtil.cutImage(fPath+"\\"+fileNmae,fPath+"\\"+fileNmae.substring(0,fileNmae.length()-4)+"_m.jpg", 300,226);
						 /* KevinUtil.imageCondense(fPath+"\\"+fileNmae,fPath+"\\"+fileNmae.substring(0,fileNmae.length()-4)+"_b.jpg", 1132,724);
						  KevinUtil.imageCondense(fPath+"\\"+fileNmae,fPath+"\\"+fileNmae.substring(0,fileNmae.length()-4)+"_m.jpg", 300,226); */
					  }else{
						  ImageUtil.cutImage(fPath+"/"+fileNmae,fPath+"/"+fileNmae.substring(0,fileNmae.length()-4)+"_b.jpg", 1132,724);
						  ImageUtil.cutImage(fPath+"/"+fileNmae,fPath+"/"+fileNmae.substring(0,fileNmae.length()-4)+"_m.jpg", 300,226);
						 /* KevinUtil.imageCondense(fPath+"/"+fileNmae,fPath+"/"+fileNmae.substring(0,fileNmae.length()-4)+"_b.jpg", 1132,724);
						  KevinUtil.imageCondense(fPath+"/"+fileNmae,fPath+"/"+fileNmae.substring(0,fileNmae.length()-4)+"_m.jpg", 300,226);  */
					  }
					  files[i].delete();
				  }
			} catch (Exception e) {
				e.printStackTrace();
			}
		  }
	}
	//@Transactional()
	public void simpleUpdate(PicPic bean,int uid) {
		if(bean!=null){
			if(bean.getTurl()!=null && bean.getTurl().length()>0 ){
				String name=bean.getTurl();
					   name=new String(name.substring(name.lastIndexOf("/")==-1?name.lastIndexOf("\\"):name.lastIndexOf("/")+1, name.length()));
				String mdr=bean.getCollectionId()+"";
			    bean.setSurl(new String("http://pic.tuanche.com/"+Resources.getString("Pics.user")+"/s/"+mdr+"/"+ZiXunDateUtil.getEndDir()+"/"+name));
				bean.setTurl(new String("http://pic.tuanche.com/"+Resources.getString("Pics.user")+"/b/"+mdr+"/"+ZiXunDateUtil.getEndDir()+"/"+name).replaceAll("_s.jpg", "_b.jpg"));
				bean.setUrl(new String("http://pic.tuanche.com/"+Resources.getString("Pics.user")+"/b/"+mdr+"/"+ZiXunDateUtil.getEndDir()+"/"+name).replaceAll("_s.jpg", "_b.jpg"));
				bean.setBurl(new String("http://pic.tuanche.com/"+Resources.getString("Pics.user")+"/b/"+mdr+"/"+ZiXunDateUtil.getEndDir()+"/"+name).replaceAll("_s.jpg", "_b.jpg"));
				bean.setMurl(new String("http://pic.tuanche.com/"+Resources.getString("Pics.user")+"/m/"+mdr+"/"+ZiXunDateUtil.getEndDir()+"/"+name).replaceAll("_s.jpg", "_m.jpg"));
				bean.setName(name);
			}
			bean.setOperationTime(ZiXunDateUtil.getDateToString());
			bean.setUid(uid);
			bean.setOperationUid(uid);
			//删除图片
			/*if(bean.getDelImg()!=null && bean.getDelImg().length()>0){
				KevinUtil.ftpDelImg(bean.getDelImg(), "", "", "",new String[]{"_b.jpg","_m.jpg"});
			}*/
			dao.simpleUpdate(bean);
		}
	}
	
	/****
	 * 获取图集
	 * ***/
	public List<PicAtlasBean> getAtlas() {
		return atlasService.getAtlas();
	}
	/**
	 * 保存pic
	 * ***/
	//@Transactional()
	public void savePic(HttpSession session,String[] parameters, PicPic bean,int uid) {
		if(parameters!=null && bean!=null){
			int parametersSize=parameters.length;
			int size=parametersSize/5;
			PicPic[] p=new PicPic[size];
			//创建对象
			if(bean !=null){
				initBean(bean,uid);
				bean.setUid(uid);
				bean.setAddTime(0);
				bean.setIsNew(1);
			}
			for (int i = 0; i < size; i++) {
				p[i]=(PicPic) bean.clone();
			}
			ArrayList<PicPic> list=new ArrayList<PicPic>(size);
			for (int i = 0; i < p.length; i++) {
				if(i==0){
					p[i].setPicTitle(parameters[i]);
					p[i].setDesc(parameters[i+1]);
					p[i].setName(parameters[i+2]);
					if(parameters[i+3]!=null &&"×".equals(parameters[i+3])){
						p[i].setPicCover(0);
					}else if(parameters[i+3]!=null &&"√".equals(parameters[i+3])){
						p[i].setPicCover(1);
						if(p[i].getCollectionId()!=null){
							coverUIniqueness(p[i].getCollectionId(),0);
						}
					}
					p[i].setScore(Integer.valueOf(parameters[i+4]));
					p[i].setTurl(new String("/pic_tmp/piclibrary/"+ZiXunDateUtil.getEndDir()+"/"+p[i].getName()));
					uploadFtp(session,p[i]);	
				}else{
					p[i].setPicTitle(parameters[i*5]);
					p[i].setDesc(parameters[i*5+1]);
					p[i].setName(parameters[i*5+2]);
					if(parameters[i*5+3]!=null &&"×".equals(parameters[i*5+3])){
						p[i].setPicCover(0);
					}else if(parameters[i*5+3]!=null &&"√".equals(parameters[i*5+3])){
						p[i].setPicCover(1);
						if(p[i].getCollectionId()!=null){
							coverUIniqueness(p[i].getCollectionId(),0);
						}
					}
					p[i].setScore(Integer.valueOf(parameters[i*5+4]));
					p[i].setTurl(new String("/pic_tmp/piclibrary/"+ZiXunDateUtil.getEndDir()+"/"+p[i].getName()));
					uploadFtp(session,p[i]);
				}
				
				list.add(p[i]);
			}
			dao.bathSavePic(list);
		}else{
			dao.simpleUpdate(bean);
		}
	}
	public void coverUIniqueness(int collid,int status) {
		if(collid>0){
			dao.coverUIniqueness(collid,status);
		}
	}
	
	private void uploadFtp(HttpSession session,PicPic bean) {
		KevinUtil.ftpUploads(session, bean.getTurl(), ZiXunDateUtil.getEndDir(), "Pics.user", "Pics.pass",new String[]{"s.jpg","m.jpg","b.jpg"},bean.getCollectionId()==null ?0:bean.getCollectionId());
		String name=bean.getTurl();
		   name=new String(name.substring(name.lastIndexOf("/")==-1?name.lastIndexOf("\\"):name.lastIndexOf("/")+1, name.length()));
	String mdr=bean.getCollectionId()+"";
	bean.setSurl(new String("http://pic.tuanche.com/"+Resources.getString("Pics.user")+"/s/"+mdr+"/"+ZiXunDateUtil.getEndDir()+"/"+name));
	bean.setTurl(new String("http://pic.tuanche.com/"+Resources.getString("Pics.user")+"/b/"+mdr+"/"+ZiXunDateUtil.getEndDir()+"/"+name).replaceAll("_s.jpg", "_b.jpg"));
	bean.setUrl(new String("http://pic.tuanche.com/"+Resources.getString("Pics.user")+"/b/"+mdr+"/"+ZiXunDateUtil.getEndDir()+"/"+name).replaceAll("_s.jpg", "_b.jpg"));
	bean.setBurl(new String("http://pic.tuanche.com/"+Resources.getString("Pics.user")+"/b/"+mdr+"/"+ZiXunDateUtil.getEndDir()+"/"+name).replaceAll("_s.jpg", "_b.jpg"));
	bean.setMurl(new String("http://pic.tuanche.com/"+Resources.getString("Pics.user")+"/m/"+mdr+"/"+ZiXunDateUtil.getEndDir()+"/"+name).replaceAll("_s.jpg", "_m.jpg"));
	bean.setName(name);
	}
	public List<PicAtlasBean> getcoll() {
		return atlasService.getAtlas();
	}
	public PicAtlasBean getcollByid(Integer id) {
		return atlasService.getcollByid(id);
	}
	public void updateCollection(PicAtlasBean bean,int uid) {
		atlasService.updateCollection(bean);
		dao.updateCollectionBycollId(new PicPic(bean.getAtlasDesc(),bean.getAtlasDesc(),uid,ZiXunDateUtil.getDateToString(),bean.getId()));
	}
	public List<PicAtlasBean> getcollByName(String collName) {
		return atlasService.getAtlasByName(collName);
	}
	public void updaeCover(Integer id) {
		PicPic pic=	new PicPic();
		pic.setId(id);
		pic.setPicCover(1);
		dao.updateBeanOrStatusOrInsert(pic);
	}
}
