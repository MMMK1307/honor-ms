package AltStd.TryParse;

import AltStd.Result.Result;

public class TryParse {
    public static Result<Integer, ParseError> TryInt(String v) {
        try {
            return Result.ok(Integer.parseInt(v));
        } catch (NumberFormatException e) {
            return Result.err(ParseError.Integer);
        }
    }

    public static Result<Float, ParseError> TryFloat(String v) {
        try {
            return Result.ok(Float.parseFloat(v));
        } catch (NumberFormatException e) {
            return Result.err(ParseError.Float);
        }
    }

    public static Result<Double, ParseError> TryDouble(String v) {
        try {
            return Result.ok(Double.parseDouble(v));
        } catch (NumberFormatException e) {
            return Result.err(ParseError.Double);
        }
    }
}
