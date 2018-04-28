//
//package util;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import net.dagongsoft.platform.collectionManage.dao.InterfacesManagementDao;
//import net.dagongsoft.platform.collectionManage.domain.TInterfacesManagement;
//import net.dagongsoft.platform.collectionManage.pagemodel.InterfacesManagement;
//import net.dagongsoft.platform.configurationmanage.dao.InterfaceAccessLogDao;
//import net.dagongsoft.platform.configurationmanage.domain.TAdmittanceAuditResult;
//import net.dagongsoft.platform.configurationmanage.pagemodel.InterfaceAccessLog;
//import net.dagongsoft.platform.configurationmanage.service.InterfaceDataService;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.UnsupportedEncodingException;
//import java.util.*;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * @Description: 公明接口调用工具类
// * @author:dupengfei
// * @date 2018年4月2日
// * @version V1.0
// */
//public class GMInterfaceUtil {
//
//	private final static String verificationBlacklistLoanUrl = "https://way.jd.com/huiyutech/p2pBlackList";
//	private final static String personalRiskUrl = "http://123.59.76.144/ws/black/compQuery";
//
//	private final static String enterpriseNegativeIsHaveUrl = "http://123.59.76.144/ws/court/nagativeList";
//	private final static String enterpriseTaxBlackUrl = "http://123.59.76.144/ws/repository/taxBlack";
//	private final static String enterpriseInformationUrl = "http://open.api.tianyancha.com/services/v4/open/baseinfo";
//	private final static String taxesUrl = "http://open.api.tianyancha.com/services/v4/open/ownTax";
//	private final static String enterpriseRiskUrl = "http://open.api.tianyancha.com/services/v4/open/riskInfo";
//
//	private final static String medthodPost = "post";
//	private final static String medthodGet = "get";
//
//	private final static String verificationBlackAppkey = "eefdfe5fa10dd4058302fd6a824103c1";
//	private final static String apikeyXinShu = "093A93DD4B0B92882AE4A8C09B9572E7";
//	private final static String signXinShu = "2qjn3";
//	public final static String authorization = "51a89735-0254-483a-abcd-71b1ebcb78a4";
//
//	private static RestTemplate restTemplate = new RestTemplate();
//
//    private static final String SQL_INSERT_SPITTER =
//            "insert into admittance_audit_result (id, content, isPass, applyFlowId,createTime) values (?, ?, ?, ?, ?)";
//
//    public static final String SQL_INSERT_ENTERPRISE =
//            "insert into company_information (id, "
//            + "loanCompanyName, "
//            + "applyFlowId, "
//            + "legalPerson,"
//            + "registrationTime,"
//            + "registeredCapital,"
//            + "companyType,"
//            + "operatingPeriod,"
//            + "registeredAddress,"
//            + "registrationAuthority,"
//            + "businessScope,"
//            + "industry,"
//            + "registeredNo,"
//            + "organizationCode,"
//            + "taxpayerIdentificationCode"
//            + "approvedDate，"
//            + "creditCode"
//            + ") values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?，?)";
//
//
//
//	/**
//	 * @Description:个人信息验证（调用接口数据存入日志表）
//	 * @author :dupengfei
//	 * @date 2018年4月12日
//	 * @param name  姓名（必填）
//	 * @param idCard 身份证号（必填）
//	 * @param mobile  手机号（必填）
//	 * @param bankCardNo 银行卡号（选填）
//	 * @param applyFlowId 项目id（必填）
//	 * @return String （0：正常，1：异常，2：警告）
//	 */
//	public static String personalVerification(String name,String idCard,String mobile,String bankCardNo,String applyFlowId) {
//		String returnStr = "0";
//		//1.调用网络借贷黑名单接口
//		if(!verificationBlacklistLoan(idCard,mobile,name,applyFlowId)) {
//			returnStr = "1";
//		}
//
//		//2.个人公安不良行为排查接口，接口目前不能调用
//
//
//		//3.调用个人风险信息综合查询接口
//		if("0".equals(returnStr)){
//			returnStr = personalRisk(name,idCard,mobile,bankCardNo,applyFlowId);
//		}
//		return returnStr;
//	}
//
//	/**
//	 * @Description:企业信息验证（调用接口数据存入日志表）
//	 * @author :dupengfei
//	 * @date 2018年4月13日
//	 * @param entName 公司名称（必填）
//	 * @param regNo   公司注册号（选填）
//	 * @param taxName  纳税人名称（纳税人名称,与法人身份证号码、税务登记号至少一个必填）
//	 * @param idCard   法人身份证号码
//	 * @param taxNo    税务登记号
//	 * @param applyFlowId 项目id（必填）
//	 * @return String （0：正常，1：异常）
//	 * @throws UnsupportedEncodingException
//	 */
//	public static String enterpriseVerification(String entName,String regNo,String taxName,String idCard,String taxNo,String applyFlowId) throws UnsupportedEncodingException{
//		String returnStr = "0";
//
//		//1.企业负面信息综合查询 验证
//		if(!enterpriseNegativeIsHave(entName,regNo,applyFlowId)) {
//			returnStr = "1";
//		}
//
//		//2.税务负面信息查询 验证
//		if("0".equals(returnStr)  && !enterpriseTaxBlack(taxName,idCard,taxNo,applyFlowId)) {
//			returnStr = "1";
//		}
//
//		//3.获取企业基本信息(天眼查)
//		if("0".equals(returnStr)  && !enterpriseInformation(entName,applyFlowId)) {
//			returnStr = "1";
//		}
//
//		//4.欠税公告(天眼查)
//		if("0".equals(returnStr)  && !taxes(entName,applyFlowId)) {
//			returnStr = "1";
//		}
//
//		//5.企业风险(天眼查)
//		if("0".equals(returnStr)  && !enterpriseRisk(entName,applyFlowId)) {
//			returnStr = "1";
//		}
//
//		doJob(entName,applyFlowId,returnStr);
//		return returnStr;
//	}
//
//
//	/**
//	 * @Description:网络借贷黑名单验证
//	 * @author :dupengfei
//	 * @date 2018年4月2日
//	 * @param idcard 身份证号码; 身份证号码、手机号码至少有1个
//	 * @param mobile 手机号码; 身份证号码、手机号码至少有1个
//	 * @param name 姓名；增加姓名字段可以提高准确性和命中率
//	 * @param applyFlowId 项目id（必填）
//	 * @return boolean true不是老赖，false是老赖
//	 */
//	private static boolean verificationBlacklistLoan(String idcard,String mobile,String name,String applyFlowId){
//		Date bigDate = new Date();
//		boolean isNormal = true;
//		HCRequest request = new HCRequest();
//		request.setUrl(verificationBlacklistLoanUrl);
//		request.setHttpMedthod(medthodPost);
//		Map<String,String> paramMap = new HashMap<>();
//		paramMap.put("appkey", verificationBlackAppkey);
//		paramMap.put("idcard", idcard);
//		paramMap.put("mobile", mobile);
//		paramMap.put("name", name);
//		String params = getParamStr(paramMap);
// 		request.setParams(params);
//		String data =  HttpClientUtil.getSourceCode(request);
// 		JSONObject jSONObject = JSON.parseObject(data);
// 		String dataState = "0";
// 		if("10000".equals(jSONObject.get("code").toString())) {
// 			dataState = "1";
// 			@SuppressWarnings("unchecked")
// 			Map<String,Object> result =  (Map<String, Object>) jSONObject.get("result");
// 			if(result.containsKey("in_blacklist") && "1".equals(result.get("in_blacklist").toString())) {
// 				isNormal = false;
// 			}
// 		}
//
// 		TAdmittanceAuditResult admittanceAuditResult = new TAdmittanceAuditResult();
// 		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
// 		admittanceAuditResult.setId(uuid);
// 		admittanceAuditResult.setCreateTime(new Date());
// 		admittanceAuditResult.setIsPass(isNormal == true ?"1":"0");
// 		admittanceAuditResult.setApplyFlowId(applyFlowId);
// 		admittanceAuditResult.setContent(name + "-" + idcard + ":" + "网络借贷黑名单验证");
// 		saveLog(request.getUrl(),request.getParams(),data,bigDate,dataState,admittanceAuditResult);
//		return isNormal;
//	}
//
//	/**
//	 * @Description:个人风险信息综合查询
//	 * @author :dupengfei
//	 * @date 2018年4月12日
//	 * @param name  姓名（必填）
//	 * @param idCard 身份证号（必填）
//	 * @param mobile  手机号（必填）
//	 * @param bankCardNo 银行卡号（选填）
//	 * @param  applyFlowId 项目id（必填）
//	 * @return String （0：正常，1：异常，2：警告）
//	 */
//	private static String personalRisk(String name,String idCard,String mobile,String bankCardNo,String applyFlowId) {
// 		Date bigDate = new Date();
//		String returnStr = "0";
//		HCRequest request = new HCRequest();
//		request.setUrl(personalRiskUrl);
//		request.setHttpMedthod(medthodPost);
//		Map<String,String> paramMap = new HashMap<>();
//		paramMap.put("apikey", apikeyXinShu);
//		paramMap.put("sign", MD5Util.getMD5Code(signXinShu + DateUtil.format(new Date(), "yyyyMMdd")));
//		paramMap.put("name", name);
//		paramMap.put("idCard", idCard);
//		paramMap.put("mobile", mobile);
//		paramMap.put("bankCardNo", bankCardNo);
//		String params = getParamStr(paramMap);
//		request.setParams(params);
//		String data =  HttpClientUtil.getSourceCode(request);
// 		JSONObject jSONObject = JSON.parseObject(data);
// 		JSONObject jSONdata = (JSONObject) jSONObject.get("data");
//  		if(jSONdata != null) {
//  	 		if(jSONdata.get("list") != null){
//  	 	 		@SuppressWarnings("unchecked")
//				List<Map<String,String>> list =  (List<Map<String, String>>) jSONdata.get("list");
//  	 	 		for(Map<String,String> mapEn : list){
//  	 	 			String blackFactsType = mapEn.get("blackFactsType").trim();
//  	 	 			if("A01".equals(blackFactsType) || "A02".equals(blackFactsType)){
//   	 	 			   returnStr = "1";
//  	 	 			   break;
//  	 	 			}
//
//  	 	 		}
//
//  	 	 		if("0".equals(returnStr)) {
//  	  	 	 		for(Map<String,String> mapEn : list){
//  	  	 	 			String blackFactsType = mapEn.get("blackFactsType").trim();
//  	  	 	 			if("C01".equals(blackFactsType) || "C02".equals(blackFactsType)){
//  	  	 	 			   returnStr = "2";
//  	  	 	 			   break;
//  	  	 	 			}
//
//  	  	 	 		}
//  	 	 		}
//
// 	 		}
// 		}
// 		TAdmittanceAuditResult admittanceAuditResult = new TAdmittanceAuditResult();
// 		admittanceAuditResult.setContent(name + "-" + idCard + ":" + "个人风险信息综合查询");
//  		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
// 		admittanceAuditResult.setId(uuid);
// 		admittanceAuditResult.setCreateTime(new Date());
// 		admittanceAuditResult.setIsPass("1".equals(returnStr)?"0":"1");
// 		admittanceAuditResult.setApplyFlowId(applyFlowId);
//  		saveLog(request.getUrl(),request.getParams(),data,bigDate,returnStr,admittanceAuditResult);
//		return returnStr;
//	}
//
// 	/**
//	 * @Description:企业负面信息综合查询
//	 * @author :dupengfei
//	 * @date 2018年4月12日
//	 * @param entName 企业名称，必填
//	 * @param regNo  企业注册号,选填
//	 * @param  applyFlowId 项目id（必填）
//	 * @return boolean true：正常，false异常
//	 */
//	private static boolean enterpriseNegativeIsHave(String entName,String regNo,String applyFlowId){
// 		Date bigDate = new Date();
//		boolean isNormal = true;
//		HCRequest request = new HCRequest();
//		request.setUrl(enterpriseNegativeIsHaveUrl);
//		request.setHttpMedthod(medthodPost);
//		Map<String,String> paramMap = new HashMap<>();
//		paramMap.put("apikey", apikeyXinShu);
//		paramMap.put("sign", MD5Util.getMD5Code(signXinShu + DateUtil.format(new Date(), "yyyyMMdd")));
//		paramMap.put("entName", entName);
//		paramMap.put("regNo", regNo);
//		String params = getParamStr(paramMap);
//		request.setParams(params);
//		String data =  HttpClientUtil.getSourceCode(request);
// 		JSONObject jSONObject = JSON.parseObject(data);
// 		JSONObject jSONdata = (JSONObject) jSONObject.get("data");
// 		if(jSONdata != null &&jSONdata.get("data") != null) {
// 			JSONObject jSONdataCourtInfo = (JSONObject) jSONdata.get("data");
// 			if(jSONdataCourtInfo.get("courtInfo") != null){
// 				List<Map<String,String>> list =  (List<Map<String, String>>) jSONdataCourtInfo.get("courtInfo");
// 				for(Map<String,String> mapData : list){
// 					String lxqk = mapData.get("lxqk");
// 					if(StringUtil.isNotEmpty(lxqk) && lxqk.contains("未履行")){
// 						isNormal = false;
// 						break;
// 					}
//
// 					String status = mapData.get("status");
// 					if(StringUtil.isNotEmpty(status) && "执行中".equals(status)){
// 						isNormal = false;
// 						break;
// 					}
// 				}
// 			}
// 		}
// 		String dataState = "0";
// 		if(!isNormal){
// 			dataState = "1";
// 		}
// 		TAdmittanceAuditResult admittanceAuditResult = new TAdmittanceAuditResult();
//	 	admittanceAuditResult.setContent(entName+ ":" + "企业负面信息综合查询");
//  		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
// 		admittanceAuditResult.setId(uuid);
// 		admittanceAuditResult.setCreateTime(new Date());
// 		admittanceAuditResult.setIsPass(isNormal == true ?"1":"0");
// 		admittanceAuditResult.setApplyFlowId(applyFlowId);
// 		saveLog(request.getUrl(),request.getParams(),data,bigDate,dataState,admittanceAuditResult);
//		return isNormal;
//	}
//
// 	/**
//	 * @Description:税务负面信息查询
//	 * @author :dupengfei
//	 * @date 2018年4月12日
//	 * @param taxName  纳税人名称,与法人身份证号码、税务登记号至少一个必填
//	 * @param idCard
//	 * @param taxNo
//	 * @param  applyFlowId 项目id（必填）
//	 * @return boolean true：正常，false异常
//	 */
//	private static boolean enterpriseTaxBlack(String taxName,String idCard,String taxNo,String applyFlowId){
// 		Date bigDate = new Date();
//		boolean isNormal = true;
//		HCRequest request = new HCRequest();
//		request.setUrl(enterpriseTaxBlackUrl);
//		request.setHttpMedthod(medthodPost);
//		Map<String,String> paramMap = new HashMap<>();
//		paramMap.put("apikey", apikeyXinShu);
//		paramMap.put("sign", MD5Util.getMD5Code(signXinShu + DateUtil.format(new Date(), "yyyyMMdd")));
//		paramMap.put("taxName", taxName);
//		paramMap.put("idCard", idCard);
//		paramMap.put("taxNo", taxNo);
//		String params = getParamStr(paramMap);
//		request.setParams(params);
//		String data =  HttpClientUtil.getSourceCode(request);
// 		JSONObject jSONObject = JSON.parseObject(data);
//		List<Map<String,String>> list =  (List<Map<String, String>>) jSONObject.get("data");
// 		if(list != null) {
// 			for(Map<String,String> mapData : list){
//				String blackType = mapData.get("blackType");
//				String abnormal = "重大违法案件;欠税户;非正常户;失效户;违法户;注销";
//				if(abnormal.contains(blackType)){
// 					isNormal = false;
//				}
//			}
//  		}
// 		String dataState = "0";
// 		if(!isNormal){
// 			dataState = "1";
// 		}
// 		TAdmittanceAuditResult admittanceAuditResult = new TAdmittanceAuditResult();
// 		admittanceAuditResult.setContent(taxName +":" + "企业有税务负面信息");
//  		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
// 		admittanceAuditResult.setId(uuid);
// 		admittanceAuditResult.setCreateTime(new Date());
// 		admittanceAuditResult.setIsPass(isNormal == true ?"1":"0");
// 		admittanceAuditResult.setApplyFlowId(applyFlowId);
// 		saveLog(request.getUrl(),request.getParams(),data,bigDate,dataState,admittanceAuditResult);
//		return isNormal;
//	}
//
//	/**
//	 * @Description:获取企业基本信息(天眼查)
//	 * @author :dupengfei
//	 * @date 2018年4月12日
//	 * @param entName 企业名称
//	 * @param  applyFlowId 项目id（必填）
//	 * @return boolean true：正常，false异常
//	 * @throws UnsupportedEncodingException
//	 */
//	private static boolean enterpriseInformation(String entName,String applyFlowId) throws UnsupportedEncodingException{
// 		Date bigDate = new Date();
//		boolean isNormal = true;
//		HCRequest request = new HCRequest();
//		request.setUrl(enterpriseInformationUrl + "?name=" + entName);
//		request.setHttpMedthod(medthodGet);
//		Map<String,String> header = new HashMap<>();
//		header.put("Authorization", authorization);
//		request.setHeader(header);
//		String data =  HttpClientUtil.getSourceCode(request);
// 		JSONObject jSONObject = JSON.parseObject(data);
// 		String errorCode = jSONObject.get("error_code").toString();
// 		if("0".equals(errorCode)){
// 			if(jSONObject.get("result") != null){
// 	 			JSONObject result = (JSONObject) jSONObject.get("result");
// 	 			if(result.get("regStatus") != null){
// 	 	 			String regStatus = (String) result.get("regStatus");
// 					String abnormal = "吊销;注销;停业;清算";
// 					if(abnormal.contains(regStatus)){
//  						isNormal = false;
// 					}
// 	 			}
// 			}
//  		}
// 		String dataState = "0";
// 		if("0".equals(errorCode) || "300000".equals(errorCode)){
// 			dataState = "1";
// 		}
// 		TAdmittanceAuditResult admittanceAuditResult = new TAdmittanceAuditResult();
//	 	admittanceAuditResult.setContent(entName + ":" + "企业未处于正常运营状态");
//  		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
// 		admittanceAuditResult.setId(uuid);
// 		admittanceAuditResult.setCreateTime(new Date());
// 		admittanceAuditResult.setIsPass(isNormal == true ?"1":"0");
// 		admittanceAuditResult.setApplyFlowId(applyFlowId);
// 		saveLog(request.getUrl(),request.getParams(),data,bigDate,dataState,admittanceAuditResult);
//		return isNormal;
//	}
//
//	/**
//	 * @Description:企业风险(天眼查)
//	 * @author :dupengfei
//	 * @date 2018年4月16日
//	 * @param entName
//	 * @param  applyFlowId 项目id（必填）
//	 * @return boolean true：正常，false异常
//	 */
//	private static boolean enterpriseRisk(String entName,String applyFlowId){
// 		Date bigDate = new Date();
//		boolean isNormal = true;
//		HCRequest request = new HCRequest();
//		request.setUrl(enterpriseRiskUrl + "?name=" + entName);
//		request.setHttpMedthod(medthodGet);
//		Map<String,String> header = new HashMap<>();
//		header.put("Authorization", authorization);
//		request.setHeader(header);
//		String data =  HttpClientUtil.getSourceCode(request);
// 		JSONObject jSONObject = JSON.parseObject(data);
// 		String errorCode = jSONObject.get("error_code").toString();
// 		if("1".equals(errorCode)){
// 			if(jSONObject.get("result") != null){
// 	 			JSONObject result = (JSONObject) jSONObject.get("result");
// 	 			if(result.get("internalList") != null){
// 	 	 			List<Map<String,String>> internalList = (List<Map<String, String>>) result.get("internalList");
// 					String abnormal = "1-严重违法，2-失信人，3-失信公司，4-被执行人，5-被执行公司";
// 	 	 			for(Map<String,String> mapObj : internalList) {
// 	  					if(mapObj.get("type") != null && abnormal.contains(mapObj.get("type"))){
//  	 						isNormal = false;
// 	 					}
// 	 	 			}
// 	 			}
// 			}
//  		}
// 		String dataState = "0";
// 		if("1".equals(errorCode) || "300000".equals(errorCode) ){
// 			dataState = "1";
// 		}
// 		TAdmittanceAuditResult admittanceAuditResult = new TAdmittanceAuditResult();
//	 	admittanceAuditResult.setContent(entName + ":企业有风险信息");
//
// 		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
// 		admittanceAuditResult.setId(uuid);
// 		admittanceAuditResult.setCreateTime(new Date());
// 		admittanceAuditResult.setIsPass(isNormal == true ?"1":"0");
// 		admittanceAuditResult.setApplyFlowId(applyFlowId);
// 		saveLog(request.getUrl(),request.getParams(),data,bigDate,dataState,admittanceAuditResult);
//		return isNormal;
//	}
//
//	/**
//	 * @Description:欠税公告(天眼查)
//	 * @author :dupengfei
//	 * @date 2018年4月12日
//	 * @param entName 企业名称
//	 * @param  applyFlowId 项目id（必填）
//	 * @return boolean true：正常，false异常
//	 * @throws UnsupportedEncodingException
//	 */
//	private static boolean taxes(String entName,String applyFlowId) throws UnsupportedEncodingException{
//		Date bigDate = new Date();
//		boolean isNormal = true;
//		HCRequest request = new HCRequest();
//		request.setUrl(taxesUrl + "?name=" + entName);
//		request.setHttpMedthod(medthodGet);
//		Map<String,String> header = new HashMap<>();
//		header.put("Authorization", authorization);
//		request.setHeader(header);
//		String data =  HttpClientUtil.getSourceCode(request);
// 		JSONObject jSONObject = JSON.parseObject(data);
// 		String errorCode = jSONObject.get("error_code").toString();
// 		if("1".equals(errorCode)){
// 			if(jSONObject.get("result") != null){
// 	 			JSONObject result = (JSONObject) jSONObject.get("result");
// 	 			if(result.get("total") != null){
// 	 				int total = (int) result.get("total");
// 	 				if(total > 0) {
// 	 					isNormal = false;
// 	 				}
// 	 			}
// 			}
//  		}
// 		String dataState = "0";
// 		if("1".equals(errorCode) || "300000".equals(errorCode)){
// 			dataState = "1";
// 		}
//
// 		TAdmittanceAuditResult admittanceAuditResult = new TAdmittanceAuditResult();
//	 	admittanceAuditResult.setContent(entName + ":" + "企业有税务负面信息");
//  		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
// 		admittanceAuditResult.setId(uuid);
// 		admittanceAuditResult.setCreateTime(new Date());
// 		admittanceAuditResult.setIsPass(isNormal == true ?"1":"0");
// 		admittanceAuditResult.setApplyFlowId(applyFlowId);
//   		saveLog(request.getUrl(),request.getParams(),data,bigDate,dataState,admittanceAuditResult);
//		return isNormal;
//	}
//
//	/**
//	 * @Description:保存日志
//	 * @author :dupengfei
//	 * @date 2018年4月13日
//	 * @param url
//	 * @param params
//	 * @param data
//	 * @param bigDate
//	 */
//	private static void saveLog(String url,String params,String data,Date bigDate,String dataState,TAdmittanceAuditResult admittanceAuditResult){
//		Date endDate = new Date();
// 		InterfaceAccessLogDao interfaceAccessLogDao = SpringBeanUtil.getBean("interfaceAccessLogDao");
// 		InterfaceAccessLog interfaceAccessLog = new InterfaceAccessLog();
// 		interfaceAccessLog.setUrl(url);
// 		interfaceAccessLog.setParams(params);
// 		interfaceAccessLog.setDataState(dataState);
//		//interfaceAccessLog.setReturnValue(data);
// 		interfaceAccessLog.setCreateTime(endDate);
//  		interfaceAccessLog.setProcessingTime((int)endDate.getTime() - (int)bigDate.getTime());
//
//  		InterfacesManagementDao interfacesManagementDao = SpringBeanUtil.getBean("interfacesManagementDao");
//  		InterfacesManagement interfacesManagement = new InterfacesManagement();
//  		interfacesManagement.setPage(0);
//  		List<TInterfacesManagement> interfacesList = interfacesManagementDao.query(interfacesManagement);
//  		for(TInterfacesManagement tInterfacesManagement : interfacesList){
//  			if(url.equals(tInterfacesManagement.getInterfaceUrl().trim())){
//  		  		interfaceAccessLog.setInterfaceCode(tInterfacesManagement.getInterfaceCode());
//  			}
//  		}
//   		interfaceAccessLog.setRequestType("normal");
// 		interfaceAccessLogDao.insert(interfaceAccessLog);
//
// 		JDBCUtil.jdbcTemplate.update(SQL_INSERT_SPITTER,
// 				admittanceAuditResult.getId(),
// 				admittanceAuditResult.getContent(),
// 				admittanceAuditResult.getIsPass(),
// 				admittanceAuditResult.getApplyFlowId(),
// 				admittanceAuditResult.getCreateTime()
// 				);
//	}
//
//	/**
//	 * @Description:拼装参数
//	 * @author :dupengfei
//	 * @date 2018年4月13日
//	 * @param paramMap
//	 * @return String
//	 */
//	private static String getParamStr(Map<String,String> paramMap){
//		String str = "";
//		for(Map.Entry<String, String> ent : paramMap.entrySet()) {
//			if(StringUtil.isNotEmpty(ent.getValue())){
//				str += ent.getKey() + "=" + ent.getValue() + "&";
//			}
// 		}
//		if(StringUtil.isNotEmpty(str)) {
//			str = str.substring(0, str.length() -1);
//		}
//		return str;
//	}
//
//    private static void doJob(String entName,String applyFlowId,String returnStr) {
//    	//数据放入队列
//    	Map<String,String> param = new HashMap<>();
//    	param.put("entName", entName);
//    	param.put("applyFlowId", applyFlowId);
//    	TaskQueue.add(param);
//    	if("0".equals(returnStr)){
//            //从线程池获取线程
//            try {
//                   ExecutorService cachedThreadPool = Executors.newFixedThreadPool(20);
//                   cachedThreadPool.execute(new Runnable() {
//                        public void run() {
// 							try {
// 								//取出队列中的值
//								Map<String, String> taskParam = (Map<String, String>) TaskQueue.getTaskQueue().take();
//	                        	InterfaceDataService interfaceDataService = SpringBeanUtil.getBean("interfaceDataService");
//	                        	//获取天眼查数据
//	                        	interfaceDataService.dataSave(taskParam.get("entName"), taskParam.get("applyFlowId"));
//							} catch (InterruptedException e) {
// 								e.printStackTrace();
//							}
//                        }
//                    });
//              } catch (Exception e) {
//            	 e.printStackTrace();
//             }
//    	}
//    }
//
//
//	public static void main(String[] args) {
//		try {
//			taxes("大公信软","sdsd");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//
//		//1.get
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Authorization", authorization);
//
//		ResponseEntity<String> getString = restTemplate.exchange(taxesUrl + "?name=大公信软"
//				, HttpMethod.GET
//				, new HttpEntity<byte[]>(headers)
//				, String.class);
//		System.out.println(getString.getBody());
//
//		//2.post
//		MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
//		paramMap.add("appkey", verificationBlackAppkey);
//		paramMap.add("idcard", "130130199010210011");
//		paramMap.add("mobile", "13300001234");
//		paramMap.add("name", "张三");
//
//		ResponseEntity<String> ss = restTemplate.postForEntity(verificationBlacklistLoanUrl, paramMap, String.class);
//		System.out.println(ss.getBody());
//	}
//}
