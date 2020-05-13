package com.fanap.podchat.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationManagerCompat;

import com.fanap.podchat.R;
import com.fanap.podchat.chat.App;
import com.fanap.podchat.chat.CoreConfig;
import com.fanap.podchat.mainmodel.AsyncMessage;
import com.fanap.podchat.mainmodel.ChatMessage;
import com.fanap.podchat.model.Error;
import com.fanap.podchat.util.ChatMessageType;
import com.google.firebase.messaging.RemoteMessage;
import com.securepreferences.SecurePreferences;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class PodNotificationManager {

    private static boolean chatIsReady = false;
    private static long DEVICE_TOKEN_HAS_ALREADY_REGISTERED = 152;

    private static final String TARGET_ACTIVITY = "TARGET_ACTIVITY";
    private static final String ICON = "ICON";
    private static final String NOTIF_IMPORTANCE = "N_IMP";
    private static final String CHANNEL_ID = "CHANNEL_ID";
    private static final String PACKAGE_NAME = "PACKAGE_NAME";
    private static final String KEY_FCM_TOKEN = "FCM_TOKEN";
    private static IPodNotificationManager listener;
    private static BroadcastReceiver receiver;
    private static String fcmToken;
    private static SecurePreferences mSecurePrefs;

    private static int STATE = 0;

    private static final int NEED_REGISTER_USER_DEVICE = 1;

    private static final int NEED_REFRESH_TOKEN = 2;


    private static String messageUniqueId = "";

    private static Map<String, Runnable> messagesQ = new HashMap<>();


    private static synchronized String generateUniqueId() {
        return UUID.randomUUID().toString();
    }


//    private static void createRegisterAppRequest(Context context) {
//
//        String uniqueId = generateUniqueId();
//
//        Runnable task = () -> {
//
//            JsonObject content = new JsonObject();
//
//            content.addProperty("appId", context.getApplicationInfo().packageName);
//
//            AsyncMessage message = new AsyncMessage();
//            message.setContent(content.toString());
//            message.setType(ChatMessageType.Constants.REGISTER_FCM_APP);
//            message.setToken(CoreConfig.token);
//            message.setTokenIssuer(CoreConfig.tokenIssuer);
//            message.setUniqueId(uniqueId);
//            message.setTypeCode(CoreConfig.typeCode);
//
//
//            listener.onLogEvent("App Register Request added to queue");
//
//            listener.sendAsyncMessage(App.getGson().toJson(message), "REGISTER NEW NOTIFICATION APP");
//
//        };
//
//        messageUniqueId = uniqueId;
//
//        messagesQ.put(uniqueId, task);
//
//
//    }

    private static void createRegisterUserDeviceRequest(Context context, long userId) {

        String uniqueId = generateUniqueId();
        STATE = NEED_REGISTER_USER_DEVICE;
        Map<String, String> userDeviceTokenMap = new HashMap<>();
        userDeviceTokenMap.put(String.valueOf(userId), fcmToken);

        FcmAppUsersVO fcmAppUsersVO = new FcmAppUsersVO();

        fcmAppUsersVO.setAppId(context.getApplicationInfo().packageName);
        fcmAppUsersVO.setUserDeviceTokenMap(userDeviceTokenMap);


        AsyncMessage message = new AsyncMessage();
        message.setContent(App.getGson().toJson(fcmAppUsersVO));
        message.setType(ChatMessageType.Constants.REGISTER_FCM_USER_DEVICE);
        message.setToken(CoreConfig.token);
        message.setTokenIssuer(CoreConfig.tokenIssuer);
        message.setUniqueId(uniqueId);
        message.setTypeCode(CoreConfig.typeCode);

        messageUniqueId = uniqueId;

        listener.sendAsyncMessage(App.getGson().toJson(message), "Register notification device and user");

    }

    private static void createUpdateUserDeviceRequest(String newToken) {

        String uniqueId = generateUniqueId();
        STATE = NEED_REFRESH_TOKEN;

        Map<String, String> tokensMap = new HashMap<>();
        tokensMap.put(fcmToken, newToken);

        AsyncMessage message = new AsyncMessage();
        message.setContent(App.getGson().toJson(tokensMap));
        message.setType(ChatMessageType.Constants.UPDATE_FCM_APP_USERS_DEVICE);
        message.setToken(CoreConfig.token);
        message.setTokenIssuer(CoreConfig.tokenIssuer);
        message.setUniqueId(uniqueId);
        message.setTypeCode(CoreConfig.typeCode);

        messageUniqueId = uniqueId;

        listener.sendAsyncMessage(App.getGson().toJson(message), "UPDATE_FCM_APP_USERS_DEVICE");


    }

    private static String getPackageName(Context context) {
        return context.getApplicationInfo().packageName;
    }

    public static void registerFCMTokenReceiver(Context context) {

        try {

            fcmToken = getSavedFCMToken(context);

            listener.onLogEvent("Try to register notification receiver");

            if (receiver == null) {

                listener.onLogEvent("Registering notification receiver");

                receiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {

                        String token = intent.getStringExtra(PodChatPushNotificationService.KEY_TOKEN);
//                        if (fcmToken == null) {
//
//                            STATE = NEED_REGISTER_USER_DEVICE;
//
//                            listener.onLogEvent("Register new application");
//
//                            createRegisterUserDeviceRequest(context);
//
//                        } else {
//
//                            STATE = NEED_REGISTER_USER_DEVICE;
//
//                            listener.onLogEvent("Notification token refreshed");
//
//                            createUpdateUserDeviceRequest(token);
//                        }


                        saveFCMToken(token, context);
                    }
                };


                context.registerReceiver(receiver, getFCMTokenIntentFilter());

                listener.onLogEvent("Notification receiver registered successfully");


            }


        } catch (Exception e) {
            listener.onLogEvent("failed to registering notification receiver");
            listener.onLogEvent(e.getMessage());
        }

    }

    public static void unRegisterReceiver(Context context) {

        try {
            listener.onLogEvent("Try to unregister notification receiver");

            context.unregisterReceiver(receiver);
            receiver = null;
            listener.onLogEvent(" unregister notification receiver done");

        } catch (Exception e) {
            listener.onLogEvent("failed to unregistering notification receiver");
            listener.onLogEvent(e.getMessage());
        }

    }

    public static void listenLogs(IPodNotificationManager mListener) {

        listener = mListener;

    }

    private static IntentFilter getFCMTokenIntentFilter() {
        return new IntentFilter(PodChatPushNotificationService.ACTION_REFRESH);
    }

    public static void withConfig(CustomNotificationConfig mConfig, Context context) {

        ShowNotificationHelper.setupNotificationChannel(
                mConfig.getTargetActivity(),
                mConfig.getChannelId(),
                mConfig.getChannelName(),
                mConfig.getChannelDescription(),
                mConfig.getNotificationImportance());

        saveConfig(mConfig, context);
    }

    private static void saveConfig(CustomNotificationConfig mConfig, Context context) {

        SecurePreferences s = getSecurePrefs(context);

        SharedPreferences.Editor e = s.edit();

        e.putString(TARGET_ACTIVITY, mConfig.getTargetActivity().getClass().getName());

        e.putInt(ICON, mConfig.getIcon());

        e.putInt(NOTIF_IMPORTANCE, mConfig.getNotificationImportance());

        e.putString(CHANNEL_ID, mConfig.getChannelId());

        e.putString(PACKAGE_NAME, mConfig.getTargetActivity().getApplication().getApplicationInfo().packageName);

        e.apply();


    }

    private static void showNotification(Map<String, String> data, Context context) {

        SecurePreferences securePreferences = getSecurePrefs(context);

        ShowNotificationHelper.showNewMessageNotification(
                data.get("threadName"),
                data.get("senderName"),
                data.get("image"),
                data.get("content"),
                context,
                securePreferences.getString(TARGET_ACTIVITY, ""),
                securePreferences.getInt(NOTIF_IMPORTANCE, NotificationManagerCompat.IMPORTANCE_DEFAULT),
                securePreferences.getInt(ICON, R.drawable.common_google_signin_btn_icon_dark),
                securePreferences.getString(CHANNEL_ID, "")
        );

    }

    static void handleMessage(Context context, RemoteMessage remoteMessage) {

        Map<String, String> data = remoteMessage.getData();

        showNotification(data, context);

    }

    private static void saveFCMToken(String token, Context context) {


        mSecurePrefs = getSecurePrefs(context);

        mSecurePrefs.edit().putString(KEY_FCM_TOKEN, token).apply();

    }

    private static SecurePreferences getSecurePrefs(Context context) {

        if (mSecurePrefs == null) {
            mSecurePrefs = new SecurePreferences(context, "", "chat_prefs.xml");
        }
        return mSecurePrefs;
    }

    private static String getSavedFCMToken(Context context) {

        SecurePreferences mSecurePrefs = getSecurePrefs(context);

        return mSecurePrefs.getString(KEY_FCM_TOKEN, null);

    }

