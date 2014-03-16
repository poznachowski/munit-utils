package pl.poznachowski.mule.message.enricher;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.Map;

import org.mule.api.MuleEvent;

import com.google.common.collect.ImmutableMap;

public class MuleEventEnricher extends AbstractPropertyEnricher<MuleEventEnricher> {

	private final MuleEvent muleEvent;

	public MuleEventEnricher(MuleEvent muleEvent) {
		checkNotNull(muleEvent);
		checkNotNull(muleEvent.getMessage());
		this.muleEvent = muleEvent;
		super.muleMessage = muleEvent.getMessage();
	}
	
	public MuleEventEnricher withSessionProperty(String name, Serializable value) {
		return withSessionProperties(ImmutableMap.of(name, value));
	}
	
	public MuleEventEnricher withSessionProperties(Map<String, Serializable> sessionProperties) {
		checkNotNull(muleEvent.getSession());
		checkNotNull(sessionProperties);
		for (String sessionKey : sessionProperties.keySet()) {
			muleEvent.getSession().setProperty(sessionKey, sessionProperties.get(sessionKey));
        }
		return this;
	}

	public MuleEvent get() {
		return muleEvent;
	}
}
