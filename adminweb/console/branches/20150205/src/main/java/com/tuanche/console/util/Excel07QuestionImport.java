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



public class Excel07QuestionImport {
				 //删除/未解决/未推荐
			    private static final Byte DELETED = 0;
			    //下线/已解决/以推荐
			    private static final Byte AFTER_LINE = 1;
			    //未审核
			    private static final Byte NOEXAM = 2;
			    //未上线
			    private static final Byte LINE = 3;
			    //上线
			    private static final Byte ONLIE = 4;
			    
	public static List<Integer> readNewExcel(QuestionService questionService,AnswerService answerService,KeywordService keywordService,InputStream stream,HttpSession session) throws IOException{ 
			Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
			List<Answer> answers=new ArrayList<Answer>();
			List<Integer> questIds=new ArrayList<Integer>();
		    XSSFWorkbook workbook=null;
		    XSSFCell cell=null;
		    Answer answer=null;
		    //获取Excel文件对象
		    workbook=new XSSFWorkbook(stream);
		    String style=null;
		   
		    for(int index=0;index<workbook.getNumberOfSheets();index++){
		    	XSSFSheet sheet=workbook.getSheetAt(index);
		    	style=sheet.getSheetName();
		    	if(style==null||style.length()==0){
		    		continue;
		    	}
			    //行数(表头的目录不需要，从1开始)
		    	for(int i=1;i<=sheet.getLastRowNum();i++){
		    		//标示符变量 记录 是否解决问题************************************************
		    		Question question=new Question();
		    		answer=new Answer();
		    		int t=0;
		    		Byte q=0;//问题是否已解决状态。
		    		question= new Question();
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
		    						question.setSecondkindId(kindid);
		    						question.setFirstkindId(GlobalConstants.kindsmap.get(kindid).getParentId());
		    					}
		    				}    				
		    				
		    			}else{
		    				break;
		    			}
		    				}
		    			//添加关键词维度
		    			if(j==1&&cell!=null&&!"".equals(cell.toString().trim())){
		    				 type=sheet.getRow(i).getCell(j).getCellType();
		    				String kdId=null;
		    				if(type==1){
		    				 kdId=cell.getStringCellValue().trim();
		    				}
		    				if(type==0){
		    				kdId=(cell.getNumericCellValue()+"").substring(0,(cell.getNumericCellValue()+"").indexOf("."));
		    				}
		    				if(kdId.length()>0&&!StringUtils.isEmpty(kdId)){
		    					String[] kdIds=kdId.split(",");
		    					List<Integer> keywords= keywordService.findIdsByKdId(kdIds);
		    					StringBuffer Ids=new StringBuffer();	//关键词维度id
		    					StringBuffer kds=new StringBuffer();	//关键词维度
		    				for(Integer id:keywords){
		    					Ids.append(id+",");
		    					kds.append(keywordService.findKeyword(id)+",");
		    					}
		    				question.setDimension(kdId);
		    				question.setKdId(Ids.substring(0, Ids.lastIndexOf(",")));
		    				question.setKeyword(kds.substring(0,kds.lastIndexOf(",")));
		    				
		    				}else{
		    					break;
		    				}
		    			}
		    			//添加相关标签
		    			if(j==2&&cell!=null&&!"".equals(cell.toString().trim())){
		    				 type=sheet.getRow(i).getCell(j).getCellType();
		    				 String lable=null;
		    				 if(type==1){
		    					 lable=cell.getStringCellValue().trim();
		    				 }
		    				 if(type==0){
		    					 lable=(cell.getNumericCellValue()+"").substring(0,(cell.getNumericCellValue()+"").indexOf("."));
		    				 }
		    				
		    				if(lable.length()>0&&!StringUtils.isEmpty(lable)){
		    					question.setTitle(lable);
		    				}else{
			    				break;
			    			}
		    						    				
		    			}
		    			//添加提问用户
		    			if(j==3&&cell!=null&&!"".equals(cell.toString().trim())){
		    				 type=sheet.getRow(i).getCell(j).getCellType();
		    				String questionUser=null;
		    				if(type==1){
		    					questionUser=cell.getStringCellValue().trim();
		    				}
		    				if(type==0){
		    					questionUser=(cell.getNumericCellValue()+"").substring(0,(cell.getNumericCellValue()+"").indexOf("."));
		    				}	
		    				if(questionUser.length()>0&&!StringUtils.isEmpty(questionUser)){
		    					question.setShowEmp(questionUser);		    									    			
		    				}else{
		    					break;
		    				}
		    			}
		    			if(j==4&&cell!=null&&!StringUtils.isEmpty(cell.toString().trim())){
		    				 type=sheet.getRow(i).getCell(j).getCellType();
		    				String isResolve=null;
		    				if(type==1){
		    					isResolve=cell.getStringCellValue().trim();
		    				}
		    				if(type==0){
		    					isResolve=(cell.getNumericCellValue()+"").substring(0,(cell.getNumericCellValue()+"").indexOf("."));
		    				}
		    				if(isResolve.equals("是")){
		    					q=1;
		    				}
		    				if(isResolve.length()>0&&!StringUtils.isEmpty(isResolve)){
		    					question.setIsResolve(q);
		    					question.setQuestionStatus(ONLIE);
		    					question.setBuildEmp(employee.getId());
		    					question.setUpdateEmp(employee.getId());
		    					question.setIsRecom(DELETED);
				    			questionService.insertQuestion(question);
				    			t=question.getId();
				    			questIds.add(t);
		    				}else{
		    					break;
		    				}
		    			}
//添加问题是否已解决状态。是 则修改标示符变量，状态设置为1，保存问题，否则变量不变，将状态设置为0，保存问题
		    			//循环添加问题（用户以及内容）
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
		    						answer.setShowEmp(answeruser);
		    					}else{
			    					break;
			    				}
		    				}
		    				if(j==x+1&&cell!=null&&!"".equals(cell.toString().trim())){
		    					type=sheet.getRow(i).getCell(j).getCellType();
		    					String answerContent=null;
		    					if(type==1){
		    						answerContent=cell.getStringCellValue().trim();
		    					}
		    					if(type==0){
		    						answerContent=(cell.getNumericCellValue()+"").substring(0,(cell.getNumericCellValue()+"").indexOf("."));
		    					}
		    					if(answerContent.length()>0&&!StringUtils.isEmpty(answerContent)){
		    						answer.setAnswerAdopt(q);
		    						answer.setContent(answerContent);
		    						answer.setQuestionId(t);
		    						answer.setBuildEmp(employee.getId());
		    						answer.setUpdateEmp(employee.getId());
		    						answer.setAnswerStatus(ONLIE);
		    						answers.add(answer);
		    						//answerService.addAnswers(answers);
		    				    	//answers.removeAll(answers);
		    						answer=new Answer();
		    				    	q=0;
		    					}else{
			    					break;
			    				}
		    				}
		    			}//
		    		}
		    	}
		    	
		    	
		    }
		    answerService.addAnswers(answers);
		    return questIds;
	}
}
