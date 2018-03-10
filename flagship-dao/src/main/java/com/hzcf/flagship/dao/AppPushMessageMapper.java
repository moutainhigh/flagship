package com.hzcf.flagship.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hzcf.flagship.model.AppPushMessage;
import com.hzcf.flagship.model.AppPushMessageExample;

public interface AppPushMessageMapper {
    int countByExample(AppPushMessageExample example);

    int deleteByExample(AppPushMessageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AppPushMessage record);

    int insertSelective(AppPushMessage record);

    List<AppPushMessage> selectByExample(AppPushMessageExample example);

    AppPushMessage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AppPushMessage record, @Param("example") AppPushMessageExample example);

    int updateByExample(@Param("record") AppPushMessage record, @Param("example") AppPushMessageExample example);

    int updateByPrimaryKeySelective(AppPushMessage record);

    int updateByPrimaryKey(AppPushMessage record);
    
    /**
     * 根据用户Id查询用户推送记录
     * @param userId
     * @return
     */
   List< Map<String, String>> getMessagesByUserId(int userId);
   
   /**
    * 批量插入推送消息
    */
   void insertMessages(List<AppPushMessage> messages);
   
}