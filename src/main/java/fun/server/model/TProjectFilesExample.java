package fun.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TProjectFilesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    /**
     * 构造查询条件
     * pdm_server..t_project_files
     * @mbg.generated
     */
    public TProjectFilesExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * 设置排序字段
     * pdm_server..t_project_files
     * @param orderByClause 排序字段
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * 获取排序字段
     * pdm_server..t_project_files
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * 设置过滤重复数据
     * pdm_server..t_project_files
     * @param distinct 是否过滤重复数据
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * 是否过滤重复数据
     * pdm_server..t_project_files
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * 获取当前的查询条件实例
     * pdm_server..t_project_files
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * pdm_server..t_project_files
     * 增加或者的查询条件,用于构建或者查询
     * @param criteria 过滤条件实例
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * pdm_server..t_project_files
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
     * pdm_server..t_project_files
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
     * pdm_server..t_project_files
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * pdm_server..t_project_files
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * TProjectFiles
     * GeneratedCriteria
     * 数据库表：pdm_server..t_project_files
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

        public Criteria andFprojectidIsNull() {
            addCriterion("FProjectID is null");
            return (Criteria) this;
        }

        public Criteria andFprojectidIsNotNull() {
            addCriterion("FProjectID is not null");
            return (Criteria) this;
        }

        public Criteria andFprojectidEqualTo(Long value) {
            addCriterion("FProjectID =", value, "fprojectid");
            return (Criteria) this;
        }

        public Criteria andFprojectidNotEqualTo(Long value) {
            addCriterion("FProjectID <>", value, "fprojectid");
            return (Criteria) this;
        }

        public Criteria andFprojectidGreaterThan(Long value) {
            addCriterion("FProjectID >", value, "fprojectid");
            return (Criteria) this;
        }

        public Criteria andFprojectidGreaterThanOrEqualTo(Long value) {
            addCriterion("FProjectID >=", value, "fprojectid");
            return (Criteria) this;
        }

        public Criteria andFprojectidLessThan(Long value) {
            addCriterion("FProjectID <", value, "fprojectid");
            return (Criteria) this;
        }

        public Criteria andFprojectidLessThanOrEqualTo(Long value) {
            addCriterion("FProjectID <=", value, "fprojectid");
            return (Criteria) this;
        }

        public Criteria andFprojectidIn(List<Long> values) {
            addCriterion("FProjectID in", values, "fprojectid");
            return (Criteria) this;
        }

        public Criteria andFprojectidNotIn(List<Long> values) {
            addCriterion("FProjectID not in", values, "fprojectid");
            return (Criteria) this;
        }

        public Criteria andFprojectidBetween(Long value1, Long value2) {
            addCriterion("FProjectID between", value1, value2, "fprojectid");
            return (Criteria) this;
        }

        public Criteria andFprojectidNotBetween(Long value1, Long value2) {
            addCriterion("FProjectID not between", value1, value2, "fprojectid");
            return (Criteria) this;
        }

        public Criteria andFtaskidIsNull() {
            addCriterion("FTaskID is null");
            return (Criteria) this;
        }

        public Criteria andFtaskidIsNotNull() {
            addCriterion("FTaskID is not null");
            return (Criteria) this;
        }

        public Criteria andFtaskidEqualTo(Long value) {
            addCriterion("FTaskID =", value, "ftaskid");
            return (Criteria) this;
        }

        public Criteria andFtaskidNotEqualTo(Long value) {
            addCriterion("FTaskID <>", value, "ftaskid");
            return (Criteria) this;
        }

        public Criteria andFtaskidGreaterThan(Long value) {
            addCriterion("FTaskID >", value, "ftaskid");
            return (Criteria) this;
        }

        public Criteria andFtaskidGreaterThanOrEqualTo(Long value) {
            addCriterion("FTaskID >=", value, "ftaskid");
            return (Criteria) this;
        }

        public Criteria andFtaskidLessThan(Long value) {
            addCriterion("FTaskID <", value, "ftaskid");
            return (Criteria) this;
        }

        public Criteria andFtaskidLessThanOrEqualTo(Long value) {
            addCriterion("FTaskID <=", value, "ftaskid");
            return (Criteria) this;
        }

        public Criteria andFtaskidIn(List<Long> values) {
            addCriterion("FTaskID in", values, "ftaskid");
            return (Criteria) this;
        }

        public Criteria andFtaskidNotIn(List<Long> values) {
            addCriterion("FTaskID not in", values, "ftaskid");
            return (Criteria) this;
        }

        public Criteria andFtaskidBetween(Long value1, Long value2) {
            addCriterion("FTaskID between", value1, value2, "ftaskid");
            return (Criteria) this;
        }

        public Criteria andFtaskidNotBetween(Long value1, Long value2) {
            addCriterion("FTaskID not between", value1, value2, "ftaskid");
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

        public Criteria andFtypeEqualTo(Integer value) {
            addCriterion("FType =", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeNotEqualTo(Integer value) {
            addCriterion("FType <>", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeGreaterThan(Integer value) {
            addCriterion("FType >", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("FType >=", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeLessThan(Integer value) {
            addCriterion("FType <", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeLessThanOrEqualTo(Integer value) {
            addCriterion("FType <=", value, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeIn(List<Integer> values) {
            addCriterion("FType in", values, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeNotIn(List<Integer> values) {
            addCriterion("FType not in", values, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeBetween(Integer value1, Integer value2) {
            addCriterion("FType between", value1, value2, "ftype");
            return (Criteria) this;
        }

        public Criteria andFtypeNotBetween(Integer value1, Integer value2) {
            addCriterion("FType not between", value1, value2, "ftype");
            return (Criteria) this;
        }

        public Criteria andFfilenameIsNull() {
            addCriterion("FFileName is null");
            return (Criteria) this;
        }

        public Criteria andFfilenameIsNotNull() {
            addCriterion("FFileName is not null");
            return (Criteria) this;
        }

        public Criteria andFfilenameEqualTo(String value) {
            addCriterion("FFileName =", value, "ffilename");
            return (Criteria) this;
        }

        public Criteria andFfilenameNotEqualTo(String value) {
            addCriterion("FFileName <>", value, "ffilename");
            return (Criteria) this;
        }

        public Criteria andFfilenameGreaterThan(String value) {
            addCriterion("FFileName >", value, "ffilename");
            return (Criteria) this;
        }

        public Criteria andFfilenameGreaterThanOrEqualTo(String value) {
            addCriterion("FFileName >=", value, "ffilename");
            return (Criteria) this;
        }

        public Criteria andFfilenameLessThan(String value) {
            addCriterion("FFileName <", value, "ffilename");
            return (Criteria) this;
        }

        public Criteria andFfilenameLessThanOrEqualTo(String value) {
            addCriterion("FFileName <=", value, "ffilename");
            return (Criteria) this;
        }

        public Criteria andFfilenameLike(String value) {
            addCriterion("FFileName like", value, "ffilename");
            return (Criteria) this;
        }

        public Criteria andFfilenameNotLike(String value) {
            addCriterion("FFileName not like", value, "ffilename");
            return (Criteria) this;
        }

        public Criteria andFfilenameIn(List<String> values) {
            addCriterion("FFileName in", values, "ffilename");
            return (Criteria) this;
        }

        public Criteria andFfilenameNotIn(List<String> values) {
            addCriterion("FFileName not in", values, "ffilename");
            return (Criteria) this;
        }

        public Criteria andFfilenameBetween(String value1, String value2) {
            addCriterion("FFileName between", value1, value2, "ffilename");
            return (Criteria) this;
        }

        public Criteria andFfilenameNotBetween(String value1, String value2) {
            addCriterion("FFileName not between", value1, value2, "ffilename");
            return (Criteria) this;
        }

        public Criteria andFtypeidIsNull() {
            addCriterion("FTypeID is null");
            return (Criteria) this;
        }

        public Criteria andFtypeidIsNotNull() {
            addCriterion("FTypeID is not null");
            return (Criteria) this;
        }

        public Criteria andFtypeidEqualTo(Long value) {
            addCriterion("FTypeID =", value, "ftypeid");
            return (Criteria) this;
        }

        public Criteria andFtypeidNotEqualTo(Long value) {
            addCriterion("FTypeID <>", value, "ftypeid");
            return (Criteria) this;
        }

        public Criteria andFtypeidGreaterThan(Long value) {
            addCriterion("FTypeID >", value, "ftypeid");
            return (Criteria) this;
        }

        public Criteria andFtypeidGreaterThanOrEqualTo(Long value) {
            addCriterion("FTypeID >=", value, "ftypeid");
            return (Criteria) this;
        }

        public Criteria andFtypeidLessThan(Long value) {
            addCriterion("FTypeID <", value, "ftypeid");
            return (Criteria) this;
        }

        public Criteria andFtypeidLessThanOrEqualTo(Long value) {
            addCriterion("FTypeID <=", value, "ftypeid");
            return (Criteria) this;
        }

        public Criteria andFtypeidIn(List<Long> values) {
            addCriterion("FTypeID in", values, "ftypeid");
            return (Criteria) this;
        }

        public Criteria andFtypeidNotIn(List<Long> values) {
            addCriterion("FTypeID not in", values, "ftypeid");
            return (Criteria) this;
        }

        public Criteria andFtypeidBetween(Long value1, Long value2) {
            addCriterion("FTypeID between", value1, value2, "ftypeid");
            return (Criteria) this;
        }

        public Criteria andFtypeidNotBetween(Long value1, Long value2) {
            addCriterion("FTypeID not between", value1, value2, "ftypeid");
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

        public Criteria andFfiletypeIsNull() {
            addCriterion("FFileType is null");
            return (Criteria) this;
        }

        public Criteria andFfiletypeIsNotNull() {
            addCriterion("FFileType is not null");
            return (Criteria) this;
        }

        public Criteria andFfiletypeEqualTo(String value) {
            addCriterion("FFileType =", value, "ffiletype");
            return (Criteria) this;
        }

        public Criteria andFfiletypeNotEqualTo(String value) {
            addCriterion("FFileType <>", value, "ffiletype");
            return (Criteria) this;
        }

        public Criteria andFfiletypeGreaterThan(String value) {
            addCriterion("FFileType >", value, "ffiletype");
            return (Criteria) this;
        }

        public Criteria andFfiletypeGreaterThanOrEqualTo(String value) {
            addCriterion("FFileType >=", value, "ffiletype");
            return (Criteria) this;
        }

        public Criteria andFfiletypeLessThan(String value) {
            addCriterion("FFileType <", value, "ffiletype");
            return (Criteria) this;
        }

        public Criteria andFfiletypeLessThanOrEqualTo(String value) {
            addCriterion("FFileType <=", value, "ffiletype");
            return (Criteria) this;
        }

        public Criteria andFfiletypeLike(String value) {
            addCriterion("FFileType like", value, "ffiletype");
            return (Criteria) this;
        }

        public Criteria andFfiletypeNotLike(String value) {
            addCriterion("FFileType not like", value, "ffiletype");
            return (Criteria) this;
        }

        public Criteria andFfiletypeIn(List<String> values) {
            addCriterion("FFileType in", values, "ffiletype");
            return (Criteria) this;
        }

        public Criteria andFfiletypeNotIn(List<String> values) {
            addCriterion("FFileType not in", values, "ffiletype");
            return (Criteria) this;
        }

        public Criteria andFfiletypeBetween(String value1, String value2) {
            addCriterion("FFileType between", value1, value2, "ffiletype");
            return (Criteria) this;
        }

        public Criteria andFfiletypeNotBetween(String value1, String value2) {
            addCriterion("FFileType not between", value1, value2, "ffiletype");
            return (Criteria) this;
        }

        public Criteria andFsysnameIsNull() {
            addCriterion("FSysName is null");
            return (Criteria) this;
        }

        public Criteria andFsysnameIsNotNull() {
            addCriterion("FSysName is not null");
            return (Criteria) this;
        }

        public Criteria andFsysnameEqualTo(String value) {
            addCriterion("FSysName =", value, "fsysname");
            return (Criteria) this;
        }

        public Criteria andFsysnameNotEqualTo(String value) {
            addCriterion("FSysName <>", value, "fsysname");
            return (Criteria) this;
        }

        public Criteria andFsysnameGreaterThan(String value) {
            addCriterion("FSysName >", value, "fsysname");
            return (Criteria) this;
        }

        public Criteria andFsysnameGreaterThanOrEqualTo(String value) {
            addCriterion("FSysName >=", value, "fsysname");
            return (Criteria) this;
        }

        public Criteria andFsysnameLessThan(String value) {
            addCriterion("FSysName <", value, "fsysname");
            return (Criteria) this;
        }

        public Criteria andFsysnameLessThanOrEqualTo(String value) {
            addCriterion("FSysName <=", value, "fsysname");
            return (Criteria) this;
        }

        public Criteria andFsysnameLike(String value) {
            addCriterion("FSysName like", value, "fsysname");
            return (Criteria) this;
        }

        public Criteria andFsysnameNotLike(String value) {
            addCriterion("FSysName not like", value, "fsysname");
            return (Criteria) this;
        }

        public Criteria andFsysnameIn(List<String> values) {
            addCriterion("FSysName in", values, "fsysname");
            return (Criteria) this;
        }

        public Criteria andFsysnameNotIn(List<String> values) {
            addCriterion("FSysName not in", values, "fsysname");
            return (Criteria) this;
        }

        public Criteria andFsysnameBetween(String value1, String value2) {
            addCriterion("FSysName between", value1, value2, "fsysname");
            return (Criteria) this;
        }

        public Criteria andFsysnameNotBetween(String value1, String value2) {
            addCriterion("FSysName not between", value1, value2, "fsysname");
            return (Criteria) this;
        }

        public Criteria andFfileidIsNull() {
            addCriterion("FFileID is null");
            return (Criteria) this;
        }

        public Criteria andFfileidIsNotNull() {
            addCriterion("FFileID is not null");
            return (Criteria) this;
        }

        public Criteria andFfileidEqualTo(Long value) {
            addCriterion("FFileID =", value, "ffileid");
            return (Criteria) this;
        }

        public Criteria andFfileidNotEqualTo(Long value) {
            addCriterion("FFileID <>", value, "ffileid");
            return (Criteria) this;
        }

        public Criteria andFfileidGreaterThan(Long value) {
            addCriterion("FFileID >", value, "ffileid");
            return (Criteria) this;
        }

        public Criteria andFfileidGreaterThanOrEqualTo(Long value) {
            addCriterion("FFileID >=", value, "ffileid");
            return (Criteria) this;
        }

        public Criteria andFfileidLessThan(Long value) {
            addCriterion("FFileID <", value, "ffileid");
            return (Criteria) this;
        }

        public Criteria andFfileidLessThanOrEqualTo(Long value) {
            addCriterion("FFileID <=", value, "ffileid");
            return (Criteria) this;
        }

        public Criteria andFfileidIn(List<Long> values) {
            addCriterion("FFileID in", values, "ffileid");
            return (Criteria) this;
        }

        public Criteria andFfileidNotIn(List<Long> values) {
            addCriterion("FFileID not in", values, "ffileid");
            return (Criteria) this;
        }

        public Criteria andFfileidBetween(Long value1, Long value2) {
            addCriterion("FFileID between", value1, value2, "ffileid");
            return (Criteria) this;
        }

        public Criteria andFfileidNotBetween(Long value1, Long value2) {
            addCriterion("FFileID not between", value1, value2, "ffileid");
            return (Criteria) this;
        }
    }

    /**
     * 类注释
     * Criteria
     * 数据库表：pdm_server..t_project_files
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * TProjectFiles
     * Criterion
     * 数据库表：pdm_server..t_project_files
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