package com.slloan.util;

import java.io.Serializable;

import com.slloan.entity.ApplyForLoanInformation;
import com.slloan.entity.Contacts;
import com.slloan.entity.Page;
import com.slloan.entity.PersonalProfile;
import com.slloan.entity.PropertyInformation;

import net.sf.json.JSONObject;

@SuppressWarnings("serial")
public class Json implements  Serializable{
	private boolean status;
    private String msg;
    private Object obj;
    private String value;//增加一个状态值
	private String amount;
	private String contacts;
	private String name;
    public Json(Page<PersonalProfile> page, String string) {
        super();
    }

    public Json(boolean status, String msg, ApplyForLoanInformation amount2, PropertyInformation name2, Contacts contacts2 ){
    	 super();
         this.status = status;
         this.msg = msg;
         this.amount= amount;
         this.contacts = name;
         this.name = contacts;
    }
    public Json(boolean status, String msg, Object obj) {
        super();
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }
    public Json(boolean status, String msg, Object obj,String value) {
        super();
        this.status = status;
        this.msg = msg;
        this.obj = obj;
        this.value = value;
    }
    public Json(String string, boolean fallbackinsert) {
		// TODO Auto-generated constructor stub
	}

	public Json(boolean b, String string) {
		// TODO Auto-generated constructor stub
	}

	public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
    
}
