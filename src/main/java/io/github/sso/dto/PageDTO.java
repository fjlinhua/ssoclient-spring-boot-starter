package io.github.sso.dto;

import cn.hutool.core.util.NumberUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Optional;

/**
 * <p>Title: PageDTO</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: Copyfun Co., Ltd.</p>
 * <p>Create Time: 2023/8/14 17:32</p>
 *
 * @author Think
 * <p>Update Time: 2023/8/14 17:32</p>
 * <p>Updater: Think</p>
 * <p>Update Comments: </p>
 */
@Getter
@Setter
@ToString
public class PageDTO {

    private Integer page;

    private Integer limit;

    public Integer getPage() {
        return Optional.ofNullable(page).orElse(1);
    }

    public Integer getLimit() {
        return NumberUtil.min(Optional.ofNullable(limit).orElse(20), 100);
    }
}
