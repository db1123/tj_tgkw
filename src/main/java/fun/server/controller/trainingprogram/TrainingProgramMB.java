package fun.server.controller.trainingprogram;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.TTrainingProgramMb;
import fun.server.model.TTrainingProgramMbExample;
import fun.server.service.TTrainingProgramMbService;
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
 * 培养目标管理 相关业务处理
 */
@RestController
@RequestMapping("/s/trainingprogrammb")
public class TrainingProgramMB {

    private final TTrainingProgramMbService tTrainingProgramMbService;

    public TrainingProgramMB(TTrainingProgramMbService tTrainingProgramMbService) {
        this.tTrainingProgramMbService = tTrainingProgramMbService;
    }


    /**
     * 获取培养目标信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytrainingprogrammb", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytrainingprogrammb(HttpServletRequest request)
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
            JSONArray trainingprogrammb_Array = new JSONArray();
            // 查询条件
            TTrainingProgramMbExample TTrainingProgramMbExample = new TTrainingProgramMbExample();
            TTrainingProgramMbExample.Criteria criteria = TTrainingProgramMbExample.createCriteria();
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
                TTrainingProgramMbExample.setOrderByClause(orderSql.substring(1));
            } else {
                TTrainingProgramMbExample.setOrderByClause("FCDATE desc");
            }
            PageInfo<TTrainingProgramMb> trainingprogrammbPageInfo = tTrainingProgramMbService.findByExampleMapper(TTrainingProgramMbExample, (page - 1) * results, results);
            List<TTrainingProgramMb> trainingprogrammb_list = trainingprogrammbPageInfo.getList();
            for (TTrainingProgramMb trainingprogrammb : trainingprogrammb_list) {
                JSONObject trainingprogrammb_object = new JSONObject();
                trainingprogrammb_object.put("key", ParamTools.getEnParam(trainingprogrammb.getFkeyid().toString()));
                trainingprogrammb_object.put("FTMID", ParamTools.getEnParam(trainingprogrammb.getFtmid().toString()));
                trainingprogrammb_object.put("FTPID", ParamTools.getEnParam(trainingprogrammb.getFtpid().toString()));
                if (dataall == 1) {
                    trainingprogrammb_object.put("FName", trainingprogrammb.getFname() == null ? "" : trainingprogrammb.getFname());
                    trainingprogrammb_object.put("FNum", trainingprogrammb.getFnum());
                    trainingprogrammb_object.put("FCID", trainingprogrammb.getFcid());
                    trainingprogrammb_object.put("FUID", trainingprogrammb.getFuid());
                    trainingprogrammb_object.put("FCDATE", trainingprogrammb.getFcdate());
                    trainingprogrammb_object.put("FUDATE", trainingprogrammb.getFudate());
                } else {
                    trainingprogrammb_object.put("FName", "*****");
                    trainingprogrammb_object.put("FCID", "*****");
                    trainingprogrammb_object.put("FUID", "*****");
                    trainingprogrammb_object.put("FCDATE", "*****");
                    trainingprogrammb_object.put("FUDATE", "*****");
                }

                trainingprogrammb_object.put("FState", trainingprogrammb.getFstate());
                trainingprogrammb_Array.add(trainingprogrammb_object);
            }
            // 返回值
            object.put("list", trainingprogrammb_Array);
            object.put("total", trainingprogrammbPageInfo.getTotal()); // 总行数
            object.put("page", trainingprogrammbPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取培养目标信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatatrainingprogrammbSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatatrainingprogrammbSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray trainingprogrammb_Array = new JSONArray();
            TTrainingProgramMbExample trainingprogrammbExample = new TTrainingProgramMbExample();
            TTrainingProgramMbExample.Criteria criteria = trainingprogrammbExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            trainingprogrammbExample.setOrderByClause("FName asc");
            List<TTrainingProgramMb> trainingprogrammb_list = tTrainingProgramMbService.findByExample(trainingprogrammbExample);
            for (TTrainingProgramMb trainingprogrammb : trainingprogrammb_list) {
                JSONObject trainingprogrammb_object = new JSONObject();
                trainingprogrammb_object.put("id", ParamTools.getEnParam(trainingprogrammb.getFkeyid().toString()));
                trainingprogrammb_object.put("text", trainingprogrammb.getFname());
                trainingprogrammb_Array.add(trainingprogrammb_object);
            }
            // 返回值

            object.put("list", trainingprogrammb_Array);
            object.put("result", "success");
            object.put("results", trainingprogrammb_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取培养目标信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytrainingprogrammbInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytrainingprogrammbInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询培养目标信息
            TTrainingProgramMb trainingprogrammb = tTrainingProgramMbService.findById(key);
            JSONObject trainingprogrammb_object = new JSONObject();
            trainingprogrammb_object.put("key", ParamTools.getEnParam(trainingprogrammb.getFkeyid().toString()));
            trainingprogrammb_object.put("FTMID", ParamTools.getEnParam(trainingprogrammb.getFtmid().toString()));
            trainingprogrammb_object.put("FName", trainingprogrammb.getFname() == null ? "" : trainingprogrammb.getFname());
            trainingprogrammb_object.put("FNum", trainingprogrammb.getFnum());
            trainingprogrammb_object.put("FCID", trainingprogrammb.getFcid());
            trainingprogrammb_object.put("FUID", trainingprogrammb.getFuid());
            trainingprogrammb_object.put("FCDATE", trainingprogrammb.getFcdate());
            trainingprogrammb_object.put("FUDATE", trainingprogrammb.getFudate());
            trainingprogrammb_object.put("FState", trainingprogrammb.getFstate());
            // 返回值
            object.put("info", trainingprogrammb_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加培养目标信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加培养目标信息")
    @ResponseBody
    @RequestMapping(value = "/addtrainingprogrammb", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addtrainingprogrammb(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String Fname = jsonParam.getString("FName");
//        int FNum = jsonParam.getInteger("FNum");
        String FTMID = jsonParam.getString("FTMID");
        String FTPID = jsonParam.getString("FTPID");
        try {
            FTMID = FTMID == null ? "0" : ParamTools.getdeParam(FTMID);
            FTPID = FTPID == null ? "0" : ParamTools.getdeParam(FTPID);

            TTrainingProgramMbExample tTrainingProgramMbExample = new TTrainingProgramMbExample();
            tTrainingProgramMbExample.createCriteria().andFtpidEqualTo(Long.parseLong(FTPID)).andFtmidEqualTo(Long.parseLong(FTMID));
            tTrainingProgramMbExample.setOrderByClause("FNum desc ");
            int fum = 1;
            List<TTrainingProgramMb> mbList = tTrainingProgramMbService.findByExample(tTrainingProgramMbExample);

            if(mbList.size() > 0){
                fum = mbList.get(0).getFnum() + 1;
            }

            TTrainingProgramMbExample trainingprogrammbExample = new TTrainingProgramMbExample();
            TTrainingProgramMbExample.Criteria criteria = trainingprogrammbExample.createCriteria();
            criteria.andFnameEqualTo(Fname);
            criteria.andFtmidEqualTo(Long.parseLong(FTMID));
            criteria.andFtpidEqualTo(Long.parseLong(FTPID));
            List<TTrainingProgramMb> programList = tTrainingProgramMbService.findByExample(trainingprogrammbExample);
            if (programList.size() == 0) {
                String CookiesLogintrainingprogrammbID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogintrainingprogrammbID != null && !CookiesLogintrainingprogrammbID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogintrainingprogrammbID);
                }
                // 新建培养目标信息
                TTrainingProgramMb trainingprogrammb = new TTrainingProgramMb();
                trainingprogrammb.setFname(Fname);
                trainingprogrammb.setFnum(fum);
                trainingprogrammb.setFtmid(Long.parseLong(FTMID));
                trainingprogrammb.setFtpid(Long.parseLong(FTPID));
                trainingprogrammb.setFcid(Long.parseLong(uid));
                trainingprogrammb.setFcdate(new Date());
                tTrainingProgramMbService.save(trainingprogrammb);
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
     * 修改培养目标信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改培养目标信息")
    @ResponseBody
    @RequestMapping(value = "/updatetrainingprogrammb", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatetrainingprogrammb(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String Fname = jsonParam.getString("FName");
//        int FNum = jsonParam.getInteger("FNum");
        String FTMID = jsonParam.getString("FTMID");
        String FTPID = jsonParam.getString("FTPID");
        try {
            FTMID = FTMID == null ? "0" : ParamTools.getdeParam(FTMID);
            FTPID = FTPID == null ? "0" : ParamTools.getdeParam(FTPID);
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);

            TTrainingProgramMbExample trainingprogrammbExample = new TTrainingProgramMbExample();
            TTrainingProgramMbExample.Criteria criteria = trainingprogrammbExample.createCriteria();
            criteria.andFnameEqualTo(Fname);
            criteria.andFkeyidNotEqualTo(key);
            criteria.andFtmidEqualTo(Long.parseLong(FTMID));
            criteria.andFtpidEqualTo(Long.parseLong(FTPID));
            List<TTrainingProgramMb> programList = tTrainingProgramMbService.findByExample(trainingprogrammbExample);
            if (programList.size() == 0) {
                String CookiesLogintrainingprogrammbID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogintrainingprogrammbID != null && !CookiesLogintrainingprogrammbID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogintrainingprogrammbID);
                }
                // 更新培养目标信息
                TTrainingProgramMb trainingprogrammb = new TTrainingProgramMb();
                trainingprogrammb.setFkeyid(key);
                trainingprogrammb.setFname(Fname);
                trainingprogrammb.setFuid(Long.parseLong(uid));
                trainingprogrammb.setFudate(new Date());
                tTrainingProgramMbService.update(trainingprogrammb);
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
     * 删除培养目标信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除培养目标信息")
    @ResponseBody
    @RequestMapping(value = "/deltrainingprogrammb", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deltrainingprogrammb(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLogintrainingprogrammbID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintrainingprogrammbID != null && !CookiesLogintrainingprogrammbID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));

            tTrainingProgramMbService.deleteById(Long.parseLong(id));
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
     * 变更培养目标信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/statetrainingprogrammb", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String statetrainingprogrammb(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLogintrainingprogrammbID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintrainingprogrammbID != null && !CookiesLogintrainingprogrammbID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TTrainingProgramMb trainingprogrammb = new TTrainingProgramMb();
            trainingprogrammb.setFkeyid(Long.parseLong(id));
            trainingprogrammb.setFuid(Long.parseLong(uid));
            trainingprogrammb.setFudate(new Date());
            trainingprogrammb.setFstate(Integer.valueOf(state));
            tTrainingProgramMbService.update(trainingprogrammb);
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