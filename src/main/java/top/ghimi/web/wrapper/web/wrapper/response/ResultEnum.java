package top.ghimi.web.wrapper.web.wrapper.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ghimi
 * @date 2022/10/12
 */
@Getter
@AllArgsConstructor
public enum ResultEnum implements IResult {
    /**
     * 成功
     */
    SUCCESS(2000, "success"),
    /**
     * 失败
     */
    FAILED(4000, "failed"),
    /**
     * 参数验证失败
     */
    VALIDATION_FAILED(4003, "validation failed");
    /**
     * 状态码
     */
    private final Integer code;
    /**
     *
     */
    private final String message;
}
