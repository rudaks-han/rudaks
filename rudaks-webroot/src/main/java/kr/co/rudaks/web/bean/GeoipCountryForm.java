package kr.co.rudaks.web.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class GeoipCountryForm implements Serializable
{
    String beginIp;
    String endIp;
    double beginIpNum;
    double endIpNum;
    String countryCode;
    String countryName;
}
