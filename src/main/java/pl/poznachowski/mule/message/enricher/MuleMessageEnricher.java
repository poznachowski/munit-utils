package pl.poznachowski.mule.message.enricher;

import static com.google.common.base.Preconditions.checkNotNull;

import org.mule.api.MuleMessage;

public class MuleMessageEnricher extends AbstractPropertyEnricher<MuleMessageEnricher> {

	public MuleMessageEnricher(MuleMessage muleMessage) {
		checkNotNull(muleMessage);
		super.muleMessage = muleMessage;
	}

	public MuleMessage get() {
		return muleMessage;
	}
}