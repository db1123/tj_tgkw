package fun.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class THireAbilityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_hire_ability
     * @mbg.generated
     */
    public THireAbilityExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * 设置排序字段
     * student_capability_evaluation..t_hire_ability
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * student_capability_evaluation..t_hire_ability
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * student_capability_evaluation..t_hire_ability
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * student_capability_evaluation..t_hire_ability
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * student_capability_evaluation..t_hire_ability
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * student_capability_evaluation..t_hire_ability
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * student_capability_evaluation..t_hire_ability
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
     * student_capability_evaluation..t_hire_ability
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
     * student_capability_evaluation..t_hire_ability
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * student_capability_evaluation..t_hire_ability
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * THireAbility
     * GeneratedCriteria
     * 数据库表：student_capability_evaluation..t_hire_ability
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

        public Criteria andFhireidIsNull() {
            addCriterion("FHireID is null");
            return (Criteria) this;
        }

        public Criteria andFhireidIsNotNull() {
            addCriterion("FHireID is not null");
            return (Criteria) this;
        }

        public Criteria andFhireidEqualTo(Long value) {
            addCriterion("FHireID =", value, "fhireid");
            return (Criteria) this;
        }

        public Criteria andFhireidNotEqualTo(Long value) {
            addCriterion("FHireID <>", value, "fhireid");
            return (Criteria) this;
        }

        public Criteria andFhireidGreaterThan(Long value) {
            addCriterion("FHireID >", value, "fhireid");
            return (Criteria) this;
        }

        public Criteria andFhireidGreaterThanOrEqualTo(Long value) {
            addCriterion("FHireID >=", value, "fhireid");
            return (Criteria) this;
        }

        public Criteria andFhireidLessThan(Long value) {
            addCriterion("FHireID <", value, "fhireid");
            return (Criteria) this;
        }

        public Criteria andFhireidLessThanOrEqualTo(Long value) {
            addCriterion("FHireID <=", value, "fhireid");
            return (Criteria) this;
        }

        public Criteria andFhireidIn(List<Long> values) {
            addCriterion("FHireID in", values, "fhireid");
            return (Criteria) this;
        }

        public Criteria andFhireidNotIn(List<Long> values) {
            addCriterion("FHireID not in", values, "fhireid");
            return (Criteria) this;
        }

        public Criteria andFhireidBetween(Long value1, Long value2) {
            addCriterion("FHireID between", value1, value2, "fhireid");
            return (Criteria) this;
        }

        public Criteria andFhireidNotBetween(Long value1, Long value2) {
            addCriterion("FHireID not between", value1, value2, "fhireid");
            return (Criteria) this;
        }

        public Criteria andFabilityidIsNull() {
            addCriterion("FAbilityID is null");
            return (Criteria) this;
        }

        public Criteria andFabilityidIsNotNull() {
            addCriterion("FAbilityID is not null");
            return (Criteria) this;
        }

        public Criteria andFabilityidEqualTo(Long value) {
            addCriterion("FAbilityID =", value, "fabilityid");
            return (Criteria) this;
        }

        public Criteria andFabilityidNotEqualTo(Long value) {
            addCriterion("FAbilityID <>", value, "fabilityid");
            return (Criteria) this;
        }

        public Criteria andFabilityidGreaterThan(Long value) {
            addCriterion("FAbilityID >", value, "fabilityid");
            return (Criteria) this;
        }

        public Criteria andFabilityidGreaterThanOrEqualTo(Long value) {
            addCriterion("FAbilityID >=", value, "fabilityid");
            return (Criteria) this;
        }

        public Criteria andFabilityidLessThan(Long value) {
            addCriterion("FAbilityID <", value, "fabilityid");
            return (Criteria) this;
        }

        public Criteria andFabilityidLessThanOrEqualTo(Long value) {
            addCriterion("FAbilityID <=", value, "fabilityid");
            return (Criteria) this;
        }

        public Criteria andFabilityidIn(List<Long> values) {
            addCriterion("FAbilityID in", values, "fabilityid");
            return (Criteria) this;
        }

        public Criteria andFabilityidNotIn(List<Long> values) {
            addCriterion("FAbilityID not in", values, "fabilityid");
            return (Criteria) this;
        }

        public Criteria andFabilityidBetween(Long value1, Long value2) {
            addCriterion("FAbilityID between", value1, value2, "fabilityid");
            return (Criteria) this;
        }

        public Criteria andFabilityidNotBetween(Long value1, Long value2) {
            addCriterion("FAbilityID not between", value1, value2, "fabilityid");
            return (Criteria) this;
        }

        public Criteria andFabilitylevelidIsNull() {
            addCriterion("FAbilityLevelID is null");
            return (Criteria) this;
        }

        public Criteria andFabilitylevelidIsNotNull() {
            addCriterion("FAbilityLevelID is not null");
            return (Criteria) this;
        }

        public Criteria andFabilitylevelidEqualTo(Long value) {
            addCriterion("FAbilityLevelID =", value, "fabilitylevelid");
            return (Criteria) this;
        }

        public Criteria andFabilitylevelidNotEqualTo(Long value) {
            addCriterion("FAbilityLevelID <>", value, "fabilitylevelid");
            return (Criteria) this;
        }

        public Criteria andFabilitylevelidGreaterThan(Long value) {
            addCriterion("FAbilityLevelID >", value, "fabilitylevelid");
            return (Criteria) this;
        }

        public Criteria andFabilitylevelidGreaterThanOrEqualTo(Long value) {
            addCriterion("FAbilityLevelID >=", value, "fabilitylevelid");
            return (Criteria) this;
        }

        public Criteria andFabilitylevelidLessThan(Long value) {
            addCriterion("FAbilityLevelID <", value, "fabilitylevelid");
            return (Criteria) this;
        }

        public Criteria andFabilitylevelidLessThanOrEqualTo(Long value) {
            addCriterion("FAbilityLevelID <=", value, "fabilitylevelid");
            return (Criteria) this;
        }

        public Criteria andFabilitylevelidIn(List<Long> values) {
            addCriterion("FAbilityLevelID in", values, "fabilitylevelid");
            return (Criteria) this;
        }

        public Criteria andFabilitylevelidNotIn(List<Long> values) {
            addCriterion("FAbilityLevelID not in", values, "fabilitylevelid");
            return (Criteria) this;
        }

        public Criteria andFabilitylevelidBetween(Long value1, Long value2) {
            addCriterion("FAbilityLevelID between", value1, value2, "fabilitylevelid");
            return (Criteria) this;
        }

        public Criteria andFabilitylevelidNotBetween(Long value1, Long value2) {
            addCriterion("FAbilityLevelID not between", value1, value2, "fabilitylevelid");
            return (Criteria) this;
        }

        public Criteria andFweightIsNull() {
            addCriterion("FWeight is null");
            return (Criteria) this;
        }

        public Criteria andFweightIsNotNull() {
            addCriterion("FWeight is not null");
            return (Criteria) this;
        }

        public Criteria andFweightEqualTo(Integer value) {
            addCriterion("FWeight =", value, "fweight");
            return (Criteria) this;
        }

        public Criteria andFweightNotEqualTo(Integer value) {
            addCriterion("FWeight <>", value, "fweight");
            return (Criteria) this;
        }

        public Criteria andFweightGreaterThan(Integer value) {
            addCriterion("FWeight >", value, "fweight");
            return (Criteria) this;
        }

        public Criteria andFweightGreaterThanOrEqualTo(Integer value) {
            addCriterion("FWeight >=", value, "fweight");
            return (Criteria) this;
        }

        public Criteria andFweightLessThan(Integer value) {
            addCriterion("FWeight <", value, "fweight");
            return (Criteria) this;
        }

        public Criteria andFweightLessThanOrEqualTo(Integer value) {
            addCriterion("FWeight <=", value, "fweight");
            return (Criteria) this;
        }

        public Criteria andFweightIn(List<Integer> values) {
            addCriterion("FWeight in", values, "fweight");
            return (Criteria) this;
        }

        public Criteria andFweightNotIn(List<Integer> values) {
            addCriterion("FWeight not in", values, "fweight");
            return (Criteria) this;
        }

        public Criteria andFweightBetween(Integer value1, Integer value2) {
            addCriterion("FWeight between", value1, value2, "fweight");
            return (Criteria) this;
        }

        public Criteria andFweightNotBetween(Integer value1, Integer value2) {
            addCriterion("FWeight not between", value1, value2, "fweight");
            return (Criteria) this;
        }
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：student_capability_evaluation..t_hire_ability
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * THireAbility
     * Criterion
     * 数据库表：student_capability_evaluation..t_hire_ability
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