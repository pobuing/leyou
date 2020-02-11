package com.leyou.common.exceptions;

import lombok.Getter;
import org.joda.time.DateTime;

/**
 * @author wangxin
 * @date 2020/2/11 15:57
 * @description: 自定义异常结果对象
 * GOOD LUCK！
 */
@Getter
public class ExceptionResult {
    private int status;
    private String message;
    private String timestamp;

    public ExceptionResult(LyException e) {
        this.status = e.getStatus();
        this.message = e.getMessage();
        this.timestamp = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
    }
}
