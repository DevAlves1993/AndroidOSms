# OsmsAndroid
OSM Android is a simple library that allows you to consume  quickly orange api [SMS API] (https://www.orangepartner.com/SMS-CI-API) and making you productive in the development of your mobile apps requiring a called on the [orange api] (https://www.orangepartner.com/SMS-CI-API).
It is based on the library [retrofit] (https://github.com/square/retrofit)


## Usage

#### How generate a Token :

The Token object it is the representation object of the JSon response returned by the orange smsAPI.
For generate a Token object, create a object GenerateService.Then call the method `generatedToken()` of 
object GenerateService.
For Example:

    private void generateMyToken()
    {
      GenerateService service = new GenerateService("5454656","mon code secret");
      Token token = service.generatedToken();  
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

    {
    	Bundle bundleHeader = new Bundle();
    	private void SendMySMS()
    	{
    		// the address and the senderAddress must be written on this form.
			// the Iso code of the country concatenated to the number
			// +XXXxxxxxxxx
    		String address = "+22500000000";
    		String senderAddress = "+22511111111";
    		SMS sms = new SMS(address,senderAddress,content);
    		Bundle bubdleHeader = new Bundle();
    		GenerateService service = new GenerateService("5454656","mon code secret");
    		Token token = service.generatedToken();
    		ResponseSMS responseSMS = service.sendSMS(sms,bundleHeader,Token);
    	}
    	
    	// recuperation of information of responseSMS
    	String contentMessage = responseSMS.getOutBoundSMSMessageRequest().getOutboundSMSTextMessage();
    	String senderAddress = responseSMS.getOutBoundSMSMessageRequest().getSenderAddress();
    	String ResourceUrl = response.getRescourceUrl();
    	
    	// recuperation of information of  headers
    	String contentType = bundleHeader.get(GenerateService.CONTENT_TYPE);
    	String contentLength = bundleHeader.get(GenerateService.CONTENT_LENGTH);
    	String location = bundleHeader.get(GenerateService.LOCATION);
    	String date = bundleHeader.get(GenerateService.DATE);
    }

Mark:In order to send SMS since our API, you must first of all buy a bundle SMS with Orange.In order to 
to facilitate the integration of API, you have the possiblity to buy a bundle "statter".

#### How consulted numbers sms remainder :

The RemainderSMS object it is the representation object of JSon response returned by orange smsAPI having sent a request of consultation of remaining SMS.
At first you have to be interested in the object PartnerContracts. I invite you to glance on the source code.
For example :

    {
    	RemainderSMS remainderSMS;
    	private void numbersSMS()
    	{
    		GenerateService service = new GenerateService("5454656","mon code secret");
    		Token token = service.generatedToken();  
    		remainderSMS = service.remainderSMS(token);
    	}
    	PartnerContracts partnerContracts =  remainderSMS.getPartnerContracts();
    }


#### How consulted the statistics of use of the application :

The StatisticSMS object it is the representation object of JSon response returned by orange smsAPI having sent a request of consultation of statistics ussage.
At first you have to be interested in the object PartnerStatistics. I invite you to glance on the source code.
For example :

        {
        	StatisticSMS statistics;
        	private void consultedStatistics()
        	{
        		GenerateService service = new GenerateService("5454656","mon code secret");
        		Token token = service.generatedToken();
        		statistics = service.statisticSMS(token);
        	}
        	PartnerStatistics partnerStatistics = statistics.getPartnerStatistics();
        }

#### How consulted the historic purchase :

The HistoricPurchase object it is the representation object of JSon response returned by orange smsAPI having sent a request of consultation of purchase historic.
At first you have to be interested in the object PurchaseOrders. I invite you to glance on the source code.
For example :

    {
    	HistoricPurchase historic;
    	private void showHistoric()
    	{
    		GenerateService service = new GenerateService("5454656","mon code secret");
    		Token token = service.generatedToken();
    		historic = service.historicPurchase(token);
    	}
    	PurchaseOrders[] purchaseOrders = historic.getPurchaseOrders();
    }
 the method `getPurchaseOrders()` return a table of PurchaseOrders.

#### Mark:
If you test the library in an emulator and that this error this raises : javax.net.ssl.SSLHandshakeException: org.bouncycastle.jce.exception.ExtCertPathValidatorException: Could not validate certificate signature
Take care to put your emulator per hour to indicate on your PC or MAC.

## Authors and Contributors
In 2015, Amani Christian Cyrille Alves (@DevAlves1993) founded OSmsAndroid.

##Contacts

* Gmail : [alvesamani@gmail.com] (mailto:alvesamani@gmail.com)
* Twitter [@cyrilleamani] (https://twitter.com/cyrilleamani)

## Contributing
If you would like to contribute code you can do so through GitHub by forking the repository and sending a pull request.
