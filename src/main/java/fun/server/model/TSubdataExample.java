package fun.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TSubdataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * t_subdata
     * @mbg.generated
     */
    public TSubdataExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * 设置排序字段
     * t_subdata
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * t_subdata
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * t_subdata
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * t_subdata
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * t_subdata
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * t_subdata
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * t_subdata
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
     * t_subdata
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
     * t_subdata
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * t_subdata
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TSubdata
     * GeneratedCriteria
     * 数据库表：t_subdata
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

        public Criteria andFtypeIsNull() {
            addCriterion("FType is null");
            return (Criteria) this;
        }

        public Criteria andFtypeIsNotNull() {
            addCriterion("FType is not null");
            return (Criteria) this;
        }

        public Criteria andFtypeEqualTo(Integer value) {
            addCriterion("FType =", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeNotEqualTo(Integer value) {
            addCriterion("FType <>", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeGreaterThan(Integer value) {
            addCriterion("FType >", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("FType >=", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeLessThan(Integer value) {
            addCriterion("FType <", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeLessThanOrEqualTo(Integer value) {
            addCriterion("FType <=", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeIn(List<Integer> values) {
            addCriterion("FType in", values, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeNotIn(List<Integer> values) {
            addCriterion("FType not in", values, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeBetween(Integer value1, Integer value2) {
            addCriterion("FType between", value1, value2, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeNotBetween(Integer value1, Integer value2) {
            addCriterion("FType not between", value1, value2, "ftype");
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

        public Criteria andFtaskidIsNull() {
            addCriterion("FTaskID is null");
            return (Criteria) this;
        }

        public Criteria andFtaskidIsNotNull() {
            addCriterion("FTaskID is not null");
            return (Criteria) this;
        }

        public Criteria andFtaskidEqualTo(Long value) {
            addCriterion("FTaskID =", value, "ftaskid");
            return (Criteria) this;
        }

        public Criteria andFtaskidNotEqualTo(Long value) {
            addCriterion("FTaskID <>", value, "ftaskid");
            return (Criteria) this;
        }

        public Criteria andFtaskidGreaterThan(Long value) {
            addCriterion("FTaskID >", value, "ftaskid");
            return (Criteria) this;
        }

        public Criteria andFtaskidGreaterThanOrEqualTo(Long value) {
            addCriterion("FTaskID >=", value, "ftaskid");
            return (Criteria) this;
        }

        public Criteria andFtaskidLessThan(Long value) {
            addCriterion("FTaskID <", value, "ftaskid");
            return (Criteria) this;
        }

        public Criteria andFtaskidLessThanOrEqualTo(Long value) {
            addCriterion("FTaskID <=", value, "ftaskid");
            return (Criteria) this;
        }

        public Criteria andFtaskidIn(List<Long> values) {
            addCriterion("FTaskID in", values, "ftaskid");
            return (Criteria) this;
        }

        public Criteria andFtaskidNotIn(List<Long> values) {
            addCriterion("FTaskID not in", values, "ftaskid");
            return (Criteria) this;
        }

        public Criteria andFtaskidBetween(Long value1, Long value2) {
            addCriterion("FTaskID between", value1, value2, "ftaskid");
            return (Criteria) this;
        }

        public Criteria andFtaskidNotBetween(Long value1, Long value2) {
            addCriterion("FTaskID not between", value1, value2, "ftaskid");
            return (Criteria) this;
        }

        public Criteria andFworkflowidIsNull() {
            addCriterion("FWorkFlowID is null");
            return (Criteria) this;
        }

        public Criteria andFworkflowidIsNotNull() {
            addCriterion("FWorkFlowID is not null");
            return (Criteria) this;
        }

        public Criteria andFworkflowidEqualTo(Long value) {
            addCriterion("FWorkFlowID =", value, "fworkflowid");
            return (Criteria) this;
        }

        public Criteria andFworkflowidNotEqualTo(Long value) {
            addCriterion("FWorkFlowID <>", value, "fworkflowid");
            return (Criteria) this;
        }

        public Criteria andFworkflowidGreaterThan(Long value) {
            addCriterion("FWorkFlowID >", value, "fworkflowid");
            return (Criteria) this;
        }

        public Criteria andFworkflowidGreaterThanOrEqualTo(Long value) {
            addCriterion("FWorkFlowID >=", value, "fworkflowid");
            return (Criteria) this;
        }

        public Criteria andFworkflowidLessThan(Long value) {
            addCriterion("FWorkFlowID <", value, "fworkflowid");
            return (Criteria) this;
        }

        public Criteria andFworkflowidLessThanOrEqualTo(Long value) {
            addCriterion("FWorkFlowID <=", value, "fworkflowid");
            return (Criteria) this;
        }

        public Criteria andFworkflowidIn(List<Long> values) {
            addCriterion("FWorkFlowID in", values, "fworkflowid");
            return (Criteria) this;
        }

        public Criteria andFworkflowidNotIn(List<Long> values) {
            addCriterion("FWorkFlowID not in", values, "fworkflowid");
            return (Criteria) this;
        }

        public Criteria andFworkflowidBetween(Long value1, Long value2) {
            addCriterion("FWorkFlowID between", value1, value2, "fworkflowid");
            return (Criteria) this;
        }

        public Criteria andFworkflowidNotBetween(Long value1, Long value2) {
            addCriterion("FWorkFlowID not between", value1, value2, "fworkflowid");
            return (Criteria) this;
        }

        public Criteria andFnodeidIsNull() {
            addCriterion("FNodeID is null");
            return (Criteria) this;
        }

        public Criteria andFnodeidIsNotNull() {
            addCriterion("FNodeID is not null");
            return (Criteria) this;
        }

        public Criteria andFnodeidEqualTo(Long value) {
            addCriterion("FNodeID =", value, "fnodeid");
            return (Criteria) this;
        }

        public Criteria andFnodeidNotEqualTo(Long value) {
            addCriterion("FNodeID <>", value, "fnodeid");
            return (Criteria) this;
        }

        public Criteria andFnodeidGreaterThan(Long value) {
            addCriterion("FNodeID >", value, "fnodeid");
            return (Criteria) this;
        }

        public Criteria andFnodeidGreaterThanOrEqualTo(Long value) {
            addCriterion("FNodeID >=", value, "fnodeid");
            return (Criteria) this;
        }

        public Criteria andFnodeidLessThan(Long value) {
            addCriterion("FNodeID <", value, "fnodeid");
            return (Criteria) this;
        }

        public Criteria andFnodeidLessThanOrEqualTo(Long value) {
            addCriterion("FNodeID <=", value, "fnodeid");
            return (Criteria) this;
        }

        public Criteria andFnodeidIn(List<Long> values) {
            addCriterion("FNodeID in", values, "fnodeid");
            return (Criteria) this;
        }

        public Criteria andFnodeidNotIn(List<Long> values) {
            addCriterion("FNodeID not in", values, "fnodeid");
            return (Criteria) this;
        }

        public Criteria andFnodeidBetween(Long value1, Long value2) {
            addCriterion("FNodeID between", value1, value2, "fnodeid");
            return (Criteria) this;
        }

        public Criteria andFnodeidNotBetween(Long value1, Long value2) {
            addCriterion("FNodeID not between", value1, value2, "fnodeid");
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

        public Criteria andFtoolIsNull() {
            addCriterion("FTool is null");
            return (Criteria) this;
        }

        public Criteria andFtoolIsNotNull() {
            addCriterion("FTool is not null");
            return (Criteria) this;
        }

        public Criteria andFtoolEqualTo(String value) {
            addCriterion("FTool =", value, "ftool");
            return (Criteria) this;
        }

        public Criteria andFtoolNotEqualTo(String value) {
            addCriterion("FTool <>", value, "ftool");
            return (Criteria) this;
        }

        public Criteria andFtoolGreaterThan(String value) {
            addCriterion("FTool >", value, "ftool");
            return (Criteria) this;
        }

        public Criteria andFtoolGreaterThanOrEqualTo(String value) {
            addCriterion("FTool >=", value, "ftool");
            return (Criteria) this;
        }

        public Criteria andFtoolLessThan(String value) {
            addCriterion("FTool <", value, "ftool");
            return (Criteria) this;
        }

        public Criteria andFtoolLessThanOrEqualTo(String value) {
            addCriterion("FTool <=", value, "ftool");
            return (Criteria) this;
        }

        public Criteria andFtoolLike(String value) {
            addCriterion("FTool like", value, "ftool");
            return (Criteria) this;
        }

        public Criteria andFtoolNotLike(String value) {
            addCriterion("FTool not like", value, "ftool");
            return (Criteria) this;
        }

        public Criteria andFtoolIn(List<String> values) {
            addCriterion("FTool in", values, "ftool");
            return (Criteria) this;
        }

        public Criteria andFtoolNotIn(List<String> values) {
            addCriterion("FTool not in", values, "ftool");
            return (Criteria) this;
        }

        public Criteria andFtoolBetween(String value1, String value2) {
            addCriterion("FTool between", value1, value2, "ftool");
            return (Criteria) this;
        }

        public Criteria andFtoolNotBetween(String value1, String value2) {
            addCriterion("FTool not between", value1, value2, "ftool");
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

        public Criteria andFfiletypeIsNull() {
            addCriterion("FFileType is null");
            return (Criteria) this;
        }

        public Criteria andFfiletypeIsNotNull() {
            addCriterion("FFileType is not null");
            return (Criteria) this;
        }

        public Criteria andFfiletypeEqualTo(String value) {
            addCriterion("FFileType =", value, "ffiletype");
            return (Criteria) this;
        }

        public Criteria andFfiletypeNotEqualTo(String value) {
            addCriterion("FFileType <>", value, "ffiletype");
            return (Criteria) this;
        }

        public Criteria andFfiletypeGreaterThan(String value) {
            addCriterion("FFileType >", value, "ffiletype");
            return (Criteria) this;
        }

        public Criteria andFfiletypeGreaterThanOrEqualTo(String value) {
            addCriterion("FFileType >=", value, "ffiletype");
            return (Criteria) this;
        }

        public Criteria andFfiletypeLessThan(String value) {
            addCriterion("FFileType <", value, "ffiletype");
            return (Criteria) this;
        }

        public Criteria andFfiletypeLessThanOrEqualTo(String value) {
            addCriterion("FFileType <=", value, "ffiletype");
            return (Criteria) this;
        }

        public Criteria andFfiletypeLike(String value) {
            addCriterion("FFileType like", value, "ffiletype");
            return (Criteria) this;
        }

        public Criteria andFfiletypeNotLike(String value) {
            addCriterion("FFileType not like", value, "ffiletype");
            return (Criteria) this;
        }

        public Criteria andFfiletypeIn(List<String> values) {
            addCriterion("FFileType in", values, "ffiletype");
            return (Criteria) this;
        }

        public Criteria andFfiletypeNotIn(List<String> values) {
            addCriterion("FFileType not in", values, "ffiletype");
            return (Criteria) this;
        }

        public Criteria andFfiletypeBetween(String value1, String value2) {
            addCriterion("FFileType between", value1, value2, "ffiletype");
            return (Criteria) this;
        }

        public Criteria andFfiletypeNotBetween(String value1, String value2) {
            addCriterion("FFileType not between", value1, value2, "ffiletype");
            return (Criteria) this;
        }

        public Criteria andFcheckflowidIsNull() {
            addCriterion("FCheckFlowID is null");
            return (Criteria) this;
        }

        public Criteria andFcheckflowidIsNotNull() {
            addCriterion("FCheckFlowID is not null");
            return (Criteria) this;
        }

        public Criteria andFcheckflowidEqualTo(Long value) {
            addCriterion("FCheckFlowID =", value, "fcheckflowid");
            return (Criteria) this;
        }

        public Criteria andFcheckflowidNotEqualTo(Long value) {
            addCriterion("FCheckFlowID <>", value, "fcheckflowid");
            return (Criteria) this;
        }

        public Criteria andFcheckflowidGreaterThan(Long value) {
            addCriterion("FCheckFlowID >", value, "fcheckflowid");
            return (Criteria) this;
        }

        public Criteria andFcheckflowidGreaterThanOrEqualTo(Long value) {
            addCriterion("FCheckFlowID >=", value, "fcheckflowid");
            return (Criteria) this;
        }

        public Criteria andFcheckflowidLessThan(Long value) {
            addCriterion("FCheckFlowID <", value, "fcheckflowid");
            return (Criteria) this;
        }

        public Criteria andFcheckflowidLessThanOrEqualTo(Long value) {
            addCriterion("FCheckFlowID <=", value, "fcheckflowid");
            return (Criteria) this;
        }

        public Criteria andFcheckflowidIn(List<Long> values) {
            addCriterion("FCheckFlowID in", values, "fcheckflowid");
            return (Criteria) this;
        }

        public Criteria andFcheckflowidNotIn(List<Long> values) {
            addCriterion("FCheckFlowID not in", values, "fcheckflowid");
            return (Criteria) this;
        }

        public Criteria andFcheckflowidBetween(Long value1, Long value2) {
            addCriterion("FCheckFlowID between", value1, value2, "fcheckflowid");
            return (Criteria) this;
        }

        public Criteria andFcheckflowidNotBetween(Long value1, Long value2) {
            addCriterion("FCheckFlowID not between", value1, value2, "fcheckflowid");
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

        public Criteria andFsysnameIsNull() {
            addCriterion("FSysName is null");
            return (Criteria) this;
        }

        public Criteria andFsysnameIsNotNull() {
            addCriterion("FSysName is not null");
            return (Criteria) this;
        }

        public Criteria andFsysnameEqualTo(String value) {
            addCriterion("FSysName =", value, "fsysname");
            return (Criteria) this;
        }

        public Criteria andFsysnameNotEqualTo(String value) {
            addCriterion("FSysName <>", value, "fsysname");
            return (Criteria) this;
        }

        public Criteria andFsysnameGreaterThan(String value) {
            addCriterion("FSysName >", value, "fsysname");
            return (Criteria) this;
        }

        public Criteria andFsysnameGreaterThanOrEqualTo(String value) {
            addCriterion("FSysName >=", value, "fsysname");
            return (Criteria) this;
        }

        public Criteria andFsysnameLessThan(String value) {
            addCriterion("FSysName <", value, "fsysname");
            return (Criteria) this;
        }

        public Criteria andFsysnameLessThanOrEqualTo(String value) {
            addCriterion("FSysName <=", value, "fsysname");
            return (Criteria) this;
        }

        public Criteria andFsysnameLike(String value) {
            addCriterion("FSysName like", value, "fsysname");
            return (Criteria) this;
        }

        public Criteria andFsysnameNotLike(String value) {
            addCriterion("FSysName not like", value, "fsysname");
            return (Criteria) this;
        }

        public Criteria andFsysnameIn(List<String> values) {
            addCriterion("FSysName in", values, "fsysname");
            return (Criteria) this;
        }

        public Criteria andFsysnameNotIn(List<String> values) {
            addCriterion("FSysName not in", values, "fsysname");
            return (Criteria) this;
        }

        public Criteria andFsysnameBetween(String value1, String value2) {
            addCriterion("FSysName between", value1, value2, "fsysname");
            return (Criteria) this;
        }

        public Criteria andFsysnameNotBetween(String value1, String value2) {
            addCriterion("FSysName not between", value1, value2, "fsysname");
            return (Criteria) this;
        }

        public Criteria andFfilenameIsNull() {
            addCriterion("FFileName is null");
            return (Criteria) this;
        }

        public Criteria andFfilenameIsNotNull() {
            addCriterion("FFileName is not null");
            return (Criteria) this;
        }

        public Criteria andFfilenameEqualTo(String value) {
            addCriterion("FFileName =", value, "ffilename");
            return (Criteria) this;
        }

        public Criteria andFfilenameNotEqualTo(String value) {
            addCriterion("FFileName <>", value, "ffilename");
            return (Criteria) this;
        }

        public Criteria andFfilenameGreaterThan(String value) {
            addCriterion("FFileName >", value, "ffilename");
            return (Criteria) this;
        }

        public Criteria andFfilenameGreaterThanOrEqualTo(String value) {
            addCriterion("FFileName >=", value, "ffilename");
            return (Criteria) this;
        }

        public Criteria andFfilenameLessThan(String value) {
            addCriterion("FFileName <", value, "ffilename");
            return (Criteria) this;
        }

        public Criteria andFfilenameLessThanOrEqualTo(String value) {
            addCriterion("FFileName <=", value, "ffilename");
            return (Criteria) this;
        }

        public Criteria andFfilenameLike(String value) {
            addCriterion("FFileName like", value, "ffilename");
            return (Criteria) this;
        }

        public Criteria andFfilenameNotLike(String value) {
            addCriterion("FFileName not like", value, "ffilename");
            return (Criteria) this;
        }

        public Criteria andFfilenameIn(List<String> values) {
            addCriterion("FFileName in", values, "ffilename");
            return (Criteria) this;
        }

        public Criteria andFfilenameNotIn(List<String> values) {
            addCriterion("FFileName not in", values, "ffilename");
            return (Criteria) this;
        }

        public Criteria andFfilenameBetween(String value1, String value2) {
            addCriterion("FFileName between", value1, value2, "ffilename");
            return (Criteria) this;
        }

        public Criteria andFfilenameNotBetween(String value1, String value2) {
            addCriterion("FFileName not between", value1, value2, "ffilename");
            return (Criteria) this;
        }

        public Criteria andFsubuseridIsNull() {
            addCriterion("FSubUserID is null");
            return (Criteria) this;
        }

        public Criteria andFsubuseridIsNotNull() {
            addCriterion("FSubUserID is not null");
            return (Criteria) this;
        }

        public Criteria andFsubuseridEqualTo(Long value) {
            addCriterion("FSubUserID =", value, "fsubuserid");
            return (Criteria) this;
        }

        public Criteria andFsubuseridNotEqualTo(Long value) {
            addCriterion("FSubUserID <>", value, "fsubuserid");
            return (Criteria) this;
        }

        public Criteria andFsubuseridGreaterThan(Long value) {
            addCriterion("FSubUserID >", value, "fsubuserid");
            return (Criteria) this;
        }

        public Criteria andFsubuseridGreaterThanOrEqualTo(Long value) {
            addCriterion("FSubUserID >=", value, "fsubuserid");
            return (Criteria) this;
        }

        public Criteria andFsubuseridLessThan(Long value) {
            addCriterion("FSubUserID <", value, "fsubuserid");
            return (Criteria) this;
        }

        public Criteria andFsubuseridLessThanOrEqualTo(Long value) {
            addCriterion("FSubUserID <=", value, "fsubuserid");
            return (Criteria) this;
        }

        public Criteria andFsubuseridIn(List<Long> values) {
            addCriterion("FSubUserID in", values, "fsubuserid");
            return (Criteria) this;
        }

        public Criteria andFsubuseridNotIn(List<Long> values) {
            addCriterion("FSubUserID not in", values, "fsubuserid");
            return (Criteria) this;
        }

        public Criteria andFsubuseridBetween(Long value1, Long value2) {
            addCriterion("FSubUserID between", value1, value2, "fsubuserid");
            return (Criteria) this;
        }

        public Criteria andFsubuseridNotBetween(Long value1, Long value2) {
            addCriterion("FSubUserID not between", value1, value2, "fsubuserid");
            return (Criteria) this;
        }

        public Criteria andFsubdateIsNull() {
            addCriterion("FSubDate is null");
            return (Criteria) this;
        }

        public Criteria andFsubdateIsNotNull() {
            addCriterion("FSubDate is not null");
            return (Criteria) this;
        }

        public Criteria andFsubdateEqualTo(Date value) {
            addCriterion("FSubDate =", value, "fsubdate");
            return (Criteria) this;
        }

        public Criteria andFsubdateNotEqualTo(Date value) {
            addCriterion("FSubDate <>", value, "fsubdate");
            return (Criteria) this;
        }

        public Criteria andFsubdateGreaterThan(Date value) {
            addCriterion("FSubDate >", value, "fsubdate");
            return (Criteria) this;
        }

        public Criteria andFsubdateGreaterThanOrEqualTo(Date value) {
            addCriterion("FSubDate >=", value, "fsubdate");
            return (Criteria) this;
        }

        public Criteria andFsubdateLessThan(Date value) {
            addCriterion("FSubDate <", value, "fsubdate");
            return (Criteria) this;
        }

        public Criteria andFsubdateLessThanOrEqualTo(Date value) {
            addCriterion("FSubDate <=", value, "fsubdate");
            return (Criteria) this;
        }

        public Criteria andFsubdateIn(List<Date> values) {
            addCriterion("FSubDate in", values, "fsubdate");
            return (Criteria) this;
        }

        public Criteria andFsubdateNotIn(List<Date> values) {
            addCriterion("FSubDate not in", values, "fsubdate");
            return (Criteria) this;
        }

        public Criteria andFsubdateBetween(Date value1, Date value2) {
            addCriterion("FSubDate between", value1, value2, "fsubdate");
            return (Criteria) this;
        }

        public Criteria andFsubdateNotBetween(Date value1, Date value2) {
            addCriterion("FSubDate not between", value1, value2, "fsubdate");
            return (Criteria) this;
        }

        public Criteria andFsubmodeIsNull() {
            addCriterion("FSubMode is null");
            return (Criteria) this;
        }

        public Criteria andFsubmodeIsNotNull() {
            addCriterion("FSubMode is not null");
            return (Criteria) this;
        }

        public Criteria andFsubmodeEqualTo(Integer value) {
            addCriterion("FSubMode =", value, "fsubmode");
            return (Criteria) this;
        }

        public Criteria andFsubmodeNotEqualTo(Integer value) {
            addCriterion("FSubMode <>", value, "fsubmode");
            return (Criteria) this;
        }

        public Criteria andFsubmodeGreaterThan(Integer value) {
            addCriterion("FSubMode >", value, "fsubmode");
            return (Criteria) this;
        }

        public Criteria andFsubmodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("FSubMode >=", value, "fsubmode");
            return (Criteria) this;
        }

        public Criteria andFsubmodeLessThan(Integer value) {
            addCriterion("FSubMode <", value, "fsubmode");
            return (Criteria) this;
        }

        public Criteria andFsubmodeLessThanOrEqualTo(Integer value) {
            addCriterion("FSubMode <=", value, "fsubmode");
            return (Criteria) this;
        }

        public Criteria andFsubmodeIn(List<Integer> values) {
            addCriterion("FSubMode in", values, "fsubmode");
            return (Criteria) this;
        }

        public Criteria andFsubmodeNotIn(List<Integer> values) {
            addCriterion("FSubMode not in", values, "fsubmode");
            return (Criteria) this;
        }

        public Criteria andFsubmodeBetween(Integer value1, Integer value2) {
            addCriterion("FSubMode between", value1, value2, "fsubmode");
            return (Criteria) this;
        }

        public Criteria andFsubmodeNotBetween(Integer value1, Integer value2) {
            addCriterion("FSubMode not between", value1, value2, "fsubmode");
            return (Criteria) this;
        }

        public Criteria andFcheckstateIsNull() {
            addCriterion("FCheckState is null");
            return (Criteria) this;
        }

        public Criteria andFcheckstateIsNotNull() {
            addCriterion("FCheckState is not null");
            return (Criteria) this;
        }

        public Criteria andFcheckstateEqualTo(Integer value) {
            addCriterion("FCheckState =", value, "fcheckstate");
            return (Criteria) this;
        }

        public Criteria andFcheckstateNotEqualTo(Integer value) {
            addCriterion("FCheckState <>", value, "fcheckstate");
            return (Criteria) this;
        }

        public Criteria andFcheckstateGreaterThan(Integer value) {
            addCriterion("FCheckState >", value, "fcheckstate");
            return (Criteria) this;
        }

        public Criteria andFcheckstateGreaterThanOrEqualTo(Integer value) {
            addCriterion("FCheckState >=", value, "fcheckstate");
            return (Criteria) this;
        }

        public Criteria andFcheckstateLessThan(Integer value) {
            addCriterion("FCheckState <", value, "fcheckstate");
            return (Criteria) this;
        }

        public Criteria andFcheckstateLessThanOrEqualTo(Integer value) {
            addCriterion("FCheckState <=", value, "fcheckstate");
            return (Criteria) this;
        }

        public Criteria andFcheckstateIn(List<Integer> values) {
            addCriterion("FCheckState in", values, "fcheckstate");
            return (Criteria) this;
        }

        public Criteria andFcheckstateNotIn(List<Integer> values) {
            addCriterion("FCheckState not in", values, "fcheckstate");
            return (Criteria) this;
        }

        public Criteria andFcheckstateBetween(Integer value1, Integer value2) {
            addCriterion("FCheckState between", value1, value2, "fcheckstate");
            return (Criteria) this;
        }

        public Criteria andFcheckstateNotBetween(Integer value1, Integer value2) {
            addCriterion("FCheckState not between", value1, value2, "fcheckstate");
            return (Criteria) this;
        }

        public Criteria andFsubdataidIsNull() {
            addCriterion("FSubDataID is null");
            return (Criteria) this;
        }

        public Criteria andFsubdataidIsNotNull() {
            addCriterion("FSubDataID is not null");
            return (Criteria) this;
        }

        public Criteria andFsubdataidEqualTo(Long value) {
            addCriterion("FSubDataID =", value, "fsubdataid");
            return (Criteria) this;
        }

        public Criteria andFsubdataidNotEqualTo(Long value) {
            addCriterion("FSubDataID <>", value, "fsubdataid");
            return (Criteria) this;
        }

        public Criteria andFsubdataidGreaterThan(Long value) {
            addCriterion("FSubDataID >", value, "fsubdataid");
            return (Criteria) this;
        }

        public Criteria andFsubdataidGreaterThanOrEqualTo(Long value) {
            addCriterion("FSubDataID >=", value, "fsubdataid");
            return (Criteria) this;
        }

        public Criteria andFsubdataidLessThan(Long value) {
            addCriterion("FSubDataID <", value, "fsubdataid");
            return (Criteria) this;
        }

        public Criteria andFsubdataidLessThanOrEqualTo(Long value) {
            addCriterion("FSubDataID <=", value, "fsubdataid");
            return (Criteria) this;
        }

        public Criteria andFsubdataidIn(List<Long> values) {
            addCriterion("FSubDataID in", values, "fsubdataid");
            return (Criteria) this;
        }

        public Criteria andFsubdataidNotIn(List<Long> values) {
            addCriterion("FSubDataID not in", values, "fsubdataid");
            return (Criteria) this;
        }

        public Criteria andFsubdataidBetween(Long value1, Long value2) {
            addCriterion("FSubDataID between", value1, value2, "fsubdataid");
            return (Criteria) this;
        }

        public Criteria andFsubdataidNotBetween(Long value1, Long value2) {
            addCriterion("FSubDataID not between", value1, value2, "fsubdataid");
            return (Criteria) this;
        }

        public Criteria andFsubdatanumIsNull() {
            addCriterion("FSubDataNum is null");
            return (Criteria) this;
        }

        public Criteria andFsubdatanumIsNotNull() {
            addCriterion("FSubDataNum is not null");
            return (Criteria) this;
        }

        public Criteria andFsubdatanumEqualTo(Integer value) {
            addCriterion("FSubDataNum =", value, "fsubdatanum");
            return (Criteria) this;
        }

        public Criteria andFsubdatanumNotEqualTo(Integer value) {
            addCriterion("FSubDataNum <>", value, "fsubdatanum");
            return (Criteria) this;
        }

        public Criteria andFsubdatanumGreaterThan(Integer value) {
            addCriterion("FSubDataNum >", value, "fsubdatanum");
            return (Criteria) this;
        }

        public Criteria andFsubdatanumGreaterThanOrEqualTo(Integer value) {
            addCriterion("FSubDataNum >=", value, "fsubdatanum");
            return (Criteria) this;
        }

        public Criteria andFsubdatanumLessThan(Integer value) {
            addCriterion("FSubDataNum <", value, "fsubdatanum");
            return (Criteria) this;
        }

        public Criteria andFsubdatanumLessThanOrEqualTo(Integer value) {
            addCriterion("FSubDataNum <=", value, "fsubdatanum");
            return (Criteria) this;
        }

        public Criteria andFsubdatanumIn(List<Integer> values) {
            addCriterion("FSubDataNum in", values, "fsubdatanum");
            return (Criteria) this;
        }

        public Criteria andFsubdatanumNotIn(List<Integer> values) {
            addCriterion("FSubDataNum not in", values, "fsubdatanum");
            return (Criteria) this;
        }

        public Criteria andFsubdatanumBetween(Integer value1, Integer value2) {
            addCriterion("FSubDataNum between", value1, value2, "fsubdatanum");
            return (Criteria) this;
        }

        public Criteria andFsubdatanumNotBetween(Integer value1, Integer value2) {
            addCriterion("FSubDataNum not between", value1, value2, "fsubdatanum");
            return (Criteria) this;
        }

        public Criteria andFhavetypeIsNull() {
            addCriterion("FHaveType is null");
            return (Criteria) this;
        }

        public Criteria andFhavetypeIsNotNull() {
            addCriterion("FHaveType is not null");
            return (Criteria) this;
        }

        public Criteria andFhavetypeEqualTo(Integer value) {
            addCriterion("FHaveType =", value, "fhavetype");
            return (Criteria) this;
        }

        public Criteria andFhavetypeNotEqualTo(Integer value) {
            addCriterion("FHaveType <>", value, "fhavetype");
            return (Criteria) this;
        }

        public Criteria andFhavetypeGreaterThan(Integer value) {
            addCriterion("FHaveType >", value, "fhavetype");
            return (Criteria) this;
        }

        public Criteria andFhavetypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("FHaveType >=", value, "fhavetype");
            return (Criteria) this;
        }

        public Criteria andFhavetypeLessThan(Integer value) {
            addCriterion("FHaveType <", value, "fhavetype");
            return (Criteria) this;
        }

        public Criteria andFhavetypeLessThanOrEqualTo(Integer value) {
            addCriterion("FHaveType <=", value, "fhavetype");
            return (Criteria) this;
        }

        public Criteria andFhavetypeIn(List<Integer> values) {
            addCriterion("FHaveType in", values, "fhavetype");
            return (Criteria) this;
        }

        public Criteria andFhavetypeNotIn(List<Integer> values) {
            addCriterion("FHaveType not in", values, "fhavetype");
            return (Criteria) this;
        }

        public Criteria andFhavetypeBetween(Integer value1, Integer value2) {
            addCriterion("FHaveType between", value1, value2, "fhavetype");
            return (Criteria) this;
        }

        public Criteria andFhavetypeNotBetween(Integer value1, Integer value2) {
            addCriterion("FHaveType not between", value1, value2, "fhavetype");
            return (Criteria) this;
        }

        public Criteria andFisinitIsNull() {
            addCriterion("FISInit is null");
            return (Criteria) this;
        }

        public Criteria andFisinitIsNotNull() {
            addCriterion("FISInit is not null");
            return (Criteria) this;
        }

        public Criteria andFisinitEqualTo(Integer value) {
            addCriterion("FISInit =", value, "fisinit");
            return (Criteria) this;
        }

        public Criteria andFisinitNotEqualTo(Integer value) {
            addCriterion("FISInit <>", value, "fisinit");
            return (Criteria) this;
        }

        public Criteria andFisinitGreaterThan(Integer value) {
            addCriterion("FISInit >", value, "fisinit");
            return (Criteria) this;
        }

        public Criteria andFisinitGreaterThanOrEqualTo(Integer value) {
            addCriterion("FISInit >=", value, "fisinit");
            return (Criteria) this;
        }

        public Criteria andFisinitLessThan(Integer value) {
            addCriterion("FISInit <", value, "fisinit");
            return (Criteria) this;
        }

        public Criteria andFisinitLessThanOrEqualTo(Integer value) {
            addCriterion("FISInit <=", value, "fisinit");
            return (Criteria) this;
        }

        public Criteria andFisinitIn(List<Integer> values) {
            addCriterion("FISInit in", values, "fisinit");
            return (Criteria) this;
        }

        public Criteria andFisinitNotIn(List<Integer> values) {
            addCriterion("FISInit not in", values, "fisinit");
            return (Criteria) this;
        }

        public Criteria andFisinitBetween(Integer value1, Integer value2) {
            addCriterion("FISInit between", value1, value2, "fisinit");
            return (Criteria) this;
        }

        public Criteria andFisinitNotBetween(Integer value1, Integer value2) {
            addCriterion("FISInit not between", value1, value2, "fisinit");
            return (Criteria) this;
        }

        public Criteria andFinitfileidIsNull() {
            addCriterion("FInitFileID is null");
            return (Criteria) this;
        }

        public Criteria andFinitfileidIsNotNull() {
            addCriterion("FInitFileID is not null");
            return (Criteria) this;
        }

        public Criteria andFinitfileidEqualTo(Long value) {
            addCriterion("FInitFileID =", value, "finitfileid");
            return (Criteria) this;
        }

        public Criteria andFinitfileidNotEqualTo(Long value) {
            addCriterion("FInitFileID <>", value, "finitfileid");
            return (Criteria) this;
        }

        public Criteria andFinitfileidGreaterThan(Long value) {
            addCriterion("FInitFileID >", value, "finitfileid");
            return (Criteria) this;
        }

        public Criteria andFinitfileidGreaterThanOrEqualTo(Long value) {
            addCriterion("FInitFileID >=", value, "finitfileid");
            return (Criteria) this;
        }

        public Criteria andFinitfileidLessThan(Long value) {
            addCriterion("FInitFileID <", value, "finitfileid");
            return (Criteria) this;
        }

        public Criteria andFinitfileidLessThanOrEqualTo(Long value) {
            addCriterion("FInitFileID <=", value, "finitfileid");
            return (Criteria) this;
        }

        public Criteria andFinitfileidIn(List<Long> values) {
            addCriterion("FInitFileID in", values, "finitfileid");
            return (Criteria) this;
        }

        public Criteria andFinitfileidNotIn(List<Long> values) {
            addCriterion("FInitFileID not in", values, "finitfileid");
            return (Criteria) this;
        }

        public Criteria andFinitfileidBetween(Long value1, Long value2) {
            addCriterion("FInitFileID between", value1, value2, "finitfileid");
            return (Criteria) this;
        }

        public Criteria andFinitfileidNotBetween(Long value1, Long value2) {
            addCriterion("FInitFileID not between", value1, value2, "finitfileid");
            return (Criteria) this;
        }

        public Criteria andFeditionnoIsNull() {
            addCriterion("FEditionNo is null");
            return (Criteria) this;
        }

        public Criteria andFeditionnoIsNotNull() {
            addCriterion("FEditionNo is not null");
            return (Criteria) this;
        }

        public Criteria andFeditionnoEqualTo(Integer value) {
            addCriterion("FEditionNo =", value, "feditionno");
            return (Criteria) this;
        }

        public Criteria andFeditionnoNotEqualTo(Integer value) {
            addCriterion("FEditionNo <>", value, "feditionno");
            return (Criteria) this;
        }

        public Criteria andFeditionnoGreaterThan(Integer value) {
            addCriterion("FEditionNo >", value, "feditionno");
            return (Criteria) this;
        }

        public Criteria andFeditionnoGreaterThanOrEqualTo(Integer value) {
            addCriterion("FEditionNo >=", value, "feditionno");
            return (Criteria) this;
        }

        public Criteria andFeditionnoLessThan(Integer value) {
            addCriterion("FEditionNo <", value, "feditionno");
            return (Criteria) this;
        }

        public Criteria andFeditionnoLessThanOrEqualTo(Integer value) {
            addCriterion("FEditionNo <=", value, "feditionno");
            return (Criteria) this;
        }

        public Criteria andFeditionnoIn(List<Integer> values) {
            addCriterion("FEditionNo in", values, "feditionno");
            return (Criteria) this;
        }

        public Criteria andFeditionnoNotIn(List<Integer> values) {
            addCriterion("FEditionNo not in", values, "feditionno");
            return (Criteria) this;
        }

        public Criteria andFeditionnoBetween(Integer value1, Integer value2) {
            addCriterion("FEditionNo between", value1, value2, "feditionno");
            return (Criteria) this;
        }

        public Criteria andFeditionnoNotBetween(Integer value1, Integer value2) {
            addCriterion("FEditionNo not between", value1, value2, "feditionno");
            return (Criteria) this;
        }

        public Criteria andFeditionIsNull() {
            addCriterion("FEdition is null");
            return (Criteria) this;
        }

        public Criteria andFeditionIsNotNull() {
            addCriterion("FEdition is not null");
            return (Criteria) this;
        }

        public Criteria andFeditionEqualTo(String value) {
            addCriterion("FEdition =", value, "fedition");
            return (Criteria) this;
        }

        public Criteria andFeditionNotEqualTo(String value) {
            addCriterion("FEdition <>", value, "fedition");
            return (Criteria) this;
        }

        public Criteria andFeditionGreaterThan(String value) {
            addCriterion("FEdition >", value, "fedition");
            return (Criteria) this;
        }

        public Criteria andFeditionGreaterThanOrEqualTo(String value) {
            addCriterion("FEdition >=", value, "fedition");
            return (Criteria) this;
        }

        public Criteria andFeditionLessThan(String value) {
            addCriterion("FEdition <", value, "fedition");
            return (Criteria) this;
        }

        public Criteria andFeditionLessThanOrEqualTo(String value) {
            addCriterion("FEdition <=", value, "fedition");
            return (Criteria) this;
        }

        public Criteria andFeditionLike(String value) {
            addCriterion("FEdition like", value, "fedition");
            return (Criteria) this;
        }

        public Criteria andFeditionNotLike(String value) {
            addCriterion("FEdition not like", value, "fedition");
            return (Criteria) this;
        }

        public Criteria andFeditionIn(List<String> values) {
            addCriterion("FEdition in", values, "fedition");
            return (Criteria) this;
        }

        public Criteria andFeditionNotIn(List<String> values) {
            addCriterion("FEdition not in", values, "fedition");
            return (Criteria) this;
        }

        public Criteria andFeditionBetween(String value1, String value2) {
            addCriterion("FEdition between", value1, value2, "fedition");
            return (Criteria) this;
        }

        public Criteria andFeditionNotBetween(String value1, String value2) {
            addCriterion("FEdition not between", value1, value2, "fedition");
            return (Criteria) this;
        }

        public Criteria andFvalidIsNull() {
            addCriterion("FValid is null");
            return (Criteria) this;
        }

        public Criteria andFvalidIsNotNull() {
            addCriterion("FValid is not null");
            return (Criteria) this;
        }

        public Criteria andFvalidEqualTo(Integer value) {
            addCriterion("FValid =", value, "fvalid");
            return (Criteria) this;
        }

        public Criteria andFvalidNotEqualTo(Integer value) {
            addCriterion("FValid <>", value, "fvalid");
            return (Criteria) this;
        }

        public Criteria andFvalidGreaterThan(Integer value) {
            addCriterion("FValid >", value, "fvalid");
            return (Criteria) this;
        }

        public Criteria andFvalidGreaterThanOrEqualTo(Integer value) {
            addCriterion("FValid >=", value, "fvalid");
            return (Criteria) this;
        }

        public Criteria andFvalidLessThan(Integer value) {
            addCriterion("FValid <", value, "fvalid");
            return (Criteria) this;
        }

        public Criteria andFvalidLessThanOrEqualTo(Integer value) {
            addCriterion("FValid <=", value, "fvalid");
            return (Criteria) this;
        }

        public Criteria andFvalidIn(List<Integer> values) {
            addCriterion("FValid in", values, "fvalid");
            return (Criteria) this;
        }

        public Criteria andFvalidNotIn(List<Integer> values) {
            addCriterion("FValid not in", values, "fvalid");
            return (Criteria) this;
        }

        public Criteria andFvalidBetween(Integer value1, Integer value2) {
            addCriterion("FValid between", value1, value2, "fvalid");
            return (Criteria) this;
        }

        public Criteria andFvalidNotBetween(Integer value1, Integer value2) {
            addCriterion("FValid not between", value1, value2, "fvalid");
            return (Criteria) this;
        }

        public Criteria andFzsubdataidIsNull() {
            addCriterion("FZSubDataID is null");
            return (Criteria) this;
        }

        public Criteria andFzsubdataidIsNotNull() {
            addCriterion("FZSubDataID is not null");
            return (Criteria) this;
        }

        public Criteria andFzsubdataidEqualTo(Long value) {
            addCriterion("FZSubDataID =", value, "fzsubdataid");
            return (Criteria) this;
        }

        public Criteria andFzsubdataidNotEqualTo(Long value) {
            addCriterion("FZSubDataID <>", value, "fzsubdataid");
            return (Criteria) this;
        }

        public Criteria andFzsubdataidGreaterThan(Long value) {
            addCriterion("FZSubDataID >", value, "fzsubdataid");
            return (Criteria) this;
        }

        public Criteria andFzsubdataidGreaterThanOrEqualTo(Long value) {
            addCriterion("FZSubDataID >=", value, "fzsubdataid");
            return (Criteria) this;
        }

        public Criteria andFzsubdataidLessThan(Long value) {
            addCriterion("FZSubDataID <", value, "fzsubdataid");
            return (Criteria) this;
        }

        public Criteria andFzsubdataidLessThanOrEqualTo(Long value) {
            addCriterion("FZSubDataID <=", value, "fzsubdataid");
            return (Criteria) this;
        }

        public Criteria andFzsubdataidIn(List<Long> values) {
            addCriterion("FZSubDataID in", values, "fzsubdataid");
            return (Criteria) this;
        }

        public Criteria andFzsubdataidNotIn(List<Long> values) {
            addCriterion("FZSubDataID not in", values, "fzsubdataid");
            return (Criteria) this;
        }

        public Criteria andFzsubdataidBetween(Long value1, Long value2) {
            addCriterion("FZSubDataID between", value1, value2, "fzsubdataid");
            return (Criteria) this;
        }

        public Criteria andFzsubdataidNotBetween(Long value1, Long value2) {
            addCriterion("FZSubDataID not between", value1, value2, "fzsubdataid");
            return (Criteria) this;
        }
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：t_subdata
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * TSubdata
     * Criterion
     * 数据库表：t_subdata
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