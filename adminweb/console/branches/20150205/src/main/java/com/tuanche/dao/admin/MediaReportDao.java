package com.tuanche.dao.admin;

import java.util.List;






import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.tuanche.bean.admin.MediaReport;
import com.tuanche.mapper.admin.read.MediaReportReadMapper;
import com.tuanche.mapper.admin.write.MediaReportWriteMapper;
import com.tuanche.util.KevinUtil;
@Repository
public class MediaReportDao {
	@Resource
	private MediaReportReadMapper readMapper;
	@Resource
	private MediaReportWriteMapper writeMapper;
	public List<MediaReport> list(List<String> condition) {
		return readMapper.selectAllByPage(condition);
	}
	public MediaReport getMediaReportById(Integer id) {
		return readMapper.getMediaReportById(id);
	}
	public void save(MediaReport bean) {
		if(bean!=null){
			if(bean.getId()!=null){
				writeMapper.update(bean);
			}else{
			writeMapper.save(bean);
			}
		}
	}
	/***单个操作*******/
	public void operation(Integer id, Integer type, Integer eid) {
			if(id!=null && type!=null && eid!=null ){
				writeMapper.updateStatus(id,type,eid);
			}
	}
	public void operation(List<String>list, Integer type, Integer eid) {
		if(list!=null && list.size()>0 && type!=null && eid !=null){
			writeMapper.updateStatusBatch(list,type,eid);
		}
	}
	public List<MediaReport> titleRepetition(String title) {
		// TODO Auto-generated method stub
		return readMapper.titleRepetition(title);
	}
}
