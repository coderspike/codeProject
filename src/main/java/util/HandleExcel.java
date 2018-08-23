//package util;
//
//import com.google.common.collect.Lists;
//import com.sun.rowset.internal.Row;
//import org.apache.commons.lang3.StringUtils;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.Iterator;
//import java.util.LinkedHashSet;
//import java.util.List;
//
///**
// * @ClassName HandleExcel
// * @Description TODO
// * @Author 39446
// * @Date 2018/8/21 10:16
// * @Version 1.0
// **/
//public class HandleExcel {
//    //Excel 读取参数
//
//    private static String EXCEL_2003 = "xls";
//    private static String EXCEL_2007_AFTER = "xlsx";
//    private static int READ_SHEET = 1;
//    private static int READ_ROW_NUMBER = 1;
//    private static int READ_CELL = 0;
//    private static String FILE_PATH = "C:\\Users\\39446\\Desktop\\7月榜单默认排序及子榜实体情况V2.xlsx";
//    private static String SQL_PATH = "C:\\Users\\39446\\Desktop\\出道新人.txt";
//
//    //数据库连接参数
//
//    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://localhost/STUDENTS";
//    static final String USER = "username";
//    static final String PASS = "password";
//
//    public static void main(String[] args) {
//        HandleExcel handleExcel = new HandleExcel();
//        LinkedHashSet<List<String>> content = handleExcel.readExcel(FILE_PATH);
//        for (List<String> str : content) {
//            str.forEach(n -> System.out.println(n));
//            System.out.println("------------------");
//        }
//        handleExcel.generateSqlFile(content);
//    }
//
//    /**
//     * 生成sql文件
//     */
//    public void generateSqlFile(LinkedHashSet<List<String>> content) {
//        for (int i = 1; i <= content.size(); i++) {
//            for (Iterator<List<String>> iterator = content.iterator(); iterator.hasNext(); ) {
//                List<String> next = iterator.next();
//                String sql = "";
//                for (String str : next) {
//                    sql = "insert into iindex_dimensionality_rank_artist_relation values " + i + "," + "," + str;
//                }
//                System.out.println(sql);
//            }
//        }
//
//    }
//
//    /**
//     * 插入数据
//     */
//    public void insertData(List<String> content) {
//        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
//            Class.forName(JDBC_DRIVER);
//            String insertTableSQL = "INSERT INTO DBUSER"
//                    + "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) VALUES"
//                    + "(?,?,?,?)";
//            try (PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL)) {
//                preparedStatement.setInt(1, 11);
//                preparedStatement.setString(2, "mkyong");
//                preparedStatement.setString(3, "system");
//                preparedStatement.executeUpdate();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 判断文件类型
//     *
//     * @param filePath
//     * @return
//     */
//    private Workbook getReadWorkBookType(String filePath) {
//        FileInputStream is;
//        try {
//            is = new FileInputStream(filePath);
//            if (filePath.toLowerCase().endsWith(EXCEL_2007_AFTER)) {
//                return new XSSFWorkbook(is);
//            }
//            if (filePath.toLowerCase().endsWith(EXCEL_2003)) {
//                return new HSSFWorkbook(is);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 读取Excel
//     *
//     * @param sourceFilePath
//     * @return
//     */
//    public LinkedHashSet<List<String>> readExcel(String sourceFilePath) {
//        Workbook workbook;
//        LinkedHashSet<List<String>> contents = new LinkedHashSet<>();
//        try {
//            workbook = getReadWorkBookType(sourceFilePath);
//            Sheet sheet = workbook.getSheetAt(READ_SHEET);
//            for (int rowNum = READ_ROW_NUMBER; rowNum <= sheet.getLastRowNum(); rowNum++) {
//                Row row = sheet.getRow(rowNum);
//                List<String> rowContent = Lists.newArrayList();
//                for (int j = READ_CELL; j < row.getPhysicalNumberOfCells(); j++) {
//                    Cell cell = row.getCell(j);
//                    rowContent.add(getCellStringVal(cell).trim());
//                }
//                contents.add(rowContent);
//            }
//            return contents;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return contents;
//    }
//
//    /**
//     * 判断单元格数据类型
//     *
//     * @param cell
//     * @return
//     */
//    private String getCellStringVal(Cell cell) {
//        CellType cellType = cell.getCellTypeEnum();
//        switch (cellType) {
//            case NUMERIC:
//                return String.valueOf(cell.getNumericCellValue());
//            case STRING:
//                return cell.getStringCellValue();
//            case BOOLEAN:
//                return String.valueOf(cell.getBooleanCellValue());
//            case FORMULA:
//                return cell.getCellFormula();
//            case BLANK:
//                return "";
//            case ERROR:
//                return String.valueOf(cell.getErrorCellValue());
//            default:
//                return StringUtils.EMPTY;
//        }
//    }
//}
