package test.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

class ObstacleObject {
    public int y = -284;
    public int x;
    public int width = 150;
    public int height = 284;
    public int pos = 0;
    private Rect obstacleRect;
    private Bitmap obstacleImage;
    int cycle = 0;
    Obstacles parent;

    public int getPos() {
        return pos;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public ObstacleObject(int y, int pos, Bitmap obstacleImage, Obstacles obstacles) {
        this.y = y;
        this.pos = pos;
        this.obstacleImage = obstacleImage;
        int height = 0;
        this.parent = obstacles;

    }

    public void keepTrack(Canvas canvas, int speed) {
        if (y > canvas.getHeight()) {
            if (this.parent.getLastR() > 800) {
                y = -284;
                this.pos = new Random().nextInt(3) + 1;
                this.parent.setLastR(0);
            }
        }
    }

    public void draw(Canvas canvas, Paint paint, int speed) {
        paint.setColor(Color.GREEN);


        if (pos == 1) {
            this.x = canvas.getWidth() / 3 / 2 - width / 2;
        } else if (pos == 2) {
            this.x = (canvas.getWidth() / 2) - width / 2;
        } else if (pos == 3) {
            this.x = canvas.getWidth() - canvas.getWidth() / 3 / 2 - width / 2;
        }

        //canvas.drawRect(x, y, x + width, y + height, paint);
        obstacleRect = new Rect(x, y, x + width, y + height);
        canvas.drawBitmap(obstacleImage, null, obstacleRect, paint);

        y = y + speed;
    }

    public Rect getObstacleRect() {
        return obstacleRect;
    }
}
