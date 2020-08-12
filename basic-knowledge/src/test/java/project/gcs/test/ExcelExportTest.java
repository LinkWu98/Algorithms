package project.gcs.test;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import project.gcs.common.dto.EmTaskAssignmentLog4ExcelDto;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Link
 * @version 1.0
 * @date 2020/8/12 14:02
 */
public class ExcelExportTest {

    private List<EmTaskAssignmentLog4ExcelDto> data;

    private Integer dataSize = 16;

    private String header = "";

    private Logger log = LoggerFactory.getLogger(ExcelExportTest.class);

    /**
     * 反射获取字段属性
     *
     * @throws IllegalAccessException
     */
    @Test
    public void reflectTest() throws IllegalAccessException {

        EmTaskAssignmentLog4ExcelDto dto = new EmTaskAssignmentLog4ExcelDto();
        Class<? extends EmTaskAssignmentLog4ExcelDto> clazz = dto.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.println(field.get(dto));
        }


    }

    @Before
    public void createData() throws IllegalAccessException, InstantiationException {

        data = new ArrayList<>();

        Class<EmTaskAssignmentLog4ExcelDto> clazz = EmTaskAssignmentLog4ExcelDto.class;
        Field[] fields = clazz.getDeclaredFields();

        for (int i = 0; i < dataSize; i++) {
            EmTaskAssignmentLog4ExcelDto excelDto = clazz.newInstance();
            for (int j = 0; j < fields.length; j++) {
                Field field = fields[j];
                field.setAccessible(true);
                field.set(excelDto, j + "");
            }
            data.add(excelDto);
        }

        System.out.println("=============create data=============");

    }

    @Before
    public void createHeader() {

        if (CollectionUtils.isEmpty(data)) {
            return;
        }

        EmTaskAssignmentLog4ExcelDto dto = data.get(0);
        Field[] fields = dto.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            if (i == 0) {
                header += field.getName();
                continue;
            }

            header += (field.getName() + ",");
        }

        System.out.println("=============create header=============");

    }

    @Test
    public void excelWriteTest() throws Exception {

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        //1. 创建表
        HSSFSheet sheet = hssfWorkbook.createSheet();
        //2. 创建表头
        HSSFRow firstRow = sheet.createRow(0);
        //2.1 表头字符串集合
        List<String> headerStrList = Arrays.asList(header.split(","));
        for (int i = 0; i < headerStrList.size(); i++) {
            HSSFCell cell = firstRow.createCell(i);
            cell.setCellValue(headerStrList.get(i));
        }

        //3. 遍历数据集，写入表
        //3.1 先获取该 class 的所有字段
        List<Field> fields = Arrays.asList(data.get(0).getClass().getDeclaredFields());
        for (int i = 1; i < data.size() + 1; i++) {
            //3.1 数据从第二行开始，因此 i 从 1 开始
            HSSFRow row = sheet.createRow(i);
            for (int j = 0; j < fields.size(); j++) {
                //3.3 先创建列，空了就是空
                HSSFCell cell = row.createCell(j);
                Field field = fields.get(0);
                field.setAccessible(true);
                //3.4 获取属性值设置给该行的该列
                Object value = field.get(data.get(i - 1));
                if (value != null) {
                    cell.setCellValue((String) value);
                }
            }
        }

        hssfWorkbook.write(new FileOutputStream(new File("K:\\test.xls")));

    }

}
