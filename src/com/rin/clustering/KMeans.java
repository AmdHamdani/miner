package com.rin.clustering;

import com.rin.miner.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Hamda on 5/23/2017.
 */
public class KMeans {

    public static void calculate(ArrayList<Model> dataset, ArrayList<Model> centroid, int k) {
        boolean finish = false;
        float[] dst = new float[centroid.size()];
        ArrayList<Model> old;
        int iteration = 0;

//        System.out.println("Dataset : ");
//        for(Model m : dataset)
//            System.out.println(Arrays.toString(m.instance()));
        System.out.println("----------------------------------\n");

        do {
            System.out.println("Iteration - " + iteration++);
            System.out.println("Old Centroid : ");
            printCentroid(centroid);

            for (Model m : dataset) {
                for (int i = 0; i < m.instance().length; i++) {
                    dst[i] = euclideanDst(centroid.get(i), m);
                }

                m.setLabel(setLabel("label ", dst));
            }

            old = centroid;
            centroid = getNewCentroid(dataset, k);
            finish = compareCentroid(centroid, old);

            System.out.println("New Centroid : ");
            printCentroid(centroid);
            System.out.println("finish : " + finish);

//            System.out.println("Clustered Dataset : ");
//            print(dataset);
            System.out.println("----------------------------------");

        } while(!finish);

        System.out.println("Total iteration : " + iteration);
    }

    public static void singleCalculation(ArrayList<Model> dataset, ArrayList<Model> centroid, int k) {
        boolean finish = false;
        float[] dst = new float[centroid.size()];
        ArrayList<Model> old;
        int iteration = 0;

        System.out.println("Dataset : ");
        for(Model m : dataset)
            System.out.println(Arrays.toString(m.instance()));
        System.out.println("----------------------------------\n");

        System.out.println("Iteration - " + iteration++);
        System.out.println("Old Centroid : ");
        printCentroid(centroid);

        for (Model m : dataset) {
            for (int i = 0; i < m.instance().length; i++) {
                dst[i] = euclideanDst(centroid.get(i), m);
            }

            m.setLabel(setLabel("label ", dst));
        }

        old = centroid;
        centroid = getNewCentroid(dataset, k);
        finish = compareCentroid(centroid, old);

        System.out.println("New Centroid : ");
        printCentroid(centroid);
        System.out.println("finish : " + finish);

        System.out.println("Clustered Dataset : ");
        print(dataset);
        System.out.println("----------------------------------");

        System.out.println("Total iteration : " + iteration);
    }

    public static ArrayList<Model> getCentroid(ArrayList<Model> dataset, int k) {
        ArrayList<Model> temp = new ArrayList<Model>();
        int lastIndex = 0;

        for (int i = 0; i < k; i++) {
            int idx = ThreadLocalRandom.current().nextInt(0, dataset.size());
            if(i == 0)
                lastIndex = idx;
            else {
                if(lastIndex == idx && idx < dataset.size() - 1)
                    idx += 1;
                else if(lastIndex == idx && idx >= dataset.size())
                    idx -= 1;
            }

            temp.add(dataset.get(idx));
        }

        return temp;
    }

    public static ArrayList<Model> getNewCentroid(ArrayList<Model> dataset, int k) {
        ArrayList<Model> temp = new ArrayList<>();

        for(int i = 0; i < k; i++) {
            temp.add(new Model("", sum(dataset, " label", i)));
        }

        return temp;
    }

    public static float euclideanDst(Model instance, Model centroid) {
        float res = 0f;

        for (int i = 0; i < centroid.length(); i++) {
            res += Math.pow((centroid.instance()[i] - instance.instance()[i]), 2);
        }

        return (float) Math.sqrt(res);
    }

    public static String setLabel(String label, float[] dst) {
        int idx = 0;
        float tmp = dst[0];
        for (int i = 0; i < dst.length; i++) {
            if (tmp > dst[i]) {
                tmp = dst[i];
                idx = i;
            }
        }
        return label + idx;
    }

    public static boolean compareCentroid(ArrayList<Model> centroid, ArrayList<Model> temp) {

        int[] done = new int[centroid.size()];

        for (int i = 0; i < centroid.size(); i++) {
            for (int j = 0; j < centroid.get(i).length(); j++) {
                done[i] += temp.get(i).instance()[j] == centroid.get(i).instance()[j] ? 1 : 0;
            }

            if (done[i] > 1)
                done[i] = 1;
        }

        for (int i = 0; i < done.length; i++) {
            if (done[i] == 0)
                return false;
        }

        return true;
    }

    public static float[] sum(ArrayList<Model> dataset, String label, int k) {
        float[] temp = new float[dataset.get(0).instance().length];
        float avg = 0;

        for(int i = 0; i < temp.length; i++) {
            int counter = 0;
            for(int j = 0; j < dataset.size(); j++) {
                if(dataset.get(j).getLabel().equalsIgnoreCase("label " + k)) {
                    avg += dataset.get(j).instance()[i];
                    counter++;
                }
            }

            temp[i] = avg / counter;
            avg = 0;
            counter = 0;
        }

        return temp;
    }

    public static void print(ArrayList<Model> dataset) {
        for(Model m : dataset)
            System.out.println(Arrays.toString(m.instance()) + " - " + m.getLabel());
    }

    public static void printCentroid(ArrayList<Model> centroid) {
        for(Model m : centroid)
            System.out.println(Arrays.toString(m.instance()));
    }
}
