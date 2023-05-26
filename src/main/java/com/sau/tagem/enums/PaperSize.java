package com.sau.tagem.enums;

public enum PaperSize {
    A4(210, 297),
    A5(148, 210);

    private final int width;
    private final int height;

    PaperSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
