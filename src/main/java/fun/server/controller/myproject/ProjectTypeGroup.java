package fun.server.controller.myproject;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.*;
import fun.server.service.TFlowCellFileTypeGroupService;
import fun.server.service.TFlowCellFileTypeService;
import fun.server.service.TLogActionService;
import fun.server.service.TUserService;
import fun.tools.ParamTools;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 交付物类型分组管理 相关业务处理
 */
@RestController
@RequestMapping("/s/projecttypegroup")
public class ProjectTypeGroup {

    private final TFlowCellFileTypeGroupService tFlowCellFileTypeGroupService;
    private final TFlowCellFileTypeService tFlowCellFileTypeService;
    private final TLogActionService logActionService;
    private final TUserService tUserService;
    public ProjectTypeGroup(TFlowCellFileTypeGroupService tFlowCellFileTypeGroupService, TFlowCellFileTypeService tFlowCellFileTypeService, TLogActionService logActionService, TUserService tUserService) {
        this.tFlowCellFileTypeGroupService = tFlowCellFileTypeGroupService;
        this.tFlowCellFileTypeService = tFlowCellFileTypeService;
        this.logActionService = logActionService;
        this.tUserService = tUserService;
    }

    /**
     * 获取交付物类型分组信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryprojecttypegroup", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryprojecttypegroup(HttpServletRequest request)
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
            JSONArray projecttypegroup_Array = new JSONArray();
            // 查询条件
            TFlowCellFileTypeGroupExample tFlowCellFileTypeGroupExample = new TFlowCellFileTypeGroupExample(); 
            TFlowCellFileTypeGroupExample.Criteria criteria = tFlowCellFileTypeGroupExample.createCriteria();

            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");

            }
            tFlowCellFileTypeGroupExample.setOrderByClause("FCDATE desc");
            PageInfo<TFlowCellFileTypeGroup> projecttypegroupPageInfo = tFlowCellFileTypeGroupService.findByExampleMapper(tFlowCellFileTypeGroupExample, (page - 1) * results, results);
            List<TFlowCellFileTypeGroup> projecttypegroup_list = projecttypegroupPageInfo.getList();

            for (TFlowCellFileTypeGroup tFlowCellFileTypeGroup : projecttypegroup_list) {
                JSONObject projecttypegroup_object = new JSONObject();
                projecttypegroup_object.put("key", ParamTools.getEnParam(tFlowCellFileTypeGroup.getFkeyid().toString()));
                projecttypegroup_object.put("FName", tFlowCellFileTypeGroup.getFname());
                projecttypegroup_object.put("FType", tFlowCellFileTypeGroup.getFtype());
                if(tFlowCellFileTypeGroup.getFtype() ==1){
                    projecttypegroup_object.put("FTypeName", "项目");
                }else if(tFlowCellFileTypeGroup.getFtype() ==2){
                    projecttypegroup_object.put("FTypeName", "模具");
                }else{
                    projecttypegroup_object.put("FTypeName", "");
                }
                projecttypegroup_object.put("FCID", tFlowCellFileTypeGroup.getFcid());
                projecttypegroup_object.put("FUID", tFlowCellFileTypeGroup.getFuid());
                projecttypegroup_object.put("FCDATE", tFlowCellFileTypeGroup.getFcdate());
                projecttypegroup_object.put("FUDATE", tFlowCellFileTypeGroup.getFudate());
                projecttypegroup_object.put("FState", tFlowCellFileTypeGroup.getFstate());
                projecttypegroup_Array.add(projecttypegroup_object);
            }
            // 返回值
            object.put("list", projecttypegroup_Array);
            object.put("total", projecttypegroupPageInfo.getTotal()); // 总行数
            object.put("page", projecttypegroupPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }
 
    /**
     * 删除交付物类型分组信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
//    @LogOperation("删除交付物类型分组信息")
    @ResponseBody
    @RequestMapping(value = "/delprojecttypegroup", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delprojecttypegroup(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLoginpartnertypeID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginpartnertypeID != null && !CookiesLoginpartnertypeID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));

            TFlowCellFileTypeExample tFlowCellFileTypeExample = new TFlowCellFileTypeExample();
            TFlowCellFileTypeExample.Criteria typeExampleCriteria = tFlowCellFileTypeExample.createCriteria();
            typeExampleCriteria.andFFtypeEqualTo(Long.parseLong(id));
            List<TFlowCellFileType> typeList = tFlowCellFileTypeService.findByExample(tFlowCellFileTypeExample);
            if (typeList.size() > 0) {
                object.put("result", "error");
                object.put("error", "当前分组在交付物类型中被使用，不能删除！");
            } else {

                TFlowCellFileTypeGroup typeGroup = tFlowCellFileTypeGroupService.findById(Long.parseLong(id));

                tFlowCellFileTypeGroupService.deleteById(Long.parseLong(id));

                try {
                    TUser tUser = tUserService.findById(Long.parseLong(uid));
                    TLogAction logAction = new TLogAction();
                    logAction.setfUserId(tUser.getfKeyId());
                    logAction.setfUserName(tUser.getfName());
                    logAction.setfType(3);
                    logAction.setfPath("projecttypegroup/delprojecttypegroup{" + id + "}");
                    logAction.setfUserType(1);

                    logAction.setfMemo("删除交付物类型分组【"+typeGroup.getFname()+"】信息。");
                    logActionService.save(logAction);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

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




}