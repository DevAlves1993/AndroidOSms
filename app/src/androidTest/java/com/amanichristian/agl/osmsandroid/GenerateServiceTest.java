package com.amanichristian.agl.osmsandroid;

import junit.framework.TestCase;

/**
 * Created by AMANI CHRISTIAN CYRILLE on 26/11/2015.
 */
public class GenerateServiceTest extends TestCase
{

    public void testGeneratedToken() throws Exception
    {
        GenerateService service = new GenerateService("5854556","code secret ");
        Token token = service.generatedToken();
    }

    public void testSendSMS() throws Exception
    {

    }

    public void testRemainderSMS() throws Exception
    {

    }

    public void testStatisticSMS() throws Exception
    {

    }

    public void testHistoricPurchase() throws Exception
    {

    }
}