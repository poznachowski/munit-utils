package pl.poznachowski.mule.message.assertions;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Objects;
import org.assertj.core.internal.Strings;
import org.mule.api.MuleMessage;

public class MuleMessageAssert extends AbstractAssert<MuleMessageAssert, MuleMessage> {

	Strings strings = Strings.instance();
	Objects objects = Objects.instance();
	
	public MuleMessageAssert(MuleMessage actual) {
		super(actual, MuleMessageAssert.class);
	}

	public MuleMessageAssert hasPayload(Object payload) {
		isNotNull();
		if (actual.getPayload() != payload) {
			failWithMessage("\nExpected Mule payload to be exact object <%s>, but is <%s>", actual.getPayload(), payload);
		}
		return this;
	}
	
	public MuleMessageAssert hasPayloadInstanceOf(Class<?> payloadClass) {
		isNotNull();
		objects.assertIsInstanceOf(info, actual.getPayload(), payloadClass);
		return this;
	}
	
	public MuleMessageAssert hasStringPayload(String expectedStringPayload) {
		isNotNull();
		String actualStringPayload = getPayloadString();
		if (!expectedStringPayload.equals(actualStringPayload)) {
			failWithMessage("\nExpected Mule String payload to be <%s>, but is <%s>", expectedStringPayload, actualStringPayload);
		}
		return this;
	}
	
	public MuleMessageAssert hasStringPayloadContaining(CharSequence... values) {
		isNotNull();
		String actualStringPayload = getPayloadString();
		strings.assertContains(info, actualStringPayload, values);
		return this;
	}

	public MuleMessagePropertyAssert hasInvocationProperty(String propertyName) {
		isNotNull();
		return new MuleMessagePropertyAssert(MessagePropertyTypes.invocation(), propertyName);
	}
	
	public MuleMessagePropertyAssert hasInboundProperty(String propertyName) {
		isNotNull();
		return new MuleMessagePropertyAssert(MessagePropertyTypes.inbound(), propertyName);
	}
	
	public MuleMessagePropertyAssert hasOutboundProperty(String propertyName) {
		isNotNull();
		return new MuleMessagePropertyAssert(MessagePropertyTypes.outbound(), propertyName);
	}

	public class MuleMessagePropertyAssert {

		private String propertyName;
		private PropertyFunction propertyFunction;

		public MuleMessagePropertyAssert(PropertyFunction function, String propertyName) {
			this.propertyFunction = function;
			this.propertyName = propertyName;
		}
		
		public MuleMessageAssert set() {
			if (propertyFunction.execute(actual, propertyName) == null) {
				failWithMessage("\nExpected Mule %s property named <%s> is not set", propertyFunction.getType(), propertyName);
			}
			return MuleMessageAssert.this;
		}
		
		public MuleMessageAssert notSet() {
			if (propertyFunction.execute(actual, propertyName) != null) {
				failWithMessage("\nExpected Mule %s property named <%s> is set", propertyFunction.getType(), propertyName);
			}
			return MuleMessageAssert.this;
		}

		public MuleMessageAssert withValue(Object value) {
			if (!value.equals(propertyFunction.execute(actual, propertyName))) {
				failWithMessage("\nExpected Mule %s property named <%s> to be <%s>, but is <%s>", propertyFunction.getType(), propertyName, value,
						propertyFunction.execute(actual, propertyName));
			}
			return MuleMessageAssert.this;
		}
	}
	
	private String getPayloadString() {
		try {
			return actual.getPayloadAsString();
		} catch (Exception e) {
			failWithMessage("\nActual not a String payload.");
			return null;
		}
	}
}
