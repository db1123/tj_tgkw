package fun.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TCourseStudentScoreExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_student_score
     * @mbg.generated
     */
    public TCourseStudentScoreExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * 设置排序字段
     * student_capability_evaluation..t_course_student_score
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * student_capability_evaluation..t_course_student_score
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * student_capability_evaluation..t_course_student_score
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * student_capability_evaluation..t_course_student_score
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * student_capability_evaluation..t_course_student_score
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * student_capability_evaluation..t_course_student_score
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * student_capability_evaluation..t_course_student_score
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
     * student_capability_evaluation..t_course_student_score
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
     * student_capability_evaluation..t_course_student_score
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * student_capability_evaluation..t_course_student_score
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TCourseStudentScore
     * GeneratedCriteria
     * 数据库表：student_capability_evaluation..t_course_student_score
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

        public Criteria andFcamidIsNull() {
            addCriterion("FCAMID is null");
            return (Criteria) this;
        }

        public Criteria andFcamidIsNotNull() {
            addCriterion("FCAMID is not null");
            return (Criteria) this;
        }

        public Criteria andFcamidEqualTo(Long value) {
            addCriterion("FCAMID =", value, "fcamid");
            return (Criteria) this;
        }

        public Criteria andFcamidNotEqualTo(Long value) {
            addCriterion("FCAMID <>", value, "fcamid");
            return (Criteria) this;
        }

        public Criteria andFcamidGreaterThan(Long value) {
            addCriterion("FCAMID >", value, "fcamid");
            return (Criteria) this;
        }

        public Criteria andFcamidGreaterThanOrEqualTo(Long value) {
            addCriterion("FCAMID >=", value, "fcamid");
            return (Criteria) this;
        }

        public Criteria andFcamidLessThan(Long value) {
            addCriterion("FCAMID <", value, "fcamid");
            return (Criteria) this;
        }

        public Criteria andFcamidLessThanOrEqualTo(Long value) {
            addCriterion("FCAMID <=", value, "fcamid");
            return (Criteria) this;
        }

        public Criteria andFcamidIn(List<Long> values) {
            addCriterion("FCAMID in", values, "fcamid");
            return (Criteria) this;
        }

        public Criteria andFcamidNotIn(List<Long> values) {
            addCriterion("FCAMID not in", values, "fcamid");
            return (Criteria) this;
        }

        public Criteria andFcamidBetween(Long value1, Long value2) {
            addCriterion("FCAMID between", value1, value2, "fcamid");
            return (Criteria) this;
        }

        public Criteria andFcamidNotBetween(Long value1, Long value2) {
            addCriterion("FCAMID not between", value1, value2, "fcamid");
            return (Criteria) this;
        }

        public Criteria andFstudentidIsNull() {
            addCriterion("FStudentID is null");
            return (Criteria) this;
        }

        public Criteria andFstudentidIsNotNull() {
            addCriterion("FStudentID is not null");
            return (Criteria) this;
        }

        public Criteria andFstudentidEqualTo(Long value) {
            addCriterion("FStudentID =", value, "fstudentid");
            return (Criteria) this;
        }

        public Criteria andFstudentidNotEqualTo(Long value) {
            addCriterion("FStudentID <>", value, "fstudentid");
            return (Criteria) this;
        }

        public Criteria andFstudentidGreaterThan(Long value) {
            addCriterion("FStudentID >", value, "fstudentid");
            return (Criteria) this;
        }

        public Criteria andFstudentidGreaterThanOrEqualTo(Long value) {
            addCriterion("FStudentID >=", value, "fstudentid");
            return (Criteria) this;
        }

        public Criteria andFstudentidLessThan(Long value) {
            addCriterion("FStudentID <", value, "fstudentid");
            return (Criteria) this;
        }

        public Criteria andFstudentidLessThanOrEqualTo(Long value) {
            addCriterion("FStudentID <=", value, "fstudentid");
            return (Criteria) this;
        }

        public Criteria andFstudentidIn(List<Long> values) {
            addCriterion("FStudentID in", values, "fstudentid");
            return (Criteria) this;
        }

        public Criteria andFstudentidNotIn(List<Long> values) {
            addCriterion("FStudentID not in", values, "fstudentid");
            return (Criteria) this;
        }

        public Criteria andFstudentidBetween(Long value1, Long value2) {
            addCriterion("FStudentID between", value1, value2, "fstudentid");
            return (Criteria) this;
        }

        public Criteria andFstudentidNotBetween(Long value1, Long value2) {
            addCriterion("FStudentID not between", value1, value2, "fstudentid");
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

        public Criteria andFconditionidEqualTo(String value) {
            addCriterion("FConditionID =", value, "fconditionid");
            return (Criteria) this;
        }

        public Criteria andFconditionidNotEqualTo(String value) {
            addCriterion("FConditionID <>", value, "fconditionid");
            return (Criteria) this;
        }

        public Criteria andFconditionidGreaterThan(String value) {
            addCriterion("FConditionID >", value, "fconditionid");
            return (Criteria) this;
        }

        public Criteria andFconditionidGreaterThanOrEqualTo(String value) {
            addCriterion("FConditionID >=", value, "fconditionid");
            return (Criteria) this;
        }

        public Criteria andFconditionidLessThan(String value) {
            addCriterion("FConditionID <", value, "fconditionid");
            return (Criteria) this;
        }

        public Criteria andFconditionidLessThanOrEqualTo(String value) {
            addCriterion("FConditionID <=", value, "fconditionid");
            return (Criteria) this;
        }

        public Criteria andFconditionidLike(String value) {
            addCriterion("FConditionID like", value, "fconditionid");
            return (Criteria) this;
        }

        public Criteria andFconditionidNotLike(String value) {
            addCriterion("FConditionID not like", value, "fconditionid");
            return (Criteria) this;
        }

        public Criteria andFconditionidIn(List<String> values) {
            addCriterion("FConditionID in", values, "fconditionid");
            return (Criteria) this;
        }

        public Criteria andFconditionidNotIn(List<String> values) {
            addCriterion("FConditionID not in", values, "fconditionid");
            return (Criteria) this;
        }

        public Criteria andFconditionidBetween(String value1, String value2) {
            addCriterion("FConditionID between", value1, value2, "fconditionid");
            return (Criteria) this;
        }

        public Criteria andFconditionidNotBetween(String value1, String value2) {
            addCriterion("FConditionID not between", value1, value2, "fconditionid");
            return (Criteria) this;
        }

        public Criteria andFzscoreIsNull() {
            addCriterion("FZScore is null");
            return (Criteria) this;
        }

        public Criteria andFzscoreIsNotNull() {
            addCriterion("FZScore is not null");
            return (Criteria) this;
        }

        public Criteria andFzscoreEqualTo(Float value) {
            addCriterion("FZScore =", value, "fzscore");
            return (Criteria) this;
        }

        public Criteria andFzscoreNotEqualTo(Float value) {
            addCriterion("FZScore <>", value, "fzscore");
            return (Criteria) this;
        }

        public Criteria andFzscoreGreaterThan(Float value) {
            addCriterion("FZScore >", value, "fzscore");
            return (Criteria) this;
        }

        public Criteria andFzscoreGreaterThanOrEqualTo(Float value) {
            addCriterion("FZScore >=", value, "fzscore");
            return (Criteria) this;
        }

        public Criteria andFzscoreLessThan(Float value) {
            addCriterion("FZScore <", value, "fzscore");
            return (Criteria) this;
        }

        public Criteria andFzscoreLessThanOrEqualTo(Float value) {
            addCriterion("FZScore <=", value, "fzscore");
            return (Criteria) this;
        }

        public Criteria andFzscoreIn(List<Float> values) {
            addCriterion("FZScore in", values, "fzscore");
            return (Criteria) this;
        }

        public Criteria andFzscoreNotIn(List<Float> values) {
            addCriterion("FZScore not in", values, "fzscore");
            return (Criteria) this;
        }

        public Criteria andFzscoreBetween(Float value1, Float value2) {
            addCriterion("FZScore between", value1, value2, "fzscore");
            return (Criteria) this;
        }

        public Criteria andFzscoreNotBetween(Float value1, Float value2) {
            addCriterion("FZScore not between", value1, value2, "fzscore");
            return (Criteria) this;
        }

        public Criteria andFstudentscoreIsNull() {
            addCriterion("FStudentScore is null");
            return (Criteria) this;
        }

        public Criteria andFstudentscoreIsNotNull() {
            addCriterion("FStudentScore is not null");
            return (Criteria) this;
        }

        public Criteria andFstudentscoreEqualTo(Float value) {
            addCriterion("FStudentScore =", value, "fstudentscore");
            return (Criteria) this;
        }

        public Criteria andFstudentscoreNotEqualTo(Float value) {
            addCriterion("FStudentScore <>", value, "fstudentscore");
            return (Criteria) this;
        }

        public Criteria andFstudentscoreGreaterThan(Float value) {
            addCriterion("FStudentScore >", value, "fstudentscore");
            return (Criteria) this;
        }

        public Criteria andFstudentscoreGreaterThanOrEqualTo(Float value) {
            addCriterion("FStudentScore >=", value, "fstudentscore");
            return (Criteria) this;
        }

        public Criteria andFstudentscoreLessThan(Float value) {
            addCriterion("FStudentScore <", value, "fstudentscore");
            return (Criteria) this;
        }

        public Criteria andFstudentscoreLessThanOrEqualTo(Float value) {
            addCriterion("FStudentScore <=", value, "fstudentscore");
            return (Criteria) this;
        }

        public Criteria andFstudentscoreIn(List<Float> values) {
            addCriterion("FStudentScore in", values, "fstudentscore");
            return (Criteria) this;
        }

        public Criteria andFstudentscoreNotIn(List<Float> values) {
            addCriterion("FStudentScore not in", values, "fstudentscore");
            return (Criteria) this;
        }

        public Criteria andFstudentscoreBetween(Float value1, Float value2) {
            addCriterion("FStudentScore between", value1, value2, "fstudentscore");
            return (Criteria) this;
        }

        public Criteria andFstudentscoreNotBetween(Float value1, Float value2) {
            addCriterion("FStudentScore not between", value1, value2, "fstudentscore");
            return (Criteria) this;
        }

        public Criteria andFcontributionweightIsNull() {
            addCriterion("FContributionWeight is null");
            return (Criteria) this;
        }

        public Criteria andFcontributionweightIsNotNull() {
            addCriterion("FContributionWeight is not null");
            return (Criteria) this;
        }

        public Criteria andFcontributionweightEqualTo(Float value) {
            addCriterion("FContributionWeight =", value, "fcontributionweight");
            return (Criteria) this;
        }

        public Criteria andFcontributionweightNotEqualTo(Float value) {
            addCriterion("FContributionWeight <>", value, "fcontributionweight");
            return (Criteria) this;
        }

        public Criteria andFcontributionweightGreaterThan(Float value) {
            addCriterion("FContributionWeight >", value, "fcontributionweight");
            return (Criteria) this;
        }

        public Criteria andFcontributionweightGreaterThanOrEqualTo(Float value) {
            addCriterion("FContributionWeight >=", value, "fcontributionweight");
            return (Criteria) this;
        }

        public Criteria andFcontributionweightLessThan(Float value) {
            addCriterion("FContributionWeight <", value, "fcontributionweight");
            return (Criteria) this;
        }

        public Criteria andFcontributionweightLessThanOrEqualTo(Float value) {
            addCriterion("FContributionWeight <=", value, "fcontributionweight");
            return (Criteria) this;
        }

        public Criteria andFcontributionweightIn(List<Float> values) {
            addCriterion("FContributionWeight in", values, "fcontributionweight");
            return (Criteria) this;
        }

        public Criteria andFcontributionweightNotIn(List<Float> values) {
            addCriterion("FContributionWeight not in", values, "fcontributionweight");
            return (Criteria) this;
        }

        public Criteria andFcontributionweightBetween(Float value1, Float value2) {
            addCriterion("FContributionWeight between", value1, value2, "fcontributionweight");
            return (Criteria) this;
        }

        public Criteria andFcontributionweightNotBetween(Float value1, Float value2) {
            addCriterion("FContributionWeight not between", value1, value2, "fcontributionweight");
            return (Criteria) this;
        }
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：student_capability_evaluation..t_course_student_score
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * TCourseStudentScore
     * Criterion
     * 数据库表：student_capability_evaluation..t_course_student_score
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