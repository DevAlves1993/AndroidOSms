package org.akanza.androidosms.core;

import org.akanza.androidosms.core.exception.HttpApiOrangeException;
import org.akanza.androidosms.entity.OrangeSMS;
import org.akanza.androidosms.entity.Token;
import org.akanza.androidosms.entity.apiorangeresponse.ContractsSMS;
import org.akanza.androidosms.entity.apiorangeresponse.HistoricPurchase;
import org.akanza.androidosms.entity.apiorangeresponse.ResponseSMS;
import org.akanza.androidosms.entity.apiorangeresponse.ResponseSubscription;
import org.akanza.androidosms.entity.apiorangeresponse.StatisticSMS;

import java.io.IOException;

import okhttp3.OkHttpClient;


/**
 * Created by user on 02/02/2017.
 */

public class OSms implements HttpApiOrange
{
    private OkHttpClient client;
    private Token token;

    private OSms(Token token,OkHttpClient client)
    {
        this.token = token;
        this.client = client;
    }

    public Token getToken()
    {
        return this.token;
    }

    @Override
    public ResponseSMS sendSms(OrangeSMS sms) throws IOException, HttpApiOrangeException
    {
        return null;
    }

    @Override
    public ResponseSubscription sendSubscription() throws IOException, HttpApiOrangeException
    {
        return null;
    }

    @Override
    public StatisticSMS obtainStatisticSMS() throws IOException, HttpApiOrangeException
    {
        return null;
    }

    @Override
    public ContractsSMS obtainsContractsSMS() throws IOException, HttpApiOrangeException
    {
        return null;
    }

    @Override
    public HistoricPurchase obtainHistoricSMS() throws IOException, HttpApiOrangeException
    {
        return null;
    }

    public static class BuilderOSms extends Builder
    {
        @Override
        public HttpApiOrange build() throws IOException, HttpApiOrangeException
        {
            if(id == null || secretCode == null)
            {
                Token token = obtainsToken();
                return new OSms(token,Builder.client);
            }
            return null;
        }
    }
}
