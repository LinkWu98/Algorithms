package project.gcs.common.dto;

/**
 * 员工派工的 excel 数据模型
 *
 * @author Link
 * @version 1.0
 * @date 2020/8/12 9:24
 */
public class EmTaskAssignmentLog4ExcelDto {

    /**
     * 员工名称
     */
    private String cnName;

    /**
     * 工号
     */
    private String employeeId;

    /**
     * 岗位
     */
    private String officeName;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务id
     */
    private String taskId;

    /**
     * 任务日期
     */
    private String planDate;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 生成方式
     */
    private String assignGeneration;

    /**
     * 操作时间
     */
    private String logCreateTime;

    /* getters & setters */
    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getPlanDate() {
        return planDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getAssignGeneration() {
        return assignGeneration;
    }

    public void setAssignGeneration(String assignGeneration) {
        this.assignGeneration = assignGeneration;
    }

    public String getLogCreateTime() {
        return logCreateTime;
    }

    public void setLogCreateTime(String logCreateTime) {
        this.logCreateTime = logCreateTime;
    }
}
