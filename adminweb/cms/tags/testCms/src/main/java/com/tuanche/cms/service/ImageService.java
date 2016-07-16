package com.tuanche.cms.service;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Service
public class ImageService {
	
	public static final Integer Max_Width_Show = 500;
	public static final Integer Max_Height_Show = 500;

	public String getPicAbsolutePath(CommonsMultipartFile mFile, String dbpath, String picpath) {
		if (!mFile.isEmpty()) {
			String fileName = mFile.getFileItem().getName();
			String ext = fileName.substring(fileName.lastIndexOf("."));
			File file = new File(dbpath + picpath + new Date().getTime() + ext); // 新建一个文件
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			try {
				mFile.getFileItem().write(file); // 将上传的文件写入新建的文件中
				return picpath +file.getName();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	/**
	 * 图片裁剪
	 */
	public static boolean cut(String imageType,String srcpath,String destpath,int x,int y,int w,int h){
		try {
			BufferedImage bi = ImageIO.read(new File(srcpath));
			int srcWidth = bi.getWidth();
			int srcHeight = bi.getHeight();
			if (srcWidth >= w && srcHeight >= h) {
				Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
				ImageFilter cropFilter = new CropImageFilter(x, y, w, h);
				Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
				BufferedImage tag = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
				Graphics g = tag.getGraphics();
				g.drawImage(img, 0, 0, null);
				g.dispose();
				ImageIO.write(tag, imageType, new File(destpath));
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 图片缩放(图片等比例缩放为指定大小)
	 */
	public static boolean zoom(String imageType,String srcpath,String destpath,int w,int h){
		try {
			File srcImageFile = new File(srcpath);
			File destImageFile = new File(destpath);
			BufferedImage srcBufferedImage = ImageIO.read(srcImageFile);
			int imgWidth = w;
			int imgHeight = h;
			int srcWidth = srcBufferedImage.getWidth();
			int srcHeight = srcBufferedImage.getHeight();
			if (srcHeight >= srcWidth) {
				imgWidth = (int) Math.round(((h * 1.0 / srcHeight) * srcWidth));
			} else {
				imgHeight = (int) Math.round(((w * 1.0 / srcWidth) * srcHeight));
			}
			BufferedImage destBufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2D = destBufferedImage.createGraphics();
			graphics2D.setBackground(Color.WHITE);
			graphics2D.clearRect(0, 0, w, h);
			graphics2D.drawImage(srcBufferedImage.getScaledInstance(imgWidth, imgHeight, Image.SCALE_DEFAULT), (w/2) - (imgWidth/2), (h/2) - (imgHeight/2), null);
			graphics2D.dispose();
			ImageIO.write(destBufferedImage,imageType, destImageFile);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 获取图片文件的类型.
	 * 
	 * @param imageFile
	 *            图片文件对象.
	 * @return 图片文件类型
	 */
	public static String getImageFormatName(File imageFile) {
		try {
			ImageInputStream imageInputStream = ImageIO.createImageInputStream(imageFile);
			Iterator<ImageReader> iterator = ImageIO.getImageReaders(imageInputStream);
			if (!iterator.hasNext()) {
				return null;
			}
			ImageReader imageReader = (ImageReader) iterator.next();
			imageInputStream.close();
			return imageReader.getFormatName().toLowerCase();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
