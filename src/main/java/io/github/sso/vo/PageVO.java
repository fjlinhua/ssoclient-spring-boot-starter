package io.github.sso.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

/**
 * <p>Title: PageVO</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: Copyfun Co., Ltd.</p>
 * <p>Create Time: 2023/8/14 17:59</p>
 *
 * @author Think
 * <p>Update Time: 2023/8/14 17:59</p>
 * <p>Updater: Think</p>
 * <p>Update Comments: </p>
 */
@Getter
@Setter
@ToString
public class PageVO {

    private Long count;

    private List<?> content;

    public static PageVO of(List<?> content, Long count) {
        PageVO vo = new PageVO();
        vo.setCount(count);
        vo.setContent(content);
        return vo;
    }

    public static PageVO of() {
        return of(Collections.emptyList(), 0L);
    }

}
