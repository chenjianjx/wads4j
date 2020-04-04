package org.wads4j.restfulcommon;

import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError.ResourceResponse;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.rs.response.OAuthRSResponse;

import org.wads4j.core.ErrorResultAo;
import org.wads4j.core.ResponseAo;


public class RestfulResponseFactory {


    /**
     * convert api response data-structure to a common restful response
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static <SUCCESS_TYPE> RestfulResponse createRestfulResponse(ResponseAo<SUCCESS_TYPE> appResponse) {
        if (appResponse == null) {
            return null;
        }

        RestfulResponse commonResponse = new RestfulResponse();
        if (appResponse.isSuccessful()) {
            SUCCESS_TYPE successResult = appResponse.getSuccessResult();
            if (successResult == null) {
                commonResponse.setStatusCode(204);
                return commonResponse;
            } else {
                commonResponse.setStatusCode(200);
                commonResponse.setBody(successResult);
                return commonResponse;
            }
        } else {
            ErrorResultAo error = appResponse.getErrorResult();
            commonResponse.setStatusCode(error.getErrorCode().getHttpCode());
            commonResponse.setBody(error);
            return commonResponse;
        }
    }



    private static <T> RestfulResponse buildNotLoginRestResponse(String  realm) {
 //       try {

//            OAuthResponse oltuResponse = OAuthRSResponse
//                    .errorResponse(HttpServletResponse.SC_BAD_REQUEST)
//                    .setRealm(realm).setError(ResourceResponse.INVALID_REQUEST)
//                    .setErrorDescription("Missing auth token")
//                    .buildHeaderMessage();
//
//            RestfulResponse commonResponse = new RestfulResponse();
//
//            commonResponse.setBody();

//            return Response
//                    .status(oltuResponse.getResponseStatus())
//                    .header(OAuth.HeaderType.WWW_AUTHENTICATE,
//                            oltuResponse
//                                    .getHeader(OAuth.HeaderType.WWW_AUTHENTICATE))
//                    .entity(oltuResponse.getBody()).build();

            return null;
//        } catch (OAuthSystemException e) {
//            throw new RuntimeException(e);
//        }
    }
}
