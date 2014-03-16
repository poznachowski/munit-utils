package pl.poznachowski.mule.message.enricher;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mule.api.MuleMessage;

import pl.poznachowski.mule.message.TestingMuleMessage;

import com.google.common.collect.ImmutableMap;

public class EnrichMuleMessageTest {
	private static final String VARIABLE1 = "variable1";
	private static final String VARIABLE2 = "variable2";
	private static final String VALUE1 = "value1";
	private static final String VALUE2 = "value2";
	private static final String PAYLOAD = "payload";
	private MuleMessage message;

	@Before
	public void setUp() {
		message = TestingMuleMessage.withPayload(PAYLOAD);
	}

	@Test
	public void shouldEnrichMuleMessageWithInvocationVariable() {
		MuleMessage enrichedMessage = PropertyEnricher.enrich(message).withInvocationProperty(VARIABLE1, VALUE1).get();
		assertThat(enrichedMessage.getInvocationProperty(VARIABLE1)).isEqualTo(VALUE1);
	}

	@Test
	public void shouldEnrichMuleMessageWithInvocationVariables() {
		Map<String, Object> invocationProperties = ImmutableMap.of(VARIABLE1, (Object) VALUE1, VARIABLE2, VALUE2);
		MuleMessage enrichedMessage = PropertyEnricher.enrich(message).withInvocationProperties(invocationProperties).get();
		assertThat(enrichedMessage.getInvocationProperty(VARIABLE1)).isEqualTo(VALUE1);
		assertThat(enrichedMessage.getInvocationProperty(VARIABLE2)).isEqualTo(VALUE2);
	}

	@Test
	public void shouldEnrichMuleMessageWithInboundVariable() {
		MuleMessage enrichedMessage = PropertyEnricher.enrich(message).withInboundProperty(VARIABLE1, VALUE1).get();
		assertThat(enrichedMessage.getInboundProperty(VARIABLE1)).isEqualTo(VALUE1);
	}

	@Test
	public void shouldEnrichMuleMessageWithOutboundVariable() {
		MuleMessage enrichedMessage = PropertyEnricher.enrich(message).withOutboundProperty(VARIABLE1, VALUE1).get();
		assertThat(enrichedMessage.getOutboundProperty(VARIABLE1)).isEqualTo(VALUE1);
	}
}
