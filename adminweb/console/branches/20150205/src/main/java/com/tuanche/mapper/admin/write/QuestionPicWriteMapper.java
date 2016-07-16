package com.tuanche.mapper.admin.write;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tuanche.bean.admin.Question;
import com.tuanche.bean.admin.QuestionPic;




/**
 * 图片保存，删除，修改
 * @author wtk
 *
 */

@Repository
public interface QuestionPicWriteMapper {
	/**
	 * 修改图片
	 * @param map
	 */
	void updatePic(QuestionPic pic);
	/**
	 * 修改排序
	 * @param map
	 */
	void updateSort(QuestionPic pic);
	/**
	 * 修改图片状态（下线）
	 * @param map
	 */
	void updatePicStatus(QuestionPic pic);
	/**
	 * 图片新增
	 * @param pic
	 */
	void addQuestionPic(QuestionPic pic);
	/**
	 * 修改图片全部
	 * @param pic
	 */
	void updatePicAll(QuestionPic pic);
}
