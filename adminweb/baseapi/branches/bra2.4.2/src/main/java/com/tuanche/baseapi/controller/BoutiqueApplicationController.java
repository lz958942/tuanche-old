package com.tuanche.baseapi.controller;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tuanche.basedata.api.BoutiqueApplicationApi;
import com.tuanche.basedata.entity.BoutiqueApplicationVO;
import com.tuanche.framework.base.constant.CommonCode;
import com.tuanche.framework.base.entity.DataTransferObject;

@Controller
@RequestMapping(value = "/application", method = { RequestMethod.POST, RequestMethod.GET })
public class BoutiqueApplicationController {
	
	@Resource(name = "boutiqueApplicationProxy")
	BoutiqueApplicationApi boutiqueApplicationApi;
	
	
	@RequestMapping(value = "/getBoutiqueApplicationBy" , method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Object getBoutiqueApplicationBy(HttpServletRequest request) {
		DataTransferObject<List<BoutiqueApplicationVO>> list =  null;
		String platform = request.getParameter("platform");
		int platFormId = 0;
		if(platform!=null && !platform.equals("")){
			platFormId = Integer.parseInt(platform);
			list = boutiqueApplicationApi.getBoutiqueApplicationBy(platFormId);
		}else{
			list = new DataTransferObject<List<BoutiqueApplicationVO>>(CommonCode.PAR_EXCEPTION.getCode(),"");
		}
		return list;
	}

	
}
