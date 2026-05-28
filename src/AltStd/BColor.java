package AltStd;

public record BColor(int r, int g, int b) {
    @Override
    public String toString() {
        return r+";"+g+";"+b;
    }
    public String foreground() {
        return "\033[38;2;" + toString() +"m";
    }
    public String background() {
        return "\033[48;2;" + toString() +"m";
    }
}
