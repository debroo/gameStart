package test.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.constraint.solver.widgets.Rectangle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    //private CharacterSprite characterSprite;
    private Road road;
    private Car car;
    private Obstacles obstacles;
    private int points = 0;
    private int maxPoints = 0;
    private int speed = 5;
    private DisplayMetrics metrics;

    public DisplayMetrics getMetrics() {
        return metrics;
    }

    public GameView(Context context, DisplayMetrics metrics) {
        super(context);
        this.metrics = metrics;
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Bitmap roadImage = BitmapFactory.decodeResource(getResources(), R.drawable.roadimg);
        Bitmap carImage = BitmapFactory.decodeResource(getResources(), R.drawable.car);
        Bitmap obstacleImage = BitmapFactory.decodeResource(getResources(), R.drawable.obstaclecar);
        road = new Road(roadImage);
        car = new Car(carImage);
        obstacles = new Obstacles(obstacleImage);
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }

    }

    public void update() {
        //characterSprite.update();
        road.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            /*canvas.drawColor(Color.WHITE);
            Paint paint = new Paint();
            paint.setColor(Color.rgb(250, 0, 0));
            canvas.drawRect(100, 100, 200, 200, paint);*/
            road.draw(canvas);
            obstacles.draw(canvas);
            car.draw(canvas, metrics);
            points++;
            if (points > maxPoints) maxPoints = points;
            checkCollision(car, obstacles, canvas);
            speed++;
            road.setSpeed(speed / 50);
            obstacles.setSpeed(speed / 50);

            // characterSprite.draw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
  /*      characterSprite.setY((int) event.getY());
        characterSprite.setX((int) event.getX());
*/
        car.move((int) event.getX());
        return super.onTouchEvent(event);
    }

    public void checkCollision(Car car, Obstacles obstacles, Canvas canvas) {
        Paint paint = new Paint();

        paint.setColor(Color.BLACK);
        paint.setTextSize(20 * getResources().getDisplayMetrics().density);
        canvas.drawText("Points: " + points, 50, 60, paint);
        canvas.drawText("Record: " + maxPoints, 500, 60, paint);
        paint.setColor(Color.WHITE);
     /*   for (ObstacleObject obj : obstacles.obstaclesList) {
            if (obj.getPos() == car.getCarPosition() && car.getY() < obj.getY() && obj.getY() < car.getY() + car.getHeight()) {
                System.out.println("colision");
                canvas.drawText("Collision", 200, 450, paint);
                points = 0;
                speed = 10;
            }
        }*/

        for (ObstacleObject obj : obstacles.obstaclesList) {

            if (car.getCarRectangle().intersect(obj.getObstacleRect())) {
                System.out.println("colision");
                canvas.drawText("Collision", 200, 450, paint);
                points = 0;
                speed = 10;
            }
        }
    }

}
