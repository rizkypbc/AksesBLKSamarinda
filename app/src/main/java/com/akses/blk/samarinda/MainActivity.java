package com.akses.blk.samarinda;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.akses.blk.samarinda.Adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {


    private AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.app.ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setLogo(R.mipmap.ic_launcher);
        menu.setDisplayUseLogoEnabled(true);

//        Context context = getApplicationContext(); // or activity.getApplicationContext()
//        PackageManager packageManager = context.getPackageManager();
//        String packageName = context.getPackageName();
//
//        String myVersionName = "not available"; // initialize String
//
//        try {
//            myVersionName = packageManager.getPackageInfo(packageName, 0).versionName;
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
//        mTitle.setText("BLKI");


        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),
                MainActivity.this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_blki, menu);
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()) {

           case R.id.action_profil:
               Intent intent = new Intent(this, Profil.class);
               this.startActivity(intent);
               break;

           case R.id.about:


               AlertDialog.Builder tentang = new AlertDialog.Builder(this);

               Context context = getApplicationContext(); // or activity.getApplicationContext()
               PackageManager packageManager = context.getPackageManager();
               String packageName = context.getPackageName();

               String myVersionName = "not available"; // initialize String

               try {
                   myVersionName = packageManager.getPackageInfo(packageName, 0).versionName;
               } catch (PackageManager.NameNotFoundException e) {
                   e.printStackTrace();
               }

               tentang.setMessage("Akses BLKI Versi : " + (myVersionName) + "\n\nCreated By : Kios3in1 Samarinda  \n\n Email : Kios3in1.blki@gmail.com \n\n Website : www.kios3in1.net/009/").setPositiveButton("Close", new DialogInterface.OnClickListener(){
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       dialogInterface.dismiss();
                   }
               }).setTitle("About").create();
               tentang.show();
               break;

           default:
               return super.onOptionsItemSelected(item);
       }

       return true;
    }


    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
