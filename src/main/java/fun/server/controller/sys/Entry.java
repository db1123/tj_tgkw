package fun.server.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import fun.config.intercepors.actionlog.LogOperation;
import fun.config.intercepors.actionlog.LogOperationSupplier;
import fun.server.model.*;
import fun.server.service.*;
import fun.tools.ParamTools;
import fun.tools.RedisTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户访问系统 相关业务处理
 */
@RestController
@RequestMapping("/s/entry")
public class Entry {

    private TUserService userService;
    private RedisTools redisTools;
    private final TPowerRoleMenuOptionService powerRoleMenuOptionService;
    private final TPowerRoleUserService powerRoleUserService;

    private final TDeptService deptService;
    private final TPostService postService;
    private final TLogActionService logActionService;

    @Autowired
    public Entry(
            RedisTools redisTools,
            TUserService userService,
            TPowerRoleMenuOptionService powerRoleMenuOptionService,
            TPowerRoleUserService powerRoleUserService,

            TDeptService deptService,
            TPostService postService, TLogActionService logActionService) {
        this.redisTools = redisTools;
        this.userService = userService;
        this.powerRoleMenuOptionService = powerRoleMenuOptionService;
        this.powerRoleUserService = powerRoleUserService;
        this.deptService = deptService;
        this.postService = postService;
        this.logActionService = logActionService;
    }

