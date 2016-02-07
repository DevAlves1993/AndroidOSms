# AndroidOSms
AndroidOSms is a simple library that allows you to consume  quickly orange api [SMS API] (https://www.orangepartner.com/SMS-CI-API) and making you productive in the development of your mobile apps requiring a called on the [orange api] (https://www.orangepartner.com/SMS-CI-API).
It is based on the library [retrofit] (https://github.com/square/retrofit) and the library [okhttp] (https://github.com/square/okhttp)


## Usage

#### How generate a Token :

The Token object it is the representation object of the JSon response returned by the orange smsAPI.
For generate a Token object, create a object GenerateService.Then call the method `generatedToken()` of 
object GenerateService.
For example:

    private void generateMyToken() throws IOException,ServiceException
    {
    	GenerateService service = new GenerateService("5454656","my secret code");
    	Token token = service.generatedToken();
    }

For example with RxJava:

	private void generateMyToken() throws IOException,ServiceException
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

#### How Send a SMS :

The SMS object it is the representation object of your SMS have send.
It is composed of three fields (`String address`, `String senderAddress`,`String content`).
* the field address it is your number phone.
* the field senderAddress it is the number phone recipient 
* the field content it is content of message
The ResponseSMS object it is the representation of the JSon response returned by  orange smsAPI after having sent a message.
For send a sms Call method `sendSMS(SMS sms ,Bundle bundleHeader)` of object GenerateService.
* The Bundle parameter is used to store the data heading returned by the apiSMS.

For example:
<<<<<<< HEAD

    {
		Bundle bundleHeader = new Bundle();
		ResponseSMS responseSMS
		private void SendMySMS() throws IOException,ServiceException
		{
			// the address and the senderAddress must be written on this form.
			// the Iso code of the country concatenated to the number
			// +XXXxxxxxxxx
			String address = "+22500000000";
			String senderAddress = "+22511111111";
			String content = "my content"
			SMS sms = new SMS(address,senderAddress,content);
			GenerateService service = new GenerateService("5454656","mon code secret");
			Token token = service.generatedToken();
			responseSMS = service.sendSMS(sms,bundleHeader,token);
		}

		// recuperation of information of responseSMS
		String contentMessage = responseSMS.getOutBoundSMSMessageRequest().getOutboundSMSTextMessage();
		String senderAddress = responseSMS.getOutBoundSMSMessageRequest().getSenderAddress();
		String ResourceUrl = responseSMS.getResourceUrl();

		// recuperation of information of  headers
		String contentType = bundleHeader.getString(GenerateService.CONTENT_TYPE);
		String contentLength = bundleHeader.getString(GenerateService.CONTENT_LENGTH);
		String location = bundleHeader.getString(GenerateService.LOCATION);
		String date = bundleHeader.getString(GenerateService.DATE);
    	}


For example with RxJava:

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


Mark:In order to send SMS since our API, you must first of all buy a bundle SMS with Orange.In order to 
to facilitate the integration of API, you have the possibility to buy a bundle "starter".

#### How consulted numbers sms remainder :

The RemainderSMS object it is the representation object of JSon response returned by orange smsAPI having sent a request of consultation of remaining SMS.
At first you have to be interested in the object PartnerContracts. I invite you to glance on the source code.
For example :

    {
    	RemainderSMS remainderSMS;
    	private void numbersSMS() throws IOException,ServiceException
    	{
    		GenerateService service = new GenerateService("5454656","secret code");
    		Token token = service.generatedToken();  
    		remainderSMS = service.remainderSMS(token);
    	}
    	PartnerContracts partnerContracts =  remainderSMS.getPartnerContracts();
    }


For example with RxJava:

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

#### How consulted the statistics of use of the application :

The StatisticSMS object it is the representation object of JSon response returned by orange smsAPI having sent a request of consultation of statistics ussage.
At first you have to be interested in the object PartnerStatistics. I invite you to glance on the source code.
For example :

        {
        	StatisticSMS statistics;
        	private void consultedStatistics()  throws IOException,ServiceException
        	{
        		GenerateService service = new GenerateService("5454656","my secret code");
        		Token token = service.generatedToken();
        		statistics = service.statisticSMS(token);
        	}
        	PartnerStatistics partnerStatistics = statistics.getPartnerStatistics();
        }

For exampe with RxJava:

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

#### How consulted the historic purchase :

The HistoricPurchase object it is the representation object of JSon response returned by orange smsAPI having sent a request of consultation of purchase historic.
At first you have to be interested in the object PurchaseOrders. I invite you to glance on the source code.
For example :

    {
    	HistoricPurchase historic;
    	private void showHistoric()  throws IOException,ServiceException
    	{
    		GenerateService service = new GenerateService("5454656","secret code");
    		Token token = service.generatedToken();
    		historic = service.historicPurchase(token);
    	}
    	PurchaseOrders[] purchaseOrders = historic.getPurchaseOrders();
    }
 the method `getPurchaseOrders()` return a table of PurchaseOrders.

For example with RxJava :

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

#### Mark:
If you test the library in an emulator and that this error this raises : javax.net.ssl.SSLHandshakeException: org.bouncycastle.jce.exception.ExtCertPathValidatorException: Could not validate certificate signature
Take care to put your emulator per hour to indicate on your PC or MAC.

## Authors and Contributors
In 2015, Amani Christian Cyrille Alves (@DevAlves1993) founded AndroidOSms And of developer group Akanza.

## Contacts

* Gmail : [alvesamani@gmail.com] (mailto:alvesamani@gmail.com)
* Twitter [@cyrilleamani] (https://twitter.com/cyrilleamani)

## Contributing
If you would like to contribute code you can do so through GitHub by forking the repository and sending a pull request.
