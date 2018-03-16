package base;

/**
 * 封装了分页的信息
 */
public class BasePageModel implements java.io.Serializable {

    private static final long serialVersionUID = -8963316942190157192L;

    private String[] ids;
    private int page;// 当前页
    private int rows = 10;// 每页显示记录数
    private int offset = -1;//当前偏移量
    private String sortord;// 排序字段名和排序方式


    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public String getSortord() {
        return sortord;
    }

    public void setSortord(String sortord) {
        this.sortord = sortord;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getOffset() {
        if (page == 0) {
            return -1;
        }
        return (this.page - 1) * this.rows;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }


}
