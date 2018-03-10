package com.hzcf.flagship.service;

import java.util.List;
import java.util.Map;

import com.hzcf.flagship.model.AppHelpContent;
import com.hzcf.flagship.util.PageModel;

/**
 *
 * 类名：HelpService.java</dt>
 * 功能描述：app用户帮助 interface
 * 创建时间： 2017年8月1日 下午10:51:02</dd>
 * 创建人：tieguowei</dd>
 */
public interface HelpService {

	PageModel findAllByPage(Map<String, Object> paramsCondition);

	List<?> getAllSorts();

	void doAddHelpContent(AppHelpContent appHelpContent);

	Map<String, Object> findByContentID(Integer id);

	void doUpdateHelpContent(AppHelpContent appHelpContent);

	long getCountBySortId(AppHelpContent appHelpContent);

	void deleteById(AppHelpContent appHelpContent);
}
