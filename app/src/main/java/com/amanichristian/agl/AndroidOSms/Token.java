package com.amanichristian.agl.AndroidOSms;

/**
 * Created by AMANI CHRISTIAN CYRILLE on 23/11/2015.
 */
public class Token
{
    private String token_type;
    private String access_token;
    private String expires_in;

    public Token(String token_type, String access_token, String expires_in)
    {
        this.token_type = token_type;
        this.access_token = access_token;
        this.expires_in = expires_in;
    }

    public String getToken_type()
    {
        return token_type;
    }
    public String getAccess_token()
    {
        return access_token;
    }
    public String getExpires_in()
    {
        return expires_in;
    }
}
