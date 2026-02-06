package fun.server.controller.trainingprogram;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.TTrainingProgramQt;
import fun.server.model.TTrainingProgramQtExample;
import fun.server.service.TTrainingProgramQtService;
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
 * 其他管理 相关业务处理
 */
@RestController
@RequestMapping("/s/trainingprogramqt")
public class TrainingProgramQT {

    private final TTrainingProgramQtService tTrainingProgramQtService;

    public TrainingProgramQT(TTrainingProgramQtService tTrainingProgramQtService) {
        this.tTrainingProgramQtService = tTrainingProgramQtService;
    }


    /**
     * 获取其他信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytrainingprogramqt", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytrainingprogramqt(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("name");
        String FTPID = jsonParam.getString("FTPID");
        Integer types = jsonParam.getInteger("types");
        Integer dataall = jsonParam.getInteger("dataall");
        try {
            // 获取数据库记录
            JSONArray trainingprogramqt_Array = new JSONArray();
            // 查询条件
            TTrainingProgramQtExample TTrainingProgramQtExample = new TTrainingProgramQtExample();
            TTrainingProgramQtExample.Criteria criteria = TTrainingProgramQtExample.createCriteria();
            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");
            }
            FTPID = FTPID == null ? "0":ParamTools.getdeParam(FTPID);
            criteria.andFtypeEqualTo(types);
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
                TTrainingProgramQtExample.setOrderByClause(orderSql.substring(1));
            } else {
                TTrainingProgramQtExample.setOrderByClause("FCDATE desc");
            }
            PageInfo<TTrainingProgramQt> trainingprogramqtPageInfo = tTrainingProgramQtService.findByExampleMapper(TTrainingProgramQtExample, (page - 1) * results, results);
            List<TTrainingProgramQt> trainingprogramqt_list = trainingprogramqtPageInfo.getList();
            for (TTrainingProgramQt trainingprogramqt : trainingprogramqt_list) {
                JSONObject trainingprogramqt_object = new JSONObject();
                trainingprogramqt_object.put("key", ParamTools.getEnParam(trainingprogramqt.getFkeyid().toString()));
                trainingprogramqt_object.put("FTMID", ParamTools.getEnParam(trainingprogramqt.getFtmid().toString()));
                trainingprogramqt_object.put("FTPID", ParamTools.getEnParam(trainingprogramqt.getFtpid().toString()));
                if (dataall == 1) {
                    trainingprogramqt_object.put("FName", trainingprogramqt.getFname() == null ? "" : trainingprogramqt.getFname());
                    trainingprogramqt_object.put("FDW", trainingprogramqt.getFdw() == null ? "" : trainingprogramqt.getFdw());
                    trainingprogramqt_object.put("FJS", trainingprogramqt.getFjs() == null ? "" : trainingprogramqt.getFjs());
                    trainingprogramqt_object.put("FType", trainingprogramqt.getFtype());

                    trainingprogramqt_object.put("FCID", trainingprogramqt.getFcid());
                    trainingprogramqt_object.put("FUID", trainingprogramqt.getFuid());
                    trainingprogramqt_object.put("FCDATE", trainingprogramqt.getFcdate());
                    trainingprogramqt_object.put("FUDATE", trainingprogramqt.getFudate());
                } else {
                    trainingprogramqt_object.put("FName", "*****");
                    trainingprogramqt_object.put("FDW", "*****");
                    trainingprogramqt_object.put("FJS", "*****");
                    trainingprogramqt_object.put("FType", "*****");
                    trainingprogramqt_object.put("FCID", "*****");
                    trainingprogramqt_object.put("FUID", "*****");
                    trainingprogramqt_object.put("FCDATE", "*****");
                    trainingprogramqt_object.put("FUDATE", "*****");
                }
                trainingprogramqt_object.put("FOrder", trainingprogramqt.getForder());
                trainingprogramqt_object.put("FState", trainingprogramqt.getFstate());
                trainingprogramqt_Array.add(trainingprogramqt_object);
            }
            // 返回值
            object.put("list", trainingprogramqt_Array);
            object.put("total", trainingprogramqtPageInfo.getTotal()); // 总行数
            object.put("page", trainingprogramqtPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取其他信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatatrainingprogramqtSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatatrainingprogramqtSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray trainingprogramqt_Array = new JSONArray();
            TTrainingProgramQtExample trainingprogramqtExample = new TTrainingProgramQtExample();
            TTrainingProgramQtExample.Criteria criteria = trainingprogramqtExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            trainingprogramqtExample.setOrderByClause("FName asc");
            List<TTrainingProgramQt> trainingprogramqt_list = tTrainingProgramQtService.findByExample(trainingprogramqtExample);
            for (TTrainingProgramQt trainingprogramqt : trainingprogramqt_list) {
                JSONObject trainingprogramqt_object = new JSONObject();
                trainingprogramqt_object.put("id", ParamTools.getEnParam(trainingprogramqt.getFkeyid().toString()));
                trainingprogramqt_object.put("text", trainingprogramqt.getFname());
                trainingprogramqt_Array.add(trainingprogramqt_object);
            }
            // 返回值

            object.put("list", trainingprogramqt_Array);
            object.put("result", "success");
            object.put("results", trainingprogramqt_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取其他信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querytrainingprogramqtInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querytrainingprogramqtInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询其他信息
            TTrainingProgramQt trainingprogramqt = tTrainingProgramQtService.findById(key);
            JSONObject trainingprogramqt_object = new JSONObject();
            trainingprogramqt_object.put("key", ParamTools.getEnParam(trainingprogramqt.getFkeyid().toString()));
            trainingprogramqt_object.put("FTMID", ParamTools.getEnParam(trainingprogramqt.getFtmid().toString()));
            trainingprogramqt_object.put("FTPID", ParamTools.getEnParam(trainingprogramqt.getFtpid().toString()));
            trainingprogramqt_object.put("FName", trainingprogramqt.getFname() == null ? "" : trainingprogramqt.getFname());
            trainingprogramqt_object.put("FDW", trainingprogramqt.getFdw() == null ? "" : trainingprogramqt.getFdw());
            trainingprogramqt_object.put("FJS", trainingprogramqt.getFjs() == null ? "" : trainingprogramqt.getFjs());
            trainingprogramqt_object.put("FType", trainingprogramqt.getFtype());
            trainingprogramqt_object.put("FOrder", trainingprogramqt.getForder());
            trainingprogramqt_object.put("FCID", trainingprogramqt.getFcid());
            trainingprogramqt_object.put("FUID", trainingprogramqt.getFuid());
            trainingprogramqt_object.put("FCDATE", trainingprogramqt.getFcdate());
            trainingprogramqt_object.put("FUDATE", trainingprogramqt.getFudate());
            trainingprogramqt_object.put("FState", trainingprogramqt.getFstate());
            // 返回值
            object.put("info", trainingprogramqt_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加其他信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加其他信息")
    @ResponseBody
    @RequestMapping(value = "/addtrainingprogramqt", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addtrainingprogramqt(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);

        String Fname = jsonParam.getString("FName");
        String FDW = jsonParam.getString("FDW");
        String FJS = jsonParam.getString("FJS");
        int FType = jsonParam.getInteger("FType");
        String FTMID = jsonParam.getString("FTMID");
        String FTPID = jsonParam.getString("FTPID");
        try {
            FTMID = FTMID == null ? "0" : ParamTools.getdeParam(FTMID);
            FTPID = FTPID == null ? "0" : ParamTools.getdeParam(FTPID);

            TTrainingProgramQtExample tTrainingProgramQtExample = new TTrainingProgramQtExample();
            tTrainingProgramQtExample.createCriteria().andFtmidEqualTo(Long.parseLong(FTMID)).andFtpidEqualTo(Long.parseLong(FTPID));
            List<TTrainingProgramQt> qtList = tTrainingProgramQtService.findByExample(tTrainingProgramQtExample);
            int forder= 1;
            if(qtList.size()>0){
                forder = qtList.get(0).getForder() + 1;
            }

            TTrainingProgramQtExample trainingprogramqtExample = new TTrainingProgramQtExample();
            TTrainingProgramQtExample.Criteria criteria = trainingprogramqtExample.createCriteria();
            criteria.andFnameEqualTo(Fname);
            criteria.andFtypeEqualTo(FType);
            criteria.andFtmidEqualTo(Long.parseLong(FTMID));
            criteria.andFtpidEqualTo(Long.parseLong(FTPID));
            List<TTrainingProgramQt> programList = tTrainingProgramQtService.findByExample(trainingprogramqtExample);
            if (programList.size() == 0) {
                String CookiesLogintrainingprogramqtID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogintrainingprogramqtID != null && !CookiesLogintrainingprogramqtID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogintrainingprogramqtID);
                }
                // 新建其他信息
                TTrainingProgramQt trainingprogramqt = new TTrainingProgramQt();
                trainingprogramqt.setFname(Fname);
                trainingprogramqt.setFdw(FDW);
                trainingprogramqt.setFtype(FType);
                trainingprogramqt.setFjs(FJS);
                trainingprogramqt.setForder(forder);
                trainingprogramqt.setFtmid(Long.parseLong(FTMID));
                trainingprogramqt.setFtpid(Long.parseLong(FTPID));
                trainingprogramqt.setFcid(Long.parseLong(uid));
                trainingprogramqt.setFcdate(new Date());
                tTrainingProgramQtService.save(trainingprogramqt);
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
     * 修改其他信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改其他信息")
    @ResponseBody
    @RequestMapping(value = "/updatetrainingprogramqt", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatetrainingprogramqt(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String Fname = jsonParam.getString("FName");
        String FDW = jsonParam.getString("FDW");
        String FJS = jsonParam.getString("FJS");
        String FTMID = jsonParam.getString("FTMID");
        String FTPID = jsonParam.getString("FTPID");
        int FType = jsonParam.getInteger("FType");
        try {
            FTMID = FTMID == null ? "0" : ParamTools.getdeParam(FTMID);
            FTPID = FTPID == null ? "0" : ParamTools.getdeParam(FTPID);
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);

            TTrainingProgramQtExample trainingprogramqtExample = new TTrainingProgramQtExample();
            TTrainingProgramQtExample.Criteria criteria = trainingprogramqtExample.createCriteria();
            criteria.andFnameEqualTo(Fname);
            criteria.andFkeyidNotEqualTo(key);
            criteria.andFtypeEqualTo(FType);
            criteria.andFtmidEqualTo(Long.parseLong(FTMID));
            criteria.andFtpidEqualTo(Long.parseLong(FTPID));
            List<TTrainingProgramQt> programList = tTrainingProgramQtService.findByExample(trainingprogramqtExample);
            if (programList.size() == 0) {
                String CookiesLogintrainingprogramqtID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLogintrainingprogramqtID != null && !CookiesLogintrainingprogramqtID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLogintrainingprogramqtID);
                }
                // 更新其他信息
                TTrainingProgramQt trainingprogramqt = new TTrainingProgramQt();
                trainingprogramqt.setFkeyid(key);
                trainingprogramqt.setFname(Fname);
                trainingprogramqt.setFdw(FDW);
//                trainingprogramqt.setFtype(FType);
                trainingprogramqt.setFjs(FJS);
                trainingprogramqt.setFuid(Long.parseLong(uid));
                trainingprogramqt.setFudate(new Date());
                tTrainingProgramQtService.update(trainingprogramqt);
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
     * 删除其他信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除其他信息")
    @ResponseBody
    @RequestMapping(value = "/deltrainingprogramqt", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String deltrainingprogramqt(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLogintrainingprogramqtID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintrainingprogramqtID != null && !CookiesLogintrainingprogramqtID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));

            tTrainingProgramQtService.deleteById(Long.parseLong(id));
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
     * 变更其他信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/statetrainingprogramqt", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String statetrainingprogramqt(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLogintrainingprogramqtID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLogintrainingprogramqtID != null && !CookiesLogintrainingprogramqtID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TTrainingProgramQt trainingprogramqt = new TTrainingProgramQt();
            trainingprogramqt.setFkeyid(Long.parseLong(id));
            trainingprogramqt.setFuid(Long.parseLong(uid));
            trainingprogramqt.setFudate(new Date());
            trainingprogramqt.setFstate(Integer.valueOf(state));
            tTrainingProgramQtService.update(trainingprogramqt);
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