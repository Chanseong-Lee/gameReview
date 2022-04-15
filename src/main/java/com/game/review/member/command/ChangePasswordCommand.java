package com.game.review.member.command;

public class ChangePasswordCommand {
	
	private String password;
	private String confirmPassword;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	@Override
	public String toString() {
		return "ChangePasswordCommand [password=" + password + ", confirmPassword=" + confirmPassword + "]";
	}
	
	
}
