package dagong.service;


import base.DataGrid;
import dagong.pageModel.ApplyLoan;

/***********************************
 * 2018-02-01
 *
 * @author:GWB 服务端申请贷款service
 ***********************************
 */
public interface ApplyLoanService {

    /**
     * 查询方法
     */
    DataGrid findList(ApplyLoan applyLoan);

    /**
     * 数量查询
     */
    Long listNum(ApplyLoan applyLoan);

    /**
     * 插入方法
     */
    int insert(ApplyLoan applyLoan);

    /**
     * 删除方法
     */
    int delete(ApplyLoan applyLoan);

    /**
     * 修改方法
     */
    int update(ApplyLoan applyLoan);


}
