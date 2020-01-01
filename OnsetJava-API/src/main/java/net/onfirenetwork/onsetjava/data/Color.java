package net.onfirenetwork.onsetjava.data;

public class Color {
    private int red;
    private int green;
    private int blue;
    private int alpha;
    public Color(int red, int green, int blue, int alpha){
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }
    public Color(int red, int green, int blue){
        this(red, green, blue, 255);
    }
    public int getRed() {
        return red;
    }
    public int getGreen() {
        return green;
    }
    public int getBlue() {
        return blue;
    }
    public int getAlpha() {
        return alpha;
    }
    public void setRed(int red) {
        this.red = red;
    }
    public void setGreen(int green) {
        this.green = green;
    }
    public void setBlue(int blue) {
        this.blue = blue;
    }
    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }
    public int toHexRGB(){
        return (65536 * red)+(256 * green)+blue;
    }
    public int toHexRGBA(){
        return toHexRGB()+(16777216 * alpha);
    }
    public static Color fromHex(int hex){
        return new Color((hex % 16777216) / 65536, ((hex % 16777216) % 65536) / 255, ((hex % 16777216) % 65536) % 256, hex / 16777216);
    }
}
