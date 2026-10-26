package chess;

public enum Color {
    BLACK,
    WHITE;

    public Color getOpposite() {
        return switch (this) {
            case WHITE -> BLACK;
            case BLACK -> WHITE;
        };
    }

    public String background() {
        return switch (this) {
            case WHITE -> "\u001b[41m";
            case BLACK -> "\u001b[42m";
        };
    }

    public String foreground() {
        return switch (this) {
            case WHITE -> "\u001b[37m";
            case BLACK -> "\u001b[30m";
        };
    }

    public String reset() {
        return "\u001b[0m";
    }
}
