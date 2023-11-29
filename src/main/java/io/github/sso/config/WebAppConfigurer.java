package io.github.sso.config;
/**
 * @author WebAppConfigurer
 * @date 2023/11/28
 */

import io.github.sso.mvc.LogMdcFilter;
import io.github.sso.properties.SsoClientProperties;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>Title: WebAppConfigurer</p>
 * <p>Description: WebAppConfigurer</p>
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: TD Co., Ltd.</p>
 * <p>Create Time: 2023/11/28</p>
 *
 * @author TD00857
 * <p>Update Time: 09:59</p>
 * <p>Updater: </p>
 * <p>Update Comments: </p>
 */
@ConditionalOnWebApplication
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    @Resource
    private SsoClientProperties ssoClientProperties;

    @Bean
    public FilterRegistrationBean<LogMdcFilter> logMDCFilter() {
        FilterRegistrationBean<LogMdcFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new LogMdcFilter());
        registration.addUrlPatterns("/*");
        registration.setName(LogMdcFilter.class.getName());
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin())).addPathPatterns(ssoClientProperties.getProtectPathPatterns()).excludePathPatterns(ssoClientProperties.getExcludePathPatterns());
    }

}
