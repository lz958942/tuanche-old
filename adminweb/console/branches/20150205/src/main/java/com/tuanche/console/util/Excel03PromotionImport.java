package com.tuanche.console.util;


import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.tuanche.bean.admin.Promotion;


public class Excel03PromotionImport {
	public static List<Promotion> readNewExcel(InputStream stream) throws BiffException, IOException{
		   //创建一个list 用来存储读取的内容
		    List<Promotion> list = new ArrayList<Promotion>();
		    Workbook workbook=null;
		    Cell cell=null;
		    Map<String,List<String>> mapList=new HashMap<String,List<String>>();
		     //创建输入流
		    // InputStream stream = new FileInputStream(file);
		    //获取Excel文件对象
		    workbook=Workbook.getWorkbook(stream);
		    String style=null;
		    Promotion promotion=null;
		    for(int index=0;index<workbook.getNumberOfSheets();index++){
		    	Sheet sheet=workbook.getSheet(index);
		    	style=sheet.getName();
		    	if(style==null||style.length()==0||style.trim().equals("基础数据")){
		    		continue;
		    	}
			    //行数(表头的目录不需要，从1开始)
		    	for(int i=1;i<sheet.getRows();i++){
		    		int t=0;
		    		promotion= new Promotion();
		    		for(int j=0;j<sheet.getColumns();j++){
		    			//获取第i行，第j列的值
		    			cell=sheet.getCell(j, i);
		    			if(j==0&&cell!=null&&!("".equals(cell.toString().trim()))){
		    				//添加城市
		    				String cvalue=cell.getContents().trim();
		    				if(cvalue.length()>0&&!StringUtils.isEmpty(cvalue)){
		    				for(String citycode:GlobalConstants.districtMap.keySet()){
		    					if(GlobalConstants.districtMap.get(citycode).getName().equals(cvalue)){
		    						promotion.setCityCode(citycode);
		    						promotion.setCityId(GlobalConstants.districtMap.get(citycode).getId());
		    						t=1;
		    					}
		    				}    				
		    				
		    			}else{
		    				t=0;
		    				break;
		    			}
		    				}
		    			//添加品牌id向model中
		    			if(j==1&&cell!=null&&!cell.getContents().trim().equals("")){
		    				String brand=null;
		    				if(cell.getContents().trim().equals("-汽车团购")){
		    					brand="0";
		    				}else if(cell.getContents().trim().equals("-其他")){
		    					brand="-1";
		    				}else if(cell.getContents().trim().contains("-")){
		    					 brand=cell.getContents().trim().substring(0,cell.getContents().trim().indexOf("-"));
		    				}
		    				
		    				if(brand.length()>0&&!StringUtils.isEmpty(brand)){
		    					for(Integer brandid:GlobalConstants.brandMap.keySet()){
		    						if(brand.equals("0")){
		    							promotion.setBrandId(0);
		    							t=1;
		    						}
		    						if(brand.equals("-1")){
		    							promotion.setBrandId(-1);
		    							t=1;
		    						}
		    						if(brand.equals(brandid+"")){
		    							promotion.setBrandId(brandid);
		    							t=1;
		    						}
		    					}
		    				  
		    				}else{
			    				t=0;
			    				break;
			    			}
		    						    				
		    			}
		    			//添加money 
		    			if(j==2&&cell!=null&&!("".equals(cell.toString().trim()))){
		    				String money=cell.getContents().trim();
		    				if(money.length()>0&&!StringUtils.isEmpty(money)){
		    					promotion.setMoney(money.replaceAll(",",""));;
		    					t=1;
		    				}else{
			    				t=0;
			    				break;
			    			}
		    				
		    			}
		    		}
		    		//把刚获取的列存入list
		    		if(t==1&&promotion.getMoney()!=null&&promotion.getCityId()!=null&&promotion.getBrandId()!=null){
		    			list.add(promotion);
		    		}
		    	}
		    	
		    }
		  return list;
	}
}
