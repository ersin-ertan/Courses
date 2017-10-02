import processing.core.PApplet
import processing.core.PConstants


// Unit 3: Selection
// https://streamable.com/5wb5j for video

class U3_Sketch0:PApplet() {

    override fun settings() {
        size(1000, 500)
    }

    override fun setup() {
        colorMode(PConstants.HSB)
        noStroke()
    }

    var xLoc = 300F
    var yLoc = 100F
    var xVel = 10F
    var yVel = 1.2F
    val diam = 100F
    val radius = diam * 0.5F
    val afterImage = mutableListOf<Triple<Float, Float, Float>>() // x, y

    fun Float.reset255():Float = if (this > 255) 0F else this

    override fun draw() {
        background(0)

//        getRandCoords()
//        moveToMouseCoords()
        moveBall()

        drawAfterImage()
//        drawObject()


    }

    val tailLength = 120F
    val subSize = 1.0F / tailLength
    var sizeConstant = subSize

    val hueSubSize = 255 / tailLength
    val alphaSubSize = 255 / tailLength
    var hueConstant = 255F
    var alphaConstant = 0F

    private fun drawAfterImage() {

        afterImage.asReversed().forEachIndexed { index, pair ->
            // mod is the distance between each object
            if (index % 38 == 0) hueConstant++
            fill(pair.third, 255F, 255F, alphaConstant)
            alphaConstant += alphaSubSize
//            if (index % 2 == 0) // increase gaps
            ellipse(pair.first, pair.second, diam * sizeConstant, diam * sizeConstant) // x cord, y cord, width, height
            sizeConstant += subSize
        }

        sizeConstant = subSize
        alphaConstant = 0F
        hueConstant = hueConstant.reset255()


        afterImage.add(0, Triple(xLoc, yLoc, hueConstant))

        if (afterImage.size > tailLength) {
            afterImage.remove(afterImage.last())
        }

    }

    private fun moveBall() {
        xLoc += xVel
        yLoc += yVel
        if (xLoc + radius > width) xVel *= -1 else if (xLoc - radius < 0) xVel *= -1
        if (yLoc + radius > height) yVel *= -1 else if (yLoc - radius < 0) yVel *= -1
    }

    private fun moveToMouseCoords() {
        xLoc = mouseX.toFloat()
        yLoc = mouseY.toFloat()
    }


    private fun drawObject() {
        ellipse(xLoc, yLoc, diam, diam) // x cord, y cord, width, height
    }

    private fun getRandCoords() {
        xLoc = random(width.toFloat())
        yLoc = random(height.toFloat())
    }
}

fun main(args:Array<String>) {
    PApplet.main(arrayOf("U3_Sketch0"))
}
