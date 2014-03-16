package pl.poznachowski.mule.message.assertions;

import java.io.Serializable;

import org.assertj.core.api.AbstractAssert;
import org.mule.api.MuleEvent;

public class MuleEventAssert extends AbstractAssert<MuleEventAssert, MuleEvent> {

	public MuleEventAssert(MuleEvent actual) {
		super(actual, MuleEventAssert.class);
	}
	
	public MuleEventSessionProperty hasSessionVariable(String name) {
		isNotNull();
		return new MuleEventSessionProperty(name);
	}

	public MuleMessageAssert hasMuleMessageWhich() {
		isNotNull();
		if (actual.getMessage() == null) {
			failWithMessage("\nExpecting actual not to be null");
		}
		return new MuleMessageAssert(actual.getMessage());
	}
	
	public class MuleEventSessionProperty {

		private String name;

		public MuleEventSessionProperty(String name) {
			this.name = name;
		}
		
		public MuleEventAssert set() {
			if (actual.getSessionVariable(name) == null) {
				failWithMessage("\nExpected Mule session variable named <%s> is not set", name);
			}
			return MuleEventAssert.this;
		}
		
		public MuleEventAssert notSet() {
			if (actual.getSessionVariable(name) != null) {
				failWithMessage("\nExpected Mule session variable named <%s> is set", name);
			}
			return MuleEventAssert.this;
		}

		public MuleEventAssert withValue(Serializable value) {
			if (!value.equals(actual.getSessionVariable(name))) {
				failWithMessage("\nExpected Mule session variable named <%s> to be <%s>, but is <%s>", name, value,
						actual.getSessionVariable(name));
			}
			return MuleEventAssert.this;
		}
	}
}