//    public static void handleOnAppRegistered(ChatMessage chatMessage, Context context, long userId) {
//
//        String content = chatMessage.getContent();
//
//        String uniqueId = chatMessage.getUniqueId();
//
//        if (listener != null) {
//
//            listener.onLogEvent("Notification App registered");
//
//            listener.onLogEvent(content);
//
//            createRegisterUserDeviceRequest(uniqueId, context, userId);
//
//        }
//
//
//    }

    public static void onChatIsReady(Context context, long userId) {

        chatIsReady = true;

        // if notification is not enabled
        if (listener == null) return;


        if (fcmToken == null) {

            //no fcmToken Saved in device
            //check for device register
            createRegisterUserDeviceRequest(context, userId);

        } else {

            //we have saved token
            //update token
            createUpdateUserDeviceRequest(getSavedFCMToken(context));

        }

        if (messagesQ.size() > 0) {

            try {
                if (messagesQ.containsKey(messageUniqueId))
                    Objects.requireNonNull(messagesQ.get(messageUniqueId)).run();
            } catch (Exception e) {
                listener.onLogEvent("could not register app");
            }
        }

    }

    public static void handleOnUserAndDeviceRegistered(ChatMessage chatMessage) {

        String content = chatMessage.getContent();

        String uniqueId = chatMessage.getUniqueId();

        if (listener != null) {

            listener.onLogEvent("User and device registered successfully");

            listener.onLogEvent(content);

            messagesQ.remove(uniqueId);

            messageUniqueId = "";

            STATE = 0;

        }

    }

    public static void handleOnFCMTokenRefreshed(ChatMessage chatMessage) {

        String content = chatMessage.getContent();

        String uniqueId = chatMessage.getUniqueId();

        if (listener != null) {

            listener.onLogEvent("FCM token refreshed successfully");

            listener.onLogEvent(content);

            messagesQ.remove(uniqueId);

            STATE = 0;

        }

    }

    public static boolean isNotificationError(ChatMessage chatMessage, Error error, Context context, long userId) {


        if (listener == null || messageUniqueId.isEmpty()) return false;

        if (messageUniqueId.equals(chatMessage.getUniqueId())) {

            switch (STATE) {

                case NEED_REGISTER_USER_DEVICE: {

                    if (error.getCode() == DEVICE_TOKEN_HAS_ALREADY_REGISTERED) {

                        createUpdateUserDeviceRequest(getSavedFCMToken(context));

                    }

                    break;
                }
                case NEED_REFRESH_TOKEN: {

                    // TODO: 5/13/2020 Handle refreshes error


                    break;
                }
            }

            return true;
        }

        return false;

    }

    public interface IPodNotificationManager {

        void onLogEvent(String log);

        void sendAsyncMessage(String message, String info);
    }

}
