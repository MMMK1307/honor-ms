package AltStd.Result;

import java.util.Iterator;

import static java.util.Collections.emptyIterator;

public final class Err<T, E> implements Result<T, E> {
    E value;

    Err(E value) {
        this.value = value;
    }

    public T get() {
        return null;
    }

    @Override
    public T get(T defaultT) {
        return defaultT;
    }

    public E getE() {
        return this.value;
    }

    @Override
    public Iterator iterator() {
        return emptyIterator();
    }

    @Override
    public boolean isOk() {
        return false;
    }
}
