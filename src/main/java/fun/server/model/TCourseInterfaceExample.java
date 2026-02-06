package fun.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TCourseInterfaceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_course_interface
     * @mbg.generated
     */
    public TCourseInterfaceExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * 设置排序字段
     * student_capability_evaluation..t_course_interface
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * student_capability_evaluation..t_course_interface
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * student_capability_evaluation..t_course_interface
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * student_capability_evaluation..t_course_interface
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * student_capability_evaluation..t_course_interface
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * student_capability_evaluation..t_course_interface
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * student_capability_evaluation..t_course_interface
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
     * student_capability_evaluation..t_course_interface
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
     * student_capability_evaluation..t_course_interface
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * student_capability_evaluation..t_course_interface
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TCourseInterface
     * GeneratedCriteria
     * 数据库表：student_capability_evaluation..t_course_interface
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

        public Criteria andFifidIsNull() {
            addCriterion("FIFID is null");
            return (Criteria) this;
        }

        public Criteria andFifidIsNotNull() {
            addCriterion("FIFID is not null");
            return (Criteria) this;
        }

        public Criteria andFifidEqualTo(Long value) {
            addCriterion("FIFID =", value, "fifid");
            return (Criteria) this;
        }

        public Criteria andFifidNotEqualTo(Long value) {
            addCriterion("FIFID <>", value, "fifid");
            return (Criteria) this;
        }

        public Criteria andFifidGreaterThan(Long value) {
            addCriterion("FIFID >", value, "fifid");
            return (Criteria) this;
        }

        public Criteria andFifidGreaterThanOrEqualTo(Long value) {
            addCriterion("FIFID >=", value, "fifid");
            return (Criteria) this;
        }

        public Criteria andFifidLessThan(Long value) {
            addCriterion("FIFID <", value, "fifid");
            return (Criteria) this;
        }

        public Criteria andFifidLessThanOrEqualTo(Long value) {
            addCriterion("FIFID <=", value, "fifid");
            return (Criteria) this;
        }

        public Criteria andFifidIn(List<Long> values) {
            addCriterion("FIFID in", values, "fifid");
            return (Criteria) this;
        }

        public Criteria andFifidNotIn(List<Long> values) {
            addCriterion("FIFID not in", values, "fifid");
            return (Criteria) this;
        }

        public Criteria andFifidBetween(Long value1, Long value2) {
            addCriterion("FIFID between", value1, value2, "fifid");
            return (Criteria) this;
        }

        public Criteria andFifidNotBetween(Long value1, Long value2) {
            addCriterion("FIFID not between", value1, value2, "fifid");
            return (Criteria) this;
        }

        public Criteria andFceidIsNull() {
            addCriterion("FCEID is null");
            return (Criteria) this;
        }

        public Criteria andFceidIsNotNull() {
            addCriterion("FCEID is not null");
            return (Criteria) this;
        }

        public Criteria andFceidEqualTo(Long value) {
            addCriterion("FCEID =", value, "fceid");
            return (Criteria) this;
        }

        public Criteria andFceidNotEqualTo(Long value) {
            addCriterion("FCEID <>", value, "fceid");
            return (Criteria) this;
        }

        public Criteria andFceidGreaterThan(Long value) {
            addCriterion("FCEID >", value, "fceid");
            return (Criteria) this;
        }

        public Criteria andFceidGreaterThanOrEqualTo(Long value) {
            addCriterion("FCEID >=", value, "fceid");
            return (Criteria) this;
        }

        public Criteria andFceidLessThan(Long value) {
            addCriterion("FCEID <", value, "fceid");
            return (Criteria) this;
        }

        public Criteria andFceidLessThanOrEqualTo(Long value) {
            addCriterion("FCEID <=", value, "fceid");
            return (Criteria) this;
        }

        public Criteria andFceidIn(List<Long> values) {
            addCriterion("FCEID in", values, "fceid");
            return (Criteria) this;
        }

        public Criteria andFceidNotIn(List<Long> values) {
            addCriterion("FCEID not in", values, "fceid");
            return (Criteria) this;
        }

        public Criteria andFceidBetween(Long value1, Long value2) {
            addCriterion("FCEID between", value1, value2, "fceid");
            return (Criteria) this;
        }

        public Criteria andFceidNotBetween(Long value1, Long value2) {
            addCriterion("FCEID not between", value1, value2, "fceid");
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

        public Criteria andFnameIsNull() {
            addCriterion("FName is null");
            return (Criteria) this;
        }

        public Criteria andFnameIsNotNull() {
            addCriterion("FName is not null");
            return (Criteria) this;
        }

        public Criteria andFnameEqualTo(String value) {
            addCriterion("FName =", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameNotEqualTo(String value) {
            addCriterion("FName <>", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameGreaterThan(String value) {
            addCriterion("FName >", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameGreaterThanOrEqualTo(String value) {
            addCriterion("FName >=", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameLessThan(String value) {
            addCriterion("FName <", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameLessThanOrEqualTo(String value) {
            addCriterion("FName <=", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameLike(String value) {
            addCriterion("FName like", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameNotLike(String value) {
            addCriterion("FName not like", value, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameIn(List<String> values) {
            addCriterion("FName in", values, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameNotIn(List<String> values) {
            addCriterion("FName not in", values, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameBetween(String value1, String value2) {
            addCriterion("FName between", value1, value2, "fname");
            return (Criteria) this;
        }

        public Criteria andFnameNotBetween(String value1, String value2) {
            addCriterion("FName not between", value1, value2, "fname");
            return (Criteria) this;
        }

        public Criteria andFtypeidIsNull() {
            addCriterion("FTypeID is null");
            return (Criteria) this;
        }

        public Criteria andFtypeidIsNotNull() {
            addCriterion("FTypeID is not null");
            return (Criteria) this;
        }

        public Criteria andFtypeidEqualTo(Long value) {
            addCriterion("FTypeID =", value, "ftypeid");
            return (Criteria) this;
        }

        public Criteria andFtypeidNotEqualTo(Long value) {
            addCriterion("FTypeID <>", value, "ftypeid");
            return (Criteria) this;
        }

        public Criteria andFtypeidGreaterThan(Long value) {
            addCriterion("FTypeID >", value, "ftypeid");
            return (Criteria) this;
        }

        public Criteria andFtypeidGreaterThanOrEqualTo(Long value) {
            addCriterion("FTypeID >=", value, "ftypeid");
            return (Criteria) this;
        }

        public Criteria andFtypeidLessThan(Long value) {
            addCriterion("FTypeID <", value, "ftypeid");
            return (Criteria) this;
        }

        public Criteria andFtypeidLessThanOrEqualTo(Long value) {
            addCriterion("FTypeID <=", value, "ftypeid");
            return (Criteria) this;
        }

        public Criteria andFtypeidIn(List<Long> values) {
            addCriterion("FTypeID in", values, "ftypeid");
            return (Criteria) this;
        }

        public Criteria andFtypeidNotIn(List<Long> values) {
            addCriterion("FTypeID not in", values, "ftypeid");
            return (Criteria) this;
        }

        public Criteria andFtypeidBetween(Long value1, Long value2) {
            addCriterion("FTypeID between", value1, value2, "ftypeid");
            return (Criteria) this;
        }

        public Criteria andFtypeidNotBetween(Long value1, Long value2) {
            addCriterion("FTypeID not between", value1, value2, "ftypeid");
            return (Criteria) this;
        }

        public Criteria andFmaturityIsNull() {
            addCriterion("FMaturity is null");
            return (Criteria) this;
        }

        public Criteria andFmaturityIsNotNull() {
            addCriterion("FMaturity is not null");
            return (Criteria) this;
        }

        public Criteria andFmaturityEqualTo(Float value) {
            addCriterion("FMaturity =", value, "fmaturity");
            return (Criteria) this;
        }

        public Criteria andFmaturityNotEqualTo(Float value) {
            addCriterion("FMaturity <>", value, "fmaturity");
            return (Criteria) this;
        }

        public Criteria andFmaturityGreaterThan(Float value) {
            addCriterion("FMaturity >", value, "fmaturity");
            return (Criteria) this;
        }

        public Criteria andFmaturityGreaterThanOrEqualTo(Float value) {
            addCriterion("FMaturity >=", value, "fmaturity");
            return (Criteria) this;
        }

        public Criteria andFmaturityLessThan(Float value) {
            addCriterion("FMaturity <", value, "fmaturity");
            return (Criteria) this;
        }

        public Criteria andFmaturityLessThanOrEqualTo(Float value) {
            addCriterion("FMaturity <=", value, "fmaturity");
            return (Criteria) this;
        }

        public Criteria andFmaturityIn(List<Float> values) {
            addCriterion("FMaturity in", values, "fmaturity");
            return (Criteria) this;
        }

        public Criteria andFmaturityNotIn(List<Float> values) {
            addCriterion("FMaturity not in", values, "fmaturity");
            return (Criteria) this;
        }

        public Criteria andFmaturityBetween(Float value1, Float value2) {
            addCriterion("FMaturity between", value1, value2, "fmaturity");
            return (Criteria) this;
        }

        public Criteria andFmaturityNotBetween(Float value1, Float value2) {
            addCriterion("FMaturity not between", value1, value2, "fmaturity");
            return (Criteria) this;
        }

        public Criteria andFinterfacellmidIsNull() {
            addCriterion("FInterfacellmID is null");
            return (Criteria) this;
        }

        public Criteria andFinterfacellmidIsNotNull() {
            addCriterion("FInterfacellmID is not null");
            return (Criteria) this;
        }

        public Criteria andFinterfacellmidEqualTo(String value) {
            addCriterion("FInterfacellmID =", value, "finterfacellmid");
            return (Criteria) this;
        }

        public Criteria andFinterfacellmidNotEqualTo(String value) {
            addCriterion("FInterfacellmID <>", value, "finterfacellmid");
            return (Criteria) this;
        }

        public Criteria andFinterfacellmidGreaterThan(String value) {
            addCriterion("FInterfacellmID >", value, "finterfacellmid");
            return (Criteria) this;
        }

        public Criteria andFinterfacellmidGreaterThanOrEqualTo(String value) {
            addCriterion("FInterfacellmID >=", value, "finterfacellmid");
            return (Criteria) this;
        }

        public Criteria andFinterfacellmidLessThan(String value) {
            addCriterion("FInterfacellmID <", value, "finterfacellmid");
            return (Criteria) this;
        }

        public Criteria andFinterfacellmidLessThanOrEqualTo(String value) {
            addCriterion("FInterfacellmID <=", value, "finterfacellmid");
            return (Criteria) this;
        }

        public Criteria andFinterfacellmidLike(String value) {
            addCriterion("FInterfacellmID like", value, "finterfacellmid");
            return (Criteria) this;
        }

        public Criteria andFinterfacellmidNotLike(String value) {
            addCriterion("FInterfacellmID not like", value, "finterfacellmid");
            return (Criteria) this;
        }

        public Criteria andFinterfacellmidIn(List<String> values) {
            addCriterion("FInterfacellmID in", values, "finterfacellmid");
            return (Criteria) this;
        }

        public Criteria andFinterfacellmidNotIn(List<String> values) {
            addCriterion("FInterfacellmID not in", values, "finterfacellmid");
            return (Criteria) this;
        }

        public Criteria andFinterfacellmidBetween(String value1, String value2) {
            addCriterion("FInterfacellmID between", value1, value2, "finterfacellmid");
            return (Criteria) this;
        }

        public Criteria andFinterfacellmidNotBetween(String value1, String value2) {
            addCriterion("FInterfacellmID not between", value1, value2, "finterfacellmid");
            return (Criteria) this;
        }

        public Criteria andFinterfacellmstateIsNull() {
            addCriterion("FInterfacellmState is null");
            return (Criteria) this;
        }

        public Criteria andFinterfacellmstateIsNotNull() {
            addCriterion("FInterfacellmState is not null");
            return (Criteria) this;
        }

        public Criteria andFinterfacellmstateEqualTo(Integer value) {
            addCriterion("FInterfacellmState =", value, "finterfacellmstate");
            return (Criteria) this;
        }

        public Criteria andFinterfacellmstateNotEqualTo(Integer value) {
            addCriterion("FInterfacellmState <>", value, "finterfacellmstate");
            return (Criteria) this;
        }

        public Criteria andFinterfacellmstateGreaterThan(Integer value) {
            addCriterion("FInterfacellmState >", value, "finterfacellmstate");
            return (Criteria) this;
        }

        public Criteria andFinterfacellmstateGreaterThanOrEqualTo(Integer value) {
            addCriterion("FInterfacellmState >=", value, "finterfacellmstate");
            return (Criteria) this;
        }

        public Criteria andFinterfacellmstateLessThan(Integer value) {
            addCriterion("FInterfacellmState <", value, "finterfacellmstate");
            return (Criteria) this;
        }

        public Criteria andFinterfacellmstateLessThanOrEqualTo(Integer value) {
            addCriterion("FInterfacellmState <=", value, "finterfacellmstate");
            return (Criteria) this;
        }

        public Criteria andFinterfacellmstateIn(List<Integer> values) {
            addCriterion("FInterfacellmState in", values, "finterfacellmstate");
            return (Criteria) this;
        }

        public Criteria andFinterfacellmstateNotIn(List<Integer> values) {
            addCriterion("FInterfacellmState not in", values, "finterfacellmstate");
            return (Criteria) this;
        }

        public Criteria andFinterfacellmstateBetween(Integer value1, Integer value2) {
            addCriterion("FInterfacellmState between", value1, value2, "finterfacellmstate");
            return (Criteria) this;
        }

        public Criteria andFinterfacellmstateNotBetween(Integer value1, Integer value2) {
            addCriterion("FInterfacellmState not between", value1, value2, "finterfacellmstate");
            return (Criteria) this;
        }

        public Criteria andFpjcontentIsNull() {
            addCriterion("FPJContent is null");
            return (Criteria) this;
        }

        public Criteria andFpjcontentIsNotNull() {
            addCriterion("FPJContent is not null");
            return (Criteria) this;
        }

        public Criteria andFpjcontentEqualTo(String value) {
            addCriterion("FPJContent =", value, "fpjcontent");
            return (Criteria) this;
        }

        public Criteria andFpjcontentNotEqualTo(String value) {
            addCriterion("FPJContent <>", value, "fpjcontent");
            return (Criteria) this;
        }

        public Criteria andFpjcontentGreaterThan(String value) {
            addCriterion("FPJContent >", value, "fpjcontent");
            return (Criteria) this;
        }

        public Criteria andFpjcontentGreaterThanOrEqualTo(String value) {
            addCriterion("FPJContent >=", value, "fpjcontent");
            return (Criteria) this;
        }

        public Criteria andFpjcontentLessThan(String value) {
            addCriterion("FPJContent <", value, "fpjcontent");
            return (Criteria) this;
        }

        public Criteria andFpjcontentLessThanOrEqualTo(String value) {
            addCriterion("FPJContent <=", value, "fpjcontent");
            return (Criteria) this;
        }

        public Criteria andFpjcontentLike(String value) {
            addCriterion("FPJContent like", value, "fpjcontent");
            return (Criteria) this;
        }

        public Criteria andFpjcontentNotLike(String value) {
            addCriterion("FPJContent not like", value, "fpjcontent");
            return (Criteria) this;
        }

        public Criteria andFpjcontentIn(List<String> values) {
            addCriterion("FPJContent in", values, "fpjcontent");
            return (Criteria) this;
        }

        public Criteria andFpjcontentNotIn(List<String> values) {
            addCriterion("FPJContent not in", values, "fpjcontent");
            return (Criteria) this;
        }

        public Criteria andFpjcontentBetween(String value1, String value2) {
            addCriterion("FPJContent between", value1, value2, "fpjcontent");
            return (Criteria) this;
        }

        public Criteria andFpjcontentNotBetween(String value1, String value2) {
            addCriterion("FPJContent not between", value1, value2, "fpjcontent");
            return (Criteria) this;
        }
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：student_capability_evaluation..t_course_interface
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * TCourseInterface
     * Criterion
     * 数据库表：student_capability_evaluation..t_course_interface
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