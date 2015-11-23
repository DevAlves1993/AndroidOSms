package com.amanichristian.agl.osmsandroid;

/**
 * Created by AMANI CHRISTIAN CYRILLE on 23/11/2015.
 */
import android.util.Base64;

public final class GenerateService
{
    private String id;
    private String secretCode;
    private String codeEncoded;
    public GenerateService(String id, String secretCode)
    {
        this.id = id;
        this.secretCode = secretCode;
        String id_secretCode = this.id + this.secretCode;
        this.codeEncoded = Base64.encodeToString(id_secretCode.getBytes(),Base64.DEFAULT);
    }
}
