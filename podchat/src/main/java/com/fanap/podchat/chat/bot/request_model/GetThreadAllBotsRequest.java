package com.fanap.podchat.chat.bot.request_model;

import com.fanap.podchat.requestobject.GeneralRequestObject;

public class GetThreadAllBotsRequest extends GeneralRequestObject {

    private long threadId;
    private String botName;

    GetThreadAllBotsRequest(GetThreadAllBotsRequest.Builder builder) {
        super(builder);
        this.threadId = builder.threadId;
        this.botName = builder.botName;
    }

    public static class Builder extends GeneralRequestObject.Builder<GetThreadAllBotsRequest.Builder> {
        private long threadId;
        private String botName;

        public Builder(long threadId, String botName) {
            this.threadId = threadId;
            this.botName = botName;
        }


        public GetThreadAllBotsRequest build() {
            return new GetThreadAllBotsRequest(this);
        }

        @Override
        protected GetThreadAllBotsRequest.Builder self() {
            return this;
        }
    }

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }

    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }
}
