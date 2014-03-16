package pl.poznachowski.mule.message.assertions;

import org.mule.api.MuleMessage;

public abstract class MessagePropertyTypes {

	public static PropertyFunction invocation() {
		return Invocation.INSTANCE;
	}
	
	public static PropertyFunction inbound() {
		return Inbound.INSTANCE;
	}
	
	public static PropertyFunction outbound() {
		return Outbound.INSTANCE;
	}

	private MessagePropertyTypes() {
	}
	
	private enum Invocation implements PropertyFunction {
		INSTANCE("invocation");
		
		private String type;

		Invocation(String type) {
			this.type = type;
		}
		
		public String getType() {
			return type;
		}
		
		public Object execute(MuleMessage message, String name) {
			return message.getInvocationProperty(name);
		}
	}

	private enum Inbound implements PropertyFunction {
		INSTANCE("inbound");
		
		private String type;

		Inbound(String type) {
			this.type = type;
		}
		
		public String getType() {
			return type;
		}
		
		public Object execute(MuleMessage message, String name) {
			return message.getInboundProperty(name);
		}
	}
	
	private enum Outbound implements PropertyFunction {
		INSTANCE("outbound");
		
		private String type;

		Outbound(String type) {
			this.type = type;
		}
		
		public String getType() {
			return type;
		}
		
		public Object execute(MuleMessage message, String name) {
			return message.getOutboundProperty(name);
		}
	}
}
