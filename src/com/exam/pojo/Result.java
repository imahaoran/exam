package com.exam.pojo;

public class Result {
	private int rid;
	private int eid;
	private String sid;
	private String ip;
	private String submitfile;
	public String getSubmitfile() {
		return submitfile;
	}
	public void setSubmitfile(String submitfile) {
		this.submitfile = submitfile;
	}
	private double grade;
	private Exam exam;
	private Student student;
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public Exam getExam() {
		return exam;
	}
	public void setExam(Exam exam) {
		this.exam = exam;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
}
