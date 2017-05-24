package com.rin.miner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Hamdani on 5/9/17.
 */
public class Data {

    public String label;
    /***
     * idx 0    =>  P(c) or P(x)
     * idx 1++  =>  P(x|c)
     */
    public List<Float> target;

    public Data(String label, Float...targets) {
        this.label = label;
        target = Arrays.asList(targets);
    }
}
