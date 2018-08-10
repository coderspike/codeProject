package base;

import java.io.Serializable;
import java.util.List;

/**
 * 数据表格，返回页面中数据表格部分
 * 之前针对EasyUI封装的数据格式，现在一直在沿用
 */
public class DataGrid implements Serializable {

    private Long total;// 总记录数
    private List rows;// 每行记录

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
