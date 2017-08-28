package com.alexandrli.mydrawapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;


import java.util.ArrayList;
import java.util.Random;

/**
 * Created by alexandrli on 25/08/17.
 */

public class MyDrawApp extends View implements OnTouchListener
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

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent)
    {
        if (isMulti)
        {
            points.add(new Coordinate(motionEvent.getX(), motionEvent.getY(), random.nextInt(), size));
        }
        else
        {
            points.add(new Coordinate(motionEvent.getX(), motionEvent.getY(), Color.BLACK, size));
        }

        invalidate();

        return true;
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
    }
}
