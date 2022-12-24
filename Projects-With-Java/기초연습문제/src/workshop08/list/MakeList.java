package workshop08.list;

import java.util.ArrayList;

public class MakeList {
	ArrayList<Integer> list;
	
	public MakeList() {
	}
	
	public void makeArrayList(int size) {
		list = new ArrayList<Integer>(size);
		
		for(int i=0; i < size; i++) {
			list.add((int) ((Math.random() + 0.1) * 100));
		}
	}
	
	public ArrayList<Integer> getList() {
		return list;
	}
	
	public double getAverage() {
		double sum = 0;
		for (int i=0; i < list.size(); i++) {
			sum += list.get(i);
		}
		return sum / list.size();
	}
}
