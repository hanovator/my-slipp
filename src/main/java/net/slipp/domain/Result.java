package net.slipp.domain;

public class Result {
	private boolean valid;
	
	private String erroMessage;
	
	private Result(){}
	
	private Result(boolean valid, String errorMessage){
		this.valid = valid;
		this.setErroMessage(errorMessage);
	}
	
	public boolean isValid(){
		return valid;
	}
	
	public static Result ok() {
		return new Result(true, null);
	}
	
	public static Result fail(String errorMessage) {
		return new Result(false, errorMessage);
	}

	public String getErroMessage() {
		return erroMessage;
	}

	public void setErroMessage(String erroMessage) {
		this.erroMessage = erroMessage;
	}
}
