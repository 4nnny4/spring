package edu.kh.fin.member.model.vo;

public class Ingan {
	private String name;
	private int age;
	private long spiritAge;
	public Ingan() {
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public long getSpiritAge() {
		return spiritAge;
	}
	public void setSpiritAge(long spiritAge) {
		this.spiritAge = spiritAge;
	}
	@Override
	public String toString() {
		return "Ingan [name=" + name + ", age=" + age + ", spiritAge=" + spiritAge + "]";
	}
	
}
