package com.amanichristian.agl.osmsandroid.Others;

import com.amanichristian.agl.osmsandroid.Others.ServiceStatistics;

/**
 * Created by AMANI CHRISTIAN CYRILLE on 23/11/2015.
 */
public class PartnerStatistics
{
    private String partnerId;
    private Statistics[] statistics;

    public PartnerStatistics(String partnerId, Statistics[] statistics)
    {
        this.partnerId = partnerId;
        this.statistics = statistics;
    }

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

        public Statistics(String service, ServiceStatistics[] serviceStatistics)
        {
            this.service = service;
            this.serviceStatistics = serviceStatistics;
        }

        public String getService()
        {
            return service;
        }
        public ServiceStatistics[]  getServiceStatistics()
        {
            return serviceStatistics;
        }


    }
}
