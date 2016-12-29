package com.test.json.jsonIterator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jsoniter.JsonIterator;
import com.test.json.jsonIterator.entity.AwardDetail;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by rmiao on 12/28/2016.
 */
public class TestJsonIterator {
    private String json;
    @Before
    public void setUp(){
        json = "{\n" +
                "    \"id\": \"6da9ea7e-9315-44d9-a6d6-d598dae80b3e\",\n" +
                "    \"brandType\": \"EXPEDIA\",\n" +
                "    \"name\": \"The MVP\",\n" +
                "    \"awardDescription\": \"awardDesc\",\n" +
                "    \"awardLandingPageURL\": \"awardLangdingPageURL\",\n" +
                "    \"status\": \"Live\",\n" +
                "    \"currentStep\": 5,\n" +
                "    \"lastUpdateDate\": \"2016-12-29T02:26:52+0000\",\n" +
                "    \"lastUpdateBy\": \"33\",\n" +
                "    \"variations\": {\n" +
                "        \"light\": [\n" +
                "            {\n" +
                "                \"artWorkUrl\": \"url\",\n" +
                "                \"sizeType\": \"Square\",\n" +
                "                \"sizeShow\": \"152x152\",\n" +
                "                \"imgConfig\": {\n" +
                "                    \"artworkX\": \"7px\",\n" +
                "                    \"artworkY\": \"7px\",\n" +
                "                    \"hotelNameLTX\": \"7px\",\n" +
                "                    \"hotelNameLTY\": \"85px\",\n" +
                "                    \"hotelNameRBX\": \"145px\",\n" +
                "                    \"hotelNameRBY\": \"123px\",\n" +
                "                    \"hotelNameFontSize\": \"13px\",\n" +
                "                    \"hotelNameFontFamily\": \"Courie New\",\n" +
                "                    \"hotelNameFontColor\": \"#005eb3\"\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"artWorkUrl\": \"url\",\n" +
                "                \"sizeType\": \"Landscape\",\n" +
                "                \"sizeShow\": \"152x82\",\n" +
                "                \"imgConfig\": {\n" +
                "                    \"artworkX\": \"7px\",\n" +
                "                    \"artworkY\": \"7px\",\n" +
                "                    \"hotelNameLTX\": \"7px\",\n" +
                "                    \"hotelNameLTY\": \"51px\",\n" +
                "                    \"hotelNameRBX\": \"121px\",\n" +
                "                    \"hotelNameRBY\": \"81px\",\n" +
                "                    \"hotelNameFontSize\": \"11px\",\n" +
                "                    \"hotelNameFontFamily\": \"Courie New\",\n" +
                "                    \"hotelNameFontColor\": \"#003452\"\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"artWorkUrl\": \"url\",\n" +
                "                \"sizeType\": \"portrait\",\n" +
                "                \"sizeShow\": \"82x152\",\n" +
                "                \"imgConfig\": {\n" +
                "                    \"artworkX\": \"7px\",\n" +
                "                    \"artworkY\": \"35px\",\n" +
                "                    \"hotelNameLTX\": \"7px\",\n" +
                "                    \"hotelNameLTY\": \"115px\",\n" +
                "                    \"hotelNameRBX\": \"75px\",\n" +
                "                    \"hotelNameRBY\": \"150px\",\n" +
                "                    \"hotelNameFontSize\": \"11px\",\n" +
                "                    \"hotelNameFontFamily\": \"Courie New\",\n" +
                "                    \"hotelNameFontColor\": \"#003452\"\n" +
                "                }\n" +
                "            }\n" +
                "        ],\n" +
                "        \"dark\": [\n" +
                "            {\n" +
                "                \"artWorkUrl\": \"url\",\n" +
                "                \"sizeType\": \"Square\",\n" +
                "                \"sizeShow\": \"152x152\",\n" +
                "                \"imgConfig\": {\n" +
                "                    \"artworkX\": \"7px\",\n" +
                "                    \"artworkY\": \"7px\",\n" +
                "                    \"hotelNameLTX\": \"7px\",\n" +
                "                    \"hotelNameLTY\": \"85px\",\n" +
                "                    \"hotelNameRBX\": \"145px\",\n" +
                "                    \"hotelNameRBY\": \"123px\",\n" +
                "                    \"hotelNameFontSize\": \"13px\",\n" +
                "                    \"hotelNameFontFamily\": \"Courie New\",\n" +
                "                    \"hotelNameFontColor\": \"#fff\"\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"artWorkUrl\": \"url\",\n" +
                "                \"sizeType\": \"Landscape\",\n" +
                "                \"sizeShow\": \"152x82\",\n" +
                "                \"imgConfig\": {\n" +
                "                    \"artworkX\": \"7px\",\n" +
                "                    \"artworkY\": \"7px\",\n" +
                "                    \"hotelNameLTX\": \"7px\",\n" +
                "                    \"hotelNameLTY\": \"51px\",\n" +
                "                    \"hotelNameRBX\": \"121px\",\n" +
                "                    \"hotelNameRBY\": \"81px\",\n" +
                "                    \"hotelNameFontSize\": \"11px\",\n" +
                "                    \"hotelNameFontFamily\": \"Courie New\",\n" +
                "                    \"hotelNameFontColor\": \"#003452\"\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"artWorkUrl\": \"url\",\n" +
                "                \"sizeType\": \"portrait\",\n" +
                "                \"sizeShow\": \"82x152\",\n" +
                "                \"imgConfig\": {\n" +
                "                    \"artworkX\": \"7px\",\n" +
                "                    \"artworkY\": \"35px\",\n" +
                "                    \"hotelNameLTX\": \"7px\",\n" +
                "                    \"hotelNameLTY\": \"115px\",\n" +
                "                    \"hotelNameRBX\": \"75px\",\n" +
                "                    \"hotelNameRBY\": \"150px\",\n" +
                "                    \"hotelNameFontSize\": \"11px\",\n" +
                "                    \"hotelNameFontFamily\": \"Courie New\",\n" +
                "                    \"hotelNameFontColor\": \"#fff\"\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
    }

    @Test
    public void testSpeed() throws IOException {
        long gson_start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            byGson();
        }
        long gson_end = System.currentTimeMillis();
        long time_gson = gson_end - gson_start;


        long jackson_start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            byJackson();
        }
        long jackson_end = System.currentTimeMillis();
        long time_jackson = jackson_end - jackson_start;

        System.out.println(time_gson);
        System.out.println(time_jackson);
        System.out.println(time_gson-time_jackson);

        //you bug
//        byIterator();
    }

    private void byGson() {
        Gson gson = new Gson();
        AwardDetail awardDetail = gson.fromJson(json, AwardDetail.class);
        Assert.assertNotNull(awardDetail.getId());
        Assert.assertNotNull(awardDetail.getVariations().getDark().get(0).getImgConfig());
    }

    private void byJackson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        AwardDetail awardDetail = objectMapper.readValue(json, AwardDetail.class);
        Assert.assertNotNull(awardDetail.getId());
        Assert.assertNotNull(awardDetail.getVariations().getDark().get(0).getImgConfig());
    }

    private void byIterator() throws IOException {
//        JsonIterator iter = JsonIterator.parse("{\"id\":\"8554a76c-8334-4b45-9730-80b7feafa428\"}");
        JsonIterator iter = JsonIterator.parse(json);
        AwardDetail read = iter.read(AwardDetail.class);
        System.out.println(read.getId());

    }
}
