package cloud.om00857.sso.mvc;

import cn.dev33.satoken.util.SaResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Type;

/**
 * <p>Title: DefResponseHandler</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: Copyfun Co., Ltd.</p>
 * <p>Create Time: 2023/8/11 15:03</p>
 *
 * @author Think
 * <p>Update Time: 2023/8/11 15:03</p>
 * <p>Updater: Think</p>
 * <p>Update Comments: </p>
 */
@RestControllerAdvice
public class DefResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Type type = returnType.getGenericParameterType();
        if (type instanceof Class) {
            if (void.class.isAssignableFrom((Class<?>) type)) {
                return SaResult.ok();
            }
        }
        if (body == null || body instanceof SaResult) {
            return body;
        }
        return SaResult.data(body);
    }

}
