package com.my.core.cryptography.matrix.util;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;


public class CharacterMatrixTest {
    @Test
    public void setRow() throws Exception {
        CharacterMatrix characterMatrix = new CharacterMatrix(5, 5);
        characterMatrix.setRow(0, "coh");
        Assert.assertThat(characterMatrix.getRow(0), is("coh"));
    }

    @Test
    public void whenMatrix5x5AndTextCryptographyosaGetBy0ColumnShouldBeCoh() throws Exception {
        CharacterMatrix characterMatrix = new CharacterMatrix(5, 5, "cryptographyosa");
        Assert.assertThat(characterMatrix.getColumn(0), is("coh"));
    }

    @Test
    public void setByColumnTest() throws Exception {
        CharacterMatrix characterMatrix = new CharacterMatrix(5, 5);
        characterMatrix.setColumn(0, "coh");
        Assert.assertThat(characterMatrix.getColumn(0), is("coh"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setByColumnTooLongDataTest() throws Exception {
        CharacterMatrix characterMatrix = new CharacterMatrix(5, 5);
        characterMatrix.setColumn(0, "cohhhh");
    }
}