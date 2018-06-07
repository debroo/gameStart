package test.myapplication;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.Iterator;

public class Road {
    int y = -200;
    int cycle = 0;
    ArrayList<Bar> bars = new ArrayList<>();

    int speed = 10;
    Bitmap roadImg;

    public void setSpeed(int speed) {
        this.speed = speed + 4;
    }

    public Road(Bitmap img) {
        this.roadImg = img;

    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        int my = y;
        while (my < canvas.getHeight()) {
            canvas.drawBitmap(roadImg, null, new Rect(0, my, canvas.getWidth(), my + 200), paint);
            my += 200;
        }
        y = y + speed;
        cycle = cycle + speed;
        if (cycle >= 200) {
            y = -200 + y;
            if (y > -400) y -= 400;
            cycle = 0;
        }

        paint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, canvas.getWidth(), 100, paint);
        paint.setColor(Color.BLUE);
        canvas.drawRect(0, canvas.getHeight() - 100, canvas.getWidth(), canvas.getHeight(), paint);

    }

    public void update() {
    }

    public int getSpeed() {
        return speed;
    }

    class Bar {
        public int y;

        public Bar(int pos) {
            this.y = pos;
        }

        public void keepTrack(Canvas canvas) {
            if (y >= canvas.getHeight()) y = 0;
        }

        public void draw(Canvas canvas, Paint paint) {
           /* paint.setColor(Color.WHITE);
            for (int i = 1; i < 3; i++) {
                canvas.drawRect(canvas.getWidth() / 3 * i, y, canvas.getWidth() / 3 * i + 20, y + 80, paint);
            }*/

            canvas.drawBitmap(roadImg, null, new Rect(0, y, canvas.getWidth(), y + 205), paint);
        }
    }
}
