package fun.server.controller.enterprise;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.TEnterprise;
import fun.server.model.TEnterpriseExample;
import fun.server.model.TUser;
import fun.server.service.TEnterpriseService;
import fun.server.service.TUserService;
import fun.tools.ParamTools;
import fun.tools.SnowflakeIdWorker;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 企业管理 相关业务处理
 */
@RestController
@RequestMapping("/s/enterprise")
public class Enterprise {

    private final TEnterpriseService tEnterpriseService;
    private final TUserService tUserService;

    public Enterprise(TEnterpriseService tEnterpriseService, TUserService tUserService) {
        this.tEnterpriseService = tEnterpriseService;
        this.tUserService = tUserService;
    }


    /**
     * 获取企业信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryenterprise", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryenterprise(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("name");
        String ftype = jsonParam.getString("ftype");
        String findusytry = jsonParam.getString("findusytry");
        Integer dataall = jsonParam.getInteger("dataall");
        try {
            // 获取数据库记录
            JSONArray enterprise_Array = new JSONArray();
            // 查询条件
            TEnterpriseExample TEnterpriseExample = new TEnterpriseExample();
            TEnterpriseExample.Criteria criteria = TEnterpriseExample.createCriteria();

            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");
            }
            if (ftype != null && !ftype.equals("")) {
                criteria.andFtypeLike("%" + ftype + "%");
            }
            if (findusytry != null && !findusytry.equals("")) {
                criteria.andFindustryLike("%" + findusytry + "%");
            }
            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                TEnterpriseExample.setOrderByClause(orderSql.substring(1));
            } else {
                TEnterpriseExample.setOrderByClause("FCDATE desc , FName desc");
            }
            PageInfo<TEnterprise> enterprisePageInfo = tEnterpriseService.findByExampleMapper(TEnterpriseExample, (page - 1) * results, results);
            List<TEnterprise> enterprise_list = enterprisePageInfo.getList();

            for (TEnterprise enterprise : enterprise_list) {
                JSONObject enterprise_object = new JSONObject();
                enterprise_object.put("key", ParamTools.getEnParam(enterprise.getFkeyid().toString()));
                enterprise_object.put("FUserID", enterprise.getFuserid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(enterprise.getFuserid().toString()));

                if (dataall == 1) {
                    enterprise_object.put("FName", enterprise.getFname() == null ? "" : enterprise.getFname());
                    enterprise_object.put("FType", enterprise.getFtype() == null ? "" : enterprise.getFtype());
                    enterprise_object.put("FBLNo", enterprise.getFblno() == null ? "" : enterprise.getFblno());
                    enterprise_object.put("FLegalPerson", enterprise.getFlegalperson() == null ? "" : enterprise.getFlegalperson());
                    enterprise_object.put("FCreateDate", enterprise.getFcreatedate() == null ? "" : enterprise.getFcreatedate());
                    enterprise_object.put("FIndustry", enterprise.getFindustry() == null ? "" : enterprise.getFindustry());
                    enterprise_object.put("FScope", enterprise.getFscope() == null ? "" : enterprise.getFscope());
                    enterprise_object.put("FRegisterAddr", enterprise.getFregisteraddr() == null ? "" : enterprise.getFregisteraddr());
                    enterprise_object.put("FWorkAddr", enterprise.getFworkaddr() == null ? "" : enterprise.getFworkaddr());
                    enterprise_object.put("FUser", enterprise.getFuser() == null ? "" : enterprise.getFuser());
                    enterprise_object.put("FUserTel", enterprise.getFusertel() == null ? "" : enterprise.getFusertel());
                    enterprise_object.put("FNote", enterprise.getFnote() == null ? "" : enterprise.getFnote());
                    enterprise_object.put("FProduct", enterprise.getFproduct() == null ? "" : enterprise.getFproduct());
                    enterprise_object.put("FOperateState", enterprise.getFoperatestate() == null ? "" : enterprise.getFoperatestate());
                    enterprise_object.put("FIntroduction", enterprise.getFintroduction() == null ? "" : enterprise.getFintroduction());


                    enterprise_object.put("FCID", enterprise.getFcid());
                    enterprise_object.put("FUID", enterprise.getFuid());
                    enterprise_object.put("FCDATE", enterprise.getFcdate());
                    enterprise_object.put("FUDATE", enterprise.getFudate());
                } else {
                    enterprise_object.put("FName", "*****");
                    enterprise_object.put("FType", "*****");
                    enterprise_object.put("FBLNo", "*****");
                    enterprise_object.put("FLegalPerson", "*****");
                    enterprise_object.put("FCreateDate", "*****");
                    enterprise_object.put("FIndustry", "*****");
                    enterprise_object.put("FScope", "*****");
                    enterprise_object.put("FRegisterAddr", "*****");
                    enterprise_object.put("FWorkAddr", "*****");
                    enterprise_object.put("FUser", "*****");
                    enterprise_object.put("FUserTel", "*****");
                    enterprise_object.put("FNote", "*****");
                    enterprise_object.put("FProduct", "*****");
                    enterprise_object.put("FOperateState", "*****");
                    enterprise_object.put("FIntroduction", "*****");
                    enterprise_object.put("FCID", "*****");
                    enterprise_object.put("FUID", "*****");
                    enterprise_object.put("FCDATE", "*****");
                    enterprise_object.put("FUDATE", "*****");
                }
                enterprise_object.put("FMode", enterprise.getFmode());
                enterprise_object.put("FState", enterprise.getFstate());
                enterprise_Array.add(enterprise_object);
            }
            // 返回值
            object.put("list", enterprise_Array);
            object.put("total", enterprisePageInfo.getTotal()); // 总行数
            object.put("page", enterprisePageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取企业信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDataenterpriseSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDataenterpriseSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray enterprise_Array = new JSONArray();
            TEnterpriseExample enterpriseExample = new TEnterpriseExample();
            TEnterpriseExample.Criteria criteria = enterpriseExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            enterpriseExample.setOrderByClause("FName asc");
            List<TEnterprise> enterprise_list = tEnterpriseService.findByExample(enterpriseExample);
            for (TEnterprise enterprise : enterprise_list) {
                JSONObject enterprise_object = new JSONObject();
                enterprise_object.put("id", ParamTools.getEnParam(enterprise.getFkeyid().toString()));
                enterprise_object.put("text", enterprise.getFname());
                enterprise_Array.add(enterprise_object);
            }
            // 返回值

            object.put("list", enterprise_Array);
            object.put("results", enterprise_Array);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取企业信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryenterpriseInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryenterpriseInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询企业信息
            TEnterprise enterprise = tEnterpriseService.findById(key);
            JSONObject enterprise_object = new JSONObject();
            enterprise_object.put("key", ParamTools.getEnParam(enterprise.getFkeyid().toString()));
            enterprise_object.put("FUserID", enterprise.getFuserid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(enterprise.getFuserid().toString()));
            enterprise_object.put("FName", enterprise.getFname() == null ? "" : enterprise.getFname());
            enterprise_object.put("FType", enterprise.getFtype() == null ? "" : enterprise.getFtype());
            enterprise_object.put("FBLNo", enterprise.getFblno() == null ? "" : enterprise.getFblno());
            enterprise_object.put("FLegalPerson", enterprise.getFlegalperson() == null ? "" : enterprise.getFlegalperson());
            enterprise_object.put("FCreateDate", enterprise.getFcreatedate() == null ? "" : enterprise.getFcreatedate());
            enterprise_object.put("FIndustry", enterprise.getFindustry() == null ? "" : enterprise.getFindustry());
            enterprise_object.put("FScope", enterprise.getFscope() == null ? "" : enterprise.getFscope());
            enterprise_object.put("FRegisterAddr", enterprise.getFregisteraddr() == null ? "" : enterprise.getFregisteraddr());
            enterprise_object.put("FWorkAddr", enterprise.getFworkaddr() == null ? "" : enterprise.getFworkaddr());
            enterprise_object.put("FUser", enterprise.getFuser() == null ? "" : enterprise.getFuser());
            enterprise_object.put("FUserTel", enterprise.getFusertel() == null ? "" : enterprise.getFusertel());
            enterprise_object.put("FNote", enterprise.getFnote() == null ? "" : enterprise.getFnote());
            enterprise_object.put("FProduct", enterprise.getFproduct() == null ? "" : enterprise.getFproduct());
            enterprise_object.put("FOperateState", enterprise.getFoperatestate() == null ? "" : enterprise.getFoperatestate());
            enterprise_object.put("FIntroduction", enterprise.getFintroduction() == null ? "" : enterprise.getFintroduction());
            enterprise_object.put("FMode", enterprise.getFmode());
            enterprise_object.put("FState", enterprise.getFstate());
            enterprise_object.put("FCID", enterprise.getFcid());
            enterprise_object.put("FUID", enterprise.getFuid());
            enterprise_object.put("FCDATE", enterprise.getFcdate());
            enterprise_object.put("FUDATE", enterprise.getFudate());
            enterprise_object.put("FState", enterprise.getFstate());
            // 返回值
            object.put("info", enterprise_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加企业信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加企业信息")
    @ResponseBody
    @RequestMapping(value = "/addenterprise", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addenterprise(HttpServletRequest request, Pageable pageable)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FName = jsonParam.getString("FName");
        String FType = jsonParam.getString("FType");
        String FBLNo = jsonParam.getString("FBLNo");
        String FLegalPerson = jsonParam.getString("FLegalPerson");
        String FCreateDate = jsonParam.getString("FCreateDate");
        String FIndustry = jsonParam.getString("FIndustry");
        String FScope = jsonParam.getString("FScope");
        String FRegisterAddr = jsonParam.getString("FRegisterAddr");
        String FWorkAddr = jsonParam.getString("FWorkAddr");
        String FUser = jsonParam.getString("FUser");
        String FUserTel = jsonParam.getString("FUserTel");
        String FNote = jsonParam.getString("FNote");
        String FProduct = jsonParam.getString("FProduct");
        String FOperateState = jsonParam.getString("FOperateState");
        String FIntroduction = jsonParam.getString("FIntroduction");
        try {
            String CookiesLoginenterpriseID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginenterpriseID != null && !CookiesLoginenterpriseID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginenterpriseID);
            }

            TEnterpriseExample enterpriseExample = new TEnterpriseExample();
            TEnterpriseExample.Criteria criteria = enterpriseExample.createCriteria();
            criteria.andFblnoEqualTo(FBLNo);
            criteria.andFnameEqualTo(FName);
            List<TEnterprise> enterpriseList = tEnterpriseService.findByExample(enterpriseExample);
            if (enterpriseList.size() == 0) {

                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
                long key = idWorker.nextId();
                long enterprisekey = idWorker.nextId();
                // 新建企业信息
                TEnterprise enterprise = new TEnterprise();
                enterprise.setFkeyid(enterprisekey);
                enterprise.setFmode(1);
                enterprise.setFtype(FType);
                enterprise.setFname(FName);
                enterprise.setFblno(FBLNo);
                enterprise.setFlegalperson(FLegalPerson);
                enterprise.setFcreatedate(FCreateDate);
                enterprise.setFindustry(FIndustry);
                enterprise.setFscope(FScope);
                enterprise.setFregisteraddr(FRegisterAddr);
                enterprise.setFworkaddr(FWorkAddr);
                enterprise.setFuser(FUser);
                enterprise.setFusertel(FUserTel);
                enterprise.setFnote(FNote);
                enterprise.setFproduct(FProduct);
                enterprise.setFoperatestate(FOperateState);
                enterprise.setFintroduction(FIntroduction);
                enterprise.setFuserid(key);
                enterprise.setFcid(Long.parseLong(uid));
                enterprise.setFcdate(new Date());
                tEnterpriseService.save(enterprise);


                //用户表添加信息
                TUser tuser = new TUser();
                tuser.setfKeyId(key);
                tuser.setfLogin(FBLNo);
                if (FBLNo.length() > 6) {
                    String lastSixChars = FBLNo.substring(FBLNo.length() - 6);
                    tuser.setfPass(ParamTools.getEnParam(lastSixChars));
                } else {
                    tuser.setfPass(ParamTools.getEnParam(FBLNo));
                }
                tuser.setfTel(FUserTel);
                tuser.setfEmail("");
                tuser.setfJoinId(enterprisekey);
                tuser.setfUserno(FBLNo);
                tuser.setfName(FName);
                tuser.setfType(2);
                tuser.setfState(1);
                tuser.setfIsAdmin(0);
                tUserService.save(tuser);
                // 返回值
                object.put("result", "success");
            } else {
                // 返回值
                object.put("result", "fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 修改企业信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改企业信息")
    @ResponseBody
    @RequestMapping(value = "/updateenterprise", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateenterprise(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FName = jsonParam.getString("FName");
        String FType = jsonParam.getString("FType");
        String FBLNo = jsonParam.getString("FBLNo");
        String FLegalPerson = jsonParam.getString("FLegalPerson");
        String FCreateDate = jsonParam.getString("FCreateDate");
        String FIndustry = jsonParam.getString("FIndustry");
        String FScope = jsonParam.getString("FScope");
        String FRegisterAddr = jsonParam.getString("FRegisterAddr");
        String FWorkAddr = jsonParam.getString("FWorkAddr");
        String FUser = jsonParam.getString("FUser");
        String FUserTel = jsonParam.getString("FUserTel");
        String FNote = jsonParam.getString("FNote");
        String FProduct = jsonParam.getString("FProduct");
        String FOperateState = jsonParam.getString("FOperateState");
        String FIntroduction = jsonParam.getString("FIntroduction");

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            TEnterpriseExample enterpriseExample = new TEnterpriseExample();
            TEnterpriseExample.Criteria criteria = enterpriseExample.createCriteria();
            criteria.andFblnoEqualTo(FBLNo);
            criteria.andFnameEqualTo(FName);
            criteria.andFkeyidNotEqualTo(key);
            List<TEnterprise> enterpriseList = tEnterpriseService.findByExample(enterpriseExample);
            if (enterpriseList.size() == 0) {

                String CookiesLoginenterpriseID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginenterpriseID != null && !CookiesLoginenterpriseID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginenterpriseID);
                }
                // 更新企业信息
                TEnterprise enterprise = new TEnterprise();
                enterprise.setFkeyid(key);
                enterprise.setFname(FName);
                enterprise.setFmode(1);
                enterprise.setFtype(FType);
                enterprise.setFname(FName);
                enterprise.setFblno(FBLNo);
                enterprise.setFlegalperson(FLegalPerson);
                enterprise.setFcreatedate(FCreateDate);
                enterprise.setFindustry(FIndustry);
                enterprise.setFscope(FScope);
                enterprise.setFregisteraddr(FRegisterAddr);
                enterprise.setFworkaddr(FWorkAddr);
                enterprise.setFuser(FUser);
                enterprise.setFusertel(FUserTel);
                enterprise.setFnote(FNote);
                enterprise.setFproduct(FProduct);
                enterprise.setFoperatestate(FOperateState);
                enterprise.setFintroduction(FIntroduction);
                enterprise.setFuid(Long.parseLong(uid));
                enterprise.setFudate(new Date());
                tEnterpriseService.update(enterprise);


                TEnterprise tEnterprise = tEnterpriseService.findById(key);

                TUser tUser = new TUser();
                tUser.setfKeyId(tEnterprise.getFuserid());
                tUser.setfTel(FUserTel);
                tUser.setfLogin(FBLNo);
                tUser.setfName(FName);
                if (!FBLNo.equals(tEnterprise.getFblno())) {
                    if (FBLNo.length() > 6) {
                        String lastSixChars = FBLNo.substring(FBLNo.length() - 6);
                        tUser.setfPass(ParamTools.getEnParam(lastSixChars));
                    } else {
                        tUser.setfPass(ParamTools.getEnParam(FBLNo));
                    }
                }
                tUserService.update(tUser);

                // 返回值
                object.put("result", "success");
            } else {
                // 返回值
                object.put("result", "fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 删除企业信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除企业信息")
    @ResponseBody
    @RequestMapping(value = "/delenterprise", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delenterprise(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
//            String CookiesLoginenterpriseID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
//            String uid = ""; // 当前登录用户ID
//            if (CookiesLoginenterpriseID != null && !CookiesLoginenterpriseID.equals("")) {
//                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
//            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            TEnterprise enterprise = tEnterpriseService.findById(Long.parseLong(id));
            TUser tUser = tUserService.findById(enterprise.getFuserid());
            if(tUser!=null) {
                tUserService.deleteById(enterprise.getFuserid());
            }
            tEnterpriseService.deleteById(Long.parseLong(id));
            // 返回值
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 变更企业信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/stateenterprise", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String stateenterprise(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLoginenterpriseID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginenterpriseID != null && !CookiesLoginenterpriseID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TEnterprise enterprise = new TEnterprise();
            enterprise.setFkeyid(Long.parseLong(id));
            enterprise.setFuid(Long.parseLong(uid));
            enterprise.setFudate(new Date());
            enterprise.setFstate(Integer.valueOf(state));
            tEnterpriseService.update(enterprise);
            // 返回值
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }
    //营业执照号 正则判断 15 位或者18位
    public static boolean isValidBusinessLicense(String licenseNumber) {
        // 中国大陆营业执照号正则表达式
        String regex = "^[A-Z0-9]{15}$|^[A-Z0-9]{18}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(licenseNumber);

        return matcher.matches();
    }

    private static final String REGEX_MOBILE_NUMBER = "^1[3-9]\\d{9}$";

    /**
     * 模板导入
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/enterpriseupmoban", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String enterpriseupmoban(@RequestParam("file-1") MultipartFile[] uploadFiles, HttpServletRequest request)
            throws Exception {
        JSONObject object = new JSONObject();
        BufferedOutputStream stream = null;
        String CookiesLogindesignpurchaseapplyID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
        String uid = ""; // 当前登录用户ID
        if (CookiesLogindesignpurchaseapplyID != null && !CookiesLogindesignpurchaseapplyID.equals("")) {
            uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
        }

        for (MultipartFile file : uploadFiles) {
            try {
                // 获取文件名
                String filename = file.getOriginalFilename();
                if (filename == null || filename.equals("")) {
                    object.put("result", "error");
                    object.put("error", "请选择文件后再进行上传！");
                } else {
                    JSONArray student_Array = new JSONArray();
                    // 获取文件的后缀名
                    String suffixName = filename.substring(filename.lastIndexOf(".") + 1);
                    //生成新的文件名
                    SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
                    String realFileName = idWorker.nextId() + "." + suffixName;

                    //读取excel
                    // 2.创建工作区workbook
                    XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    // 3.获取表sheet,这里sheet0代表获取下表为0的excel表,也就是第一个表
                    XSSFSheet sheet = workbook.getSheetAt(0);
                    boolean iserror = false;
                    if (suffixName.equals("xlsx")) {
                        String FName = "";
                        String FType = "";
                        String FBLNo = "";
                        String FLegalPerson = "";
                        String FCreateDate = "";
                        String FIndustry = "";
                        String FScope = "";
                        String FRegisterAddr = "";
                        String FWorkAddr = "";
                        String FUser = "";
                        String FUserTel = "";
                        String FNote = "";
                        String FProduct = "";
                        String FOperateState = "";
                        String FIntroduction = "";

                        // 4.获取数据
                        // getLastRowNum() 获取一张sheet表中行的数量
                        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                            // 获取第i行的数据
                            XSSFRow row = sheet.getRow(i);
                            FName = "";
                            FType = "";
                            FBLNo = "";
                            FLegalPerson = "";
                            FCreateDate = "";
                            FIndustry = "";
                            FScope = "";
                            FRegisterAddr = "";
                            FWorkAddr = "";
                            FUser = "";
                            FUserTel = "";
                            FNote = "";
                            FProduct = "";
                            FOperateState = "";
                            FIntroduction = "";
                            for (int j = 0; j < row.getLastCellNum(); j++) {
                                // 获取第i行第j列的单元格数据
                                String cell = null;
                                try {
                                    cell = row.getCell(j).toString().trim() == null ? row.createCell(j).toString() : row.getCell(j).toString().trim();
                                } catch (Exception e) {
                                    continue;
                                }

                                if (cell == null && cell.equals("")) {
                                    break;
                                }
                                if (i == 0 && j == 0 && !cell.equals("*企业名称")) {
                                    iserror = true;
                                    break;
                                }
                                if (iserror == true) {
                                    break;
                                } else {

                                    if (i == 0) {
                                        break;
                                    }
                                    switch (j) {
                                        case 0:
                                            FName = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
                                        case 1:
                                            FBLNo = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
                                        case 2:
                                            FLegalPerson = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
                                        case 3:
                                            FRegisterAddr = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
                                        case 4:
                                            FType = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
                                        case 5:
//                                            row.getCell(j).setCellType(CellType.STRING);
//                                            FCreateDate = row.getCell(j).getStringCellValue();
                                            if (DateUtil.isCellDateFormatted(row.getCell(j))) {
                                                // 如果是日期格式，转换为日期对象
                                                Date date = row.getCell(j).getDateCellValue();
                                                FCreateDate = dateFormat.format(date);
                                            }else{
                                                FCreateDate ="";
                                            }
//                                            FCreateDate = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
                                        case 6:
                                            FWorkAddr = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
                                        case 7:
                                            FIndustry = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
                                        case 8:
                                            FOperateState = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
                                        case 9:
                                            FUser = row.getCell(j).toString().trim() == null ? "0" : row.getCell(j).toString().trim();
                                            break;
                                        case 10:
                                            row.getCell(j).setCellType(CellType.STRING);
                                            FUserTel = row.getCell(j).getStringCellValue();
//                                            FUserTel = row.getCell(j).toString().trim() == null ? "0" : row.getCell(j).toString().trim();
                                            break;
                                        case 11:
                                            FScope = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
                                        case 12:
                                            FProduct = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
                                        case 13:
                                            FIntroduction = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
                                        case 14:
                                            FNote = row.getCell(j).toString().trim() == null ? "" : row.getCell(j).toString().trim();
                                            break;
                                    }
                                }
                                // 5.打印单元格数据
//                            System.out.print(cell + " | ");
                            }

                            JSONObject student_object = new JSONObject();
                            String error = "";
                            if (i > 0) {
                                if(FBLNo.equals("") && FName.equals(""))
                                    break;
                                if (FName.equals(""))
                                    error += "企业名称未填写" + ";";
                                if (FBLNo.equals(""))
                                    error += "营业执照号号未填写" + ";";
                                if (!FBLNo.equals("") && !isValidBusinessLicense(FBLNo)) {
                                    error += "营业执照号填写错误" + ";";
                                }
                                System.out.println(FCreateDate);
                                if (!FUserTel.equals("") && !Pattern.matches(REGEX_MOBILE_NUMBER, convertToPhoneNumber(FUserTel))) {
                                    error += "联系电话填写错误" + ";";
                                }
                                if (!error.equals("")) {
                                    student_object.put("FName", FName == null ? "未填写" : FName);
                                    student_object.put("error", error);
                                    student_Array.add(student_object);
                                    continue;
                                } else {


                                    TEnterpriseExample te = new TEnterpriseExample();
                                    TEnterpriseExample.Criteria criteria = te.createCriteria();
                                    criteria.andFblnoEqualTo(FBLNo);
                                    criteria.andFnameEqualTo(FName);
                                    List<TEnterprise> enterpriseList = tEnterpriseService.findByExample(te);
                                    if (enterpriseList.size() == 0) {

                                        idWorker = new SnowflakeIdWorker(1, 1);
                                        long key = idWorker.nextId();
                                        long enterprisekey = idWorker.nextId();
                                        // 新建企业信息
                                        TEnterprise enterprise = new TEnterprise();
                                        enterprise.setFkeyid(enterprisekey);
                                        enterprise.setFmode(1);
                                        enterprise.setFtype(FType);
                                        enterprise.setFname(FName);
                                        enterprise.setFblno(FBLNo);
                                        enterprise.setFlegalperson(FLegalPerson);
                                        enterprise.setFcreatedate(FCreateDate);
                                        enterprise.setFindustry(FIndustry);
                                        enterprise.setFscope(FScope);
                                        enterprise.setFregisteraddr(FRegisterAddr);
                                        enterprise.setFworkaddr(FWorkAddr);
                                        enterprise.setFuser(FUser);
                                        enterprise.setFusertel(convertToPhoneNumber(FUserTel));
                                        enterprise.setFnote(FNote);
                                        enterprise.setFproduct(FProduct);
                                        enterprise.setFoperatestate(FOperateState);
                                        enterprise.setFintroduction(FIntroduction);
                                        enterprise.setFuserid(key);
                                        enterprise.setFcid(Long.parseLong(uid));
                                        enterprise.setFcdate(new Date());
                                        tEnterpriseService.save(enterprise);


                                        //用户表添加信息
                                        TUser tuser = new TUser();
                                        tuser.setfKeyId(key);
                                        tuser.setfLogin(FBLNo);
                                        if (FBLNo.length() > 6) {
                                            String lastSixChars = FBLNo.substring(FBLNo.length() - 6);
                                            tuser.setfPass(ParamTools.getEnParam(lastSixChars));
                                        } else {
                                            tuser.setfPass(ParamTools.getEnParam(FBLNo));
                                        }
                                        tuser.setfTel(FUserTel);
                                        tuser.setfEmail("");
                                        tuser.setfJoinId(enterprisekey);
                                        tuser.setfUserno(FBLNo);
                                        tuser.setfName(FName);
                                        tuser.setfType(2);
                                        tuser.setfState(1);
                                        tuser.setfIsAdmin(0);
                                        tUserService.save(tuser);
                                    }

                                }
                            }
                        }
                    } else {
                        iserror = true;
                    }

                    if (iserror == true) {
                        object.put("result", "error");
                        object.put("error", "请选择正确的模板进行导入！");
                    } else {
                        // 返回值
                        object.put("studenterror", student_Array);
                        object.put("result", "success");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                object.put("result", "error");
                object.put("error", e.toString());
            }
        }
        return object.toString();
    }

    /**
     * 将科学计数法表示的数字转换为手机号（
     *
     * @param scientificNumberStr 科学计数法表示的数字
     * @return 手机号字符串
     */
    public static String convertToPhoneNumber(String scientificNumberStr) {
        // 将科学计数法字符串解析为 double
        double scientificNumber = Double.parseDouble(scientificNumberStr);

        // 将 double 转换为 long，确保精度不丢失
        long phoneNumber = (long) scientificNumber;

        // 将手机号转换为字符串并返回
        return Long.toString(phoneNumber);
    }
}