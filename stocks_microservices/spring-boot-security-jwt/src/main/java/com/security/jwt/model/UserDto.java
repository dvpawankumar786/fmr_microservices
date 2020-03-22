package com.security.jwt.model;
public class UserDto {
	
    private String username;
    private String password;
    private String role;
    private int age;
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserDto [custid=" + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", age=" + age + "]";
	}

}
