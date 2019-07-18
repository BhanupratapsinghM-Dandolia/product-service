package filter.pre;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PreRequest extends ZuulFilter {
	private static final Logger LOG = LoggerFactory.getLogger(PreRequest.class);
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		LOG.info("Pre ZuulFilter Called");
		RequestContext context = RequestContext.getCurrentContext();
		context.set("startTime", (Long) System.nanoTime());
		String requestId = UUID.randomUUID().toString();
		context.set("requestId", requestId);
		context.addZuulRequestHeader("request-id", requestId);
		MDC.put("request-id", requestId);
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}
}
