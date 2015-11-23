package com.amanichristian.agl.osmsandroid;

/**
 * Created by AMANI CHRISTIAN CYRILLE on 23/11/2015.
 */

import retrofit.http.POST;
import retrofit.http.FormUrlEncoded;

public interface TokenService
{
    String END_POINT = "https://api.orange.com";

    @FormUrlEncoded @POST("/oauth/v2/token")
    String getToken();
}
