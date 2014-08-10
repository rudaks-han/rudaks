package kr.co.rudaks.web.service;

import kr.co.rudaks.web.bean.GeoipCountryForm;

public interface IGeoipCountryService
{
    GeoipCountryForm selectCountryByIp(String ip);
}
