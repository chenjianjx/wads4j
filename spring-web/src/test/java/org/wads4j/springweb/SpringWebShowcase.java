package org.wads4j.springweb;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wads4j.core.ResponseAo;

import javax.annotation.Resource;

public class SpringWebShowcase {


    @RestController
    public class SampleController {

        @Resource
        SampleManager sampleApi;

        @RequestMapping(value = "/something", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        @ApiOperation(value = "Get something")
        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "Success", response = SampleResultAo.class),
                @ApiResponse(code = 404, message = "Record not found")
        })
        public ResponseEntity getSomething() {
            SampleUserAo currentUser = getCurrentUser();
            ResponseAo<SampleResultAo> appResponse = sampleApi.getSomething(currentUser);
            return SpringWebRestUtils.fromAppResponse(appResponse);
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
