package com.my.core.cryptography.railfence.util;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RailFenceUtil {
    public static List<Integer> upDownList(int depth, int length) {
        List<Integer> upDownList = Lists.newArrayList();
        if (depth <= 1) return getIdentityList(length);
        for (int i = 0; i < length; i += 2 * depth- 2) {
            upDownList.addAll(range(1, depth));
            upDownList.addAll(range(depth, 2));
        }
        return truncate(upDownList, length);
    }

    private static List<Integer> getIdentityList(int length) {
        Integer[] integers = new Integer[length];
        Arrays.fill(integers, 1);
        return Arrays.asList(integers);
    }

    public static List<Integer> truncate(List<Integer> upDownList, int length) {
        return upDownList.subList(0, length);
    }

    public static List<Integer> range(int from, int to) {
        if (from == to) return new ArrayList<>();
        if (from > to) {
            return IntStream.range(to, from).map(i -> from - i + to - 1).boxed().collect(Collectors.toList());
        } else {
            return IntStream.rangeClosed(from, to).boxed().collect(Collectors.toList());
        }
    }

}
