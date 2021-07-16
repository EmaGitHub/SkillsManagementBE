package it.plansoft.skills.Model;

public class RestPagingResponse<T> extends RestResponse<T> {

	Integer totalItems;
	
	public RestPagingResponse(T data) {
		super(data);
	}
	
}
