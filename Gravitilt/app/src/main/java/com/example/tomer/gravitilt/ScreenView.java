package com.example.tomer.gravitilt;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Tomer on 23/04/2017.
 */

public class ScreenView extends View {
    private boolean running;
    private Canvas canvas;
    long currentTimeMS;
    public ScreenView(Context context)
    {
        super(context);
        running = false;
    }
    public void run ()
    {
        running = true;
        currentTimeMS = System.currentTimeMillis();
        invalidate();
    }
    public void stop()
    {
        running = false;
    }



    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;

            super.onDraw(canvas);
            float widthDp = Game.pxToDp(canvas.getWidth(),getContext());
            float heightDp = Game.pxToDp(canvas.getHeight(),getContext());
            float elapsedSeconds = calcElapsedSeconds();
            Game.calcEntitiesAttributes(elapsedSeconds);
            Game.fixOffBorderEntities(widthDp,heightDp);
            drawAllEntities();
            GameplayActivity.onFrameTick();

        if (running) {
            invalidate(); //calls onDraw
        }
    }


    private void drawAllEntities() {
        for (Entity entity : Game.getAllEntities()) {
            if (entity instanceof Circle)
                drawCircle(canvas, (Circle) entity);
            else if (entity instanceof Rectangle)
                drawRectangle(canvas, (Rectangle)entity);

        }
    }

    private void drawRectangle(Canvas canvas, Rectangle rectangle) {
        float left = Game.dpToPx(rectangle.getLocationX(),getContext());
        float top = Game.dpToPx(rectangle.getLocationY(),getContext());
        float right = Game.dpToPx(rectangle.getLocationX() + rectangle.getWidth(),getContext());
        float bottom = Game.dpToPx(rectangle.getLocationY() + rectangle.getHeight(),getContext());
        Paint paint = rectangle.getPaint();
        canvas.drawRect(left,top,right,bottom,paint);
    }

    private void drawCircle(Canvas canvas, Circle circle) {

        float locX = Game.dpToPx(circle.getLocationX(),getContext());
        float locY = Game.dpToPx(circle.getLocationY(),getContext());
        float radius = Game.dpToPx(circle.getRadius(),getContext());
        Paint paint = circle.getPaint();


        canvas.drawCircle(locX,locY,radius,paint);
    }
    private float calcElapsedSeconds() {
        float elapsedSeconds = (System.currentTimeMillis() -  currentTimeMS) /1000F;
        currentTimeMS = System.currentTimeMillis();
        return elapsedSeconds;
    }

}