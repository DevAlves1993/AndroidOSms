package com.amanichristian.agl.AndroidOSms;

/**
 * Created by AMANI CHRISTIAN CYRILLE on 23/11/2015.
 */

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServiceOSms
{
    String END_POINT = "https://api.orange.com";

    @FormUrlEncoded
    @POST("/oauth/v2/token")
    Call<Token> getToken(@Header("Authorization") String codeEncoded,@Field("grant_type") String grant_type);

    @Headers("Content-Type:application/json")
    @POST("/smsmessaging/v1/outbound/{tel}/requests")
    Call<ResponseSMS> sendSMS(@Body SMS sms,@Path("tel") String tel,@Header("Authorization") String codeEncoded);

    @GET("/sms/admin/v1/contracts")
    Call<RemainderSMS> showSMSRemain(@Header("Authorization") String codeEncoded);


    @GET("/sms/admin/v1/statistics")
    Call<StatisticSMS> getStatistics(@Header("Authorization") String codeEncoded);

    @GET("/sms/admin/v1/purchaseorders")
    Call<HistoricPurchase> getHistoric(@Header("Authorization") String codeEncoded);
}
