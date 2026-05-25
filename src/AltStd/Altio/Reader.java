package AltStd.Altio;

import AltStd.Result.Result;
import AltStd.TryParse.TryParse;
import AltStd.TryParse.ParseError;

import java.io.InputStream;
import java.util.Scanner;
import java.util.function.Function;

public class Reader implements AutoCloseable {
    private final Scanner Sc;

    public Reader() {
        Sc = new Scanner(System.in);
    }

    public Reader(InputStream outs) {
        Sc = new Scanner(outs);
    }

    public String String() {
        return Sc.nextLine();
    }

    public String String(String message) {
        Print.n(message);
        return String();
    }

    public Result<Integer, ParseError> Int() {
        return TryParse.TryInt(Sc.nextLine());
    }

    public Result<Integer, ParseError> Int(String message) {
        Print.n(message);
        return Int();
    }

    public int UntilInt(String inputMessage, String retryMessage, Function<Integer, Boolean> requirement) {
        Result<Integer, ParseError> input = Int(inputMessage);
        while(input.isErr() || !requirement.apply(input.get())) {
            input = Int(retryMessage);
        }
        return input.get();
    }

    public int UntilInt(String inputMessage, String retryMessage) {
        return UntilInt(inputMessage, retryMessage, (a) -> true);
    }

    public Result<Float, ParseError> Float() {
        return TryParse.TryFloat(Sc.nextLine());
    }

    public Result<Float, ParseError> Float(String message) {
        Print.n(message);
        return Float();
    }

    public float UntilFloat(String inputMessage, String retryMessage, Function<Float, Boolean> requirement) {
        Result<Float, ParseError> input = Float(inputMessage);
        while(input.isErr() || !requirement.apply(input.get())) {
            input = Float(retryMessage);
        }
        return input.get();
    }

    public float UntilFloat(String inputMessage, String retryMessage) {
        return UntilFloat(inputMessage, retryMessage, (a) -> true);
    }

    public Result<Double, ParseError> Double() {
        return TryParse.TryDouble(Sc.nextLine());
    }

    public Result<Double, ParseError> Double(String message) {
        Print.n(message);
        return Double();
    }

    public double UntilDouble(String inputMessage, String retryMessage, Function<Double, Boolean> requirement) {
        Result<Double, ParseError> input = Double(inputMessage);
        while(input.isErr() || !requirement.apply(input.get())) {
            input = Double(retryMessage);
        }
        return input.get();
    }

    public double UntilDouble(String inputMessage, String retryMessage) {
        return UntilDouble(inputMessage, retryMessage, (a) -> true);
    }

    @Override
    public void close() {
        Sc.close();
    }
}
