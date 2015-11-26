# OsmsAndroid
OSM Android is a simple library that allows you to consume rather quickly orange api [SMS API] (https://www.orangepartner.com/SMS-CI-API) and making you productive in the development of your mobile apps requiring a called on the [orange api] (https://www.orangepartner.com/SMS-CI-API).
It is based on the library [retrofit] (https://github.com/square/retrofit)


### Usage
How generate a Token

    private void generateMyToken()
    {
      GenerateService service = new GenerateService("5454656","mon code secret");
      Token token = service.generatedToken();  
    }
  
How Send a SMS

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

 
### Authors and Contributors
In 2015, Amani Christian (@DevAlves1993) founded OSmsAndroid.

### Contributing
If you would like to contribute code you can do so through GitHub by forking the repository and sending a pull request.
