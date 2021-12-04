package mn.mo.backbone.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import mn.mo.backbone.exception.ErrorDetails;

import java.util.List;

@Getter
@Setter
@ToString
public class ErrorResponse {
  private String status;
  private String message;
  private String reason;
  private List<ErrorDetails> errorDetails;

  public ErrorResponse(String status, String message, String reason) {
    this.status = status;
    this.message = message;
    this.reason = reason;
  }

  public ErrorResponse() {}
}
