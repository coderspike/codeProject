package dagong.dao;


import dagong.domain.TApplyLoan;
import dagong.pageModel.ApplyLoan;

import java.util.List;
import java.util.Map;

/***********************************
 * 2018-02-01
 *
 * @author:GWB
 * 服务端申请贷款Dao
 ***********************************
 */
public interface ApplyLoanDao {

    /**
     * 查询方法
     */
    List<TApplyLoan> findList(ApplyLoan applyLoan);

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

    /**
     * 获取实体
     */
    void get(ApplyLoan applyLoan);

    /**
     * 获取项目管理列表项
     *
     * @param applyLoan
     * @return
     */
    List<TApplyLoan> queryList(ApplyLoan applyLoan);

    /**
     * 查询项目关联出的相关信息
     *
     * @param applyLoan
     * @return
     */
    List<TApplyLoan> queryConList(ApplyLoan applyLoan);

    /**
     * 查询项目关联出的相关信息
     *
     * @param applyLoan
     * @return
     */
    List<Map<String, Object>> queryAllList(ApplyLoan applyLoan);

    /**
     * 查询用户名下项目关联出的相关信息数量
     *
     * @param applyLoan
     * @return
     */
    long queryAllListNum(ApplyLoan loan);
}
