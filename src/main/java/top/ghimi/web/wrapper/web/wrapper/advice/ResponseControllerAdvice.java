package top.ghimi.web.wrapper.web.wrapper.advice;

import top.ghimi.web.wrapper.web.wrapper.annotation.UnwrappedBody;
import top.ghimi.web.wrapper.web.wrapper.response.IResult;
import top.ghimi.web.wrapper.web.wrapper.response.ResultWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * 封装返回结果
 * @author ghimi
 * @date 2022/10/12
 */
@Slf4j
@RestControllerAdvice(basePackages = "top.ghimi.web.wrapper.web")
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {

    @Autowired
    ObjectMapper mapper;

    @Override
    public boolean supports(MethodParameter returnType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        Class<?> type = returnType.getParameterType();
        // 如果设置了raw result 注解
        if (IResult.class.isAssignableFrom(type)
            || Objects.nonNull(returnType.getExecutable().getAnnotation(UnwrappedBody.class))) {
            log.info("the method {} has annotated by raw result",
                returnType.getExecutable().getName());
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        if (StringHttpMessageConverter.class.isAssignableFrom(selectedConverterType)) {
            try {
                // 重新封装字符串
                return mapper.writeValueAsString(ResultWrapper.success(body));
            } catch (JsonProcessingException e) {
                log.warn("wrap body has occurred an exception {}", body, e);
                return body;
            }
        }
        return ResultWrapper.success(body);
    }

}
