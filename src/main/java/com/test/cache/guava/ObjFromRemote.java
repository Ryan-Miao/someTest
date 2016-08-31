package com.test.cache.guava;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

/**
 * a POJO  to store the info from remote server
 * Created by Ryan on 2016/8/31 0031.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ObjFromRemote implements Serializable{
    private static final long serialVersionUID = 7000209940367071022L;
    private String id;
    private String subject;
    private String body;
    private Date creationDate;

    public ObjFromRemote() {
    }

    public ObjFromRemote(String id, String subject, String body, Date creationDate) {
        this.id = id;
        this.subject = subject;
        this.body = body;
        this.creationDate = creationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "ObjFromRemote{" +
                "id='" + id + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
