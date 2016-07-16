package com.tuanche.console.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tuanche.bean.admin.Answer;
import com.tuanche.bean.admin.Question;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.service.KeywordService;
import com.tuanche.service.admin.AnswerService;
import com.tuanche.service.admin.QuestionService;



public class ExamExcel07QuestionImport {
	public static String readNewExcel(InputStream stream,HttpSession session) throws IOException{ 
			Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		    XSSFWorkbook workbook=null;
		    XSSFCell cell=null;
		     //创建输入流
		    // InputStream stream = new FileInputStream(file);
		    //获取Excel文件对象
		    workbook=new XSSFWorkbook(stream);
		    String style=null;
    		int t=0;
		    for(int index=0;index<workbook.getNumberOfSheets();index++){

		    	XSSFSheet sheet=workbook.getSheetAt(index);
		    	style=sheet.getSheetName();
		    	if(style==null||style.length()==0){
		    		continue;
		    	}
			    //行数(表头的目录不需要，从1开始)
		    	for(int i=1;i<=sheet.getLastRowNum();i++){
		    		//标示符变量 记录 是否解决问题************************************************

		    		for(int j=0;j<sheet.getRow(i).getLastCellNum();j++){
		    			//获取第i行，第j列的值
		    			int type=1;
		    			if(sheet.getRow(i)==null||sheet.getRow(i).getCell(j)==null){
		    				continue;
		    			}
		    			cell=sheet.getRow(i).getCell(j);
		    			if(j==0&&cell!=null&&!"".equals(cell.toString().trim())){
		    				//添加分类
		    				String kind=cell.getStringCellValue().trim();
		    				if(kind.length()>0&&!StringUtils.isEmpty(kind)){
		    				for(Integer kindid:GlobalConstants.questionKindmap.keySet()){
		    					if(GlobalConstants.questionKindmap.get(kindid).trim().equals(kind)){
		    						//
		    						t=1;
		    						continue;
		    					}
		    				}    				
		    				
		    			}else{
		    				return "no";
		    			}
		    				}
		    			if(t==0){
		    				return "no";
		    			}
		    			//添加提问内容
		    			if(j==2&&(cell==null||"".equals(cell.toString().trim())||cell.toString().length()>125)){
		    				return "no";
		    			}else{
		    				t=1;
		    			}
		    			//添加提问用户
		    			if(j==3&&(cell==null||"".equals(cell.toString().trim()))){
		    				return "no";
		    			}else{
		    				t=1;
		    			}
		    			
		    			for(int x=5;x<sheet.getRow(i).getLastCellNum();x+=2){
		    				//判断标示符变量为1则将答案设置为最佳。循环一次将变量置为初始值
		    				if(j==x&&cell!=null&&!"".equals(cell.toString().trim())){
		    					 type=sheet.getRow(i).getCell(j).getCellType();
			    				String answeruser=null;
			    				if(type==1){
			    					answeruser=cell.getStringCellValue().trim();
			    				}
			    				if(type==0){
			    					answeruser=(cell.getNumericCellValue()+"").substring(0,(cell.getNumericCellValue()+"").indexOf("."));
			    				}	
		    					if(answeruser.length()>0&&!StringUtils.isEmpty(answeruser)){
		    						t=1;		    						
		    						//
		    					}else{
			    					return "no";
			    				}
		    				}
		    			}//
		    		}
		    	}
		    	
		    	
		    }
		    return t==1?"yes":"no";
	}
}
