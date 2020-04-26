package m2m.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.support.MessageBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import m2m.dto.Sensor;
import m2m.service.interfaces.IDispatcher;

import java.io.IOException;

@EnableBinding(IDispatcher.class)
public class Dispatcher {
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	IDispatcher dispatcher;
	@Value("${min_normal_value:45}")
	int minNormalValue;
	@Value("${max_normal_value:200}")
	int maxNormalValue;
	
	@StreamListener(Sink.INPUT)
	void takeSensorData(String sensorJson) throws JsonParseException, JsonMappingException, IOException {
		Sensor sensor =
				mapper.readValue(sensorJson, Sensor.class);
		if(sensor.value>maxNormalValue)
		{
			//bigValues channel
			dispatcher.bigValues().send(MessageBuilder.withPayload(sensorJson).build());
		}else if(sensor.value<minNormalValue)
		{
			//smallValues channel
			dispatcher.smallValue().send(MessageBuilder.withPayload(sensorJson).build());
		}
		//avg processing channel
		dispatcher.output().send(MessageBuilder.withPayload(sensorJson).build());
				
	}
	
}
