package pl.poznachowski.mule.message.enricher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.rules.ExpectedException.none;

import java.io.Serializable;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mule.api.MuleEvent;

import pl.poznachowski.mule.message.TestingMuleEvent;

import com.google.common.collect.ImmutableMap;

public class EnrichMuleEventTest {

	private static final String VARIABLE1 = "variable1";
	private static final String VARIABLE2 = "variable2";
	private static final String VALUE1 = "value1";
	private static final String VALUE2 = "value2";
	private static final String PAYLOAD = "payload";
	private MuleEvent event;
	
	@Rule
	public ExpectedException exception = none();

	@Before
	public void setUp() {
		event = TestingMuleEvent.withPayload(PAYLOAD);
	}
	
	@Test
	public void shouldThrowExceptionWhenEventIsNull(){
		exception.expect(NullPointerException.class);
		PropertyEnricher.enrich((MuleEvent)null).withSessionProperty(VARIABLE1, VALUE1).get();
	}
	
	@Test
	public void shouldEnrichMuleEventWithSessionVariable() {
		MuleEvent enrichedEvent = PropertyEnricher.enrich(event).withSessionProperty(VARIABLE1, VALUE1).get();
		
		assertThat(enrichedEvent.getSessionVariable(VARIABLE1)).isEqualTo(VALUE1);
	}
	
	@Test
	public void shouldEnrichMuleEventWithSessionVariables() {
		Map<String, Serializable> sessionProperties = ImmutableMap.of(VARIABLE1, (Serializable)VALUE1, VARIABLE2, VALUE2);
		
		MuleEvent enrichedEvent = PropertyEnricher.enrich(event).withSessionProperties(sessionProperties).get();
		
		assertThat(enrichedEvent.getSessionVariable(VARIABLE1)).isEqualTo(VALUE1);
		assertThat(enrichedEvent.getSessionVariable(VARIABLE2)).isEqualTo(VALUE2);
	}
	
	@Test
	public void shouldEnrichMuleEventWithInvocationVariable() {
		MuleEvent enrichedEvent = PropertyEnricher.enrich(event).withInvocationProperty(VARIABLE1, VALUE1).get();
		
		assertThat(enrichedEvent.getMessage().getInvocationProperty(VARIABLE1)).isEqualTo(VALUE1);
	}
	
	@Test
	public void shouldEnrichMuleEventWithInvocationVariables() {
		Map<String, Object> invocationProperties = ImmutableMap.of(VARIABLE1, (Object)VALUE1, VARIABLE2, VALUE2);
		MuleEvent enrichedEvent = PropertyEnricher.enrich(event).withInvocationProperties(invocationProperties).get();
		
		assertThat(enrichedEvent.getMessage().getInvocationProperty(VARIABLE1)).isEqualTo(VALUE1);
		assertThat(enrichedEvent.getMessage().getInvocationProperty(VARIABLE2)).isEqualTo(VALUE2);
	}
	
	@Test
	public void shouldEnrichMuleEventWithInboundVariable() {
		MuleEvent enrichedEvent = PropertyEnricher.enrich(event).withInboundProperty(VARIABLE1, VALUE1).get();
		assertThat(enrichedEvent.getMessage().getInboundProperty(VARIABLE1)).isEqualTo(VALUE1);
	}
	
	@Test
	public void shouldEnrichMuleEventWithOutboundVariable() {
		MuleEvent enrichedEvent = PropertyEnricher.enrich(event).withOutboundProperty(VARIABLE1, VALUE1).get();
		assertThat(enrichedEvent.getMessage().getOutboundProperty(VARIABLE1)).isEqualTo(VALUE1);
	}
}
