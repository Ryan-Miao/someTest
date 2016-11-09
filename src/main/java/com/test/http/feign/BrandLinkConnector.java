package com.test.http.feign;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.Date;

public interface BrandLinkConnector {

    @Headers({"Content-Type: application/json", "authorization: Basic YWRtaW46ZGV2"})
    @RequestLine("GET /hello/date.json")
    Date getDate();

    @Headers({"Content-Type: application/json"})
    @RequestLine("GET /InfositeProperty/{hotelId}/getCanonicalUrl?langId={langId}&siteId={TPID}&returnFullyQualifiedUrl=1")
    GetCanonicalUrlResponse getBrandLinkV2(@Param("hotelId") Long hotelId, @Param("langId") String langId, @Param("TPID") String siteId);




}
