package cloud.om00857.sso.mvc;
/**
 * @author LogMdcFilter
 * @date 2023/11/28
 */

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * <p>Title: LogMdcFilter</p>
 * <p>Description: LogMdcFilter</p>
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: TD Co., Ltd.</p>
 * <p>Create Time: 2023/11/28</p>
 *
 * @author TD00857
 * <p>Update Time: 10:00</p>
 * <p>Updater: </p>
 * <p>Update Comments: </p>
 */
public class LogMdcFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String traceid = StrUtil.blankToDefault(request.getHeader("Trace-ID"), request.getParameter("traceid"));
        if (StrUtil.isBlank(traceid)) {
            traceid = StrUtil.concat(true, RandomUtil.randomString(16), "@", String.valueOf(Thread.currentThread().getId()));
        } else {
            if (!StrUtil.contains(traceid, '@')) {
                traceid = StrUtil.concat(true, traceid, "@", String.valueOf(Thread.currentThread().getId()));
            }
        }
        MDC.put("RequestId", traceid);
        try {
            filterChain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }

}
