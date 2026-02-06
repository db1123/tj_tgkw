package fun.server.controller.trainingprogram;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.TTrainingProgramByyq;
import fun.server.model.TTrainingProgramByyqExample;
import fun.server.service.TTrainingProgramByyqService;
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
 * 毕业要求管理 相关业务处理
 */
@RestController
@RequestMapping("/s/trainingprogrambyyq")
public class TrainingProgramBYYQ {

    private final TTrainingProgramByyqService tTrainingProgramByyqService;

    public TrainingProgramBYYQ(TTrainingProgramByyqService tTrainingProgramByyqService) {
        this.tTrainingProgramByyqService = tTrainingProgramByyqService;
    }


    /**
     * 获取毕业要求信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytrainingprogrambyyq", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytrainingprogrambyyq(HttpServletRequest request)
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
            JSONArray trainingprogrambyyq_Array = new JSONArray();
            // 查询条件
            TTrainingProgramByyqExample TTrainingProgramByyqExample = new TTrainingProgramByyqExample();
            TTrainingProgramByyqExample.Criteria criteria = TTrainingProgramByyqExample.createCriteria();
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
                TTrainingProgramByyqExample.setOrderByClause(orderSql.substring(1));
            } else {
                TTrainingProgramByyqExample.setOrderByClause("FCDATE desc");
            }
            PageInfo<TTrainingProgramByyq> trainingprogrambyyqPageInfo = tTrainingProgramByyqService.findByExampleMapper(TTrainingProgramByyqExample, (page - 1) * results, results);
            List<TTrainingProgramByyq> trainingprogrambyyq_list = trainingprogrambyyqPageInfo.getList();
            for (TTrainingProgramByyq trainingprogrambyyq : trainingprogrambyyq_list) {
                JSONObject trainingprogrambyyq_object = new JSONObject();
                trainingprogrambyyq_object.put("key", ParamTools.getEnParam(trainingprogrambyyq.getFkeyid().toString()));
                trainingprogrambyyq_object.put("FTMID", ParamTools.getEnParam(trainingprogrambyyq.getFtmid().toString()));
                trainingprogrambyyq_object.put("FTPID", ParamTools.getEnParam(trainingprogrambyyq.getFtpid().toString()));
                if (dataall == 1) {
                    trainingprogrambyyq_object.put("FName", trainingprogrambyyq.getFname() == null ? "" : trainingprogrambyyq.getFname());
                    trainingprogrambyyq_object.put("FNote", trainingprogrambyyq.getFnote() == null ? "" : trainingprogrambyyq.getFnote());
                    trainingprogrambyyq_object.put("FCID", trainingprogrambyyq.getFcid());

                    trainingprogrambyyq_object.put("FUID", trainingprogrambyyq.getFuid());
                    trainingprogrambyyq_object.put("FCDATE", trainingprogrambyyq.getFcdate());
                    trainingprogrambyyq_object.put("FUDATE", trainingprogrambyyq.getFudate());
                } else {
                    trainingprogrambyyq_object.put("FName", "*****");
                    trainingprogrambyyq_object.put("FCID", "*****");
                    trainingprogrambyyq_object.put("FNote", "*****");

                    trainingprogrambyyq_object.put("FUID", "*****");
                    trainingprogrambyyq_object.put("FCDATE", "*****");
                    trainingprogrambyyq_object.put("FUDATE", "*****");
                }
                trainingprogrambyyq_object.put("FOrder", trainingprogrambyyq.getForder());
                trainingprogrambyyq_object.put("FState", trainingprogrambyyq.getFstate());
                trainingprogrambyyq_Array.add(trainingprogrambyyq_object);
            }
            // 返回值
            object.put("list", trainingprogrambyyq_Array);
            object.put("total", trainingprogrambyyqPageInfo.getTotal()); // 总行数
            object.put("page", trainingprogrambyyqPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取毕业要求信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatatrainingprogrambyyqSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatatrainingprogrambyyqSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray trainingprogrambyyq_Array = new JSONArray();
            TTrainingProgramByyqExample trainingprogrambyyqExample = new TTrainingProgramByyqExample();
            TTrainingProgramByyqExample.Criteria criteria = trainingprogrambyyqExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            trainingprogrambyyqExample.setOrderByClause("FName asc");
            List<TTrainingProgramByyq> trainingprogrambyyq_list = tTrainingProgramByyqService.findByExample(trainingprogrambyyqExample);
            for (TTrainingProgramByyq trainingprogrambyyq : trainingprogrambyyq_list) {
                JSONObject trainingprogrambyyq_object = new JSONObject();
                trainingprogrambyyq_object.put("id", ParamTools.getEnParam(trainingprogrambyyq.getFkeyid().toString()));
                trainingprogrambyyq_object.put("text", trainingprogrambyyq.getFname());
                trainingprogrambyyq_Array.add(trainingprogrambyyq_object);
            }
            // 返回值

            object.put("list", trainingprogrambyyq_Array);
            object.put("result", "success");
            object.put("results", trainingprogrambyyq_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 获取毕业要求信息(下拉列表) _ 培养方案ID
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatatrainingprogrambyyqSelect_id", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatatrainingprogrambyyqSelect_id(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        String FTPID = request.getParameter("FTPID");
        try {
            FTPID = FTPID == null ? "" : ParamTools.getdeParam(FTPID);
            // 获取数据库记录
            JSONArray trainingprogrambyyq_Array = new JSONArray();
            TTrainingProgramByyqExample trainingprogrambyyqExample = new TTrainingProgramByyqExample();
            TTrainingProgramByyqExample.Criteria criteria = trainingprogrambyyqExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            criteria.andFtpidEqualTo(Long.parseLong(FTPID));
            trainingprogrambyyqExample.setOrderByClause("FOrder asc");
            List<TTrainingProgramByyq> trainingprogrambyyq_list = tTrainingProgramByyqService.findByExample(trainingprogrambyyqExample);
            for (TTrainingProgramByyq trainingprogrambyyq : trainingprogrambyyq_list) {
                JSONObject trainingprogrambyyq_object = new JSONObject();
                trainingprogrambyyq_object.put("id", ParamTools.getEnParam(trainingprogrambyyq.getFkeyid().toString()));
                trainingprogrambyyq_object.put("text", trainingprogrambyyq.getFname());
                trainingprogrambyyq_Array.add(trainingprogrambyyq_object);
            }
            // 返回值

            object.put("list", trainingprogrambyyq_Array);
            object.put("result", "success");
            object.put("results", trainingprogrambyyq_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取毕业要求信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytrainingprogrambyyqInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytrainingprogrambyyqInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询毕业要求信息
            TTrainingProgramByyq trainingprogrambyyq = tTrainingProgramByyqService.findById(key);
            JSONObject trainingprogrambyyq_object = new JSONObject();
            trainingprogrambyyq_object.put("key", ParamTools.getEnParam(trainingprogrambyyq.getFkeyid().toString()));
            trainingprogrambyyq_object.put("FTMID", ParamTools.getEnParam(trainingprogrambyyq.getFtmid().toString()));
            trainingprogrambyyq_object.put("FTPID", ParamTools.getEnParam(trainingprogrambyyq.getFtpid().toString()));
            trainingprogrambyyq_object.put("FName", trainingprogrambyyq.getFname() == null ? "" : trainingprogrambyyq.getFname());
            trainingprogrambyyq_object.put("FNote", trainingprogrambyyq.getFnote() == null ? "" : trainingprogrambyyq.getFnote());
            trainingprogrambyyq_object.put("FCID", trainingprogrambyyq.getFcid());
            trainingprogrambyyq_object.put("FUID", trainingprogrambyyq.getFuid());
            trainingprogrambyyq_object.put("FCDATE", trainingprogrambyyq.getFcdate());
            trainingprogrambyyq_object.put("FUDATE", trainingprogrambyyq.getFudate());
            trainingprogrambyyq_object.put("FState", trainingprogrambyyq.getFstate());
            // 返回值
            object.put("info", trainingprogrambyyq_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加毕业要求信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加毕业要求信息")
    @ResponseBody
    @RequestMapping(value = "/addtrainingprogrambyyq", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addtrainingprogrambyyq(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);

        String Fname = jsonParam.getString("FName");
        String FNote = jsonParam.getString("FNote");
        String FTMID = jsonParam.getString("FTMID");
        String FTPID = jsonParam.getString("FTPID");
        try {
            FTMID = FTMID == null ? "0" : ParamTools.getdeParam(FTMID);
            FTPID = FTPID == null ? "0" : ParamTools.getdeParam(FTPID);

            TTrainingProgramByyqExample tTrainingProgramByyqExample = new TTrainingProgramByyqExample();
            tTrainingProgramByyqExample.createCriteria().andFtmidEqualTo(Long.parseLong(FTMID)).andFtpidEqualTo(Long.parseLong(FTPID));
            tTrainingProgramByyqExample.setOrderByClause("FOrder desc ");
            int forder =1;

            List<TTrainingProgramByyq> byyqList = tTrainingProgramByyqService.findByExample(tTrainingProgramByyqExample);
            if(byyqList.size() > 0){
                forder = byyqList.get(0).getForder() + 1 ;
            }


            TTrainingProgramByyqExample trainingprogrambyyqExample = new TTrainingProgramByyqExample();
            TTrainingProgramByyqExample.Criteria criteria = trainingprogrambyyqExample.createCriteria();
            criteria.andFnameEqualTo(Fname);
            criteria.andFtmidEqualTo(Long.parseLong(FTMID));
            criteria.andFtpidEqualTo(Long.parseLong(FTPID));
            List<TTrainingProgramByyq> programList = tTrainingProgramByyqService.findByExample(trainingprogrambyyqExample);



            if (programList.size() == 0) {
                String CookiesLogintrainingprogrambyyqID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogintrainingprogrambyyqID != null && !CookiesLogintrainingprogrambyyqID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogintrainingprogrambyyqID);
                }
                // 新建毕业要求信息
                TTrainingProgramByyq trainingprogrambyyq = new TTrainingProgramByyq();
                trainingprogrambyyq.setFname(Fname);
                trainingprogrambyyq.setFnote(FNote);
                trainingprogrambyyq.setForder(forder);
                trainingprogrambyyq.setFtmid(Long.parseLong(FTMID));
                trainingprogrambyyq.setFtpid(Long.parseLong(FTPID));
                trainingprogrambyyq.setFcid(Long.parseLong(uid));
                trainingprogrambyyq.setFcdate(new Date());
                tTrainingProgramByyqService.save(trainingprogrambyyq);
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
     * 修改毕业要求信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改毕业要求信息")
    @ResponseBody
    @RequestMapping(value = "/updatetrainingprogrambyyq", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatetrainingprogrambyyq(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String Fname = jsonParam.getString("FName");
        String FTMID = jsonParam.getString("FTMID");
        String FTPID = jsonParam.getString("FTPID");
        String FNote = jsonParam.getString("FNote");
        try {
            FTMID = FTMID == null ? "0" : ParamTools.getdeParam(FTMID);
            FTPID = FTPID == null ? "0" : ParamTools.getdeParam(FTPID);
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);

            TTrainingProgramByyqExample trainingprogrambyyqExample = new TTrainingProgramByyqExample();
            TTrainingProgramByyqExample.Criteria criteria = trainingprogrambyyqExample.createCriteria();
            criteria.andFnameEqualTo(Fname);
            criteria.andFkeyidNotEqualTo(key);
            criteria.andFtmidEqualTo(Long.parseLong(FTMID));
            criteria.andFtpidEqualTo(Long.parseLong(FTPID));
            List<TTrainingProgramByyq> programList = tTrainingProgramByyqService.findByExample(trainingprogrambyyqExample);
            if (programList.size() == 0) {
                String CookiesLogintrainingprogrambyyqID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogintrainingprogrambyyqID != null && !CookiesLogintrainingprogrambyyqID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogintrainingprogrambyyqID);
                }
                // 更新毕业要求信息
                TTrainingProgramByyq trainingprogrambyyq = new TTrainingProgramByyq();
                trainingprogrambyyq.setFkeyid(key);
                trainingprogrambyyq.setFname(Fname);
                trainingprogrambyyq.setFnote(FNote);
                trainingprogrambyyq.setFuid(Long.parseLong(uid));
                trainingprogrambyyq.setFudate(new Date());
                tTrainingProgramByyqService.update(trainingprogrambyyq);
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
     * 删除毕业要求信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除毕业要求信息")
    @ResponseBody
    @RequestMapping(value = "/deltrainingprogrambyyq", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deltrainingprogrambyyq(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLogintrainingprogrambyyqID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintrainingprogrambyyqID != null && !CookiesLogintrainingprogrambyyqID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));

            tTrainingProgramByyqService.deleteById(Long.parseLong(id));
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
     * 变更毕业要求信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/statetrainingprogrambyyq", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String statetrainingprogrambyyq(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLogintrainingprogrambyyqID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintrainingprogrambyyqID != null && !CookiesLogintrainingprogrambyyqID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TTrainingProgramByyq trainingprogrambyyq = new TTrainingProgramByyq();
            trainingprogrambyyq.setFkeyid(Long.parseLong(id));
            trainingprogrambyyq.setFuid(Long.parseLong(uid));
            trainingprogrambyyq.setFudate(new Date());
            trainingprogrambyyq.setFstate(Integer.valueOf(state));
            tTrainingProgramByyqService.update(trainingprogrambyyq);
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