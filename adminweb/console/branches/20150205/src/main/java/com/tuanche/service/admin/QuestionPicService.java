package com.tuanche.service.admin;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tuanche.bean.admin.DecorateBase;
import com.tuanche.bean.admin.QuestionPic;
import com.tuanche.commons.util.Resources;
import com.tuanche.mapper.admin.read.QuestionPicReadMapper;
import com.tuanche.mapper.admin.write.QuestionPicWriteMapper;
import com.tuanche.upload.FtpUtil;



@Service
public class QuestionPicService {
	@Autowired
	QuestionPicReadMapper picReadMapper;
	@Autowired
	QuestionPicWriteMapper picWriteMapper;
/**
 * 查询所有图片
 * @return
 */
	public List<QuestionPic> selectPicsByPage(){
	return 	picReadMapper.selectPicsByPage();
	}
	/**
	 * 查询单个图片进入修改页面
	 * @param id
	 * @return
	 */
	public QuestionPic selectPicById(Integer id){
		return picReadMapper.selectPicById(id);
	}
	/**
	 * 修改排序
	 * @param map
	 */
	public void updateSort(QuestionPic pic){
		picWriteMapper.updateSort(pic);
	}
	/**
	 * 图片删除
	 * @param pic
	 */
	public void updatePicStatus(QuestionPic pic){
		picWriteMapper.updatePicStatus(pic);
	}
	/**
	 * 图片新增
	 * @param pic
	 * @param request 
	 */
	//@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.SERIALIZABLE)
	public void addQuestionPic(QuestionPic pic, HttpServletRequest request){
		if(pic!=null){
			if(pic.getPicUrl()!=null&&!"".equals(pic.getPicUrl())){
				String topPath=pic.getPicUrl();
				topPath=topPath.replaceAll("/pic_tmp/recpic", "http://pic.tuanche.com/recpic/wenda");
				pic.setPicUrl(topPath);
				topPath=topPath.replaceAll("/pic.tuanche.com/recpic/wenda/","");
				ftpUploadPic(request, topPath);
			}
			if(pic.getPicUrl()!=null&&!"".equals(pic.getPicUrl())){
				String picUrl=pic.getPicUrl();
				picUrl=picUrl.replaceAll("/pic_tmp/recpic", "http://pic.tuanche.com/recpic/wenda");
				pic.setPicUrl(picUrl);
				pic.setPicStatus((byte)2);
				ftpUploadPic(request, picUrl);
			}
		}
		picWriteMapper.addQuestionPic(pic);
	}
		
	
	/**
	 * 图片修改全
	 * @param pic
	 */
	//@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.SERIALIZABLE)
	public void updatePicAll(QuestionPic pic,HttpServletRequest request){
		if(pic!=null){
			String topPath=pic.getPicUrl();
			topPath=topPath.replaceAll("/pic_tmp/recpic","http://pic.tuanche.com/recpic/wenda");
			pic.setPicUrl(topPath);
			topPath=topPath.replaceAll("/pic.tuanche.com/recpic/wenda/","");
			ftpUploadPic(request, topPath);
		}
		if(pic.getPicUrl()!=null&&!"".equals(pic.getPicUrl())){
			String picUrl=pic.getPicUrl();
			picUrl=picUrl.replaceAll("/pic_tmp/recpic", "http://pic.tuanche.com/recpic/wenda");
			pic.setPicUrl(picUrl);
			picUrl=picUrl.replaceAll("/pic.tuanche.com/recpic/wenda/","");
			ftpUploadPic(request, picUrl);
		}
		picWriteMapper.updatePicAll(pic);
	}
	/**
	 * @param request
	 * @author wtk
	 * @Description 将图片上传到服务器
	 */
	public  void  ftpUploadPic(HttpServletRequest request,String picList){
		String realPath=request.getSession().getServletContext().getRealPath(request.getContextPath());
		String descFile=picList.substring(1,picList.length()-1);
		System.out.println(descFile);
		descFile=descFile.substring(descFile.indexOf("/")+1, descFile.lastIndexOf("/"));
		picList=Resources.getString("temfilePath")+picList;
		String fileName=picList.substring(picList.lastIndexOf("/")+1);
		realPath=realPath+Resources.getString("temfilePath")+"recpic/"+descFile+"/";
		File file=new File(realPath+fileName);
		if(file.exists()){
			FtpUtil.ftpUpload(Resources.getString("picFtpHost"), Resources.getString("wendaftp.username"), Resources.getString("wendaftp.password"), realPath,"wenda/"+descFile, fileName);
		}
		System.out.println("realpath=================="+realPath+"          descFile===="+descFile+"          filename======"+fileName);
	}
}
