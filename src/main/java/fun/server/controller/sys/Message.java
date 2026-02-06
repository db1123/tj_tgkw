package fun.server.controller.sys;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fun.server.model.TMessage;
import fun.server.model.TMessageExample;
import fun.server.model.TUser;
import fun.server.service.TMessageService;
import fun.server.service.TUserService;
import fun.tools.ParamTools;
import fun.tools.TimeTools;
import fun.websocket.MessageServer;

/**
 * 消息管理 相关业务处理
 */
@RestController
@RequestMapping("/s/message")
public class Message {

    @Resource
    private MessageServer messageServer;

    private final TMessageService messageService;
    private final TUserService userService;

    @Autowired
    public Message(TMessageService messageService, TUserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    /**
     * 获取消息信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryMessage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryMessage( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        int results = jsonParam.getInteger("results");
        int page = jsonParam.getInteger("page");
        JSONArray columns_JA = jsonParam.getJSONArray("columns");
        JSONArray order_JA = jsonParam.getJSONArray("order");
        String type = jsonParam.getString("type");
        String title = jsonParam.getString("title");
        try {
            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginUserID);
            }
            // 获取数据库记录
            JSONArray message_Array = new JSONArray();
            // 查询条件
            TMessageExample messageExample = new TMessageExample();
            TMessageExample.Criteria criteria = messageExample.createCriteria();
            if (type != null && !type.equals("") && !type.equals("-1")) {
                criteria.andFTypeEqualTo(Integer.parseInt(type));
            }
            if (title != null && !title.equals("")) {
                criteria.andFTitleLike("%" + title + "%");
            }
            criteria.andFToUserIdEqualTo(Long.parseLong(uid));
            // 排序
            String orderSql = ",f_c_date desc";
            for (Object order: order_JA) {
                JSONObject order_Object = (JSONObject)order;
                JSONObject column_Object = columns_JA.getJSONObject(order_Object.getInteger("column"));
                String colName = column_Object.getString("data");
                if (colName.equals("type_name")) {
                    colName = "f_type";
                }
                orderSql += "," + colName + " " + order_Object.getString("dir");
            }
            if (orderSql.length() > 0) {
                messageExample.setOrderByClause(orderSql.substring(1));
            } else {
                messageExample.setOrderByClause("f_c_date desc");
            }
            PageInfo<TMessage> messagePageInfo = messageService.findByExampleMapper(messageExample, (page-1)*results, results);
            List<TMessage> message_list = messagePageInfo.getList();
            for (TMessage message: message_list) {
                JSONObject message_object = new JSONObject();
                message_object.put("key", ParamTools.getEnParam(message.getfKeyId().toString()));
                message_object.put("f_type", message.getfType());
                message_object.put("f_title", message.getfTitle());
                message_object.put("f_note", message.getfNote());
                message_object.put("f_is_read", message.getfIsRead());
                TUser user = userService.findById(message.getfFromUserId());
                message_object.put("f_from_user_id", message.getfFromUserId());
                message_object.put("from_user_name", user == null ? "系统" : user.getfName());
                message_object.put("f_to_user_id", message.getfToUserId());
                message_object.put("f_state", message.getfState());
                message_object.put("f_c_date", message.getfCDate() == null ? "" : format.format(message.getfCDate()));
                message_Array.add(message_object);
            }
            // 返回值
            object.put("list", message_Array);
            object.put("total", messagePageInfo.getTotal()); // 总行数
            object.put("page", messagePageInfo.getPageNum()); // 当前页数
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 获取消息信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryMessageInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryMessageInfo( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 获取数据库记录
            TMessage message = messageService.findById(key);
            JSONObject message_object = new JSONObject();
            message_object.put("key", ParamTools.getEnParam(message.getfKeyId().toString()));
            message_object.put("f_type", message.getfType());
            message_object.put("f_title", message.getfTitle());
            message_object.put("f_note", message.getfNote());
            message_object.put("f_is_read", message.getfIsRead());
            message_object.put("f_from_user_id", message.getfFromUserId());
            message_object.put("f_to_user_id", message.getfToUserId());
            message_object.put("f_state", message.getfState());
            message_object.put("f_c_date", message.getfCDate() == null ? "" : format.format(message.getfCDate()));
            // 返回值
            object.put("info", message_object);
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 添加消息信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/addMessage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addMessage( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String f_type = jsonParam.getString("f_type");
        String f_title = jsonParam.getString("f_title");
        String f_note = jsonParam.getString("f_note");
        String f_from_user_id = jsonParam.getString("f_from_user_id");
        String f_to_user_id = jsonParam.getString("f_to_user_id");
        try {
            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginUserID);
            }
            TMessage message = new TMessage();
            message.setfCId(Long.parseLong(uid));
            message.setfType(Integer.parseInt(f_type));
            message.setfTitle(f_title);
            message.setfNote(f_note);
            message.setfFromUserId(f_from_user_id == null ? null : Long.parseLong(ParamTools.getdeParam(f_from_user_id)));
            message.setfToUserId(f_to_user_id == null ? null : Long.parseLong(ParamTools.getdeParam(f_to_user_id)));
            message.setfIsRead(0);
            message.setfState(1);
            messageService.save(message);
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
     * 修改消息信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/updateMessage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateMessage( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("f_key_id");
        String f_type = jsonParam.getString("f_type");
        String f_title = jsonParam.getString("f_title");
        String f_note = jsonParam.getString("f_note");
        String f_from_user_id = jsonParam.getString("f_from_user_id");
        String f_to_user_id = jsonParam.getString("f_to_user_id");
        try {
            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginUserID);
            }
            id = id == null ? "0" : ParamTools.getdeParam(id);
            TMessage message = new TMessage();
            message.setfKeyId(Long.parseLong(id));
            message.setfUId(Long.parseLong(uid));
            message.setfUDate(new Date());
            message.setfType(Integer.parseInt(f_type));
            message.setfTitle(f_title);
            message.setfNote(f_note);
            message.setfFromUserId(f_from_user_id == null ? null : Long.parseLong(ParamTools.getdeParam(f_from_user_id)));
            message.setfToUserId(f_to_user_id == null ? null : Long.parseLong(ParamTools.getdeParam(f_to_user_id)));
            messageService.update(message);
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
     * 删除消息信息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/delMessage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String delMessage( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        try {
            id = id == null ? "0" : ParamTools.getdeParam(id);
            long key = Long.parseLong(id);
            // 删除记录
            messageService.deleteById(key);
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
     * 变更消息状态
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/stateMessage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String stateMessage( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String state = jsonParam.getString("state");
        try {
            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginUserID);
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            state = state.equals("1") ? "0" : "1";
            TMessage message = new TMessage();
            message.setfKeyId(Long.parseLong(id));
            message.setfUId(Long.parseLong(uid));
            message.setfUDate(new Date());
            message.setfState(Integer.valueOf(state));
            messageService.update(message);
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
     * 变更读取状态
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/isReadMessage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String isReadMessage( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String id = jsonParam.getString("id");
        String isRead = jsonParam.getString("isRead");
        try {
            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginUserID);
            }
            id = id == null ? "0" : (id.equals("0") ? "0" : ParamTools.getdeParam(id));
            isRead = isRead.equals("1") ? "0" : "1";
            TMessage message = new TMessage();
            message.setfKeyId(Long.parseLong(id));
            message.setfUId(Long.parseLong(uid));
            message.setfUDate(new Date());
            message.setfIsRead(Integer.valueOf(isRead));
            messageService.update(message);
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
     * 全部设置为已读取状态
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/allReadMessage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String allReadMessage( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        try {
            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginUserID);
            }
            TMessage message = new TMessage();
            message.setfUId(Long.parseLong(uid));
            message.setfUDate(new Date());
            message.setfIsRead(1);
            TMessageExample messageExample = new TMessageExample();
            messageExample.or().andFToUserIdEqualTo(Long.parseLong(uid));
            messageService.updateByExample(message, messageExample);
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
     * 获取未读信息数
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/queryUnreadNum", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryUnreadNum( HttpServletRequest request )
            throws UnsupportedEncodingException, IOException {
        JSONObject object = new JSONObject();
        try {
            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(request); // 当前登录用户ID
            String uid = ""; // 当前登录用户ID
            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                uid = ParamTools.getdeParam(CookiesLoginUserID);
            }
            // 查询条件
            TimeTools timeTools = new TimeTools();
            List<TMessage> messages = null;
            TMessageExample messageExample = new TMessageExample();
            // 总未读
            messageExample.or()
              .andFToUserIdEqualTo(uid.equals("") ? -1L : Long.parseLong(uid))
              .andFIsReadEqualTo(0);
            int allNum = messageService.count(messageExample);
            object.put("AllNum", allNum);
            // 系统未读
            messageExample.clear();
            messageExample.or()
              .andFToUserIdEqualTo(uid.equals("") ? -1L : Long.parseLong(uid))
              .andFIsReadEqualTo(0)
              .andFTypeEqualTo(1);
            messageExample.setOrderByClause("f_c_date desc");
            int SysNum = messageService.count(messageExample);
            object.put("SysNum", SysNum);
            messages = messageService.findByExampleMapper(messageExample, 0, 1).getList();
            if (messages.size() > 0) {
                object.put("SysNumStr", timeTools.getTimeTitle(new Date(), messages.get(0).getfCDate()));
            } else {
                object.put("SysNumStr", "");
            }
            // 用户未读
            messageExample.clear();
            messageExample.or()
              .andFToUserIdEqualTo(uid.equals("") ? -1L : Long.parseLong(uid))
              .andFIsReadEqualTo(0)
              .andFTypeEqualTo(2);
            messageExample.setOrderByClause("f_c_date desc");
            int UserNum = messageService.count(messageExample);
            object.put("UserNum", UserNum);
            messages = messageService.findByExampleMapper(messageExample, 0, 1).getList();
            if (messages.size() > 0) {
                object.put("UserNumStr", timeTools.getTimeTitle(new Date(), messages.get(0).getfCDate()));
            } else {
                object.put("UserNumStr", "");
            }
            // 流程图未读
            messageExample.clear();
            messageExample.or()
              .andFToUserIdEqualTo(uid.equals("") ? -1L : Long.parseLong(uid))
              .andFIsReadEqualTo(0)
              .andFTypeEqualTo(3);
            messageExample.setOrderByClause("f_c_date desc");
            int FlowNum = messageService.count(messageExample);
            object.put("FlowNum", FlowNum);
            messages = messageService.findByExampleMapper(messageExample, 0, 1).getList();
            if (messages.size() > 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                object.put("FlowNumStr", timeTools.getTimeTitle(new Date(), messages.get(0).getfCDate()));
            } else {
                object.put("FlowNumStr", "");
            }
//            // 评价未读
//            messageExample.clear();
//            messageExample.or()
//              .andFToUserIdEqualTo(uid.equals("") ? -1L : Long.parseLong(uid))
//              .andFIsReadEqualTo(0)
//              .andFTypeEqualTo(4);
//            messageExample.setOrderByClause("f_c_date desc");
//            int EvaluateNum = messageService.count(messageExample);
//            object.put("EvaluateNum", EvaluateNum);
//            messages = messageService.findByExampleMapper(messageExample, 0, 1).getList();
//            if (messages.size() > 0) {
//                object.put("EvaluateNumStr", timeTools.getTimeTitle(new Date(), messages.get(0).getfCDate()));
//            } else {
//                object.put("EvaluateNumStr", "");
//            }

            // 项目未读
            messageExample.clear();
            messageExample.or()
                    .andFToUserIdEqualTo(uid.equals("") ? -1L : Long.parseLong(uid))
                    .andFIsReadEqualTo(0)
                    .andFTypeEqualTo(5);
            messageExample.setOrderByClause("f_c_date desc");
            int ProjectNum = messageService.count(messageExample);
            object.put("ProjectNum", ProjectNum);
            messages = messageService.findByExampleMapper(messageExample, 0, 1).getList();
            if (messages.size() > 0) {
                object.put("ProjectNumStr", timeTools.getTimeTitle(new Date(), messages.get(0).getfCDate()));
            } else {
                object.put("ProjectNumStr", "");
            }

            // 模具任务未读
            messageExample.clear();
            messageExample.or()
                    .andFToUserIdEqualTo(uid.equals("") ? -1L : Long.parseLong(uid))
                    .andFIsReadEqualTo(0)
                    .andFTypeEqualTo(6);
            messageExample.setOrderByClause("f_c_date desc");
            int MjTaskNum = messageService.count(messageExample);
            object.put("MjTaskNum", MjTaskNum);
            messages = messageService.findByExampleMapper(messageExample, 0, 1).getList();
            if (messages.size() > 0) {
                object.put("MjTaskNumStr", timeTools.getTimeTitle(new Date(), messages.get(0).getfCDate()));
            } else {
                object.put("MjTaskNumStr", "");
            }

            // 返回
            object.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            object.put("result", "error");
            object.put("error", e.toString());
        }
        return object.toString();
    }

    /**
     * 给指定用户推送消息
//     * @param userName 用户名
//     * @param message 消息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/message", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void messageOne( HttpServletRequest request ) throws IOException {
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String userName = jsonParam.getString("userName");
        String message = jsonParam.getString("message");
        try {
            messageServer.sendInfo(userName, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 给所有用户推送消息
//     * @param message 消息
     * @param request 客户端请求
     * @return 响应结果
     * @throws UnsupportedEncodingException 未知编码异常
     * @throws IOException 输入输出异常
     */
    @ResponseBody
    @RequestMapping(value = "/message/all", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void messageAll( HttpServletRequest request ) throws IOException {
        // 获取请求参数
        JSONObject jsonParam = ParamTools.getParameters(request);
        String message = jsonParam.getString("message");
        try {
            messageServer.onMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}