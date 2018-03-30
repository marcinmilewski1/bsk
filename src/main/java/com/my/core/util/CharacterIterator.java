package com.my.core.util;

import java.util.Iterator;

public class CharacterIterator implements Iterator<Character> {
    private final String str;
    private int pos = 0;

    public CharacterIterator(String str) {
        this.str = str;
    }

    public Character next() {
        return str.charAt(pos++);
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public String getNext(int number) {
        String toReturn = null;
        if (pos + number < str.length()) {
            toReturn =  str.substring(pos, pos + number);
            pos += number;
        } else if (!hasNext()) {
            toReturn = "";
        } else {
            toReturn =  str.substring(pos);
            pos = str.length();
        }
        return toReturn;
    }

    public boolean hasNext() {
        return pos < str.length();
    }
}
