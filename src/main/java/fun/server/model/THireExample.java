package fun.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class THireExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_hire
     * @mbg.generated
     */
    public THireExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * 设置排序字段
     * student_capability_evaluation..t_hire
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * student_capability_evaluation..t_hire
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * student_capability_evaluation..t_hire
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * student_capability_evaluation..t_hire
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * student_capability_evaluation..t_hire
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * student_capability_evaluation..t_hire
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * student_capability_evaluation..t_hire
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
     * student_capability_evaluation..t_hire
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
     * student_capability_evaluation..t_hire
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * student_capability_evaluation..t_hire
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * THire
     * GeneratedCriteria
     * 数据库表：student_capability_evaluation..t_hire
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

        public Criteria andFenterpriseidIsNull() {
            addCriterion("FEnterpriseID is null");
            return (Criteria) this;
        }

        public Criteria andFenterpriseidIsNotNull() {
            addCriterion("FEnterpriseID is not null");
            return (Criteria) this;
        }

        public Criteria andFenterpriseidEqualTo(Long value) {
            addCriterion("FEnterpriseID =", value, "fenterpriseid");
            return (Criteria) this;
        }

        public Criteria andFenterpriseidNotEqualTo(Long value) {
            addCriterion("FEnterpriseID <>", value, "fenterpriseid");
            return (Criteria) this;
        }

        public Criteria andFenterpriseidGreaterThan(Long value) {
            addCriterion("FEnterpriseID >", value, "fenterpriseid");
            return (Criteria) this;
        }

        public Criteria andFenterpriseidGreaterThanOrEqualTo(Long value) {
            addCriterion("FEnterpriseID >=", value, "fenterpriseid");
            return (Criteria) this;
        }

        public Criteria andFenterpriseidLessThan(Long value) {
            addCriterion("FEnterpriseID <", value, "fenterpriseid");
            return (Criteria) this;
        }

        public Criteria andFenterpriseidLessThanOrEqualTo(Long value) {
            addCriterion("FEnterpriseID <=", value, "fenterpriseid");
            return (Criteria) this;
        }

        public Criteria andFenterpriseidIn(List<Long> values) {
            addCriterion("FEnterpriseID in", values, "fenterpriseid");
            return (Criteria) this;
        }

        public Criteria andFenterpriseidNotIn(List<Long> values) {
            addCriterion("FEnterpriseID not in", values, "fenterpriseid");
            return (Criteria) this;
        }

        public Criteria andFenterpriseidBetween(Long value1, Long value2) {
            addCriterion("FEnterpriseID between", value1, value2, "fenterpriseid");
            return (Criteria) this;
        }

        public Criteria andFenterpriseidNotBetween(Long value1, Long value2) {
            addCriterion("FEnterpriseID not between", value1, value2, "fenterpriseid");
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

        public Criteria andFnumIsNull() {
            addCriterion("FNum is null");
            return (Criteria) this;
        }

        public Criteria andFnumIsNotNull() {
            addCriterion("FNum is not null");
            return (Criteria) this;
        }

        public Criteria andFnumEqualTo(Integer value) {
            addCriterion("FNum =", value, "fnum");
            return (Criteria) this;
        }

        public Criteria andFnumNotEqualTo(Integer value) {
            addCriterion("FNum <>", value, "fnum");
            return (Criteria) this;
        }

        public Criteria andFnumGreaterThan(Integer value) {
            addCriterion("FNum >", value, "fnum");
            return (Criteria) this;
        }

        public Criteria andFnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("FNum >=", value, "fnum");
            return (Criteria) this;
        }

        public Criteria andFnumLessThan(Integer value) {
            addCriterion("FNum <", value, "fnum");
            return (Criteria) this;
        }

        public Criteria andFnumLessThanOrEqualTo(Integer value) {
            addCriterion("FNum <=", value, "fnum");
            return (Criteria) this;
        }

        public Criteria andFnumIn(List<Integer> values) {
            addCriterion("FNum in", values, "fnum");
            return (Criteria) this;
        }

        public Criteria andFnumNotIn(List<Integer> values) {
            addCriterion("FNum not in", values, "fnum");
            return (Criteria) this;
        }

        public Criteria andFnumBetween(Integer value1, Integer value2) {
            addCriterion("FNum between", value1, value2, "fnum");
            return (Criteria) this;
        }

        public Criteria andFnumNotBetween(Integer value1, Integer value2) {
            addCriterion("FNum not between", value1, value2, "fnum");
            return (Criteria) this;
        }

        public Criteria andFwagesIsNull() {
            addCriterion("FWages is null");
            return (Criteria) this;
        }

        public Criteria andFwagesIsNotNull() {
            addCriterion("FWages is not null");
            return (Criteria) this;
        }

        public Criteria andFwagesEqualTo(String value) {
            addCriterion("FWages =", value, "fwages");
            return (Criteria) this;
        }

        public Criteria andFwagesNotEqualTo(String value) {
            addCriterion("FWages <>", value, "fwages");
            return (Criteria) this;
        }

        public Criteria andFwagesGreaterThan(String value) {
            addCriterion("FWages >", value, "fwages");
            return (Criteria) this;
        }

        public Criteria andFwagesGreaterThanOrEqualTo(String value) {
            addCriterion("FWages >=", value, "fwages");
            return (Criteria) this;
        }

        public Criteria andFwagesLessThan(String value) {
            addCriterion("FWages <", value, "fwages");
            return (Criteria) this;
        }

        public Criteria andFwagesLessThanOrEqualTo(String value) {
            addCriterion("FWages <=", value, "fwages");
            return (Criteria) this;
        }

        public Criteria andFwagesLike(String value) {
            addCriterion("FWages like", value, "fwages");
            return (Criteria) this;
        }

        public Criteria andFwagesNotLike(String value) {
            addCriterion("FWages not like", value, "fwages");
            return (Criteria) this;
        }

        public Criteria andFwagesIn(List<String> values) {
            addCriterion("FWages in", values, "fwages");
            return (Criteria) this;
        }

        public Criteria andFwagesNotIn(List<String> values) {
            addCriterion("FWages not in", values, "fwages");
            return (Criteria) this;
        }

        public Criteria andFwagesBetween(String value1, String value2) {
            addCriterion("FWages between", value1, value2, "fwages");
            return (Criteria) this;
        }

        public Criteria andFwagesNotBetween(String value1, String value2) {
            addCriterion("FWages not between", value1, value2, "fwages");
            return (Criteria) this;
        }

        public Criteria andFbenefitIsNull() {
            addCriterion("FBenefit is null");
            return (Criteria) this;
        }

        public Criteria andFbenefitIsNotNull() {
            addCriterion("FBenefit is not null");
            return (Criteria) this;
        }

        public Criteria andFbenefitEqualTo(String value) {
            addCriterion("FBenefit =", value, "fbenefit");
            return (Criteria) this;
        }

        public Criteria andFbenefitNotEqualTo(String value) {
            addCriterion("FBenefit <>", value, "fbenefit");
            return (Criteria) this;
        }

        public Criteria andFbenefitGreaterThan(String value) {
            addCriterion("FBenefit >", value, "fbenefit");
            return (Criteria) this;
        }

        public Criteria andFbenefitGreaterThanOrEqualTo(String value) {
            addCriterion("FBenefit >=", value, "fbenefit");
            return (Criteria) this;
        }

        public Criteria andFbenefitLessThan(String value) {
            addCriterion("FBenefit <", value, "fbenefit");
            return (Criteria) this;
        }

        public Criteria andFbenefitLessThanOrEqualTo(String value) {
            addCriterion("FBenefit <=", value, "fbenefit");
            return (Criteria) this;
        }

        public Criteria andFbenefitLike(String value) {
            addCriterion("FBenefit like", value, "fbenefit");
            return (Criteria) this;
        }

        public Criteria andFbenefitNotLike(String value) {
            addCriterion("FBenefit not like", value, "fbenefit");
            return (Criteria) this;
        }

        public Criteria andFbenefitIn(List<String> values) {
            addCriterion("FBenefit in", values, "fbenefit");
            return (Criteria) this;
        }

        public Criteria andFbenefitNotIn(List<String> values) {
            addCriterion("FBenefit not in", values, "fbenefit");
            return (Criteria) this;
        }

        public Criteria andFbenefitBetween(String value1, String value2) {
            addCriterion("FBenefit between", value1, value2, "fbenefit");
            return (Criteria) this;
        }

        public Criteria andFbenefitNotBetween(String value1, String value2) {
            addCriterion("FBenefit not between", value1, value2, "fbenefit");
            return (Criteria) this;
        }

        public Criteria andFworkdateIsNull() {
            addCriterion("FWorkDate is null");
            return (Criteria) this;
        }

        public Criteria andFworkdateIsNotNull() {
            addCriterion("FWorkDate is not null");
            return (Criteria) this;
        }

        public Criteria andFworkdateEqualTo(String value) {
            addCriterion("FWorkDate =", value, "fworkdate");
            return (Criteria) this;
        }

        public Criteria andFworkdateNotEqualTo(String value) {
            addCriterion("FWorkDate <>", value, "fworkdate");
            return (Criteria) this;
        }

        public Criteria andFworkdateGreaterThan(String value) {
            addCriterion("FWorkDate >", value, "fworkdate");
            return (Criteria) this;
        }

        public Criteria andFworkdateGreaterThanOrEqualTo(String value) {
            addCriterion("FWorkDate >=", value, "fworkdate");
            return (Criteria) this;
        }

        public Criteria andFworkdateLessThan(String value) {
            addCriterion("FWorkDate <", value, "fworkdate");
            return (Criteria) this;
        }

        public Criteria andFworkdateLessThanOrEqualTo(String value) {
            addCriterion("FWorkDate <=", value, "fworkdate");
            return (Criteria) this;
        }

        public Criteria andFworkdateLike(String value) {
            addCriterion("FWorkDate like", value, "fworkdate");
            return (Criteria) this;
        }

        public Criteria andFworkdateNotLike(String value) {
            addCriterion("FWorkDate not like", value, "fworkdate");
            return (Criteria) this;
        }

        public Criteria andFworkdateIn(List<String> values) {
            addCriterion("FWorkDate in", values, "fworkdate");
            return (Criteria) this;
        }

        public Criteria andFworkdateNotIn(List<String> values) {
            addCriterion("FWorkDate not in", values, "fworkdate");
            return (Criteria) this;
        }

        public Criteria andFworkdateBetween(String value1, String value2) {
            addCriterion("FWorkDate between", value1, value2, "fworkdate");
            return (Criteria) this;
        }

        public Criteria andFworkdateNotBetween(String value1, String value2) {
            addCriterion("FWorkDate not between", value1, value2, "fworkdate");
            return (Criteria) this;
        }

        public Criteria andFaddrIsNull() {
            addCriterion("FAddr is null");
            return (Criteria) this;
        }

        public Criteria andFaddrIsNotNull() {
            addCriterion("FAddr is not null");
            return (Criteria) this;
        }

        public Criteria andFaddrEqualTo(String value) {
            addCriterion("FAddr =", value, "faddr");
            return (Criteria) this;
        }

        public Criteria andFaddrNotEqualTo(String value) {
            addCriterion("FAddr <>", value, "faddr");
            return (Criteria) this;
        }

        public Criteria andFaddrGreaterThan(String value) {
            addCriterion("FAddr >", value, "faddr");
            return (Criteria) this;
        }

        public Criteria andFaddrGreaterThanOrEqualTo(String value) {
            addCriterion("FAddr >=", value, "faddr");
            return (Criteria) this;
        }

        public Criteria andFaddrLessThan(String value) {
            addCriterion("FAddr <", value, "faddr");
            return (Criteria) this;
        }

        public Criteria andFaddrLessThanOrEqualTo(String value) {
            addCriterion("FAddr <=", value, "faddr");
            return (Criteria) this;
        }

        public Criteria andFaddrLike(String value) {
            addCriterion("FAddr like", value, "faddr");
            return (Criteria) this;
        }

        public Criteria andFaddrNotLike(String value) {
            addCriterion("FAddr not like", value, "faddr");
            return (Criteria) this;
        }

        public Criteria andFaddrIn(List<String> values) {
            addCriterion("FAddr in", values, "faddr");
            return (Criteria) this;
        }

        public Criteria andFaddrNotIn(List<String> values) {
            addCriterion("FAddr not in", values, "faddr");
            return (Criteria) this;
        }

        public Criteria andFaddrBetween(String value1, String value2) {
            addCriterion("FAddr between", value1, value2, "faddr");
            return (Criteria) this;
        }

        public Criteria andFaddrNotBetween(String value1, String value2) {
            addCriterion("FAddr not between", value1, value2, "faddr");
            return (Criteria) this;
        }

        public Criteria andFconIsNull() {
            addCriterion("FCon is null");
            return (Criteria) this;
        }

        public Criteria andFconIsNotNull() {
            addCriterion("FCon is not null");
            return (Criteria) this;
        }

        public Criteria andFconEqualTo(String value) {
            addCriterion("FCon =", value, "fcon");
            return (Criteria) this;
        }

        public Criteria andFconNotEqualTo(String value) {
            addCriterion("FCon <>", value, "fcon");
            return (Criteria) this;
        }

        public Criteria andFconGreaterThan(String value) {
            addCriterion("FCon >", value, "fcon");
            return (Criteria) this;
        }

        public Criteria andFconGreaterThanOrEqualTo(String value) {
            addCriterion("FCon >=", value, "fcon");
            return (Criteria) this;
        }

        public Criteria andFconLessThan(String value) {
            addCriterion("FCon <", value, "fcon");
            return (Criteria) this;
        }

        public Criteria andFconLessThanOrEqualTo(String value) {
            addCriterion("FCon <=", value, "fcon");
            return (Criteria) this;
        }

        public Criteria andFconLike(String value) {
            addCriterion("FCon like", value, "fcon");
            return (Criteria) this;
        }

        public Criteria andFconNotLike(String value) {
            addCriterion("FCon not like", value, "fcon");
            return (Criteria) this;
        }

        public Criteria andFconIn(List<String> values) {
            addCriterion("FCon in", values, "fcon");
            return (Criteria) this;
        }

        public Criteria andFconNotIn(List<String> values) {
            addCriterion("FCon not in", values, "fcon");
            return (Criteria) this;
        }

        public Criteria andFconBetween(String value1, String value2) {
            addCriterion("FCon between", value1, value2, "fcon");
            return (Criteria) this;
        }

        public Criteria andFconNotBetween(String value1, String value2) {
            addCriterion("FCon not between", value1, value2, "fcon");
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
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：student_capability_evaluation..t_hire
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * THire
     * Criterion
     * 数据库表：student_capability_evaluation..t_hire
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