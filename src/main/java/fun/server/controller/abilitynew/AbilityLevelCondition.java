package fun.server.controller.abilitynew;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.TAbilityLevelCondition;
import fun.server.model.TAbilityLevelConditionExample;
import fun.server.model.TAbilityTree;
import fun.server.model.TAbilityTreeExample;
import fun.server.service.TAbilityLevelConditionService;
import fun.server.service.TAbilityTreeService;
import fun.tools.ParamTools;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * 能力水平考核条件管理 相关业务处理
 */
@RestController
@RequestMapping("/s/abilitylevelcondition")
public class AbilityLevelCondition {

    private final TAbilityLevelConditionService tAbilityLevelConditionService;

    private final TAbilityTreeService tAbilityTreeService;

    public AbilityLevelCondition(TAbilityLevelConditionService tAbilityLevelConditionService, TAbilityTreeService tAbilityTreeService) {
        this.tAbilityLevelConditionService = tAbilityLevelConditionService;
        this.tAbilityTreeService = tAbilityTreeService;
    }


    /**
     * 获取能力水平考核条件信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryabilitylevelcondition", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryabilitylevelcondition(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("name");
        Integer dataall = jsonParam.getInteger("dataall");
        try {
            // 获取数据库记录
            JSONArray abilitylevelcondition_Array = new JSONArray();
            // 查询条件
            TAbilityLevelConditionExample TAbilityLevelConditionExample = new TAbilityLevelConditionExample();
            TAbilityLevelConditionExample.Criteria criteria = TAbilityLevelConditionExample.createCriteria();

            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");

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
                TAbilityLevelConditionExample.setOrderByClause(orderSql.substring(1));
            } else {
                TAbilityLevelConditionExample.setOrderByClause("FCDATE desc");
            }
            PageInfo<TAbilityLevelCondition> abilitylevelconditionPageInfo = tAbilityLevelConditionService.findByExampleMapper(TAbilityLevelConditionExample, (page - 1) * results, results);
            List<TAbilityLevelCondition> abilitylevelcondition_list = abilitylevelconditionPageInfo.getList();

            for (TAbilityLevelCondition abilitylevelcondition : abilitylevelcondition_list) {
                JSONObject abilitylevelcondition_object = new JSONObject();
                abilitylevelcondition_object.put("key", ParamTools.getEnParam(abilitylevelcondition.getFkeyid().toString()));
                if (dataall == 1) {
                    abilitylevelcondition_object.put("FName", abilitylevelcondition.getFname());
                    abilitylevelcondition_object.put("FScore", abilitylevelcondition.getFscore());
                    abilitylevelcondition_object.put("FNote", abilitylevelcondition.getFnote());
                    abilitylevelcondition_object.put("FCID", abilitylevelcondition.getFcid());
                    abilitylevelcondition_object.put("FUID", abilitylevelcondition.getFuid());
                    abilitylevelcondition_object.put("FCDATE", abilitylevelcondition.getFcdate());
                    abilitylevelcondition_object.put("FUDATE", abilitylevelcondition.getFudate());
                } else {
                    abilitylevelcondition_object.put("FName", "*****");
                    abilitylevelcondition_object.put("FCID", "*****");
                    abilitylevelcondition_object.put("FUID", "*****");
                    abilitylevelcondition_object.put("FCDATE", "*****");
                    abilitylevelcondition_object.put("FUDATE", "*****");
                }

                abilitylevelcondition_object.put("FState", abilitylevelcondition.getFstate());
                abilitylevelcondition_Array.add(abilitylevelcondition_object);
            }
            // 返回值
            object.put("list", abilitylevelcondition_Array);
            object.put("total", abilitylevelconditionPageInfo.getTotal()); // 总行数
            object.put("page", abilitylevelconditionPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取能力水平考核条件信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDataabilitylevelconditionSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDataabilitylevelconditionSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray abilitylevelcondition_Array = new JSONArray();
            TAbilityLevelConditionExample abilitylevelconditionExample = new TAbilityLevelConditionExample();
            TAbilityLevelConditionExample.Criteria criteria = abilitylevelconditionExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            abilitylevelconditionExample.setOrderByClause("FName asc");
            List<TAbilityLevelCondition> abilitylevelcondition_list = tAbilityLevelConditionService.findByExample(abilitylevelconditionExample);
            for (TAbilityLevelCondition abilitylevelcondition : abilitylevelcondition_list) {
                JSONObject abilitylevelcondition_object = new JSONObject();
                abilitylevelcondition_object.put("id", ParamTools.getEnParam(abilitylevelcondition.getFkeyid().toString()));
                abilitylevelcondition_object.put("text", abilitylevelcondition.getFname());
                abilitylevelcondition_Array.add(abilitylevelcondition_object);
            }
            // 返回值

            object.put("list", abilitylevelcondition_Array);
            object.put("result", "success");
            object.put("results", abilitylevelcondition_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取能力水平考核条件信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryabilitylevelconditionInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryabilitylevelconditionInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询能力水平考核条件信息
            TAbilityLevelCondition abilitylevelcondition = tAbilityLevelConditionService.findById(key);
            JSONObject abilitylevelcondition_object = new JSONObject();
            abilitylevelcondition_object.put("key", ParamTools.getEnParam(abilitylevelcondition.getFkeyid().toString()));
            abilitylevelcondition_object.put("FName", abilitylevelcondition.getFname());
            abilitylevelcondition_object.put("FScore", abilitylevelcondition.getFscore());
            abilitylevelcondition_object.put("FNote", abilitylevelcondition.getFnote());
            abilitylevelcondition_object.put("FCID", abilitylevelcondition.getFcid());
            abilitylevelcondition_object.put("FUID", abilitylevelcondition.getFuid());
            abilitylevelcondition_object.put("FCDATE", abilitylevelcondition.getFcdate());
            abilitylevelcondition_object.put("FUDATE", abilitylevelcondition.getFudate());
            abilitylevelcondition_object.put("FState", abilitylevelcondition.getFstate());
            // 返回值
            object.put("info", abilitylevelcondition_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加考核条件信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加考核条件信息")
    @ResponseBody
    @RequestMapping(value = "/addabilitylevelcondition", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addabilitylevelcondition(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FName = jsonParam.getString("FName");
        int FScore = jsonParam.getInteger("FScore");
        String FNote = jsonParam.getString("FNote");

        try {

            String CookiesLoginabilitylevelconditionID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginabilitylevelconditionID != null && !CookiesLoginabilitylevelconditionID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginabilitylevelconditionID);
            }
            TAbilityLevelConditionExample abilitylevelconditionExample = new TAbilityLevelConditionExample();
            TAbilityLevelConditionExample.Criteria criteria = abilitylevelconditionExample.createCriteria();
            criteria.andFnameEqualTo(FName);
            List<TAbilityLevelCondition> conditionList = tAbilityLevelConditionService.findByExample(abilitylevelconditionExample);
            if (conditionList.size() == 0) {
                TAbilityLevelCondition tAbilityLevelCondition = new TAbilityLevelCondition();
                tAbilityLevelCondition.setFname(FName);
                tAbilityLevelCondition.setFscore(FScore);
                tAbilityLevelCondition.setFnote(FNote);
                tAbilityLevelConditionService.save(tAbilityLevelCondition);
                // 返回值
                object.put("key", ParamTools.getEnParam(tAbilityLevelCondition.getFkeyid().toString()));
                object.put("FName", FName);
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
     * 修改考核条件信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改考核条件信息")
    @ResponseBody
    @RequestMapping(value = "/updateabilitylevelcondition", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateabilitylevelcondition(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FName = jsonParam.getString("FName");
        int FScore = jsonParam.getInteger("FScore");
        String FNote = jsonParam.getString("FNote");

        try {

            id = id == null ? "0" : ParamTools.getdeParam(id);

            String CookiesLoginabilitylevelconditionID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginabilitylevelconditionID != null && !CookiesLoginabilitylevelconditionID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginabilitylevelconditionID);
            }
            TAbilityLevelConditionExample abilitylevelconditionExample = new TAbilityLevelConditionExample();
            TAbilityLevelConditionExample.Criteria criteria = abilitylevelconditionExample.createCriteria();
            criteria.andFnameEqualTo(FName);
            criteria.andFkeyidNotEqualTo(Long.parseLong(id));
            List<TAbilityLevelCondition> conditionList = tAbilityLevelConditionService.findByExample(abilitylevelconditionExample);
            if (conditionList.size() == 0) {
                TAbilityLevelCondition tAbilityLevelCondition = new TAbilityLevelCondition();
                tAbilityLevelCondition.setFkeyid(Long.parseLong(id));
                tAbilityLevelCondition.setFname(FName);
                tAbilityLevelCondition.setFscore(FScore);
                tAbilityLevelCondition.setFnote(FNote);
                tAbilityLevelCondition.setFuid(Long.parseLong(uid));
                tAbilityLevelCondition.setFudate(new Date());
                tAbilityLevelConditionService.update(tAbilityLevelCondition);
                // 返回值
                object.put("key", ParamTools.getEnParam(tAbilityLevelCondition.getFkeyid().toString()));
                object.put("FName", FName);
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
     * 修改考核条件信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除考核条件信息")
    @ResponseBody
    @RequestMapping(value = "/delabilitylevelcondition", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delabilitylevelcondition(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            String CookiesLoginabilitylevelconditionID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginabilitylevelconditionID != null && !CookiesLoginabilitylevelconditionID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginabilitylevelconditionID);
            }
            TAbilityTreeExample tAbilityTreeExample = new TAbilityTreeExample();
            TAbilityTreeExample.Criteria criteria = tAbilityTreeExample.createCriteria();
            criteria.andFdelEqualTo(1);
            criteria.andFnodetypeEqualTo(3);
            criteria.andFalcidEqualTo(Long.parseLong(id));
            List<TAbilityTree> tAbilityTreeList = tAbilityTreeService.findByExample(tAbilityTreeExample);
            if (tAbilityTreeList.size() == 0) {
                tAbilityLevelConditionService.deleteById(Long.parseLong(id));
                object.put("result", "success");
            }else{
                object.put("result", "error");
                object.put("error", "考核条件已被使用，不能删除！可以在维护页面禁用！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 变更考核条件信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/stateabilitylevelcondition", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String stateabilitylevelcondition(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLoginabilitylevelconditionID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginabilitylevelconditionID != null && !CookiesLoginabilitylevelconditionID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TAbilityLevelCondition abilitylevelcondition = new TAbilityLevelCondition();
            abilitylevelcondition.setFkeyid(Long.parseLong(id));
            abilitylevelcondition.setFuid(Long.parseLong(uid));
            abilitylevelcondition.setFudate(new Date());
            abilitylevelcondition.setFstate(Integer.valueOf(state));
            tAbilityLevelConditionService.update(abilitylevelcondition);
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
     * 初始化
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/chushihua", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String chushihua(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);

        try {
            String CookiesLoginabilitylevelconditionID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginabilitylevelconditionID != null && !CookiesLoginabilitylevelconditionID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }

            String filePath = "D:\\tiaojian.json"; // 替换为你的JSON文件路径

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                StringBuilder content = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    content.append(line);
                }
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = content.toString();
                System.out.println("JSON文件内容: " + jsonData);

                JsonNode arrayNode = objectMapper.readTree(jsonData);

                if (arrayNode.isArray()) {
                    System.out.println("JSON数组包含 " + arrayNode.size() + " 个元素:");

                    for (JsonNode element : arrayNode) {
//                        System.out.println("\nID: " + element.get("FName").asText());
//                        System.out.println("Name: " + element.get("FConditionScore").asText());
//                        System.out.println("FNote: " + element.get("FNote").asText());

                        TAbilityLevelCondition tAbilityLevelCondition = new TAbilityLevelCondition();
                        tAbilityLevelCondition.setFname(element.get("FName").asText());
                        tAbilityLevelCondition.setFscore(element.get("FConditionScore").asText() == null? 0 : element.get("FConditionScore").asInt());
                        tAbilityLevelCondition.setFnote(element.get("FNote").asText());
                        tAbilityLevelCondition.setFcid(Long.parseLong(uid));
                        tAbilityLevelCondition.setFcdate(new Date());
                        tAbilityLevelConditionService.save(tAbilityLevelCondition);
                    }
                }

                // 这里可以添加JSON解析代码

            } catch (IOException e) {
                e.printStackTrace();
            }

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