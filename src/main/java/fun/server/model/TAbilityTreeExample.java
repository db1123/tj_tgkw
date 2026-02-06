package fun.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TAbilityTreeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_ability_tree
     * @mbg.generated
     */
    public TAbilityTreeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * 设置排序字段
     * student_capability_evaluation..t_ability_tree
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * student_capability_evaluation..t_ability_tree
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * student_capability_evaluation..t_ability_tree
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * student_capability_evaluation..t_ability_tree
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * student_capability_evaluation..t_ability_tree
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * student_capability_evaluation..t_ability_tree
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * student_capability_evaluation..t_ability_tree
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
     * student_capability_evaluation..t_ability_tree
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
     * student_capability_evaluation..t_ability_tree
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * student_capability_evaluation..t_ability_tree
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TAbilityTree
     * GeneratedCriteria
     * 数据库表：student_capability_evaluation..t_ability_tree
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

        public Criteria andFpidIsNull() {
            addCriterion("FPID is null");
            return (Criteria) this;
        }

        public Criteria andFpidIsNotNull() {
            addCriterion("FPID is not null");
            return (Criteria) this;
        }

        public Criteria andFpidEqualTo(Long value) {
            addCriterion("FPID =", value, "fpid");
            return (Criteria) this;
        }

        public Criteria andFpidNotEqualTo(Long value) {
            addCriterion("FPID <>", value, "fpid");
            return (Criteria) this;
        }

        public Criteria andFpidGreaterThan(Long value) {
            addCriterion("FPID >", value, "fpid");
            return (Criteria) this;
        }

        public Criteria andFpidGreaterThanOrEqualTo(Long value) {
            addCriterion("FPID >=", value, "fpid");
            return (Criteria) this;
        }

        public Criteria andFpidLessThan(Long value) {
            addCriterion("FPID <", value, "fpid");
            return (Criteria) this;
        }

        public Criteria andFpidLessThanOrEqualTo(Long value) {
            addCriterion("FPID <=", value, "fpid");
            return (Criteria) this;
        }

        public Criteria andFpidIn(List<Long> values) {
            addCriterion("FPID in", values, "fpid");
            return (Criteria) this;
        }

        public Criteria andFpidNotIn(List<Long> values) {
            addCriterion("FPID not in", values, "fpid");
            return (Criteria) this;
        }

        public Criteria andFpidBetween(Long value1, Long value2) {
            addCriterion("FPID between", value1, value2, "fpid");
            return (Criteria) this;
        }

        public Criteria andFpidNotBetween(Long value1, Long value2) {
            addCriterion("FPID not between", value1, value2, "fpid");
            return (Criteria) this;
        }

        public Criteria andFisleafIsNull() {
            addCriterion("FIsleaf is null");
            return (Criteria) this;
        }

        public Criteria andFisleafIsNotNull() {
            addCriterion("FIsleaf is not null");
            return (Criteria) this;
        }

        public Criteria andFisleafEqualTo(Integer value) {
            addCriterion("FIsleaf =", value, "fisleaf");
            return (Criteria) this;
        }

        public Criteria andFisleafNotEqualTo(Integer value) {
            addCriterion("FIsleaf <>", value, "fisleaf");
            return (Criteria) this;
        }

        public Criteria andFisleafGreaterThan(Integer value) {
            addCriterion("FIsleaf >", value, "fisleaf");
            return (Criteria) this;
        }

        public Criteria andFisleafGreaterThanOrEqualTo(Integer value) {
            addCriterion("FIsleaf >=", value, "fisleaf");
            return (Criteria) this;
        }

        public Criteria andFisleafLessThan(Integer value) {
            addCriterion("FIsleaf <", value, "fisleaf");
            return (Criteria) this;
        }

        public Criteria andFisleafLessThanOrEqualTo(Integer value) {
            addCriterion("FIsleaf <=", value, "fisleaf");
            return (Criteria) this;
        }

        public Criteria andFisleafIn(List<Integer> values) {
            addCriterion("FIsleaf in", values, "fisleaf");
            return (Criteria) this;
        }

        public Criteria andFisleafNotIn(List<Integer> values) {
            addCriterion("FIsleaf not in", values, "fisleaf");
            return (Criteria) this;
        }

        public Criteria andFisleafBetween(Integer value1, Integer value2) {
            addCriterion("FIsleaf between", value1, value2, "fisleaf");
            return (Criteria) this;
        }

        public Criteria andFisleafNotBetween(Integer value1, Integer value2) {
            addCriterion("FIsleaf not between", value1, value2, "fisleaf");
            return (Criteria) this;
        }

        public Criteria andFpathIsNull() {
            addCriterion("FPath is null");
            return (Criteria) this;
        }

        public Criteria andFpathIsNotNull() {
            addCriterion("FPath is not null");
            return (Criteria) this;
        }

        public Criteria andFpathEqualTo(String value) {
            addCriterion("FPath =", value, "fpath");
            return (Criteria) this;
        }

        public Criteria andFpathNotEqualTo(String value) {
            addCriterion("FPath <>", value, "fpath");
            return (Criteria) this;
        }

        public Criteria andFpathGreaterThan(String value) {
            addCriterion("FPath >", value, "fpath");
            return (Criteria) this;
        }

        public Criteria andFpathGreaterThanOrEqualTo(String value) {
            addCriterion("FPath >=", value, "fpath");
            return (Criteria) this;
        }

        public Criteria andFpathLessThan(String value) {
            addCriterion("FPath <", value, "fpath");
            return (Criteria) this;
        }

        public Criteria andFpathLessThanOrEqualTo(String value) {
            addCriterion("FPath <=", value, "fpath");
            return (Criteria) this;
        }

        public Criteria andFpathLike(String value) {
            addCriterion("FPath like", value, "fpath");
            return (Criteria) this;
        }

        public Criteria andFpathNotLike(String value) {
            addCriterion("FPath not like", value, "fpath");
            return (Criteria) this;
        }

        public Criteria andFpathIn(List<String> values) {
            addCriterion("FPath in", values, "fpath");
            return (Criteria) this;
        }

        public Criteria andFpathNotIn(List<String> values) {
            addCriterion("FPath not in", values, "fpath");
            return (Criteria) this;
        }

        public Criteria andFpathBetween(String value1, String value2) {
            addCriterion("FPath between", value1, value2, "fpath");
            return (Criteria) this;
        }

        public Criteria andFpathNotBetween(String value1, String value2) {
            addCriterion("FPath not between", value1, value2, "fpath");
            return (Criteria) this;
        }

        public Criteria andFmethodweightIsNull() {
            addCriterion("FMethodWeight is null");
            return (Criteria) this;
        }

        public Criteria andFmethodweightIsNotNull() {
            addCriterion("FMethodWeight is not null");
            return (Criteria) this;
        }

        public Criteria andFmethodweightEqualTo(Float value) {
            addCriterion("FMethodWeight =", value, "fmethodweight");
            return (Criteria) this;
        }

        public Criteria andFmethodweightNotEqualTo(Float value) {
            addCriterion("FMethodWeight <>", value, "fmethodweight");
            return (Criteria) this;
        }

        public Criteria andFmethodweightGreaterThan(Float value) {
            addCriterion("FMethodWeight >", value, "fmethodweight");
            return (Criteria) this;
        }

        public Criteria andFmethodweightGreaterThanOrEqualTo(Float value) {
            addCriterion("FMethodWeight >=", value, "fmethodweight");
            return (Criteria) this;
        }

        public Criteria andFmethodweightLessThan(Float value) {
            addCriterion("FMethodWeight <", value, "fmethodweight");
            return (Criteria) this;
        }

        public Criteria andFmethodweightLessThanOrEqualTo(Float value) {
            addCriterion("FMethodWeight <=", value, "fmethodweight");
            return (Criteria) this;
        }

        public Criteria andFmethodweightIn(List<Float> values) {
            addCriterion("FMethodWeight in", values, "fmethodweight");
            return (Criteria) this;
        }

        public Criteria andFmethodweightNotIn(List<Float> values) {
            addCriterion("FMethodWeight not in", values, "fmethodweight");
            return (Criteria) this;
        }

        public Criteria andFmethodweightBetween(Float value1, Float value2) {
            addCriterion("FMethodWeight between", value1, value2, "fmethodweight");
            return (Criteria) this;
        }

        public Criteria andFmethodweightNotBetween(Float value1, Float value2) {
            addCriterion("FMethodWeight not between", value1, value2, "fmethodweight");
            return (Criteria) this;
        }

        public Criteria andFdivIsNull() {
            addCriterion("FDiv is null");
            return (Criteria) this;
        }

        public Criteria andFdivIsNotNull() {
            addCriterion("FDiv is not null");
            return (Criteria) this;
        }

        public Criteria andFdivEqualTo(Integer value) {
            addCriterion("FDiv =", value, "fdiv");
            return (Criteria) this;
        }

        public Criteria andFdivNotEqualTo(Integer value) {
            addCriterion("FDiv <>", value, "fdiv");
            return (Criteria) this;
        }

        public Criteria andFdivGreaterThan(Integer value) {
            addCriterion("FDiv >", value, "fdiv");
            return (Criteria) this;
        }

        public Criteria andFdivGreaterThanOrEqualTo(Integer value) {
            addCriterion("FDiv >=", value, "fdiv");
            return (Criteria) this;
        }

        public Criteria andFdivLessThan(Integer value) {
            addCriterion("FDiv <", value, "fdiv");
            return (Criteria) this;
        }

        public Criteria andFdivLessThanOrEqualTo(Integer value) {
            addCriterion("FDiv <=", value, "fdiv");
            return (Criteria) this;
        }

        public Criteria andFdivIn(List<Integer> values) {
            addCriterion("FDiv in", values, "fdiv");
            return (Criteria) this;
        }

        public Criteria andFdivNotIn(List<Integer> values) {
            addCriterion("FDiv not in", values, "fdiv");
            return (Criteria) this;
        }

        public Criteria andFdivBetween(Integer value1, Integer value2) {
            addCriterion("FDiv between", value1, value2, "fdiv");
            return (Criteria) this;
        }

        public Criteria andFdivNotBetween(Integer value1, Integer value2) {
            addCriterion("FDiv not between", value1, value2, "fdiv");
            return (Criteria) this;
        }

        public Criteria andFnodetypeIsNull() {
            addCriterion("FNodeType is null");
            return (Criteria) this;
        }

        public Criteria andFnodetypeIsNotNull() {
            addCriterion("FNodeType is not null");
            return (Criteria) this;
        }

        public Criteria andFnodetypeEqualTo(Integer value) {
            addCriterion("FNodeType =", value, "fnodetype");
            return (Criteria) this;
        }

        public Criteria andFnodetypeNotEqualTo(Integer value) {
            addCriterion("FNodeType <>", value, "fnodetype");
            return (Criteria) this;
        }

        public Criteria andFnodetypeGreaterThan(Integer value) {
            addCriterion("FNodeType >", value, "fnodetype");
            return (Criteria) this;
        }

        public Criteria andFnodetypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("FNodeType >=", value, "fnodetype");
            return (Criteria) this;
        }

        public Criteria andFnodetypeLessThan(Integer value) {
            addCriterion("FNodeType <", value, "fnodetype");
            return (Criteria) this;
        }

        public Criteria andFnodetypeLessThanOrEqualTo(Integer value) {
            addCriterion("FNodeType <=", value, "fnodetype");
            return (Criteria) this;
        }

        public Criteria andFnodetypeIn(List<Integer> values) {
            addCriterion("FNodeType in", values, "fnodetype");
            return (Criteria) this;
        }

        public Criteria andFnodetypeNotIn(List<Integer> values) {
            addCriterion("FNodeType not in", values, "fnodetype");
            return (Criteria) this;
        }

        public Criteria andFnodetypeBetween(Integer value1, Integer value2) {
            addCriterion("FNodeType between", value1, value2, "fnodetype");
            return (Criteria) this;
        }

        public Criteria andFnodetypeNotBetween(Integer value1, Integer value2) {
            addCriterion("FNodeType not between", value1, value2, "fnodetype");
            return (Criteria) this;
        }

        public Criteria andFpointsIsNull() {
            addCriterion("FPoints is null");
            return (Criteria) this;
        }

        public Criteria andFpointsIsNotNull() {
            addCriterion("FPoints is not null");
            return (Criteria) this;
        }

        public Criteria andFpointsEqualTo(Float value) {
            addCriterion("FPoints =", value, "fpoints");
            return (Criteria) this;
        }

        public Criteria andFpointsNotEqualTo(Float value) {
            addCriterion("FPoints <>", value, "fpoints");
            return (Criteria) this;
        }

        public Criteria andFpointsGreaterThan(Float value) {
            addCriterion("FPoints >", value, "fpoints");
            return (Criteria) this;
        }

        public Criteria andFpointsGreaterThanOrEqualTo(Float value) {
            addCriterion("FPoints >=", value, "fpoints");
            return (Criteria) this;
        }

        public Criteria andFpointsLessThan(Float value) {
            addCriterion("FPoints <", value, "fpoints");
            return (Criteria) this;
        }

        public Criteria andFpointsLessThanOrEqualTo(Float value) {
            addCriterion("FPoints <=", value, "fpoints");
            return (Criteria) this;
        }

        public Criteria andFpointsIn(List<Float> values) {
            addCriterion("FPoints in", values, "fpoints");
            return (Criteria) this;
        }

        public Criteria andFpointsNotIn(List<Float> values) {
            addCriterion("FPoints not in", values, "fpoints");
            return (Criteria) this;
        }

        public Criteria andFpointsBetween(Float value1, Float value2) {
            addCriterion("FPoints between", value1, value2, "fpoints");
            return (Criteria) this;
        }

        public Criteria andFpointsNotBetween(Float value1, Float value2) {
            addCriterion("FPoints not between", value1, value2, "fpoints");
            return (Criteria) this;
        }

        public Criteria andFconditionscoreIsNull() {
            addCriterion("FConditionScore is null");
            return (Criteria) this;
        }

        public Criteria andFconditionscoreIsNotNull() {
            addCriterion("FConditionScore is not null");
            return (Criteria) this;
        }

        public Criteria andFconditionscoreEqualTo(Integer value) {
            addCriterion("FConditionScore =", value, "fconditionscore");
            return (Criteria) this;
        }

        public Criteria andFconditionscoreNotEqualTo(Integer value) {
            addCriterion("FConditionScore <>", value, "fconditionscore");
            return (Criteria) this;
        }

        public Criteria andFconditionscoreGreaterThan(Integer value) {
            addCriterion("FConditionScore >", value, "fconditionscore");
            return (Criteria) this;
        }

        public Criteria andFconditionscoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("FConditionScore >=", value, "fconditionscore");
            return (Criteria) this;
        }

        public Criteria andFconditionscoreLessThan(Integer value) {
            addCriterion("FConditionScore <", value, "fconditionscore");
            return (Criteria) this;
        }

        public Criteria andFconditionscoreLessThanOrEqualTo(Integer value) {
            addCriterion("FConditionScore <=", value, "fconditionscore");
            return (Criteria) this;
        }

        public Criteria andFconditionscoreIn(List<Integer> values) {
            addCriterion("FConditionScore in", values, "fconditionscore");
            return (Criteria) this;
        }

        public Criteria andFconditionscoreNotIn(List<Integer> values) {
            addCriterion("FConditionScore not in", values, "fconditionscore");
            return (Criteria) this;
        }

        public Criteria andFconditionscoreBetween(Integer value1, Integer value2) {
            addCriterion("FConditionScore between", value1, value2, "fconditionscore");
            return (Criteria) this;
        }

        public Criteria andFconditionscoreNotBetween(Integer value1, Integer value2) {
            addCriterion("FConditionScore not between", value1, value2, "fconditionscore");
            return (Criteria) this;
        }

        public Criteria andFgpidIsNull() {
            addCriterion("FGPID is null");
            return (Criteria) this;
        }

        public Criteria andFgpidIsNotNull() {
            addCriterion("FGPID is not null");
            return (Criteria) this;
        }

        public Criteria andFgpidEqualTo(Long value) {
            addCriterion("FGPID =", value, "fgpid");
            return (Criteria) this;
        }

        public Criteria andFgpidNotEqualTo(Long value) {
            addCriterion("FGPID <>", value, "fgpid");
            return (Criteria) this;
        }

        public Criteria andFgpidGreaterThan(Long value) {
            addCriterion("FGPID >", value, "fgpid");
            return (Criteria) this;
        }

        public Criteria andFgpidGreaterThanOrEqualTo(Long value) {
            addCriterion("FGPID >=", value, "fgpid");
            return (Criteria) this;
        }

        public Criteria andFgpidLessThan(Long value) {
            addCriterion("FGPID <", value, "fgpid");
            return (Criteria) this;
        }

        public Criteria andFgpidLessThanOrEqualTo(Long value) {
            addCriterion("FGPID <=", value, "fgpid");
            return (Criteria) this;
        }

        public Criteria andFgpidIn(List<Long> values) {
            addCriterion("FGPID in", values, "fgpid");
            return (Criteria) this;
        }

        public Criteria andFgpidNotIn(List<Long> values) {
            addCriterion("FGPID not in", values, "fgpid");
            return (Criteria) this;
        }

        public Criteria andFgpidBetween(Long value1, Long value2) {
            addCriterion("FGPID between", value1, value2, "fgpid");
            return (Criteria) this;
        }

        public Criteria andFgpidNotBetween(Long value1, Long value2) {
            addCriterion("FGPID not between", value1, value2, "fgpid");
            return (Criteria) this;
        }

        public Criteria andFscoreminIsNull() {
            addCriterion("FScoreMin is null");
            return (Criteria) this;
        }

        public Criteria andFscoreminIsNotNull() {
            addCriterion("FScoreMin is not null");
            return (Criteria) this;
        }

        public Criteria andFscoreminEqualTo(Integer value) {
            addCriterion("FScoreMin =", value, "fscoremin");
            return (Criteria) this;
        }

        public Criteria andFscoreminNotEqualTo(Integer value) {
            addCriterion("FScoreMin <>", value, "fscoremin");
            return (Criteria) this;
        }

        public Criteria andFscoreminGreaterThan(Integer value) {
            addCriterion("FScoreMin >", value, "fscoremin");
            return (Criteria) this;
        }

        public Criteria andFscoreminGreaterThanOrEqualTo(Integer value) {
            addCriterion("FScoreMin >=", value, "fscoremin");
            return (Criteria) this;
        }

        public Criteria andFscoreminLessThan(Integer value) {
            addCriterion("FScoreMin <", value, "fscoremin");
            return (Criteria) this;
        }

        public Criteria andFscoreminLessThanOrEqualTo(Integer value) {
            addCriterion("FScoreMin <=", value, "fscoremin");
            return (Criteria) this;
        }

        public Criteria andFscoreminIn(List<Integer> values) {
            addCriterion("FScoreMin in", values, "fscoremin");
            return (Criteria) this;
        }

        public Criteria andFscoreminNotIn(List<Integer> values) {
            addCriterion("FScoreMin not in", values, "fscoremin");
            return (Criteria) this;
        }

        public Criteria andFscoreminBetween(Integer value1, Integer value2) {
            addCriterion("FScoreMin between", value1, value2, "fscoremin");
            return (Criteria) this;
        }

        public Criteria andFscoreminNotBetween(Integer value1, Integer value2) {
            addCriterion("FScoreMin not between", value1, value2, "fscoremin");
            return (Criteria) this;
        }

        public Criteria andFscoremaxIsNull() {
            addCriterion("FScoreMax is null");
            return (Criteria) this;
        }

        public Criteria andFscoremaxIsNotNull() {
            addCriterion("FScoreMax is not null");
            return (Criteria) this;
        }

        public Criteria andFscoremaxEqualTo(Integer value) {
            addCriterion("FScoreMax =", value, "fscoremax");
            return (Criteria) this;
        }

        public Criteria andFscoremaxNotEqualTo(Integer value) {
            addCriterion("FScoreMax <>", value, "fscoremax");
            return (Criteria) this;
        }

        public Criteria andFscoremaxGreaterThan(Integer value) {
            addCriterion("FScoreMax >", value, "fscoremax");
            return (Criteria) this;
        }

        public Criteria andFscoremaxGreaterThanOrEqualTo(Integer value) {
            addCriterion("FScoreMax >=", value, "fscoremax");
            return (Criteria) this;
        }

        public Criteria andFscoremaxLessThan(Integer value) {
            addCriterion("FScoreMax <", value, "fscoremax");
            return (Criteria) this;
        }

        public Criteria andFscoremaxLessThanOrEqualTo(Integer value) {
            addCriterion("FScoreMax <=", value, "fscoremax");
            return (Criteria) this;
        }

        public Criteria andFscoremaxIn(List<Integer> values) {
            addCriterion("FScoreMax in", values, "fscoremax");
            return (Criteria) this;
        }

        public Criteria andFscoremaxNotIn(List<Integer> values) {
            addCriterion("FScoreMax not in", values, "fscoremax");
            return (Criteria) this;
        }

        public Criteria andFscoremaxBetween(Integer value1, Integer value2) {
            addCriterion("FScoreMax between", value1, value2, "fscoremax");
            return (Criteria) this;
        }

        public Criteria andFscoremaxNotBetween(Integer value1, Integer value2) {
            addCriterion("FScoreMax not between", value1, value2, "fscoremax");
            return (Criteria) this;
        }

        public Criteria andFdelIsNull() {
            addCriterion("FDel is null");
            return (Criteria) this;
        }

        public Criteria andFdelIsNotNull() {
            addCriterion("FDel is not null");
            return (Criteria) this;
        }

        public Criteria andFdelEqualTo(Integer value) {
            addCriterion("FDel =", value, "fdel");
            return (Criteria) this;
        }

        public Criteria andFdelNotEqualTo(Integer value) {
            addCriterion("FDel <>", value, "fdel");
            return (Criteria) this;
        }

        public Criteria andFdelGreaterThan(Integer value) {
            addCriterion("FDel >", value, "fdel");
            return (Criteria) this;
        }

        public Criteria andFdelGreaterThanOrEqualTo(Integer value) {
            addCriterion("FDel >=", value, "fdel");
            return (Criteria) this;
        }

        public Criteria andFdelLessThan(Integer value) {
            addCriterion("FDel <", value, "fdel");
            return (Criteria) this;
        }

        public Criteria andFdelLessThanOrEqualTo(Integer value) {
            addCriterion("FDel <=", value, "fdel");
            return (Criteria) this;
        }

        public Criteria andFdelIn(List<Integer> values) {
            addCriterion("FDel in", values, "fdel");
            return (Criteria) this;
        }

        public Criteria andFdelNotIn(List<Integer> values) {
            addCriterion("FDel not in", values, "fdel");
            return (Criteria) this;
        }

        public Criteria andFdelBetween(Integer value1, Integer value2) {
            addCriterion("FDel between", value1, value2, "fdel");
            return (Criteria) this;
        }

        public Criteria andFdelNotBetween(Integer value1, Integer value2) {
            addCriterion("FDel not between", value1, value2, "fdel");
            return (Criteria) this;
        }

        public Criteria andFmethodidIsNull() {
            addCriterion("FMethodID is null");
            return (Criteria) this;
        }

        public Criteria andFmethodidIsNotNull() {
            addCriterion("FMethodID is not null");
            return (Criteria) this;
        }

        public Criteria andFmethodidEqualTo(Long value) {
            addCriterion("FMethodID =", value, "fmethodid");
            return (Criteria) this;
        }

        public Criteria andFmethodidNotEqualTo(Long value) {
            addCriterion("FMethodID <>", value, "fmethodid");
            return (Criteria) this;
        }

        public Criteria andFmethodidGreaterThan(Long value) {
            addCriterion("FMethodID >", value, "fmethodid");
            return (Criteria) this;
        }

        public Criteria andFmethodidGreaterThanOrEqualTo(Long value) {
            addCriterion("FMethodID >=", value, "fmethodid");
            return (Criteria) this;
        }

        public Criteria andFmethodidLessThan(Long value) {
            addCriterion("FMethodID <", value, "fmethodid");
            return (Criteria) this;
        }

        public Criteria andFmethodidLessThanOrEqualTo(Long value) {
            addCriterion("FMethodID <=", value, "fmethodid");
            return (Criteria) this;
        }

        public Criteria andFmethodidIn(List<Long> values) {
            addCriterion("FMethodID in", values, "fmethodid");
            return (Criteria) this;
        }

        public Criteria andFmethodidNotIn(List<Long> values) {
            addCriterion("FMethodID not in", values, "fmethodid");
            return (Criteria) this;
        }

        public Criteria andFmethodidBetween(Long value1, Long value2) {
            addCriterion("FMethodID between", value1, value2, "fmethodid");
            return (Criteria) this;
        }

        public Criteria andFmethodidNotBetween(Long value1, Long value2) {
            addCriterion("FMethodID not between", value1, value2, "fmethodid");
            return (Criteria) this;
        }

        public Criteria andFalcidIsNull() {
            addCriterion("FALCID is null");
            return (Criteria) this;
        }

        public Criteria andFalcidIsNotNull() {
            addCriterion("FALCID is not null");
            return (Criteria) this;
        }

        public Criteria andFalcidEqualTo(Long value) {
            addCriterion("FALCID =", value, "falcid");
            return (Criteria) this;
        }

        public Criteria andFalcidNotEqualTo(Long value) {
            addCriterion("FALCID <>", value, "falcid");
            return (Criteria) this;
        }

        public Criteria andFalcidGreaterThan(Long value) {
            addCriterion("FALCID >", value, "falcid");
            return (Criteria) this;
        }

        public Criteria andFalcidGreaterThanOrEqualTo(Long value) {
            addCriterion("FALCID >=", value, "falcid");
            return (Criteria) this;
        }

        public Criteria andFalcidLessThan(Long value) {
            addCriterion("FALCID <", value, "falcid");
            return (Criteria) this;
        }

        public Criteria andFalcidLessThanOrEqualTo(Long value) {
            addCriterion("FALCID <=", value, "falcid");
            return (Criteria) this;
        }

        public Criteria andFalcidIn(List<Long> values) {
            addCriterion("FALCID in", values, "falcid");
            return (Criteria) this;
        }

        public Criteria andFalcidNotIn(List<Long> values) {
            addCriterion("FALCID not in", values, "falcid");
            return (Criteria) this;
        }

        public Criteria andFalcidBetween(Long value1, Long value2) {
            addCriterion("FALCID between", value1, value2, "falcid");
            return (Criteria) this;
        }

        public Criteria andFalcidNotBetween(Long value1, Long value2) {
            addCriterion("FALCID not between", value1, value2, "falcid");
            return (Criteria) this;
        }
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：student_capability_evaluation..t_ability_tree
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * TAbilityTree
     * Criterion
     * 数据库表：student_capability_evaluation..t_ability_tree
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