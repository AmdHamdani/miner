package com.rin.test;

import com.rin.classification.NaiveBayes;
import com.rin.miner.Data;
import com.rin.miner.Miner;

import java.util.*;

/**
 * Created by Hamdani on 5/9/17.
 */
public class NaiveBayesTest {

    public static void main(String[] args) {
        String dataset = Miner.readFile("res/dataset.txt");
        ArrayList<String> table = NaiveBayes.getTable(dataset);

        // build frequency table
        HashMap<String, Data> ftable = new HashMap<String, Data>();

        NaiveBayes.assignClass(ftable, table);
        for(int i = 0; i < table.size()-1; i++)
            NaiveBayes.assignAttrValue(ftable, dataset, table, i);

        // probability of yes
        {
            float pc = ftable.get("yes").target.get(0);     // P(c) = P(yes) = 9/14 = 0.64
            float px = ftable.get("sunny").target.get(0);   // P(x) = P(sunny) = 5/14 = 0.36
            float pxuc = ftable.get("sunny").target.get(1); // P(x|c) = P(sunny|yes) = 3/9 = 0.33
            float pcux = pxuc * pc / px;                    // P(c|x) = P(yes|sunny) = P(x|c) * P(c) / P(x) = 0.60

            System.out.println("P(Yes|Sunny) = " + pcux +"\n" + "P(c) = " + pc + "\nP(x) = " + px + "\nP(x|c) = " + pxuc);
        }

        System.out.println("--------------------------");

        // probability of no
        {
            float pc = ftable.get("no").target.get(0);      // P(c) = P(yes) = 5/14 = 0.36
            float px = ftable.get("sunny").target.get(0);   // P(x) = P(sunny) = 5/14 = 0.36
            float pxuc = ftable.get("sunny").target.get(2); // P(x|c) = P(sunny|no) = 3/9 = 0.4
            float pcux = pxuc * pc / px;                    // P(c|x) = P(yes|sunny) = P(x|c) * P(c) / P(x) = 0.4

            System.out.println("P(No|Sunny) = " + pcux + "\n" + "P(c) = " + pc + "\nP(x) = " + px + "\nP(x|c) = " + pxuc);
        }

    }
}