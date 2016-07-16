package com.tuanche.mapper.che.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.che.Review;

public interface ReviewMapper {

	List<Review> getReviewAllByPage();

	Review getParticular(@Param("id")Integer id);

	List<Review> searchByPage(@Param("search")List<String> search,@Param("type")int type);

	List<Review> reviewHomeByPage(@Param("search")List<String> search);

	Review getReviewByid(@Param("id")Integer id);

}
