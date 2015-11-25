package com.amanichristian.agl.osmsandroid;

/**
 * Created by AMANI CHRISTIAN CYRILLE on 25/11/2015.
 */
public class ResponseSMS
{
    private SMSResponse outBoundSMSMessageRequest;
    private String resourceURL;
    public class SMSResponse
    {
        private String senderAddress;
        private SMSContent outboundSMSTextMessage;

        private class SMSContent
        {
            private String message;
        }
    }
}
