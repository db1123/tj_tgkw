package fun.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TEnterpriseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_enterprise
     * @mbg.generated
     */
    public TEnterpriseExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * 设置排序字段
     * student_capability_evaluation..t_enterprise
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * student_capability_evaluation..t_enterprise
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * student_capability_evaluation..t_enterprise
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * student_capability_evaluation..t_enterprise
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * student_capability_evaluation..t_enterprise
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * student_capability_evaluation..t_enterprise
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * student_capability_evaluation..t_enterprise
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
     * student_capability_evaluation..t_enterprise
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
     * student_capability_evaluation..t_enterprise
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * student_capability_evaluation..t_enterprise
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TEnterprise
     * GeneratedCriteria
     * 数据库表：student_capability_evaluation..t_enterprise
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

        public Criteria andFmodeIsNull() {
            addCriterion("FMode is null");
            return (Criteria) this;
        }

        public Criteria andFmodeIsNotNull() {
            addCriterion("FMode is not null");
            return (Criteria) this;
        }

        public Criteria andFmodeEqualTo(Integer value) {
            addCriterion("FMode =", value, "fmode");
            return (Criteria) this;
        }

        public Criteria andFmodeNotEqualTo(Integer value) {
            addCriterion("FMode <>", value, "fmode");
            return (Criteria) this;
        }

        public Criteria andFmodeGreaterThan(Integer value) {
            addCriterion("FMode >", value, "fmode");
            return (Criteria) this;
        }

        public Criteria andFmodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("FMode >=", value, "fmode");
            return (Criteria) this;
        }

        public Criteria andFmodeLessThan(Integer value) {
            addCriterion("FMode <", value, "fmode");
            return (Criteria) this;
        }

        public Criteria andFmodeLessThanOrEqualTo(Integer value) {
            addCriterion("FMode <=", value, "fmode");
            return (Criteria) this;
        }

        public Criteria andFmodeIn(List<Integer> values) {
            addCriterion("FMode in", values, "fmode");
            return (Criteria) this;
        }

        public Criteria andFmodeNotIn(List<Integer> values) {
            addCriterion("FMode not in", values, "fmode");
            return (Criteria) this;
        }

        public Criteria andFmodeBetween(Integer value1, Integer value2) {
            addCriterion("FMode between", value1, value2, "fmode");
            return (Criteria) this;
        }

        public Criteria andFmodeNotBetween(Integer value1, Integer value2) {
            addCriterion("FMode not between", value1, value2, "fmode");
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

        public Criteria andFtypeIsNull() {
            addCriterion("FType is null");
            return (Criteria) this;
        }

        public Criteria andFtypeIsNotNull() {
            addCriterion("FType is not null");
            return (Criteria) this;
        }

        public Criteria andFtypeEqualTo(String value) {
            addCriterion("FType =", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeNotEqualTo(String value) {
            addCriterion("FType <>", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeGreaterThan(String value) {
            addCriterion("FType >", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeGreaterThanOrEqualTo(String value) {
            addCriterion("FType >=", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeLessThan(String value) {
            addCriterion("FType <", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeLessThanOrEqualTo(String value) {
            addCriterion("FType <=", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeLike(String value) {
            addCriterion("FType like", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeNotLike(String value) {
            addCriterion("FType not like", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeIn(List<String> values) {
            addCriterion("FType in", values, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeNotIn(List<String> values) {
            addCriterion("FType not in", values, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeBetween(String value1, String value2) {
            addCriterion("FType between", value1, value2, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeNotBetween(String value1, String value2) {
            addCriterion("FType not between", value1, value2, "ftype");
            return (Criteria) this;
        }

        public Criteria andFblnoIsNull() {
            addCriterion("FBLNo is null");
            return (Criteria) this;
        }

        public Criteria andFblnoIsNotNull() {
            addCriterion("FBLNo is not null");
            return (Criteria) this;
        }

        public Criteria andFblnoEqualTo(String value) {
            addCriterion("FBLNo =", value, "fblno");
            return (Criteria) this;
        }

        public Criteria andFblnoNotEqualTo(String value) {
            addCriterion("FBLNo <>", value, "fblno");
            return (Criteria) this;
        }

        public Criteria andFblnoGreaterThan(String value) {
            addCriterion("FBLNo >", value, "fblno");
            return (Criteria) this;
        }

        public Criteria andFblnoGreaterThanOrEqualTo(String value) {
            addCriterion("FBLNo >=", value, "fblno");
            return (Criteria) this;
        }

        public Criteria andFblnoLessThan(String value) {
            addCriterion("FBLNo <", value, "fblno");
            return (Criteria) this;
        }

        public Criteria andFblnoLessThanOrEqualTo(String value) {
            addCriterion("FBLNo <=", value, "fblno");
            return (Criteria) this;
        }

        public Criteria andFblnoLike(String value) {
            addCriterion("FBLNo like", value, "fblno");
            return (Criteria) this;
        }

        public Criteria andFblnoNotLike(String value) {
            addCriterion("FBLNo not like", value, "fblno");
            return (Criteria) this;
        }

        public Criteria andFblnoIn(List<String> values) {
            addCriterion("FBLNo in", values, "fblno");
            return (Criteria) this;
        }

        public Criteria andFblnoNotIn(List<String> values) {
            addCriterion("FBLNo not in", values, "fblno");
            return (Criteria) this;
        }

        public Criteria andFblnoBetween(String value1, String value2) {
            addCriterion("FBLNo between", value1, value2, "fblno");
            return (Criteria) this;
        }

        public Criteria andFblnoNotBetween(String value1, String value2) {
            addCriterion("FBLNo not between", value1, value2, "fblno");
            return (Criteria) this;
        }

        public Criteria andFlegalpersonIsNull() {
            addCriterion("FLegalPerson is null");
            return (Criteria) this;
        }

        public Criteria andFlegalpersonIsNotNull() {
            addCriterion("FLegalPerson is not null");
            return (Criteria) this;
        }

        public Criteria andFlegalpersonEqualTo(String value) {
            addCriterion("FLegalPerson =", value, "flegalperson");
            return (Criteria) this;
        }

        public Criteria andFlegalpersonNotEqualTo(String value) {
            addCriterion("FLegalPerson <>", value, "flegalperson");
            return (Criteria) this;
        }

        public Criteria andFlegalpersonGreaterThan(String value) {
            addCriterion("FLegalPerson >", value, "flegalperson");
            return (Criteria) this;
        }

        public Criteria andFlegalpersonGreaterThanOrEqualTo(String value) {
            addCriterion("FLegalPerson >=", value, "flegalperson");
            return (Criteria) this;
        }

        public Criteria andFlegalpersonLessThan(String value) {
            addCriterion("FLegalPerson <", value, "flegalperson");
            return (Criteria) this;
        }

        public Criteria andFlegalpersonLessThanOrEqualTo(String value) {
            addCriterion("FLegalPerson <=", value, "flegalperson");
            return (Criteria) this;
        }

        public Criteria andFlegalpersonLike(String value) {
            addCriterion("FLegalPerson like", value, "flegalperson");
            return (Criteria) this;
        }

        public Criteria andFlegalpersonNotLike(String value) {
            addCriterion("FLegalPerson not like", value, "flegalperson");
            return (Criteria) this;
        }

        public Criteria andFlegalpersonIn(List<String> values) {
            addCriterion("FLegalPerson in", values, "flegalperson");
            return (Criteria) this;
        }

        public Criteria andFlegalpersonNotIn(List<String> values) {
            addCriterion("FLegalPerson not in", values, "flegalperson");
            return (Criteria) this;
        }

        public Criteria andFlegalpersonBetween(String value1, String value2) {
            addCriterion("FLegalPerson between", value1, value2, "flegalperson");
            return (Criteria) this;
        }

        public Criteria andFlegalpersonNotBetween(String value1, String value2) {
            addCriterion("FLegalPerson not between", value1, value2, "flegalperson");
            return (Criteria) this;
        }

        public Criteria andFcreatedateIsNull() {
            addCriterion("FCreateDate is null");
            return (Criteria) this;
        }

        public Criteria andFcreatedateIsNotNull() {
            addCriterion("FCreateDate is not null");
            return (Criteria) this;
        }

        public Criteria andFcreatedateEqualTo(String value) {
            addCriterion("FCreateDate =", value, "fcreatedate");
            return (Criteria) this;
        }

        public Criteria andFcreatedateNotEqualTo(String value) {
            addCriterion("FCreateDate <>", value, "fcreatedate");
            return (Criteria) this;
        }

        public Criteria andFcreatedateGreaterThan(String value) {
            addCriterion("FCreateDate >", value, "fcreatedate");
            return (Criteria) this;
        }

        public Criteria andFcreatedateGreaterThanOrEqualTo(String value) {
            addCriterion("FCreateDate >=", value, "fcreatedate");
            return (Criteria) this;
        }

        public Criteria andFcreatedateLessThan(String value) {
            addCriterion("FCreateDate <", value, "fcreatedate");
            return (Criteria) this;
        }

        public Criteria andFcreatedateLessThanOrEqualTo(String value) {
            addCriterion("FCreateDate <=", value, "fcreatedate");
            return (Criteria) this;
        }

        public Criteria andFcreatedateLike(String value) {
            addCriterion("FCreateDate like", value, "fcreatedate");
            return (Criteria) this;
        }

        public Criteria andFcreatedateNotLike(String value) {
            addCriterion("FCreateDate not like", value, "fcreatedate");
            return (Criteria) this;
        }

        public Criteria andFcreatedateIn(List<String> values) {
            addCriterion("FCreateDate in", values, "fcreatedate");
            return (Criteria) this;
        }

        public Criteria andFcreatedateNotIn(List<String> values) {
            addCriterion("FCreateDate not in", values, "fcreatedate");
            return (Criteria) this;
        }

        public Criteria andFcreatedateBetween(String value1, String value2) {
            addCriterion("FCreateDate between", value1, value2, "fcreatedate");
            return (Criteria) this;
        }

        public Criteria andFcreatedateNotBetween(String value1, String value2) {
            addCriterion("FCreateDate not between", value1, value2, "fcreatedate");
            return (Criteria) this;
        }

        public Criteria andFindustryIsNull() {
            addCriterion("FIndustry is null");
            return (Criteria) this;
        }

        public Criteria andFindustryIsNotNull() {
            addCriterion("FIndustry is not null");
            return (Criteria) this;
        }

        public Criteria andFindustryEqualTo(String value) {
            addCriterion("FIndustry =", value, "findustry");
            return (Criteria) this;
        }

        public Criteria andFindustryNotEqualTo(String value) {
            addCriterion("FIndustry <>", value, "findustry");
            return (Criteria) this;
        }

        public Criteria andFindustryGreaterThan(String value) {
            addCriterion("FIndustry >", value, "findustry");
            return (Criteria) this;
        }

        public Criteria andFindustryGreaterThanOrEqualTo(String value) {
            addCriterion("FIndustry >=", value, "findustry");
            return (Criteria) this;
        }

        public Criteria andFindustryLessThan(String value) {
            addCriterion("FIndustry <", value, "findustry");
            return (Criteria) this;
        }

        public Criteria andFindustryLessThanOrEqualTo(String value) {
            addCriterion("FIndustry <=", value, "findustry");
            return (Criteria) this;
        }

        public Criteria andFindustryLike(String value) {
            addCriterion("FIndustry like", value, "findustry");
            return (Criteria) this;
        }

        public Criteria andFindustryNotLike(String value) {
            addCriterion("FIndustry not like", value, "findustry");
            return (Criteria) this;
        }

        public Criteria andFindustryIn(List<String> values) {
            addCriterion("FIndustry in", values, "findustry");
            return (Criteria) this;
        }

        public Criteria andFindustryNotIn(List<String> values) {
            addCriterion("FIndustry not in", values, "findustry");
            return (Criteria) this;
        }

        public Criteria andFindustryBetween(String value1, String value2) {
            addCriterion("FIndustry between", value1, value2, "findustry");
            return (Criteria) this;
        }

        public Criteria andFindustryNotBetween(String value1, String value2) {
            addCriterion("FIndustry not between", value1, value2, "findustry");
            return (Criteria) this;
        }

        public Criteria andFscopeIsNull() {
            addCriterion("FScope is null");
            return (Criteria) this;
        }

        public Criteria andFscopeIsNotNull() {
            addCriterion("FScope is not null");
            return (Criteria) this;
        }

        public Criteria andFscopeEqualTo(String value) {
            addCriterion("FScope =", value, "fscope");
            return (Criteria) this;
        }

        public Criteria andFscopeNotEqualTo(String value) {
            addCriterion("FScope <>", value, "fscope");
            return (Criteria) this;
        }

        public Criteria andFscopeGreaterThan(String value) {
            addCriterion("FScope >", value, "fscope");
            return (Criteria) this;
        }

        public Criteria andFscopeGreaterThanOrEqualTo(String value) {
            addCriterion("FScope >=", value, "fscope");
            return (Criteria) this;
        }

        public Criteria andFscopeLessThan(String value) {
            addCriterion("FScope <", value, "fscope");
            return (Criteria) this;
        }

        public Criteria andFscopeLessThanOrEqualTo(String value) {
            addCriterion("FScope <=", value, "fscope");
            return (Criteria) this;
        }

        public Criteria andFscopeLike(String value) {
            addCriterion("FScope like", value, "fscope");
            return (Criteria) this;
        }

        public Criteria andFscopeNotLike(String value) {
            addCriterion("FScope not like", value, "fscope");
            return (Criteria) this;
        }

        public Criteria andFscopeIn(List<String> values) {
            addCriterion("FScope in", values, "fscope");
            return (Criteria) this;
        }

        public Criteria andFscopeNotIn(List<String> values) {
            addCriterion("FScope not in", values, "fscope");
            return (Criteria) this;
        }

        public Criteria andFscopeBetween(String value1, String value2) {
            addCriterion("FScope between", value1, value2, "fscope");
            return (Criteria) this;
        }

        public Criteria andFscopeNotBetween(String value1, String value2) {
            addCriterion("FScope not between", value1, value2, "fscope");
            return (Criteria) this;
        }

        public Criteria andFregisteraddrIsNull() {
            addCriterion("FRegisterAddr is null");
            return (Criteria) this;
        }

        public Criteria andFregisteraddrIsNotNull() {
            addCriterion("FRegisterAddr is not null");
            return (Criteria) this;
        }

        public Criteria andFregisteraddrEqualTo(String value) {
            addCriterion("FRegisterAddr =", value, "fregisteraddr");
            return (Criteria) this;
        }

        public Criteria andFregisteraddrNotEqualTo(String value) {
            addCriterion("FRegisterAddr <>", value, "fregisteraddr");
            return (Criteria) this;
        }

        public Criteria andFregisteraddrGreaterThan(String value) {
            addCriterion("FRegisterAddr >", value, "fregisteraddr");
            return (Criteria) this;
        }

        public Criteria andFregisteraddrGreaterThanOrEqualTo(String value) {
            addCriterion("FRegisterAddr >=", value, "fregisteraddr");
            return (Criteria) this;
        }

        public Criteria andFregisteraddrLessThan(String value) {
            addCriterion("FRegisterAddr <", value, "fregisteraddr");
            return (Criteria) this;
        }

        public Criteria andFregisteraddrLessThanOrEqualTo(String value) {
            addCriterion("FRegisterAddr <=", value, "fregisteraddr");
            return (Criteria) this;
        }

        public Criteria andFregisteraddrLike(String value) {
            addCriterion("FRegisterAddr like", value, "fregisteraddr");
            return (Criteria) this;
        }

        public Criteria andFregisteraddrNotLike(String value) {
            addCriterion("FRegisterAddr not like", value, "fregisteraddr");
            return (Criteria) this;
        }

        public Criteria andFregisteraddrIn(List<String> values) {
            addCriterion("FRegisterAddr in", values, "fregisteraddr");
            return (Criteria) this;
        }

        public Criteria andFregisteraddrNotIn(List<String> values) {
            addCriterion("FRegisterAddr not in", values, "fregisteraddr");
            return (Criteria) this;
        }

        public Criteria andFregisteraddrBetween(String value1, String value2) {
            addCriterion("FRegisterAddr between", value1, value2, "fregisteraddr");
            return (Criteria) this;
        }

        public Criteria andFregisteraddrNotBetween(String value1, String value2) {
            addCriterion("FRegisterAddr not between", value1, value2, "fregisteraddr");
            return (Criteria) this;
        }

        public Criteria andFworkaddrIsNull() {
            addCriterion("FWorkAddr is null");
            return (Criteria) this;
        }

        public Criteria andFworkaddrIsNotNull() {
            addCriterion("FWorkAddr is not null");
            return (Criteria) this;
        }

        public Criteria andFworkaddrEqualTo(String value) {
            addCriterion("FWorkAddr =", value, "fworkaddr");
            return (Criteria) this;
        }

        public Criteria andFworkaddrNotEqualTo(String value) {
            addCriterion("FWorkAddr <>", value, "fworkaddr");
            return (Criteria) this;
        }

        public Criteria andFworkaddrGreaterThan(String value) {
            addCriterion("FWorkAddr >", value, "fworkaddr");
            return (Criteria) this;
        }

        public Criteria andFworkaddrGreaterThanOrEqualTo(String value) {
            addCriterion("FWorkAddr >=", value, "fworkaddr");
            return (Criteria) this;
        }

        public Criteria andFworkaddrLessThan(String value) {
            addCriterion("FWorkAddr <", value, "fworkaddr");
            return (Criteria) this;
        }

        public Criteria andFworkaddrLessThanOrEqualTo(String value) {
            addCriterion("FWorkAddr <=", value, "fworkaddr");
            return (Criteria) this;
        }

        public Criteria andFworkaddrLike(String value) {
            addCriterion("FWorkAddr like", value, "fworkaddr");
            return (Criteria) this;
        }

        public Criteria andFworkaddrNotLike(String value) {
            addCriterion("FWorkAddr not like", value, "fworkaddr");
            return (Criteria) this;
        }

        public Criteria andFworkaddrIn(List<String> values) {
            addCriterion("FWorkAddr in", values, "fworkaddr");
            return (Criteria) this;
        }

        public Criteria andFworkaddrNotIn(List<String> values) {
            addCriterion("FWorkAddr not in", values, "fworkaddr");
            return (Criteria) this;
        }

        public Criteria andFworkaddrBetween(String value1, String value2) {
            addCriterion("FWorkAddr between", value1, value2, "fworkaddr");
            return (Criteria) this;
        }

        public Criteria andFworkaddrNotBetween(String value1, String value2) {
            addCriterion("FWorkAddr not between", value1, value2, "fworkaddr");
            return (Criteria) this;
        }

        public Criteria andFuserIsNull() {
            addCriterion("FUser is null");
            return (Criteria) this;
        }

        public Criteria andFuserIsNotNull() {
            addCriterion("FUser is not null");
            return (Criteria) this;
        }

        public Criteria andFuserEqualTo(String value) {
            addCriterion("FUser =", value, "fuser");
            return (Criteria) this;
        }

        public Criteria andFuserNotEqualTo(String value) {
            addCriterion("FUser <>", value, "fuser");
            return (Criteria) this;
        }

        public Criteria andFuserGreaterThan(String value) {
            addCriterion("FUser >", value, "fuser");
            return (Criteria) this;
        }

        public Criteria andFuserGreaterThanOrEqualTo(String value) {
            addCriterion("FUser >=", value, "fuser");
            return (Criteria) this;
        }

        public Criteria andFuserLessThan(String value) {
            addCriterion("FUser <", value, "fuser");
            return (Criteria) this;
        }

        public Criteria andFuserLessThanOrEqualTo(String value) {
            addCriterion("FUser <=", value, "fuser");
            return (Criteria) this;
        }

        public Criteria andFuserLike(String value) {
            addCriterion("FUser like", value, "fuser");
            return (Criteria) this;
        }

        public Criteria andFuserNotLike(String value) {
            addCriterion("FUser not like", value, "fuser");
            return (Criteria) this;
        }

        public Criteria andFuserIn(List<String> values) {
            addCriterion("FUser in", values, "fuser");
            return (Criteria) this;
        }

        public Criteria andFuserNotIn(List<String> values) {
            addCriterion("FUser not in", values, "fuser");
            return (Criteria) this;
        }

        public Criteria andFuserBetween(String value1, String value2) {
            addCriterion("FUser between", value1, value2, "fuser");
            return (Criteria) this;
        }

        public Criteria andFuserNotBetween(String value1, String value2) {
            addCriterion("FUser not between", value1, value2, "fuser");
            return (Criteria) this;
        }

        public Criteria andFusertelIsNull() {
            addCriterion("FUserTel is null");
            return (Criteria) this;
        }

        public Criteria andFusertelIsNotNull() {
            addCriterion("FUserTel is not null");
            return (Criteria) this;
        }

        public Criteria andFusertelEqualTo(String value) {
            addCriterion("FUserTel =", value, "fusertel");
            return (Criteria) this;
        }

        public Criteria andFusertelNotEqualTo(String value) {
            addCriterion("FUserTel <>", value, "fusertel");
            return (Criteria) this;
        }

        public Criteria andFusertelGreaterThan(String value) {
            addCriterion("FUserTel >", value, "fusertel");
            return (Criteria) this;
        }

        public Criteria andFusertelGreaterThanOrEqualTo(String value) {
            addCriterion("FUserTel >=", value, "fusertel");
            return (Criteria) this;
        }

        public Criteria andFusertelLessThan(String value) {
            addCriterion("FUserTel <", value, "fusertel");
            return (Criteria) this;
        }

        public Criteria andFusertelLessThanOrEqualTo(String value) {
            addCriterion("FUserTel <=", value, "fusertel");
            return (Criteria) this;
        }

        public Criteria andFusertelLike(String value) {
            addCriterion("FUserTel like", value, "fusertel");
            return (Criteria) this;
        }

        public Criteria andFusertelNotLike(String value) {
            addCriterion("FUserTel not like", value, "fusertel");
            return (Criteria) this;
        }

        public Criteria andFusertelIn(List<String> values) {
            addCriterion("FUserTel in", values, "fusertel");
            return (Criteria) this;
        }

        public Criteria andFusertelNotIn(List<String> values) {
            addCriterion("FUserTel not in", values, "fusertel");
            return (Criteria) this;
        }

        public Criteria andFusertelBetween(String value1, String value2) {
            addCriterion("FUserTel between", value1, value2, "fusertel");
            return (Criteria) this;
        }

        public Criteria andFusertelNotBetween(String value1, String value2) {
            addCriterion("FUserTel not between", value1, value2, "fusertel");
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

        public Criteria andFproductIsNull() {
            addCriterion("FProduct is null");
            return (Criteria) this;
        }

        public Criteria andFproductIsNotNull() {
            addCriterion("FProduct is not null");
            return (Criteria) this;
        }

        public Criteria andFproductEqualTo(String value) {
            addCriterion("FProduct =", value, "fproduct");
            return (Criteria) this;
        }

        public Criteria andFproductNotEqualTo(String value) {
            addCriterion("FProduct <>", value, "fproduct");
            return (Criteria) this;
        }

        public Criteria andFproductGreaterThan(String value) {
            addCriterion("FProduct >", value, "fproduct");
            return (Criteria) this;
        }

        public Criteria andFproductGreaterThanOrEqualTo(String value) {
            addCriterion("FProduct >=", value, "fproduct");
            return (Criteria) this;
        }

        public Criteria andFproductLessThan(String value) {
            addCriterion("FProduct <", value, "fproduct");
            return (Criteria) this;
        }

        public Criteria andFproductLessThanOrEqualTo(String value) {
            addCriterion("FProduct <=", value, "fproduct");
            return (Criteria) this;
        }

        public Criteria andFproductLike(String value) {
            addCriterion("FProduct like", value, "fproduct");
            return (Criteria) this;
        }

        public Criteria andFproductNotLike(String value) {
            addCriterion("FProduct not like", value, "fproduct");
            return (Criteria) this;
        }

        public Criteria andFproductIn(List<String> values) {
            addCriterion("FProduct in", values, "fproduct");
            return (Criteria) this;
        }

        public Criteria andFproductNotIn(List<String> values) {
            addCriterion("FProduct not in", values, "fproduct");
            return (Criteria) this;
        }

        public Criteria andFproductBetween(String value1, String value2) {
            addCriterion("FProduct between", value1, value2, "fproduct");
            return (Criteria) this;
        }

        public Criteria andFproductNotBetween(String value1, String value2) {
            addCriterion("FProduct not between", value1, value2, "fproduct");
            return (Criteria) this;
        }

        public Criteria andFoperatestateIsNull() {
            addCriterion("FOperateState is null");
            return (Criteria) this;
        }

        public Criteria andFoperatestateIsNotNull() {
            addCriterion("FOperateState is not null");
            return (Criteria) this;
        }

        public Criteria andFoperatestateEqualTo(String value) {
            addCriterion("FOperateState =", value, "foperatestate");
            return (Criteria) this;
        }

        public Criteria andFoperatestateNotEqualTo(String value) {
            addCriterion("FOperateState <>", value, "foperatestate");
            return (Criteria) this;
        }

        public Criteria andFoperatestateGreaterThan(String value) {
            addCriterion("FOperateState >", value, "foperatestate");
            return (Criteria) this;
        }

        public Criteria andFoperatestateGreaterThanOrEqualTo(String value) {
            addCriterion("FOperateState >=", value, "foperatestate");
            return (Criteria) this;
        }

        public Criteria andFoperatestateLessThan(String value) {
            addCriterion("FOperateState <", value, "foperatestate");
            return (Criteria) this;
        }

        public Criteria andFoperatestateLessThanOrEqualTo(String value) {
            addCriterion("FOperateState <=", value, "foperatestate");
            return (Criteria) this;
        }

        public Criteria andFoperatestateLike(String value) {
            addCriterion("FOperateState like", value, "foperatestate");
            return (Criteria) this;
        }

        public Criteria andFoperatestateNotLike(String value) {
            addCriterion("FOperateState not like", value, "foperatestate");
            return (Criteria) this;
        }

        public Criteria andFoperatestateIn(List<String> values) {
            addCriterion("FOperateState in", values, "foperatestate");
            return (Criteria) this;
        }

        public Criteria andFoperatestateNotIn(List<String> values) {
            addCriterion("FOperateState not in", values, "foperatestate");
            return (Criteria) this;
        }

        public Criteria andFoperatestateBetween(String value1, String value2) {
            addCriterion("FOperateState between", value1, value2, "foperatestate");
            return (Criteria) this;
        }

        public Criteria andFoperatestateNotBetween(String value1, String value2) {
            addCriterion("FOperateState not between", value1, value2, "foperatestate");
            return (Criteria) this;
        }

        public Criteria andFintroductionIsNull() {
            addCriterion("FIntroduction is null");
            return (Criteria) this;
        }

        public Criteria andFintroductionIsNotNull() {
            addCriterion("FIntroduction is not null");
            return (Criteria) this;
        }

        public Criteria andFintroductionEqualTo(String value) {
            addCriterion("FIntroduction =", value, "fintroduction");
            return (Criteria) this;
        }

        public Criteria andFintroductionNotEqualTo(String value) {
            addCriterion("FIntroduction <>", value, "fintroduction");
            return (Criteria) this;
        }

        public Criteria andFintroductionGreaterThan(String value) {
            addCriterion("FIntroduction >", value, "fintroduction");
            return (Criteria) this;
        }

        public Criteria andFintroductionGreaterThanOrEqualTo(String value) {
            addCriterion("FIntroduction >=", value, "fintroduction");
            return (Criteria) this;
        }

        public Criteria andFintroductionLessThan(String value) {
            addCriterion("FIntroduction <", value, "fintroduction");
            return (Criteria) this;
        }

        public Criteria andFintroductionLessThanOrEqualTo(String value) {
            addCriterion("FIntroduction <=", value, "fintroduction");
            return (Criteria) this;
        }

        public Criteria andFintroductionLike(String value) {
            addCriterion("FIntroduction like", value, "fintroduction");
            return (Criteria) this;
        }

        public Criteria andFintroductionNotLike(String value) {
            addCriterion("FIntroduction not like", value, "fintroduction");
            return (Criteria) this;
        }

        public Criteria andFintroductionIn(List<String> values) {
            addCriterion("FIntroduction in", values, "fintroduction");
            return (Criteria) this;
        }

        public Criteria andFintroductionNotIn(List<String> values) {
            addCriterion("FIntroduction not in", values, "fintroduction");
            return (Criteria) this;
        }

        public Criteria andFintroductionBetween(String value1, String value2) {
            addCriterion("FIntroduction between", value1, value2, "fintroduction");
            return (Criteria) this;
        }

        public Criteria andFintroductionNotBetween(String value1, String value2) {
            addCriterion("FIntroduction not between", value1, value2, "fintroduction");
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
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：student_capability_evaluation..t_enterprise
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * TEnterprise
     * Criterion
     * 数据库表：student_capability_evaluation..t_enterprise
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