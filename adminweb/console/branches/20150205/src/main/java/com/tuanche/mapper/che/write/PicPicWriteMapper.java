package com.tuanche.mapper.che.write;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.che.PicPic;

public interface PicPicWriteMapper {
	void updateStatus(@Param("id")Integer id, @Param("type")Integer type,@Param("uid")Integer uid);
	void updateBean(PicPic bean);
	void insert(PicPic bean);
	void bathSavePic(ArrayList<PicPic> list);
	void updateCollectionBycollId(PicPic bean);
	void coverUIniqueness(@Param("collid")int collid,@Param("status")int status);

}
