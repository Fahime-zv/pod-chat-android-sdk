package com.fanap.podchat.requestobject;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RequestAddParticipants extends GeneralRequestObject {
    private long threadId;
    private List<Long> contactIds;
    private List<String> userNames;

    RequestAddParticipants(@NonNull Builder builder) {
        super(builder);
        this.contactIds = builder.contactIds;
        this.threadId = builder.threadId;
        this.userNames = builder.userNames;
    }

    public RequestAddParticipants() {
    }

    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    public List<Long> getContactIds() {
        return contactIds;
    }

    public void setContactIds(List<Long> contactIds) {
        this.contactIds = contactIds;
    }

    public List<String> getUserNames() {
        return userNames;
    }

    public void setUserNames(List<String> userNames) {
        this.userNames = userNames;
    }

    public static class Builder extends GeneralRequestObject.Builder<Builder> {

        private long threadId;
        private List<Long> contactIds;
        private List<String> userNames;

        @Deprecated
        public Builder(long threadId, List<Long> contactIds) {
            this.threadId = threadId;
            this.contactIds = contactIds;
        }

        @Deprecated
        public Builder(long threadId, List<Long> contactIds, List<String> userNames) {
            this.contactIds = contactIds;
            this.threadId = threadId;
            this.userNames = userNames;
        }


        public Builder() {
        }

        @Nullable
        @Override
        protected Builder self() {
            return this;
        }

        @NonNull
        public RequestAddParticipants build() {
            return new RequestAddParticipants(this);
        }


        public static ThreadIdStep newBuilder() {

            return new Steps();
        }


        public static interface ThreadIdStep {

            UsernameStep addParticipantWithUsernameTo(Long threadId);

            ContactIdStep addParticipantWithContactIdTo(Long threadId);

        }


        public static interface UsernameStep {

            BuildStep withUsername(String username);

            BuildStep withUserNames(String... userNames);

            BuildStep withUserNames(List<String> userNames);

        }

        public static interface ContactIdStep {

            BuildStep withContactId(Long contactId);

            BuildStep withContactIds(Long... contactIds);

            BuildStep withContactIds(List<Long> contactIds);

        }


        public static interface BuildStep {


            RequestAddParticipants build();

        }


        private static class Steps implements ThreadIdStep,
                ContactIdStep, UsernameStep, BuildStep {

            private long threadId;
            private List<Long> contactIds;
            private List<String> userNames;


            @Override
            public UsernameStep addParticipantWithUsernameTo(Long threadId) {
                this.threadId = threadId;
                return this;
            }

            @Override
            public ContactIdStep addParticipantWithContactIdTo(Long threadId) {
                this.threadId = threadId;
                return this;
            }

            @Override
            public BuildStep withUsername(String username) {
                this.userNames.add(username);
                return this;
            }

            @Override
            public BuildStep withUserNames(String... userNames) {
                this.userNames = Arrays.asList(userNames);
                return this;
            }

            @Override
            public BuildStep withUserNames(List<String> userNames) {
                this.userNames = userNames;
                return this;
            }

            @Override
            public BuildStep withContactId(Long contactId) {
                contactIds = new ArrayList<>();
                this.contactIds.add(contactId);
                return this;
            }

            @Override
            public BuildStep withContactIds(Long... contactIds) {
                this.contactIds = Arrays.asList(contactIds);
                return this;
            }

            @Override
            public BuildStep withContactIds(List<Long> contactIds) {
                this.contactIds = contactIds;
                return this;
            }

            @Override
            public RequestAddParticipants build() {

                RequestAddParticipants request = new RequestAddParticipants();

                request.setThreadId(threadId);

                if (request.getContactIds() != null)
                    request.setContactIds(contactIds);
                else request.setUserNames(userNames);

                return request;
            }
        }


    }


}
