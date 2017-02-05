package org.akanza.androidosms.core;

import org.akanza.androidosms.core.exception.HttpApiOrangeException;
import org.akanza.androidosms.entity.OrangeSMS;
import org.akanza.androidosms.entity.apiorangeresponse.ContractsSMS;
import org.akanza.androidosms.entity.apiorangeresponse.HistoricPurchase;
import org.akanza.androidosms.entity.apiorangeresponse.ResponseSMS;
import org.akanza.androidosms.entity.apiorangeresponse.ResponseSubscription;
import org.akanza.androidosms.entity.apiorangeresponse.StatisticSMS;

import java.io.IOException;


/**
 * Created by user on 05/02/2017.
 */

interface HttpApiOrange
{
    ResponseSMS sendSms(OrangeSMS sms) throws IOException,HttpApiOrangeException;
    ResponseSubscription sendSubscription() throws IOException,HttpApiOrangeException;
    StatisticSMS obtainStatisticSMS() throws IOException,HttpApiOrangeException;
    ContractsSMS obtainsContractsSMS() throws IOException,HttpApiOrangeException;
    HistoricPurchase obtainHistoricSMS() throws IOException,HttpApiOrangeException;
}
