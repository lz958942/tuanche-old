package com.tuanche.mapper.admin.read;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tuanche.bean.admin.QuestionPic;

/**
 * 图片读取mapper
 * @author wtk
 *
 */
@Repository
public interface QuestionPicReadMapper {
	/**
	 * 查询所有图片
	 * @return
	 */
	List<QuestionPic>  selectPicsByPage();
	/**
	 * 根据id 查询图片
	 * @param id
	 * @return
	 */
	QuestionPic selectPicById(Integer id);

}
