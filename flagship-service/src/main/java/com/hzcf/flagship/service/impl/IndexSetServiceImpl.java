package com.hzcf.flagship.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.IndexMapper;
import com.hzcf.flagship.model.Index;
import com.hzcf.flagship.service.IndexService;

@Service
public class IndexSetServiceImpl implements IndexService {

	@Autowired
	private IndexMapper indexMapper;

	@Override
	public List<Index> selectByExample(Object object) {
		List<Index> selectByExample = indexMapper.selectByExample(null);
		return selectByExample;
	}

	@Override
	public void updateByCode(Index index) {
		indexMapper.updateByCode(index);
	}




}
