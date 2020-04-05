package org.wads4j.jaxrs;

import org.wads4j.core.ResponseAo;
import org.wads4j.restfulcommon.RestfulResponse;
import org.wads4j.restfulcommon.RestfulResponseCreator;

import javax.ws.rs.core.Response;

public class JaxRsResponseCreator {

    private RestfulResponseCreator _restfulResponseCreator;

    /**
     * convert api response data-structure to jax-rs's response
     *
     * @return
     */
    public <SUCCESS_RESULT> Response.ResponseBuilder fromAppResponse(ResponseAo<SUCCESS_RESULT> appResponse) {
        RestfulResponse restfulResponse = getRestfulResponseCreator().fromAppResponse(appResponse);
        Response.ResponseBuilder rb = Response
                .status(restfulResponse.getStatusCode())
                .entity(restfulResponse.getBodyEntity());
        restfulResponse.getHeader().entrySet().forEach(e -> rb.header(e.getKey(), e.getValue()));
        return rb;
    }

    /**
     * it has a  fallback. You don't have to call this
     * @param restfulResponseCreator
     */
    public void setRestfulResponseCreator(RestfulResponseCreator restfulResponseCreator) {
        this._restfulResponseCreator = restfulResponseCreator;
    }

    private RestfulResponseCreator getRestfulResponseCreator() {
        if (_restfulResponseCreator == null) {
            _restfulResponseCreator = new RestfulResponseCreator();
        }
        return _restfulResponseCreator;
    }
}
