package com.tuanche.smc.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
	  public static void copyFile(String oldPath, String newPath,String fileName) { 
		  BufferedInputStream inBuff = null;
	        BufferedOutputStream outBuff = null;
	        try {
	            // 新建文件输入流并对它进行缓冲
	            inBuff = new BufferedInputStream(new FileInputStream(oldPath));

	            // 新建文件输出流并对它进行缓冲
	            File newFile=new File(newPath);
	            if(!newFile.exists()){
	            	newFile.mkdirs();
	            }
	            File newFiles=new File(newPath+fileName);
	            if(!newFiles.exists()){
	            	newFile.createNewFile();
	            }
	            outBuff = new BufferedOutputStream(new FileOutputStream(newFiles,true));

	            // 缓冲数组
	            byte[] b = new byte[1024 * 5];
	            int len;
	            while ((len = inBuff.read(b)) != -1) {
	                outBuff.write(b, 0, len);
	            }
	            // 刷新此缓冲的输出流
	            outBuff.flush();
	        } catch(Exception ee){
	        	ee.printStackTrace();
	        }finally {
	            // 关闭流
	            
					try {
						if (inBuff != null){
							inBuff.close();
						}
						 if (outBuff != null){
				                outBuff.close();
				        }
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        }

	   } 
	  public static void main(String[] args){
		  FileUtil.copyFile("D:/tuanche-xml/temp/zt/mc/1.html", "D:/tuanche-xml1/temp/zt/mc/","1.html");
	  }
}
