package org.wads4j.core;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Map;


/**
 * This will be the return method type of a app-layer method. It includes either a success result, or an error result.
 * Ao = Application-layer object.
 * @param <T> The type of the success result
 */
public class ResponseAo<T> {

    private ErrorResultAo errorResult;

    private T successResult;

    public T getSuccessResult() {
        return successResult;
    }

    public void setSuccessResult(T successResult) {
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

    public static <T> ResponseAo<T> success(T successResult) {
        ResponseAo<T> r = new ResponseAo<T>();
        r.setSuccessResult(successResult);
        return r;
    }

    public static <T> ResponseAo<T> devErrResponse(ErrorCodeAo errorCode, String devErrMsg, String exceptionId) {
        ResponseAo<T> response = new ResponseAo<T>();
        ErrorResultAo err = new ErrorResultAo();
        err.setErrorCode(errorCode);
        err.setDevErrMsg(devErrMsg);
        err.setExceptionId(exceptionId);
        response.setErrorResult(err);
        return response;
    }


    public static <T> ResponseAo<T> devErrResponse(ErrorCodeAo errorCode, String devErrMsg) {
        return devErrResponse(errorCode, devErrMsg, null);
    }


    public static <T> ResponseAo<T> userErrResponse(ErrorCodeAo errorCode, String nonFieldUserError, Map<String, String> fieldUserErrors) {
        ResponseAo<T> response = new ResponseAo<T>();
        ErrorResultAo err = new ErrorResultAo();
        err.setErrorCode(errorCode);
        err.setNonFieldUserError(nonFieldUserError);
        err.setFieldUserErrors(fieldUserErrors);
        response.setErrorResult(err);
        return response;
    }

    public static <T> ResponseAo<T> errResponseWithSubErrorCode(ErrorCodeAo errorCode, String subErrorCode) {
        ResponseAo<T> response = new ResponseAo<T>();
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
