package test.myapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class Car {

    int x = 0;
    int y = 0;
    int width = 60;
    int height = 140;
    int carPosition = 2;
    int lastX = 0;

    public Car() {

    }

    public void draw(Canvas canvas) {
        if (x == 0 || y == 0) {
            x = canvas.getWidth() / 2 - width / 2;
            y = canvas.getHeight() - height - 150;
        }

        int onethird = canvas.getWidth() / 3;
        if (lastX <= onethird) carPosition = 1;
        if (lastX > onethird && lastX < 2 * onethird) carPosition = 2;
        if (lastX >= 2 * onethird) carPosition = 3;


        if (carPosition == 1) {
            this.x = canvas.getWidth() / 3 / 2 - width / 2;
        } else if (carPosition == 2) {
            this.x = (canvas.getWidth() / 2) - width / 2;
        } else if (carPosition == 3) {
            this.x = canvas.getWidth() - canvas.getWidth() / 3 / 2 - width / 2;
        }

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawRect(x, y, x + width, y + height, paint);
    }

    public void move(int x, Canvas canvas) {
        this.lastX = x;
        System.out.println("move to :" + x);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getCarPosition() {
        return carPosition;
    }
}
