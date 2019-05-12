# Chat-Pod
A Chat helepr that works with Fanap's POD Chat service.

### Change Log
All notable changes to this project will be documented [here](https://github.com/Sinarahimi/Chat-Pod/blob/develop/CHANGELOG.md).

### Prerequisites

You need to Add this module to your project and after that set the `internet` permission in the manifest.
```java
<uses-permission android:name="android.permission.INTERNET" />
```
There is two another permission you need to add to your manifest
```java
<uses-permission android:name="android.permission.READ_CONTACTS" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```
The first step is to initialize the Chat module.

```java
chat.init(context);
```
Then you need to connect to chat server with this method.

```java
@deprecated
chat.connect(String socketServerAddress,String appId,String  serverName,String token, String ssoHost, String platformHost, String fileServer)@deprecated

        //or
chat.connect(RequestConnect requestConnect) {}
```
And now it's ready for chat .

|Num|Thread & Base Method           | Description                                                                            |
|:--|:------------------------------|:------------------------------------------------------------------------------------|
|1|`getThreads(RequestThread requestThread, ChatHandler handler)`| Gets thread list|
|10|`logOutSocket()`    | log out of the socket.      |
|13|`isLoggable(boolean log)`         | shows log        |
|14|`rawLog(boolean rawLog)`         | shows log without any changes         |
|20|`getHistory(RequestGetHistory request, ChatHandler handler)`    |Gets list of conversation         |
|21|`searchHistory(NosqlListMessageCriteriaVO messageCriteriaVO, ChatHandler handler)`  |search through conversation history|
|31|`spam(RequestSpam request)` | |
|36|`getUserInfo(ChatHandler handler) ` | |
|18|`leaveThread(RequestLeaveThread request, ChatHandler handler)`|leaves from the thread      |
|20|`getHistory(RequestGetHistory request, ChatHandler handler)`    |Gets list of conversation         |
|33|`createThreadWithMessage(RequestCreateThread threadRequest) ` | |
|37|`unMuteThread(RequestMuteThread request, ChatHandler handler)` | |
|40|`muteThread(RequestMuteThread request, ChatHandler handler)` | |

|Num|Admin Method           | Description                                                                            |
|:--|:------------------------------|:------------------------------------------------------------------------------------|
|15|`setAdmin(RequestAddAdmin requestAddAdmin)`        |  Adds admin with rules or removes them           |

|Num|Participant Method           | Description                                                                            |
|:--|:------------------------------|:------------------------------------------------------------------------------------|
|34|`getThreadParticipants(RequestThreadParticipant request, ChatHandler handler) ` | |
|16|`addParticipants(RequestAddParticipants request, ChatHandler handler)`    | adds participant to the group      |
|17|`removeParticipants(RequestRemoveParticipants request, ChatHandler handler)` |  removes participant of the group |

⭐️

|Num|Message Method           | Description   (All of the methods returns string as Unique id)|
|:--|:------------------------------|:----------------------------------------------------------------------------------|
|11|`sendTextMessage(RequestMessage requestMessage, ChatHandler handler)`               |  send Text message      |
|12|`sendFileMessage(RequestFileMessage requestFileMessage, ProgressHandler.sendFileMessage handler)`|Send file with message|
| |`resendMessage(String uniqueId) ` | |
| |`replyMessage(RequestReplyMessage request, ChatHandler handler)` | |
| |`deleteMessage(RequestDeleteMessage request, ChatHandler handler)` | |

|Num|File Method           | Description   (All of the methods returns string as Unique id)|
|:--|:------------------------------|:----------------------------------------------------------------------------------|
| |`uploadImage(RequestUploadImage requestUploadImage) ` | |
| |`uploadFile(@NonNull RequestUploadFile requestUploadFile) ` | |
| |`cancelUpload(String uniqueId) ` | |
| |`retryUpload(RetryUpload retry, ProgressHandler.sendFileMessage handler)` | |
| |`getFile(RequestGetFile requestGetFile) ` | |
| |`getImage(RequestGetImage requestGetImage)` | |
|13 |`uploadImageProgress(RequestUploadImage requestUploadImage,ProgressHandler.onProgress handler) ` | |



⭐️

|Num|Contact Method           | Description                                                                            |
|:--|:------------------------------|:---------------------------------------------------------------------------------------|
|24|`getContacts(RequestGetContact request, ChatHandler handler)` |Gets contact list |
|26|`addContact(RequestAddContact request)` |Adds contact|
|27|`removeContact(RequestRemoveContact request)` |Removes contact|
|28|`updateContact(RequestUpdateContact request)` |Updates contacs|
|29|`block(RequestBlock request, ChatHandler handler)` |Block thread or contact|
|30|`unblock(RequestUnBlock request, ChatHandler handler)` |Unblock|
|5|`syncContact(Activity activity)`         | Sync mobile's Contact with server contact      |
|9|`searchContact(SearchContact searchContact)`         |  search through the contacts    |
|32|`getBlockList(RequestBlockList request, ChatHandler handler)` | |


⭐️

|Num|Map Method           | Description                                                                            |
|:--|:------------------------------|:-----------------------------------------------------------------------------|
|2|`mapSearch(RequestMapSearch request)`         | give you the direction     |
|2|`mapRouting(RequestMapRouting request)`         | give you the direction     |
|2|`mapStaticImage(RequestMapStaticImage request)`         | gives  the image of the map|
|2|`sendLocationMessage(RequestLocationMessage request)`         | send message with the image of the map|
|2|`mapReverse(RequestMapReverse request)`         | give you the direction     |

⭐️⭐️

### Register Listener
After creating a Chat instance, you should call addListener method to register a ChatListener that receives Chat events.
ChatAdapter is an empty implementation of ChatListener interface.
For getting call back you should extend your class from `ChatAdapter`.

###The table below is the list of callback methods defined in ChatListener interface.

|Num|Thread & Base Method           | Listener Description       |
|:--|:------------------------------|:---------------------------------------------------------------------------------------|
|1| `onGetHistory()`| Called when history of the threadVo is return.      |
|2| `onGetThread()`       |  Called when get threadVos is return.          |
|3| `onMuteThread()`                 |Called when threadVo is muted.         |
|4| `onUnmuteThread()`         | Called when message is un muted.      |
|5| `onUserInfo()`         | Called when information of the user is return.     |
|6| `onCreateThread()`         |Called when threadVo is created.         |
|7| `onGetThreadParticipant()`         |Called when you want participants of the specific threadVo.         |
|8| `onRenameThread()`         |Called when you rename of the threadVo that you are admin of that       |
|9| `onChatState()`         | Return the last state of the chat      |
|10| `onThreadInfoUpdated()`         | Called when something change is the threadVo      |
|11| `onThreadRemoveParticipant()`         |Called when remove the participant of the group      |
|12| `onThreadLeaveParticipant()`         | Called when participant of the group leaves the threadVo      |
|13| `onThreadAddParticipant()`         |Called when contact added as a participant in the group       |
|14| `onError()`         |Called when something error happend       |

⭐️⭐️

|Num|Contact Method           | Listener Description       |
|:--|:------------------------------|:---------------------------------------------------------------------------------------|
|1| `onGetContacts()`| Called when get contacts is return.      |
|2| `onContactAdded()` |Called when contact added to your contact       |
|3| `onGetBlockList()`         |Called when list of block contacts are received        |
|4| `onBlock()`         |Called when contact was blocked       |
|5| `onUnBlock()`         |Called when contact was unblocked      |
|6| `onSearchContact()`         |Called when        |
|7| `onRemoveContact()`         |Called when you want to remove contact       |
|8| `onSyncContact()`         |Called your phone contact sync to the server contact       |
|9| `onUpdateContact()`         |Called when update contact received       |

⭐️⭐️

|Num|Map Method           | Listener Description       |
|:--|:------------------------------|:---------------------------------------------------------------------------------------|
|1| `onMapSearch()`         |       |
|2| `onMapRouting()`         |       |

⭐️⭐️

|Num|Message Method                 | Listener Description                                                                            |
|:--|:------------------------------|:---------------------------------------------------------------------------------------|
|1| `onDeliver()`   | Called when message is delivered.       |
|2| `onSeen()`                |  Called when message is seen.  |
|3| `onSent()`         | Called when message is sent.       |
|4| `onEditedMessage()`         |Called when message edited       |
|5| `onDeleteMessage()`         | Called when message delited      |
|6| `onNewMessage()`         |Called when new message recived      |
|7| `onUploadFile()`         |Called when file uploaded       |
|8| `onUploadImageFile()`         | Called when image uploaded      |


## Built With :heart:

* [Retrofit2](https://square.github.io/retrofit/) - Retrofit2
* [Rxjava](https://github.com/ReactiveX/RxAndroid) - Rxjava
gson
rxandroid
room
saferoom
dagger
secure-preferences
podasync


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Contact Me

If you have ideas or feedback, feel free to open up issues, put up pull reqeusts, and  [contact me directly](mailto:develop.rahimi95@gmail.com).
