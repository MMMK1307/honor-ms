package AltStd.Result;

public sealed interface Result<T, E> extends Iterable permits Ok, Err {
    static <T, E> Ok<T, E> ok(T value) {
        return new Ok(value);
    }

    static <T, E> Err<T, E> err(E value) {
        return new Err(value);
    }

    T get();

    T get(T defaultV);

    boolean isOk();

    default boolean isErr() {
        return !isOk();
    }
}
