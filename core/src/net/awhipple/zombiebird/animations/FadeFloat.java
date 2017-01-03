package net.awhipple.zombiebird.animations;

public class FadeFloat extends Animation {

  float floatSpeed, fadeInSpeed, fadeOutSpeed, fadeInTime, floatTime, fadeOutTime, finishedFloatTime;

  public FadeFloat(float floatDistance, float fadeInTime, float floatTime, float fadeOutTime) {
    super(fadeInTime + floatTime + fadeOutTime, 0, 0, 0);

    this.fadeInSpeed = 1f / fadeInTime;
    this.fadeOutSpeed = 1f / fadeOutTime;

    this.fadeInTime = fadeInTime;
    this.floatTime = floatTime;
    this.fadeOutTime = fadeOutTime;
    this.finishedFloatTime = fadeInTime + floatTime;

    this.floatSpeed = floatDistance / this.duration;
  }

  public void update(float delta) {
    super.update(delta);
    y -= floatSpeed * delta;

    if(progress >= finishedFloatTime) {
      a -= fadeOutSpeed * delta;
    } else if(progress <= fadeInTime) {
      a += fadeInSpeed * delta;
    }

  }
}
