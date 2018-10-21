package Luces;

public class ColorRGB  { 
	private byte b;
	private byte r;
	private byte g;
	public ColorRGB(int r, int g, int b) {
		this.r=(byte) ((r>255)?255:r);
		this.g=(byte) ((g>255)?255:g);
		this.b=(byte) ((b>255)?255:b);
	}
	
	public final static ColorRGB RED= new ColorRGB(255,0,0);
	public final static ColorRGB BLUE= new ColorRGB(0,0,255);
	public final static ColorRGB GREEN= new ColorRGB(0,255,0);
	public final static ColorRGB BLACK= new ColorRGB(0,0,0);
	public final static ColorRGB WHITE= new ColorRGB(255,255,255);
	
	public byte getBlue() {return b;}
	public byte getRed() {return r;}
	public byte getGreen() {return g;}
	
}