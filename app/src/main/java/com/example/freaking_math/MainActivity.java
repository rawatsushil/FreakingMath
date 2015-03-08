package com.example.freaking_math;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;

public class MainActivity extends Activity {


    TextView text,text2;
    int score,a2,ans,rnd,ran,val,a,b,val2;
    Button correct,wrong;
    private Timer mytimer;
    List<Integer> arr2 = new ArrayList<Integer>();
    AlertDialog.Builder builder;
    SharedPreferences sh;
    public static String MYPREF="myPref";
    public int High=0;
    ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        val2=1;
        correct=(Button)findViewById(R.id.button2);
        wrong=(Button)findViewById(R.id.button3);
        text=(TextView)findViewById(R.id.textView1);
        score=0;
        sh=getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        builder=new AlertDialog.Builder(this);
        mProgressBar=(ProgressBar)findViewById(R.id.progressBar);
        builder.setPositiveButton("END", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialog, int which) {
                finish();

            }
        });
        builder.setNegativeButton("RESTART", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i=new Intent(MainActivity.this,MainActivity.class);
                finish();
                startActivity(i);
            }

        });


        new CountDownTimer(3000,0001){      //1st arg is the time of counter and 2nd is after how much time on tick will call itself
            public void onTick(long millisUntilFinished){//this is the time ontick will call itself first initially or after finish
                    mProgressBar.setProgress((int)millisUntilFinished/100);
                    if(val2!=0) {
                        rnd = ans = 5;
                        text.setText(3 + "+" + 2 + "=" + rnd);
                        val2=val2-1;
                    }

                correct.setOnClickListener(new OnClickListener(){
                    public void onClick(View v){
                        if(rnd!=ans){
                            cancel();
                            SharedPreferences.Editor edit=sh.edit();
                            if(sh.contains("High")){
                                if(sh.getInt("High",0) > score){
                                    builder.setMessage("High Score is "+sh.getInt("High",0)+"\n"+"Your Score"+score);
                                }
                                else{
                                    edit.putInt("High",score);
                                    edit.commit();
                                    builder.setMessage("New High Score is "+score+"\n"+"Your Score"+score);
                                }
                            }
                            else{
                                edit.putInt("High",score);
                                edit.commit();
                            }

                            AlertDialog d=builder.create();
                            d.show();
                        }
                    else{
                        cancel();
                        ++score;
                        text2=(TextView)findViewById(R.id.textView2);
                        text2.setText(""+score);
                            arr2.clear();
                            val=(int)(Math.random()*2);
                            a=(int)(Math.random()*9);
                            b=(int)(Math.random()*9);
                            ans=ran=rnd=0;


                            if(val==1){
                                ans=a+b;
                                ran=ans+1;
                                arr2.add(ans);
                                arr2.add(ran);
                                Collections.shuffle(arr2);
                                rnd=arr2.get(0);
                                text.setText(a+"+"+ b+"="+rnd);
                            }
                            else{
                                ans=a-b;
                                ran=ans-1;
                                arr2.add(ans);
                                arr2.add(ran);

                                Collections.shuffle(arr2);
                                rnd=arr2.get(0);

                                text.setText(a+"-"+ b+"="+rnd);
                            }
                        start();
                        return ;
                    }

                 }

                });
                wrong.setOnClickListener(new OnClickListener(){

                    public void onClick(View v){
                        if(rnd==ans) {
                            cancel();
                            SharedPreferences.Editor edit=sh.edit();
                            if(sh.contains("High")){
                                if(sh.getInt("High",0) > score){
                                    builder.setMessage("High Score is "+sh.getInt("High",0)+"\n"+"Your Score"+score);
                                }
                                else{
                                    edit.putInt("High",score);
                                    edit.commit();
                                    builder.setMessage("New High Score is "+score+"\n"+"Your Score"+score);
                                }
                            }
                            else{
                                edit.putInt("High",score);
                                edit.commit();
                            }
                                AlertDialog d = builder.create();
                                d.show();
                        }
                        else{
                            cancel();

                            ++score;
                            text2=(TextView)findViewById(R.id.textView2);
                            text2.setText(""+score);
                            arr2.clear();
                            val=(int)(Math.random()*2);
                            a=(int)(Math.random()*9);
                            b=(int)(Math.random()*9);
                            ans=ran=rnd=0;


                            if(val==1){
                                ans=a+b;
                                ran=ans+1;
                                arr2.add(ans);
                                arr2.add(ran);
                                Collections.shuffle(arr2);
                                rnd=arr2.get(0);
                                text.setText(a+"+"+ b+"="+rnd);
                            }
                            else{
                                ans=a-b;
                                ran=ans-1;
                                arr2.add(ans);
                                arr2.add(ran);

                                Collections.shuffle(arr2);
                                rnd=arr2.get(0);

                                text.setText(a+"-"+ b+"="+rnd);
                            }
                            start();
                            return ;
                        }
                    }
                });

            }
            public void onFinish(){

                mProgressBar.setProgress(0);
                cancel();
                SharedPreferences.Editor edit=sh.edit();
                if(sh.contains("High")){
                    if(sh.getInt("High",0) > score){
                        builder.setMessage("High Score is "+sh.getInt("High",0)+"\n"+"Your Score"+score);
                    }
                    else{
                        edit.putInt("High",score);
                        edit.commit();
                        builder.setMessage("New High Score is "+score+"\n"+"Your Score"+score);
                    }
                }
                else{
                    edit.putInt("High",score);
                    edit.commit();
                }
                AlertDialog d=builder.create();
                d.show();
            }
        }.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}