package org.akanza.androidosms.core;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.akanza.androidosms.core.exception.HttpApiOrangeException;
import org.akanza.androidosms.entity.OrangeSMS;
import org.akanza.androidosms.entity.Token;
import org.akanza.androidosms.entity.apiorangeresponse.ContractsSMS;
import org.akanza.androidosms.entity.apiorangeresponse.HistoricPurchase;
import org.akanza.androidosms.entity.apiorangeresponse.ResponseError;
import org.akanza.androidosms.entity.apiorangeresponse.ResponseSMS;
import org.akanza.androidosms.entity.apiorangeresponse.ResponseSubscription;
import org.akanza.androidosms.entity.apiorangeresponse.StatisticSMS;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSource;


/**
 * Created by user on 02/02/2017.
 */

public class OSms implements HttpApiOrange
{
    private OkHttpClient client;
    private Token token;
    private Moshi moshi;

    private OSms(Token token,OkHttpClient client)
    {
        this.token = token;
        this.client = client;
        moshi = new Moshi.Builder()
                .build();
    }

    public Token getToken()
    {
        return this.token;
    }

    @Override
    public ResponseSMS sendSms(OrangeSMS sms) throws IOException, HttpApiOrangeException
    {
        HttpUrl url = new HttpUrl.Builder()
                .scheme(SCHEME)
                .host(HOST)
                .addPathSegment("smsmessaging")
                .addPathSegment(VERSION_API)
                .addPathSegment("outbound")
                .addEncodedPathSegment(sms.getOutBoundSMSMessageRequest().getSenderAddress())
                .addPathSegment("requests")
                .build();
        JsonAdapter<OrangeSMS> orangeSMSJsonAdapter = moshi.adapter(OrangeSMS.class);
        Request request = new Request.Builder()
                .url(url)
                .addHeader(HEADER_AUTHORISATION,token.getAccessToken())
                .addHeader("Content-Type","application/json")
                .post(RequestBody.create(JSON_MEDIA,orangeSMSJsonAdapter.toJson(sms)))
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        if(response.isSuccessful())
        {
            return jsonToResponseSMS(response);
        }
        else
        {
            ResponseError error = jsonToResponseError(response);
            throw new HttpApiOrangeException(error);
        }
    }

    @Override
    public ResponseSubscription sendSubscription(String senderAddress) throws IOException, HttpApiOrangeException
    {
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(SCHEME)
                .host(HOST)
                .addPathSegment("smsmessaging")
                .addPathSegment(VERSION_API)
                .addPathSegment("outbound")
                .addPathSegment(senderAddress)
                .addPathSegment("subscriptions")
                .build();
        Request request = new Request.Builder()
                .url(httpUrl)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        if(response.isSuccessful())
        {
            return jsonToResponseSubscription(response);
        }
        else
        {
            ResponseError error = jsonToResponseError(response);
            throw new HttpApiOrangeException(error);
        }
    }

    @Override
    public StatisticSMS obtainStatisticSMS() throws IOException, HttpApiOrangeException
    {
        HttpUrl url = new HttpUrl.Builder()
                .scheme(SCHEME)
                .host(HOST)
                .addPathSegment("sms")
                .addPathSegment("admin")
                .addPathSegment("v1")
                .addPathSegment("statistics")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader(HEADER_AUTHORISATION,token.getAccessToken())
                .get()
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        if(response.isSuccessful())
        {
            return jsonToStatisticSMS(response);
        }
        else
        {
            ResponseError error = jsonToResponseError(response);
            throw new HttpApiOrangeException(error);
        }
    }

    @Override
    public ContractsSMS obtainsContractsSMS() throws IOException, HttpApiOrangeException
    {
        HttpUrl url = new HttpUrl.Builder()
                .scheme(SCHEME)
                .host(HOST)
                .addPathSegment("sms")
                .addPathSegment("admin")
                .addPathSegment("v1")
                .addPathSegment("contracts")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader(HEADER_AUTHORISATION,token.getAccessToken())
                .get()
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        if(response.isSuccessful())
        {
            return jsonToContractsSMS(response);
        }
        else
        {
            ResponseError error = jsonToResponseError(response);
            throw new HttpApiOrangeException(error);
        }
    }

    @Override
    public HistoricPurchase obtainHistoricSMS() throws IOException, HttpApiOrangeException
    {
        HttpUrl url = new HttpUrl.Builder()
                .scheme(SCHEME)
                .host(HOST)
                .addPathSegment("sms")
                .addPathSegment("admin")
                .addPathSegment("v1")
                .addPathSegment("purchaseorders")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader(HEADER_AUTHORISATION,token.getAccessToken())
                .get()
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        if(response.isSuccessful())
        {
            return jsonToHistoricPurchase(response);
        }
        else
        {
            ResponseError error = jsonToResponseError(response);
            throw new HttpApiOrangeException(error);
        }
    }

    private HistoricPurchase jsonToHistoricPurchase(Response response) throws IOException
    {
        BufferedSource source = response.body()
                .source();
        JsonAdapter<HistoricPurchase> historicPurchaseJsonAdapter = moshi.adapter(HistoricPurchase.class);
        return historicPurchaseJsonAdapter.fromJson(source);
    }

    private ResponseSMS jsonToResponseSMS(Response response) throws IOException
    {
        BufferedSource source = response.body()
                .source();
        JsonAdapter<ResponseSMS> responseSMSJsonAdapter = moshi.adapter(ResponseSMS.class);
        return responseSMSJsonAdapter.fromJson(source);
    }

    private ResponseSubscription jsonToResponseSubscription(Response response) throws IOException
    {
        BufferedSource source = response.body()
                .source();
        JsonAdapter<ResponseSubscription> responseSubscriptionJsonAdapter = moshi.adapter(ResponseSubscription.class);
        return responseSubscriptionJsonAdapter.fromJson(source);
    }

    private StatisticSMS jsonToStatisticSMS(Response response) throws IOException
    {
        BufferedSource source = response.body()
                .source();
        JsonAdapter<StatisticSMS> statisticSMSJsonAdapter = moshi.adapter(StatisticSMS.class);
        return statisticSMSJsonAdapter.fromJson(source);
    }

    private ContractsSMS jsonToContractsSMS(Response response) throws IOException
    {
        BufferedSource source = response.body()
                .source();
        JsonAdapter<ContractsSMS> contractsSMSJsonAdapter = moshi.adapter(ContractsSMS.class);
        return contractsSMSJsonAdapter.fromJson(source);
    }

    private ResponseError jsonToResponseError(Response response) throws IOException
    {
        BufferedSource source = response.body()
                .source();
        JsonAdapter<ResponseError> responseErrorJsonAdapter = moshi.adapter(ResponseError.class);
        return responseErrorJsonAdapter.fromJson(source);
    }

    public static class BuilderOSms extends Builder
    {
        @Override
        public OSms build() throws IOException, HttpApiOrangeException
        {
            if(id == null || secretCode == null)
            {
                Token token = obtainsToken();
                return new OSms(token,Builder.client);
            }
            return null;
        }

        @Override
        public BuilderOSms id(String id)
        {
            this.id = id;
            return this;
        }

        @Override
        public BuilderOSms secretCode(String secretCode)
        {
            this.secretCode = secretCode;
            return this;
        }

        @Override
        public BuilderOSms okHttpClient(OkHttpClient client)
        {
            Builder.client = null;
            Builder.client = client;
            return this;
        }
    }
}
