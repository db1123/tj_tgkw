package fun.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TLogActionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * zsysj_server_220310..t_log_action
     * @mbg.generated
     */
    public TLogActionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * 设置排序字段
     * zsysj_server_220310..t_log_action
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * zsysj_server_220310..t_log_action
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * zsysj_server_220310..t_log_action
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * zsysj_server_220310..t_log_action
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * zsysj_server_220310..t_log_action
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * zsysj_server_220310..t_log_action
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * zsysj_server_220310..t_log_action
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
     * zsysj_server_220310..t_log_action
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
     * zsysj_server_220310..t_log_action
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * zsysj_server_220310..t_log_action
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TLogAction
     * GeneratedCriteria
     * 数据库表：zsysj_server_220310..t_log_action
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

        public Criteria andFUserTypeIsNull() {
            addCriterion("f_user_type is null");
            return (Criteria) this;
        }

        public Criteria andFUserTypeIsNotNull() {
            addCriterion("f_user_type is not null");
            return (Criteria) this;
        }

        public Criteria andFUserTypeEqualTo(Integer value) {
            addCriterion("f_user_type =", value, "fUserType");
            return (Criteria) this;
        }

        public Criteria andFUserTypeNotEqualTo(Integer value) {
            addCriterion("f_user_type <>", value, "fUserType");
            return (Criteria) this;
        }

        public Criteria andFUserTypeGreaterThan(Integer value) {
            addCriterion("f_user_type >", value, "fUserType");
            return (Criteria) this;
        }

        public Criteria andFUserTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("f_user_type >=", value, "fUserType");
            return (Criteria) this;
        }

        public Criteria andFUserTypeLessThan(Integer value) {
            addCriterion("f_user_type <", value, "fUserType");
            return (Criteria) this;
        }

        public Criteria andFUserTypeLessThanOrEqualTo(Integer value) {
            addCriterion("f_user_type <=", value, "fUserType");
            return (Criteria) this;
        }

        public Criteria andFUserTypeIn(List<Integer> values) {
            addCriterion("f_user_type in", values, "fUserType");
            return (Criteria) this;
        }

        public Criteria andFUserTypeNotIn(List<Integer> values) {
            addCriterion("f_user_type not in", values, "fUserType");
            return (Criteria) this;
        }

        public Criteria andFUserTypeBetween(Integer value1, Integer value2) {
            addCriterion("f_user_type between", value1, value2, "fUserType");
            return (Criteria) this;
        }

        public Criteria andFUserTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("f_user_type not between", value1, value2, "fUserType");
            return (Criteria) this;
        }

        public Criteria andFUserIdIsNull() {
            addCriterion("f_user_id is null");
            return (Criteria) this;
        }

        public Criteria andFUserIdIsNotNull() {
            addCriterion("f_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andFUserIdEqualTo(Long value) {
            addCriterion("f_user_id =", value, "fUserId");
            return (Criteria) this;
        }

        public Criteria andFUserIdNotEqualTo(Long value) {
            addCriterion("f_user_id <>", value, "fUserId");
            return (Criteria) this;
        }

        public Criteria andFUserIdGreaterThan(Long value) {
            addCriterion("f_user_id >", value, "fUserId");
            return (Criteria) this;
        }

        public Criteria andFUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("f_user_id >=", value, "fUserId");
            return (Criteria) this;
        }

        public Criteria andFUserIdLessThan(Long value) {
            addCriterion("f_user_id <", value, "fUserId");
            return (Criteria) this;
        }

        public Criteria andFUserIdLessThanOrEqualTo(Long value) {
            addCriterion("f_user_id <=", value, "fUserId");
            return (Criteria) this;
        }

        public Criteria andFUserIdIn(List<Long> values) {
            addCriterion("f_user_id in", values, "fUserId");
            return (Criteria) this;
        }

        public Criteria andFUserIdNotIn(List<Long> values) {
            addCriterion("f_user_id not in", values, "fUserId");
            return (Criteria) this;
        }

        public Criteria andFUserIdBetween(Long value1, Long value2) {
            addCriterion("f_user_id between", value1, value2, "fUserId");
            return (Criteria) this;
        }

        public Criteria andFUserIdNotBetween(Long value1, Long value2) {
            addCriterion("f_user_id not between", value1, value2, "fUserId");
            return (Criteria) this;
        }

        public Criteria andFUserNameIsNull() {
            addCriterion("f_user_name is null");
            return (Criteria) this;
        }

        public Criteria andFUserNameIsNotNull() {
            addCriterion("f_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andFUserNameEqualTo(String value) {
            addCriterion("f_user_name =", value, "fUserName");
            return (Criteria) this;
        }

        public Criteria andFUserNameNotEqualTo(String value) {
            addCriterion("f_user_name <>", value, "fUserName");
            return (Criteria) this;
        }

        public Criteria andFUserNameGreaterThan(String value) {
            addCriterion("f_user_name >", value, "fUserName");
            return (Criteria) this;
        }

        public Criteria andFUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("f_user_name >=", value, "fUserName");
            return (Criteria) this;
        }

        public Criteria andFUserNameLessThan(String value) {
            addCriterion("f_user_name <", value, "fUserName");
            return (Criteria) this;
        }

        public Criteria andFUserNameLessThanOrEqualTo(String value) {
            addCriterion("f_user_name <=", value, "fUserName");
            return (Criteria) this;
        }

        public Criteria andFUserNameLike(String value) {
            addCriterion("f_user_name like", value, "fUserName");
            return (Criteria) this;
        }

        public Criteria andFUserNameNotLike(String value) {
            addCriterion("f_user_name not like", value, "fUserName");
            return (Criteria) this;
        }

        public Criteria andFUserNameIn(List<String> values) {
            addCriterion("f_user_name in", values, "fUserName");
            return (Criteria) this;
        }

        public Criteria andFUserNameNotIn(List<String> values) {
            addCriterion("f_user_name not in", values, "fUserName");
            return (Criteria) this;
        }

        public Criteria andFUserNameBetween(String value1, String value2) {
            addCriterion("f_user_name between", value1, value2, "fUserName");
            return (Criteria) this;
        }

        public Criteria andFUserNameNotBetween(String value1, String value2) {
            addCriterion("f_user_name not between", value1, value2, "fUserName");
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

        public Criteria andFPathIsNull() {
            addCriterion("f_path is null");
            return (Criteria) this;
        }

        public Criteria andFPathIsNotNull() {
            addCriterion("f_path is not null");
            return (Criteria) this;
        }

        public Criteria andFPathEqualTo(String value) {
            addCriterion("f_path =", value, "fPath");
            return (Criteria) this;
        }

        public Criteria andFPathNotEqualTo(String value) {
            addCriterion("f_path <>", value, "fPath");
            return (Criteria) this;
        }

        public Criteria andFPathGreaterThan(String value) {
            addCriterion("f_path >", value, "fPath");
            return (Criteria) this;
        }

        public Criteria andFPathGreaterThanOrEqualTo(String value) {
            addCriterion("f_path >=", value, "fPath");
            return (Criteria) this;
        }

        public Criteria andFPathLessThan(String value) {
            addCriterion("f_path <", value, "fPath");
            return (Criteria) this;
        }

        public Criteria andFPathLessThanOrEqualTo(String value) {
            addCriterion("f_path <=", value, "fPath");
            return (Criteria) this;
        }

        public Criteria andFPathLike(String value) {
            addCriterion("f_path like", value, "fPath");
            return (Criteria) this;
        }

        public Criteria andFPathNotLike(String value) {
            addCriterion("f_path not like", value, "fPath");
            return (Criteria) this;
        }

        public Criteria andFPathIn(List<String> values) {
            addCriterion("f_path in", values, "fPath");
            return (Criteria) this;
        }

        public Criteria andFPathNotIn(List<String> values) {
            addCriterion("f_path not in", values, "fPath");
            return (Criteria) this;
        }

        public Criteria andFPathBetween(String value1, String value2) {
            addCriterion("f_path between", value1, value2, "fPath");
            return (Criteria) this;
        }

        public Criteria andFPathNotBetween(String value1, String value2) {
            addCriterion("f_path not between", value1, value2, "fPath");
            return (Criteria) this;
        }

        public Criteria andFMemoIsNull() {
            addCriterion("f_memo is null");
            return (Criteria) this;
        }

        public Criteria andFMemoIsNotNull() {
            addCriterion("f_memo is not null");
            return (Criteria) this;
        }

        public Criteria andFMemoEqualTo(String value) {
            addCriterion("f_memo =", value, "fMemo");
            return (Criteria) this;
        }

        public Criteria andFMemoNotEqualTo(String value) {
            addCriterion("f_memo <>", value, "fMemo");
            return (Criteria) this;
        }

        public Criteria andFMemoGreaterThan(String value) {
            addCriterion("f_memo >", value, "fMemo");
            return (Criteria) this;
        }

        public Criteria andFMemoGreaterThanOrEqualTo(String value) {
            addCriterion("f_memo >=", value, "fMemo");
            return (Criteria) this;
        }

        public Criteria andFMemoLessThan(String value) {
            addCriterion("f_memo <", value, "fMemo");
            return (Criteria) this;
        }

        public Criteria andFMemoLessThanOrEqualTo(String value) {
            addCriterion("f_memo <=", value, "fMemo");
            return (Criteria) this;
        }

        public Criteria andFMemoLike(String value) {
            addCriterion("f_memo like", value, "fMemo");
            return (Criteria) this;
        }

        public Criteria andFMemoNotLike(String value) {
            addCriterion("f_memo not like", value, "fMemo");
            return (Criteria) this;
        }

        public Criteria andFMemoIn(List<String> values) {
            addCriterion("f_memo in", values, "fMemo");
            return (Criteria) this;
        }

        public Criteria andFMemoNotIn(List<String> values) {
            addCriterion("f_memo not in", values, "fMemo");
            return (Criteria) this;
        }

        public Criteria andFMemoBetween(String value1, String value2) {
            addCriterion("f_memo between", value1, value2, "fMemo");
            return (Criteria) this;
        }

        public Criteria andFMemoNotBetween(String value1, String value2) {
            addCriterion("f_memo not between", value1, value2, "fMemo");
            return (Criteria) this;
        }
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：zsysj_server_220310..t_log_action
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * TLogAction
     * Criterion
     * 数据库表：zsysj_server_220310..t_log_action
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