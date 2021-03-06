package com.fanap.podchat.chat.pin.pin_message.model;

import com.fanap.podchat.mainmodel.Participant;
import com.google.gson.annotations.SerializedName;

public class ResultPinMessage{

	@SerializedName("notifyAll")
	private boolean notifyAll;

	@SerializedName("messageId")
	private int messageId;

	@SerializedName("text")
	private String text;

	@SerializedName("sender")
	private
	Participant participant;

	@SerializedName("time")
	private long time;


	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	public void setNotifyAll(boolean notifyAll){
		this.notifyAll = notifyAll;
	}

	public boolean isNotifyAll(){
		return notifyAll;
	}

	public void setMessageId(int messageId){
		this.messageId = messageId;
	}

	public int getMessageId(){
		return messageId;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	@Override
 	public String toString(){
		return 
			"ResultPinMessage{" + 
			"notifyAll = '" + notifyAll + '\'' + 
			",messageId = '" + messageId + '\'' + 
			",text = '" + text + '\'' + 
			"}";
		}
}