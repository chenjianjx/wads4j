package org.wads4j;

import org.junit.Test;

public class ApiSampleTest {


    public static class SampleApi {

        public ApiResponse<SampleResult> getSomethingForFree(SampleUser user) {
            return ApiResponse.success(new SampleResult());
        }

        public ApiResponse<SampleResult> getSomethingProtected(SampleUser user) {
            if (user == null) {
                return ApiResponse.devErrResponse(ApiErrorCode.invalid_token, "User has logged out");
            }
            return ApiResponse.success(new SampleResult());
        }

        public ApiResponse<SampleResult> getSomethingNotExisting(SampleUser user) {
            return ApiResponse.userErrResponse(ApiErrorCode.RECORD_NOT_FOUND, "Can't get anything. ", null);
        }

        public ApiResponse<Void> doVoid() {
            return ApiResponse.success(null);
        }

    }


    public static class SampleUser {

    }

    public static class SampleResult {

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
        //do nothing
    }


}
