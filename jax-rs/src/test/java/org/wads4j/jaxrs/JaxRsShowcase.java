package org.wads4j.jaxrs;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.junit.Test;
import org.wads4j.core.ResponseAo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class JaxRsShowcase {


    public class SampleResource {


        SampleManager sampleManager = new SampleManager();
        JaxRsResponseCreator responseCreator = new JaxRsResponseCreator();


        @GET
        @Path(value = "/something")
        @Produces(MediaType.APPLICATION_JSON)
        @ApiOperation(value = "Get something")
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "Success", response = SampleResultAo.class),
                @ApiResponse(code = 404, message = "Record not found")
        })
        public Response getSomething() {
            SampleUserAo currentUser = getCurrentUser();
            ResponseAo<SampleResultAo> appResponse = sampleManager.getSomething(currentUser);
            return responseCreator.fromAppResponse(appResponse).build();
        }


        private SampleUserAo getCurrentUser() {
            return new SampleUserAo();
        }

    }

    public static class SampleManager {

        public ResponseAo<SampleResultAo> getSomething(SampleUserAo user) {
            return ResponseAo.success(new SampleResultAo());
        }
    }


    public static class SampleUserAo {

    }

    public static class SampleResultAo {

        private String foo;
        private String bar;


        public String getFoo() {
            return foo;
        }

        public void setFoo(String foo) {
            this.foo = foo;
        }

        public String getBar() {
            return bar;
        }

        public void setBar(String bar) {
            this.bar = bar;
        }

    }

    @Test
    public void dummyTest() {

    }
}
