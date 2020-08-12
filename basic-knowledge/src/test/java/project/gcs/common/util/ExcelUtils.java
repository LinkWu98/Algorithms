package project.gcs.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

/**
 * @author Link
 * @version 1.0
 * @date 2020/8/12 13:59
 */
public class ExcelUtils {

    /**
     * 创建 excel 将数据写入并返回给 response
     * <p>
     * .xls格式 Excel2003
     * 最大支持 65535条数据
     *
     * @param header 表头，用逗号分隔 (表头数量必须和数据模型参数个数一致！顺序也是一一对应的！)
     * @param data   数据集合，每个对象对应一行记录
     */
    public static <T> void exportExcelByResponse(HttpServletResponse response, String header, String filename, List<T> data) throws Exception {

        //参数校验
        if (response == null || StringUtils.isBlank(header) || StringUtils.isBlank(filename) || CollectionUtils.isEmpty(data)) {
            return;
        }

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
        for (int i = 1; i < data.size() + 1; i++) {
            //3.1 数据从第二行开始，因此 i 从 1 开始
            HSSFRow row = sheet.createRow(i);
            //3.2 获取该 class 的所有字段及其属性值，设置给该行的每一列
            List<Field> fields = Arrays.asList(data.get(i - 1).getClass().getDeclaredFields());
            for (int j = 0; j < fields.size(); j++) {
                //3.3 先创建列，空了就是空
                HSSFCell cell = row.createCell(j);
                Field field = fields.get(0);
                field.setAccessible(true);
                Object value = field.get(field.getName());
                if (value == null) {
                    continue;
                }
                cell.setCellValue((String) value);
            }
        }

        //设置响应信息，写入输出流
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-Disposition", "attachment;filename=" + URLEncoder.encode(filename + ".xls", "utf-8"));
        hssfWorkbook.write(response.getOutputStream());

    }

}
