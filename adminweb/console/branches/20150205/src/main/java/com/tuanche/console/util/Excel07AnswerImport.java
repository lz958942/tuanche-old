package com.tuanche.console.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tuanche.bean.admin.Answer;


public class Excel07AnswerImport {
	public static List<Answer> readNewExcel(InputStream stream) throws IOException{
		   //创建一个list 用来存储读取的内容
		    List<Answer> list = new ArrayList<Answer>();
		    XSSFWorkbook workbook=null;
		    XSSFCell cell=null;
		     //创建输入流
		    // InputStream stream = new FileInputStream(file);
		    //获取Excel文件对象
		    workbook=new XSSFWorkbook(stream);
		    String style=null;
		    Answer answer=null;
		    for(int index=0;index<workbook.getNumberOfSheets();index++){
		    	XSSFSheet sheet=workbook.getSheetAt(index);
		    	style=sheet.getSheetName();
		    	if(style==null||style.length()==0){
		    		continue;
		    	}
			    //行数(表头的目录不需要，从1开始)
		    	for(int i=1;i<=sheet.getLastRowNum();i++){
		    		int t=0;
		    		answer= new Answer();
		    		for(int j=0;j<sheet.getRow(i).getLastCellNum();j++){
		    			//获取第i行，第j列的值
		    			if(sheet.getRow(i)==null||sheet.getRow(i).getCell(j)==null){
		    				continue;
		    			}
		    			cell=sheet.getRow(i).getCell(j);
		    			if(j==0&&cell!=null&&!"".equals(cell.toString().trim())){
		    				//添加父id
		    				int type=sheet.getRow(i).getCell(j).getCellType();
		    				String pId=null;
		    				if(type==1){
		    					pId=cell.getStringCellValue().trim();
		    				}
		    				if(type==0){
		    					pId=(cell.getNumericCellValue()+"").substring(0,(cell.getNumericCellValue()+"").indexOf("."));
		    				}
		    				if(pId.length()>0&&!StringUtils.isEmpty(pId)){
		    					answer.setQuestionId(Integer.parseInt(pId));	
		    					t=1;		    				
		    			}else{
		    				t=0;
		    				break;
		    			}
		    				}
		    			//添加页面显示用户
		    			if(j==1&&cell!=null&&!"".equals(cell.toString().trim())){
		    				int type=sheet.getRow(i).getCell(j).getCellType();
		    				String user=null;
		    				if(type==1){
		    					user=cell.getStringCellValue().trim();
		    				}
		    				if(type==0){
		    					user=(cell.getNumericCellValue()+"").substring(0,(cell.getNumericCellValue()+"").indexOf("."));
		    				}
		    				if(user.length()>0&&!StringUtils.isEmpty(user)){
	    					answer.setShowEmp(user);
	    					t=1;
		    				}else{
			    				t=0;
			    				break;
			    			}
		    						    				
		    			}
		    			//添加是否为最佳答案（状态）
		    			if(j==2&&cell!=null&&!"".equals(cell.toString().trim())){
		    				String adopt=null;
		    				if(cell.getStringCellValue().trim().equals("是")){
		    					adopt="1";
		    					t=1;
		    				}else if(cell.getStringCellValue().trim().equals("否")){
		    					adopt="0";
		    					t=1;
		    				}else{
		    					t=0;
			    				break;
			    			}
		    				if(adopt.equals("1")){
		    					answer.setAnswerAdopt(Byte.valueOf("1"));
		    				}else if(adopt.equals("0")){
		    					answer.setAnswerAdopt(Byte.valueOf("0"));
		    				}
		    				
		    			}
		    			//答案内容
		    			if(j==3&&cell!=null&&!"".equals(cell.toString().trim())){
		    				String content=cell.getStringCellValue()+"".trim();
		    				if(content.length()>0&&!StringUtils.isEmpty(content)){
		    					answer.setContent(content);
		    					t=1;
		    				}else{
			    				t=0;
			    				break;
			    			}
		    				
		    			}
		    		}
		    		//把刚获取的列存入list
		    		if(t==1){
		    			list.add(answer);
		    		}
		    	}
		    	
		    }
		  return list;
	}
}
