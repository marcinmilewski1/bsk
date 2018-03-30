package com.my.core.cryptography.generator.stream.lfsr;

import com.google.common.collect.Lists;
import com.my.core.cryptography.Generator;
import com.my.core.cryptography.generator.stream.property.LfsrGeneratorProperty;

import java.util.List;
import java.util.Properties;

import static com.my.core.cryptography.generator.stream.util.BinaryUtils.getBooleanArray;
import static com.my.core.cryptography.generator.stream.util.BinaryUtils.shiftRightNoCarry;

public class LfsrGenerator implements Generator {
    private LfsrGeneratorBitComputer lfsrGeneratorBitComputer;

    public LfsrGenerator() {
        lfsrGeneratorBitComputer = new LfsrGeneratorBitComputer();
    }

    @Override
    public boolean[] generate(Properties properties, int number) {
        String polynomialString = properties.getProperty(LfsrGeneratorProperty.POLYNOMIAL.name());
        String generatorStateString = properties.getProperty(LfsrGeneratorProperty.SEED.name());
        if (polynomialString == null || polynomialString.isEmpty()) throw new IllegalArgumentException();
        if (generatorStateString == null || generatorStateString.length() != polynomialString.length()) throw new IllegalArgumentException();
        boolean[] polynomial = getBooleanArray(polynomialString);
        boolean[] generatorState = getBooleanArray(generatorStateString);
        boolean[] output = new boolean[number];

        List<Integer> additionOrder = getAdditionOrder(polynomial);

        for (int i = 0; i < number; i++) {
            printState(i, generatorState);
            boolean computedBit = lfsrGeneratorBitComputer.compute(additionOrder, generatorState);
            System.out.println(i + " computed bit: " + (computedBit == true ? "1" : "0") );
            generatorState = shiftRightNoCarry(generatorState);
            generatorState[0] = computedBit;
            output[i] = computedBit;
        }
        return output;
    }

    private void printState(int i, boolean[] generatorState) {
        StringBuilder sb = new StringBuilder();
        sb.append(i).append(" Stan: ");
        for (boolean b : generatorState) {
            sb.append(b==true ? "1" : "0");
        }
        System.out.println(sb.toString());
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

}
