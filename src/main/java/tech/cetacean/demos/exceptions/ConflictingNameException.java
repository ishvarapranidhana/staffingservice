package tech.cetacean.demos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
 
@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictingNameException extends RuntimeException 
{
    
	public ConflictingNameException(String exception) {
        super(exception);
    }
}