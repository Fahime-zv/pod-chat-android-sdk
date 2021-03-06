package com.fanap.podchat.requestobject;


import com.fanap.podchat.mainmodel.Invitee;
import com.fanap.podchat.mainmodel.RequestThreadInnerMessage;

import java.io.Serializable;
import java.util.List;

public class RequestCreateThreadWithMessage extends BaseRequestObject {

    private int type;
    private String ownerSsoId;
    private List<Invitee> invitees;
    private String title;
    private RequestThreadInnerMessage message;
    private String description;
    private String image;


    private transient RequestUploadImage uploadImageRequest;


    private int messageType;

    RequestCreateThreadWithMessage(Builder builder) {
        super(builder);
        this.type = builder.type;
        this.message = builder.message;
        this.title = builder.title;
        this.invitees = builder.invitees;
        this.description = builder.description;
        this.image = builder.image;
        this.messageType = builder.messageType;
        this.uploadImageRequest = builder.uploadImageRequest;


    }

    public RequestCreateThreadWithMessage(BaseRequestObject.Builder<?> builder) {
        super(builder);
    }

    public RequestUploadImage getUploadThreadImageRequest() {
        return uploadImageRequest;
    }

    public RequestCreateThreadWithMessage() {
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public RequestThreadInnerMessage getMessage() {
        return message;
    }

    public void setMessage(RequestThreadInnerMessage message) {
        this.message = message;
    }

    public String getOwnerSsoId() {
        return ownerSsoId;
    }

    public void setOwnerSsoId(String ownerSsoId) {
        this.ownerSsoId = ownerSsoId;
    }

    public List<Invitee> getInvitees() {
        return invitees;
    }

    public void setInvitees(List<Invitee> invitees) {
        this.invitees = invitees;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static class Builder extends BaseRequestObject.Builder<Builder> {
        private final int type;
        private final List<Invitee> invitees;
        private String title;
        private RequestThreadInnerMessage message;
        private String description;
        private String image;
        private int messageType;
        private RequestUploadImage uploadImageRequest;


        public Builder(int type, List<Invitee> invitees, int messageType) {
            this.invitees = invitees;
            this.type = type;
            this.messageType = messageType;
        }


        public Builder setUploadThreadImageRequest(RequestUploadImage uploadImageRequest) {
            this.uploadImageRequest = uploadImageRequest;
            return this;
        }

        public Builder message(RequestThreadInnerMessage message) {
            this.message = message;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder image(String image) {
            this.image = image;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }


        public RequestCreateThreadWithMessage build() {
            return new RequestCreateThreadWithMessage(this);
        }
    }
}
