package pl.poznachowski.mule.message.enricher;

import java.util.Map;

import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.transport.PropertyScope;

import com.google.common.collect.ImmutableMap;

public abstract class AbstractPropertyEnricher<S extends AbstractPropertyEnricher<S>> {
	
	protected MuleMessage muleMessage;
	
	@SuppressWarnings("unchecked")
	protected final S self() {
		return (S)this;
	}
	
	public static MuleEventEnricher enrich(MuleEvent muleEvent) {
		return new MuleEventEnricher(muleEvent);
	}
	
	public S withInboundProperties(Map<String, Object> properties){
		return withProperties(properties, PropertyScope.INBOUND);
	}
	
	public S withInboundProperty(String name, Object value){
		return withProperties(ImmutableMap.of(name, value), PropertyScope.INBOUND);
	}
	
	public S withOutboundProperties(Map<String, Object> properties){
		return withProperties(properties, PropertyScope.OUTBOUND);
	}
	
	public S withOutboundProperty(String name, Object value){
		return withProperties(ImmutableMap.of(name, value), PropertyScope.OUTBOUND);
	}
	
	public S withInvocationProperties(Map<String, Object> properties){
		return withProperties(properties, PropertyScope.INVOCATION);
	}
	
	public S withInvocationProperty(String name, Object value){
		return withProperties(ImmutableMap.of(name, value), PropertyScope.INVOCATION);
	}
	
	public S withProperties(Map<String, Object> properties, PropertyScope scope){
		for (String propertyKey : properties.keySet()) {
			muleMessage.setProperty(propertyKey, properties.get(propertyKey), scope);
        }
		return self();
	}
}