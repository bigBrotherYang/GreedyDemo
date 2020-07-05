package com.xktj.xtzy;

import java.io.Serializable;

public class MsgBen implements Serializable {
	private static final long serialVersionUID = 1168811139492707680L;
	private int userId;
	private int othId;
	private String msg;

	public MsgBen() {
	}

	public MsgBen(String msg) {
		this.msg = msg;
	}

	public MsgBen(int userId) {
		this.userId = userId;
	}

	public MsgBen(int userId, int othId) {
		this.userId = userId;
		this.othId = othId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getOthId() {
		return othId;
	}

	public void setOthId(int othId) {
		this.othId = othId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "MsgBen{" +
				"userId=" + userId +
				", othId=" + othId +
				", msg='" + msg + '\'' +
				'}';
	}
}
