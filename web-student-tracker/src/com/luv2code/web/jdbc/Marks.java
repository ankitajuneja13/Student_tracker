package com.luv2code.web.jdbc;

public class Marks {

	private int id;
	private String first_name;
	private int eng;
	private int hindi ;
	private int science ;
	public Marks(int id, String first_name, int eng, int hindi, int science) {
	
		this.id = id;
		this.first_name = first_name;
		this.eng = eng;
		this.hindi = hindi;
		this.science = science;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getHindi() {
		return hindi;
	}
	public void setHindi(int hindi) {
		this.hindi = hindi;
	}
	public int getScience() {
		return science;
	}
	public void setScience(int science) {
		this.science = science;
	}
	@Override
	public String toString() {
		return "Marks [id=" + id + ", first_name=" + first_name + ", eng=" + eng + ", hindi=" + hindi + ", science="
				+ science + "]";
	}
	
	
	
}
