package com.fanap.podchat.requestobject;

import android.support.annotation.NonNull;

public class EditMessageRequest extends GeneralRequestObject {
    private String messageContent;
    private long messageId;
    private String metaData;

     EditMessageRequest(@NonNull Builder builder) {
        super(builder);
        this.metaData = builder.metaData;
        this.messageContent = builder.messageContent;
        this.messageId = builder.messageId;
    }

    public static class Builder extends GeneralRequestObject.Builder<Builder> {
        private String messageContent;
        private long messageId;
        private String metaData;

        public Builder(String messageContent, long messageId) {
            this.messageContent = messageContent;
            this.messageId = messageId;
        }

        @NonNull
        public Builder metaData(String metaData) {
            this.metaData = metaData;
            return this;
        }

        @NonNull
        public EditMessageRequest build() {
            return new EditMessageRequest(this);
        }

        @NonNull
        @Override
        protected Builder self() {
            return this;
        }
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }
}