# OsmsAndroid
OSM Android is a simple library that allows you to consume  quickly orange api [SMS API] (https://www.orangepartner.com/SMS-CI-API) and making you productive in the development of your mobile apps requiring a called on the [orange api] (https://www.orangepartner.com/SMS-CI-API).
It is based on the library [retrofit] (https://github.com/square/retrofit)


## Usage
How generate a Token :
The Token object it is the representation object of the JSon answer returned by the orange smsAPI.
For generate a Token object, create a object GenerateService.Then call the method `generatedToken` of 
object GenerateService.
For Exemple:

    private void generateMyToken()
    {
      GenerateService service = new GenerateService("5454656","mon code secret");
      Token token = service.generatedToken();  
    }
  
How Send a SMS :
The SMS object it is the representation object of your SMS have send.
It is composed of three fields (`String address`, `String senderAddress`,`String content`).
* the field address it is your number phone.
* the field senderAddress it is the number phone recipient 
* the field content it is content of message
The Response object it is 

    private void SendMySMS(String address , String senderAddress,String content)
    {
      SMS sms = new SMS(address,senderAddress,content);
      Bundle bubdleHeader = new Bundle();
      GenerateService service = new GenerateService("5454656","mon code secret");
      Token token = service.generatedToken();  
      ResponseSMS responseSMS = service.sendSMS(sms,bundleHeader);
    }

How consulted numbers sms remainder
  
    private void numbersSMS()
    {
      GenerateService service = new GenerateService("5454656","mon code secret");
      Token token = service.generatedToken();  
      RemainderSMS remainderSMS = service.remainderSMS();
    }


How consulted the statistics of use of the application 
	
	private void consultedStatistics()
	{
		GenerateService service = new GenerateService("5454656","mon code secret");
		Token token = service.generatedToken();
		StatisticSMS statistics = service.statisticSMS();
	}

How consulted the historic purchase
	{
		GenerateService service = new GenerateService("5454656","mon code secret");
		Token token = service.generatedToken();
		HistoricPurchase historic = service.historicPurchase();
	}
 
### Authors and Contributors
In 2015, Amani Christian (@DevAlves1993) founded OSmsAndroid.

### Contributing
If you would like to contribute code you can do so through GitHub by forking the repository and sending a pull request.