package com.handtours.service.dao.back;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author Administrator
 *
 *         CreatedTime  2016/8/8 0008 10:49
 */
public interface ProjectionForAddress {
    String getMobile();

    @Value("#{target.address.country}")
    String getC();

    @Value("#{target.address.street}")
    String getS();

    @Value("#{(target.address.street == null || target.address.street.empty) ? null : '******'}")
    String getStreetEncrypted();

    @Value("国家:#{target.address.country},省份:#{target.address.state},街道:#{target.address.street}")
    String getFullAddress();
}
