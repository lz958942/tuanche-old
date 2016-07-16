package com.tuanche.web.gambitReview;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.tuanche.bean.admin.TopicReview;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.service.admin.TopicReviewService;
import com.tuanche.smc.common.page.impl.Pagination;

@Controller
public class GambitReviewController {
	@Autowired
	private TopicReviewService service;
	@RequestMapping(value="/wd/review/list",method={RequestMethod.GET,RequestMethod.POST})
	 public ModelAndView reviewList(Model model,TopicReview bean) {
		Pagination pagination=bean.getPage();
		if(pagination==null){pagination = new Pagination();}
		Pagination.threadLocal.set(pagination);
		//跟新缓存
		service.UpdateTopicCache();
		model.addAttribute("bean", bean);
		model.addAttribute("topic", GlobalConstants.gambit);
		model.addAttribute("page",Pagination.threadLocal.get());
		return new ModelAndView("/T9W/home","beans",service.reviewList(bean));
	}
	@RequestMapping(value="/wd/review/operation",method={RequestMethod.POST})
	@ResponseBody
	public int operation(@Param("id") Integer id,@Param("ids") String ids,@Param("auditStatus")Integer auditStatus ) {
		try{
			service.operation(id,ids,auditStatus);
		}catch(Exception e){
			System.out.println(e.toString());
			return 0;
		}
		return 1;
	}
	@RequestMapping(value="/wd/review/updateSort",method={RequestMethod.POST})
	@ResponseBody
	public int updateSort(TopicReview bean,@Param("divSort")Integer divSort) {
		try {
			service.updateSort(bean.getId(), bean.getTopicId(), divSort,bean.getSort());
			return 9;
		} catch (Exception e) {
			return 0;
		}
	}
	@RequestMapping(value="/json/ht/review/uploadExcel")
	@ResponseBody
	public Map<String, String>   uploadExcel(HttpServletRequest request,@Param("gid")Integer gid) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile stream = multipartRequest.getFile("file1");
		Map<String, String> size=service.Excel(stream.getInputStream(),stream.getOriginalFilename(),gid);	
		return size;
	}
}
