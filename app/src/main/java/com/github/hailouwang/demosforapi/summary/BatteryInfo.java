package com.github.hailouwang.demosforapi.summary;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.hailouwang.demosforapi.R;

import java.text.NumberFormat;
import java.util.Set;


public class BatteryInfo extends Activity{
    private static final String TAG = "Battery";
    private static final boolean TEST_MODE = true;
    
    private TextView mStatus;
    private TextView mPower;
    private TextView mLevel;
    private TextView mScale;
    private TextView mHealth;
    private TextView mVoltage;
    private TextView mTemperature;
    private TextView mTechnology;
    private TextView mUptime;
    private ImageView mIconSmaller;
    private TextView mInvalidCharger;
    private TextView mBatteryPresent;
    private TextView mBatteryPresent2;
    //drodYoung start for update time 1000ms a time
    private static final int EVENT_TICK = 1;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case EVENT_TICK:
                    updateBatteryStats();
                    sendEmptyMessageDelayed(EVENT_TICK, 1000);
                    break;
            }
        }
    };
    private void updateBatteryStats() {
        if(mUptime!=null){//更新启动时间
            long uptime = SystemClock.elapsedRealtime();
            mUptime.setText(DateUtils.formatElapsedTime(uptime / 1000));
        }
    }
    //drodYoung end

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_battery_info);
        
        mStatus = (TextView)findViewById(R.id.status);
        mPower = (TextView)findViewById(R.id.power);
        mLevel = (TextView)findViewById(R.id.level);
        mScale = (TextView)findViewById(R.id.scale);
        mHealth = (TextView)findViewById(R.id.health);
        mTechnology = (TextView)findViewById(R.id.technology);
        mVoltage = (TextView)findViewById(R.id.voltage);
        mTemperature = (TextView)findViewById(R.id.temperature);
        mUptime = (TextView) findViewById(R.id.uptime);
        mIconSmaller = (ImageView) findViewById(R.id.icon_small);
        mInvalidCharger = (TextView) findViewById(R.id.battery_invalid_charger);
        mBatteryPresent = (TextView) findViewById(R.id.battery_present);
        mBatteryPresent2 = (TextView) findViewById(R.id.battery_present_2nd);
        
        // Get awake time plugged in and on battery
        mHandler.sendEmptyMessageDelayed(EVENT_TICK, 1000);
        
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        
        registerReceiver(mIntentReceiver, mIntentFilter);
    }
    
    /**
     *Listens for intent broadcasts
     */
     private IntentFilter   mIntentFilter;

     private BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
         @Override
         public void onReceive(Context context, Intent intent) {
             String action = intent.getAction();
             Log.d(TAG, "BatteryInfo BroadcastReceiver ...action is:"+action);
             if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                 if(TEST_MODE){
                     Set<String> valueKeySet = intent == null?null:intent.getExtras()==null?null:intent.getExtras().keySet();
                     for(String key : valueKeySet){
                         Log.d(TAG,"BatteryInfo Test key:value:"+key+":"+intent.getExtras().get(key));
                     }
                 }
                 //1、更新当前电量
                 int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL/*"level"*/, 0);
                 int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE/*"scale"*/, 0);
                 mLevel.setText("" + Utils.formatPercentage((double) (level*100/scale) / 100.0));
                 //2、当前最大电量
                 mScale.setText("" + scale);
                 //3、更新电压
                 mVoltage.setText("" + intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE/*"voltage"*/, 0) + " "
                         + getString(R.string.battery_info_voltage_units));
                 //4、电池温度
                 int ori = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE/*"temperature"*/, 0);
                 mTemperature.setText("" + tenthsToFixedString(ori)
                         + getString(R.string.battery_info_temperature_units));
                 //5、电池技术
                 mTechnology.setText("" + intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY/*"technology"*/));
                 //6、充电状态[充电]、[耗电]
                 mStatus.setText(Utils.getBatteryStatus(getResources(), intent));
                 //7、更新充电类型
                 mPower.setText(Utils.getPluggedStatus(getResources(), intent));
                 //8、电池运转状态
                 mHealth.setText(Utils.getHealthStatus(getResources(), intent));
                 //9、icon_smaller
                 int icon_smaller = intent.getIntExtra(BatteryManager.EXTRA_ICON_SMALL, 0);
                 mIconSmaller.setImageResource(icon_smaller);
                 //10、不支持的充电设备
                 boolean isInvalidCharger = intent.getIntExtra(/*BatteryManager.EXTRA_INVALID_CHARGER*/"invalid_charger", 0) != 0;
                 mInvalidCharger.setText(isInvalidCharger?"是":"否");
                 //11、电池是否存在
                 boolean isPresent = intent.getBooleanExtra(BatteryManager.EXTRA_PRESENT, false);
                 boolean isPresent2nd = intent.getBooleanExtra(/*BatteryManager.EXTRA_PRESENT*/"present_2nd", false);
                 mBatteryPresent.setText(isPresent?"是":"否");
                 mBatteryPresent2.setText(isPresent2nd?"是":"否");
             }
         }
     };
    
     /**
      * 格式化 整型 为 字符串
      * Format a number of tenths-units as a decimal string without using a
      * conversion to float.  E.g. 347 -> "34.7", -99 -> "-9.9"
      */
     private final String tenthsToFixedString(int x) {
         int tens = x / 10;
         // use Math.abs to avoid "-9.-9" about -99
         return Integer.toString(tens) + "." + Math.abs(x - 10 * tens);
     }
     
     static class Utils{
         /**
          * 格式化 百分比
          * @param percentage
          * @return
          */
         private static String formatPercentage(double percentage) {
             return NumberFormat.getPercentInstance().format(percentage);
         }
         
         /**
          * 获得电池 运行状态
          * @param res
          * @param batteryChangedIntent
          * @return
          */
         public static String getBatteryStatus(Resources res, Intent batteryChangedIntent) {
             final Intent intent = batteryChangedIntent;
             //1、获得电源插入类型
             int plugType = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
             //2、获得电源状态
             int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS,
                     BatteryManager.BATTERY_STATUS_UNKNOWN);
             String statusString;
             //3、依据充电状态，返回相应的描述
             if (status == BatteryManager.BATTERY_STATUS_CHARGING) {
                 int resId;
                 if (plugType == BatteryManager.BATTERY_PLUGGED_AC) {
                     //正在通过交流电源充电
                     resId = R.string.battery_info_status_charging_ac;
                 } else if (plugType == BatteryManager.BATTERY_PLUGGED_USB) {
                     //正在通过USB充电
                     resId = R.string.battery_info_status_charging_usb;
                 } else if (plugType == BatteryManager.BATTERY_PLUGGED_WIRELESS) {
                     //正在通过无线充电
                     resId = R.string.battery_info_status_charging_wireless;
                 } else if (plugType == (BatteryManager.BATTERY_PLUGGED_AC|BatteryManager.BATTERY_PLUGGED_USB)) {
                     //正在通过交流电源（AC-enhancement）充电
                     resId = R.string.battery_info_status_ac_usb;
                 } else {
                     //正在充电
                     resId = R.string.battery_info_status_charging;
                 }
                 statusString = res.getString(resId);
             } else if (status == BatteryManager.BATTERY_STATUS_DISCHARGING) {
                 //正在耗电
                 statusString = res.getString(R.string.battery_info_status_discharging);
             } else if (status == BatteryManager.BATTERY_STATUS_NOT_CHARGING) {
                 //未在耗电
                 statusString = res.getString(R.string.battery_info_status_not_charging);
             } else if (status == BatteryManager.BATTERY_STATUS_FULL) {
                 //已充满
                 statusString = res.getString(R.string.battery_info_status_full);
             } else {
                 //未知
                 statusString = res.getString(R.string.battery_info_status_unknown);
             }
             
             return statusString;
         }
         /**
          * 获得电源插入类型状态
          * @param res
          * @param batteryChangedIntent
          * @return
          */
         public static String getPluggedStatus(Resources res, Intent batteryChangedIntent){
             final Intent intent = batteryChangedIntent;
             //1、获得电源插入类型
             int plugType = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
             switch (plugType) {
                 case 0:
                     //未插电
                     return res.getString(R.string.battery_info_power_unplugged);
                 case BatteryManager.BATTERY_PLUGGED_AC:
                     //正在通过交流电充电
                     return res.getString(R.string.battery_info_power_ac);
                 case BatteryManager.BATTERY_PLUGGED_USB:
                     //正在通过USB充电
                     return res.getString(R.string.battery_info_power_usb);
                 case BatteryManager.BATTERY_PLUGGED_WIRELESS:
                     //正在通过无线充电
                     return res.getString(R.string.battery_info_power_wireless);
                 case (BatteryManager.BATTERY_PLUGGED_AC|BatteryManager.BATTERY_PLUGGED_USB):
                     //交流电+USB
                     return res.getString(R.string.battery_info_power_ac_usb);
                 default:
                     //未知
                     return res.getString(R.string.battery_info_power_unknown);
             }
         }
         
         public static String getHealthStatus(Resources res, Intent batteryChangedIntent){
             final Intent intent = batteryChangedIntent;
             //1、获得电池健康状态
             int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH/*"health"*/, BatteryManager.BATTERY_HEALTH_UNKNOWN);
             String healthString;
             if (health == BatteryManager.BATTERY_HEALTH_GOOD) {
                 //正常
                 healthString = res.getString(R.string.battery_info_health_good);
             } else if (health == BatteryManager.BATTERY_HEALTH_OVERHEAT) {
                 //过热
                 healthString = res.getString(R.string.battery_info_health_overheat);
             } else if (health == BatteryManager.BATTERY_HEALTH_DEAD) {
                 //电池耗尽、没电
                 healthString = res.getString(R.string.battery_info_health_dead);
             } else if (health == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE) {
                 //电压过高
                 healthString = res.getString(R.string.battery_info_health_over_voltage);
             } else if (health == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE) {
                 //未知错误
                 healthString = res.getString(R.string.battery_info_health_unspecified_failure);
             } else if (health == BatteryManager.BATTERY_HEALTH_COLD) {
                 //冷、电压低
                 healthString = res.getString(R.string.battery_info_health_cold);
             } else {
                 //未知、不明
                 healthString = res.getString(R.string.battery_info_health_unknown);
             }
             return healthString;
         }
     }
}
