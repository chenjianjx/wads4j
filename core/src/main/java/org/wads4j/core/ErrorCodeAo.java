package org.wads4j.core;

/**
 * See {@link ErrorResultAo}
 */
public class ErrorCodeAo {
    /*OAUTH2 compatible error codes begin. See https://tools.ietf.org/html/rfc6750#page-9*/
    public static ErrorCodeAo EC_INVALID_REQUEST = new ErrorCodeAo("invalid_request", 400);
    public static ErrorCodeAo EC_INVALID_TOKEN = new ErrorCodeAo("invalid_token", 401);
    public static ErrorCodeAo EC_INSUFFICIENT_SCOPE = new ErrorCodeAo("insufficient_scope", 403);
    /*OAUTH2 compatible error codes end*/


    public static ErrorCodeAo EC_RECORD_NOT_FOUND = new ErrorCodeAo("record_not_found", 404);
    public static ErrorCodeAo EC_RECORD_ALREADY_EXISTS = new ErrorCodeAo("record_already_exists", 409);
    public static ErrorCodeAo EC_SERVER_ERROR = new ErrorCodeAo("server_error", 500);

    private String codeName;
    private int httpCode;

    public ErrorCodeAo(String codeName, int httpCode) {
        this.codeName = codeName;
        this.httpCode = httpCode;
    }


    public int getHttpCode() {
        return httpCode;
    }

    public String getCodeName() {
        return codeName;
    }
}
