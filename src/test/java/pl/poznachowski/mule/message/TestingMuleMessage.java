package pl.poznachowski.mule.message;

import org.mule.DefaultMuleMessage;
import org.mule.api.MuleContext;
import org.mule.api.MuleMessage;
import org.mule.api.context.MuleContextFactory;
import org.mule.context.DefaultMuleContextFactory;

public class TestingMuleMessage {

public static MuleMessage withPayload(Object object) {
		
		MuleContextFactory contextFactory = new DefaultMuleContextFactory();
		MuleContext context = null;
		try {
			context = contextFactory.createMuleContext();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new DefaultMuleMessage(object, context);
	}

}
