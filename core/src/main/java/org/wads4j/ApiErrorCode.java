package org.wads4j;

public enum ApiErrorCode {
    /*OAUTH2 compatible error codes begin. See https://tools.ietf.org/html/rfc6750#page-9*/
    invalid_request(400),
    invalid_token(401),
    insufficient_scope(403),
    /*OAUTH2 compatible error codes end*/

    //custom error fields. Capitalised
    RECORD_NOT_FOUND(404),
    RECORD_ALREADY_EXISTS(409),
    SERVER_ERROR(500);

    private int httpCode;

    ApiErrorCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public int getHttpCode() {
        return httpCode;
    }
}
