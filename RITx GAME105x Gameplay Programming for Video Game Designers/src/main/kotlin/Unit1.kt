import processing.core.PApplet

// Unit 1: Drawing and Variables

class Sketch0:PApplet() {

    val ySize = height/2F

    override fun settings() {
        size(500, 500)
    }

    override fun setup() {}

    override fun draw() {
        background(255)
        fill(0F, 200F, 200F)

        strokeWeight(10F)
        rect(100F, 200F, 200F, 200F)
        line(100F, 200F, 300F, 400F)


        strokeWeight(5F)
        fill(0F, 255F, 255F)
        ellipse(100F, 200F, width/2F, ySize)
    }
}

fun main(args:Array<String>) {
    PApplet.main(arrayOf("Sketch0"))
}
