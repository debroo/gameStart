package test.myapplication;


import android.graphics.Bitmap;
import android.graphics.Canvas;

public class CharacterSprite {

    public Bitmap image;
    private double x,y, goalX, goalY;
    private final int speed = 10;
    double speedX;
    double speedY;

    public CharacterSprite(Bitmap bmp) {
        image = bmp;
        x = 0;
        y = 0;
        goalY = y;
        goalX = x;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image,  (int) x - image.getWidth() / 2, (int) y - image.getHeight() / 2, null);
    }

    public void update() {


        if (x != goalX && !(goalX > x - speedX && goalX < x + speedX) ) {
            x = goalX > x ? x + speedX : x - speedX;
        }

        if (y != goalY && !( goalY > y - speedY && goalY < y + speedY) ) {
            y = goalY > y ? y + speedY : y - speedY;
        }
    }

    public void setX(int x) {
        speedX = Math.abs(goalX - x) * speed * 0.001;
        this.goalX = x;
    }

    public void setY(int y) {
        speedY = Math.abs(goalY - y) * speed * 0.001;
        this.goalY = y;
    }
}
