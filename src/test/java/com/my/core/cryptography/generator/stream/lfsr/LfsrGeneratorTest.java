package com.my.core.cryptography.generator.stream.lfsr;

import com.google.common.collect.Lists;
import com.my.core.cryptography.generator.stream.property.LfsrGeneratorProperty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.BitSet;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;

public class LfsrGeneratorTest {
    private LfsrGenerator lfsrGenerator;
    private Properties properties;

    @Before
    public void setUp() throws Exception {
        lfsrGenerator = new LfsrGenerator();
        properties = new Properties();
    }

    @Test
    public void generateWhenPolynomialIs1001AndInitialStateIs1010() throws Exception {
        properties.setProperty(LfsrGeneratorProperty.POLYNOMIAL.name(), "1001");
        properties.setProperty(LfsrGeneratorProperty.SEED.name(), "1010");
        List<Integer> order = Lists.newArrayList(3, 0);
        BitSet state = new BitSet(4);
        state.set(0, true);
        state.set(0, false);
        state.set(0, true);
        state.set(0, false);
        boolean[] generated = lfsrGenerator.generate(properties, 4);
        Assert.assertThat(generated[0], is(true));
        Assert.assertThat(generated[1], is(false));
        Assert.assertThat(generated[2], is(false));
        Assert.assertThat(generated[3], is(true));
    }

}