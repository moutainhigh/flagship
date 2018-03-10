package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.AppPageModel;
import com.hzcf.flagship.model.AppPageModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppPageModelMapper {
    int countByExample(AppPageModelExample example);

    int deleteByExample(AppPageModelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AppPageModel record);

    int insertSelective(AppPageModel record);

    List<AppPageModel> selectByExample(AppPageModelExample example);

    AppPageModel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AppPageModel record, @Param("example") AppPageModelExample example);

    int updateByExample(@Param("record") AppPageModel record, @Param("example") AppPageModelExample example);

    int updateByPrimaryKeySelective(AppPageModel record);

    int updateByPrimaryKey(AppPageModel record);
    
    /**
     * 查询风控执委页面的模块信息
     */
    List<String> getRiskZWModel();
    
    /**
     * 查询风控管理部页面模块信息
     */
    List<String> getRiskManagerModel();

	List<AppPageModel> findModelByPage(String page);

	void updatePage(AppPageModel appPageModel);
}