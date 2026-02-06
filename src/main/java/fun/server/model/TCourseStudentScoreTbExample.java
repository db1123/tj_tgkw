package fun.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TCourseStudentScoreTbExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_student_score_tb
     * @mbg.generated
     */
    public TCourseStudentScoreTbExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * 设置排序字段
     * student_capability_evaluation..t_course_student_score_tb
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * student_capability_evaluation..t_course_student_score_tb
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * student_capability_evaluation..t_course_student_score_tb
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * student_capability_evaluation..t_course_student_score_tb
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * student_capability_evaluation..t_course_student_score_tb
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * student_capability_evaluation..t_course_student_score_tb
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * student_capability_evaluation..t_course_student_score_tb
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
     * student_capability_evaluation..t_course_student_score_tb
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
     * student_capability_evaluation..t_course_student_score_tb
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * student_capability_evaluation..t_course_student_score_tb
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TCourseStudentScoreTb
     * GeneratedCriteria
     * 数据库表：student_capability_evaluation..t_course_student_score_tb
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

        public Criteria andFcoursestudentidIsNull() {
            addCriterion("FCourseStudentID is null");
            return (Criteria) this;
        }

        public Criteria andFcoursestudentidIsNotNull() {
            addCriterion("FCourseStudentID is not null");
            return (Criteria) this;
        }

        public Criteria andFcoursestudentidEqualTo(Long value) {
            addCriterion("FCourseStudentID =", value, "fcoursestudentid");
            return (Criteria) this;
        }

        public Criteria andFcoursestudentidNotEqualTo(Long value) {
            addCriterion("FCourseStudentID <>", value, "fcoursestudentid");
            return (Criteria) this;
        }

        public Criteria andFcoursestudentidGreaterThan(Long value) {
            addCriterion("FCourseStudentID >", value, "fcoursestudentid");
            return (Criteria) this;
        }

        public Criteria andFcoursestudentidGreaterThanOrEqualTo(Long value) {
            addCriterion("FCourseStudentID >=", value, "fcoursestudentid");
            return (Criteria) this;
        }

        public Criteria andFcoursestudentidLessThan(Long value) {
            addCriterion("FCourseStudentID <", value, "fcoursestudentid");
            return (Criteria) this;
        }

        public Criteria andFcoursestudentidLessThanOrEqualTo(Long value) {
            addCriterion("FCourseStudentID <=", value, "fcoursestudentid");
            return (Criteria) this;
        }

        public Criteria andFcoursestudentidIn(List<Long> values) {
            addCriterion("FCourseStudentID in", values, "fcoursestudentid");
            return (Criteria) this;
        }

        public Criteria andFcoursestudentidNotIn(List<Long> values) {
            addCriterion("FCourseStudentID not in", values, "fcoursestudentid");
            return (Criteria) this;
        }

        public Criteria andFcoursestudentidBetween(Long value1, Long value2) {
            addCriterion("FCourseStudentID between", value1, value2, "fcoursestudentid");
            return (Criteria) this;
        }

        public Criteria andFcoursestudentidNotBetween(Long value1, Long value2) {
            addCriterion("FCourseStudentID not between", value1, value2, "fcoursestudentid");
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

        public Criteria andFconditionidIsNull() {
            addCriterion("FConditionID is null");
            return (Criteria) this;
        }

        public Criteria andFconditionidIsNotNull() {
            addCriterion("FConditionID is not null");
            return (Criteria) this;
        }

        public Criteria andFconditionidEqualTo(Long value) {
            addCriterion("FConditionID =", value, "fconditionid");
            return (Criteria) this;
        }

        public Criteria andFconditionidNotEqualTo(Long value) {
            addCriterion("FConditionID <>", value, "fconditionid");
            return (Criteria) this;
        }

        public Criteria andFconditionidGreaterThan(Long value) {
            addCriterion("FConditionID >", value, "fconditionid");
            return (Criteria) this;
        }

        public Criteria andFconditionidGreaterThanOrEqualTo(Long value) {
            addCriterion("FConditionID >=", value, "fconditionid");
            return (Criteria) this;
        }

        public Criteria andFconditionidLessThan(Long value) {
            addCriterion("FConditionID <", value, "fconditionid");
            return (Criteria) this;
        }

        public Criteria andFconditionidLessThanOrEqualTo(Long value) {
            addCriterion("FConditionID <=", value, "fconditionid");
            return (Criteria) this;
        }

        public Criteria andFconditionidIn(List<Long> values) {
            addCriterion("FConditionID in", values, "fconditionid");
            return (Criteria) this;
        }

        public Criteria andFconditionidNotIn(List<Long> values) {
            addCriterion("FConditionID not in", values, "fconditionid");
            return (Criteria) this;
        }

        public Criteria andFconditionidBetween(Long value1, Long value2) {
            addCriterion("FConditionID between", value1, value2, "fconditionid");
            return (Criteria) this;
        }

        public Criteria andFconditionidNotBetween(Long value1, Long value2) {
            addCriterion("FConditionID not between", value1, value2, "fconditionid");
            return (Criteria) this;
        }

        public Criteria andFacamidIsNull() {
            addCriterion("FACAMID is null");
            return (Criteria) this;
        }

        public Criteria andFacamidIsNotNull() {
            addCriterion("FACAMID is not null");
            return (Criteria) this;
        }

        public Criteria andFacamidEqualTo(Long value) {
            addCriterion("FACAMID =", value, "facamid");
            return (Criteria) this;
        }

        public Criteria andFacamidNotEqualTo(Long value) {
            addCriterion("FACAMID <>", value, "facamid");
            return (Criteria) this;
        }

        public Criteria andFacamidGreaterThan(Long value) {
            addCriterion("FACAMID >", value, "facamid");
            return (Criteria) this;
        }

        public Criteria andFacamidGreaterThanOrEqualTo(Long value) {
            addCriterion("FACAMID >=", value, "facamid");
            return (Criteria) this;
        }

        public Criteria andFacamidLessThan(Long value) {
            addCriterion("FACAMID <", value, "facamid");
            return (Criteria) this;
        }

        public Criteria andFacamidLessThanOrEqualTo(Long value) {
            addCriterion("FACAMID <=", value, "facamid");
            return (Criteria) this;
        }

        public Criteria andFacamidIn(List<Long> values) {
            addCriterion("FACAMID in", values, "facamid");
            return (Criteria) this;
        }

        public Criteria andFacamidNotIn(List<Long> values) {
            addCriterion("FACAMID not in", values, "facamid");
            return (Criteria) this;
        }

        public Criteria andFacamidBetween(Long value1, Long value2) {
            addCriterion("FACAMID between", value1, value2, "facamid");
            return (Criteria) this;
        }

        public Criteria andFacamidNotBetween(Long value1, Long value2) {
            addCriterion("FACAMID not between", value1, value2, "facamid");
            return (Criteria) this;
        }

        public Criteria andFcoursestandardsidIsNull() {
            addCriterion("FCourseStandardsID is null");
            return (Criteria) this;
        }

        public Criteria andFcoursestandardsidIsNotNull() {
            addCriterion("FCourseStandardsID is not null");
            return (Criteria) this;
        }

        public Criteria andFcoursestandardsidEqualTo(Long value) {
            addCriterion("FCourseStandardsID =", value, "fcoursestandardsid");
            return (Criteria) this;
        }

        public Criteria andFcoursestandardsidNotEqualTo(Long value) {
            addCriterion("FCourseStandardsID <>", value, "fcoursestandardsid");
            return (Criteria) this;
        }

        public Criteria andFcoursestandardsidGreaterThan(Long value) {
            addCriterion("FCourseStandardsID >", value, "fcoursestandardsid");
            return (Criteria) this;
        }

        public Criteria andFcoursestandardsidGreaterThanOrEqualTo(Long value) {
            addCriterion("FCourseStandardsID >=", value, "fcoursestandardsid");
            return (Criteria) this;
        }

        public Criteria andFcoursestandardsidLessThan(Long value) {
            addCriterion("FCourseStandardsID <", value, "fcoursestandardsid");
            return (Criteria) this;
        }

        public Criteria andFcoursestandardsidLessThanOrEqualTo(Long value) {
            addCriterion("FCourseStandardsID <=", value, "fcoursestandardsid");
            return (Criteria) this;
        }

        public Criteria andFcoursestandardsidIn(List<Long> values) {
            addCriterion("FCourseStandardsID in", values, "fcoursestandardsid");
            return (Criteria) this;
        }

        public Criteria andFcoursestandardsidNotIn(List<Long> values) {
            addCriterion("FCourseStandardsID not in", values, "fcoursestandardsid");
            return (Criteria) this;
        }

        public Criteria andFcoursestandardsidBetween(Long value1, Long value2) {
            addCriterion("FCourseStandardsID between", value1, value2, "fcoursestandardsid");
            return (Criteria) this;
        }

        public Criteria andFcoursestandardsidNotBetween(Long value1, Long value2) {
            addCriterion("FCourseStandardsID not between", value1, value2, "fcoursestandardsid");
            return (Criteria) this;
        }

        public Criteria andFcontentIsNull() {
            addCriterion("FContent is null");
            return (Criteria) this;
        }

        public Criteria andFcontentIsNotNull() {
            addCriterion("FContent is not null");
            return (Criteria) this;
        }

        public Criteria andFcontentEqualTo(String value) {
            addCriterion("FContent =", value, "fcontent");
            return (Criteria) this;
        }

        public Criteria andFcontentNotEqualTo(String value) {
            addCriterion("FContent <>", value, "fcontent");
            return (Criteria) this;
        }

        public Criteria andFcontentGreaterThan(String value) {
            addCriterion("FContent >", value, "fcontent");
            return (Criteria) this;
        }

        public Criteria andFcontentGreaterThanOrEqualTo(String value) {
            addCriterion("FContent >=", value, "fcontent");
            return (Criteria) this;
        }

        public Criteria andFcontentLessThan(String value) {
            addCriterion("FContent <", value, "fcontent");
            return (Criteria) this;
        }

        public Criteria andFcontentLessThanOrEqualTo(String value) {
            addCriterion("FContent <=", value, "fcontent");
            return (Criteria) this;
        }

        public Criteria andFcontentLike(String value) {
            addCriterion("FContent like", value, "fcontent");
            return (Criteria) this;
        }

        public Criteria andFcontentNotLike(String value) {
            addCriterion("FContent not like", value, "fcontent");
            return (Criteria) this;
        }

        public Criteria andFcontentIn(List<String> values) {
            addCriterion("FContent in", values, "fcontent");
            return (Criteria) this;
        }

        public Criteria andFcontentNotIn(List<String> values) {
            addCriterion("FContent not in", values, "fcontent");
            return (Criteria) this;
        }

        public Criteria andFcontentBetween(String value1, String value2) {
            addCriterion("FContent between", value1, value2, "fcontent");
            return (Criteria) this;
        }

        public Criteria andFcontentNotBetween(String value1, String value2) {
            addCriterion("FContent not between", value1, value2, "fcontent");
            return (Criteria) this;
        }

        public Criteria andFscoreIsNull() {
            addCriterion("FScore is null");
            return (Criteria) this;
        }

        public Criteria andFscoreIsNotNull() {
            addCriterion("FScore is not null");
            return (Criteria) this;
        }

        public Criteria andFscoreEqualTo(Float value) {
            addCriterion("FScore =", value, "fscore");
            return (Criteria) this;
        }

        public Criteria andFscoreNotEqualTo(Float value) {
            addCriterion("FScore <>", value, "fscore");
            return (Criteria) this;
        }

        public Criteria andFscoreGreaterThan(Float value) {
            addCriterion("FScore >", value, "fscore");
            return (Criteria) this;
        }

        public Criteria andFscoreGreaterThanOrEqualTo(Float value) {
            addCriterion("FScore >=", value, "fscore");
            return (Criteria) this;
        }

        public Criteria andFscoreLessThan(Float value) {
            addCriterion("FScore <", value, "fscore");
            return (Criteria) this;
        }

        public Criteria andFscoreLessThanOrEqualTo(Float value) {
            addCriterion("FScore <=", value, "fscore");
            return (Criteria) this;
        }

        public Criteria andFscoreIn(List<Float> values) {
            addCriterion("FScore in", values, "fscore");
            return (Criteria) this;
        }

        public Criteria andFscoreNotIn(List<Float> values) {
            addCriterion("FScore not in", values, "fscore");
            return (Criteria) this;
        }

        public Criteria andFscoreBetween(Float value1, Float value2) {
            addCriterion("FScore between", value1, value2, "fscore");
            return (Criteria) this;
        }

        public Criteria andFscoreNotBetween(Float value1, Float value2) {
            addCriterion("FScore not between", value1, value2, "fscore");
            return (Criteria) this;
        }
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：student_capability_evaluation..t_course_student_score_tb
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * TCourseStudentScoreTb
     * Criterion
     * 数据库表：student_capability_evaluation..t_course_student_score_tb
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