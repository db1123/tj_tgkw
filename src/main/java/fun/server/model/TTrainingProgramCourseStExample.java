package fun.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TTrainingProgramCourseStExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * teaching_diversity..t_training_program_course_st
     * @mbg.generated
     */
    public TTrainingProgramCourseStExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * 设置排序字段
     * teaching_diversity..t_training_program_course_st
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * teaching_diversity..t_training_program_course_st
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * teaching_diversity..t_training_program_course_st
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * teaching_diversity..t_training_program_course_st
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * teaching_diversity..t_training_program_course_st
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * teaching_diversity..t_training_program_course_st
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * teaching_diversity..t_training_program_course_st
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
     * teaching_diversity..t_training_program_course_st
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
     * teaching_diversity..t_training_program_course_st
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * teaching_diversity..t_training_program_course_st
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TTrainingProgramCourseSt
     * GeneratedCriteria
     * 数据库表：teaching_diversity..t_training_program_course_st
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

        public Criteria andFtmidIsNull() {
            addCriterion("FTMID is null");
            return (Criteria) this;
        }

        public Criteria andFtmidIsNotNull() {
            addCriterion("FTMID is not null");
            return (Criteria) this;
        }

        public Criteria andFtmidEqualTo(Long value) {
            addCriterion("FTMID =", value, "ftmid");
            return (Criteria) this;
        }

        public Criteria andFtmidNotEqualTo(Long value) {
            addCriterion("FTMID <>", value, "ftmid");
            return (Criteria) this;
        }

        public Criteria andFtmidGreaterThan(Long value) {
            addCriterion("FTMID >", value, "ftmid");
            return (Criteria) this;
        }

        public Criteria andFtmidGreaterThanOrEqualTo(Long value) {
            addCriterion("FTMID >=", value, "ftmid");
            return (Criteria) this;
        }

        public Criteria andFtmidLessThan(Long value) {
            addCriterion("FTMID <", value, "ftmid");
            return (Criteria) this;
        }

        public Criteria andFtmidLessThanOrEqualTo(Long value) {
            addCriterion("FTMID <=", value, "ftmid");
            return (Criteria) this;
        }

        public Criteria andFtmidIn(List<Long> values) {
            addCriterion("FTMID in", values, "ftmid");
            return (Criteria) this;
        }

        public Criteria andFtmidNotIn(List<Long> values) {
            addCriterion("FTMID not in", values, "ftmid");
            return (Criteria) this;
        }

        public Criteria andFtmidBetween(Long value1, Long value2) {
            addCriterion("FTMID between", value1, value2, "ftmid");
            return (Criteria) this;
        }

        public Criteria andFtmidNotBetween(Long value1, Long value2) {
            addCriterion("FTMID not between", value1, value2, "ftmid");
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

        public Criteria andFcoursenameIsNull() {
            addCriterion("FCourseName is null");
            return (Criteria) this;
        }

        public Criteria andFcoursenameIsNotNull() {
            addCriterion("FCourseName is not null");
            return (Criteria) this;
        }

        public Criteria andFcoursenameEqualTo(String value) {
            addCriterion("FCourseName =", value, "fcoursename");
            return (Criteria) this;
        }

        public Criteria andFcoursenameNotEqualTo(String value) {
            addCriterion("FCourseName <>", value, "fcoursename");
            return (Criteria) this;
        }

        public Criteria andFcoursenameGreaterThan(String value) {
            addCriterion("FCourseName >", value, "fcoursename");
            return (Criteria) this;
        }

        public Criteria andFcoursenameGreaterThanOrEqualTo(String value) {
            addCriterion("FCourseName >=", value, "fcoursename");
            return (Criteria) this;
        }

        public Criteria andFcoursenameLessThan(String value) {
            addCriterion("FCourseName <", value, "fcoursename");
            return (Criteria) this;
        }

        public Criteria andFcoursenameLessThanOrEqualTo(String value) {
            addCriterion("FCourseName <=", value, "fcoursename");
            return (Criteria) this;
        }

        public Criteria andFcoursenameLike(String value) {
            addCriterion("FCourseName like", value, "fcoursename");
            return (Criteria) this;
        }

        public Criteria andFcoursenameNotLike(String value) {
            addCriterion("FCourseName not like", value, "fcoursename");
            return (Criteria) this;
        }

        public Criteria andFcoursenameIn(List<String> values) {
            addCriterion("FCourseName in", values, "fcoursename");
            return (Criteria) this;
        }

        public Criteria andFcoursenameNotIn(List<String> values) {
            addCriterion("FCourseName not in", values, "fcoursename");
            return (Criteria) this;
        }

        public Criteria andFcoursenameBetween(String value1, String value2) {
            addCriterion("FCourseName between", value1, value2, "fcoursename");
            return (Criteria) this;
        }

        public Criteria andFcoursenameNotBetween(String value1, String value2) {
            addCriterion("FCourseName not between", value1, value2, "fcoursename");
            return (Criteria) this;
        }

        public Criteria andForderIsNull() {
            addCriterion("FOrder is null");
            return (Criteria) this;
        }

        public Criteria andForderIsNotNull() {
            addCriterion("FOrder is not null");
            return (Criteria) this;
        }

        public Criteria andForderEqualTo(Integer value) {
            addCriterion("FOrder =", value, "forder");
            return (Criteria) this;
        }

        public Criteria andForderNotEqualTo(Integer value) {
            addCriterion("FOrder <>", value, "forder");
            return (Criteria) this;
        }

        public Criteria andForderGreaterThan(Integer value) {
            addCriterion("FOrder >", value, "forder");
            return (Criteria) this;
        }

        public Criteria andForderGreaterThanOrEqualTo(Integer value) {
            addCriterion("FOrder >=", value, "forder");
            return (Criteria) this;
        }

        public Criteria andForderLessThan(Integer value) {
            addCriterion("FOrder <", value, "forder");
            return (Criteria) this;
        }

        public Criteria andForderLessThanOrEqualTo(Integer value) {
            addCriterion("FOrder <=", value, "forder");
            return (Criteria) this;
        }

        public Criteria andForderIn(List<Integer> values) {
            addCriterion("FOrder in", values, "forder");
            return (Criteria) this;
        }

        public Criteria andForderNotIn(List<Integer> values) {
            addCriterion("FOrder not in", values, "forder");
            return (Criteria) this;
        }

        public Criteria andForderBetween(Integer value1, Integer value2) {
            addCriterion("FOrder between", value1, value2, "forder");
            return (Criteria) this;
        }

        public Criteria andForderNotBetween(Integer value1, Integer value2) {
            addCriterion("FOrder not between", value1, value2, "forder");
            return (Criteria) this;
        }

        public Criteria andFxfIsNull() {
            addCriterion("FXF is null");
            return (Criteria) this;
        }

        public Criteria andFxfIsNotNull() {
            addCriterion("FXF is not null");
            return (Criteria) this;
        }

        public Criteria andFxfEqualTo(Float value) {
            addCriterion("FXF =", value, "fxf");
            return (Criteria) this;
        }

        public Criteria andFxfNotEqualTo(Float value) {
            addCriterion("FXF <>", value, "fxf");
            return (Criteria) this;
        }

        public Criteria andFxfGreaterThan(Float value) {
            addCriterion("FXF >", value, "fxf");
            return (Criteria) this;
        }

        public Criteria andFxfGreaterThanOrEqualTo(Float value) {
            addCriterion("FXF >=", value, "fxf");
            return (Criteria) this;
        }

        public Criteria andFxfLessThan(Float value) {
            addCriterion("FXF <", value, "fxf");
            return (Criteria) this;
        }

        public Criteria andFxfLessThanOrEqualTo(Float value) {
            addCriterion("FXF <=", value, "fxf");
            return (Criteria) this;
        }

        public Criteria andFxfIn(List<Float> values) {
            addCriterion("FXF in", values, "fxf");
            return (Criteria) this;
        }

        public Criteria andFxfNotIn(List<Float> values) {
            addCriterion("FXF not in", values, "fxf");
            return (Criteria) this;
        }

        public Criteria andFxfBetween(Float value1, Float value2) {
            addCriterion("FXF between", value1, value2, "fxf");
            return (Criteria) this;
        }

        public Criteria andFxfNotBetween(Float value1, Float value2) {
            addCriterion("FXF not between", value1, value2, "fxf");
            return (Criteria) this;
        }

        public Criteria andFtotalhoursIsNull() {
            addCriterion("FTotalHours is null");
            return (Criteria) this;
        }

        public Criteria andFtotalhoursIsNotNull() {
            addCriterion("FTotalHours is not null");
            return (Criteria) this;
        }

        public Criteria andFtotalhoursEqualTo(String value) {
            addCriterion("FTotalHours =", value, "ftotalhours");
            return (Criteria) this;
        }

        public Criteria andFtotalhoursNotEqualTo(String value) {
            addCriterion("FTotalHours <>", value, "ftotalhours");
            return (Criteria) this;
        }

        public Criteria andFtotalhoursGreaterThan(String value) {
            addCriterion("FTotalHours >", value, "ftotalhours");
            return (Criteria) this;
        }

        public Criteria andFtotalhoursGreaterThanOrEqualTo(String value) {
            addCriterion("FTotalHours >=", value, "ftotalhours");
            return (Criteria) this;
        }

        public Criteria andFtotalhoursLessThan(String value) {
            addCriterion("FTotalHours <", value, "ftotalhours");
            return (Criteria) this;
        }

        public Criteria andFtotalhoursLessThanOrEqualTo(String value) {
            addCriterion("FTotalHours <=", value, "ftotalhours");
            return (Criteria) this;
        }

        public Criteria andFtotalhoursLike(String value) {
            addCriterion("FTotalHours like", value, "ftotalhours");
            return (Criteria) this;
        }

        public Criteria andFtotalhoursNotLike(String value) {
            addCriterion("FTotalHours not like", value, "ftotalhours");
            return (Criteria) this;
        }

        public Criteria andFtotalhoursIn(List<String> values) {
            addCriterion("FTotalHours in", values, "ftotalhours");
            return (Criteria) this;
        }

        public Criteria andFtotalhoursNotIn(List<String> values) {
            addCriterion("FTotalHours not in", values, "ftotalhours");
            return (Criteria) this;
        }

        public Criteria andFtotalhoursBetween(String value1, String value2) {
            addCriterion("FTotalHours between", value1, value2, "ftotalhours");
            return (Criteria) this;
        }

        public Criteria andFtotalhoursNotBetween(String value1, String value2) {
            addCriterion("FTotalHours not between", value1, value2, "ftotalhours");
            return (Criteria) this;
        }

        public Criteria andFweeklystudyhoursIsNull() {
            addCriterion("FWeeklyStudyHours is null");
            return (Criteria) this;
        }

        public Criteria andFweeklystudyhoursIsNotNull() {
            addCriterion("FWeeklyStudyHours is not null");
            return (Criteria) this;
        }

        public Criteria andFweeklystudyhoursEqualTo(String value) {
            addCriterion("FWeeklyStudyHours =", value, "fweeklystudyhours");
            return (Criteria) this;
        }

        public Criteria andFweeklystudyhoursNotEqualTo(String value) {
            addCriterion("FWeeklyStudyHours <>", value, "fweeklystudyhours");
            return (Criteria) this;
        }

        public Criteria andFweeklystudyhoursGreaterThan(String value) {
            addCriterion("FWeeklyStudyHours >", value, "fweeklystudyhours");
            return (Criteria) this;
        }

        public Criteria andFweeklystudyhoursGreaterThanOrEqualTo(String value) {
            addCriterion("FWeeklyStudyHours >=", value, "fweeklystudyhours");
            return (Criteria) this;
        }

        public Criteria andFweeklystudyhoursLessThan(String value) {
            addCriterion("FWeeklyStudyHours <", value, "fweeklystudyhours");
            return (Criteria) this;
        }

        public Criteria andFweeklystudyhoursLessThanOrEqualTo(String value) {
            addCriterion("FWeeklyStudyHours <=", value, "fweeklystudyhours");
            return (Criteria) this;
        }

        public Criteria andFweeklystudyhoursLike(String value) {
            addCriterion("FWeeklyStudyHours like", value, "fweeklystudyhours");
            return (Criteria) this;
        }

        public Criteria andFweeklystudyhoursNotLike(String value) {
            addCriterion("FWeeklyStudyHours not like", value, "fweeklystudyhours");
            return (Criteria) this;
        }

        public Criteria andFweeklystudyhoursIn(List<String> values) {
            addCriterion("FWeeklyStudyHours in", values, "fweeklystudyhours");
            return (Criteria) this;
        }

        public Criteria andFweeklystudyhoursNotIn(List<String> values) {
            addCriterion("FWeeklyStudyHours not in", values, "fweeklystudyhours");
            return (Criteria) this;
        }

        public Criteria andFweeklystudyhoursBetween(String value1, String value2) {
            addCriterion("FWeeklyStudyHours between", value1, value2, "fweeklystudyhours");
            return (Criteria) this;
        }

        public Criteria andFweeklystudyhoursNotBetween(String value1, String value2) {
            addCriterion("FWeeklyStudyHours not between", value1, value2, "fweeklystudyhours");
            return (Criteria) this;
        }

        public Criteria andFtheoreticalstudyhoursIsNull() {
            addCriterion("FTheoreticalStudyHours is null");
            return (Criteria) this;
        }

        public Criteria andFtheoreticalstudyhoursIsNotNull() {
            addCriterion("FTheoreticalStudyHours is not null");
            return (Criteria) this;
        }

        public Criteria andFtheoreticalstudyhoursEqualTo(String value) {
            addCriterion("FTheoreticalStudyHours =", value, "ftheoreticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFtheoreticalstudyhoursNotEqualTo(String value) {
            addCriterion("FTheoreticalStudyHours <>", value, "ftheoreticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFtheoreticalstudyhoursGreaterThan(String value) {
            addCriterion("FTheoreticalStudyHours >", value, "ftheoreticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFtheoreticalstudyhoursGreaterThanOrEqualTo(String value) {
            addCriterion("FTheoreticalStudyHours >=", value, "ftheoreticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFtheoreticalstudyhoursLessThan(String value) {
            addCriterion("FTheoreticalStudyHours <", value, "ftheoreticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFtheoreticalstudyhoursLessThanOrEqualTo(String value) {
            addCriterion("FTheoreticalStudyHours <=", value, "ftheoreticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFtheoreticalstudyhoursLike(String value) {
            addCriterion("FTheoreticalStudyHours like", value, "ftheoreticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFtheoreticalstudyhoursNotLike(String value) {
            addCriterion("FTheoreticalStudyHours not like", value, "ftheoreticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFtheoreticalstudyhoursIn(List<String> values) {
            addCriterion("FTheoreticalStudyHours in", values, "ftheoreticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFtheoreticalstudyhoursNotIn(List<String> values) {
            addCriterion("FTheoreticalStudyHours not in", values, "ftheoreticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFtheoreticalstudyhoursBetween(String value1, String value2) {
            addCriterion("FTheoreticalStudyHours between", value1, value2, "ftheoreticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFtheoreticalstudyhoursNotBetween(String value1, String value2) {
            addCriterion("FTheoreticalStudyHours not between", value1, value2, "ftheoreticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFpracticalstudyhoursIsNull() {
            addCriterion("FPracticalStudyHours is null");
            return (Criteria) this;
        }

        public Criteria andFpracticalstudyhoursIsNotNull() {
            addCriterion("FPracticalStudyHours is not null");
            return (Criteria) this;
        }

        public Criteria andFpracticalstudyhoursEqualTo(String value) {
            addCriterion("FPracticalStudyHours =", value, "fpracticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFpracticalstudyhoursNotEqualTo(String value) {
            addCriterion("FPracticalStudyHours <>", value, "fpracticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFpracticalstudyhoursGreaterThan(String value) {
            addCriterion("FPracticalStudyHours >", value, "fpracticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFpracticalstudyhoursGreaterThanOrEqualTo(String value) {
            addCriterion("FPracticalStudyHours >=", value, "fpracticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFpracticalstudyhoursLessThan(String value) {
            addCriterion("FPracticalStudyHours <", value, "fpracticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFpracticalstudyhoursLessThanOrEqualTo(String value) {
            addCriterion("FPracticalStudyHours <=", value, "fpracticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFpracticalstudyhoursLike(String value) {
            addCriterion("FPracticalStudyHours like", value, "fpracticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFpracticalstudyhoursNotLike(String value) {
            addCriterion("FPracticalStudyHours not like", value, "fpracticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFpracticalstudyhoursIn(List<String> values) {
            addCriterion("FPracticalStudyHours in", values, "fpracticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFpracticalstudyhoursNotIn(List<String> values) {
            addCriterion("FPracticalStudyHours not in", values, "fpracticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFpracticalstudyhoursBetween(String value1, String value2) {
            addCriterion("FPracticalStudyHours between", value1, value2, "fpracticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFpracticalstudyhoursNotBetween(String value1, String value2) {
            addCriterion("FPracticalStudyHours not between", value1, value2, "fpracticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFppstateIsNull() {
            addCriterion("FPPState is null");
            return (Criteria) this;
        }

        public Criteria andFppstateIsNotNull() {
            addCriterion("FPPState is not null");
            return (Criteria) this;
        }

        public Criteria andFppstateEqualTo(Integer value) {
            addCriterion("FPPState =", value, "fppstate");
            return (Criteria) this;
        }

        public Criteria andFppstateNotEqualTo(Integer value) {
            addCriterion("FPPState <>", value, "fppstate");
            return (Criteria) this;
        }

        public Criteria andFppstateGreaterThan(Integer value) {
            addCriterion("FPPState >", value, "fppstate");
            return (Criteria) this;
        }

        public Criteria andFppstateGreaterThanOrEqualTo(Integer value) {
            addCriterion("FPPState >=", value, "fppstate");
            return (Criteria) this;
        }

        public Criteria andFppstateLessThan(Integer value) {
            addCriterion("FPPState <", value, "fppstate");
            return (Criteria) this;
        }

        public Criteria andFppstateLessThanOrEqualTo(Integer value) {
            addCriterion("FPPState <=", value, "fppstate");
            return (Criteria) this;
        }

        public Criteria andFppstateIn(List<Integer> values) {
            addCriterion("FPPState in", values, "fppstate");
            return (Criteria) this;
        }

        public Criteria andFppstateNotIn(List<Integer> values) {
            addCriterion("FPPState not in", values, "fppstate");
            return (Criteria) this;
        }

        public Criteria andFppstateBetween(Integer value1, Integer value2) {
            addCriterion("FPPState between", value1, value2, "fppstate");
            return (Criteria) this;
        }

        public Criteria andFppstateNotBetween(Integer value1, Integer value2) {
            addCriterion("FPPState not between", value1, value2, "fppstate");
            return (Criteria) this;
        }

        public Criteria andFyzstateIsNull() {
            addCriterion("FYZState is null");
            return (Criteria) this;
        }

        public Criteria andFyzstateIsNotNull() {
            addCriterion("FYZState is not null");
            return (Criteria) this;
        }

        public Criteria andFyzstateEqualTo(Integer value) {
            addCriterion("FYZState =", value, "fyzstate");
            return (Criteria) this;
        }

        public Criteria andFyzstateNotEqualTo(Integer value) {
            addCriterion("FYZState <>", value, "fyzstate");
            return (Criteria) this;
        }

        public Criteria andFyzstateGreaterThan(Integer value) {
            addCriterion("FYZState >", value, "fyzstate");
            return (Criteria) this;
        }

        public Criteria andFyzstateGreaterThanOrEqualTo(Integer value) {
            addCriterion("FYZState >=", value, "fyzstate");
            return (Criteria) this;
        }

        public Criteria andFyzstateLessThan(Integer value) {
            addCriterion("FYZState <", value, "fyzstate");
            return (Criteria) this;
        }

        public Criteria andFyzstateLessThanOrEqualTo(Integer value) {
            addCriterion("FYZState <=", value, "fyzstate");
            return (Criteria) this;
        }

        public Criteria andFyzstateIn(List<Integer> values) {
            addCriterion("FYZState in", values, "fyzstate");
            return (Criteria) this;
        }

        public Criteria andFyzstateNotIn(List<Integer> values) {
            addCriterion("FYZState not in", values, "fyzstate");
            return (Criteria) this;
        }

        public Criteria andFyzstateBetween(Integer value1, Integer value2) {
            addCriterion("FYZState between", value1, value2, "fyzstate");
            return (Criteria) this;
        }

        public Criteria andFyzstateNotBetween(Integer value1, Integer value2) {
            addCriterion("FYZState not between", value1, value2, "fyzstate");
            return (Criteria) this;
        }
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：teaching_diversity..t_training_program_course_st
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * TTrainingProgramCourseSt
     * Criterion
     * 数据库表：teaching_diversity..t_training_program_course_st
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