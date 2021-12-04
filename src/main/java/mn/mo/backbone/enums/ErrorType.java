package mn.mo.backbone.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ErrorType {

    TOKEN_EXPIRED("H0001", "Token expired!"),
    INTERNAL_SYSTEM_ERROR("H0002","System error!");
    private String errorCode;
    private String errorText;

    ErrorType(String errorCode, String errorText){
        this.errorCode = errorCode;
        this.errorText = errorText;
    }

}
