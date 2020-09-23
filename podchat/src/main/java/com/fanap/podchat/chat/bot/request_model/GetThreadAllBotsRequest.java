package com.fanap.podchat.chat.bot.request_model;

import com.fanap.podchat.requestobject.GeneralRequestObject;

public class GetThreadAllBotsRequest extends GeneralRequestObject {

    private long threadId;


    GetThreadAllBotsRequest(GetThreadAllBotsRequest.Builder builder) {
        super(builder);
        this.threadId = builder.threadId;

    }

    public static class Builder extends GeneralRequestObject.Builder<GetThreadAllBotsRequest.Builder> {
        private long threadId;


        public Builder(long threadId) {
            this.threadId = threadId;

        }


        public GetThreadAllBotsRequest build() {
            return new GetThreadAllBotsRequest(this);
        }

        @Override
        protected GetThreadAllBotsRequest.Builder self() {
            return this;
        }
    }



    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }
}
