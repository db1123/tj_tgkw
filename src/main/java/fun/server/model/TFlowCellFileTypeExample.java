package fun.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TFlowCellFileTypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * pdm_server..t_flow_cell_file_type
     * @mbg.generated
     */
    public TFlowCellFileTypeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * 设置排序字段
     * pdm_server..t_flow_cell_file_type
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * pdm_server..t_flow_cell_file_type
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * pdm_server..t_flow_cell_file_type
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * pdm_server..t_flow_cell_file_type
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * pdm_server..t_flow_cell_file_type
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * pdm_server..t_flow_cell_file_type
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * pdm_server..t_flow_cell_file_type
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
     * pdm_server..t_flow_cell_file_type
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
     * pdm_server..t_flow_cell_file_type
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * pdm_server..t_flow_cell_file_type
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TFlowCellFileType
     * GeneratedCriteria
     * 数据库表：pdm_server..t_flow_cell_file_type
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

        public Criteria andFCidIsNull() {
            addCriterion("f_cid is null");
            return (Criteria) this;
        }

        public Criteria andFCidIsNotNull() {
            addCriterion("f_cid is not null");
            return (Criteria) this;
        }

        public Criteria andFCidEqualTo(Long value) {
            addCriterion("f_cid =", value, "fCid");
            return (Criteria) this;
        }

        public Criteria andFCidNotEqualTo(Long value) {
            addCriterion("f_cid <>", value, "fCid");
            return (Criteria) this;
        }

        public Criteria andFCidGreaterThan(Long value) {
            addCriterion("f_cid >", value, "fCid");
            return (Criteria) this;
        }

        public Criteria andFCidGreaterThanOrEqualTo(Long value) {
            addCriterion("f_cid >=", value, "fCid");
            return (Criteria) this;
        }

        public Criteria andFCidLessThan(Long value) {
            addCriterion("f_cid <", value, "fCid");
            return (Criteria) this;
        }

        public Criteria andFCidLessThanOrEqualTo(Long value) {
            addCriterion("f_cid <=", value, "fCid");
            return (Criteria) this;
        }

        public Criteria andFCidIn(List<Long> values) {
            addCriterion("f_cid in", values, "fCid");
            return (Criteria) this;
        }

        public Criteria andFCidNotIn(List<Long> values) {
            addCriterion("f_cid not in", values, "fCid");
            return (Criteria) this;
        }

        public Criteria andFCidBetween(Long value1, Long value2) {
            addCriterion("f_cid between", value1, value2, "fCid");
            return (Criteria) this;
        }

        public Criteria andFCidNotBetween(Long value1, Long value2) {
            addCriterion("f_cid not between", value1, value2, "fCid");
            return (Criteria) this;
        }

        public Criteria andFUidIsNull() {
            addCriterion("f_uid is null");
            return (Criteria) this;
        }

        public Criteria andFUidIsNotNull() {
            addCriterion("f_uid is not null");
            return (Criteria) this;
        }

        public Criteria andFUidEqualTo(Long value) {
            addCriterion("f_uid =", value, "fUid");
            return (Criteria) this;
        }

        public Criteria andFUidNotEqualTo(Long value) {
            addCriterion("f_uid <>", value, "fUid");
            return (Criteria) this;
        }

        public Criteria andFUidGreaterThan(Long value) {
            addCriterion("f_uid >", value, "fUid");
            return (Criteria) this;
        }

        public Criteria andFUidGreaterThanOrEqualTo(Long value) {
            addCriterion("f_uid >=", value, "fUid");
            return (Criteria) this;
        }

        public Criteria andFUidLessThan(Long value) {
            addCriterion("f_uid <", value, "fUid");
            return (Criteria) this;
        }

        public Criteria andFUidLessThanOrEqualTo(Long value) {
            addCriterion("f_uid <=", value, "fUid");
            return (Criteria) this;
        }

        public Criteria andFUidIn(List<Long> values) {
            addCriterion("f_uid in", values, "fUid");
            return (Criteria) this;
        }

        public Criteria andFUidNotIn(List<Long> values) {
            addCriterion("f_uid not in", values, "fUid");
            return (Criteria) this;
        }

        public Criteria andFUidBetween(Long value1, Long value2) {
            addCriterion("f_uid between", value1, value2, "fUid");
            return (Criteria) this;
        }

        public Criteria andFUidNotBetween(Long value1, Long value2) {
            addCriterion("f_uid not between", value1, value2, "fUid");
            return (Criteria) this;
        }

        public Criteria andFCdateIsNull() {
            addCriterion("f_cdate is null");
            return (Criteria) this;
        }

        public Criteria andFCdateIsNotNull() {
            addCriterion("f_cdate is not null");
            return (Criteria) this;
        }

        public Criteria andFCdateEqualTo(Date value) {
            addCriterion("f_cdate =", value, "fCdate");
            return (Criteria) this;
        }

        public Criteria andFCdateNotEqualTo(Date value) {
            addCriterion("f_cdate <>", value, "fCdate");
            return (Criteria) this;
        }

        public Criteria andFCdateGreaterThan(Date value) {
            addCriterion("f_cdate >", value, "fCdate");
            return (Criteria) this;
        }

        public Criteria andFCdateGreaterThanOrEqualTo(Date value) {
            addCriterion("f_cdate >=", value, "fCdate");
            return (Criteria) this;
        }

        public Criteria andFCdateLessThan(Date value) {
            addCriterion("f_cdate <", value, "fCdate");
            return (Criteria) this;
        }

        public Criteria andFCdateLessThanOrEqualTo(Date value) {
            addCriterion("f_cdate <=", value, "fCdate");
            return (Criteria) this;
        }

        public Criteria andFCdateIn(List<Date> values) {
            addCriterion("f_cdate in", values, "fCdate");
            return (Criteria) this;
        }

        public Criteria andFCdateNotIn(List<Date> values) {
            addCriterion("f_cdate not in", values, "fCdate");
            return (Criteria) this;
        }

        public Criteria andFCdateBetween(Date value1, Date value2) {
            addCriterion("f_cdate between", value1, value2, "fCdate");
            return (Criteria) this;
        }

        public Criteria andFCdateNotBetween(Date value1, Date value2) {
            addCriterion("f_cdate not between", value1, value2, "fCdate");
            return (Criteria) this;
        }

        public Criteria andFUdateIsNull() {
            addCriterion("f_udate is null");
            return (Criteria) this;
        }

        public Criteria andFUdateIsNotNull() {
            addCriterion("f_udate is not null");
            return (Criteria) this;
        }

        public Criteria andFUdateEqualTo(Date value) {
            addCriterion("f_udate =", value, "fUdate");
            return (Criteria) this;
        }

        public Criteria andFUdateNotEqualTo(Date value) {
            addCriterion("f_udate <>", value, "fUdate");
            return (Criteria) this;
        }

        public Criteria andFUdateGreaterThan(Date value) {
            addCriterion("f_udate >", value, "fUdate");
            return (Criteria) this;
        }

        public Criteria andFUdateGreaterThanOrEqualTo(Date value) {
            addCriterion("f_udate >=", value, "fUdate");
            return (Criteria) this;
        }

        public Criteria andFUdateLessThan(Date value) {
            addCriterion("f_udate <", value, "fUdate");
            return (Criteria) this;
        }

        public Criteria andFUdateLessThanOrEqualTo(Date value) {
            addCriterion("f_udate <=", value, "fUdate");
            return (Criteria) this;
        }

        public Criteria andFUdateIn(List<Date> values) {
            addCriterion("f_udate in", values, "fUdate");
            return (Criteria) this;
        }

        public Criteria andFUdateNotIn(List<Date> values) {
            addCriterion("f_udate not in", values, "fUdate");
            return (Criteria) this;
        }

        public Criteria andFUdateBetween(Date value1, Date value2) {
            addCriterion("f_udate between", value1, value2, "fUdate");
            return (Criteria) this;
        }

        public Criteria andFUdateNotBetween(Date value1, Date value2) {
            addCriterion("f_udate not between", value1, value2, "fUdate");
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

        public Criteria andFPormIsNull() {
            addCriterion("f_porm is null");
            return (Criteria) this;
        }

        public Criteria andFPormIsNotNull() {
            addCriterion("f_porm is not null");
            return (Criteria) this;
        }

        public Criteria andFPormEqualTo(Integer value) {
            addCriterion("f_porm =", value, "fPorm");
            return (Criteria) this;
        }

        public Criteria andFPormNotEqualTo(Integer value) {
            addCriterion("f_porm <>", value, "fPorm");
            return (Criteria) this;
        }

        public Criteria andFPormGreaterThan(Integer value) {
            addCriterion("f_porm >", value, "fPorm");
            return (Criteria) this;
        }

        public Criteria andFPormGreaterThanOrEqualTo(Integer value) {
            addCriterion("f_porm >=", value, "fPorm");
            return (Criteria) this;
        }

        public Criteria andFPormLessThan(Integer value) {
            addCriterion("f_porm <", value, "fPorm");
            return (Criteria) this;
        }

        public Criteria andFPormLessThanOrEqualTo(Integer value) {
            addCriterion("f_porm <=", value, "fPorm");
            return (Criteria) this;
        }

        public Criteria andFPormIn(List<Integer> values) {
            addCriterion("f_porm in", values, "fPorm");
            return (Criteria) this;
        }

        public Criteria andFPormNotIn(List<Integer> values) {
            addCriterion("f_porm not in", values, "fPorm");
            return (Criteria) this;
        }

        public Criteria andFPormBetween(Integer value1, Integer value2) {
            addCriterion("f_porm between", value1, value2, "fPorm");
            return (Criteria) this;
        }

        public Criteria andFPormNotBetween(Integer value1, Integer value2) {
            addCriterion("f_porm not between", value1, value2, "fPorm");
            return (Criteria) this;
        }

        public Criteria andFFtypeIsNull() {
            addCriterion("f_ftype is null");
            return (Criteria) this;
        }

        public Criteria andFFtypeIsNotNull() {
            addCriterion("f_ftype is not null");
            return (Criteria) this;
        }

        public Criteria andFFtypeEqualTo(Long value) {
            addCriterion("f_ftype =", value, "fFtype");
            return (Criteria) this;
        }

        public Criteria andFFtypeNotEqualTo(Long value) {
            addCriterion("f_ftype <>", value, "fFtype");
            return (Criteria) this;
        }

        public Criteria andFFtypeGreaterThan(Long value) {
            addCriterion("f_ftype >", value, "fFtype");
            return (Criteria) this;
        }

        public Criteria andFFtypeGreaterThanOrEqualTo(Long value) {
            addCriterion("f_ftype >=", value, "fFtype");
            return (Criteria) this;
        }

        public Criteria andFFtypeLessThan(Long value) {
            addCriterion("f_ftype <", value, "fFtype");
            return (Criteria) this;
        }

        public Criteria andFFtypeLessThanOrEqualTo(Long value) {
            addCriterion("f_ftype <=", value, "fFtype");
            return (Criteria) this;
        }

        public Criteria andFFtypeIn(List<Long> values) {
            addCriterion("f_ftype in", values, "fFtype");
            return (Criteria) this;
        }

        public Criteria andFFtypeNotIn(List<Long> values) {
            addCriterion("f_ftype not in", values, "fFtype");
            return (Criteria) this;
        }

        public Criteria andFFtypeBetween(Long value1, Long value2) {
            addCriterion("f_ftype between", value1, value2, "fFtype");
            return (Criteria) this;
        }

        public Criteria andFFtypeNotBetween(Long value1, Long value2) {
            addCriterion("f_ftype not between", value1, value2, "fFtype");
            return (Criteria) this;
        }
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：pdm_server..t_flow_cell_file_type
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * TFlowCellFileType
     * Criterion
     * 数据库表：pdm_server..t_flow_cell_file_type
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