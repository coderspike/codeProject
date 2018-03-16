package dagong.service.impl;

import base.DataGrid;
import dagong.dao.ApplyLoanDao;
import dagong.pageModel.ApplyLoan;
import dagong.service.ApplyLoanService;
import org.springframework.stereotype.Service;
import util.ChangeModelUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***********************************
 * 2018-02-01
 *
 * @author:GWB 服务端贷款申请serviceImpl
 ***********************************
 */
@Service("applyLoanService")
public class ApplyLoanServiceImpl implements ApplyLoanService {

    @Resource
    ApplyLoanDao applyLoanDao;

    @Override
    public DataGrid findList(ApplyLoan applyLoan) {
        DataGrid dg = new DataGrid();
        Map dictionaryMap = new HashMap();
        dictionaryMap.put("businessNode", "flowNode");
        dictionaryMap.put("bussinessNodeType", "flowNodeStatus");
        dictionaryMap.put("businessType", "flowStatus");
        List<?> list = ChangeModelUtil.changeModel(applyLoanDao.findList(applyLoan), applyLoan.getClass().getName(),
                dictionaryMap);
        dg.setRows(list);
        return dg;
    }

    @Override
    public Long listNum(ApplyLoan applyLoan) {
        return applyLoanDao.listNum(applyLoan);
    }

    @Override
    public int insert(ApplyLoan applyLoan) {
        return applyLoanDao.insert(applyLoan);
    }

    @Override
    public int delete(ApplyLoan applyLoan) {
        return applyLoanDao.delete(applyLoan);
    }

    @Override
    public int update(ApplyLoan applyLoan) {
        return applyLoanDao.update(applyLoan);
    }

}
