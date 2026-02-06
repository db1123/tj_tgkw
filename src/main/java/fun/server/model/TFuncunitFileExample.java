package fun.server.model;

import java.util.ArrayList;
import java.util.List;

public class TFuncunitFileExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * t_funcunit_file
     * @mbg.generated
     */
    public TFuncunitFileExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * 设置排序字段
     * t_funcunit_file
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * t_funcunit_file
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * t_funcunit_file
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * t_funcunit_file
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * t_funcunit_file
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * t_funcunit_file
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * t_funcunit_file
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
     * t_funcunit_file
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
     * t_funcunit_file
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * t_funcunit_file
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TFuncunitFile
     * GeneratedCriteria
     * 数据库表：t_funcunit_file
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

        public Criteria andFScriptIdIsNull() {
            addCriterion("f_script_id is null");
            return (Criteria) this;
        }

        public Criteria andFScriptIdIsNotNull() {
            addCriterion("f_script_id is not null");
            return (Criteria) this;
        }

        public Criteria andFScriptIdEqualTo(Long value) {
            addCriterion("f_script_id =", value, "fScriptId");
            return (Criteria) this;
        }

        public Criteria andFScriptIdNotEqualTo(Long value) {
            addCriterion("f_script_id <>", value, "fScriptId");
            return (Criteria) this;
        }

        public Criteria andFScriptIdGreaterThan(Long value) {
            addCriterion("f_script_id >", value, "fScriptId");
            return (Criteria) this;
        }

        public Criteria andFScriptIdGreaterThanOrEqualTo(Long value) {
            addCriterion("f_script_id >=", value, "fScriptId");
            return (Criteria) this;
        }

        public Criteria andFScriptIdLessThan(Long value) {
            addCriterion("f_script_id <", value, "fScriptId");
            return (Criteria) this;
        }

        public Criteria andFScriptIdLessThanOrEqualTo(Long value) {
            addCriterion("f_script_id <=", value, "fScriptId");
            return (Criteria) this;
        }

        public Criteria andFScriptIdIn(List<Long> values) {
            addCriterion("f_script_id in", values, "fScriptId");
            return (Criteria) this;
        }

        public Criteria andFScriptIdNotIn(List<Long> values) {
            addCriterion("f_script_id not in", values, "fScriptId");
            return (Criteria) this;
        }

        public Criteria andFScriptIdBetween(Long value1, Long value2) {
            addCriterion("f_script_id between", value1, value2, "fScriptId");
            return (Criteria) this;
        }

        public Criteria andFScriptIdNotBetween(Long value1, Long value2) {
            addCriterion("f_script_id not between", value1, value2, "fScriptId");
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

        public Criteria andFFilenameIsNull() {
            addCriterion("f_filename is null");
            return (Criteria) this;
        }

        public Criteria andFFilenameIsNotNull() {
            addCriterion("f_filename is not null");
            return (Criteria) this;
        }

        public Criteria andFFilenameEqualTo(String value) {
            addCriterion("f_filename =", value, "fFilename");
            return (Criteria) this;
        }

        public Criteria andFFilenameNotEqualTo(String value) {
            addCriterion("f_filename <>", value, "fFilename");
            return (Criteria) this;
        }

        public Criteria andFFilenameGreaterThan(String value) {
            addCriterion("f_filename >", value, "fFilename");
            return (Criteria) this;
        }

        public Criteria andFFilenameGreaterThanOrEqualTo(String value) {
            addCriterion("f_filename >=", value, "fFilename");
            return (Criteria) this;
        }

        public Criteria andFFilenameLessThan(String value) {
            addCriterion("f_filename <", value, "fFilename");
            return (Criteria) this;
        }

        public Criteria andFFilenameLessThanOrEqualTo(String value) {
            addCriterion("f_filename <=", value, "fFilename");
            return (Criteria) this;
        }

        public Criteria andFFilenameLike(String value) {
            addCriterion("f_filename like", value, "fFilename");
            return (Criteria) this;
        }

        public Criteria andFFilenameNotLike(String value) {
            addCriterion("f_filename not like", value, "fFilename");
            return (Criteria) this;
        }

        public Criteria andFFilenameIn(List<String> values) {
            addCriterion("f_filename in", values, "fFilename");
            return (Criteria) this;
        }

        public Criteria andFFilenameNotIn(List<String> values) {
            addCriterion("f_filename not in", values, "fFilename");
            return (Criteria) this;
        }

        public Criteria andFFilenameBetween(String value1, String value2) {
            addCriterion("f_filename between", value1, value2, "fFilename");
            return (Criteria) this;
        }

        public Criteria andFFilenameNotBetween(String value1, String value2) {
            addCriterion("f_filename not between", value1, value2, "fFilename");
            return (Criteria) this;
        }

        public Criteria andFExtIsNull() {
            addCriterion("f_ext is null");
            return (Criteria) this;
        }

        public Criteria andFExtIsNotNull() {
            addCriterion("f_ext is not null");
            return (Criteria) this;
        }

        public Criteria andFExtEqualTo(String value) {
            addCriterion("f_ext =", value, "fExt");
            return (Criteria) this;
        }

        public Criteria andFExtNotEqualTo(String value) {
            addCriterion("f_ext <>", value, "fExt");
            return (Criteria) this;
        }

        public Criteria andFExtGreaterThan(String value) {
            addCriterion("f_ext >", value, "fExt");
            return (Criteria) this;
        }

        public Criteria andFExtGreaterThanOrEqualTo(String value) {
            addCriterion("f_ext >=", value, "fExt");
            return (Criteria) this;
        }

        public Criteria andFExtLessThan(String value) {
            addCriterion("f_ext <", value, "fExt");
            return (Criteria) this;
        }

        public Criteria andFExtLessThanOrEqualTo(String value) {
            addCriterion("f_ext <=", value, "fExt");
            return (Criteria) this;
        }

        public Criteria andFExtLike(String value) {
            addCriterion("f_ext like", value, "fExt");
            return (Criteria) this;
        }

        public Criteria andFExtNotLike(String value) {
            addCriterion("f_ext not like", value, "fExt");
            return (Criteria) this;
        }

        public Criteria andFExtIn(List<String> values) {
            addCriterion("f_ext in", values, "fExt");
            return (Criteria) this;
        }

        public Criteria andFExtNotIn(List<String> values) {
            addCriterion("f_ext not in", values, "fExt");
            return (Criteria) this;
        }

        public Criteria andFExtBetween(String value1, String value2) {
            addCriterion("f_ext between", value1, value2, "fExt");
            return (Criteria) this;
        }

        public Criteria andFExtNotBetween(String value1, String value2) {
            addCriterion("f_ext not between", value1, value2, "fExt");
            return (Criteria) this;
        }
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：t_funcunit_file
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * TFuncunitFile
     * Criterion
     * 数据库表：t_funcunit_file
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