package br.com.blogapi.blog.application.resourcehandler;

import java.util.ArrayList;
import java.util.List;

public class Messages {

	private List<String> messages = new ArrayList<>();

	public Messages() {
	}

	public Messages(String message) {
		addMessage(message);
	}

	public List<String> getMessages() {
		return messages;
	}

	public Messages addMessage(String message) {
		this.messages.add(message);
		return this;
	}

	public Messages build() {
		return this;
	}
}
