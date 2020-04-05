package org.wads4j.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Map;




/**
 * the error object for errors. Its error fields are compatible with OAuth2 fields (e.g. "error" and "error_description").
 * For its usage see {@link ResponseAo}
 */
@ApiModel("ErrorResult")
public class ErrorResultAo {

    public static final String OAUTH2_ERROR_CODE_FN = "error";

    public static final String OAUTH2_ERROR_DESC_FN = "error_description";

    public static final String EXCEPTION_ID_FN = "exception_id";

    public static final String ERROR_DESC_FOR_USER_FN = "error_description_for_user";

    public static final String NON_FIELD_ERROR_FOR_USER_FN = "non_field_error_for_user";

    public static final String FIELD_ERRORS_FOR_USER_FN = "field_errors_for_user";

    public static final String SUB_ERROR_CODE_FN = "sub_error_code";


    @ApiModelProperty(name = OAUTH2_ERROR_CODE_FN, required = false, value = "Error code. Compatible with OAuth2")
    @JsonProperty(OAUTH2_ERROR_CODE_FN)
    private String errorCode;

    @ApiModelProperty(name = OAUTH2_ERROR_DESC_FN, required = false, value = "Error message for client developers to read. Not for users. Compatible with OAuth2")
    @JsonProperty(OAUTH2_ERROR_DESC_FN)
    private String devErrMsg;


    @ApiModelProperty(name = EXCEPTION_ID_FN, value = "exception Id. Please send this to the backend developer for troubleshooting", required = false)
    @JsonProperty(EXCEPTION_ID_FN)
    private String exceptionId;

    @ApiModelProperty(name = NON_FIELD_ERROR_FOR_USER_FN, required = false, value = "Imagine there is a form, this is the error shown on top of the form, unrelated to any specific field ")
    @JsonProperty(NON_FIELD_ERROR_FOR_USER_FN)
    private String nonFieldUserError;

    @ApiModelProperty(name = FIELD_ERRORS_FOR_USER_FN, required = false, value = "Imagine there is a form, this is the error shown beside the input fields ")
    @JsonProperty(FIELD_ERRORS_FOR_USER_FN)
    private Map<String, String> fieldUserErrors;

    @ApiModelProperty(name = SUB_ERROR_CODE_FN, required = false, value = "Sub error code for conditioned handling on client side. The client can check if this sub code matches something, then do things accordingly")
    @JsonProperty(SUB_ERROR_CODE_FN)
    private String subErrorCode;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String error) {
        this.errorCode = error;
    }

    public String getDevErrMsg() {
        return devErrMsg;
    }

    public void setDevErrMsg(String devMsg) {
        this.devErrMsg = devMsg;
    }

    @ApiModelProperty(name = ERROR_DESC_FOR_USER_FN, required = false, value = "Error message that can be shown to users. It's derived from " + NON_FIELD_ERROR_FOR_USER_FN + " and " + FIELD_ERRORS_FOR_USER_FN)
    @JsonProperty(ERROR_DESC_FOR_USER_FN)
    public String getUserErrMsg() {
        if (nonFieldUserError != null) {
            return nonFieldUserError;
        }

        if (fieldUserErrors != null && fieldUserErrors.size() > 0) {
            return fieldUserErrors.values().stream().findFirst().get();
        }

        return null;
    }

    public String getSubErrorCode() {
        return subErrorCode;
    }

    public void setSubErrorCode(String subErrorCode) {
        this.subErrorCode = subErrorCode;
    }

    public String getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(String exceptionId) {
        this.exceptionId = exceptionId;
    }


    public String getNonFieldUserError() {
        return nonFieldUserError;
    }

    public void setNonFieldUserError(String nonFieldUserError) {
        this.nonFieldUserError = nonFieldUserError;
    }

    public Map<String, String> getFieldUserErrors() {
        return fieldUserErrors;
    }

    public void setFieldUserErrors(Map<String, String> fieldUserErrors) {
        this.fieldUserErrors = fieldUserErrors;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
