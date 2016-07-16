package com.tuanche.service.che;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.bean.che.SysConfig;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.dao.che.SysConfigDao;
import com.tuanche.smc.util.ZiXunDateUtil;

@Service
public class SysConfigService {
	@Autowired
	private SysConfigDao sysConfigDao;

	public List<SysConfig> home(SysConfig sysConfig, Integer type) {
		List<String> condition=new ArrayList<>();
		//type==1 首页  type==2搜索
		if(type!=null && type==2 && sysConfig!=null ){
			//搜索
			if(sysConfig.getName()!=null && sysConfig.getName().length()>0 &&!"NULL".equals(sysConfig.getName()) ){
				condition.add("t.name LIKE '%"+sysConfig.getName()+"%'");
			}
			if(sysConfig.getKey()!=null && sysConfig.getKey().length()>0 &&!"NULL".equals(sysConfig.getKey()) ){
				condition.add("t.key LIKE '%"+sysConfig.getKey()+"%'");
			}
		}else if(type!=null && type==3){
			//编辑快捷回复
			if(sysConfig.getName()!=null && sysConfig.getName().length()>0){
				condition.add("t.name LIKE '%"+sysConfig.getName()+"%'");
			}
			condition.add("t.key = 'evaluate_reply_shortcut'");
		}else if(type!=null && type==4){
			//后续添加
			}
		//home
		condition.add("t.status > -1");
		List<SysConfig> list=sysConfigDao.selectAllByPage(condition);
		return list;
	}
	public void saveConfig(SysConfig sysConfig ,HttpSession session ) {
		if(sysConfig.getId()==null){
			initSaveConfig(sysConfig,1,session);
		}else{
			//保存
			initSaveConfig(sysConfig,2,session);
		}
	}
	private  void  initSaveConfig(SysConfig sysConfig,int type,HttpSession session) {
 		if(isAttributeIsNull(sysConfig)){
 			Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
 			if(sessionUser!=null &&sessionUser.getId()!=null ){
 				sysConfig.setCreateUid(sessionUser.getId());
 				if(type==1){
 					//保存
 				sysConfig.setStatus(1);
 				sysConfigDao.saveConfig(sysConfig);
 				}else{
 					//修改
 					sysConfigDao.updateConfig(sysConfig);
 				}
 			}
		}
		
	}

	private boolean isAttributeIsNull(SysConfig SysConfig) {
			if(SysConfig==null){
				return false;
			}
			if(SysConfig.getCode()==null||SysConfig.getCode().length()==0||"NULL".equals(SysConfig.getCode())){
				return false;
			}
			if(SysConfig.getDesc()==null||SysConfig.getDesc().length()==0||"NULL".equals(SysConfig.getDesc())){
				return false;
			}
			if(SysConfig.getKey()==null||SysConfig.getKey().length()==0||"NULL".equals(SysConfig.getKey())){
				return false;
			}
			if(SysConfig.getName()==null||SysConfig.getName().length()==0||"NULL".equals(SysConfig.getName())){
				return false;
			}
			return true;
	}
	public SysConfig updateBefore(Integer id) {
		return sysConfigDao.updateBefore(id);
	}
	public  boolean isNumeric(String str){
		  for (int i = str.length();--i>=0;){   
		   if (!Character.isDigit(str.charAt(i))){
		    return false;
		   }
		  }
		  return true;
		 }
	public Boolean verification(String key, String code) {
		List<SysConfig> list=sysConfigDao.verification(key,code);
		if(list!=null&& list.size()>0){
			return false;
		}
		return true;
	}
	
	
	
	/************编辑快捷回复模块****************/
	public void saveEditConfig(SysConfig sysConfig,HttpSession session) {
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
			if(sessionUser!=null && sessionUser.getId()!=null ){
				sysConfig.setCreateUid(sessionUser.getId());
		if(sysConfig!=null){
			if(sysConfig.getId()==null){
			//编号
			sysConfig.setKey("evaluate_reply_shortcut");
			Integer code=sysConfigDao.getCodeByKey("evaluate_reply_shortcut");
			if(code!=null){
				sysConfig.setCode(code+1+"");
			}
			sysConfig.setStatus(1);
			sysConfigDao.saveConfig(sysConfig);
			}else{
				sysConfigDao.updateConfig(sysConfig);
			}
		}
	}
	}
}
