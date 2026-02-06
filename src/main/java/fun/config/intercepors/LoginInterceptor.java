package fun.config.intercepors;

import fun.tools.RedisTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor{

    private RedisTools redisTools;

    @Autowired
    public LoginInterceptor(RedisTools redisTools) {
        this.redisTools = redisTools;
    }

    // 这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            // 根据cookie来判断是否登录
            Cookie[] cookies = request.getCookies();

            // System.out.println("getHeaderNames: ");  
            // Enumeration<String> enumeration = request.getHeaderNames();
            // while (enumeration.hasMoreElements()) {
            //     String name = enumeration.nextElement();
            //     String value = request.getHeader(name);
            //     System.out.println(name + ":" + value);
            // }
            // String ip = request.getHeader("x-forwarded-for");
            // if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            //     ip = request.getHeader("Proxy-Client-IP");
            // }    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            //     ip = request.getHeader("WL-Proxy-Client-IP");
            // }    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            //     ip = request.getRemoteAddr();
            // }
            // System.out.println("ip: "+ip);
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    // 查看cookie中有没有叫token的授权码key，指向的是redis中登录时存的user对象
                    if (cookie.getName().equals("simulation_dmsnid")) {
                        // 如果有取出token中的key授权码value去核对
                        String token = cookie.getValue();
                        // 在redis中看该授权码对应的信息是否正确
                        String result = null;
                        try {
                            result = redisTools.get("SESSION:" + token).toString();
                        } catch (Exception e) {
                            result = null;
                        }
                        if (result == null || result.equals("")) {
                            // 如果在redis中没有对应的信息跳转到登录页面
                            response.sendRedirect(request.getContextPath() + "/module/go.html");
                            return false;
                        }
                        return true;
                    }

                    if (cookie.getName().equals("simulation_supplierdmsnid")) {
                        // 如果有取出token中的key授权码value去核对
                        String token = cookie.getValue();
                        // 在redis中看该授权码对应的信息是否正确
                        String result = null;
                        try {
                            result = redisTools.get("SESSION:" + token).toString();
                        } catch (Exception e) {
                            result = null;
                        }
                        if (result == null || result.equals("")) {
                            // 如果在redis中没有对应的信息跳转到登录页面
                            response.sendRedirect(request.getContextPath() + "/module/suppliergo.html");
                            return false;
                        }
                        return true;
                    }

                }
                // 如果能到这里说明cookie中没有一个值是token所以跳转到登录页面
                response.sendRedirect(request.getContextPath() + "/module/go.html");
                return false;
            } else {
                if (request.getMethod().equals("GET")) {
                    return true;
                } else {
                    // 跳转到登录页面
                    response.sendRedirect(request.getContextPath() + "/module/go.html");
                    return false;
                }
            }
        } catch (Exception e) {
            // 如果报错也跳转到登录页面
            response.sendRedirect(request.getContextPath() + "/module/go.html");
            return false;
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {}

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {}
}
