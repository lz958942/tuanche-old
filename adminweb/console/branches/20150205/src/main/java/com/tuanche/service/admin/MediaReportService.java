package com.tuanche.service.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.bean.admin.MediaReport;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.dao.admin.MediaReportDao;
import com.tuanche.smc.util.ZiXunDateUtil;

@Service
public class MediaReportService {
	@Autowired
	private MediaReportDao dao;

	public List<MediaReport> list(MediaReport bean) {
		if(bean!=null){
		List<String> condition=initCondition(bean);
		if(condition!=null && condition.size()>0){
			return dao.list(condition);
		}
		}
		return null;
	}

	private List<String> initCondition(MediaReport bean) {
		List<String> condition=new ArrayList<String>();
		if(bean !=null){
			if(bean.getId()!=null){
				condition.add("id = "+bean.getId());
				}
			if(bean.getStatus()!=null){
			condition.add("status = "+bean.getStatus());
			}
			if(bean.getReportTime()!=null && bean.getReportTime().length()>0 ){
				condition.add("report_time >= '"+bean.getReportTime()+"'");
				}
			if(bean.getReportTimeNnd()!=null && bean.getReportTimeNnd().length()>0 ){
				condition.add("report_time <= '"+bean.getReportTimeNnd()+"'");
				}
			if(bean.getTitle()!=null && bean.getTitle().length()>0 ){
				condition.add("title LIKE '%"+bean.getTitle()+"%'");
				}
			condition.add("status > -1");
			return condition;
		}else{
			condition.add("status > -1");
			return condition;
		}
	}

	public MediaReport getMediaReportById(Integer id) {
		return dao.getMediaReportById(id);
	}

	public void saveXW(MediaReport bean,HttpSession session) {
		if(bean!=null){
			initBean(bean,session);
			dao.save(bean);
		}
		
	}

	private void initBean(MediaReport bean,HttpSession session) {
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		if(sessionUser!=null&&sessionUser.getId()!=null && bean !=null){
			if(bean.getId()==null){
				bean.setCreateTime(ZiXunDateUtil.getDateToString().trim());
				bean.setCreateUid(sessionUser.getId());
			}else{
				bean.setUpdateTime(ZiXunDateUtil.getDateToString());
				bean.setUpdateUid(sessionUser.getId());
			}
			if(bean.getStatus()!=null && bean.getStatus()==1){
				bean.setPublishUid(sessionUser.getId());
			}
		}
	}

	public void operation(Integer id, Integer type, String ids,HttpSession session) {
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		if(sessionUser!=null&&sessionUser.getId()!=null && type !=null){
		if(id!=null){
			//单个
			dao.operation(id,type,sessionUser.getId());
		}else{
			//批量
			if(ids!=null&& ids.length()>0){
			dao.operation(Arrays.asList(ids.split(",|，")),type,sessionUser.getId());	
			}
			}
		}
		
	}

	public boolean titleRepetition(String title) {
		List<MediaReport> ts=dao.titleRepetition(title);
		if(ts==null || ts.size()==0){
			return true;
		}
		return false;
	}
}
