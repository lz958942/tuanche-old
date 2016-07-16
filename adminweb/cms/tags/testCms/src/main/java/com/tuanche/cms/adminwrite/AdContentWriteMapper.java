package com.tuanche.cms.adminwrite;

import com.tuanche.cms.bean.AdContent;
import com.tuanche.cms.bean.AdPosition;


public interface AdContentWriteMapper {
	public int addAdContent(AdContent adContent);
	public void updateAdContent(AdContent adContent);
	public void openContent(AdContent adContent);
	public void assignCiy(AdPosition adPosition);
	public void deleteContentbyId(int id);
}
