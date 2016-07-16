package com.tuanche.dao.che;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tuanche.bean.che.PicAtlasBean;
import com.tuanche.mapper.che.read.PicAtlasReadMapper;
import com.tuanche.mapper.che.write.PicAtlasWriteMapper;

@Repository
public class PicAtlasDao {
	@Autowired
private PicAtlasReadMapper atlasReadMapper;
	@Autowired
private PicAtlasWriteMapper atlasWriteMapper;
	public List<PicAtlasBean> getAtlas() {
		return atlasReadMapper.getAtlas();
	}
	public PicAtlasBean findByName(String name) {
		return atlasReadMapper.findByName(name);
	}
	public void insert(PicAtlasBean bena) {
		atlasWriteMapper.insert(bena);
	}
	public PicAtlasBean findByid(Integer id) {
		return atlasReadMapper.findByid(id);
	}
	public void updateCollection(PicAtlasBean bean) {
		atlasWriteMapper.updateCollection(bean);
	}
	public List<PicAtlasBean> getAtlasByName(String name) {
		return atlasReadMapper.getAtlasByName(name);
	}

}
