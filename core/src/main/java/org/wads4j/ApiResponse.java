package org.wads4j;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Map;


/**
 * This will be the return method type of an api method. It includes either a success result, or an error result
 * @param <T> The type of the success result
 */
public class ApiResponse<T> {

    private ApiErrorResult errorResult;

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

    public ApiErrorResult getErrorResult() {
        return errorResult;
    }

    public void setErrorResult(ApiErrorResult errorResult) {
        this.errorResult = errorResult;
    }

    public static <T> ApiResponse<T> success(T successResult) {
        ApiResponse<T> r = new ApiResponse<T>();
        r.setSuccessResult(successResult);
        return r;
    }

    public static <T> ApiResponse<T> devErrResponse(ApiErrorCode errorCode, String devErrMsg, String exceptionId) {
        ApiResponse<T> response = new ApiResponse<T>();
        ApiErrorResult err = new ApiErrorResult();
        err.setErrorCode(errorCode);
        err.setDevErrMsg(devErrMsg);
        err.setExceptionId(exceptionId);
        response.setErrorResult(err);
        return response;
    }


    public static <T> ApiResponse<T> devErrResponse(ApiErrorCode errorCode, String devErrMsg) {
        return devErrResponse(errorCode, devErrMsg, null);
    }


    public static <T> ApiResponse<T> userErrResponse(ApiErrorCode errorCode, String nonFieldUserError, Map<String, String> fieldUserErrors) {
        ApiResponse<T> response = new ApiResponse<T>();
        ApiErrorResult err = new ApiErrorResult();
        err.setErrorCode(errorCode);
        err.setNonFieldUserError(nonFieldUserError);
        err.setFieldUserErrors(fieldUserErrors);
        response.setErrorResult(err);
        return response;
    }

    public static <T> ApiResponse<T> errResponseWithSubErrorCode(ApiErrorCode errorCode, String subErrorCode) {
        ApiResponse<T> response = new ApiResponse<T>();
        ApiErrorResult err = new ApiErrorResult();
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
