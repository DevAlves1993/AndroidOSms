package com.amanichristian.agl.AndroidOSms.Others;

/**
 * Created by AMANI CHRISTIAN CYRILLE on 27/11/2015.
 */
public class ServiceStatistics
{
    private String country;
    private  CountryStatistics[] countryStatistics;

    public ServiceStatistics(String country, CountryStatistics[] countryStatistics)
    {
        this.country = country;
        this.countryStatistics = countryStatistics;
    }

    public String getCountry()
    {
        return country;
    }
    public CountryStatistics[] getCountyStatistics()
    {
        return countryStatistics;
    }

    public class CountryStatistics
    {
        private  String applicationId;
        private  String usage;

        public CountryStatistics(String applicationId, String usage)
        {
            this.applicationId = applicationId;
            this.usage = usage;
        }

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
