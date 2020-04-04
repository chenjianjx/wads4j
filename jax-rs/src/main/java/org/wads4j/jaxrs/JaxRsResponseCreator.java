package org.wads4j.jaxrs;

import org.wads4j.core.ErrorResultAo;
import org.wads4j.core.ResponseAo;

import javax.ws.rs.core.Response;

public class JaxRsResponseCreator {


    /**
     * convert api response data-structure to jax-rs's response
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static <SUCCESS_TYPE> Response fromAppResponse(ResponseAo<SUCCESS_TYPE> appResponse) {
        if (appResponse == null) {
            return null;
        }
        if (appResponse.isSuccessful()) {
            SUCCESS_TYPE successResult = appResponse.getSuccessResult();
            if (successResult == null) {
                return Response.status(Response.Status.NO_CONTENT).entity(null).build();
            } else {
                return Response.status(Response.Status.OK).entity(successResult).build();
            }
        } else {
            ErrorResultAo error = appResponse.getErrorResult();
            return Response.status(error.getErrorCode().getHttpCode()).entity(error).build();
        }
    }
}
