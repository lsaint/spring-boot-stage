package com.ethan.stage.zuul;

import com.ethan.stage.common.GenericResponse;
import com.ethan.stage.common.enums.ErrEnum;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

@Slf4j
public class TokenFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre"; // 可以在请求被路由之前调用
    }

    @Override
    public int filterOrder() {
        return 0; // filter执行顺序，通过数字指定 ,优先级为0，数字越大，优先级越低
    }

    @Override
    public boolean shouldFilter() {
        return true; // 是否执行该过滤器，此处为true，说明需要过滤
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info("TokenFilter {},{}", request.getMethod(), request.getRequestURL().toString());

        String token = request.getParameter("token"); // 获取请求的参数

        if (StringUtils.isNotBlank(token)) {
            ctx.setSendZuulResponse(true); // 继续路由
            ctx.setResponseStatusCode(HttpStatus.OK.value());
            ctx.set("isSuccess", true);
            return null;
        } else {
            ctx.setSendZuulResponse(false); // 中断路由
            ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            ctx.setResponseBody(GenericResponse.onError(ErrEnum.INVALID_TOKEN).toJson());
            ctx.set("isSuccess", false);
            return null;
        }
    }
}

/*
*  pre	    -3	    ServletDetectionFilter	标记处理Servlet的类型
   pre	    -2	    Servlet30WrapperFilter	包装HttpServletRequest请求
   pre	    -1	    FormBodyWrapperFilter	包装请求体
   route	1	    DebugFilter	            标记调试标志
   route	5	    PreDecorationFilter	    处理请求上下文供后续使用
   route	10	    RibbonRoutingFilter	    serviceId请求转发
   route	100	    SimpleHostRoutingFilter	url请求转发
   route	500	    SendForwardFilter	    forward请求转发
   post	    0	    SendErrorFilter	        处理有错误的请求响应
   post	    1000	SendResponseFilter	    处理正常的请求响应
*/
