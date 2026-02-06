package fun.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TCourseSemesterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * teaching_diversity..t_course_semester
     * @mbg.generated
     */
    public TCourseSemesterExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * 设置排序字段
     * teaching_diversity..t_course_semester
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * teaching_diversity..t_course_semester
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * teaching_diversity..t_course_semester
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * teaching_diversity..t_course_semester
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * teaching_diversity..t_course_semester
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * teaching_diversity..t_course_semester
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * teaching_diversity..t_course_semester
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
     * teaching_diversity..t_course_semester
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
     * teaching_diversity..t_course_semester
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * teaching_diversity..t_course_semester
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TCourseSemester
     * GeneratedCriteria
     * 数据库表：teaching_diversity..t_course_semester
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

        public Criteria andFxnIsNull() {
            addCriterion("FXN is null");
            return (Criteria) this;
        }

        public Criteria andFxnIsNotNull() {
            addCriterion("FXN is not null");
            return (Criteria) this;
        }

        public Criteria andFxnEqualTo(Integer value) {
            addCriterion("FXN =", value, "fxn");
            return (Criteria) this;
        }

        public Criteria andFxnNotEqualTo(Integer value) {
            addCriterion("FXN <>", value, "fxn");
            return (Criteria) this;
        }

        public Criteria andFxnGreaterThan(Integer value) {
            addCriterion("FXN >", value, "fxn");
            return (Criteria) this;
        }

        public Criteria andFxnGreaterThanOrEqualTo(Integer value) {
            addCriterion("FXN >=", value, "fxn");
            return (Criteria) this;
        }

        public Criteria andFxnLessThan(Integer value) {
            addCriterion("FXN <", value, "fxn");
            return (Criteria) this;
        }

        public Criteria andFxnLessThanOrEqualTo(Integer value) {
            addCriterion("FXN <=", value, "fxn");
            return (Criteria) this;
        }

        public Criteria andFxnIn(List<Integer> values) {
            addCriterion("FXN in", values, "fxn");
            return (Criteria) this;
        }

        public Criteria andFxnNotIn(List<Integer> values) {
            addCriterion("FXN not in", values, "fxn");
            return (Criteria) this;
        }

        public Criteria andFxnBetween(Integer value1, Integer value2) {
            addCriterion("FXN between", value1, value2, "fxn");
            return (Criteria) this;
        }

        public Criteria andFxnNotBetween(Integer value1, Integer value2) {
            addCriterion("FXN not between", value1, value2, "fxn");
            return (Criteria) this;
        }

        public Criteria andFxqIsNull() {
            addCriterion("FXQ is null");
            return (Criteria) this;
        }

        public Criteria andFxqIsNotNull() {
            addCriterion("FXQ is not null");
            return (Criteria) this;
        }

        public Criteria andFxqEqualTo(Integer value) {
            addCriterion("FXQ =", value, "fxq");
            return (Criteria) this;
        }

        public Criteria andFxqNotEqualTo(Integer value) {
            addCriterion("FXQ <>", value, "fxq");
            return (Criteria) this;
        }

        public Criteria andFxqGreaterThan(Integer value) {
            addCriterion("FXQ >", value, "fxq");
            return (Criteria) this;
        }

        public Criteria andFxqGreaterThanOrEqualTo(Integer value) {
            addCriterion("FXQ >=", value, "fxq");
            return (Criteria) this;
        }

        public Criteria andFxqLessThan(Integer value) {
            addCriterion("FXQ <", value, "fxq");
            return (Criteria) this;
        }

        public Criteria andFxqLessThanOrEqualTo(Integer value) {
            addCriterion("FXQ <=", value, "fxq");
            return (Criteria) this;
        }

        public Criteria andFxqIn(List<Integer> values) {
            addCriterion("FXQ in", values, "fxq");
            return (Criteria) this;
        }

        public Criteria andFxqNotIn(List<Integer> values) {
            addCriterion("FXQ not in", values, "fxq");
            return (Criteria) this;
        }

        public Criteria andFxqBetween(Integer value1, Integer value2) {
            addCriterion("FXQ between", value1, value2, "fxq");
            return (Criteria) this;
        }

        public Criteria andFxqNotBetween(Integer value1, Integer value2) {
            addCriterion("FXQ not between", value1, value2, "fxq");
            return (Criteria) this;
        }

        public Criteria andFjnameIsNull() {
            addCriterion("FJName is null");
            return (Criteria) this;
        }

        public Criteria andFjnameIsNotNull() {
            addCriterion("FJName is not null");
            return (Criteria) this;
        }

        public Criteria andFjnameEqualTo(String value) {
            addCriterion("FJName =", value, "fjname");
            return (Criteria) this;
        }

        public Criteria andFjnameNotEqualTo(String value) {
            addCriterion("FJName <>", value, "fjname");
            return (Criteria) this;
        }

        public Criteria andFjnameGreaterThan(String value) {
            addCriterion("FJName >", value, "fjname");
            return (Criteria) this;
        }

        public Criteria andFjnameGreaterThanOrEqualTo(String value) {
            addCriterion("FJName >=", value, "fjname");
            return (Criteria) this;
        }

        public Criteria andFjnameLessThan(String value) {
            addCriterion("FJName <", value, "fjname");
            return (Criteria) this;
        }

        public Criteria andFjnameLessThanOrEqualTo(String value) {
            addCriterion("FJName <=", value, "fjname");
            return (Criteria) this;
        }

        public Criteria andFjnameLike(String value) {
            addCriterion("FJName like", value, "fjname");
            return (Criteria) this;
        }

        public Criteria andFjnameNotLike(String value) {
            addCriterion("FJName not like", value, "fjname");
            return (Criteria) this;
        }

        public Criteria andFjnameIn(List<String> values) {
            addCriterion("FJName in", values, "fjname");
            return (Criteria) this;
        }

        public Criteria andFjnameNotIn(List<String> values) {
            addCriterion("FJName not in", values, "fjname");
            return (Criteria) this;
        }

        public Criteria andFjnameBetween(String value1, String value2) {
            addCriterion("FJName between", value1, value2, "fjname");
            return (Criteria) this;
        }

        public Criteria andFjnameNotBetween(String value1, String value2) {
            addCriterion("FJName not between", value1, value2, "fjname");
            return (Criteria) this;
        }

        public Criteria andFkkxqIsNull() {
            addCriterion("FKKXQ is null");
            return (Criteria) this;
        }

        public Criteria andFkkxqIsNotNull() {
            addCriterion("FKKXQ is not null");
            return (Criteria) this;
        }

        public Criteria andFkkxqEqualTo(Integer value) {
            addCriterion("FKKXQ =", value, "fkkxq");
            return (Criteria) this;
        }

        public Criteria andFkkxqNotEqualTo(Integer value) {
            addCriterion("FKKXQ <>", value, "fkkxq");
            return (Criteria) this;
        }

        public Criteria andFkkxqGreaterThan(Integer value) {
            addCriterion("FKKXQ >", value, "fkkxq");
            return (Criteria) this;
        }

        public Criteria andFkkxqGreaterThanOrEqualTo(Integer value) {
            addCriterion("FKKXQ >=", value, "fkkxq");
            return (Criteria) this;
        }

        public Criteria andFkkxqLessThan(Integer value) {
            addCriterion("FKKXQ <", value, "fkkxq");
            return (Criteria) this;
        }

        public Criteria andFkkxqLessThanOrEqualTo(Integer value) {
            addCriterion("FKKXQ <=", value, "fkkxq");
            return (Criteria) this;
        }

        public Criteria andFkkxqIn(List<Integer> values) {
            addCriterion("FKKXQ in", values, "fkkxq");
            return (Criteria) this;
        }

        public Criteria andFkkxqNotIn(List<Integer> values) {
            addCriterion("FKKXQ not in", values, "fkkxq");
            return (Criteria) this;
        }

        public Criteria andFkkxqBetween(Integer value1, Integer value2) {
            addCriterion("FKKXQ between", value1, value2, "fkkxq");
            return (Criteria) this;
        }

        public Criteria andFkkxqNotBetween(Integer value1, Integer value2) {
            addCriterion("FKKXQ not between", value1, value2, "fkkxq");
            return (Criteria) this;
        }
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：teaching_diversity..t_course_semester
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * TCourseSemester
     * Criterion
     * 数据库表：teaching_diversity..t_course_semester
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