/*
*
* BlogLeaveMsgExample.java
* Copyright(C) 2017-2020 fendo公司
* @date 2018-08-02
*/
package cn.web.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlogLeaveMsgExample {
    /**
     * blog_leave_msg
     */
    protected String orderByClause;

    /**
     * blog_leave_msg
     */
    protected boolean distinct;

    /**
     * blog_leave_msg
     */
    protected List<Criteria> oredCriteria;

    /**
     *
     * @mbg.generated
     */
    public BlogLeaveMsgExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     *
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
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * blog_leave_msg null
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

        public Criteria andLeaveIdIsNull() {
            addCriterion("leave_id is null");
            return (Criteria) this;
        }

        public Criteria andLeaveIdIsNotNull() {
            addCriterion("leave_id is not null");
            return (Criteria) this;
        }

        public Criteria andLeaveIdEqualTo(Long value) {
            addCriterion("leave_id =", value, "leaveId");
            return (Criteria) this;
        }

        public Criteria andLeaveIdNotEqualTo(Long value) {
            addCriterion("leave_id <>", value, "leaveId");
            return (Criteria) this;
        }

        public Criteria andLeaveIdGreaterThan(Long value) {
            addCriterion("leave_id >", value, "leaveId");
            return (Criteria) this;
        }

        public Criteria andLeaveIdGreaterThanOrEqualTo(Long value) {
            addCriterion("leave_id >=", value, "leaveId");
            return (Criteria) this;
        }

        public Criteria andLeaveIdLessThan(Long value) {
            addCriterion("leave_id <", value, "leaveId");
            return (Criteria) this;
        }

        public Criteria andLeaveIdLessThanOrEqualTo(Long value) {
            addCriterion("leave_id <=", value, "leaveId");
            return (Criteria) this;
        }

        public Criteria andLeaveIdIn(List<Long> values) {
            addCriterion("leave_id in", values, "leaveId");
            return (Criteria) this;
        }

        public Criteria andLeaveIdNotIn(List<Long> values) {
            addCriterion("leave_id not in", values, "leaveId");
            return (Criteria) this;
        }

        public Criteria andLeaveIdBetween(Long value1, Long value2) {
            addCriterion("leave_id between", value1, value2, "leaveId");
            return (Criteria) this;
        }

        public Criteria andLeaveIdNotBetween(Long value1, Long value2) {
            addCriterion("leave_id not between", value1, value2, "leaveId");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Long value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Long value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Long value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Long value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Long value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Long> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Long> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Long value1, Long value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Long value1, Long value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andFromIdIsNull() {
            addCriterion("from_id is null");
            return (Criteria) this;
        }

        public Criteria andFromIdIsNotNull() {
            addCriterion("from_id is not null");
            return (Criteria) this;
        }

        public Criteria andFromIdEqualTo(String value) {
            addCriterion("from_id =", value, "fromId");
            return (Criteria) this;
        }

        public Criteria andFromIdNotEqualTo(String value) {
            addCriterion("from_id <>", value, "fromId");
            return (Criteria) this;
        }

        public Criteria andFromIdGreaterThan(String value) {
            addCriterion("from_id >", value, "fromId");
            return (Criteria) this;
        }

        public Criteria andFromIdGreaterThanOrEqualTo(String value) {
            addCriterion("from_id >=", value, "fromId");
            return (Criteria) this;
        }

        public Criteria andFromIdLessThan(String value) {
            addCriterion("from_id <", value, "fromId");
            return (Criteria) this;
        }

        public Criteria andFromIdLessThanOrEqualTo(String value) {
            addCriterion("from_id <=", value, "fromId");
            return (Criteria) this;
        }

        public Criteria andFromIdLike(String value) {
            addCriterion("from_id like", value, "fromId");
            return (Criteria) this;
        }

        public Criteria andFromIdNotLike(String value) {
            addCriterion("from_id not like", value, "fromId");
            return (Criteria) this;
        }

        public Criteria andFromIdIn(List<String> values) {
            addCriterion("from_id in", values, "fromId");
            return (Criteria) this;
        }

        public Criteria andFromIdNotIn(List<String> values) {
            addCriterion("from_id not in", values, "fromId");
            return (Criteria) this;
        }

        public Criteria andFromIdBetween(String value1, String value2) {
            addCriterion("from_id between", value1, value2, "fromId");
            return (Criteria) this;
        }

        public Criteria andFromIdNotBetween(String value1, String value2) {
            addCriterion("from_id not between", value1, value2, "fromId");
            return (Criteria) this;
        }

        public Criteria andFromNameIsNull() {
            addCriterion("from_name is null");
            return (Criteria) this;
        }

        public Criteria andFromNameIsNotNull() {
            addCriterion("from_name is not null");
            return (Criteria) this;
        }

        public Criteria andFromNameEqualTo(String value) {
            addCriterion("from_name =", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameNotEqualTo(String value) {
            addCriterion("from_name <>", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameGreaterThan(String value) {
            addCriterion("from_name >", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameGreaterThanOrEqualTo(String value) {
            addCriterion("from_name >=", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameLessThan(String value) {
            addCriterion("from_name <", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameLessThanOrEqualTo(String value) {
            addCriterion("from_name <=", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameLike(String value) {
            addCriterion("from_name like", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameNotLike(String value) {
            addCriterion("from_name not like", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameIn(List<String> values) {
            addCriterion("from_name in", values, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameNotIn(List<String> values) {
            addCriterion("from_name not in", values, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameBetween(String value1, String value2) {
            addCriterion("from_name between", value1, value2, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameNotBetween(String value1, String value2) {
            addCriterion("from_name not between", value1, value2, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromHeadImgIsNull() {
            addCriterion("from_head_img is null");
            return (Criteria) this;
        }

        public Criteria andFromHeadImgIsNotNull() {
            addCriterion("from_head_img is not null");
            return (Criteria) this;
        }

        public Criteria andFromHeadImgEqualTo(String value) {
            addCriterion("from_head_img =", value, "fromHeadImg");
            return (Criteria) this;
        }

        public Criteria andFromHeadImgNotEqualTo(String value) {
            addCriterion("from_head_img <>", value, "fromHeadImg");
            return (Criteria) this;
        }

        public Criteria andFromHeadImgGreaterThan(String value) {
            addCriterion("from_head_img >", value, "fromHeadImg");
            return (Criteria) this;
        }

        public Criteria andFromHeadImgGreaterThanOrEqualTo(String value) {
            addCriterion("from_head_img >=", value, "fromHeadImg");
            return (Criteria) this;
        }

        public Criteria andFromHeadImgLessThan(String value) {
            addCriterion("from_head_img <", value, "fromHeadImg");
            return (Criteria) this;
        }

        public Criteria andFromHeadImgLessThanOrEqualTo(String value) {
            addCriterion("from_head_img <=", value, "fromHeadImg");
            return (Criteria) this;
        }

        public Criteria andFromHeadImgLike(String value) {
            addCriterion("from_head_img like", value, "fromHeadImg");
            return (Criteria) this;
        }

        public Criteria andFromHeadImgNotLike(String value) {
            addCriterion("from_head_img not like", value, "fromHeadImg");
            return (Criteria) this;
        }

        public Criteria andFromHeadImgIn(List<String> values) {
            addCriterion("from_head_img in", values, "fromHeadImg");
            return (Criteria) this;
        }

        public Criteria andFromHeadImgNotIn(List<String> values) {
            addCriterion("from_head_img not in", values, "fromHeadImg");
            return (Criteria) this;
        }

        public Criteria andFromHeadImgBetween(String value1, String value2) {
            addCriterion("from_head_img between", value1, value2, "fromHeadImg");
            return (Criteria) this;
        }

        public Criteria andFromHeadImgNotBetween(String value1, String value2) {
            addCriterion("from_head_img not between", value1, value2, "fromHeadImg");
            return (Criteria) this;
        }

        public Criteria andToIdIsNull() {
            addCriterion("to_id is null");
            return (Criteria) this;
        }

        public Criteria andToIdIsNotNull() {
            addCriterion("to_id is not null");
            return (Criteria) this;
        }

        public Criteria andToIdEqualTo(String value) {
            addCriterion("to_id =", value, "toId");
            return (Criteria) this;
        }

        public Criteria andToIdNotEqualTo(String value) {
            addCriterion("to_id <>", value, "toId");
            return (Criteria) this;
        }

        public Criteria andToIdGreaterThan(String value) {
            addCriterion("to_id >", value, "toId");
            return (Criteria) this;
        }

        public Criteria andToIdGreaterThanOrEqualTo(String value) {
            addCriterion("to_id >=", value, "toId");
            return (Criteria) this;
        }

        public Criteria andToIdLessThan(String value) {
            addCriterion("to_id <", value, "toId");
            return (Criteria) this;
        }

        public Criteria andToIdLessThanOrEqualTo(String value) {
            addCriterion("to_id <=", value, "toId");
            return (Criteria) this;
        }

        public Criteria andToIdLike(String value) {
            addCriterion("to_id like", value, "toId");
            return (Criteria) this;
        }

        public Criteria andToIdNotLike(String value) {
            addCriterion("to_id not like", value, "toId");
            return (Criteria) this;
        }

        public Criteria andToIdIn(List<String> values) {
            addCriterion("to_id in", values, "toId");
            return (Criteria) this;
        }

        public Criteria andToIdNotIn(List<String> values) {
            addCriterion("to_id not in", values, "toId");
            return (Criteria) this;
        }

        public Criteria andToIdBetween(String value1, String value2) {
            addCriterion("to_id between", value1, value2, "toId");
            return (Criteria) this;
        }

        public Criteria andToIdNotBetween(String value1, String value2) {
            addCriterion("to_id not between", value1, value2, "toId");
            return (Criteria) this;
        }

        public Criteria andToNameIsNull() {
            addCriterion("to_name is null");
            return (Criteria) this;
        }

        public Criteria andToNameIsNotNull() {
            addCriterion("to_name is not null");
            return (Criteria) this;
        }

        public Criteria andToNameEqualTo(String value) {
            addCriterion("to_name =", value, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameNotEqualTo(String value) {
            addCriterion("to_name <>", value, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameGreaterThan(String value) {
            addCriterion("to_name >", value, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameGreaterThanOrEqualTo(String value) {
            addCriterion("to_name >=", value, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameLessThan(String value) {
            addCriterion("to_name <", value, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameLessThanOrEqualTo(String value) {
            addCriterion("to_name <=", value, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameLike(String value) {
            addCriterion("to_name like", value, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameNotLike(String value) {
            addCriterion("to_name not like", value, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameIn(List<String> values) {
            addCriterion("to_name in", values, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameNotIn(List<String> values) {
            addCriterion("to_name not in", values, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameBetween(String value1, String value2) {
            addCriterion("to_name between", value1, value2, "toName");
            return (Criteria) this;
        }

        public Criteria andToNameNotBetween(String value1, String value2) {
            addCriterion("to_name not between", value1, value2, "toName");
            return (Criteria) this;
        }

        public Criteria andLikeNumIsNull() {
            addCriterion("like_num is null");
            return (Criteria) this;
        }

        public Criteria andLikeNumIsNotNull() {
            addCriterion("like_num is not null");
            return (Criteria) this;
        }

        public Criteria andLikeNumEqualTo(Long value) {
            addCriterion("like_num =", value, "likeNum");
            return (Criteria) this;
        }

        public Criteria andLikeNumNotEqualTo(Long value) {
            addCriterion("like_num <>", value, "likeNum");
            return (Criteria) this;
        }

        public Criteria andLikeNumGreaterThan(Long value) {
            addCriterion("like_num >", value, "likeNum");
            return (Criteria) this;
        }

        public Criteria andLikeNumGreaterThanOrEqualTo(Long value) {
            addCriterion("like_num >=", value, "likeNum");
            return (Criteria) this;
        }

        public Criteria andLikeNumLessThan(Long value) {
            addCriterion("like_num <", value, "likeNum");
            return (Criteria) this;
        }

        public Criteria andLikeNumLessThanOrEqualTo(Long value) {
            addCriterion("like_num <=", value, "likeNum");
            return (Criteria) this;
        }

        public Criteria andLikeNumIn(List<Long> values) {
            addCriterion("like_num in", values, "likeNum");
            return (Criteria) this;
        }

        public Criteria andLikeNumNotIn(List<Long> values) {
            addCriterion("like_num not in", values, "likeNum");
            return (Criteria) this;
        }

        public Criteria andLikeNumBetween(Long value1, Long value2) {
            addCriterion("like_num between", value1, value2, "likeNum");
            return (Criteria) this;
        }

        public Criteria andLikeNumNotBetween(Long value1, Long value2) {
            addCriterion("like_num not between", value1, value2, "likeNum");
            return (Criteria) this;
        }

        public Criteria andAreaIpIsNull() {
            addCriterion("area_ip is null");
            return (Criteria) this;
        }

        public Criteria andAreaIpIsNotNull() {
            addCriterion("area_ip is not null");
            return (Criteria) this;
        }

        public Criteria andAreaIpEqualTo(String value) {
            addCriterion("area_ip =", value, "areaIp");
            return (Criteria) this;
        }

        public Criteria andAreaIpNotEqualTo(String value) {
            addCriterion("area_ip <>", value, "areaIp");
            return (Criteria) this;
        }

        public Criteria andAreaIpGreaterThan(String value) {
            addCriterion("area_ip >", value, "areaIp");
            return (Criteria) this;
        }

        public Criteria andAreaIpGreaterThanOrEqualTo(String value) {
            addCriterion("area_ip >=", value, "areaIp");
            return (Criteria) this;
        }

        public Criteria andAreaIpLessThan(String value) {
            addCriterion("area_ip <", value, "areaIp");
            return (Criteria) this;
        }

        public Criteria andAreaIpLessThanOrEqualTo(String value) {
            addCriterion("area_ip <=", value, "areaIp");
            return (Criteria) this;
        }

        public Criteria andAreaIpLike(String value) {
            addCriterion("area_ip like", value, "areaIp");
            return (Criteria) this;
        }

        public Criteria andAreaIpNotLike(String value) {
            addCriterion("area_ip not like", value, "areaIp");
            return (Criteria) this;
        }

        public Criteria andAreaIpIn(List<String> values) {
            addCriterion("area_ip in", values, "areaIp");
            return (Criteria) this;
        }

        public Criteria andAreaIpNotIn(List<String> values) {
            addCriterion("area_ip not in", values, "areaIp");
            return (Criteria) this;
        }

        public Criteria andAreaIpBetween(String value1, String value2) {
            addCriterion("area_ip between", value1, value2, "areaIp");
            return (Criteria) this;
        }

        public Criteria andAreaIpNotBetween(String value1, String value2) {
            addCriterion("area_ip not between", value1, value2, "areaIp");
            return (Criteria) this;
        }

        public Criteria andAreaIsNull() {
            addCriterion("area is null");
            return (Criteria) this;
        }

        public Criteria andAreaIsNotNull() {
            addCriterion("area is not null");
            return (Criteria) this;
        }

        public Criteria andAreaEqualTo(String value) {
            addCriterion("area =", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotEqualTo(String value) {
            addCriterion("area <>", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThan(String value) {
            addCriterion("area >", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThanOrEqualTo(String value) {
            addCriterion("area >=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThan(String value) {
            addCriterion("area <", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThanOrEqualTo(String value) {
            addCriterion("area <=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLike(String value) {
            addCriterion("area like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotLike(String value) {
            addCriterion("area not like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaIn(List<String> values) {
            addCriterion("area in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotIn(List<String> values) {
            addCriterion("area not in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaBetween(String value1, String value2) {
            addCriterion("area between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotBetween(String value1, String value2) {
            addCriterion("area not between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }
    }

    /**
     *  * blog_leave_msg
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * blog_leave_msg null
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