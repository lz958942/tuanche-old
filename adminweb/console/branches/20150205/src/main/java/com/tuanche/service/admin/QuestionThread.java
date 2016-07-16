package com.tuanche.service.admin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tuanche.bean.admin.Question;
import com.tuanche.mapper.admin.read.QuestionReadMapper;
import com.tuanche.mapper.admin.write.QuestionWriteMapper;
import com.tuanche.web.wenda.QuestionAnswerController;

public class QuestionThread implements Runnable {

	private QuestionReadMapper readMapper;
	private QuestionWriteMapper writeMapper;

	public QuestionThread(QuestionReadMapper readMapper,
			QuestionWriteMapper writeMapper) {
		this.readMapper = readMapper;
		this.writeMapper = writeMapper;
	}

	public QuestionReadMapper getReadMapper() {
		return readMapper;
	}

	public QuestionWriteMapper getWriteMapper() {
		return writeMapper;

	}

	@Override
	public void run() {
		
		List<Question> questionsi = readMapper.selectQuestions();
		
		List<String> list1=new ArrayList<String>();
		List<Integer> list2=new ArrayList<Integer>();
		
		for (Question qi : questionsi) 
		{
			if(list1.contains(qi.getTitle()))
			{
				list2.add(qi.getId());
			}
			else
			{
				list1.add(qi.getTitle());
			}
			
		}
		writeMapper.updateDeduplication(list2);
		
		QuestionAnswerController.deduplication = null;
	}

}
