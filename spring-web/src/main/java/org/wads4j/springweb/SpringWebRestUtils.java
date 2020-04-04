package org.wads4j.springweb;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.wads4j.core.ErrorResultAo;
import org.wads4j.core.ResponseAo;


public class SpringWebRestUtils {

    /**
     * convert api response data-structure to spring rest's response
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static <SUCCESS_TYPE> ResponseEntity fromAppResponse(ResponseAo<SUCCESS_TYPE> apiResponse) {
        if (apiResponse == null) {
            return null;
        }
        if (apiResponse.isSuccessful()) {
            SUCCESS_TYPE successResult = apiResponse.getSuccessResult();
            if (successResult == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(successResult);
            }
        } else {
            ErrorResultAo error = apiResponse.getErrorResult();
            return ResponseEntity.status(error.getErrorCode().getHttpCode()).body(error);
        }
    }

}
