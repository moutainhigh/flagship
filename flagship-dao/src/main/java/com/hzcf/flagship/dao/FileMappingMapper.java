package com.hzcf.flagship.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hzcf.flagship.model.FileMapping;
import com.hzcf.flagship.model.FileMappingExample;

public interface FileMappingMapper {
    int countByExample(FileMappingExample example);

    int deleteByExample(FileMappingExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FileMapping record);

    int insertSelective(FileMapping record);

    List<FileMapping> selectByExample(FileMappingExample example);

    FileMapping selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FileMapping record, @Param("example") FileMappingExample example);

    int updateByExample(@Param("record") FileMapping record, @Param("example") FileMappingExample example);

    int updateByPrimaryKeySelective(FileMapping record);

    int updateByPrimaryKey(FileMapping record);
    /**
	    * @Description:根据 info查询 code 
	    * @param @param info
	    * @param @return    
	    * @return FileMapping
	    * @throws
     */
	FileMapping findCodeByInfo(String info);
	 /**
	    * @Description:根据 code查询 info 
	    * @param @param code
	    * @param @return    
	    * @return FileMapping
	    * @throws
     */
	FileMapping findInfoAndAddressByCode(String code);
}