package com.tuanche.dao.che;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tuanche.bean.che.PicColour;
import com.tuanche.mapper.che.read.PicColourReadMapper;
import com.tuanche.mapper.che.write.PicColourWriteMapper;

@Repository
public class PicColourDao {
	@Autowired
private PicColourReadMapper colourRead;
	@Autowired
private PicColourWriteMapper colourWrite;
	
	public PicColour findByName(String colourName) {
		return colourRead.findByName(colourName);
	}

	public void insert(PicColour col) {
		colourWrite.insert(col);
	}

}
