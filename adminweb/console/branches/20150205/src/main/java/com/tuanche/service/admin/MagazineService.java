package com.tuanche.service.admin;


import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.bean.admin.Article;
import com.tuanche.bean.admin.Employe;
import com.tuanche.bean.admin.Magazine;
import com.tuanche.commons.util.Resources;
import com.tuanche.mapper.admin.read.ArticleReadMapper;
import com.tuanche.mapper.admin.read.EmployeReadMapper;
import com.tuanche.mapper.admin.read.MagazineReadMapper;
import com.tuanche.mapper.admin.write.ArticleWriteMapper;
import com.tuanche.mapper.admin.write.EmployeWriteMapper;
import com.tuanche.mapper.admin.write.MagazineWriteMapper;
import com.tuanche.upload.FtpUtil;
import com.tuanche.util.KevinUtil;




@Service
public class MagazineService {
	@Autowired
	private MagazineReadMapper magazineReadMapper;
	@Autowired
	private MagazineWriteMapper magazineWriteMapper;
	@Autowired
	private ArticleReadMapper articleReadMapper;
	@Autowired
	private ArticleWriteMapper articleWriteMapper;
	@Autowired
	private EmployeReadMapper employeReadMapper;
	@Autowired
	private EmployeWriteMapper employeWriteMapper;
	
	
	public Employe selectEmployeById(Integer id) {
		return employeReadMapper.selectEmployeById(id);
	}


	public List<Employe> selectEmpByPage() {
		return employeReadMapper.selectEmpByPage();
	}
	
	
	
	
	/**
	 * 图片新增
	 * @param pic
	 * @param request 
	 */
	//@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.SERIALIZABLE)
	public void addQuestionPic(Employe employe, HttpServletRequest request){
			if(employe.getEmployePic()!=null&&!"".equals(employe.getEmployePic())){
				/*String oldurl=employe.getOldUrl();
				oldurl=oldurl.substring(oldurl.indexOf("zhoukan"),oldurl.length());
				KevinUtil.ftpDelImg(oldurl.trim(), "picFtpHost", "wendaftp.username", "wendaftp.password");*/
				String picUrl=employe.getEmployePic();
				picUrl=picUrl.replaceAll("/pic_tmp/zhoukan", "http://pic.tuanche.com/recpic/zhoukan");
				employe.setEmployePic(picUrl);
				employe.setEmployeStatus((byte)0);
				picUrl=picUrl.replaceAll("/pic.tuanche.com/recpic/zhoukan/","");
				ftpUploadPic(request, picUrl);
			}
		
		employeWriteMapper.addEmploye(employe);
	}
		
	
	/**
	 * 图片修改全
	 * @param pic
	 */
	//@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.SERIALIZABLE)
	public void updatePicAll(Employe employe,HttpServletRequest request){
		if(employe.getEmployePic()!=null&&!"".equals(employe.getEmployePic())){
			String oldurl=employe.getOldUrl();
			oldurl=oldurl.substring(oldurl.indexOf("zhoukan"),oldurl.length());
			KevinUtil.ftpDelImg(oldurl.trim(), "picFtpHost", "wendaftp.username", "wendaftp.password");
			String picUrl=employe.getEmployePic();
			picUrl=picUrl.replaceAll("/pic_tmp/zhoukan", "http://pic.tuanche.com/recpic/zhoukan");
			employe.setEmployePic(picUrl);
			picUrl=picUrl.replaceAll("/pic.tuanche.com/recpic/zhoukan/","");
			ftpUploadPic(request, picUrl);
		}
		employeWriteMapper.updateEmploye(employe);
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
		realPath=realPath+Resources.getString("temfilePath")+"zhoukan/"+descFile+"/";
		File file=new File(realPath+fileName);
		if(file.exists()){
			FtpUtil.ftpUpload(Resources.getString("picFtpHost"), Resources.getString("wendaftp.username"), Resources.getString("wendaftp.password"), realPath,"zhoukan/"+descFile, fileName);
		}
		System.out.println("realpath=================="+realPath+"          descFile===="+descFile+"          filename======"+fileName);
	}


	public void updateEmpStatus(Byte status, Integer updateEmp, Integer id) {
		employeWriteMapper.updateEmpStatus(status,updateEmp,id);
	}


	public List<Article> articleByPage(Article article) {
		return articleReadMapper.articleByPage(article);
	}


	public Article selectArticleById(Integer id) {
		return articleReadMapper.selectArticleById(id);
	}
	public List<Magazine> magazinesByPage(Magazine magazine){
		return magazineReadMapper.magazinesByPage(magazine);
	}
	public List<Magazine> selectMagazines(){
		return magazineReadMapper.selectMagazines();
	}


	public void updateArtPicAll(Article article, HttpServletRequest request) {
		if(article.getPicture()!=null&&!"".equals(article.getPicture())){
			String oldurl=article.getHiddenUrl();
			oldurl=oldurl.substring(oldurl.indexOf("zhoukan"),oldurl.length());
			KevinUtil.ftpDelImg(oldurl.trim(), "picFtpHost", "wendaftp.username", "wendaftp.password");
			String picUrl=article.getPicture();
			picUrl=picUrl.replaceAll("/pic_tmp/zhoukan", "http://pic.tuanche.com/recpic/zhoukan");
			article.setPicture(picUrl);
			picUrl=picUrl.replaceAll("/pic.tuanche.com/recpic/zhoukan/","");
			ftpUploadPic(request, picUrl);
		}
		articleWriteMapper.updateArticle(article);
		}


	public void addArticlePic(Article article, HttpServletRequest request) {
		if(article.getPicture()!=null&&!"".equals(article.getPicture())){
			/*String oldurl=article.getHiddenUrl();
			oldurl=oldurl.substring(oldurl.indexOf("zhoukan"),oldurl.length());
			KevinUtil.ftpDelImg(oldurl.trim(), "picFtpHost", "wendaftp.username", "wendaftp.password");*/
			String picUrl=article.getPicture();
			picUrl=picUrl.replaceAll("/pic_tmp/zhoukan", "http://pic.tuanche.com/recpic/zhoukan");
			article.setPicture(picUrl);
			article.setArticleStatus((byte)1);
			picUrl=picUrl.replaceAll("/pic.tuanche.com/recpic/zhoukan/","");
			ftpUploadPic(request, picUrl);
		}
	
		articleWriteMapper.addArticle(article);
	}

/**
 * 修改文章状态
 * @param status
 * @param updateEmp
 * @param id
 */
	public void updateArtStatus(Byte status, Integer updateEmp, Integer id) {
		articleWriteMapper.updateArtStatus(status,updateEmp,id);
	}


public Magazine selectMagazineById(Integer id) {
	return magazineReadMapper.selectMagazineById(id);
}


public void updateMagazine(Magazine magazine) {
	magazineWriteMapper.updateMagazine(magazine);
}


public void addMagazine(Magazine magazine) {
	magazineWriteMapper.addMagazine(magazine);
}


public void updateMagStatus(Byte status, Integer updateEmp, Integer id) {
	magazineWriteMapper.updateMagStatus(status,updateEmp,id);
}


public int selectMagCountById(Integer edit,Integer id) {
	return magazineReadMapper.selectMagCountById(edit,id);
}


public int selectArtCountById(Integer sort, Integer id,Integer magazineId) {
	return articleReadMapper.selectArtCountById(sort,id,magazineId);
}
	

}
