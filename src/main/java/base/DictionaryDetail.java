package base;


import java.io.Serializable;
import java.util.Date;

/**
 * 字典明细
 */
public class DictionaryDetail extends BasePageModel implements Serializable {

    private String id;//编号
    private String detailTypeId;//明细编号
    private String detailName;//名称
    private String typeCode;//数据字典类型
    private Integer orderNo;//顺序
    private String detailCode;//code
    private String parentId;//父编号
    private Date lastCreateTime;//最后一次操作时间
    private String lastCreater;//最后一次操作人
    private String lastHandleType;//最后一次操作类型
    private String dataStatus;//数据状态
    private String dataStatusStr;//数据状态
    private String dictionaryImages;//图片
    private String oldImages;//旧图片

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getDictionaryImages() {
        return dictionaryImages;
    }

    public void setDictionaryImages(String dictionaryImages) {
        this.dictionaryImages = dictionaryImages;
    }

    public String getOldImages() {
        return oldImages;
    }

    public void setOldImages(String oldImages) {
        this.oldImages = oldImages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Date getLastCreateTime() {
        return lastCreateTime;
    }

    public void setLastCreateTime(Date lastCreateTime) {
        this.lastCreateTime = lastCreateTime;
    }

    public String getLastCreater() {
        return lastCreater;
    }

    public void setLastCreater(String lastCreater) {
        this.lastCreater = lastCreater;
    }

    public String getLastHandleType() {
        return lastHandleType;
    }

    public void setLastHandleType(String lastHandleType) {
        this.lastHandleType = lastHandleType;
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getDetailTypeId() {
        return detailTypeId;
    }

    public void setDetailTypeId(String detailTypeId) {
        this.detailTypeId = detailTypeId;
    }

    public String getDetailCode() {
        return detailCode;
    }

    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }

    public String getDataStatusStr() {
        return dataStatusStr;
    }

    public void setDataStatusStr(String dataStatusStr) {
        this.dataStatusStr = dataStatusStr;
    }
}
