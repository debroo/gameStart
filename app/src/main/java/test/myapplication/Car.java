package test.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;


public class Car {

    int x = 0;
    int y = 0;
    int width = 150;

    public Rect getCarRectangle() {
        return carRectangle;
    }

    int height = 284;
    int carPrevriousPosition = 2;
    int carPosition = 2;
    int lastX = 0;
    private DisplayMetrics metrics;
    private Bitmap carImage;
    private int speed;
    private Rect carRectangle;

    public Car(Bitmap carImage, int speed) {
        this.carImage = carImage;
        this.speed = speed;
    }

    public void draw(Canvas canvas, DisplayMetrics metrics) {
        this.metrics = metrics;

        if (x == 0 || y == 0) {
            x = canvas.getWidth() / 2 - width / 2;
            y = canvas.getHeight() - height - 150;
        }

        /*int onethird = canvas.getWidth() / 3;
        if (lastX <= onethird) carPosition = 1;
        if (lastX > onethird && lastX < 2 * onethird) carPosition = 2;
        if (lastX >= 2 * onethird) carPosition = 3;*/




        if (carPosition == 1) {
            int destX = canvas.getWidth() / 3 / 2 - width / 2;
            MoveCar(destX);
            //this.x = canvas.getWidth() / 3 / 2 - width / 2;
        } else if (carPosition == 2) {
            int destX = (canvas.getWidth() / 2) - width / 2;
            MoveCar(destX);
        } else if (carPosition == 3) {
            int destX = canvas.getWidth() - canvas.getWidth() / 3 / 2 - width / 2;
            MoveCar(destX);
        }

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        //canvas.drawRect(x, y, x + width, y + height, paint);
        carRectangle =  new Rect(x, y, x + width, y + height);
        canvas.drawBitmap(carImage, null, carRectangle, paint);
    }

    private void MoveCar(int destX) {
        if (x != destX) {
            if (x > destX) {
                for (int i = 0 ; i < 20; i++) {
                    this.x--;
                }
            } else {
                for (int i = 0 ; i < 20; i++) {
                    this.x++;
                }
            }
        }
    }

    public void move(int x) {
        int half = metrics.widthPixels /2;

        if (x <= half && carPosition > 1) carPosition--;
        if (x > half && carPosition < 3) carPosition++;
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
