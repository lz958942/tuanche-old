package com.tuanche.mapper.che.write;
import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.che.AdContentPosition;
public interface AdContentPositionWriteMapper {
	public void openContentLocation(AdContentPosition adContentPosition); 
	public void deleteContentPositionById(int id);
	public void updateImg(@Param("id")Integer id, @Param("path")String path,@Param("picTitle")String picTitle, @Param("picDesc")String picDesc,@Param("uid")int uid,@Param("turl")String turl);
	public void deleteContentPositionById(@Param("contentPositionId")int contentPositionId,@Param("sta")Integer sta);
}
