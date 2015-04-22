package com.icyvenom.needforghetto;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

//TODO: Change GUI for this (?)
public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startButton = (Button) findViewById(R.id.startButton);
        Button exitButton = (Button) findViewById(R.id.exitButton);
        startButton.setOnClickListener(this);
        exitButton.setOnClickListener(this);
        Bullet.sprite = BitmapFactory.decodeResource(getResources(), R.drawable.mmbullet);
        Player.getInstance().setWeapon(new WeaponStandard(this,
                new BulletStandard(this, new Point(0,0),BulletDirection.UP)));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.startButton:
                Intent i = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(i);
                break;
            case R.id.exitButton:
                finish();
                break;
        }
    }
}
