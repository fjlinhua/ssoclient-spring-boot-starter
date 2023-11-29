package io.github.sso.config;/**
 * @author ClientAutoConfiguration
 * @date 2023/11/28
 */

import io.github.sso.properties.SsoClientProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * <p>Title: ClientAutoConfiguration</p>
 * <p>Description: ClientAutoConfiguration</p>
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: TD Co., Ltd.</p>
 * <p>Create Time: 2023/11/28</p>
 *
 * @author TD00857
 * <p>Update Time: 16:55</p>
 * <p>Updater: </p>
 * <p>Update Comments: </p>
 */
@AutoConfiguration
@ConditionalOnClass(SsoClientProperties.class)
@EnableConfigurationProperties(SsoClientProperties.class)
public class ClientAutoConfiguration {

}
