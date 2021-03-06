package com.amanichristian.agl.AndroidOSms;

/**
 * Created by AMANI CHRISTIAN CYRILLE on 24/11/2015.
 */
public class SMS
{
    private SMSMessage outBoundSMSMessageRequest;

    public SMS(String address , String senderAddress,String content)
    {
        outBoundSMSMessageRequest = new SMSMessage(address,senderAddress,content);
    }

    public SMSMessage getOutBoundSMSMessageRequest()
    {
        return outBoundSMSMessageRequest;
    }

    public void setOutBoundSMSMessageRequest(SMSMessage outBoundSMSMessageRequest)
    {
        this.outBoundSMSMessageRequest = outBoundSMSMessageRequest;
    }


    public class SMSMessage
    {
        private String address;
        private String senderAddress;
        private SMSContent message;

        public SMSMessage(String address,String senderAddress,String content)
        {
            this.address ="tel:"+address;
            this.senderAddress ="tel:"+ senderAddress;
            this.message = new SMSContent(content);
        }

        public String getMessage()
        {
            return message.getMessage();
        }

        public void setAddress(String address)
        {
            this.address = address;
        }

        public String getSenderAddress()
        {
            return senderAddress;
        }

        public void setSenderAddress(String senderAddress)
        {
            this.senderAddress = senderAddress;
        }

        public void setMessage(String content)
        {
            message.setMessage(content);
        }

        public String getAddress()
        {
            return address;
        }

        private class SMSContent
        {
            private String message;
            public SMSContent(String message)
            {
                this.message = message;
            }

            public void setMessage(String message)
            {
                this.message = message;
            }

            public String getMessage()
            {
                return this.message;
            }
        }
    }
}
