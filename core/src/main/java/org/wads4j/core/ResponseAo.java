package org.wads4j.core;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Map;


/**
 * This will be the return method type of a app-layer method. It includes either a success result, or an error result.
 * Ao = Application-layer object.
 * @param <SUCCESS_RESULT> The type of the success result
 */
public class ResponseAo<SUCCESS_RESULT> {

    private ErrorResultAo errorResult;

    private SUCCESS_RESULT successResult;

    public SUCCESS_RESULT getSuccessResult() {
        return successResult;
    }

    public void setSuccessResult(SUCCESS_RESULT successResult) {
        this.successResult = successResult;
    }

    public boolean isSuccessful() {
        return errorResult == null;
    }

    public ErrorResultAo getErrorResult() {
        return errorResult;
    }

    public void setErrorResult(ErrorResultAo errorResult) {
        this.errorResult = errorResult;
    }

    public static <SUCCESS_RESULT> ResponseAo<SUCCESS_RESULT> success(SUCCESS_RESULT successResult) {
        ResponseAo<SUCCESS_RESULT> r = new ResponseAo<SUCCESS_RESULT>();
        r.setSuccessResult(successResult);
        return r;
    }

    public static <SUCCESS_RESULT> ResponseAo<SUCCESS_RESULT> devErrResponse(String errorCode, String devErrMsg, String exceptionId) {
        ResponseAo<SUCCESS_RESULT> response = new ResponseAo<SUCCESS_RESULT>();
        ErrorResultAo err = new ErrorResultAo();
        err.setErrorCode(errorCode);
        err.setDevErrMsg(devErrMsg);
        err.setExceptionId(exceptionId);
        response.setErrorResult(err);
        return response;
    }


    public static <SUCCESS_RESULT> ResponseAo<SUCCESS_RESULT> devErrResponse(String errorCode, String devErrMsg) {
        return devErrResponse(errorCode, devErrMsg, null);
    }


    public static <SUCCESS_RESULT> ResponseAo<SUCCESS_RESULT> userErrResponse(String errorCode, String nonFieldUserError, Map<String, String> fieldUserErrors) {
        ResponseAo<SUCCESS_RESULT> response = new ResponseAo<SUCCESS_RESULT>();
        ErrorResultAo err = new ErrorResultAo();
        err.setErrorCode(errorCode);
        err.setNonFieldUserError(nonFieldUserError);
        err.setFieldUserErrors(fieldUserErrors);
        response.setErrorResult(err);
        return response;
    }

    public static <SUCCESS_RESULT> ResponseAo<SUCCESS_RESULT> errResponseWithSubErrorCode(String errorCode, String subErrorCode) {
        ResponseAo<SUCCESS_RESULT> response = new ResponseAo<SUCCESS_RESULT>();
        ErrorResultAo err = new ErrorResultAo();
        err.setErrorCode(errorCode);
        err.setSubErrorCode(subErrorCode);
        response.setErrorResult(err);
        return response;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
