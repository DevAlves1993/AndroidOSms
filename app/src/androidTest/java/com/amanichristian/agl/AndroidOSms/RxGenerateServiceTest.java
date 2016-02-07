package com.amanichristian.agl.AndroidOSms;

import android.os.Bundle;

import com.amanichristian.agl.AndroidOSms.Others.PartnerContracts;
import com.amanichristian.agl.AndroidOSms.Others.PartnerStatistics;
import com.amanichristian.agl.AndroidOSms.Others.PurchaseOrders;
import com.amanichristian.agl.AndroidOSms.Others.ServiceContracts;
import com.amanichristian.agl.AndroidOSms.Others.ServiceStatistics;

import junit.framework.TestCase;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by AMANI CHRISTIAN CYRILLE on 07/02/2016.
 */
public class RxGenerateServiceTest extends TestCase
{

    public void testGenerateToken() throws Exception
    {
        String id = "xxxx";
        String secretCode = "xxxxx";
        RxGenerateService service = new RxGenerateService(id,secretCode);
        Token token = service.generateToken();
        String expire = token.getExpires_in();
        String access = token.getAccess_token();
        String tokenType = token.getToken_type();
        System.out.println("this token expire in :"+expire);
        System.out.println("the access of token it is :"+ access);
        System.out.println("the type token it is :" + tokenType);
    }

    public void testSendSMS(Token token) throws Exception
    {
        String id = "xxxx";
        String secretCode = "xxxxx";
        RxGenerateService service = new RxGenerateService(id,secretCode);
        String senderAddress = "+22500000000";
        String address = "+22500000000";
        String content = "my content";
        SMS sms = new SMS(address,senderAddress,content);
        Bundle bundleHeader = new Bundle();

        Observable<ResponseSMS> RxResponseSms = service.sendSMS(sms,bundleHeader,token);
        RxResponseSms.map(new Func1<ResponseSMS, ResponseSMS.SMSResponse>()
                        {
                            @Override
                            public ResponseSMS.SMSResponse call(ResponseSMS responseSMS)
                            {
                                return responseSMS.getOutBoundSMSMessageRequest();
                            }
                        })
                        .doOnNext(new Action1<ResponseSMS.SMSResponse>()
                        {
                            @Override
                            public void call(ResponseSMS.SMSResponse smsResponse)
                            {
                                System.out.println(smsResponse.getOutboundSMSTextMessage());
                                System.out.println(smsResponse.getSenderAddress());
                            }
                        })
                        .subscribe();

        // recuperation of information of  headers
        String contentType = bundleHeader.getString(GenerateService.CONTENT_TYPE);
        String contentLength = bundleHeader.getString(GenerateService.CONTENT_LENGTH);
        String location = bundleHeader.getString(GenerateService.LOCATION);
        String date = bundleHeader.getString(GenerateService.DATE);

    }

    public void testRemainderSMS(Token token) throws Exception
    {
        String id = "xxxx";
        String secretCode = "xxxxx";
        RxGenerateService service = new RxGenerateService(id,secretCode);
        Observable<RemainderSMS> RxRemainderSms = service.remainderSMS(token);
        RxRemainderSms.map(new Func1<RemainderSMS, PartnerContracts>()
                        {
                            @Override
                            public PartnerContracts call(RemainderSMS remainderSMS)
                            {
                                return remainderSMS.getPartnerContracts();
                            }
                        })
                        .flatMap(new Func1<PartnerContracts, Observable<PartnerContracts.Contract>>()
                        {
                            @Override
                            public Observable<PartnerContracts.Contract> call(PartnerContracts partnerContracts)
                            {
                                return Observable.from(partnerContracts.getContracts());
                            }
                        })
                        .doOnNext(new Action1<PartnerContracts.Contract>()
                        {
                            @Override
                            public void call(PartnerContracts.Contract contract)
                            {
                                System.out.println("Contract definition it is : "+contract.getContractDescription());
                                System.out.println("Service it is: "+contract.getService());
                                System.out.println();
                            }
                        })
                        .flatMap(new Func1<PartnerContracts.Contract, Observable<ServiceContracts>>()
                        {
                            @Override
                            public Observable<ServiceContracts> call(PartnerContracts.Contract contract)
                            {
                                return Observable.from(contract.getServiceContracts());
                            }
                        })
                        .doOnNext(new Action1<ServiceContracts>()
                        {
                            @Override
                            public void call(ServiceContracts serviceContracts)
                            {
                                System.out.println("Your contract service it is : "+serviceContracts.getService());
                                System.out.println("Description :"+serviceContracts.getScDescription());
                                System.out.println("Country : "+serviceContracts.getCountry());
                                System.out.println("The contract id it is : "+serviceContracts.getContractId());
                                System.out.println("The service expire in : "+serviceContracts.getExpires());
                                System.out.println("The units :"+serviceContracts.getAvailableUnits());
                            }
                        }).subscribe();

    }

