package com.exam.pojo;

public class Exam {
	private int eid;
	private String ename;
	private String etime;
	private boolean eautostart;
	private boolean eactive;
	private boolean efinish;
	private boolean earchive;
	private boolean ecleared;
	private String tid;
	private String epaper;
	private String etype;
	private Teacher teacher;
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	public boolean isEautostart() {
		return eautostart;
	}
	public void setEautostart(boolean eautostart) {
		this.eautostart = eautostart;
	}
	public boolean isEactive() {
		return eactive;
	}
	public void setEactive(boolean eactive) {
		this.eactive = eactive;
	}
	public boolean isEfinish() {
		return efinish;
	}
	public void setEfinish(boolean efinish) {
		this.efinish = efinish;
	}
	public boolean isEarchive() {
		return earchive;
	}
	public void setEarchive(boolean earchive) {
		this.earchive = earchive;
	}
	public boolean isEcleared() {
		return ecleared;
	}
	public void setEcleared(boolean ecleared) {
		this.ecleared = ecleared;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getEpaper() {
		return epaper;
	}
	public void setEpaper(String epaper) {
		this.epaper = epaper;
	}
	public String getEtype() {
		return etype;
	}
	public void setEtype(String etype) {
		this.etype = etype;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
}
