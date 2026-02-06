package fun.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TMessageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * t_message
     * @mbg.generated
     */
    public TMessageExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * 设置排序字段
     * t_message
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * t_message
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * t_message
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * t_message
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * t_message
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * t_message
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * t_message
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
     * t_message
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
     * t_message
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * t_message
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TMessage
     * GeneratedCriteria
     * 数据库表：t_message
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

        public Criteria andFFromUserIdIsNull() {
            addCriterion("f_from_user_id is null");
            return (Criteria) this;
        }

        public Criteria andFFromUserIdIsNotNull() {
            addCriterion("f_from_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andFFromUserIdEqualTo(Long value) {
            addCriterion("f_from_user_id =", value, "fFromUserId");
            return (Criteria) this;
        }

        public Criteria andFFromUserIdNotEqualTo(Long value) {
            addCriterion("f_from_user_id <>", value, "fFromUserId");
            return (Criteria) this;
        }

        public Criteria andFFromUserIdGreaterThan(Long value) {
            addCriterion("f_from_user_id >", value, "fFromUserId");
            return (Criteria) this;
        }

        public Criteria andFFromUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("f_from_user_id >=", value, "fFromUserId");
            return (Criteria) this;
        }

        public Criteria andFFromUserIdLessThan(Long value) {
            addCriterion("f_from_user_id <", value, "fFromUserId");
            return (Criteria) this;
        }

        public Criteria andFFromUserIdLessThanOrEqualTo(Long value) {
            addCriterion("f_from_user_id <=", value, "fFromUserId");
            return (Criteria) this;
        }

        public Criteria andFFromUserIdIn(List<Long> values) {
            addCriterion("f_from_user_id in", values, "fFromUserId");
            return (Criteria) this;
        }

        public Criteria andFFromUserIdNotIn(List<Long> values) {
            addCriterion("f_from_user_id not in", values, "fFromUserId");
            return (Criteria) this;
        }

        public Criteria andFFromUserIdBetween(Long value1, Long value2) {
            addCriterion("f_from_user_id between", value1, value2, "fFromUserId");
            return (Criteria) this;
        }

        public Criteria andFFromUserIdNotBetween(Long value1, Long value2) {
            addCriterion("f_from_user_id not between", value1, value2, "fFromUserId");
            return (Criteria) this;
        }

        public Criteria andFToUserIdIsNull() {
            addCriterion("f_to_user_id is null");
            return (Criteria) this;
        }

        public Criteria andFToUserIdIsNotNull() {
            addCriterion("f_to_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andFToUserIdEqualTo(Long value) {
            addCriterion("f_to_user_id =", value, "fToUserId");
            return (Criteria) this;
        }

        public Criteria andFToUserIdNotEqualTo(Long value) {
            addCriterion("f_to_user_id <>", value, "fToUserId");
            return (Criteria) this;
        }

        public Criteria andFToUserIdGreaterThan(Long value) {
            addCriterion("f_to_user_id >", value, "fToUserId");
            return (Criteria) this;
        }

        public Criteria andFToUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("f_to_user_id >=", value, "fToUserId");
            return (Criteria) this;
        }

        public Criteria andFToUserIdLessThan(Long value) {
            addCriterion("f_to_user_id <", value, "fToUserId");
            return (Criteria) this;
        }

        public Criteria andFToUserIdLessThanOrEqualTo(Long value) {
            addCriterion("f_to_user_id <=", value, "fToUserId");
            return (Criteria) this;
        }

        public Criteria andFToUserIdIn(List<Long> values) {
            addCriterion("f_to_user_id in", values, "fToUserId");
            return (Criteria) this;
        }

        public Criteria andFToUserIdNotIn(List<Long> values) {
            addCriterion("f_to_user_id not in", values, "fToUserId");
            return (Criteria) this;
        }

        public Criteria andFToUserIdBetween(Long value1, Long value2) {
            addCriterion("f_to_user_id between", value1, value2, "fToUserId");
            return (Criteria) this;
        }

        public Criteria andFToUserIdNotBetween(Long value1, Long value2) {
            addCriterion("f_to_user_id not between", value1, value2, "fToUserId");
            return (Criteria) this;
        }

        public Criteria andFTitleIsNull() {
            addCriterion("f_title is null");
            return (Criteria) this;
        }

        public Criteria andFTitleIsNotNull() {
            addCriterion("f_title is not null");
            return (Criteria) this;
        }

        public Criteria andFTitleEqualTo(String value) {
            addCriterion("f_title =", value, "fTitle");
            return (Criteria) this;
        }

        public Criteria andFTitleNotEqualTo(String value) {
            addCriterion("f_title <>", value, "fTitle");
            return (Criteria) this;
        }

        public Criteria andFTitleGreaterThan(String value) {
            addCriterion("f_title >", value, "fTitle");
            return (Criteria) this;
        }

        public Criteria andFTitleGreaterThanOrEqualTo(String value) {
            addCriterion("f_title >=", value, "fTitle");
            return (Criteria) this;
        }

        public Criteria andFTitleLessThan(String value) {
            addCriterion("f_title <", value, "fTitle");
            return (Criteria) this;
        }

        public Criteria andFTitleLessThanOrEqualTo(String value) {
            addCriterion("f_title <=", value, "fTitle");
            return (Criteria) this;
        }

        public Criteria andFTitleLike(String value) {
            addCriterion("f_title like", value, "fTitle");
            return (Criteria) this;
        }

        public Criteria andFTitleNotLike(String value) {
            addCriterion("f_title not like", value, "fTitle");
            return (Criteria) this;
        }

        public Criteria andFTitleIn(List<String> values) {
            addCriterion("f_title in", values, "fTitle");
            return (Criteria) this;
        }

        public Criteria andFTitleNotIn(List<String> values) {
            addCriterion("f_title not in", values, "fTitle");
            return (Criteria) this;
        }

        public Criteria andFTitleBetween(String value1, String value2) {
            addCriterion("f_title between", value1, value2, "fTitle");
            return (Criteria) this;
        }

        public Criteria andFTitleNotBetween(String value1, String value2) {
            addCriterion("f_title not between", value1, value2, "fTitle");
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

        public Criteria andFIsReadIsNull() {
            addCriterion("f_is_read is null");
            return (Criteria) this;
        }

        public Criteria andFIsReadIsNotNull() {
            addCriterion("f_is_read is not null");
            return (Criteria) this;
        }

        public Criteria andFIsReadEqualTo(Integer value) {
            addCriterion("f_is_read =", value, "fIsRead");
            return (Criteria) this;
        }

        public Criteria andFIsReadNotEqualTo(Integer value) {
            addCriterion("f_is_read <>", value, "fIsRead");
            return (Criteria) this;
        }

        public Criteria andFIsReadGreaterThan(Integer value) {
            addCriterion("f_is_read >", value, "fIsRead");
            return (Criteria) this;
        }

        public Criteria andFIsReadGreaterThanOrEqualTo(Integer value) {
            addCriterion("f_is_read >=", value, "fIsRead");
            return (Criteria) this;
        }

        public Criteria andFIsReadLessThan(Integer value) {
            addCriterion("f_is_read <", value, "fIsRead");
            return (Criteria) this;
        }

        public Criteria andFIsReadLessThanOrEqualTo(Integer value) {
            addCriterion("f_is_read <=", value, "fIsRead");
            return (Criteria) this;
        }

        public Criteria andFIsReadIn(List<Integer> values) {
            addCriterion("f_is_read in", values, "fIsRead");
            return (Criteria) this;
        }

        public Criteria andFIsReadNotIn(List<Integer> values) {
            addCriterion("f_is_read not in", values, "fIsRead");
            return (Criteria) this;
        }

        public Criteria andFIsReadBetween(Integer value1, Integer value2) {
            addCriterion("f_is_read between", value1, value2, "fIsRead");
            return (Criteria) this;
        }

        public Criteria andFIsReadNotBetween(Integer value1, Integer value2) {
            addCriterion("f_is_read not between", value1, value2, "fIsRead");
            return (Criteria) this;
        }
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：t_message
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * TMessage
     * Criterion
     * 数据库表：t_message
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