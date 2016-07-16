package com.tuanche.service.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.bean.admin.Black;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.dao.admin.BlackDao;
@Service
public class BlackService {
	@Autowired
	private BlackDao blackDao;

	public List<Black> search(Black black) {
		List<String>search=new ArrayList<String>();
		if(black!=null){
			if(black.getWord()!=null &&black.getWord().length()>0 && !"NULL".equalsIgnoreCase(black.getWord())){
				search.add("t.word LIKE'%"+black.getWord()+"'");
			}
			if(black.getType()!=null){
				search.add("t.type ="+black.getType());
			}
			if(black.getUpdateUid()!=null){
				search.add("t.create_uid ="+black.getUpdateUid());
			}
		}
		search.add("t.status>0");
		return blackDao.search(search);
	}

	public void saveBlack(Black black,HttpSession session) {
		if(black!=null){
			if(isNotNull(black)){
				if(black.getId()!=null){
					//修改
					Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
					if(sessionUser!=null &&sessionUser.getId()!=null ){
						black.setUpdateUid(sessionUser.getId());
					}
					blackDao.update(black);
				}else{
					//添加
					Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
					if(sessionUser!=null &&sessionUser.getId()!=null ){
						black.setCreateUid(sessionUser.getId());
					}
					blackDao.saveBlack(black);
				}
			}
		}
	}
	public Black selectById(Integer id) {
		return blackDao.selectById(id);
	}
	private boolean isNotNull(Black black) {
		if(black==null){
			return false;
		}
		if(black.getWord()==null||black.getWord().length()==0){
			return false;
		}
		if(black.getType()==null){
			return false;
		}
		return true;
	}

	public boolean verifyWord(String word, Integer type) {
		if(word!=null && word.length()>0 && !"NULL".equalsIgnoreCase(word) &&type!=null){
			List<Black> blist=blackDao.verifyWord(word,type);
			if(blist!=null && blist.size()>0){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
}
