package com.amanichristian.agl.osmsandroid;

import java.util.ArrayList;

/**
 * Created by AMANI CHRISTIAN CYRILLE on 23/11/2015.
 */
public class PartnerStatistics
{
    private String partnerId;
    private Statistics[] statistics;

    public String getPartnerId()
    {
        return partnerId;
    }
    public  Statistics[] getStatistics()
    {
        return statistics;
    }

    public class Statistics
    {
        private String service;
        private ServiceStatistics[] serviceStatistics;

        public String getService()
        {
            return service;
        }
        public ServiceStatistics[]  getServiceStatistics()
        {
            return serviceStatistics;
        }

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
    }
}
