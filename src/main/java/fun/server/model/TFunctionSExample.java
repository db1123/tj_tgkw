package fun.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TFunctionSExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * t_function_s
     * @mbg.generated
     */
    public TFunctionSExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * 设置排序字段
     * t_function_s
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * t_function_s
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * t_function_s
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * t_function_s
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * t_function_s
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * t_function_s
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * t_function_s
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
     * t_function_s
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
     * t_function_s
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * t_function_s
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TFunctionS
     * GeneratedCriteria
     * 数据库表：t_function_s
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
            addCriterion("fkeyid is null");
            return (Criteria) this;
        }

        public Criteria andFkeyidIsNotNull() {
            addCriterion("fkeyid is not null");
            return (Criteria) this;
        }

        public Criteria andFkeyidEqualTo(Long value) {
            addCriterion("fkeyid =", value, "fkeyid");
            return (Criteria) this;
        }

        public Criteria andFkeyidNotEqualTo(Long value) {
            addCriterion("fkeyid <>", value, "fkeyid");
            return (Criteria) this;
        }

        public Criteria andFkeyidGreaterThan(Long value) {
            addCriterion("fkeyid >", value, "fkeyid");
            return (Criteria) this;
        }

        public Criteria andFkeyidGreaterThanOrEqualTo(Long value) {
            addCriterion("fkeyid >=", value, "fkeyid");
            return (Criteria) this;
        }

        public Criteria andFkeyidLessThan(Long value) {
            addCriterion("fkeyid <", value, "fkeyid");
            return (Criteria) this;
        }

        public Criteria andFkeyidLessThanOrEqualTo(Long value) {
            addCriterion("fkeyid <=", value, "fkeyid");
            return (Criteria) this;
        }

        public Criteria andFkeyidIn(List<Long> values) {
            addCriterion("fkeyid in", values, "fkeyid");
            return (Criteria) this;
        }

        public Criteria andFkeyidNotIn(List<Long> values) {
            addCriterion("fkeyid not in", values, "fkeyid");
            return (Criteria) this;
        }

        public Criteria andFkeyidBetween(Long value1, Long value2) {
            addCriterion("fkeyid between", value1, value2, "fkeyid");
            return (Criteria) this;
        }

        public Criteria andFkeyidNotBetween(Long value1, Long value2) {
            addCriterion("fkeyid not between", value1, value2, "fkeyid");
            return (Criteria) this;
        }

        public Criteria andFfuncmidIsNull() {
            addCriterion("ffuncmid is null");
            return (Criteria) this;
        }

        public Criteria andFfuncmidIsNotNull() {
            addCriterion("ffuncmid is not null");
            return (Criteria) this;
        }

        public Criteria andFfuncmidEqualTo(Long value) {
            addCriterion("ffuncmid =", value, "ffuncmid");
            return (Criteria) this;
        }

        public Criteria andFfuncmidNotEqualTo(Long value) {
            addCriterion("ffuncmid <>", value, "ffuncmid");
            return (Criteria) this;
        }

        public Criteria andFfuncmidGreaterThan(Long value) {
            addCriterion("ffuncmid >", value, "ffuncmid");
            return (Criteria) this;
        }

        public Criteria andFfuncmidGreaterThanOrEqualTo(Long value) {
            addCriterion("ffuncmid >=", value, "ffuncmid");
            return (Criteria) this;
        }

        public Criteria andFfuncmidLessThan(Long value) {
            addCriterion("ffuncmid <", value, "ffuncmid");
            return (Criteria) this;
        }

        public Criteria andFfuncmidLessThanOrEqualTo(Long value) {
            addCriterion("ffuncmid <=", value, "ffuncmid");
            return (Criteria) this;
        }

        public Criteria andFfuncmidIn(List<Long> values) {
            addCriterion("ffuncmid in", values, "ffuncmid");
            return (Criteria) this;
        }

        public Criteria andFfuncmidNotIn(List<Long> values) {
            addCriterion("ffuncmid not in", values, "ffuncmid");
            return (Criteria) this;
        }

        public Criteria andFfuncmidBetween(Long value1, Long value2) {
            addCriterion("ffuncmid between", value1, value2, "ffuncmid");
            return (Criteria) this;
        }

        public Criteria andFfuncmidNotBetween(Long value1, Long value2) {
            addCriterion("ffuncmid not between", value1, value2, "ffuncmid");
            return (Criteria) this;
        }

        public Criteria andFfidIsNull() {
            addCriterion("ffid is null");
            return (Criteria) this;
        }

        public Criteria andFfidIsNotNull() {
            addCriterion("ffid is not null");
            return (Criteria) this;
        }

        public Criteria andFfidEqualTo(Long value) {
            addCriterion("ffid =", value, "ffid");
            return (Criteria) this;
        }

        public Criteria andFfidNotEqualTo(Long value) {
            addCriterion("ffid <>", value, "ffid");
            return (Criteria) this;
        }

        public Criteria andFfidGreaterThan(Long value) {
            addCriterion("ffid >", value, "ffid");
            return (Criteria) this;
        }

        public Criteria andFfidGreaterThanOrEqualTo(Long value) {
            addCriterion("ffid >=", value, "ffid");
            return (Criteria) this;
        }

        public Criteria andFfidLessThan(Long value) {
            addCriterion("ffid <", value, "ffid");
            return (Criteria) this;
        }

        public Criteria andFfidLessThanOrEqualTo(Long value) {
            addCriterion("ffid <=", value, "ffid");
            return (Criteria) this;
        }

        public Criteria andFfidIn(List<Long> values) {
            addCriterion("ffid in", values, "ffid");
            return (Criteria) this;
        }

        public Criteria andFfidNotIn(List<Long> values) {
            addCriterion("ffid not in", values, "ffid");
            return (Criteria) this;
        }

        public Criteria andFfidBetween(Long value1, Long value2) {
            addCriterion("ffid between", value1, value2, "ffid");
            return (Criteria) this;
        }

        public Criteria andFfidNotBetween(Long value1, Long value2) {
            addCriterion("ffid not between", value1, value2, "ffid");
            return (Criteria) this;
        }

        public Criteria andFfuncunitidIsNull() {
            addCriterion("ffuncunitid is null");
            return (Criteria) this;
        }

        public Criteria andFfuncunitidIsNotNull() {
            addCriterion("ffuncunitid is not null");
            return (Criteria) this;
        }

        public Criteria andFfuncunitidEqualTo(Long value) {
            addCriterion("ffuncunitid =", value, "ffuncunitid");
            return (Criteria) this;
        }

        public Criteria andFfuncunitidNotEqualTo(Long value) {
            addCriterion("ffuncunitid <>", value, "ffuncunitid");
            return (Criteria) this;
        }

        public Criteria andFfuncunitidGreaterThan(Long value) {
            addCriterion("ffuncunitid >", value, "ffuncunitid");
            return (Criteria) this;
        }

        public Criteria andFfuncunitidGreaterThanOrEqualTo(Long value) {
            addCriterion("ffuncunitid >=", value, "ffuncunitid");
            return (Criteria) this;
        }

        public Criteria andFfuncunitidLessThan(Long value) {
            addCriterion("ffuncunitid <", value, "ffuncunitid");
            return (Criteria) this;
        }

        public Criteria andFfuncunitidLessThanOrEqualTo(Long value) {
            addCriterion("ffuncunitid <=", value, "ffuncunitid");
            return (Criteria) this;
        }

        public Criteria andFfuncunitidIn(List<Long> values) {
            addCriterion("ffuncunitid in", values, "ffuncunitid");
            return (Criteria) this;
        }

        public Criteria andFfuncunitidNotIn(List<Long> values) {
            addCriterion("ffuncunitid not in", values, "ffuncunitid");
            return (Criteria) this;
        }

        public Criteria andFfuncunitidBetween(Long value1, Long value2) {
            addCriterion("ffuncunitid between", value1, value2, "ffuncunitid");
            return (Criteria) this;
        }

        public Criteria andFfuncunitidNotBetween(Long value1, Long value2) {
            addCriterion("ffuncunitid not between", value1, value2, "ffuncunitid");
            return (Criteria) this;
        }

        public Criteria andFlevelIsNull() {
            addCriterion("flevel is null");
            return (Criteria) this;
        }

        public Criteria andFlevelIsNotNull() {
            addCriterion("flevel is not null");
            return (Criteria) this;
        }

        public Criteria andFlevelEqualTo(Integer value) {
            addCriterion("flevel =", value, "flevel");
            return (Criteria) this;
        }

        public Criteria andFlevelNotEqualTo(Integer value) {
            addCriterion("flevel <>", value, "flevel");
            return (Criteria) this;
        }

        public Criteria andFlevelGreaterThan(Integer value) {
            addCriterion("flevel >", value, "flevel");
            return (Criteria) this;
        }

        public Criteria andFlevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("flevel >=", value, "flevel");
            return (Criteria) this;
        }

        public Criteria andFlevelLessThan(Integer value) {
            addCriterion("flevel <", value, "flevel");
            return (Criteria) this;
        }

        public Criteria andFlevelLessThanOrEqualTo(Integer value) {
            addCriterion("flevel <=", value, "flevel");
            return (Criteria) this;
        }

        public Criteria andFlevelIn(List<Integer> values) {
            addCriterion("flevel in", values, "flevel");
            return (Criteria) this;
        }

        public Criteria andFlevelNotIn(List<Integer> values) {
            addCriterion("flevel not in", values, "flevel");
            return (Criteria) this;
        }

        public Criteria andFlevelBetween(Integer value1, Integer value2) {
            addCriterion("flevel between", value1, value2, "flevel");
            return (Criteria) this;
        }

        public Criteria andFlevelNotBetween(Integer value1, Integer value2) {
            addCriterion("flevel not between", value1, value2, "flevel");
            return (Criteria) this;
        }

        public Criteria andFleafIsNull() {
            addCriterion("fleaf is null");
            return (Criteria) this;
        }

        public Criteria andFleafIsNotNull() {
            addCriterion("fleaf is not null");
            return (Criteria) this;
        }

        public Criteria andFleafEqualTo(Integer value) {
            addCriterion("fleaf =", value, "fleaf");
            return (Criteria) this;
        }

        public Criteria andFleafNotEqualTo(Integer value) {
            addCriterion("fleaf <>", value, "fleaf");
            return (Criteria) this;
        }

        public Criteria andFleafGreaterThan(Integer value) {
            addCriterion("fleaf >", value, "fleaf");
            return (Criteria) this;
        }

        public Criteria andFleafGreaterThanOrEqualTo(Integer value) {
            addCriterion("fleaf >=", value, "fleaf");
            return (Criteria) this;
        }

        public Criteria andFleafLessThan(Integer value) {
            addCriterion("fleaf <", value, "fleaf");
            return (Criteria) this;
        }

        public Criteria andFleafLessThanOrEqualTo(Integer value) {
            addCriterion("fleaf <=", value, "fleaf");
            return (Criteria) this;
        }

        public Criteria andFleafIn(List<Integer> values) {
            addCriterion("fleaf in", values, "fleaf");
            return (Criteria) this;
        }

        public Criteria andFleafNotIn(List<Integer> values) {
            addCriterion("fleaf not in", values, "fleaf");
            return (Criteria) this;
        }

        public Criteria andFleafBetween(Integer value1, Integer value2) {
            addCriterion("fleaf between", value1, value2, "fleaf");
            return (Criteria) this;
        }

        public Criteria andFleafNotBetween(Integer value1, Integer value2) {
            addCriterion("fleaf not between", value1, value2, "fleaf");
            return (Criteria) this;
        }

        public Criteria andForderIsNull() {
            addCriterion("forder is null");
            return (Criteria) this;
        }

        public Criteria andForderIsNotNull() {
            addCriterion("forder is not null");
            return (Criteria) this;
        }

        public Criteria andForderEqualTo(Integer value) {
            addCriterion("forder =", value, "forder");
            return (Criteria) this;
        }

        public Criteria andForderNotEqualTo(Integer value) {
            addCriterion("forder <>", value, "forder");
            return (Criteria) this;
        }

        public Criteria andForderGreaterThan(Integer value) {
            addCriterion("forder >", value, "forder");
            return (Criteria) this;
        }

        public Criteria andForderGreaterThanOrEqualTo(Integer value) {
            addCriterion("forder >=", value, "forder");
            return (Criteria) this;
        }

        public Criteria andForderLessThan(Integer value) {
            addCriterion("forder <", value, "forder");
            return (Criteria) this;
        }

        public Criteria andForderLessThanOrEqualTo(Integer value) {
            addCriterion("forder <=", value, "forder");
            return (Criteria) this;
        }

        public Criteria andForderIn(List<Integer> values) {
            addCriterion("forder in", values, "forder");
            return (Criteria) this;
        }

        public Criteria andForderNotIn(List<Integer> values) {
            addCriterion("forder not in", values, "forder");
            return (Criteria) this;
        }

        public Criteria andForderBetween(Integer value1, Integer value2) {
            addCriterion("forder between", value1, value2, "forder");
            return (Criteria) this;
        }

        public Criteria andForderNotBetween(Integer value1, Integer value2) {
            addCriterion("forder not between", value1, value2, "forder");
            return (Criteria) this;
        }

        public Criteria andFmemoIsNull() {
            addCriterion("fmemo is null");
            return (Criteria) this;
        }

        public Criteria andFmemoIsNotNull() {
            addCriterion("fmemo is not null");
            return (Criteria) this;
        }

        public Criteria andFmemoEqualTo(String value) {
            addCriterion("fmemo =", value, "fmemo");
            return (Criteria) this;
        }

        public Criteria andFmemoNotEqualTo(String value) {
            addCriterion("fmemo <>", value, "fmemo");
            return (Criteria) this;
        }

        public Criteria andFmemoGreaterThan(String value) {
            addCriterion("fmemo >", value, "fmemo");
            return (Criteria) this;
        }

        public Criteria andFmemoGreaterThanOrEqualTo(String value) {
            addCriterion("fmemo >=", value, "fmemo");
            return (Criteria) this;
        }

        public Criteria andFmemoLessThan(String value) {
            addCriterion("fmemo <", value, "fmemo");
            return (Criteria) this;
        }

        public Criteria andFmemoLessThanOrEqualTo(String value) {
            addCriterion("fmemo <=", value, "fmemo");
            return (Criteria) this;
        }

        public Criteria andFmemoLike(String value) {
            addCriterion("fmemo like", value, "fmemo");
            return (Criteria) this;
        }

        public Criteria andFmemoNotLike(String value) {
            addCriterion("fmemo not like", value, "fmemo");
            return (Criteria) this;
        }

        public Criteria andFmemoIn(List<String> values) {
            addCriterion("fmemo in", values, "fmemo");
            return (Criteria) this;
        }

        public Criteria andFmemoNotIn(List<String> values) {
            addCriterion("fmemo not in", values, "fmemo");
            return (Criteria) this;
        }

        public Criteria andFmemoBetween(String value1, String value2) {
            addCriterion("fmemo between", value1, value2, "fmemo");
            return (Criteria) this;
        }

        public Criteria andFmemoNotBetween(String value1, String value2) {
            addCriterion("fmemo not between", value1, value2, "fmemo");
            return (Criteria) this;
        }

        public Criteria andFactiveIsNull() {
            addCriterion("factive is null");
            return (Criteria) this;
        }

        public Criteria andFactiveIsNotNull() {
            addCriterion("factive is not null");
            return (Criteria) this;
        }

        public Criteria andFactiveEqualTo(Integer value) {
            addCriterion("factive =", value, "factive");
            return (Criteria) this;
        }

        public Criteria andFactiveNotEqualTo(Integer value) {
            addCriterion("factive <>", value, "factive");
            return (Criteria) this;
        }

        public Criteria andFactiveGreaterThan(Integer value) {
            addCriterion("factive >", value, "factive");
            return (Criteria) this;
        }

        public Criteria andFactiveGreaterThanOrEqualTo(Integer value) {
            addCriterion("factive >=", value, "factive");
            return (Criteria) this;
        }

        public Criteria andFactiveLessThan(Integer value) {
            addCriterion("factive <", value, "factive");
            return (Criteria) this;
        }

        public Criteria andFactiveLessThanOrEqualTo(Integer value) {
            addCriterion("factive <=", value, "factive");
            return (Criteria) this;
        }

        public Criteria andFactiveIn(List<Integer> values) {
            addCriterion("factive in", values, "factive");
            return (Criteria) this;
        }

        public Criteria andFactiveNotIn(List<Integer> values) {
            addCriterion("factive not in", values, "factive");
            return (Criteria) this;
        }

        public Criteria andFactiveBetween(Integer value1, Integer value2) {
            addCriterion("factive between", value1, value2, "factive");
            return (Criteria) this;
        }

        public Criteria andFactiveNotBetween(Integer value1, Integer value2) {
            addCriterion("factive not between", value1, value2, "factive");
            return (Criteria) this;
        }

        public Criteria andFcidIsNull() {
            addCriterion("fcid is null");
            return (Criteria) this;
        }

        public Criteria andFcidIsNotNull() {
            addCriterion("fcid is not null");
            return (Criteria) this;
        }

        public Criteria andFcidEqualTo(Long value) {
            addCriterion("fcid =", value, "fcid");
            return (Criteria) this;
        }

        public Criteria andFcidNotEqualTo(Long value) {
            addCriterion("fcid <>", value, "fcid");
            return (Criteria) this;
        }

        public Criteria andFcidGreaterThan(Long value) {
            addCriterion("fcid >", value, "fcid");
            return (Criteria) this;
        }

        public Criteria andFcidGreaterThanOrEqualTo(Long value) {
            addCriterion("fcid >=", value, "fcid");
            return (Criteria) this;
        }

        public Criteria andFcidLessThan(Long value) {
            addCriterion("fcid <", value, "fcid");
            return (Criteria) this;
        }

        public Criteria andFcidLessThanOrEqualTo(Long value) {
            addCriterion("fcid <=", value, "fcid");
            return (Criteria) this;
        }

        public Criteria andFcidIn(List<Long> values) {
            addCriterion("fcid in", values, "fcid");
            return (Criteria) this;
        }

        public Criteria andFcidNotIn(List<Long> values) {
            addCriterion("fcid not in", values, "fcid");
            return (Criteria) this;
        }

        public Criteria andFcidBetween(Long value1, Long value2) {
            addCriterion("fcid between", value1, value2, "fcid");
            return (Criteria) this;
        }

        public Criteria andFcidNotBetween(Long value1, Long value2) {
            addCriterion("fcid not between", value1, value2, "fcid");
            return (Criteria) this;
        }

        public Criteria andFuidIsNull() {
            addCriterion("fuid is null");
            return (Criteria) this;
        }

        public Criteria andFuidIsNotNull() {
            addCriterion("fuid is not null");
            return (Criteria) this;
        }

        public Criteria andFuidEqualTo(Long value) {
            addCriterion("fuid =", value, "fuid");
            return (Criteria) this;
        }

        public Criteria andFuidNotEqualTo(Long value) {
            addCriterion("fuid <>", value, "fuid");
            return (Criteria) this;
        }

        public Criteria andFuidGreaterThan(Long value) {
            addCriterion("fuid >", value, "fuid");
            return (Criteria) this;
        }

        public Criteria andFuidGreaterThanOrEqualTo(Long value) {
            addCriterion("fuid >=", value, "fuid");
            return (Criteria) this;
        }

        public Criteria andFuidLessThan(Long value) {
            addCriterion("fuid <", value, "fuid");
            return (Criteria) this;
        }

        public Criteria andFuidLessThanOrEqualTo(Long value) {
            addCriterion("fuid <=", value, "fuid");
            return (Criteria) this;
        }

        public Criteria andFuidIn(List<Long> values) {
            addCriterion("fuid in", values, "fuid");
            return (Criteria) this;
        }

        public Criteria andFuidNotIn(List<Long> values) {
            addCriterion("fuid not in", values, "fuid");
            return (Criteria) this;
        }

        public Criteria andFuidBetween(Long value1, Long value2) {
            addCriterion("fuid between", value1, value2, "fuid");
            return (Criteria) this;
        }

        public Criteria andFuidNotBetween(Long value1, Long value2) {
            addCriterion("fuid not between", value1, value2, "fuid");
            return (Criteria) this;
        }

        public Criteria andFcdtIsNull() {
            addCriterion("fcdt is null");
            return (Criteria) this;
        }

        public Criteria andFcdtIsNotNull() {
            addCriterion("fcdt is not null");
            return (Criteria) this;
        }

        public Criteria andFcdtEqualTo(Date value) {
            addCriterion("fcdt =", value, "fcdt");
            return (Criteria) this;
        }

        public Criteria andFcdtNotEqualTo(Date value) {
            addCriterion("fcdt <>", value, "fcdt");
            return (Criteria) this;
        }

        public Criteria andFcdtGreaterThan(Date value) {
            addCriterion("fcdt >", value, "fcdt");
            return (Criteria) this;
        }

        public Criteria andFcdtGreaterThanOrEqualTo(Date value) {
            addCriterion("fcdt >=", value, "fcdt");
            return (Criteria) this;
        }

        public Criteria andFcdtLessThan(Date value) {
            addCriterion("fcdt <", value, "fcdt");
            return (Criteria) this;
        }

        public Criteria andFcdtLessThanOrEqualTo(Date value) {
            addCriterion("fcdt <=", value, "fcdt");
            return (Criteria) this;
        }

        public Criteria andFcdtIn(List<Date> values) {
            addCriterion("fcdt in", values, "fcdt");
            return (Criteria) this;
        }

        public Criteria andFcdtNotIn(List<Date> values) {
            addCriterion("fcdt not in", values, "fcdt");
            return (Criteria) this;
        }

        public Criteria andFcdtBetween(Date value1, Date value2) {
            addCriterion("fcdt between", value1, value2, "fcdt");
            return (Criteria) this;
        }

        public Criteria andFcdtNotBetween(Date value1, Date value2) {
            addCriterion("fcdt not between", value1, value2, "fcdt");
            return (Criteria) this;
        }

        public Criteria andFudtIsNull() {
            addCriterion("fudt is null");
            return (Criteria) this;
        }

        public Criteria andFudtIsNotNull() {
            addCriterion("fudt is not null");
            return (Criteria) this;
        }

        public Criteria andFudtEqualTo(Date value) {
            addCriterion("fudt =", value, "fudt");
            return (Criteria) this;
        }

        public Criteria andFudtNotEqualTo(Date value) {
            addCriterion("fudt <>", value, "fudt");
            return (Criteria) this;
        }

        public Criteria andFudtGreaterThan(Date value) {
            addCriterion("fudt >", value, "fudt");
            return (Criteria) this;
        }

        public Criteria andFudtGreaterThanOrEqualTo(Date value) {
            addCriterion("fudt >=", value, "fudt");
            return (Criteria) this;
        }

        public Criteria andFudtLessThan(Date value) {
            addCriterion("fudt <", value, "fudt");
            return (Criteria) this;
        }

        public Criteria andFudtLessThanOrEqualTo(Date value) {
            addCriterion("fudt <=", value, "fudt");
            return (Criteria) this;
        }

        public Criteria andFudtIn(List<Date> values) {
            addCriterion("fudt in", values, "fudt");
            return (Criteria) this;
        }

        public Criteria andFudtNotIn(List<Date> values) {
            addCriterion("fudt not in", values, "fudt");
            return (Criteria) this;
        }

        public Criteria andFudtBetween(Date value1, Date value2) {
            addCriterion("fudt between", value1, value2, "fudt");
            return (Criteria) this;
        }

        public Criteria andFudtNotBetween(Date value1, Date value2) {
            addCriterion("fudt not between", value1, value2, "fudt");
            return (Criteria) this;
        }
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：t_function_s
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * TFunctionS
     * Criterion
     * 数据库表：t_function_s
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