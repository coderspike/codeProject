package dagong.domain;

import java.math.BigDecimal;
import java.util.Date;

/***********************************
 * 2018-02-01
 * @author:GWB
 * 申请借款信息实体类
 ***********************************
 */
public class TApplyLoan {
	
	private String customerName;
	private String gmScore;
	private String reportUrl;

	private String id;
    /**
     * 申请额度
     */
    private BigDecimal requestLimit;
    /**
     * 贷款期限
     */
    private String loanPeriod;
    /**
     * 期望利率
     */
    private BigDecimal hopeRate;
    /**
     * 过桥垫资
     */
    private String loanBridge;
    /**
     * 贷款用途
     */
    private String loanUse;
    /**
     * 第一还款来源
     */
    private String firstSourceRepayment;
    /**
     * 第二还款来源
     */
    private String secondSourceRepayment;
    /**
     * 申请状态
     */
    private String applyStatus;
    /**
     * 用户ID
     */
    private String customerUserId;
    /**
     * 借款项目名称
     */
    private String loanProjectName;
    //节点表相关字段
    /**
     * 当前业务节点
     */
    private String flowNode;
    /**
     * 当前业务节点状态
     */
    private String flowNodeStatus;
    /**
     * 业务状态
     */
    private String flowStatus;
    /**
     * 当前流程节点处理时间
     */
    private String flowNodeTime;
    /**
     * 流程处理人
     */
    private String flowHandlePerson;
    /**
     * 项目进度状态
     */
    private String clientEnd;
    /**
     * 用户名称
     */
    private String customerUserName;
    /**
     * 流程所属
     */
    private String flowType;
    
    /**
     * 关联查询ID 没实际作用，用于查询
     */
    private String applyFlowId;
    /**
     * 项目创建时间
     */
    private Date startTime;
    
    private String flowTypeName;//流程所属名称
    
    

    public String getFlowTypeName() {
		return flowTypeName;
	}

	public void setFlowTypeName(String flowTypeName) {
		this.flowTypeName = flowTypeName;
	}

	public String getCustomerUserName() {
        return customerUserName;
    }

    public void setCustomerUserName(String customerUserName) {
        this.customerUserName = customerUserName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getRequestLimit() {
        return requestLimit;
    }

    public void setRequestLimit(BigDecimal requestLimit) {
        this.requestLimit = requestLimit;
    }

    public String getLoanPeriod() {
        return loanPeriod;
    }

    public void setLoanPeriod(String loanPeriod) {
        this.loanPeriod = loanPeriod;
    }

    public BigDecimal getHopeRate() {
        return hopeRate;
    }

    public void setHopeRate(BigDecimal hopeRate) {
        this.hopeRate = hopeRate;
    }

    public String getLoanBridge() {
        return loanBridge;
    }

    public void setLoanBridge(String loanBridge) {
        this.loanBridge = loanBridge;
    }

    public String getLoanUse() {
        return loanUse;
    }

    public void setLoanUse(String loanUse) {
        this.loanUse = loanUse;
    }

    public String getFirstSourceRepayment() {
        return firstSourceRepayment;
    }

    public void setFirstSourceRepayment(String firstSourceRepayment) {
        this.firstSourceRepayment = firstSourceRepayment;
    }

    public String getSecondSourceRepayment() {
        return secondSourceRepayment;
    }

    public void setSecondSourceRepayment(String secondSourceRepayment) {
        this.secondSourceRepayment = secondSourceRepayment;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    public String getCustomerUserId() {
        return customerUserId;
    }

    public void setCustomerUserId(String customerUserId) {
        this.customerUserId = customerUserId;
    }

    public String getLoanProjectName() {
        return loanProjectName;
    }

    public void setLoanProjectName(String loanProjectName) {
        this.loanProjectName = loanProjectName;
    }

    public String getFlowNode() {
        return flowNode;
    }

    public void setFlowNode(String flowNode) {
        this.flowNode = flowNode;
    }

    public String getFlowNodeStatus() {
        return flowNodeStatus;
    }

    public void setFlowNodeStatus(String flowNodeStatus) {
        this.flowNodeStatus = flowNodeStatus;
    }

    public String getFlowStatus() {
        return flowStatus;
    }

    public void setFlowStatus(String flowStatus) {
        this.flowStatus = flowStatus;
    }

    public String getFlowNodeTime() {
        return flowNodeTime;
    }

    public void setFlowNodeTime(String flowNodeTime) {
        this.flowNodeTime = flowNodeTime;
    }

    public String getFlowHandlePerson() {
        return flowHandlePerson;
    }

    public void setFlowHandlePerson(String flowHandlePerson) {
        this.flowHandlePerson = flowHandlePerson;
    }

    public String getClientEnd() {
        return clientEnd;
    }

    public void setClientEnd(String clientEnd) {
        this.clientEnd = clientEnd;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public String getApplyFlowId() {
        return applyFlowId;
    }

    public void setApplyFlowId(String applyFlowId) {
        this.applyFlowId = applyFlowId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    

    public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getGmScore() {
		return gmScore;
	}

	public void setGmScore(String gmScore) {
		this.gmScore = gmScore;
	}

	public String getReportUrl() {
		return reportUrl;
	}

	public void setReportUrl(String reportUrl) {
		this.reportUrl = reportUrl;
	}
}
