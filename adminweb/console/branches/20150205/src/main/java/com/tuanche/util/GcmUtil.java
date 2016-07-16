package com.tuanche.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import com.tuanche.commons.util.Resources;
import com.tuanche.commons.util.StringUtils;
import com.tuanche.upload.FtpUtil;

public class GcmUtil {
	//上传图片到服务器
	public static String uploadImg(String img,HttpServletRequest request){
		if(StringUtils.isEmpty(img) ){
			return "";
		}
		if(!img.contains(Resources.getString("temfilePath"))){
			return img;
		}
		String realPath=request.getSession().getServletContext().getRealPath(request.getContextPath());
		String imageFile = img.substring(img.indexOf("/zhuanti/"));
		File file=new File(realPath+Resources.getString("temfilePath")+imageFile);
		if(file.exists()){
			String ftphost = Resources.getString("zhuantiftp.host");
			String ftpname = Resources.getString("zhuantiftp.username");
			String ftppwd = Resources.getString("zhuantiftp.password");
			String descFile=imageFile.substring(1,imageFile.length()-1);
			descFile=descFile.substring(descFile.indexOf("/")+1, descFile.lastIndexOf("/"));
			String fileName=imageFile.substring(imageFile.lastIndexOf("/")+1);
			realPath=realPath+Resources.getString("temfilePath")+"zhuanti/"+descFile+"/";
			boolean ftpUpload = FtpUtil.ftpUpload(ftphost,ftpname, ftppwd,realPath, descFile, fileName);
			if(ftpUpload){
				return imageFile;
			}
		}
		return img;
	} 
}
