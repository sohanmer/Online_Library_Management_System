package com.eLib.beans;

public class StudentBean {
private int sid;
private String sname,spassword;
private long smobile;

public StudentBean() {}

public StudentBean(int sid, String sname, String spassword, long smobile) {
	super();
	this.sid = sid;
	this.sname = sname;
	this.spassword = spassword;
	this.smobile = smobile;
}
public StudentBean(String sname, String spassword, long smobile) {
	super();
	this.sname = sname;
	this.spassword = spassword;
	this.smobile = smobile;
}

public int getId() {
	return sid;
}
public void setId(int sid) {
	this.sid = sid;
}
public String getName() {
	return sname;
}
public void setName(String sname) {
	this.sname = sname;
}
public String getPassword() {
	return spassword;
}
public void setPassword(String spassword) {
	this.spassword = spassword;
}
public long getMobile() {
	return smobile;
}
public void setMobile(long smobile) {
	this.smobile = smobile;
}

}

