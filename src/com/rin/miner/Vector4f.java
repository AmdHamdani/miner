package com.rin.miner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Hamdani on 5/16/17.
 */
public class Vector4f {

    public String label;
    public List<Float> data;

    public Vector4f() {}

    public Vector4f(Float...data) {
        this.data = Arrays.asList(data);
    }

    public Vector4f(String label, Float...data) {
        this.label = label;
        this.data = Arrays.asList(data);
    }

}
