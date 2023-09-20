package com.test.jia;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toast.makeText(MainActivity.this, "首次连接请等待",Toast.LENGTH_SHORT ).show();
        Button btupdate=(Button)findViewById(R.id.button_Con);
//        Button btnexitiot=(Button)findViewById(R.id.button_exitiot);
//        Switch swled=(Switch)findViewById(R.id.switch_led);
//        Switch swwindows=(Switch)findViewById(R.id.switch_windows);
        final ImageView imageView_line=(ImageView)findViewById(R.id.imageView_line);
        final ImageView imageView_led=(ImageView)findViewById(R.id.imageView_led);
        final ImageView imageView_timing=(ImageView)findViewById(R.id.imageView_timing);
        final TextView textview_temp=(TextView) findViewById(R.id.textView_temp);
        final TextView textview_humi=(TextView) findViewById(R.id.textView_humi);
        final TextView textView_light=(TextView) findViewById(R.id.textView_light);
        final TextView textView_status=(TextView) findViewById(R.id.textView_status);
//        final TextView textView_led=(TextView) findViewById(R.id.textView_led);
        SeekBar seekBar_leda=(SeekBar) findViewById(R.id.seekBar);
        Spinner spinner_hour=(Spinner) findViewById(R.id.spinner_hour);
        Spinner spinner_min=(Spinner) findViewById(R.id.spinner_min);
        Spinner spinner_sec=(Spinner) findViewById(R.id.spinner_sec);
        Spinner spinner_switch_state=(Spinner) findViewById(R.id.spinner_switch_state);
        Button button_timing=(Button) findViewById(R.id.button_timing);
        Spinner spinner_switch_light=(Spinner) findViewById(R.id.spinner_switch_light);
        final TextView textView_led1_value=(TextView) findViewById(R.id.textView_led1_value);
        final TextView textView_led2_value=(TextView) findViewById(R.id.textView_led2_value);
        final TextView textView_led3_value=(TextView) findViewById(R.id.textView_led3_value);


