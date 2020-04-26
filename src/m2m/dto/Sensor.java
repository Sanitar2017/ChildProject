package m2m.dto;

public class Sensor {
public int id;
public int seqNumber;
public int value;
public long timestamp;
public Sensor() {
	
}
public Sensor(int id, int value, long timestamp) {
	super();
	this.id = id;
	this.value = value;
	this.timestamp = timestamp;
}
public int getId() {
	return id;
}
public int getSeqNumber() {
	return seqNumber;
}
public int getValue() {
	return value;
}
public long getTimestamp() {
	return timestamp;
}

}
