package com.amanichristian.agl.osmsandroid.Others;

/**
 * Created by AMANI CHRISTIAN CYRILLE on 27/11/2015.
 */
public class ServiceStatistics
{
    private String country;
    private  CountyStatistics[] countryStatistics;

    public String getCountry()
    {
        return country;
    }
    public CountyStatistics[] getCountyStatistics()
    {
        return countryStatistics;
    }

    public class CountyStatistics
    {
        String applicationId;
        String usage;

        public String getApplicationId()
        {
            return this.applicationId;
        }
        public String getUsage()
        {
            return this.usage;
        }
    }
}
