# wads4j

The basic data structures for web api responses.  Features: 

* They are **POJOs on App Layer**, **unit-testable** without involving restful frameworks, and **reusable outside of restful** apis. 
* Error responses are **compatible with OAuth2** response data structure (e.g. error, error_description)
* Out-of-box support of Spring Rest (e.g. Spring Boot) and JAX-RS (e.g. Jersey),  with auth errors converted to OAuth2 http responses (401, WWW-Authenticate headers, etc.)
  

# Prerequisites
Java 8+

# Example

```java

import org.wads4j.core.ErrorCodes;
import org.wads4j.core.ResponseAo;

//The app layer manager
public class SampleManager {
  
    public ResponseAo<SampleResultAo> getSomethingProtected(SampleUserAo currentUser) {
        if (currentUser == null) {
            return ResponseAo.devErrResponse(ErrorCodes.INVALID_TOKEN, "User has logged out");
        }
        return ResponseAo.success(new SampleResultAo());
    }
}

//Your own result class for successful response
public class SampleResultAo {
    ...
}
```


# Use with Spring Rest (e.g. Spring Boot)


```$xml
        <dependency>
            <groupId>org.wads4j</groupId>
            <artifactId>wads4j-spring-web</artifactId>
            <version>1.0.0</version>
        </dependency>
```


```$java

 
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

```$xml
        <dependency>
            <groupId>org.wads4j</groupId>
            <artifactId>wads4j-jax-rs</artifactId>
            <version>1.0.0</version>
        </dependency>
```

```$xml

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
            <groupId>org.wads4j</groupId>
            <artifactId>wads4j-core</artifactId>
            <version>1.0.0</version>
        </dependency>

```