package com.rin.miner;

public class Model {

	private String label;
	private float[] instance;

	public Model() { }

	public Model(String label, float...instance) {
		this.label = label;
		this.instance = instance;
	}

	public Model(String data) {
		String[] temp = data.split(",");
		instance = new float[temp.length];
		
		for(int i = 0; i < temp.length; i++)
			instance[i] = Float.parseFloat(temp[i]);
	}
	
	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setInstance(float...instance) {
		this.instance = instance;
	}

	public void setData(int idx, float data) {
		instance[idx] = data;
	}

	public float[] instance() {
		return instance;
	}

	public int length() {
		return instance.length;
	}

}
