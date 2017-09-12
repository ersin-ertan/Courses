import processing.core.PApplet

// Unit 2: Stuff that moves

class U2_Sketch0:PApplet() {


    override fun settings() {
        size(500, 500)
    }

    override fun setup() {

    }

    var xLoc = 0F
    var yLoc = 0F

    override fun draw() {
        background(0)

//        getRandCoords()
        moveToMouseCoords()

        drawObject()

    }

    private fun moveToMouseCoords() {
        xLoc = mouseX.toFloat()
        yLoc = mouseY.toFloat()
    }

    private fun drawObject() {
        ellipse(xLoc, yLoc, 50F, 50F)
    }

    private fun getRandCoords() {
        xLoc = random(width.toFloat())
        yLoc = random(height.toFloat())
    }
}

fun main(args:Array<String>) {
    PApplet.main(arrayOf("U2_Sketch0"))
}
