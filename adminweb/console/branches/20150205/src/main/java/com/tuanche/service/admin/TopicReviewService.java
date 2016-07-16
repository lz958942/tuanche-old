package com.tuanche.service.admin;

import java.io.IOException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.bean.admin.Topic;
import com.tuanche.bean.admin.TopicReview;
import com.tuanche.console.dao.InitDao;
import com.tuanche.dao.admin.TopicReviewDao;
import com.tuanche.smc.util.ZiXunDateUtil;

@Service
public class TopicReviewService {
	@Autowired
	private TopicReviewDao dao;
	@Autowired
	private InitDao initDao;
	public List<TopicReview> reviewList(TopicReview bean) {
		return dao.reviewList(bean);
	}

	public TopicReview findByid(int id) {
		return dao.findByid(id);
	}

	public void operation(Integer id, String ids, Integer auditStatus) {
		
		if(null==id && ids!=null){
			//批量
			dao.operation(ids.split(","),auditStatus);
		}else {
			dao.operation(id,auditStatus);
		}
	}
	
	
	public void updateSort(int id,int topicId,int sort_old,int sort_new)
	{
		dao.updateSort( id, topicId, sort_old, sort_new);
	}

	public void UpdateTopicCache() {
		initDao.topicAll();
	}

	public Map<String, String> Excel(InputStream inputStream,String filename, Integer gid) {
		Map<String,String>map=null;
		if(inputStream!=null &&filename!=null){
			if(filename.endsWith(".xls")){
				map=excle2003(inputStream,gid);
			}else{
				map=excle2007(inputStream,gid);
			}
		}
		return map;
	}

	private Map<String, String> excle2007(InputStream inputStream,Integer gid) {
		Map<String,String>map=new ConcurrentHashMap<String,String>();
		 int size=0;
		 List<TopicReview> list=new ArrayList<TopicReview>();
		 StringBuilder errorName=new StringBuilder();
		 TopicReview review=new TopicReview(2,1,gid,0);
		 review.setAddTime(ZiXunDateUtil.getDateToString());
		TopicReview  tmpReview=null;
		XSSFWorkbook workbook=null;
	    XSSFCell cell=null;
	    XSSFSheet sheet=null;
	    String username=null;
	    String comment=null;
	    int length=0;
	    String tmpCode=null;
	    try {
			workbook=new XSSFWorkbook(inputStream);
			sheet=workbook.getSheetAt(0);
			length=sheet.getLastRowNum();
			for (int i = 1; i <= length; i++) {
				username=sheet.getRow(i).getCell(0).toString();
				comment=sheet.getRow(i).getCell(1).toString();
				if(null!=username && username.length()>0 && null !=comment && comment.length()>0){
					tmpReview=(TopicReview)review.clone();
					tmpReview.setUserName(username);
					tmpReview.setComment(comment);
					list.add(tmpReview);
				}
			}
			  if(list!=null && list.size()>0){
				  dao.batchInsert(list);
				  size=list.size();
			  }
		} catch (IOException e) {
			errorName.append(tmpCode+",");
			e.printStackTrace();
		}
	    map.put("size",String.valueOf(size));
	    map.put("error", errorName.length()==0?"":errorName.substring(0,errorName.length()-1));
		return map;
	}

	private Map<String, String> excle2003(InputStream inputStream,Integer gid) {
		Map<String,String>map=new ConcurrentHashMap<String,String>();
		List<TopicReview> list=new ArrayList<TopicReview>();
		TopicReview review=new TopicReview(2,1,gid,0);
		review.setAddTime(ZiXunDateUtil.getDateToString());
		TopicReview  tmpReview=null;
		  Workbook book = null;
		    Sheet sheet=null;
		    int size=0;
		    StringBuilder errorName=new StringBuilder();
			String name=null;
		    try {
				book = Workbook.getWorkbook(inputStream);
				 sheet = book.getSheet(0);  
				  int rowCount = sheet.getRows();  
				  for (int i = 1; i < rowCount; i++) { 
				      Cell[] cells = sheet.getRow(i);  
						tmpReview=(TopicReview) review.clone(); 
						tmpReview.setUserName(cells[0].getContents());
						tmpReview.setComment(cells[1].getContents());
						list.add(tmpReview);
						cells=null;
				      }
				  dao.batchInsert(list);
				  if(list!=null && list.size()>0){
					  size=list.size();
				  }
				  book=null;
				  list=null;
		    } catch (Exception e) {
		    	errorName.append(name+",");
				e.printStackTrace();
			}
		    map.put("size",String.valueOf(size));
		    map.put("error", errorName.length()==0?"":errorName.substring(0,errorName.length()-1));
		    return map;
	}
}
