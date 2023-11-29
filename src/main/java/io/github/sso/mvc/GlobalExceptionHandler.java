package io.github.sso.mvc;

import io.github.sso.exception.BizException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>Title: GlobalExceptionHandler</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: Copyfun Co., Ltd.</p>
 * <p>Create Time: 2023/8/11 12:24</p>
 *
 * @author Think
 * <p>Update Time: 2023/8/11 12:24</p>
 * <p>Updater: Think</p>
 * <p>Update Comments: </p>
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotLoginException.class)
    public SaResult handleNotLogin() {
        return SaResult.code(HttpStatus.UNAUTHORIZED.value()).setMsg("Please login first");
    }

    @ExceptionHandler
    public SaResult handlerException(Exception e) {
        log.error("Unknow error!", e);
        return SaResult.error(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public SaResult hanelMethodArgError(MethodArgumentNotValidException e) {
        String defaultMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        return SaResult.code(HttpStatus.BAD_REQUEST.value()).setMsg(defaultMessage);
    }

    @ExceptionHandler(BizException.class)
    public SaResult handleBizException(BizException e) {
        return SaResult.code(e.getCode()).setMsg(e.getMessage());
    }

}
