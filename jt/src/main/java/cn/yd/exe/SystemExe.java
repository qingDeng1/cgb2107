package cn.yd.exe;

import cn.yd.vo.SysResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//1、表示该类是全局异常处理机制.返回值都是json串
//通知：AOP中的技术，解决特定问题的，
@RestControllerAdvice
//@ControllerAdvice
public class SystemExe {
    /*
    * 业务说明：需要为全局异常定义一个方法.
    * 要求：返回的统一的业务数据SysResult
    * 拦截：指定遇到某种异常实现AOP处理
    * 特点：该异常处理机制，只拦截Controller层抛出的异常（其他层异常会向上抛，都抛到Controller层）
    * */
    //异常类型比较多，返回值使用object
    //出现运行异常时执行这个方法
    @ExceptionHandler(RuntimeException.class)
    public SysResult fail(Exception e) {
        e.printStackTrace();
        return SysResult.fail();
    }
}
