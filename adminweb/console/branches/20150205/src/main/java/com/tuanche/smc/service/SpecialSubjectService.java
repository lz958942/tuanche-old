package com.tuanche.smc.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tuanche.commons.util.Resources;
import com.tuanche.commons.util.StringUtils;
import com.tuanche.smc.domain.specialSubject.SpecialContent;
import com.tuanche.smc.domain.specialSubject.SpecialSubject;
import com.tuanche.smc.domain.specialSubject.Template;
import com.tuanche.smc.persistence.read.admin.special.SpecialContentReadMapper;
import com.tuanche.smc.persistence.read.admin.special.SpecialSubjectReadMapper;
import com.tuanche.smc.persistence.read.admin.special.TemplateReadMapper;
import com.tuanche.smc.persistence.read.che100.CarStyleMapper;
import com.tuanche.smc.persistence.write.admin.special.SpecialContentWriteMapper;
import com.tuanche.smc.persistence.write.admin.special.SpecialSubjectWriteMapper;
import com.tuanche.smc.persistence.write.admin.special.TemplateWriteMapper;
import com.tuanche.upload.FtpUtil;

@Service
public class SpecialSubjectService {
	
	@Autowired
	private SpecialSubjectReadMapper specialSubjectReadMapper;
	
	@Autowired
	private CarStyleMapper carStyleMapper;
	
	@Autowired
	private SpecialSubjectWriteMapper specialSubjectWriteMapper;
	
	@Autowired
	private SpecialContentWriteMapper specialTitlesWriteMapper;
	
	@Autowired
	private TemplateReadMapper templateReadMapper;
	
	@Autowired
	private TemplateWriteMapper templateWriteMapper;
	
	@Autowired
	private SpecialContentReadMapper specialContentReadMapper;
	
	private SpecialSubject specialSubject =new SpecialSubject();
	
