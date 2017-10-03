package com.zeowls.ajmanded.libs.notification;

import android.content.Context;
import android.graphics.Point;

import com.zeowls.ajmanded.ChatHeadUtils;



public class ChatHeadDefaultConfig extends ChatHeadConfig {
    public ChatHeadDefaultConfig(Context context) {
        int diameter = 56;
        setHeadHeight(ChatHeadUtils.dpToPx(context,diameter));
        setHeadWidth(ChatHeadUtils.dpToPx(context, diameter));
        setHeadHorizontalSpacing(ChatHeadUtils.dpToPx(context, 10));
        setHeadVerticalSpacing(ChatHeadUtils.dpToPx(context, 50));
        setInitialPosition(new Point(0,ChatHeadUtils.dpToPx(context, 120)));
        setCloseButtonHidden(false);
        setCloseButtonWidth(ChatHeadUtils.dpToPx(context, 62));
        setCloseButtonHeight(ChatHeadUtils.dpToPx(context, 62));
        setCloseButtonBottomMargin(ChatHeadUtils.dpToPx(context, 50));
        setCircularRingWidth(ChatHeadUtils.dpToPx(context,diameter+5));
        setCircularRingHeight(ChatHeadUtils.dpToPx(context,diameter+5));
        setMaxChatHeads(1);
    }

    @Override
    public int getCircularFanOutRadius(int maxWidth, int maxHeight) {
        return (int) (maxWidth/2.5f);
    }
}
