package AltStd.Altio;

import AltStd.BColor;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.Map;

public class Print {
    private static PrintStream PStream = System.out;

    public static void n(String str) {
        PStream.print(str);
    }

    public static void n(BColor foreground, String str) {
        n(foreground.foreground() + str);
    }

    public static void nl(String str) {
        PStream.println(str);
    }
    public static void nl(BColor foreground, String str) {
        nl(foreground.foreground() + str);
    }

    public static void nl() {
        PStream.println();
    }

    public static void f(String str, Object... values) {
        PStream.printf(str, values);
    }

    public static void f(BColor foreground, String str, Object... values) {
        n(foreground.foreground());
        f(str, values);
    }

    public static void fl(String str, Object... values) {
        PStream.printf((str) + "%n", values);
    }

    public static void fl(BColor foreground, String str, Object... values) {
        n(foreground.foreground());
        fl(str, values);
    }

    public static void m(String str, Object... values) {
        PStream.print(MessageFormat.format(str, values));
    }

    public static void m(BColor foreground, String str, Object... values) {
        n(foreground.foreground());
        m(str, values);
    }

    public static void ml(String str, Object... values) {
        PStream.println(MessageFormat.format(str, values));
    }

    public static void ml(BColor foreground, String str, Object... values) {
        n(foreground.foreground());
        ml(str, values);
    }

    public static void s(Object... values) {
        StringBuilder sb = new StringBuilder();
        for(var v : values) {
            sb.append(v);
        }
        PStream.print(sb);
    }

    public static void s(BColor foreground, Object... values) {
        n(foreground.foreground());
        s(values);
    }

    public static void sl(Object... values) {
        StringBuilder sb = new StringBuilder();
        for(var v : values) {
            sb.append(v);
        }
        PStream.println(sb);
    }

    public static void sl(BColor foreground, Object... values) {
        n(foreground.foreground());
        sl(values);
    }

    public static void d(String str, Map<String, Object> values) {
        for(String v : values.keySet()) {
            String key = "{" + v + "}";
            str = str.replace(key, ("" + values.get(v)));
        }
        PStream.print(str);
    }

    public static void dl(String str, Map<String, Object> values) {
        for(String v : values.keySet()) {
            str = str.replace("{" + v + "}", ("" + values.get(v)));
        }
        PStream.println(str);
    }
}
