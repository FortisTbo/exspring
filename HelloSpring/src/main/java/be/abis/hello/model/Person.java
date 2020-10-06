package be.abis.hello.model;

public class Person {
	private String firstName;

	public Person () {}
	
	public Person(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}
