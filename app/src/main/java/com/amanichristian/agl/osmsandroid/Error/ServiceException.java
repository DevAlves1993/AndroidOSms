package com.amanichristian.agl.osmsandroid.Error;

import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.lang.annotation.Annotation;

import retrofit.Converter;
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
    public static void launchException(Retrofit retrofit, Response<?> response) throws IOException
    {
        Converter<ResponseBody,Errors> errorConverter = retrofit.responseConverter(Errors.class,new Annotation[0]);
        Errors error = errorConverter.convert(response.errorBody());
        messageError = error.error;
    }
}
