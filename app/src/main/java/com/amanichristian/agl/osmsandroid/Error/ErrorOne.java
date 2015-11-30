package com.amanichristian.agl.osmsandroid.Error;

/**
 * Created by AMANI CHRISTIAN CYRILLE on 25/11/2015.
 */
public class ErrorOne
{
    private  String error;
    private  String error_description;

    public ErrorOne(String error, String error_message)
    {
        this.error = error;
        this.error_description = error_message;
    }

    @Override
    public String toString()
    {
        return "error : "+error+" error description : "+error_description;
    }
}
