package fun.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TTrainingProgramImageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_training_program_image
     * @mbg.generated
     */
    public TTrainingProgramImageExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * 设置排序字段
     * student_capability_evaluation..t_training_program_image
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * student_capability_evaluation..t_training_program_image
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * student_capability_evaluation..t_training_program_image
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * student_capability_evaluation..t_training_program_image
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * student_capability_evaluation..t_training_program_image
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * student_capability_evaluation..t_training_program_image
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * student_capability_evaluation..t_training_program_image
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
     * student_capability_evaluation..t_training_program_image
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
     * student_capability_evaluation..t_training_program_image
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * student_capability_evaluation..t_training_program_image
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TTrainingProgramImage
     * GeneratedCriteria
     * 数据库表：student_capability_evaluation..t_training_program_image
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

        public Criteria andFurlIsNull() {
            addCriterion("FUrl is null");
            return (Criteria) this;
        }

        public Criteria andFurlIsNotNull() {
            addCriterion("FUrl is not null");
            return (Criteria) this;
        }

        public Criteria andFurlEqualTo(String value) {
            addCriterion("FUrl =", value, "furl");
            return (Criteria) this;
        }

        public Criteria andFurlNotEqualTo(String value) {
            addCriterion("FUrl <>", value, "furl");
            return (Criteria) this;
        }

        public Criteria andFurlGreaterThan(String value) {
            addCriterion("FUrl >", value, "furl");
            return (Criteria) this;
        }

        public Criteria andFurlGreaterThanOrEqualTo(String value) {
            addCriterion("FUrl >=", value, "furl");
            return (Criteria) this;
        }

        public Criteria andFurlLessThan(String value) {
            addCriterion("FUrl <", value, "furl");
            return (Criteria) this;
        }

        public Criteria andFurlLessThanOrEqualTo(String value) {
            addCriterion("FUrl <=", value, "furl");
            return (Criteria) this;
        }

        public Criteria andFurlLike(String value) {
            addCriterion("FUrl like", value, "furl");
            return (Criteria) this;
        }

        public Criteria andFurlNotLike(String value) {
            addCriterion("FUrl not like", value, "furl");
            return (Criteria) this;
        }

        public Criteria andFurlIn(List<String> values) {
            addCriterion("FUrl in", values, "furl");
            return (Criteria) this;
        }

        public Criteria andFurlNotIn(List<String> values) {
            addCriterion("FUrl not in", values, "furl");
            return (Criteria) this;
        }

        public Criteria andFurlBetween(String value1, String value2) {
            addCriterion("FUrl between", value1, value2, "furl");
            return (Criteria) this;
        }

        public Criteria andFurlNotBetween(String value1, String value2) {
            addCriterion("FUrl not between", value1, value2, "furl");
            return (Criteria) this;
        }

        public Criteria andFbaseIsNull() {
            addCriterion("FBase is null");
            return (Criteria) this;
        }

        public Criteria andFbaseIsNotNull() {
            addCriterion("FBase is not null");
            return (Criteria) this;
        }

        public Criteria andFbaseEqualTo(String value) {
            addCriterion("FBase =", value, "fbase");
            return (Criteria) this;
        }

        public Criteria andFbaseNotEqualTo(String value) {
            addCriterion("FBase <>", value, "fbase");
            return (Criteria) this;
        }

        public Criteria andFbaseGreaterThan(String value) {
            addCriterion("FBase >", value, "fbase");
            return (Criteria) this;
        }

        public Criteria andFbaseGreaterThanOrEqualTo(String value) {
            addCriterion("FBase >=", value, "fbase");
            return (Criteria) this;
        }

        public Criteria andFbaseLessThan(String value) {
            addCriterion("FBase <", value, "fbase");
            return (Criteria) this;
        }

        public Criteria andFbaseLessThanOrEqualTo(String value) {
            addCriterion("FBase <=", value, "fbase");
            return (Criteria) this;
        }

        public Criteria andFbaseLike(String value) {
            addCriterion("FBase like", value, "fbase");
            return (Criteria) this;
        }

        public Criteria andFbaseNotLike(String value) {
            addCriterion("FBase not like", value, "fbase");
            return (Criteria) this;
        }

        public Criteria andFbaseIn(List<String> values) {
            addCriterion("FBase in", values, "fbase");
            return (Criteria) this;
        }

        public Criteria andFbaseNotIn(List<String> values) {
            addCriterion("FBase not in", values, "fbase");
            return (Criteria) this;
        }

        public Criteria andFbaseBetween(String value1, String value2) {
            addCriterion("FBase between", value1, value2, "fbase");
            return (Criteria) this;
        }

        public Criteria andFbaseNotBetween(String value1, String value2) {
            addCriterion("FBase not between", value1, value2, "fbase");
            return (Criteria) this;
        }

        public Criteria andFhzIsNull() {
            addCriterion("FHZ is null");
            return (Criteria) this;
        }

        public Criteria andFhzIsNotNull() {
            addCriterion("FHZ is not null");
            return (Criteria) this;
        }

        public Criteria andFhzEqualTo(String value) {
            addCriterion("FHZ =", value, "fhz");
            return (Criteria) this;
        }

        public Criteria andFhzNotEqualTo(String value) {
            addCriterion("FHZ <>", value, "fhz");
            return (Criteria) this;
        }

        public Criteria andFhzGreaterThan(String value) {
            addCriterion("FHZ >", value, "fhz");
            return (Criteria) this;
        }

        public Criteria andFhzGreaterThanOrEqualTo(String value) {
            addCriterion("FHZ >=", value, "fhz");
            return (Criteria) this;
        }

        public Criteria andFhzLessThan(String value) {
            addCriterion("FHZ <", value, "fhz");
            return (Criteria) this;
        }

        public Criteria andFhzLessThanOrEqualTo(String value) {
            addCriterion("FHZ <=", value, "fhz");
            return (Criteria) this;
        }

        public Criteria andFhzLike(String value) {
            addCriterion("FHZ like", value, "fhz");
            return (Criteria) this;
        }

        public Criteria andFhzNotLike(String value) {
            addCriterion("FHZ not like", value, "fhz");
            return (Criteria) this;
        }

        public Criteria andFhzIn(List<String> values) {
            addCriterion("FHZ in", values, "fhz");
            return (Criteria) this;
        }

        public Criteria andFhzNotIn(List<String> values) {
            addCriterion("FHZ not in", values, "fhz");
            return (Criteria) this;
        }

        public Criteria andFhzBetween(String value1, String value2) {
            addCriterion("FHZ between", value1, value2, "fhz");
            return (Criteria) this;
        }

        public Criteria andFhzNotBetween(String value1, String value2) {
            addCriterion("FHZ not between", value1, value2, "fhz");
            return (Criteria) this;
        }

        public Criteria andFysfilenameIsNull() {
            addCriterion("FYSFIleName is null");
            return (Criteria) this;
        }

        public Criteria andFysfilenameIsNotNull() {
            addCriterion("FYSFIleName is not null");
            return (Criteria) this;
        }

        public Criteria andFysfilenameEqualTo(String value) {
            addCriterion("FYSFIleName =", value, "fysfilename");
            return (Criteria) this;
        }

        public Criteria andFysfilenameNotEqualTo(String value) {
            addCriterion("FYSFIleName <>", value, "fysfilename");
            return (Criteria) this;
        }

        public Criteria andFysfilenameGreaterThan(String value) {
            addCriterion("FYSFIleName >", value, "fysfilename");
            return (Criteria) this;
        }

        public Criteria andFysfilenameGreaterThanOrEqualTo(String value) {
            addCriterion("FYSFIleName >=", value, "fysfilename");
            return (Criteria) this;
        }

        public Criteria andFysfilenameLessThan(String value) {
            addCriterion("FYSFIleName <", value, "fysfilename");
            return (Criteria) this;
        }

        public Criteria andFysfilenameLessThanOrEqualTo(String value) {
            addCriterion("FYSFIleName <=", value, "fysfilename");
            return (Criteria) this;
        }

        public Criteria andFysfilenameLike(String value) {
            addCriterion("FYSFIleName like", value, "fysfilename");
            return (Criteria) this;
        }

        public Criteria andFysfilenameNotLike(String value) {
            addCriterion("FYSFIleName not like", value, "fysfilename");
            return (Criteria) this;
        }

        public Criteria andFysfilenameIn(List<String> values) {
            addCriterion("FYSFIleName in", values, "fysfilename");
            return (Criteria) this;
        }

        public Criteria andFysfilenameNotIn(List<String> values) {
            addCriterion("FYSFIleName not in", values, "fysfilename");
            return (Criteria) this;
        }

        public Criteria andFysfilenameBetween(String value1, String value2) {
            addCriterion("FYSFIleName between", value1, value2, "fysfilename");
            return (Criteria) this;
        }

        public Criteria andFysfilenameNotBetween(String value1, String value2) {
            addCriterion("FYSFIleName not between", value1, value2, "fysfilename");
            return (Criteria) this;
        }

        public Criteria andFxtfilenameIsNull() {
            addCriterion("FXTFIleName is null");
            return (Criteria) this;
        }

        public Criteria andFxtfilenameIsNotNull() {
            addCriterion("FXTFIleName is not null");
            return (Criteria) this;
        }

        public Criteria andFxtfilenameEqualTo(String value) {
            addCriterion("FXTFIleName =", value, "fxtfilename");
            return (Criteria) this;
        }

        public Criteria andFxtfilenameNotEqualTo(String value) {
            addCriterion("FXTFIleName <>", value, "fxtfilename");
            return (Criteria) this;
        }

        public Criteria andFxtfilenameGreaterThan(String value) {
            addCriterion("FXTFIleName >", value, "fxtfilename");
            return (Criteria) this;
        }

        public Criteria andFxtfilenameGreaterThanOrEqualTo(String value) {
            addCriterion("FXTFIleName >=", value, "fxtfilename");
            return (Criteria) this;
        }

        public Criteria andFxtfilenameLessThan(String value) {
            addCriterion("FXTFIleName <", value, "fxtfilename");
            return (Criteria) this;
        }

        public Criteria andFxtfilenameLessThanOrEqualTo(String value) {
            addCriterion("FXTFIleName <=", value, "fxtfilename");
            return (Criteria) this;
        }

        public Criteria andFxtfilenameLike(String value) {
            addCriterion("FXTFIleName like", value, "fxtfilename");
            return (Criteria) this;
        }

        public Criteria andFxtfilenameNotLike(String value) {
            addCriterion("FXTFIleName not like", value, "fxtfilename");
            return (Criteria) this;
        }

        public Criteria andFxtfilenameIn(List<String> values) {
            addCriterion("FXTFIleName in", values, "fxtfilename");
            return (Criteria) this;
        }

        public Criteria andFxtfilenameNotIn(List<String> values) {
            addCriterion("FXTFIleName not in", values, "fxtfilename");
            return (Criteria) this;
        }

        public Criteria andFxtfilenameBetween(String value1, String value2) {
            addCriterion("FXTFIleName between", value1, value2, "fxtfilename");
            return (Criteria) this;
        }

        public Criteria andFxtfilenameNotBetween(String value1, String value2) {
            addCriterion("FXTFIleName not between", value1, value2, "fxtfilename");
            return (Criteria) this;
        }

        public Criteria andFrelativepath1IsNull() {
            addCriterion("FRelativePath1 is null");
            return (Criteria) this;
        }

        public Criteria andFrelativepath1IsNotNull() {
            addCriterion("FRelativePath1 is not null");
            return (Criteria) this;
        }

        public Criteria andFrelativepath1EqualTo(String value) {
            addCriterion("FRelativePath1 =", value, "frelativepath1");
            return (Criteria) this;
        }

        public Criteria andFrelativepath1NotEqualTo(String value) {
            addCriterion("FRelativePath1 <>", value, "frelativepath1");
            return (Criteria) this;
        }

        public Criteria andFrelativepath1GreaterThan(String value) {
            addCriterion("FRelativePath1 >", value, "frelativepath1");
            return (Criteria) this;
        }

        public Criteria andFrelativepath1GreaterThanOrEqualTo(String value) {
            addCriterion("FRelativePath1 >=", value, "frelativepath1");
            return (Criteria) this;
        }

        public Criteria andFrelativepath1LessThan(String value) {
            addCriterion("FRelativePath1 <", value, "frelativepath1");
            return (Criteria) this;
        }

        public Criteria andFrelativepath1LessThanOrEqualTo(String value) {
            addCriterion("FRelativePath1 <=", value, "frelativepath1");
            return (Criteria) this;
        }

        public Criteria andFrelativepath1Like(String value) {
            addCriterion("FRelativePath1 like", value, "frelativepath1");
            return (Criteria) this;
        }

        public Criteria andFrelativepath1NotLike(String value) {
            addCriterion("FRelativePath1 not like", value, "frelativepath1");
            return (Criteria) this;
        }

        public Criteria andFrelativepath1In(List<String> values) {
            addCriterion("FRelativePath1 in", values, "frelativepath1");
            return (Criteria) this;
        }

        public Criteria andFrelativepath1NotIn(List<String> values) {
            addCriterion("FRelativePath1 not in", values, "frelativepath1");
            return (Criteria) this;
        }

        public Criteria andFrelativepath1Between(String value1, String value2) {
            addCriterion("FRelativePath1 between", value1, value2, "frelativepath1");
            return (Criteria) this;
        }

        public Criteria andFrelativepath1NotBetween(String value1, String value2) {
            addCriterion("FRelativePath1 not between", value1, value2, "frelativepath1");
            return (Criteria) this;
        }

        public Criteria andFrelativepath2IsNull() {
            addCriterion("FRelativePath2 is null");
            return (Criteria) this;
        }

        public Criteria andFrelativepath2IsNotNull() {
            addCriterion("FRelativePath2 is not null");
            return (Criteria) this;
        }

        public Criteria andFrelativepath2EqualTo(String value) {
            addCriterion("FRelativePath2 =", value, "frelativepath2");
            return (Criteria) this;
        }

        public Criteria andFrelativepath2NotEqualTo(String value) {
            addCriterion("FRelativePath2 <>", value, "frelativepath2");
            return (Criteria) this;
        }

        public Criteria andFrelativepath2GreaterThan(String value) {
            addCriterion("FRelativePath2 >", value, "frelativepath2");
            return (Criteria) this;
        }

        public Criteria andFrelativepath2GreaterThanOrEqualTo(String value) {
            addCriterion("FRelativePath2 >=", value, "frelativepath2");
            return (Criteria) this;
        }

        public Criteria andFrelativepath2LessThan(String value) {
            addCriterion("FRelativePath2 <", value, "frelativepath2");
            return (Criteria) this;
        }

        public Criteria andFrelativepath2LessThanOrEqualTo(String value) {
            addCriterion("FRelativePath2 <=", value, "frelativepath2");
            return (Criteria) this;
        }

        public Criteria andFrelativepath2Like(String value) {
            addCriterion("FRelativePath2 like", value, "frelativepath2");
            return (Criteria) this;
        }

        public Criteria andFrelativepath2NotLike(String value) {
            addCriterion("FRelativePath2 not like", value, "frelativepath2");
            return (Criteria) this;
        }

        public Criteria andFrelativepath2In(List<String> values) {
            addCriterion("FRelativePath2 in", values, "frelativepath2");
            return (Criteria) this;
        }

        public Criteria andFrelativepath2NotIn(List<String> values) {
            addCriterion("FRelativePath2 not in", values, "frelativepath2");
            return (Criteria) this;
        }

        public Criteria andFrelativepath2Between(String value1, String value2) {
            addCriterion("FRelativePath2 between", value1, value2, "frelativepath2");
            return (Criteria) this;
        }

        public Criteria andFrelativepath2NotBetween(String value1, String value2) {
            addCriterion("FRelativePath2 not between", value1, value2, "frelativepath2");
            return (Criteria) this;
        }
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：student_capability_evaluation..t_training_program_image
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * TTrainingProgramImage
     * Criterion
     * 数据库表：student_capability_evaluation..t_training_program_image
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