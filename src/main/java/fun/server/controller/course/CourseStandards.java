package fun.server.controller.course;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.server.model.*;
import fun.server.service.*;
import fun.tools.ParamTools;
import fun.tools.SnowflakeIdWorker;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 课程能力标准设置子表管理 相关业务处理
 */
@RestController
@RequestMapping("/s/coursestandards")
public class CourseStandards {

    private final TCourseStandardService tCourseStandardService;

    private final TCourseService tCourseService;

    private final TCourseStandardSService tCourseStandardSService;

    private final TAbilityService tAbilityService;

    private final TAbilityLevelService tAbilityLevelService;

    private final TUserService tUserService;

    private final TAbilityConditionService tAbilityConditionService;

    private final TAbilityConditionSService tAbilityConditionSService;

    public CourseStandards(TCourseStandardService tCourseStandardService, TCourseService tCourseService, TCourseStandardSService tCourseStandardSService, TAbilityService tAbilityService, TAbilityLevelService tAbilityLevelService, TUserService tUserService, TAbilityConditionService tAbilityConditionService, TAbilityConditionSService tAbilityConditionSService) {
        this.tCourseStandardService = tCourseStandardService;
        this.tCourseService = tCourseService;
        this.tCourseStandardSService = tCourseStandardSService;
        this.tAbilityService = tAbilityService;
        this.tAbilityLevelService = tAbilityLevelService;
        this.tUserService = tUserService;
        this.tAbilityConditionService = tAbilityConditionService;
        this.tAbilityConditionSService = tAbilityConditionSService;
    }



