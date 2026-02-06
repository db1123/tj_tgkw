package fun.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TAbilityAssessmentMethodExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_ability_assessment_method
     * @mbg.generated
     */
    public TAbilityAssessmentMethodExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * 设置排序字段
     * student_capability_evaluation..t_ability_assessment_method
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * student_capability_evaluation..t_ability_assessment_method
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * student_capability_evaluation..t_ability_assessment_method
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * student_capability_evaluation..t_ability_assessment_method
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * student_capability_evaluation..t_ability_assessment_method
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * student_capability_evaluation..t_ability_assessment_method
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * student_capability_evaluation..t_ability_assessment_method
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
     * student_capability_evaluation..t_ability_assessment_method
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
     * student_capability_evaluation..t_ability_assessment_method
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * student_capability_evaluation..t_ability_assessment_method
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TAbilityAssessmentMethod
     * GeneratedCriteria
     * 数据库表：student_capability_evaluation..t_ability_assessment_method
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

        public Criteria andFuidIsNull() {
            addCriterion("FUID is null");
            return (Criteria) this;
        }

        public Criteria andFuidIsNotNull() {
            addCriterion("FUID is not null");
            return (Criteria) this;
        }

        public Criteria andFuidEqualTo(Long value) {
            addCriterion("FUID =", value, "fuid");
            return (Criteria) this;
        }

        public Criteria andFuidNotEqualTo(Long value) {
            addCriterion("FUID <>", value, "fuid");
            return (Criteria) this;
        }

        public Criteria andFuidGreaterThan(Long value) {
            addCriterion("FUID >", value, "fuid");
            return (Criteria) this;
        }

        public Criteria andFuidGreaterThanOrEqualTo(Long value) {
            addCriterion("FUID >=", value, "fuid");
            return (Criteria) this;
        }

        public Criteria andFuidLessThan(Long value) {
            addCriterion("FUID <", value, "fuid");
            return (Criteria) this;
        }

        public Criteria andFuidLessThanOrEqualTo(Long value) {
            addCriterion("FUID <=", value, "fuid");
            return (Criteria) this;
        }

        public Criteria andFuidIn(List<Long> values) {
            addCriterion("FUID in", values, "fuid");
            return (Criteria) this;
        }

        public Criteria andFuidNotIn(List<Long> values) {
            addCriterion("FUID not in", values, "fuid");
            return (Criteria) this;
        }

        public Criteria andFuidBetween(Long value1, Long value2) {
            addCriterion("FUID between", value1, value2, "fuid");
            return (Criteria) this;
        }

        public Criteria andFuidNotBetween(Long value1, Long value2) {
            addCriterion("FUID not between", value1, value2, "fuid");
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

        public Criteria andFudateIsNull() {
            addCriterion("FUDATE is null");
            return (Criteria) this;
        }

        public Criteria andFudateIsNotNull() {
            addCriterion("FUDATE is not null");
            return (Criteria) this;
        }

        public Criteria andFudateEqualTo(Date value) {
            addCriterion("FUDATE =", value, "fudate");
            return (Criteria) this;
        }

        public Criteria andFudateNotEqualTo(Date value) {
            addCriterion("FUDATE <>", value, "fudate");
            return (Criteria) this;
        }

        public Criteria andFudateGreaterThan(Date value) {
            addCriterion("FUDATE >", value, "fudate");
            return (Criteria) this;
        }

        public Criteria andFudateGreaterThanOrEqualTo(Date value) {
            addCriterion("FUDATE >=", value, "fudate");
            return (Criteria) this;
        }

        public Criteria andFudateLessThan(Date value) {
            addCriterion("FUDATE <", value, "fudate");
            return (Criteria) this;
        }

        public Criteria andFudateLessThanOrEqualTo(Date value) {
            addCriterion("FUDATE <=", value, "fudate");
            return (Criteria) this;
        }

        public Criteria andFudateIn(List<Date> values) {
            addCriterion("FUDATE in", values, "fudate");
            return (Criteria) this;
        }

        public Criteria andFudateNotIn(List<Date> values) {
            addCriterion("FUDATE not in", values, "fudate");
            return (Criteria) this;
        }

        public Criteria andFudateBetween(Date value1, Date value2) {
            addCriterion("FUDATE between", value1, value2, "fudate");
            return (Criteria) this;
        }

        public Criteria andFudateNotBetween(Date value1, Date value2) {
            addCriterion("FUDATE not between", value1, value2, "fudate");
            return (Criteria) this;
        }

        public Criteria andFstateIsNull() {
            addCriterion("FState is null");
            return (Criteria) this;
        }

        public Criteria andFstateIsNotNull() {
            addCriterion("FState is not null");
            return (Criteria) this;
        }

        public Criteria andFstateEqualTo(Integer value) {
            addCriterion("FState =", value, "fstate");
            return (Criteria) this;
        }

        public Criteria andFstateNotEqualTo(Integer value) {
            addCriterion("FState <>", value, "fstate");
            return (Criteria) this;
        }

        public Criteria andFstateGreaterThan(Integer value) {
            addCriterion("FState >", value, "fstate");
            return (Criteria) this;
        }

        public Criteria andFstateGreaterThanOrEqualTo(Integer value) {
            addCriterion("FState >=", value, "fstate");
            return (Criteria) this;
        }

        public Criteria andFstateLessThan(Integer value) {
            addCriterion("FState <", value, "fstate");
            return (Criteria) this;
        }

        public Criteria andFstateLessThanOrEqualTo(Integer value) {
            addCriterion("FState <=", value, "fstate");
            return (Criteria) this;
        }

        public Criteria andFstateIn(List<Integer> values) {
            addCriterion("FState in", values, "fstate");
            return (Criteria) this;
        }

        public Criteria andFstateNotIn(List<Integer> values) {
            addCriterion("FState not in", values, "fstate");
            return (Criteria) this;
        }

        public Criteria andFstateBetween(Integer value1, Integer value2) {
            addCriterion("FState between", value1, value2, "fstate");
            return (Criteria) this;
        }

        public Criteria andFstateNotBetween(Integer value1, Integer value2) {
            addCriterion("FState not between", value1, value2, "fstate");
            return (Criteria) this;
        }

        public Criteria andFmethodnameIsNull() {
            addCriterion("FMethodName is null");
            return (Criteria) this;
        }

        public Criteria andFmethodnameIsNotNull() {
            addCriterion("FMethodName is not null");
            return (Criteria) this;
        }

        public Criteria andFmethodnameEqualTo(String value) {
            addCriterion("FMethodName =", value, "fmethodname");
            return (Criteria) this;
        }

        public Criteria andFmethodnameNotEqualTo(String value) {
            addCriterion("FMethodName <>", value, "fmethodname");
            return (Criteria) this;
        }

        public Criteria andFmethodnameGreaterThan(String value) {
            addCriterion("FMethodName >", value, "fmethodname");
            return (Criteria) this;
        }

        public Criteria andFmethodnameGreaterThanOrEqualTo(String value) {
            addCriterion("FMethodName >=", value, "fmethodname");
            return (Criteria) this;
        }

        public Criteria andFmethodnameLessThan(String value) {
            addCriterion("FMethodName <", value, "fmethodname");
            return (Criteria) this;
        }

        public Criteria andFmethodnameLessThanOrEqualTo(String value) {
            addCriterion("FMethodName <=", value, "fmethodname");
            return (Criteria) this;
        }

        public Criteria andFmethodnameLike(String value) {
            addCriterion("FMethodName like", value, "fmethodname");
            return (Criteria) this;
        }

        public Criteria andFmethodnameNotLike(String value) {
            addCriterion("FMethodName not like", value, "fmethodname");
            return (Criteria) this;
        }

        public Criteria andFmethodnameIn(List<String> values) {
            addCriterion("FMethodName in", values, "fmethodname");
            return (Criteria) this;
        }

        public Criteria andFmethodnameNotIn(List<String> values) {
            addCriterion("FMethodName not in", values, "fmethodname");
            return (Criteria) this;
        }

        public Criteria andFmethodnameBetween(String value1, String value2) {
            addCriterion("FMethodName between", value1, value2, "fmethodname");
            return (Criteria) this;
        }

        public Criteria andFmethodnameNotBetween(String value1, String value2) {
            addCriterion("FMethodName not between", value1, value2, "fmethodname");
            return (Criteria) this;
        }

        public Criteria andFdescriptionIsNull() {
            addCriterion("FDescription is null");
            return (Criteria) this;
        }

        public Criteria andFdescriptionIsNotNull() {
            addCriterion("FDescription is not null");
            return (Criteria) this;
        }

        public Criteria andFdescriptionEqualTo(String value) {
            addCriterion("FDescription =", value, "fdescription");
            return (Criteria) this;
        }

        public Criteria andFdescriptionNotEqualTo(String value) {
            addCriterion("FDescription <>", value, "fdescription");
            return (Criteria) this;
        }

        public Criteria andFdescriptionGreaterThan(String value) {
            addCriterion("FDescription >", value, "fdescription");
            return (Criteria) this;
        }

        public Criteria andFdescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("FDescription >=", value, "fdescription");
            return (Criteria) this;
        }

        public Criteria andFdescriptionLessThan(String value) {
            addCriterion("FDescription <", value, "fdescription");
            return (Criteria) this;
        }

        public Criteria andFdescriptionLessThanOrEqualTo(String value) {
            addCriterion("FDescription <=", value, "fdescription");
            return (Criteria) this;
        }

        public Criteria andFdescriptionLike(String value) {
            addCriterion("FDescription like", value, "fdescription");
            return (Criteria) this;
        }

        public Criteria andFdescriptionNotLike(String value) {
            addCriterion("FDescription not like", value, "fdescription");
            return (Criteria) this;
        }

        public Criteria andFdescriptionIn(List<String> values) {
            addCriterion("FDescription in", values, "fdescription");
            return (Criteria) this;
        }

        public Criteria andFdescriptionNotIn(List<String> values) {
            addCriterion("FDescription not in", values, "fdescription");
            return (Criteria) this;
        }

        public Criteria andFdescriptionBetween(String value1, String value2) {
            addCriterion("FDescription between", value1, value2, "fdescription");
            return (Criteria) this;
        }

        public Criteria andFdescriptionNotBetween(String value1, String value2) {
            addCriterion("FDescription not between", value1, value2, "fdescription");
            return (Criteria) this;
        }

        public Criteria andFmethodmaxscoreIsNull() {
            addCriterion("FMethodMaxScore is null");
            return (Criteria) this;
        }

        public Criteria andFmethodmaxscoreIsNotNull() {
            addCriterion("FMethodMaxScore is not null");
            return (Criteria) this;
        }

        public Criteria andFmethodmaxscoreEqualTo(Integer value) {
            addCriterion("FMethodMaxScore =", value, "fmethodmaxscore");
            return (Criteria) this;
        }

        public Criteria andFmethodmaxscoreNotEqualTo(Integer value) {
            addCriterion("FMethodMaxScore <>", value, "fmethodmaxscore");
            return (Criteria) this;
        }

        public Criteria andFmethodmaxscoreGreaterThan(Integer value) {
            addCriterion("FMethodMaxScore >", value, "fmethodmaxscore");
            return (Criteria) this;
        }

        public Criteria andFmethodmaxscoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("FMethodMaxScore >=", value, "fmethodmaxscore");
            return (Criteria) this;
        }

        public Criteria andFmethodmaxscoreLessThan(Integer value) {
            addCriterion("FMethodMaxScore <", value, "fmethodmaxscore");
            return (Criteria) this;
        }

        public Criteria andFmethodmaxscoreLessThanOrEqualTo(Integer value) {
            addCriterion("FMethodMaxScore <=", value, "fmethodmaxscore");
            return (Criteria) this;
        }

        public Criteria andFmethodmaxscoreIn(List<Integer> values) {
            addCriterion("FMethodMaxScore in", values, "fmethodmaxscore");
            return (Criteria) this;
        }

        public Criteria andFmethodmaxscoreNotIn(List<Integer> values) {
            addCriterion("FMethodMaxScore not in", values, "fmethodmaxscore");
            return (Criteria) this;
        }

        public Criteria andFmethodmaxscoreBetween(Integer value1, Integer value2) {
            addCriterion("FMethodMaxScore between", value1, value2, "fmethodmaxscore");
            return (Criteria) this;
        }

        public Criteria andFmethodmaxscoreNotBetween(Integer value1, Integer value2) {
            addCriterion("FMethodMaxScore not between", value1, value2, "fmethodmaxscore");
            return (Criteria) this;
        }
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：student_capability_evaluation..t_ability_assessment_method
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * TAbilityAssessmentMethod
     * Criterion
     * 数据库表：student_capability_evaluation..t_ability_assessment_method
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