package com.help.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PictureExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PictureExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPicNameIsNull() {
            addCriterion("pic_name is null");
            return (Criteria) this;
        }

        public Criteria andPicNameIsNotNull() {
            addCriterion("pic_name is not null");
            return (Criteria) this;
        }

        public Criteria andPicNameEqualTo(String value) {
            addCriterion("pic_name =", value, "picName");
            return (Criteria) this;
        }

        public Criteria andPicNameNotEqualTo(String value) {
            addCriterion("pic_name <>", value, "picName");
            return (Criteria) this;
        }

        public Criteria andPicNameGreaterThan(String value) {
            addCriterion("pic_name >", value, "picName");
            return (Criteria) this;
        }

        public Criteria andPicNameGreaterThanOrEqualTo(String value) {
            addCriterion("pic_name >=", value, "picName");
            return (Criteria) this;
        }

        public Criteria andPicNameLessThan(String value) {
            addCriterion("pic_name <", value, "picName");
            return (Criteria) this;
        }

        public Criteria andPicNameLessThanOrEqualTo(String value) {
            addCriterion("pic_name <=", value, "picName");
            return (Criteria) this;
        }

        public Criteria andPicNameLike(String value) {
            addCriterion("pic_name like", value, "picName");
            return (Criteria) this;
        }

        public Criteria andPicNameNotLike(String value) {
            addCriterion("pic_name not like", value, "picName");
            return (Criteria) this;
        }

        public Criteria andPicNameIn(List<String> values) {
            addCriterion("pic_name in", values, "picName");
            return (Criteria) this;
        }

        public Criteria andPicNameNotIn(List<String> values) {
            addCriterion("pic_name not in", values, "picName");
            return (Criteria) this;
        }

        public Criteria andPicNameBetween(String value1, String value2) {
            addCriterion("pic_name between", value1, value2, "picName");
            return (Criteria) this;
        }

        public Criteria andPicNameNotBetween(String value1, String value2) {
            addCriterion("pic_name not between", value1, value2, "picName");
            return (Criteria) this;
        }

        public Criteria andPicSizeIsNull() {
            addCriterion("pic_size is null");
            return (Criteria) this;
        }

        public Criteria andPicSizeIsNotNull() {
            addCriterion("pic_size is not null");
            return (Criteria) this;
        }

        public Criteria andPicSizeEqualTo(Integer value) {
            addCriterion("pic_size =", value, "picSize");
            return (Criteria) this;
        }

        public Criteria andPicSizeNotEqualTo(Integer value) {
            addCriterion("pic_size <>", value, "picSize");
            return (Criteria) this;
        }

        public Criteria andPicSizeGreaterThan(Integer value) {
            addCriterion("pic_size >", value, "picSize");
            return (Criteria) this;
        }

        public Criteria andPicSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("pic_size >=", value, "picSize");
            return (Criteria) this;
        }

        public Criteria andPicSizeLessThan(Integer value) {
            addCriterion("pic_size <", value, "picSize");
            return (Criteria) this;
        }

        public Criteria andPicSizeLessThanOrEqualTo(Integer value) {
            addCriterion("pic_size <=", value, "picSize");
            return (Criteria) this;
        }

        public Criteria andPicSizeIn(List<Integer> values) {
            addCriterion("pic_size in", values, "picSize");
            return (Criteria) this;
        }

        public Criteria andPicSizeNotIn(List<Integer> values) {
            addCriterion("pic_size not in", values, "picSize");
            return (Criteria) this;
        }

        public Criteria andPicSizeBetween(Integer value1, Integer value2) {
            addCriterion("pic_size between", value1, value2, "picSize");
            return (Criteria) this;
        }

        public Criteria andPicSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("pic_size not between", value1, value2, "picSize");
            return (Criteria) this;
        }

        public Criteria andPicSuffixIsNull() {
            addCriterion("pic_suffix is null");
            return (Criteria) this;
        }

        public Criteria andPicSuffixIsNotNull() {
            addCriterion("pic_suffix is not null");
            return (Criteria) this;
        }

        public Criteria andPicSuffixEqualTo(String value) {
            addCriterion("pic_suffix =", value, "picSuffix");
            return (Criteria) this;
        }

        public Criteria andPicSuffixNotEqualTo(String value) {
            addCriterion("pic_suffix <>", value, "picSuffix");
            return (Criteria) this;
        }

        public Criteria andPicSuffixGreaterThan(String value) {
            addCriterion("pic_suffix >", value, "picSuffix");
            return (Criteria) this;
        }

        public Criteria andPicSuffixGreaterThanOrEqualTo(String value) {
            addCriterion("pic_suffix >=", value, "picSuffix");
            return (Criteria) this;
        }

        public Criteria andPicSuffixLessThan(String value) {
            addCriterion("pic_suffix <", value, "picSuffix");
            return (Criteria) this;
        }

        public Criteria andPicSuffixLessThanOrEqualTo(String value) {
            addCriterion("pic_suffix <=", value, "picSuffix");
            return (Criteria) this;
        }

        public Criteria andPicSuffixLike(String value) {
            addCriterion("pic_suffix like", value, "picSuffix");
            return (Criteria) this;
        }

        public Criteria andPicSuffixNotLike(String value) {
            addCriterion("pic_suffix not like", value, "picSuffix");
            return (Criteria) this;
        }

        public Criteria andPicSuffixIn(List<String> values) {
            addCriterion("pic_suffix in", values, "picSuffix");
            return (Criteria) this;
        }

        public Criteria andPicSuffixNotIn(List<String> values) {
            addCriterion("pic_suffix not in", values, "picSuffix");
            return (Criteria) this;
        }

        public Criteria andPicSuffixBetween(String value1, String value2) {
            addCriterion("pic_suffix between", value1, value2, "picSuffix");
            return (Criteria) this;
        }

        public Criteria andPicSuffixNotBetween(String value1, String value2) {
            addCriterion("pic_suffix not between", value1, value2, "picSuffix");
            return (Criteria) this;
        }

        public Criteria andPicMd5IsNull() {
            addCriterion("pic_md5 is null");
            return (Criteria) this;
        }

        public Criteria andPicMd5IsNotNull() {
            addCriterion("pic_md5 is not null");
            return (Criteria) this;
        }

        public Criteria andPicMd5EqualTo(String value) {
            addCriterion("pic_md5 =", value, "picMd5");
            return (Criteria) this;
        }

        public Criteria andPicMd5NotEqualTo(String value) {
            addCriterion("pic_md5 <>", value, "picMd5");
            return (Criteria) this;
        }

        public Criteria andPicMd5GreaterThan(String value) {
            addCriterion("pic_md5 >", value, "picMd5");
            return (Criteria) this;
        }

        public Criteria andPicMd5GreaterThanOrEqualTo(String value) {
            addCriterion("pic_md5 >=", value, "picMd5");
            return (Criteria) this;
        }

        public Criteria andPicMd5LessThan(String value) {
            addCriterion("pic_md5 <", value, "picMd5");
            return (Criteria) this;
        }

        public Criteria andPicMd5LessThanOrEqualTo(String value) {
            addCriterion("pic_md5 <=", value, "picMd5");
            return (Criteria) this;
        }

        public Criteria andPicMd5Like(String value) {
            addCriterion("pic_md5 like", value, "picMd5");
            return (Criteria) this;
        }

        public Criteria andPicMd5NotLike(String value) {
            addCriterion("pic_md5 not like", value, "picMd5");
            return (Criteria) this;
        }

        public Criteria andPicMd5In(List<String> values) {
            addCriterion("pic_md5 in", values, "picMd5");
            return (Criteria) this;
        }

        public Criteria andPicMd5NotIn(List<String> values) {
            addCriterion("pic_md5 not in", values, "picMd5");
            return (Criteria) this;
        }

        public Criteria andPicMd5Between(String value1, String value2) {
            addCriterion("pic_md5 between", value1, value2, "picMd5");
            return (Criteria) this;
        }

        public Criteria andPicMd5NotBetween(String value1, String value2) {
            addCriterion("pic_md5 not between", value1, value2, "picMd5");
            return (Criteria) this;
        }

        public Criteria andPicUrlIsNull() {
            addCriterion("pic_url is null");
            return (Criteria) this;
        }

        public Criteria andPicUrlIsNotNull() {
            addCriterion("pic_url is not null");
            return (Criteria) this;
        }

        public Criteria andPicUrlEqualTo(String value) {
            addCriterion("pic_url =", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlNotEqualTo(String value) {
            addCriterion("pic_url <>", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlGreaterThan(String value) {
            addCriterion("pic_url >", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("pic_url >=", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlLessThan(String value) {
            addCriterion("pic_url <", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlLessThanOrEqualTo(String value) {
            addCriterion("pic_url <=", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlLike(String value) {
            addCriterion("pic_url like", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlNotLike(String value) {
            addCriterion("pic_url not like", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlIn(List<String> values) {
            addCriterion("pic_url in", values, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlNotIn(List<String> values) {
            addCriterion("pic_url not in", values, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlBetween(String value1, String value2) {
            addCriterion("pic_url between", value1, value2, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlNotBetween(String value1, String value2) {
            addCriterion("pic_url not between", value1, value2, "picUrl");
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

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(id) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andPicNameLikeInsensitive(String value) {
            addCriterion("upper(pic_name) like", value.toUpperCase(), "picName");
            return (Criteria) this;
        }

        public Criteria andPicSuffixLikeInsensitive(String value) {
            addCriterion("upper(pic_suffix) like", value.toUpperCase(), "picSuffix");
            return (Criteria) this;
        }

        public Criteria andPicMd5LikeInsensitive(String value) {
            addCriterion("upper(pic_md5) like", value.toUpperCase(), "picMd5");
            return (Criteria) this;
        }

        public Criteria andPicUrlLikeInsensitive(String value) {
            addCriterion("upper(pic_url) like", value.toUpperCase(), "picUrl");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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