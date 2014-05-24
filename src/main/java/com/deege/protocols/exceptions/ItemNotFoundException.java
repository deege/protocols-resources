package com.deege.protocols.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ItemNotFoundException extends WebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7584214021589976232L;
	
	public ItemNotFoundException(String message) {	
		super(Response.status(404).entity(message).type(MediaType.TEXT_PLAIN).build());
	}
}