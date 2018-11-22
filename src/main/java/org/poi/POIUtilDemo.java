package org.poi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class POIUtilDemo {
    public static final String FILENAME = "fileName";
    public static final String FILEDATE = "fileDate";
    public static final String SHEETNAME = "sheetName";
    public static final String COLNAMETOINDEX = "colNameToIndex";
    public static final String COLINDEXTONAME = "colIndexToName";
    public static final String TOTALROWS = "totalRows";
    public static final String TOTALCOLS = "totalCols";
    public static final String ROWSDATA = "rowsData";
    public static final String CELLTYPE = "cellType";
    public static final String CELLDATE = "cellDate";

    /**
     * excel文件列名不要重
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public static Map<String, Object> readPoi(String filePath) throws Exception {

        File file = new File(filePath);
        String fileName = file.getName();

        if (!file.exists()) {
            throw new Exception("此文件不存在");
        }
        InputStream fileInputStream = new FileInputStream(file);
        //excel工作簿对象
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(fileInputStream);
        //excel工作簿对象个数
        int hssfSheetNum = hssfWorkbook.getNumberOfSheets();
        //获取工作表个数
        if (hssfSheetNum < 0) {
            throw new Exception("此文件没有工作簿");
        }

        Map<String, Object> fileMap = new HashMap<>();
        List<Map<String, Object>> bookList = new ArrayList<>();
        for (int i = 0; i < hssfSheetNum; i++) {
            Map<String, Object> hssfSheetsMap = new HashMap<>();
            //工作表对象
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(i);
            //工作表名称
            String sheetName = hssfSheet.getSheetName();

            //工作表总行数
            int rowlen = hssfSheet.getLastRowNum() + 1;
            //todo　以首行为准 获取此行列数
            int collen = hssfSheet.getRow(0).getLastCellNum();

            List<Object> rowList = new ArrayList<>();
            Map<String, String> colNameToIndexMap = new HashMap<>();
            Map<String, String> colIndexToNameMap = new HashMap<>();
            //todo row=0 列名　execl文件首行为列名
            // 获取每个Sheet每行
            for (int row = 1; row < rowlen; row++) {
                //todo 存在空行直接跳出不在读取数据　execl文件数据行之间不要有空行
                if (hssfSheet.getRow(row) == null) {
                    break;
                }
//                //获取此行列数
//                int collen = hssfSheet.getRow(row).getLastCellNum();

                //Map 列名,列值
                Map<String, Object> rowcolMap = new HashMap<>();
                //获取每行每个单元格
                for (int col = 0; col < collen; col++) {
                    //获取单元格
                    HSSFCell headhssfcell = hssfSheet.getRow(0).getCell(col);
                    HSSFCell hssfCell = hssfSheet.getRow(row).getCell(col);

                    if(hssfCell==null){
                        continue;
                    }

                    String headcelValue = headhssfcell.getStringCellValue();
                    Object cellValue = POIUtilDemo.getCellValueByType(hssfCell);

                    rowcolMap.put(headcelValue, cellValue);

                    if (!colNameToIndexMap.containsKey(String.valueOf(col))) {
                        colNameToIndexMap.put(headcelValue, String.valueOf(col));
                        colIndexToNameMap.put(String.valueOf(col), headcelValue);

                    }
                }
                rowList.add(rowcolMap);
            }

            hssfSheetsMap.put(POIUtilDemo.ROWSDATA, rowList);
            hssfSheetsMap.put(POIUtilDemo.SHEETNAME, sheetName);
            hssfSheetsMap.put(POIUtilDemo.TOTALROWS, rowlen);
            hssfSheetsMap.put(POIUtilDemo.TOTALCOLS, hssfSheet.getRow(0).getLastCellNum());
            hssfSheetsMap.put(POIUtilDemo.COLNAMETOINDEX, colNameToIndexMap);
            hssfSheetsMap.put(POIUtilDemo.COLINDEXTONAME, colIndexToNameMap);


            bookList.add(hssfSheetsMap);
        }

        fileMap.put(POIUtilDemo.FILENAME, fileName);
        fileMap.put(POIUtilDemo.FILEDATE, bookList);


        return fileMap;
    }

    public static Map<String, Object> getCellValueByType(HSSFCell hssfCell) throws Exception {
        //获取单元格类型
        int hssfCellType = hssfCell.getCellType();

        Object cellValue = null;
        String cellType = "";
        Map<String, Object> cellMap = new HashMap<>();

        switch (hssfCellType) {
            case HSSFCell.CELL_TYPE_NUMERIC:
                //数字
            {
                if (HSSFDateUtil.isCellDateFormatted(hssfCell)) {
                    //Date 日期类型
                    cellValue = hssfCell.getDateCellValue();
                    cellType = CellTypeEnum.DATE.type;
                } else {
                    // double
                    cellValue = hssfCell.getNumericCellValue();
                    cellType = CellTypeEnum.DOUBLE.type;
                }
            }
            break;
            case HSSFCell.CELL_TYPE_STRING:
                //字符串
            {
                cellValue = hssfCell.getStringCellValue();
                cellType = CellTypeEnum.STRING.type;
            }
            break;

            case HSSFCell.CELL_TYPE_BOOLEAN:
                //布尔
            {
                cellValue = hssfCell.getBooleanCellValue();
                cellType = CellTypeEnum.BOOLEN.type;
            }
            break;
            case HSSFCell.CELL_TYPE_FORMULA:
                //公式
            {
                cellValue = hssfCell.getCellFormula();
                cellType = CellTypeEnum.FORMULA.type;
            }
            break;

            case HSSFCell.CELL_TYPE_BLANK:
                //空值
            {
                cellValue = "";
                cellType = CellTypeEnum.STRING.type;
            }
            break;
            case HSSFCell.CELL_TYPE_ERROR:
                //非法字符
            {
                throw new Exception("cell[" + (hssfCell.getRowIndex() + 1) + "][" + (hssfCell.getColumnIndex() + 1) + "]单元格类型存在非法字符(cell[行][列])");
            }
            default: {
                throw new Exception("cell[" + (hssfCell.getRowIndex() + 1) + "][" + (hssfCell.getColumnIndex() + 1) + "]单元格类型存在未知类型(cell[行][列])");
            }


        }
        cellMap.put(CELLTYPE, cellType);
        cellMap.put(CELLDATE, cellValue);

        return cellMap;
    }

    /**
     * 写execl文件模板
     * 一个文件的Sheet格式必须相同
     * 原索引－现在索引
     * 原模板列值(列名不能重)－现在模板列(列索引)
     * 列名内容过滤
     */
    public static void writeExcel(Map<String, String> oldColToNewColMap, Map<String, Object> map, String filePath, Map<String, String> filtermap) throws Exception {

        File file = new File(filePath);

        if (!file.exists()) {
            throw new Exception("此文件不存在");
        }
        InputStream fileInputStream = new FileInputStream(file);
        //excel工作簿对象
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(fileInputStream);
        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
        int indexRow = hssfSheet.getLastRowNum() + 1;

        OutputStream outputStream = new FileOutputStream(file);
        List<Map<String, Object>> list = (List<Map<String, Object>>) map.get(POIUtilDemo.FILEDATE);

        for (Map<String, Object> fileDateMap : list) {
            Map<String, String> colNameToIndexMap = (Map<String, String>) fileDateMap.get(POIUtilDemo.COLNAMETOINDEX);
            Map<String, String> colIndexToNameMap = (Map<String, String>) fileDateMap.get(POIUtilDemo.COLINDEXTONAME);
            List<Map<String, Object>> rowsDataList = (List<Map<String, Object>>) fileDateMap.get(POIUtilDemo.ROWSDATA);


            for (Map<String, Object> row : rowsDataList) {

                boolean filterFlag = false;

                if (!(colIndexToNameMap.keySet().isEmpty())) {
                    Set<String> oldSet = colIndexToNameMap.keySet();
                    Set<String> filterSet = filtermap.keySet();
                    filterSet.retainAll(oldSet);

                    if (!filterSet.isEmpty()) {
                        for (String s : filterSet) {
                            String colNameFilter = colIndexToNameMap.get(s);
                            String colvalue = (String) ((Map<String, Object>) row.get(colNameFilter)).get(POIUtilDemo.CELLDATE);
                            String filterColvalue = filtermap.get(s);
                            if (!colvalue.equals(filterColvalue)) {
                                filterFlag = true;
                                break;
                            }
                        }
                    }
                }

                if (filterFlag) continue;

                HSSFRow hssfRow = hssfSheet.createRow(indexRow);
                //遍历行
                for (Map.Entry<String, Object> col : row.entrySet()) {
                    String colName = col.getKey();
                    String colNameIndex = colNameToIndexMap.get(colName);

                    if (oldColToNewColMap.containsKey(colNameIndex)) {
                        Map<String, String> cell = (Map<String, String>) col.getValue();
                        String data = String.valueOf(cell.get(POIUtilDemo.CELLDATE));
                        String type = cell.get(POIUtilDemo.CELLTYPE);

                        if (!data.equals("")) {
                            String newIndex = oldColToNewColMap.get(colNameIndex);
                            HSSFCell hssfCell = hssfRow.createCell(Short.parseShort(newIndex));

                            if (type.equals(CellTypeEnum.DATE.type)) {
                                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
                                data = sf.format(new Date(data));

                            }

                            if(type.equals(CellTypeEnum.DOUBLE.type)){
                                data=String.valueOf((int) (Double.valueOf(data)*100));
                            }

                            hssfCell.setCellValue(data);
                        }
                    }
                }
                indexRow++;
            }

        }


        hssfWorkbook.write(outputStream);
        outputStream.close();
    }

    public static void main(String[] args) throws Exception {
        String filepath = "C:\\Users\\Administrator\\Documents\\工作任务模板-- - 副本.xls";
        String filepath2 = "C:\\Users\\Administrator\\Documents\\测试_工作任务 - 副本.xls";
        Map<String, Object> map = POIUtilDemo.readPoi(filepath);

        System.out.println(JSONObject.toJSONString(map));

        Map<String, String> oldToNewIndex = new HashMap<>();
        oldToNewIndex.put("2", "2");
        oldToNewIndex.put("3", "3");
        oldToNewIndex.put("4", "4");
        oldToNewIndex.put("5", "5");

        Map<String, String> filterMap = new HashMap<>();
//        filterMap.put("0", "日常工作");
        System.out.println(JSON.toJSONString(map));
        POIUtilDemo.writeExcel(oldToNewIndex, map, filepath2, filterMap);

    }


    enum CellTypeEnum {
        DATE("date"),
        DOUBLE("double"),
        STRING("string"),
        BOOLEN("boolean"),
        FORMULA("formula");

        private String type;

        CellTypeEnum(String type) {
            this.type = type;
        }
    }
}
