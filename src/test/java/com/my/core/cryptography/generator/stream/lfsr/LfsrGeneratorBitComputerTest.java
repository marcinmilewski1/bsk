package com.my.core.cryptography.generator.stream.lfsr;

import com.google.common.collect.Lists;
import com.my.core.cryptography.generator.stream.util.BinaryUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.BitSet;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class LfsrGeneratorBitComputerTest {

    private LfsrGeneratorBitComputer lfsrGeneratorBitComputer;

    @Before
    public void setUp() throws Exception {
        lfsrGeneratorBitComputer = new LfsrGeneratorBitComputer();
    }

    @Test
    public void whenPolynominalIs1001AndState1010Test() throws Exception {
        List<Integer> order = Lists.newArrayList(3, 0);
        BitSet state = new BitSet(4);
        state.set(0, true);
        state.set(1, false);
        state.set(2, true);
        state.set(3, false);

        boolean computed = lfsrGeneratorBitComputer.compute(order, BinaryUtils.toBooleanArray(state, 4));
        Assert.assertThat(computed, is(true));
    }

    @Test
    public void whenPolynominalIs1001AndState0101Test() throws Exception {
        List<Integer> order = Lists.newArrayList(3, 0);
        BitSet state = new BitSet(4);
        state.set(0, false);
        state.set(1, true);
        state.set(2, false);
        state.set(3, true);
        boolean computed = lfsrGeneratorBitComputer.compute(order, BinaryUtils.toBooleanArray(state, 4));
        Assert.assertThat(computed, is(true));
    }


}