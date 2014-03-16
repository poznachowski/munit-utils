package pl.poznachowski.mule.message.enricher;

import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;

public class PropertyEnricher {

	public static MuleEventEnricher enrich(MuleEvent muleEvent) {
		return new MuleEventEnricher(muleEvent);
	}
	
	public static MuleMessageEnricher enrich(MuleMessage muleMessage) {
		return new MuleMessageEnricher(muleMessage);
	}
}
