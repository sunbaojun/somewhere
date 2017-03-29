/**
 * Eya.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.eya.core.mapper;

import java.util.List;
import java.util.Map;

import com.eya.core.model.BaseModel;
import com.eya.core.vo.BaseExample;

/**
 * 持久层接口基类
 * @author luolin
 *
 * @version $id:BaseMapper.java,v 0.1 2015年12月15日 上午11:02:15 luolin Exp $
 */
public interface BaseMapper<Entity extends BaseModel> {

    /**
     * 根据example进行分页查询
     * 
     * @param example 查询的条件
     * @return 分页查询出来的记录集合
     */
    List<Entity> selectByPaging(BaseExample example);

    /**
     * 根据example统计行数
     * 
     * @param example 查询的条件
     * @return  统计行数
     */
    int selectPagingCount(BaseExample example);

    /**
     * 新增记录
     * 
     * @param record 记录
     */
    void insert(Entity record);

    /**
     * 根据主键查询记录
     * 
     * @param pk 主键
     * @return  查询到的记录
     */
    Entity selectByPrimaryKey(Object pk);

    /**
     * 根据主键更新记录
     * 
     * @param record 记录
     */
    void updateByPrimaryKey(Entity record);

    /**
     * 根据主键删除
     * 
     * @param pk 主键
     */
    void deleteByPrimaryKey(Map<String, Object> param);

    /**
     * 批量删除
     * 
     * @param list 需要删除的ids集合
     */
    void deleteByBatch(Map<String, Object> map);

    /**
     * 根据example进行查询
     * 
     * @param example 查询条件
     * @return 满足条件的记录集合
     */
    List<Entity> selectByExample(BaseExample example);

}
