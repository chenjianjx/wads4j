package org.wads4j.restfulcommon;

import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.rs.response.OAuthRSResponse;
import org.wads4j.core.ErrorCodeAo;
import org.wads4j.core.ErrorResultAo;
import org.wads4j.core.ResponseAo;

/**
 * Should not be used outside the framework unless you want to extend it
 */
public class RestfulResponseCreator {


    protected static final int HTTP_CODE_NO_CONTENT = 204;
    protected static final int HTTP_CODE_OK = 200;


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
            ErrorCodeAo errorCode = error.getErrorCode();

            if (errorCode == ErrorCodeAo.invalid_token || errorCode == ErrorCodeAo.insufficient_scope) {
                return buildOAuth2ErrorResponse(appResponse);
            } else {
                RestfulResponse restfulResponse = new RestfulResponse();
                restfulResponse.setStatusCode(errorCode.getHttpCode());
                restfulResponse.setBodyEntity(error);
                return restfulResponse;
            }
        }
    }


    protected <SUCCESS_RESULT> RestfulResponse buildOAuth2ErrorResponse(ResponseAo<SUCCESS_RESULT> appResponse) {
        try {

            ErrorCodeAo errorCode = appResponse.getErrorResult().getErrorCode();
            OAuthResponse oltuResponse = OAuthRSResponse
                    .errorResponse(errorCode.getHttpCode())
                    .setError(errorCode.name())
                    .setErrorDescription(appResponse.getErrorResult().getDevErrMsg())
                    .buildHeaderMessage();

            RestfulResponse restfulResponse = fromOltuResponse(oltuResponse);
            return restfulResponse;
        } catch (OAuthSystemException e) {
            throw new RuntimeException(e);
        }
    }


    protected RestfulResponse fromOltuResponse(OAuthResponse oltuResponse) {
        RestfulResponse restfulResponse = new RestfulResponse();
        restfulResponse.setBodyEntity(null);
        restfulResponse.getHeader().put(OAuth.HeaderType.WWW_AUTHENTICATE, oltuResponse.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
        restfulResponse.setStatusCode(oltuResponse.getResponseStatus());
        return restfulResponse;
    }

}
