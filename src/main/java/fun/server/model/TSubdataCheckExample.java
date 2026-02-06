package fun.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TSubdataCheckExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * t_subdata_check
     * @mbg.generated
     */
    public TSubdataCheckExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * 设置排序字段
     * t_subdata_check
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * t_subdata_check
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * t_subdata_check
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * t_subdata_check
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * t_subdata_check
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * t_subdata_check
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * t_subdata_check
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
     * t_subdata_check
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
     * t_subdata_check
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * t_subdata_check
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TSubdataCheck
     * GeneratedCriteria
     * 数据库表：t_subdata_check
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

        public Criteria andFcheckresultIsNull() {
            addCriterion("FCheckResult is null");
            return (Criteria) this;
        }

        public Criteria andFcheckresultIsNotNull() {
            addCriterion("FCheckResult is not null");
            return (Criteria) this;
        }

        public Criteria andFcheckresultEqualTo(Integer value) {
            addCriterion("FCheckResult =", value, "fcheckresult");
            return (Criteria) this;
        }

        public Criteria andFcheckresultNotEqualTo(Integer value) {
            addCriterion("FCheckResult <>", value, "fcheckresult");
            return (Criteria) this;
        }

        public Criteria andFcheckresultGreaterThan(Integer value) {
            addCriterion("FCheckResult >", value, "fcheckresult");
            return (Criteria) this;
        }

        public Criteria andFcheckresultGreaterThanOrEqualTo(Integer value) {
            addCriterion("FCheckResult >=", value, "fcheckresult");
            return (Criteria) this;
        }

        public Criteria andFcheckresultLessThan(Integer value) {
            addCriterion("FCheckResult <", value, "fcheckresult");
            return (Criteria) this;
        }

        public Criteria andFcheckresultLessThanOrEqualTo(Integer value) {
            addCriterion("FCheckResult <=", value, "fcheckresult");
            return (Criteria) this;
        }

        public Criteria andFcheckresultIn(List<Integer> values) {
            addCriterion("FCheckResult in", values, "fcheckresult");
            return (Criteria) this;
        }

        public Criteria andFcheckresultNotIn(List<Integer> values) {
            addCriterion("FCheckResult not in", values, "fcheckresult");
            return (Criteria) this;
        }

        public Criteria andFcheckresultBetween(Integer value1, Integer value2) {
            addCriterion("FCheckResult between", value1, value2, "fcheckresult");
            return (Criteria) this;
        }

        public Criteria andFcheckresultNotBetween(Integer value1, Integer value2) {
            addCriterion("FCheckResult not between", value1, value2, "fcheckresult");
            return (Criteria) this;
        }

        public Criteria andFcheckopinionIsNull() {
            addCriterion("FCheckOpinion is null");
            return (Criteria) this;
        }

        public Criteria andFcheckopinionIsNotNull() {
            addCriterion("FCheckOpinion is not null");
            return (Criteria) this;
        }

        public Criteria andFcheckopinionEqualTo(String value) {
            addCriterion("FCheckOpinion =", value, "fcheckopinion");
            return (Criteria) this;
        }

        public Criteria andFcheckopinionNotEqualTo(String value) {
            addCriterion("FCheckOpinion <>", value, "fcheckopinion");
            return (Criteria) this;
        }

        public Criteria andFcheckopinionGreaterThan(String value) {
            addCriterion("FCheckOpinion >", value, "fcheckopinion");
            return (Criteria) this;
        }

        public Criteria andFcheckopinionGreaterThanOrEqualTo(String value) {
            addCriterion("FCheckOpinion >=", value, "fcheckopinion");
            return (Criteria) this;
        }

        public Criteria andFcheckopinionLessThan(String value) {
            addCriterion("FCheckOpinion <", value, "fcheckopinion");
            return (Criteria) this;
        }

        public Criteria andFcheckopinionLessThanOrEqualTo(String value) {
            addCriterion("FCheckOpinion <=", value, "fcheckopinion");
            return (Criteria) this;
        }

        public Criteria andFcheckopinionLike(String value) {
            addCriterion("FCheckOpinion like", value, "fcheckopinion");
            return (Criteria) this;
        }

        public Criteria andFcheckopinionNotLike(String value) {
            addCriterion("FCheckOpinion not like", value, "fcheckopinion");
            return (Criteria) this;
        }

        public Criteria andFcheckopinionIn(List<String> values) {
            addCriterion("FCheckOpinion in", values, "fcheckopinion");
            return (Criteria) this;
        }

        public Criteria andFcheckopinionNotIn(List<String> values) {
            addCriterion("FCheckOpinion not in", values, "fcheckopinion");
            return (Criteria) this;
        }

        public Criteria andFcheckopinionBetween(String value1, String value2) {
            addCriterion("FCheckOpinion between", value1, value2, "fcheckopinion");
            return (Criteria) this;
        }

        public Criteria andFcheckopinionNotBetween(String value1, String value2) {
            addCriterion("FCheckOpinion not between", value1, value2, "fcheckopinion");
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
     * 数据库表：t_subdata_check
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * TSubdataCheck
     * Criterion
     * 数据库表：t_subdata_check
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