package com.test.http.feign;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by rmiao on 11/8/2016.
 */
public class GetCanonicalUrlResponse  implements Serializable{
    private static final long serialVersionUID = 5828004733787349090L;
    private String id;
    private boolean error;
    private String errorMessage;
    private String url;
    private Date createdDate;

    public GetCanonicalUrlResponse() {
    }

    public GetCanonicalUrlResponse(String id, boolean error, String errorMessage, String url, Date createdDate) {
        this.id = id;
        this.error = error;
        this.errorMessage = errorMessage;
        this.url = url;
        this.createdDate = createdDate;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isError() {
        return this.error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String toString() {
        return "GetCanonicalUrlResponse{id=\'" + this.id + '\'' + ", error=" + this.error + ", errorMessage=\'" + this.errorMessage + '\'' + ", url=\'" + this.url + '\'' + ", createdDate=" + this.createdDate + '}';
    }
}
