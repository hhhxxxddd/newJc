package com.jinchi.common.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.jinchi.common.domain.FireMageTestItems;
import io.swagger.models.auth.In;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件导出工具类
 * @author Wayne
 * @date 2019/5/15
 */
public class ExportUtil {

    /**
     * 无模块导出Excel方法，
     * 参数data格式: [
     *      {
     *          "姓名": "张三";
     *          "年龄": "23";
     *          "性别": "男"
     *      },
     *      {
     *          "姓名": "李四";
     *          "年龄": "24";
     *          "性别": "男"
     *      }
     * ]
     *
     * @param data 需要导出的数据
     * @return 创建好的Excel文件，用于前端下载
     */
    public static String exportExcel(List<Map<String, Object>> data,String path){
        // 从参数data中解析出打印的每列标题，放入title中
        List<String> title = Lists.newArrayList();
        for(Map.Entry<String, Object> entry : data.get(0).entrySet()) {
            title.add(entry.getKey());
        }
        // 新建一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // Excel中的sheet
        HSSFSheet sheet = wb.createSheet();
        // sheet中的行，0表示第一行
        HSSFRow row = sheet.createRow(0);
        // 设置标题居中
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // sheet中的单元格
        HSSFCell cell = null;

        // 给第一行赋值，值为我们从参数中解析出的标题，因此需要我们在传参的时候需要严格按照约定
        for(int i = 0; i < title.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(title.get(i));
            cell.setCellStyle(cellStyle);
        }

        // 根据参数内容数量，创建表格行数
        for(int i = 0; i < data.size(); i++) {
            row = sheet.createRow(i + 1);

            Map<String, Object> values = data.get(i);

            // 将参数插入每一个单元格
            for(int k = 0; k < title.size(); k++) {
                Object value = values.get(title.get(k));
                if(null == value) {
                    value = "";
                }
                String val = JSON.toJSONString(value);
                row.createCell(k).setCellValue(value.toString());
            }
        }

        // 将文件写到硬盘中，将来根据需求，这个是写在path的文件夹下  枚举类维护
        File serverFile = null;
        try {
            serverFile = new File(verifyPath(path, NumberGenerator.fileCode(".xls")));
            serverFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(serverFile);
            wb.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            System.out.println("导出失败");
        }
        return serverFile.getName();
    }

    public static String fireMgaeExport(List<Map<String, Object>> data, String path, List<FireMageTestItems> items){
        List<String> title = Lists.newArrayList();
        title.add("批号");
        title.add("检验状态");

        for(FireMageTestItems i: items) {
            title.add(i.getName());
        }

        // 新建一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // Excel中的sheet
        HSSFSheet sheet = wb.createSheet();
        // sheet中的行，0表示第一行
        HSSFRow row = sheet.createRow(0);
        // 设置标题居中
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // sheet中的单元格
        HSSFCell cell = null;

        // 给第一行赋值，值为我们从参数中解析出的标题，因此需要我们在传参的时候需要严格按照约定
        for(int i = 0; i < title.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(title.get(i));
            cell.setCellStyle(cellStyle);
        }

        // 根据参数内容数量，创建表格行数
        for(int i = 0; i < data.size(); i++) {
            row = sheet.createRow(i + 1);

            Map<String, Object> values = data.get(i);

            // 将参数插入每一个单元格
            for(int k = 0; k < title.size(); k++) {
                Object value = values.get(title.get(k));
                if(null == value) {
                    value = "";
                }
                String val = JSON.toJSONString(value);
                row.createCell(k).setCellValue(value.toString());
            }
        }

        // 将文件写到硬盘中，将来根据需求，这个是写在path的文件夹下  枚举类维护
        File serverFile = null;
        try {
            serverFile = new File(verifyPath(path, NumberGenerator.fileCode(".xls")));
            serverFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(serverFile);
            wb.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            System.out.println("导出失败");
        }
        return serverFile.getName();

    }

    public static String fireMgaeCExport(List<Map<String, Object>> data, String path, List<String> items){
        List<String> title = Lists.newArrayList();
        title.add("批号");

        for(String i: items) {
            title.add(i);
        }

        // 新建一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // Excel中的sheet
        HSSFSheet sheet = wb.createSheet();
        // sheet中的行，0表示第一行
        HSSFRow row = sheet.createRow(0);
        // 设置标题居中
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // sheet中的单元格
        HSSFCell cell = null;

        // 给第一行赋值，值为我们从参数中解析出的标题，因此需要我们在传参的时候需要严格按照约定
        for(int i = 0; i < title.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(title.get(i));
            cell.setCellStyle(cellStyle);
        }

        // 根据参数内容数量，创建表格行数
        for(int i = 0; i < data.size(); i++) {
            row = sheet.createRow(i + 1);

            Map<String, Object> values = data.get(i);

            // 将参数插入每一个单元格
            for(int k = 0; k < title.size(); k++) {
                Object value = values.get(title.get(k));
                if(null == value) {
                    value = "";
                }
                String val = JSON.toJSONString(value);
                row.createCell(k).setCellValue(value.toString());
            }
        }

        // 将文件写到硬盘中，将来根据需求，这个是写在path的文件夹下  枚举类维护
        File serverFile = null;
        try {
            serverFile = new File(verifyPath(path, NumberGenerator.fileCode(".xls")));
            serverFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(serverFile);
            wb.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            System.out.println("导出失败");
        }
        return serverFile.getName();

    }

    private static String verifyPath(String runningPath, String fileName) {
        File path = new File(runningPath.substring(0,runningPath.length()-1));
        //如果文件夹不存在 就创建
        if (!path.exists()) path.mkdirs();
        return runningPath + fileName;
    }

    /**
     * 获取表头数据
     * @param filePath
     * @return
     */
    public static List getExcelHead(String filePath){
        File finalXlsxFile;
        Workbook workBook;
        OutputStream out;
        int num = 0;
        int colNum = 0;
        Sheet sheet = null;

        finalXlsxFile = new File(filePath);
        try {
            workBook = getWorkbok(finalXlsxFile);
            sheet = workBook.getSheetAt(0);
            num = sheet.getLastRowNum();
            Row row = sheet.getRow(0);
            colNum = row.getLastCellNum();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<String> ans = new ArrayList<>();
        Row head = sheet.getRow(0);
        for(int i=0;i<colNum;i++){
            ans.add(head.getCell(i).toString());
        }
        return ans;
    }

    /**
     * 获取表格数据
     * @param filePath
     * @param heads
     * @return
     */
    public static List<Map<String,Object>> getExcelInfo(String filePath,List<String> heads){
        File finalXlsxFile;
        Workbook workBook;
        OutputStream out;
        int rowNum = 0;
        Sheet sheet = null;

        finalXlsxFile = new File(filePath);
        try {
            workBook = getWorkbok(finalXlsxFile);
            sheet = workBook.getSheetAt(0);
            rowNum = sheet.getLastRowNum();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<Map<String,Object>> maps = new ArrayList<>();
        for(int i=1;i<=rowNum;i++){
            Row row = sheet.getRow(i);
            Map<String,Object> map = new HashMap<>();
            for(int j=0;j<heads.size();j++){
                map.put(heads.get(j),row.getCell(j).toString());
            }
            maps.add(map);
        }
        return maps;
    }
    private static Workbook getWorkbok(File file) throws IOException {
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if (file.getName().endsWith("xls")) { // Excel&nbsp;2003
            wb = new HSSFWorkbook(in);
        } else if (file.getName().endsWith("xlsx")) { // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

}