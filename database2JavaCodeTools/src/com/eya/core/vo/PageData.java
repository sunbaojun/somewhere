/**
 * Eya.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.eya.core.vo;

import java.util.List;

import com.eya.core.enums.MatchType;
import com.eya.core.model.BaseModel;
import com.eya.core.vo.BaseExample.Criteria;

/**
 * 分页组件
 * @author luolin
 *
 * @version $id:PageData.java,v 0.1 2015年12月15日 上午11:03:36 luolin Exp $
 */
public class PageData<Entity extends BaseModel> {

    /**页面数据列表*/
    private List<Entity> result;

    /**当前页码*/
    private Integer      pageNum        = 1;

    /**每页显示条数*/
    private Integer      numPerPage     = 20;

    /**查询到的总条数*/
    private Integer      totalCount     = 1;

    /**排序字段*/
    private String       orderField     = "";

    /**排序方式*/
    private String       orderDirection = "asc";

    /**封装查询条件,MyBatis*/
    private BaseExample  example;

    /**组装查询条件*/
    private Criteria     criteria;

    /**
     * 构造方法
     */
    public PageData() {

        example = new BaseExample();
    }

    /**
     * Setter method for property <tt>example</tt>.
     * 
     * @param example value to be assigned to property example
     */
    public List<Entity> getResult() {

        return result;
    }

    /**
     * Setter method for property <tt>result</tt>.
     * 
     * @param result value to be assigned to property result
     */
    public void setResult(List<Entity> result) {

        this.result = result;
    }

    /**
     * Getter method for property <tt>example</tt>.
     * 
     * @return example value of example
     */
    public BaseExample getExample() {

        return example;
    }

    /**
     * Setter method for property <tt>example</tt>.
     * 
     * @param example value to be assigned to property example
     */
    public void setExample(BaseExample example) {

        this.example = example;
    }

    /**
     * Getter method for property <tt>orderField</tt>.
     * 
     * @return orderField value of orderField
     */
    public String getOrderField() {

        return orderField;
    }

    /**
     * Setter method for property <tt>orderField</tt>.
     * 
     * @param orderField value to be assigned to property orderField
     */
    public void setOrderField(String orderField) {

        this.orderField = orderField;
    }

    /**
     * Getter method for property <tt>orderDirection</tt>.
     * 
     * @return orderDirection value of orderDirection
     */
    public String getOrderDirection() {

        return orderDirection;
    }

    /**
     * Setter method for property <tt>icdCode</tt>.
     * 
     * @param icdCode value to be assigned to property icdCode
     */
    public void setOrderDirection(String orderDirection) {

        this.orderDirection = orderDirection;
    }

    /**
     * Getter method for property <tt>orderField</tt>.
     * 
     * @return orderField value of orderField
     */
    public String getOrderByClause() {

        return orderField + " " + orderDirection;
    }

    /**
     * Getter method for property <tt>pageNum</tt>.
     * 
     * @return pageNum value of pageNum
     */
    public Integer getPageNum() {

        if (pageNum < 1) {
            return 1;
        }

        return pageNum;
    }

    /**
     * Setter method for property <tt>pageNum</tt>.
     * 
     * @param pageNum value to be assigned to property pageNum
     */
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;

    }

    /**
     * Getter method for property <tt>numPerPage</tt>.
     * 
     * @return numPerPage value of numPerPage
     */
    public Integer getNumPerPage() {

        return numPerPage;
    }

    /**
     * Setter method for property <tt>numPerPage</tt>.
     * 
     * @param numPerPage value to be assigned to property numPerPage
     */
    public void setNumPerPage(Integer numPerPage) {

        this.numPerPage = numPerPage == null || numPerPage < 2 ? Integer.valueOf(15) : numPerPage;
    }

    /**
     * Getter method for property <tt>totalCount</tt>.
     * 
     * @return totalCount value of totalCount
     */
    public Integer getTotalCount() {

        return totalCount;
    }

    /**
     * Setter method for property <tt>totalCount</tt>.
     * 
     * @param totalCount value to be assigned to property totalCount
     */
    public void setTotalCount(Integer totalCount) {

        this.totalCount = totalCount;
    }

    /**
     * 获取当前页面上第一条记录对应数据库中的索引
     * 
     * @return 当前页面上第一条记录对应数据库中的索引
     */
    public int getFirst() {

        return (getPageNum() - 1) * numPerPage;
    }

    /**
     * 获得当前页上最后一条数据对应数据库中的索引
     * 
     * @return 当前页上最后一条数据对应数据库中的索引
     */
    public int getEnd() {
        return getFirst() + numPerPage;
    }

    /**
     * 手动添加查询条件
     * 
     * @param field 查询的字段
     * @param matchType 匹配类型
     * @param value 查询过滤的值
     */
    public void addCondition(String field, MatchType matchType, String... value) {

        if (example.getOredCriteria().size() == 0) {
            this.criteria = example.createCriteria();
        } else {
            this.criteria = example.getOredCriteria().get(0);
        }
        if (matchType == MatchType.EQ) {
            criteria.andFieldEqualTo(field, value[0]);
        } else if (matchType == MatchType.GE) {
            criteria.andFieldGreaterThanOrEqualTo(field, value[0]);
        } else if (matchType == MatchType.GT) {
            criteria.andFieldGreaterThan(field, value[0]);
        } else if (matchType == MatchType.LE) {
            criteria.andFieldLessThanOrEqualTo(field, value[0]);
        } else if (matchType == MatchType.LT) {
            criteria.andFieldLessThan(field, value[0]);
        } else if (matchType == MatchType.LIKE) {
            criteria.andFieldLike(field, value[0]);
        } else if (matchType == MatchType.BETWEEN) {
            criteria.andFieldBetween(field, value[0], value[1]);
        }
    }

    /**
     * Getter method for property <tt>totalPages</tt>.
     * 
     * @return property value of totalPages
     */
    public Integer getTotalPages() {
        if (getTotalCount() % getNumPerPage() == 0) {
            return getTotalCount() / getNumPerPage();
        }
        return getTotalCount() / getNumPerPage() + 1;

    }
}
