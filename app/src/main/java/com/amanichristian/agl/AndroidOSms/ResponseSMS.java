package com.amanichristian.agl.AndroidOSms;

/**
 * Created by AMANI CHRISTIAN CYRILLE on 25/11/2015.
 */
public class ResponseSMS
{
    private SMSResponse outBoundSMSMessageRequest;
    private String resourceURL;

    public ResponseSMS(SMSResponse outBoundSMSMessageRequest, String resourceURL)
    {
        this.outBoundSMSMessageRequest = outBoundSMSMessageRequest;
        this.resourceURL = resourceURL;
    }

    public SMSResponse getOutBoundSMSMessageRequest()
    {
        return outBoundSMSMessageRequest;
    }

    public String getResourceURL()
    {
        return resourceURL;
    }

    public class SMSResponse
    {
        private String senderAddress;
        private SMSContent outboundSMSTextMessage;

        public String getSenderAddress()
        {
            return senderAddress;
        }

        public String getOutboundSMSTextMessage()
        {
            return outboundSMSTextMessage.getMessage();
        }

        private class SMSContent
        {
            private String message;

            public String getMessage()
            {
                return message;
            }
        }
    }
}
