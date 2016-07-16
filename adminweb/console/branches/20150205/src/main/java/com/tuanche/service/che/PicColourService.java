package com.tuanche.service.che;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.bean.che.PicColour;
import com.tuanche.dao.che.PicColourDao;
@Service
public class PicColourService {
	@Autowired
	private  PicColourDao colourDao;

	public int findByName(String colourName) {
		PicColour col=colourDao.findByName(colourName);
		if(col==null){
			//添加
			col=new PicColour(colourName);
			colourDao.insert(col);
		}
		return col.getId();
	}
}
