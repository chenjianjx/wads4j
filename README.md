# wads4j

**wads4j** provides basic data structures for web api responses.  

Features: 

* They are **POJOs on App Layer** 
  * **unit-testable** without involving restful frameworks
  * **reusable outside of restful frameworks** 
* Error responses are **compatible with OAuth2** response data structure (e.g. error, error_description fields)
* They can be **converted to restful frameworks' objects**. 
  * Out-of-box support for **Spring Rest** (e.g. Spring Boot) 
  * Out-of-box support for **JAX-RS** (e.g. Jersey)
  * During conversion, auth errors will turn into **OAuth2 http responses** (401, WWW-Authenticate headers, etc.)
  

# Prerequisites
Java 8+
 
# Example: use the data structures

```java

import org.wads4j.core.ErrorCodes;
import org.wads4j.core.ResponseAo;

//The app layer manager
public class SampleManager {
  
    public ResponseAo<SampleResultAo> getSomethingProtected(SampleUserAo currentUser) {
        if (currentUser == null) {
            //error for frontend developers
            return ResponseAo.devErrResponse(ErrorCodes.INVALID_TOKEN, "User has logged out"); 
        }
        return ResponseAo.success(new SampleResultAo());
    }


    public ResponseAo<Void> doThingsNotAllowed(SampleUserAo currentUser) {
        //error for end users
        return ResponseAo.userErrResponse(ErrorCodes.INSUFFICIENT_SCOPE, "No permission", null);
    }

}

//Your own result class for successful response
public class SampleResultAo {
    ...
}
```

```java
        if (appResponse.isSuccessful()) {
            SUCCESS_RESULT successResult = appResponse.getSuccessResult();
            ...
        } else {
            ErrorResultAo error = appResponse.getErrorResult();
            String errorCode = error.getErrorCode();
            ...
        }

```


# Use with Spring Rest (e.g. Spring Boot)


```xml
        <dependency>
            <groupId>com.github.chenjianjx.wads4j</groupId>
            <artifactId>wads4j-spring-web</artifactId>
            <version>1.1.1</version>
        </dependency>
```


```java

 
@RestController
public class SomeController {

    @Resource
    SampleManager sampleManager;

    SpringWebResponseCreator springWebResponseCreator = new SpringWebResponseCreator(); //use IoC in real projects

    @GetMapping(value = "/something-protected")
    public ResponseEntity getSomethingProtected(SampleUserAo user) {
        ResponseAo<SampleResultAo> appResponse = sampleManager.getSomethingProtected(getCurrentUser());
        return springWebResponseCreator.fromAppResponse(appResponse);
    }

}
```


# Use with JAX-RS (e.g. Jersey)

```xml
        <dependency>
            <groupId>com.github.chenjianjx.wads4j</groupId>
            <artifactId>wads4j-jax-rs</artifactId>
            <version>1.1.1</version>
        </dependency>
```

```java

public class SomeResource {

    SampleManager sampleManager = new SampleManager(); //use IoC in real projects

    JaxRsResponseCreator jaxRsResponseCreator = new JaxRsResponseCreator(); //use IoC in real projects

    @GET
    @Path(value = "/something-protected")
    public Response getSomethingProtected(SampleUserAo user) {
        ResponseAo<SampleResultAo> appResponse = sampleManager.getSomethingProtected(getCurrentUser());
        return jaxRsResponseCreator.fromAppResponse(appResponse).build();
    }
}
```


# App Layer's Maven Dependency

If your app layer is an independent module from your restful layer, it should only dependent on the pure-POJO wads4j module: 

```xml

        <dependency>
            <groupId>com.github.chenjianjx.wads4j</groupId>
            <artifactId>wads4j-core</artifactId>
            <version>1.1.1</version>
        </dependency>

```


# Appendix: development

* Maven version should be >= 3.5.0 so that ${revision} can be used in pom files
