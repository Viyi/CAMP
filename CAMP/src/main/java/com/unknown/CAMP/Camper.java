package com.unknown.CAMP;


public class Camper {
	
	private String name,request,request2;
	//gender is based on number of x chromosome, 1:male 2:female
	private int gender,grade,cabin,group;
	public Camper() {
		name = "Arthur Howard";
		gender = 1;
		grade = 13;	
		request = "Christian Mulligan";
	}
	
	public Camper(String n, String r, int ge) {
		name = n.toLowerCase();
		gender = ge;
		grade = -1;
		r = r.toLowerCase().trim();
		if(r.startsWith("-")) {
			r = r.substring(1,r.length());
		}
		System.out.println("camper class r = " + r);
		if(!r.contains("-")) {
			request = r;
		}else {
			request = r.substring(0, r.indexOf("-"));
			request2 = r.substring(r.indexOf("-")+1,r.length());
			System.out.println("Request: " + request + "Request 2: "  +request2);
		}
		
	}
	
	
	//Probably don't need all these, but oh well.
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRequest() {
		return request;
	}
	public String getRequest2() {
		return request2;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public void setRequest2(String request2) {
		this.request = request2;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getCabin() {
		return cabin;
	}

	public void setCabin(int cabin) {
		this.cabin = cabin;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public String toString() {
		return name;
	}
}
