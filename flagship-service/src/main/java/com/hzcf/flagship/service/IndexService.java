package com.hzcf.flagship.service;

import java.util.List;

import com.hzcf.flagship.model.Index;

/**
 *<dl>
 *<dt>类名：IndexService.java</dt>
 *<dd>描述: 阈值设置</dd> 
 *<dd>创建时间： 2017年5月25日 上午11:46:00</dd>
 *<dd>创建人： TieGuowei </dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年5月25日 上午11:46:00    TieGuowei       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
public interface IndexService {

	List<Index> selectByExample(Object object);

	void updateByCode(Index index);

}
