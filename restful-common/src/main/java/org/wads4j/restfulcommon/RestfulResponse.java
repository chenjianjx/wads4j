package org.wads4j.restfulcommon;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * a common response, not specific to any restful framework
 */
public class RestfulResponse {

    private String header;

    private Object body;

    private int statusCode;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object bodyEntity) {
        this.body = bodyEntity;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
