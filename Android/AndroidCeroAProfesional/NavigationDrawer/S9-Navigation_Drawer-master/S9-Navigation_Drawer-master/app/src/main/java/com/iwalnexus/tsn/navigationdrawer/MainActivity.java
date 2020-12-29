package com.iwalnexus.tsn.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        navigationView = findViewById(R.id.navView);
        drawerLayout = findViewById(R.id.DrawerLayout);
        navigationView.setNavigationItemSelectedListener(this);

        Toolbar t = findViewById(R.id.toolbar);
        setSupportActionBar(t);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        View headerView = navigationView.getHeaderView(0);
        TextView name = headerView.findViewById(R.id.userName);
        ImageView img = headerView.findViewById(R.id.UserIconNav);

        name.setText("Android");

        Glide.with(this).load("https://i.pinimg.com/originals/97/08/c4/9708c4c4df2259dfeefd0b6f3441a518.jpg").into(img);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        Fragment fragment = null;
        Boolean isTransactionOk = false;

        switch (item.getItemId()){


            case R.id.op1:
               fragment = new Fragment1();
               isTransactionOk = true;

                break;
            case R.id.op2:
                fragment = new Fragment2();
                isTransactionOk = true;

                break;
            case R.id.op3:
                Intent intent = new Intent(this, Main2Activity.class);
                startActivity(intent);
                break;

        }

        if (isTransactionOk){
            changeFragment(fragment, item);
            drawerLayout.closeDrawers();
        }

        return true;
    }

    private void changeFragment(Fragment fragment, MenuItem item) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor, fragment)
                .commit();
        item.setChecked(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
