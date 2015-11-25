package com.amanichristian.agl.osmsandroid;

/**
 * Created by AMANI CHRISTIAN CYRILLE on 23/11/2015.
 */
import android.app.Service;
import android.os.Bundle;
import android.util.Base64;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.RequestBody;

import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.Converter;
import retrofit.Response;
import retrofit.Retrofit;

public final class GenerateService
{
        public static final String CONTENT_TYPE = "Content-Type";
        public static final String CONTENT_LENGTH = "Content-Length";
        public static final String LOCATION = "Location";
        public static final String DATE = "Date";

        private String id;
        private String secretCode;
        private String  codeEncoded;
        private String id_secretCode;
        private String BODY = "grant_type=client_credentials";
        private ServiceOSms serviceOSms;

    public GenerateService(String id, String secretCode)
    {
        this.id = id;
        this.secretCode = secretCode;
        id_secretCode = this.id + ":"+ this.secretCode;
        codeEncoded = "Basic "+Base64.encodeToString(id_secretCode.getBytes(), Base64.DEFAULT);

    }

    public  Token generatedToken() throws IOException
    {
        ServiceOSms serviceOSms;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServiceOSms.END_POINT)
                .build();
        serviceOSms= retrofit.create(ServiceOSms.class);
        Call<Token> tokenCall = serviceOSms.getToken(codeEncoded, BODY);
        Response<Token> tokenResponse = tokenCall.execute();
        if(tokenResponse.isSuccess())
            return tokenResponse.body();
        else
            return null;
    }

    public ResponseSMS sendSMS(SMS sms,Bundle bundleHeader) throws IOException
    {
        String senderAddress = sms.getOutBoundSMSMessageRequest().getSenderAddress();
        encodedSenderAddress(senderAddress);
        ServiceOSms serviceOSms;
        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(ServiceOSms.END_POINT)
                            .build();

        serviceOSms = retrofit.create(ServiceOSms.class);
        Call<ResponseSMS> responseSMSCall = serviceOSms.sendSMS(sms, senderAddress, codeEncoded);
        Response<ResponseSMS> responseSMS = responseSMSCall.execute();
        if(responseSMS.isSuccess())
        {
            Headers headers = responseSMS.headers();
            bundleHeader.putString(CONTENT_TYPE,headers.get(CONTENT_TYPE));
            bundleHeader.putString(LOCATION,headers.get(LOCATION));
            bundleHeader.putString(CONTENT_LENGTH,headers.get(CONTENT_LENGTH));
            bundleHeader.putString(DATE,headers.get(DATE));
            return responseSMS.body();
        }
        else
            return null;
    }

    private String encodedSenderAddress(String senderAddress)
    {
        senderAddress = senderAddress.replaceAll(":\\+","%3A%2B");
        return senderAddress;
    }

    public RemainderSMS remainderSMS() throws IOException
    {
        ServiceOSms serviceOSms;
        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(ServiceOSms.END_POINT)
                            .build();
        serviceOSms = retrofit.create(ServiceOSms.class);
        Call<RemainderSMS> listSMS = serviceOSms.showSMSRemain(codeEncoded);
        Response<RemainderSMS> listResponse = listSMS.execute();
        if(listResponse.isSuccess())
            return listResponse.body();
        else
            return null;
    }

    public StatisticSMS statisticSMS() throws IOException
    {
        ServiceOSms serviceOSms;
        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(ServiceOSms.END_POINT)
                            .build();
        serviceOSms = retrofit.create(ServiceOSms.class);
        Call<StatisticSMS> statisticSMSCall = serviceOSms.getStatistics(codeEncoded);
        Response<StatisticSMS> statisticSMSResponse = statisticSMSCall.execute();
        if(statisticSMSResponse.isSuccess())
            return statisticSMSResponse.body();
        else
            return null;
    }

    public HistoricPurchase historicPurchase() throws IOException
    {
        ServiceOSms serviceOSms;
        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(ServiceOSms.END_POINT)
                            .build();
        serviceOSms = retrofit.create(ServiceOSms.class);
        Call<HistoricPurchase> historicPurchaseCall = serviceOSms.getHistoric(codeEncoded);
        Response<HistoricPurchase> historicPurchaseResponse = historicPurchaseCall.execute();
        if(historicPurchaseResponse.isSuccess())
            return historicPurchaseResponse.body();
        else
            return null;
    }
}
