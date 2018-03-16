package dagong.controller;


import base.BaseController;
import base.Json;
import common.ServerResponse;
import dagong.pageModel.ApplyLoan;
import dagong.service.ApplyLoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/***********************************
 * 2018-02-01
 *
 * @author:GWB 服务端申请贷款controller
 ***********************************
 */
@Controller
@RequestMapping("/applyLoanController")
public class ApplyLoanController extends BaseController {

    @Resource
    ApplyLoanService applyLoanService;

    /**
     * 日志记录
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取页面
     *
     * @return
     */
    @RequestMapping("/getApplyLoan.form")
    public String getCustomerUserPage() {
        return "platform/applyLoanManage/applyLoanManage";
    }

    /**
     * 获取添加页面
     *
     * @return
     */
    @RequestMapping("/getCustomerUserAddPage.form")
    public String getCustomerUserAddPage() {
        return "platform/ApplyLoanManage/";
    }

    /**
     * 获取列表项
     *
     * @param applyLoan
     */
    @RequestMapping("/datagrid.form")
    public void datagrid(ApplyLoan applyLoan) {
        super.writeJson(applyLoanService.findList(applyLoan));
    }

    /**
     * 获取总条数
     */
    @RequestMapping("/datagridNum.form")
    public void datagridNum(ApplyLoan applyLoan) {
        Json json = new Json();
        json.setSuccess(true);
        json.setObj(applyLoanService.listNum(applyLoan));
        super.writeJson(json);
    }


    /**
     * 添加方法
     */
    @RequestMapping(value = "/add.form")
    @ResponseBody
    public ServerResponse add(ApplyLoan applyLoan) {
        int result = applyLoanService.insert(applyLoan);
        if (result > 0) {
            return ServerResponse.createBySuccess("添加成功");
        }
        return ServerResponse.createBySuccess();
    }

    /**
     * 添加方法供方法调用
     *
     * @param applyLoan
     * @return
     */
    public String insert(ApplyLoan applyLoan) {
        applyLoanService.insert(applyLoan);
        return applyLoan.getId();
    }

    /**
     * 修改方法
     */
    @RequestMapping("/edit.form")
    @ResponseBody
    public ServerResponse edit(ApplyLoan applyLoan) {
        int result = applyLoanService.update(applyLoan);
        if (result > 0) {
            return ServerResponse.createBySuccess("添加成功");
        }
        return ServerResponse.createBySuccess();
    }

    /**
     * 删除方法
     */
    @RequestMapping("/delete.form")
    @ResponseBody
    public ServerResponse delete(ApplyLoan applyLoan) {
        int result = applyLoanService.delete(applyLoan);
        if (result > 0) {
            return ServerResponse.createBySuccess("删除成功");
        }
        return ServerResponse.createBySuccess();
    }


}
