package kr.co.rudaks.web.service;

import kr.co.rudaks.web.bean.GeoipCountryForm;
import kr.co.rudaks.web.mapper.GeoipCountryMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class GeoipCountryServiceImpl implements IGeoipCountryService
{
	@Autowired
	GeoipCountryMapper geoipCountryMapper;

	@Cacheable("countryCache")
	public GeoipCountryForm selectCountryByIp(String ip)
	{
	    return geoipCountryMapper.selectCountryByIp(ip);
	}
}
