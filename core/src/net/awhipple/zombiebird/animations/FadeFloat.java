package net.awhipple.zombiebird.animations;

public class FadeFloat extends Animation {

  float floatSpeed, fadeInSpeed, fadeOutSpeed, finishedFadeInTime, finishedFloatTime;

  public FadeFloat(float floatDistance, float fadeInTime, float floatTime, float fadeOutTime) {
    super(fadeInTime + floatTime + fadeOutTime, 0, 0, 0);

    this.fadeInSpeed = 1f / fadeInTime;
    this.fadeOutSpeed = 1f / fadeOutTime;

    this.finishedFadeInTime = fadeInTime;
    this.finishedFloatTime = fadeInTime + floatTime;

    this.floatSpeed = floatDistance / this.duration;
  }

  public void update(float delta) {
    super.update(delta);
    y -= floatSpeed * delta;

    if(progress < finishedFadeInTime) {
      a += fadeInSpeed * delta;
    } else if(progress >= finishedFloatTime) {
      a -= fadeOutSpeed * delta;
    } else {
      a = 1f;
    }

  }
}
