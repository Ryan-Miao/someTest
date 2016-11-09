package com.test.http.feign;


import com.test.json.gson.GsonUtils;
import feign.Feign;
import feign.gson.GsonDecoder;

import java.util.Date;

public class BrandLinkClient {

    private static final String url = "http://link-service.us-east-1.prod.expedia.com";


    public GetCanonicalUrlResponse getBrandLinkV2(Long hotelId, String langId, String siteId) throws Exception {

        final BrandLinkConnector connector = Feign.builder().decoder(new GsonDecoder())
                .target(BrandLinkConnector.class, url);

        return connector.getBrandLinkV2(hotelId,langId, siteId);
    }

    public Date getDate() throws Exception {

        final BrandLinkConnector connector = Feign.builder().decoder(new GsonDecoder(GsonUtils.getDeserializerDateGson()))
                .target(BrandLinkConnector.class, "http://localhost:8080");

        return connector.getDate();
    }

    public static void main(String[] args) throws Exception {
        BrandLinkClient client = new BrandLinkClient();
        GetCanonicalUrlResponse brandLinkV2 = client.getBrandLinkV2(6606L, "1033", "1");
        System.out.println(brandLinkV2);

        Date date = client.getDate();
        System.out.println(date);

    }
    

}