package mn.mo.backbone.exception;

import lombok.Getter;
import lombok.Setter;
import mn.mo.backbone.domain.ErrorResponse;
import mn.mo.backbone.enums.ErrorType;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class TokenException extends RuntimeException{

    private ErrorResponse response;
    private HttpStatus httpStatus;
    private ErrorType errorType;


    public TokenException(ErrorResponse response, HttpStatus httpStatus, ErrorType errorType) {
        this.response = response;
        this.httpStatus = httpStatus;
        this.errorType = errorType;
    }

    public TokenException(String message, ErrorResponse response, HttpStatus httpStatus, ErrorType errorType) {
        super(message);
        this.response = response;
        this.httpStatus = httpStatus;
        this.errorType = errorType;
    }


    public TokenException(String message, HttpStatus httpStatus, ErrorResponse response) {
        super(message);
        this.httpStatus = httpStatus;
        this.response = response;
    }
}
