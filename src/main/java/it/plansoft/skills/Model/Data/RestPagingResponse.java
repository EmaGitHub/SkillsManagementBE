package it.plansoft.skills.Model.Data;

public class RestPagingResponse<T> extends RestResponse<T> {

	Integer totalItems;
	
	public RestPagingResponse(T data) {
		super(data);
	}
	
}
