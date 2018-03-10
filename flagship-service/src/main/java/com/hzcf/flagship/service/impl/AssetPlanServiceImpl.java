package com.hzcf.flagship.service.impl;

import com.hzcf.flagship.dao.AssetBusinessMapper;
import com.hzcf.flagship.dao.AssetPlanMapper;
import com.hzcf.flagship.model.AssetPlan;
import com.hzcf.flagship.service.AssetPlanService;
import com.hzcf.flagship.util.ImportUtil;
import com.hzcf.flagship.util.PageModel;
import com.hzcf.flagship.util.RegExpUtil;
import com.hzcf.flagship.util.StringUtil;
import com.hzcf.flagship.util.log.LogDefault;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;


@Service("assetPlanService")
public class AssetPlanServiceImpl implements AssetPlanService {

    private Logger logger = LogDefault.getLogger(getClass());


    @Autowired
    private AssetPlanMapper assetPlanMapper;

    @Autowired
    private AssetBusinessMapper assetBusinessMapper;


    @Override
    public int deleteByPrimaryKey(Long id) {
        return assetPlanMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(AssetPlan assetPlan) {
        return assetPlanMapper.insert(assetPlan);
    }

    @Override
    public int insertSelective(AssetPlan assetPlan) {
        return assetPlanMapper.insertSelective(assetPlan);
    }

    @Override
    public AssetPlan selectByPrimaryKey(Long id) {
        return assetPlanMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(AssetPlan assetPlan) {
        return assetPlanMapper.updateByPrimaryKeySelective(assetPlan);
    }

    @Override
    public int updateByPrimaryKey(AssetPlan assetPlan) {
        return assetPlanMapper.updateByPrimaryKey(assetPlan);
    }

    @Override
    public PageModel queryAssetPlanList(int pageNo, int pageSize, String orgNo, String businessNo, String year) {
        Map<String, Object> param = new HashMap<>();
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        param.put("startIndex", pageModel.getStartIndex());
        param.put("pageSize", pageSize);
        param.put("orgNo", orgNo);
        param.put("businessNo", businessNo);
        param.put("year", year);

        List<Map<String, Object>> data = assetPlanMapper.selectAssetPlanList(param);
        Long totalRecords = assetPlanMapper.selectAssetPlanListCount(param);
        pageModel.setList(data);
        pageModel.setTotalRecords(totalRecords);
        return pageModel;
    }

    @Override
    public List<?> selectAssetBusiness() {
        return assetBusinessMapper.selectAssetBusiness();
    }

    @Override
    public List<?> selectUsefulYear() {
        return assetPlanMapper.selectUsefulYear();
    }

    @Override
    public int insertAssetPlanBatch(List<List<Object>> list, Integer creator) {
        if (list == null || list.isEmpty()){
            return 0;
        }

        List<AssetPlan> deleteList = new ArrayList<AssetPlan>();
        List<AssetPlan> resultList = new ArrayList<AssetPlan>();
        Date date = new Date();

        for (int i = 1; i < list.size(); i++) {
            List<Object> line = list.get(i);
            String businessNo = String.valueOf(line.get(0));
            String businessName = String.valueOf(line.get(1));
            String orgNo = String.valueOf(line.get(2));
            String orgName = String.valueOf(line.get(3));
            String year = String.valueOf(line.get(4));
            if (StringUtil.isNotEmpty(year) && year.contains(".")) {
                year = year.substring(0, year.indexOf('.'));
            }
            AssetPlan deleteAP = new AssetPlan();
            deleteAP.setOrgNo(orgNo);
            deleteAP.setBusinessNo(businessNo);
            deleteAP.setYear(year);
            deleteList.add(deleteAP);

            try {
                if (StringUtil.isAnyBlank(businessNo, businessName, orgNo, orgName, year)) {
                    throw new IllegalArgumentException("该行有必填数据为空！");
                }
                if (!RegExpUtil.isInteger(year) || year.length() != 4){
                    throw new IllegalArgumentException("年份必须为整数且长度为4");
                }
                for (int j = 5; j <= 17; j++) {

                    AssetPlan assetPlan = new AssetPlan();

                    assetPlan.setBusinessNo(businessNo);
                    assetPlan.setBusinessName(businessName);
                    assetPlan.setOrgNo(orgNo);
                    assetPlan.setOrgName(orgName);
                    assetPlan.setYear(year);

                    String planValue = String.valueOf(line.get(j));
                    if (!StringUtil.isBlank(planValue)) {
                        if (RegExpUtil.isMoney(planValue)) {
                            assetPlan.setPlanValue(new BigDecimal(planValue));
                        } else {
                            throw new IllegalArgumentException("输入格式不为金钱！");
                        }

                        String month = String.valueOf(list.get(0).get(j));
                        if (StringUtil.isNotEmpty(month)){
                            assetPlan.setMonth(month);
                        }
                        assetPlan.setCreator(creator);
                        assetPlan.setCreateTime(date);
                        resultList.add(assetPlan);
                    }
                }
            } catch (Exception e) {
                logger.error(e);
                if (0 != i) {
                    return i + 1;
                }
            }
        }

        // 如果要修改数据，需要先删除后insert。
        if (!resultList.isEmpty() && !deleteList.isEmpty()) {
            logger.debug("1、批量删除业绩目标assetPlan数据-开始-总size:" + deleteList.size());
            int deleted = assetPlanMapper.deleteAssetPlanBatch(deleteList);
            logger.debug("1、批量删除业绩目标assetPlan数据-成功-总size:" + deleted);

            List<List<?>> splitList = ImportUtil.splitList(resultList, 100);
            for (List<?> list2 : splitList) {
                logger.debug("2、批量插入业绩目标assetPlan数据-开始-总size:" + list2.size());
                int inserted = assetPlanMapper.insertAssetPlanBatch(list2);
                logger.debug("2、批量插入业绩目标assetPlan数据-成功-size:" + inserted);
            }
        }
        return 0;
    }

}
