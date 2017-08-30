package com.alexandrli.mydrawapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

/**
 * Created by alexandrli on 25/08/17.
 */

public class MyDrawApp extends View implements View.OnTouchListener
{
    private class Coordinate
    {
        float x;
        float y;
        int color;
        int size;

        public Coordinate(float x, float y, int color, int size)
        {
            this.x = x;
            this.y = y;
            this.color = color;
            this.size = size;
        }
    }

    ArrayList<Coordinate> points = new ArrayList<Coordinate>();
    ArrayList<Coordinate> lastPoints = new ArrayList<Coordinate>();
    Paint paint = new Paint();
    Random random = new Random();
    private boolean isMulti;
    private int size;
    private boolean longTouch;
    private float initialX;
    private float initialY;
    private int mainColor = Color.BLACK;

    public void setMulti(boolean multi)
    {
        isMulti = multi;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public MyDrawApp(Context context)
    {
        super(context);

        setOnTouchListener(this);
    }

    public MyDrawApp(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        setOnTouchListener(this);
    }

    public MyDrawApp(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(this);
    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mainColor = random.nextInt();
            points.remove(points.size() - 1);
            points.add(new Coordinate(initialX, initialY, mainColor, size));
            invalidate();
            handler.postDelayed(this, 700);
        }
    };

    @Override
    public boolean onTouch(final View view, final MotionEvent motionEvent)
    {
        int pointerCount = motionEvent.getPointerCount();

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:

                initialX = motionEvent.getX();
                initialY = motionEvent.getY();
                longTouch = true;

                if (!isMulti)
                {
                    handler.postDelayed(runnable, 500);
                }

                for (int i = 0; i<pointerCount; i++)
                {
                    if (isMulti)
                    {
                        points.add(new Coordinate(motionEvent.getX(i), motionEvent.getY(i), random.nextInt(), size));
                    }
                    else
                    {
                        points.add(new Coordinate(motionEvent.getX(i), motionEvent.getY(i), mainColor, size));
                    }
                }
                invalidate();
                return true;

            case MotionEvent.ACTION_UP:
                if(longTouch) {
                    longTouch = false;
                    handler.removeCallbacks(runnable);
                }
                return true;

            case MotionEvent.ACTION_MOVE:
                if(longTouch) {
                    longTouch = false;
                    handler.removeCallbacks(runnable);
                }

                for (int i = 0; i<pointerCount; i++)
                {
                    if (isMulti)
                    {
                        points.add(new Coordinate(motionEvent.getX(i), motionEvent.getY(i), random.nextInt(), size));
                    }
                    else
                    {
                        points.add(new Coordinate(motionEvent.getX(i), motionEvent.getY(i), mainColor, size));
                    }
                }
                invalidate();

                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);


        for (Coordinate pt: points)
        {
            paint.setColor(pt.color);
            canvas.drawCircle(pt.x, pt.y, pt.size, paint);
        }
    }

    public void Undo()
    {
        if (points.size() != 0)
        {
            Coordinate undoPoints;
            undoPoints = points.get(points.size() - 1);
            lastPoints.add(undoPoints);
            points.remove(points.size() - 1);
            invalidate();
        }
        else
        {
            Toast.makeText(getContext(), "Sorry, No points to Undo", Toast.LENGTH_SHORT).show();
        }
    }

    public void Redo()
    {
        if (lastPoints.size() != 0)
        {
            Coordinate redoPoints;
            redoPoints = lastPoints.get(lastPoints.size() - 1);
            points.add(redoPoints);
            lastPoints.remove(lastPoints.size() - 1);
            invalidate();
        }
        else
        {
            Toast.makeText(getContext(), "Sorry, No points to Redo", Toast.LENGTH_SHORT).show();
        }
    }

    public void Clear()
    {
        points.clear();
        lastPoints.clear();
        invalidate();
        Toast.makeText(getContext(), "Cleared", Toast.LENGTH_SHORT).show();
    }

    public void Color (int color)
    {
        if (color == Color.BLACK)
        {
            mainColor = Color.BLACK;
        }
        else if (color == Color.RED)
        {
            mainColor = Color.RED;
        }
        else if (color == Color.GREEN)
        {
            mainColor = Color.GREEN;
        }
        else if (color == Color.BLUE)
        {
            mainColor = Color.BLUE;
        }
        else if (color == Color.WHITE)
        {
            mainColor = Color.WHITE;
        }
    }
}
