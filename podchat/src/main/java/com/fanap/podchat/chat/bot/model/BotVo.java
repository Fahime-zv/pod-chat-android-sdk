package com.fanap.podchat.chat.bot.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BotVo {

    @SerializedName("botName")
    private String botName;

    @SerializedName("botUserId")
    private int botUserId;

    @SerializedName("commandList")
    private List<String> commandList;

    @SerializedName("start")
    private boolean start;

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }

    public int getBotUserId() {
        return botUserId;
    }

    public void setBotUserId(int botUserId) {
        this.botUserId = botUserId;
    }

    public List<String> getCommandList() {
        return commandList;
    }

    public void setCommandList(List<String> commandList) {
        this.commandList = commandList;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

}
