package com.hzcf.flagship.model;

import java.math.BigDecimal;
import java.util.Date;

public class MoneymgrMonthHistory {
    private Long id;

    private Date recordDate;

    private String recordName;

    private String indexName;

    private BigDecimal indexValue;

    private Date createTime;

    private Integer creatorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName == null ? null : recordName.trim();
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName == null ? null : indexName.trim();
    }


	public BigDecimal getIndexValue() {
		return indexValue;
	}

	public void setIndexValue(BigDecimal indexValue) {
		this.indexValue = indexValue;
	}

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }
}