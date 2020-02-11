package com.leyou.common.advice;

import com.leyou.common.exceptions.ExceptionResult;
import com.leyou.common.exceptions.LyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author wangxin
 * @date 2020/2/11 15:48
 * @description: TODO
 * GOOD LUCK！
 */
@Slf4j
@ControllerAdvice
public class BasicExceptionAdvice {
    @ExceptionHandler(LyException.class)
    public ResponseEntity<ExceptionResult> handleException(LyException e) {
        return ResponseEntity.status(e.getStatus()).body(new ExceptionResult(e));
    }
}
