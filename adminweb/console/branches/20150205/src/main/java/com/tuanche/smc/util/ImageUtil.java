package com.tuanche.smc.util;

import java.io.IOException;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;

import com.tuanche.commons.util.Resources;
import com.tuanche.commons.util.StringUtils;


/**
 * Title:ImageUtil
 * Description: 用im4java对图片进行处理
 * @Create_by:yangzs
 * @Create_date:2014-4-23
 * @Last_Edit_By:
 * @Edit_Description:
 * @version: 
 */
public class ImageUtil {
	
	/** 
     * ImageMagick的路径 
     */
    public static String imageMagickPath ;
	
	static{
		imageMagickPath = Resources.getString("imageMagickPath");
	}
	
	/**
     * 根据坐标裁剪默认尺寸的图片
     * @Create_by:yangzs
     * @Create_date:2014-4-23
     * @param fromPath 要裁剪图片的路径
     * @param toPath 裁剪图片后的路径
     * @param xStr 起始横坐标
     * @param yStr 起始纵坐标
     * @param wStr 宽度
     * @param hStr 高度
     * @throws Exception
     * @Last_Edit_By:
     * @Edit_Description:
     * @Create_Version: 
     */
	public static void cutImage(String fromPath,String toPath,String xStr, String yStr,
			String wStr, String hStr) throws Exception {
		cutImage(fromPath, toPath, xStr, yStr, wStr, hStr, 138, 138);
	}  
  
    /**
     * 根据坐标裁剪图片
     * @Create_by:yangzs
     * @Create_date:2014-4-23
     * @param fromPath 要裁剪图片的路径
     * @param toPath 裁剪图片后的路径
     * @param xStr 起始横坐标
     * @param yStr 起始纵坐标
     * @param wStr 宽度
     * @param hStr 高度
     * @param _width 剪裁的宽度
     * @param _hight 剪裁的高度
     * @throws Exception
     * @Last_Edit_By:
     * @Edit_Description:
     * @Create_Version: 
     */
    public static void cutImage(String fromPath,String toPath,String xStr, String yStr,
			String wStr, String hStr,int _width,int _hight) throws Exception {
    	int x = 0, y = 0, w = 0, h = 0;
		if (StringUtils.isNotEmpty(xStr)) {
			x = Integer.parseInt(xStr);
		}
		if (StringUtils.isNotEmpty(yStr)) {
			y = Integer.parseInt(yStr);
		}
		if (StringUtils.isNotEmpty(wStr)) {
			w = Integer.parseInt(wStr);
		}
		if (StringUtils.isNotEmpty(hStr)) {
			h = Integer.parseInt(hStr);
		}
        IMOperation op = new IMOperation();  
        op.addImage(fromPath);  
        /** 
         * width：  裁剪的宽度 
         * height： 裁剪的高度 
         * x：       裁剪的横坐标 
         * y：       裁剪的挫坐标 
         */
        op.crop(w, h, x, y);
        op.p_repage();	//gif清空图片以外的空白
        if (_width>0) {
        	op.resize(_width, _hight);
		}
        op.addImage(toPath);
        ConvertCmd convert = new ConvertCmd();
        // linux下不要设置此值，不然会报错
        if (StringUtils.isNotEmpty(imageMagickPath)) {
            convert.setSearchPath(imageMagickPath);
		}
        convert.run(op);
    }
  
    /**
     * 缩放图片
     * @Create_by:yangzs
     * @Create_date:2014-4-23
     * @param fromPath 源图片路径 
     * @param toPath 缩放后图片的路径 
     * @param width 缩放后的图片宽度 
     * @param height 缩放后的图片高度 
     * @param flag true：按原图比例缩放，false：按指定大小缩放
     * @throws Exception
     * @Last_Edit_By:
     * @Edit_Description:
     * @Create_Version: 
     */
    public static void cutImage(String fromPath,String toPath,int width,int height,boolean flag) throws Exception {  
        IMOperation op = new IMOperation();
        op.addImage(fromPath);
        op.coalesce();	//gif图片需要
        if(width == 0){			//根据高度缩放图片  
            op.resize(null, height);
        }else if(height == 0){		//根据宽度缩放图片  
            op.resize(width, null);
        }else {
        	if (flag) {
        		op.resize(width, height);
			}else {
				op.resize(width, height,"!");
			}
        }
        op.addImage(toPath);
        ConvertCmd convert = new ConvertCmd();
        // linux下不要设置此值，不然会报错  
        if (StringUtils.isNotEmpty(imageMagickPath)) {
            convert.setSearchPath(imageMagickPath);
		}
        convert.run(op);
        compress(toPath, convert);
    }
    
    /**
     * 根据指定尺寸缩放图片
     * @Create_by:yangzs
     * @Create_date:2014-4-23
     * @param fromPath 源图片路径 
     * @param toPath 缩放后图片的路径 
     * @param width 缩放后的图片宽度 
     * @param height 缩放后的图片高度 
     * @throws Exception
     * @Last_Edit_By:
     * @Edit_Description:
     * @Create_Version: 
     */
    public static void cutImage(String fromPath,String toPath,int width,int height) throws Exception {  
       cutImage(fromPath, toPath, width, height, false);
    }  

    
    /** 
    * @author yangzs
    * @Title: watermark 
    * @Description: 添加水印
    * @param @param src
    * @param @param waterImg
    * @param @throws Exception     
    * @return void    
    * @throws 
    */
    public static void watermark(String src,String waterImg) throws Exception{
        IMOperation op = new IMOperation();
        op.addImage(src);
        op.strip();
        op.quality(75.00);
        op.addImage(waterImg);
        op.gravity("southeast").geometry(120,44,15,15);
        op.composite().addImage(src);
        ConvertCmd convert = new ConvertCmd();
        // linux下不要设置此值，不然会报错  
        if (StringUtils.isNotEmpty(imageMagickPath)) {
            convert.setSearchPath(imageMagickPath);
        }
        convert.run(op);
        compress(src, convert);
    }

    private static void compress(String src, ConvertCmd convert) throws IOException, InterruptedException, IM4JavaException {
        IMOperation compressOp = new IMOperation();
        compressOp.strip();
        compressOp.quality(75.00);
        compressOp.addImage(src);
        compressOp.addImage(src);
        convert.run(compressOp);
    }
    
    
}