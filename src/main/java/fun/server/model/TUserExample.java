package fun.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * student_capability_evaluation..t_user
     * @mbg.generated
     */
    public TUserExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * 设置排序字段
     * student_capability_evaluation..t_user
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * student_capability_evaluation..t_user
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * student_capability_evaluation..t_user
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * student_capability_evaluation..t_user
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * student_capability_evaluation..t_user
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * student_capability_evaluation..t_user
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * student_capability_evaluation..t_user
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
     * student_capability_evaluation..t_user
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
     * student_capability_evaluation..t_user
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * student_capability_evaluation..t_user
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TUser
     * GeneratedCriteria
     * 数据库表：student_capability_evaluation..t_user
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

        public Criteria andFKeyIdIsNull() {
            addCriterion("f_key_id is null");
            return (Criteria) this;
        }

        public Criteria andFKeyIdIsNotNull() {
            addCriterion("f_key_id is not null");
            return (Criteria) this;
        }

        public Criteria andFKeyIdEqualTo(Long value) {
            addCriterion("f_key_id =", value, "fKeyId");
            return (Criteria) this;
        }

        public Criteria andFKeyIdNotEqualTo(Long value) {
            addCriterion("f_key_id <>", value, "fKeyId");
            return (Criteria) this;
        }

        public Criteria andFKeyIdGreaterThan(Long value) {
            addCriterion("f_key_id >", value, "fKeyId");
            return (Criteria) this;
        }

        public Criteria andFKeyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("f_key_id >=", value, "fKeyId");
            return (Criteria) this;
        }

        public Criteria andFKeyIdLessThan(Long value) {
            addCriterion("f_key_id <", value, "fKeyId");
            return (Criteria) this;
        }

        public Criteria andFKeyIdLessThanOrEqualTo(Long value) {
            addCriterion("f_key_id <=", value, "fKeyId");
            return (Criteria) this;
        }

        public Criteria andFKeyIdIn(List<Long> values) {
            addCriterion("f_key_id in", values, "fKeyId");
            return (Criteria) this;
        }

        public Criteria andFKeyIdNotIn(List<Long> values) {
            addCriterion("f_key_id not in", values, "fKeyId");
            return (Criteria) this;
        }

        public Criteria andFKeyIdBetween(Long value1, Long value2) {
            addCriterion("f_key_id between", value1, value2, "fKeyId");
            return (Criteria) this;
        }

        public Criteria andFKeyIdNotBetween(Long value1, Long value2) {
            addCriterion("f_key_id not between", value1, value2, "fKeyId");
            return (Criteria) this;
        }

        public Criteria andFCIdIsNull() {
            addCriterion("f_c_id is null");
            return (Criteria) this;
        }

        public Criteria andFCIdIsNotNull() {
            addCriterion("f_c_id is not null");
            return (Criteria) this;
        }

        public Criteria andFCIdEqualTo(Long value) {
            addCriterion("f_c_id =", value, "fCId");
            return (Criteria) this;
        }

        public Criteria andFCIdNotEqualTo(Long value) {
            addCriterion("f_c_id <>", value, "fCId");
            return (Criteria) this;
        }

        public Criteria andFCIdGreaterThan(Long value) {
            addCriterion("f_c_id >", value, "fCId");
            return (Criteria) this;
        }

        public Criteria andFCIdGreaterThanOrEqualTo(Long value) {
            addCriterion("f_c_id >=", value, "fCId");
            return (Criteria) this;
        }

        public Criteria andFCIdLessThan(Long value) {
            addCriterion("f_c_id <", value, "fCId");
            return (Criteria) this;
        }

        public Criteria andFCIdLessThanOrEqualTo(Long value) {
            addCriterion("f_c_id <=", value, "fCId");
            return (Criteria) this;
        }

        public Criteria andFCIdIn(List<Long> values) {
            addCriterion("f_c_id in", values, "fCId");
            return (Criteria) this;
        }

        public Criteria andFCIdNotIn(List<Long> values) {
            addCriterion("f_c_id not in", values, "fCId");
            return (Criteria) this;
        }

        public Criteria andFCIdBetween(Long value1, Long value2) {
            addCriterion("f_c_id between", value1, value2, "fCId");
            return (Criteria) this;
        }

        public Criteria andFCIdNotBetween(Long value1, Long value2) {
            addCriterion("f_c_id not between", value1, value2, "fCId");
            return (Criteria) this;
        }

        public Criteria andFUIdIsNull() {
            addCriterion("f_u_id is null");
            return (Criteria) this;
        }

        public Criteria andFUIdIsNotNull() {
            addCriterion("f_u_id is not null");
            return (Criteria) this;
        }

        public Criteria andFUIdEqualTo(Long value) {
            addCriterion("f_u_id =", value, "fUId");
            return (Criteria) this;
        }

        public Criteria andFUIdNotEqualTo(Long value) {
            addCriterion("f_u_id <>", value, "fUId");
            return (Criteria) this;
        }

        public Criteria andFUIdGreaterThan(Long value) {
            addCriterion("f_u_id >", value, "fUId");
            return (Criteria) this;
        }

        public Criteria andFUIdGreaterThanOrEqualTo(Long value) {
            addCriterion("f_u_id >=", value, "fUId");
            return (Criteria) this;
        }

        public Criteria andFUIdLessThan(Long value) {
            addCriterion("f_u_id <", value, "fUId");
            return (Criteria) this;
        }

        public Criteria andFUIdLessThanOrEqualTo(Long value) {
            addCriterion("f_u_id <=", value, "fUId");
            return (Criteria) this;
        }

        public Criteria andFUIdIn(List<Long> values) {
            addCriterion("f_u_id in", values, "fUId");
            return (Criteria) this;
        }

        public Criteria andFUIdNotIn(List<Long> values) {
            addCriterion("f_u_id not in", values, "fUId");
            return (Criteria) this;
        }

        public Criteria andFUIdBetween(Long value1, Long value2) {
            addCriterion("f_u_id between", value1, value2, "fUId");
            return (Criteria) this;
        }

        public Criteria andFUIdNotBetween(Long value1, Long value2) {
            addCriterion("f_u_id not between", value1, value2, "fUId");
            return (Criteria) this;
        }

        public Criteria andFCDateIsNull() {
            addCriterion("f_c_date is null");
            return (Criteria) this;
        }

        public Criteria andFCDateIsNotNull() {
            addCriterion("f_c_date is not null");
            return (Criteria) this;
        }

        public Criteria andFCDateEqualTo(Date value) {
            addCriterion("f_c_date =", value, "fCDate");
            return (Criteria) this;
        }

        public Criteria andFCDateNotEqualTo(Date value) {
            addCriterion("f_c_date <>", value, "fCDate");
            return (Criteria) this;
        }

        public Criteria andFCDateGreaterThan(Date value) {
            addCriterion("f_c_date >", value, "fCDate");
            return (Criteria) this;
        }

        public Criteria andFCDateGreaterThanOrEqualTo(Date value) {
            addCriterion("f_c_date >=", value, "fCDate");
            return (Criteria) this;
        }

        public Criteria andFCDateLessThan(Date value) {
            addCriterion("f_c_date <", value, "fCDate");
            return (Criteria) this;
        }

        public Criteria andFCDateLessThanOrEqualTo(Date value) {
            addCriterion("f_c_date <=", value, "fCDate");
            return (Criteria) this;
        }

        public Criteria andFCDateIn(List<Date> values) {
            addCriterion("f_c_date in", values, "fCDate");
            return (Criteria) this;
        }

        public Criteria andFCDateNotIn(List<Date> values) {
            addCriterion("f_c_date not in", values, "fCDate");
            return (Criteria) this;
        }

        public Criteria andFCDateBetween(Date value1, Date value2) {
            addCriterion("f_c_date between", value1, value2, "fCDate");
            return (Criteria) this;
        }

        public Criteria andFCDateNotBetween(Date value1, Date value2) {
            addCriterion("f_c_date not between", value1, value2, "fCDate");
            return (Criteria) this;
        }

        public Criteria andFUDateIsNull() {
            addCriterion("f_u_date is null");
            return (Criteria) this;
        }

        public Criteria andFUDateIsNotNull() {
            addCriterion("f_u_date is not null");
            return (Criteria) this;
        }

        public Criteria andFUDateEqualTo(Date value) {
            addCriterion("f_u_date =", value, "fUDate");
            return (Criteria) this;
        }

        public Criteria andFUDateNotEqualTo(Date value) {
            addCriterion("f_u_date <>", value, "fUDate");
            return (Criteria) this;
        }

        public Criteria andFUDateGreaterThan(Date value) {
            addCriterion("f_u_date >", value, "fUDate");
            return (Criteria) this;
        }

        public Criteria andFUDateGreaterThanOrEqualTo(Date value) {
            addCriterion("f_u_date >=", value, "fUDate");
            return (Criteria) this;
        }

        public Criteria andFUDateLessThan(Date value) {
            addCriterion("f_u_date <", value, "fUDate");
            return (Criteria) this;
        }

        public Criteria andFUDateLessThanOrEqualTo(Date value) {
            addCriterion("f_u_date <=", value, "fUDate");
            return (Criteria) this;
        }

        public Criteria andFUDateIn(List<Date> values) {
            addCriterion("f_u_date in", values, "fUDate");
            return (Criteria) this;
        }

        public Criteria andFUDateNotIn(List<Date> values) {
            addCriterion("f_u_date not in", values, "fUDate");
            return (Criteria) this;
        }

        public Criteria andFUDateBetween(Date value1, Date value2) {
            addCriterion("f_u_date between", value1, value2, "fUDate");
            return (Criteria) this;
        }

        public Criteria andFUDateNotBetween(Date value1, Date value2) {
            addCriterion("f_u_date not between", value1, value2, "fUDate");
            return (Criteria) this;
        }

        public Criteria andFStateIsNull() {
            addCriterion("f_state is null");
            return (Criteria) this;
        }

        public Criteria andFStateIsNotNull() {
            addCriterion("f_state is not null");
            return (Criteria) this;
        }

        public Criteria andFStateEqualTo(Integer value) {
            addCriterion("f_state =", value, "fState");
            return (Criteria) this;
        }

        public Criteria andFStateNotEqualTo(Integer value) {
            addCriterion("f_state <>", value, "fState");
            return (Criteria) this;
        }

        public Criteria andFStateGreaterThan(Integer value) {
            addCriterion("f_state >", value, "fState");
            return (Criteria) this;
        }

        public Criteria andFStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("f_state >=", value, "fState");
            return (Criteria) this;
        }

        public Criteria andFStateLessThan(Integer value) {
            addCriterion("f_state <", value, "fState");
            return (Criteria) this;
        }

        public Criteria andFStateLessThanOrEqualTo(Integer value) {
            addCriterion("f_state <=", value, "fState");
            return (Criteria) this;
        }

        public Criteria andFStateIn(List<Integer> values) {
            addCriterion("f_state in", values, "fState");
            return (Criteria) this;
        }

        public Criteria andFStateNotIn(List<Integer> values) {
            addCriterion("f_state not in", values, "fState");
            return (Criteria) this;
        }

        public Criteria andFStateBetween(Integer value1, Integer value2) {
            addCriterion("f_state between", value1, value2, "fState");
            return (Criteria) this;
        }

        public Criteria andFStateNotBetween(Integer value1, Integer value2) {
            addCriterion("f_state not between", value1, value2, "fState");
            return (Criteria) this;
        }

        public Criteria andFLoginIsNull() {
            addCriterion("f_login is null");
            return (Criteria) this;
        }

        public Criteria andFLoginIsNotNull() {
            addCriterion("f_login is not null");
            return (Criteria) this;
        }

        public Criteria andFLoginEqualTo(String value) {
            addCriterion("f_login =", value, "fLogin");
            return (Criteria) this;
        }

        public Criteria andFLoginNotEqualTo(String value) {
            addCriterion("f_login <>", value, "fLogin");
            return (Criteria) this;
        }

        public Criteria andFLoginGreaterThan(String value) {
            addCriterion("f_login >", value, "fLogin");
            return (Criteria) this;
        }

        public Criteria andFLoginGreaterThanOrEqualTo(String value) {
            addCriterion("f_login >=", value, "fLogin");
            return (Criteria) this;
        }

        public Criteria andFLoginLessThan(String value) {
            addCriterion("f_login <", value, "fLogin");
            return (Criteria) this;
        }

        public Criteria andFLoginLessThanOrEqualTo(String value) {
            addCriterion("f_login <=", value, "fLogin");
            return (Criteria) this;
        }

        public Criteria andFLoginLike(String value) {
            addCriterion("f_login like", value, "fLogin");
            return (Criteria) this;
        }

        public Criteria andFLoginNotLike(String value) {
            addCriterion("f_login not like", value, "fLogin");
            return (Criteria) this;
        }

        public Criteria andFLoginIn(List<String> values) {
            addCriterion("f_login in", values, "fLogin");
            return (Criteria) this;
        }

        public Criteria andFLoginNotIn(List<String> values) {
            addCriterion("f_login not in", values, "fLogin");
            return (Criteria) this;
        }

        public Criteria andFLoginBetween(String value1, String value2) {
            addCriterion("f_login between", value1, value2, "fLogin");
            return (Criteria) this;
        }

        public Criteria andFLoginNotBetween(String value1, String value2) {
            addCriterion("f_login not between", value1, value2, "fLogin");
            return (Criteria) this;
        }

        public Criteria andFPassIsNull() {
            addCriterion("f_pass is null");
            return (Criteria) this;
        }

        public Criteria andFPassIsNotNull() {
            addCriterion("f_pass is not null");
            return (Criteria) this;
        }

        public Criteria andFPassEqualTo(String value) {
            addCriterion("f_pass =", value, "fPass");
            return (Criteria) this;
        }

        public Criteria andFPassNotEqualTo(String value) {
            addCriterion("f_pass <>", value, "fPass");
            return (Criteria) this;
        }

        public Criteria andFPassGreaterThan(String value) {
            addCriterion("f_pass >", value, "fPass");
            return (Criteria) this;
        }

        public Criteria andFPassGreaterThanOrEqualTo(String value) {
            addCriterion("f_pass >=", value, "fPass");
            return (Criteria) this;
        }

        public Criteria andFPassLessThan(String value) {
            addCriterion("f_pass <", value, "fPass");
            return (Criteria) this;
        }

        public Criteria andFPassLessThanOrEqualTo(String value) {
            addCriterion("f_pass <=", value, "fPass");
            return (Criteria) this;
        }

        public Criteria andFPassLike(String value) {
            addCriterion("f_pass like", value, "fPass");
            return (Criteria) this;
        }

        public Criteria andFPassNotLike(String value) {
            addCriterion("f_pass not like", value, "fPass");
            return (Criteria) this;
        }

        public Criteria andFPassIn(List<String> values) {
            addCriterion("f_pass in", values, "fPass");
            return (Criteria) this;
        }

        public Criteria andFPassNotIn(List<String> values) {
            addCriterion("f_pass not in", values, "fPass");
            return (Criteria) this;
        }

        public Criteria andFPassBetween(String value1, String value2) {
            addCriterion("f_pass between", value1, value2, "fPass");
            return (Criteria) this;
        }

        public Criteria andFPassNotBetween(String value1, String value2) {
            addCriterion("f_pass not between", value1, value2, "fPass");
            return (Criteria) this;
        }

        public Criteria andFNameIsNull() {
            addCriterion("f_name is null");
            return (Criteria) this;
        }

        public Criteria andFNameIsNotNull() {
            addCriterion("f_name is not null");
            return (Criteria) this;
        }

        public Criteria andFNameEqualTo(String value) {
            addCriterion("f_name =", value, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameNotEqualTo(String value) {
            addCriterion("f_name <>", value, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameGreaterThan(String value) {
            addCriterion("f_name >", value, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameGreaterThanOrEqualTo(String value) {
            addCriterion("f_name >=", value, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameLessThan(String value) {
            addCriterion("f_name <", value, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameLessThanOrEqualTo(String value) {
            addCriterion("f_name <=", value, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameLike(String value) {
            addCriterion("f_name like", value, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameNotLike(String value) {
            addCriterion("f_name not like", value, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameIn(List<String> values) {
            addCriterion("f_name in", values, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameNotIn(List<String> values) {
            addCriterion("f_name not in", values, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameBetween(String value1, String value2) {
            addCriterion("f_name between", value1, value2, "fName");
            return (Criteria) this;
        }

        public Criteria andFNameNotBetween(String value1, String value2) {
            addCriterion("f_name not between", value1, value2, "fName");
            return (Criteria) this;
        }

        public Criteria andFTelIsNull() {
            addCriterion("f_tel is null");
            return (Criteria) this;
        }

        public Criteria andFTelIsNotNull() {
            addCriterion("f_tel is not null");
            return (Criteria) this;
        }

        public Criteria andFTelEqualTo(String value) {
            addCriterion("f_tel =", value, "fTel");
            return (Criteria) this;
        }

        public Criteria andFTelNotEqualTo(String value) {
            addCriterion("f_tel <>", value, "fTel");
            return (Criteria) this;
        }

        public Criteria andFTelGreaterThan(String value) {
            addCriterion("f_tel >", value, "fTel");
            return (Criteria) this;
        }

        public Criteria andFTelGreaterThanOrEqualTo(String value) {
            addCriterion("f_tel >=", value, "fTel");
            return (Criteria) this;
        }

        public Criteria andFTelLessThan(String value) {
            addCriterion("f_tel <", value, "fTel");
            return (Criteria) this;
        }

        public Criteria andFTelLessThanOrEqualTo(String value) {
            addCriterion("f_tel <=", value, "fTel");
            return (Criteria) this;
        }

        public Criteria andFTelLike(String value) {
            addCriterion("f_tel like", value, "fTel");
            return (Criteria) this;
        }

        public Criteria andFTelNotLike(String value) {
            addCriterion("f_tel not like", value, "fTel");
            return (Criteria) this;
        }

        public Criteria andFTelIn(List<String> values) {
            addCriterion("f_tel in", values, "fTel");
            return (Criteria) this;
        }

        public Criteria andFTelNotIn(List<String> values) {
            addCriterion("f_tel not in", values, "fTel");
            return (Criteria) this;
        }

        public Criteria andFTelBetween(String value1, String value2) {
            addCriterion("f_tel between", value1, value2, "fTel");
            return (Criteria) this;
        }

        public Criteria andFTelNotBetween(String value1, String value2) {
            addCriterion("f_tel not between", value1, value2, "fTel");
            return (Criteria) this;
        }

        public Criteria andFEmailIsNull() {
            addCriterion("f_email is null");
            return (Criteria) this;
        }

        public Criteria andFEmailIsNotNull() {
            addCriterion("f_email is not null");
            return (Criteria) this;
        }

        public Criteria andFEmailEqualTo(String value) {
            addCriterion("f_email =", value, "fEmail");
            return (Criteria) this;
        }

        public Criteria andFEmailNotEqualTo(String value) {
            addCriterion("f_email <>", value, "fEmail");
            return (Criteria) this;
        }

        public Criteria andFEmailGreaterThan(String value) {
            addCriterion("f_email >", value, "fEmail");
            return (Criteria) this;
        }

        public Criteria andFEmailGreaterThanOrEqualTo(String value) {
            addCriterion("f_email >=", value, "fEmail");
            return (Criteria) this;
        }

        public Criteria andFEmailLessThan(String value) {
            addCriterion("f_email <", value, "fEmail");
            return (Criteria) this;
        }

        public Criteria andFEmailLessThanOrEqualTo(String value) {
            addCriterion("f_email <=", value, "fEmail");
            return (Criteria) this;
        }

        public Criteria andFEmailLike(String value) {
            addCriterion("f_email like", value, "fEmail");
            return (Criteria) this;
        }

        public Criteria andFEmailNotLike(String value) {
            addCriterion("f_email not like", value, "fEmail");
            return (Criteria) this;
        }

        public Criteria andFEmailIn(List<String> values) {
            addCriterion("f_email in", values, "fEmail");
            return (Criteria) this;
        }

        public Criteria andFEmailNotIn(List<String> values) {
            addCriterion("f_email not in", values, "fEmail");
            return (Criteria) this;
        }

        public Criteria andFEmailBetween(String value1, String value2) {
            addCriterion("f_email between", value1, value2, "fEmail");
            return (Criteria) this;
        }

        public Criteria andFEmailNotBetween(String value1, String value2) {
            addCriterion("f_email not between", value1, value2, "fEmail");
            return (Criteria) this;
        }

        public Criteria andFIsAdminIsNull() {
            addCriterion("f_is_admin is null");
            return (Criteria) this;
        }

        public Criteria andFIsAdminIsNotNull() {
            addCriterion("f_is_admin is not null");
            return (Criteria) this;
        }

        public Criteria andFIsAdminEqualTo(Integer value) {
            addCriterion("f_is_admin =", value, "fIsAdmin");
            return (Criteria) this;
        }

        public Criteria andFIsAdminNotEqualTo(Integer value) {
            addCriterion("f_is_admin <>", value, "fIsAdmin");
            return (Criteria) this;
        }

        public Criteria andFIsAdminGreaterThan(Integer value) {
            addCriterion("f_is_admin >", value, "fIsAdmin");
            return (Criteria) this;
        }

        public Criteria andFIsAdminGreaterThanOrEqualTo(Integer value) {
            addCriterion("f_is_admin >=", value, "fIsAdmin");
            return (Criteria) this;
        }

        public Criteria andFIsAdminLessThan(Integer value) {
            addCriterion("f_is_admin <", value, "fIsAdmin");
            return (Criteria) this;
        }

        public Criteria andFIsAdminLessThanOrEqualTo(Integer value) {
            addCriterion("f_is_admin <=", value, "fIsAdmin");
            return (Criteria) this;
        }

        public Criteria andFIsAdminIn(List<Integer> values) {
            addCriterion("f_is_admin in", values, "fIsAdmin");
            return (Criteria) this;
        }

        public Criteria andFIsAdminNotIn(List<Integer> values) {
            addCriterion("f_is_admin not in", values, "fIsAdmin");
            return (Criteria) this;
        }

        public Criteria andFIsAdminBetween(Integer value1, Integer value2) {
            addCriterion("f_is_admin between", value1, value2, "fIsAdmin");
            return (Criteria) this;
        }

        public Criteria andFIsAdminNotBetween(Integer value1, Integer value2) {
            addCriterion("f_is_admin not between", value1, value2, "fIsAdmin");
            return (Criteria) this;
        }

        public Criteria andFNoteIsNull() {
            addCriterion("f_note is null");
            return (Criteria) this;
        }

        public Criteria andFNoteIsNotNull() {
            addCriterion("f_note is not null");
            return (Criteria) this;
        }

        public Criteria andFNoteEqualTo(String value) {
            addCriterion("f_note =", value, "fNote");
            return (Criteria) this;
        }

        public Criteria andFNoteNotEqualTo(String value) {
            addCriterion("f_note <>", value, "fNote");
            return (Criteria) this;
        }

        public Criteria andFNoteGreaterThan(String value) {
            addCriterion("f_note >", value, "fNote");
            return (Criteria) this;
        }

        public Criteria andFNoteGreaterThanOrEqualTo(String value) {
            addCriterion("f_note >=", value, "fNote");
            return (Criteria) this;
        }

        public Criteria andFNoteLessThan(String value) {
            addCriterion("f_note <", value, "fNote");
            return (Criteria) this;
        }

        public Criteria andFNoteLessThanOrEqualTo(String value) {
            addCriterion("f_note <=", value, "fNote");
            return (Criteria) this;
        }

        public Criteria andFNoteLike(String value) {
            addCriterion("f_note like", value, "fNote");
            return (Criteria) this;
        }

        public Criteria andFNoteNotLike(String value) {
            addCriterion("f_note not like", value, "fNote");
            return (Criteria) this;
        }

        public Criteria andFNoteIn(List<String> values) {
            addCriterion("f_note in", values, "fNote");
            return (Criteria) this;
        }

        public Criteria andFNoteNotIn(List<String> values) {
            addCriterion("f_note not in", values, "fNote");
            return (Criteria) this;
        }

        public Criteria andFNoteBetween(String value1, String value2) {
            addCriterion("f_note between", value1, value2, "fNote");
            return (Criteria) this;
        }

        public Criteria andFNoteNotBetween(String value1, String value2) {
            addCriterion("f_note not between", value1, value2, "fNote");
            return (Criteria) this;
        }

        public Criteria andFTypeIsNull() {
            addCriterion("f_type is null");
            return (Criteria) this;
        }

        public Criteria andFTypeIsNotNull() {
            addCriterion("f_type is not null");
            return (Criteria) this;
        }

        public Criteria andFTypeEqualTo(Integer value) {
            addCriterion("f_type =", value, "fType");
            return (Criteria) this;
        }

        public Criteria andFTypeNotEqualTo(Integer value) {
            addCriterion("f_type <>", value, "fType");
            return (Criteria) this;
        }

        public Criteria andFTypeGreaterThan(Integer value) {
            addCriterion("f_type >", value, "fType");
            return (Criteria) this;
        }

        public Criteria andFTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("f_type >=", value, "fType");
            return (Criteria) this;
        }

        public Criteria andFTypeLessThan(Integer value) {
            addCriterion("f_type <", value, "fType");
            return (Criteria) this;
        }

        public Criteria andFTypeLessThanOrEqualTo(Integer value) {
            addCriterion("f_type <=", value, "fType");
            return (Criteria) this;
        }

        public Criteria andFTypeIn(List<Integer> values) {
            addCriterion("f_type in", values, "fType");
            return (Criteria) this;
        }

        public Criteria andFTypeNotIn(List<Integer> values) {
            addCriterion("f_type not in", values, "fType");
            return (Criteria) this;
        }

        public Criteria andFTypeBetween(Integer value1, Integer value2) {
            addCriterion("f_type between", value1, value2, "fType");
            return (Criteria) this;
        }

        public Criteria andFTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("f_type not between", value1, value2, "fType");
            return (Criteria) this;
        }

        public Criteria andFSupplierIdIsNull() {
            addCriterion("f_supplier_id is null");
            return (Criteria) this;
        }

        public Criteria andFSupplierIdIsNotNull() {
            addCriterion("f_supplier_id is not null");
            return (Criteria) this;
        }

        public Criteria andFSupplierIdEqualTo(Long value) {
            addCriterion("f_supplier_id =", value, "fSupplierId");
            return (Criteria) this;
        }

        public Criteria andFSupplierIdNotEqualTo(Long value) {
            addCriterion("f_supplier_id <>", value, "fSupplierId");
            return (Criteria) this;
        }

        public Criteria andFSupplierIdGreaterThan(Long value) {
            addCriterion("f_supplier_id >", value, "fSupplierId");
            return (Criteria) this;
        }

        public Criteria andFSupplierIdGreaterThanOrEqualTo(Long value) {
            addCriterion("f_supplier_id >=", value, "fSupplierId");
            return (Criteria) this;
        }

        public Criteria andFSupplierIdLessThan(Long value) {
            addCriterion("f_supplier_id <", value, "fSupplierId");
            return (Criteria) this;
        }

        public Criteria andFSupplierIdLessThanOrEqualTo(Long value) {
            addCriterion("f_supplier_id <=", value, "fSupplierId");
            return (Criteria) this;
        }

        public Criteria andFSupplierIdIn(List<Long> values) {
            addCriterion("f_supplier_id in", values, "fSupplierId");
            return (Criteria) this;
        }

        public Criteria andFSupplierIdNotIn(List<Long> values) {
            addCriterion("f_supplier_id not in", values, "fSupplierId");
            return (Criteria) this;
        }

        public Criteria andFSupplierIdBetween(Long value1, Long value2) {
            addCriterion("f_supplier_id between", value1, value2, "fSupplierId");
            return (Criteria) this;
        }

        public Criteria andFSupplierIdNotBetween(Long value1, Long value2) {
            addCriterion("f_supplier_id not between", value1, value2, "fSupplierId");
            return (Criteria) this;
        }

        public Criteria andFActiveIsNull() {
            addCriterion("f_active is null");
            return (Criteria) this;
        }

        public Criteria andFActiveIsNotNull() {
            addCriterion("f_active is not null");
            return (Criteria) this;
        }

        public Criteria andFActiveEqualTo(Integer value) {
            addCriterion("f_active =", value, "fActive");
            return (Criteria) this;
        }

        public Criteria andFActiveNotEqualTo(Integer value) {
            addCriterion("f_active <>", value, "fActive");
            return (Criteria) this;
        }

        public Criteria andFActiveGreaterThan(Integer value) {
            addCriterion("f_active >", value, "fActive");
            return (Criteria) this;
        }

        public Criteria andFActiveGreaterThanOrEqualTo(Integer value) {
            addCriterion("f_active >=", value, "fActive");
            return (Criteria) this;
        }

        public Criteria andFActiveLessThan(Integer value) {
            addCriterion("f_active <", value, "fActive");
            return (Criteria) this;
        }

        public Criteria andFActiveLessThanOrEqualTo(Integer value) {
            addCriterion("f_active <=", value, "fActive");
            return (Criteria) this;
        }

        public Criteria andFActiveIn(List<Integer> values) {
            addCriterion("f_active in", values, "fActive");
            return (Criteria) this;
        }

        public Criteria andFActiveNotIn(List<Integer> values) {
            addCriterion("f_active not in", values, "fActive");
            return (Criteria) this;
        }

        public Criteria andFActiveBetween(Integer value1, Integer value2) {
            addCriterion("f_active between", value1, value2, "fActive");
            return (Criteria) this;
        }

        public Criteria andFActiveNotBetween(Integer value1, Integer value2) {
            addCriterion("f_active not between", value1, value2, "fActive");
            return (Criteria) this;
        }

        public Criteria andFDeptIsNull() {
            addCriterion("f_dept is null");
            return (Criteria) this;
        }

        public Criteria andFDeptIsNotNull() {
            addCriterion("f_dept is not null");
            return (Criteria) this;
        }

        public Criteria andFDeptEqualTo(Long value) {
            addCriterion("f_dept =", value, "fDept");
            return (Criteria) this;
        }

        public Criteria andFDeptNotEqualTo(Long value) {
            addCriterion("f_dept <>", value, "fDept");
            return (Criteria) this;
        }

        public Criteria andFDeptGreaterThan(Long value) {
            addCriterion("f_dept >", value, "fDept");
            return (Criteria) this;
        }

        public Criteria andFDeptGreaterThanOrEqualTo(Long value) {
            addCriterion("f_dept >=", value, "fDept");
            return (Criteria) this;
        }

        public Criteria andFDeptLessThan(Long value) {
            addCriterion("f_dept <", value, "fDept");
            return (Criteria) this;
        }

        public Criteria andFDeptLessThanOrEqualTo(Long value) {
            addCriterion("f_dept <=", value, "fDept");
            return (Criteria) this;
        }

        public Criteria andFDeptIn(List<Long> values) {
            addCriterion("f_dept in", values, "fDept");
            return (Criteria) this;
        }

        public Criteria andFDeptNotIn(List<Long> values) {
            addCriterion("f_dept not in", values, "fDept");
            return (Criteria) this;
        }

        public Criteria andFDeptBetween(Long value1, Long value2) {
            addCriterion("f_dept between", value1, value2, "fDept");
            return (Criteria) this;
        }

        public Criteria andFDeptNotBetween(Long value1, Long value2) {
            addCriterion("f_dept not between", value1, value2, "fDept");
            return (Criteria) this;
        }

        public Criteria andFPostIsNull() {
            addCriterion("f_post is null");
            return (Criteria) this;
        }

        public Criteria andFPostIsNotNull() {
            addCriterion("f_post is not null");
            return (Criteria) this;
        }

        public Criteria andFPostEqualTo(Long value) {
            addCriterion("f_post =", value, "fPost");
            return (Criteria) this;
        }

        public Criteria andFPostNotEqualTo(Long value) {
            addCriterion("f_post <>", value, "fPost");
            return (Criteria) this;
        }

        public Criteria andFPostGreaterThan(Long value) {
            addCriterion("f_post >", value, "fPost");
            return (Criteria) this;
        }

        public Criteria andFPostGreaterThanOrEqualTo(Long value) {
            addCriterion("f_post >=", value, "fPost");
            return (Criteria) this;
        }

        public Criteria andFPostLessThan(Long value) {
            addCriterion("f_post <", value, "fPost");
            return (Criteria) this;
        }

        public Criteria andFPostLessThanOrEqualTo(Long value) {
            addCriterion("f_post <=", value, "fPost");
            return (Criteria) this;
        }

        public Criteria andFPostIn(List<Long> values) {
            addCriterion("f_post in", values, "fPost");
            return (Criteria) this;
        }

        public Criteria andFPostNotIn(List<Long> values) {
            addCriterion("f_post not in", values, "fPost");
            return (Criteria) this;
        }

        public Criteria andFPostBetween(Long value1, Long value2) {
            addCriterion("f_post between", value1, value2, "fPost");
            return (Criteria) this;
        }

        public Criteria andFPostNotBetween(Long value1, Long value2) {
            addCriterion("f_post not between", value1, value2, "fPost");
            return (Criteria) this;
        }

        public Criteria andFUsernoIsNull() {
            addCriterion("f_userno is null");
            return (Criteria) this;
        }

        public Criteria andFUsernoIsNotNull() {
            addCriterion("f_userno is not null");
            return (Criteria) this;
        }

        public Criteria andFUsernoEqualTo(String value) {
            addCriterion("f_userno =", value, "fUserno");
            return (Criteria) this;
        }

        public Criteria andFUsernoNotEqualTo(String value) {
            addCriterion("f_userno <>", value, "fUserno");
            return (Criteria) this;
        }

        public Criteria andFUsernoGreaterThan(String value) {
            addCriterion("f_userno >", value, "fUserno");
            return (Criteria) this;
        }

        public Criteria andFUsernoGreaterThanOrEqualTo(String value) {
            addCriterion("f_userno >=", value, "fUserno");
            return (Criteria) this;
        }

        public Criteria andFUsernoLessThan(String value) {
            addCriterion("f_userno <", value, "fUserno");
            return (Criteria) this;
        }

        public Criteria andFUsernoLessThanOrEqualTo(String value) {
            addCriterion("f_userno <=", value, "fUserno");
            return (Criteria) this;
        }

        public Criteria andFUsernoLike(String value) {
            addCriterion("f_userno like", value, "fUserno");
            return (Criteria) this;
        }

        public Criteria andFUsernoNotLike(String value) {
            addCriterion("f_userno not like", value, "fUserno");
            return (Criteria) this;
        }

        public Criteria andFUsernoIn(List<String> values) {
            addCriterion("f_userno in", values, "fUserno");
            return (Criteria) this;
        }

        public Criteria andFUsernoNotIn(List<String> values) {
            addCriterion("f_userno not in", values, "fUserno");
            return (Criteria) this;
        }

        public Criteria andFUsernoBetween(String value1, String value2) {
            addCriterion("f_userno between", value1, value2, "fUserno");
            return (Criteria) this;
        }

        public Criteria andFUsernoNotBetween(String value1, String value2) {
            addCriterion("f_userno not between", value1, value2, "fUserno");
            return (Criteria) this;
        }

        public Criteria andFJoinIdIsNull() {
            addCriterion("f_join_id is null");
            return (Criteria) this;
        }

        public Criteria andFJoinIdIsNotNull() {
            addCriterion("f_join_id is not null");
            return (Criteria) this;
        }

        public Criteria andFJoinIdEqualTo(Long value) {
            addCriterion("f_join_id =", value, "fJoinId");
            return (Criteria) this;
        }

        public Criteria andFJoinIdNotEqualTo(Long value) {
            addCriterion("f_join_id <>", value, "fJoinId");
            return (Criteria) this;
        }

        public Criteria andFJoinIdGreaterThan(Long value) {
            addCriterion("f_join_id >", value, "fJoinId");
            return (Criteria) this;
        }

        public Criteria andFJoinIdGreaterThanOrEqualTo(Long value) {
            addCriterion("f_join_id >=", value, "fJoinId");
            return (Criteria) this;
        }

        public Criteria andFJoinIdLessThan(Long value) {
            addCriterion("f_join_id <", value, "fJoinId");
            return (Criteria) this;
        }

        public Criteria andFJoinIdLessThanOrEqualTo(Long value) {
            addCriterion("f_join_id <=", value, "fJoinId");
            return (Criteria) this;
        }

        public Criteria andFJoinIdIn(List<Long> values) {
            addCriterion("f_join_id in", values, "fJoinId");
            return (Criteria) this;
        }

        public Criteria andFJoinIdNotIn(List<Long> values) {
            addCriterion("f_join_id not in", values, "fJoinId");
            return (Criteria) this;
        }

        public Criteria andFJoinIdBetween(Long value1, Long value2) {
            addCriterion("f_join_id between", value1, value2, "fJoinId");
            return (Criteria) this;
        }

        public Criteria andFJoinIdNotBetween(Long value1, Long value2) {
            addCriterion("f_join_id not between", value1, value2, "fJoinId");
            return (Criteria) this;
        }
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：student_capability_evaluation..t_user
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * TUser
     * Criterion
     * 数据库表：student_capability_evaluation..t_user
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