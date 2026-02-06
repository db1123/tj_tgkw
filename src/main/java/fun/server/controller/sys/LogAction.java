package fun.server.controller.sys;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import fun.server.model.TLogAction;
import fun.server.model.TLogActionExample;
import fun.server.model.TUser;
import fun.server.service.TLogActionService;
import fun.server.service.TUserService;
import fun.tools.ParamTools;

/**
 * 日志管理 相关业务处理
 */
@RestController
@RequestMapping("/s/logAction")
public class LogAction {

    private final TLogActionService logActionService;
    private final TUserService userService;

    private static TLogActionService logActionService_static;
    private static TUserService userService_static;

    public LogAction(TLogActionService logActionService, TUserService userService) {
        this.logActionService = logActionService;
        this.userService = userService;
    }

    @PostConstruct 
    public void init() {
        logActionService_static = this.logActionService;
        userService_static = this.userService;
    }


    /**
     * 获取服务器信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryLogAction", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryLogAction( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String type = jsonParam.getString("type");
        String startDate = jsonParam.getString("startDate");
        String endDate = jsonParam.getString("endDate");
        String username = jsonParam.getString("username");
        String memo = jsonParam.getString("memo");
        try {
            // 获取数据库记录
            JSONArray logAction_Array = new JSONArray();
            // 查询条件
            TLogActionExample logActionExample = new TLogActionExample();
            TLogActionExample.Criteria criteria = logActionExample.createCriteria();
            if (type != null && !type.equals("0")) {
                criteria.andFTypeEqualTo(Integer.parseInt(type));
            }
            if (username != null && !username.equals("")) {
                criteria.andFUserNameLike("%" + username + "%");
            }
            if (memo != null && !memo.equals("")) {
                criteria.andFMemoLike("%" + memo + "%");
            }
            criteria.andFCDateBetween(sdf.parse(startDate.substring(0, 10) + " 00:00:00"), sdf.parse(endDate.substring(0, 10) + " 23:59:59"));
            // 排序
            String orderSql = "";
            for (Object order: order_JA) {
                JSONObject order_Object = (JSONObject)order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                logActionExample.setOrderByClause(orderSql.substring(1));
            } else {
                logActionExample.setOrderByClause("f_key_id desc");
            }
            PageInfo<TLogAction> logActionPageInfo = logActionService.findByExampleMapper(logActionExample, (page-1)*results, results);
            List<TLogAction> logAction_list = logActionPageInfo.getList();
            for (TLogAction logAction: logAction_list) {
                JSONObject logAction_object = new JSONObject();
                logAction_object.put("key", ParamTools.getEnParam(logAction.getfKeyId().toString()));
                logAction_object.put("f_user_name", logAction.getfUserName());
                logAction_object.put("f_type", logAction.getfType());
                logAction_object.put("typeName", getTypeName(logAction.getfType()));
                logAction_object.put("f_path", logAction.getfPath());
                logAction_object.put("f_memo", logAction.getfMemo());
                logAction_object.put("f_c_date", logAction.getfCDate() == null ? "" : sdf.format(logAction.getfCDate()));
                logAction_Array.add(logAction_object);
            }
            // 返回值
            object.put("list", logAction_Array);
            object.put("total", logActionPageInfo.getTotal()); // 总行数
            object.put("page", logActionPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取服务器信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryLogActionInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryLogActionInfo( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 获取数据库记录
            TLogAction logAction = logActionService.findById(key);
            JSONObject logAction_object = new JSONObject();
            logAction_object.put("key", ParamTools.getEnParam(logAction.getfKeyId().toString()));
            logAction_object.put("f_user_name", logAction.getfUserName());
            logAction_object.put("f_type", logAction.getfType());
            logAction_object.put("typeName", getTypeName(logAction.getfType()));
            logAction_object.put("f_path", logAction.getfPath());
            logAction_object.put("f_memo", logAction.getfMemo());
            logAction_object.put("f_c_date", logAction.getfCDate() == null ? "" : sdf.format(logAction.getfCDate()));
            // 返回值
            object.put("info", logAction_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    private String getTypeName(int id) {
        String typeName = "";
        switch (id) {
            case 1: typeName = "访问地址"; break;
            case 2: typeName = "服务请求"; break;
            case 3: typeName = "用户行为"; break;
            default: typeName = "未知"; break;
        }
        return typeName;
    }

    public static void AddLog(int userType, Long userId, int type, String path, String memo) {
        String userName = "匿名用户";
        if (userType == 1) {
            TUser user = userService_static.findById(userId);
            userName = user.getfName();
        }

        // 添加
        TLogAction logAction = new TLogAction();
        logAction.setfUserType(userType);
        logAction.setfUserId(userId);
        logAction.setfUserName(userName);
        logAction.setfType(type);
        logAction.setfPath(path);
        logAction.setfMemo(memo);
        logActionService_static.save(logAction);
    }
}