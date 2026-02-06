package fun.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TCourseStandardExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_standard
     * @mbg.generated
     */
    public TCourseStandardExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * 设置排序字段
     * student_capability_evaluation..t_course_standard
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * student_capability_evaluation..t_course_standard
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * student_capability_evaluation..t_course_standard
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * student_capability_evaluation..t_course_standard
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * student_capability_evaluation..t_course_standard
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * student_capability_evaluation..t_course_standard
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * student_capability_evaluation..t_course_standard
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
     * student_capability_evaluation..t_course_standard
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
     * student_capability_evaluation..t_course_standard
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * student_capability_evaluation..t_course_standard
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TCourseStandard
     * GeneratedCriteria
     * 数据库表：student_capability_evaluation..t_course_standard
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

        public Criteria andFcourseidIsNull() {
            addCriterion("FCourseID is null");
            return (Criteria) this;
        }

        public Criteria andFcourseidIsNotNull() {
            addCriterion("FCourseID is not null");
            return (Criteria) this;
        }

        public Criteria andFcourseidEqualTo(Long value) {
            addCriterion("FCourseID =", value, "fcourseid");
            return (Criteria) this;
        }

        public Criteria andFcourseidNotEqualTo(Long value) {
            addCriterion("FCourseID <>", value, "fcourseid");
            return (Criteria) this;
        }

        public Criteria andFcourseidGreaterThan(Long value) {
            addCriterion("FCourseID >", value, "fcourseid");
            return (Criteria) this;
        }

        public Criteria andFcourseidGreaterThanOrEqualTo(Long value) {
            addCriterion("FCourseID >=", value, "fcourseid");
            return (Criteria) this;
        }

        public Criteria andFcourseidLessThan(Long value) {
            addCriterion("FCourseID <", value, "fcourseid");
            return (Criteria) this;
        }

        public Criteria andFcourseidLessThanOrEqualTo(Long value) {
            addCriterion("FCourseID <=", value, "fcourseid");
            return (Criteria) this;
        }

        public Criteria andFcourseidIn(List<Long> values) {
            addCriterion("FCourseID in", values, "fcourseid");
            return (Criteria) this;
        }

        public Criteria andFcourseidNotIn(List<Long> values) {
            addCriterion("FCourseID not in", values, "fcourseid");
            return (Criteria) this;
        }

        public Criteria andFcourseidBetween(Long value1, Long value2) {
            addCriterion("FCourseID between", value1, value2, "fcourseid");
            return (Criteria) this;
        }

        public Criteria andFcourseidNotBetween(Long value1, Long value2) {
            addCriterion("FCourseID not between", value1, value2, "fcourseid");
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

        public Criteria andFcourseabilityidIsNull() {
            addCriterion("FCourseAbilityID is null");
            return (Criteria) this;
        }

        public Criteria andFcourseabilityidIsNotNull() {
            addCriterion("FCourseAbilityID is not null");
            return (Criteria) this;
        }

        public Criteria andFcourseabilityidEqualTo(Long value) {
            addCriterion("FCourseAbilityID =", value, "fcourseabilityid");
            return (Criteria) this;
        }

        public Criteria andFcourseabilityidNotEqualTo(Long value) {
            addCriterion("FCourseAbilityID <>", value, "fcourseabilityid");
            return (Criteria) this;
        }

        public Criteria andFcourseabilityidGreaterThan(Long value) {
            addCriterion("FCourseAbilityID >", value, "fcourseabilityid");
            return (Criteria) this;
        }

        public Criteria andFcourseabilityidGreaterThanOrEqualTo(Long value) {
            addCriterion("FCourseAbilityID >=", value, "fcourseabilityid");
            return (Criteria) this;
        }

        public Criteria andFcourseabilityidLessThan(Long value) {
            addCriterion("FCourseAbilityID <", value, "fcourseabilityid");
            return (Criteria) this;
        }

        public Criteria andFcourseabilityidLessThanOrEqualTo(Long value) {
            addCriterion("FCourseAbilityID <=", value, "fcourseabilityid");
            return (Criteria) this;
        }

        public Criteria andFcourseabilityidIn(List<Long> values) {
            addCriterion("FCourseAbilityID in", values, "fcourseabilityid");
            return (Criteria) this;
        }

        public Criteria andFcourseabilityidNotIn(List<Long> values) {
            addCriterion("FCourseAbilityID not in", values, "fcourseabilityid");
            return (Criteria) this;
        }

        public Criteria andFcourseabilityidBetween(Long value1, Long value2) {
            addCriterion("FCourseAbilityID between", value1, value2, "fcourseabilityid");
            return (Criteria) this;
        }

        public Criteria andFcourseabilityidNotBetween(Long value1, Long value2) {
            addCriterion("FCourseAbilityID not between", value1, value2, "fcourseabilityid");
            return (Criteria) this;
        }

        public Criteria andFtypeIsNull() {
            addCriterion("FType is null");
            return (Criteria) this;
        }

        public Criteria andFtypeIsNotNull() {
            addCriterion("FType is not null");
            return (Criteria) this;
        }

        public Criteria andFtypeEqualTo(Integer value) {
            addCriterion("FType =", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeNotEqualTo(Integer value) {
            addCriterion("FType <>", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeGreaterThan(Integer value) {
            addCriterion("FType >", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("FType >=", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeLessThan(Integer value) {
            addCriterion("FType <", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeLessThanOrEqualTo(Integer value) {
            addCriterion("FType <=", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeIn(List<Integer> values) {
            addCriterion("FType in", values, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeNotIn(List<Integer> values) {
            addCriterion("FType not in", values, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeBetween(Integer value1, Integer value2) {
            addCriterion("FType between", value1, value2, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeNotBetween(Integer value1, Integer value2) {
            addCriterion("FType not between", value1, value2, "ftype");
            return (Criteria) this;
        }

        public Criteria andFcalctypeIsNull() {
            addCriterion("FcalcType is null");
            return (Criteria) this;
        }

        public Criteria andFcalctypeIsNotNull() {
            addCriterion("FcalcType is not null");
            return (Criteria) this;
        }

        public Criteria andFcalctypeEqualTo(Long value) {
            addCriterion("FcalcType =", value, "fcalctype");
            return (Criteria) this;
        }

        public Criteria andFcalctypeNotEqualTo(Long value) {
            addCriterion("FcalcType <>", value, "fcalctype");
            return (Criteria) this;
        }

        public Criteria andFcalctypeGreaterThan(Long value) {
            addCriterion("FcalcType >", value, "fcalctype");
            return (Criteria) this;
        }

        public Criteria andFcalctypeGreaterThanOrEqualTo(Long value) {
            addCriterion("FcalcType >=", value, "fcalctype");
            return (Criteria) this;
        }

        public Criteria andFcalctypeLessThan(Long value) {
            addCriterion("FcalcType <", value, "fcalctype");
            return (Criteria) this;
        }

        public Criteria andFcalctypeLessThanOrEqualTo(Long value) {
            addCriterion("FcalcType <=", value, "fcalctype");
            return (Criteria) this;
        }

        public Criteria andFcalctypeIn(List<Long> values) {
            addCriterion("FcalcType in", values, "fcalctype");
            return (Criteria) this;
        }

        public Criteria andFcalctypeNotIn(List<Long> values) {
            addCriterion("FcalcType not in", values, "fcalctype");
            return (Criteria) this;
        }

        public Criteria andFcalctypeBetween(Long value1, Long value2) {
            addCriterion("FcalcType between", value1, value2, "fcalctype");
            return (Criteria) this;
        }

        public Criteria andFcalctypeNotBetween(Long value1, Long value2) {
            addCriterion("FcalcType not between", value1, value2, "fcalctype");
            return (Criteria) this;
        }

        public Criteria andFszstateIsNull() {
            addCriterion("FSZState is null");
            return (Criteria) this;
        }

        public Criteria andFszstateIsNotNull() {
            addCriterion("FSZState is not null");
            return (Criteria) this;
        }

        public Criteria andFszstateEqualTo(Integer value) {
            addCriterion("FSZState =", value, "fszstate");
            return (Criteria) this;
        }

        public Criteria andFszstateNotEqualTo(Integer value) {
            addCriterion("FSZState <>", value, "fszstate");
            return (Criteria) this;
        }

        public Criteria andFszstateGreaterThan(Integer value) {
            addCriterion("FSZState >", value, "fszstate");
            return (Criteria) this;
        }

        public Criteria andFszstateGreaterThanOrEqualTo(Integer value) {
            addCriterion("FSZState >=", value, "fszstate");
            return (Criteria) this;
        }

        public Criteria andFszstateLessThan(Integer value) {
            addCriterion("FSZState <", value, "fszstate");
            return (Criteria) this;
        }

        public Criteria andFszstateLessThanOrEqualTo(Integer value) {
            addCriterion("FSZState <=", value, "fszstate");
            return (Criteria) this;
        }

        public Criteria andFszstateIn(List<Integer> values) {
            addCriterion("FSZState in", values, "fszstate");
            return (Criteria) this;
        }

        public Criteria andFszstateNotIn(List<Integer> values) {
            addCriterion("FSZState not in", values, "fszstate");
            return (Criteria) this;
        }

        public Criteria andFszstateBetween(Integer value1, Integer value2) {
            addCriterion("FSZState between", value1, value2, "fszstate");
            return (Criteria) this;
        }

        public Criteria andFszstateNotBetween(Integer value1, Integer value2) {
            addCriterion("FSZState not between", value1, value2, "fszstate");
            return (Criteria) this;
        }

        public Criteria andFabilitycamidIsNull() {
            addCriterion("FAbilityCAMID is null");
            return (Criteria) this;
        }

        public Criteria andFabilitycamidIsNotNull() {
            addCriterion("FAbilityCAMID is not null");
            return (Criteria) this;
        }

        public Criteria andFabilitycamidEqualTo(Long value) {
            addCriterion("FAbilityCAMID =", value, "fabilitycamid");
            return (Criteria) this;
        }

        public Criteria andFabilitycamidNotEqualTo(Long value) {
            addCriterion("FAbilityCAMID <>", value, "fabilitycamid");
            return (Criteria) this;
        }

        public Criteria andFabilitycamidGreaterThan(Long value) {
            addCriterion("FAbilityCAMID >", value, "fabilitycamid");
            return (Criteria) this;
        }

        public Criteria andFabilitycamidGreaterThanOrEqualTo(Long value) {
            addCriterion("FAbilityCAMID >=", value, "fabilitycamid");
            return (Criteria) this;
        }

        public Criteria andFabilitycamidLessThan(Long value) {
            addCriterion("FAbilityCAMID <", value, "fabilitycamid");
            return (Criteria) this;
        }

        public Criteria andFabilitycamidLessThanOrEqualTo(Long value) {
            addCriterion("FAbilityCAMID <=", value, "fabilitycamid");
            return (Criteria) this;
        }

        public Criteria andFabilitycamidIn(List<Long> values) {
            addCriterion("FAbilityCAMID in", values, "fabilitycamid");
            return (Criteria) this;
        }

        public Criteria andFabilitycamidNotIn(List<Long> values) {
            addCriterion("FAbilityCAMID not in", values, "fabilitycamid");
            return (Criteria) this;
        }

        public Criteria andFabilitycamidBetween(Long value1, Long value2) {
            addCriterion("FAbilityCAMID between", value1, value2, "fabilitycamid");
            return (Criteria) this;
        }

        public Criteria andFabilitycamidNotBetween(Long value1, Long value2) {
            addCriterion("FAbilityCAMID not between", value1, value2, "fabilitycamid");
            return (Criteria) this;
        }

        public Criteria andFqztypeIsNull() {
            addCriterion("FQZType is null");
            return (Criteria) this;
        }

        public Criteria andFqztypeIsNotNull() {
            addCriterion("FQZType is not null");
            return (Criteria) this;
        }

        public Criteria andFqztypeEqualTo(Integer value) {
            addCriterion("FQZType =", value, "fqztype");
            return (Criteria) this;
        }

        public Criteria andFqztypeNotEqualTo(Integer value) {
            addCriterion("FQZType <>", value, "fqztype");
            return (Criteria) this;
        }

        public Criteria andFqztypeGreaterThan(Integer value) {
            addCriterion("FQZType >", value, "fqztype");
            return (Criteria) this;
        }

        public Criteria andFqztypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("FQZType >=", value, "fqztype");
            return (Criteria) this;
        }

        public Criteria andFqztypeLessThan(Integer value) {
            addCriterion("FQZType <", value, "fqztype");
            return (Criteria) this;
        }

        public Criteria andFqztypeLessThanOrEqualTo(Integer value) {
            addCriterion("FQZType <=", value, "fqztype");
            return (Criteria) this;
        }

        public Criteria andFqztypeIn(List<Integer> values) {
            addCriterion("FQZType in", values, "fqztype");
            return (Criteria) this;
        }

        public Criteria andFqztypeNotIn(List<Integer> values) {
            addCriterion("FQZType not in", values, "fqztype");
            return (Criteria) this;
        }

        public Criteria andFqztypeBetween(Integer value1, Integer value2) {
            addCriterion("FQZType between", value1, value2, "fqztype");
            return (Criteria) this;
        }

        public Criteria andFqztypeNotBetween(Integer value1, Integer value2) {
            addCriterion("FQZType not between", value1, value2, "fqztype");
            return (Criteria) this;
        }

        public Criteria andFcamtypeIsNull() {
            addCriterion("FCAMType is null");
            return (Criteria) this;
        }

        public Criteria andFcamtypeIsNotNull() {
            addCriterion("FCAMType is not null");
            return (Criteria) this;
        }

        public Criteria andFcamtypeEqualTo(Integer value) {
            addCriterion("FCAMType =", value, "fcamtype");
            return (Criteria) this;
        }

        public Criteria andFcamtypeNotEqualTo(Integer value) {
            addCriterion("FCAMType <>", value, "fcamtype");
            return (Criteria) this;
        }

        public Criteria andFcamtypeGreaterThan(Integer value) {
            addCriterion("FCAMType >", value, "fcamtype");
            return (Criteria) this;
        }

        public Criteria andFcamtypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("FCAMType >=", value, "fcamtype");
            return (Criteria) this;
        }

        public Criteria andFcamtypeLessThan(Integer value) {
            addCriterion("FCAMType <", value, "fcamtype");
            return (Criteria) this;
        }

        public Criteria andFcamtypeLessThanOrEqualTo(Integer value) {
            addCriterion("FCAMType <=", value, "fcamtype");
            return (Criteria) this;
        }

        public Criteria andFcamtypeIn(List<Integer> values) {
            addCriterion("FCAMType in", values, "fcamtype");
            return (Criteria) this;
        }

        public Criteria andFcamtypeNotIn(List<Integer> values) {
            addCriterion("FCAMType not in", values, "fcamtype");
            return (Criteria) this;
        }

        public Criteria andFcamtypeBetween(Integer value1, Integer value2) {
            addCriterion("FCAMType between", value1, value2, "fcamtype");
            return (Criteria) this;
        }

        public Criteria andFcamtypeNotBetween(Integer value1, Integer value2) {
            addCriterion("FCAMType not between", value1, value2, "fcamtype");
            return (Criteria) this;
        }
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：student_capability_evaluation..t_course_standard
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * TCourseStandard
     * Criterion
     * 数据库表：student_capability_evaluation..t_course_standard
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