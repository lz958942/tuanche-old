package com.tuanche.console.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.tuanche.bean.che.Brand;
import com.tuanche.bean.che.CarStyle;
import com.tuanche.console.bean.Keyword;


public class ExcelKeywordImport {
	public static List<Keyword> readNewExcel(InputStream stream) throws BiffException, IOException{
		   //创建一个list 用来存储读取的内容
		    List<Keyword> list = new ArrayList<Keyword>();
		    Workbook rwb = null;
		    Cell cell = null;
		    Map<String,List<String>> mapList=new HashMap<String,List<String>>();
		 
		     //创建输入流
		    // InputStream stream = new FileInputStream(file);
		    //获取Excel文件对象
		    rwb = Workbook.getWorkbook(stream);
		    String style=null;
		    Keyword keyword=null;
		    List<String> wordList=new ArrayList<String>();
		    for(int index=0;index<rwb.getNumberOfSheets();index++){
	    	 Sheet sheet = rwb.getSheet(index); 
	    	 style=sheet.getName();
	    	 if(style==null||style.length()==0){
	    		 continue;
	    	 }
		    //行数(表头的目录不需要，从1开始)
		    for(int i=1; i<sheet.getRows(); i++){
		    	int t=0;
		      //用来存储每一行的值
		      keyword = new Keyword(); 
		      //列数
		      for(int j=0; j<sheet.getColumns(); j++){
			      //获取第i行，第j列的值
			      cell = sheet.getCell(j,i);   
			      if(j==0){
			    	  String type=cell.getContents();
			    	  if(!StringUtils.isEmpty(cell.getContents())){
				    	  for(int key:GlobalConstants.typeMap.keySet()){
				    		  if(type!=null&&GlobalConstants.typeMap.get(key).trim().equals(type.trim())){
				    			 keyword.setType(key);
				    			 t=1;
				    		  }
				    	  }
			    	  }else{
			    		  t=0;
			    		  break;
			    	  }
			    	  if(t==0){
			    		  break;
			    	  }
			      }
			      if(j==1){
			    	  if(!wordList.contains(cell.getContents().trim())&&
			    			  (!StringUtils.isEmpty(cell.getContents().trim()))){
			    		  keyword.setWord(cell.getContents().trim());
			    		  keyword.setLevel(3);
			    		  wordList.add(cell.getContents().trim());
					      t=1;
			    	  }else{
			    		  t=0;
			    		  break;
			    	  }
			      }
			      if(j==2){
			    	  if(!StringUtils.isEmpty(cell.getContents())){
			    		  keyword.setDimName(cell.getContents());
			    	  } 
			      }
			      if(j==3){
			    	  if(!StringUtils.isEmpty(cell.getContents())){
			    		  boolean flag=true;
			    		  String brand=cell.getContents().trim();
			    		  Set<Map.Entry<Object,Object>> set=GlobalConstants.brands.entrySet();
			    		  Iterator<Map.Entry<Object, Object>> it=set.iterator();
			    		  while(it.hasNext()){
			    			  Brand b=(Brand) it.next().getKey();
			    			  if(brand.equals(b.getName().trim())){
			    				  keyword.setBrandId(b.getId()+"");
			    				  keyword.setCarStyleId("");
			    				  break;
			    			  }else{
			    				  if(flag){
			    					  Set<Map.Entry<Object,Object>> set1=GlobalConstants.carStyles.entrySet();
						    		  Iterator<Map.Entry<Object, Object>> it1=set1.iterator();
						    		  while(it1.hasNext()){
						    			  CarStyle c=(CarStyle) it1.next().getKey();
						    			  if(brand.equals(c.getCarStyleName().trim())){
						    				 keyword.setBrandId(c.getIds());
						    				 keyword.setCarStyleId(c.getId()+"");
						    				 flag=false;
						    				 break;
						    			  }else{
						    				  keyword.setBrandId(null);
						    				  keyword.setCarStyleId(null);
						    			  }
						    		  }
			    				  }
			    			  }
			    		  }
			    	  } 
			      }
			      if(j==4){
			    	  if(!StringUtils.isEmpty(cell.getContents())){
			    		  keyword.setBaiduIndex(StringUtils.strToInt(cell.getContents(),-1));
			    	  } 
			      }
			      if(j==5){
			    	  if(!StringUtils.isEmpty(cell.getContents())){
			    		  keyword.setRank(StringUtils.strToInt(cell.getContents(),-1));
			    	  } 
			      }
			      if(j==6){
			    	  if(!StringUtils.isEmpty(cell.getContents())){
			    		  keyword.setDescribe(cell.getContents());
			    	  } 
			      }
		     }
		     //把刚获取的列存入list
		  
		      if(t==1&&keyword.getWord()!=null){
		    	  list.add(keyword);
		      }
		     }
		   }
		  return list;
	}
}
