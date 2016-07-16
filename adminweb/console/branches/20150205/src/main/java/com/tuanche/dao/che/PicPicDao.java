package com.tuanche.dao.che;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.tuanche.bean.che.PicPic;
import com.tuanche.mapper.che.read.PicPicReadMapper;
import com.tuanche.mapper.che.write.PicPicWriteMapper;

@Repository
public class PicPicDao {
	@Resource
	private PicPicReadMapper read;
	@Resource
	private PicPicWriteMapper write;
	
	public List<PicPic> getPicAll(List<String> condition) {
		return read.getPicAllByPage(condition);
	}

	public void updateBeanOrStatus(Integer id,Integer type,Integer uid) {
		write.updateStatus(id,type,uid);
	}
	public void updateBeanOrStatusOrInsert(PicPic bean) {
		if(bean!=null){
			if(bean.getId()!=null){
				write.updateBean(bean);
			}else{
				write.insert(bean);
			}
		}
	}

	public PicPic findByID(Integer id) {
		return read.findByID(id);
	}

	public void simpleUpdate(PicPic bean) {
		write.updateBean(bean);
	}

	public void bathSavePic(ArrayList<PicPic> list) {
		write.bathSavePic(list);
	}

	public void updateCollectionBycollId(PicPic bean) {
		write.updateCollectionBycollId(bean);
	}

	public void coverUIniqueness(int collid,int status) {
		write.coverUIniqueness(collid,status);
	}

}
