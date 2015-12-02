package com.amanichristian.agl.osmsandroid;

/**
 * Created by AMANI CHRISTIAN CYRILLE on 23/11/2015.
 */
import android.os.Bundle;


import com.amanichristian.agl.osmsandroid.Error.ServiceException;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.Headers;
import java.io.IOException;
import retrofit.Call;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.GsonConverterFactory;


public final class GenerateService
{
        public static final String CONTENT_TYPE = "Content-Type";
        public static final String CONTENT_LENGTH = "Content-Length";
        public static final String LOCATION = "Location";
        public static final String DATE = "Date";

        private String id;
        private String secretCode;
        private String codeEncodedBasic;
        private String codeEncodeBearer;
        private final String BODY = "client_credentials";

        private  Retrofit retrofit;

    public GenerateService(String id, String secretCode)
    {
        this.id = id;
        this.secretCode = secretCode;
        codeEncodedBasic = Credentials.basic(this.id,this.secretCode);

        this.retrofit = new Retrofit.Builder()
            .baseUrl(ServiceOSms.END_POINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    public  Token generatedToken() throws IOException,ServiceException
    {
        ServiceOSms serviceOSms;
        serviceOSms = retrofit.create(ServiceOSms.class);
        Call<Token> tokenCall = serviceOSms.getToken(codeEncodedBasic, BODY);
        Response<Token> tokenResponse = tokenCall.execute();
        if(tokenResponse.isSuccess())
            return tokenResponse.body();
        else
        {
            ServiceException.launchException(retrofit,tokenResponse,true);
            throw new ServiceException(ServiceException.messageError);
        }

    }

    public ResponseSMS sendSMS(SMS sms,Bundle bundleHeader,Token token) throws IOException,ServiceException
    {
        codeEncodeBearer = token.getToken_type()+" "+token.getAccess_token();
        String senderAddress = sms.getOutBoundSMSMessageRequest().getSenderAddress();
        encodedSenderAddress(senderAddress);
        ServiceOSms serviceOSms;
        serviceOSms = retrofit.create(ServiceOSms.class);
        Call<ResponseSMS> responseSMSCall = serviceOSms.sendSMS(sms, senderAddress, codeEncodeBearer);
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
        {
            ServiceException.launchException(retrofit,responseSMS,false);
            throw new ServiceException(ServiceException.messageError);
        }
    }

    private String encodedSenderAddress(String senderAddress)
    {
        senderAddress = senderAddress.replaceAll(":\\+","%3A%2B");
        return senderAddress;
    }

    public RemainderSMS remainderSMS(Token token) throws IOException,ServiceException
    {
        codeEncodeBearer = token.getToken_type()+" "+token.getAccess_token();
        ServiceOSms serviceOSms;
        serviceOSms = retrofit.create(ServiceOSms.class);
        Call<RemainderSMS> listSMS = serviceOSms.showSMSRemain(codeEncodeBearer);
        Response<RemainderSMS> listResponse = listSMS.execute();
        if(listResponse.isSuccess())
            return listResponse.body();
        else
        {
            ServiceException.launchException(retrofit,listResponse,false);
            throw new ServiceException(ServiceException.messageError);
        }
    }

    public StatisticSMS statisticSMS(Token token) throws IOException, ServiceException
    {
        codeEncodeBearer = token.getToken_type()+" "+token.getAccess_token();
        ServiceOSms serviceOSms;
        serviceOSms = retrofit.create(ServiceOSms.class);
        Call<StatisticSMS> statisticSMSCall = serviceOSms.getStatistics(codeEncodeBearer);
        Response<StatisticSMS> statisticSMSResponse = statisticSMSCall.execute();
        if(statisticSMSResponse.isSuccess())
            return statisticSMSResponse.body();
        else
        {
            ServiceException.launchException(retrofit,statisticSMSResponse,false);
            throw new ServiceException(ServiceException.messageError);
        }
    }

    public HistoricPurchase historicPurchase(Token token) throws IOException, ServiceException
    {
        codeEncodeBearer = token.getToken_type()+" "+token.getAccess_token();
        ServiceOSms serviceOSms;
        serviceOSms = retrofit.create(ServiceOSms.class);
        Call<HistoricPurchase> historicPurchaseCall = serviceOSms.getHistoric(codeEncodeBearer);
        Response<HistoricPurchase> historicPurchaseResponse = historicPurchaseCall.execute();
        if(historicPurchaseResponse.isSuccess())
            return historicPurchaseResponse.body();
        else
        {
            ServiceException.launchException(retrofit, historicPurchaseResponse,false);
            throw new ServiceException(ServiceException.messageError);
        }
    }

}
