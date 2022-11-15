# 应用示例

## 使用 ResultWrapper 封装 Rest 响应

关键包: `top.ghimi.web.wrapper.web.wrapper`

使用 Spring 的 `org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice` 对响应进行重新封装
提供了统一的封装格式

```java

@Slf4j
// 声明这是一个响应拦截器
@RestControllerAdvice(basePackages = "top.ghimi.web.wrapper.web")
// 实现 ResponseBodyAdvice 重写 supports 方法,这里泛型对象为 Object 表示对全部返回结果都进行封装
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {

    @Autowired
    ObjectMapper mapper;

    @Override
    public boolean supports(MethodParameter returnType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        Class<?> type = returnType.getParameterType();
        // 如果设置了raw result 注解,声明封装逻辑执行的方式
        // 1. 如果返回结果已经是封装好的 ResultWrapper 格式了,则不处理
        // 2. 如果方法被 @UnwrappedBody 注解,则不做处理
        if (ResultWrapper.class.isAssignableFrom(type)
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
                // 对 被 StringHttpMessageConverter 处理的返回结果做特殊处理
                // 重新封装字为字符串,因为之后会被强制转换为 String 类型
                // 因此这里提前通过序列化的方式处理为json字符串格式
                return mapper.writeValueAsString(ResultWrapper.success(body));
            } catch (JsonProcessingException e) {
                // 如果报错则抛出异常,应该
                log.warn("wrap body has occurred an exception {}", body, e);
                return body;
            }
        }
        // 其余返回对象通过 ResultWrapper.success 对象封装
        return ResultWrapper.success(body);
    }

}
```