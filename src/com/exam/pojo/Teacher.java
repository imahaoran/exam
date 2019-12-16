package com.exam.pojo;

public class Teacher {
	private String tid;
	private String tname;
	private String tpwd;
	private boolean tadmin;
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTpwd() {
		return tpwd;
	}
	public void setTpwd(String tpwd) {
		this.tpwd = tpwd;
	}
	public boolean isTadmin() {
		return tadmin;
	}
	public void setTadmin(boolean tadmin) {
		this.tadmin = tadmin;
	}
	@Override
	public String toString() {
		return "Teacher [tid=" + tid + ", tname=" + tname + ", tpwd=" + tpwd + ", tadmin=" + tadmin + "]";
	}
	
}
