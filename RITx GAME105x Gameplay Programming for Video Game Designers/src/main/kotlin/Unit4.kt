import processing.core.PApplet
import processing.core.PConstants
import java.util.*


// Unit 4: Classes
// https://streamable.com/4btnc

interface Moveable {
    fun move()
}

fun ClosedRange<Int>.random() = (Random().nextInt(endInclusive - start) + start).toFloat()

class Ball(val applet:PApplet):Moveable {

    override fun move() {
        moveBall()
        drawAfterImage()
        addItemToAfterImage()
    }

    // Location
    var xLoc = 0F
    var yLoc = 0F

    // Velocity
    var xVel = 10F
    var yVel = 1.2F

    // Size
    val diam = 50F
    val radius = diam * 0.5F

    // Tail

    val tailLength = 40F
    val afterImage = mutableListOf<Triple<Float, Float, Float>>() // x, y, hue

    val subSize = 1.0F / tailLength

    val elementsPer255 = 255 / tailLength
    var sizeConstant = subSize
    var hue = 0F
    var alpha = 0F

    fun reset255(float:Float):Float = if (float > 255) 0F else float

    private fun drawAfterImage() {

        afterImage.asReversed().forEachIndexed { index, pair ->
            // mod is the distance between each object
            if (index % 38 == 0) hue++
            applet.fill(pair.third, 255F, 255F, alpha)
            alpha += elementsPer255
//            if (index % 2 == 0) // increase gaps
            applet.ellipse(pair.first, pair.second, diam * sizeConstant, diam * sizeConstant)
            sizeConstant += subSize
        }
        sizeConstant = subSize
        alpha = 0F
        hue = reset255(hue)
    }

    private fun moveBall() {
        xLoc += xVel
        yLoc += yVel
        if (xLoc + radius > applet.width) xVel *= -1 else if (xLoc - radius < 0) xVel *= -1
        if (yLoc + radius > applet.height) yVel *= -1 else if (yLoc - radius < 0) yVel *= -1
    }

    fun setup(x:Float = 300F, y:Float = 100F) {

        xLoc = x
        yLoc = y
        applet.colorMode(PConstants.HSB)
        applet.noStroke()
    }

    private fun addItemToAfterImage() {

        afterImage.add(0, Triple(xLoc, yLoc, hue))
        if (afterImage.size > tailLength) afterImage.remove(afterImage.last())
    }

}

class U4_Sketch0:PApplet() {

    val listOfBall = List(10, {
        Ball(this)
    })

    override fun settings() {
        size(1000, 500)
    }

    override fun setup() {

        listOfBall.forEach {
            it.setup(
                    (listOfBall.first().diam.toInt()..400).random(),
                    (listOfBall.first().diam.toInt()..400).random())
            it.hue = (1..255).random()
            it.yVel = (1..4).random()
            it.xVel = (4..9).random()
        }
    }


    override fun draw() {

        background(0)
        listOfBall.forEach(Ball::move)
    }
}

fun main(args:Array<String>) {
    PApplet.main(arrayOf("U4_Sketch0"))
}
