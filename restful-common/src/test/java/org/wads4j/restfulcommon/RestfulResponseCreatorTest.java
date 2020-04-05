package org.wads4j.restfulcommon;


import org.junit.Test;
import org.wads4j.core.ErrorCodeAo;
import org.wads4j.core.ErrorResultAo;
import org.wads4j.core.ResponseAo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


public class RestfulResponseCreatorTest {
    RestfulResponseCreator creator = new RestfulResponseCreator();

    @Test
    public void createRestfulResponse_204() {

        ResponseAo<Void> responseAo = new ResponseAo<>();
        responseAo.setSuccessResult(null);

        RestfulResponse restfulResponse = creator.fromAppResponse(responseAo);
        assertNull(restfulResponse.getBodyEntity());
        assertTrue(restfulResponse.getHeader().isEmpty());
        assertEquals(204, restfulResponse.getStatusCode());

    }

    @Test
    public void createRestfulResponse_200() {

        ResponseAo<String> responseAo = new ResponseAo<>();
        responseAo.setSuccessResult("welcome!");

        RestfulResponse restfulResponse = creator.fromAppResponse(responseAo);
        assertEquals("welcome!", restfulResponse.getBodyEntity());
        assertTrue(restfulResponse.getHeader().isEmpty());
        assertEquals(200, restfulResponse.getStatusCode());

    }

    @Test
    public void createRestfulResponse_401() {

        ErrorResultAo errorResult = new ErrorResultAo();
        errorResult.setErrorCode(ErrorCodeAo.EC_INVALID_TOKEN);
        errorResult.setDevErrMsg("Not authenticated");
        ResponseAo<String> responseAo = new ResponseAo<>();
        responseAo.setErrorResult(errorResult);

        responseAo.setErrorResult(errorResult);

        RestfulResponse restfulResponse = creator.fromAppResponse(responseAo);

        assertEquals(1, restfulResponse.getHeader().size());
        assertEquals("Bearer error_description=\"Not authenticated\",error=\"invalid_token\"", restfulResponse.getHeader().get("WWW-Authenticate"));


        assertEquals(ErrorCodeAo.EC_INVALID_TOKEN, ((ErrorResultAo) restfulResponse.getBodyEntity()).getErrorCode());
        assertEquals("Not authenticated", ((ErrorResultAo) restfulResponse.getBodyEntity()).getDevErrMsg());

        assertEquals(401, restfulResponse.getStatusCode());

    }


    @Test
    public void createRestfulResponse_403() {

        ErrorResultAo errorResult = new ErrorResultAo();
        errorResult.setErrorCode(ErrorCodeAo.EC_INSUFFICIENT_SCOPE);
        errorResult.setDevErrMsg("No permission");
        ResponseAo<String> responseAo = new ResponseAo<>();
        responseAo.setErrorResult(errorResult);

        responseAo.setErrorResult(errorResult);

        RestfulResponse restfulResponse = creator.fromAppResponse(responseAo);

        assertEquals(1, restfulResponse.getHeader().size());
        assertEquals("Bearer error_description=\"No permission\",error=\"insufficient_scope\"", restfulResponse.getHeader().get("WWW-Authenticate"));


        assertEquals(ErrorCodeAo.EC_INSUFFICIENT_SCOPE, ((ErrorResultAo) restfulResponse.getBodyEntity()).getErrorCode());
        assertEquals("No permission", ((ErrorResultAo) restfulResponse.getBodyEntity()).getDevErrMsg());

        assertEquals(403, restfulResponse.getStatusCode());

    }


    @Test
    public void createRestfulResponse_bizError() {

        ErrorResultAo errorResult = new ErrorResultAo();
        errorResult.setErrorCode(ErrorCodeAo.EC_SERVER_ERROR);
        errorResult.setDevErrMsg("Some thing strange");
        ResponseAo<String> responseAo = new ResponseAo<>();
        responseAo.setErrorResult(errorResult);

        responseAo.setErrorResult(errorResult);

        RestfulResponse restfulResponse = creator.fromAppResponse(responseAo);

        assertEquals(ErrorCodeAo.EC_SERVER_ERROR, ((ErrorResultAo) restfulResponse.getBodyEntity()).getErrorCode());
        assertEquals("Some thing strange", ((ErrorResultAo) restfulResponse.getBodyEntity()).getDevErrMsg());
        assertEquals(500, restfulResponse.getStatusCode());

    }
}
