package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.AppUserSort;
import com.hzcf.flagship.model.AppUserSortExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface AppUserSortMapper {
    int countByExample(AppUserSortExample example);

    int deleteByExample(AppUserSortExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppUserSort record);

    int insertSelective(AppUserSort record);

    List<AppUserSort> selectByExample(AppUserSortExample example);

    AppUserSort selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppUserSort record, @Param("example") AppUserSortExample example);

    int updateByExample(@Param("record") AppUserSort record, @Param("example") AppUserSortExample example);

    int updateByPrimaryKeySelective(AppUserSort record);

    int updateByPrimaryKey(AppUserSort record);

	List<?> getSorts();
	/**
	 * 根据id查询该用户联系人所属的类别
	 * @param telephone
	 * @return
	 */
	List<Map<String, Object>> selectByUserId(String userId);

}