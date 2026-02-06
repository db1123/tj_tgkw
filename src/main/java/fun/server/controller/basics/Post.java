package fun.server.controller.basics;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import fun.config.intercepors.actionlog.LogOperation;
import fun.server.model.*;
import fun.server.service.TPostService;
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
 * 职务管理 相关业务处理
 */
@RestController
@RequestMapping("/s/post")
public class Post {

    private final TPostService tPostService;
    private final TUserService tUserService;

    public Post(TPostService tPostService, TUserService tUserService) {
        this.tPostService = tPostService;
        this.tUserService = tUserService;
    }


    /**
     * 获取职务信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querypost", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querypost(HttpServletRequest request)
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
            JSONArray post_Array = new JSONArray();
            // 查询条件
            TPostExample TPostExample = new TPostExample();
            TPostExample.Criteria criteria = TPostExample.createCriteria();

            if (name != null && !name.equals("")) {
                criteria.andFnameLike("%" + name + "%");

            }
            TPostExample.setOrderByClause("FCDATE desc");
            PageInfo<TPost> postPageInfo = tPostService.findByExampleMapper(TPostExample, (page - 1) * results, results);
            List<TPost> post_list = postPageInfo.getList();

            for (TPost post : post_list) {
                JSONObject post_object = new JSONObject();
                post_object.put("key", ParamTools.getEnParam(post.getFkeyid().toString()));
                if(dataall == 1){
                    post_object.put("FName", post.getFname());
                    post_object.put("FCID", post.getFcid());
                    post_object.put("FUID", post.getFuid());
                    post_object.put("FCDATE", post.getFcdate());
                    post_object.put("FUDATE", post.getFudate());
                }else{
                    post_object.put("FName", "*****");
                    post_object.put("FCID", "*****");
                    post_object.put("FUID", "*****");
                    post_object.put("FCDATE", "*****");
                    post_object.put("FUDATE", "*****");
                }

                post_object.put("FState", post.getFstate());
                post_Array.add(post_object);
            }
            // 返回值
            object.put("list", post_Array);
            object.put("total", postPageInfo.getTotal()); // 总行数
            object.put("page", postPageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取职务信息(下拉列表)
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryDatapostSelect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDatapostSelect(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        String search = request.getParameter("search");
        try {
            // 获取数据库记录
            JSONArray post_Array = new JSONArray();
            TPostExample postExample = new TPostExample();
            TPostExample.Criteria criteria = postExample.createCriteria();
            if (search != null && !search.equals("")) {
                criteria.andFnameLike("%" + search + "%");
            }
            criteria.andFstateEqualTo(1);
            postExample.setOrderByClause("fname asc");
            List<TPost> post_list = tPostService.findByExample(postExample);
            for (TPost post : post_list) {
                JSONObject post_object = new JSONObject();
                post_object.put("id", ParamTools.getEnParam(post.getFkeyid().toString()));
                post_object.put("text", post.getFname());
                post_Array.add(post_object);
            }
            // 返回值

            object.put("list", post_Array);
            object.put("result", "success");
            object.put("results", post_Array);
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 根据ID获取职务信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/querypostInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String querypostInfo(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 查询职务信息
            TPost post = tPostService.findById(key);
            JSONObject post_object = new JSONObject();
            post_object.put("key", ParamTools.getEnParam(post.getFkeyid().toString()));
            post_object.put("FName", post.getFname());
            post_object.put("FCID", post.getFcid());
            post_object.put("FUID", post.getFuid());
            post_object.put("FCDATE", post.getFcdate());
            post_object.put("FUDATE", post.getFudate());
            post_object.put("FState", post.getFstate());
            // 返回值
            object.put("info", post_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加职务信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("添加职务信息")
    @ResponseBody
    @RequestMapping(value = "/addpost", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addpost(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String Fname = jsonParam.getString("Fname");

        try {
            if (repeaTPost(0L, Fname, "1") == 0) {
                String CookiesLoginpostID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginpostID != null && !CookiesLoginpostID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginpostID);
                }
                // 新建职务信息
                TPost post = new TPost();
                post.setFname(Fname);
                post.setFcid(Long.parseLong(uid));
                post.setFcdate(new Date());
                tPostService.save(post);
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
     * 修改职务信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("修改职务信息")
    @ResponseBody
    @RequestMapping(value = "/updatepost", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatepost(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("FKeyID");
        String Fname = jsonParam.getString("Fname");

        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            if (repeaTPost(key, Fname, "2") == 0) {
                String CookiesLoginpostID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
                String uid = ""; // 当前登录用户ID
                if (CookiesLoginpostID != null && !CookiesLoginpostID.equals("")) {
                    uid = ParamTools.getdeParam(CookiesLoginpostID);
                }
                // 更新职务信息
                TPost post = new TPost();
                post.setFkeyid(key);
                post.setFname(Fname);
                post.setFuid(Long.parseLong(uid));
                post.setFudate(new Date());
                tPostService.update(post);
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
     * 删除职务信息
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @LogOperation("删除职务信息")
    @ResponseBody
    @RequestMapping(value = "/delpost", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delpost(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            String CookiesLoginpostID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginpostID != null && !CookiesLoginpostID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));

            TUserExample tUserExample = new TUserExample();
            tUserExample.createCriteria().andFPostEqualTo(Long.parseLong(id));

            List<TUser> userList = tUserService.findByExample(tUserExample);
            if (userList.size() > 0) {
                object.put("result", "error");
                object.put("error", "当前职务在用户信息中被使用，不能删除！");
            } else {
                tPostService.deleteById(Long.parseLong(id));
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
     * 变更职务信息状态
     *
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException                  输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/statepost", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String statepost(HttpServletRequest request)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLoginpostID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginpostID != null && !CookiesLoginpostID.equals("")) {
                uid = ParamTools.getdeParam(ParamTools.getCookiesLoginUserID(request));
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TPost post = new TPost();
            post.setFkeyid(Long.parseLong(id));
            post.setFuid(Long.parseLong(uid));
            post.setFudate(new Date());
            post.setFstate(Integer.valueOf(state));
            tPostService.update(post);
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
     * 验证职务是否存在
     */
    private int repeaTPost(Long id, String name, String ftype) {
        int code = 0;
        try {
            // 查询条件
            TPostExample postExample = new TPostExample();
            TPostExample.Criteria criteria = postExample.createCriteria();
            if (ftype.equals("2")) { // 修改
                if (id != null) {
//                    criteria.andFkeyidEqualTo((id));
                    criteria.andFkeyidNotEqualTo(id);
                }
                if (name != null && !name.equals("")) {
                    criteria.andFnameEqualTo(name);
                }
            } else { // 新增
                if (name != null && !name.equals("")) {
                    criteria.andFnameEqualTo(name);
                }
            }
            List<TPost> postList = tPostService.findByExample(postExample);
            if (postList.size() == 0) {
                code = 0;
            } else {
                code = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }


    //根据ID查询职务名称
    public String getName(Long id) {
        TPost byId = tPostService.findById(id);
        if (byId != null) {
            return byId.getFname();
        } else {
            return "";
        }

    }
}