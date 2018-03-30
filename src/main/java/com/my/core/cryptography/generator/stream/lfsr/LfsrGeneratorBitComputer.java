package com.my.core.cryptography.generator.stream.lfsr;

import java.util.List;

public class LfsrGeneratorBitComputer {

    public boolean compute(List<Integer> order, boolean[] generatorState) {
        boolean acc = generatorState[order.get(0)];
        for (int i = 1; i < order.size(); i++) {
            acc = acc ^ generatorState[order.get(i)];
        }
        return acc;
    }


}
