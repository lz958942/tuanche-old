package com.tuanche.service.che;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.bean.che.PicAtlasBean;
import com.tuanche.bean.che.PicPic;
import com.tuanche.dao.che.PicAtlasDao;
import com.tuanche.smc.util.ZiXunDateUtil;

@Service
public class PicAtlasService {
	@Autowired
private PicAtlasDao atlasDao;

	public List<PicAtlasBean> getAtlas() {
		return atlasDao.getAtlas();
	}
	public List<PicAtlasBean> getAtlasByName(String name) {
		return atlasDao.getAtlasByName(name);
	}
	public int findByName(PicPic picBean,int uid) {
		if(picBean!=null &&picBean.getCollectionName()!=null){
			
		}
		PicAtlasBean bena= atlasDao.findByName(picBean.getCollectionName());
		if(bena==null){
			bena=new PicAtlasBean(picBean.getPicTitle(), picBean.getDesc(), picBean.getCollectionName(), uid,ZiXunDateUtil.getDateToString());
			atlasDao.insert(bena);
		}
		return bena.getId();
	}

	public PicAtlasBean getcollByid(Integer id) {
		return atlasDao.findByid(id);
	}

	public void updateCollection(PicAtlasBean bean) {
		atlasDao.updateCollection(bean);
	}
}
