package fun.server.controller.sys.permission;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.server.model.*;
import fun.server.service.*;
import fun.tools.ParamTools;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 权限项管理 相关业务处理
 */
@RestController
@RequestMapping("/s/permissionterm")
public class PermissionTerm {

    private final TPermissionTermService tPermissionTermService;
    private final TLogActionService logActionService;
    private final TUserService tUserService;

    public PermissionTerm(TPermissionTermService tPermissionTermService, TLogActionService logActionService, TUserService tUserService) {
        this.tPermissionTermService = tPermissionTermService;
        this.logActionService = logActionService;
        this.tUserService = tUserService;
    }

    /**
     * 获取权限组信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytpermissionTerm", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytpermissionTerm(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("name");
        try {
            // 获取数据库记录
            JSONArray tpermissionTerm_Array = new JSONArray();
            // 查询条件
            TPermissiontermExample TPermissiontermExample = new TPermissiontermExample();
            TPermissiontermExample.Criteria criteria = TPermissiontermExample.createCriteria();

            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");
            }
            // 排序
            String orderSql = "";
            for (Object order: order_JA) {
                JSONObject order_Object = (JSONObject)order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                TPermissiontermExample.setOrderByClause(orderSql.substring(1));
            } else {
                TPermissiontermExample.setOrderByClause("FCDATE desc");
            }
            PageInfo<TPermissionterm> tpermissionTermPageInfo = tPermissionTermService.findByExampleMapper(TPermissiontermExample, (page - 1) * results, results);
            List<TPermissionterm> tpermissionTerm_list = tpermissionTermPageInfo.getList();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (TPermissionterm tpermissionTerm : tpermissionTerm_list) {
                JSONObject tpermissionTerm_object = new JSONObject();
                tpermissionTerm_object.put("key", ParamTools.getEnParam(tpermissionTerm.getFkeyid().toString()));
                tpermissionTerm_object.put("FName", tpermissionTerm.getFname() == null ? "" : tpermissionTerm.getFname());
                tpermissionTerm_object.put("FCID", tpermissionTerm.getFcid());
                tpermissionTerm_object.put("FUID", tpermissionTerm.getFuid());
                tpermissionTerm_object.put("FCDATE", tpermissionTerm.getFcdate() == null ? "" : sdf.format(tpermissionTerm.getFcdate()));
                tpermissionTerm_object.put("FUDATE", tpermissionTerm.getFudate() == null ? "" : sdf.format(tpermissionTerm.getFudate()));
                tpermissionTerm_object.put("FState", tpermissionTerm.getFstate());
                tpermissionTerm_Array.add(tpermissionTerm_object);
            }
            // 返回值
            object.put("list", tpermissionTerm_Array);
            object.put("total", tpermissionTermPageInfo.getTotal()); // 总行数
            object.put("page", tpermissionTermPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取权限项信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatatpermissiontermSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatatpermissiontermSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray tpermissionterm_Array = new JSONArray();
            TPermissiontermExample tpermissiontermExample = new TPermissiontermExample();
            TPermissiontermExample.Criteria criteria = tpermissiontermExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            tpermissiontermExample.setOrderByClause("FKeyID asc");
            List<TPermissionterm> tpermissionterm_list = tPermissionTermService.findByExample(tpermissiontermExample);
            for (TPermissionterm tpermissionterm : tpermissionterm_list) {
                JSONObject tpermissionterm_object = new JSONObject();
                tpermissionterm_object.put("id", ParamTools.getEnParam(tpermissionterm.getFkeyid().toString()));
                tpermissionterm_object.put("text", tpermissionterm.getFname());
                tpermissionterm_Array.add(tpermissionterm_object);
            }
            // 返回值

            object.put("list", tpermissionterm_Array);
            object.put("result", "success");
            object.put("results", tpermissionterm_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }




}