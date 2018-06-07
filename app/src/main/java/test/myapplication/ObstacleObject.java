package test.myapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

class ObstacleObject {
    public int y;
    public int x;
    public int width = 50;
    public int height = 100;
    public int pos = 0;

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


    public ObstacleObject(int y, int pos) {
        this.y = y;
        this.pos = pos;
    }

    public void keepTrack(Canvas canvas) {
        if (y > canvas.getHeight() - 100) {
            y = -100;
            this.pos =  new Random().nextInt(3) + 1;
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.GREEN);


        if (pos == 1) {
            this.x = canvas.getWidth() / 3 / 2 - width / 2;
        } else if (pos == 2) {
            this.x = (canvas.getWidth() / 2) - width / 2;
        } else if (pos == 3) {
            this.x = canvas.getWidth() - canvas.getWidth() / 3 / 2 - width / 2;
        }

        canvas.drawRect(x, y, x + width, y + height, paint);
    }
}
