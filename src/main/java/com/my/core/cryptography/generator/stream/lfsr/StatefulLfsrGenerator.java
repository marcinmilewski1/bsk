package com.my.core.cryptography.generator.stream.lfsr;

import com.google.common.collect.Lists;
import com.my.core.cryptography.generator.stream.util.BinaryUtils;

import java.util.List;

public class StatefulLfsrGenerator {
    private boolean[] polynomial;
    private boolean[] state;
    private boolean[] stateTable;
    private List<Integer> additionOrder;
    private LfsrGeneratorBitComputer lfsrGeneratorBitComputer;

    public StatefulLfsrGenerator(boolean[] polynomial, boolean[] state) {
        this.polynomial = polynomial;
        this.state = state;
        additionOrder = getAdditionOrder(polynomial);
        lfsrGeneratorBitComputer = new LfsrGeneratorBitComputer();
    }

    private List<Integer> getAdditionOrder(boolean[] polynomial) {
        List<Integer> order = Lists.newArrayList();
        for (int i = polynomial.length -1 ; i >=0 ; i--) {
            if (polynomial[i] == true) {
                order.add(i);
            }
        }
        return order;
    }

    public boolean generateNext() {
        boolean computedBit = lfsrGeneratorBitComputer.compute(additionOrder, state);
        return computedBit;
    }


    public void shiftRightNoCarry() {
        state = BinaryUtils.shiftRightNoCarry(state);
    }

    public void setFirstStateBit(boolean first) {
        state[0] = first;
    }

}
