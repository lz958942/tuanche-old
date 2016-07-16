package com.tuanche.bean.che;

public class PicAtlasBean {
private Integer id,createUid;
private String atlasTitle,atlasDesc,createTime,name;

public PicAtlasBean(){}
public PicAtlasBean(String atlasTitle2,String atlasDesc2,String name2,Integer createUid2,String createTime2 ){
	this.atlasTitle=atlasTitle2;
	this.atlasDesc=atlasDesc2;
	this.name=name2;
	this.createUid=createUid2;
	this.createTime=createTime2;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getCreateUid() {
	return createUid;
}
public void setCreateUid(Integer createUid) {
	this.createUid = createUid;
}
public String getAtlasTitle() {
	return atlasTitle;
}
public void setAtlasTitle(String atlasTitle) {
	this.atlasTitle = atlasTitle;
}
public String getAtlasDesc() {
	return atlasDesc;
}
public void setAtlasDesc(String atlasDesc) {
	this.atlasDesc = atlasDesc;
}
public String getCreateTime() {
	return createTime;
}
public void setCreateTime(String createTime) {
	this.createTime = createTime;
}

}
