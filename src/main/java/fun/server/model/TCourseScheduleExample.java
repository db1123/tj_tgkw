package fun.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TCourseScheduleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_schedule
     * @mbg.generated
     */
    public TCourseScheduleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * 设置排序字段
     * student_capability_evaluation..t_course_schedule
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * student_capability_evaluation..t_course_schedule
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * student_capability_evaluation..t_course_schedule
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * student_capability_evaluation..t_course_schedule
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * student_capability_evaluation..t_course_schedule
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * student_capability_evaluation..t_course_schedule
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * student_capability_evaluation..t_course_schedule
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
     * student_capability_evaluation..t_course_schedule
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
     * student_capability_evaluation..t_course_schedule
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * student_capability_evaluation..t_course_schedule
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TCourseSchedule
     * GeneratedCriteria
     * 数据库表：student_capability_evaluation..t_course_schedule
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

        public Criteria andFcoidIsNull() {
            addCriterion("FCOID is null");
            return (Criteria) this;
        }

        public Criteria andFcoidIsNotNull() {
            addCriterion("FCOID is not null");
            return (Criteria) this;
        }

        public Criteria andFcoidEqualTo(Long value) {
            addCriterion("FCOID =", value, "fcoid");
            return (Criteria) this;
        }

        public Criteria andFcoidNotEqualTo(Long value) {
            addCriterion("FCOID <>", value, "fcoid");
            return (Criteria) this;
        }

        public Criteria andFcoidGreaterThan(Long value) {
            addCriterion("FCOID >", value, "fcoid");
            return (Criteria) this;
        }

        public Criteria andFcoidGreaterThanOrEqualTo(Long value) {
            addCriterion("FCOID >=", value, "fcoid");
            return (Criteria) this;
        }

        public Criteria andFcoidLessThan(Long value) {
            addCriterion("FCOID <", value, "fcoid");
            return (Criteria) this;
        }

        public Criteria andFcoidLessThanOrEqualTo(Long value) {
            addCriterion("FCOID <=", value, "fcoid");
            return (Criteria) this;
        }

        public Criteria andFcoidIn(List<Long> values) {
            addCriterion("FCOID in", values, "fcoid");
            return (Criteria) this;
        }

        public Criteria andFcoidNotIn(List<Long> values) {
            addCriterion("FCOID not in", values, "fcoid");
            return (Criteria) this;
        }

        public Criteria andFcoidBetween(Long value1, Long value2) {
            addCriterion("FCOID between", value1, value2, "fcoid");
            return (Criteria) this;
        }

        public Criteria andFcoidNotBetween(Long value1, Long value2) {
            addCriterion("FCOID not between", value1, value2, "fcoid");
            return (Criteria) this;
        }

        public Criteria andFcrmidIsNull() {
            addCriterion("FCRMID is null");
            return (Criteria) this;
        }

        public Criteria andFcrmidIsNotNull() {
            addCriterion("FCRMID is not null");
            return (Criteria) this;
        }

        public Criteria andFcrmidEqualTo(Long value) {
            addCriterion("FCRMID =", value, "fcrmid");
            return (Criteria) this;
        }

        public Criteria andFcrmidNotEqualTo(Long value) {
            addCriterion("FCRMID <>", value, "fcrmid");
            return (Criteria) this;
        }

        public Criteria andFcrmidGreaterThan(Long value) {
            addCriterion("FCRMID >", value, "fcrmid");
            return (Criteria) this;
        }

        public Criteria andFcrmidGreaterThanOrEqualTo(Long value) {
            addCriterion("FCRMID >=", value, "fcrmid");
            return (Criteria) this;
        }

        public Criteria andFcrmidLessThan(Long value) {
            addCriterion("FCRMID <", value, "fcrmid");
            return (Criteria) this;
        }

        public Criteria andFcrmidLessThanOrEqualTo(Long value) {
            addCriterion("FCRMID <=", value, "fcrmid");
            return (Criteria) this;
        }

        public Criteria andFcrmidIn(List<Long> values) {
            addCriterion("FCRMID in", values, "fcrmid");
            return (Criteria) this;
        }

        public Criteria andFcrmidNotIn(List<Long> values) {
            addCriterion("FCRMID not in", values, "fcrmid");
            return (Criteria) this;
        }

        public Criteria andFcrmidBetween(Long value1, Long value2) {
            addCriterion("FCRMID between", value1, value2, "fcrmid");
            return (Criteria) this;
        }

        public Criteria andFcrmidNotBetween(Long value1, Long value2) {
            addCriterion("FCRMID not between", value1, value2, "fcrmid");
            return (Criteria) this;
        }

        public Criteria andFweekdayIsNull() {
            addCriterion("FWeekday is null");
            return (Criteria) this;
        }

        public Criteria andFweekdayIsNotNull() {
            addCriterion("FWeekday is not null");
            return (Criteria) this;
        }

        public Criteria andFweekdayEqualTo(Integer value) {
            addCriterion("FWeekday =", value, "fweekday");
            return (Criteria) this;
        }

        public Criteria andFweekdayNotEqualTo(Integer value) {
            addCriterion("FWeekday <>", value, "fweekday");
            return (Criteria) this;
        }

        public Criteria andFweekdayGreaterThan(Integer value) {
            addCriterion("FWeekday >", value, "fweekday");
            return (Criteria) this;
        }

        public Criteria andFweekdayGreaterThanOrEqualTo(Integer value) {
            addCriterion("FWeekday >=", value, "fweekday");
            return (Criteria) this;
        }

        public Criteria andFweekdayLessThan(Integer value) {
            addCriterion("FWeekday <", value, "fweekday");
            return (Criteria) this;
        }

        public Criteria andFweekdayLessThanOrEqualTo(Integer value) {
            addCriterion("FWeekday <=", value, "fweekday");
            return (Criteria) this;
        }

        public Criteria andFweekdayIn(List<Integer> values) {
            addCriterion("FWeekday in", values, "fweekday");
            return (Criteria) this;
        }

        public Criteria andFweekdayNotIn(List<Integer> values) {
            addCriterion("FWeekday not in", values, "fweekday");
            return (Criteria) this;
        }

        public Criteria andFweekdayBetween(Integer value1, Integer value2) {
            addCriterion("FWeekday between", value1, value2, "fweekday");
            return (Criteria) this;
        }

        public Criteria andFweekdayNotBetween(Integer value1, Integer value2) {
            addCriterion("FWeekday not between", value1, value2, "fweekday");
            return (Criteria) this;
        }

        public Criteria andFstarttimeIsNull() {
            addCriterion("FStartTime is null");
            return (Criteria) this;
        }

        public Criteria andFstarttimeIsNotNull() {
            addCriterion("FStartTime is not null");
            return (Criteria) this;
        }

        public Criteria andFstarttimeEqualTo(Date value) {
            addCriterion("FStartTime =", value, "fstarttime");
            return (Criteria) this;
        }

        public Criteria andFstarttimeNotEqualTo(Date value) {
            addCriterion("FStartTime <>", value, "fstarttime");
            return (Criteria) this;
        }

        public Criteria andFstarttimeGreaterThan(Date value) {
            addCriterion("FStartTime >", value, "fstarttime");
            return (Criteria) this;
        }

        public Criteria andFstarttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("FStartTime >=", value, "fstarttime");
            return (Criteria) this;
        }

        public Criteria andFstarttimeLessThan(Date value) {
            addCriterion("FStartTime <", value, "fstarttime");
            return (Criteria) this;
        }

        public Criteria andFstarttimeLessThanOrEqualTo(Date value) {
            addCriterion("FStartTime <=", value, "fstarttime");
            return (Criteria) this;
        }

        public Criteria andFstarttimeIn(List<Date> values) {
            addCriterion("FStartTime in", values, "fstarttime");
            return (Criteria) this;
        }

        public Criteria andFstarttimeNotIn(List<Date> values) {
            addCriterion("FStartTime not in", values, "fstarttime");
            return (Criteria) this;
        }

        public Criteria andFstarttimeBetween(Date value1, Date value2) {
            addCriterion("FStartTime between", value1, value2, "fstarttime");
            return (Criteria) this;
        }

        public Criteria andFstarttimeNotBetween(Date value1, Date value2) {
            addCriterion("FStartTime not between", value1, value2, "fstarttime");
            return (Criteria) this;
        }

        public Criteria andFendtimeIsNull() {
            addCriterion("FEndTime is null");
            return (Criteria) this;
        }

        public Criteria andFendtimeIsNotNull() {
            addCriterion("FEndTime is not null");
            return (Criteria) this;
        }

        public Criteria andFendtimeEqualTo(Date value) {
            addCriterion("FEndTime =", value, "fendtime");
            return (Criteria) this;
        }

        public Criteria andFendtimeNotEqualTo(Date value) {
            addCriterion("FEndTime <>", value, "fendtime");
            return (Criteria) this;
        }

        public Criteria andFendtimeGreaterThan(Date value) {
            addCriterion("FEndTime >", value, "fendtime");
            return (Criteria) this;
        }

        public Criteria andFendtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("FEndTime >=", value, "fendtime");
            return (Criteria) this;
        }

        public Criteria andFendtimeLessThan(Date value) {
            addCriterion("FEndTime <", value, "fendtime");
            return (Criteria) this;
        }

        public Criteria andFendtimeLessThanOrEqualTo(Date value) {
            addCriterion("FEndTime <=", value, "fendtime");
            return (Criteria) this;
        }

        public Criteria andFendtimeIn(List<Date> values) {
            addCriterion("FEndTime in", values, "fendtime");
            return (Criteria) this;
        }

        public Criteria andFendtimeNotIn(List<Date> values) {
            addCriterion("FEndTime not in", values, "fendtime");
            return (Criteria) this;
        }

        public Criteria andFendtimeBetween(Date value1, Date value2) {
            addCriterion("FEndTime between", value1, value2, "fendtime");
            return (Criteria) this;
        }

        public Criteria andFendtimeNotBetween(Date value1, Date value2) {
            addCriterion("FEndTime not between", value1, value2, "fendtime");
            return (Criteria) this;
        }

        public Criteria andFsessionIsNull() {
            addCriterion("FSession is null");
            return (Criteria) this;
        }

        public Criteria andFsessionIsNotNull() {
            addCriterion("FSession is not null");
            return (Criteria) this;
        }

        public Criteria andFsessionEqualTo(String value) {
            addCriterion("FSession =", value, "fsession");
            return (Criteria) this;
        }

        public Criteria andFsessionNotEqualTo(String value) {
            addCriterion("FSession <>", value, "fsession");
            return (Criteria) this;
        }

        public Criteria andFsessionGreaterThan(String value) {
            addCriterion("FSession >", value, "fsession");
            return (Criteria) this;
        }

        public Criteria andFsessionGreaterThanOrEqualTo(String value) {
            addCriterion("FSession >=", value, "fsession");
            return (Criteria) this;
        }

        public Criteria andFsessionLessThan(String value) {
            addCriterion("FSession <", value, "fsession");
            return (Criteria) this;
        }

        public Criteria andFsessionLessThanOrEqualTo(String value) {
            addCriterion("FSession <=", value, "fsession");
            return (Criteria) this;
        }

        public Criteria andFsessionLike(String value) {
            addCriterion("FSession like", value, "fsession");
            return (Criteria) this;
        }

        public Criteria andFsessionNotLike(String value) {
            addCriterion("FSession not like", value, "fsession");
            return (Criteria) this;
        }

        public Criteria andFsessionIn(List<String> values) {
            addCriterion("FSession in", values, "fsession");
            return (Criteria) this;
        }

        public Criteria andFsessionNotIn(List<String> values) {
            addCriterion("FSession not in", values, "fsession");
            return (Criteria) this;
        }

        public Criteria andFsessionBetween(String value1, String value2) {
            addCriterion("FSession between", value1, value2, "fsession");
            return (Criteria) this;
        }

        public Criteria andFsessionNotBetween(String value1, String value2) {
            addCriterion("FSession not between", value1, value2, "fsession");
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
     * 数据库表：student_capability_evaluation..t_course_schedule
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * TCourseSchedule
     * Criterion
     * 数据库表：student_capability_evaluation..t_course_schedule
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