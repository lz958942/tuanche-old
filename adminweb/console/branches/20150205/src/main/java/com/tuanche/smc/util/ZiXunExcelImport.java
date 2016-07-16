package com.tuanche.smc.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.tuanche.console.bean.TSpecial;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class ZiXunExcelImport {
	
	public static List<TSpecial> readNewExcel(InputStream stream) throws BiffException, IOException{
		   //创建一个list 用来存储读取的内容
		   
		    ArrayList<TSpecial> list = new ArrayList<TSpecial>();
		    TSpecial special=null;
		    Workbook book = null;
		    Cell cell = null;
		    Sheet sheet=null;
		    
		    book = Workbook.getWorkbook(stream);  
            //得到第一个工作表对象  
            sheet = book.getSheet(0);  
            //得到第一个工作表中的总行数  
            int rowCount = sheet.getRows();  
            //日期格式化  
            DateFormat ft = new SimpleDateFormat("yyyy-MM-dd");  
            for (int i = 1; i < rowCount; i++) { 
            	
            	special = new TSpecial();  
                Cell[] cells = sheet.getRow(i);  
                System.out.println(cells.length);
               special.setSp_Name(cells[0].getContents());
  			  special.setSpAbstract(cells[1].getContents());
  			  special.setZixunIds(cells[2].getContents());
  			  special.setPicTitle(cells[3].getContents());
  			 
  			  special.setSpStatus(cells[4].getContents());
  			
  			  special.setOnline(Integer.valueOf(cells[5].getContents()));
  			  special.setKeyword(cells[6].getContents());
  			  special.setSp_Desc(cells[7].getContents());
  			  special.setCityId(Integer.valueOf(cells[8].getContents()));
  			  special.setBrandId(Integer.valueOf(cells[9].getContents()));
  			  special.setCarstyleId(Integer.valueOf(cells[10].getContents()));
  			  special.setOperateUserId(Integer.valueOf(cells[11].getContents()));
  			  special.setOperateUserName(cells[12].getContents());
  			  System.out.println(cells[13].getContents());
  			  
  			  /*special.setPub_Date(ZiXunExcelImport.getDate(new Long(strings[i+13])));
  			  special.setBeginDate(ZiXunExcelImport.getDate(new Long(strings[i+14])));
  			  special.setOnlineDate(ZiXunExcelImport.getDate(new Long(strings[i+15])));
  			  special.setTemplateId(Integer.valueOf(strings[i+16]));*/
  			  list.add(special);
            }  
			return list;
		 
		}
			
	
	
	
public static Date getDate(Long l){
	Date date = new Date(l);
	
	  return date;
}

public static void main(String[] args) {
	Date date = new Date(System.currentTimeMillis());
	DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM,Locale.CHINA);
	 //String dt = df.format(date);
	// System.out.println(date);
	 System.out.println(new Date());
	//  System.out.println(dt);
}
}