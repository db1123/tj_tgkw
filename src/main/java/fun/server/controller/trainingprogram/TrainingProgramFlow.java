package fun.server.controller.trainingprogram;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import fun.config.intercepors.actionlog.LogOperation;
import fun.server.controller.sys.LogAction;
import fun.server.model.TFlow;
import fun.server.model.TFlowExample;
import fun.server.service.TFlowService;
import fun.tools.ParamTools;

/**
 * 培养方案管理 相关业务处理
 */
@RestController
@RequestMapping("/s/trainingprogramflow")
public class TrainingProgramFlow {

    private final TFlowService flowService;

    public TrainingProgramFlow(TFlowService flowService) {
        this.flowService = flowService;
    }
    
    /**
     * 获取流程图列表信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryFlow", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryFlow( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String tpid = jsonParam.getString("tpid");
        String name = jsonParam.getString("name");
        String explain = jsonParam.getString("explain");
        try {
            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginUserID);
            }
            // 获取数据库记录
            JSONArray Flow_Array = new JSONArray();
            // 查询条件
            TFlowExample FlowExample = new TFlowExample();
            TFlowExample.Criteria criteria = FlowExample.createCriteria();
            criteria.andFCIdEqualTo(Long.parseLong(uid)); // 只读取自己创建的数据
            criteria.andFTpIdEqualTo(Long.parseLong(ParamTools.getdeParam(tpid))); // 培养ID
            // 其它查询条件
            if (name != null && !name.equals("")) {
                criteria.andFNameLike("%" + name + "%");
            }
            if (explain != null && !explain.equals("")) {
                criteria.andFExplainLike("%" + explain + "%");
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
                FlowExample.setOrderByClause(orderSql.substring(1));
            } else {
                FlowExample.setOrderByClause("f_c_date desc");
            }         
            PageInfo<TFlow> FlowPageInfo = flowService.findByExampleMapper(FlowExample, (page-1)*results, results);
            List<TFlow> Flow_list = FlowPageInfo.getList();
            for (TFlow Flow: Flow_list) {
                JSONObject Flow_object = new JSONObject();
                Flow_object.put("key", ParamTools.getEnParam(Flow.getfKeyId().toString()));
                Flow_object.put("f_name", Flow.getfName() == null ? "" : Flow.getfName());
                Flow_object.put("f_explain", Flow.getfExplain() == null ? "" : Flow.getfExplain());
                Flow_object.put("f_c_date", Flow.getfCDate() == null ? "" : sdf.format(Flow.getfCDate()));
                Flow_Array.add(Flow_object);
            }
            // 返回值
            object.put("list", Flow_Array);
            object.put("total", FlowPageInfo.getTotal()); // 总行数
            object.put("page", FlowPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取流程图信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryFlowInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryFlowInfo( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询用户信息
            TFlow Flow = flowService.findById(key);
            JSONObject Flow_object = new JSONObject();
            Flow_object.put("key", ParamTools.getEnParam(Flow.getfKeyId().toString()));
            Flow_object.put("f_name", Flow.getfName());
            Flow_object.put("f_explain", Flow.getfExplain());
            Flow_object.put("f_xml", Flow.getfXml());
            Flow_object.put("f_json", Flow.getfJson());
            // 返回值
            object.put("info", Flow_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }









    /**
     * 添加流程信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @LogOperation("添加流程信息")
    @ResponseBody
    @RequestMapping(value = "/addFlow", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addFlow( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String tpid = jsonParam.getString("tpid");
        String f_name = jsonParam.getString("f_name");
        String f_explain = jsonParam.getString("f_explain");
        String f_xml = jsonParam.getString("f_xml");
        String f_json = jsonParam.getString("f_json");
        try {
            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginUserID);
            }
            // 检测
            boolean go = true;
            // 判断名称是否重复
            TFlowExample flowExample = new TFlowExample();
            flowExample.or().andFNameEqualTo(f_name);
            List<TFlow> nameFlows = flowService.findByExample(flowExample);
            if (nameFlows.size() > 0) {
                go = false;
                object.put("result", "error");
                object.put("error", "名称重复");
            } else {
                object.put("result", "success");
            }
            // 编辑
            if (go) {
                // 添加数据
                TFlow Flow = new TFlow();
                Flow.setfTpId(Long.parseLong(ParamTools.getdeParam(tpid)));
                Flow.setfName(f_name);
                Flow.setfExplain(f_explain);
                Flow.setfXml(f_xml);
                Flow.setfJson(f_json);
                Flow.setfCId(Long.parseLong(uid));
                Flow.setfCDate(new Date());
                flowService.save(Flow);
                // 返回值
                object.put("key", ParamTools.getEnParam(Flow.getfKeyId().toString()));
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
     * 修改流程信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @LogOperation("修改流程信息")
    @ResponseBody
    @RequestMapping(value = "/updateFlow", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateFlow( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("f_key_id");
        String f_name = jsonParam.getString("f_name");
        String f_explain = jsonParam.getString("f_explain");
        String f_xml = jsonParam.getString("f_xml");
        String f_json = jsonParam.getString("f_json");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            Long key = Long.parseLong(id);
            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginUserID);
            }
            // 检测
            boolean go = true;
            // 判断名称是否重复
            TFlowExample flowExample = new TFlowExample();
            flowExample.or()
                .andFNameEqualTo(f_name)
                .andFKeyIdNotEqualTo(key);
            List<TFlow> nameFlows = flowService.findByExample(flowExample);
            if (nameFlows.size() > 0) {
                go = false;
                object.put("result", "error");
                object.put("error", "名称重复");
            } else {
                object.put("result", "success");
            }
            // 编辑
            if (go) {
                // 更新数据
                TFlow Flow = new TFlow();
                Flow.setfKeyId(key);
                Flow.setfName(f_name);
                Flow.setfExplain(f_explain);
                Flow.setfXml(f_xml);
                Flow.setfJson(f_json);
                Flow.setfUId(Long.parseLong(uid));
                Flow.setfUDate(new Date());
                flowService.update(Flow);
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
     * 删除流程信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @LogOperation("删除流程信息")
    @ResponseBody
    @RequestMapping(value = "/delFlow", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delFlow( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 删除主记录
            flowService.deleteById(key);
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
