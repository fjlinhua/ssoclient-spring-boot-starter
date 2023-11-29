package io.github.sso.vo;
/**
 * @author UserVO
 * @date 2023/11/28
 */

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * <p>Title: UserVO</p>
 * <p>Description: UserVO</p>
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: TD Co., Ltd.</p>
 * <p>Create Time: 2023/11/28</p>
 *
 * @author TD00857
 * <p>Update Time: 10:03</p>
 * <p>Updater: </p>
 * <p>Update Comments: </p>
 */
@Getter
@Setter
@ToString
public class UserVO {
    private Integer id;
    private String userName;
    private String nickName;
    private String avatar;
    private String phone;
    private String email;
    private String address;
    private Byte sex;
    private Byte state;
    private List<String> appList;
}
