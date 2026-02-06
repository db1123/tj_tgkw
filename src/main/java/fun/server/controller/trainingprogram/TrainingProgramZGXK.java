package fun.server.controller.trainingprogram;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.TTrainingProgramZgxk;
import fun.server.model.TTrainingProgramZgxkExample;
import fun.server.service.TTrainingProgramZgxkService;
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
 * 主干学科管理 相关业务处理
 */
@RestController
@RequestMapping("/s/trainingprogramzgxk")
public class TrainingProgramZGXK {

    private final TTrainingProgramZgxkService tTrainingProgramZgxkService;

    public TrainingProgramZGXK(TTrainingProgramZgxkService tTrainingProgramZgxkService) {
        this.tTrainingProgramZgxkService = tTrainingProgramZgxkService;
    }


    /**
     * 获取主干学科信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytrainingprogramzgxk", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytrainingprogramzgxk(HttpServletRequest request)
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
        String FTPID = jsonParam.getString("FTPID");
        try {
            FTPID = FTPID == null ? "" : ParamTools.getdeParam(FTPID);
            // 获取数据库记录
            JSONArray trainingprogramzgxk_Array = new JSONArray();
            // 查询条件
            TTrainingProgramZgxkExample TTrainingProgramZgxkExample = new TTrainingProgramZgxkExample();
            TTrainingProgramZgxkExample.Criteria criteria = TTrainingProgramZgxkExample.createCriteria();
            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");
            }
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
                TTrainingProgramZgxkExample.setOrderByClause(orderSql.substring(1));
            } else {
                TTrainingProgramZgxkExample.setOrderByClause("FCDATE desc");
            }
            PageInfo<TTrainingProgramZgxk> trainingprogramzgxkPageInfo = tTrainingProgramZgxkService.findByExampleMapper(TTrainingProgramZgxkExample, (page - 1) * results, results);
            List<TTrainingProgramZgxk> trainingprogramzgxk_list = trainingprogramzgxkPageInfo.getList();
            for (TTrainingProgramZgxk trainingprogramzgxk : trainingprogramzgxk_list) {
                JSONObject trainingprogramzgxk_object = new JSONObject();
                trainingprogramzgxk_object.put("key", ParamTools.getEnParam(trainingprogramzgxk.getFkeyid().toString()));
                trainingprogramzgxk_object.put("FTMID", ParamTools.getEnParam(trainingprogramzgxk.getFtmid().toString()));
                trainingprogramzgxk_object.put("FTPID", ParamTools.getEnParam(trainingprogramzgxk.getFtpid().toString()));
                if (dataall == 1) {
                    trainingprogramzgxk_object.put("FName", trainingprogramzgxk.getFname() == null ? "" : trainingprogramzgxk.getFname());
                    trainingprogramzgxk_object.put("FCID", trainingprogramzgxk.getFcid());
                    trainingprogramzgxk_object.put("FUID", trainingprogramzgxk.getFuid());
                    trainingprogramzgxk_object.put("FCDATE", trainingprogramzgxk.getFcdate());
                    trainingprogramzgxk_object.put("FUDATE", trainingprogramzgxk.getFudate());
                } else {
                    trainingprogramzgxk_object.put("FName", "*****");
                    trainingprogramzgxk_object.put("FCID", "*****");
                    trainingprogramzgxk_object.put("FUID", "*****");
                    trainingprogramzgxk_object.put("FCDATE", "*****");
                    trainingprogramzgxk_object.put("FUDATE", "*****");
                }

                trainingprogramzgxk_object.put("FState", trainingprogramzgxk.getFstate());
                trainingprogramzgxk_Array.add(trainingprogramzgxk_object);
            }
            // 返回值
            object.put("list", trainingprogramzgxk_Array);
            object.put("total", trainingprogramzgxkPageInfo.getTotal()); // 总行数
            object.put("page", trainingprogramzgxkPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取主干学科信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatatrainingprogramzgxkSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatatrainingprogramzgxkSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray trainingprogramzgxk_Array = new JSONArray();
            TTrainingProgramZgxkExample trainingprogramzgxkExample = new TTrainingProgramZgxkExample();
            TTrainingProgramZgxkExample.Criteria criteria = trainingprogramzgxkExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            trainingprogramzgxkExample.setOrderByClause("FName asc");
            List<TTrainingProgramZgxk> trainingprogramzgxk_list = tTrainingProgramZgxkService.findByExample(trainingprogramzgxkExample);
            for (TTrainingProgramZgxk trainingprogramzgxk : trainingprogramzgxk_list) {
                JSONObject trainingprogramzgxk_object = new JSONObject();
                trainingprogramzgxk_object.put("id", ParamTools.getEnParam(trainingprogramzgxk.getFkeyid().toString()));
                trainingprogramzgxk_object.put("text", trainingprogramzgxk.getFname());
                trainingprogramzgxk_Array.add(trainingprogramzgxk_object);
            }
            // 返回值

            object.put("list", trainingprogramzgxk_Array);
            object.put("result", "success");
            object.put("results", trainingprogramzgxk_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取主干学科信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytrainingprogramzgxkInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytrainingprogramzgxkInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询主干学科信息
            TTrainingProgramZgxk trainingprogramzgxk = tTrainingProgramZgxkService.findById(key);
            JSONObject trainingprogramzgxk_object = new JSONObject();
            trainingprogramzgxk_object.put("key", ParamTools.getEnParam(trainingprogramzgxk.getFkeyid().toString()));
            trainingprogramzgxk_object.put("FTMID", ParamTools.getEnParam(trainingprogramzgxk.getFtmid().toString()));
            trainingprogramzgxk_object.put("FTPID", ParamTools.getEnParam(trainingprogramzgxk.getFtpid().toString()));
            trainingprogramzgxk_object.put("FName", trainingprogramzgxk.getFname() == null ? "" : trainingprogramzgxk.getFname());
            trainingprogramzgxk_object.put("FCID", trainingprogramzgxk.getFcid());
            trainingprogramzgxk_object.put("FUID", trainingprogramzgxk.getFuid());
            trainingprogramzgxk_object.put("FCDATE", trainingprogramzgxk.getFcdate());
            trainingprogramzgxk_object.put("FUDATE", trainingprogramzgxk.getFudate());
            trainingprogramzgxk_object.put("FState", trainingprogramzgxk.getFstate());
            // 返回值
            object.put("info", trainingprogramzgxk_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加主干学科信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加主干学科信息")
    @ResponseBody
    @RequestMapping(value = "/addtrainingprogramzgxk", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addtrainingprogramzgxk(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);

        String Fname = jsonParam.getString("FName");
        String FTMID = jsonParam.getString("FTMID");
        String FTPID = jsonParam.getString("FTPID");
        try {
            FTMID = FTMID == null ? "0" : ParamTools.getdeParam(FTMID);
            FTPID = FTPID == null ? "0" : ParamTools.getdeParam(FTPID);

            TTrainingProgramZgxkExample tTrainingProgramZgxkExample = new TTrainingProgramZgxkExample();
            tTrainingProgramZgxkExample.createCriteria().andFtmidEqualTo(Long.parseLong(FTMID)).andFtpidEqualTo(Long.parseLong(FTPID));
            tTrainingProgramZgxkExample.setOrderByClause("FOrder desc");
            List<TTrainingProgramZgxk> zgxkList = tTrainingProgramZgxkService.findByExample(tTrainingProgramZgxkExample);
            int forder = 1;
            if (zgxkList.size() > 0) {
                forder = zgxkList.get(0).getForder() + 1;
            }

            TTrainingProgramZgxkExample trainingprogramzgxkExample = new TTrainingProgramZgxkExample();
            TTrainingProgramZgxkExample.Criteria criteria = trainingprogramzgxkExample.createCriteria();
            criteria.andFnameEqualTo(Fname);
            criteria.andFtmidEqualTo(Long.parseLong(FTMID));
            criteria.andFtpidEqualTo(Long.parseLong(FTPID));
            List<TTrainingProgramZgxk> programList = tTrainingProgramZgxkService.findByExample(trainingprogramzgxkExample);


            if (programList.size() == 0) {
                String CookiesLogintrainingprogramzgxkID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogintrainingprogramzgxkID != null && !CookiesLogintrainingprogramzgxkID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogintrainingprogramzgxkID);
                }
                // 新建主干学科信息
                TTrainingProgramZgxk trainingprogramzgxk = new TTrainingProgramZgxk();
                trainingprogramzgxk.setFname(Fname);
                trainingprogramzgxk.setForder(forder);
                trainingprogramzgxk.setFtpid(Long.parseLong(FTPID));
                trainingprogramzgxk.setFtmid(Long.parseLong(FTMID));
                trainingprogramzgxk.setFcid(Long.parseLong(uid));
                trainingprogramzgxk.setFcdate(new Date());
                tTrainingProgramZgxkService.save(trainingprogramzgxk);
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
     * 修改主干学科信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改主干学科信息")
    @ResponseBody
    @RequestMapping(value = "/updatetrainingprogramzgxk", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatetrainingprogramzgxk(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String Fname = jsonParam.getString("FName");
        String FTMID = jsonParam.getString("FTMID");
        String FTPID = jsonParam.getString("FTPID");
        try {
            FTMID = FTMID == null ? "0" : ParamTools.getdeParam(FTMID);
            FTPID = FTPID == null ? "0" : ParamTools.getdeParam(FTPID);
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);

            TTrainingProgramZgxkExample trainingprogramzgxkExample = new TTrainingProgramZgxkExample();
            TTrainingProgramZgxkExample.Criteria criteria = trainingprogramzgxkExample.createCriteria();
            criteria.andFnameEqualTo(Fname);
            criteria.andFkeyidNotEqualTo(key);
            criteria.andFtmidEqualTo(Long.parseLong(FTMID));
            criteria.andFtpidEqualTo(Long.parseLong(FTPID));
            List<TTrainingProgramZgxk> programList = tTrainingProgramZgxkService.findByExample(trainingprogramzgxkExample);
            if (programList.size() == 0) {
                String CookiesLogintrainingprogramzgxkID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogintrainingprogramzgxkID != null && !CookiesLogintrainingprogramzgxkID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogintrainingprogramzgxkID);
                }
                // 更新主干学科信息
                TTrainingProgramZgxk trainingprogramzgxk = new TTrainingProgramZgxk();
                trainingprogramzgxk.setFkeyid(key);
                trainingprogramzgxk.setFname(Fname);
                trainingprogramzgxk.setFuid(Long.parseLong(uid));
                trainingprogramzgxk.setFudate(new Date());
                tTrainingProgramZgxkService.update(trainingprogramzgxk);
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
     * 删除主干学科信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除主干学科信息")
    @ResponseBody
    @RequestMapping(value = "/deltrainingprogramzgxk", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deltrainingprogramzgxk(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLogintrainingprogramzgxkID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintrainingprogramzgxkID != null && !CookiesLogintrainingprogramzgxkID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));

            tTrainingProgramZgxkService.deleteById(Long.parseLong(id));
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
     * 变更主干学科信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/statetrainingprogramzgxk", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String statetrainingprogramzgxk(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLogintrainingprogramzgxkID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintrainingprogramzgxkID != null && !CookiesLogintrainingprogramzgxkID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TTrainingProgramZgxk trainingprogramzgxk = new TTrainingProgramZgxk();
            trainingprogramzgxk.setFkeyid(Long.parseLong(id));
            trainingprogramzgxk.setFuid(Long.parseLong(uid));
            trainingprogramzgxk.setFudate(new Date());
            trainingprogramzgxk.setFstate(Integer.valueOf(state));
            tTrainingProgramZgxkService.update(trainingprogramzgxk);
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