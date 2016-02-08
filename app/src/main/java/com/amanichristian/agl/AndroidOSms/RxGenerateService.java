package com.amanichristian.agl.AndroidOSms;

import android.app.Service;
import android.os.Bundle;

import com.amanichristian.agl.AndroidOSms.Error.ServiceException;
import okhttp3.Credentials;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.functions.Action1;



/**
 * Created by AMANI CHRISTIAN CYRILLE on 31/01/2016.
 */
public class RxGenerateService
{
    private String id;
    private String secretCode;
    private String codeEncodedBasic;
    private String codeEncodeBearer;
    private final String BODY = "client_credentials";

    private Retrofit retrofit;
    private RxServiceOSms rxServiceOSms;

    public RxGenerateService(String id,String secretCode)
    {
        this.id = id;
        this.secretCode = secretCode;
        retrofit = new Retrofit.Builder()
                                .baseUrl(ServiceOSms.END_POINT)
                                .addConverterFactory(GsonConverterFactory.create())
                                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                .build();
        codeEncodedBasic = Credentials.basic(this.id,this.secretCode);
        rxServiceOSms = retrofit.create(RxServiceOSms.class);
    }

    public Token generateToken() throws ServiceException
    {
        final Token[] tokenGenerate = new Token[1];
        rxServiceOSms.getToken(codeEncodedBasic,BODY)
                    .doOnNext(new Action1<Token>()
                    {
                        @Override
                        public void call(Token token)
                        {
                            tokenGenerate[0] = token;
                        }
                    })
                    .doOnError(new Action1<Throwable>()
                    {
                        @Override
                        public void call(Throwable throwable)
                        {
                            ServiceException.launchException(throwable);
                        }
                    }).subscribe();
        if(tokenGenerate[0] != null)
           return tokenGenerate[0];
        else
        {
            throw new ServiceException(ServiceException.messageError);
        }
    }
    public Observable<ResponseSMS> sendSMS(SMS sms,Bundle bundle,Token token) throws ServiceException
    {
        codeEncodeBearer = token.getToken_type()+" "+token.getAccess_token();
        Observable<ResponseSMS> observable =  rxServiceOSms.sendSMS(sms,encodedSenderAddress(sms.getOutBoundSMSMessageRequest().getSenderAddress())
                ,codeEncodeBearer)
                        .doOnError(new Action1<Throwable>()
                        {
                            @Override
                            public void call(Throwable throwable)
                            {
                                ServiceException.launchException(throwable);
                            }
                        });
        if(observable != null)
            return observable;
        else
        {
            throw new ServiceException(ServiceException.messageError);
        }
    }
    private String encodedSenderAddress(String senderAddress)
    {
        senderAddress = senderAddress.replaceAll(":\\+","%3A%2B");
        return senderAddress;
    }
    public Observable<RemainderSMS> remainderSMS(Token token) throws ServiceException
    {
        codeEncodeBearer = token.getToken_type()+" "+token.getAccess_token();
        Observable<RemainderSMS> observable = rxServiceOSms.showSMSRemain(codeEncodeBearer)
                .doOnError(new Action1<Throwable>()
                {
                    @Override
                    public void call(Throwable throwable)
                    {
                        ServiceException.launchException(throwable);
                    }
                });
        if(observable != null)
            return observable;
        else
        {
            throw new ServiceException(ServiceException.messageError);
        }
    }
    public Observable<StatisticSMS> statisticSMS(Token token) throws ServiceException
    {
        codeEncodeBearer = token.getToken_type()+" "+token.getAccess_token();
        Observable<StatisticSMS> observable = rxServiceOSms.getStatistics(codeEncodeBearer)
                    .doOnError(new Action1<Throwable>()
                    {
                        @Override
                        public void call(Throwable throwable)
                        {
                            ServiceException.launchException(throwable);
                        }
                    });
        if(observable != null)
            return observable;
        else
        {
            throw new ServiceException(ServiceException.messageError);
        }
    }

    public Observable<HistoricPurchase> historicPurchase(Token token) throws ServiceException
    {
        codeEncodeBearer = token.getToken_type()+" "+token.getAccess_token();
        Observable<HistoricPurchase> observable = rxServiceOSms.getHistoric(codeEncodeBearer)
                    .doOnError(new Action1<Throwable>()
                    {
                        @Override
                        public void call(Throwable throwable)
                        {
                            ServiceException.launchException(throwable);
                        }
                    });
        if(observable != null)
            return observable;
        else
        {
            throw new ServiceException(ServiceException.messageError);
        }
    }

}
