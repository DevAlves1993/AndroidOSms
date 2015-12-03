package com.amanichristian.agl.AndroidOSms.Error;

/**
 * Created by AMANI CHRISTIAN CYRILLE on 29/11/2015.
 */
public class ErrorTwo
{
    private String code;
    private String message;
    private String description;

    public ErrorTwo(String code, String message, String description)
    {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    @Override
    public String toString()
    {
        return "code : "+code+" message : "+message+" description : "+description;
    }
}
