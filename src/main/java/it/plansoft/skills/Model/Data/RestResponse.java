package it.plansoft.skills.Model.Data;

public class RestResponse<T> {

	T data;
	Message[] info;
	Message[] warnings;
	Message[] errors;
	
	public RestResponse(T data) {
		this.data = data;
	}
}