    /**
     * 根据ID获取标准设定数据
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querysettingInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querysettingInfo( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");//设置ID
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            TCourseStandard standard = tCourseStandardService.findById(key);
            object.put("FType", standard.getFtype());
            object.put("FSZState", standard.getFszstate());
            object.put("FQZType", standard.getFqztype());
            TAbilityCondition condition = tAbilityConditionService.findById(standard.getFabilitycamid());
            if(condition == null){
                TAbilityConditionS conditionS = tAbilityConditionSService.findById(standard.getFabilitycamid());
                object.put("FTJName", conditionS == null ? "" : conditionS.getFname());
            }else{
                object.put("FTJName", condition.getFconditionname());
            }

            // 返回值
            object.put("info", key);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 获取标准设定子表信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryStandardSettings", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryStandardSettings( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");

        String id = jsonParam.getString("id");//设置ID

        try {
            id = (id == null || id.equals("") || id.equals("-1")) ? "0" : ParamTools.getdeParam(id);

            // 获取数据库记录
            JSONArray User_Array = new JSONArray();
            // 查询条件

            TCourseStandardSExample tCourseStandardSExample = new TCourseStandardSExample();
            TCourseStandardSExample.Criteria criteria = tCourseStandardSExample.createCriteria();
            criteria.andFcsidEqualTo(Long.parseLong(id));

            // 排序
            String orderSql = "";
            for (Object order: order_JA) {
                JSONObject order_Object = (JSONObject)order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                tCourseStandardSExample.setOrderByClause(orderSql.substring(1));
            } else {
                tCourseStandardSExample.setOrderByClause("FOrder");
            }
            PageInfo<TCourseStandardS> UserPageInfo = tCourseStandardSService.findByExampleMapper(tCourseStandardSExample, (page-1)*results, results);
            List<TCourseStandardS> tStandardSettingSList = UserPageInfo.getList();
            int num =1;
            for (TCourseStandardS tStandardSettingS: tStandardSettingSList) {
                JSONObject User_object = new JSONObject();
                User_object.put("key", ParamTools.getEnParam(tStandardSettingS.getFkeyid().toString()));
                User_object.put("FValueUL", tStandardSettingS.getFvalueul());
                User_object.put("FValueLL", tStandardSettingS.getFvaluell());
                User_object.put("FMemo", tStandardSettingS.getFmemo() == null ? "" : tStandardSettingS.getFmemo());
                User_object.put("FQZType", tStandardSettingS.getFqztype());
                User_object.put("FBool", tStandardSettingS.getFbool());
                User_object.put("FSValue", tStandardSettingS.getFsvalue() == null ? "" : tStandardSettingS.getFsvalue());
                User_object.put("XH", num);
                User_Array.add(User_object);
                num ++;
            }
            // 返回值
            object.put("list", User_Array);
            object.put("total", UserPageInfo.getTotal()); // 总行数
            object.put("page", UserPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID查询标准设定子表信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/selectsettingsinfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String selectsettingsinfo( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String fssid = jsonParam.getString("id");
        try {
            SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
            String CookiesLoginStrucDID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginStrucDID != null && !CookiesLoginStrucDID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginStrucDID);
            }
            long key = Long.parseLong(ParamTools.getdeParam(fssid));//标准设定主表id
            //先查询一下 当前子表id是否存在 ，存在则修改
            TCourseStandardS tStandardSettingS = tCourseStandardSService.findById(key);
            JSONObject SettingS_object = new JSONObject();
            SettingS_object.put("key", ParamTools.getEnParam(tStandardSettingS.getFkeyid().toString()));

            SettingS_object.put("FValueUL", tStandardSettingS.getFvalueul());
            SettingS_object.put("FValueLL", tStandardSettingS.getFvaluell());
            SettingS_object.put("FMemo", tStandardSettingS.getFmemo());
            SettingS_object.put("FQZType", tStandardSettingS.getFqztype());
            SettingS_object.put("FBool", tStandardSettingS.getFbool());
            SettingS_object.put("FOrder", tStandardSettingS.getForder());
            SettingS_object.put("FSValue", tStandardSettingS.getFsvalue());

            SettingS_object.put("FCID", tStandardSettingS.getFcid());
            SettingS_object.put("FUID", tStandardSettingS.getFuid());
            SettingS_object.put("FCDATE ", tStandardSettingS.getFcdate());
            SettingS_object.put("FUDATE ", tStandardSettingS.getFudate());
            // 返回值
            object.put("info", SettingS_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID查询标准设定子表信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/selectboolinfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String selectboolinfo( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String fssid = jsonParam.getString("id");//主表iD
        try {


            long key = Long.parseLong(ParamTools.getdeParam(fssid));//标准设定主表id

            int isbool = 0;
            TCourseStandardSExample tCourseStandardSExample = new TCourseStandardSExample();
            TCourseStandardSExample.Criteria criteria = tCourseStandardSExample.createCriteria();
            criteria.andFcsidEqualTo(key);
            List<TCourseStandardS> sList = tCourseStandardSService.findByExample(tCourseStandardSExample);
            JSONObject coursestandard_object = new JSONObject();
            if(sList.size() > 0){

                for (TCourseStandardS tStandardSettingS: sList) {
                    if(tStandardSettingS.getFqztype() ==0){
                        if(tStandardSettingS.getFbool() == 0){
                            coursestandard_object.put("booltrue1", tStandardSettingS.getFvalueul());
                            coursestandard_object.put("booltrueFMemo", tStandardSettingS.getFmemo());
                        }else  if(tStandardSettingS.getFbool() == 1){
                            coursestandard_object.put("boolfalse1", tStandardSettingS.getFvalueul());
                            coursestandard_object.put("boolfalseFMemo", tStandardSettingS.getFmemo());
                        }
                    }
                }
            }else{
                isbool = 1;
            }

            // 返回值
            object.put("isbool", isbool);
            object.put("info", coursestandard_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID查询标准设定子表信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/selectsettingsinfoorder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String selectsettingsinfoorder( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String fsid = jsonParam.getString("id");
        try {
            long key = Long.parseLong(ParamTools.getdeParam(fsid));
            TCourseStandardSExample tCourseStandardSExample = new TCourseStandardSExample();
            tCourseStandardSExample.createCriteria().andFcsidEqualTo(key);
            tCourseStandardSExample.setOrderByClause("FOrder desc ");
            List<TCourseStandardS> tStandardSettingSList = tCourseStandardSService.findByExample(tCourseStandardSExample);
            if(tStandardSettingSList.size()>0){
                TCourseStandardS ts = tStandardSettingSList.get(0);
                object.put("FValueLL", ts.getFvalueul());
                object.put("FOrder", ts.getForder());
            }else{
                object.put("FValueLL", 0);
                object.put("FOrder", 0);
            }
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加取值类型 数值 得分 -标准设定主表
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/addStandardSettingsszdf", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addStandardSettingsszdf( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String fssid = jsonParam.getString("FStandardID");//设置ID
        String FValueUL = jsonParam.getString("FValueUL");
        String FValueLL = jsonParam.getString("FValueLL");
        String FMemo = jsonParam.getString("FMemo").toString().replaceAll("\n","").replaceAll("\t","").replaceAll("\r","");
        String FSValue = jsonParam.getString("FSValue");
        String forder = jsonParam.getString("forder");
//        System.out.println(forder);
        try {
            SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
            String CookiesLoginStrucDID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginStrucDID != null && !CookiesLoginStrucDID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginStrucDID);
            }
            long key = Long.parseLong(ParamTools.getdeParam(fssid));//标准设定主表id
            TCourseStandard standard = tCourseStandardService.findById(key);
            if(forder.equals(0)){
                //表示第一条，无需判断
                TCourseStandardS tCourseStandardS = new TCourseStandardS();
                tCourseStandardS.setFcourseid(standard.getFcourseid());
                tCourseStandardS.setFabilityid(standard.getFabilityid());
                tCourseStandardS.setFabilitylevelid(standard.getFabilitylevelid());
                tCourseStandardS.setFabilitycamid(standard.getFabilitycamid());
                tCourseStandardS.setFcsid(key);
                tCourseStandardS.setFcid(Long.parseLong(uid));
                tCourseStandardS.setFcdate(new Date());
                tCourseStandardS.setFvaluell(BigDecimal.valueOf(Float.parseFloat(FValueLL)));
                tCourseStandardS.setFvalueul(BigDecimal.valueOf(Float.parseFloat(FValueUL)));
                tCourseStandardS.setFmemo(FMemo);
                tCourseStandardS.setFqztype(1);
                tCourseStandardS.setFbool(-1);
                tCourseStandardS.setFsvalue(FSValue);
                tCourseStandardS.setForder(Integer.parseInt(forder) +1 );
                tCourseStandardS.setFcid(Long.parseLong(uid));
                tCourseStandardS.setFcdate(new Date());
                tCourseStandardSService.save(tCourseStandardS);

                // 返回值
                object.put("result", "success");
            }else{
                BigDecimal ll = BigDecimal.valueOf(0);
                BigDecimal ul = BigDecimal.valueOf(0);
                //查询当前顺序的forder的上下限值 进行比较
                TCourseStandardSExample tCourseStandardSExample = new TCourseStandardSExample();
                TCourseStandardSExample.Criteria criteria = tCourseStandardSExample.createCriteria();
                criteria.andFqztypeEqualTo(1);
                criteria.andForderEqualTo(Integer.parseInt(forder));
                criteria.andFcsidEqualTo(key);

                List<TCourseStandardS> tStandardSettingSList = tCourseStandardSService.findByExample(tCourseStandardSExample);
                if(tStandardSettingSList.size()>0){
                    for (TCourseStandardS ts : tStandardSettingSList){
                        ll = ts.getFvaluell();
                        ul = ts.getFvalueul();
                    }
                }
                BigDecimal bigDecimalFromString = new BigDecimal(FValueLL);
                // 比较数值是否相等（忽略标度）
                int comparisonResult = bigDecimalFromString.compareTo(ul);
                if (comparisonResult >= 0) {

                    TCourseStandardS ts = new TCourseStandardS();
                    ts.setFcourseid(standard.getFcourseid());
                    ts.setFabilityid(standard.getFabilityid());
                    ts.setFabilitylevelid(standard.getFabilitylevelid());
                    ts.setFabilitycamid(standard.getFabilitycamid());
                    ts.setFcsid(key);
                    ts.setFvaluell(BigDecimal.valueOf(Float.parseFloat(FValueLL)));
                    ts.setFvalueul(BigDecimal.valueOf(Float.parseFloat(FValueUL)));
                    ts.setFmemo(FMemo);
                    ts.setFqztype(1);
                    ts.setFbool(-1);
                    ts.setFsvalue(FSValue);
                    ts.setForder(Integer.parseInt(forder) +1 );
                    ts.setFcid(Long.parseLong(uid));
                    ts.setFcdate(new Date());
                    tCourseStandardSService.save(ts);

                    // 返回值
                    object.put("result", "success");
                }else{
                    object.put("result", "error");
                    object.put("error", "区间值输入错误！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 修改取值类型 数值 得分 -标准设定主表
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/upStandardSettingsszdf", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String upStandardSettingsszdf( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String fkeyid = jsonParam.getString("fkeyid");//标准设定子表ID
        String FValueUL = jsonParam.getString("FValueUL");//区间上限
        String FValueLL = jsonParam.getString("FValueLL");//区间下限
        String FMemo = jsonParam.getString("FMemo").toString().replaceAll("\n","").replaceAll("\t","").replaceAll("\r","");;//描述
        String forder = jsonParam.getString("forder");
        String FSValue = jsonParam.getString("FSValue");
        try {
            SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
            String CookiesLoginStrucDID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginStrucDID != null && !CookiesLoginStrucDID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginStrucDID);
            }
            long key = Long.parseLong(ParamTools.getdeParam(fkeyid));//标准设定主表id

            BigDecimal ul = new BigDecimal("-1");
            BigDecimal ll = new BigDecimal("-1");

            // 目标值 -1
            BigDecimal target = new BigDecimal("-1");
            //获取上一个顺序的上限值
            TCourseStandardSExample tCourseStandardSExample = new TCourseStandardSExample();
            TCourseStandardSExample.Criteria criteria = tCourseStandardSExample.createCriteria();

            criteria.andForderEqualTo(Integer.parseInt(forder)-1);
            criteria.andFqztypeEqualTo(1);
            List<TCourseStandardS> tStandardSettingSList = tCourseStandardSService.findByExample(tCourseStandardSExample);
            if(tStandardSettingSList.size()>0){
                ul = tStandardSettingSList.get(0).getFvalueul();
            }

            //获取下一个顺序的下限值
            tCourseStandardSExample = new TCourseStandardSExample();
            criteria = tCourseStandardSExample.createCriteria();

            criteria.andForderEqualTo(Integer.parseInt(forder)+1);
            criteria.andFqztypeEqualTo(1);
            tStandardSettingSList = tCourseStandardSService.findByExample(tCourseStandardSExample);
            if(tStandardSettingSList.size()>0){
                ll = tStandardSettingSList.get(0).getFvaluell();
            }
            TCourseStandardS tStandardSettingS = new TCourseStandardS();
            tStandardSettingS.setFkeyid(key);
            tStandardSettingS.setFvaluell(BigDecimal.valueOf(Float.parseFloat(FValueLL)));
            tStandardSettingS.setFvalueul(BigDecimal.valueOf(Float.parseFloat(FValueUL)));
            tStandardSettingS.setFmemo(FMemo);
            tStandardSettingS.setFuid(Long.parseLong(uid));
            tStandardSettingS.setFudate(new Date());
            tStandardSettingS.setFsvalue(FSValue);
            BigDecimal FValueLL1 = new BigDecimal(FValueLL);
            BigDecimal FValueUL1 = new BigDecimal(FValueUL);
            if(ul.compareTo(target) > 0 && ll.compareTo(target) > 0){ //ul !=-1 && ll !=-1
                //区间在中间 进行判断
                if(FValueLL1.compareTo(ul) >=0 && FValueUL1.compareTo(ll) <= 0){
                    tCourseStandardSService.update(tStandardSettingS);
                    // 返回值
                    object.put("result", "success");
                }else{
                    object.put("result", "error");
                    object.put("error", "区间值输入错误！");
                }
            } else if(ul.compareTo(target) > 0 && ll.compareTo(target) == 0){ //ul > -1 && ll ==-1
                //只有上面 没有下面
                if(FValueLL1.compareTo(ul) >=0){

                    tCourseStandardSService.update(tStandardSettingS);
                    // 返回值
                    object.put("result", "success");
                }else{
                    object.put("result", "error");
                    object.put("error", "区间值输入错误！");
                }
            }else if(ul.compareTo(target) == 0 && ll.compareTo(target) > 0){//ul == -1 && ll > -1
                //只有下面 没有上面
                if(FValueUL1.compareTo(ll) <= 0 && FValueLL1.compareTo(ll) < 0){
                    tCourseStandardSService.update(tStandardSettingS);
                    // 返回值
                    object.put("result", "success");
                }else{
                    object.put("result", "error");
                    object.put("error", "区间值输入错误！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加取值类型 字符串 得分 -标准设定主表
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/addStandardSettingsstrdf", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addStandardSettingsstrdf( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String fssid = jsonParam.getString("FStandardID");
        String FValueUL = jsonParam.getString("FValueUL");
        String FSValue = jsonParam.getString("FSValue");
        String FMemo = jsonParam.getString("FMemo").toString().replaceAll("\n","").replaceAll("\t","").replaceAll("\r","");;


        try {
            SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
            String CookiesLoginStrucDID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginStrucDID != null && !CookiesLoginStrucDID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginStrucDID);
            }
            long key = Long.parseLong(ParamTools.getdeParam(fssid));//标准设定主表id
            TCourseStandard standard = tCourseStandardService.findById(key);
            TCourseStandardSExample tCourseStandardSExample =new TCourseStandardSExample();
            TCourseStandardSExample.Criteria criteria = tCourseStandardSExample.createCriteria();
            criteria.andFqztypeEqualTo(2);
            criteria.andFsvalueEqualTo(FSValue);
            criteria.andFcourseidEqualTo(standard.getFcourseid());
            criteria.andFabilitycamidEqualTo(standard.getFabilitycamid());
            criteria.andFabilityidEqualTo(standard.getFabilityid());
            criteria.andFabilitylevelidEqualTo(standard.getFabilitylevelid());
            List<TCourseStandardS> standardSList = tCourseStandardSService.findByExample(tCourseStandardSExample);
            if(standardSList.size()>0){
                object.put("result", "fail");
            }else{
                TCourseStandardS tStandardSettingS = new TCourseStandardS();
                tStandardSettingS.setFcourseid(standard.getFcourseid());
                tStandardSettingS.setFabilityid(standard.getFabilityid());
                tStandardSettingS.setFabilitylevelid(standard.getFabilitylevelid());
                tStandardSettingS.setFabilitycamid(standard.getFabilitycamid());
                tStandardSettingS.setFcsid(key);
                tStandardSettingS.setFvaluell(BigDecimal.valueOf(Float.parseFloat(FValueUL)));
                tStandardSettingS.setFvalueul(BigDecimal.valueOf(Float.parseFloat(FValueUL)));
                tStandardSettingS.setFmemo(FMemo);
                tStandardSettingS.setFqztype(2);
                tStandardSettingS.setFbool(-1);
                tStandardSettingS.setFsvalue(FSValue);
                tStandardSettingS.setForder(1);
                tStandardSettingS.setFcid(Long.parseLong(uid));
                tStandardSettingS.setFcdate(new Date());
                tCourseStandardSService.save(tStandardSettingS);
                // 返回值
                object.put("result", "success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 修改取值类型  字符串 得分 -标准设定主表
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/upStandardSettingsstrdf", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String upStandardSettingsstrdf( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String fkeyid = jsonParam.getString("fkeyid");//标准设定子表ID
        String FValueUL = jsonParam.getString("FValueUL");//区间上限
        String FSValue = jsonParam.getString("FSValue");//字符串值
        String FMemo = jsonParam.getString("FMemo").toString().replaceAll("\n","").replaceAll("\t","").replaceAll("\r","");;//描述

        try {
            SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
            String CookiesLoginStrucDID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginStrucDID != null && !CookiesLoginStrucDID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginStrucDID);
            }
            long key = Long.parseLong(ParamTools.getdeParam(fkeyid));//标准设定主表id
            //获取上一个顺序的上限值
            TCourseStandardSExample tCourseStandardSExample =new TCourseStandardSExample();
            TCourseStandardSExample.Criteria criteria = tCourseStandardSExample.createCriteria();
            criteria.andFkeyidNotEqualTo(key);
            criteria.andFsvalueEqualTo(FSValue);
            List<TCourseStandardS> standardSList = tCourseStandardSService.findByExample(tCourseStandardSExample);
            if(standardSList.size()>0){
                object.put("result", "fail");
            }else{
                TCourseStandardS tStandardSettingS = new TCourseStandardS();
                tStandardSettingS.setFkeyid(key);
                tStandardSettingS.setFvaluell(BigDecimal.valueOf(Float.parseFloat(FValueUL)));
                tStandardSettingS.setFvalueul(BigDecimal.valueOf(Float.parseFloat(FValueUL)));
                tStandardSettingS.setFmemo(FMemo);
                tStandardSettingS.setFsvalue(FSValue);
                tStandardSettingS.setFuid(Long.parseLong(uid));
                tStandardSettingS.setFudate(new Date());
                tCourseStandardSService.update(tStandardSettingS);
                // 返回值
                object.put("result", "success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 删除取值类型 数值 得分 -标准设定主表
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/delStandardSettingsszdf", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delStandardSettingsszdf( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String fssid = jsonParam.getString("id");//标准设定子表ID
        String fqztype = jsonParam.getString("fqztype");//取值类型
        try {
            SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
            String CookiesLoginStrucDID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginStrucDID != null && !CookiesLoginStrucDID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginStrucDID);
            }
            long key = Long.parseLong(ParamTools.getdeParam(fssid));//标准设定主表id
            //查询当前数据的顺序 ， 大于当前顺序的数据的forder -1
            TCourseStandardS tStandardSettingS = tCourseStandardSService.findById(key);
            int forder = tStandardSettingS.getForder();
            TCourseStandardSExample tCourseStandardSExample =new TCourseStandardSExample();
            TCourseStandardSExample.Criteria criteria = tCourseStandardSExample.createCriteria();
            criteria.andForderGreaterThan(forder);
            criteria.andFqztypeEqualTo(Integer.parseInt(fqztype));
            List<TCourseStandardS> standardSList = tCourseStandardSService.findByExample(tCourseStandardSExample);
            if(standardSList.size()>0){
                for (TCourseStandardS tss : standardSList){
                    TCourseStandardS tStandardSettingS1 = new TCourseStandardS();
                    tStandardSettingS1.setFkeyid(tss.getFkeyid());
                    tStandardSettingS1.setForder(tss.getForder() -1 );
                    tStandardSettingS1.setFcid(Long.parseLong(uid));
                    tStandardSettingS1.setFudate(tss.getFudate());
                    tCourseStandardSService.update(tStandardSettingS1);
                }
            }
            tCourseStandardSService.deleteById(key);
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
     * 删除取值类型 字符串 得分 -标准设定主表
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/delStandardSettingsstrdf", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delStandardSettingsstrdf( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String fssid = jsonParam.getString("id");//标准设定子表ID
        try {
            long key = Long.parseLong(ParamTools.getdeParam(fssid));//标准设定主表id
            tCourseStandardSService.deleteById(key);
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
     * 添加取值类型 bool 得分 -标准设定主表
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/addStandardSettingsdf", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addStandardSettingsdf( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FStandardID = jsonParam.getString("FStandardID");//设置主表ID
        String booltrue1 = jsonParam.getString("booltrue1");
        String boolfalse1 = jsonParam.getString("boolfalse1");
        String booltrueFMemo = jsonParam.getString("booltrueFMemo").toString().replaceAll("\n","").replaceAll("\t","").replaceAll("\r","");
        String boolfalseFMemo = jsonParam.getString("boolfalseFMemo").replaceAll("\n","").replaceAll("\t","").replaceAll("\r","");;
//        int fqztype = jsonParam.getInteger("fqztype");
        try {
            SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
            String CookiesLoginStrucDID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginStrucDID != null && !CookiesLoginStrucDID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginStrucDID);
            }
            FStandardID = FStandardID == null ? "0" : ParamTools.getdeParam(FStandardID);
            long key = Long.parseLong(FStandardID);//标准设定主表id
            //先查询一下 当前子表id是否存在 ，存在则修改

//            TStandardSettingSExample tStandardSettingSExample = null;
            TCourseStandard standard = tCourseStandardService.findById(key);
            if(!booltrue1.equals("")){
                TCourseStandardSExample tCourseStandardSExample = new TCourseStandardSExample();
                TCourseStandardSExample.Criteria criteria = tCourseStandardSExample.createCriteria();
                criteria.andFqztypeEqualTo(0);
                criteria.andFcsidEqualTo(key);
                criteria.andFboolEqualTo(0);//true
                List<TCourseStandardS> standardSList = tCourseStandardSService.findByExample(tCourseStandardSExample);
                if(standardSList.size() == 0){
                    TCourseStandardS tCourseStandardS = new TCourseStandardS();
                    tCourseStandardS.setFqztype(0);
                    tCourseStandardS.setFbool(0);
                    tCourseStandardS.setFcsid(key);
                    tCourseStandardS.setFcdate(new Date());
                    tCourseStandardS.setFcid(Long.parseLong(uid));
                    tCourseStandardS.setFvaluell(BigDecimal.valueOf(Long.parseLong(booltrue1)));
                    tCourseStandardS.setFvalueul(BigDecimal.valueOf(Long.parseLong(booltrue1)));
                    tCourseStandardS.setFmemo(booltrueFMemo);
                    tCourseStandardS.setFcourseid(standard.getFcourseid());
                    tCourseStandardS.setFabilitycamid(standard.getFabilitycamid());
                    tCourseStandardS.setFabilityid(standard.getFabilityid());
                    tCourseStandardS.setFabilitylevelid(standard.getFabilitylevelid());
                    tCourseStandardS.setFsvalue("是");
                    tCourseStandardSService.save(tCourseStandardS);
                }else{
                    for (TCourseStandardS ts : standardSList){
                        TCourseStandardS tCourseStandardS = new TCourseStandardS();
                        tCourseStandardS.setFkeyid(ts.getFkeyid());
                        tCourseStandardS.setFcsid(key);
                        tCourseStandardS.setFudate(new Date());
                        tCourseStandardS.setFuid(Long.parseLong(uid));
                        tCourseStandardS.setFvaluell(BigDecimal.valueOf(Long.parseLong(booltrue1)));
                        tCourseStandardS.setFvalueul(BigDecimal.valueOf(Long.parseLong(booltrue1)));
                        tCourseStandardS.setFmemo(booltrueFMemo);
                        tCourseStandardS.setFsvalue("是");
                        tCourseStandardSService.update(tCourseStandardS);
                    }
                }
            }
            if(!boolfalse1.equals("")){
                TCourseStandardSExample tCourseStandardSExample = new TCourseStandardSExample();
                TCourseStandardSExample.Criteria criteria = tCourseStandardSExample.createCriteria();
                criteria.andFqztypeEqualTo(0);
                criteria.andFcsidEqualTo(key);
                criteria.andFboolEqualTo(1);//false
                List<TCourseStandardS> standardSList = tCourseStandardSService.findByExample(tCourseStandardSExample);
                if(standardSList.size() == 0){
                    TCourseStandardS tCourseStandardS = new TCourseStandardS();
                    tCourseStandardS.setFqztype(0);
                    tCourseStandardS.setFbool(1);
                    tCourseStandardS.setFcsid(key);
                    tCourseStandardS.setFcdate(new Date());
                    tCourseStandardS.setFcid(Long.parseLong(uid));
                    tCourseStandardS.setFvaluell(BigDecimal.valueOf(Long.parseLong(boolfalse1)));
                    tCourseStandardS.setFvalueul(BigDecimal.valueOf(Long.parseLong(boolfalse1)));
                    tCourseStandardS.setFmemo(boolfalseFMemo);
                    tCourseStandardS.setFcourseid(standard.getFcourseid());
                    tCourseStandardS.setFabilitycamid(standard.getFabilitycamid());
                    tCourseStandardS.setFabilityid(standard.getFabilityid());
                    tCourseStandardS.setFabilitylevelid(standard.getFabilitylevelid());
                    tCourseStandardS.setFsvalue("否");
                    tCourseStandardSService.save(tCourseStandardS);
                }else{
                    for (TCourseStandardS ts : standardSList){
                        TCourseStandardS tCourseStandardS = new TCourseStandardS();
                        tCourseStandardS.setFkeyid(ts.getFkeyid());
                        tCourseStandardS.setFcsid(key);
                        tCourseStandardS.setFudate(new Date());
                        tCourseStandardS.setFuid(Long.parseLong(uid));
                        tCourseStandardS.setFvaluell(BigDecimal.valueOf(Long.parseLong(boolfalse1)));
                        tCourseStandardS.setFvalueul(BigDecimal.valueOf(Long.parseLong(boolfalse1)));
                        tCourseStandardS.setFmemo(boolfalseFMemo);
                        tCourseStandardS.setFsvalue("否");
                        tCourseStandardSService.update(tCourseStandardS);
                    }
                }
            }
            TCourseStandard tCoursestandard = new TCourseStandard();
            tCoursestandard.setFkeyid(key);
            tCoursestandard.setFszstate(2);
            tCourseStandardService.update(tCoursestandard);
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
     * 提交取值类型 数值,字符串 得分 -标准设定主表
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/addStandardSettingsqzdf", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addStandardSettingsqzdf( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FStandardID = jsonParam.getString("FStandardID");
        Integer fqztype = jsonParam.getInteger("fqztype");
        try {
            SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);
            String CookiesLoginStrucDID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginStrucDID != null && !CookiesLoginStrucDID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginStrucDID);
            }
            FStandardID = FStandardID  == null ? "0" : ParamTools.getdeParam(FStandardID);
            long key = Long.parseLong(FStandardID);//标准设定主表id
            //先查询一下 当前子表id是否存在 ，存在则修改
            TCourseStandardSExample tCourseStandardSExample = new TCourseStandardSExample();
            TCourseStandardSExample.Criteria criteria = tCourseStandardSExample.createCriteria();
            criteria.andFqztypeEqualTo(fqztype);
            criteria.andFcsidEqualTo(key);
            List<TCourseStandardS> standardSList = tCourseStandardSService.findByExample(tCourseStandardSExample);
            if(standardSList.size()>0){
                TCourseStandard tCourseStandard = new TCourseStandard();
                tCourseStandard.setFkeyid(key);
                tCourseStandard.setFszstate(2);
                tCourseStandardService.update(tCourseStandard);
                // 返回值
                object.put("result", "success");
            }else{
                object.put("result", "error");
                if(fqztype == 1)
                    object.put("error", "请添加区间值！");
                else if(fqztype == 2){
                    object.put("error", "请添加结果值！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 提交取值类型 原值 -标准设定主表
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/addStandardSettingsyz", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addStandardSettingsyz( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FStandardID = jsonParam.getString("FStandardID");
        try {
            String CookiesLoginStrucDID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginStrucDID != null && !CookiesLoginStrucDID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginStrucDID);
            }
            FStandardID = FStandardID  == null ? "0" : ParamTools.getdeParam(FStandardID);
            long key = Long.parseLong(FStandardID);//标准设定主表id
            TCourseStandard standard = tCourseStandardService.findById(key);

            TCourseStandardSExample tCourseStandardSExample = new TCourseStandardSExample();
            TCourseStandardSExample.Criteria criteria = tCourseStandardSExample.createCriteria();
            criteria.andFcsidEqualTo(key);
            criteria.andFqztypeEqualTo(3);
            List<TCourseStandardS> tCourseStandardSList = tCourseStandardSService.findByExample(tCourseStandardSExample);
            if(tCourseStandardSList.size() == 0){
                TCourseStandardS tCourseStandardS = new TCourseStandardS();
                tCourseStandardS.setFcourseid(standard.getFcourseid());
                tCourseStandardS.setFabilityid(standard.getFabilityid());
                tCourseStandardS.setFabilitylevelid(standard.getFabilitylevelid());
                tCourseStandardS.setFcsid(key);
                tCourseStandardS.setFqztype(3);
                tCourseStandardS.setFsvalue("原值");
                tCourseStandardS.setFabilitycamid(standard.getFabilitycamid());
                tCourseStandardS.setFcdate(new Date());
                tCourseStandardS.setFcid(Long.parseLong(uid));
                tCourseStandardSService.save(tCourseStandardS);
            }

            TCourseStandard tCourseStandard = new TCourseStandard();
            tCourseStandard.setFkeyid(key);
            tCourseStandard.setFszstate(2);
            tCourseStandard.setFuid(Long.parseLong(uid));
            tCourseStandard.setFudate(new Date());
            tCourseStandardService.update(tCourseStandard);
            // 返回值
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

}