package com.example.theoryassistant.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.theoryassistant.R;
import com.example.theoryassistant.sqlite.SQLiteDataBase;
import com.google.android.material.navigation.NavigationView;

public class AdminSupport extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView welcomename;
    String name;
    String details;
    String parameter;
    Cursor cursor;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_support);
        parameter = getIntent().getStringExtra("phone");
        welcomename = findViewById(R.id.welcomename);
        openHelper = new SQLiteDataBase(this);
        db = openHelper.getReadableDatabase();

        cursor = db.rawQuery("SELECT * FROM " + SQLiteDataBase.TABLE_NAME + " WHERE "
                + SQLiteDataBase.COL_1 + " = ? ", new String[]{parameter});

        cursor.moveToLast();
        name = cursor.getString(cursor.getColumnIndex(SQLiteDataBase.COL_1));
        welcomename.setText(name);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin__support, menu);
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
            Toast.makeText(this, "You Have been Logout", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AdminSupport.this, Login.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        if (id == R.id.myaccount) {
            Intent intent = new Intent(AdminSupport.this, MyAccount.class);
            intent.putExtra("phone", cursor.getString(cursor.getColumnIndex(SQLiteDataBase.COL_1)));
            startActivity(intent);
        } else if (id == R.id.addproducts) {
            Intent intent = new Intent(AdminSupport.this, AddHint.class);
            startActivity(intent);
        } else if (id == R.id.showproducts) {
            Intent intent = new Intent(AdminSupport.this, HintList.class);
            intent.putExtra("details", details);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}