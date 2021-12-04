package mn.mo.backbone.exception;

import mn.mo.backbone.domain.ErrorResponse;
import mn.mo.backbone.enums.ErrorType;
import org.springframework.http.HttpStatus;

import java.util.List;

import static mn.mo.backbone.constants.TokenConstants.INTERNAL_SERVER_ERROR;
import static mn.mo.backbone.constants.TokenConstants.INVALID_PARAMETER_REASON;

public class ErrorBuilder {

  public static ErrorResponse formError(int httpStatus, String msg, String reason) {
    return new ErrorResponse(String.valueOf(httpStatus), msg, reason);
  }

  public static ErrorResponse form400Error(List<ErrorDetails> errorDetails) {
    ErrorResponse errorResponse =
        formError(
            HttpStatus.BAD_REQUEST.value(), INVALID_PARAMETER_REASON, INVALID_PARAMETER_REASON);
    return errorResponse;
  }

  public static ErrorResponse form500Error(List<ErrorDetails> errorDetails) {
    ErrorResponse errorResponse =
        formError(
            HttpStatus.INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR);
    return errorResponse;
  }
}
