package com.lugd.album.response;

import java.io.Serializable;

import com.lugd.album.utilities.ResultCode;

public class BaseResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String msg = "SUCCESS";
	private int error = ResultCode.SUCCESS;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

}
