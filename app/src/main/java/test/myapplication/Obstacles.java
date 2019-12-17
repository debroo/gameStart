package test.myapplication;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Random;

public class Obstacles {
    int y = 0;
    ArrayList<ObstacleObject> obstaclesList = new ArrayList<>();
    int speed = 30;
    int lastR = 0;

    public int getLastR() {
        return lastR;
    }

    public void setLastR(int lastR) {
        this.lastR = lastR;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Obstacles(Bitmap obstacleImage) {
        int countOfRows = 4;


        for (int i = 0; i <= countOfRows; i++) {
            int pos = new Random().nextInt(3) + 1;
            obstaclesList.add(new ObstacleObject( i * 800, pos, obstacleImage, this));
        }
    }






    public void draw(Canvas canvas) {
        Paint paint = new Paint();

        ObstacleObject prevCar = null;
        this.lastR = this.lastR + speed;
        for( ObstacleObject bar : obstaclesList) {

            bar.keepTrack(canvas, speed);
            bar.draw(canvas,paint, speed);
            prevCar = bar;
        }


        paint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, canvas.getWidth(), 100, paint);
        paint.setColor(Color.BLUE);
        canvas.drawRect(0, canvas.getHeight() - 100, canvas.getWidth(), canvas.getHeight(), paint);

    }

    public void update() {
    }

}
