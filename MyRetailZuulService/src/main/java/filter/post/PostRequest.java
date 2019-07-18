package filter.post;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PostRequest extends ZuulFilter {

	private static final Logger LOG = LoggerFactory.getLogger(PostRequest.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		LOG.info("Post ZuulFilter Called");
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		
		Object stt = context.get("startTime");
		if (stt != null ) {
			long startTime = (Long) stt;
			long timetaken = ((Long) System.nanoTime() - startTime) / 1000000L;
			String uri = request.getRequestURI();
			
			LOG.info("URI:{}, HTTP Method : {}, Resposne Code :{}, Time Taken :{}", 
					uri, request.getMethod(), context.getResponseStatusCode(), timetaken);
		}
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 2;
	}
}
