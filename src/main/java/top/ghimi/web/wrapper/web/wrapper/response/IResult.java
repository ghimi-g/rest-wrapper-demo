package top.ghimi.web.wrapper.web.wrapper.response;

/**
 * @author ghimi
 * @date 2022/10/12
 */
public interface IResult {
    /**
     * 获取代码
     *
     * @return {@link Integer}
     */
    Integer getCode();

    /**
     * 得到消息
     *
     * @return {@link String}
     */
    String getMessage();
}
