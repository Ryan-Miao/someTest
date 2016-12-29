package com.test.json.jsonIterator.entity;


/**
 * Created by rmiao on 11/11/2016.
 */
public class AwardVariationItem {
    private String artWorkUrl;
    private String sizeType;//Square Landscape Portrait
    private String sizeShow; // 152x152 152x82   82x152
    private ImgConfig imgConfig;


    public AwardVariationItem() {
    }

    public AwardVariationItem(String artWorkUrl, String sizeType, String sizeShow) {
        this.artWorkUrl = artWorkUrl;
        this.sizeType = sizeType;
        this.sizeShow = sizeShow;
    }

    public AwardVariationItem(String artWorkUrl, String sizeType, String sizeShow, ImgConfig imgConfig) {
        this.artWorkUrl = artWorkUrl;
        this.sizeType = sizeType;
        this.sizeShow = sizeShow;
        this.imgConfig = imgConfig;
    }

    public ImgConfig getImgConfig() {
        return imgConfig;
    }

    public void setImgConfig(ImgConfig imgConfig) {
        this.imgConfig = imgConfig;
    }

    public String getArtWorkUrl() {
        return artWorkUrl;
    }

    public void setArtWorkUrl(String artWorkUrl) {
        this.artWorkUrl = artWorkUrl;
    }

    public String getSizeType() {
        return sizeType;
    }

    public void setSizeType(String sizeType) {
        this.sizeType = sizeType;
    }

    public String getSizeShow() {
        return sizeShow;
    }

    public void setSizeShow(String sizeShow) {
        this.sizeShow = sizeShow;
    }

    @Override
    public String toString() {
        return "AwardVariationItem{" +
                "artWorkUrl='" + artWorkUrl + '\'' +
                ", sizeType='" + sizeType + '\'' +
                ", sizeShow='" + sizeShow + '\'' +
                ", imgConfig='" + imgConfig + '\'' +
                '}';
    }
}
