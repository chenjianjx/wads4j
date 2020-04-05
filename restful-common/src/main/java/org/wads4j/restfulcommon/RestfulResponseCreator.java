package org.wads4j.restfulcommon;

import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.rs.response.OAuthRSResponse;
import org.wads4j.core.ErrorCodes;
import org.wads4j.core.ErrorResultAo;
import org.wads4j.core.ResponseAo;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Should not be used outside the framework unless you want to extend it
 */
public class RestfulResponseCreator {


    protected static final int HTTP_CODE_NO_CONTENT = 204;
    protected static final int HTTP_CODE_OK = 200;

    private static Map<String, Integer> errorCode2HttpCodeMap = new LinkedHashMap<>();

    static {
        errorCode2HttpCodeMap.put(ErrorCodes.INVALID_REQUEST, 400);
        errorCode2HttpCodeMap.put(ErrorCodes.INVALID_TOKEN, 401);
        errorCode2HttpCodeMap.put(ErrorCodes.INSUFFICIENT_SCOPE, 403);
        errorCode2HttpCodeMap.put(ErrorCodes.RECORD_NOT_FOUND, 404);
        errorCode2HttpCodeMap.put(ErrorCodes.RECORD_ALREADY_EXISTS, 409);
        errorCode2HttpCodeMap.put(ErrorCodes.SERVER_ERROR, 500);
    }


    /**
     * convert api response data-structure to a common restful response
     *
     * @param appResponse      can not be null
     * @param <SUCCESS_RESULT>
     * @return
     */
    public <SUCCESS_RESULT> RestfulResponse fromAppResponse(ResponseAo<SUCCESS_RESULT> appResponse) {

        if (appResponse.isSuccessful()) {
            RestfulResponse restfulResponse = new RestfulResponse();
            SUCCESS_RESULT successResult = appResponse.getSuccessResult();
            if (successResult == null) {
                restfulResponse.setStatusCode(HTTP_CODE_NO_CONTENT);
                return restfulResponse;
            } else {
                restfulResponse.setStatusCode(HTTP_CODE_OK);
                restfulResponse.setBodyEntity(successResult);
                return restfulResponse;
            }
        } else {
            ErrorResultAo error = appResponse.getErrorResult();
            String errorCode = error.getErrorCode();

            if (ErrorCodes.INVALID_TOKEN.equals(errorCode) || ErrorCodes.INSUFFICIENT_SCOPE.equals(errorCode)) {
                return buildOAuth2ErrorResponse(appResponse);
            } else {
                RestfulResponse restfulResponse = new RestfulResponse();
                restfulResponse.setStatusCode(getHttpCodeFromErrorCode(errorCode));
                restfulResponse.setBodyEntity(error);
                return restfulResponse;
            }
        }
    }


    protected <SUCCESS_RESULT> RestfulResponse buildOAuth2ErrorResponse(ResponseAo<SUCCESS_RESULT> appResponse) {
        try {

            String errorCode = appResponse.getErrorResult().getErrorCode();
            OAuthResponse oltuResponse = OAuthRSResponse
                    .errorResponse(getHttpCodeFromErrorCode(errorCode))
                    .setError(errorCode)
                    .setErrorDescription(appResponse.getErrorResult().getDevErrMsg())
                    .buildHeaderMessage();

            RestfulResponse restfulResponse = fromOltuResponse(oltuResponse);
            restfulResponse.setBodyEntity(appResponse.getErrorResult()); //also set the errors in the body so the client side doesn't have to parse the header
            return restfulResponse;
        } catch (OAuthSystemException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Overwrite this for your needs
     *
     * @param errorCode
     * @return
     */
    protected int getHttpCodeFromErrorCode(String errorCode) {
        Integer httpCode = errorCode2HttpCodeMap.get(errorCode);
        if (httpCode != null) {
            return httpCode;
        } else {
            return 500;
        }
    }


    protected RestfulResponse fromOltuResponse(OAuthResponse oltuResponse) {
        RestfulResponse restfulResponse = new RestfulResponse();
        restfulResponse.getHeader().put(OAuth.HeaderType.WWW_AUTHENTICATE, oltuResponse.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
        restfulResponse.setStatusCode(oltuResponse.getResponseStatus());
        return restfulResponse;
    }

}
