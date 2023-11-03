package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.MenuItem;
import android.os.Handler;

import com.google.android.material.navigation.NavigationBarView;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BeaconConsumer{
    private BeaconManager beaconManager;
    // 감지된 비콘들을 임시로 담을 리스트
    private List<Beacon> beaconList = new ArrayList<>();
    HomeFragment homeFragment = new HomeFragment();
    TimeTableFragment timeTableFragment;
    InfoFragment infoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int[] before = {0};
        handler.sendEmptyMessage(0);
        // 실제로 비콘을 탐지하기 위한 비콘매니저 객체를 초기화
        beaconManager = BeaconManager.getInstanceForApplication(this);

        // 여기가 중요한데, 기기에 따라서 setBeaconLayout 안의 내용을 바꿔줘야 하는듯 싶다.
        // 필자의 경우에는 아래처럼 하니 잘 동작했음.
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));

        // 비콘 탐지를 시작한다. 실제로는 서비스를 시작하는것.
        beaconManager.bind(this);

        //데이터
        Bundle bundle = new Bundle();
        Intent secondIntent = getIntent();
        String data = secondIntent.getStringExtra("dataFromServer");
        bundle.putString("data", data);

        //homeFragment = new HomeFragment();
        timeTableFragment = new TimeTableFragment();
        infoFragment = new InfoFragment();

        timeTableFragment.setArguments(bundle);
        homeFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.containers, homeFragment).commit();

        NavigationBarView navigationBarView = findViewById(R.id.bottom_navigation);

        // 메인 액티비티 하단 네비게이션 바 화면 전환
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.to_left, R.anim.no_animation).replace(R.id.containers, homeFragment).commit();
                    before[0] = 0;
                    return true;
                } else if (item.getItemId() == R.id.timetable) {
                    if(before[0] == 0){
//                        Log.d("value", "0");
                        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.to_right, R.anim.no_animation).replace(R.id.containers, timeTableFragment).commit();
                    }
                    else if(before[0] == 1){
//                        Log.d("value", "1");
                        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.to_left, R.anim.no_animation).replace(R.id.containers, timeTableFragment).commit();
                    }
                    return true;
                } else if (item.getItemId() == R.id.info) {
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.to_right, R.anim.no_animation).replace(R.id.containers, infoFragment).commit();
                    before[0] = 1;
                    return true;
                }
                return false;
            }
        }
        );
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            // 비콘이 감지되면 해당 함수가 호출된다. Collection<Beacon> beacons에는 감지된 비콘의 리스트가,
            // region에는 비콘들에 대응하는 Region 객체가 들어온다.
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                if (beacons.size() > 0) {
                    beaconList.clear();
                    for (Beacon beacon : beacons) {
                        beaconList.add(beacon);
                        if(beacon.getId2().toString() == "4660"){
                            homeFragment.attendance_check.setEnabled(true);
                            homeFragment.attendance_check.setBackgroundColor(Color.parseColor("#f5c47e"));
                        }
                    }
                }
                else
                {
                    homeFragment.attendance_check.setEnabled(false);
                    homeFragment.attendance_check.setBackgroundColor(Color.GRAY);
                }
            }

        });

        try {
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {   }
    }
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {

            // 비콘의 아이디와 거리를 측정하여 textView에 넣는다.
            for (Beacon beacon : beaconList) {
                if(beacon.getId2().toString() == "4660"){
                    homeFragment.attendance_check.setEnabled(true);
                    homeFragment.attendance_check.setBackgroundColor(Color.parseColor("#f5c47e"));
                }
                //textView.setText("ID : " + beacon.getId2() + " / " + "Distance : " + Double.parseDouble(String.format("%.3f", beacon.getDistance())) + "m\n");
            }

            // 자기 자신을 1초마다 호출
            handler.sendEmptyMessageDelayed(0, 1000);
        }
    };
}
