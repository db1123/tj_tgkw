package fun.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TProjectLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * t_project_log
     * @mbg.generated
     */
    public TProjectLogExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * 设置排序字段
     * t_project_log
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * t_project_log
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * t_project_log
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * t_project_log
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * t_project_log
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * t_project_log
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * t_project_log
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
     * t_project_log
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
     * t_project_log
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * t_project_log
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TProjectLog
     * GeneratedCriteria
     * 数据库表：t_project_log
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

        public Criteria andFkeyidIsNull() {
            addCriterion("FKeyID is null");
            return (Criteria) this;
        }

        public Criteria andFkeyidIsNotNull() {
            addCriterion("FKeyID is not null");
            return (Criteria) this;
        }

        public Criteria andFkeyidEqualTo(Long value) {
            addCriterion("FKeyID =", value, "fkeyid");
            return (Criteria) this;
        }

        public Criteria andFkeyidNotEqualTo(Long value) {
            addCriterion("FKeyID <>", value, "fkeyid");
            return (Criteria) this;
        }

        public Criteria andFkeyidGreaterThan(Long value) {
            addCriterion("FKeyID >", value, "fkeyid");
            return (Criteria) this;
        }

        public Criteria andFkeyidGreaterThanOrEqualTo(Long value) {
            addCriterion("FKeyID >=", value, "fkeyid");
            return (Criteria) this;
        }

        public Criteria andFkeyidLessThan(Long value) {
            addCriterion("FKeyID <", value, "fkeyid");
            return (Criteria) this;
        }

        public Criteria andFkeyidLessThanOrEqualTo(Long value) {
            addCriterion("FKeyID <=", value, "fkeyid");
            return (Criteria) this;
        }

        public Criteria andFkeyidIn(List<Long> values) {
            addCriterion("FKeyID in", values, "fkeyid");
            return (Criteria) this;
        }

        public Criteria andFkeyidNotIn(List<Long> values) {
            addCriterion("FKeyID not in", values, "fkeyid");
            return (Criteria) this;
        }

        public Criteria andFkeyidBetween(Long value1, Long value2) {
            addCriterion("FKeyID between", value1, value2, "fkeyid");
            return (Criteria) this;
        }

        public Criteria andFkeyidNotBetween(Long value1, Long value2) {
            addCriterion("FKeyID not between", value1, value2, "fkeyid");
            return (Criteria) this;
        }

        public Criteria andFcidIsNull() {
            addCriterion("FCID is null");
            return (Criteria) this;
        }

        public Criteria andFcidIsNotNull() {
            addCriterion("FCID is not null");
            return (Criteria) this;
        }

        public Criteria andFcidEqualTo(Long value) {
            addCriterion("FCID =", value, "fcid");
            return (Criteria) this;
        }

        public Criteria andFcidNotEqualTo(Long value) {
            addCriterion("FCID <>", value, "fcid");
            return (Criteria) this;
        }

        public Criteria andFcidGreaterThan(Long value) {
            addCriterion("FCID >", value, "fcid");
            return (Criteria) this;
        }

        public Criteria andFcidGreaterThanOrEqualTo(Long value) {
            addCriterion("FCID >=", value, "fcid");
            return (Criteria) this;
        }

        public Criteria andFcidLessThan(Long value) {
            addCriterion("FCID <", value, "fcid");
            return (Criteria) this;
        }

        public Criteria andFcidLessThanOrEqualTo(Long value) {
            addCriterion("FCID <=", value, "fcid");
            return (Criteria) this;
        }

        public Criteria andFcidIn(List<Long> values) {
            addCriterion("FCID in", values, "fcid");
            return (Criteria) this;
        }

        public Criteria andFcidNotIn(List<Long> values) {
            addCriterion("FCID not in", values, "fcid");
            return (Criteria) this;
        }

        public Criteria andFcidBetween(Long value1, Long value2) {
            addCriterion("FCID between", value1, value2, "fcid");
            return (Criteria) this;
        }

        public Criteria andFcidNotBetween(Long value1, Long value2) {
            addCriterion("FCID not between", value1, value2, "fcid");
            return (Criteria) this;
        }

        public Criteria andFcdateIsNull() {
            addCriterion("FCDATE is null");
            return (Criteria) this;
        }

        public Criteria andFcdateIsNotNull() {
            addCriterion("FCDATE is not null");
            return (Criteria) this;
        }

        public Criteria andFcdateEqualTo(Date value) {
            addCriterion("FCDATE =", value, "fcdate");
            return (Criteria) this;
        }

        public Criteria andFcdateNotEqualTo(Date value) {
            addCriterion("FCDATE <>", value, "fcdate");
            return (Criteria) this;
        }

        public Criteria andFcdateGreaterThan(Date value) {
            addCriterion("FCDATE >", value, "fcdate");
            return (Criteria) this;
        }

        public Criteria andFcdateGreaterThanOrEqualTo(Date value) {
            addCriterion("FCDATE >=", value, "fcdate");
            return (Criteria) this;
        }

        public Criteria andFcdateLessThan(Date value) {
            addCriterion("FCDATE <", value, "fcdate");
            return (Criteria) this;
        }

        public Criteria andFcdateLessThanOrEqualTo(Date value) {
            addCriterion("FCDATE <=", value, "fcdate");
            return (Criteria) this;
        }

        public Criteria andFcdateIn(List<Date> values) {
            addCriterion("FCDATE in", values, "fcdate");
            return (Criteria) this;
        }

        public Criteria andFcdateNotIn(List<Date> values) {
            addCriterion("FCDATE not in", values, "fcdate");
            return (Criteria) this;
        }

        public Criteria andFcdateBetween(Date value1, Date value2) {
            addCriterion("FCDATE between", value1, value2, "fcdate");
            return (Criteria) this;
        }

        public Criteria andFcdateNotBetween(Date value1, Date value2) {
            addCriterion("FCDATE not between", value1, value2, "fcdate");
            return (Criteria) this;
        }

        public Criteria andFprojectidIsNull() {
            addCriterion("FProjectID is null");
            return (Criteria) this;
        }

        public Criteria andFprojectidIsNotNull() {
            addCriterion("FProjectID is not null");
            return (Criteria) this;
        }

        public Criteria andFprojectidEqualTo(Long value) {
            addCriterion("FProjectID =", value, "fprojectid");
            return (Criteria) this;
        }

        public Criteria andFprojectidNotEqualTo(Long value) {
            addCriterion("FProjectID <>", value, "fprojectid");
            return (Criteria) this;
        }

        public Criteria andFprojectidGreaterThan(Long value) {
            addCriterion("FProjectID >", value, "fprojectid");
            return (Criteria) this;
        }

        public Criteria andFprojectidGreaterThanOrEqualTo(Long value) {
            addCriterion("FProjectID >=", value, "fprojectid");
            return (Criteria) this;
        }

        public Criteria andFprojectidLessThan(Long value) {
            addCriterion("FProjectID <", value, "fprojectid");
            return (Criteria) this;
        }

        public Criteria andFprojectidLessThanOrEqualTo(Long value) {
            addCriterion("FProjectID <=", value, "fprojectid");
            return (Criteria) this;
        }

        public Criteria andFprojectidIn(List<Long> values) {
            addCriterion("FProjectID in", values, "fprojectid");
            return (Criteria) this;
        }

        public Criteria andFprojectidNotIn(List<Long> values) {
            addCriterion("FProjectID not in", values, "fprojectid");
            return (Criteria) this;
        }

        public Criteria andFprojectidBetween(Long value1, Long value2) {
            addCriterion("FProjectID between", value1, value2, "fprojectid");
            return (Criteria) this;
        }

        public Criteria andFprojectidNotBetween(Long value1, Long value2) {
            addCriterion("FProjectID not between", value1, value2, "fprojectid");
            return (Criteria) this;
        }

        public Criteria andFfunctionIsNull() {
            addCriterion("FFunction is null");
            return (Criteria) this;
        }

        public Criteria andFfunctionIsNotNull() {
            addCriterion("FFunction is not null");
            return (Criteria) this;
        }

        public Criteria andFfunctionEqualTo(String value) {
            addCriterion("FFunction =", value, "ffunction");
            return (Criteria) this;
        }

        public Criteria andFfunctionNotEqualTo(String value) {
            addCriterion("FFunction <>", value, "ffunction");
            return (Criteria) this;
        }

        public Criteria andFfunctionGreaterThan(String value) {
            addCriterion("FFunction >", value, "ffunction");
            return (Criteria) this;
        }

        public Criteria andFfunctionGreaterThanOrEqualTo(String value) {
            addCriterion("FFunction >=", value, "ffunction");
            return (Criteria) this;
        }

        public Criteria andFfunctionLessThan(String value) {
            addCriterion("FFunction <", value, "ffunction");
            return (Criteria) this;
        }

        public Criteria andFfunctionLessThanOrEqualTo(String value) {
            addCriterion("FFunction <=", value, "ffunction");
            return (Criteria) this;
        }

        public Criteria andFfunctionLike(String value) {
            addCriterion("FFunction like", value, "ffunction");
            return (Criteria) this;
        }

        public Criteria andFfunctionNotLike(String value) {
            addCriterion("FFunction not like", value, "ffunction");
            return (Criteria) this;
        }

        public Criteria andFfunctionIn(List<String> values) {
            addCriterion("FFunction in", values, "ffunction");
            return (Criteria) this;
        }

        public Criteria andFfunctionNotIn(List<String> values) {
            addCriterion("FFunction not in", values, "ffunction");
            return (Criteria) this;
        }

        public Criteria andFfunctionBetween(String value1, String value2) {
            addCriterion("FFunction between", value1, value2, "ffunction");
            return (Criteria) this;
        }

        public Criteria andFfunctionNotBetween(String value1, String value2) {
            addCriterion("FFunction not between", value1, value2, "ffunction");
            return (Criteria) this;
        }

        public Criteria andFoperationIsNull() {
            addCriterion("FOperation is null");
            return (Criteria) this;
        }

        public Criteria andFoperationIsNotNull() {
            addCriterion("FOperation is not null");
            return (Criteria) this;
        }

        public Criteria andFoperationEqualTo(String value) {
            addCriterion("FOperation =", value, "foperation");
            return (Criteria) this;
        }

        public Criteria andFoperationNotEqualTo(String value) {
            addCriterion("FOperation <>", value, "foperation");
            return (Criteria) this;
        }

        public Criteria andFoperationGreaterThan(String value) {
            addCriterion("FOperation >", value, "foperation");
            return (Criteria) this;
        }

        public Criteria andFoperationGreaterThanOrEqualTo(String value) {
            addCriterion("FOperation >=", value, "foperation");
            return (Criteria) this;
        }

        public Criteria andFoperationLessThan(String value) {
            addCriterion("FOperation <", value, "foperation");
            return (Criteria) this;
        }

        public Criteria andFoperationLessThanOrEqualTo(String value) {
            addCriterion("FOperation <=", value, "foperation");
            return (Criteria) this;
        }

        public Criteria andFoperationLike(String value) {
            addCriterion("FOperation like", value, "foperation");
            return (Criteria) this;
        }

        public Criteria andFoperationNotLike(String value) {
            addCriterion("FOperation not like", value, "foperation");
            return (Criteria) this;
        }

        public Criteria andFoperationIn(List<String> values) {
            addCriterion("FOperation in", values, "foperation");
            return (Criteria) this;
        }

        public Criteria andFoperationNotIn(List<String> values) {
            addCriterion("FOperation not in", values, "foperation");
            return (Criteria) this;
        }

        public Criteria andFoperationBetween(String value1, String value2) {
            addCriterion("FOperation between", value1, value2, "foperation");
            return (Criteria) this;
        }

        public Criteria andFoperationNotBetween(String value1, String value2) {
            addCriterion("FOperation not between", value1, value2, "foperation");
            return (Criteria) this;
        }

        public Criteria andFlinkIsNull() {
            addCriterion("FLink is null");
            return (Criteria) this;
        }

        public Criteria andFlinkIsNotNull() {
            addCriterion("FLink is not null");
            return (Criteria) this;
        }

        public Criteria andFlinkEqualTo(String value) {
            addCriterion("FLink =", value, "flink");
            return (Criteria) this;
        }

        public Criteria andFlinkNotEqualTo(String value) {
            addCriterion("FLink <>", value, "flink");
            return (Criteria) this;
        }

        public Criteria andFlinkGreaterThan(String value) {
            addCriterion("FLink >", value, "flink");
            return (Criteria) this;
        }

        public Criteria andFlinkGreaterThanOrEqualTo(String value) {
            addCriterion("FLink >=", value, "flink");
            return (Criteria) this;
        }

        public Criteria andFlinkLessThan(String value) {
            addCriterion("FLink <", value, "flink");
            return (Criteria) this;
        }

        public Criteria andFlinkLessThanOrEqualTo(String value) {
            addCriterion("FLink <=", value, "flink");
            return (Criteria) this;
        }

        public Criteria andFlinkLike(String value) {
            addCriterion("FLink like", value, "flink");
            return (Criteria) this;
        }

        public Criteria andFlinkNotLike(String value) {
            addCriterion("FLink not like", value, "flink");
            return (Criteria) this;
        }

        public Criteria andFlinkIn(List<String> values) {
            addCriterion("FLink in", values, "flink");
            return (Criteria) this;
        }

        public Criteria andFlinkNotIn(List<String> values) {
            addCriterion("FLink not in", values, "flink");
            return (Criteria) this;
        }

        public Criteria andFlinkBetween(String value1, String value2) {
            addCriterion("FLink between", value1, value2, "flink");
            return (Criteria) this;
        }

        public Criteria andFlinkNotBetween(String value1, String value2) {
            addCriterion("FLink not between", value1, value2, "flink");
            return (Criteria) this;
        }

        public Criteria andFnoteIsNull() {
            addCriterion("FNote is null");
            return (Criteria) this;
        }

        public Criteria andFnoteIsNotNull() {
            addCriterion("FNote is not null");
            return (Criteria) this;
        }

        public Criteria andFnoteEqualTo(String value) {
            addCriterion("FNote =", value, "fnote");
            return (Criteria) this;
        }

        public Criteria andFnoteNotEqualTo(String value) {
            addCriterion("FNote <>", value, "fnote");
            return (Criteria) this;
        }

        public Criteria andFnoteGreaterThan(String value) {
            addCriterion("FNote >", value, "fnote");
            return (Criteria) this;
        }

        public Criteria andFnoteGreaterThanOrEqualTo(String value) {
            addCriterion("FNote >=", value, "fnote");
            return (Criteria) this;
        }

        public Criteria andFnoteLessThan(String value) {
            addCriterion("FNote <", value, "fnote");
            return (Criteria) this;
        }

        public Criteria andFnoteLessThanOrEqualTo(String value) {
            addCriterion("FNote <=", value, "fnote");
            return (Criteria) this;
        }

        public Criteria andFnoteLike(String value) {
            addCriterion("FNote like", value, "fnote");
            return (Criteria) this;
        }

        public Criteria andFnoteNotLike(String value) {
            addCriterion("FNote not like", value, "fnote");
            return (Criteria) this;
        }

        public Criteria andFnoteIn(List<String> values) {
            addCriterion("FNote in", values, "fnote");
            return (Criteria) this;
        }

        public Criteria andFnoteNotIn(List<String> values) {
            addCriterion("FNote not in", values, "fnote");
            return (Criteria) this;
        }

        public Criteria andFnoteBetween(String value1, String value2) {
            addCriterion("FNote between", value1, value2, "fnote");
            return (Criteria) this;
        }

        public Criteria andFnoteNotBetween(String value1, String value2) {
            addCriterion("FNote not between", value1, value2, "fnote");
            return (Criteria) this;
        }
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：t_project_log
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * TProjectLog
     * Criterion
     * 数据库表：t_project_log
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