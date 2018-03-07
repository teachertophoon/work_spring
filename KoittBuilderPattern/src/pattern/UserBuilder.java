package pattern;

import java.util.Date;

public class UserBuilder {
	private String name;
	private String email;
	private Integer age;
	private Date dob;
	
	public UserBuilder setName(String name) {
		this.name = name;
		return this;
	}
	
	public UserBuilder setEmail(String email) {
		this.email = email;
		return this;
	}
	
	public UserBuilder setAge(Integer age) {
		this.age = age;
		return this;
	}
	
	public UserBuilder setDob(Date dob) {
		this.dob = dob;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [name=");
		builder.append(name);
		builder.append(", email=");
		builder.append(email);
		builder.append(", age=");
		builder.append(age);
		builder.append(", dob=");
		builder.append(dob);
		builder.append("]");
		return builder.toString();
	}
}
