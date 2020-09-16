package com.fanap.podchat.chat.bot.request_model;
import com.fanap.podchat.requestobject.GeneralRequestObject;

public class GetBotCommandsRequest extends GeneralRequestObject{

    private String botName;

    GetBotCommandsRequest(GetBotCommandsRequest.Builder builder) {
        super(builder);
        this.botName = builder.botName;
    }

    public static class Builder extends GeneralRequestObject.Builder<GetBotCommandsRequest.Builder> {
        private String botName;


        public Builder(String botName) {
            this.botName = botName;
        }


        public GetBotCommandsRequest build() {
            return new GetBotCommandsRequest(this);
        }

        @Override
        protected GetBotCommandsRequest.Builder self() {
            return this;
        }
    }

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }

}
