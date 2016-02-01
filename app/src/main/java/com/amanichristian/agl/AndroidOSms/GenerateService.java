package com.amanichristian.agl.AndroidOSms;

/**
 * Created by AMANI CHRISTIAN CYRILLE on 23/11/2015.
 */
import android.os.Bundle;


import com.amanichristian.agl.AndroidOSms.Error.ServiceException;
import java.io.IOException;
import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.GsonConverterFactory;


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
        private ServiceOSms serviceOSms;

    public GenerateService(String id, String secretCode)
    {
        this.id = id;
        this.secretCode = secretCode;
        codeEncodedBasic = Credentials.basic(this.id, this.secretCode);

        this.retrofit = new Retrofit.Builder()
            .baseUrl(ServiceOSms.END_POINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        this.serviceOSms = retrofit.create(ServiceOSms.class);
    }

    public  Token generatedToken() throws IOException,ServiceException
    {

        Call<Token> tokenCall = serviceOSms.getToken(codeEncodedBasic, BODY);
        Response<Token> tokenResponse = tokenCall.execute();
        if(tokenResponse.isSuccess())
            return tokenResponse.body();
        else
        {
            ServiceException.launchException(tokenResponse);
            throw new ServiceException(ServiceException.messageError);
        }

    }

    public ResponseSMS sendSMS(SMS sms,Bundle bundleHeader,Token token) throws IOException,ServiceException
    {
        codeEncodeBearer = token.getToken_type()+" "+token.getAccess_token();
        String senderAddress = sms.getOutBoundSMSMessageRequest().getSenderAddress();
        encodedSenderAddress(senderAddress);
        Call<ResponseSMS> responseSMSCall = serviceOSms.sendSMS(sms, senderAddress, codeEncodeBearer);
        Response<ResponseSMS> responseSMS = responseSMSCall.execute();
        if(responseSMS.isSuccess())
        {
            okhttp3.Headers headers = responseSMS.headers();
            bundleHeader.putString(CONTENT_TYPE,headers.get(CONTENT_TYPE));
            bundleHeader.putString(LOCATION,headers.get(LOCATION));
            bundleHeader.putString(CONTENT_LENGTH,headers.get(CONTENT_LENGTH));
            bundleHeader.putString(DATE,headers.get(DATE));
            return responseSMS.body();
        }
        else
        {
            ServiceException.launchException(responseSMS);
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
        Call<RemainderSMS> listSMS = serviceOSms.showSMSRemain(codeEncodeBearer);
        Response<RemainderSMS> listResponse = listSMS.execute();
        if(listResponse.isSuccess())
            return listResponse.body();
        else
        {
            ServiceException.launchException(listResponse);
            throw new ServiceException(ServiceException.messageError);
        }
    }

    public StatisticSMS statisticSMS(Token token) throws IOException, ServiceException
    {
        codeEncodeBearer = token.getToken_type()+" "+token.getAccess_token();
        Call<StatisticSMS> statisticSMSCall = serviceOSms.getStatistics(codeEncodeBearer);
        Response<StatisticSMS> statisticSMSResponse = statisticSMSCall.execute();
        if(statisticSMSResponse.isSuccess())
            return statisticSMSResponse.body();
        else
        {
            ServiceException.launchException(statisticSMSResponse);
            throw new ServiceException(ServiceException.messageError);
        }
    }

    public HistoricPurchase historicPurchase(Token token) throws IOException, ServiceException
    {
        codeEncodeBearer = token.getToken_type()+" "+token.getAccess_token();
        Call<HistoricPurchase> historicPurchaseCall = serviceOSms.getHistoric(codeEncodeBearer);
        Response<HistoricPurchase> historicPurchaseResponse = historicPurchaseCall.execute();
        if(historicPurchaseResponse.isSuccess())
            return historicPurchaseResponse.body();
        else
        {
            ServiceException.launchException(historicPurchaseResponse);
            throw new ServiceException(ServiceException.messageError);
        }
    }

}
