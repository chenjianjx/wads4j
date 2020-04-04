package org.wads4j.core;

import org.junit.Test;

public class AppLayerShowcase {


    public static class SampleManager {

        public ResponseAo<SampleResultAo> getSomethingForFree(SampleUserAo user) {
            return ResponseAo.success(new SampleResultAo());
        }

        public ResponseAo<SampleResultAo> getSomethingProtected(SampleUserAo user) {
            if (user == null) {
                return ResponseAo.devErrResponse(ErrorCodeAo.invalid_token, "User has logged out");
            }
            return ResponseAo.success(new SampleResultAo());
        }

        public ResponseAo<SampleResultAo> getSomethingNotExisting(SampleUserAo user) {
            return ResponseAo.userErrResponse(ErrorCodeAo.RECORD_NOT_FOUND, "Can't get anything. ", null);
        }

        public ResponseAo<Void> doVoid() {
            return ResponseAo.success(null);
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
        //do nothing
    }


}
