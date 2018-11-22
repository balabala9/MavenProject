package org.poi;

import com.alibaba.fastjson.JSON;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class POIUtil {

    public static Map<String, Object> readPoi(String filePath) throws Exception {

        File file = new File(filePath);

        if (!file.exists()) {
            throw new Exception("此文件不存在");
        }

        InputStream fileInputStream = new FileInputStream(file);

        //excel工作簿对象
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(fileInputStream);
        //excel工作簿对象个数
        int hssfworkbookNum = hssfWorkbook.getNumberOfSheets();

        if (hssfworkbookNum < 0) {
            throw new Exception("此文件没有工作簿");
        }

        Map<String, Object> hssfworkbookMap = new HashMap<>();

        for (int i = 0; i < hssfworkbookNum; i++) {
            //工作表对象
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(i);
            //工作表名称
            String sheetName = hssfSheet.getSheetName();
            //工作表总行数
            int rowlen = hssfSheet.getLastRowNum();

            List<Object> rowList = new ArrayList<>();
            for (int row = 1; row < rowlen; row++) {
                //每行列数
                if (hssfSheet.getRow(row) == null) {
                    break;
                }

                int collen = hssfSheet.getRow(row).getLastCellNum();


                Map<String, String> rowcolMap = new HashMap<>();
                for (int col = 0; col < collen; col++) {

                    //获取单元格
                    HSSFCell hssfCell = hssfSheet.getRow(row).getCell(col);
                    HSSFCell headhssfcell = hssfSheet.getRow(0).getCell(col);
                    System.out.println(row + "-" + rowlen + sheetName + col);
                    if (hssfCell != null) {

                        headhssfcell.setCellType(Cell.CELL_TYPE_STRING);
                    }

                    if (hssfCell == null) {
                        break;
                    }

                    String headcelValue = headhssfcell.getStringCellValue();
                    String cellValue = "";

                    if (headcelValue.equals("完成时间")) {
                        if (HSSFDateUtil.isCellDateFormatted(hssfCell)) {
                            System.out.println("是日期格式");
                            SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");
                            Date date = hssfCell.getDateCellValue();
                            cellValue = sd.format(date);
                        }
                    } else {
                        hssfCell.setCellType(Cell.CELL_TYPE_STRING);
                        cellValue = hssfCell.getStringCellValue();
                    }


                    rowcolMap.put(headcelValue, cellValue);
                }
                rowList.add(rowcolMap);
            }

            hssfworkbookMap.put(sheetName, rowList);
        }

        return hssfworkbookMap;
    }


    public static void writePoi(String filepath, Map<String, Object> map) throws IOException {

        InputStream fileInputStream = new FileInputStream(filepath);
        //excel工作簿对象
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(fileInputStream);
        //工作表对象
        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
        int rowlen = hssfSheet.getLastRowNum() + 1;

        FileOutputStream out = new FileOutputStream(filepath);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            List<Object> list = (List<Object>) entry.getValue();
            String sheetName = entry.getKey();
            for (int index = 0; index < list.size(); index++) {
                Map<Object, Object> rowMap = (Map<Object, Object>) list.get(index);
                if (rowMap.get("工作类别").equals("阶段性工作")) {
                    HSSFRow hssfRow = hssfSheet.createRow(rowlen);
                    HSSFCell nameHssfCell = hssfRow.createCell(2);
                    nameHssfCell.setCellType(Cell.CELL_TYPE_STRING);
                    nameHssfCell.setCellValue(sheetName);

                    HSSFCell contentHssfCell = hssfRow.createCell(3);
                    contentHssfCell.setCellValue((String) rowMap.get("工作内容"));


                    if (!((String) rowMap.get("完成时间")).equals("")) {
                        HSSFCell completeTimeHssfCell = hssfRow.createCell(4);
                        completeTimeHssfCell.setCellValue((String) rowMap.get("完成时间"));
                    }


                    if (!rowMap.get("权重").equals("")) {
                        HSSFCell weight = hssfRow.createCell(5);
                        Double weightValue = Double.valueOf(String.valueOf(rowMap.get("权重"))) * 100;
                        weight.setCellValue(Math.floor(weightValue));
                    }


                    rowlen++;

                }

            }

        }

        hssfWorkbook.write(out);
        out.flush();
        out.close();

    }


    public static void main(String[] args) {

        try {
            String filepath = "/home/li/桌面/工作计划-资质.xls";
            String writefilepath = "C:\\Users\\Administrator\\Documents\\测试_工作任务 - 副本.xls";

            Map<String, Object> map = POIUtil.readPoi(filepath);

            System.out.println(JSON.toJSONString(map));
            POIUtil.writePoi(writefilepath, map);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