	private FTPClient ftpClient =null; 
	/**
	 * @param conditions
	 * @return
	 * @author liuhg
	 * @Description 分页查询专题信息
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<SpecialSubject> queryByPage(List<String> conditions) {
		 
		return specialSubjectReadMapper.queryByPage(conditions);
	}
	
	/**
	 * @param specialSubject
	 * @author liuhg
	 * @param request 
	 * @param specialTitles 
	 * @Description  添加专题
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.SERIALIZABLE)
	public void addSpecialSubject(SpecialSubject specialSubject, SpecialContent specialContent, HttpServletRequest request) {
		
		if(specialSubject!=null){
			if(specialSubject.getOnlineDate()==null||"".equals(specialSubject.getOnlineDate())){
				specialSubject.setOnlineDate(null);
			}
			if(",".equals(specialSubject.getCarStyleId())){
				specialSubject.setCarStyleId("");
			}
			specialSubjectWriteMapper.addSpecialSubject(specialSubject);
		}
		if(specialContent!=null){
			//标题添加
			if(specialContent.getStTitle().length>0){
				if(specialContent.getStTitle()[0]!=null&&!"".equals(specialContent.getStTitle()[0])){
					Object[] array=new Object[specialContent.getStTitle().length];
					for(int x=0;x<array.length;x++){
						SpecialContent specialContent2=new SpecialContent();
						specialContent2.setSpId(specialSubject.getId());
						specialContent2.setStType("3");
						specialContent2.setStatus("1");
						if(specialContent.getStTitle()[x]!=null&&!"".equals(specialContent.getStTitle()[x])){
							specialContent2.setStTitles("'"+specialContent.getStTitle()[x]+"'");
						}else{
							specialContent2.setStTitles("null");
						}
						if(specialContent.getTitleUrls()[x]!=null&&!"".equals(specialContent.getTitleUrls()[x])){
							specialContent2.setTitleUrl("'"+specialContent.getTitleUrls()[x]+"'");
						}else{
							specialContent2.setTitleUrl("null");
						}
						if(specialContent.getStContent()[x]!=null&&!"".equals(specialContent.getStContent()[x])){
							specialContent2.setStContents("'"+specialContent.getStContent()[x]+"'");
						}else{
							specialContent2.setStContents("null");
						}
						if(specialContent.getSorts()[x]!=null&&!"".equals(specialContent.getSorts()[x])){
							specialContent2.setSort("'"+specialContent.getSorts()[x]+"'");
						}else{
							specialContent2.setSort("0");
						}
						array[x]=specialContent2;
					}
					specialTitlesWriteMapper.addSpecialTitles(array);
				}
			}
			if(specialContent.getListPic().length>0){
				if(specialContent.getListPic()[0]!=null&&!"".equals(specialContent.getListPic()[0])){
					Object[] array2=new Object[specialContent.getListPic().length];
					for(int x=0;x<array2.length;x++){
						String path=null;
						SpecialContent specialContent3=new SpecialContent();
						specialContent3.setSpId(specialSubject.getId());
						specialContent3.setStType("2");
						specialContent3.setStatus("1");
						if(specialContent.getListPic()[x]!=null&&!"".equals(specialContent.getListPic()[x])){
							path=specialContent.getListPic()[x];
							path=path.substring(path.indexOf("/zhuanti"), path.length());
							specialContent3.setListPicsInsert("'"+path+"'");
							specialContent3.setListPics(path);
						}else{
							specialContent3.setListPicsInsert("null");
						}
						if(specialContent.getPicTitle()!=null&&!"".equals(specialContent.getPicTitle())){
							specialContent3.setPicTitle("'"+specialContent.getPicTitle()+"'");
						}else{
							specialContent3.setPicTitle("null");
						}
						array2[x]=specialContent3;
						ftpUploadPic(request,path);
					}
					specialTitlesWriteMapper.addpicUrl(array2);
				}
			}
			if(specialContent.getOnePic()!=null&&!"".equals(specialContent.getOnePic())&&!"null".equals(specialContent.getOnePic())){
				SpecialContent specialContent4=new SpecialContent();
				specialContent4.setSpId(specialSubject.getId());
				String path=specialContent.getOnePic();
				path=path.substring(path.indexOf("/zhuanti"), path.length());
				specialContent4.setOnePic(path);
				specialContent4.setOnePicInsert(path);
				specialContent4.setStType("1");
				specialContent4.setStatus("1");
				ftpUploadPic(request,path);
				specialTitlesWriteMapper.addOnePicUrl(specialContent4);
			}
		}
	}

	/**
	 * @param id
	 * @param spOnline
	 * @author liuhg
	 * @Description 专题上下线
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.SERIALIZABLE)
	public void upOrDown(Integer id, Integer spOnline) {
		specialSubject.setId(id);
		if(spOnline==SpecialSubject.UPLINE){
			specialSubject.setSpOnline(SpecialSubject.DOWNLINE);
		}else{
			specialSubject.setSpOnline(SpecialSubject.UPLINE);
		}
		specialSubjectWriteMapper.updateSpecialStatus(specialSubject);
		
	}

	/**
	 * @param id
	 * @author liuhg
	 * @Description 删除专题
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.SERIALIZABLE)
	public void deleteSp(Integer id) {
		
		specialSubject.setId(id);
		specialSubject.setSpStatus(SpecialSubject.DELETE_Y+"");
		specialSubject.setSpOnline(SpecialSubject.DOWNLINE);
		specialSubjectWriteMapper.updateSpecialStatus(specialSubject);
		specialTitlesWriteMapper.updateStatus(id);
		List<SpecialContent> spList=specialContentReadMapper.selectPics(id);
		String[] picUrl=new String[spList.size()];
		if(spList!=null&&spList.size()>0){
			for(int x=0;x<spList.size();x++){
				String url=spList.get(x).getListPics();
				url=url.substring(url.indexOf("zhuanti/"), url.length());
				url=url.substring(url.indexOf("/"), url.length());
				picUrl[x]=url;
			}
		}
		FtpContent(picUrl);
	}

	/**
	 * @param id
	 * @author liuhg
	 * @Description 批量删除
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.SERIALIZABLE)
	public void deleteSelect(int[] id) {
		specialSubjectWriteMapper.deleteSelect(id);
		specialTitlesWriteMapper.deletePics(id);
		List<SpecialContent> spList=specialContentReadMapper.selectAllPics(id);
		String[] picUrl=new String[spList.size()];
		if(spList!=null&&spList.size()>0){
			for(int x=0;x<spList.size();x++){
				String url=spList.get(x).getListPics();
				url=url.substring(url.indexOf("zhuanti/"), url.length());
				url=url.substring(url.indexOf("/"), url.length());
				picUrl[x]=url;
			}
		}
		FtpContent(picUrl);
	}

	/**
	 * @param id
	 * @param spOnline
	 * @author liuhg
	 * @Description 批量上下线
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.SERIALIZABLE)
	public void upOrDownSelect(int[] id, boolean type) {
		if(type) {
			 specialSubjectWriteMapper.upOrDownSelect(id, SpecialSubject.UPLINE);
	    }else {
		    specialSubjectWriteMapper.upOrDownSelect(id, SpecialSubject.DOWNLINE);
	     }
	}

	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description 根据id查询
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public SpecialSubject selectById(Integer id) {
		
		return specialSubjectReadMapper.selectById(id);
	}

	/**
	 * @param conditions
	 * @return
	 * @author liuhg
	 * @Description 分页查询模板
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Template> queryTempByPage(List<String> conditions) {
		return templateReadMapper.queryTempByPage(conditions);
	}

	/**
	 * @param id
	 * @author liuhg
	 * @Description 删除模板
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.SERIALIZABLE)
	public void deleteTp(Integer id) {
		 templateWriteMapper.deleteTp(id);
	}

	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description 根据id查询模板
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Template preUpdateTemp(Integer id) {
		
		return templateReadMapper.preUpdateTemp(id);
	}

	/**
	 * @param template
	 * @author liuhg
	 * @Description 修改模板
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.SERIALIZABLE)
	public void updateTemp(Template template) {
		templateWriteMapper.updateTemp(template);
	}

	/**
	 * @param template
	 * @author liuhg
	 * @Description 插入模板
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.SERIALIZABLE)
	public void addTemplate(Template template) {
		templateWriteMapper.addTemplate(template);
	}

	/**
	 * @return
	 * @author liuhg
	 * @Description 模板下拉列表内容
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Template> selectTemp() {
		return templateReadMapper.selectTemp();
	}

	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description 根据id查询关联的标题
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<SpecialContent> selectContentById(Integer id) {
		
		return specialContentReadMapper.selectContentById(id);
	}

	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description 根据id查询关联的套图
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<SpecialContent> selectPicsById(Integer id) {
		return specialContentReadMapper.selectPicsById(id);
	}

	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description 根据id查询关联的头图
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public SpecialContent selectOneById(Integer id) {
		return specialContentReadMapper.selectOneById(id);
	}
	
	/**
	 * @param specialSubject
	 * @param specialContent
	 * @param request
	 * @author liuhg
	 * @Description 更新专题
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.SERIALIZABLE)
	public void updateSpecialSubject(SpecialSubject specialSubject,
			SpecialContent specialContent, HttpServletRequest request) {
		if(specialSubject!=null){
			if(specialSubject.getOnlineDate()==null||"".equals(specialSubject.getOnlineDate())){
				specialSubject.setOnlineDate(null);
			}
			if(",".equals(specialSubject.getCarStyleId())){
				specialSubject.setCarStyleId("");
			}
			specialSubjectWriteMapper.updateSpecialSubject(specialSubject);
		}
		if(specialContent!=null){
			
			if("2".equals(specialSubject.getZtType())){
				if(specialContent.getOnePic()!=null&&!"".equals(specialContent.getOnePic())&&!"null".equals(specialContent.getOnePic())){
					SpecialContent specialContent4=new SpecialContent();
					SpecialContent sc=specialContentReadMapper.selectOneById(specialSubject.getId());
					String path=null;
					File file=null;
					if(!StringUtils.isEmpty(specialContent.getOnePic())){
						path=specialContent.getOnePic();
						path=path.substring(path.indexOf("/zhuanti"), path.length());
						specialContent4.setOnePic(path);
						specialContent4.setOnePicInsert(path);
					}
					specialContent4.setStType("1");
					specialContent4.setStatus("1");
					specialContent4.setBanner(specialContent.getBanner());
					if(sc==null){
						ftpUploadPic(request,path);
						specialContent4.setSpId(specialSubject.getId());
						specialTitlesWriteMapper.addOnePicUrl(specialContent4);
					}else{
						String srcPath=request.getSession().getServletContext().getRealPath(request.getContextPath());
						String fileName=path.substring(path.lastIndexOf("/")+1);
						String descFile=path.substring(1,path.length()-1);
						//System.out.println(descFile);
						descFile=descFile.substring(descFile.indexOf("/")+1, descFile.lastIndexOf("/"));
						file=new File(srcPath+Resources.getString("temfilePath")+"zhuanti/"+descFile+"/"+fileName);
						if(file.exists()){
							ftpUploadPic(request,path);
						}
						specialContent4.setId(specialContent.getOnePicId());
						specialTitlesWriteMapper.updateOnePicUrl(specialContent4);
					}
				}
			}
			//新增一行图片
			if(specialContent.getPicListId()!=null&&specialContent.getPicListId().length>0){
				int[] values=specialContent.getPicListId();
				for(int x=0;x<values.length;x++){
					if(-1==values[x]){
						if(specialContent.getListPic().length>0){
							if(specialContent.getListPic()[0]!=null&&!"".equals(specialContent.getListPic()[0])){
								Object[] arrayPic=new Object[1];
								String path=null;
								for(int y=0;y<arrayPic.length;y++){
									SpecialContent specialContent3=new SpecialContent();
									specialContent3.setSpId(specialSubject.getId());
									specialContent3.setStType("2");
									specialContent3.setStatus("1");
									if(specialContent.getListPic()[x]!=null&&!"".equals(specialContent.getListPic()[x])){
										path=specialContent.getListPic()[x];
										path=path.substring(path.indexOf("/zhuanti"), path.length());
										specialContent3.setListPicsInsert("'"+path+"'");
										specialContent3.setListPics(path);
									}else{
										specialContent3.setListPicsInsert("null");
									}
									if(specialContent.getPicTitle()!=null&&!"".equals(specialContent.getPicTitle())){
										specialContent3.setPicTitle("'"+specialContent.getPicTitle()+"'");
									}else{
										specialContent3.setPicTitle("null");
									}
									arrayPic[y]=specialContent3;
									ftpUploadPic(request,path);
								}
								specialTitlesWriteMapper.addpicUrl(arrayPic);
							}
						}
					}
				}
			}
			//标题新增一行
			if(specialContent.getTitlesId()!=null&&specialContent.getTitlesId().length>0){
				int[] values=specialContent.getTitlesId();
				for(int x=0;x<values.length;x++){
					if(-2==values[x]){
						if(specialContent.getStTitle().length>0){
							if(specialContent.getStTitle()[0]!=null&&!"".equals(specialContent.getStTitle()[0])&&!"null".equals(specialContent.getStTitle()[0])){
								Object[] arrayT=new Object[1];
								SpecialContent specialContent2=new SpecialContent();
								specialContent2.setStType("3");
								specialContent2.setStatus("1");
								specialContent2.setSpId(specialSubject.getId());
								if(specialContent.getStTitle()[x]!=null&&!"".equals(specialContent.getStTitle()[x])){
									specialContent2.setStTitles("'"+specialContent.getStTitle()[x]+"'");
								}else{
									specialContent2.setStTitles("null");
								}
								if(specialContent.getTitleUrls()[x]!=null&&!"".equals(specialContent.getTitleUrls()[x])){
									specialContent2.setTitleUrl("'"+specialContent.getTitleUrls()[x]+"'");
								}else{
									specialContent2.setTitleUrl("null");
								}
								if(specialContent.getStContent()[x]!=null&&!"".equals(specialContent.getStContent()[x])){
									specialContent2.setStContents("'"+specialContent.getStContent()[x]+"'");
								}else{
									specialContent2.setStContents("null");
								}
								if(specialContent.getSorts()[x]!=null&&!"".equals(specialContent.getSorts()[x])){
									specialContent2.setSort("'"+specialContent.getSorts()[x]+"'");
								}else{
									specialContent2.setSort("0");
								}
								arrayT[x]=specialContent2;
								specialTitlesWriteMapper.addSpecialTitles(arrayT);
							}
						}
					}
				}
			}
		}
		if(specialContent!=null&&specialContent.getStTitle()!=null&&specialContent.getStTitle().length>0){
			//更新标题
			Object[] array=new Object[specialContent.getStTitle().length];
			List<SpecialContent> titleList=specialContentReadMapper.selectContentById(specialSubject.getId());
			if(titleList!=null&&titleList.size()>0){
				for(int x=0;x<array.length;x++){
					SpecialContent specialContent2=new SpecialContent();
					specialContent2.setStType("3");
					specialContent2.setStatus("1");
					specialContent2.setId(specialContent.getTitlesId()[x]);
					if(specialContent.getStTitle()[x]!=null&&!"".equals(specialContent.getStTitle()[x])){
						specialContent2.setStTitles(specialContent.getStTitle()[x]);
					}
					if(specialContent.getTitleUrls()[x]!=null&&!"".equals(specialContent.getTitleUrls()[x])){
						specialContent2.setTitleUrl(specialContent.getTitleUrls()[x]);
					}
					if(specialContent.getStContent()[x]!=null&&!"".equals(specialContent.getStContent()[x])){
						specialContent2.setStContents(specialContent.getStContent()[x]);
					}
					if(specialContent.getSorts()[x]!=null&&!"".equals(specialContent.getSorts()[x])){
						specialContent2.setSort(specialContent.getSorts()[x]);
					}
					specialTitlesWriteMapper.updateSpecialTitles(specialContent2);
				}
				
			}else{
				//如果不存在，插入
				if(specialContent.getStTitle().length>0&&StringUtils.isNotEmpty(specialContent.getStTitle()[0])){
					System.out.println(specialContent.getStTitle().length);
					Object[] arrayInsert=new Object[specialContent.getStTitle().length];
					for(int z=0;z<arrayInsert.length;z++){
						SpecialContent specialContentin=new SpecialContent();
						specialContentin.setStType("3");
						specialContentin.setStatus("1");
						if(specialContent.getStTitle()[0]!=null&&!"".equals(specialContent.getStTitle()[0])&&!"null".equals(specialContent.getStTitle()[0])){
							specialContentin.setSpId(specialSubject.getId());
						if(specialContent.getStTitle()[z]!=null&&!"".equals(specialContent.getStTitle()[z])){
							specialContentin.setStTitles("'"+specialContent.getStTitle()[z]+"'");
						}else{
							specialContentin.setStTitles("null");
						}
						if(specialContent.getTitleUrls()[z]!=null&&!"".equals(specialContent.getTitleUrls()[z])){
							specialContentin.setTitleUrl("'"+specialContent.getTitleUrls()[z]+"'");
						}else{
							specialContentin.setTitleUrl("null");
						}
						if(specialContent.getStContent()[z]!=null&&!"".equals(specialContent.getStContent()[z])){
							specialContentin.setStContents("'"+specialContent.getStContent()[z]+"'");
						}else{
							specialContentin.setStContents("null");
						}
						if(specialContent.getSorts()[z]!=null&&!"".equals(specialContent.getSorts()[z])){
							specialContentin.setSort("'"+specialContent.getSorts()[z]+"'");
						}else{
							specialContentin.setSort("0");
						}
						arrayInsert[z]=specialContentin;
						
						}
						
					}
					specialTitlesWriteMapper.addSpecialTitles(arrayInsert);
				}
			}
			//更新图片
			List<SpecialContent> picsList=specialContentReadMapper.selectPicsById(specialSubject.getId());
			Object[] array2=new Object[specialContent.getListPic().length];
			 if(picsList!=null&&picsList.size()>0){
				String path=null;
				File file=null;
				for(int x=0;x<array2.length;x++){
					SpecialContent specialContent3=new SpecialContent();
					specialContent3.setStType("2");
					specialContent3.setStatus("1");
						specialContent3.setId(specialContent.getPicListId()[x]);
						if(specialContent.getListPic()[x]!=null&&!"".equals(specialContent.getListPic()[x])){
							path=specialContent.getListPic()[x];
							path=path.substring(path.indexOf("/zhuanti"), path.length());
							specialContent3.setListPics(path);
							specialContent3.setListPicsInsert(path);
						}else{
							specialContent3.setListPicsInsert("null");
						}
						if(specialContent.getPicTitle()!=null&&!"".equals(specialContent.getPicTitle())){
							specialContent3.setPicTitle(specialContent.getPicTitle());
						}else{
							specialContent3.setPicTitle("null");
						}
						//上传图片到服务器
						String srcPath=request.getSession().getServletContext().getRealPath(request.getContextPath());
						String fileName=path.substring(path.lastIndexOf("/")+1);
						String descFile=path.substring(1,path.length()-1);
						descFile=descFile.substring(descFile.indexOf("/")+1, descFile.lastIndexOf("/"));
						file=new File(srcPath+Resources.getString("temfilePath")+"zhuanti/"+descFile+"/"+fileName);
						//System.out.println("picpath="+(srcPath+Resources.getString("temfilePath")+"zhuanti/"+descFile+"/"+fileName)+"   "+file.exists());
						if(file.exists()){
							//System.out.println("================start===========================");
							ftpUploadPic(request,path);
							//System.out.println("================end===========================");

						}
						specialTitlesWriteMapper.updatePicUrl(specialContent3);
					}
				}else{
					//如果图片不存在，插入
					if(specialContent.getListPic().length>0){
						if(specialContent.getListPic()[0]!=null&&!"".equals(specialContent.getListPic()[0])){
							Object[] array4=new Object[specialContent.getListPic().length];
							for(int x=0;x<array4.length;x++){
								String path=null;
								SpecialContent specialContent3=new SpecialContent();
								specialContent3.setSpId(specialSubject.getId());
								specialContent3.setStType("2");
								specialContent3.setStatus("1");
								if(specialContent.getListPic()[x]!=null&&!"".equals(specialContent.getListPic()[x])){
									path=specialContent.getListPic()[x];
									path=path.substring(path.indexOf("/zhuanti"), path.length());
									specialContent3.setListPicsInsert("'"+path+"'");
									specialContent3.setListPics(path);
								}else{
									specialContent3.setListPicsInsert("null");
								}
								if(specialContent.getPicTitle()!=null&&!"".equals(specialContent.getPicTitle())){
									specialContent3.setPicTitle("'"+specialContent.getPicTitle()+"'");
								}else{
									specialContent3.setPicTitle("null");
								}
								array4[x]=specialContent3;
								//ftpUploadPic(request,path);
							}
							specialTitlesWriteMapper.addpicUrl(array4);
						}
					}
				}
			if(specialContent.getOnePic()!=null&&!"".equals(specialContent.getOnePic())&&!"null".equals(specialContent.getOnePic())){
				SpecialContent specialContent4=new SpecialContent();
				SpecialContent sc=specialContentReadMapper.selectOneById(specialSubject.getId());
				String path=null;
				File file=null;
				if(!StringUtils.isEmpty(specialContent.getOnePic())){
					path=specialContent.getOnePic();
					path=path.substring(path.indexOf("/zhuanti"), path.length());
					specialContent4.setOnePic(path);
					specialContent4.setOnePicInsert(path);
				}
				specialContent4.setStType("1");
				specialContent4.setStatus("1");
				if(sc==null){
					ftpUploadPic(request,path);
					specialContent4.setSpId(specialSubject.getId());
					specialTitlesWriteMapper.addOnePicUrl(specialContent4);
				}else{
					String srcPath=request.getSession().getServletContext().getRealPath(request.getContextPath());
					String fileName=path.substring(path.lastIndexOf("/")+1);
					String descFile=path.substring(1,path.length()-1);
					//System.out.println(descFile);
					descFile=descFile.substring(descFile.indexOf("/")+1, descFile.lastIndexOf("/"));
					file=new File(srcPath+Resources.getString("temfilePath")+"zhuanti/"+descFile+"/"+fileName);
					if(file.exists()){
						ftpUploadPic(request,path);
					}
					specialContent4.setId(specialContent.getOnePicId());
					specialTitlesWriteMapper.updateOnePicUrl(specialContent4);
				}
			}
		}
	}

	/**
	 * 
	 * @author liuhg
	 * @return 
	 * @Description 查询模板创建用户，去除重复
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Template> selectTempUser() {
		
		return templateReadMapper.selectUser();
	}

	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description 根据多个id查询
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<SpecialSubject> selectByIds(String[] id) {
		return specialSubjectReadMapper.selectByIds(id);
	}

	/**
	 * @param id
	 * @author liuhg
	 * @Description  根据id删除图片
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.SERIALIZABLE)
	public void deletePic(Integer id) {
		specialTitlesWriteMapper.deletePic(id);
	}
	
	/**
	 * @param request
	 * @author liuhg
	 * @Description 将图片上传到服务器
	 */
	public  void  ftpUploadPic(HttpServletRequest request,String picList){
		String realPath=request.getSession().getServletContext().getRealPath(request.getContextPath());
		//System.out.println(picList+"-----------------------------");
		String descFile=picList.substring(1,picList.length()-1);
		//System.out.println(descFile);
		descFile=descFile.substring(descFile.indexOf("/")+1, descFile.lastIndexOf("/"));
		picList=Resources.getString("temfilePath")+picList;
		String fileName=picList.substring(picList.lastIndexOf("/")+1);
		realPath=realPath+Resources.getString("temfilePath")+"zhuanti/"+descFile+"/";
		FtpUtil.ftpUpload(Resources.getString("zhuantiftp.host"), Resources.getString("zhuantiftp.username"), Resources.getString("zhuantiftp.password"), realPath, descFile, fileName);
		//System.out.println("realpath=================="+realPath+"          descFile===="+descFile+"          filename======"+fileName);
	}

