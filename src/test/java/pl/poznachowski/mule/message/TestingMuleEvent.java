package pl.poznachowski.mule.message;

import org.mule.DefaultMuleEvent;
import org.mule.MessageExchangePattern;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.context.MuleContextFactory;
import org.mule.construct.Flow;
import org.mule.context.DefaultMuleContextFactory;

public class TestingMuleEvent {

	private static final String FLOW = "flowName";

	public static MuleEvent withPayload(Object payload) {

		MuleContextFactory contextFactory = new DefaultMuleContextFactory();
		MuleContext context = null;
		try {
			context = contextFactory.createMuleContext();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new DefaultMuleEvent(TestingMuleMessage.withPayload(payload), MessageExchangePattern.REQUEST_RESPONSE, new Flow(FLOW, context));
	}
}
