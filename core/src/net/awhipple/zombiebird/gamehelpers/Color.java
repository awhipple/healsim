package net.awhipple.zombiebird.gamehelpers;

public class Color {
  private float r, g, b;

  public Color(float r, float g, float b) {
    setColor(r, g, b);
  }

  public Color(int r, int g, int b) {
    setColor(r, g, b);
  }

  public void setColor(float r, float g, float b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  public void setColor(int r, int g, int b) {
    this.r = r / 255.0f;
    this.g = g / 255.0f;
    this.b = b / 255.0f;
  }

  public float getR() { return r; }
  public float getG() { return g; }
  public float getB() { return b; }
}
