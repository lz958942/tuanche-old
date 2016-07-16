package com.tuanche.cms.util;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;

/*import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;*/

//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGEncodeParam;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
  
public class ImageUtil {   
  
    /**  
     * 压缩图片文件<br>  
     * 先保存原文件，再压缩、上传  
     *   
     * @param oldFile  要进行压缩的文件全路径  
     * @param width   宽度  
     * @param height   高度  
     * @param quality  质量  
     * @param smallIcon  小图片的后缀  
     * @return 返回压缩后的文件的全路径  
     */  
    public static String zipImageFile(String oldFile, int width, int height,   
            float quality, String smallIcon) {   
        if (oldFile == null) {   
            return null;   
        } 
        String substring = oldFile.substring(0, oldFile.lastIndexOf("/"));
        File oldFileS = new File(substring);
        if(!oldFileS.exists()){
        	oldFileS.mkdir();
        }
        String newImage = null;   
        try {   
            /** 对服务器上的临时文件进行处理 */  
            Image srcFile = ImageIO.read(new File(oldFile));
            int w = srcFile.getWidth(null);
            System.out.println(w);
            int h = srcFile.getHeight(null);
            System.out.println(h);
            //width = w/4;
            //height = h/4;
            
            /** 宽,高设定 */  
            BufferedImage tag = new BufferedImage(width, height,   
                    BufferedImage.TYPE_INT_RGB);   
            tag.getGraphics().drawImage(srcFile, 0, 0, width, height, null);   
            String filePrex = oldFile.substring(0, oldFile.indexOf('.'));   
            /** 压缩后的文件名 */  
            newImage = filePrex + smallIcon   
                    + oldFile.substring(filePrex.length());   
  
            /** 压缩之后临时存放位置 */  
            FileOutputStream out = new FileOutputStream(newImage);   
  
           // JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);   
           // JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);   
            /** 压缩质量 */  
           // jep.setQuality(quality, true);   
            //encoder.encode(tag, jep);   
            out.close();   
  
        } catch (Exception e) {   
        	System.out.println("JPEGImageEncoder exception---------");
            e.printStackTrace();   
        }   
        return newImage;   
    }   
  
    /**
     * 通过url 获取文件
     * Administrator：zhaojl
     * @param fromPath
     * @param toPath
     */
    public static void saveToFile(String fromPath,String toPath) {
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		HttpURLConnection httpUrl = null;
		URL url = null;
		int BUFFER_SIZE = 1024;
		byte[] buf = new byte[BUFFER_SIZE];
		int size = 0;
		try {
			url = new URL(fromPath);
			httpUrl = (HttpURLConnection) url.openConnection();
			httpUrl.connect();
			bis = new BufferedInputStream(httpUrl.getInputStream());
			fos = new FileOutputStream(toPath);
			while ((size = bis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			fos.flush();
		} catch (IOException e) {
		} catch (ClassCastException e) {
		} finally {
			try {
				fos.close();
				bis.close();
				httpUrl.disconnect();
			} catch (IOException e) {
			} catch (NullPointerException e) {
			}
		}
	}
    
    public static void main(String args[]){   
        System.out.println(ImageUtil.zipImageFile("e:/95.jpg", 761, 321, 0.7f, "jpg"));   
           
    }   
}