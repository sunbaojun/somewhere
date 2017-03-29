/**
 * Eya.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.eya.core.service;

import java.util.List;
import java.util.Map;

import com.eya.core.model.BaseModel;
import com.eya.core.vo.PageData;

/**
 * 业务逻辑服务接口父接口
 * @author luolin
 *
 * @version $id:BaseService.java,v 0.1 2015年12月15日 上午11:02:40 luolin Exp $
 */
public interface BaseService<T extends BaseModel> {
    /**
     * 根据编号查询
     * 
     * @param id 编号
     * @return 返回实体
     */
    public T selectByPrimaryKey(Object id);

    /**
     * 插入一个对象
     * 
     * @param record 实体对象
     * @return  影响的行数
     */
    public void insert(T record);

    /**
     * 根据编号更新一个对象
     * 
     * @param record 实体对象
     * @return 影响的行数
     */
    public void updateByPrimaryKey(T record);

    /**
     * 根据编号删除对象
     * 
     * @param id  需要删除的对象的编号
     * @return 影响的行数
     */
    public void deleteByPrimaryKey(String id);

    /**
     * 批量删除
     * 
     * @param list  需要删除的ids
     * @return 影响的行数
     */
    public void deleteByBatch(String ids);

    /**
     * 分页查询
     * 
     * @param pageData  页面组件
     * @param param  查询条件
     * 
     */
    void selectByPaging(PageData<T> pageData, Map<String, String> param);

    /**
     * 
     * 分页查询
     * @param pageData  页面组件
     */
    void selectByPaging(PageData<T> pageData);

    /**
     * 查询所有数据
     * 
     * @return  返回查询集合
     */
    List<T> selectAllRecord();

    /**
     * 
     * 查询条件进行查询
     * @param 查询条件集合
     * @return  返回查询结构集合
     */
    List<T> selectByExample(Map<String, String> param);
}
