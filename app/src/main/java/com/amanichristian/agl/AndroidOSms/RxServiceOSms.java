package com.amanichristian.agl.AndroidOSms;

import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;




/**
 * Created by AMANI CHRISTIAN CYRILLE on 31/01/2016.
 */
public interface RxServiceOSms
{

    @FormUrlEncoded
    @POST("/oauth/v2/token")
    Observable<Token> getToken(@Header("Authorization") String codeEncoded,@Field("grant_type") String grant_type);

    @Headers("Content-Type:application/json")
    @POST("/smsmessaging/v1/outbound/{tel}/requests")
    Observable<ResponseSMS> sendSMS(@Body SMS sms,@Path("tel") String tel,@Header("Authorization") String codeEncoded);

    @GET("/sms/admin/v1/contracts")
    Observable<RemainderSMS> showSMSRemain(@Header("Authorization") String codeEncoded);


    @GET("/sms/admin/v1/statistics")
    Observable<StatisticSMS> getStatistics(@Header("Authorization") String codeEncoded);

    @GET("/sms/admin/v1/purchaseorders")
    Observable<HistoricPurchase> getHistoric(@Header("Authorization") String codeEncoded);
}
