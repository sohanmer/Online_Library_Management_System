package com.eLib.beans;

import java.sql.Date;

public class IssueBookBean {
private String callno,studentname;
private int studentid;
private long studentmobile;
private Date issueddate;
private String returnstatus;
private String name;

public IssueBookBean() {}
public IssueBookBean(String callno, int studentid, String studentname, long studentmobile) {
	super();
	this.callno = callno;
	this.studentname = name;
	this.studentid = studentid;
	this.studentname = studentname;
	this.studentmobile = studentmobile;
}

public String getReturnstatus() {
	return returnstatus;
}
public void setReturnstatus(String returnstatus) {
	this.returnstatus = returnstatus;
}
public Date getIssueddate() {
	return issueddate;
}
public void setIssueddate(Date issueddate) {
	this.issueddate = issueddate;
}
public String getCallno() {
	return callno;
}
public void setCallno(String callno) {
	this.callno = callno;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getStudentid() {
	return studentid;
}
public void setStudentid(int studentid) {
	this.studentid = studentid;
}
public String getStudentname() {
	return studentname;
}
public void setStudentname(String studentname) {
	this.studentname = studentname;
}
public long getStudentmobile() {
	return studentmobile;
}
public void setStudentmobile(long studentmobile) {
	this.studentmobile = studentmobile;
}

}