	/**
	 * @return
	 * @author liuhg
	 * @Description 专题查询操作人员
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<SpecialSubject> selectSpecialUser() {
		
		return specialSubjectReadMapper.selectSpecialUser();
	}

	public SpecialSubject selectTcSpecial() {
		return specialSubjectReadMapper.selectTcSpecial();
	}

	public SpecialContent selectOldTemp(SpecialSubject specialSubject) {
		
		SpecialContent specialContent2=new SpecialContent();
		Map<String,Object> map=new HashMap<String,Object>();
		Map<String,Object> map2=new HashMap<String,Object>();
		//老模板往期团购
		List<SpecialContent> WQList=specialContentReadMapper.selectWQList();
		//老模板轮播图
		List<SpecialContent> lunboList=specialContentReadMapper.selectlunboPic();
		map.put("lunboList", lunboList);
		map.put("WQList", WQList);
		//老模板资讯
	    List<SpecialContent> oldZXList=new ArrayList<SpecialContent>();
		//老模板的网友观点
		List<SpecialContent> guandianList=new ArrayList<SpecialContent>();
		//老模板的“团购报名入口”
		List<SpecialContent> tgList=new ArrayList<SpecialContent>();
		//老模板的“去看看”
		List<SpecialContent> kkList=new ArrayList<SpecialContent>();
		//老模板的谍报观点
		List<SpecialContent> dbList=new ArrayList<SpecialContent>();
		//老模板信息
		List<SpecialContent> speList=new ArrayList<SpecialContent>();
		if(specialSubject.getId()!=null&&!"".equals(specialSubject.getId())){
			speList=specialContentReadMapper.selectOldTemp(specialSubject.getId());
		}
		//老模板相关介绍
		List<SpecialContent> XGList=new ArrayList<SpecialContent>();
		//老模板相关介绍(end)
		List<SpecialContent> XGendList=new ArrayList<SpecialContent>();
		if(specialSubject.getBrandId()!=null&&!"".equals(specialSubject.getBrandId())){
			if(Integer.parseInt(specialSubject.getBrandId())>0){
				int brandId=Integer.parseInt(specialSubject.getBrandId());
			    XGList=carStyleMapper.selectxgpp(brandId);
				if(XGList!=null&&XGList.size()>0){
					map.put("XGList", XGList);
				}
			}else{
				XGendList=specialContentReadMapper.selectxgppNoBrand();
				if(XGendList!=null&&XGendList.size()>0){
					map.put("XGendList", XGendList);
				}
			}
		}
		if(specialSubject.getCarStyleId()!=null&&!"".equals(specialSubject.getCarStyleId())){
			if(Integer.parseInt(specialSubject.getCarStyleId())>0){
				int carStyleId=Integer.parseInt(specialSubject.getCarStyleId());
			    String carName=carStyleMapper.selectCarName(carStyleId);
				if(carName!=null&&!"".equals(carName)){
					map2.put("carName", carName);
					XGendList=specialContentReadMapper.selectXGEndList(map2);
					if(XGendList!=null&&XGendList.size()>0){
						map.put("XGendList", XGendList);
					}
				}
			}
		}
		if(speList!=null&&speList.size()>0){
			for(int x=0;x<speList.size();x++){
				if("4".equals(speList.get(x).getStType())){
					oldZXList.add(speList.get(x));
					map.put("oldZXList", oldZXList);
				}
				if("5".equals(speList.get(x).getStType())){
					kkList.add(speList.get(x));
					map.put("kkList", kkList);
				}
				if("6".equals(speList.get(x).getStType())){
					guandianList.add(speList.get(x));
					map.put("guandianList", guandianList);
				}
				if("7".equals(speList.get(x).getStType())){
					tgList.add(speList.get(x));
					map.put("tgList", tgList);
				}
				if("8".equals(speList.get(x).getStType())){
					dbList.add(speList.get(x));
					map.put("dbList", dbList);
				}
			}
			specialContent2.setOldMap(map);
		}
		return specialContent2;
	}
	
	public void FtpContent(String[] picUrl){
		
		ftpClient = new FTPClient();  
        try{  
            //连接  
            ftpClient.connect(Resources.getString("zhuantiftp.host"));  
            ftpClient.login(Resources.getString("zhuantiftp.username"),Resources.getString("zhuantiftp.password"));
            if(picUrl!=null&&picUrl.length>0){
                for(int x=0;x<picUrl.length;x++){
                	if(picUrl[x]!=null&&!"".equals(picUrl[x])){
                		 ftpClient.deleteFile(picUrl[x]);
                	}
                }
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
	/*
	 * 为专题填充专题模板名称
	 */
	public void fullTpName(List<SpecialSubject> specialByPage){
		Map<Integer,String> map = new HashMap<Integer, String>();
		List<Template> tempList= selectTemp();
		for(Template template : tempList){
			map.put(template.getId(), template.getTpName());
		}
		for(SpecialSubject special : specialByPage){
			if(map.containsKey(special.getTemplateId())){
				special.setTpName(map.get(special.getTemplateId()));
			}
		}
	}

}
