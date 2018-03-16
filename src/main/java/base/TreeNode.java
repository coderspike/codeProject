package base;

import java.util.Map;

/**
 * 树节点
 */
public class TreeNode implements java.io.Serializable {

    private String id; //节点id
    private String pId;//父级节点id
    private String checkboxName;//以哪个属性做多选按钮
    private String checkboxValue;//多选属性对应的值
    private String name;//节点显示的值
    private Boolean open;//是否展开
    private String pageUrl; //路径
    private String orderNo;//排序
    private String defaultImage;  //默认菜单图标
    private String activeImage;   //活动菜单图标


    private Map<String, Object> attributes;// 其他参数

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPId() {
        return pId;
    }

    public void setPId(String pId) {
        this.pId = pId;
    }

    public String getCheckboxName() {
        return checkboxName;
    }

    public void setCheckboxName(String checkboxName) {
        this.checkboxName = checkboxName;
    }

    public String getCheckboxValue() {
        return checkboxValue;
    }

    public void setCheckboxValue(String checkboxValue) {
        this.checkboxValue = checkboxValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(String defaultImage) {
        this.defaultImage = defaultImage;
    }

    public String getActiveImage() {
        return activeImage;
    }

    public void setActiveImage(String activeImage) {
        this.activeImage = activeImage;
    }
}
