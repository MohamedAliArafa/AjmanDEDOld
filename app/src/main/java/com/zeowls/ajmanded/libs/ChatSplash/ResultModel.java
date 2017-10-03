package com.zeowls.ajmanded.libs.ChatSplash;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by root on 9/3/17.
 */

public class ResultModel {
    @SerializedName("messages")
    @Expose
    private List<Message> messages = null;
    @SerializedName("options")
    @Expose
    private List<Option> options = null;

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
}
