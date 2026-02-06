package fun.server.model;

import java.util.ArrayList;
import java.util.List;

public class TMenuOptionsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * t_menu_options
     * @mbg.generated
     */
    public TMenuOptionsExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * 设置排序字段
     * t_menu_options
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * t_menu_options
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * t_menu_options
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * t_menu_options
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * t_menu_options
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * t_menu_options
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * t_menu_options
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
     * t_menu_options
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
     * t_menu_options
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * t_menu_options
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TMenuOptions
     * GeneratedCriteria
     * 数据库表：t_menu_options
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

        public Criteria andFTypeIdIsNull() {
            addCriterion("f_type_id is null");
            return (Criteria) this;
        }

        public Criteria andFTypeIdIsNotNull() {
            addCriterion("f_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andFTypeIdEqualTo(Long value) {
            addCriterion("f_type_id =", value, "fTypeId");
            return (Criteria) this;
        }

        public Criteria andFTypeIdNotEqualTo(Long value) {
            addCriterion("f_type_id <>", value, "fTypeId");
            return (Criteria) this;
        }

        public Criteria andFTypeIdGreaterThan(Long value) {
            addCriterion("f_type_id >", value, "fTypeId");
            return (Criteria) this;
        }

        public Criteria andFTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("f_type_id >=", value, "fTypeId");
            return (Criteria) this;
        }

        public Criteria andFTypeIdLessThan(Long value) {
            addCriterion("f_type_id <", value, "fTypeId");
            return (Criteria) this;
        }

        public Criteria andFTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("f_type_id <=", value, "fTypeId");
            return (Criteria) this;
        }

        public Criteria andFTypeIdIn(List<Long> values) {
            addCriterion("f_type_id in", values, "fTypeId");
            return (Criteria) this;
        }

        public Criteria andFTypeIdNotIn(List<Long> values) {
            addCriterion("f_type_id not in", values, "fTypeId");
            return (Criteria) this;
        }

        public Criteria andFTypeIdBetween(Long value1, Long value2) {
            addCriterion("f_type_id between", value1, value2, "fTypeId");
            return (Criteria) this;
        }

        public Criteria andFTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("f_type_id not between", value1, value2, "fTypeId");
            return (Criteria) this;
        }

        public Criteria andFPIdIsNull() {
            addCriterion("f_p_id is null");
            return (Criteria) this;
        }

        public Criteria andFPIdIsNotNull() {
            addCriterion("f_p_id is not null");
            return (Criteria) this;
        }

        public Criteria andFPIdEqualTo(Long value) {
            addCriterion("f_p_id =", value, "fPId");
            return (Criteria) this;
        }

        public Criteria andFPIdNotEqualTo(Long value) {
            addCriterion("f_p_id <>", value, "fPId");
            return (Criteria) this;
        }

        public Criteria andFPIdGreaterThan(Long value) {
            addCriterion("f_p_id >", value, "fPId");
            return (Criteria) this;
        }

        public Criteria andFPIdGreaterThanOrEqualTo(Long value) {
            addCriterion("f_p_id >=", value, "fPId");
            return (Criteria) this;
        }

        public Criteria andFPIdLessThan(Long value) {
            addCriterion("f_p_id <", value, "fPId");
            return (Criteria) this;
        }

        public Criteria andFPIdLessThanOrEqualTo(Long value) {
            addCriterion("f_p_id <=", value, "fPId");
            return (Criteria) this;
        }

        public Criteria andFPIdIn(List<Long> values) {
            addCriterion("f_p_id in", values, "fPId");
            return (Criteria) this;
        }

        public Criteria andFPIdNotIn(List<Long> values) {
            addCriterion("f_p_id not in", values, "fPId");
            return (Criteria) this;
        }

        public Criteria andFPIdBetween(Long value1, Long value2) {
            addCriterion("f_p_id between", value1, value2, "fPId");
            return (Criteria) this;
        }

        public Criteria andFPIdNotBetween(Long value1, Long value2) {
            addCriterion("f_p_id not between", value1, value2, "fPId");
            return (Criteria) this;
        }

        public Criteria andFIsLeafIsNull() {
            addCriterion("f_is_leaf is null");
            return (Criteria) this;
        }

        public Criteria andFIsLeafIsNotNull() {
            addCriterion("f_is_leaf is not null");
            return (Criteria) this;
        }

        public Criteria andFIsLeafEqualTo(Integer value) {
            addCriterion("f_is_leaf =", value, "fIsLeaf");
            return (Criteria) this;
        }

        public Criteria andFIsLeafNotEqualTo(Integer value) {
            addCriterion("f_is_leaf <>", value, "fIsLeaf");
            return (Criteria) this;
        }

        public Criteria andFIsLeafGreaterThan(Integer value) {
            addCriterion("f_is_leaf >", value, "fIsLeaf");
            return (Criteria) this;
        }

        public Criteria andFIsLeafGreaterThanOrEqualTo(Integer value) {
            addCriterion("f_is_leaf >=", value, "fIsLeaf");
            return (Criteria) this;
        }

        public Criteria andFIsLeafLessThan(Integer value) {
            addCriterion("f_is_leaf <", value, "fIsLeaf");
            return (Criteria) this;
        }

        public Criteria andFIsLeafLessThanOrEqualTo(Integer value) {
            addCriterion("f_is_leaf <=", value, "fIsLeaf");
            return (Criteria) this;
        }

        public Criteria andFIsLeafIn(List<Integer> values) {
            addCriterion("f_is_leaf in", values, "fIsLeaf");
            return (Criteria) this;
        }

        public Criteria andFIsLeafNotIn(List<Integer> values) {
            addCriterion("f_is_leaf not in", values, "fIsLeaf");
            return (Criteria) this;
        }

        public Criteria andFIsLeafBetween(Integer value1, Integer value2) {
            addCriterion("f_is_leaf between", value1, value2, "fIsLeaf");
            return (Criteria) this;
        }

        public Criteria andFIsLeafNotBetween(Integer value1, Integer value2) {
            addCriterion("f_is_leaf not between", value1, value2, "fIsLeaf");
            return (Criteria) this;
        }

        public Criteria andFNoIsNull() {
            addCriterion("f_no is null");
            return (Criteria) this;
        }

        public Criteria andFNoIsNotNull() {
            addCriterion("f_no is not null");
            return (Criteria) this;
        }

        public Criteria andFNoEqualTo(String value) {
            addCriterion("f_no =", value, "fNo");
            return (Criteria) this;
        }

        public Criteria andFNoNotEqualTo(String value) {
            addCriterion("f_no <>", value, "fNo");
            return (Criteria) this;
        }

        public Criteria andFNoGreaterThan(String value) {
            addCriterion("f_no >", value, "fNo");
            return (Criteria) this;
        }

        public Criteria andFNoGreaterThanOrEqualTo(String value) {
            addCriterion("f_no >=", value, "fNo");
            return (Criteria) this;
        }

        public Criteria andFNoLessThan(String value) {
            addCriterion("f_no <", value, "fNo");
            return (Criteria) this;
        }

        public Criteria andFNoLessThanOrEqualTo(String value) {
            addCriterion("f_no <=", value, "fNo");
            return (Criteria) this;
        }

        public Criteria andFNoLike(String value) {
            addCriterion("f_no like", value, "fNo");
            return (Criteria) this;
        }

        public Criteria andFNoNotLike(String value) {
            addCriterion("f_no not like", value, "fNo");
            return (Criteria) this;
        }

        public Criteria andFNoIn(List<String> values) {
            addCriterion("f_no in", values, "fNo");
            return (Criteria) this;
        }

        public Criteria andFNoNotIn(List<String> values) {
            addCriterion("f_no not in", values, "fNo");
            return (Criteria) this;
        }

        public Criteria andFNoBetween(String value1, String value2) {
            addCriterion("f_no between", value1, value2, "fNo");
            return (Criteria) this;
        }

        public Criteria andFNoNotBetween(String value1, String value2) {
            addCriterion("f_no not between", value1, value2, "fNo");
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

        public Criteria andFIconIsNull() {
            addCriterion("f_icon is null");
            return (Criteria) this;
        }

        public Criteria andFIconIsNotNull() {
            addCriterion("f_icon is not null");
            return (Criteria) this;
        }

        public Criteria andFIconEqualTo(String value) {
            addCriterion("f_icon =", value, "fIcon");
            return (Criteria) this;
        }

        public Criteria andFIconNotEqualTo(String value) {
            addCriterion("f_icon <>", value, "fIcon");
            return (Criteria) this;
        }

        public Criteria andFIconGreaterThan(String value) {
            addCriterion("f_icon >", value, "fIcon");
            return (Criteria) this;
        }

        public Criteria andFIconGreaterThanOrEqualTo(String value) {
            addCriterion("f_icon >=", value, "fIcon");
            return (Criteria) this;
        }

        public Criteria andFIconLessThan(String value) {
            addCriterion("f_icon <", value, "fIcon");
            return (Criteria) this;
        }

        public Criteria andFIconLessThanOrEqualTo(String value) {
            addCriterion("f_icon <=", value, "fIcon");
            return (Criteria) this;
        }

        public Criteria andFIconLike(String value) {
            addCriterion("f_icon like", value, "fIcon");
            return (Criteria) this;
        }

        public Criteria andFIconNotLike(String value) {
            addCriterion("f_icon not like", value, "fIcon");
            return (Criteria) this;
        }

        public Criteria andFIconIn(List<String> values) {
            addCriterion("f_icon in", values, "fIcon");
            return (Criteria) this;
        }

        public Criteria andFIconNotIn(List<String> values) {
            addCriterion("f_icon not in", values, "fIcon");
            return (Criteria) this;
        }

        public Criteria andFIconBetween(String value1, String value2) {
            addCriterion("f_icon between", value1, value2, "fIcon");
            return (Criteria) this;
        }

        public Criteria andFIconNotBetween(String value1, String value2) {
            addCriterion("f_icon not between", value1, value2, "fIcon");
            return (Criteria) this;
        }

        public Criteria andFStyleIsNull() {
            addCriterion("f_style is null");
            return (Criteria) this;
        }

        public Criteria andFStyleIsNotNull() {
            addCriterion("f_style is not null");
            return (Criteria) this;
        }

        public Criteria andFStyleEqualTo(String value) {
            addCriterion("f_style =", value, "fStyle");
            return (Criteria) this;
        }

        public Criteria andFStyleNotEqualTo(String value) {
            addCriterion("f_style <>", value, "fStyle");
            return (Criteria) this;
        }

        public Criteria andFStyleGreaterThan(String value) {
            addCriterion("f_style >", value, "fStyle");
            return (Criteria) this;
        }

        public Criteria andFStyleGreaterThanOrEqualTo(String value) {
            addCriterion("f_style >=", value, "fStyle");
            return (Criteria) this;
        }

        public Criteria andFStyleLessThan(String value) {
            addCriterion("f_style <", value, "fStyle");
            return (Criteria) this;
        }

        public Criteria andFStyleLessThanOrEqualTo(String value) {
            addCriterion("f_style <=", value, "fStyle");
            return (Criteria) this;
        }

        public Criteria andFStyleLike(String value) {
            addCriterion("f_style like", value, "fStyle");
            return (Criteria) this;
        }

        public Criteria andFStyleNotLike(String value) {
            addCriterion("f_style not like", value, "fStyle");
            return (Criteria) this;
        }

        public Criteria andFStyleIn(List<String> values) {
            addCriterion("f_style in", values, "fStyle");
            return (Criteria) this;
        }

        public Criteria andFStyleNotIn(List<String> values) {
            addCriterion("f_style not in", values, "fStyle");
            return (Criteria) this;
        }

        public Criteria andFStyleBetween(String value1, String value2) {
            addCriterion("f_style between", value1, value2, "fStyle");
            return (Criteria) this;
        }

        public Criteria andFStyleNotBetween(String value1, String value2) {
            addCriterion("f_style not between", value1, value2, "fStyle");
            return (Criteria) this;
        }

        public Criteria andFUrlIsNull() {
            addCriterion("f_url is null");
            return (Criteria) this;
        }

        public Criteria andFUrlIsNotNull() {
            addCriterion("f_url is not null");
            return (Criteria) this;
        }

        public Criteria andFUrlEqualTo(String value) {
            addCriterion("f_url =", value, "fUrl");
            return (Criteria) this;
        }

        public Criteria andFUrlNotEqualTo(String value) {
            addCriterion("f_url <>", value, "fUrl");
            return (Criteria) this;
        }

        public Criteria andFUrlGreaterThan(String value) {
            addCriterion("f_url >", value, "fUrl");
            return (Criteria) this;
        }

        public Criteria andFUrlGreaterThanOrEqualTo(String value) {
            addCriterion("f_url >=", value, "fUrl");
            return (Criteria) this;
        }

        public Criteria andFUrlLessThan(String value) {
            addCriterion("f_url <", value, "fUrl");
            return (Criteria) this;
        }

        public Criteria andFUrlLessThanOrEqualTo(String value) {
            addCriterion("f_url <=", value, "fUrl");
            return (Criteria) this;
        }

        public Criteria andFUrlLike(String value) {
            addCriterion("f_url like", value, "fUrl");
            return (Criteria) this;
        }

        public Criteria andFUrlNotLike(String value) {
            addCriterion("f_url not like", value, "fUrl");
            return (Criteria) this;
        }

        public Criteria andFUrlIn(List<String> values) {
            addCriterion("f_url in", values, "fUrl");
            return (Criteria) this;
        }

        public Criteria andFUrlNotIn(List<String> values) {
            addCriterion("f_url not in", values, "fUrl");
            return (Criteria) this;
        }

        public Criteria andFUrlBetween(String value1, String value2) {
            addCriterion("f_url between", value1, value2, "fUrl");
            return (Criteria) this;
        }

        public Criteria andFUrlNotBetween(String value1, String value2) {
            addCriterion("f_url not between", value1, value2, "fUrl");
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

        public Criteria andFOrderIsNull() {
            addCriterion("f_order is null");
            return (Criteria) this;
        }

        public Criteria andFOrderIsNotNull() {
            addCriterion("f_order is not null");
            return (Criteria) this;
        }

        public Criteria andFOrderEqualTo(Integer value) {
            addCriterion("f_order =", value, "fOrder");
            return (Criteria) this;
        }

        public Criteria andFOrderNotEqualTo(Integer value) {
            addCriterion("f_order <>", value, "fOrder");
            return (Criteria) this;
        }

        public Criteria andFOrderGreaterThan(Integer value) {
            addCriterion("f_order >", value, "fOrder");
            return (Criteria) this;
        }

        public Criteria andFOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("f_order >=", value, "fOrder");
            return (Criteria) this;
        }

        public Criteria andFOrderLessThan(Integer value) {
            addCriterion("f_order <", value, "fOrder");
            return (Criteria) this;
        }

        public Criteria andFOrderLessThanOrEqualTo(Integer value) {
            addCriterion("f_order <=", value, "fOrder");
            return (Criteria) this;
        }

        public Criteria andFOrderIn(List<Integer> values) {
            addCriterion("f_order in", values, "fOrder");
            return (Criteria) this;
        }

        public Criteria andFOrderNotIn(List<Integer> values) {
            addCriterion("f_order not in", values, "fOrder");
            return (Criteria) this;
        }

        public Criteria andFOrderBetween(Integer value1, Integer value2) {
            addCriterion("f_order between", value1, value2, "fOrder");
            return (Criteria) this;
        }

        public Criteria andFOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("f_order not between", value1, value2, "fOrder");
            return (Criteria) this;
        }

        public Criteria andFMenuTypeIsNull() {
            addCriterion("f_menu_type is null");
            return (Criteria) this;
        }

        public Criteria andFMenuTypeIsNotNull() {
            addCriterion("f_menu_type is not null");
            return (Criteria) this;
        }

        public Criteria andFMenuTypeEqualTo(Integer value) {
            addCriterion("f_menu_type =", value, "fMenuType");
            return (Criteria) this;
        }

        public Criteria andFMenuTypeNotEqualTo(Integer value) {
            addCriterion("f_menu_type <>", value, "fMenuType");
            return (Criteria) this;
        }

        public Criteria andFMenuTypeGreaterThan(Integer value) {
            addCriterion("f_menu_type >", value, "fMenuType");
            return (Criteria) this;
        }

        public Criteria andFMenuTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("f_menu_type >=", value, "fMenuType");
            return (Criteria) this;
        }

        public Criteria andFMenuTypeLessThan(Integer value) {
            addCriterion("f_menu_type <", value, "fMenuType");
            return (Criteria) this;
        }

        public Criteria andFMenuTypeLessThanOrEqualTo(Integer value) {
            addCriterion("f_menu_type <=", value, "fMenuType");
            return (Criteria) this;
        }

        public Criteria andFMenuTypeIn(List<Integer> values) {
            addCriterion("f_menu_type in", values, "fMenuType");
            return (Criteria) this;
        }

        public Criteria andFMenuTypeNotIn(List<Integer> values) {
            addCriterion("f_menu_type not in", values, "fMenuType");
            return (Criteria) this;
        }

        public Criteria andFMenuTypeBetween(Integer value1, Integer value2) {
            addCriterion("f_menu_type between", value1, value2, "fMenuType");
            return (Criteria) this;
        }

        public Criteria andFMenuTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("f_menu_type not between", value1, value2, "fMenuType");
            return (Criteria) this;
        }
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：t_menu_options
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * TMenuOptions
     * Criterion
     * 数据库表：t_menu_options
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