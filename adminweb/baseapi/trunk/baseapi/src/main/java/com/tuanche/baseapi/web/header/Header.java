package com.tuanche.baseapi.web.header;

import com.tuanche.framework.base.util.NumberHelper;

public class Header {

	/** 响应编码类型. gzip*/
	private final String encoding;
	/** UA */
	private final String uAgent;
	/** 响应是否加密 */
	private final String des;

	/* ~~~~~~~~~~~~~~~ traceinfo parameters ~~~~~~~~~~~~~~~ */
	/** 版本名称 */
	private String versionname;
	/** 版本号code。后台判断版本用这个字段 */
	private String versioncode;
	/** IMEI 号 */
	private String imei;
	/** SIM卡序列号 */
	private String sim;
	/** MAC 地址 */
	private String macaddress;
	/** APP 构建版本 */
	private String buildversion;
	/** 操作系统 */
	private String osversion;
	/** 手机号 */
	private String model;
	/** 应用名称 */
	private String appname;
	/** 当前网络类型. 2G, 3G,Wifi */
	private String network;
	/** 渠道ID */
	private String channelid;
	/** 渠道名称 */
	private String channelName;
	/** 设备ID */
	private String deviceid;
	/** 来源. IOS: 24; Android: 22 */
	private String source;
	/** 用户当前位置 */
	private String location;

	public Header(final String encoding, final String uAgent, final String des) {
		this.encoding = encoding;
		this.uAgent = uAgent;
		this.des = des;
	}

	public String getEncoding() {
		return encoding;
	}

	public String getuAgent() {
		return uAgent;
	}

	public boolean getDes() {
		return Boolean.parseBoolean(this.des);
	}

	public String getVersionname() {
		return versionname;
	}

	public void setVersionname(final String versionname) {
		this.versionname = versionname;
	}

	public String getVersioncode() {
		return versioncode;
	}

	public void setVersioncode(final String versioncode) {
		this.versioncode = versioncode;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(final String imei) {
		this.imei = imei;
	}

	public String getSim() {
		return sim;
	}

	public void setSim(final String sim) {
		this.sim = sim;
	}

	public String getMacaddress() {
		return macaddress;
	}

	public void setMacaddress(final String macaddress) {
		this.macaddress = macaddress;
	}

	public String getBuildversion() {
		return buildversion;
	}

	public void setBuildversion(final String buildversion) {
		this.buildversion = buildversion;
	}

	public String getOsversion() {
		return osversion;
	}

	public void setOsversion(final String osversion) {
		this.osversion = osversion;
	}

	public String getModel() {
		return model;
	}

	public void setModel(final String model) {
		this.model = model;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(final String appname) {
		this.appname = appname;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(final String network) {
		this.network = network;
	}

	public String getChannelid() {
		return channelid;
	}

	public void setChannelid(final String channelid) {
		this.channelid = channelid;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(final String deviceid) {
		this.deviceid = deviceid;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(final String channelName) {
		this.channelName = channelName;
	}

	public int getSource() {
		return NumberHelper.parseInt(source, 0);
	}

	public void setSource(final String source) {
		this.source = source;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(final String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		final StringBuilder tostr = new StringBuilder();
		tostr.append("Header[");
		tostr.append("encoding: ").append(this.encoding);
		tostr.append(", uAgent: ").append(this.uAgent);
		tostr.append(", des: ").append(this.des);
		tostr.append(", versionname: ").append(this.versionname);
		tostr.append(", versioncode: ").append(this.versioncode);
		tostr.append(", imei: ").append(this.imei);
		tostr.append(", sim: ").append(this.sim);
		tostr.append(", macaddress: ").append(this.macaddress);
		tostr.append(", buildversion: ").append(this.buildversion);
		tostr.append(", osversion: ").append(this.osversion);
		tostr.append(", model: ").append(this.model);
		tostr.append(", appname: ").append(this.appname);
		tostr.append(", network: ").append(this.network);
		tostr.append(", channelid: ").append(this.channelid);
		tostr.append(", channelName: ").append(this.channelName);
		tostr.append(", deviceid: ").append(this.deviceid);
		tostr.append(", location: ").append(this.location);
		tostr.append("]");
		return tostr.toString();
	}
}
