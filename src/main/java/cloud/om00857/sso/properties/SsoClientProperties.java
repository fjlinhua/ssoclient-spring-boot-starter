package cloud.om00857.sso.properties;/**
 * @author SsoClientProperties
 * @date 2023/11/28
 */

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>Title: SsoClientProperties</p>
 * <p>Description: SsoClientProperties</p>
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: TD Co., Ltd.</p>
 * <p>Create Time: 2023/11/28</p>
 *
 * @author TD00857
 * <p>Update Time: 16:54</p>
 * <p>Updater: </p>
 * <p>Update Comments: </p>
 */
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "sso.client")
public class SsoClientProperties {

    private String applicationId;

    private String[] protectPathPatterns = new String[]{"/api/**"};

    private String[] excludePathPatterns = new String[]{};

}
