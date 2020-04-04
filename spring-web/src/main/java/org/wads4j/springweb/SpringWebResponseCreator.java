package org.wads4j.springweb;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.wads4j.core.ErrorResultAo;
import org.wads4j.core.ResponseAo;


public class SpringWebResponseCreator {

    /**
     * convert api response data-structure to spring rest's response
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static <SUCCESS_TYPE> ResponseEntity fromAppResponse(ResponseAo<SUCCESS_TYPE> appResponse) {
        if (appResponse == null) {
            return null;
        }
        if (appResponse.isSuccessful()) {
            SUCCESS_TYPE successResult = appResponse.getSuccessResult();
            if (successResult == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(successResult);
            }
        } else {
            ErrorResultAo error = appResponse.getErrorResult();
            return ResponseEntity.status(error.getErrorCode().getHttpCode()).body(error);
        }
    }

}
