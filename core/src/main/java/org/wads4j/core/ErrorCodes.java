package org.wads4j.core;

/**
 * It may not be complete. Feel free to create your own constant class
 */
public interface ErrorCodes {

    /*OAUTH2 compatible error codes begin. See https://tools.ietf.org/html/rfc6750#page-9*/
    String INVALID_REQUEST = "invalid_request";
    String INVALID_TOKEN = "invalid_token";
    String INSUFFICIENT_SCOPE = "insufficient_scope";
    /*OAUTH2 compatible error codes end*/


    String RECORD_NOT_FOUND = "record_not_found";
    String RECORD_ALREADY_EXISTS = "record_already_exists";
    String SERVER_ERROR = "server_error";

}
