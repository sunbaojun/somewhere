/**
 * Eya.com Inc.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.eya.core.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询条件基类模型
 * @author luolin
 *
 * @version $id:BaseExample.java,v 0.1 2015年12月15日 上午11:03:12 luolin Exp $
 */
public class BaseExample {

    /**排序方式*/
    protected String         orderByClause;

    /**是否distinct查询*/
    protected boolean        distinct;

    /**查询条件*/
    protected List<Criteria> oredCriteria;

    /**分页查询的起始索引*/
    protected int            start;

    /**分页查询的结束索引*/
    protected int            end;

    /**
     * Getter method for property <tt>start</tt>.
     * 
     * @return property value of start
     */
    public int getStart() {
        return start;
    }

    /**
     * Setter method for property <tt>start</tt>.
     * 
     * @param start value to be assigned to property start
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * Getter method for property <tt>end</tt>.
     * 
     * @return property value of end
     */
    public int getEnd() {
        return end;
    }

    /**
     * Setter method for property <tt>end</tt>.
     * 
     * @param end value to be assigned to property end
     */
    public void setEnd(int end) {
        this.end = end;
    }

    /**
     * 构造方法，实例化oredCriteria
     */
    public BaseExample() {

        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * Setter method for property <tt>orderByClause</tt>.
     * 
     * @param orderByClause value to be assigned to property orderByClause
     */
    public void setOrderByClause(String orderByClause) {

        this.orderByClause = orderByClause;
    }

    /**
     * Getter method for property <tt>orderByClause</tt>.
     * 
     * @return property value of orderByClause
     */
    public String getOrderByClause() {

        return orderByClause;
    }

    /**
     * Setter method for property <tt>distinct</tt>.
     * 
     * @param distinct value to be assigned to property distinct
     */
    public void setDistinct(boolean distinct) {

        this.distinct = distinct;
    }

    /**
     * Setter method for property <tt>distinct</tt>.
     * 
     * @param distinct value to be assigned to property distinct
     */
    public boolean isDistinct() {

        return distinct;
    }

    /**
     * Getter method for property <tt>oredCriteria</tt>.
     * 
     * @return property value of oredCriteria
     */
    public List<Criteria> getOredCriteria() {

        return oredCriteria;
    }

    /**
     * 添加查询条件
     * 
     * @param criteria 查询条件
     */
    public void or(Criteria criteria) {

        oredCriteria.add(criteria);
    }

    /**
     * or查询条件
     * 
     * @return 查询条件
     */
    public Criteria or() {

        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * 创建一个查询条件对象
     * 
     * @return 查询条件（Criteria）对象
     */
    public Criteria createCriteria() {

        Criteria criteria = null;
        if (oredCriteria.size() > 0) {
            criteria = oredCriteria.get(0);
        } else {
            criteria = createCriteriaInternal();
        }
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * 创建一个查询条件对象
     * 
     * @return 查询条件（Criteria）对象
     */
    protected Criteria createCriteriaInternal() {

        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清楚查询条件
     */
    public void clear() {

        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * 内部类
     * 
     * @author LUOLIN
     * @version $Id: BaseExample.java, v 0.1 Dec 19, 2013 2:50:53 PM LUOLIN Exp $
     */
    protected abstract static class GeneratedCriteria {

        /**查询条件*/
        protected List<Criterion> criteria;

        /**
         * 构造方法
         */
        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        /**
         * 是否有查询条件
         * 
         * @return 是否有查询查询条件
         */
        public boolean isValid() {

            return criteria.size() > 0;
        }

        /**
         * 获得所有查询条件
         * 
         * @return 所有查询条件集合
         */
        public List<Criterion> getAllCriteria() {

            return criteria;
        }

        /**
         * Getter method for property <tt>criteria</tt>.
         * 
         * @return property value of criteria
         */
        public List<Criterion> getCriteria() {

            return criteria;
        }

        /**
         * 添加查询条件
         * 
         * @param condition 条件
         */
        public void addCriterion(String condition) {

            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        /**
         * 添加查询条件
         * 
         * @param condition 条件
         * @param value 值
         * @param property 属性
         */
        public void addCriterion(String condition, Object value, String property) {

            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        /**
         * 添加查询条件
         * 
         * @param condition 条件
         * @param value1 值1
         * @param value2 值2
         * @param property 属性
         */
        public void addCriterion(String condition, Object value1, Object value2, String property) {

            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        /**
         * 设置字段的查询条件
         * 
         * @param field 字段
         * @param condition 条件
         * @param value 值
         * @return Criteria条件对象
         */
        public Criteria andFieldCondition(String field, String condition, String value) {

            addCriterion(field + condition + value);
            return (Criteria) this;
        }

        /**
         * 设置字段为空的查询条件
         * 
         * @param field 字段
         * @return Criteria条件对象
         */
        public Criteria andFieldIsNull(String field) {

            addCriterion(field + " is null");
            return (Criteria) this;
        }

        /**
         * 设置字段步为空的查询条件
         * 
         * @param field 字段
         * @return Criteria条件对象
         */
        public Criteria andFieldIsNotNull(String field) {

            addCriterion(field + " is not null");
            return (Criteria) this;
        }

        /**
         * 设置字段等于某个值的查询条件
         * 
         * @param field 字段
         * @param value 值
         * @return Criteria条件对象
         */
        public Criteria andFieldEqualTo(String field, String value) {

            addCriterion(field + " =", value, field);
            return (Criteria) this;
        }

        /**
         * 设置字段不等于某个值的查询条件
         * 
         * @param field 字段
         * @param value 值
         * @return Criteria条件对象
         */
        public Criteria andFieldNotEqualTo(String field, String value) {

            addCriterion(field + " <>", value, field);
            return (Criteria) this;
        }

        /**
         * 设置字段大于某个值的查询条件
         * 
         * @param field 字段
         * @param value 值
         * @return Criteria条件对象
         */
        public Criteria andFieldGreaterThan(String field, String value) {

            addCriterion(field + " >", value, field);
            return (Criteria) this;
        }

        /**
         * 设置字段大于或等于某个值的查询条件
         * 
         * @param field 字段
         * @param value 值
         * @return Criteria条件对象
         */
        public Criteria andFieldGreaterThanOrEqualTo(String field, String value) {

            addCriterion(field + " >=", value, field);
            return (Criteria) this;
        }

        /**
         * 设置字段小于某个值的查询条件
         * 
         * @param field 字段
         * @param value 值
         * @return Criteria条件对象
         */
        public Criteria andFieldLessThan(String field, String value) {

            addCriterion(field + " <", value, field);
            return (Criteria) this;
        }

        /**
         * 设置字段小于或等于某个值的查询条件
         * 
         * @param field 字段
         * @param value 值
         * @return Criteria条件对象
         */
        public Criteria andFieldLessThanOrEqualTo(String field, String value) {

            addCriterion(field + " <=", value, field);
            return (Criteria) this;
        }

        /**
         * 设置模糊查询条件(like)
         * 
         * @param field 字段
         * @param value 模糊查询的值
         * @return Criteria条件对象
         */
        public Criteria andFieldLike(String field, String value) {

            addCriterion(field + " like", "%" + value + "%", field);
            return (Criteria) this;
        }

        /**
         * 设置模糊查询条件（not like）
         * 
         * @param field 字段
         * @param value 模糊查询的值
         * @return Criteria条件对象
         */
        public Criteria andFieldNotLike(String field, String value) {

            addCriterion(field + " not like", "%" + value + "%", field);
            return (Criteria) this;
        }

        /**
         * 设置“in”查询条件
         * 
         * @param field 字段
         * @param values 值
         * @return Criteria条件对象
         */
        public Criteria andFieldIn(String field, List<String> values) {

            addCriterion(field + " in ", values, field);
            return (Criteria) this;
        }

        /**
         * 设置“not in”查询条件
         * 
         * @param field 条件
         * @param values 值
         * @return Criteria条件对象
         */
        public Criteria andFieldNotIn(String field, List<String> values) {

            addCriterion(field + " not in ", values, field);
            return (Criteria) this;
        }

        /**
         * 设置“between”查询条件
         * 
         * @param field 字段
         * @param value1 值1
         * @param value2 值2
         * @return Criteria条件对象
         */
        public Criteria andFieldBetween(String field, String value1, String value2) {

            addCriterion(field + " between", value1, value2, field);
            return (Criteria) this;
        }

        /**
         * 设置“not between”查询条件
         * 
         * @param field 字段
         * @param value1 值1
         * @param value2 值2
         * @return Criteria条件对象
         */
        public Criteria andFieldNotBetween(String field, String value1, String value2) {

            addCriterion(field + " not between", value1, value2, field);
            return (Criteria) this;
        }
    }

    /**
     * 静态内部类
     * 
     * @author LUOLIN
     * @version $Id: BaseExample.java, v 0.1 Dec 19, 2013 3:12:55 PM LUOLIN Exp $
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {

            super();
        }
    }

    /**
     * 静态内部类
     * 
     * @author LUOLIN
     * @version $Id: BaseExample.java, v 0.1 Dec 19, 2013 3:13:07 PM LUOLIN Exp $
     */
    public static class Criterion {

        /**条件*/
        private String  condition;

        /**第一个值*/
        private Object  value;

        /**第二个值*/
        private Object  secondValue;

        /**是否有值*/
        private boolean noValue;

        /**是否是单一值*/
        private boolean singleValue;

        /**是否是两个值*/
        private boolean betweenValue;

        /**是否有多个值*/
        private boolean listValue;

        /**类型*/
        private String  typeHandler;

        /**
         * Getter method for property <tt>condition</tt>.
         * 
         * @return property value of condition
         */
        public String getCondition() {
            return condition;
        }

        /**
         * Getter method for property <tt>value</tt>.
         * 
         * @return property value of value
         */
        public Object getValue() {
            return value;
        }

        /**
         * Getter method for property <tt>secondValue</tt>.
         * 
         * @return property value of secondValue
         */
        public Object getSecondValue() {
            return secondValue;
        }

        /**
         * Getter method for property <tt>noValue</tt>.
         * 
         * @return property value of noValue
         */
        public boolean isNoValue() {
            return noValue;
        }

        /**
         * Getter method for property <tt>singleValue</tt>.
         * 
         * @return property value of singleValue
         */
        public boolean isSingleValue() {
            return singleValue;
        }

        /**
         * Getter method for property <tt>betweenValue</tt>.
         * 
         * @return property value of betweenValue
         */
        public boolean isBetweenValue() {
            return betweenValue;
        }

        /**
         * Getter method for property <tt>listValue</tt>.
         * 
         * @return property value of listValue
         */
        public boolean isListValue() {
            return listValue;
        }

        /**
         * Getter method for property <tt>typeHandler</tt>.
         * 
         * @return property value of typeHandler
         */
        public String getTypeHandler() {
            return typeHandler;
        }

        /**
         * 构造方法
         * 
         * @param condition 条件
         */
        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        /**
         * 构造方法
         * 
         * @param condition 条件
         * @param value 值
         * @param typeHandler 类型
         */
        protected Criterion(String condition, Object value, String typeHandler) {

            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        /**
         * 构造方法
         * 
         * @param condition 条件
         * @param value 值
         */
        protected Criterion(String condition, Object value) {

            this(condition, value, null);
        }

        /**
         * 构造方法
         * 
         * @param condition 条件
         * @param value 第一个值
         * @param secondValue 第二个值
         * @param typeHandler 类型
         */
        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {

            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        /**
         * 构造方法
         * 
         * @param condition 条件
         * @param value 第一个值
         * @param secondValue 第二个值
         */
        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