//        final Thread t = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    HuaweiIOT hwiot=new HuaweiIOT();
//                    System.out.println("获取状态");
//                    String str="";
//                    str=hwiot.getAtt("","status");
//                    if(str.equals("OFFLINE")) {
//                        str = "设备离线";
//                        imageView_line.setImageDrawable(getResources().getDrawable(R.drawable.outline));
//                    }
//                    else if(str.equals("ONLINE"))
//                    {
//                        str="设备在线";
//                        imageView_line.setImageDrawable(getResources().getDrawable(R.drawable.online));
//                    }
//                    textView_status.setText(str);
//                    System.out.println("获取数据");
//                    str=hwiot.getAtt("current","shadow");
//                    textview_temp.setText(str.substring(0,5)+"A");
//                    System.out.println("获取成功，电流："+str);
//                    str=hwiot.getAtt("voltage","shadow");
//                    textview_humi.setText(str.substring(0,5)+"V");
//                    System.out.println("获取成功，电压："+str);
//                    str=hwiot.getAtt("power","shadow");
//                    textView_light.setText(str.substring(0,5)+"W");
//                    System.out.println("获取成功，功率："+str);
//                }
//                catch (Exception e) {
//                    e.printStackTrace();
//                    System.out.println("获取失败："+e.toString());
//                }
//            }
//        };
//        t.start();
//        Toast.makeText(MainActivity.this, "属性获取中",Toast.LENGTH_LONG ).show();


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                //System.out.println("test222");
                try {

                    HuaweiIOT hwiot=new HuaweiIOT();
                    System.out.println("获取状态");
                    String str="";
                    str=hwiot.getAtt("","status");
                    if(str.equals("OFFLINE")) {
                        str = "设备离线";
                        imageView_line.setImageDrawable(getResources().getDrawable(R.drawable.outline));
                    }
                    else if(str.equals("ONLINE"))
                    {
                        str="设备在线";
                        imageView_line.setImageDrawable(getResources().getDrawable(R.drawable.online));
                    }
                    textView_status.setText(str);
                    System.out.println("获取数据");
                    str=hwiot.getAtt("current","shadow");
                    textview_temp.setText(str.substring(0,5)+"A");
                    System.out.println("获取成功，电流："+str);
                    str=hwiot.getAtt("voltage","shadow");
                    textview_humi.setText(str.substring(0,5)+"V");
                    System.out.println("获取成功，电压："+str);
                    str=hwiot.getAtt("power","shadow");
                    textView_light.setText(str.substring(0,5)+"W");
                    System.out.println("获取成功，功率："+str);
                    str= hwiot.getAtt("L1","shadow");
                    textView_led1_value.setText(str.substring(0,str.indexOf("."))+"%");
                    str= hwiot.getAtt("L2","shadow");
                    textView_led2_value.setText(str.substring(0,str.indexOf("."))+"%");
                    str= hwiot.getAtt("L3","shadow");
                    textView_led3_value.setText(str.substring(0,str.indexOf("."))+"%");
                }
                catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("获取失败："+e.toString());
                }
            }
        },5*1000,6*1000);



        btupdate.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Thread t = new Thread() {
                    @Override
                    public void run() {
                        try {
                            HuaweiIOT hwiot=new HuaweiIOT();
                            System.out.println("获取状态");
                            String str="";
                            str=hwiot.getAtt("","status");
                            if(str.equals("OFFLINE")) {
                                str = "设备离线";
                                imageView_line.setImageDrawable(getResources().getDrawable(R.drawable.outline));
                            }
                            else if(str.equals("ONLINE"))
                            {
                                str="设备在线";
                                imageView_line.setImageDrawable(getResources().getDrawable(R.drawable.online));
                            }
                            textView_status.setText(str);
                            System.out.println("获取数据");
                            str=hwiot.getAtt("current","shadow");
                            textview_temp.setText(str.substring(0,5)+"A");
                            System.out.println("获取成功，电流："+str);
                            str=hwiot.getAtt("voltage","shadow");
                            textview_humi.setText(str.substring(0,5)+"V");
                            System.out.println("获取成功，电压："+str);
                            str=hwiot.getAtt("power","shadow");
                            textView_light.setText(str.substring(0,5)+"W");
                            System.out.println("获取成功，功率："+str);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("获取失败："+e.toString());
                        }
                    }
                };
                t.start();
                Toast.makeText(MainActivity.this, "属性获取中",Toast.LENGTH_SHORT ).show();
            }
        });

        spinner_switch_light.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        seekBar_leda.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress<25){
                    progress = 0;
                    seekBar_leda.setProgress(progress);
                    imageView_led.setImageDrawable(getResources().getDrawable(R.drawable.ledoff));
                } else if (progress>=25 && progress<50) {
                    progress = 25;
                    seekBar_leda.setProgress(progress);
                    imageView_led.setImageDrawable(getResources().getDrawable(R.drawable.ledon));
                } else if (progress>=50 && progress<75) {
                    progress = 50;
                    seekBar_leda.setProgress(progress);
                    imageView_led.setImageDrawable(getResources().getDrawable(R.drawable.ledon));
                } else if (progress>=75 && progress<99) {
                    progress = 75;
                    seekBar_leda.setProgress(progress);
                    imageView_led.setImageDrawable(getResources().getDrawable(R.drawable.ledon));
                } else if (progress ==99) {
                    progress = 99;
                    seekBar_leda.setProgress(progress);
                    imageView_led.setImageDrawable(getResources().getDrawable(R.drawable.ledon));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //System.out.println(seekBar_leda.getProgress());
                Thread t = new Thread(){
                    @Override
                    public void run(){
                        try {
                            HuaweiIOT hwiot = new HuaweiIOT();
                            System.out.print("命令下发2");
                            if(seekBar_leda.getProgress()==0) {
                                hwiot.setCom("power", "00%", "switch", (String) spinner_switch_light.getSelectedItem());
//                                imageView_led.setImageDrawable(getResources().getDrawable(R.drawable.ledoff));
                            }
                            else if (seekBar_leda.getProgress()==25) {
                                hwiot.setCom("power", "25%", "switch", (String) spinner_switch_light.getSelectedItem());
//                                imageView_led.setImageDrawable(getResources().getDrawable(R.drawable.ledon));
                            }
                            else if (seekBar_leda.getProgress()==50) {
                                hwiot.setCom("power", "50%", "switch", (String) spinner_switch_light.getSelectedItem());
//                                imageView_led.setImageDrawable(getResources().getDrawable(R.drawable.ledon));
                            }
                            else if (seekBar_leda.getProgress()==75) {
                                hwiot.setCom("power", "75%", "switch", (String) spinner_switch_light.getSelectedItem());
//                                imageView_led.setImageDrawable(getResources().getDrawable(R.drawable.ledon));
                            }
                            else if (seekBar_leda.getProgress()==99) {
                                hwiot.setCom("power", "99%", "switch", "ALL");
//                                imageView_led.setImageDrawable(getResources().getDrawable(R.drawable.ledon));
                            }
                            System.out.println(seekBar_leda.getProgress());
                        }
                        catch (Exception e){
                            e.printStackTrace();
                            System.out.print("下发失败2："+e.toString());
                        }
                    }
                };
                t.start();
            }
        });
        spinner_hour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_min.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_sec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_switch_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        button_timing.setOnClickListener(new View.OnClickListener() {
            private String str_set;
            @Override
            public void onClick(View v) {
                Thread t = new Thread(){
                    @Override
                    public void run(){
                        try{
                            str_set = (String) spinner_hour.getSelectedItem() + (String) spinner_min.getSelectedItem() + (String) spinner_sec.getSelectedItem();
                            HuaweiIOT hwiot=new HuaweiIOT();
                            System.out.println("命令下发3");
//                            imageView_led.setImageDrawable(getResources().getDrawable(R.drawable.ledon));
//                            System.out.println(str_set);
                            hwiot.setCom("power",(String) spinner_switch_state.getSelectedItem(),"set",str_set);

                        }
                        catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("下发失败3："+e.toString());
                        }
                    }
                };
                t.start();
            }
        });
    }
