import processing.core.PApplet
import processing.core.PConstants
import java.time.Duration
import java.time.Instant
import java.time.temporal.ChronoUnit


// Unit 5: Capstone Pong Game
// https://streamable.com/imygq

class Balll(val applet:PApplet):Moveable {

    override fun move() {

        applet.strokeWeight(2F)
        if (isPlaying) moveBall()

        if (isPlaying || afterImage.size > 0) {
            drawAfterImage()
            addItemToAfterImage()
        }

    }

    var isPlaying = true

    // Location
    var xLoc = 0F
    var yLoc = 0F

    // Velocity
    var xVel = 10F
    var yVel = 1.2F

    // Size
    val diam = 75F
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
        if (xLoc + radius > applet.width) {
            xVel *= -1.1F; (applet as U5_Sketch0).incScore()
        } else if (xLoc - radius < 0) {
            isPlaying = false
            xVel = 0F
            yVel = 0F
        }
        if (yLoc + radius > applet.height) {
            yVel *= -1.1F; (applet as U5_Sketch0).incScore()
        } else if (yLoc - radius < 0) {
            yVel *= -1.1F; (applet as U5_Sketch0).incScore()
        }
    }

    fun setup(x:Float = 300F, y:Float = 100F) {

        xLoc = x
        yLoc = y
        applet.colorMode(PConstants.HSB)
    }

    private fun addItemToAfterImage() {

        if (isPlaying) {
            afterImage.add(0, Triple(xLoc, yLoc, hue))
            if (afterImage.size > tailLength) afterImage.remove(afterImage.last())
        } else if (afterImage.size > 0) {
            afterImage.remove(afterImage.last())
        }
    }

}

class Paddle(val applet:PApplet):Moveable {

    var locY = applet.height / 2

    override fun move() {
        locY = applet.mouseY
        draw()
    }

    val lineXPos = 30F
    val lineHeight = applet.height.toFloat() // ??
    fun draw() {
        applet.stroke(255)
        applet.strokeWeight(25F)
        applet.line(lineXPos, locY - lineHeight, lineXPos, locY + lineHeight)
    }

}

class U5_Sketch0:PApplet() {

    var score = 0
    fun incScore() = score++

    var isGameRunning = true
    val paddle = Paddle(this)
    val ball = Balll(this)
    val textDuration = Duration.of(2, ChronoUnit.SECONDS)
    var startEndGameTime = Instant.now().plus(textDuration)
    var textWidth:Float = 1F
    override fun settings() {
        size(2000, 800)
    }

    override fun setup() {

        textSize(height * 0.2F)

        ball.setup()
        ball.hue = (1..255).random()
        ball.yVel = 3F
        ball.xVel = 7F
    }

    fun collisionDetection() {
        if ((ball.xLoc - ball.radius <= paddle.lineXPos) && (paddle.locY + paddle.lineHeight > ball.yLoc + ball.radius) &&
                (paddle.locY - paddle.lineHeight < ball.yLoc - ball.radius)) ball.xVel *= -1
    }

    override fun draw() {

        background(0)

        if (Instant.now().isBefore(startEndGameTime)) {
            fill(255)
            text("Start", width * 0.4F, height * 0.4F)
        }

        collisionDetection()
        ball.move()
        paddle.move()

        if (!ball.isPlaying) {
            if (isGameRunning) {
                fill(255)
                text("Game Over", width * 0.3F, height * 0.3F)
                text("Score: $score", width * 0.38F, height * 0.6F)

            }
        }
    }
}

fun main(args:Array<String>) {
    PApplet.main(arrayOf("U5_Sketch0"))
}
