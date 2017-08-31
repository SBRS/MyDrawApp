package com.alexandrli.mydrawapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MyDrawApp drawView = (MyDrawApp) findViewById(R.id.customView);

        //Switch for multicolor
        final Switch multicolor = (Switch) findViewById(R.id.swtMulti);

        multicolor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                drawView.setMulti(isChecked);
            }
        });

        //Spinner for size
        Spinner size = (Spinner) findViewById(R.id.sprSize);

        ArrayList<Integer> sizes = new ArrayList<Integer>();
        sizes.add(5);
        sizes.add(10);
        sizes.add(15);
        sizes.add(20);
        sizes.add(25);
        sizes.add(30);

        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, sizes);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        size.setAdapter(dataAdapter);
        size.setSelection(2);

        size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                drawView.setSize((Integer) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

                // sometimes you need nothing here
            }
        });

        //Button for undo
        Button btnUndo = (Button) findViewById(R.id.btnUndo);

        btnUndo.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                drawView.Undo();
                return true;
            }
        });

        //Button for redo
        Button btnRedo = (Button) findViewById(R.id.btnRedo);

        btnRedo.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                drawView.Redo();
                return true;
            }
        });

        //Button for clear
        Button btnClear = (Button) findViewById(R.id.btnClear);

        btnClear.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                drawView.Clear();
                return true;
            }
        });

        //Button for black
        Button btnBlack = (Button) findViewById(R.id.btnBlack);

        btnBlack.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                drawView.Color(Color.BLACK);
                if (multicolor.isChecked())
                {
                    multicolor.setChecked(false);
                    drawView.setMulti(false);
                    Toast.makeText(MainActivity.this, "Multicolor switched off", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        //Button for red
        Button btnRed = (Button) findViewById(R.id.btnRed);

        btnRed.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                drawView.Color(Color.RED);
                if (multicolor.isChecked())
                {
                    multicolor.setChecked(false);
                    drawView.setMulti(false);
                    Toast.makeText(MainActivity.this, "Multicolor switched off", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        //Button for green
        Button btnGreen = (Button) findViewById(R.id.btnGreen);

        btnGreen.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                drawView.Color(Color.GREEN);
                if (multicolor.isChecked())
                {
                    multicolor.setChecked(false);
                    drawView.setMulti(false);
                    Toast.makeText(MainActivity.this, "Multicolor switched off", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        //Button for blue
        Button btnBlue = (Button) findViewById(R.id.btnBlue);

        btnBlue.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                drawView.Color(Color.BLUE);
                if (multicolor.isChecked())
                {
                    multicolor.setChecked(false);
                    drawView.setMulti(false);
                    Toast.makeText(MainActivity.this, "Multicolor switched off", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        //Button for eraser
        Button btnEraser = (Button) findViewById(R.id.btnEraser);

        btnEraser.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                drawView.Color(Color.WHITE);
                if (multicolor.isChecked())
                {
                    multicolor.setChecked(false);
                    drawView.setMulti(false);
                    Toast.makeText(MainActivity.this, "Multicolor switched off", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }
}
