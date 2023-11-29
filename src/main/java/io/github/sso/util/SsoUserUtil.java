package io.github.sso.util;
/**
 * @author SsoUserUtil
 * @date 2023/11/08
 */

import io.github.sso.vo.UserVO;
import cn.dev33.satoken.sso.SaSsoUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: SsoUserUtil</p>
 * <p>Description: SsoUserUtil</p>
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: TD Co., Ltd.</p>
 * <p>Create Time: 2023/11/08</p>
 *
 * @author TD00857
 * <p>Update Time: 17:09</p>
 * <p>Updater: </p>
 * <p>Update Comments: </p>
 */
public final class SsoUserUtil {

    public static UserVO getUserInfo(Object loginId) {
        Map<String, Object> p = new HashMap<>(1);
        p.put("userId", loginId);
        String data = (String) SaSsoUtil.getData(p);
        if (JSONUtil.isTypeJSONObject(data)) {
            return BeanUtil.toBean(JSONUtil.parseObj(data).getJSONObject("data"), UserVO.class);
        }
        return null;
    }

}
