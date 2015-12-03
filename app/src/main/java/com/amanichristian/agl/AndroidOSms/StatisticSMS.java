package com.amanichristian.agl.AndroidOSms;

import com.amanichristian.agl.AndroidOSms.Others.PartnerStatistics;

/**
 * Created by AMANI CHRISTIAN CYRILLE on 25/11/2015.
 */
public class StatisticSMS
{
    private PartnerStatistics partnerStatistics;

    public StatisticSMS(PartnerStatistics partnerStatistics)
    {
        this.partnerStatistics = partnerStatistics;
    }

    public PartnerStatistics getPartnerStatistics()
    {
        return partnerStatistics;
    }

}
