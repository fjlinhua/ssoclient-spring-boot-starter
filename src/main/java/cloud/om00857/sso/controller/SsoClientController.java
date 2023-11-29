package cloud.om00857.sso.controller;
/**
 * @author SsoClientController
 * @date 2023/11/28
 */

import cloud.om00857.sso.exception.BizException;
import cloud.om00857.sso.properties.SsoClientProperties;
import cloud.om00857.sso.util.SsoUserUtil;
import cloud.om00857.sso.vo.UserVO;
import cn.dev33.satoken.config.SaSsoConfig;
import cn.dev33.satoken.sso.SaSsoProcessor;
import cn.dev33.satoken.sso.SaSsoUtil;
import cn.dev33.satoken.sso.exception.SaSsoException;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.URLUtil;
import com.dtflys.forest.Forest;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title: SsoClientController</p>
 * <p>Description: SsoClientController</p>
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: TD Co., Ltd.</p>
 * <p>Create Time: 2023/11/28</p>
 *
 * @author TD00857
 * <p>Update Time: 10:02</p>
 * <p>Updater: </p>
 * <p>Update Comments: </p>
 */
@RestController
public class SsoClientController {

    @Resource
    private SsoClientProperties ssoClientProperties;

    /**
     * 返回SSO认证中心登录地址
     *
     * @param clientLoginUrl
     * @return
     */
    @RequestMapping("/sso/getSsoAuthUrl")
    public SaResult getSsoAuthUrl(String clientLoginUrl, String back) {
        String serverAuthUrl = SaSsoUtil.buildServerAuthUrl(URLUtil.encode(clientLoginUrl), back);
        return SaResult.data(serverAuthUrl);
    }

    /**
     * 根据ticket进行登录
     *
     * @param ticket
     * @return
     */
    @RequestMapping("/sso/doLoginByTicket")
    public SaResult doLoginByTicket(String ticket) {
        Object loginId;
        try {
            loginId = SaSsoProcessor.instance.checkTicket(ticket, "/sso/doLoginByTicket");
        } catch (SaSsoException e) {
            throw BizException.of(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
        if (loginId != null) {
            StpUtil.login(loginId);
            UserVO user = SsoUserUtil.getUserInfo(loginId);
            if (CollectionUtil.contains(user.getAppList(), ssoClientProperties.getApplicationId())) {
                return SaResult.data(StpUtil.getTokenValue());
            } else {
                SaSsoProcessor.instance.ssoLogout();
                throw BizException.of(HttpStatus.UNAUTHORIZED, "You do not have permission to access this page");
            }
        }
        throw BizException.of(HttpStatus.UNAUTHORIZED, "Invalid ticket");
    }

    @RequestMapping("/sso/*")
    public SaResult ssoRequest() {
        return SaResult.data(SaSsoProcessor.instance.clientDister());
    }

    @Autowired
    private void configSso(SaSsoConfig sso) {
        // 配置Http请求处理器
        sso.setSendHttp(url -> Forest.get(url).executeAsString());
    }

}
