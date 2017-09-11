package com.zeowls.ajmanded;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zeowls.ajmanded.ChatSplash.Message;
import com.zeowls.ajmanded.ChatSplash.Option;
import com.zeowls.ajmanded.ChatSplash.ResultModel;
import com.zeowls.ajmanded.ChatSplash.SplashChatAdapter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class FaqActivity extends AppCompatActivity {

    RecyclerView rv;
    LinearLayout linearLayout;
    SplashChatAdapter adapter;
    ResultModel[] mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        rv = (RecyclerView) findViewById(R.id.recycler_view);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        adapter = new SplashChatAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        rv.setLayoutManager(linearLayoutManager);
        rv.setItemAnimator(new SlideInUpAnimator());
        rv.setAdapter(adapter);

        InputStream raw = getResources().openRawResource(R.raw.json);
        Reader rd = new BufferedReader(new InputStreamReader(raw));
        Gson gson = new Gson();
        mModel = gson.fromJson(rd, ResultModel[].class);
        loadModel(0);
    }

    void loadModel(int modelID) {

        if (modelID == -1) {
            startActivity(new Intent(this, MainActivity.class));
            return;
        }

        Handler handler = new Handler();
        final List<Message> messages = mModel[modelID].getMessages();
        for (int i = 0; i < messages.size() * 2; i++) {
            final int finalI = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (finalI % 2 == 0) {
                        adapter.add(adapter.getItemCount(), "...", null, false);
                    } else {
                        adapter.delete(adapter.getItemCount() - 1);
                        adapter.add(adapter.getItemCount(), messages.get(finalI / 2).getText(), null, false);
                    }
                    rv.scrollToPosition(adapter.getItemCount() - 1);
                }
            }, 1000 * i);
        }

        final List<Option> options = mModel[modelID].getOptions();
        final TextView[] textViews = new TextView[options.size()];
        for (int i = 0; i < options.size(); i++) {
            TextView newTextView = new TextView(this);
            newTextView.setText(options.get(i).getMessage());
            newTextView.setId(View.generateViewId());
            newTextView.setLayoutParams(new LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT));
            newTextView.setBackgroundResource(R.drawable.bg_rounded_left);
            newTextView.setTextColor(getResources().getColor(android.R.color.white));
            float scale = getResources().getDisplayMetrics().density;
            int dp = (int) (10 * scale + 0.5f);
            newTextView.setPadding(dp, dp, dp, dp);

            final int finalI = i;
            newTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapter.add(adapter.getItemCount(), options.get(finalI).getMessage(), null, true);
                    rv.scrollToPosition(adapter.getItemCount() - 1);
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fad_in_out);
                    view.startAnimation(animation);
                    linearLayout.removeAllViews();
                    loadModel(options.get(finalI).getCode());
                }
            });

            textViews[i] = newTextView;
        }

        handler.postDelayed(new Runnable() {
            public void run() {
                for (int i = 0; i < options.size(); i++) {
                    linearLayout.addView(textViews[i]);
                }
            }
        }, 1000 * messages.size() * 2);
    }
}
