package fanap.podchat.pin.model;


import com.fanap.chatcore.model.base.GeneralRequestObject;

public class RequestPinMessage extends GeneralRequestObject {

    private long messageId;
    private boolean notifyAll;


    public RequestPinMessage(Builder builder) {
        super(builder);
        this.messageId = builder.messageId;
        this.notifyAll = builder.notifyAll;
    }


    public long getMessageId() {
        return messageId;
    }

    public boolean isNotifyAll() {
        return notifyAll;
    }

    public static class Builder extends GeneralRequestObject.Builder {
        long messageId;
        boolean notifyAll;


        public Builder setMessageId(long messageId) {
            this.messageId = messageId;
            return this;
        }

        public Builder setNotifyAll(boolean notifyAll) {
            this.notifyAll = notifyAll;
            return this;
        }


        @Override
        public GeneralRequestObject.Builder typeCode(String typeCode) {
            return super.typeCode(typeCode);
        }

        @Override
        public RequestPinMessage build() {
            return new RequestPinMessage(this);
        }


        @Override
        protected GeneralRequestObject.Builder self() {
            return this;
        }
    }


}
