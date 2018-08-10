package util;

import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.*;

public class JDBCArsb {
    private String userName;
    private String passWord;
    private String url;
    private String driver;
    private Connection con;
    private Statement stat;
    private String databaseCode;


    public String getDatabaseCode() {
        return databaseCode;
    }

    public void setDatabaseCode(String databaseCode) {
        this.databaseCode = databaseCode;
    }

    public JDBCArsb(String userName, String passWord, String url, String driver) {
        this.userName = userName;
        this.passWord = passWord;
        this.url = url;
        this.driver = driver;
    }

    public JDBCArsb() {
        this.userName = ResourceUtil.getPropertiesValue("username");
        this.passWord = ResourceUtil.getPropertiesValue("password");
        this.url = ResourceUtil.getPropertiesValue("url");
        this.driver = "com.mysql.jdbc.Driver";
        this.databaseCode = ResourceUtil.getPropertiesValue("databaseCode");
    }

    public Connection getConnection() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, passWord);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public Statement getStatement() {
        getConnection();
        try {
            stat = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stat;
    }

    /**
     * @param sql
     * @return
     * @throws SQLException
     * @Description:(执行给定的SQL语句,DDL语句)
     * @author :dupengfei
     * @date 2017年11月29日
     */
    public boolean executeUpdate(String sql) {
        getStatement();
        try {
            stat.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stat != null)
                    stat.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    /**
     * @param sql
     * @return
     * @throws SQLException
     * @Description:(执行给定的SQL语句,insert/update语句)
     * @author :liurunsheng
     * @date 2018-1-24 11:27:25
     */
    public boolean execute(String sql) {
        getStatement();
        try {
            stat.execute(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stat != null)
                    stat.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    /**
     * @param sql
     * @return List<Map<String,Object>>
     * @Description:执行查询语句
     * @author :dupengfei
     * @date 2017年11月29日
     */
    public List<Map<String, Object>> executeQuery(String sql) {
        getStatement();
        ResultSet rs = null;
        try {
            try {
                rs = stat.executeQuery(sql);
                return ResultToListMap(rs);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (stat != null)
                    stat.close();
                if (con != null)
                    con.close();
            }
            return ResultToListMap(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @param tableName
     * @return boolean
     * @Description:删除表
     * @author :dupengfei
     * @date 2017年11月29日
     */
    public boolean dropTable(String tableName) {
        if (StringUtils.isNotEmpty(tableName)) {
            String sql = "DROP TABLE IF EXISTS " + tableName;
            executeUpdate(sql);
            return true;
        }
        return false;
    }

    /**
     * 为空的字段不进行返回
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private static List<Map<String, Object>> ResultToListMap(ResultSet rs) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        while (rs.next()) {
            ResultSetMetaData md = rs.getMetaData();
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < md.getColumnCount(); i++) {
                if (rs.getObject(i + 1) != null && !"".equals(rs.getObject(i + 1))) {
                    map.put(md.getColumnLabel(i + 1), rs.getObject(i + 1));
                }
            }
            list.add(map);
        }
        return list;
    }

    /**
     * @param sql
     * @return List<Map<String,Object>>
     * @Description:查询sql
     * @author :dupengfei
     * @date 2017年12月12日
     */
    public List<Map<String, Object>> getResultList(String sql) {
        getStatement();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            ResultSet re = stat.executeQuery(sql);
            ResultSetMetaData meat = re.getMetaData();
            int columnCount = meat.getColumnCount();
            String[] columnName = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                columnName[i] = meat.getColumnName(i + 1);
            }
            while (re.next()) {
                Map<String, Object> recode = new HashMap<String, Object>();
                for (int i = 0; i < columnName.length; i++) {
                    Object value = re.getObject(columnName[i]);
                    recode.put(columnName[i], value);
                }
                list.add(recode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 查询所有列名
     *
     * @param tableName
     * @return
     */
    public List getField(String tableName) {
        getStatement();
        List list = new ArrayList();
        try {
            ResultSet re = stat.executeQuery("select * from " + tableName);
            ResultSetMetaData meat = re.getMetaData();
            int columnCount = meat.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                list.add(meat.getColumnName(i + 1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 查询所有表名
     *
     * @param tableName
     * @return
     */
    public List<String> queryTableName(String tableName) {
        getStatement();
        List<String> list = new ArrayList<String>();
        try {
            ResultSet re = stat.executeQuery("show tables");
            int i = 0;
            while (re.next()) {
                list.add(re.getString("Tables_in_" + tableName));
                i = i + 1;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param tableName 表名
     * @param param     Map<String, Object> key：字段名，value: 字段值
     * @Description:插入一条数据
     * @author :dupengfei
     * @date 2017年12月12日
     */
    public String insert(String tableName, Map<String, Object> param) {
        String uuid = "";
        if (param.get("id") != null && !"".equals(param.get("id").toString().trim())) {
            uuid = param.get("id").toString();
        } else {
            uuid = tableName + "_" + UUID.randomUUID().toString().replaceAll("-", "");
            param.put("id", uuid);
        }
        StringBuffer name = new StringBuffer();
        StringBuffer value = new StringBuffer();
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            if (entry.getValue() != null && StringUtils.isNotEmpty(entry.getValue().toString())) {
                name.append(entry.getKey() + ",");
                value.append("'" + entry.getValue() + "',");
            }
        }
        String sql = "INSERT INTO " + tableName + "(" + name.toString().substring(0, name.length() - 1) + ") VALUES (" + value.toString().substring(0, value.length() - 1) + ")";
        executeUpdate(sql);
        return uuid;
    }


    /**
     * @param id
     * @Description:根据id删除
     * @author :dupengfei
     * @date 2017年12月12日
     */
    public void delete(String tableName, String id) {
        String sql = "DELETE FROM " + tableName + " WHERE id = '" + id + "'";
        executeUpdate(sql);
    }

    /**
     * @param tableName 表名
     * @param param     Map<String, Object> key：字段名，value: 字段值
     * @Description:更新一条记录
     * @author :dupengfei
     * @date 2017年12月12日
     */
    public void update(String tableName, Map<String, Object> param) {
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE " + tableName + " set ");
        int i = 0;
        String id = "";
        for (Map.Entry<String, Object> entry : param.entrySet()) {
            if ("id".equals(entry.getKey())) {
                id = (String) entry.getValue();
            } else {
                if (i == param.size() - 1) {
                    sql.append(entry.getKey() + "='" + entry.getValue() + "'");
                } else {
                    sql.append(entry.getKey() + "='" + entry.getValue() + "',");
                }
            }
            i++;
        }
        sql.append("WHERE id = '" + id + "'");
        executeUpdate(sql.toString());
    }

//    /**
//     * 查询列名及字段类型
//     *
//     * @param tableName
//     * @return
//     * @author lyy
//     */
//    public List<Map<String, String>> getFieldAndType(String tableName) {
//        getStatement();
//        @SuppressWarnings({"unchecked", "rawtypes"})
//        List<Map<String, String>> list = new ArrayList();
//
//        com.mysql.jdbc.PreparedStatement pst = null;
//        try {
//            pst = (com.mysql.jdbc.PreparedStatement) con.prepareStatement("select * from " + tableName + " where 1=2");
//            ResultSetMetaData rsd = pst.executeQuery().getMetaData();
//            for (int i = 0; i < rsd.getColumnCount(); i++) {
//                Map<String, String> map = new HashMap<String, String>();
//                map.put("type", rsd.getColumnTypeName(i + 1));
//                map.put("column", rsd.getColumnName(i + 1));
//                list.add(map);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return list;
//
//    }


    /**
     * @param sql
     * @return List<Map<String,String>>
     * @Description:查询sql
     * @author :lyy
     * @date 2018年1月18日14:41:28
     */
    public List<Map<String, String>> getStrList(String sql) {
        getStatement();
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        try {
            ResultSet re = stat.executeQuery(sql);
            ResultSetMetaData meat = re.getMetaData();
            int columnCount = meat.getColumnCount();
            String[] columnName = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                columnName[i] = meat.getColumnName(i + 1);
            }
            while (re.next()) {
                Map<String, String> recode = new HashMap<String, String>();
                for (int i = 0; i < columnName.length; i++) {
                    Object value = re.getObject(columnName[i]);
                    recode.put(columnName[i], (value != null) ? value.toString() : "");
                }
                list.add(recode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取数据表中列的详细信息
     *
     * @param tableName 数据表名
     * @return
     * @author lyy
     */
    public List<Map<String, String>> getColumnDetilesInfo(String tableName) {
        getStatement();
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        DatabaseMetaData dbmd = null;

        try {
            dbmd = (DatabaseMetaData) con.getMetaData();
            ResultSet rs = dbmd.getColumns(null, getSchema(con), tableName, "%");
            while (rs.next()) {
//                  System.out.println("字段名："+rs.getString("COLUMN_NAME")+"--字段注释："+rs.getString("REMARKS")+"--字段数据类型："+rs.getString("TYPE_NAME"));
                Map<String, String> map = new HashMap<String, String>();

                String colName = rs.getString("COLUMN_NAME");
                map.put("code", colName);

                String remarks = rs.getString("REMARKS");
                if (remarks == null || remarks.equals("")) {
                    remarks = colName;
                }
                map.put("name", remarks);

                String length = rs.getString("COLUMN_SIZE");
                map.put("length", length);

                String dbType = rs.getString("TYPE_NAME");
                map.put("dbType", dbType);
                result.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    //其他数据库不需要这个方法 oracle和db2需要
    private static String getSchema(Connection conn) throws Exception {
        String schema;
        schema = conn.getMetaData().getUserName();
        if ((schema == null) || (schema.length() == 0)) {
            throw new Exception("ORACLE数据库模式不允许为空");
        }
        return schema.toUpperCase().toString();

    }
}