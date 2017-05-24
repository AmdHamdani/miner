package com.rin.miner;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import com.rin.miner.Model;

public class Miner {

	private static ArrayList<Model> dataset = new ArrayList<Model>();

	public static String readFile(String path) {
		StringBuilder sb = new StringBuilder();

		try {
			InputStream is = new FileInputStream(path);
			BufferedReader in = new BufferedReader(new InputStreamReader(is));

			String line = in.readLine();

			while(line != null) {
				sb.append(line).append('\n');
				line = in.readLine();
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	public static void writeFile(ArrayList<Model> dataset, String path, String lineEnding) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			for(Model m : dataset) {
				bw.write(Arrays.toString(m.instance()) + " - " + m.getLabel() + lineEnding);
			}

			System.out.println("Finish writing on file. . . ");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Model> buildDataset(String data, String...delimiter) {
		String[] temp = data.split(delimiter[0]);

		for(String s : temp) {
			String[] str = s.split(delimiter[1]);
			float[] num = new float[str.length];

			for(int i = 0; i < str.length; i++) {
				num[i] = Float.parseFloat(str[i]);
			}

			dataset.add(new Model("", num));
		}

		return dataset;
	}

}
