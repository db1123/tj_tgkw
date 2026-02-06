package fun.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TProjectDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * t_project_data
     * @mbg.generated
     */
    public TProjectDataExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * 设置排序字段
     * t_project_data
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * t_project_data
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * t_project_data
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * t_project_data
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * t_project_data
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * t_project_data
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * t_project_data
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
     * t_project_data
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
     * t_project_data
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * t_project_data
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TProjectData
     * GeneratedCriteria
     * 数据库表：t_project_data
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

        public Criteria andFbrowsenumIsNull() {
            addCriterion("FBrowseNum is null");
            return (Criteria) this;
        }

        public Criteria andFbrowsenumIsNotNull() {
            addCriterion("FBrowseNum is not null");
            return (Criteria) this;
        }

        public Criteria andFbrowsenumEqualTo(Integer value) {
            addCriterion("FBrowseNum =", value, "fbrowsenum");
            return (Criteria) this;
        }

        public Criteria andFbrowsenumNotEqualTo(Integer value) {
            addCriterion("FBrowseNum <>", value, "fbrowsenum");
            return (Criteria) this;
        }

        public Criteria andFbrowsenumGreaterThan(Integer value) {
            addCriterion("FBrowseNum >", value, "fbrowsenum");
            return (Criteria) this;
        }

        public Criteria andFbrowsenumGreaterThanOrEqualTo(Integer value) {
            addCriterion("FBrowseNum >=", value, "fbrowsenum");
            return (Criteria) this;
        }

        public Criteria andFbrowsenumLessThan(Integer value) {
            addCriterion("FBrowseNum <", value, "fbrowsenum");
            return (Criteria) this;
        }

        public Criteria andFbrowsenumLessThanOrEqualTo(Integer value) {
            addCriterion("FBrowseNum <=", value, "fbrowsenum");
            return (Criteria) this;
        }

        public Criteria andFbrowsenumIn(List<Integer> values) {
            addCriterion("FBrowseNum in", values, "fbrowsenum");
            return (Criteria) this;
        }

        public Criteria andFbrowsenumNotIn(List<Integer> values) {
            addCriterion("FBrowseNum not in", values, "fbrowsenum");
            return (Criteria) this;
        }

        public Criteria andFbrowsenumBetween(Integer value1, Integer value2) {
            addCriterion("FBrowseNum between", value1, value2, "fbrowsenum");
            return (Criteria) this;
        }

        public Criteria andFbrowsenumNotBetween(Integer value1, Integer value2) {
            addCriterion("FBrowseNum not between", value1, value2, "fbrowsenum");
            return (Criteria) this;
        }

        public Criteria andFdownloadnumIsNull() {
            addCriterion("FDownloadNum is null");
            return (Criteria) this;
        }

        public Criteria andFdownloadnumIsNotNull() {
            addCriterion("FDownloadNum is not null");
            return (Criteria) this;
        }

        public Criteria andFdownloadnumEqualTo(Integer value) {
            addCriterion("FDownloadNum =", value, "fdownloadnum");
            return (Criteria) this;
        }

        public Criteria andFdownloadnumNotEqualTo(Integer value) {
            addCriterion("FDownloadNum <>", value, "fdownloadnum");
            return (Criteria) this;
        }

        public Criteria andFdownloadnumGreaterThan(Integer value) {
            addCriterion("FDownloadNum >", value, "fdownloadnum");
            return (Criteria) this;
        }

        public Criteria andFdownloadnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("FDownloadNum >=", value, "fdownloadnum");
            return (Criteria) this;
        }

        public Criteria andFdownloadnumLessThan(Integer value) {
            addCriterion("FDownloadNum <", value, "fdownloadnum");
            return (Criteria) this;
        }

        public Criteria andFdownloadnumLessThanOrEqualTo(Integer value) {
            addCriterion("FDownloadNum <=", value, "fdownloadnum");
            return (Criteria) this;
        }

        public Criteria andFdownloadnumIn(List<Integer> values) {
            addCriterion("FDownloadNum in", values, "fdownloadnum");
            return (Criteria) this;
        }

        public Criteria andFdownloadnumNotIn(List<Integer> values) {
            addCriterion("FDownloadNum not in", values, "fdownloadnum");
            return (Criteria) this;
        }

        public Criteria andFdownloadnumBetween(Integer value1, Integer value2) {
            addCriterion("FDownloadNum between", value1, value2, "fdownloadnum");
            return (Criteria) this;
        }

        public Criteria andFdownloadnumNotBetween(Integer value1, Integer value2) {
            addCriterion("FDownloadNum not between", value1, value2, "fdownloadnum");
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
     * 数据库表：t_project_data
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * TProjectData
     * Criterion
     * 数据库表：t_project_data
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