package cloud.om00857.sso.util;

import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.json.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * <p>Title: WebUtil</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: Copyfun Co., Ltd.</p>
 * <p>Create Time: 2023/8/14 18:19</p>
 *
 * @author Think
 * <p>Update Time: 2023/8/14 18:19</p>
 * <p>Updater: Think</p>
 * <p>Update Comments: </p>
 */
@Slf4j
public final class WebUtil {

    public static ServletRequestAttributes getRequestAttributes() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
    }

    public static JSONObject buildRequestFormParameter2Json() {
        HttpServletRequest request = getRequestAttributes().getRequest();
        Enumeration<String> parameterNames = request.getParameterNames();
        JSONObject param = new JSONObject();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            param.set(paramName, request.getParameter(paramName));
        }
        return param;
    }

    public static String getClientIp() {
        HttpServletRequest request = getRequestAttributes().getRequest();
        if (request == null) {
            return null;
        }
        String ip = request.getHeader("X-Forwarded-For");
        if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                log.error("Get client ip error!", e);
                return StrUtil.EMPTY;
            }
        }
        return StrUtil.subBefore(ip, CharUtil.COMMA, false);
    }

    public static String getUserAgent() {
        HttpServletRequest request = getRequestAttributes().getRequest();
        if (request == null) {
            return null;
        }
        return request.getHeader(Header.USER_AGENT.getValue());
    }

}
