/**
 * Eya.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.eya.core.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.eya.core.enums.ErrorCode;
import com.eya.core.exception.BusinessException;
import com.eya.core.mapper.BaseMapper;
import com.eya.core.model.BaseModel;
import com.eya.core.vo.BaseExample;
import com.eya.core.vo.PageData;

/**
 * 服务类基类，包含数据库最基本的操作
 * @author luolin
 *
 * @version $id:BaseServiceImpl.java,v 0.1 2015年12月15日 上午11:02:05 luolin Exp $
 */
public abstract class BaseServiceImpl<T extends BaseModel> implements BaseService<T> {

    /** 
     * @see com.sunnysoft.core.service.BaseService#selectAllRecord()
     */
    @Override
    public List<T> selectAllRecord() {
        return baseMapper.selectByExample(null);
    }

    /**日志*/
    private static final Logger logger = Logger.getLogger(BaseServiceImpl.class);

    /**基本操作接口*/
    protected BaseMapper<T>     baseMapper;

    /**
     * 批量删除
     * @see com.sunnysoft.emr.service.BaseService#deleteByBatch(java.util.List)
     */
    @Override
    public void deleteByBatch(String ids) {
        if (StringUtils.isBlank(ids)) {
            logger.warn("BaseServiceImpl中的批量删除参数校验为空。");
            throw new BusinessException(ErrorCode.NULL_ARGUMENT, "BaseServiceImpl中的批量删除参数校验为空。");
        }
        if (logger.isInfoEnabled()) {
            logger.info("批量删除,方法：deleteByBatch; 参数：" + ids);
        }
        List<String> list = Arrays.asList(ids.split(","));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        baseMapper.deleteByBatch(map);
    }

    /**
     * 根据编号删除
     * @see com.sunnysoft.emr.service.BaseService#deleteById(java.util.Map)
     */
    @Override
    public void deleteByPrimaryKey(String id) {
        if (StringUtils.isBlank(id)) {
            throw new BusinessException(ErrorCode.NULL_ARGUMENT, "BaseServiceImpl中的根据编号删除参数校验为空。");
        }
        if (logger.isInfoEnabled()) {
            logger.info("根据编号删除,方法：deleteByPrimaryKey; 参数：" + id);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        baseMapper.deleteByPrimaryKey(map);
    }

    /**
     * 新增一个对象
     * @see com.sunnysoft.emr.service.BaseService#insert(java.lang.Object)
     */
    @Override
    public void insert(T record) {
        baseMapper.insert(record);
    }

    /**
     * 根据编号查询
     * @see com.sunnysoft.emr.service.BaseService#selectById(java.lang.Object)
     */
    @Override
    public T selectByPrimaryKey(Object id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    /** 
     * @see com.sunnysoft.core.service.BaseService#selectByPaging(com.sunnysoft.core.vo.PageData)
     */
    @Override
    public void selectByPaging(PageData<T> pageData) {
        BaseExample example = pageData.getExample();
        example.setStart(pageData.getFirst());
        example.setEnd(pageData.getEnd());
        if (StringUtils.isNotBlank(pageData.getOrderField())) {
            example.setOrderByClause(pageData.getOrderByClause());
        }

        //根据条件查询数据
        List<T> list = baseMapper.selectByPaging(example);

        //根据查询条件返回共计行数
        pageData.setTotalCount(baseMapper.selectPagingCount(example));
        pageData.setResult(list);
    }

    /**
     * 分页查询
     * @see com.sunnysoft.emr.service.BaseService#selectByPaging(com.sunnysoft.emr.core.web.PageData)
     */
    @Override
    public void selectByPaging(PageData<T> pageData, Map<String, String> param) {
        BaseExample example = pageData.getExample();
        example.setStart(pageData.getFirst());
        example.setEnd(pageData.getEnd());
        if (StringUtils.isNotBlank(pageData.getOrderField())) {
            example.setOrderByClause(pageData.getOrderByClause());
        }
        //设置查询条件
        BaseExample.Criteria criteria = example.createCriteria();
        //criteria.andFieldEqualTo("is_delete", DeleteStatus.DELETED.getCode());
        if (MapUtils.isNotEmpty(param)) {
            for (Map.Entry<String, String> map : param.entrySet()) {

                criteria.andFieldEqualTo(map.getKey(), map.getValue());

            }
        }

        //根据条件查询数据
        List<T> list = baseMapper.selectByPaging(example);

        //根据查询条件返回共计行数
        pageData.setTotalCount(baseMapper.selectPagingCount(example));
        pageData.setResult(list);

    }

    /**
     * 根据编号更新对象
     * @see com.sunnysoft.emr.service.BaseService#updateById(java.lang.Object)
     */
    @Override
    public void updateByPrimaryKey(T record) {
        baseMapper.updateByPrimaryKey(record);
    }

    /**
     * 查询条件进行查询
     * @see com.sunnysoft.emr.service.BaseService#selectByExample(java.util.Map)
     */
    public List<T> selectByExample(Map<String, String> param) {
        BaseExample example = new BaseExample();

        //设置查询条件

        BaseExample.Criteria criteria = example.createCriteria();
        if (MapUtils.isNotEmpty(param)) {
            for (Map.Entry<String, String> map : param.entrySet()) {
                criteria.andFieldEqualTo(map.getKey(), map.getValue());
            }
        }
        return baseMapper.selectByExample(example);
    }

    /**
     * Setter method for property <tt>base</tt>.
     * 
     * @param hospCode value to be base to property base
     */
    public abstract void setBaseMapper();
}
