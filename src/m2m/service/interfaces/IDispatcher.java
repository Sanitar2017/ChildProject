package m2m.service.interfaces;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;

public interface IDispatcher extends Processor {
	String BIG_VALUES = "bigValues";
	
	@Output(BIG_VALUES)
	MessageChannel bigValues();
	
	String SMALL_VALUES = "smallValues";
	
	@Output(SMALL_VALUES)
	MessageChannel smallValue();
	
}
