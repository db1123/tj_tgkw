package fun.server.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TCourseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * teaching_diversity..t_course
     * @mbg.generated
     */
    public TCourseExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * 设置排序字段
     * teaching_diversity..t_course
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * teaching_diversity..t_course
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * teaching_diversity..t_course
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * teaching_diversity..t_course
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * teaching_diversity..t_course
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * teaching_diversity..t_course
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * teaching_diversity..t_course
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
     * teaching_diversity..t_course
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
     * teaching_diversity..t_course
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * teaching_diversity..t_course
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TCourse
     * GeneratedCriteria
     * 数据库表：teaching_diversity..t_course
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

        public Criteria andFpassscoreIsNull() {
            addCriterion("FPassScore is null");
            return (Criteria) this;
        }

        public Criteria andFpassscoreIsNotNull() {
            addCriterion("FPassScore is not null");
            return (Criteria) this;
        }

        public Criteria andFpassscoreEqualTo(BigDecimal value) {
            addCriterion("FPassScore =", value, "fpassscore");
            return (Criteria) this;
        }

        public Criteria andFpassscoreNotEqualTo(BigDecimal value) {
            addCriterion("FPassScore <>", value, "fpassscore");
            return (Criteria) this;
        }

        public Criteria andFpassscoreGreaterThan(BigDecimal value) {
            addCriterion("FPassScore >", value, "fpassscore");
            return (Criteria) this;
        }

        public Criteria andFpassscoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FPassScore >=", value, "fpassscore");
            return (Criteria) this;
        }

        public Criteria andFpassscoreLessThan(BigDecimal value) {
            addCriterion("FPassScore <", value, "fpassscore");
            return (Criteria) this;
        }

        public Criteria andFpassscoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FPassScore <=", value, "fpassscore");
            return (Criteria) this;
        }

        public Criteria andFpassscoreIn(List<BigDecimal> values) {
            addCriterion("FPassScore in", values, "fpassscore");
            return (Criteria) this;
        }

        public Criteria andFpassscoreNotIn(List<BigDecimal> values) {
            addCriterion("FPassScore not in", values, "fpassscore");
            return (Criteria) this;
        }

        public Criteria andFpassscoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FPassScore between", value1, value2, "fpassscore");
            return (Criteria) this;
        }

        public Criteria andFpassscoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FPassScore not between", value1, value2, "fpassscore");
            return (Criteria) this;
        }

        public Criteria andFfullscoreIsNull() {
            addCriterion("FFullScore is null");
            return (Criteria) this;
        }

        public Criteria andFfullscoreIsNotNull() {
            addCriterion("FFullScore is not null");
            return (Criteria) this;
        }

        public Criteria andFfullscoreEqualTo(BigDecimal value) {
            addCriterion("FFullScore =", value, "ffullscore");
            return (Criteria) this;
        }

        public Criteria andFfullscoreNotEqualTo(BigDecimal value) {
            addCriterion("FFullScore <>", value, "ffullscore");
            return (Criteria) this;
        }

        public Criteria andFfullscoreGreaterThan(BigDecimal value) {
            addCriterion("FFullScore >", value, "ffullscore");
            return (Criteria) this;
        }

        public Criteria andFfullscoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FFullScore >=", value, "ffullscore");
            return (Criteria) this;
        }

        public Criteria andFfullscoreLessThan(BigDecimal value) {
            addCriterion("FFullScore <", value, "ffullscore");
            return (Criteria) this;
        }

        public Criteria andFfullscoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FFullScore <=", value, "ffullscore");
            return (Criteria) this;
        }

        public Criteria andFfullscoreIn(List<BigDecimal> values) {
            addCriterion("FFullScore in", values, "ffullscore");
            return (Criteria) this;
        }

        public Criteria andFfullscoreNotIn(List<BigDecimal> values) {
            addCriterion("FFullScore not in", values, "ffullscore");
            return (Criteria) this;
        }

        public Criteria andFfullscoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FFullScore between", value1, value2, "ffullscore");
            return (Criteria) this;
        }

        public Criteria andFfullscoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FFullScore not between", value1, value2, "ffullscore");
            return (Criteria) this;
        }

        public Criteria andFabilityidIsNull() {
            addCriterion("FAbilityID is null");
            return (Criteria) this;
        }

        public Criteria andFabilityidIsNotNull() {
            addCriterion("FAbilityID is not null");
            return (Criteria) this;
        }

        public Criteria andFabilityidEqualTo(Long value) {
            addCriterion("FAbilityID =", value, "fabilityid");
            return (Criteria) this;
        }

        public Criteria andFabilityidNotEqualTo(Long value) {
            addCriterion("FAbilityID <>", value, "fabilityid");
            return (Criteria) this;
        }

        public Criteria andFabilityidGreaterThan(Long value) {
            addCriterion("FAbilityID >", value, "fabilityid");
            return (Criteria) this;
        }

        public Criteria andFabilityidGreaterThanOrEqualTo(Long value) {
            addCriterion("FAbilityID >=", value, "fabilityid");
            return (Criteria) this;
        }

        public Criteria andFabilityidLessThan(Long value) {
            addCriterion("FAbilityID <", value, "fabilityid");
            return (Criteria) this;
        }

        public Criteria andFabilityidLessThanOrEqualTo(Long value) {
            addCriterion("FAbilityID <=", value, "fabilityid");
            return (Criteria) this;
        }

        public Criteria andFabilityidIn(List<Long> values) {
            addCriterion("FAbilityID in", values, "fabilityid");
            return (Criteria) this;
        }

        public Criteria andFabilityidNotIn(List<Long> values) {
            addCriterion("FAbilityID not in", values, "fabilityid");
            return (Criteria) this;
        }

        public Criteria andFabilityidBetween(Long value1, Long value2) {
            addCriterion("FAbilityID between", value1, value2, "fabilityid");
            return (Criteria) this;
        }

        public Criteria andFabilityidNotBetween(Long value1, Long value2) {
            addCriterion("FAbilityID not between", value1, value2, "fabilityid");
            return (Criteria) this;
        }

        public Criteria andFabilitylevelidIsNull() {
            addCriterion("FAbilityLevelID is null");
            return (Criteria) this;
        }

        public Criteria andFabilitylevelidIsNotNull() {
            addCriterion("FAbilityLevelID is not null");
            return (Criteria) this;
        }

        public Criteria andFabilitylevelidEqualTo(Long value) {
            addCriterion("FAbilityLevelID =", value, "fabilitylevelid");
            return (Criteria) this;
        }

        public Criteria andFabilitylevelidNotEqualTo(Long value) {
            addCriterion("FAbilityLevelID <>", value, "fabilitylevelid");
            return (Criteria) this;
        }

        public Criteria andFabilitylevelidGreaterThan(Long value) {
            addCriterion("FAbilityLevelID >", value, "fabilitylevelid");
            return (Criteria) this;
        }

        public Criteria andFabilitylevelidGreaterThanOrEqualTo(Long value) {
            addCriterion("FAbilityLevelID >=", value, "fabilitylevelid");
            return (Criteria) this;
        }

        public Criteria andFabilitylevelidLessThan(Long value) {
            addCriterion("FAbilityLevelID <", value, "fabilitylevelid");
            return (Criteria) this;
        }

        public Criteria andFabilitylevelidLessThanOrEqualTo(Long value) {
            addCriterion("FAbilityLevelID <=", value, "fabilitylevelid");
            return (Criteria) this;
        }

        public Criteria andFabilitylevelidIn(List<Long> values) {
            addCriterion("FAbilityLevelID in", values, "fabilitylevelid");
            return (Criteria) this;
        }

        public Criteria andFabilitylevelidNotIn(List<Long> values) {
            addCriterion("FAbilityLevelID not in", values, "fabilitylevelid");
            return (Criteria) this;
        }

        public Criteria andFabilitylevelidBetween(Long value1, Long value2) {
            addCriterion("FAbilityLevelID between", value1, value2, "fabilitylevelid");
            return (Criteria) this;
        }

        public Criteria andFabilitylevelidNotBetween(Long value1, Long value2) {
            addCriterion("FAbilityLevelID not between", value1, value2, "fabilitylevelid");
            return (Criteria) this;
        }

        public Criteria andFtotalhoursIsNull() {
            addCriterion("FTotalHours is null");
            return (Criteria) this;
        }

        public Criteria andFtotalhoursIsNotNull() {
            addCriterion("FTotalHours is not null");
            return (Criteria) this;
        }

        public Criteria andFtotalhoursEqualTo(String value) {
            addCriterion("FTotalHours =", value, "ftotalhours");
            return (Criteria) this;
        }

        public Criteria andFtotalhoursNotEqualTo(String value) {
            addCriterion("FTotalHours <>", value, "ftotalhours");
            return (Criteria) this;
        }

        public Criteria andFtotalhoursGreaterThan(String value) {
            addCriterion("FTotalHours >", value, "ftotalhours");
            return (Criteria) this;
        }

        public Criteria andFtotalhoursGreaterThanOrEqualTo(String value) {
            addCriterion("FTotalHours >=", value, "ftotalhours");
            return (Criteria) this;
        }

        public Criteria andFtotalhoursLessThan(String value) {
            addCriterion("FTotalHours <", value, "ftotalhours");
            return (Criteria) this;
        }

        public Criteria andFtotalhoursLessThanOrEqualTo(String value) {
            addCriterion("FTotalHours <=", value, "ftotalhours");
            return (Criteria) this;
        }

        public Criteria andFtotalhoursLike(String value) {
            addCriterion("FTotalHours like", value, "ftotalhours");
            return (Criteria) this;
        }

        public Criteria andFtotalhoursNotLike(String value) {
            addCriterion("FTotalHours not like", value, "ftotalhours");
            return (Criteria) this;
        }

        public Criteria andFtotalhoursIn(List<String> values) {
            addCriterion("FTotalHours in", values, "ftotalhours");
            return (Criteria) this;
        }

        public Criteria andFtotalhoursNotIn(List<String> values) {
            addCriterion("FTotalHours not in", values, "ftotalhours");
            return (Criteria) this;
        }

        public Criteria andFtotalhoursBetween(String value1, String value2) {
            addCriterion("FTotalHours between", value1, value2, "ftotalhours");
            return (Criteria) this;
        }

        public Criteria andFtotalhoursNotBetween(String value1, String value2) {
            addCriterion("FTotalHours not between", value1, value2, "ftotalhours");
            return (Criteria) this;
        }

        public Criteria andFcreditsIsNull() {
            addCriterion("FCredits is null");
            return (Criteria) this;
        }

        public Criteria andFcreditsIsNotNull() {
            addCriterion("FCredits is not null");
            return (Criteria) this;
        }

        public Criteria andFcreditsEqualTo(Float value) {
            addCriterion("FCredits =", value, "fcredits");
            return (Criteria) this;
        }

        public Criteria andFcreditsNotEqualTo(Float value) {
            addCriterion("FCredits <>", value, "fcredits");
            return (Criteria) this;
        }

        public Criteria andFcreditsGreaterThan(Float value) {
            addCriterion("FCredits >", value, "fcredits");
            return (Criteria) this;
        }

        public Criteria andFcreditsGreaterThanOrEqualTo(Float value) {
            addCriterion("FCredits >=", value, "fcredits");
            return (Criteria) this;
        }

        public Criteria andFcreditsLessThan(Float value) {
            addCriterion("FCredits <", value, "fcredits");
            return (Criteria) this;
        }

        public Criteria andFcreditsLessThanOrEqualTo(Float value) {
            addCriterion("FCredits <=", value, "fcredits");
            return (Criteria) this;
        }

        public Criteria andFcreditsIn(List<Float> values) {
            addCriterion("FCredits in", values, "fcredits");
            return (Criteria) this;
        }

        public Criteria andFcreditsNotIn(List<Float> values) {
            addCriterion("FCredits not in", values, "fcredits");
            return (Criteria) this;
        }

        public Criteria andFcreditsBetween(Float value1, Float value2) {
            addCriterion("FCredits between", value1, value2, "fcredits");
            return (Criteria) this;
        }

        public Criteria andFcreditsNotBetween(Float value1, Float value2) {
            addCriterion("FCredits not between", value1, value2, "fcredits");
            return (Criteria) this;
        }

        public Criteria andFmajorIsNull() {
            addCriterion("FMajor is null");
            return (Criteria) this;
        }

        public Criteria andFmajorIsNotNull() {
            addCriterion("FMajor is not null");
            return (Criteria) this;
        }

        public Criteria andFmajorEqualTo(String value) {
            addCriterion("FMajor =", value, "fmajor");
            return (Criteria) this;
        }

        public Criteria andFmajorNotEqualTo(String value) {
            addCriterion("FMajor <>", value, "fmajor");
            return (Criteria) this;
        }

        public Criteria andFmajorGreaterThan(String value) {
            addCriterion("FMajor >", value, "fmajor");
            return (Criteria) this;
        }

        public Criteria andFmajorGreaterThanOrEqualTo(String value) {
            addCriterion("FMajor >=", value, "fmajor");
            return (Criteria) this;
        }

        public Criteria andFmajorLessThan(String value) {
            addCriterion("FMajor <", value, "fmajor");
            return (Criteria) this;
        }

        public Criteria andFmajorLessThanOrEqualTo(String value) {
            addCriterion("FMajor <=", value, "fmajor");
            return (Criteria) this;
        }

        public Criteria andFmajorLike(String value) {
            addCriterion("FMajor like", value, "fmajor");
            return (Criteria) this;
        }

        public Criteria andFmajorNotLike(String value) {
            addCriterion("FMajor not like", value, "fmajor");
            return (Criteria) this;
        }

        public Criteria andFmajorIn(List<String> values) {
            addCriterion("FMajor in", values, "fmajor");
            return (Criteria) this;
        }

        public Criteria andFmajorNotIn(List<String> values) {
            addCriterion("FMajor not in", values, "fmajor");
            return (Criteria) this;
        }

        public Criteria andFmajorBetween(String value1, String value2) {
            addCriterion("FMajor between", value1, value2, "fmajor");
            return (Criteria) this;
        }

        public Criteria andFmajorNotBetween(String value1, String value2) {
            addCriterion("FMajor not between", value1, value2, "fmajor");
            return (Criteria) this;
        }

        public Criteria andFprerequisitesIsNull() {
            addCriterion("FPrerequisites is null");
            return (Criteria) this;
        }

        public Criteria andFprerequisitesIsNotNull() {
            addCriterion("FPrerequisites is not null");
            return (Criteria) this;
        }

        public Criteria andFprerequisitesEqualTo(String value) {
            addCriterion("FPrerequisites =", value, "fprerequisites");
            return (Criteria) this;
        }

        public Criteria andFprerequisitesNotEqualTo(String value) {
            addCriterion("FPrerequisites <>", value, "fprerequisites");
            return (Criteria) this;
        }

        public Criteria andFprerequisitesGreaterThan(String value) {
            addCriterion("FPrerequisites >", value, "fprerequisites");
            return (Criteria) this;
        }

        public Criteria andFprerequisitesGreaterThanOrEqualTo(String value) {
            addCriterion("FPrerequisites >=", value, "fprerequisites");
            return (Criteria) this;
        }

        public Criteria andFprerequisitesLessThan(String value) {
            addCriterion("FPrerequisites <", value, "fprerequisites");
            return (Criteria) this;
        }

        public Criteria andFprerequisitesLessThanOrEqualTo(String value) {
            addCriterion("FPrerequisites <=", value, "fprerequisites");
            return (Criteria) this;
        }

        public Criteria andFprerequisitesLike(String value) {
            addCriterion("FPrerequisites like", value, "fprerequisites");
            return (Criteria) this;
        }

        public Criteria andFprerequisitesNotLike(String value) {
            addCriterion("FPrerequisites not like", value, "fprerequisites");
            return (Criteria) this;
        }

        public Criteria andFprerequisitesIn(List<String> values) {
            addCriterion("FPrerequisites in", values, "fprerequisites");
            return (Criteria) this;
        }

        public Criteria andFprerequisitesNotIn(List<String> values) {
            addCriterion("FPrerequisites not in", values, "fprerequisites");
            return (Criteria) this;
        }

        public Criteria andFprerequisitesBetween(String value1, String value2) {
            addCriterion("FPrerequisites between", value1, value2, "fprerequisites");
            return (Criteria) this;
        }

        public Criteria andFprerequisitesNotBetween(String value1, String value2) {
            addCriterion("FPrerequisites not between", value1, value2, "fprerequisites");
            return (Criteria) this;
        }

        public Criteria andFteachingrequirementsIsNull() {
            addCriterion("FTeachingRequirements is null");
            return (Criteria) this;
        }

        public Criteria andFteachingrequirementsIsNotNull() {
            addCriterion("FTeachingRequirements is not null");
            return (Criteria) this;
        }

        public Criteria andFteachingrequirementsEqualTo(String value) {
            addCriterion("FTeachingRequirements =", value, "fteachingrequirements");
            return (Criteria) this;
        }

        public Criteria andFteachingrequirementsNotEqualTo(String value) {
            addCriterion("FTeachingRequirements <>", value, "fteachingrequirements");
            return (Criteria) this;
        }

        public Criteria andFteachingrequirementsGreaterThan(String value) {
            addCriterion("FTeachingRequirements >", value, "fteachingrequirements");
            return (Criteria) this;
        }

        public Criteria andFteachingrequirementsGreaterThanOrEqualTo(String value) {
            addCriterion("FTeachingRequirements >=", value, "fteachingrequirements");
            return (Criteria) this;
        }

        public Criteria andFteachingrequirementsLessThan(String value) {
            addCriterion("FTeachingRequirements <", value, "fteachingrequirements");
            return (Criteria) this;
        }

        public Criteria andFteachingrequirementsLessThanOrEqualTo(String value) {
            addCriterion("FTeachingRequirements <=", value, "fteachingrequirements");
            return (Criteria) this;
        }

        public Criteria andFteachingrequirementsLike(String value) {
            addCriterion("FTeachingRequirements like", value, "fteachingrequirements");
            return (Criteria) this;
        }

        public Criteria andFteachingrequirementsNotLike(String value) {
            addCriterion("FTeachingRequirements not like", value, "fteachingrequirements");
            return (Criteria) this;
        }

        public Criteria andFteachingrequirementsIn(List<String> values) {
            addCriterion("FTeachingRequirements in", values, "fteachingrequirements");
            return (Criteria) this;
        }

        public Criteria andFteachingrequirementsNotIn(List<String> values) {
            addCriterion("FTeachingRequirements not in", values, "fteachingrequirements");
            return (Criteria) this;
        }

        public Criteria andFteachingrequirementsBetween(String value1, String value2) {
            addCriterion("FTeachingRequirements between", value1, value2, "fteachingrequirements");
            return (Criteria) this;
        }

        public Criteria andFteachingrequirementsNotBetween(String value1, String value2) {
            addCriterion("FTeachingRequirements not between", value1, value2, "fteachingrequirements");
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

        public Criteria andFtypeEqualTo(Long value) {
            addCriterion("FType =", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeNotEqualTo(Long value) {
            addCriterion("FType <>", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeGreaterThan(Long value) {
            addCriterion("FType >", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeGreaterThanOrEqualTo(Long value) {
            addCriterion("FType >=", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeLessThan(Long value) {
            addCriterion("FType <", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeLessThanOrEqualTo(Long value) {
            addCriterion("FType <=", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeIn(List<Long> values) {
            addCriterion("FType in", values, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeNotIn(List<Long> values) {
            addCriterion("FType not in", values, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeBetween(Long value1, Long value2) {
            addCriterion("FType between", value1, value2, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeNotBetween(Long value1, Long value2) {
            addCriterion("FType not between", value1, value2, "ftype");
            return (Criteria) this;
        }

        public Criteria andFcnatureIsNull() {
            addCriterion("FCNature is null");
            return (Criteria) this;
        }

        public Criteria andFcnatureIsNotNull() {
            addCriterion("FCNature is not null");
            return (Criteria) this;
        }

        public Criteria andFcnatureEqualTo(Long value) {
            addCriterion("FCNature =", value, "fcnature");
            return (Criteria) this;
        }

        public Criteria andFcnatureNotEqualTo(Long value) {
            addCriterion("FCNature <>", value, "fcnature");
            return (Criteria) this;
        }

        public Criteria andFcnatureGreaterThan(Long value) {
            addCriterion("FCNature >", value, "fcnature");
            return (Criteria) this;
        }

        public Criteria andFcnatureGreaterThanOrEqualTo(Long value) {
            addCriterion("FCNature >=", value, "fcnature");
            return (Criteria) this;
        }

        public Criteria andFcnatureLessThan(Long value) {
            addCriterion("FCNature <", value, "fcnature");
            return (Criteria) this;
        }

        public Criteria andFcnatureLessThanOrEqualTo(Long value) {
            addCriterion("FCNature <=", value, "fcnature");
            return (Criteria) this;
        }

        public Criteria andFcnatureIn(List<Long> values) {
            addCriterion("FCNature in", values, "fcnature");
            return (Criteria) this;
        }

        public Criteria andFcnatureNotIn(List<Long> values) {
            addCriterion("FCNature not in", values, "fcnature");
            return (Criteria) this;
        }

        public Criteria andFcnatureBetween(Long value1, Long value2) {
            addCriterion("FCNature between", value1, value2, "fcnature");
            return (Criteria) this;
        }

        public Criteria andFcnatureNotBetween(Long value1, Long value2) {
            addCriterion("FCNature not between", value1, value2, "fcnature");
            return (Criteria) this;
        }

        public Criteria andFweeklystudyhoursIsNull() {
            addCriterion("FWeeklyStudyHours is null");
            return (Criteria) this;
        }

        public Criteria andFweeklystudyhoursIsNotNull() {
            addCriterion("FWeeklyStudyHours is not null");
            return (Criteria) this;
        }

        public Criteria andFweeklystudyhoursEqualTo(String value) {
            addCriterion("FWeeklyStudyHours =", value, "fweeklystudyhours");
            return (Criteria) this;
        }

        public Criteria andFweeklystudyhoursNotEqualTo(String value) {
            addCriterion("FWeeklyStudyHours <>", value, "fweeklystudyhours");
            return (Criteria) this;
        }

        public Criteria andFweeklystudyhoursGreaterThan(String value) {
            addCriterion("FWeeklyStudyHours >", value, "fweeklystudyhours");
            return (Criteria) this;
        }

        public Criteria andFweeklystudyhoursGreaterThanOrEqualTo(String value) {
            addCriterion("FWeeklyStudyHours >=", value, "fweeklystudyhours");
            return (Criteria) this;
        }

        public Criteria andFweeklystudyhoursLessThan(String value) {
            addCriterion("FWeeklyStudyHours <", value, "fweeklystudyhours");
            return (Criteria) this;
        }

        public Criteria andFweeklystudyhoursLessThanOrEqualTo(String value) {
            addCriterion("FWeeklyStudyHours <=", value, "fweeklystudyhours");
            return (Criteria) this;
        }

        public Criteria andFweeklystudyhoursLike(String value) {
            addCriterion("FWeeklyStudyHours like", value, "fweeklystudyhours");
            return (Criteria) this;
        }

        public Criteria andFweeklystudyhoursNotLike(String value) {
            addCriterion("FWeeklyStudyHours not like", value, "fweeklystudyhours");
            return (Criteria) this;
        }

        public Criteria andFweeklystudyhoursIn(List<String> values) {
            addCriterion("FWeeklyStudyHours in", values, "fweeklystudyhours");
            return (Criteria) this;
        }

        public Criteria andFweeklystudyhoursNotIn(List<String> values) {
            addCriterion("FWeeklyStudyHours not in", values, "fweeklystudyhours");
            return (Criteria) this;
        }

        public Criteria andFweeklystudyhoursBetween(String value1, String value2) {
            addCriterion("FWeeklyStudyHours between", value1, value2, "fweeklystudyhours");
            return (Criteria) this;
        }

        public Criteria andFweeklystudyhoursNotBetween(String value1, String value2) {
            addCriterion("FWeeklyStudyHours not between", value1, value2, "fweeklystudyhours");
            return (Criteria) this;
        }

        public Criteria andFtheoreticalstudyhoursIsNull() {
            addCriterion("FTheoreticalStudyHours is null");
            return (Criteria) this;
        }

        public Criteria andFtheoreticalstudyhoursIsNotNull() {
            addCriterion("FTheoreticalStudyHours is not null");
            return (Criteria) this;
        }

        public Criteria andFtheoreticalstudyhoursEqualTo(String value) {
            addCriterion("FTheoreticalStudyHours =", value, "ftheoreticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFtheoreticalstudyhoursNotEqualTo(String value) {
            addCriterion("FTheoreticalStudyHours <>", value, "ftheoreticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFtheoreticalstudyhoursGreaterThan(String value) {
            addCriterion("FTheoreticalStudyHours >", value, "ftheoreticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFtheoreticalstudyhoursGreaterThanOrEqualTo(String value) {
            addCriterion("FTheoreticalStudyHours >=", value, "ftheoreticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFtheoreticalstudyhoursLessThan(String value) {
            addCriterion("FTheoreticalStudyHours <", value, "ftheoreticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFtheoreticalstudyhoursLessThanOrEqualTo(String value) {
            addCriterion("FTheoreticalStudyHours <=", value, "ftheoreticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFtheoreticalstudyhoursLike(String value) {
            addCriterion("FTheoreticalStudyHours like", value, "ftheoreticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFtheoreticalstudyhoursNotLike(String value) {
            addCriterion("FTheoreticalStudyHours not like", value, "ftheoreticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFtheoreticalstudyhoursIn(List<String> values) {
            addCriterion("FTheoreticalStudyHours in", values, "ftheoreticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFtheoreticalstudyhoursNotIn(List<String> values) {
            addCriterion("FTheoreticalStudyHours not in", values, "ftheoreticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFtheoreticalstudyhoursBetween(String value1, String value2) {
            addCriterion("FTheoreticalStudyHours between", value1, value2, "ftheoreticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFtheoreticalstudyhoursNotBetween(String value1, String value2) {
            addCriterion("FTheoreticalStudyHours not between", value1, value2, "ftheoreticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFpracticalstudyhoursIsNull() {
            addCriterion("FPracticalStudyHours is null");
            return (Criteria) this;
        }

        public Criteria andFpracticalstudyhoursIsNotNull() {
            addCriterion("FPracticalStudyHours is not null");
            return (Criteria) this;
        }

        public Criteria andFpracticalstudyhoursEqualTo(String value) {
            addCriterion("FPracticalStudyHours =", value, "fpracticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFpracticalstudyhoursNotEqualTo(String value) {
            addCriterion("FPracticalStudyHours <>", value, "fpracticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFpracticalstudyhoursGreaterThan(String value) {
            addCriterion("FPracticalStudyHours >", value, "fpracticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFpracticalstudyhoursGreaterThanOrEqualTo(String value) {
            addCriterion("FPracticalStudyHours >=", value, "fpracticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFpracticalstudyhoursLessThan(String value) {
            addCriterion("FPracticalStudyHours <", value, "fpracticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFpracticalstudyhoursLessThanOrEqualTo(String value) {
            addCriterion("FPracticalStudyHours <=", value, "fpracticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFpracticalstudyhoursLike(String value) {
            addCriterion("FPracticalStudyHours like", value, "fpracticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFpracticalstudyhoursNotLike(String value) {
            addCriterion("FPracticalStudyHours not like", value, "fpracticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFpracticalstudyhoursIn(List<String> values) {
            addCriterion("FPracticalStudyHours in", values, "fpracticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFpracticalstudyhoursNotIn(List<String> values) {
            addCriterion("FPracticalStudyHours not in", values, "fpracticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFpracticalstudyhoursBetween(String value1, String value2) {
            addCriterion("FPracticalStudyHours between", value1, value2, "fpracticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFpracticalstudyhoursNotBetween(String value1, String value2) {
            addCriterion("FPracticalStudyHours not between", value1, value2, "fpracticalstudyhours");
            return (Criteria) this;
        }

        public Criteria andFkkqjIsNull() {
            addCriterion("FKKQJ is null");
            return (Criteria) this;
        }

        public Criteria andFkkqjIsNotNull() {
            addCriterion("FKKQJ is not null");
            return (Criteria) this;
        }

        public Criteria andFkkqjEqualTo(Integer value) {
            addCriterion("FKKQJ =", value, "fkkqj");
            return (Criteria) this;
        }

        public Criteria andFkkqjNotEqualTo(Integer value) {
            addCriterion("FKKQJ <>", value, "fkkqj");
            return (Criteria) this;
        }

        public Criteria andFkkqjGreaterThan(Integer value) {
            addCriterion("FKKQJ >", value, "fkkqj");
            return (Criteria) this;
        }

        public Criteria andFkkqjGreaterThanOrEqualTo(Integer value) {
            addCriterion("FKKQJ >=", value, "fkkqj");
            return (Criteria) this;
        }

        public Criteria andFkkqjLessThan(Integer value) {
            addCriterion("FKKQJ <", value, "fkkqj");
            return (Criteria) this;
        }

        public Criteria andFkkqjLessThanOrEqualTo(Integer value) {
            addCriterion("FKKQJ <=", value, "fkkqj");
            return (Criteria) this;
        }

        public Criteria andFkkqjIn(List<Integer> values) {
            addCriterion("FKKQJ in", values, "fkkqj");
            return (Criteria) this;
        }

        public Criteria andFkkqjNotIn(List<Integer> values) {
            addCriterion("FKKQJ not in", values, "fkkqj");
            return (Criteria) this;
        }

        public Criteria andFkkqjBetween(Integer value1, Integer value2) {
            addCriterion("FKKQJ between", value1, value2, "fkkqj");
            return (Criteria) this;
        }

        public Criteria andFkkqjNotBetween(Integer value1, Integer value2) {
            addCriterion("FKKQJ not between", value1, value2, "fkkqj");
            return (Criteria) this;
        }

        public Criteria andFkkcjIsNull() {
            addCriterion("FKKCJ is null");
            return (Criteria) this;
        }

        public Criteria andFkkcjIsNotNull() {
            addCriterion("FKKCJ is not null");
            return (Criteria) this;
        }

        public Criteria andFkkcjEqualTo(Integer value) {
            addCriterion("FKKCJ =", value, "fkkcj");
            return (Criteria) this;
        }

        public Criteria andFkkcjNotEqualTo(Integer value) {
            addCriterion("FKKCJ <>", value, "fkkcj");
            return (Criteria) this;
        }

        public Criteria andFkkcjGreaterThan(Integer value) {
            addCriterion("FKKCJ >", value, "fkkcj");
            return (Criteria) this;
        }

        public Criteria andFkkcjGreaterThanOrEqualTo(Integer value) {
            addCriterion("FKKCJ >=", value, "fkkcj");
            return (Criteria) this;
        }

        public Criteria andFkkcjLessThan(Integer value) {
            addCriterion("FKKCJ <", value, "fkkcj");
            return (Criteria) this;
        }

        public Criteria andFkkcjLessThanOrEqualTo(Integer value) {
            addCriterion("FKKCJ <=", value, "fkkcj");
            return (Criteria) this;
        }

        public Criteria andFkkcjIn(List<Integer> values) {
            addCriterion("FKKCJ in", values, "fkkcj");
            return (Criteria) this;
        }

        public Criteria andFkkcjNotIn(List<Integer> values) {
            addCriterion("FKKCJ not in", values, "fkkcj");
            return (Criteria) this;
        }

        public Criteria andFkkcjBetween(Integer value1, Integer value2) {
            addCriterion("FKKCJ between", value1, value2, "fkkcj");
            return (Criteria) this;
        }

        public Criteria andFkkcjNotBetween(Integer value1, Integer value2) {
            addCriterion("FKKCJ not between", value1, value2, "fkkcj");
            return (Criteria) this;
        }

        public Criteria andFdxqIsNull() {
            addCriterion("FDXQ is null");
            return (Criteria) this;
        }

        public Criteria andFdxqIsNotNull() {
            addCriterion("FDXQ is not null");
            return (Criteria) this;
        }

        public Criteria andFdxqEqualTo(Integer value) {
            addCriterion("FDXQ =", value, "fdxq");
            return (Criteria) this;
        }

        public Criteria andFdxqNotEqualTo(Integer value) {
            addCriterion("FDXQ <>", value, "fdxq");
            return (Criteria) this;
        }

        public Criteria andFdxqGreaterThan(Integer value) {
            addCriterion("FDXQ >", value, "fdxq");
            return (Criteria) this;
        }

        public Criteria andFdxqGreaterThanOrEqualTo(Integer value) {
            addCriterion("FDXQ >=", value, "fdxq");
            return (Criteria) this;
        }

        public Criteria andFdxqLessThan(Integer value) {
            addCriterion("FDXQ <", value, "fdxq");
            return (Criteria) this;
        }

        public Criteria andFdxqLessThanOrEqualTo(Integer value) {
            addCriterion("FDXQ <=", value, "fdxq");
            return (Criteria) this;
        }

        public Criteria andFdxqIn(List<Integer> values) {
            addCriterion("FDXQ in", values, "fdxq");
            return (Criteria) this;
        }

        public Criteria andFdxqNotIn(List<Integer> values) {
            addCriterion("FDXQ not in", values, "fdxq");
            return (Criteria) this;
        }

        public Criteria andFdxqBetween(Integer value1, Integer value2) {
            addCriterion("FDXQ between", value1, value2, "fdxq");
            return (Criteria) this;
        }

        public Criteria andFdxqNotBetween(Integer value1, Integer value2) {
            addCriterion("FDXQ not between", value1, value2, "fdxq");
            return (Criteria) this;
        }

        public Criteria andFjyxnxqIsNull() {
            addCriterion("FJYXNXQ is null");
            return (Criteria) this;
        }

        public Criteria andFjyxnxqIsNotNull() {
            addCriterion("FJYXNXQ is not null");
            return (Criteria) this;
        }

        public Criteria andFjyxnxqEqualTo(String value) {
            addCriterion("FJYXNXQ =", value, "fjyxnxq");
            return (Criteria) this;
        }

        public Criteria andFjyxnxqNotEqualTo(String value) {
            addCriterion("FJYXNXQ <>", value, "fjyxnxq");
            return (Criteria) this;
        }

        public Criteria andFjyxnxqGreaterThan(String value) {
            addCriterion("FJYXNXQ >", value, "fjyxnxq");
            return (Criteria) this;
        }

        public Criteria andFjyxnxqGreaterThanOrEqualTo(String value) {
            addCriterion("FJYXNXQ >=", value, "fjyxnxq");
            return (Criteria) this;
        }

        public Criteria andFjyxnxqLessThan(String value) {
            addCriterion("FJYXNXQ <", value, "fjyxnxq");
            return (Criteria) this;
        }

        public Criteria andFjyxnxqLessThanOrEqualTo(String value) {
            addCriterion("FJYXNXQ <=", value, "fjyxnxq");
            return (Criteria) this;
        }

        public Criteria andFjyxnxqLike(String value) {
            addCriterion("FJYXNXQ like", value, "fjyxnxq");
            return (Criteria) this;
        }

        public Criteria andFjyxnxqNotLike(String value) {
            addCriterion("FJYXNXQ not like", value, "fjyxnxq");
            return (Criteria) this;
        }

        public Criteria andFjyxnxqIn(List<String> values) {
            addCriterion("FJYXNXQ in", values, "fjyxnxq");
            return (Criteria) this;
        }

        public Criteria andFjyxnxqNotIn(List<String> values) {
            addCriterion("FJYXNXQ not in", values, "fjyxnxq");
            return (Criteria) this;
        }

        public Criteria andFjyxnxqBetween(String value1, String value2) {
            addCriterion("FJYXNXQ between", value1, value2, "fjyxnxq");
            return (Criteria) this;
        }

        public Criteria andFjyxnxqNotBetween(String value1, String value2) {
            addCriterion("FJYXNXQ not between", value1, value2, "fjyxnxq");
            return (Criteria) this;
        }

        public Criteria andFywnameIsNull() {
            addCriterion("FYWName is null");
            return (Criteria) this;
        }

        public Criteria andFywnameIsNotNull() {
            addCriterion("FYWName is not null");
            return (Criteria) this;
        }

        public Criteria andFywnameEqualTo(String value) {
            addCriterion("FYWName =", value, "fywname");
            return (Criteria) this;
        }

        public Criteria andFywnameNotEqualTo(String value) {
            addCriterion("FYWName <>", value, "fywname");
            return (Criteria) this;
        }

        public Criteria andFywnameGreaterThan(String value) {
            addCriterion("FYWName >", value, "fywname");
            return (Criteria) this;
        }

        public Criteria andFywnameGreaterThanOrEqualTo(String value) {
            addCriterion("FYWName >=", value, "fywname");
            return (Criteria) this;
        }

        public Criteria andFywnameLessThan(String value) {
            addCriterion("FYWName <", value, "fywname");
            return (Criteria) this;
        }

        public Criteria andFywnameLessThanOrEqualTo(String value) {
            addCriterion("FYWName <=", value, "fywname");
            return (Criteria) this;
        }

        public Criteria andFywnameLike(String value) {
            addCriterion("FYWName like", value, "fywname");
            return (Criteria) this;
        }

        public Criteria andFywnameNotLike(String value) {
            addCriterion("FYWName not like", value, "fywname");
            return (Criteria) this;
        }

        public Criteria andFywnameIn(List<String> values) {
            addCriterion("FYWName in", values, "fywname");
            return (Criteria) this;
        }

        public Criteria andFywnameNotIn(List<String> values) {
            addCriterion("FYWName not in", values, "fywname");
            return (Criteria) this;
        }

        public Criteria andFywnameBetween(String value1, String value2) {
            addCriterion("FYWName between", value1, value2, "fywname");
            return (Criteria) this;
        }

        public Criteria andFywnameNotBetween(String value1, String value2) {
            addCriterion("FYWName not between", value1, value2, "fywname");
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

        public Criteria andFsjzsIsNull() {
            addCriterion("FSJZS is null");
            return (Criteria) this;
        }

        public Criteria andFsjzsIsNotNull() {
            addCriterion("FSJZS is not null");
            return (Criteria) this;
        }

        public Criteria andFsjzsEqualTo(String value) {
            addCriterion("FSJZS =", value, "fsjzs");
            return (Criteria) this;
        }

        public Criteria andFsjzsNotEqualTo(String value) {
            addCriterion("FSJZS <>", value, "fsjzs");
            return (Criteria) this;
        }

        public Criteria andFsjzsGreaterThan(String value) {
            addCriterion("FSJZS >", value, "fsjzs");
            return (Criteria) this;
        }

        public Criteria andFsjzsGreaterThanOrEqualTo(String value) {
            addCriterion("FSJZS >=", value, "fsjzs");
            return (Criteria) this;
        }

        public Criteria andFsjzsLessThan(String value) {
            addCriterion("FSJZS <", value, "fsjzs");
            return (Criteria) this;
        }

        public Criteria andFsjzsLessThanOrEqualTo(String value) {
            addCriterion("FSJZS <=", value, "fsjzs");
            return (Criteria) this;
        }

        public Criteria andFsjzsLike(String value) {
            addCriterion("FSJZS like", value, "fsjzs");
            return (Criteria) this;
        }

        public Criteria andFsjzsNotLike(String value) {
            addCriterion("FSJZS not like", value, "fsjzs");
            return (Criteria) this;
        }

        public Criteria andFsjzsIn(List<String> values) {
            addCriterion("FSJZS in", values, "fsjzs");
            return (Criteria) this;
        }

        public Criteria andFsjzsNotIn(List<String> values) {
            addCriterion("FSJZS not in", values, "fsjzs");
            return (Criteria) this;
        }

        public Criteria andFsjzsBetween(String value1, String value2) {
            addCriterion("FSJZS between", value1, value2, "fsjzs");
            return (Criteria) this;
        }

        public Criteria andFsjzsNotBetween(String value1, String value2) {
            addCriterion("FSJZS not between", value1, value2, "fsjzs");
            return (Criteria) this;
        }

        public Criteria andFistkIsNull() {
            addCriterion("FIstk is null");
            return (Criteria) this;
        }

        public Criteria andFistkIsNotNull() {
            addCriterion("FIstk is not null");
            return (Criteria) this;
        }

        public Criteria andFistkEqualTo(Integer value) {
            addCriterion("FIstk =", value, "fistk");
            return (Criteria) this;
        }

        public Criteria andFistkNotEqualTo(Integer value) {
            addCriterion("FIstk <>", value, "fistk");
            return (Criteria) this;
        }

        public Criteria andFistkGreaterThan(Integer value) {
            addCriterion("FIstk >", value, "fistk");
            return (Criteria) this;
        }

        public Criteria andFistkGreaterThanOrEqualTo(Integer value) {
            addCriterion("FIstk >=", value, "fistk");
            return (Criteria) this;
        }

        public Criteria andFistkLessThan(Integer value) {
            addCriterion("FIstk <", value, "fistk");
            return (Criteria) this;
        }

        public Criteria andFistkLessThanOrEqualTo(Integer value) {
            addCriterion("FIstk <=", value, "fistk");
            return (Criteria) this;
        }

        public Criteria andFistkIn(List<Integer> values) {
            addCriterion("FIstk in", values, "fistk");
            return (Criteria) this;
        }

        public Criteria andFistkNotIn(List<Integer> values) {
            addCriterion("FIstk not in", values, "fistk");
            return (Criteria) this;
        }

        public Criteria andFistkBetween(Integer value1, Integer value2) {
            addCriterion("FIstk between", value1, value2, "fistk");
            return (Criteria) this;
        }

        public Criteria andFistkNotBetween(Integer value1, Integer value2) {
            addCriterion("FIstk not between", value1, value2, "fistk");
            return (Criteria) this;
        }

        public Criteria andFparentidIsNull() {
            addCriterion("FParentId is null");
            return (Criteria) this;
        }

        public Criteria andFparentidIsNotNull() {
            addCriterion("FParentId is not null");
            return (Criteria) this;
        }

        public Criteria andFparentidEqualTo(Long value) {
            addCriterion("FParentId =", value, "fparentid");
            return (Criteria) this;
        }

        public Criteria andFparentidNotEqualTo(Long value) {
            addCriterion("FParentId <>", value, "fparentid");
            return (Criteria) this;
        }

        public Criteria andFparentidGreaterThan(Long value) {
            addCriterion("FParentId >", value, "fparentid");
            return (Criteria) this;
        }

        public Criteria andFparentidGreaterThanOrEqualTo(Long value) {
            addCriterion("FParentId >=", value, "fparentid");
            return (Criteria) this;
        }

        public Criteria andFparentidLessThan(Long value) {
            addCriterion("FParentId <", value, "fparentid");
            return (Criteria) this;
        }

        public Criteria andFparentidLessThanOrEqualTo(Long value) {
            addCriterion("FParentId <=", value, "fparentid");
            return (Criteria) this;
        }

        public Criteria andFparentidIn(List<Long> values) {
            addCriterion("FParentId in", values, "fparentid");
            return (Criteria) this;
        }

        public Criteria andFparentidNotIn(List<Long> values) {
            addCriterion("FParentId not in", values, "fparentid");
            return (Criteria) this;
        }

        public Criteria andFparentidBetween(Long value1, Long value2) {
            addCriterion("FParentId between", value1, value2, "fparentid");
            return (Criteria) this;
        }

        public Criteria andFparentidNotBetween(Long value1, Long value2) {
            addCriterion("FParentId not between", value1, value2, "fparentid");
            return (Criteria) this;
        }

        public Criteria andFjyxqIsNull() {
            addCriterion("FJYXQ is null");
            return (Criteria) this;
        }

        public Criteria andFjyxqIsNotNull() {
            addCriterion("FJYXQ is not null");
            return (Criteria) this;
        }

        public Criteria andFjyxqEqualTo(Integer value) {
            addCriterion("FJYXQ =", value, "fjyxq");
            return (Criteria) this;
        }

        public Criteria andFjyxqNotEqualTo(Integer value) {
            addCriterion("FJYXQ <>", value, "fjyxq");
            return (Criteria) this;
        }

        public Criteria andFjyxqGreaterThan(Integer value) {
            addCriterion("FJYXQ >", value, "fjyxq");
            return (Criteria) this;
        }

        public Criteria andFjyxqGreaterThanOrEqualTo(Integer value) {
            addCriterion("FJYXQ >=", value, "fjyxq");
            return (Criteria) this;
        }

        public Criteria andFjyxqLessThan(Integer value) {
            addCriterion("FJYXQ <", value, "fjyxq");
            return (Criteria) this;
        }

        public Criteria andFjyxqLessThanOrEqualTo(Integer value) {
            addCriterion("FJYXQ <=", value, "fjyxq");
            return (Criteria) this;
        }

        public Criteria andFjyxqIn(List<Integer> values) {
            addCriterion("FJYXQ in", values, "fjyxq");
            return (Criteria) this;
        }

        public Criteria andFjyxqNotIn(List<Integer> values) {
            addCriterion("FJYXQ not in", values, "fjyxq");
            return (Criteria) this;
        }

        public Criteria andFjyxqBetween(Integer value1, Integer value2) {
            addCriterion("FJYXQ between", value1, value2, "fjyxq");
            return (Criteria) this;
        }

        public Criteria andFjyxqNotBetween(Integer value1, Integer value2) {
            addCriterion("FJYXQ not between", value1, value2, "fjyxq");
            return (Criteria) this;
        }

        public Criteria andFjczykIsNull() {
            addCriterion("FJCZYK is null");
            return (Criteria) this;
        }

        public Criteria andFjczykIsNotNull() {
            addCriterion("FJCZYK is not null");
            return (Criteria) this;
        }

        public Criteria andFjczykEqualTo(Integer value) {
            addCriterion("FJCZYK =", value, "fjczyk");
            return (Criteria) this;
        }

        public Criteria andFjczykNotEqualTo(Integer value) {
            addCriterion("FJCZYK <>", value, "fjczyk");
            return (Criteria) this;
        }

        public Criteria andFjczykGreaterThan(Integer value) {
            addCriterion("FJCZYK >", value, "fjczyk");
            return (Criteria) this;
        }

        public Criteria andFjczykGreaterThanOrEqualTo(Integer value) {
            addCriterion("FJCZYK >=", value, "fjczyk");
            return (Criteria) this;
        }

        public Criteria andFjczykLessThan(Integer value) {
            addCriterion("FJCZYK <", value, "fjczyk");
            return (Criteria) this;
        }

        public Criteria andFjczykLessThanOrEqualTo(Integer value) {
            addCriterion("FJCZYK <=", value, "fjczyk");
            return (Criteria) this;
        }

        public Criteria andFjczykIn(List<Integer> values) {
            addCriterion("FJCZYK in", values, "fjczyk");
            return (Criteria) this;
        }

        public Criteria andFjczykNotIn(List<Integer> values) {
            addCriterion("FJCZYK not in", values, "fjczyk");
            return (Criteria) this;
        }

        public Criteria andFjczykBetween(Integer value1, Integer value2) {
            addCriterion("FJCZYK between", value1, value2, "fjczyk");
            return (Criteria) this;
        }

        public Criteria andFjczykNotBetween(Integer value1, Integer value2) {
            addCriterion("FJCZYK not between", value1, value2, "fjczyk");
            return (Criteria) this;
        }

        public Criteria andFkkdwIsNull() {
            addCriterion("FKKDW is null");
            return (Criteria) this;
        }

        public Criteria andFkkdwIsNotNull() {
            addCriterion("FKKDW is not null");
            return (Criteria) this;
        }

        public Criteria andFkkdwEqualTo(Long value) {
            addCriterion("FKKDW =", value, "fkkdw");
            return (Criteria) this;
        }

        public Criteria andFkkdwNotEqualTo(Long value) {
            addCriterion("FKKDW <>", value, "fkkdw");
            return (Criteria) this;
        }

        public Criteria andFkkdwGreaterThan(Long value) {
            addCriterion("FKKDW >", value, "fkkdw");
            return (Criteria) this;
        }

        public Criteria andFkkdwGreaterThanOrEqualTo(Long value) {
            addCriterion("FKKDW >=", value, "fkkdw");
            return (Criteria) this;
        }

        public Criteria andFkkdwLessThan(Long value) {
            addCriterion("FKKDW <", value, "fkkdw");
            return (Criteria) this;
        }

        public Criteria andFkkdwLessThanOrEqualTo(Long value) {
            addCriterion("FKKDW <=", value, "fkkdw");
            return (Criteria) this;
        }

        public Criteria andFkkdwIn(List<Long> values) {
            addCriterion("FKKDW in", values, "fkkdw");
            return (Criteria) this;
        }

        public Criteria andFkkdwNotIn(List<Long> values) {
            addCriterion("FKKDW not in", values, "fkkdw");
            return (Criteria) this;
        }

        public Criteria andFkkdwBetween(Long value1, Long value2) {
            addCriterion("FKKDW between", value1, value2, "fkkdw");
            return (Criteria) this;
        }

        public Criteria andFkkdwNotBetween(Long value1, Long value2) {
            addCriterion("FKKDW not between", value1, value2, "fkkdw");
            return (Criteria) this;
        }
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：teaching_diversity..t_course
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * TCourse
     * Criterion
     * 数据库表：teaching_diversity..t_course
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