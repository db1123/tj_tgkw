package fun.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TCourseOfferingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_offering
     * @mbg.generated
     */
    public TCourseOfferingExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * 设置排序字段
     * student_capability_evaluation..t_course_offering
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * student_capability_evaluation..t_course_offering
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * student_capability_evaluation..t_course_offering
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * student_capability_evaluation..t_course_offering
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * student_capability_evaluation..t_course_offering
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * student_capability_evaluation..t_course_offering
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * student_capability_evaluation..t_course_offering
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
     * student_capability_evaluation..t_course_offering
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
     * student_capability_evaluation..t_course_offering
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * student_capability_evaluation..t_course_offering
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TCourseOffering
     * GeneratedCriteria
     * 数据库表：student_capability_evaluation..t_course_offering
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

        public Criteria andFtpidIsNull() {
            addCriterion("FTPID is null");
            return (Criteria) this;
        }

        public Criteria andFtpidIsNotNull() {
            addCriterion("FTPID is not null");
            return (Criteria) this;
        }

        public Criteria andFtpidEqualTo(Long value) {
            addCriterion("FTPID =", value, "ftpid");
            return (Criteria) this;
        }

        public Criteria andFtpidNotEqualTo(Long value) {
            addCriterion("FTPID <>", value, "ftpid");
            return (Criteria) this;
        }

        public Criteria andFtpidGreaterThan(Long value) {
            addCriterion("FTPID >", value, "ftpid");
            return (Criteria) this;
        }

        public Criteria andFtpidGreaterThanOrEqualTo(Long value) {
            addCriterion("FTPID >=", value, "ftpid");
            return (Criteria) this;
        }

        public Criteria andFtpidLessThan(Long value) {
            addCriterion("FTPID <", value, "ftpid");
            return (Criteria) this;
        }

        public Criteria andFtpidLessThanOrEqualTo(Long value) {
            addCriterion("FTPID <=", value, "ftpid");
            return (Criteria) this;
        }

        public Criteria andFtpidIn(List<Long> values) {
            addCriterion("FTPID in", values, "ftpid");
            return (Criteria) this;
        }

        public Criteria andFtpidNotIn(List<Long> values) {
            addCriterion("FTPID not in", values, "ftpid");
            return (Criteria) this;
        }

        public Criteria andFtpidBetween(Long value1, Long value2) {
            addCriterion("FTPID between", value1, value2, "ftpid");
            return (Criteria) this;
        }

        public Criteria andFtpidNotBetween(Long value1, Long value2) {
            addCriterion("FTPID not between", value1, value2, "ftpid");
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

        public Criteria andFsemesterIsNull() {
            addCriterion("FSemester is null");
            return (Criteria) this;
        }

        public Criteria andFsemesterIsNotNull() {
            addCriterion("FSemester is not null");
            return (Criteria) this;
        }

        public Criteria andFsemesterEqualTo(Long value) {
            addCriterion("FSemester =", value, "fsemester");
            return (Criteria) this;
        }

        public Criteria andFsemesterNotEqualTo(Long value) {
            addCriterion("FSemester <>", value, "fsemester");
            return (Criteria) this;
        }

        public Criteria andFsemesterGreaterThan(Long value) {
            addCriterion("FSemester >", value, "fsemester");
            return (Criteria) this;
        }

        public Criteria andFsemesterGreaterThanOrEqualTo(Long value) {
            addCriterion("FSemester >=", value, "fsemester");
            return (Criteria) this;
        }

        public Criteria andFsemesterLessThan(Long value) {
            addCriterion("FSemester <", value, "fsemester");
            return (Criteria) this;
        }

        public Criteria andFsemesterLessThanOrEqualTo(Long value) {
            addCriterion("FSemester <=", value, "fsemester");
            return (Criteria) this;
        }

        public Criteria andFsemesterIn(List<Long> values) {
            addCriterion("FSemester in", values, "fsemester");
            return (Criteria) this;
        }

        public Criteria andFsemesterNotIn(List<Long> values) {
            addCriterion("FSemester not in", values, "fsemester");
            return (Criteria) this;
        }

        public Criteria andFsemesterBetween(Long value1, Long value2) {
            addCriterion("FSemester between", value1, value2, "fsemester");
            return (Criteria) this;
        }

        public Criteria andFsemesterNotBetween(Long value1, Long value2) {
            addCriterion("FSemester not between", value1, value2, "fsemester");
            return (Criteria) this;
        }

        public Criteria andFteacherIsNull() {
            addCriterion("FTeacher is null");
            return (Criteria) this;
        }

        public Criteria andFteacherIsNotNull() {
            addCriterion("FTeacher is not null");
            return (Criteria) this;
        }

        public Criteria andFteacherEqualTo(Long value) {
            addCriterion("FTeacher =", value, "fteacher");
            return (Criteria) this;
        }

        public Criteria andFteacherNotEqualTo(Long value) {
            addCriterion("FTeacher <>", value, "fteacher");
            return (Criteria) this;
        }

        public Criteria andFteacherGreaterThan(Long value) {
            addCriterion("FTeacher >", value, "fteacher");
            return (Criteria) this;
        }

        public Criteria andFteacherGreaterThanOrEqualTo(Long value) {
            addCriterion("FTeacher >=", value, "fteacher");
            return (Criteria) this;
        }

        public Criteria andFteacherLessThan(Long value) {
            addCriterion("FTeacher <", value, "fteacher");
            return (Criteria) this;
        }

        public Criteria andFteacherLessThanOrEqualTo(Long value) {
            addCriterion("FTeacher <=", value, "fteacher");
            return (Criteria) this;
        }

        public Criteria andFteacherIn(List<Long> values) {
            addCriterion("FTeacher in", values, "fteacher");
            return (Criteria) this;
        }

        public Criteria andFteacherNotIn(List<Long> values) {
            addCriterion("FTeacher not in", values, "fteacher");
            return (Criteria) this;
        }

        public Criteria andFteacherBetween(Long value1, Long value2) {
            addCriterion("FTeacher between", value1, value2, "fteacher");
            return (Criteria) this;
        }

        public Criteria andFteacherNotBetween(Long value1, Long value2) {
            addCriterion("FTeacher not between", value1, value2, "fteacher");
            return (Criteria) this;
        }

        public Criteria andFmaxcapacityIsNull() {
            addCriterion("FMaxCapacity is null");
            return (Criteria) this;
        }

        public Criteria andFmaxcapacityIsNotNull() {
            addCriterion("FMaxCapacity is not null");
            return (Criteria) this;
        }

        public Criteria andFmaxcapacityEqualTo(Integer value) {
            addCriterion("FMaxCapacity =", value, "fmaxcapacity");
            return (Criteria) this;
        }

        public Criteria andFmaxcapacityNotEqualTo(Integer value) {
            addCriterion("FMaxCapacity <>", value, "fmaxcapacity");
            return (Criteria) this;
        }

        public Criteria andFmaxcapacityGreaterThan(Integer value) {
            addCriterion("FMaxCapacity >", value, "fmaxcapacity");
            return (Criteria) this;
        }

        public Criteria andFmaxcapacityGreaterThanOrEqualTo(Integer value) {
            addCriterion("FMaxCapacity >=", value, "fmaxcapacity");
            return (Criteria) this;
        }

        public Criteria andFmaxcapacityLessThan(Integer value) {
            addCriterion("FMaxCapacity <", value, "fmaxcapacity");
            return (Criteria) this;
        }

        public Criteria andFmaxcapacityLessThanOrEqualTo(Integer value) {
            addCriterion("FMaxCapacity <=", value, "fmaxcapacity");
            return (Criteria) this;
        }

        public Criteria andFmaxcapacityIn(List<Integer> values) {
            addCriterion("FMaxCapacity in", values, "fmaxcapacity");
            return (Criteria) this;
        }

        public Criteria andFmaxcapacityNotIn(List<Integer> values) {
            addCriterion("FMaxCapacity not in", values, "fmaxcapacity");
            return (Criteria) this;
        }

        public Criteria andFmaxcapacityBetween(Integer value1, Integer value2) {
            addCriterion("FMaxCapacity between", value1, value2, "fmaxcapacity");
            return (Criteria) this;
        }

        public Criteria andFmaxcapacityNotBetween(Integer value1, Integer value2) {
            addCriterion("FMaxCapacity not between", value1, value2, "fmaxcapacity");
            return (Criteria) this;
        }

        public Criteria andFcurrentenrollmentIsNull() {
            addCriterion("FCurrentEnrollment is null");
            return (Criteria) this;
        }

        public Criteria andFcurrentenrollmentIsNotNull() {
            addCriterion("FCurrentEnrollment is not null");
            return (Criteria) this;
        }

        public Criteria andFcurrentenrollmentEqualTo(Integer value) {
            addCriterion("FCurrentEnrollment =", value, "fcurrentenrollment");
            return (Criteria) this;
        }

        public Criteria andFcurrentenrollmentNotEqualTo(Integer value) {
            addCriterion("FCurrentEnrollment <>", value, "fcurrentenrollment");
            return (Criteria) this;
        }

        public Criteria andFcurrentenrollmentGreaterThan(Integer value) {
            addCriterion("FCurrentEnrollment >", value, "fcurrentenrollment");
            return (Criteria) this;
        }

        public Criteria andFcurrentenrollmentGreaterThanOrEqualTo(Integer value) {
            addCriterion("FCurrentEnrollment >=", value, "fcurrentenrollment");
            return (Criteria) this;
        }

        public Criteria andFcurrentenrollmentLessThan(Integer value) {
            addCriterion("FCurrentEnrollment <", value, "fcurrentenrollment");
            return (Criteria) this;
        }

        public Criteria andFcurrentenrollmentLessThanOrEqualTo(Integer value) {
            addCriterion("FCurrentEnrollment <=", value, "fcurrentenrollment");
            return (Criteria) this;
        }

        public Criteria andFcurrentenrollmentIn(List<Integer> values) {
            addCriterion("FCurrentEnrollment in", values, "fcurrentenrollment");
            return (Criteria) this;
        }

        public Criteria andFcurrentenrollmentNotIn(List<Integer> values) {
            addCriterion("FCurrentEnrollment not in", values, "fcurrentenrollment");
            return (Criteria) this;
        }

        public Criteria andFcurrentenrollmentBetween(Integer value1, Integer value2) {
            addCriterion("FCurrentEnrollment between", value1, value2, "fcurrentenrollment");
            return (Criteria) this;
        }

        public Criteria andFcurrentenrollmentNotBetween(Integer value1, Integer value2) {
            addCriterion("FCurrentEnrollment not between", value1, value2, "fcurrentenrollment");
            return (Criteria) this;
        }

        public Criteria andFstatusIsNull() {
            addCriterion("FStatus is null");
            return (Criteria) this;
        }

        public Criteria andFstatusIsNotNull() {
            addCriterion("FStatus is not null");
            return (Criteria) this;
        }

        public Criteria andFstatusEqualTo(Integer value) {
            addCriterion("FStatus =", value, "fstatus");
            return (Criteria) this;
        }

        public Criteria andFstatusNotEqualTo(Integer value) {
            addCriterion("FStatus <>", value, "fstatus");
            return (Criteria) this;
        }

        public Criteria andFstatusGreaterThan(Integer value) {
            addCriterion("FStatus >", value, "fstatus");
            return (Criteria) this;
        }

        public Criteria andFstatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("FStatus >=", value, "fstatus");
            return (Criteria) this;
        }

        public Criteria andFstatusLessThan(Integer value) {
            addCriterion("FStatus <", value, "fstatus");
            return (Criteria) this;
        }

        public Criteria andFstatusLessThanOrEqualTo(Integer value) {
            addCriterion("FStatus <=", value, "fstatus");
            return (Criteria) this;
        }

        public Criteria andFstatusIn(List<Integer> values) {
            addCriterion("FStatus in", values, "fstatus");
            return (Criteria) this;
        }

        public Criteria andFstatusNotIn(List<Integer> values) {
            addCriterion("FStatus not in", values, "fstatus");
            return (Criteria) this;
        }

        public Criteria andFstatusBetween(Integer value1, Integer value2) {
            addCriterion("FStatus between", value1, value2, "fstatus");
            return (Criteria) this;
        }

        public Criteria andFstatusNotBetween(Integer value1, Integer value2) {
            addCriterion("FStatus not between", value1, value2, "fstatus");
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
     * 数据库表：student_capability_evaluation..t_course_offering
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * TCourseOffering
     * Criterion
     * 数据库表：student_capability_evaluation..t_course_offering
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