package fun.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TProjectPlanBglogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * t_project_plan_bglog
     * @mbg.generated
     */
    public TProjectPlanBglogExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * 设置排序字段
     * t_project_plan_bglog
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * t_project_plan_bglog
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * t_project_plan_bglog
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * t_project_plan_bglog
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * t_project_plan_bglog
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * t_project_plan_bglog
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * t_project_plan_bglog
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
     * t_project_plan_bglog
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
     * t_project_plan_bglog
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * t_project_plan_bglog
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TProjectPlanBglog
     * GeneratedCriteria
     * 数据库表：t_project_plan_bglog
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
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

        public Criteria andFprojectidIsNull() {
            addCriterion("FProjectID is null");
            return (Criteria) this;
        }

        public Criteria andFprojectidIsNotNull() {
            addCriterion("FProjectID is not null");
            return (Criteria) this;
        }

        public Criteria andFprojectidEqualTo(Long value) {
            addCriterion("FProjectID =", value, "fprojectid");
            return (Criteria) this;
        }

        public Criteria andFprojectidNotEqualTo(Long value) {
            addCriterion("FProjectID <>", value, "fprojectid");
            return (Criteria) this;
        }

        public Criteria andFprojectidGreaterThan(Long value) {
            addCriterion("FProjectID >", value, "fprojectid");
            return (Criteria) this;
        }

        public Criteria andFprojectidGreaterThanOrEqualTo(Long value) {
            addCriterion("FProjectID >=", value, "fprojectid");
            return (Criteria) this;
        }

        public Criteria andFprojectidLessThan(Long value) {
            addCriterion("FProjectID <", value, "fprojectid");
            return (Criteria) this;
        }

        public Criteria andFprojectidLessThanOrEqualTo(Long value) {
            addCriterion("FProjectID <=", value, "fprojectid");
            return (Criteria) this;
        }

        public Criteria andFprojectidIn(List<Long> values) {
            addCriterion("FProjectID in", values, "fprojectid");
            return (Criteria) this;
        }

        public Criteria andFprojectidNotIn(List<Long> values) {
            addCriterion("FProjectID not in", values, "fprojectid");
            return (Criteria) this;
        }

        public Criteria andFprojectidBetween(Long value1, Long value2) {
            addCriterion("FProjectID between", value1, value2, "fprojectid");
            return (Criteria) this;
        }

        public Criteria andFprojectidNotBetween(Long value1, Long value2) {
            addCriterion("FProjectID not between", value1, value2, "fprojectid");
            return (Criteria) this;
        }

        public Criteria andFprojectplanidIsNull() {
            addCriterion("FProjectPlanID is null");
            return (Criteria) this;
        }

        public Criteria andFprojectplanidIsNotNull() {
            addCriterion("FProjectPlanID is not null");
            return (Criteria) this;
        }

        public Criteria andFprojectplanidEqualTo(Long value) {
            addCriterion("FProjectPlanID =", value, "fprojectplanid");
            return (Criteria) this;
        }

        public Criteria andFprojectplanidNotEqualTo(Long value) {
            addCriterion("FProjectPlanID <>", value, "fprojectplanid");
            return (Criteria) this;
        }

        public Criteria andFprojectplanidGreaterThan(Long value) {
            addCriterion("FProjectPlanID >", value, "fprojectplanid");
            return (Criteria) this;
        }

        public Criteria andFprojectplanidGreaterThanOrEqualTo(Long value) {
            addCriterion("FProjectPlanID >=", value, "fprojectplanid");
            return (Criteria) this;
        }

        public Criteria andFprojectplanidLessThan(Long value) {
            addCriterion("FProjectPlanID <", value, "fprojectplanid");
            return (Criteria) this;
        }

        public Criteria andFprojectplanidLessThanOrEqualTo(Long value) {
            addCriterion("FProjectPlanID <=", value, "fprojectplanid");
            return (Criteria) this;
        }

        public Criteria andFprojectplanidIn(List<Long> values) {
            addCriterion("FProjectPlanID in", values, "fprojectplanid");
            return (Criteria) this;
        }

        public Criteria andFprojectplanidNotIn(List<Long> values) {
            addCriterion("FProjectPlanID not in", values, "fprojectplanid");
            return (Criteria) this;
        }

        public Criteria andFprojectplanidBetween(Long value1, Long value2) {
            addCriterion("FProjectPlanID between", value1, value2, "fprojectplanid");
            return (Criteria) this;
        }

        public Criteria andFprojectplanidNotBetween(Long value1, Long value2) {
            addCriterion("FProjectPlanID not between", value1, value2, "fprojectplanid");
            return (Criteria) this;
        }

        public Criteria andFqpsdateIsNull() {
            addCriterion("FQPSDate is null");
            return (Criteria) this;
        }

        public Criteria andFqpsdateIsNotNull() {
            addCriterion("FQPSDate is not null");
            return (Criteria) this;
        }

        public Criteria andFqpsdateEqualTo(Date value) {
            addCriterion("FQPSDate =", value, "fqpsdate");
            return (Criteria) this;
        }

        public Criteria andFqpsdateNotEqualTo(Date value) {
            addCriterion("FQPSDate <>", value, "fqpsdate");
            return (Criteria) this;
        }

        public Criteria andFqpsdateGreaterThan(Date value) {
            addCriterion("FQPSDate >", value, "fqpsdate");
            return (Criteria) this;
        }

        public Criteria andFqpsdateGreaterThanOrEqualTo(Date value) {
            addCriterion("FQPSDate >=", value, "fqpsdate");
            return (Criteria) this;
        }

        public Criteria andFqpsdateLessThan(Date value) {
            addCriterion("FQPSDate <", value, "fqpsdate");
            return (Criteria) this;
        }

        public Criteria andFqpsdateLessThanOrEqualTo(Date value) {
            addCriterion("FQPSDate <=", value, "fqpsdate");
            return (Criteria) this;
        }

        public Criteria andFqpsdateIn(List<Date> values) {
            addCriterion("FQPSDate in", values, "fqpsdate");
            return (Criteria) this;
        }

        public Criteria andFqpsdateNotIn(List<Date> values) {
            addCriterion("FQPSDate not in", values, "fqpsdate");
            return (Criteria) this;
        }

        public Criteria andFqpsdateBetween(Date value1, Date value2) {
            addCriterion("FQPSDate between", value1, value2, "fqpsdate");
            return (Criteria) this;
        }

        public Criteria andFqpsdateNotBetween(Date value1, Date value2) {
            addCriterion("FQPSDate not between", value1, value2, "fqpsdate");
            return (Criteria) this;
        }

        public Criteria andFqpedateIsNull() {
            addCriterion("FQPEDate is null");
            return (Criteria) this;
        }

        public Criteria andFqpedateIsNotNull() {
            addCriterion("FQPEDate is not null");
            return (Criteria) this;
        }

        public Criteria andFqpedateEqualTo(Date value) {
            addCriterion("FQPEDate =", value, "fqpedate");
            return (Criteria) this;
        }

        public Criteria andFqpedateNotEqualTo(Date value) {
            addCriterion("FQPEDate <>", value, "fqpedate");
            return (Criteria) this;
        }

        public Criteria andFqpedateGreaterThan(Date value) {
            addCriterion("FQPEDate >", value, "fqpedate");
            return (Criteria) this;
        }

        public Criteria andFqpedateGreaterThanOrEqualTo(Date value) {
            addCriterion("FQPEDate >=", value, "fqpedate");
            return (Criteria) this;
        }

        public Criteria andFqpedateLessThan(Date value) {
            addCriterion("FQPEDate <", value, "fqpedate");
            return (Criteria) this;
        }

        public Criteria andFqpedateLessThanOrEqualTo(Date value) {
            addCriterion("FQPEDate <=", value, "fqpedate");
            return (Criteria) this;
        }

        public Criteria andFqpedateIn(List<Date> values) {
            addCriterion("FQPEDate in", values, "fqpedate");
            return (Criteria) this;
        }

        public Criteria andFqpedateNotIn(List<Date> values) {
            addCriterion("FQPEDate not in", values, "fqpedate");
            return (Criteria) this;
        }

        public Criteria andFqpedateBetween(Date value1, Date value2) {
            addCriterion("FQPEDate between", value1, value2, "fqpedate");
            return (Criteria) this;
        }

        public Criteria andFqpedateNotBetween(Date value1, Date value2) {
            addCriterion("FQPEDate not between", value1, value2, "fqpedate");
            return (Criteria) this;
        }

        public Criteria andFpsdateIsNull() {
            addCriterion("FPSDate is null");
            return (Criteria) this;
        }

        public Criteria andFpsdateIsNotNull() {
            addCriterion("FPSDate is not null");
            return (Criteria) this;
        }

        public Criteria andFpsdateEqualTo(Date value) {
            addCriterion("FPSDate =", value, "fpsdate");
            return (Criteria) this;
        }

        public Criteria andFpsdateNotEqualTo(Date value) {
            addCriterion("FPSDate <>", value, "fpsdate");
            return (Criteria) this;
        }

        public Criteria andFpsdateGreaterThan(Date value) {
            addCriterion("FPSDate >", value, "fpsdate");
            return (Criteria) this;
        }

        public Criteria andFpsdateGreaterThanOrEqualTo(Date value) {
            addCriterion("FPSDate >=", value, "fpsdate");
            return (Criteria) this;
        }

        public Criteria andFpsdateLessThan(Date value) {
            addCriterion("FPSDate <", value, "fpsdate");
            return (Criteria) this;
        }

        public Criteria andFpsdateLessThanOrEqualTo(Date value) {
            addCriterion("FPSDate <=", value, "fpsdate");
            return (Criteria) this;
        }

        public Criteria andFpsdateIn(List<Date> values) {
            addCriterion("FPSDate in", values, "fpsdate");
            return (Criteria) this;
        }

        public Criteria andFpsdateNotIn(List<Date> values) {
            addCriterion("FPSDate not in", values, "fpsdate");
            return (Criteria) this;
        }

        public Criteria andFpsdateBetween(Date value1, Date value2) {
            addCriterion("FPSDate between", value1, value2, "fpsdate");
            return (Criteria) this;
        }

        public Criteria andFpsdateNotBetween(Date value1, Date value2) {
            addCriterion("FPSDate not between", value1, value2, "fpsdate");
            return (Criteria) this;
        }

        public Criteria andFpedateIsNull() {
            addCriterion("FPEDate is null");
            return (Criteria) this;
        }

        public Criteria andFpedateIsNotNull() {
            addCriterion("FPEDate is not null");
            return (Criteria) this;
        }

        public Criteria andFpedateEqualTo(Date value) {
            addCriterion("FPEDate =", value, "fpedate");
            return (Criteria) this;
        }

        public Criteria andFpedateNotEqualTo(Date value) {
            addCriterion("FPEDate <>", value, "fpedate");
            return (Criteria) this;
        }

        public Criteria andFpedateGreaterThan(Date value) {
            addCriterion("FPEDate >", value, "fpedate");
            return (Criteria) this;
        }

        public Criteria andFpedateGreaterThanOrEqualTo(Date value) {
            addCriterion("FPEDate >=", value, "fpedate");
            return (Criteria) this;
        }

        public Criteria andFpedateLessThan(Date value) {
            addCriterion("FPEDate <", value, "fpedate");
            return (Criteria) this;
        }

        public Criteria andFpedateLessThanOrEqualTo(Date value) {
            addCriterion("FPEDate <=", value, "fpedate");
            return (Criteria) this;
        }

        public Criteria andFpedateIn(List<Date> values) {
            addCriterion("FPEDate in", values, "fpedate");
            return (Criteria) this;
        }

        public Criteria andFpedateNotIn(List<Date> values) {
            addCriterion("FPEDate not in", values, "fpedate");
            return (Criteria) this;
        }

        public Criteria andFpedateBetween(Date value1, Date value2) {
            addCriterion("FPEDate between", value1, value2, "fpedate");
            return (Criteria) this;
        }

        public Criteria andFpedateNotBetween(Date value1, Date value2) {
            addCriterion("FPEDate not between", value1, value2, "fpedate");
            return (Criteria) this;
        }
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：t_project_plan_bglog
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * TProjectPlanBglog
     * Criterion
     * 数据库表：t_project_plan_bglog
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