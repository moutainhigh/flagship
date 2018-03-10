package com.hzcf.flagship.service.impl;

import com.hzcf.flagship.dao.AppPageModelMapper;
import com.hzcf.flagship.dao.RiskOrgDataMapper;
import com.hzcf.flagship.dao.RiskSubcenterMapper;
import com.hzcf.flagship.model.AppPageModel;
import com.hzcf.flagship.model.RiskOrgData;
import com.hzcf.flagship.model.RiskOrgDataExample;
import com.hzcf.flagship.model.RiskSubcenter;
import com.hzcf.flagship.model.RiskSubcenterExample;
import com.hzcf.flagship.service.RiskSubcenterService;
import com.hzcf.flagship.util.PageModel;
import com.hzcf.flagship.util.StringUtil;
import com.hzcf.flagship.util.UUIDUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RiskSubcenterServiceImpl implements RiskSubcenterService {

    private final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private RiskSubcenterMapper riskSubcenterMapper;
    @Autowired
    private RiskOrgDataMapper riskOrgDataMapper;
    @Autowired
    private AppPageModelMapper appPageModelMapper;
    @Override
    public int countByExample(RiskSubcenterExample example) {
        return riskSubcenterMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(RiskSubcenterExample example) {
        return riskSubcenterMapper.deleteByExample(example);
    }

    /**
     * 通过主键(id)删除数据(实际上是根据id修改status = '1')
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(Long id) {
        return riskSubcenterMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RiskSubcenter record) {
        return riskSubcenterMapper.insert(record);
    }

    @Override
    public int insertSelective(RiskSubcenter record, Integer creator) {
        record.setCreator(creator);
        record.setCreateTime(new Date());
        record.setSubcenterNo(UUIDUtil.getUUID());
        return riskSubcenterMapper.insertSelective(record);
    }

    @Override
    public List<RiskSubcenter> selectByExample(RiskSubcenterExample example) {
        return riskSubcenterMapper.selectByExample(example);
    }

    @Override
    public RiskSubcenter selectByPrimaryKey(Long id) {
        if (id == null || id == 0) {
            throw new IllegalArgumentException("参数id不能为null或0！当前传入id为" + id);
        }
        RiskSubcenter riskSubcenter = riskSubcenterMapper.selectByPrimaryKey(id);
        if (riskSubcenter == null) {
            throw new NullPointerException("selectByPrimaryKey-数据库中查询结果为null。查询id为：" + id);
        }
        return riskSubcenter;
    }

    @Override
    public int updateByExampleSelective(RiskSubcenter record, RiskSubcenterExample example) {
        return riskSubcenterMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(RiskSubcenter record, RiskSubcenterExample example) {
        return riskSubcenterMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(RiskSubcenter record) {
        return riskSubcenterMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RiskSubcenter record) {
        return riskSubcenterMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageModel subcenterList(int pageNo, int pageSize) {
        Map<String, Object> param = new HashMap<>();
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        param.put("startIndex", pageModel.getStartIndex());
        param.put("pageSize", pageSize);

        List<RiskSubcenter> data = riskSubcenterMapper.subcenterList(param);
        int totalRecords = riskSubcenterMapper.subcenterListCount(param).size();
        pageModel.setList(data);
        pageModel.setTotalRecords(Long.valueOf(totalRecords));
        return pageModel;
    }

    /**
     * 查询最新的分中心数据
     * @Name:selectForDistinct
     * @Author:YuanGuoLei
     * @CreateTime:2017年11月27日下午4:30:19
     * @return
     */
	@Override
	public List<RiskSubcenter> selectForDistinct() {
		return riskSubcenterMapper.selectForDistinct();
	}

    /**
     * 通过SubcenterNo修改数据(实际上是原数据不变,并新增一条数据。)<br />
     * @param riskSubcenter {@link RiskSubcenter}
     * @param creator 创建人id/当前登录用户id
     * @return
     */
    @Override
    public int updateBySubcenterNoSelective(RiskSubcenter riskSubcenter, Integer creator) {
        riskSubcenter.setCreator(creator);
        riskSubcenter.setCreateTime(new Date());
        return riskSubcenterMapper.insertSelective(riskSubcenter);
    }

    @Override
    public String selectManagementOrgDataBySubcenterNo(String subcenterNo) {
        if (StringUtil.isBlank(subcenterNo)) {
            logger.debug("subcenterNo参数is blank,返回空字符串");
            return "";
        }
        List<RiskOrgData> orgDatas = riskOrgDataMapper.selectOrgDataBySubcenterNo(subcenterNo);
        if (orgDatas == null || orgDatas.size() == 0) {
            logger.debug("根据" + subcenterNo + "查询出的orgDatas == null || orgDatas.size == 0;返回空字符串");
            return "";
        }
        StringBuilder orgDataNames = new StringBuilder();
        for (RiskOrgData orgData : orgDatas) {
            orgDataNames.append(orgData.getOrgShortName());
            orgDataNames.append(",");
        }

        if (orgDataNames.length() == 0) {
            return "";
        }
        return orgDataNames.substring(0, orgDataNames.length() - 1);
    }

    @Override
    public int delete(String subcenterNo, String name, Integer creator) {
        if (StringUtil.isBlank(subcenterNo)) {
            logger.debug("subcenterNo参数is blank,返回空字符串");
            throw new IllegalArgumentException("参数subcenterNo can not be blank！当前传入subcenterNo为" + subcenterNo);
        }

        RiskSubcenter riskSubcenter = new RiskSubcenter();
        riskSubcenter.setSubcenterNo(subcenterNo);
        riskSubcenter.setName(name);
        riskSubcenter.setCreateTime(new Date());
        riskSubcenter.setCreator(creator);
        riskSubcenter.setStatus(RiskSubcenter.STATUS_DELETED);
        return riskSubcenterMapper.insertSelective(riskSubcenter);
    }
    
    @Override
	public List<AppPageModel> findModelByPage(String page) {
		return appPageModelMapper.findModelByPage(page);
	}

	@Override
	public void updatePage(String data,Integer id) {
		String[] split = data.split(":");
		//id
		String ids = split[0];
		String[] splitIds = ids.split(",");
		//是否回显
		String visibles = split[1];
		String[] splitVisibles = visibles.split(",");

		//顺序
		String sorts = split[2];
		String[] splitSorts = sorts.split(",");
		for (int i = 0; i < splitIds.length; i++) {
			AppPageModel appPageModel = new AppPageModel();
			appPageModel.setId(Long.valueOf(splitIds[i]));
			appPageModel.setVisible(splitVisibles[i]);
			appPageModel.setSorting(Integer.valueOf(splitSorts[i]));
			appPageModel.setUpdateTime(new Date());
			appPageModel.setUpdateUser(id);
			appPageModelMapper.updatePage(appPageModel);
		}
	}
}
