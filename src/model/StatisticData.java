package model;

public class StatisticData implements Comparable<StatisticData>{
	private int index;
	private double data;
	
	public StatisticData(int index, double data) {
		this.setIndex(index);
		this.setData(data);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public double getData() {
		return data;
	}

	public void setData(double data) {
		this.data = data;
	}

	@Override
	public int compareTo(StatisticData o) {
		if(data<o.getData()) {
			return -1;
		}else if(data>o.getData()) {
			return 1;
		}else {
			return 0;
		}
	}
	
}
