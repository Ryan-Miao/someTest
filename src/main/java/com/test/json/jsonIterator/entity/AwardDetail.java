package com.test.json.jsonIterator.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * The POJO related to the table on dynamondb
 * The hotel award detail
 *
 * Created by rmiao on 11/11/2016.
 */
public class AwardDetail {
    private String id;
    private String brandType;
    private String name;
    private String awardDescription;
    private String awardLandingPageURL;
    private String status;// live/ in progress
    private Integer currentStep;
//    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ",timezone = "GMT")
    private Date lastUpdateDate;
    private String lastUpdateBy;
    /**the award styles**/
    private AwardVariations variations;

    public String getId() {
        return id;
    }


    public AwardDetail() {
    }

    public AwardDetail(String id) {
        this.id = id;
    }

    public AwardDetail(String brandType, String name, String awardDescription, String awardLandingPageURL, String status, Integer currentStep, Date lastUpdateDate, String lastUpdateBy, AwardVariations variations) {
        this.brandType = brandType;
        this.name = name;
        this.awardDescription = awardDescription;
        this.awardLandingPageURL = awardLandingPageURL;
        this.status = status;
        this.currentStep = currentStep;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdateBy = lastUpdateBy;
        this.variations = variations;
    }



    public AwardVariations getVariations() {
        return variations;
    }

    public void setVariations(AwardVariations variations) {
        this.variations = variations;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrandType() {
        return brandType;
    }

    public void setBrandType(String brandType) {
        this.brandType = brandType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAwardDescription() {
        return awardDescription;
    }

    public void setAwardDescription(String awardDescription) {
        this.awardDescription = awardDescription;
    }

    public String getAwardLandingPageURL() {
        return awardLandingPageURL;
    }

    public void setAwardLandingPageURL(String awardLandingPageURL) {
        this.awardLandingPageURL = awardLandingPageURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(Integer currentStep) {
        this.currentStep = currentStep;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }


    @Override
    public String toString() {
        return "AwardDetail{" +
                "id='" + id + '\'' +
                ", brandType='" + brandType + '\'' +
                ", name='" + name + '\'' +
                ", awardDescription='" + awardDescription + '\'' +
                ", awardLandingPageURL='" + awardLandingPageURL + '\'' +
                ", status='" + status + '\'' +
                ", currentStep=" + currentStep +
                ", lastUpdateDate=" + lastUpdateDate +
                ", lastUpdateBy='" + lastUpdateBy + '\'' +
                ", variations=" + variations +
                '}';
    }
}
