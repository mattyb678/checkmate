package xyz.mattyb.checkmate;

import java.util.Collection;

public class Index {
    private int size = -1;
    private int index = -1;
    private boolean indexableNull = false;
    private String type = "";

    public Index() {
        // empty on purpose
    }

    public void setIndex(final int index) {
        this.index = index;
    }

    public <T> void calcSize(final T[] array) {
        type = "array";
        if (array == null) {
            size = 0;
            indexableNull = true;
        } else {
            size = array.length;
        }
    }

    public <T extends Collection<?>> void calcSize(final T collection) {
        type = "collection";
        if (collection == null) {
            size = 0;
            indexableNull = true;
        } else {
            size = collection.size();
        }
    }

    public <T extends CharSequence> void calcSize(final T chars) {
        type = "character sequence";
        if (chars == null) {
            size = 0;
            indexableNull = true;
        } else {
            size = chars.length();
        }
    }

    public int getSize() {
        return size;
    }

    public int getIndex() {
        return index;
    }

    public String getType() {
        return type;
    }

    public boolean isNull() {
        return indexableNull;
    }
}
