package Utils;

import java.util.LinkedList;
import java.util.List;

public class ObjectJson {
	public List<Pair> atts = new LinkedList<Pair>();
	
	public void add(String key) {
		atts.add(new Pair(key, ""));
	}
	
	public void setValueLast(String value)
	{
		atts.getLast().s = value;
	}
}