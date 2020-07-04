package com.xktj.xtzy;

import java.io.Serializable;

public class MsgBen implements Serializable {
	private static final long serialVersionUID = 1168811139492707680L;
	private int userId;
	private int othId;

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

}
