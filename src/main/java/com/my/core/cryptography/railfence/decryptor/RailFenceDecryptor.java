package com.my.core.cryptography.railfence.decryptor;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.my.core.cryptography.Decryptor;
import com.my.core.cryptography.railfence.encryptor.RailFenceEncryptor;
import com.my.core.cryptography.railfence.properties.RailfenceProperty;
import com.my.core.cryptography.railfence.util.RailFenceUtil;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class RailFenceDecryptor implements Decryptor {
    private Properties properties;

    public RailFenceDecryptor() {
    }

    @Override
    public String decrypt(String data, Properties properties) {
        this.properties = properties;
        String depth = properties.getProperty(RailfenceProperty.DEPTH.name());
        if (depth == null || depth.isEmpty() || data.isEmpty()) throw new IllegalArgumentException();

        return decryptInternal(Integer.valueOf(depth), data);
    }

    private String decryptInternal(Integer depth, String data) {
        List<Integer> from1ToDataLengthNumbers = RailFenceUtil.range(0, data.length() - 1);
        List<Integer> encryptedIndexes = getByRow(RailFenceEncryptor.getRowIntegerRailFenceOrderMap(RailFenceUtil.upDownList(depth, from1ToDataLengthNumbers.size()), from1ToDataLengthNumbers, depth));
        return getOrderBy(encryptedIndexes, data);
    }

    private List<Integer> getByRow(Multimap<Integer, Integer> rowIntegerRailFenceOrderMap) {
        Set<Integer> sortedKeySet = rowIntegerRailFenceOrderMap.keys().stream().sorted().collect(Collectors.toCollection(TreeSet<Integer>::new));
        List<Integer> indexes = Lists.newArrayList();
        sortedKeySet.forEach(e -> indexes.addAll(rowIntegerRailFenceOrderMap.get(e)));
        return indexes;
    }

    private String getOrderBy(List<Integer> encryptedIndexes, String data) {
        Map<Integer, Integer> decryptedIndexByEncryptedIndex = getDecryptedIndexByEncryptedIndex(encryptedIndexes);
        StringBuilder sb = new StringBuilder(data.length());
        for (int i = 0; i < data.length(); i++) {
            sb.append(data.charAt(decryptedIndexByEncryptedIndex.get(i)));
        }
        return sb.toString();
    }

    private Map<Integer, Integer> getDecryptedIndexByEncryptedIndex(List<Integer> encryptedIndexes)    {
        Map<Integer, Integer> decryptedIndexByEncryptedIndex = Maps.newTreeMap();
        IntConsumer putConsumer = new IntConsumer() {
            @Override
            public void accept(int value) {
                decryptedIndexByEncryptedIndex.put(value, encryptedIndexes.lastIndexOf(value));
            }
        };

        IntStream.range(0, encryptedIndexes.size())
                .iterator()
                .forEachRemaining(putConsumer);
        return decryptedIndexByEncryptedIndex;
    }

    @Override
    public File decrypt(File data, Properties properties) {
        return null;
    }

}
