package com.hzcf.flagship.model;

import java.util.Date;

public class RiskEmail {
    private Long id;

    private String orgNo;

    private String receiverNo;

    private String receiverName;

    private String receiverAddress;

    private String result;

    private Date createTime;
    
    private String isPrincipalDetail;//负责人是否发送明细数据
    
    private String isCreditManagerDetail;//风控经理是否发送明细数据

    private String isLeave;//是否离职
    
    private String principal;//是否是负责人
    
    private String principalSendAging;//负责人邮件发送账龄
    
    private String managerSendAging;//风控经理邮件发送账龄
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo == null ? null : orgNo.trim();
    }

    public String getReceiverNo() {
        return receiverNo;
    }

    public void setReceiverNo(String receiverNo) {
        this.receiverNo = receiverNo == null ? null : receiverNo.trim();
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress == null ? null : receiverAddress.trim();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


	public String getIsPrincipalDetail() {
		return isPrincipalDetail;
	}

	public void setIsPrincipalDetail(String isPrincipalDetail) {
		this.isPrincipalDetail = isPrincipalDetail;
	}

	public String getIsCreditManagerDetail() {
		return isCreditManagerDetail;
	}

	public void setIsCreditManagerDetail(String isCreditManagerDetail) {
		this.isCreditManagerDetail = isCreditManagerDetail;
	}

	public String getIsLeave() {
		return isLeave;
	}

	public void setIsLeave(String isLeave) {
		this.isLeave = isLeave;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getManagerSendAging() {
		return managerSendAging;
	}

	public void setManagerSendAging(String managerSendAging) {
		this.managerSendAging = managerSendAging;
	}

	public String getPrincipalSendAging() {
		return principalSendAging;
	}

	public void setPrincipalSendAging(String principalSendAging) {
		this.principalSendAging = principalSendAging;
	}

	 
	
    
}