package com.tuanche.mapper.che.write;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.che.Review;



public interface ReviewWriteMapper {
	
	void pass(@Param("id")Integer id,@Param("editId") int editId);

	void cream(@Param("id")Integer id,@Param("editId")Integer editId);

	void creamRemove(@Param("id")Integer id,@Param("editId")Integer editId);

	void passNo(@Param("id")Integer id, @Param("editId")Integer editId);
	void reply(@Param("id")Integer id, @Param("editId")Integer editId);

	void deleteReview(@Param("id")Integer id);

	void updateReview(Review review);

	void insertReview(Review review);
	
}
