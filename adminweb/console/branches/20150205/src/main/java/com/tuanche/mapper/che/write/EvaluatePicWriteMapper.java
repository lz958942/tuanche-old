package com.tuanche.mapper.che.write;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.servlet.ModelAndView;

import com.tuanche.bean.che.EvaluatePic;

public interface EvaluatePicWriteMapper {

	void passPic(@Param("id")Integer id, @Param("state")int state,@Param("editId")Integer editId);

	void batchPass(@Param("id")Integer id,@Param("editId")Integer editId);

	void insertPic(@Param("picid")Integer picid, @Param("src")String src);

	void UpdatePic(@Param("eid")Integer eid, @Param("src")String src);

	void deleteReviewPic(@Param("eid")Integer eid);



}
