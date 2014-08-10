package kr.co.rudaks.web.bean;

import lombok.Data;

@Data
public class GeoipCountryForm
{
    String beginIp;
    String endIp;
    double beginIpNum;
    double endIpNum;
    String countryCode;
    String countryName;
}
