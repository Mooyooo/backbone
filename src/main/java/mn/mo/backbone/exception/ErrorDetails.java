package mn.mo.backbone.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetails {

  private String errorField;
  private String errorText;

  public ErrorDetails(String errorField, String errorText) {
    this.errorField = errorField;
    this.errorText = errorText;
  }
}
