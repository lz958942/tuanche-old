package com.tuanche.dao.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tuanche.bean.admin.GmsContent;
import com.tuanche.commons.util.StringUtils;
import com.tuanche.mapper.admin.read.GmsContentReadMapper;
import com.tuanche.mapper.admin.write.GmsContentWriteMapper;
import com.tuanche.util.GcmUtil;

@Repository
public class GcmContentDao {
	@Autowired
	private GmsContentReadMapper contentRead;
	
	@Autowired
	private GmsContentWriteMapper contentWrite;
	/**
	 * Administrator：zhaojl
	 * @param contentList  内容集合
	 * @param gcmId   团车会id
	 * @param type    内容
	 * @param request
	 */
	public void addContentList(List<GmsContent> contentList,Integer gcmId,
			Integer type,HttpServletRequest request){
		if(contentList!=null && contentList.size() > 0){
			for (GmsContent content :contentList) {
				if(content==null){
					continue;
				}
				if(content != null && content.getDianId()!= null && content.getDianId()==-1){
					continue;
				}
				String image = content.getImage();
				if(!StringUtils.isEmpty(image)){
					String uploadImg = GcmUtil.uploadImg(image, request);
					content.setImage(uploadImg);
				}
				if(type == GmsContent.TYPE_DIAN && content.getDianId()==null){
					continue;
				}else{
					if(!StringUtils.isEmpty(content.getTitle())||!StringUtils.isEmpty(content.getImage()) || content.getDianId()!= null) {
						content.setGcmId(gcmId);
						content.setType(type);
						contentWrite.insert(content);
					}
				}
				
			}
		}
	}
	
	/**
	 * Administrator：zhaojl
	 * @param gcmId  团车会id
	 * @param type   内容类型
	 * @return
	 */
	public List<GmsContent> getContentListByGcmId(Integer gcmId,Integer type){
		List<GmsContent> selectByGcmId = contentRead.selectByGcmId(gcmId, type);
		if(selectByGcmId != null && selectByGcmId.size() > 0){
			return selectByGcmId;
		}
		return null;
	}
	
	/**
	 * Administrator：zhaojl
	 * @param contentList
	 * @param gcmId
	 * @param type
	 * @param request
	 */
	public void updateConList(List<GmsContent> contentList,Integer gcmId,
			Integer type,HttpServletRequest request){
		if(contentList!=null && contentList.size() > 0){
			for (GmsContent content :contentList) {
				if(content==null){
					continue;
				}
				if(content != null && content.getDianId()!= null && content.getDianId()==-1){
					continue;
				}
				String image = content.getImage();
				if(!StringUtils.isEmpty(image)){
					String uploadImg = GcmUtil.uploadImg(image, request);
					content.setImage(uploadImg);
				}
				if(type == GmsContent.TYPE_DIAN && content.getDianId()==null){
					continue;
				}else{
					if(!StringUtils.isEmpty(content.getTitle())||!StringUtils.isEmpty(content.getImage()) || content.getDianId()!= null) {
						if(content.getId()==null){//新增
							content.setGcmId(gcmId);
							content.setType(type);
							contentWrite.insert(content);
						}else{//修改
							contentWrite.updateByPrimaryKeySelective(content);
						}
					}
				}
			}
		}
	}
	
	public int updateGmsContent(GmsContent content){
		if(content == null){
			return 0;
		}
		return contentWrite.updateByPrimaryKeySelective(content);
	}
}
