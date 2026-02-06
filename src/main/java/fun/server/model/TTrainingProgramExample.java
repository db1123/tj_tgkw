package fun.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TTrainingProgramExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_training_program
     * @mbg.generated
     */
    public TTrainingProgramExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * 设置排序字段
     * student_capability_evaluation..t_training_program
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * student_capability_evaluation..t_training_program
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * student_capability_evaluation..t_training_program
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * student_capability_evaluation..t_training_program
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * student_capability_evaluation..t_training_program
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * student_capability_evaluation..t_training_program
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * student_capability_evaluation..t_training_program
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
     * student_capability_evaluation..t_training_program
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
     * student_capability_evaluation..t_training_program
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * student_capability_evaluation..t_training_program
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TTrainingProgram
     * GeneratedCriteria
     * 数据库表：student_capability_evaluation..t_training_program
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

        public Criteria andFtmidIsNull() {
            addCriterion("FTMID is null");
            return (Criteria) this;
        }

        public Criteria andFtmidIsNotNull() {
            addCriterion("FTMID is not null");
            return (Criteria) this;
        }

        public Criteria andFtmidEqualTo(Long value) {
            addCriterion("FTMID =", value, "ftmid");
            return (Criteria) this;
        }

        public Criteria andFtmidNotEqualTo(Long value) {
            addCriterion("FTMID <>", value, "ftmid");
            return (Criteria) this;
        }

        public Criteria andFtmidGreaterThan(Long value) {
            addCriterion("FTMID >", value, "ftmid");
            return (Criteria) this;
        }

        public Criteria andFtmidGreaterThanOrEqualTo(Long value) {
            addCriterion("FTMID >=", value, "ftmid");
            return (Criteria) this;
        }

        public Criteria andFtmidLessThan(Long value) {
            addCriterion("FTMID <", value, "ftmid");
            return (Criteria) this;
        }

        public Criteria andFtmidLessThanOrEqualTo(Long value) {
            addCriterion("FTMID <=", value, "ftmid");
            return (Criteria) this;
        }

        public Criteria andFtmidIn(List<Long> values) {
            addCriterion("FTMID in", values, "ftmid");
            return (Criteria) this;
        }

        public Criteria andFtmidNotIn(List<Long> values) {
            addCriterion("FTMID not in", values, "ftmid");
            return (Criteria) this;
        }

        public Criteria andFtmidBetween(Long value1, Long value2) {
            addCriterion("FTMID between", value1, value2, "ftmid");
            return (Criteria) this;
        }

        public Criteria andFtmidNotBetween(Long value1, Long value2) {
            addCriterion("FTMID not between", value1, value2, "ftmid");
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

        public Criteria andFjsIsNull() {
            addCriterion("FJS is null");
            return (Criteria) this;
        }

        public Criteria andFjsIsNotNull() {
            addCriterion("FJS is not null");
            return (Criteria) this;
        }

        public Criteria andFjsEqualTo(String value) {
            addCriterion("FJS =", value, "fjs");
            return (Criteria) this;
        }

        public Criteria andFjsNotEqualTo(String value) {
            addCriterion("FJS <>", value, "fjs");
            return (Criteria) this;
        }

        public Criteria andFjsGreaterThan(String value) {
            addCriterion("FJS >", value, "fjs");
            return (Criteria) this;
        }

        public Criteria andFjsGreaterThanOrEqualTo(String value) {
            addCriterion("FJS >=", value, "fjs");
            return (Criteria) this;
        }

        public Criteria andFjsLessThan(String value) {
            addCriterion("FJS <", value, "fjs");
            return (Criteria) this;
        }

        public Criteria andFjsLessThanOrEqualTo(String value) {
            addCriterion("FJS <=", value, "fjs");
            return (Criteria) this;
        }

        public Criteria andFjsLike(String value) {
            addCriterion("FJS like", value, "fjs");
            return (Criteria) this;
        }

        public Criteria andFjsNotLike(String value) {
            addCriterion("FJS not like", value, "fjs");
            return (Criteria) this;
        }

        public Criteria andFjsIn(List<String> values) {
            addCriterion("FJS in", values, "fjs");
            return (Criteria) this;
        }

        public Criteria andFjsNotIn(List<String> values) {
            addCriterion("FJS not in", values, "fjs");
            return (Criteria) this;
        }

        public Criteria andFjsBetween(String value1, String value2) {
            addCriterion("FJS between", value1, value2, "fjs");
            return (Criteria) this;
        }

        public Criteria andFjsNotBetween(String value1, String value2) {
            addCriterion("FJS not between", value1, value2, "fjs");
            return (Criteria) this;
        }

        public Criteria andFzyjsIsNull() {
            addCriterion("FZYJS is null");
            return (Criteria) this;
        }

        public Criteria andFzyjsIsNotNull() {
            addCriterion("FZYJS is not null");
            return (Criteria) this;
        }

        public Criteria andFzyjsEqualTo(String value) {
            addCriterion("FZYJS =", value, "fzyjs");
            return (Criteria) this;
        }

        public Criteria andFzyjsNotEqualTo(String value) {
            addCriterion("FZYJS <>", value, "fzyjs");
            return (Criteria) this;
        }

        public Criteria andFzyjsGreaterThan(String value) {
            addCriterion("FZYJS >", value, "fzyjs");
            return (Criteria) this;
        }

        public Criteria andFzyjsGreaterThanOrEqualTo(String value) {
            addCriterion("FZYJS >=", value, "fzyjs");
            return (Criteria) this;
        }

        public Criteria andFzyjsLessThan(String value) {
            addCriterion("FZYJS <", value, "fzyjs");
            return (Criteria) this;
        }

        public Criteria andFzyjsLessThanOrEqualTo(String value) {
            addCriterion("FZYJS <=", value, "fzyjs");
            return (Criteria) this;
        }

        public Criteria andFzyjsLike(String value) {
            addCriterion("FZYJS like", value, "fzyjs");
            return (Criteria) this;
        }

        public Criteria andFzyjsNotLike(String value) {
            addCriterion("FZYJS not like", value, "fzyjs");
            return (Criteria) this;
        }

        public Criteria andFzyjsIn(List<String> values) {
            addCriterion("FZYJS in", values, "fzyjs");
            return (Criteria) this;
        }

        public Criteria andFzyjsNotIn(List<String> values) {
            addCriterion("FZYJS not in", values, "fzyjs");
            return (Criteria) this;
        }

        public Criteria andFzyjsBetween(String value1, String value2) {
            addCriterion("FZYJS between", value1, value2, "fzyjs");
            return (Criteria) this;
        }

        public Criteria andFzyjsNotBetween(String value1, String value2) {
            addCriterion("FZYJS not between", value1, value2, "fzyjs");
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
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：student_capability_evaluation..t_training_program
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * TTrainingProgram
     * Criterion
     * 数据库表：student_capability_evaluation..t_training_program
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