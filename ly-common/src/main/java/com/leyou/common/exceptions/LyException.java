package com.leyou.common.exceptions;

import com.leyou.common.enums.ExceptionEnum;
import lombok.Getter;

/**
 * @author wangxin
 * @date 2020/2/11 15:43
 * @description: 自定义异常
 * GOOD LUCK！
 */
@Getter
public class LyException extends RuntimeException {
    private int status;


    public LyException(int status, String message) {
        super(message);
        this.status = status;
    }

    public LyException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.status = exceptionEnum.getStatus();
    }
}