    /**
     * 对登录验证码进行加密处理
     *
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/oCode", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String oCode(HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String code = jsonParam.getString("code");
        try {
            object.put("result", "success");
            object.put("oCode", ParamTools.getEnParam("628" + code));
        } catch (Exception e) {
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 登录
     *
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String login(HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String FLogin = jsonParam.getString("FLogin");
        String FPass = jsonParam.getString("FPass");
        String code = jsonParam.getString("code");
        String oCode = jsonParam.getString("oCode");
        try {
            if (code.equals(ParamTools.getdeParam(oCode).substring(3))) {
                // 查询条件
                TUserExample UserExample = new TUserExample();
                UserExample.or()
                        .andFLoginEqualTo(FLogin)
                        .andFPassEqualTo(ParamTools.getEnParam(FPass))
                        .andFStateEqualTo(1);
                List<TUser> userList = userService.findByExample(UserExample);
                if (userList.size() > 0) {
                    // 用户模型
                    TUser user = userList.get(0);
                    // 用户唯一标识(加密的用户FKeyID)
                    String simulation_dmsnid = ParamTools.getEnParam(user.getfKeyId().toString());
                    // 存入redis, 将用户模型转换成gson字符串格式，格式说明:[SESSION:加密的用户FKeyID]
                    Gson gson = new Gson();
                    redisTools.set("SESSION:" + simulation_dmsnid, gson.toJson(user), Long.valueOf(24 * 60 * 60));
                    // 存入Cookie,只存加密的用户FKeyID
                    Cookie cookie = new Cookie("simulation_dmsnid", simulation_dmsnid);
                    cookie.setMaxAge(24 * 60 * 60);// 设置cookie的失效时间
                    cookie.setPath("/");// 设置cookie作用域在当前项目下
                    response.addCookie(cookie);// 写入cookie

                    try {
                        TLogAction logAction = new TLogAction();
                        logAction.setfUserId(user.getfKeyId());
                        logAction.setfUserName(user.getfName());
                        logAction.setfType(3);
                        logAction.setfPath("Entry/login");
                        logAction.setfUserType(1);
                        logAction.setfMemo("用户：" + user.getfName() + "登录系统");
                        logActionService.save(logAction);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    // 返回结果
                    object.put("simulation_dmsnid", simulation_dmsnid);
                    object.put("result", "success");
                } else {
                    object.put("result", "用户名或密码错误！");
                }
            } else {
               object.put("result", "error");
               object.put("error", "验证码异常");
            }
        } catch (Exception e) {
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }




    /**
     * 注销登录
     *
     * @param request
     * @param response
     */
    @LogOperation("用户注销登录")
    @ResponseBody
    @RequestMapping(value = "/loginOut", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String loginOut(HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        try {
            // 获取用户simulation_dmsnid
            String simulation_dmsnid = ParamTools.getCookiesLoginUserID(request);
            // 清除redis
            redisTools.del("SESSION:" + simulation_dmsnid);
            // 清空cookie
            Cookie cookie = new Cookie("simulation_dmsnid", null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
            // 返回结果
            object.put("result", "success");
        } catch (Exception e) {
            e.getStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }


    /**
     * 注销登录
     *
     * @param request
     * @param response
     */
    @LogOperationSupplier("供应商注销登录")
    @ResponseBody
    @RequestMapping(value = "/supplierloginOut", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String supplierloginOut(HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        try {
            // 获取用户simulation_dmsnid
            String simulation_dmsnid = ParamTools.getCookiesLoginSupplierUserID(request);
            // 清除redis
            redisTools.del("SESSION:" + simulation_dmsnid);
            // 清空cookie
            Cookie cookie = new Cookie("simulation_supplierdmsnid", null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
            // 返回结果
            object.put("result", "success");
        } catch (Exception e) {
            e.getStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取登录人信息
     *
     * @param request 客户端请求
     * @return 响应结果
     */
    @ResponseBody
    @RequestMapping(value = "/showUserInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String showUserInfo(HttpServletRequest request) {
        JSONObject object = new JSONObject();
        try {
            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
//            System.out.println(CookiesLoginUserID);
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginUserID);
            }
            // 获取数据库记录
            TUser user = null;
            try {
                user = userService.findById(Long.parseLong(uid));
            } catch (NumberFormatException e) {
                user = null;
            }
            // 返回值
            if (user != null) {
                JSONObject user_object = new JSONObject();
                user_object.put("key", ParamTools.getEnParam(user.getfKeyId().toString()));


                if (user.getfDept() == null) {
                    user_object.put("f_dept", "");
                    user_object.put("dept_name", "");
                } else {
                    user_object.put("f_dept", ParamTools.getEnParam(user.getfDept().toString()));
                    TDept tDept = deptService.findById(user.getfDept());
                    user_object.put("dept_name", tDept == null ? "" : tDept.getFname());
                }
                if (user.getfPost() == null) {
                    user_object.put("f_post", "");
                    user_object.put("post_name", "");
                } else {
                    user_object.put("f_post", ParamTools.getEnParam(user.getfPost().toString()));
                    TPost tPost = postService.findById(user.getfPost());
                    user_object.put("post_name", tPost == null ? "" : tPost.getFname());
                }
                user_object.put("f_userno", user.getfUserno() == null ? "" : user.getfUserno());
                user_object.put("f_name", user.getfName());
                user_object.put("f_login", user.getfLogin());
                user_object.put("f_name", user.getfName());
                user_object.put("f_tel", user.getfTel());
                user_object.put("f_email", user.getfEmail());
                user_object.put("f_note", user.getfNote());
                //用户密码
                user_object.put("f_pass", ParamTools.getdeParam(user.getfPass()));
                
                object.put("user", user_object);
                object.put("result", "success");
            }else{
                object.put("result", "error");
            }

        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 判定用户资格
     *
     * @param request 客户端请求
     * @return 响应结果
     */
    @ResponseBody
    @RequestMapping(value = "/judgeQualification", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String judgeQualification(HttpServletRequest request) {
        JSONObject object = new JSONObject();
        try {
            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginUserID);
            }
            if (!uid.equals("")) {
                Object UserJSON = redisTools.get("SESSION:" + CookiesLoginUserID);
                if (UserJSON == null || UserJSON.equals("")) {
                    // 无
                    object.put("result", "fail");
                } else {
                    // 判断是否为管理员
                    Gson gson = new Gson();
                    TUser user = gson.fromJson(UserJSON.toString(), TUser.class);
                    if (user.getfIsAdmin() == 1) {
                        object.put("result", "success");
                    } else {
                        // 获取请求参数
                        JSONObject jsonParam = ParamTools.getParameters(request);
                        String MenuID = ParamTools.getdeParam(jsonParam.getString("MenuID"));
                        // 获取用户角色信息
                        List<Long> roleList = new ArrayList<>();
                        TPowerRoleUserExample powerRoleUserExample = new TPowerRoleUserExample();
                        powerRoleUserExample.or().andFUserIdEqualTo(Long.parseLong(uid));
                        List<TPowerRoleUser> powerRoleUserList = powerRoleUserService.findByExample(powerRoleUserExample);
                        for (TPowerRoleUser powerRoleUser : powerRoleUserList) {
                            roleList.add(powerRoleUser.getfRoleId());
                        }
                        // 判断是否拥有权限
                        TPowerRoleMenuOptionExample powerRoleMenuOptionExample = new TPowerRoleMenuOptionExample();
                        powerRoleMenuOptionExample.or().andFRoleIdIn(roleList).andFMenuOptionIdEqualTo(Long.parseLong(MenuID));
                        List<TPowerRoleMenuOption> powerRoleMenuOptionList = powerRoleMenuOptionService.findByExample(powerRoleMenuOptionExample);
                        if (powerRoleMenuOptionList.size() > 0) {
                            // 有权限
                            object.put("result", "success");
                        } else {
                            // 无
                            object.put("result", "fail");
                        }
                    }
                }
            } else {
                // 无
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
     * 外部登录
     *
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/singleSignOn", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String singleSignOn(HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String login = jsonParam.getString("login");
        String pass = jsonParam.getString("pass");
        try {
            // 查询条件
            TUserExample UserExample = new TUserExample();
            UserExample.or()
                    .andFLoginEqualTo(login)
                    .andFPassEqualTo(ParamTools.getEnParam(pass))
                    .andFStateEqualTo(1);
            List<TUser> userList = userService.findByExample(UserExample);
            if (userList.size() > 0) {
                // 用户模型
                TUser user = userList.get(0);
                // 用户唯一标识(加密的用户FKeyID)
                String simulation_dmsnid = ParamTools.getEnParam(user.getfKeyId().toString());
                // 存入redis, 将用户模型转换成gson字符串格式，格式说明:[SESSION:加密的用户FKeyID]
                Gson gson = new Gson();
                redisTools.set("SESSION:" + simulation_dmsnid, gson.toJson(user), Long.valueOf(24 * 60 * 60));
                // 存入Cookie,只存加密的用户FKeyID
                Cookie cookie = new Cookie("simulation_dmsnid", simulation_dmsnid);
                cookie.setMaxAge(24 * 60 * 60);// 设置cookie的失效时间
                cookie.setPath("/");// 设置cookie作用域在当前项目下
                response.addCookie(cookie);// 写入cookie
                // 返回结果
                object.put("FUserID", ParamTools.getEnParam(userList.get(0).getfKeyId().toString()));
                object.put("simulation_dmsnid", simulation_dmsnid);
                object.put("result", "success");
            } else {
                object.put("result", "error");
                object.put("error", "用户名或密码错误！");
            }
        } catch (Exception e) {
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 解密
     *
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/jiemi", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String jiemi(HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String accessInfo = jsonParam.getString("accessInfo");
        try {
            object.put("accessInfoss", ParamTools.getdeParam(accessInfo));
            object.put("result", "success");
        } catch (Exception e) {
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }
}