    public void testStatisticSMS(Token token) throws Exception
    {
        String id = "xxxx";
        String secretCode = "xxxxx";
        RxGenerateService service = new RxGenerateService(id,secretCode);
        Observable<StatisticSMS> RxStatisticSms = service.statisticSMS(token);
        RxStatisticSms.map(new Func1<StatisticSMS, PartnerStatistics>()
                        {
                            @Override
                            public PartnerStatistics call(StatisticSMS statisticSMS)
                            {
                                return statisticSMS.getPartnerStatistics();
                            }
                        })
                        .doOnNext(new Action1<PartnerStatistics>()
                        {
                            @Override
                            public void call(PartnerStatistics partnerStatistics)
                            {
                                System.out.println("The partner id it is: "+partnerStatistics.getPartnerId());
                            }
                        })
                        .flatMap(new Func1<PartnerStatistics, Observable<PartnerStatistics.Statistics>>()
                        {
                            @Override
                            public Observable<PartnerStatistics.Statistics> call(PartnerStatistics partnerStatistics)
                            {
                                return Observable.from(partnerStatistics.getStatistics());
                            }
                        })
                        .doOnNext(new Action1<PartnerStatistics.Statistics>()
                        {
                            @Override
                            public void call(PartnerStatistics.Statistics statistics)
                            {
                                System.out.println("Service :"+statistics.getService());
                                System.out.println();
                            }
                        })
                        .flatMap(new Func1<PartnerStatistics.Statistics, Observable<ServiceStatistics>>()
                        {
                            @Override
                            public Observable<ServiceStatistics> call(PartnerStatistics.Statistics statistics)
                            {
                                return Observable.from(statistics.getServiceStatistics());
                            }
                        })
                        .doOnNext(new Action1<ServiceStatistics>()
                        {
                            @Override
                            public void call(ServiceStatistics serviceStatistics)
                            {
                                System.out.println("Country : "+serviceStatistics.getCountry());
                            }
                        })
                        .flatMap(new Func1<ServiceStatistics, Observable<ServiceStatistics.CountryStatistics>>()
                        {
                            @Override
                            public Observable<ServiceStatistics.CountryStatistics> call(ServiceStatistics serviceStatistics)
                            {
                                return Observable.from(serviceStatistics.getCountyStatistics());
                            }
                        })
                        .subscribe(new Action1<ServiceStatistics.CountryStatistics>()
                        {
                            @Override
                            public void call(ServiceStatistics.CountryStatistics countryStatistics)
                            {
                                System.out.println("Id application it is: "+ countryStatistics.getApplicationId());
                                System.out.println("Usage : "+countryStatistics.getUsage());
                            }
                        });
    }

    public void testHistoricPurchase(Token token) throws Exception
    {
        String id = "xxxx";
        String secretCode = "xxxxx";
        RxGenerateService service = new RxGenerateService(id,secretCode);
        Observable<HistoricPurchase> RxHistoricSms = service.historicPurchase(token);
        RxHistoricSms.flatMap(new Func1<HistoricPurchase, Observable<PurchaseOrders>>()
                        {
                            @Override
                            public Observable<PurchaseOrders> call(HistoricPurchase historicPurchase)
                            {
                                return Observable.from(historicPurchase.getPurchaseOrders());
                            }
                        })
                        .doOnNext(new Action1<PurchaseOrders>()
                        {
                            @Override
                            public void call(PurchaseOrders purchaseOrders)
                            {
                                System.out.println("Partner Id : "+purchaseOrders.getPartnerId());
                                System.out.println("Bundle Id : "+purchaseOrders.getBundleId());
                                System.out.println("Description bundle : "+purchaseOrders.getBundleDescription());
                                System.out.println("purchase Orders id : "+purchaseOrders.getPurchaseOrderId());
                                System.out.println("Mode : " + purchaseOrders.getMode());
                                Observable.from(purchaseOrders.getInputs())
                                            .subscribe(new Action1<PurchaseOrders.Inputs>()
                                            {
                                                @Override
                                                public void call(PurchaseOrders.Inputs inputs)
                                                {
                                                    System.out.println("Type : "+inputs.getType());
                                                    System.out.println("Value : "+inputs.getValue());
                                                }
                                            });
                            }
                        })
                        .map(new Func1<PurchaseOrders, PurchaseOrders.OrderExecutionInformation>()
                        {
                            @Override
                            public PurchaseOrders.OrderExecutionInformation call(PurchaseOrders purchaseOrders)
                            {
                                return purchaseOrders.getOrderExecutionInformation();
                            }
                        })
                        .subscribe(new Action1<PurchaseOrders.OrderExecutionInformation>()
                        {
                            @Override
                            public void call(PurchaseOrders.OrderExecutionInformation orderExecutionInformation)
                            {
                                System.out.println("Amount : "+orderExecutionInformation.getAmount());
                                System.out.println("Id Contract : "+orderExecutionInformation.getContractId());
                                System.out.println("Country : "+orderExecutionInformation.getCountry());
                                System.out.println("Currency : "+orderExecutionInformation.getCurrency());
                                System.out.println("Date : "+orderExecutionInformation.getDate());
                                System.out.println("Service : "+orderExecutionInformation.getService());
                            }
                        });

    }
}