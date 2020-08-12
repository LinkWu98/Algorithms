package project.gcs.common.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: Link
 * @Date: 2020/7/30 10:37
 * @Version 1.0
 */
@Data
public class TaskFlight {

    private Long taskTypeId;

    private Date planStartTime;

    private Date planEndTime;

}
