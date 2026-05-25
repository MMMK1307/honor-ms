package AltStd.Result;

import java.util.Iterator;

import static java.util.Collections.singletonList;

public final class Ok<T, E> implements Result<T, E> {
    T value;

    Ok(T value) {
        this.value = value;
    }

    public T get() {
       return this.value;
    }

    @Override
    public T get(T defaultV) {
        if(value == null) {
            return defaultV;
        }
        return value;
    }

    @Override
    public Iterator iterator() {
        return singletonList(this.value).iterator();
    }

    @Override
    public boolean isOk() {
        return true;
    }
}
