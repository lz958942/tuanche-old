package com.tuanche.service.che;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.bean.che.PersonCase;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.dao.che.PersonCaseDao;
import com.tuanche.smc.util.ZiXunDateUtil;
import com.tuanche.util.KevinUtil;

@Service
public class PersonCaseService {
	@Autowired
	private PersonCaseDao dao;
	public List<PersonCase> PEListByCondition(PersonCase bean) {
		List<String> condition=initCondition(bean);
		return dao.PEListByCondition(condition);
	}
	
	public void sava(PersonCase bean,HttpSession session) {
		if(bean!=null){
			if(bean.getId()==null){
				//新增
				initPESave(bean,session);
				dao.sava(bean);
			}else{
				//修改
				Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
				if(sessionUser!=null&&sessionUser.getId()!=null && bean !=null){
					bean.setUpdateTime(ZiXunDateUtil.getDateToString());
					bean.setUpdateUid(sessionUser.getId());
					if(bean.getPicture()!=null && bean.getPicture().length()>0 && bean.getPicture().startsWith("/pic_tmp") ){
						uploadPic(bean,session);
						bean.setPicture(bean.getPicture().replace("/pic_tmp/PE", "http://pic.tuanche.com/recpic/pcase/"));
					}
					dao.operation(bean);
				}
			}
		}
	}
	private void initPESave(PersonCase bean, HttpSession session) {
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		if(sessionUser!=null&&sessionUser.getId()!=null){	
		if(bean!=null){
				uploadPic(bean,session);
				bean.setCreateUid(sessionUser.getId());
				bean.setCrateTime(ZiXunDateUtil.getDateToString());
				bean.setStatus(0);
				if(bean.getPicture()!=null && bean.getPicture().length()>0){
					bean.setPicture(bean.getPicture().replace("/pic_tmp/PE", "http://pic.tuanche.com/recpic/pcase/"));
				}
				}
			}
	}
	public PersonCase operation(Integer id, Integer type) {
		if(id!=null && type!=null){
			if(type==1){
				// 1 操作
				return dao.operation(id);
			}else{
				//2 删除  3上线 
				dao.operation(id,type);
			}
		}
		return null;
	}
	//批量
	public void operation(HttpSession session, Integer type, String ids) {
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		if(sessionUser!=null&&sessionUser.getId()!=null){
			if(type!=null && ids!=null && ids.length()>0){
				 List<String> list =Arrays.asList(ids.split(",|，"));
				 dao.operation(list,type);
			}
		}
	}
	private List<String> initCondition(PersonCase bean) {
		List<String> condition=new ArrayList<String>();
		if(bean!=null){
			if(bean.getId()!=null){
				condition.add("pc.id = "+bean.getId());
			}
			if(bean.getCityId()!=null && bean.getCityId() > 0 ){
				condition.add("pc.city_id = "+bean.getCityId());
			}
			if(bean.getBrandId()!=null && bean.getBrandId()>0){
				condition.add("pc.brand_id = "+bean.getBrandId());
			}
			if(bean.getStyleId()!=null){
				condition.add("pc.style_id = "+bean.getStyleId());
			}
			if(bean.getPriceEvaluate()!=null){
				int type=bean.getPriceEvaluate();
				condition.add("pc.price_evaluate = "+bean.getPriceEvaluate());
			}
			if(bean.getUserName()!=null && bean.getUserName().length()>0 ){
				condition.add("pc.user_name LIKE '%"+bean.getUserName()+"%'");
			}
			if(bean.getStatus()!=null){
				condition.add("pc.status="+bean.getStatus());
			}
			if(bean.getCrateTime()!=null && bean.getCrateTime().length()>0){
				condition.add("pc.crate_time > "+bean.getCrateTime());
			}
			if(bean.getPicture()!=null && "0".equals(bean.getPicture())){
				condition.add("(picture IS NULL  OR picture='' ) ");
			}else if(bean.getPicture()!=null && "1".equals(bean.getPicture())){
				condition.add("(picture IS NOT NULL  and TRIM(picture) != '') ");
			}
			condition.add("pc.status > -1");
			return condition;
		}else{
			condition.add("pc.status > -1");
			return condition;
		}
	}
	private void uploadPic(PersonCase bean, HttpSession session) {
		if(bean.getPicture()!=null && bean.getPicture().length()>0 ){
			String path = session.getServletContext().getContextPath()+bean.getPicture();
			String fPath= session.getServletContext().getRealPath(path);
			if(new File(fPath).exists()){
				KevinUtil.ftpUpload(session,path,"pcase/"+ZiXunDateUtil.getEndDir(),"wendaftp.username","wendaftp.password");
			}
			}
		
	}

}
