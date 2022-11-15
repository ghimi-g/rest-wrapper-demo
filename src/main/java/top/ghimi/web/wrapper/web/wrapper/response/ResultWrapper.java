package top.ghimi.web.wrapper.web.wrapper.response;

import lombok.Builder;
import lombok.Getter;

/**
 * @author ghimi
 * @date 2022/10/12
 */
@Getter
@Builder
public class ResultWrapper<U> implements IResult {
    private Integer code;
    private String  message;
    private U       data;

    public static <T> ResultWrapper<T> success() {
        return success(null);
    }

    public static <T> ResultWrapper<T> success(T data) {
        return instance(ResultEnum.SUCCESS, data);
    }

    public static <T> ResultWrapper<T> failed() {
        return instance(ResultEnum.FAILED, null);
    }

    /**
     * 实例
     *
     * @param result  结果
     * @param message 消息
     * @param data    数据
     * @return {@link ResultWrapper}<{@link T}>
     */
    public static <T> ResultWrapper<T> instance(IResult result, String message, T data) {
        return instance(result.getCode(), message, data);
    }

    public static <T> ResultWrapper<T> instance(IResult result, T data) {
        return instance(result.getCode(), result.getMessage(), data);
    }

    public static <T> ResultWrapper<T> instance(Integer code, String message, T data) {
        return ResultWrapper.<T> builder().code(code).message(message).data(data).build();
    }
}
