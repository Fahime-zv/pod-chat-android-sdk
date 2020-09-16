package com.fanap.podchat.chat.bot.result_model;


import com.fanap.podchat.chat.bot.model.BotVo;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ThreadAllBotsResult {

    @SerializedName("bots")
    private List<BotVo> botList;

    public ThreadAllBotsResult(List<BotVo> botList) {
        this.botList = botList;
    }

    public List<BotVo> getBotList() {
        return botList;
    }

    public void setBotList(List<BotVo> botList) {
        this.botList = botList;
    }
}