//    private void getdata(){
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    HuaweiIOT hwiot=new HuaweiIOT();
//                    System.out.println("获取状态");
//                    String str="";
//                    str=hwiot.getAtt("","status");
//                    if(str.equals("OFFLINE")) {
//                        str = "设备离线";
//                        imageView_line.setImageDrawable(getResources().getDrawable(R.drawable.outline));
//                    }
//                    else if(str.equals("ONLINE"))
//                    {
//                        str="设备在线";
//                        imageView_line.setImageDrawable(getResources().getDrawable(R.drawable.online));
//                    }
//                    textView_status.setText(str);
//                    System.out.println("获取数据");
//                    str=hwiot.getAtt("current","shadow");
//                    textview_temp.setText(str.substring(0,5)+"A");
//                    System.out.println("获取成功，电流："+str);
//                    str=hwiot.getAtt("voltage","shadow");
//                    textview_humi.setText(str.substring(0,5)+"V");
//                    System.out.println("获取成功，电压："+str);
//                    str=hwiot.getAtt("power","shadow");
//                    textView_light.setText(str.substring(0,5)+"W");
//                    System.out.println("获取成功，功率："+str);
//                }
//                catch (Exception e) {
//                    e.printStackTrace();
//                    System.out.println("获取失败："+e.toString());
//                }
//            }
//        });
//    }

//    private void showNormalDialog(String strtip,int num){
//        final int a=num;
//        //创建dialog构造器
//        AlertDialog.Builder normalDialog = new AlertDialog.Builder(this);
//        //设置title
//        normalDialog.setTitle("温馨提示：");
//        //设置icon
//        normalDialog.setIcon(R.drawable.ic_launcher_round);
//        //设置内容
//        normalDialog.setMessage(strtip);
//        //设置按钮
//        normalDialog.setPositiveButton(getString(R.string.sure)
//                , new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        System.out.println("确定");
//                        if(a>1) finish();
//                    }
//                });
//        if(a==2)
//            normalDialog.setNegativeButton(getString(R.string.cancel)
//                    , new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            System.out.println("取消");
//                        }
//                    });
//        //创建并显示
//        normalDialog.create().show();
//    }
}








