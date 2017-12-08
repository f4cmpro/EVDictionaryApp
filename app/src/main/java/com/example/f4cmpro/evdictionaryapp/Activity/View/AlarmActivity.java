package com.example.f4cmpro.evdictionaryapp.Activity.View;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.f4cmpro.evdictionaryapp.R;
import com.example.f4cmpro.evdictionaryapp.Receiver.AlarmReceiver;

public class AlarmActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnTurnOn;
    private Button mBtnTurnOff;
    private TimePicker mTimePicker;
    private TextView mTvTime;

    private Calendar mCalendar;
    private AlarmManager mAlarmManager;
    private PendingIntent mPendingIntent;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Alarm");

        mBtnTurnOn = (Button) findViewById(R.id.btn_turn_on);
        mBtnTurnOff = (Button) findViewById(R.id.btn_turn_off);
        mTimePicker = (TimePicker) findViewById(R.id.time_picker);
        mTvTime = (TextView) findViewById(R.id.tv_time);
        Log.e("AA", "TranslateApi: " + Build.VERSION.SDK_INT);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            mCalendar = Calendar.getInstance();
//        }
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Bangkok");
        mCalendar = Calendar.getInstance(timeZone);
        Log.e("AA", "calendar long times: " + mCalendar.getTimeInMillis());
        mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        mBtnTurnOn.setOnClickListener(this);
        mBtnTurnOff.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnTurnOn)) {
            if (mCalendar == null) {
                Log.e("AA", "mCalendar: " + mCalendar);
                return;
            }
            mCalendar.set(Calendar.HOUR_OF_DAY, mTimePicker.getHour());
            mCalendar.set(Calendar.MINUTE, mTimePicker.getMinute());

            String textTime = String.valueOf(mTimePicker.getHour()) + ":"
                    + String.valueOf(mTimePicker.getMinute()) + " ";
            mTvTime.setText(textTime);
            Intent alarmIntent = new Intent(AlarmActivity.this, AlarmReceiver.class);
            mPendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0,
                    alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            Log.e("AA", "calendar long times: " + mCalendar.getTimeInMillis());
            mAlarmManager.set(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), mPendingIntent);

        } else if (v.equals(mBtnTurnOff)) {
            mTvTime.setText("Turn off!");
            mAlarmManager.cancel(mPendingIntent);
        }
    }
}
