package colorwheel

import org.scalatest.funsuite.AnyFunSuite

class ColorWheelTest extends AnyFunSuite:
  test("hsvToRgb calculates pure red at 0 degrees") {
    assert(ColorWheel.hsvToRgb(0.0, 1.0, 1.0) == (255, 0, 0))
  }
  
  test("hsvToRgb calculates pure green at 120 degrees") {
    assert(ColorWheel.hsvToRgb(120.0, 1.0, 1.0) == (0, 255, 0))
  }