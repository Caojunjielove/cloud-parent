package org.cjj.filter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StreamUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class MessageEncryptFilter extends ZuulFilter  {

	protected final Log log = LogFactory.getLog(this.getClass());
	@Override
	public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();  
        InputStream is = ctx.getResponseDataStream();
        String body = "";
		try {
			body = StreamUtils.copyToString(is, Charset.forName("utf-8"));
		} catch (IOException e) {
			log.error("报文处理异常",e);
		}
		//-----------------------加密处理开始------------------------
	    //msg加密处理
		//-----------------------加密处理结束------------------------
		body = body + "321";
		log.error("响应报文"+body);
	   	ctx.setSendZuulResponse(false); 
        ctx.setResponseStatusCode(200);  
        ctx.setResponseBody(body); 
		return null;
	}

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true; // 是否执行该过滤器，此处为true，说明需要过滤  
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 3; //优先级为0，数字越大，优先级越低  
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "post";// 后置过滤器  
	}
}
