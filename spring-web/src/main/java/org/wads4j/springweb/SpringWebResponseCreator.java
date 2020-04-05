package org.wads4j.springweb;

import org.springframework.http.ResponseEntity;
import org.wads4j.core.ResponseAo;
import org.wads4j.restfulcommon.RestfulResponse;
import org.wads4j.restfulcommon.RestfulResponseCreator;

import java.util.Map;


public class SpringWebResponseCreator {

    private RestfulResponseCreator _restfulResponseCreator;

    /**
     * convert api response data-structure to spring rest's response
     *
     * @return
     */

    public <SUCCESS_RESULT> ResponseEntity fromAppResponse(ResponseAo<SUCCESS_RESULT> appResponse) {
        RestfulResponse restfulResponse = getRestfulResponseCreator().fromAppResponse(appResponse);
        ResponseEntity.BodyBuilder rb = copyRestfulResponseExceptBody(restfulResponse);
        return rb.body(restfulResponse.getBodyEntity());
    }

    protected ResponseEntity.BodyBuilder copyRestfulResponseExceptBody(RestfulResponse restfulResponse) {
        ResponseEntity.BodyBuilder rb = ResponseEntity.status(restfulResponse.getStatusCode());
        restfulResponse.getHeader().entrySet().forEach((Map.Entry<String, String> e) -> rb.header(e.getKey(), e.getValue()));
        return rb;
    }


    /**
     * it has a  fallback. You don't have to call this
     *
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
