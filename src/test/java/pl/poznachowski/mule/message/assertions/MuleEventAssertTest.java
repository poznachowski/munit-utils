package pl.poznachowski.mule.message.assertions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static pl.poznachowski.mule.message.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mule.api.MuleEvent;

import pl.poznachowski.mule.message.TestingMuleEvent;

public class MuleEventAssertTest extends BaseTest {
	
	MuleEvent actual;

	@Before
	public void setUp() {
		actual = TestingMuleEvent.withPayload("PAYLOAD");
	}

	@Test
	public void shouldFailIfActualIsNull() {
		expectException(AssertionError.class, actualIsNull());
	    actual = null;
	    assertThat(actual).hasSessionVariable("var").notSet();
	}
	
	@Test
	public void shouldFailIfSessionVariableSet() {
		expectException(AssertionError.class, "\nExpected Mule session variable named <'sessionVar'> is set");
	    actual.setSessionVariable("sessionVar", "SESSION_VAR");
	    assertThat(actual).hasSessionVariable("sessionVar").notSet();
	}
	
	@Test
	public void shouldFailIfSessionVariableNotSet() {
		expectException(AssertionError.class, "\nExpected Mule session variable named <'sessionVar'> is not set");
	    assertThat(actual).hasSessionVariable("sessionVar").set();
	}
	
	@Test
	public void shouldFailIfSessionVariableHasDifferentValue() { 
		actual.setSessionVariable("sessionVar", "BAD_VALUE");
		expectException(AssertionError.class, "\nExpected Mule session variable named <'sessionVar'> to be <'VALUE'>, but is <'BAD_VALUE'>");
	    assertThat(actual).hasSessionVariable("sessionVar").withValue("VALUE");
	}
	
	@Test
	public void shouldPassControlToMuleMessageAssert() {
		assertThat(assertThat(actual).hasMuleMessageWhich()).isInstanceOf(MuleMessageAssert.class);
	}
}
