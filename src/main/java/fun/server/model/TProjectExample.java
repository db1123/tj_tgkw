package fun.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TProjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * teaching_diversity..t_project
     * @mbg.generated
     */
    public TProjectExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * 设置排序字段
     * teaching_diversity..t_project
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * teaching_diversity..t_project
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * teaching_diversity..t_project
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * teaching_diversity..t_project
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * teaching_diversity..t_project
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * teaching_diversity..t_project
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * teaching_diversity..t_project
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
     * teaching_diversity..t_project
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
     * teaching_diversity..t_project
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * teaching_diversity..t_project
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TProject
     * GeneratedCriteria
     * 数据库表：teaching_diversity..t_project
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

        public Criteria andFuseridIsNull() {
            addCriterion("FUserID is null");
            return (Criteria) this;
        }

        public Criteria andFuseridIsNotNull() {
            addCriterion("FUserID is not null");
            return (Criteria) this;
        }

        public Criteria andFuseridEqualTo(Long value) {
            addCriterion("FUserID =", value, "fuserid");
            return (Criteria) this;
        }

        public Criteria andFuseridNotEqualTo(Long value) {
            addCriterion("FUserID <>", value, "fuserid");
            return (Criteria) this;
        }

        public Criteria andFuseridGreaterThan(Long value) {
            addCriterion("FUserID >", value, "fuserid");
            return (Criteria) this;
        }

        public Criteria andFuseridGreaterThanOrEqualTo(Long value) {
            addCriterion("FUserID >=", value, "fuserid");
            return (Criteria) this;
        }

        public Criteria andFuseridLessThan(Long value) {
            addCriterion("FUserID <", value, "fuserid");
            return (Criteria) this;
        }

        public Criteria andFuseridLessThanOrEqualTo(Long value) {
            addCriterion("FUserID <=", value, "fuserid");
            return (Criteria) this;
        }

        public Criteria andFuseridIn(List<Long> values) {
            addCriterion("FUserID in", values, "fuserid");
            return (Criteria) this;
        }

        public Criteria andFuseridNotIn(List<Long> values) {
            addCriterion("FUserID not in", values, "fuserid");
            return (Criteria) this;
        }

        public Criteria andFuseridBetween(Long value1, Long value2) {
            addCriterion("FUserID between", value1, value2, "fuserid");
            return (Criteria) this;
        }

        public Criteria andFuseridNotBetween(Long value1, Long value2) {
            addCriterion("FUserID not between", value1, value2, "fuserid");
            return (Criteria) this;
        }

        public Criteria andFdateIsNull() {
            addCriterion("FDate is null");
            return (Criteria) this;
        }

        public Criteria andFdateIsNotNull() {
            addCriterion("FDate is not null");
            return (Criteria) this;
        }

        public Criteria andFdateEqualTo(Date value) {
            addCriterion("FDate =", value, "fdate");
            return (Criteria) this;
        }

        public Criteria andFdateNotEqualTo(Date value) {
            addCriterion("FDate <>", value, "fdate");
            return (Criteria) this;
        }

        public Criteria andFdateGreaterThan(Date value) {
            addCriterion("FDate >", value, "fdate");
            return (Criteria) this;
        }

        public Criteria andFdateGreaterThanOrEqualTo(Date value) {
            addCriterion("FDate >=", value, "fdate");
            return (Criteria) this;
        }

        public Criteria andFdateLessThan(Date value) {
            addCriterion("FDate <", value, "fdate");
            return (Criteria) this;
        }

        public Criteria andFdateLessThanOrEqualTo(Date value) {
            addCriterion("FDate <=", value, "fdate");
            return (Criteria) this;
        }

        public Criteria andFdateIn(List<Date> values) {
            addCriterion("FDate in", values, "fdate");
            return (Criteria) this;
        }

        public Criteria andFdateNotIn(List<Date> values) {
            addCriterion("FDate not in", values, "fdate");
            return (Criteria) this;
        }

        public Criteria andFdateBetween(Date value1, Date value2) {
            addCriterion("FDate between", value1, value2, "fdate");
            return (Criteria) this;
        }

        public Criteria andFdateNotBetween(Date value1, Date value2) {
            addCriterion("FDate not between", value1, value2, "fdate");
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

        public Criteria andFnoIsNull() {
            addCriterion("FNo is null");
            return (Criteria) this;
        }

        public Criteria andFnoIsNotNull() {
            addCriterion("FNo is not null");
            return (Criteria) this;
        }

        public Criteria andFnoEqualTo(String value) {
            addCriterion("FNo =", value, "fno");
            return (Criteria) this;
        }

        public Criteria andFnoNotEqualTo(String value) {
            addCriterion("FNo <>", value, "fno");
            return (Criteria) this;
        }

        public Criteria andFnoGreaterThan(String value) {
            addCriterion("FNo >", value, "fno");
            return (Criteria) this;
        }

        public Criteria andFnoGreaterThanOrEqualTo(String value) {
            addCriterion("FNo >=", value, "fno");
            return (Criteria) this;
        }

        public Criteria andFnoLessThan(String value) {
            addCriterion("FNo <", value, "fno");
            return (Criteria) this;
        }

        public Criteria andFnoLessThanOrEqualTo(String value) {
            addCriterion("FNo <=", value, "fno");
            return (Criteria) this;
        }

        public Criteria andFnoLike(String value) {
            addCriterion("FNo like", value, "fno");
            return (Criteria) this;
        }

        public Criteria andFnoNotLike(String value) {
            addCriterion("FNo not like", value, "fno");
            return (Criteria) this;
        }

        public Criteria andFnoIn(List<String> values) {
            addCriterion("FNo in", values, "fno");
            return (Criteria) this;
        }

        public Criteria andFnoNotIn(List<String> values) {
            addCriterion("FNo not in", values, "fno");
            return (Criteria) this;
        }

        public Criteria andFnoBetween(String value1, String value2) {
            addCriterion("FNo between", value1, value2, "fno");
            return (Criteria) this;
        }

        public Criteria andFnoNotBetween(String value1, String value2) {
            addCriterion("FNo not between", value1, value2, "fno");
            return (Criteria) this;
        }

        public Criteria andFcustomeridIsNull() {
            addCriterion("FCustomerID is null");
            return (Criteria) this;
        }

        public Criteria andFcustomeridIsNotNull() {
            addCriterion("FCustomerID is not null");
            return (Criteria) this;
        }

        public Criteria andFcustomeridEqualTo(Long value) {
            addCriterion("FCustomerID =", value, "fcustomerid");
            return (Criteria) this;
        }

        public Criteria andFcustomeridNotEqualTo(Long value) {
            addCriterion("FCustomerID <>", value, "fcustomerid");
            return (Criteria) this;
        }

        public Criteria andFcustomeridGreaterThan(Long value) {
            addCriterion("FCustomerID >", value, "fcustomerid");
            return (Criteria) this;
        }

        public Criteria andFcustomeridGreaterThanOrEqualTo(Long value) {
            addCriterion("FCustomerID >=", value, "fcustomerid");
            return (Criteria) this;
        }

        public Criteria andFcustomeridLessThan(Long value) {
            addCriterion("FCustomerID <", value, "fcustomerid");
            return (Criteria) this;
        }

        public Criteria andFcustomeridLessThanOrEqualTo(Long value) {
            addCriterion("FCustomerID <=", value, "fcustomerid");
            return (Criteria) this;
        }

        public Criteria andFcustomeridIn(List<Long> values) {
            addCriterion("FCustomerID in", values, "fcustomerid");
            return (Criteria) this;
        }

        public Criteria andFcustomeridNotIn(List<Long> values) {
            addCriterion("FCustomerID not in", values, "fcustomerid");
            return (Criteria) this;
        }

        public Criteria andFcustomeridBetween(Long value1, Long value2) {
            addCriterion("FCustomerID between", value1, value2, "fcustomerid");
            return (Criteria) this;
        }

        public Criteria andFcustomeridNotBetween(Long value1, Long value2) {
            addCriterion("FCustomerID not between", value1, value2, "fcustomerid");
            return (Criteria) this;
        }

        public Criteria andFaskIsNull() {
            addCriterion("FAsk is null");
            return (Criteria) this;
        }

        public Criteria andFaskIsNotNull() {
            addCriterion("FAsk is not null");
            return (Criteria) this;
        }

        public Criteria andFaskEqualTo(String value) {
            addCriterion("FAsk =", value, "fask");
            return (Criteria) this;
        }

        public Criteria andFaskNotEqualTo(String value) {
            addCriterion("FAsk <>", value, "fask");
            return (Criteria) this;
        }

        public Criteria andFaskGreaterThan(String value) {
            addCriterion("FAsk >", value, "fask");
            return (Criteria) this;
        }

        public Criteria andFaskGreaterThanOrEqualTo(String value) {
            addCriterion("FAsk >=", value, "fask");
            return (Criteria) this;
        }

        public Criteria andFaskLessThan(String value) {
            addCriterion("FAsk <", value, "fask");
            return (Criteria) this;
        }

        public Criteria andFaskLessThanOrEqualTo(String value) {
            addCriterion("FAsk <=", value, "fask");
            return (Criteria) this;
        }

        public Criteria andFaskLike(String value) {
            addCriterion("FAsk like", value, "fask");
            return (Criteria) this;
        }

        public Criteria andFaskNotLike(String value) {
            addCriterion("FAsk not like", value, "fask");
            return (Criteria) this;
        }

        public Criteria andFaskIn(List<String> values) {
            addCriterion("FAsk in", values, "fask");
            return (Criteria) this;
        }

        public Criteria andFaskNotIn(List<String> values) {
            addCriterion("FAsk not in", values, "fask");
            return (Criteria) this;
        }

        public Criteria andFaskBetween(String value1, String value2) {
            addCriterion("FAsk between", value1, value2, "fask");
            return (Criteria) this;
        }

        public Criteria andFaskNotBetween(String value1, String value2) {
            addCriterion("FAsk not between", value1, value2, "fask");
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

        public Criteria andFlevelidIsNull() {
            addCriterion("FLevelID is null");
            return (Criteria) this;
        }

        public Criteria andFlevelidIsNotNull() {
            addCriterion("FLevelID is not null");
            return (Criteria) this;
        }

        public Criteria andFlevelidEqualTo(Long value) {
            addCriterion("FLevelID =", value, "flevelid");
            return (Criteria) this;
        }

        public Criteria andFlevelidNotEqualTo(Long value) {
            addCriterion("FLevelID <>", value, "flevelid");
            return (Criteria) this;
        }

        public Criteria andFlevelidGreaterThan(Long value) {
            addCriterion("FLevelID >", value, "flevelid");
            return (Criteria) this;
        }

        public Criteria andFlevelidGreaterThanOrEqualTo(Long value) {
            addCriterion("FLevelID >=", value, "flevelid");
            return (Criteria) this;
        }

        public Criteria andFlevelidLessThan(Long value) {
            addCriterion("FLevelID <", value, "flevelid");
            return (Criteria) this;
        }

        public Criteria andFlevelidLessThanOrEqualTo(Long value) {
            addCriterion("FLevelID <=", value, "flevelid");
            return (Criteria) this;
        }

        public Criteria andFlevelidIn(List<Long> values) {
            addCriterion("FLevelID in", values, "flevelid");
            return (Criteria) this;
        }

        public Criteria andFlevelidNotIn(List<Long> values) {
            addCriterion("FLevelID not in", values, "flevelid");
            return (Criteria) this;
        }

        public Criteria andFlevelidBetween(Long value1, Long value2) {
            addCriterion("FLevelID between", value1, value2, "flevelid");
            return (Criteria) this;
        }

        public Criteria andFlevelidNotBetween(Long value1, Long value2) {
            addCriterion("FLevelID not between", value1, value2, "flevelid");
            return (Criteria) this;
        }

        public Criteria andFleaduseridIsNull() {
            addCriterion("FLeadUserID is null");
            return (Criteria) this;
        }

        public Criteria andFleaduseridIsNotNull() {
            addCriterion("FLeadUserID is not null");
            return (Criteria) this;
        }

        public Criteria andFleaduseridEqualTo(Long value) {
            addCriterion("FLeadUserID =", value, "fleaduserid");
            return (Criteria) this;
        }

        public Criteria andFleaduseridNotEqualTo(Long value) {
            addCriterion("FLeadUserID <>", value, "fleaduserid");
            return (Criteria) this;
        }

        public Criteria andFleaduseridGreaterThan(Long value) {
            addCriterion("FLeadUserID >", value, "fleaduserid");
            return (Criteria) this;
        }

        public Criteria andFleaduseridGreaterThanOrEqualTo(Long value) {
            addCriterion("FLeadUserID >=", value, "fleaduserid");
            return (Criteria) this;
        }

        public Criteria andFleaduseridLessThan(Long value) {
            addCriterion("FLeadUserID <", value, "fleaduserid");
            return (Criteria) this;
        }

        public Criteria andFleaduseridLessThanOrEqualTo(Long value) {
            addCriterion("FLeadUserID <=", value, "fleaduserid");
            return (Criteria) this;
        }

        public Criteria andFleaduseridIn(List<Long> values) {
            addCriterion("FLeadUserID in", values, "fleaduserid");
            return (Criteria) this;
        }

        public Criteria andFleaduseridNotIn(List<Long> values) {
            addCriterion("FLeadUserID not in", values, "fleaduserid");
            return (Criteria) this;
        }

        public Criteria andFleaduseridBetween(Long value1, Long value2) {
            addCriterion("FLeadUserID between", value1, value2, "fleaduserid");
            return (Criteria) this;
        }

        public Criteria andFleaduseridNotBetween(Long value1, Long value2) {
            addCriterion("FLeadUserID not between", value1, value2, "fleaduserid");
            return (Criteria) this;
        }

        public Criteria andFsdateIsNull() {
            addCriterion("FSDate is null");
            return (Criteria) this;
        }

        public Criteria andFsdateIsNotNull() {
            addCriterion("FSDate is not null");
            return (Criteria) this;
        }

        public Criteria andFsdateEqualTo(Date value) {
            addCriterion("FSDate =", value, "fsdate");
            return (Criteria) this;
        }

        public Criteria andFsdateNotEqualTo(Date value) {
            addCriterion("FSDate <>", value, "fsdate");
            return (Criteria) this;
        }

        public Criteria andFsdateGreaterThan(Date value) {
            addCriterion("FSDate >", value, "fsdate");
            return (Criteria) this;
        }

        public Criteria andFsdateGreaterThanOrEqualTo(Date value) {
            addCriterion("FSDate >=", value, "fsdate");
            return (Criteria) this;
        }

        public Criteria andFsdateLessThan(Date value) {
            addCriterion("FSDate <", value, "fsdate");
            return (Criteria) this;
        }

        public Criteria andFsdateLessThanOrEqualTo(Date value) {
            addCriterion("FSDate <=", value, "fsdate");
            return (Criteria) this;
        }

        public Criteria andFsdateIn(List<Date> values) {
            addCriterion("FSDate in", values, "fsdate");
            return (Criteria) this;
        }

        public Criteria andFsdateNotIn(List<Date> values) {
            addCriterion("FSDate not in", values, "fsdate");
            return (Criteria) this;
        }

        public Criteria andFsdateBetween(Date value1, Date value2) {
            addCriterion("FSDate between", value1, value2, "fsdate");
            return (Criteria) this;
        }

        public Criteria andFsdateNotBetween(Date value1, Date value2) {
            addCriterion("FSDate not between", value1, value2, "fsdate");
            return (Criteria) this;
        }

        public Criteria andFedateIsNull() {
            addCriterion("FEDate is null");
            return (Criteria) this;
        }

        public Criteria andFedateIsNotNull() {
            addCriterion("FEDate is not null");
            return (Criteria) this;
        }

        public Criteria andFedateEqualTo(Date value) {
            addCriterion("FEDate =", value, "fedate");
            return (Criteria) this;
        }

        public Criteria andFedateNotEqualTo(Date value) {
            addCriterion("FEDate <>", value, "fedate");
            return (Criteria) this;
        }

        public Criteria andFedateGreaterThan(Date value) {
            addCriterion("FEDate >", value, "fedate");
            return (Criteria) this;
        }

        public Criteria andFedateGreaterThanOrEqualTo(Date value) {
            addCriterion("FEDate >=", value, "fedate");
            return (Criteria) this;
        }

        public Criteria andFedateLessThan(Date value) {
            addCriterion("FEDate <", value, "fedate");
            return (Criteria) this;
        }

        public Criteria andFedateLessThanOrEqualTo(Date value) {
            addCriterion("FEDate <=", value, "fedate");
            return (Criteria) this;
        }

        public Criteria andFedateIn(List<Date> values) {
            addCriterion("FEDate in", values, "fedate");
            return (Criteria) this;
        }

        public Criteria andFedateNotIn(List<Date> values) {
            addCriterion("FEDate not in", values, "fedate");
            return (Criteria) this;
        }

        public Criteria andFedateBetween(Date value1, Date value2) {
            addCriterion("FEDate between", value1, value2, "fedate");
            return (Criteria) this;
        }

        public Criteria andFedateNotBetween(Date value1, Date value2) {
            addCriterion("FEDate not between", value1, value2, "fedate");
            return (Criteria) this;
        }

        public Criteria andFstartdateIsNull() {
            addCriterion("FStartDate is null");
            return (Criteria) this;
        }

        public Criteria andFstartdateIsNotNull() {
            addCriterion("FStartDate is not null");
            return (Criteria) this;
        }

        public Criteria andFstartdateEqualTo(Date value) {
            addCriterion("FStartDate =", value, "fstartdate");
            return (Criteria) this;
        }

        public Criteria andFstartdateNotEqualTo(Date value) {
            addCriterion("FStartDate <>", value, "fstartdate");
            return (Criteria) this;
        }

        public Criteria andFstartdateGreaterThan(Date value) {
            addCriterion("FStartDate >", value, "fstartdate");
            return (Criteria) this;
        }

        public Criteria andFstartdateGreaterThanOrEqualTo(Date value) {
            addCriterion("FStartDate >=", value, "fstartdate");
            return (Criteria) this;
        }

        public Criteria andFstartdateLessThan(Date value) {
            addCriterion("FStartDate <", value, "fstartdate");
            return (Criteria) this;
        }

        public Criteria andFstartdateLessThanOrEqualTo(Date value) {
            addCriterion("FStartDate <=", value, "fstartdate");
            return (Criteria) this;
        }

        public Criteria andFstartdateIn(List<Date> values) {
            addCriterion("FStartDate in", values, "fstartdate");
            return (Criteria) this;
        }

        public Criteria andFstartdateNotIn(List<Date> values) {
            addCriterion("FStartDate not in", values, "fstartdate");
            return (Criteria) this;
        }

        public Criteria andFstartdateBetween(Date value1, Date value2) {
            addCriterion("FStartDate between", value1, value2, "fstartdate");
            return (Criteria) this;
        }

        public Criteria andFstartdateNotBetween(Date value1, Date value2) {
            addCriterion("FStartDate not between", value1, value2, "fstartdate");
            return (Criteria) this;
        }

        public Criteria andFenddateIsNull() {
            addCriterion("FEndDate is null");
            return (Criteria) this;
        }

        public Criteria andFenddateIsNotNull() {
            addCriterion("FEndDate is not null");
            return (Criteria) this;
        }

        public Criteria andFenddateEqualTo(Date value) {
            addCriterion("FEndDate =", value, "fenddate");
            return (Criteria) this;
        }

        public Criteria andFenddateNotEqualTo(Date value) {
            addCriterion("FEndDate <>", value, "fenddate");
            return (Criteria) this;
        }

        public Criteria andFenddateGreaterThan(Date value) {
            addCriterion("FEndDate >", value, "fenddate");
            return (Criteria) this;
        }

        public Criteria andFenddateGreaterThanOrEqualTo(Date value) {
            addCriterion("FEndDate >=", value, "fenddate");
            return (Criteria) this;
        }

        public Criteria andFenddateLessThan(Date value) {
            addCriterion("FEndDate <", value, "fenddate");
            return (Criteria) this;
        }

        public Criteria andFenddateLessThanOrEqualTo(Date value) {
            addCriterion("FEndDate <=", value, "fenddate");
            return (Criteria) this;
        }

        public Criteria andFenddateIn(List<Date> values) {
            addCriterion("FEndDate in", values, "fenddate");
            return (Criteria) this;
        }

        public Criteria andFenddateNotIn(List<Date> values) {
            addCriterion("FEndDate not in", values, "fenddate");
            return (Criteria) this;
        }

        public Criteria andFenddateBetween(Date value1, Date value2) {
            addCriterion("FEndDate between", value1, value2, "fenddate");
            return (Criteria) this;
        }

        public Criteria andFenddateNotBetween(Date value1, Date value2) {
            addCriterion("FEndDate not between", value1, value2, "fenddate");
            return (Criteria) this;
        }

        public Criteria andFmaterialidIsNull() {
            addCriterion("FMaterialID is null");
            return (Criteria) this;
        }

        public Criteria andFmaterialidIsNotNull() {
            addCriterion("FMaterialID is not null");
            return (Criteria) this;
        }

        public Criteria andFmaterialidEqualTo(Long value) {
            addCriterion("FMaterialID =", value, "fmaterialid");
            return (Criteria) this;
        }

        public Criteria andFmaterialidNotEqualTo(Long value) {
            addCriterion("FMaterialID <>", value, "fmaterialid");
            return (Criteria) this;
        }

        public Criteria andFmaterialidGreaterThan(Long value) {
            addCriterion("FMaterialID >", value, "fmaterialid");
            return (Criteria) this;
        }

        public Criteria andFmaterialidGreaterThanOrEqualTo(Long value) {
            addCriterion("FMaterialID >=", value, "fmaterialid");
            return (Criteria) this;
        }

        public Criteria andFmaterialidLessThan(Long value) {
            addCriterion("FMaterialID <", value, "fmaterialid");
            return (Criteria) this;
        }

        public Criteria andFmaterialidLessThanOrEqualTo(Long value) {
            addCriterion("FMaterialID <=", value, "fmaterialid");
            return (Criteria) this;
        }

        public Criteria andFmaterialidIn(List<Long> values) {
            addCriterion("FMaterialID in", values, "fmaterialid");
            return (Criteria) this;
        }

        public Criteria andFmaterialidNotIn(List<Long> values) {
            addCriterion("FMaterialID not in", values, "fmaterialid");
            return (Criteria) this;
        }

        public Criteria andFmaterialidBetween(Long value1, Long value2) {
            addCriterion("FMaterialID between", value1, value2, "fmaterialid");
            return (Criteria) this;
        }

        public Criteria andFmaterialidNotBetween(Long value1, Long value2) {
            addCriterion("FMaterialID not between", value1, value2, "fmaterialid");
            return (Criteria) this;
        }

        public Criteria andFdeptidIsNull() {
            addCriterion("FDeptID is null");
            return (Criteria) this;
        }

        public Criteria andFdeptidIsNotNull() {
            addCriterion("FDeptID is not null");
            return (Criteria) this;
        }

        public Criteria andFdeptidEqualTo(Long value) {
            addCriterion("FDeptID =", value, "fdeptid");
            return (Criteria) this;
        }

        public Criteria andFdeptidNotEqualTo(Long value) {
            addCriterion("FDeptID <>", value, "fdeptid");
            return (Criteria) this;
        }

        public Criteria andFdeptidGreaterThan(Long value) {
            addCriterion("FDeptID >", value, "fdeptid");
            return (Criteria) this;
        }

        public Criteria andFdeptidGreaterThanOrEqualTo(Long value) {
            addCriterion("FDeptID >=", value, "fdeptid");
            return (Criteria) this;
        }

        public Criteria andFdeptidLessThan(Long value) {
            addCriterion("FDeptID <", value, "fdeptid");
            return (Criteria) this;
        }

        public Criteria andFdeptidLessThanOrEqualTo(Long value) {
            addCriterion("FDeptID <=", value, "fdeptid");
            return (Criteria) this;
        }

        public Criteria andFdeptidIn(List<Long> values) {
            addCriterion("FDeptID in", values, "fdeptid");
            return (Criteria) this;
        }

        public Criteria andFdeptidNotIn(List<Long> values) {
            addCriterion("FDeptID not in", values, "fdeptid");
            return (Criteria) this;
        }

        public Criteria andFdeptidBetween(Long value1, Long value2) {
            addCriterion("FDeptID between", value1, value2, "fdeptid");
            return (Criteria) this;
        }

        public Criteria andFdeptidNotBetween(Long value1, Long value2) {
            addCriterion("FDeptID not between", value1, value2, "fdeptid");
            return (Criteria) this;
        }

        public Criteria andFcontractnoIsNull() {
            addCriterion("FContractNo is null");
            return (Criteria) this;
        }

        public Criteria andFcontractnoIsNotNull() {
            addCriterion("FContractNo is not null");
            return (Criteria) this;
        }

        public Criteria andFcontractnoEqualTo(String value) {
            addCriterion("FContractNo =", value, "fcontractno");
            return (Criteria) this;
        }

        public Criteria andFcontractnoNotEqualTo(String value) {
            addCriterion("FContractNo <>", value, "fcontractno");
            return (Criteria) this;
        }

        public Criteria andFcontractnoGreaterThan(String value) {
            addCriterion("FContractNo >", value, "fcontractno");
            return (Criteria) this;
        }

        public Criteria andFcontractnoGreaterThanOrEqualTo(String value) {
            addCriterion("FContractNo >=", value, "fcontractno");
            return (Criteria) this;
        }

        public Criteria andFcontractnoLessThan(String value) {
            addCriterion("FContractNo <", value, "fcontractno");
            return (Criteria) this;
        }

        public Criteria andFcontractnoLessThanOrEqualTo(String value) {
            addCriterion("FContractNo <=", value, "fcontractno");
            return (Criteria) this;
        }

        public Criteria andFcontractnoLike(String value) {
            addCriterion("FContractNo like", value, "fcontractno");
            return (Criteria) this;
        }

        public Criteria andFcontractnoNotLike(String value) {
            addCriterion("FContractNo not like", value, "fcontractno");
            return (Criteria) this;
        }

        public Criteria andFcontractnoIn(List<String> values) {
            addCriterion("FContractNo in", values, "fcontractno");
            return (Criteria) this;
        }

        public Criteria andFcontractnoNotIn(List<String> values) {
            addCriterion("FContractNo not in", values, "fcontractno");
            return (Criteria) this;
        }

        public Criteria andFcontractnoBetween(String value1, String value2) {
            addCriterion("FContractNo between", value1, value2, "fcontractno");
            return (Criteria) this;
        }

        public Criteria andFcontractnoNotBetween(String value1, String value2) {
            addCriterion("FContractNo not between", value1, value2, "fcontractno");
            return (Criteria) this;
        }

        public Criteria andFsecretIsNull() {
            addCriterion("FSecret is null");
            return (Criteria) this;
        }

        public Criteria andFsecretIsNotNull() {
            addCriterion("FSecret is not null");
            return (Criteria) this;
        }

        public Criteria andFsecretEqualTo(Long value) {
            addCriterion("FSecret =", value, "fsecret");
            return (Criteria) this;
        }

        public Criteria andFsecretNotEqualTo(Long value) {
            addCriterion("FSecret <>", value, "fsecret");
            return (Criteria) this;
        }

        public Criteria andFsecretGreaterThan(Long value) {
            addCriterion("FSecret >", value, "fsecret");
            return (Criteria) this;
        }

        public Criteria andFsecretGreaterThanOrEqualTo(Long value) {
            addCriterion("FSecret >=", value, "fsecret");
            return (Criteria) this;
        }

        public Criteria andFsecretLessThan(Long value) {
            addCriterion("FSecret <", value, "fsecret");
            return (Criteria) this;
        }

        public Criteria andFsecretLessThanOrEqualTo(Long value) {
            addCriterion("FSecret <=", value, "fsecret");
            return (Criteria) this;
        }

        public Criteria andFsecretIn(List<Long> values) {
            addCriterion("FSecret in", values, "fsecret");
            return (Criteria) this;
        }

        public Criteria andFsecretNotIn(List<Long> values) {
            addCriterion("FSecret not in", values, "fsecret");
            return (Criteria) this;
        }

        public Criteria andFsecretBetween(Long value1, Long value2) {
            addCriterion("FSecret between", value1, value2, "fsecret");
            return (Criteria) this;
        }

        public Criteria andFsecretNotBetween(Long value1, Long value2) {
            addCriterion("FSecret not between", value1, value2, "fsecret");
            return (Criteria) this;
        }

        public Criteria andFfromIsNull() {
            addCriterion("FFrom is null");
            return (Criteria) this;
        }

        public Criteria andFfromIsNotNull() {
            addCriterion("FFrom is not null");
            return (Criteria) this;
        }

        public Criteria andFfromEqualTo(String value) {
            addCriterion("FFrom =", value, "ffrom");
            return (Criteria) this;
        }

        public Criteria andFfromNotEqualTo(String value) {
            addCriterion("FFrom <>", value, "ffrom");
            return (Criteria) this;
        }

        public Criteria andFfromGreaterThan(String value) {
            addCriterion("FFrom >", value, "ffrom");
            return (Criteria) this;
        }

        public Criteria andFfromGreaterThanOrEqualTo(String value) {
            addCriterion("FFrom >=", value, "ffrom");
            return (Criteria) this;
        }

        public Criteria andFfromLessThan(String value) {
            addCriterion("FFrom <", value, "ffrom");
            return (Criteria) this;
        }

        public Criteria andFfromLessThanOrEqualTo(String value) {
            addCriterion("FFrom <=", value, "ffrom");
            return (Criteria) this;
        }

        public Criteria andFfromLike(String value) {
            addCriterion("FFrom like", value, "ffrom");
            return (Criteria) this;
        }

        public Criteria andFfromNotLike(String value) {
            addCriterion("FFrom not like", value, "ffrom");
            return (Criteria) this;
        }

        public Criteria andFfromIn(List<String> values) {
            addCriterion("FFrom in", values, "ffrom");
            return (Criteria) this;
        }

        public Criteria andFfromNotIn(List<String> values) {
            addCriterion("FFrom not in", values, "ffrom");
            return (Criteria) this;
        }

        public Criteria andFfromBetween(String value1, String value2) {
            addCriterion("FFrom between", value1, value2, "ffrom");
            return (Criteria) this;
        }

        public Criteria andFfromNotBetween(String value1, String value2) {
            addCriterion("FFrom not between", value1, value2, "ffrom");
            return (Criteria) this;
        }

        public Criteria andFfundsIsNull() {
            addCriterion("FFunds is null");
            return (Criteria) this;
        }

        public Criteria andFfundsIsNotNull() {
            addCriterion("FFunds is not null");
            return (Criteria) this;
        }

        public Criteria andFfundsEqualTo(String value) {
            addCriterion("FFunds =", value, "ffunds");
            return (Criteria) this;
        }

        public Criteria andFfundsNotEqualTo(String value) {
            addCriterion("FFunds <>", value, "ffunds");
            return (Criteria) this;
        }

        public Criteria andFfundsGreaterThan(String value) {
            addCriterion("FFunds >", value, "ffunds");
            return (Criteria) this;
        }

        public Criteria andFfundsGreaterThanOrEqualTo(String value) {
            addCriterion("FFunds >=", value, "ffunds");
            return (Criteria) this;
        }

        public Criteria andFfundsLessThan(String value) {
            addCriterion("FFunds <", value, "ffunds");
            return (Criteria) this;
        }

        public Criteria andFfundsLessThanOrEqualTo(String value) {
            addCriterion("FFunds <=", value, "ffunds");
            return (Criteria) this;
        }

        public Criteria andFfundsLike(String value) {
            addCriterion("FFunds like", value, "ffunds");
            return (Criteria) this;
        }

        public Criteria andFfundsNotLike(String value) {
            addCriterion("FFunds not like", value, "ffunds");
            return (Criteria) this;
        }

        public Criteria andFfundsIn(List<String> values) {
            addCriterion("FFunds in", values, "ffunds");
            return (Criteria) this;
        }

        public Criteria andFfundsNotIn(List<String> values) {
            addCriterion("FFunds not in", values, "ffunds");
            return (Criteria) this;
        }

        public Criteria andFfundsBetween(String value1, String value2) {
            addCriterion("FFunds between", value1, value2, "ffunds");
            return (Criteria) this;
        }

        public Criteria andFfundsNotBetween(String value1, String value2) {
            addCriterion("FFunds not between", value1, value2, "ffunds");
            return (Criteria) this;
        }

        public Criteria andFsysnoIsNull() {
            addCriterion("FSysNo is null");
            return (Criteria) this;
        }

        public Criteria andFsysnoIsNotNull() {
            addCriterion("FSysNo is not null");
            return (Criteria) this;
        }

        public Criteria andFsysnoEqualTo(String value) {
            addCriterion("FSysNo =", value, "fsysno");
            return (Criteria) this;
        }

        public Criteria andFsysnoNotEqualTo(String value) {
            addCriterion("FSysNo <>", value, "fsysno");
            return (Criteria) this;
        }

        public Criteria andFsysnoGreaterThan(String value) {
            addCriterion("FSysNo >", value, "fsysno");
            return (Criteria) this;
        }

        public Criteria andFsysnoGreaterThanOrEqualTo(String value) {
            addCriterion("FSysNo >=", value, "fsysno");
            return (Criteria) this;
        }

        public Criteria andFsysnoLessThan(String value) {
            addCriterion("FSysNo <", value, "fsysno");
            return (Criteria) this;
        }

        public Criteria andFsysnoLessThanOrEqualTo(String value) {
            addCriterion("FSysNo <=", value, "fsysno");
            return (Criteria) this;
        }

        public Criteria andFsysnoLike(String value) {
            addCriterion("FSysNo like", value, "fsysno");
            return (Criteria) this;
        }

        public Criteria andFsysnoNotLike(String value) {
            addCriterion("FSysNo not like", value, "fsysno");
            return (Criteria) this;
        }

        public Criteria andFsysnoIn(List<String> values) {
            addCriterion("FSysNo in", values, "fsysno");
            return (Criteria) this;
        }

        public Criteria andFsysnoNotIn(List<String> values) {
            addCriterion("FSysNo not in", values, "fsysno");
            return (Criteria) this;
        }

        public Criteria andFsysnoBetween(String value1, String value2) {
            addCriterion("FSysNo between", value1, value2, "fsysno");
            return (Criteria) this;
        }

        public Criteria andFsysnoNotBetween(String value1, String value2) {
            addCriterion("FSysNo not between", value1, value2, "fsysno");
            return (Criteria) this;
        }

        public Criteria andFsysnonumIsNull() {
            addCriterion("FSysNoNum is null");
            return (Criteria) this;
        }

        public Criteria andFsysnonumIsNotNull() {
            addCriterion("FSysNoNum is not null");
            return (Criteria) this;
        }

        public Criteria andFsysnonumEqualTo(Integer value) {
            addCriterion("FSysNoNum =", value, "fsysnonum");
            return (Criteria) this;
        }

        public Criteria andFsysnonumNotEqualTo(Integer value) {
            addCriterion("FSysNoNum <>", value, "fsysnonum");
            return (Criteria) this;
        }

        public Criteria andFsysnonumGreaterThan(Integer value) {
            addCriterion("FSysNoNum >", value, "fsysnonum");
            return (Criteria) this;
        }

        public Criteria andFsysnonumGreaterThanOrEqualTo(Integer value) {
            addCriterion("FSysNoNum >=", value, "fsysnonum");
            return (Criteria) this;
        }

        public Criteria andFsysnonumLessThan(Integer value) {
            addCriterion("FSysNoNum <", value, "fsysnonum");
            return (Criteria) this;
        }

        public Criteria andFsysnonumLessThanOrEqualTo(Integer value) {
            addCriterion("FSysNoNum <=", value, "fsysnonum");
            return (Criteria) this;
        }

        public Criteria andFsysnonumIn(List<Integer> values) {
            addCriterion("FSysNoNum in", values, "fsysnonum");
            return (Criteria) this;
        }

        public Criteria andFsysnonumNotIn(List<Integer> values) {
            addCriterion("FSysNoNum not in", values, "fsysnonum");
            return (Criteria) this;
        }

        public Criteria andFsysnonumBetween(Integer value1, Integer value2) {
            addCriterion("FSysNoNum between", value1, value2, "fsysnonum");
            return (Criteria) this;
        }

        public Criteria andFsysnonumNotBetween(Integer value1, Integer value2) {
            addCriterion("FSysNoNum not between", value1, value2, "fsysnonum");
            return (Criteria) this;
        }

        public Criteria andFyymmnumIsNull() {
            addCriterion("FYYMMNum is null");
            return (Criteria) this;
        }

        public Criteria andFyymmnumIsNotNull() {
            addCriterion("FYYMMNum is not null");
            return (Criteria) this;
        }

        public Criteria andFyymmnumEqualTo(String value) {
            addCriterion("FYYMMNum =", value, "fyymmnum");
            return (Criteria) this;
        }

        public Criteria andFyymmnumNotEqualTo(String value) {
            addCriterion("FYYMMNum <>", value, "fyymmnum");
            return (Criteria) this;
        }

        public Criteria andFyymmnumGreaterThan(String value) {
            addCriterion("FYYMMNum >", value, "fyymmnum");
            return (Criteria) this;
        }

        public Criteria andFyymmnumGreaterThanOrEqualTo(String value) {
            addCriterion("FYYMMNum >=", value, "fyymmnum");
            return (Criteria) this;
        }

        public Criteria andFyymmnumLessThan(String value) {
            addCriterion("FYYMMNum <", value, "fyymmnum");
            return (Criteria) this;
        }

        public Criteria andFyymmnumLessThanOrEqualTo(String value) {
            addCriterion("FYYMMNum <=", value, "fyymmnum");
            return (Criteria) this;
        }

        public Criteria andFyymmnumLike(String value) {
            addCriterion("FYYMMNum like", value, "fyymmnum");
            return (Criteria) this;
        }

        public Criteria andFyymmnumNotLike(String value) {
            addCriterion("FYYMMNum not like", value, "fyymmnum");
            return (Criteria) this;
        }

        public Criteria andFyymmnumIn(List<String> values) {
            addCriterion("FYYMMNum in", values, "fyymmnum");
            return (Criteria) this;
        }

        public Criteria andFyymmnumNotIn(List<String> values) {
            addCriterion("FYYMMNum not in", values, "fyymmnum");
            return (Criteria) this;
        }

        public Criteria andFyymmnumBetween(String value1, String value2) {
            addCriterion("FYYMMNum between", value1, value2, "fyymmnum");
            return (Criteria) this;
        }

        public Criteria andFyymmnumNotBetween(String value1, String value2) {
            addCriterion("FYYMMNum not between", value1, value2, "fyymmnum");
            return (Criteria) this;
        }

        public Criteria andFinsidenoIsNull() {
            addCriterion("FInsideNo is null");
            return (Criteria) this;
        }

        public Criteria andFinsidenoIsNotNull() {
            addCriterion("FInsideNo is not null");
            return (Criteria) this;
        }

        public Criteria andFinsidenoEqualTo(String value) {
            addCriterion("FInsideNo =", value, "finsideno");
            return (Criteria) this;
        }

        public Criteria andFinsidenoNotEqualTo(String value) {
            addCriterion("FInsideNo <>", value, "finsideno");
            return (Criteria) this;
        }

        public Criteria andFinsidenoGreaterThan(String value) {
            addCriterion("FInsideNo >", value, "finsideno");
            return (Criteria) this;
        }

        public Criteria andFinsidenoGreaterThanOrEqualTo(String value) {
            addCriterion("FInsideNo >=", value, "finsideno");
            return (Criteria) this;
        }

        public Criteria andFinsidenoLessThan(String value) {
            addCriterion("FInsideNo <", value, "finsideno");
            return (Criteria) this;
        }

        public Criteria andFinsidenoLessThanOrEqualTo(String value) {
            addCriterion("FInsideNo <=", value, "finsideno");
            return (Criteria) this;
        }

        public Criteria andFinsidenoLike(String value) {
            addCriterion("FInsideNo like", value, "finsideno");
            return (Criteria) this;
        }

        public Criteria andFinsidenoNotLike(String value) {
            addCriterion("FInsideNo not like", value, "finsideno");
            return (Criteria) this;
        }

        public Criteria andFinsidenoIn(List<String> values) {
            addCriterion("FInsideNo in", values, "finsideno");
            return (Criteria) this;
        }

        public Criteria andFinsidenoNotIn(List<String> values) {
            addCriterion("FInsideNo not in", values, "finsideno");
            return (Criteria) this;
        }

        public Criteria andFinsidenoBetween(String value1, String value2) {
            addCriterion("FInsideNo between", value1, value2, "finsideno");
            return (Criteria) this;
        }

        public Criteria andFinsidenoNotBetween(String value1, String value2) {
            addCriterion("FInsideNo not between", value1, value2, "finsideno");
            return (Criteria) this;
        }
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：teaching_diversity..t_project
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * TProject
     * Criterion
     * 数据库表：teaching_diversity..t_project
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