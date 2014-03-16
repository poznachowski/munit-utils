package pl.poznachowski.mule.message.assertions;

import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;

public class Assertions {

	public static MuleEventAssert assertThat(MuleEvent actual) {
		return new MuleEventAssert(actual);
	}
	
	public static MuleMessageAssert assertThat(MuleMessage actual) {
		return new MuleMessageAssert(actual);
	}
}
