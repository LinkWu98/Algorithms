package project.gcs.test;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import project.gcs.common.DateUtils;
import project.gcs.entity.TaskFlight;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @Author: Link
 * @Date: 2020/7/30 10:39
 * @Version 1.0
 */
public class ArrangeRateTest {

    private List<TaskFlight> taskFlights = new ArrayList<>();

    private String taskArrangeRate = "0:0.5,1:0.5,2:0.5,3:0.5";

    /**
     * 创建 TaskFlight
     */
    @Before
    public void before() {

        int taskTypeId = 0;

        for (int i = 1; i <= 30; i++) {

            TaskFlight taskFlight = new TaskFlight();

            taskFlight.setTaskTypeId(Integer.valueOf(taskTypeId).longValue());

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, new Random().nextInt(3) + 1);
            taskFlight.setPlanStartTime(calendar.getTime());

            taskFlights.add(taskFlight);

            if (i % 10 == 0) {
                taskTypeId++;
            }

        }

        Map<Long, List<TaskFlight>> groupedByTaskTypeId = taskFlights.stream().collect(Collectors.groupingBy(TaskFlight::getTaskTypeId));
        for (Map.Entry<Long, List<TaskFlight>> taskFlightEntry : groupedByTaskTypeId.entrySet()) {
            System.out.print("taskTypeId : " + taskFlightEntry.getKey());
            System.out.println(", taskFlightSize : " + taskFlightEntry.getValue().size());

        }

        System.out.println("====================before====================");

    }

    @After
    public void after() {
        System.out.println("====================after====================");
        System.out.println(taskFlights.size());
    }

    /**
     * taskArrangeRate : "0:0.1,1:0.2,2:0.3,3:0.4"
     */
    @Test
    public void randomTaskTest() {

        //如果 taskArrangeRate 为空或 1 (默认值)，直接返回全部的任务
        if (!StringUtils.isNotBlank(taskArrangeRate) || StringUtils.equals(taskArrangeRate, "1")) {
            System.out.println("不筛选，全部返回");
        }

        //1. 将筛选率转为 map : key-taskTypeId, value-rate
        Map<Long, Float> taskTypeArrangeRateMap = convertArrangeRateStrToTaskTypeArrangeRateMap(taskArrangeRate);

        //2. 将 TaskFlight 按 taskTypeId 分组 map : key-taskTypeId, value-TaskFlight
        Map<Long, List<TaskFlight>> taskFlightMap = taskFlights.stream().collect(Collectors.groupingBy(TaskFlight::getTaskTypeId));

        //随机安排前，清空 list
        taskFlights = new ArrayList<>();

        //3. 遍历 taskFlightMap, 不同 taskTypeId 下的 TaskFlight任务，按照开始时间的小时分组，通过任务比例系数随机筛选任务
        for (Map.Entry<Long, List<TaskFlight>> taskFlightEntry : taskFlightMap.entrySet()) {

            Float rate = taskTypeArrangeRateMap.get(taskFlightEntry.getKey());
            if (rate == null || rate <= 0F || rate > 1F) {
                continue;
            }

            //3.1 同一个 taskType，将任务按照开始时间的小时分组(无所谓日期),  map : key-hour, value-TaskFlights,
            Map<Integer, List<TaskFlight>> taskFlightsGroupedByHour = taskFlightEntry.getValue().stream().filter(taskFlight -> taskFlight.getPlanStartTime() != null).collect(Collectors.groupingBy(taskFlight -> DateUtils.getHourOfDate(taskFlight.getPlanStartTime())));
            for (List<TaskFlight> taskFlightList : taskFlightsGroupedByHour.values()) {

                System.out.println("beforeSize : " + taskFlightList.size());

                arrangeTaskRandomly(taskFlightList, rate);

                System.out.println("afterSize : " + taskFlightList.size());

                taskFlights.addAll(taskFlightList);

            }

        }

    }

    /**
     * 按照比率随机筛选任务
     *
     * @param taskList
     * @param rate
     */
    private <T> void arrangeTaskRandomly(List<T> taskList, Float rate) {

        //向上取整
        double finalArrangedSize = Math.ceil(taskList.size() * rate);

        System.out.println("finalSize:" + finalArrangedSize);

        while (taskList.size() > finalArrangedSize) {

            //随机删除任意索引的任务
            taskList.remove(new Random().nextInt(taskList.size() - 1));

        }

    }


    /**
     * 将比例字符串转为 map
     *
     * @param arrangeRate
     * @return
     */
    private Map<Long, Float> convertArrangeRateStrToTaskTypeArrangeRateMap(String arrangeRate) {
        List<String> rateList = Arrays.asList(arrangeRate.split(","));
        return rateList.stream().collect(Collectors.toMap(str -> Long.parseLong(str.split(":")[0]), str -> Float.parseFloat(str.split(":")[1]), (oldVal, newVal) -> newVal));
    }

    @Test
    public void test(){

        String jsonStr = "{'arrangeRate':'10000:0.1'}";

        Map<String, String> map = (Map<String, String>)JSON.parse(jsonStr);

        System.out.println(map.get("arrangeRate"));

    }

}
