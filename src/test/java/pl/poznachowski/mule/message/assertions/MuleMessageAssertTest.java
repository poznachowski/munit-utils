package pl.poznachowski.mule.message.assertions;

import static org.assertj.core.util.FailureMessages.actualIsNull;
import static pl.poznachowski.mule.message.assertions.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mule.api.MuleMessage;
import org.mule.api.transport.PropertyScope;

import pl.poznachowski.mule.message.TestingMuleMessage;

public class MuleMessageAssertTest extends BaseTest {

	MuleMessage actual;

	@Before
	public void setUp() {
		actual = TestingMuleMessage.withPayload("PAYLOAD");
	}

	@Test
	public void shouldFailIfActualIsNull() {
		expectException(AssertionError.class, actualIsNull());
	    actual = null;
	    assertThat(actual).hasStringPayload("abc");
	}
	
	@Test
	public void shouldFailIfPayloadIsNotGivenString() {
		expectException(AssertionError.class, "Expected Mule String payload to be <'XYZ'>, but is <'PAYLOAD'>"); 
		assertThat(actual).hasStringPayload("XYZ");
	}
	
	@Test
	public void shouldFailIfPayloadNotContaining() {
		expectException(AssertionError.class, "<['TEST']>"); 
		assertThat(actual).hasStringPayloadContaining("PAYLOAD", "TEST");
	}
	
	@Test
	public void shouldFailIfPayloadOfAWrongType() {
		expectException(AssertionError.class, "instance of");
		assertThat(actual).hasPayloadInstanceOf(List.class);
	}
	
	@Test
	public void shouldFailIfNotTheSamePayload() {
		expectException(AssertionError.class, "exact object");
		assertThat(actual).hasPayload(new String("PAYLOAD"));
	}
	
	@Test
	public void shouldFailIfInvocationPropertyIsNotSet() {
		expectException(AssertionError.class, "Expected Mule 'invocation' property named <'var'> is not set");
		assertThat(actual).hasInvocationProperty("var").set();
	}
	
	@Test
	public void shouldFailIfInvocationPropertyIsSet() {
		actual.setInvocationProperty("var", "value");
		expectException(AssertionError.class, "Expected Mule 'invocation' property named <'var'> is set");
		assertThat(actual).hasInvocationProperty("var").notSet();
	}
	
	@Test
	public void shouldFailIfInvocationPropertyHasWrongValue() {
		actual.setInvocationProperty("var", "bad");
		expectException(AssertionError.class, "Expected Mule 'invocation' property named <'var'> to be <'value'>, but is <'bad'>");
		assertThat(actual).hasInvocationProperty("var").withValue("value");
	}
	
	@Test
	public void shouldFailIfInboundPropertyIsNotSet() {
		expectException(AssertionError.class, "Expected Mule 'inbound' property named <'var'> is not set");
		assertThat(actual).hasInboundProperty("var").set();
	}
	
	@Test
	public void shouldFailIfInboundPropertyIsSet() {
		actual.setProperty("var", "value", PropertyScope.INBOUND);
		expectException(AssertionError.class, "Expected Mule 'inbound' property named <'var'> is set");
		assertThat(actual).hasInboundProperty("var").notSet();
	}
	
	@Test
	public void shouldFailIfInboundPropertyHasWrongValue() {
		actual.setProperty("var", "bad", PropertyScope.INBOUND);
		expectException(AssertionError.class, "Expected Mule 'inbound' property named <'var'> to be <'value'>, but is <'bad'>");
		assertThat(actual).hasInboundProperty("var").withValue("value");
	}
	
	@Test
	public void shouldFailIfOutboundPropertyIsNotSet() {
		expectException(AssertionError.class, "Expected Mule 'outbound' property named <'var'> is not set");
		assertThat(actual).hasOutboundProperty("var").set();
	}
	
	@Test
	public void shouldFailIfOutboundPropertyIsSet() {
		actual.setOutboundProperty("var", "value");
		expectException(AssertionError.class, "Expected Mule 'outbound' property named <'var'> is set");
		assertThat(actual).hasOutboundProperty("var").notSet();
	}
	
	@Test
	public void shouldFailIfOutboundPropertyHasWrongValue() {
		actual.setOutboundProperty("var", "bad");
		expectException(AssertionError.class, "Expected Mule 'outbound' property named <'var'> to be <'value'>, but is <'bad'>");
		assertThat(actual).hasOutboundProperty("var").withValue("value");
	}
}
