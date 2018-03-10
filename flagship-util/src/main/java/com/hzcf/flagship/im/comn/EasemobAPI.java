package com.hzcf.flagship.im.comn;

import org.springframework.stereotype.Component;

import io.swagger.client.ApiException;

/**
 * Created by easemob on 2017/3/16.
 */
@Component
public interface EasemobAPI {
    Object invokeEasemobAPI() throws ApiException;
}