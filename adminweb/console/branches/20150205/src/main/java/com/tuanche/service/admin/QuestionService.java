package com.tuanche.service.admin;


import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.bean.admin.Question;
import com.tuanche.bean.admin.Topic;
import com.tuanche.commons.util.Resources;
import com.tuanche.mapper.admin.read.QuestionReadMapper;
import com.tuanche.mapper.admin.read.TopicReadMapper;
import com.tuanche.mapper.admin.write.QuestionWriteMapper;
import com.tuanche.mapper.admin.write.TopicWriteMapper;
import com.tuanche.upload.FtpUtil;


@Service
public class QuestionService{
	@Autowired
	QuestionReadMapper readMapper;
	@Autowired
	QuestionWriteMapper writeMapper;
	@Autowired
	private TopicReadMapper topicReadMapper;
	@Autowired
	private TopicWriteMapper topicWriteMapper;
	/**
	 * 分页查询话题
	 * @param topic
	 * @return
	 */
	public List<Topic> topicByPage(Topic topic){
		return topicReadMapper.topicByPage(topic);
	}
	
	/**
	 * 分页查询
	 * @param question
	 * @return
	 */
	public List<Question> queryByPage(Question question){
		return readMapper.queryByPage(question);
	}
	/**
	 * 下载
	 * @param question
	 * @return
	 */
	public List<Question> selectAlls(Question question){
		return readMapper.selectAlls(question);
	}
	/**
	 * 批量修改数据
	 * @param map
	 */
	public void updateQuestions(Byte questionStatus,Integer buildEmp,List<String> list){
		writeMapper.updateQuestions(questionStatus, buildEmp, list);
	}
	/**
	 * 删除单条问题
	 * @param id
	 */
	public void deleteOneQuestion(Byte questionStatus,Integer buildEmp,Integer id){
		writeMapper.deleteOneQuestion(questionStatus,buildEmp,id);
	}
	/**
	 * 推荐问题
	 * @param map
	 */
	public void isRecom(Map<String,Object> map){
		writeMapper.isRecom(map);
	}
	/**
	 * 插入问题返回插入id
	 * @param question
	 * @return
	 */
	public void insertQuestion(Question question){
		 writeMapper.insertQuestion(question);
	}
	/**
	 * 根据id获取对象
	 */
	public Question getQuestionByid(Integer id) {
		return readMapper.selectQuestion(id);
	}
	/**
	 * 问题得到回复
	 * @param map
	 */
	public void resolve(Byte resolve,Integer buildEmp,Integer id){
		writeMapper.resolve(resolve, buildEmp, id);
	}
	/**
	 * 修改问题回复个数
	 * @param map
	 */
	public void updateReply(Integer reply,Integer id){
		writeMapper.updateReply(reply,id);
	}
	public void updateReplys(List<Integer> list){
		writeMapper.updateReplys(list);
	}
	/**
	 * 根据二级分类id 查询问题个数
	 * @param id
	 * @return
	 */
	public Integer selectAnsCount(Integer id){
		return readMapper.selectAnsCount(id);
	}
	/**
	 * 根据id查询话题
	 * @param id
	 * @return
	 */
	public Topic selectTopicById(Integer id) {
		return topicReadMapper.selectTopicById(id);
	}

	public void updateTopic(Topic topic, HttpServletRequest request) {
		if(topic.getPicture()!=null&&!"".equals(topic.getPicture())){
			String picUrl=topic.getPicture();
			picUrl=picUrl.replaceAll("/pic_tmp/recpic", "http://pic.tuanche.com/recpic/wenda");
			topic.setPicture(picUrl);
			picUrl=picUrl.replaceAll("/pic.tuanche.com/recpic/wenda/","");
			ftpUploadPic(request, picUrl);
		}
		topicWriteMapper.updateTopic(topic);
	}

	public void addTopic(Topic topic, HttpServletRequest request) {
		if(topic.getPicture()!=null&&!"".equals(topic.getPicture())){
			String picUrl=topic.getPicture();
			picUrl=picUrl.replaceAll("/pic_tmp/recpic", "http://pic.tuanche.com/recpic/wenda");
			topic.setPicture(picUrl);
			topic.setTopicStatus((byte)1);
			topic.setIsRecom((byte)0);
			topic.setReview(0);
			picUrl=picUrl.replaceAll("/pic.tuanche.com/recpic/wenda/","");
			ftpUploadPic(request,picUrl);
		}
	
		topicWriteMapper.addTopic(topic);
	}
	/**
	 * @param request
	 * @author wtk
	 * @Description 将图片上传到服务器
	 */
	public  void  ftpUploadPic(HttpServletRequest request,String picList){
		String realPath=request.getSession().getServletContext().getRealPath(request.getContextPath());
		String descFile=picList.substring(1,picList.length()-1);
		System.out.println(descFile);
		descFile=descFile.substring(descFile.indexOf("/")+1, descFile.lastIndexOf("/"));
		picList=Resources.getString("temfilePath")+picList;
		String fileName=picList.substring(picList.lastIndexOf("/")+1);
		realPath=realPath+Resources.getString("temfilePath")+"recpic/"+descFile+"/";
		File file=new File(realPath+fileName);
		if(file.exists()){
			FtpUtil.ftpUpload(Resources.getString("picFtpHost"), Resources.getString("wendaftp.username"), Resources.getString("wendaftp.password"), realPath,"wenda/"+descFile, fileName);
		}
		System.out.println("realpath=================="+realPath+"          descFile===="+descFile+"          filename======"+fileName);
	}

	public void updatetopstatus(Byte status, Integer updateEmp, Integer id) {
		topicWriteMapper.updateTopStatus(status, updateEmp, id);
		
	}

	public void recom(Byte recom, Integer updateEmp, Integer id) {
		topicWriteMapper.recom(recom,updateEmp,id);
		
	}

	public void current(Byte current, Integer updateEmp, Integer id) {
		topicWriteMapper.current(current,updateEmp,id);		
	}

	public void deleteContent(Integer userId, Integer id) {
		writeMapper.deleteContent(userId,id);
	}

	public void updateContent(Integer employeeId, Integer quetionId, String content) {
		writeMapper.updateContent(employeeId,quetionId,content);
	}

	public void modify(Integer userId,Integer id,Integer firstkindId,Integer secondkindId,Integer brandId,Integer carstyleId) {
		writeMapper.modify(userId,id,firstkindId,secondkindId,brandId,carstyleId);
	}

	public void delete(List<String> asList) {
		writeMapper.delete(asList);
	}

	public void batchUpdate(List<String> ids, Integer id,
			Integer firstkindId, Integer secondkindId, Integer brandId,
			Integer carstyleId) {
		writeMapper.batchUpdate(ids,id,firstkindId,secondkindId,brandId,carstyleId);
	}
}
