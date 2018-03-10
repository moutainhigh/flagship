package com.hzcf.flagship.service.impl;

import com.hzcf.flagship.dao.AssetPersonMapper;
import com.hzcf.flagship.model.AssetPerson;
import com.hzcf.flagship.model.AssetPersonExample;
import com.hzcf.flagship.service.AssetPersonService;
import com.hzcf.flagship.util.*;
import com.hzcf.flagship.util.log.LogDefault;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("assetPersonService")
public class AssetPersonServiceImpl implements AssetPersonService {

    private Logger logger = LogDefault.getLogger(getClass());

    @Autowired
    private AssetPersonMapper assetPersonMapper;

    @Override
    public int countByExample(AssetPersonExample example) {
        return assetPersonMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(AssetPersonExample example) {
        return assetPersonMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return assetPersonMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(AssetPerson record) {
        return assetPersonMapper.insert(record);
    }

    @Override
    public int insertSelective(AssetPerson record) {
        return assetPersonMapper.insertSelective(record);
    }

    @Override
    public List<AssetPerson> selectByExample(AssetPersonExample example) {
        return assetPersonMapper.selectByExample(example);
    }

    @Override
    public AssetPerson selectByPrimaryKey(Long id) {
        return assetPersonMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(AssetPerson record, AssetPersonExample example) {
        return assetPersonMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(AssetPerson record, AssetPersonExample example) {
        return assetPersonMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(AssetPerson record) {
        return assetPersonMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AssetPerson record) {
        return assetPersonMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageModel queryAssetPersonList(int pageNo, int pageSize, String depNo, String name, String mobile) {
        Map<String, Object> param = new HashMap<>();
        PageModel pageModel = new PageModel();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        param.put("startIndex", pageModel.getStartIndex());
        param.put("pageSize", pageSize);
        param.put("depNo", depNo);
        param.put("name", StringUtil.trim(name));
        param.put("mobile", mobile);

        // 脱敏
        List<AssetPerson> data = assetPersonMapper.queryAssetPersonList(param);
        for (AssetPerson assetPerson : data) {
            assetPerson.setMobile(SensitiveInfoUtils.mobilePhone(assetPerson.getMobile()));
        }
        Long totalRecords = assetPersonMapper.queryAssetPersonListCount(param);
        pageModel.setList(data);
        pageModel.setTotalRecords(totalRecords);
        return pageModel;
    }

    @Override
    public int insertassetPersonBatch(List<List<Object>> list, Integer creator) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        List<AssetPerson> resultList = new ArrayList<AssetPerson>();
        List<String> deleteList = new ArrayList<String>();
        Date date = new Date();

        for (int i = 1; i < list.size(); i++) {
            List<Object> line = list.get(i);
            try {
                String depNo = String.valueOf(line.get(0));
                String depName = String.valueOf(line.get(1));
                String name = String.valueOf(line.get(2));
                String districtNo = String.valueOf(line.get(3));
                String districtName = String.valueOf(line.get(4));
                String salesdepNo = String.valueOf(line.get(5));
                String salesdepName = String.valueOf(line.get(6));
                String teamName = String.valueOf(line.get(7));
                String subTeamName = String.valueOf(line.get(8));
                String personNo = String.valueOf(line.get(9));
                if (StringUtil.isNotEmpty(personNo) && personNo.contains(".")) {
                    personNo = personNo.substring(0, personNo.indexOf('.'));
                }
                String mobile = String.valueOf(line.get(10));
                if (StringUtil.isNotEmpty(mobile) && mobile.contains(".")) {
                    mobile = mobile.substring(0, mobile.indexOf('.'));
                }
                Date dimissionTime = DateTimeUtil.convertAsDate(String.valueOf(line.get(11)));
                if (StringUtil.isAnyBlank(depNo, depName, name, mobile, personNo)) {
                    throw new IllegalArgumentException("该行有必填数据为空！");
                }
                if (!RegExpUtil.isMobile(mobile)){
                    throw new IllegalArgumentException(name + "的手机号不符合规则");
                }
                if (!RegExpUtil.isInteger(personNo) || personNo.length()>20){
                    throw new IllegalArgumentException("员工编号只能由数字组成且不能超过20个字符");
                }

                AssetPerson assetPerson = new AssetPerson();
                assetPerson.setDepNo(depNo);
                assetPerson.setDepName(depName);
                assetPerson.setName(name);
                assetPerson.setDistrictNo(districtNo);
                assetPerson.setDistrictName(districtName);
                assetPerson.setSalesdepNo(salesdepNo);
                assetPerson.setSalesdepName(salesdepName);
                assetPerson.setTeamName(teamName);
                assetPerson.setSubTeamName(subTeamName);
                assetPerson.setPersonNo(personNo);
                assetPerson.setMobile(mobile);
                assetPerson.setDimissionTime(dimissionTime);

                assetPerson.setCaretor(creator);
                assetPerson.setCreateTime(date);
                resultList.add(assetPerson);
                deleteList.add(personNo);

            } catch (Exception e) {
                logger.error(e);
                if (0 != i) {
                    return i + 1;
                }
            }
        }

        // 如果要修改数据，需要先删除后insert。
        if (!deleteList.isEmpty()) {
            List<List<?>> splitList = ImportUtil.splitList(deleteList, 100);
                for (List<?> list2 : splitList) {
                logger.debug("1、批量删除花名册assetPerson数据-开始-总size:" + deleteList.size());
                int deleted = assetPersonMapper.deleteAssetPersonBatch(list2);
                logger.debug("1、批量删除花名册assetPerson数据-成功-总size:" + deleted);
            }
        }

        if (!resultList.isEmpty()) {
            List<List<?>> splitList = ImportUtil.splitList(resultList, 100);
            for (List<?> list2 : splitList) {
                logger.debug("2、批量插入花名册assetPerson数据-开始-总size:" + list2.size());
                int inserted = assetPersonMapper.insertAssetPersonBatch(list2);
                logger.debug("2、批量插入花名册assetPerson数据-成功-size:" + inserted);
            }
        }
        return 0;
    }
}
