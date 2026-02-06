package fun.server.controller.sys.permission;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.server.model.*;
import fun.server.service.TLogActionService;
import fun.server.service.TUserGroupsRelationshipService;
import fun.server.service.TUserService;
import fun.tools.ParamTools;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * 用户组与用户关系管理 相关业务处理
 */
@RestController
@RequestMapping("/s/usergroupsrelationship")
public class UserGroupsRelationship {

    private final TUserGroupsRelationshipService tUserGroupsRelationshipService;
    private final TLogActionService logActionService;
    private final TUserService tUserService;

    public UserGroupsRelationship(TUserGroupsRelationshipService tUserGroupsRelationshipService, TLogActionService logActionService, TUserService tUserService) {
        this.tUserGroupsRelationshipService = tUserGroupsRelationshipService;
        this.logActionService = logActionService;
        this.tUserService = tUserService;
    }


    /**
     * 获取用户组与用户关系信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryusergroupsrelationship", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryusergroupsrelationship(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String id = jsonParam.getString("id"); //用户组ID
        try {
            // 获取数据库记录
            JSONArray usergroups_Array = new JSONArray();
            // 查询条件
            TUsergroupsrelationshipExample tUsergroupsrelationshipExample = new TUsergroupsrelationshipExample();
            TUsergroupsrelationshipExample.Criteria criteria = tUsergroupsrelationshipExample.createCriteria();
//
//            if (name != null && !name.equals("")) {
//                criteria.andFnameLike("%" + name + "%");
//            }
            id = id == null ? "0" : id.equals("0") ? "0" : ParamTools.getdeParam(id);
            criteria.andFugidEqualTo(Long.parseLong(id));
            // 排序
            String orderSql = "";
            for (Object order: order_JA) {
                JSONObject order_Object = (JSONObject)order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                tUsergroupsrelationshipExample.setOrderByClause(orderSql.substring(1));
            } else {
                tUsergroupsrelationshipExample.setOrderByClause("FCDATE desc");
            }
            PageInfo<TUsergroupsrelationship> usergroupsPageInfo = tUserGroupsRelationshipService.findByExampleMapper(tUsergroupsrelationshipExample, (page - 1) * results, results);
            List<TUsergroupsrelationship> usergroups_list = usergroupsPageInfo.getList();

            for (TUsergroupsrelationship usergroupsrelationship : usergroups_list) {
                JSONObject usergroups_object = new JSONObject();
                usergroups_object.put("key", ParamTools.getEnParam(usergroupsrelationship.getFkeyid().toString()));
                usergroups_object.put("FUGID", usergroupsrelationship.getFugid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(usergroupsrelationship.getFugid().toString()));
                usergroups_object.put("FUserID", usergroupsrelationship.getFuserid() == null ? ParamTools.getEnParam("0") : ParamTools.getEnParam(usergroupsrelationship.getFuserid().toString()));
                TUser tUser = tUserService.findById(usergroupsrelationship.getFuserid());
                usergroups_object.put("TUserName", tUser == null ? "" : tUser.getfName());
                usergroups_object.put("TUserNo", tUser == null ? "" : tUser.getfUserno());
                usergroups_object.put("FCID", usergroupsrelationship.getFcid());
                usergroups_object.put("FUID", usergroupsrelationship.getFuid());
                usergroups_object.put("FCDATE", usergroupsrelationship.getFcdate());
                usergroups_object.put("FUDATE", usergroupsrelationship.getFudate());
                usergroups_object.put("FState", usergroupsrelationship.getFstate());
                usergroups_Array.add(usergroups_object);
            }
            // 返回值
            object.put("list", usergroups_Array);
            object.put("total", usergroupsPageInfo.getTotal()); // 总行数
            object.put("page", usergroupsPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }





}