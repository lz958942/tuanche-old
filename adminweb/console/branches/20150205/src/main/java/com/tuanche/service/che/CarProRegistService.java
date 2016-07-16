package com.tuanche.service.che;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tuanche.bean.admin.DecorateBase;
import com.tuanche.bean.admin.DecorateContent;
import com.tuanche.bean.admin.DecorateTemp;
import com.tuanche.bean.che.Subject;
import com.tuanche.bean.che.SubjectInfo;
import com.tuanche.bean.che.SubjectKind;
import com.tuanche.commons.util.Resources;
import com.tuanche.console.util.StringUtils;
import com.tuanche.mapper.admin.read.DecorateBaseReadMapper;
import com.tuanche.mapper.admin.read.DecorateContentReadMapper;
import com.tuanche.mapper.admin.read.DecorateTempReadMapper;
import com.tuanche.mapper.admin.write.DecorateBaseWriteMapper;
import com.tuanche.mapper.admin.write.DecorateContentWriteMapper;
import com.tuanche.mapper.admin.write.DecorateTempWriteMapper;
import com.tuanche.mapper.che.read.SubjectInfoReadMapper;
import com.tuanche.mapper.che.read.SubjectKindReadMapper;
import com.tuanche.mapper.che.read.SubjectReadMapper;
import com.tuanche.upload.FtpUtil;

@Service
public class CarProRegistService {

	@Autowired
	private SubjectInfoReadMapper subjectInfoReadMapper;
	@Autowired
	private SubjectKindReadMapper subjectKindReadMapper;
	@Autowired
	private SubjectReadMapper subjectReadMapper;
	@Autowired
	private DecorateBaseWriteMapper decorateBaseWriteMapper;
	@Autowired
	private DecorateBaseReadMapper decorateBaseReadMapper;
	@Autowired
	private DecorateTempReadMapper decorateTempReadMapper;
	@Autowired
	private DecorateTempWriteMapper decorateTempWriteMapper;
	@Autowired
	private DecorateContentReadMapper decorateContentReadMapper;
	@Autowired
	private DecorateContentWriteMapper decorateContentWriteMapper;
	/**
	 * @param condition
	 * @return
	 * @author liuhg
	 * @Description 页面展示所有报名信息
	 */
	public List<SubjectInfo> queryByPage(List<String> condition) {
		
		List<SubjectInfo> list=subjectInfoReadMapper.queryByPage(condition);
		if(list!=null&&list.size()>0){
			for(SubjectInfo s:list){
				DecorateBase base=decorateBaseReadMapper.selectById(s.getNewSubjectId());
				if(base!=null){
					s.setTitle(base.getTitle());
				}
			}
		}
		
		return list;
	}
	/**
	 * @return
	 * @author liuhg
	 * @Description 加载页面装饰分类
	 */
	public List<SubjectKind> styleList() {
		return subjectKindReadMapper.styleList();
	}
	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description 联动专题名称
	 */
	public List<Subject> ztList(Subject subject) {
		return subjectReadMapper.ztList(subject);
	}
	/**
	 * @param decorateBase
	 * @author liuhg
	 * @Description 装饰基本信息添加
	 */
	public void addDecorateBase(DecorateBase decorateBase,HttpServletRequest request) {
		if(decorateBase!=null){
			if(decorateBase.getTopPicUrl()!=null&&!"".equals(decorateBase.getTopPicUrl())){
				String topPath=decorateBase.getTopPicUrl();
				topPath=topPath.replaceAll(Resources.getString("temfilePath"), "");
				decorateBase.setTopPicUrl(topPath);
				ftpUploadPic(request, topPath);
			}
			if(decorateBase.getPicUrl()!=null&&!"".equals(decorateBase.getPicUrl())){
				String picUrl=decorateBase.getPicUrl();
				picUrl=picUrl.replaceAll(Resources.getString("temfilePath"), "");
				decorateBase.setPicUrl(picUrl);
				ftpUploadPic(request, picUrl);
			}
		}
		decorateBaseWriteMapper.addDecorateBase(decorateBase);
	}
	/**
	 * @param condition
	 * @return
	 * @author liuhg
	 * @Description 装饰条件查询
	 */
	public List<DecorateBase> selectByPage(List<String> condition) {
		return decorateBaseReadMapper.selectByPage(condition);
	}
	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description 根据id查询
	 */
	public DecorateBase selectById(Integer id) {
		return decorateBaseReadMapper.selectById(id);
	}
	/**
	 * @param decorateBase
	 * @author liuhg
	 * @Description  修改装饰基本信息
	 */
	public void updateBase(DecorateBase decorateBase,HttpServletRequest request) {
		if(decorateBase!=null){
			if(decorateBase.getTopPicUrl()!=null&&!"".equals(decorateBase.getTopPicUrl())){
				String topPath=decorateBase.getTopPicUrl();
				topPath=topPath.replaceAll(Resources.getString("temfilePath"), "");
				decorateBase.setTopPicUrl(topPath);
				ftpUploadPic(request, topPath);
			}
			if(decorateBase.getPicUrl()!=null&&!"".equals(decorateBase.getPicUrl())){
				String picUrl=decorateBase.getPicUrl();
				picUrl=picUrl.replaceAll(Resources.getString("temfilePath"), "");
				decorateBase.setPicUrl(picUrl);
				ftpUploadPic(request, picUrl);
			}
		}
		decorateBaseWriteMapper.updateBase(decorateBase);
	}

