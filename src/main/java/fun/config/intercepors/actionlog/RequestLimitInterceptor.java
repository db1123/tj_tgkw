package fun.config.intercepors.actionlog;

import fun.server.model.TLogAction;
import fun.server.model.TMenuOptions;
import fun.server.model.TMenuOptionsExample;
import fun.server.model.TUser;
import fun.server.service.TLogActionService;
import fun.server.service.TMenuOptionsService;
import fun.server.service.TUserService;
import fun.tools.ParamTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Component
public class RequestLimitInterceptor implements HandlerInterceptor {

    private final TUserService userService;
    private final TMenuOptionsService menuOptionsService;
    private final TLogActionService logActionService;

    @Autowired
    public RequestLimitInterceptor(
      TUserService userService,
      TMenuOptionsService menuOptionsService,
      TLogActionService logActionService) {
        this.userService = userService;
        this.menuOptionsService = menuOptionsService;
        this.logActionService = logActionService;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        try {
            // 获取用户信息
            Long userId = -1L;
            String userName = "匿名用户";
            String CookiesLoginUserID = ParamTools.getCookiesLoginUserID(httpServletRequest);

            if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
                String uid = "0"; // 当前登录用户ID
                try {
                    uid = ParamTools.getdeParam(CookiesLoginUserID);
                } catch (Exception e) {
                    uid = "0";
                }
                try {
                    TUser user = userService.findById(Long.parseLong(uid));
                    if(user!=null){
                        userId = user.getfKeyId();
                        userName = user.getfName();
                    }else{
                        userId = -1L;
                        userName = "匿名用户";
                    }
                } catch (NumberFormatException e) {
                    userId = -1L;
                    userName = "匿名用户";
                }
            } else {
//                CookiesLoginUserID = ParamTools.getCookiesLoginSupplierUserID(httpServletRequest); // 当前登录用户ID
//                if (CookiesLoginUserID != null && !CookiesLoginUserID.equals("")) {
//                    String uid = ParamTools.getdeParam(CookiesLoginUserID);
//                    TSupplier supplier = supplierService.findById(Long.parseLong(uid));
//                    userId = supplier.getfKeyId();
//                    userName = supplier.getfName();
//                }
            }
            // 服务请求
            // int s_indexOf = url.indexOf("/s/");
            // if(s_indexOf >= 0) {
            //     System.out.println(url.substring(s_indexOf));
            // }
            // 执行方法
            if(handler instanceof HandlerMethod) {
                HandlerMethod h = (HandlerMethod)handler;
                // 普通用户
                LogOperation logOperation = h.getMethodAnnotation(LogOperation.class);
                if (logOperation != null) {
                    // 添加
                    TLogAction logAction = new TLogAction();
                    logAction.setfUserType(1);
                    logAction.setfUserId(userId);
                    logAction.setfUserName(userName);
                    logAction.setfType(2);
                    logAction.setfPath(h.getShortLogMessage());
                    logAction.setfMemo("执行操作:" + logOperation.value());
                    logActionService.save(logAction);
                }
//                // 供应商用户
//                LogOperationSupplier logOperationSupplier = h.getMethodAnnotation(LogOperationSupplier.class);
//                if (logOperationSupplier != null) {
//                    // 添加
//                    TLogAction logAction = new TLogAction();
//                    logAction.setfUserType(2);
//                    logAction.setfUserId(userId);
//                    logAction.setfUserName(userName);
//                    logAction.setfType(2);
//                    logAction.setfPath(h.getShortLogMessage());
//                    logAction.setfMemo("执行操作:" + logOperationSupplier.value());
//                    logActionService.save(logAction);
//                }
            }
            // 访问地址
            String url = httpServletRequest.getRequestURL().toString();
            String param = httpServletRequest.getQueryString();
            if(param != null && !param.equals("")) {
                url = url + "?" + param;
            }
            int module_indexOf = url.indexOf("/module/");
            if(module_indexOf >= 0) {
                String go_url = url.substring(module_indexOf);
                TMenuOptionsExample menuOptionsExample = new TMenuOptionsExample();
                menuOptionsExample.or().andFUrlEqualTo(go_url);
                List<TMenuOptions> menuOption_list = menuOptionsService.findByExample(menuOptionsExample);
                if (menuOption_list.size() > 0) {
                    // 添加
                    TLogAction logAction = new TLogAction();
                    logAction.setfUserId(userId);
                    logAction.setfUserName(userName);
                    logAction.setfType(1);
                    logAction.setfPath(url);
                    logAction.setfMemo("访问：" + menuOption_list.get(0).getfName());
                    logActionService.save(logAction);
                }
            }
        } catch (Exception e) {
            //log.error("发生异常", e);
        }
        return  true;
    }

}