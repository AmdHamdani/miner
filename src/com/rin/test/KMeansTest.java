package com.rin.test;

import com.rin.clustering.KMeans;
import com.rin.miner.Debugger;
import com.rin.miner.Miner;
import com.rin.miner.Model;

import java.util.ArrayList;

/**
 * Created by Hamdani on 5/16/17.
 */
public class KMeansTest {

    public static void main(String[] args) {
        Debugger.setStartTime();
        Debugger.setBeforeUsedMemory();

        ArrayList<Model> dataset = Miner.buildDataset(Miner.readFile("res/dataset/tugasPP.in"), "\n", " ");
        ArrayList<Model> centroid = KMeans.getCentroid(dataset, 2);

        KMeans.calculate(dataset, centroid, 2);

        Miner.writeFile(dataset, "res/result/tugasPP.out", "\n");

        Debugger.setAfterUsedMemory();
        Debugger.setEndTime();

        System.out.println("Execution time = " + Debugger.executionTime() + " ms");
        System.out.println("Memory usage = " + Debugger.memoryUsage() + " bytes");
    }

}
