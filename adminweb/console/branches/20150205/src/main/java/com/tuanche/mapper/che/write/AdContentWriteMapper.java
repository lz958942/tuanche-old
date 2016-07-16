package com.tuanche.mapper.che.write;

import com.tuanche.bean.che.AdContent;
import com.tuanche.bean.che.AdPosition;
public interface AdContentWriteMapper {
	public int addAdContent(AdContent adContent);
	public void updateAdContent(AdContent adContent);
	public void openContent(AdContent adContent);
	public void assignCiy(AdPosition adPosition);
	public void deleteContentbyId(int id);
}
