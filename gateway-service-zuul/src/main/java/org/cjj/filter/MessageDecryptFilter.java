package org.cjj.filter;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StreamUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.ServletInputStreamWrapper;

public class MessageDecryptFilter extends ZuulFilter  {

	protected final Log log = LogFactory.getLog(this.getClass());
	@Override
	public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();  
        HttpServletRequest request = ctx.getRequest();
        boolean flag = false;
        String msg = "";
        try {
			msg = StreamUtils.copyToString(request.getInputStream(), Charset.forName("utf-8"));
			log.info("请求报文：" + msg);
			//-----------------------解密处理开始------------------------
		    //msg解密处理
			//-----------------------解密处理结束------------------------
			msg = msg + "123";
			flag = true;
		} catch (IOException e) {
			log.error("报文处理异常",e);
		}
        if(flag){
        	HttpServletRequest requestWrapper = new MyHttpServletRequestWrapper(request,msg);
        	ctx.setSendZuulResponse(true);  
            ctx.setResponseStatusCode(200);
            ctx.setRequest(requestWrapper);
        }else{
        	ctx.setSendZuulResponse(false);  
            ctx.setResponseStatusCode(401);  
            ctx.setResponseBody("{\"result\":\"message is not correct!\"}"); 
        }
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
		return 0; //优先级为0，数字越大，优先级越低  
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";// 前置过滤器  
	}

	/** 
	* 重新封装request包装类 
	* @author Administrator 
	* 
	*/  
	class MyHttpServletRequestWrapper extends HttpServletRequestWrapper{  
		private String body;  
		  
		public MyHttpServletRequestWrapper(HttpServletRequest request,String body) {  
			super(request);  
			this.body = body;  
		}

		@Override
		public int getContentLength() {
			// TODO Auto-generated method stub
			return body.getBytes(Charset.forName("utf-8")).length;
		}

		@Override
		public long getContentLengthLong() {
			// TODO Auto-generated method stub
			return body.getBytes(Charset.forName("utf-8")).length;
		}

		@Override
		public ServletInputStream getInputStream() throws IOException {
			return new ServletInputStreamWrapper(body.getBytes(Charset.forName("utf-8")));
		}  	  
	}  
}
