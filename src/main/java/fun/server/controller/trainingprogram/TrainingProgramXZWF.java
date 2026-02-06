package fun.server.controller.trainingprogram;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.TTrainingProgramXzwf;
import fun.server.model.TTrainingProgramXzwfExample;
import fun.server.service.TTrainingProgramXzwfService;
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
 * 学制、学位、学分管理 相关业务处理
 */
@RestController
@RequestMapping("/s/trainingprogramxzwf")
public class TrainingProgramXZWF {

    private final TTrainingProgramXzwfService tTrainingProgramXzwfService;

    public TrainingProgramXZWF(TTrainingProgramXzwfService tTrainingProgramXzwfService) {
        this.tTrainingProgramXzwfService = tTrainingProgramXzwfService;
    }


    /**
     * 获取学制、学位、学分信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytrainingprogramxzwf", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytrainingprogramxzwf(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
//        String name = jsonParam.getString("name");
        Integer dataall = jsonParam.getInteger("dataall");
        String FTPID = jsonParam.getString("FTPID");

        try {
            // 获取数据库记录
            JSONArray trainingprogramxzwf_Array = new JSONArray();
            // 查询条件
            TTrainingProgramXzwfExample TTrainingProgramXzwfExample = new TTrainingProgramXzwfExample();
            TTrainingProgramXzwfExample.Criteria criteria = TTrainingProgramXzwfExample.createCriteria();
//            if (name != null && !name.equals("")) {
//                TTrainingProgramXzwfExample.or()
//                        .andFxzLike("%" + name + "%")
//                        .andFxwLike("%" + name + "%")
//                        .andFxfLike("%" + name + "%");
//            }
            FTPID = FTPID == null ? "0" : ParamTools.getdeParam(FTPID);

            criteria.andFtpidEqualTo(Long.parseLong(FTPID));
            // 排序
            String orderSql = "";
            for (Object order : order_JA) {
                JSONObject order_Object = (JSONObject) order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                TTrainingProgramXzwfExample.setOrderByClause(orderSql.substring(1));
            } else {
                TTrainingProgramXzwfExample.setOrderByClause("FCDATE desc");
            }
            PageInfo<TTrainingProgramXzwf> trainingprogramxzwfPageInfo = tTrainingProgramXzwfService.findByExampleMapper(TTrainingProgramXzwfExample, (page - 1) * results, results);
            List<TTrainingProgramXzwf> trainingprogramxzwf_list = trainingprogramxzwfPageInfo.getList();
            for (TTrainingProgramXzwf trainingprogramxzwf : trainingprogramxzwf_list) {
                JSONObject trainingprogramxzwf_object = new JSONObject();
                trainingprogramxzwf_object.put("key", ParamTools.getEnParam(trainingprogramxzwf.getFkeyid().toString()));
                trainingprogramxzwf_object.put("FTMID", ParamTools.getEnParam(trainingprogramxzwf.getFtmid().toString()));
                trainingprogramxzwf_object.put("FTPID", ParamTools.getEnParam(trainingprogramxzwf.getFtpid().toString()));
                if (dataall == 1) {
                    trainingprogramxzwf_object.put("FXZ", trainingprogramxzwf.getFxz() == null ? "" : trainingprogramxzwf.getFxz());
                    trainingprogramxzwf_object.put("FXW", trainingprogramxzwf.getFxw() == null ? "" : trainingprogramxzwf.getFxw());
                    trainingprogramxzwf_object.put("FXF", trainingprogramxzwf.getFxf() == null ? "" : trainingprogramxzwf.getFxf());
                    trainingprogramxzwf_object.put("FCID", trainingprogramxzwf.getFcid());
                    trainingprogramxzwf_object.put("FUID", trainingprogramxzwf.getFuid());
                    trainingprogramxzwf_object.put("FCDATE", trainingprogramxzwf.getFcdate());
                    trainingprogramxzwf_object.put("FUDATE", trainingprogramxzwf.getFudate());
                } else {
                    trainingprogramxzwf_object.put("FXZ", "*****");
                    trainingprogramxzwf_object.put("FXW", "*****");
                    trainingprogramxzwf_object.put("FXF", "*****");
                    trainingprogramxzwf_object.put("FCID", "*****");
                    trainingprogramxzwf_object.put("FUID", "*****");
                    trainingprogramxzwf_object.put("FCDATE", "*****");
                    trainingprogramxzwf_object.put("FUDATE", "*****");
                }

                trainingprogramxzwf_object.put("FState", trainingprogramxzwf.getFstate());
                trainingprogramxzwf_Array.add(trainingprogramxzwf_object);
            }
            // 返回值
            object.put("list", trainingprogramxzwf_Array);
            object.put("total", trainingprogramxzwfPageInfo.getTotal()); // 总行数
            object.put("page", trainingprogramxzwfPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取学制、学位、学分信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatatrainingprogramxzwfSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatatrainingprogramxzwfSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray trainingprogramxzwf_Array = new JSONArray();
            TTrainingProgramXzwfExample trainingprogramxzwfExample = new TTrainingProgramXzwfExample();
            TTrainingProgramXzwfExample.Criteria criteria = trainingprogramxzwfExample.createCriteria();
            if (search != null && !search.equals("")) {

                trainingprogramxzwfExample.or()
                        .andFxzLike("%" + search + "%")
                        .andFxwLike("%" + search + "%")
                        .andFxfLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            trainingprogramxzwfExample.setOrderByClause("FName asc");
            List<TTrainingProgramXzwf> trainingprogramxzwf_list = tTrainingProgramXzwfService.findByExample(trainingprogramxzwfExample);
            for (TTrainingProgramXzwf trainingprogramxzwf : trainingprogramxzwf_list) {
                JSONObject trainingprogramxzwf_object = new JSONObject();
                trainingprogramxzwf_object.put("id", ParamTools.getEnParam(trainingprogramxzwf.getFkeyid().toString()));
                trainingprogramxzwf_object.put("text", trainingprogramxzwf.getFxz());
                trainingprogramxzwf_Array.add(trainingprogramxzwf_object);
            }
            // 返回值

            object.put("list", trainingprogramxzwf_Array);
            object.put("result", "success");
            object.put("results", trainingprogramxzwf_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取学制、学位、学分信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytrainingprogramxzwfInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytrainingprogramxzwfInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询学制、学位、学分信息
            TTrainingProgramXzwf trainingprogramxzwf = tTrainingProgramXzwfService.findById(key);
            JSONObject trainingprogramxzwf_object = new JSONObject();
            trainingprogramxzwf_object.put("key", ParamTools.getEnParam(trainingprogramxzwf.getFkeyid().toString()));
            trainingprogramxzwf_object.put("FTMID", ParamTools.getEnParam(trainingprogramxzwf.getFtmid().toString()));
            trainingprogramxzwf_object.put("FTPID", ParamTools.getEnParam(trainingprogramxzwf.getFtpid().toString()));
            trainingprogramxzwf_object.put("FXZ", trainingprogramxzwf.getFxz() == null ? "" : trainingprogramxzwf.getFxz());
            trainingprogramxzwf_object.put("FXW", trainingprogramxzwf.getFxw() == null ? "" : trainingprogramxzwf.getFxw());
            trainingprogramxzwf_object.put("FXF", trainingprogramxzwf.getFxf() == null ? "" : trainingprogramxzwf.getFxf());
            trainingprogramxzwf_object.put("FCID", trainingprogramxzwf.getFcid());
            trainingprogramxzwf_object.put("FUID", trainingprogramxzwf.getFuid());
            trainingprogramxzwf_object.put("FCDATE", trainingprogramxzwf.getFcdate());
            trainingprogramxzwf_object.put("FUDATE", trainingprogramxzwf.getFudate());
            trainingprogramxzwf_object.put("FState", trainingprogramxzwf.getFstate());
            // 返回值
            object.put("info", trainingprogramxzwf_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加学制、学位、学分信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加学制、学位、学分信息")
    @ResponseBody
    @RequestMapping(value = "/addtrainingprogramxzwf", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addtrainingprogramxzwf(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);

        String FXZ = jsonParam.getString("FXZ");
        String FXW = jsonParam.getString("FXW");
        String FXF = jsonParam.getString("FXF");
        Integer fType = jsonParam.getInteger("FType");
        String FTMID = jsonParam.getString("FTMID");
        String FTPID = jsonParam.getString("FTPID");
        try {
            FTMID = FTMID == null ? "0" : ParamTools.getdeParam(FTMID);
            FTPID = FTPID == null ? "0" : ParamTools.getdeParam(FTPID);

            if(FXZ.equals("") && FXW.equals("") && FXF.equals("")) {
                object.put("result", "error");
                object.put("error", "至少填一项内容再进行保存！");
            }else{
                String CookiesLogintrainingprogramxzwfID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogintrainingprogramxzwfID != null && !CookiesLogintrainingprogramxzwfID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogintrainingprogramxzwfID);
                }
                // 新建学制、学位、学分信息
                TTrainingProgramXzwf trainingprogramxzwf = new TTrainingProgramXzwf();
                trainingprogramxzwf.setFtmid(Long.parseLong(FTMID));
                trainingprogramxzwf.setFtpid(Long.parseLong(FTPID));
                trainingprogramxzwf.setFxz(FXZ);
                trainingprogramxzwf.setFxw(FXW);
                trainingprogramxzwf.setFxf(FXF);
                trainingprogramxzwf.setFcid(Long.parseLong(uid));
                trainingprogramxzwf.setFcdate(new Date());
                tTrainingProgramXzwfService.save(trainingprogramxzwf);
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
     * 修改学制、学位、学分信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改学制、学位、学分信息")
    @ResponseBody
    @RequestMapping(value = "/updatetrainingprogramxzwf", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatetrainingprogramxzwf(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String FXZ = jsonParam.getString("FXZ");
        String FXW = jsonParam.getString("FXW");
        String FXF = jsonParam.getString("FXF");
//        String FTMID = jsonParam.getString("FTMID");
//        String FTPID = jsonParam.getString("FTPID");
        try {
//            FTMID = FTMID == null ? "0" : ParamTools.getdeParam(FTMID);
//            FTPID = FTPID == null ? "0" : ParamTools.getdeParam(FTPID);
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);

            String CookiesLogintrainingprogramxzwfID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintrainingprogramxzwfID != null && !CookiesLogintrainingprogramxzwfID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLogintrainingprogramxzwfID);
            }
            // 更新学制、学位、学分信息
            TTrainingProgramXzwf trainingprogramxzwf = new TTrainingProgramXzwf();
            trainingprogramxzwf.setFkeyid(key);
            trainingprogramxzwf.setFxz(FXZ);
            trainingprogramxzwf.setFxw(FXW);
            trainingprogramxzwf.setFxf(FXF);
            trainingprogramxzwf.setFuid(Long.parseLong(uid));
            trainingprogramxzwf.setFudate(new Date());
            tTrainingProgramXzwfService.update(trainingprogramxzwf);
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
     * 删除学制、学位、学分信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除学制、学位、学分信息")
    @ResponseBody
    @RequestMapping(value = "/deltrainingprogramxzwf", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deltrainingprogramxzwf(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLogintrainingprogramxzwfID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintrainingprogramxzwfID != null && !CookiesLogintrainingprogramxzwfID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));

            tTrainingProgramXzwfService.deleteById(Long.parseLong(id));
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
     * 变更学制、学位、学分信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/statetrainingprogramxzwf", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String statetrainingprogramxzwf(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLogintrainingprogramxzwfID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintrainingprogramxzwfID != null && !CookiesLogintrainingprogramxzwfID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TTrainingProgramXzwf trainingprogramxzwf = new TTrainingProgramXzwf();
            trainingprogramxzwf.setFkeyid(Long.parseLong(id));
            trainingprogramxzwf.setFuid(Long.parseLong(uid));
            trainingprogramxzwf.setFudate(new Date());
            trainingprogramxzwf.setFstate(Integer.valueOf(state));
            tTrainingProgramXzwfService.update(trainingprogramxzwf);
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
     * 查询是否存在学制学位学分
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytrainingprogramxzwfcz", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytrainingprogramxzwfcz(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");//专业ID
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            TTrainingProgramXzwfExample trainingprogramExample = new TTrainingProgramXzwfExample();
            trainingprogramExample.createCriteria().andFtpidEqualTo(key);
            List<TTrainingProgramXzwf> trainingProgramList = tTrainingProgramXzwfService.findByExample(trainingprogramExample);
            // 返回值
            object.put("isadd", trainingProgramList.size());
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }
}