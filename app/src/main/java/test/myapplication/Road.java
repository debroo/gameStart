package test.myapplication;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

public class Road {
    int y = 0;
    ArrayList<Bar> bars = new ArrayList<>();

    int speed = 10;

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Road() {

    }

    public void draw(Canvas canvas) {
        if (bars.isEmpty()) {
            int countOfRows = canvas.getHeight() / 200;
            for (int i = 0; i <= countOfRows; i++) {
                bars.add(new Bar( i * 200));
            }
        }
        canvas.drawColor(Color.BLACK);
        Paint paint = new Paint();

        for( Bar bar : bars) {
            bar.keepTrack(canvas);
            bar.draw(canvas,paint);
            bar.y = bar.y + speed;
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
            if (y > canvas.getHeight() - 100) y = -100;
        }

        public void draw(Canvas canvas, Paint paint) {
            paint.setColor(Color.WHITE);
            for (int i = 1; i < 3; i++) {
                canvas.drawRect(canvas.getWidth() / 3 * i, y, canvas.getWidth() / 3 * i + 20, y + 80, paint);
            }
        }
    }
}
