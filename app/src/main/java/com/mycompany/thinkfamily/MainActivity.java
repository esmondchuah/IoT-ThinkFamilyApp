package com.mycompany.thinkfamily;

import android.app.NotificationManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.app.ToolbarActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager mPager;
    public static double calorieCount;
    public static double calorieToday;
    public static int stepToday;
    public static boolean ready;
    public static int visits;
    NotificationCompat.Builder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.animation_enter_left, R.anim.animation_exit_right);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mPager.setCurrentItem(1);

        Intent serviceIntent = new Intent(this, DataPullingService.class);
        startService(serviceIntent);

        mBuilder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.thinkfamily_icon)
                        .setContentTitle("ThinkFamily")
                        .setContentText("ALERT!!! Your mom fell!!!");


        // check for the presence of the photo frame in close proximity through bluetooth
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();

        IntentFilter filter = new IntentFilter();

        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        registerReceiver(mReceiver, filter);
        adapter.startDiscovery();
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

    public void checkinBtnClicked (View v) {
//        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
//
//        IntentFilter filter = new IntentFilter();
//
//        filter.addAction(BluetoothDevice.ACTION_FOUND);
//        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
//        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
//
//        registerReceiver(mReceiver, filter);
//        adapter.startDiscovery();
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                Toast.makeText(getApplicationContext(), "Device found " + device.getName(), Toast.LENGTH_SHORT).show(); //Device found
                if (device.getName().equals("SM-T325")) {
                    Calendar c = Calendar.getInstance();
                    int daysSinceLastVisit = c.get(Calendar.DAY_OF_YEAR);

                    SharedPreferences preferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("daysSinceLastVisit", daysSinceLastVisit);
                    editor.commit();

                    ((TextView) findViewById(R.id.days_since_last_visit)).setText("0");
                }
            }
            else if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
                Toast.makeText(getApplicationContext(), "Action connected", Toast.LENGTH_SHORT).show(); //Device is now connected
            }
            else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                Toast.makeText(getApplicationContext(), "Discovery finished", Toast.LENGTH_SHORT).show(); //Done searching
            }
            else if (BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED.equals(action)) {
                Toast.makeText(getApplicationContext(), "Disconnect Requested", Toast.LENGTH_SHORT).show(); //Device is about to disconnect
            }
            else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
                Toast.makeText(getApplicationContext(), "Disconnected", Toast.LENGTH_SHORT).show(); //Device has disconnected
            }

        }
    };

    public void skypeOnClick (View v){
        Intent sky = new Intent("android.intent.action.VIEW");
        sky.setData(Uri.parse("skype:" + "adyasadanand" + "?call&video=true"));
        startActivity(sky);
    }

    public void notificationOnClick (View v){
//        // Sets an ID for the notification
//        int mNotificationId = 001;
//        // Gets an instance of the NotificationManager service
//        NotificationManager mNotifyMgr =
//                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        // Builds the notification and issues it.
//        mNotifyMgr.notify(mNotificationId, mBuilder.build());
        // refresh DaysSinceLastVisit
    }
}


class MyPagerAdapter extends FragmentStatePagerAdapter {

    public MyPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position){
        Fragment fragment = null;
        if (position == 0) {
            fragment = new SuggestionsFragment();
        }else if (position == 1) {
            fragment = new CameraFragment();
        }else if (position == 2){
            fragment = new AnalyticsFragment();
        }
        return fragment;
    }

    @Override
    public int getCount(){
        return 3;
    }
}


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.cameraFragment);
//        Toast.makeText(this, "main: " + Integer.toString(requestCode), Toast.LENGTH_SHORT).show();
//        if(requestCode == REQUEST_CODE_CAPTURE_IMAGE){
//            fragment.onActivityResult(13, resultCode, data);
//        }else if(requestCode == REQUEST_CODE_CREATOR){
//            fragment.onActivityResult(requestCode, resultCode, data);
//        }
//    }
