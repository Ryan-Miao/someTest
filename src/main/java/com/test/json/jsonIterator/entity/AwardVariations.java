package com.test.json.jsonIterator.entity;


import java.util.List;

/**
 * Created by rmiao on 11/16/2016.
 */
public class AwardVariations {
    private List<AwardVariationItem> light;
    private List<AwardVariationItem> dark;

    public AwardVariations() {
    }

    public AwardVariations(List<AwardVariationItem> light, List<AwardVariationItem> dark) {
        this.light = light;
        this.dark = dark;
    }

    public List<AwardVariationItem> getLight() {
        return light;
    }

    public void setLight(List<AwardVariationItem> light) {
        this.light = light;
    }

    public List<AwardVariationItem> getDark() {
        return dark;
    }

    public void setDark(List<AwardVariationItem> dark) {
        this.dark = dark;
    }

    @Override
    public String toString() {
        return "AwardVariations{" +
                "light=" + light +
                ", dark=" + dark +
                '}';
    }
}
