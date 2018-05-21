
public class Camper {
	
	private String name,request;
	//gender is based on number of x chromosome, 1:male 2:female
	private int gender,grade,cabin,group;
	public Camper() {
		name = "Arthur Howard";
		gender = 1;
		grade = 13;	
		request = "Christian Mulligan";
	}
	
	public Camper(String n, String r, int ge, int gr) {
		name = n;
		gender = ge;
		grade = gr;	
		request = r;
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

	public void setRequest(String request) {
		this.request = request;
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
