package pl.poznachowski.mule.message.assertions;

import org.mule.api.MuleMessage;

public interface PropertyFunction {
	Object execute(MuleMessage message, String name);
	String getType();
}
