package fun.server.controller.myproject;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.server.model.TProjectLog;
import fun.server.model.TProjectLogExample;
import fun.server.model.TUser;
import fun.server.service.TProjectLogService;
import fun.server.service.TUserService;
import fun.tools.ParamTools;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 日志管理 相关业务处理
 */

@RestController
@RequestMapping("/s/projectlog")
public class ProjectLog {
    
    private final TProjectLogService  tProjectLogService;
    private final TUserService tUserService;
    public ProjectLog(TProjectLogService tProjectLogService, TUserService tUserService) {
        this.tProjectLogService = tProjectLogService;
        this.tUserService = tUserService;
    }


    /**
     * 获取日志信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryprojectlog", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryprojectlog( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String name = jsonParam.getString("name");
        String FProjectID = jsonParam.getString("FProjectID");
        try {
            FProjectID = FProjectID == null ? "0": ParamTools.getdeParam(FProjectID);
            // 获取数据库记录
            JSONArray projectlog_Array = new JSONArray();
            // 查询条件
            TProjectLogExample TProjectLogExample = new TProjectLogExample();
            TProjectLogExample.Criteria criteria = TProjectLogExample.createCriteria();

            if (name != null && !name.equals("")) {
                criteria.andFnoteLike("%" + name + "%");
                 
            }
            criteria.andFprojectidEqualTo(Long.parseLong(FProjectID));
            TProjectLogExample.setOrderByClause("FCDATE desc");
            PageInfo<TProjectLog> projectlogPageInfo = tProjectLogService.findByExampleMapper(TProjectLogExample, (page-1)*results, results);
            List<TProjectLog> projectlog_list = projectlogPageInfo.getList();
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int iii=1;
            for (TProjectLog projectlog: projectlog_list) {
                JSONObject projectlog_object = new JSONObject();
                projectlog_object.put("no",iii);
                projectlog_object.put("key", ParamTools.getEnParam(projectlog.getFkeyid().toString()));
                projectlog_object.put("FProjectID",projectlog.getFprojectid() == null? "": ParamTools.getEnParam(projectlog.getFprojectid().toString()));
                projectlog_object.put("FFunction", projectlog.getFfunction() == null? "": projectlog.getFfunction());
                projectlog_object.put("FOperation", projectlog.getFoperation() == null? "": projectlog.getFoperation());
                projectlog_object.put("FLink",projectlog.getFlink() == null? "": projectlog.getFlink());
                projectlog_object.put("FNote",projectlog.getFnote() == null? "": projectlog.getFnote());
                TUser tUser = tUserService.findById(projectlog.getFcid());
                projectlog_object.put("FUNo", tUser == null ? "":tUser.getfUserno());
                projectlog_object.put("FUName", tUser == null ? "":tUser.getfName());
                projectlog_object.put("FCID", projectlog.getFcid());
                projectlog_object.put("FCDATE", projectlog.getFcdate() == null ? "": sdf.format(projectlog.getFcdate()));

                projectlog_Array.add(projectlog_object);
                iii++;
            }
            // 返回值
            object.put("list", projectlog_Array);
            object.put("total", projectlogPageInfo.getTotal()); // 总行数
            object.put("page", projectlogPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取日志信息(下拉列表)
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDataprojectlogSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDataprojectlogSelect( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray projectlog_Array = new JSONArray();
            TProjectLogExample projectlogExample = new TProjectLogExample();
            TProjectLogExample.Criteria criteria = projectlogExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnoteLike("%" + search + "%");
            }

            projectlogExample.setOrderByClause("fname asc");
            List<TProjectLog> projectlog_list = tProjectLogService.findByExample(projectlogExample);
            for (TProjectLog projectlog: projectlog_list) {
                JSONObject projectlog_object = new JSONObject();
                projectlog_object.put("id", ParamTools.getEnParam(projectlog.getFkeyid().toString()));
                projectlog_object.put("text", projectlog.getFnote());
                projectlog_Array.add(projectlog_object);
            }
            // 返回值

            object.put("list", projectlog_Array);
            object.put("result", "success");
            object.put("results", projectlog_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取日志信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryprojectlogInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryprojectlogInfo( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询日志信息
            TProjectLog projectlog = tProjectLogService.findById(key);
            JSONObject projectlog_object = new JSONObject();
            projectlog_object.put("key", ParamTools.getEnParam(projectlog.getFkeyid().toString()));
            projectlog_object.put("FProjectID",projectlog.getFprojectid() == null? "": ParamTools.getEnParam(projectlog.getFprojectid().toString()));
            projectlog_object.put("FFunction", projectlog.getFfunction() == null? "": projectlog.getFfunction());
            projectlog_object.put("FOperation", projectlog.getFoperation() == null? "": projectlog.getFoperation());
            projectlog_object.put("FLink",projectlog.getFlink() == null? "": projectlog.getFlink());
            projectlog_object.put("FNote",projectlog.getFnote() == null? "": projectlog.getFnote());
            projectlog_object.put("FCID", projectlog.getFcid());
            projectlog_object.put("FCDATE", projectlog.getFcdate());
            // 返回值
            object.put("info", projectlog_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    public void addprojectlog(String fnote,String FProjectID,String FFunction,String FOperation,String FLink,String uid){
        try {
            TProjectLog tTaskLog = new TProjectLog();
            tTaskLog.setFcdate(new Date());
            tTaskLog.setFcid(Long.parseLong(uid));
            tTaskLog.setFfunction(FFunction);
            tTaskLog.setFlink(FLink);
            tTaskLog.setFnote(fnote);
            tTaskLog.setFoperation(FOperation);
            tTaskLog.setFprojectid(Long.parseLong(FProjectID));
            tProjectLogService.save(tTaskLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}