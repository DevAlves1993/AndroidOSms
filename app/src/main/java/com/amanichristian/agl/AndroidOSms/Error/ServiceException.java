package com.amanichristian.agl.AndroidOSms.Error;

import java.io.IOException;

import retrofit2.Response;

/**
 * Created by AMANI CHRISTIAN CYRILLE on 25/11/2015.
 */
public class ServiceException extends Exception
{
    public static String messageError;
    public ServiceException(String detailMessage)
    {
        super(detailMessage);
    }
    public static void launchException(Response<?> response) throws IOException
    {
      messageError = response.errorBody().string();
    }
    public static void launchException(Throwable throwable)
    {
        messageError = throwable.getMessage();
    }
}
