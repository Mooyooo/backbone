package mn.mo.backbone.constants;

import mn.mo.backbone.enums.ErrorType;

public class TokenConstants {
    public static final String TOKEN_EXPIRED="Token expired!";
    public static final String INVALID_PARAMETER_REASON = "InvalidParameter";
    public static final String INTERNAL_SERVER_ERROR = ErrorType.INTERNAL_SYSTEM_ERROR.getErrorCode();
    public static final String INVALID_PARAMETER_TXT = "Request contains invalid parameter!";
}
