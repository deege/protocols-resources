package com.deege.protocols.exceptions;

import java.net.HttpURLConnection;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * <p>{@link javax.ws.rs.WebApplicationException} for when an item cannot be found in 
 * the application persistence.  Returns {@link HttpURLConnection.HTTP_NOT_FOUND}.</p>
 *
 */
public class ItemNotFoundException extends WebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7584214021589976232L;
	
	/**
	 * <p>Constructor.  Returns a {@link HttpURLConnection.HTTP_NOT_FOUND} response.</p>
	 * 
	 * @param message
	 */
	public ItemNotFoundException(String message) {	
		super(Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(message).type(MediaType.TEXT_PLAIN).build());
	}
}