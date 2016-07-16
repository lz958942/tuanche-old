package com.tuanche.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.net.ftp.FTPClient;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tuanche.commons.util.Resources;
import com.tuanche.console.util.StringUtils;
import com.tuanche.smc.util.ImageUtil;
import com.tuanche.smc.util.ZiXunDateUtil;
import com.tuanche.upload.FtpUtil;

public class KevinUtil {
	 public static String imageMagickPath ;
	 static String OS = System.getProperty("os.name").toLowerCase();
		static{
			imageMagickPath = Resources.getString("imageMagickPath");
		}

	public static boolean isNumeric(String str){
        for ( int i = str.length();--i>=0;){  
         if (!Character. isDigit(str.charAt(i))){
          return false;
         }
        }
        return true;
       }
	//num=getFrequency(string, "src=");
	/***
	 * 判断字符出现的次数
	 * **/
	public static int getFrequency(String parentString,String substring) {
	       if(parentString.length() < substring.length()){
	            return 0;
	       }
	       int leng=parentString.length();
	       int mum=0;
	       for (int i = 0; i < leng; i ++){
	             if(parentString.indexOf(substring)!=-1){
	                if(parentString.length()==0){
	                    break; 
	                }
	                if(parentString.indexOf(substring)==0){
	                    mum++;
	                }
	                parentString=parentString.substring(1);
	             }
	       }
	       return mum;
	    }
	public static boolean isChinese(String src) {
	       boolean isChinese= false;
	       if(src!=null && src.length()>0){
	            int length=src.length();
	            int byteLength=src.getBytes(). length;
	            if(byteLength!=length){
	              isChinese= true;
	           }
	       }
	       return isChinese;
	    }

