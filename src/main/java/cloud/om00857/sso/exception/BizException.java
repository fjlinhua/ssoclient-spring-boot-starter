package cloud.om00857.sso.exception;
/**
 * @author BizException
 * @date 2023/11/06
 */

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * <p>Title: BizException</p>
 * <p>Description: BizException</p>
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: TD Co., Ltd.</p>
 * <p>Create Time: 2023/11/06</p>
 *
 * @author TD00857
 * <p>Update Time: 11:16</p>
 * <p>Updater: </p>
 * <p>Update Comments: </p>
 */
@Getter
public class BizException extends RuntimeException {

    private int code;

    private BizException(int code, String message, Throwable e) {
        super(message, e);
        this.code = code;
    }

    public static BizException of(String message) {
        return new BizException(HttpStatus.BAD_REQUEST.value(), message, null);
    }

    public static BizException of(HttpStatus code, String message) {
        return new BizException(code.value(), message, null);
    }

}
