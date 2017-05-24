package com.rin.classification;

import com.rin.miner.Data;

import java.io.*;
import java.util.*;

/**
 * Created by Hamdani on 5/9/17.
 */
public class NaiveBayes {

    public static String readFile(String dir) {
        StringBuilder sb = new StringBuilder();

        try {
            InputStream is = new FileInputStream(dir);
            BufferedReader in = new BufferedReader(new InputStreamReader(is));

            String line = in.readLine();

            while(line != null) {
                sb.append(line).append("\n");
                line = in.readLine();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static ArrayList<String> getTable(String dataset) {
        String[] temp = dataset.split("\n");
        int tn = temp[0].split(";").length;
        ArrayList<String> table = new ArrayList<String>();

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < tn; i++) {
            for(int j = 0; j < temp.length; j++) {
                String[] store = temp[j].split(";");
                sb.append(store[i]).append(",");
            }
            table.add(sb.toString());
            sb.delete(0, sb.length());
        }

        return table;
    }

    public static String[] getFrequency(String line) {
        List asList = Arrays.asList(line.split(","));
        Set<String> set = new HashSet<String>(asList);

        String out = "";
        for(String s: set) {
            out += s + " " +Collections.frequency(asList, s) + "\n";
        }

        return out.split("\n");
    }

    public static int likelihood(String s, String x, String c) {
        int counter = 1;
        String[] line = s.split("\n");
        for(int i = 0; i < line.length; i++) {
            if (line[i].contains(x) && line[i].contains(c))
                counter++;
        }

        return counter;
    }

    public static void assignAttrValue(HashMap<String, Data> ftable, String dataset, ArrayList<String> table, int idx) {
        String[] temp = NaiveBayes.getFrequency(table.get(idx));
        String[] s;

        for(int i = 0; i < temp.length; i++) {
            s = temp[i].split(" ");

            int yes = NaiveBayes.likelihood(dataset, s[0], "yes");
            int no = Integer.parseInt(s[1]) - yes;

            ftable.put(s[0], new Data(s[0], Float.parseFloat(s[1])/14,
                    yes / ftable.get("yes").target.get(1),
                    no / ftable.get("no").target.get(1)));
        }
    }

    public static void assignClass(HashMap<String, Data> ftable, ArrayList<String> table) {
        String[] temp = NaiveBayes.getFrequency(table.get(table.size()-1));
        String[] s;

        for(int i = 0; i < temp.length; i++) {
            s = temp[i].split(" ");
            ftable.put(s[0], new Data(s[0], Float.parseFloat(s[1])/14, Float.parseFloat(s[1])));
        }
    }

    public static void printData(ArrayList<Data> table) {
        for(int i = 0; i < table.size(); i++) {
            System.out.println(Arrays.toString(table.get(i).target.toArray()));
        }

    }
}
