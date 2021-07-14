package it.plansoft.skills.Model;

public class RestResponse<T> {
	T data;
	Message[] info;
	Message[] warnings;
	Message[] errors;
}
