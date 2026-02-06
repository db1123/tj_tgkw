package fun.server.model;

import java.util.ArrayList;
import java.util.List;

public class TCityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * t_city
     * @mbg.generated
     */
    public TCityExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * 设置排序字段
     * t_city
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * t_city
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * t_city
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * t_city
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * t_city
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * t_city
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * t_city
     * 创建一个新的或者查询条件
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * 创建一个查询条件
     * t_city
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * 内部构建查询条件对象
     * t_city
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * t_city
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TCity
     * GeneratedCriteria
     * 数据库表：t_city
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andFKeyIsNull() {
            addCriterion("f_key is null");
            return (Criteria) this;
        }

        public Criteria andFKeyIsNotNull() {
            addCriterion("f_key is not null");
            return (Criteria) this;
        }

        public Criteria andFKeyEqualTo(Long value) {
            addCriterion("f_key =", value, "fKey");
            return (Criteria) this;
        }

        public Criteria andFKeyNotEqualTo(Long value) {
            addCriterion("f_key <>", value, "fKey");
            return (Criteria) this;
        }

        public Criteria andFKeyGreaterThan(Long value) {
            addCriterion("f_key >", value, "fKey");
            return (Criteria) this;
        }

        public Criteria andFKeyGreaterThanOrEqualTo(Long value) {
            addCriterion("f_key >=", value, "fKey");
            return (Criteria) this;
        }

        public Criteria andFKeyLessThan(Long value) {
            addCriterion("f_key <", value, "fKey");
            return (Criteria) this;
        }

        public Criteria andFKeyLessThanOrEqualTo(Long value) {
            addCriterion("f_key <=", value, "fKey");
            return (Criteria) this;
        }

        public Criteria andFKeyIn(List<Long> values) {
            addCriterion("f_key in", values, "fKey");
            return (Criteria) this;
        }

        public Criteria andFKeyNotIn(List<Long> values) {
            addCriterion("f_key not in", values, "fKey");
            return (Criteria) this;
        }

        public Criteria andFKeyBetween(Long value1, Long value2) {
            addCriterion("f_key between", value1, value2, "fKey");
            return (Criteria) this;
        }

        public Criteria andFKeyNotBetween(Long value1, Long value2) {
            addCriterion("f_key not between", value1, value2, "fKey");
            return (Criteria) this;
        }

        public Criteria andFCityIsNull() {
            addCriterion("f_city is null");
            return (Criteria) this;
        }

        public Criteria andFCityIsNotNull() {
            addCriterion("f_city is not null");
            return (Criteria) this;
        }

        public Criteria andFCityEqualTo(String value) {
            addCriterion("f_city =", value, "fCity");
            return (Criteria) this;
        }

        public Criteria andFCityNotEqualTo(String value) {
            addCriterion("f_city <>", value, "fCity");
            return (Criteria) this;
        }

        public Criteria andFCityGreaterThan(String value) {
            addCriterion("f_city >", value, "fCity");
            return (Criteria) this;
        }

        public Criteria andFCityGreaterThanOrEqualTo(String value) {
            addCriterion("f_city >=", value, "fCity");
            return (Criteria) this;
        }

        public Criteria andFCityLessThan(String value) {
            addCriterion("f_city <", value, "fCity");
            return (Criteria) this;
        }

        public Criteria andFCityLessThanOrEqualTo(String value) {
            addCriterion("f_city <=", value, "fCity");
            return (Criteria) this;
        }

        public Criteria andFCityLike(String value) {
            addCriterion("f_city like", value, "fCity");
            return (Criteria) this;
        }

        public Criteria andFCityNotLike(String value) {
            addCriterion("f_city not like", value, "fCity");
            return (Criteria) this;
        }

        public Criteria andFCityIn(List<String> values) {
            addCriterion("f_city in", values, "fCity");
            return (Criteria) this;
        }

        public Criteria andFCityNotIn(List<String> values) {
            addCriterion("f_city not in", values, "fCity");
            return (Criteria) this;
        }

        public Criteria andFCityBetween(String value1, String value2) {
            addCriterion("f_city between", value1, value2, "fCity");
            return (Criteria) this;
        }

        public Criteria andFCityNotBetween(String value1, String value2) {
            addCriterion("f_city not between", value1, value2, "fCity");
            return (Criteria) this;
        }

        public Criteria andFProvinceIdIsNull() {
            addCriterion("f_province_id is null");
            return (Criteria) this;
        }

        public Criteria andFProvinceIdIsNotNull() {
            addCriterion("f_province_id is not null");
            return (Criteria) this;
        }

        public Criteria andFProvinceIdEqualTo(Long value) {
            addCriterion("f_province_id =", value, "fProvinceId");
            return (Criteria) this;
        }

        public Criteria andFProvinceIdNotEqualTo(Long value) {
            addCriterion("f_province_id <>", value, "fProvinceId");
            return (Criteria) this;
        }

        public Criteria andFProvinceIdGreaterThan(Long value) {
            addCriterion("f_province_id >", value, "fProvinceId");
            return (Criteria) this;
        }

        public Criteria andFProvinceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("f_province_id >=", value, "fProvinceId");
            return (Criteria) this;
        }

        public Criteria andFProvinceIdLessThan(Long value) {
            addCriterion("f_province_id <", value, "fProvinceId");
            return (Criteria) this;
        }

        public Criteria andFProvinceIdLessThanOrEqualTo(Long value) {
            addCriterion("f_province_id <=", value, "fProvinceId");
            return (Criteria) this;
        }

        public Criteria andFProvinceIdIn(List<Long> values) {
            addCriterion("f_province_id in", values, "fProvinceId");
            return (Criteria) this;
        }

        public Criteria andFProvinceIdNotIn(List<Long> values) {
            addCriterion("f_province_id not in", values, "fProvinceId");
            return (Criteria) this;
        }

        public Criteria andFProvinceIdBetween(Long value1, Long value2) {
            addCriterion("f_province_id between", value1, value2, "fProvinceId");
            return (Criteria) this;
        }

        public Criteria andFProvinceIdNotBetween(Long value1, Long value2) {
            addCriterion("f_province_id not between", value1, value2, "fProvinceId");
            return (Criteria) this;
        }
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：t_city
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * TCity
     * Criterion
     * 数据库表：t_city
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

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

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}