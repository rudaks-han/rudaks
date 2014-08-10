package kr.co.rudaks.web.mapper;

import kr.co.rudaks.web.bean.GeoipCountryForm;

public interface GeoipCountryMapper
{   
    public GeoipCountryForm selectCountryByIp(String ip);
}
