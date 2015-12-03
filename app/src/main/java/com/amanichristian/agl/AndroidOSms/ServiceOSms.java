package com.amanichristian.agl.AndroidOSms;

/**
 * Created by AMANI CHRISTIAN CYRILLE on 23/11/2015.
 */

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

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
