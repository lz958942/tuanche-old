package com.tuanche.bean.admin;

import java.io.Serializable;
import java.util.Date;

import com.tuanche.commons.util.StringUtils;

public class GmsAddress implements Serializable{
   
	private static final long serialVersionUID = -7969441482460534084L;

	private Integer id;

    private Integer gcmId;

    private String addressNumber;

    private String addressDesc;
    
    //自驾路线
    private String addrDesc_t1;
    //乘车路线
    private String addrDesc_t2;
    //停车区
    private String addrDesc_t3;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGcmId() {
        return gcmId;
    }

    public void setGcmId(Integer gcmId) {
        this.gcmId = gcmId;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber == null ? null : addressNumber.trim();
    }

    public String getAddressDesc() {
    	if(StringUtils.isEmpty(addressDesc)){
    		addressDesc = addrDesc_t1 +"&"+addrDesc_t2+"&"+addrDesc_t3;
    	}
        return addressDesc;
    }
    
    public void setAddressDesc(String addressDesc) {
        this.addressDesc = addressDesc == null ? null : addressDesc.trim();
    }

	public String getAddrDesc_t1() {
		if(StringUtils.isEmpty(addrDesc_t1) && !StringUtils.isEmpty(addressDesc)){
			int indexOf = addressDesc.indexOf("&");
			if(indexOf!=-1){
				return addressDesc.substring(0, indexOf);
			}
		}
		return addrDesc_t1;
	}

	public void setAddrDesc_t1(String addrDesc_t1) {
		this.addrDesc_t1 = addrDesc_t1;
	}

	public String getAddrDesc_t2() {
		if(StringUtils.isEmpty(addrDesc_t2) && !StringUtils.isEmpty(addressDesc)){
			int indexOf = addressDesc.indexOf("&");
			int lastIndexOf = addressDesc.lastIndexOf("&");
			if(indexOf!=-1 && indexOf != lastIndexOf){
				return addressDesc.substring( indexOf+1,lastIndexOf);
			}
		}
		return addrDesc_t2;
	}

	public void setAddrDesc_t2(String addrDesc_t2) {
		this.addrDesc_t2 = addrDesc_t2;
	}

	public String getAddrDesc_t3() {
		if(StringUtils.isEmpty(addrDesc_t3) && !StringUtils.isEmpty(addressDesc)){
			int indexOf = addressDesc.indexOf("&");
			int lastIndexOf = addressDesc.lastIndexOf("&");
			if(indexOf!=-1 && indexOf != lastIndexOf){
				return addressDesc.substring(lastIndexOf+1);
			}
		}
		return addrDesc_t3;
	}

	public void setAddrDesc_t3(String addrDesc_t3) {
		this.addrDesc_t3 = addrDesc_t3;
	}
}