	public  static boolean isNotListFirst(List<Object> list) {
		 if(list!=null && list.size()>0 && list.get(0)!=null ){
			 return true;
		 }
		 return false;
	 }
	public  static boolean isNotList(List<Object> list) {
		 if(list!=null && list.size()>0 ){
			 return true;
		 }
		 return false;
	 }
	//上传图片一张
	public static String picUpload(HttpServletRequest request, String picId,String delSrc,String folder) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile imgFile = multipartRequest.getFile(picId);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String dailyPath = simpleDateFormat.format(new Date()) + "/";
		String uname = System.currentTimeMillis() + ""+ new Random().nextInt(10000);
		String ext = ".jpg";
		String imgName = uname + ext;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String fullPath = "";
		String servletPath = request.getContextPath();
		String realPath = request.getSession().getServletContext().getRealPath(servletPath);
		fullPath = new File(realPath) + "/"+ Resources.getString("temfilePath") + folder+"/" + dailyPath+imgName;
		if(delSrc!=null){
			String fpath=realPath+"/pic_tmp/"+folder+"/"+ZiXunDateUtil.getEndDir()+delSrc;
			if(new File(fpath).exists()){
				new File(fpath).delete();
			}
		}
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
			return "错误";
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
			ImageUtil.cutImage(fullPath, fullPath, 600,400);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/pic_tmp/"+folder+"/"+ZiXunDateUtil.getEndDir()+"/"+imgName;
	}
	
	/******
	 * 压缩多张
	 * ***/
	public static String picUploadCompress(HttpServletRequest request, String picId,String delSrc,String folder,int compress) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile imgFile = multipartRequest.getFile(picId);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String dailyPath = simpleDateFormat.format(new Date()) + "/";
		String uname = System.currentTimeMillis() + ""+ new Random().nextInt(10000);
		String ext = ".jpg";
		String imgName = uname + ext;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String fullPath = "";
		String servletPath = request.getContextPath();
		String realPath = request.getSession().getServletContext().getRealPath(servletPath);
		fullPath = new File(realPath) + "/"+ Resources.getString("temfilePath") + folder+"/" + dailyPath+imgName;
		if(delSrc!=null){
			String fpath=realPath+"/pic_tmp"+folder+"/"+ZiXunDateUtil.getEndDir()+delSrc;
			if(new File(fpath).exists()){
				new File(fpath).delete();
			}
		}
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
			return "错误";
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
			if(compress > 0 && compress==1){
				ImageUtil.cutImage(fullPath, fullPath, 600,400);
			}else{
				//小图
				ImageUtil.cutImage(fullPath, fullPath.replaceAll(".jpg", "_s.jpg"), 150,150);
				//中图
				ImageUtil.cutImage(fullPath, fullPath.replaceAll(".jpg", "_m.jpg"), 350,350);
				//大图
				ImageUtil.cutImage(fullPath, fullPath.replaceAll(".jpg", "_b.jpg"), 600,400);
				if(new File(fullPath).exists()){
					new File(fullPath).delete();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/pic_tmp/"+folder+"/"+ZiXunDateUtil.getEndDir()+"/"+imgName.replaceAll(".jpg", "_s.jpg");
	}
	//上传图片 1个
	public static void ftpUpload(HttpSession session, String path,String FTPDir,String userName,String password) {
		if(path!=null &&path.length()>0){
			String fPath= session.getServletContext().getRealPath(path);
			if(new File(fPath).exists()){
				int subleng=path.lastIndexOf("/");
				int fPathSubleng=fPath.lastIndexOf("/");
				if(subleng==-1){
					subleng=path.lastIndexOf("\\");
				}
				if(fPathSubleng==-1){
					fPathSubleng=fPath.lastIndexOf("\\");
				}
				String  fileName=path.substring(subleng+1,path.length());
				path=new String(fPath.substring(0,fPathSubleng));
				FtpUtil.ftpUpload(Resources.getString("ftp.host"),Resources.getString(userName),Resources.getString(password), path+"/",FTPDir, fileName);
			}
		}
	}
	//上传图片 多个
	public static void ftpUploads(HttpSession session, String path,String FTPDir,String userName,String password,String[] names,int mrd) {
		if(path!=null &&path.length()>0 && names!=null){
			String fPath= session.getServletContext().getRealPath(path);
			String fPaths=fPath;
			String paths=path;
			for (String string : names) {
				fPaths= new String(fPath.replaceAll("s.jpg",string ));
				paths=new String(path.replaceAll("s.jpg",string ));
			if(new File(fPaths).exists()){
				int subleng=paths.lastIndexOf("/");
				int fPathSubleng=fPaths.lastIndexOf("/");
				if(subleng==-1){
					subleng=paths.lastIndexOf("\\");
				}
				if(fPathSubleng==-1){
					fPathSubleng=fPaths.lastIndexOf("\\");
				}
				String  fileName=paths.substring(subleng+1,paths.length());
				paths=new String(fPaths.substring(0,fPathSubleng));
				if("m.jpg".equals(string)){
					FtpUtil.ftpUpload(Resources.getString("ftp.host"),Resources.getString(userName),Resources.getString(password), paths+"/","m/"+mrd+"/"+FTPDir, fileName);
				}else if("b.jpg".equals(string)){
					FtpUtil.ftpUpload(Resources.getString("ftp.host"),Resources.getString(userName),Resources.getString(password), paths+"/","b/"+mrd+"/"+FTPDir, fileName);
				}else if("s.jpg".equals(string)){
					FtpUtil.ftpUpload(Resources.getString("ftp.host"),Resources.getString(userName),Resources.getString(password), paths+"/","s/"+mrd+"/"+FTPDir, fileName);
				}
				
			}
		}
		}
	}
	//删除ftp图片一张
public  static void ftpDelImg(String spic,String host,String userName,String pws) {
		
		FTPClient	ftpClient = new FTPClient();  
        try{  
            //连接  
            ftpClient.connect(Resources.getString(host));  
            ftpClient.login(Resources.getString(userName),Resources.getString(pws));
            if(spic!=null&&spic.length()>0){
               ftpClient.deleteFile(spic);
            }
            System.out.println("success=========================");
            //检测连接是否成功  
        }catch(Exception ex){  
            ex.printStackTrace();  
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
//删除ftp上文件
public  static void ftpDelImg(String spic,String host,String userName,String pws,String[] names) {
	
	FTPClient	ftpClient = new FTPClient();  
    try{  
        //连接  
        ftpClient.connect(Resources.getString(host));  
        ftpClient.login(Resources.getString(userName),Resources.getString(pws));
        if(spic!=null&&spic.length()>0){
        	if(names!=null &&names.length>0){
        		for (String string : names) {
        			ftpClient.deleteFile(spic.replaceAll("_s.jgp", string));
				}
        	}
        }
        System.out.println("success=========================");
        //检测连接是否成功  
    }catch(Exception ex){  
        ex.printStackTrace();  
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

	public static String getStringTimeByLong(long time) {
		String returnDate="";
		Date date = new Date(time);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		returnDate=df.format(date);
		return returnDate;
}
	//解压图片
	public  static Map<String,String> zipDecompression (InputStream in,String outPath ,HttpSession session){
		Map<String,String> map=new HashMap<String, String>();
		long startTime=System. currentTimeMillis();
         StringBuilder builder= new StringBuilder();
         StringBuilder errorName= new StringBuilder();
         ZipInputStream Zin= new ZipInputStream(in);//输入源 zip路径 
		 BufferedInputStream Bin= new BufferedInputStream(Zin); 
		 String fPath= session.getServletContext().getRealPath(outPath);
		 File fout= null; 
		 String longName=null;
		 String imgName=null;
		 String sourceName=null;
		 FileOutputStream out=null;
		 BufferedOutputStream Bout=null;
		 try { 
			 ZipEntry entry = Zin.getNextEntry();
			 boolean isMkdir =!entry.isDirectory();
		      fout= new File(fPath); 
		      if(!fout.exists()){ 
		            ( new File(fPath)).mkdirs(); 
		         }
		     while(entry!= null && isMkdir ){
		    	longName=new String(new Date().getTime()+"");
		    	if(OS.indexOf("win") != -1){
		    		imgName=new String(fPath+"\\"+longName);
		    	}else{
		    		imgName=new String(fPath+"/"+longName);	
		    	}
		    	sourceName=entry.getName();
		    	fout= new File(imgName+".jpg"); 
		        out = new FileOutputStream(fout); 
		        Bout= new BufferedOutputStream(out); 
		         byte[] tem = new byte[1024];
		         int b;
					while ((b=Bin.read(tem)) != -1) {
						Bout.write(tem);
					}
				 Bout.flush();
		         Bout.close(); 
		         out.close(); 
		         KevinUtil.imageCondense(imgName+".jpg",imgName+"_s.jpg", 160,100);
		         builder.append(longName+".jpg"+",");
		         entry=Zin.getNextEntry();
		     } 
		     Bin.close(); 
		     Zin.close(); 
		 } catch (Exception e) { 
			 deleteFile(imgName+".jpg",new File(fPath));
			 e.toString();
			 errorName.append(sourceName+",");
		     e.printStackTrace(); 
		 } 
         long endTime=System. currentTimeMillis(); 
         System. out.println( "耗费时间： "+(endTime-startTime)+" ms"); 
         map.put("fileName", builder.toString());
         map.put("error", errorName.toString());
         return  map;
     } 
	
	public static void sentResponseData(HttpServletResponse response, String string) {
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.close();
			out.println(string);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
	//删除图片
	public static void deleteFile(String imgNmae,File file){ 
		if(file.isDirectory() && imgNmae!=null){
			 File files[] = file.listFiles();             
		     for(int i=0;i<files.length;i++){          
		    	 if(imgNmae.equals(files[i].getName())){
		    		files[i].delete(); 
		    	 }
		     }
			}
		    } 
	
	
	public static void imageCondense(String in,String out,int w,int h) throws Exception {
		
		//IMOperation op = new IMOperation();
        /*if(w == 0){			//根据高度缩放图片  
            op.resize(null, h);
        }else if(h == 0){		//根据宽度缩放图片  
            op.resize(w, null);
        }else {
        	if (true) {
        		op.resize(w, h);
			}else {
				op.resize(width, height,"!");
			}
        }*/
		IMOperation op = new IMOperation();
		 op.coalesce();	//gif图片需要
		 op.resize(w,h);
		 op.addImage(in);
		 op.strip();
	     op.quality(75.00);
		 op.addImage(out);
		 ConvertCmd convert = new ConvertCmd();  
		 if (null!=imageMagickPath && imageMagickPath.length()>0) {
	            convert.setSearchPath(imageMagickPath);
			}
		 convert.run(op);
		 
	}
	
	public static String cutImage(int width, int height, String srcPath, String newPath,int type,String quality) throws Exception {
		   IMOperation op = new IMOperation();      
		   ConvertCmd cmd = new ConvertCmd(true);
		   op.addImage();
		   String raw = "";
		   if(type == 1){
		    //按像素
		    raw = width+"x"+height+"^";
		   }else{
		    //按像素百分比
		    raw = width+"%x"+height+"%";
		   } 
		   op.addRawArgs("-sample" ,  raw );  
		   if((quality !=null && quality.equals(""))){
		    op.addRawArgs("-quality" ,  quality );
		   }
		   op.addImage();  
		    
		   if(OS.indexOf("win") != -1) {
		    //linux下不要设置此值，不然会报错
		    cmd.setSearchPath("E:/work/ImageMagick-6.8.9-Q16");
		   }
		 
		   try{
		    cmd.run(op, srcPath, newPath);       
		   }catch(Exception e){
		    e.printStackTrace();
		   }
		   return newPath;
		 }		
	public static String picUploadByWH(HttpServletRequest request, String picId,String delSrc,String folder,int w,int h) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile imgFile = multipartRequest.getFile(picId);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String dailyPath = simpleDateFormat.format(new Date()) + "/";
		String uname = System.currentTimeMillis() + ""+ new Random().nextInt(10000);
		String ext = ".jpg";
		String imgName = uname + ext;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String fullPath = "";
		String servletPath = request.getContextPath();
		String realPath = request.getSession().getServletContext().getRealPath(servletPath);
		fullPath = new File(realPath) + "/"+ Resources.getString("temfilePath") + folder+"/" + dailyPath+imgName;
		if(delSrc!=null){
			String fpath=realPath+"/pic_tmp/"+folder+"/"+ZiXunDateUtil.getEndDir()+delSrc;
			if(new File(fpath).exists()){
				new File(fpath).delete();
			}
		}
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
			return "错误";
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
			//imageCondense(fullPath, fullPath, w,h);
			ImageUtil.cutImage(fullPath, fullPath, w, h, false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/pic_tmp/"+folder+"/"+ZiXunDateUtil.getEndDir()+"/"+imgName;
	}
	/**计算 两个日期相差天数**/
	public static String relativelyDate(String toDate,String endDate) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    long to = 0;
	    long from = 0;
		try {
			to = df.parse(toDate).getTime();
			from = df.parse(endDate).getTime();
			if(to < from){
				return "0";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	   return ((to - from) / (1000 * 60 * 60 * 24))+"";

	}
	public static void main(String[] args) {
		ftpDelImg("zhoukan/20141117/14162223366715229.jpg", "picFtpHost", "wendaftp.username", "wendaftp.password");
	}
} 

	
