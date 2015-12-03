package com.amanichristian.agl.AndroidOSms.Error;

import java.io.IOException;

import retrofit.Response;
import retrofit.Retrofit;

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
    public static void launchException(Retrofit retrofit, Response<?> response,boolean isErrorOne) throws IOException
    {
      messageError = response.errorBody().string();
    }
}
