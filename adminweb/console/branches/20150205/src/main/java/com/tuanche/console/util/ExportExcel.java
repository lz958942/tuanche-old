package com.tuanche.console.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tuanche.bean.che.Brand;
import com.tuanche.bean.sem.Account;
import com.tuanche.console.web.AuthUtil;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExportExcel {
	public final static<E> String newExportExcel(String fileName,Map<String,String> titleValueMap,List<E> listContent,HttpServletRequest request,HttpServletResponse response){
		Map<Integer,Account> accountMap = (Map<Integer, Account>) request.getAttribute("accountMap");
		 Map<Integer, Brand> brandMap = AuthUtil.checkedBrandMap(null);
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ ExportExcel.getCorrectFileName(request, fileName));
			response.setContentType("application/msexcel");
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			WritableSheet sheet = workbook.createSheet("Sheet1", 0);
			jxl.SheetSettings sheetset = sheet.getSettings();
			sheetset.setProtected(false);
		    WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD);
		    WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
		    int m=0;
		   for (String s:titleValueMap.values()) {
			   sheet.addCell(new Label(m++, 0,s,wcf_center));
		   }  
		   Class classObj=null;
		   Method meth=null;
		   m=1;
		   Date date = null;
		   for(E obj:listContent){
			   classObj=obj.getClass();
			   int j=0;
			   for(String s:titleValueMap.keySet()){
				   meth=classObj.getDeclaredMethod("get"+s);
				   Object o=meth.invoke(obj);
				   if("Device".equals(s)){
					   String str = o.equals(new Integer(1))?"计算机":"移动";
					   sheet.addCell(new Label(j++,m,str==null?"":str.toString(),wcf_center));
					   continue;
				   } else if("BrandId".equals(s)){
					   Brand brand = brandMap.get(o);
					   sheet.addCell(new Label(j++,m,brand==null?"":brand.getName(),wcf_center));
					   continue;
				   }else  if("Accountid".equals(s)){
					   Account account = accountMap.get(o);
					   sheet.addCell(new Label(j++,m,account==null?"":account.getAccountName(),wcf_center));
					   continue; 
				   }
				   sheet.addCell(new Label(j++,m,o==null?"":o.toString(),wcf_center));
			   }
			   m++;
		   }
		   workbook.write();
		   workbook.close();  
		} catch (Exception e) {
			e.printStackTrace();
		}   
		
		return null;
	}
	
	
	 public  final static <E> String exportExcel(String fileName,Map<String,String> titleValueMap,List<E> listContent,HttpServletRequest request,HttpServletResponse response) {
		  String result="系统提示：Excel文件导出成功！";  
		  try {    
		   OutputStream os = response.getOutputStream();   
		   response.reset();
		   response.setHeader("Content-disposition", "attachment; filename="+ getCorrectFileName(request,fileName));     
		   response.setContentType("application/msexcel");
		   WritableWorkbook workbook = Workbook.createWorkbook(os);
		   WritableSheet sheet = workbook.createSheet("Sheet1", 0);
		   jxl.SheetSettings sheetset = sheet.getSettings();
		   sheetset.setProtected(false);
		   WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
		   WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD);
		   WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
		   
		   wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN);
		   wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE);
		   wcf_center.setAlignment(Alignment.CENTRE);
		   wcf_center.setWrap(false);
		   
		   WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
		   wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN);
		   wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE);
		   wcf_left.setAlignment(Alignment.LEFT);
		   wcf_left.setWrap(false);   
		   
		   int m=0;
		   for (String s:titleValueMap.values()) {
			   sheet.addCell(new Label(m++, 0,s,wcf_center));
		   }   
		   
		   Class classObj=null;
		   Method meth=null;
		   m=1;
		   DateFormat df = new DateFormat("yyyy-MM-dd HH:mm:ss");
		   WritableCellFormat cf1 = new WritableCellFormat(df);
		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   Date date = null;
		   for(E obj:listContent){
			   classObj=obj.getClass();
			   int j=0;
			   for(String s:titleValueMap.keySet()){
				   meth=classObj.getDeclaredMethod("get"+s);
				   Object o=meth.invoke(obj);
				   if("Bmtime".equals(s) && o != null){
					   date = sdf.parse(o.toString());
					   sheet.addCell(new DateTime(j++, m, date, cf1));
				   }else{
					   sheet.addCell(new Label(j++,m,o==null?"":o.toString(),wcf_left));
				   }
			   }
			   m++;
		   }
		   workbook.write();
		   workbook.close();   

		  } catch (Exception e) {
		   result="系统提示：Excel文件导出失败，原因："+ e.toString();
		   e.printStackTrace();
		  }
		  return result;
		 }
	 
	 public final static String exportApplyNumExcel(HttpServletRequest request,String fileName,Map<Integer,Map<String,Integer>> resultMap,HttpServletResponse response,String name, Map<Integer, Integer> zongSum){
		 String result="系统提示：Excel文件导出成功！";  
		  try {    
		   OutputStream os = response.getOutputStream();   
		   response.reset();
		   response.setHeader("Content-disposition", "attachment; filename="+getCorrectFileName(request,fileName));     
		   response.setContentType("application/msexcel");
		   WritableWorkbook workbook = Workbook.createWorkbook(os);
		   WritableSheet sheet = workbook.createSheet("Sheet1", 0);
		   jxl.SheetSettings sheetset = sheet.getSettings();
		   sheetset.setProtected(false);
		   WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
		   WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD);
		   WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
		   
		   wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN);
		   wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE);
		   wcf_center.setAlignment(Alignment.CENTRE);
		   wcf_center.setWrap(false);
		   
		   WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
		   wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN);
		   wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE);
		   wcf_left.setAlignment(Alignment.LEFT);
		   wcf_left.setWrap(false);   
		   
		   int m=0;
		   int temp=0;
		   Map<String,Integer> titleMap=resultMap.get(0);
		   
		   for (String s:titleMap.keySet()) {
			if(s.equals("0")) continue;
		    sheet.addCell(new Label(++m, 0,s,wcf_center));
		   }
		   sheet.addCell(new Label(++m,0,"总计",wcf_center));
		   sheet.addCell(new Label(++temp,1,titleMap.get("0")+"",wcf_left));
		   m=1;
		   for(Integer obj:resultMap.keySet()){
			   if(obj>0){
				   int j=0;
				   sheet.addCell(new Label(j++,m,name=="brand"?NameUtil.getBrandName(obj):NameUtil.getDistrictName(obj),wcf_left));
				   for(String s:titleMap.keySet()){
					   if(s.equals("0")) continue;
					   sheet.addCell(new Label(j++,m,resultMap.get(obj).containsKey(s)?(resultMap.get(obj).get(s)+""):"0",wcf_left));
				   }
				   sheet.addCell(new Label(j++,m,zongSum.get(obj)==null?0+"":zongSum.get(obj)+"",wcf_left));
				   m++;
			   }
		   }
		   sheet.addCell(new Label(0,m,"总计",wcf_left));
		   int tempz = 1;
		   int tempzz = 0;
		   for (String s:titleMap.keySet()) {
			if(s.equals("0")) continue;
				tempzz += titleMap.get(s);
				sheet.addCell(new Label(tempz++,m,titleMap.get(s)+"",wcf_left));
		   }
		   sheet.addCell(new Label(tempz++,m,tempzz+"",wcf_left));
		   workbook.write();
		   workbook.close();   

		  } catch (Exception e) {
		   result="系统提示：Excel文件导出失败，原因："+ e.toString();
		   e.printStackTrace();
		  }
		  return result;
	 }
	 
	 public static String getCorrectFileName(HttpServletRequest request,String fileName) throws UnsupportedEncodingException{
		 final String userAgent = request.getHeader("USER-AGENT");
	     if(userAgent.indexOf("MSIE")>-1){//IE浏览器
	    	 	return URLEncoder.encode(fileName,"UTF8");
	     }else if(userAgent.indexOf("Mozilla")>-1){//google,火狐浏览器
	            return  new String(fileName.getBytes(), "ISO8859-1");
	     }else{
	            return URLEncoder.encode(fileName,"UTF8");//其他浏览器
	     }
	 }
}