	/**
	 * @param request
	 * @author liuhg
	 * @Description 将图片上传到服务器
	 */
	public  void  ftpUploadPic(HttpServletRequest request,String picList){
		String realPath=request.getSession().getServletContext().getRealPath(request.getContextPath());
		System.out.println(picList+"-----------------------------");
		String descFile=picList.substring(1,picList.length()-1);
		System.out.println(descFile);
		descFile=descFile.substring(descFile.indexOf("/")+1, descFile.lastIndexOf("/"));
		picList=Resources.getString("temfilePath")+picList;
		String fileName=picList.substring(picList.lastIndexOf("/")+1);
		realPath=realPath+Resources.getString("temfilePath")+"decorate/"+descFile+"/";
		File file=new File(realPath+fileName);
		if(file.exists()){
			FtpUtil.ftpUpload(Resources.getString("picFtpHost"), Resources.getString("zhuangshiftp.username"), Resources.getString("zhuangshiftp.password"), realPath, descFile, fileName);
		}
		System.out.println("realpath=================="+realPath+"          descFile===="+descFile+"          filename======"+fileName);
	}
	/**
	 * @param condition
	 * @return
	 * @author liuhg
	 * @Description 板块条件查询
	 */
	public List<DecorateTemp> selectTempByPage(List<String> condition) {
		return decorateTempReadMapper.selectTempByPage(condition);
	}
	/**
	 * @param list
	 * @author liuhg
	 * @Description 添加板块标题
	 */
	public void addDecPlate(List<DecorateTemp> list) {
		decorateTempWriteMapper.addDecPlate(list);
	}
	/**
	 * @param id
	 * @author liuhg
	 * @Description 删除
	 */
	public void deletePlate(Integer id) {
		decorateTempWriteMapper.deletePlate(id);
	}
	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description 根据id查询标题信息
	 */
	public DecorateTemp selectPlateById(Integer id) {
		return decorateTempReadMapper.selectPlateById(id);
	}
	/**
	 * @param decorateTemp
	 * @author liuhg
	 * @Description 修改小标题
	 */
	public void updatePlate(DecorateTemp decorateTemp) {
		decorateTempWriteMapper.updatePlate(decorateTemp);
	
	}
	/**
	 * @param list
	 * @author liuhg
	 * @Description 添加内容
	 */
	public void addContent(DecorateContent decorateContent,HttpServletRequest request) {
			String picPath=decorateContent.getPicUrl();
			if(picPath!=null&&!"".equals(picPath)){
				picPath=picPath.replaceAll(Resources.getString("temfilePath"), "");
				decorateContent.setPicUrl(picPath);
				ftpUploadPic(request, picPath);
			}
		decorateContentWriteMapper.addContent(decorateContent);
	}
	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description 内容修改前查询
	 */
	public Integer selectContentByIdResult(Integer id) {
		return decorateContentReadMapper.selectContentByIdResult(id);
	}
	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description 查询板块内容
	 */
	public Map<String, Object> selectContentById(Integer id) {
		List<DecorateContent> wenziList=new ArrayList<DecorateContent>();
		List<DecorateContent> picList=new ArrayList<DecorateContent>();
		List<DecorateContent> tuwenList=new ArrayList<DecorateContent>();
		Map<String,Object> map=new HashMap<String,Object>();
		List<DecorateContent> list=decorateContentReadMapper.selectContentByBaseId(id);
		for(DecorateContent d:list){
			if("1".equals(d.getStyle())){
				wenziList.add(d);
			}else if("2".equals(d.getStyle())){
				picList.add(d);
			}else if("3".equals(d.getStyle())){
				tuwenList.add(d);
			}
		}
		map.put("wenziList",wenziList);
		map.put("picList",picList);
		map.put("tuwenList",tuwenList);
		return map;
	}
	/**
	 * @param decorateContent
	 * @author liuhg
	 * @param request 
	 * @Description 更新内容
	 */
	public void updateContent(DecorateContent decorateContent, HttpServletRequest request) {
		String picPath=decorateContent.getPicUrl();
		if(picPath!=null&&!"".equals(picPath)){
			picPath=picPath.replaceAll(Resources.getString("temfilePath"), "");
			decorateContent.setPicUrl(picPath);
			ftpUploadPic(request, picPath);
		}
		decorateContentWriteMapper.updateContent(decorateContent);
	}
	
	/**
	 * @param id
	 * @author liuhg
	 * @Description 删除内容
	 */
	public void deleteContent(Integer id) {
		DecorateContent decorateContent=decorateContentReadMapper.selectContentById(id);
		if(decorateContent!=null){
			String picUrl=decorateContent.getPicUrl();
			if(StringUtils.isNotEmptyString(picUrl)){
			 picUrl=picUrl.replace("/decorate/", "");
			 FtpUtil.delFile(Resources.getString("picFtpHost"), "decorate", "decorate", picUrl);
			}
		}
		decorateContentWriteMapper.deleteContent(id);
	}
	/**
	 * @param map
	 * @author liuhg
	 * @Description 上下线
	 */
	public void changeOnline(Map<String, Object> map) {
		decorateBaseWriteMapper.changeOnline(map);
	}
	/**
	 * @param id
	 * @author liuhg
	 * @Description
	 */
	public void deleteDecorate(Integer id) {
		decorateBaseWriteMapper.deleteDecorate(id);
	}
}
