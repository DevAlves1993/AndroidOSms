package com.amanichristian.agl.osmsandroid;

/**
 * Created by AMANI CHRISTIAN CYRILLE on 23/11/2015.
 */

import java.util.List;

import retrofit.Call;
import retrofit.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

public interface ServiceOSms
{
    String END_POINT = "https://api.orange.com";

    @POST("/oauth/v2/token")
    Call<Token> getToken(@Header("Authorization") String codeEncoded,@Body() String body);

    @Headers("Content-Type:application/json")
    @POST("/smsmessaging/v1/outbound/{tel}/requests")
    Call<ResponseSMS> sendSMS(@Body SMS sms,@Path("tel") String tel,@Header("Authorization") String codeEncoded);

    @GET("/sms/admin/v1/contracts")
    Call<RemainderSMS> showSMSRemain(@Header("Authorization") String codeEncoded);


    @GET("/sms/admin/v1/statistics")
    Call<StatisticSMS> getStatistics(@Header("Authorization") String codeEncoded);
}
