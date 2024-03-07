package in.prachi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class AppExceptionHandler {
	public class appExceptionHandler {
		@ExceptionHandler(value= Exception.class)
	    public ResponseEntity<String >handleAE(Exception e) {
	    	return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

}
