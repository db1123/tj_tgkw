package fun.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TSolrProcessLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * teaching_diversity..t_solr_process_log
     * @mbg.generated
     */
    public TSolrProcessLogExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * 设置排序字段
     * teaching_diversity..t_solr_process_log
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * teaching_diversity..t_solr_process_log
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * teaching_diversity..t_solr_process_log
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * teaching_diversity..t_solr_process_log
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * teaching_diversity..t_solr_process_log
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * teaching_diversity..t_solr_process_log
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * teaching_diversity..t_solr_process_log
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
     * teaching_diversity..t_solr_process_log
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
     * teaching_diversity..t_solr_process_log
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * teaching_diversity..t_solr_process_log
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TSolrProcessLog
     * GeneratedCriteria
     * 数据库表：teaching_diversity..t_solr_process_log
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

        public Criteria andFsourcetableIsNull() {
            addCriterion("FSourceTable is null");
            return (Criteria) this;
        }

        public Criteria andFsourcetableIsNotNull() {
            addCriterion("FSourceTable is not null");
            return (Criteria) this;
        }

        public Criteria andFsourcetableEqualTo(String value) {
            addCriterion("FSourceTable =", value, "fsourcetable");
            return (Criteria) this;
        }

        public Criteria andFsourcetableNotEqualTo(String value) {
            addCriterion("FSourceTable <>", value, "fsourcetable");
            return (Criteria) this;
        }

        public Criteria andFsourcetableGreaterThan(String value) {
            addCriterion("FSourceTable >", value, "fsourcetable");
            return (Criteria) this;
        }

        public Criteria andFsourcetableGreaterThanOrEqualTo(String value) {
            addCriterion("FSourceTable >=", value, "fsourcetable");
            return (Criteria) this;
        }

        public Criteria andFsourcetableLessThan(String value) {
            addCriterion("FSourceTable <", value, "fsourcetable");
            return (Criteria) this;
        }

        public Criteria andFsourcetableLessThanOrEqualTo(String value) {
            addCriterion("FSourceTable <=", value, "fsourcetable");
            return (Criteria) this;
        }

        public Criteria andFsourcetableLike(String value) {
            addCriterion("FSourceTable like", value, "fsourcetable");
            return (Criteria) this;
        }

        public Criteria andFsourcetableNotLike(String value) {
            addCriterion("FSourceTable not like", value, "fsourcetable");
            return (Criteria) this;
        }

        public Criteria andFsourcetableIn(List<String> values) {
            addCriterion("FSourceTable in", values, "fsourcetable");
            return (Criteria) this;
        }

        public Criteria andFsourcetableNotIn(List<String> values) {
            addCriterion("FSourceTable not in", values, "fsourcetable");
            return (Criteria) this;
        }

        public Criteria andFsourcetableBetween(String value1, String value2) {
            addCriterion("FSourceTable between", value1, value2, "fsourcetable");
            return (Criteria) this;
        }

        public Criteria andFsourcetableNotBetween(String value1, String value2) {
            addCriterion("FSourceTable not between", value1, value2, "fsourcetable");
            return (Criteria) this;
        }

        public Criteria andFsourceidIsNull() {
            addCriterion("FSourceID is null");
            return (Criteria) this;
        }

        public Criteria andFsourceidIsNotNull() {
            addCriterion("FSourceID is not null");
            return (Criteria) this;
        }

        public Criteria andFsourceidEqualTo(Long value) {
            addCriterion("FSourceID =", value, "fsourceid");
            return (Criteria) this;
        }

        public Criteria andFsourceidNotEqualTo(Long value) {
            addCriterion("FSourceID <>", value, "fsourceid");
            return (Criteria) this;
        }

        public Criteria andFsourceidGreaterThan(Long value) {
            addCriterion("FSourceID >", value, "fsourceid");
            return (Criteria) this;
        }

        public Criteria andFsourceidGreaterThanOrEqualTo(Long value) {
            addCriterion("FSourceID >=", value, "fsourceid");
            return (Criteria) this;
        }

        public Criteria andFsourceidLessThan(Long value) {
            addCriterion("FSourceID <", value, "fsourceid");
            return (Criteria) this;
        }

        public Criteria andFsourceidLessThanOrEqualTo(Long value) {
            addCriterion("FSourceID <=", value, "fsourceid");
            return (Criteria) this;
        }

        public Criteria andFsourceidIn(List<Long> values) {
            addCriterion("FSourceID in", values, "fsourceid");
            return (Criteria) this;
        }

        public Criteria andFsourceidNotIn(List<Long> values) {
            addCriterion("FSourceID not in", values, "fsourceid");
            return (Criteria) this;
        }

        public Criteria andFsourceidBetween(Long value1, Long value2) {
            addCriterion("FSourceID between", value1, value2, "fsourceid");
            return (Criteria) this;
        }

        public Criteria andFsourceidNotBetween(Long value1, Long value2) {
            addCriterion("FSourceID not between", value1, value2, "fsourceid");
            return (Criteria) this;
        }

        public Criteria andFprocesstimeIsNull() {
            addCriterion("FProcessTime is null");
            return (Criteria) this;
        }

        public Criteria andFprocesstimeIsNotNull() {
            addCriterion("FProcessTime is not null");
            return (Criteria) this;
        }

        public Criteria andFprocesstimeEqualTo(Date value) {
            addCriterion("FProcessTime =", value, "fprocesstime");
            return (Criteria) this;
        }

        public Criteria andFprocesstimeNotEqualTo(Date value) {
            addCriterion("FProcessTime <>", value, "fprocesstime");
            return (Criteria) this;
        }

        public Criteria andFprocesstimeGreaterThan(Date value) {
            addCriterion("FProcessTime >", value, "fprocesstime");
            return (Criteria) this;
        }

        public Criteria andFprocesstimeGreaterThanOrEqualTo(Date value) {
            addCriterion("FProcessTime >=", value, "fprocesstime");
            return (Criteria) this;
        }

        public Criteria andFprocesstimeLessThan(Date value) {
            addCriterion("FProcessTime <", value, "fprocesstime");
            return (Criteria) this;
        }

        public Criteria andFprocesstimeLessThanOrEqualTo(Date value) {
            addCriterion("FProcessTime <=", value, "fprocesstime");
            return (Criteria) this;
        }

        public Criteria andFprocesstimeIn(List<Date> values) {
            addCriterion("FProcessTime in", values, "fprocesstime");
            return (Criteria) this;
        }

        public Criteria andFprocesstimeNotIn(List<Date> values) {
            addCriterion("FProcessTime not in", values, "fprocesstime");
            return (Criteria) this;
        }

        public Criteria andFprocesstimeBetween(Date value1, Date value2) {
            addCriterion("FProcessTime between", value1, value2, "fprocesstime");
            return (Criteria) this;
        }

        public Criteria andFprocesstimeNotBetween(Date value1, Date value2) {
            addCriterion("FProcessTime not between", value1, value2, "fprocesstime");
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
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：teaching_diversity..t_solr_process_log
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * TSolrProcessLog
     * Criterion
     * 数据库表：teaching_diversity..t_solr_process_log
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