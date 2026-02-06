package fun.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TStudentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_student
     * @mbg.generated
     */
    public TStudentExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * 设置排序字段
     * student_capability_evaluation..t_student
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * student_capability_evaluation..t_student
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * student_capability_evaluation..t_student
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * student_capability_evaluation..t_student
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * student_capability_evaluation..t_student
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * student_capability_evaluation..t_student
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * student_capability_evaluation..t_student
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
     * student_capability_evaluation..t_student
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
     * student_capability_evaluation..t_student
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * student_capability_evaluation..t_student
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TStudent
     * GeneratedCriteria
     * 数据库表：student_capability_evaluation..t_student
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

        public Criteria andFsexIsNull() {
            addCriterion("FSex is null");
            return (Criteria) this;
        }

        public Criteria andFsexIsNotNull() {
            addCriterion("FSex is not null");
            return (Criteria) this;
        }

        public Criteria andFsexEqualTo(Integer value) {
            addCriterion("FSex =", value, "fsex");
            return (Criteria) this;
        }

        public Criteria andFsexNotEqualTo(Integer value) {
            addCriterion("FSex <>", value, "fsex");
            return (Criteria) this;
        }

        public Criteria andFsexGreaterThan(Integer value) {
            addCriterion("FSex >", value, "fsex");
            return (Criteria) this;
        }

        public Criteria andFsexGreaterThanOrEqualTo(Integer value) {
            addCriterion("FSex >=", value, "fsex");
            return (Criteria) this;
        }

        public Criteria andFsexLessThan(Integer value) {
            addCriterion("FSex <", value, "fsex");
            return (Criteria) this;
        }

        public Criteria andFsexLessThanOrEqualTo(Integer value) {
            addCriterion("FSex <=", value, "fsex");
            return (Criteria) this;
        }

        public Criteria andFsexIn(List<Integer> values) {
            addCriterion("FSex in", values, "fsex");
            return (Criteria) this;
        }

        public Criteria andFsexNotIn(List<Integer> values) {
            addCriterion("FSex not in", values, "fsex");
            return (Criteria) this;
        }

        public Criteria andFsexBetween(Integer value1, Integer value2) {
            addCriterion("FSex between", value1, value2, "fsex");
            return (Criteria) this;
        }

        public Criteria andFsexNotBetween(Integer value1, Integer value2) {
            addCriterion("FSex not between", value1, value2, "fsex");
            return (Criteria) this;
        }

        public Criteria andFbirthdayIsNull() {
            addCriterion("FBirthday is null");
            return (Criteria) this;
        }

        public Criteria andFbirthdayIsNotNull() {
            addCriterion("FBirthday is not null");
            return (Criteria) this;
        }

        public Criteria andFbirthdayEqualTo(String value) {
            addCriterion("FBirthday =", value, "fbirthday");
            return (Criteria) this;
        }

        public Criteria andFbirthdayNotEqualTo(String value) {
            addCriterion("FBirthday <>", value, "fbirthday");
            return (Criteria) this;
        }

        public Criteria andFbirthdayGreaterThan(String value) {
            addCriterion("FBirthday >", value, "fbirthday");
            return (Criteria) this;
        }

        public Criteria andFbirthdayGreaterThanOrEqualTo(String value) {
            addCriterion("FBirthday >=", value, "fbirthday");
            return (Criteria) this;
        }

        public Criteria andFbirthdayLessThan(String value) {
            addCriterion("FBirthday <", value, "fbirthday");
            return (Criteria) this;
        }

        public Criteria andFbirthdayLessThanOrEqualTo(String value) {
            addCriterion("FBirthday <=", value, "fbirthday");
            return (Criteria) this;
        }

        public Criteria andFbirthdayLike(String value) {
            addCriterion("FBirthday like", value, "fbirthday");
            return (Criteria) this;
        }

        public Criteria andFbirthdayNotLike(String value) {
            addCriterion("FBirthday not like", value, "fbirthday");
            return (Criteria) this;
        }

        public Criteria andFbirthdayIn(List<String> values) {
            addCriterion("FBirthday in", values, "fbirthday");
            return (Criteria) this;
        }

        public Criteria andFbirthdayNotIn(List<String> values) {
            addCriterion("FBirthday not in", values, "fbirthday");
            return (Criteria) this;
        }

        public Criteria andFbirthdayBetween(String value1, String value2) {
            addCriterion("FBirthday between", value1, value2, "fbirthday");
            return (Criteria) this;
        }

        public Criteria andFbirthdayNotBetween(String value1, String value2) {
            addCriterion("FBirthday not between", value1, value2, "fbirthday");
            return (Criteria) this;
        }

        public Criteria andFidnumberIsNull() {
            addCriterion("FIDNumber is null");
            return (Criteria) this;
        }

        public Criteria andFidnumberIsNotNull() {
            addCriterion("FIDNumber is not null");
            return (Criteria) this;
        }

        public Criteria andFidnumberEqualTo(String value) {
            addCriterion("FIDNumber =", value, "fidnumber");
            return (Criteria) this;
        }

        public Criteria andFidnumberNotEqualTo(String value) {
            addCriterion("FIDNumber <>", value, "fidnumber");
            return (Criteria) this;
        }

        public Criteria andFidnumberGreaterThan(String value) {
            addCriterion("FIDNumber >", value, "fidnumber");
            return (Criteria) this;
        }

        public Criteria andFidnumberGreaterThanOrEqualTo(String value) {
            addCriterion("FIDNumber >=", value, "fidnumber");
            return (Criteria) this;
        }

        public Criteria andFidnumberLessThan(String value) {
            addCriterion("FIDNumber <", value, "fidnumber");
            return (Criteria) this;
        }

        public Criteria andFidnumberLessThanOrEqualTo(String value) {
            addCriterion("FIDNumber <=", value, "fidnumber");
            return (Criteria) this;
        }

        public Criteria andFidnumberLike(String value) {
            addCriterion("FIDNumber like", value, "fidnumber");
            return (Criteria) this;
        }

        public Criteria andFidnumberNotLike(String value) {
            addCriterion("FIDNumber not like", value, "fidnumber");
            return (Criteria) this;
        }

        public Criteria andFidnumberIn(List<String> values) {
            addCriterion("FIDNumber in", values, "fidnumber");
            return (Criteria) this;
        }

        public Criteria andFidnumberNotIn(List<String> values) {
            addCriterion("FIDNumber not in", values, "fidnumber");
            return (Criteria) this;
        }

        public Criteria andFidnumberBetween(String value1, String value2) {
            addCriterion("FIDNumber between", value1, value2, "fidnumber");
            return (Criteria) this;
        }

        public Criteria andFidnumberNotBetween(String value1, String value2) {
            addCriterion("FIDNumber not between", value1, value2, "fidnumber");
            return (Criteria) this;
        }

        public Criteria andFtelIsNull() {
            addCriterion("FTel is null");
            return (Criteria) this;
        }

        public Criteria andFtelIsNotNull() {
            addCriterion("FTel is not null");
            return (Criteria) this;
        }

        public Criteria andFtelEqualTo(String value) {
            addCriterion("FTel =", value, "ftel");
            return (Criteria) this;
        }

        public Criteria andFtelNotEqualTo(String value) {
            addCriterion("FTel <>", value, "ftel");
            return (Criteria) this;
        }

        public Criteria andFtelGreaterThan(String value) {
            addCriterion("FTel >", value, "ftel");
            return (Criteria) this;
        }

        public Criteria andFtelGreaterThanOrEqualTo(String value) {
            addCriterion("FTel >=", value, "ftel");
            return (Criteria) this;
        }

        public Criteria andFtelLessThan(String value) {
            addCriterion("FTel <", value, "ftel");
            return (Criteria) this;
        }

        public Criteria andFtelLessThanOrEqualTo(String value) {
            addCriterion("FTel <=", value, "ftel");
            return (Criteria) this;
        }

        public Criteria andFtelLike(String value) {
            addCriterion("FTel like", value, "ftel");
            return (Criteria) this;
        }

        public Criteria andFtelNotLike(String value) {
            addCriterion("FTel not like", value, "ftel");
            return (Criteria) this;
        }

        public Criteria andFtelIn(List<String> values) {
            addCriterion("FTel in", values, "ftel");
            return (Criteria) this;
        }

        public Criteria andFtelNotIn(List<String> values) {
            addCriterion("FTel not in", values, "ftel");
            return (Criteria) this;
        }

        public Criteria andFtelBetween(String value1, String value2) {
            addCriterion("FTel between", value1, value2, "ftel");
            return (Criteria) this;
        }

        public Criteria andFtelNotBetween(String value1, String value2) {
            addCriterion("FTel not between", value1, value2, "ftel");
            return (Criteria) this;
        }

        public Criteria andFemailIsNull() {
            addCriterion("FEmail is null");
            return (Criteria) this;
        }

        public Criteria andFemailIsNotNull() {
            addCriterion("FEmail is not null");
            return (Criteria) this;
        }

        public Criteria andFemailEqualTo(String value) {
            addCriterion("FEmail =", value, "femail");
            return (Criteria) this;
        }

        public Criteria andFemailNotEqualTo(String value) {
            addCriterion("FEmail <>", value, "femail");
            return (Criteria) this;
        }

        public Criteria andFemailGreaterThan(String value) {
            addCriterion("FEmail >", value, "femail");
            return (Criteria) this;
        }

        public Criteria andFemailGreaterThanOrEqualTo(String value) {
            addCriterion("FEmail >=", value, "femail");
            return (Criteria) this;
        }

        public Criteria andFemailLessThan(String value) {
            addCriterion("FEmail <", value, "femail");
            return (Criteria) this;
        }

        public Criteria andFemailLessThanOrEqualTo(String value) {
            addCriterion("FEmail <=", value, "femail");
            return (Criteria) this;
        }

        public Criteria andFemailLike(String value) {
            addCriterion("FEmail like", value, "femail");
            return (Criteria) this;
        }

        public Criteria andFemailNotLike(String value) {
            addCriterion("FEmail not like", value, "femail");
            return (Criteria) this;
        }

        public Criteria andFemailIn(List<String> values) {
            addCriterion("FEmail in", values, "femail");
            return (Criteria) this;
        }

        public Criteria andFemailNotIn(List<String> values) {
            addCriterion("FEmail not in", values, "femail");
            return (Criteria) this;
        }

        public Criteria andFemailBetween(String value1, String value2) {
            addCriterion("FEmail between", value1, value2, "femail");
            return (Criteria) this;
        }

        public Criteria andFemailNotBetween(String value1, String value2) {
            addCriterion("FEmail not between", value1, value2, "femail");
            return (Criteria) this;
        }

        public Criteria andFstartschooldateIsNull() {
            addCriterion("FStartSchoolDate is null");
            return (Criteria) this;
        }

        public Criteria andFstartschooldateIsNotNull() {
            addCriterion("FStartSchoolDate is not null");
            return (Criteria) this;
        }

        public Criteria andFstartschooldateEqualTo(String value) {
            addCriterion("FStartSchoolDate =", value, "fstartschooldate");
            return (Criteria) this;
        }

        public Criteria andFstartschooldateNotEqualTo(String value) {
            addCriterion("FStartSchoolDate <>", value, "fstartschooldate");
            return (Criteria) this;
        }

        public Criteria andFstartschooldateGreaterThan(String value) {
            addCriterion("FStartSchoolDate >", value, "fstartschooldate");
            return (Criteria) this;
        }

        public Criteria andFstartschooldateGreaterThanOrEqualTo(String value) {
            addCriterion("FStartSchoolDate >=", value, "fstartschooldate");
            return (Criteria) this;
        }

        public Criteria andFstartschooldateLessThan(String value) {
            addCriterion("FStartSchoolDate <", value, "fstartschooldate");
            return (Criteria) this;
        }

        public Criteria andFstartschooldateLessThanOrEqualTo(String value) {
            addCriterion("FStartSchoolDate <=", value, "fstartschooldate");
            return (Criteria) this;
        }

        public Criteria andFstartschooldateLike(String value) {
            addCriterion("FStartSchoolDate like", value, "fstartschooldate");
            return (Criteria) this;
        }

        public Criteria andFstartschooldateNotLike(String value) {
            addCriterion("FStartSchoolDate not like", value, "fstartschooldate");
            return (Criteria) this;
        }

        public Criteria andFstartschooldateIn(List<String> values) {
            addCriterion("FStartSchoolDate in", values, "fstartschooldate");
            return (Criteria) this;
        }

        public Criteria andFstartschooldateNotIn(List<String> values) {
            addCriterion("FStartSchoolDate not in", values, "fstartschooldate");
            return (Criteria) this;
        }

        public Criteria andFstartschooldateBetween(String value1, String value2) {
            addCriterion("FStartSchoolDate between", value1, value2, "fstartschooldate");
            return (Criteria) this;
        }

        public Criteria andFstartschooldateNotBetween(String value1, String value2) {
            addCriterion("FStartSchoolDate not between", value1, value2, "fstartschooldate");
            return (Criteria) this;
        }

        public Criteria andFendschooldateIsNull() {
            addCriterion("FEndSchoolDate is null");
            return (Criteria) this;
        }

        public Criteria andFendschooldateIsNotNull() {
            addCriterion("FEndSchoolDate is not null");
            return (Criteria) this;
        }

        public Criteria andFendschooldateEqualTo(String value) {
            addCriterion("FEndSchoolDate =", value, "fendschooldate");
            return (Criteria) this;
        }

        public Criteria andFendschooldateNotEqualTo(String value) {
            addCriterion("FEndSchoolDate <>", value, "fendschooldate");
            return (Criteria) this;
        }

        public Criteria andFendschooldateGreaterThan(String value) {
            addCriterion("FEndSchoolDate >", value, "fendschooldate");
            return (Criteria) this;
        }

        public Criteria andFendschooldateGreaterThanOrEqualTo(String value) {
            addCriterion("FEndSchoolDate >=", value, "fendschooldate");
            return (Criteria) this;
        }

        public Criteria andFendschooldateLessThan(String value) {
            addCriterion("FEndSchoolDate <", value, "fendschooldate");
            return (Criteria) this;
        }

        public Criteria andFendschooldateLessThanOrEqualTo(String value) {
            addCriterion("FEndSchoolDate <=", value, "fendschooldate");
            return (Criteria) this;
        }

        public Criteria andFendschooldateLike(String value) {
            addCriterion("FEndSchoolDate like", value, "fendschooldate");
            return (Criteria) this;
        }

        public Criteria andFendschooldateNotLike(String value) {
            addCriterion("FEndSchoolDate not like", value, "fendschooldate");
            return (Criteria) this;
        }

        public Criteria andFendschooldateIn(List<String> values) {
            addCriterion("FEndSchoolDate in", values, "fendschooldate");
            return (Criteria) this;
        }

        public Criteria andFendschooldateNotIn(List<String> values) {
            addCriterion("FEndSchoolDate not in", values, "fendschooldate");
            return (Criteria) this;
        }

        public Criteria andFendschooldateBetween(String value1, String value2) {
            addCriterion("FEndSchoolDate between", value1, value2, "fendschooldate");
            return (Criteria) this;
        }

        public Criteria andFendschooldateNotBetween(String value1, String value2) {
            addCriterion("FEndSchoolDate not between", value1, value2, "fendschooldate");
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

        public Criteria andFeducationIsNull() {
            addCriterion("FEducation is null");
            return (Criteria) this;
        }

        public Criteria andFeducationIsNotNull() {
            addCriterion("FEducation is not null");
            return (Criteria) this;
        }

        public Criteria andFeducationEqualTo(String value) {
            addCriterion("FEducation =", value, "feducation");
            return (Criteria) this;
        }

        public Criteria andFeducationNotEqualTo(String value) {
            addCriterion("FEducation <>", value, "feducation");
            return (Criteria) this;
        }

        public Criteria andFeducationGreaterThan(String value) {
            addCriterion("FEducation >", value, "feducation");
            return (Criteria) this;
        }

        public Criteria andFeducationGreaterThanOrEqualTo(String value) {
            addCriterion("FEducation >=", value, "feducation");
            return (Criteria) this;
        }

        public Criteria andFeducationLessThan(String value) {
            addCriterion("FEducation <", value, "feducation");
            return (Criteria) this;
        }

        public Criteria andFeducationLessThanOrEqualTo(String value) {
            addCriterion("FEducation <=", value, "feducation");
            return (Criteria) this;
        }

        public Criteria andFeducationLike(String value) {
            addCriterion("FEducation like", value, "feducation");
            return (Criteria) this;
        }

        public Criteria andFeducationNotLike(String value) {
            addCriterion("FEducation not like", value, "feducation");
            return (Criteria) this;
        }

        public Criteria andFeducationIn(List<String> values) {
            addCriterion("FEducation in", values, "feducation");
            return (Criteria) this;
        }

        public Criteria andFeducationNotIn(List<String> values) {
            addCriterion("FEducation not in", values, "feducation");
            return (Criteria) this;
        }

        public Criteria andFeducationBetween(String value1, String value2) {
            addCriterion("FEducation between", value1, value2, "feducation");
            return (Criteria) this;
        }

        public Criteria andFeducationNotBetween(String value1, String value2) {
            addCriterion("FEducation not between", value1, value2, "feducation");
            return (Criteria) this;
        }

        public Criteria andFpoliticalIsNull() {
            addCriterion("FPolitical is null");
            return (Criteria) this;
        }

        public Criteria andFpoliticalIsNotNull() {
            addCriterion("FPolitical is not null");
            return (Criteria) this;
        }

        public Criteria andFpoliticalEqualTo(String value) {
            addCriterion("FPolitical =", value, "fpolitical");
            return (Criteria) this;
        }

        public Criteria andFpoliticalNotEqualTo(String value) {
            addCriterion("FPolitical <>", value, "fpolitical");
            return (Criteria) this;
        }

        public Criteria andFpoliticalGreaterThan(String value) {
            addCriterion("FPolitical >", value, "fpolitical");
            return (Criteria) this;
        }

        public Criteria andFpoliticalGreaterThanOrEqualTo(String value) {
            addCriterion("FPolitical >=", value, "fpolitical");
            return (Criteria) this;
        }

        public Criteria andFpoliticalLessThan(String value) {
            addCriterion("FPolitical <", value, "fpolitical");
            return (Criteria) this;
        }

        public Criteria andFpoliticalLessThanOrEqualTo(String value) {
            addCriterion("FPolitical <=", value, "fpolitical");
            return (Criteria) this;
        }

        public Criteria andFpoliticalLike(String value) {
            addCriterion("FPolitical like", value, "fpolitical");
            return (Criteria) this;
        }

        public Criteria andFpoliticalNotLike(String value) {
            addCriterion("FPolitical not like", value, "fpolitical");
            return (Criteria) this;
        }

        public Criteria andFpoliticalIn(List<String> values) {
            addCriterion("FPolitical in", values, "fpolitical");
            return (Criteria) this;
        }

        public Criteria andFpoliticalNotIn(List<String> values) {
            addCriterion("FPolitical not in", values, "fpolitical");
            return (Criteria) this;
        }

        public Criteria andFpoliticalBetween(String value1, String value2) {
            addCriterion("FPolitical between", value1, value2, "fpolitical");
            return (Criteria) this;
        }

        public Criteria andFpoliticalNotBetween(String value1, String value2) {
            addCriterion("FPolitical not between", value1, value2, "fpolitical");
            return (Criteria) this;
        }

        public Criteria andFaddrIsNull() {
            addCriterion("FAddr is null");
            return (Criteria) this;
        }

        public Criteria andFaddrIsNotNull() {
            addCriterion("FAddr is not null");
            return (Criteria) this;
        }

        public Criteria andFaddrEqualTo(String value) {
            addCriterion("FAddr =", value, "faddr");
            return (Criteria) this;
        }

        public Criteria andFaddrNotEqualTo(String value) {
            addCriterion("FAddr <>", value, "faddr");
            return (Criteria) this;
        }

        public Criteria andFaddrGreaterThan(String value) {
            addCriterion("FAddr >", value, "faddr");
            return (Criteria) this;
        }

        public Criteria andFaddrGreaterThanOrEqualTo(String value) {
            addCriterion("FAddr >=", value, "faddr");
            return (Criteria) this;
        }

        public Criteria andFaddrLessThan(String value) {
            addCriterion("FAddr <", value, "faddr");
            return (Criteria) this;
        }

        public Criteria andFaddrLessThanOrEqualTo(String value) {
            addCriterion("FAddr <=", value, "faddr");
            return (Criteria) this;
        }

        public Criteria andFaddrLike(String value) {
            addCriterion("FAddr like", value, "faddr");
            return (Criteria) this;
        }

        public Criteria andFaddrNotLike(String value) {
            addCriterion("FAddr not like", value, "faddr");
            return (Criteria) this;
        }

        public Criteria andFaddrIn(List<String> values) {
            addCriterion("FAddr in", values, "faddr");
            return (Criteria) this;
        }

        public Criteria andFaddrNotIn(List<String> values) {
            addCriterion("FAddr not in", values, "faddr");
            return (Criteria) this;
        }

        public Criteria andFaddrBetween(String value1, String value2) {
            addCriterion("FAddr between", value1, value2, "faddr");
            return (Criteria) this;
        }

        public Criteria andFaddrNotBetween(String value1, String value2) {
            addCriterion("FAddr not between", value1, value2, "faddr");
            return (Criteria) this;
        }

        public Criteria andFhonorIsNull() {
            addCriterion("FHonor is null");
            return (Criteria) this;
        }

        public Criteria andFhonorIsNotNull() {
            addCriterion("FHonor is not null");
            return (Criteria) this;
        }

        public Criteria andFhonorEqualTo(String value) {
            addCriterion("FHonor =", value, "fhonor");
            return (Criteria) this;
        }

        public Criteria andFhonorNotEqualTo(String value) {
            addCriterion("FHonor <>", value, "fhonor");
            return (Criteria) this;
        }

        public Criteria andFhonorGreaterThan(String value) {
            addCriterion("FHonor >", value, "fhonor");
            return (Criteria) this;
        }

        public Criteria andFhonorGreaterThanOrEqualTo(String value) {
            addCriterion("FHonor >=", value, "fhonor");
            return (Criteria) this;
        }

        public Criteria andFhonorLessThan(String value) {
            addCriterion("FHonor <", value, "fhonor");
            return (Criteria) this;
        }

        public Criteria andFhonorLessThanOrEqualTo(String value) {
            addCriterion("FHonor <=", value, "fhonor");
            return (Criteria) this;
        }

        public Criteria andFhonorLike(String value) {
            addCriterion("FHonor like", value, "fhonor");
            return (Criteria) this;
        }

        public Criteria andFhonorNotLike(String value) {
            addCriterion("FHonor not like", value, "fhonor");
            return (Criteria) this;
        }

        public Criteria andFhonorIn(List<String> values) {
            addCriterion("FHonor in", values, "fhonor");
            return (Criteria) this;
        }

        public Criteria andFhonorNotIn(List<String> values) {
            addCriterion("FHonor not in", values, "fhonor");
            return (Criteria) this;
        }

        public Criteria andFhonorBetween(String value1, String value2) {
            addCriterion("FHonor between", value1, value2, "fhonor");
            return (Criteria) this;
        }

        public Criteria andFhonorNotBetween(String value1, String value2) {
            addCriterion("FHonor not between", value1, value2, "fhonor");
            return (Criteria) this;
        }

        public Criteria andFpunishIsNull() {
            addCriterion("FPunish is null");
            return (Criteria) this;
        }

        public Criteria andFpunishIsNotNull() {
            addCriterion("FPunish is not null");
            return (Criteria) this;
        }

        public Criteria andFpunishEqualTo(String value) {
            addCriterion("FPunish =", value, "fpunish");
            return (Criteria) this;
        }

        public Criteria andFpunishNotEqualTo(String value) {
            addCriterion("FPunish <>", value, "fpunish");
            return (Criteria) this;
        }

        public Criteria andFpunishGreaterThan(String value) {
            addCriterion("FPunish >", value, "fpunish");
            return (Criteria) this;
        }

        public Criteria andFpunishGreaterThanOrEqualTo(String value) {
            addCriterion("FPunish >=", value, "fpunish");
            return (Criteria) this;
        }

        public Criteria andFpunishLessThan(String value) {
            addCriterion("FPunish <", value, "fpunish");
            return (Criteria) this;
        }

        public Criteria andFpunishLessThanOrEqualTo(String value) {
            addCriterion("FPunish <=", value, "fpunish");
            return (Criteria) this;
        }

        public Criteria andFpunishLike(String value) {
            addCriterion("FPunish like", value, "fpunish");
            return (Criteria) this;
        }

        public Criteria andFpunishNotLike(String value) {
            addCriterion("FPunish not like", value, "fpunish");
            return (Criteria) this;
        }

        public Criteria andFpunishIn(List<String> values) {
            addCriterion("FPunish in", values, "fpunish");
            return (Criteria) this;
        }

        public Criteria andFpunishNotIn(List<String> values) {
            addCriterion("FPunish not in", values, "fpunish");
            return (Criteria) this;
        }

        public Criteria andFpunishBetween(String value1, String value2) {
            addCriterion("FPunish between", value1, value2, "fpunish");
            return (Criteria) this;
        }

        public Criteria andFpunishNotBetween(String value1, String value2) {
            addCriterion("FPunish not between", value1, value2, "fpunish");
            return (Criteria) this;
        }

        public Criteria andFhealthIsNull() {
            addCriterion("FHealth is null");
            return (Criteria) this;
        }

        public Criteria andFhealthIsNotNull() {
            addCriterion("FHealth is not null");
            return (Criteria) this;
        }

        public Criteria andFhealthEqualTo(String value) {
            addCriterion("FHealth =", value, "fhealth");
            return (Criteria) this;
        }

        public Criteria andFhealthNotEqualTo(String value) {
            addCriterion("FHealth <>", value, "fhealth");
            return (Criteria) this;
        }

        public Criteria andFhealthGreaterThan(String value) {
            addCriterion("FHealth >", value, "fhealth");
            return (Criteria) this;
        }

        public Criteria andFhealthGreaterThanOrEqualTo(String value) {
            addCriterion("FHealth >=", value, "fhealth");
            return (Criteria) this;
        }

        public Criteria andFhealthLessThan(String value) {
            addCriterion("FHealth <", value, "fhealth");
            return (Criteria) this;
        }

        public Criteria andFhealthLessThanOrEqualTo(String value) {
            addCriterion("FHealth <=", value, "fhealth");
            return (Criteria) this;
        }

        public Criteria andFhealthLike(String value) {
            addCriterion("FHealth like", value, "fhealth");
            return (Criteria) this;
        }

        public Criteria andFhealthNotLike(String value) {
            addCriterion("FHealth not like", value, "fhealth");
            return (Criteria) this;
        }

        public Criteria andFhealthIn(List<String> values) {
            addCriterion("FHealth in", values, "fhealth");
            return (Criteria) this;
        }

        public Criteria andFhealthNotIn(List<String> values) {
            addCriterion("FHealth not in", values, "fhealth");
            return (Criteria) this;
        }

        public Criteria andFhealthBetween(String value1, String value2) {
            addCriterion("FHealth between", value1, value2, "fhealth");
            return (Criteria) this;
        }

        public Criteria andFhealthNotBetween(String value1, String value2) {
            addCriterion("FHealth not between", value1, value2, "fhealth");
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

        public Criteria andFpointsIsNull() {
            addCriterion("FPoints is null");
            return (Criteria) this;
        }

        public Criteria andFpointsIsNotNull() {
            addCriterion("FPoints is not null");
            return (Criteria) this;
        }

        public Criteria andFpointsEqualTo(Integer value) {
            addCriterion("FPoints =", value, "fpoints");
            return (Criteria) this;
        }

        public Criteria andFpointsNotEqualTo(Integer value) {
            addCriterion("FPoints <>", value, "fpoints");
            return (Criteria) this;
        }

        public Criteria andFpointsGreaterThan(Integer value) {
            addCriterion("FPoints >", value, "fpoints");
            return (Criteria) this;
        }

        public Criteria andFpointsGreaterThanOrEqualTo(Integer value) {
            addCriterion("FPoints >=", value, "fpoints");
            return (Criteria) this;
        }

        public Criteria andFpointsLessThan(Integer value) {
            addCriterion("FPoints <", value, "fpoints");
            return (Criteria) this;
        }

        public Criteria andFpointsLessThanOrEqualTo(Integer value) {
            addCriterion("FPoints <=", value, "fpoints");
            return (Criteria) this;
        }

        public Criteria andFpointsIn(List<Integer> values) {
            addCriterion("FPoints in", values, "fpoints");
            return (Criteria) this;
        }

        public Criteria andFpointsNotIn(List<Integer> values) {
            addCriterion("FPoints not in", values, "fpoints");
            return (Criteria) this;
        }

        public Criteria andFpointsBetween(Integer value1, Integer value2) {
            addCriterion("FPoints between", value1, value2, "fpoints");
            return (Criteria) this;
        }

        public Criteria andFpointsNotBetween(Integer value1, Integer value2) {
            addCriterion("FPoints not between", value1, value2, "fpoints");
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

        public Criteria andFworkstateIsNull() {
            addCriterion("FWorkState is null");
            return (Criteria) this;
        }

        public Criteria andFworkstateIsNotNull() {
            addCriterion("FWorkState is not null");
            return (Criteria) this;
        }

        public Criteria andFworkstateEqualTo(Integer value) {
            addCriterion("FWorkState =", value, "fworkstate");
            return (Criteria) this;
        }

        public Criteria andFworkstateNotEqualTo(Integer value) {
            addCriterion("FWorkState <>", value, "fworkstate");
            return (Criteria) this;
        }

        public Criteria andFworkstateGreaterThan(Integer value) {
            addCriterion("FWorkState >", value, "fworkstate");
            return (Criteria) this;
        }

        public Criteria andFworkstateGreaterThanOrEqualTo(Integer value) {
            addCriterion("FWorkState >=", value, "fworkstate");
            return (Criteria) this;
        }

        public Criteria andFworkstateLessThan(Integer value) {
            addCriterion("FWorkState <", value, "fworkstate");
            return (Criteria) this;
        }

        public Criteria andFworkstateLessThanOrEqualTo(Integer value) {
            addCriterion("FWorkState <=", value, "fworkstate");
            return (Criteria) this;
        }

        public Criteria andFworkstateIn(List<Integer> values) {
            addCriterion("FWorkState in", values, "fworkstate");
            return (Criteria) this;
        }

        public Criteria andFworkstateNotIn(List<Integer> values) {
            addCriterion("FWorkState not in", values, "fworkstate");
            return (Criteria) this;
        }

        public Criteria andFworkstateBetween(Integer value1, Integer value2) {
            addCriterion("FWorkState between", value1, value2, "fworkstate");
            return (Criteria) this;
        }

        public Criteria andFworkstateNotBetween(Integer value1, Integer value2) {
            addCriterion("FWorkState not between", value1, value2, "fworkstate");
            return (Criteria) this;
        }

        public Criteria andFgradelevelIsNull() {
            addCriterion("FGradeLevel is null");
            return (Criteria) this;
        }

        public Criteria andFgradelevelIsNotNull() {
            addCriterion("FGradeLevel is not null");
            return (Criteria) this;
        }

        public Criteria andFgradelevelEqualTo(Integer value) {
            addCriterion("FGradeLevel =", value, "fgradelevel");
            return (Criteria) this;
        }

        public Criteria andFgradelevelNotEqualTo(Integer value) {
            addCriterion("FGradeLevel <>", value, "fgradelevel");
            return (Criteria) this;
        }

        public Criteria andFgradelevelGreaterThan(Integer value) {
            addCriterion("FGradeLevel >", value, "fgradelevel");
            return (Criteria) this;
        }

        public Criteria andFgradelevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("FGradeLevel >=", value, "fgradelevel");
            return (Criteria) this;
        }

        public Criteria andFgradelevelLessThan(Integer value) {
            addCriterion("FGradeLevel <", value, "fgradelevel");
            return (Criteria) this;
        }

        public Criteria andFgradelevelLessThanOrEqualTo(Integer value) {
            addCriterion("FGradeLevel <=", value, "fgradelevel");
            return (Criteria) this;
        }

        public Criteria andFgradelevelIn(List<Integer> values) {
            addCriterion("FGradeLevel in", values, "fgradelevel");
            return (Criteria) this;
        }

        public Criteria andFgradelevelNotIn(List<Integer> values) {
            addCriterion("FGradeLevel not in", values, "fgradelevel");
            return (Criteria) this;
        }

        public Criteria andFgradelevelBetween(Integer value1, Integer value2) {
            addCriterion("FGradeLevel between", value1, value2, "fgradelevel");
            return (Criteria) this;
        }

        public Criteria andFgradelevelNotBetween(Integer value1, Integer value2) {
            addCriterion("FGradeLevel not between", value1, value2, "fgradelevel");
            return (Criteria) this;
        }

        public Criteria andFclassnameIsNull() {
            addCriterion("FClassName is null");
            return (Criteria) this;
        }

        public Criteria andFclassnameIsNotNull() {
            addCriterion("FClassName is not null");
            return (Criteria) this;
        }

        public Criteria andFclassnameEqualTo(String value) {
            addCriterion("FClassName =", value, "fclassname");
            return (Criteria) this;
        }

        public Criteria andFclassnameNotEqualTo(String value) {
            addCriterion("FClassName <>", value, "fclassname");
            return (Criteria) this;
        }

        public Criteria andFclassnameGreaterThan(String value) {
            addCriterion("FClassName >", value, "fclassname");
            return (Criteria) this;
        }

        public Criteria andFclassnameGreaterThanOrEqualTo(String value) {
            addCriterion("FClassName >=", value, "fclassname");
            return (Criteria) this;
        }

        public Criteria andFclassnameLessThan(String value) {
            addCriterion("FClassName <", value, "fclassname");
            return (Criteria) this;
        }

        public Criteria andFclassnameLessThanOrEqualTo(String value) {
            addCriterion("FClassName <=", value, "fclassname");
            return (Criteria) this;
        }

        public Criteria andFclassnameLike(String value) {
            addCriterion("FClassName like", value, "fclassname");
            return (Criteria) this;
        }

        public Criteria andFclassnameNotLike(String value) {
            addCriterion("FClassName not like", value, "fclassname");
            return (Criteria) this;
        }

        public Criteria andFclassnameIn(List<String> values) {
            addCriterion("FClassName in", values, "fclassname");
            return (Criteria) this;
        }

        public Criteria andFclassnameNotIn(List<String> values) {
            addCriterion("FClassName not in", values, "fclassname");
            return (Criteria) this;
        }

        public Criteria andFclassnameBetween(String value1, String value2) {
            addCriterion("FClassName between", value1, value2, "fclassname");
            return (Criteria) this;
        }

        public Criteria andFclassnameNotBetween(String value1, String value2) {
            addCriterion("FClassName not between", value1, value2, "fclassname");
            return (Criteria) this;
        }
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：student_capability_evaluation..t_student
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * TStudent
     * Criterion
     * 数据库表：student_capability_evaluation..t_student
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