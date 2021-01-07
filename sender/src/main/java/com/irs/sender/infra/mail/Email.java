package com.irs.sender.infra.mail;

import com.irs.sender.domain.Person;

public interface Email {

	void sendMessage(Person person);
}
