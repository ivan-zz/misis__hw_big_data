package colorwheel

import java.io.PrintWriter
import scala.math.*

object ColorWheel:
  def hsvToRgb(h: Double, s: Double, v: Double): (Int, Int, Int) =
    val c = s * v
    val x = c * (1 - abs(((h / 60.0) % 2) - 1))
    val m = v - c
    
    val (r, g, b) = 
      if h < 60 then (c, x, 0.0)
      else if h < 120 then (x, c, 0.0)
      else if h < 180 then (0.0, c, x)
      else if h < 240 then (0.0, x, c)
      else if h < 300 then (x, 0.0, c)
      else (c, 0.0, x)

    (((r + m) * 255).toInt, ((g + m) * 255).toInt, ((b + m) * 255).toInt)

  def generate(size: Int = 300, path: String = "wheel.ppm"): Unit =
    val radius = size / 2.0
    val pw = new PrintWriter(path)
    pw.println(s"P3\n$size $size\n255")
    
    for y <- 0 until size; x <- 0 until size do
      val (dx, dy) = (x - radius, y - radius)
      val dist = hypot(dx, dy)
      
      if dist <= radius then
        val hue = (toDegrees(atan2(dy, dx)) + 360) % 360
        val (red, grn, blu) = hsvToRgb(hue, dist / radius, 1.0)
        pw.println(s"$red $grn $blu")
      else pw.println("0 0 0")
      
    pw.close()


@main def runColorWheel(): Unit =
  println("Generating wheel.ppm...")
  ColorWheel.generate(500, "wheel.ppm")
  println("Done!")