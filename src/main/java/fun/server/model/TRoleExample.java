package fun.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TRoleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * t_role
     * @mbg.generated
     */
    public TRoleExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * 设置排序字段
     * t_role
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * t_role
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * t_role
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * t_role
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * t_role
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * t_role
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * t_role
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
     * t_role
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
     * t_role
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * t_role
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TRole
     * GeneratedCriteria
     * 数据库表：t_role
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
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

        public Criteria andFKeyIdIsNull() {
            addCriterion("f_key_id is null");
            return (Criteria) this;
        }

        public Criteria andFKeyIdIsNotNull() {
            addCriterion("f_key_id is not null");
            return (Criteria) this;
        }

        public Criteria andFKeyIdEqualTo(Long value) {
            addCriterion("f_key_id =", value, "fKeyId");
            return (Criteria) this;
        }

        public Criteria andFKeyIdNotEqualTo(Long value) {
            addCriterion("f_key_id <>", value, "fKeyId");
            return (Criteria) this;
        }

        public Criteria andFKeyIdGreaterThan(Long value) {
            addCriterion("f_key_id >", value, "fKeyId");
            return (Criteria) this;
        }

        public Criteria andFKeyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("f_key_id >=", value, "fKeyId");
            return (Criteria) this;
        }

        public Criteria andFKeyIdLessThan(Long value) {
            addCriterion("f_key_id <", value, "fKeyId");
            return (Criteria) this;
        }

        public Criteria andFKeyIdLessThanOrEqualTo(Long value) {
            addCriterion("f_key_id <=", value, "fKeyId");
            return (Criteria) this;
        }

        public Criteria andFKeyIdIn(List<Long> values) {
            addCriterion("f_key_id in", values, "fKeyId");
            return (Criteria) this;
        }

        public Criteria andFKeyIdNotIn(List<Long> values) {
            addCriterion("f_key_id not in", values, "fKeyId");
            return (Criteria) this;
        }

        public Criteria andFKeyIdBetween(Long value1, Long value2) {
            addCriterion("f_key_id between", value1, value2, "fKeyId");
            return (Criteria) this;
        }

        public Criteria andFKeyIdNotBetween(Long value1, Long value2) {
            addCriterion("f_key_id not between", value1, value2, "fKeyId");
            return (Criteria) this;
        }

        public Criteria andFNameIsNull() {
            addCriterion("f_name is null");
            return (Criteria) this;
        }

        public Criteria andFNameIsNotNull() {
            addCriterion("f_name is not null");
            return (Criteria) this;
        }

        public Criteria andFNameEqualTo(String value) {
            addCriterion("f_name =", value, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameNotEqualTo(String value) {
            addCriterion("f_name <>", value, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameGreaterThan(String value) {
            addCriterion("f_name >", value, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameGreaterThanOrEqualTo(String value) {
            addCriterion("f_name >=", value, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameLessThan(String value) {
            addCriterion("f_name <", value, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameLessThanOrEqualTo(String value) {
            addCriterion("f_name <=", value, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameLike(String value) {
            addCriterion("f_name like", value, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameNotLike(String value) {
            addCriterion("f_name not like", value, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameIn(List<String> values) {
            addCriterion("f_name in", values, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameNotIn(List<String> values) {
            addCriterion("f_name not in", values, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameBetween(String value1, String value2) {
            addCriterion("f_name between", value1, value2, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameNotBetween(String value1, String value2) {
            addCriterion("f_name not between", value1, value2, "fName");
            return (Criteria) this;
        }

        public Criteria andFStateIsNull() {
            addCriterion("f_state is null");
            return (Criteria) this;
        }

        public Criteria andFStateIsNotNull() {
            addCriterion("f_state is not null");
            return (Criteria) this;
        }

        public Criteria andFStateEqualTo(Integer value) {
            addCriterion("f_state =", value, "fState");
            return (Criteria) this;
        }

        public Criteria andFStateNotEqualTo(Integer value) {
            addCriterion("f_state <>", value, "fState");
            return (Criteria) this;
        }

        public Criteria andFStateGreaterThan(Integer value) {
            addCriterion("f_state >", value, "fState");
            return (Criteria) this;
        }

        public Criteria andFStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("f_state >=", value, "fState");
            return (Criteria) this;
        }

        public Criteria andFStateLessThan(Integer value) {
            addCriterion("f_state <", value, "fState");
            return (Criteria) this;
        }

        public Criteria andFStateLessThanOrEqualTo(Integer value) {
            addCriterion("f_state <=", value, "fState");
            return (Criteria) this;
        }

        public Criteria andFStateIn(List<Integer> values) {
            addCriterion("f_state in", values, "fState");
            return (Criteria) this;
        }

        public Criteria andFStateNotIn(List<Integer> values) {
            addCriterion("f_state not in", values, "fState");
            return (Criteria) this;
        }

        public Criteria andFStateBetween(Integer value1, Integer value2) {
            addCriterion("f_state between", value1, value2, "fState");
            return (Criteria) this;
        }

        public Criteria andFStateNotBetween(Integer value1, Integer value2) {
            addCriterion("f_state not between", value1, value2, "fState");
            return (Criteria) this;
        }

        public Criteria andFCIdIsNull() {
            addCriterion("f_c_id is null");
            return (Criteria) this;
        }

        public Criteria andFCIdIsNotNull() {
            addCriterion("f_c_id is not null");
            return (Criteria) this;
        }

        public Criteria andFCIdEqualTo(Long value) {
            addCriterion("f_c_id =", value, "fCId");
            return (Criteria) this;
        }

        public Criteria andFCIdNotEqualTo(Long value) {
            addCriterion("f_c_id <>", value, "fCId");
            return (Criteria) this;
        }

        public Criteria andFCIdGreaterThan(Long value) {
            addCriterion("f_c_id >", value, "fCId");
            return (Criteria) this;
        }

        public Criteria andFCIdGreaterThanOrEqualTo(Long value) {
            addCriterion("f_c_id >=", value, "fCId");
            return (Criteria) this;
        }

        public Criteria andFCIdLessThan(Long value) {
            addCriterion("f_c_id <", value, "fCId");
            return (Criteria) this;
        }

        public Criteria andFCIdLessThanOrEqualTo(Long value) {
            addCriterion("f_c_id <=", value, "fCId");
            return (Criteria) this;
        }

        public Criteria andFCIdIn(List<Long> values) {
            addCriterion("f_c_id in", values, "fCId");
            return (Criteria) this;
        }

        public Criteria andFCIdNotIn(List<Long> values) {
            addCriterion("f_c_id not in", values, "fCId");
            return (Criteria) this;
        }

        public Criteria andFCIdBetween(Long value1, Long value2) {
            addCriterion("f_c_id between", value1, value2, "fCId");
            return (Criteria) this;
        }

        public Criteria andFCIdNotBetween(Long value1, Long value2) {
            addCriterion("f_c_id not between", value1, value2, "fCId");
            return (Criteria) this;
        }

        public Criteria andFUIdIsNull() {
            addCriterion("f_u_id is null");
            return (Criteria) this;
        }

        public Criteria andFUIdIsNotNull() {
            addCriterion("f_u_id is not null");
            return (Criteria) this;
        }

        public Criteria andFUIdEqualTo(Long value) {
            addCriterion("f_u_id =", value, "fUId");
            return (Criteria) this;
        }

        public Criteria andFUIdNotEqualTo(Long value) {
            addCriterion("f_u_id <>", value, "fUId");
            return (Criteria) this;
        }

        public Criteria andFUIdGreaterThan(Long value) {
            addCriterion("f_u_id >", value, "fUId");
            return (Criteria) this;
        }

        public Criteria andFUIdGreaterThanOrEqualTo(Long value) {
            addCriterion("f_u_id >=", value, "fUId");
            return (Criteria) this;
        }

        public Criteria andFUIdLessThan(Long value) {
            addCriterion("f_u_id <", value, "fUId");
            return (Criteria) this;
        }

        public Criteria andFUIdLessThanOrEqualTo(Long value) {
            addCriterion("f_u_id <=", value, "fUId");
            return (Criteria) this;
        }

        public Criteria andFUIdIn(List<Long> values) {
            addCriterion("f_u_id in", values, "fUId");
            return (Criteria) this;
        }

        public Criteria andFUIdNotIn(List<Long> values) {
            addCriterion("f_u_id not in", values, "fUId");
            return (Criteria) this;
        }

        public Criteria andFUIdBetween(Long value1, Long value2) {
            addCriterion("f_u_id between", value1, value2, "fUId");
            return (Criteria) this;
        }

        public Criteria andFUIdNotBetween(Long value1, Long value2) {
            addCriterion("f_u_id not between", value1, value2, "fUId");
            return (Criteria) this;
        }

        public Criteria andFCDateIsNull() {
            addCriterion("f_c_date is null");
            return (Criteria) this;
        }

        public Criteria andFCDateIsNotNull() {
            addCriterion("f_c_date is not null");
            return (Criteria) this;
        }

        public Criteria andFCDateEqualTo(Date value) {
            addCriterion("f_c_date =", value, "fCDate");
            return (Criteria) this;
        }

        public Criteria andFCDateNotEqualTo(Date value) {
            addCriterion("f_c_date <>", value, "fCDate");
            return (Criteria) this;
        }

        public Criteria andFCDateGreaterThan(Date value) {
            addCriterion("f_c_date >", value, "fCDate");
            return (Criteria) this;
        }

        public Criteria andFCDateGreaterThanOrEqualTo(Date value) {
            addCriterion("f_c_date >=", value, "fCDate");
            return (Criteria) this;
        }

        public Criteria andFCDateLessThan(Date value) {
            addCriterion("f_c_date <", value, "fCDate");
            return (Criteria) this;
        }

        public Criteria andFCDateLessThanOrEqualTo(Date value) {
            addCriterion("f_c_date <=", value, "fCDate");
            return (Criteria) this;
        }

        public Criteria andFCDateIn(List<Date> values) {
            addCriterion("f_c_date in", values, "fCDate");
            return (Criteria) this;
        }

        public Criteria andFCDateNotIn(List<Date> values) {
            addCriterion("f_c_date not in", values, "fCDate");
            return (Criteria) this;
        }

        public Criteria andFCDateBetween(Date value1, Date value2) {
            addCriterion("f_c_date between", value1, value2, "fCDate");
            return (Criteria) this;
        }

        public Criteria andFCDateNotBetween(Date value1, Date value2) {
            addCriterion("f_c_date not between", value1, value2, "fCDate");
            return (Criteria) this;
        }

        public Criteria andFUDateIsNull() {
            addCriterion("f_u_date is null");
            return (Criteria) this;
        }

        public Criteria andFUDateIsNotNull() {
            addCriterion("f_u_date is not null");
            return (Criteria) this;
        }

        public Criteria andFUDateEqualTo(Date value) {
            addCriterion("f_u_date =", value, "fUDate");
            return (Criteria) this;
        }

        public Criteria andFUDateNotEqualTo(Date value) {
            addCriterion("f_u_date <>", value, "fUDate");
            return (Criteria) this;
        }

        public Criteria andFUDateGreaterThan(Date value) {
            addCriterion("f_u_date >", value, "fUDate");
            return (Criteria) this;
        }

        public Criteria andFUDateGreaterThanOrEqualTo(Date value) {
            addCriterion("f_u_date >=", value, "fUDate");
            return (Criteria) this;
        }

        public Criteria andFUDateLessThan(Date value) {
            addCriterion("f_u_date <", value, "fUDate");
            return (Criteria) this;
        }

        public Criteria andFUDateLessThanOrEqualTo(Date value) {
            addCriterion("f_u_date <=", value, "fUDate");
            return (Criteria) this;
        }

        public Criteria andFUDateIn(List<Date> values) {
            addCriterion("f_u_date in", values, "fUDate");
            return (Criteria) this;
        }

        public Criteria andFUDateNotIn(List<Date> values) {
            addCriterion("f_u_date not in", values, "fUDate");
            return (Criteria) this;
        }

        public Criteria andFUDateBetween(Date value1, Date value2) {
            addCriterion("f_u_date between", value1, value2, "fUDate");
            return (Criteria) this;
        }

        public Criteria andFUDateNotBetween(Date value1, Date value2) {
            addCriterion("f_u_date not between", value1, value2, "fUDate");
            return (Criteria) this;
        }
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：t_role
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * TRole
     * Criterion
     * 数据库表：t_role
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