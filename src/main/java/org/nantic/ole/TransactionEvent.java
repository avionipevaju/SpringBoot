package org.nantic.ole;

import org.nantic.entites.Transaction;

public class TransactionEvent {
	
	
	public TransactionEvent(Transaction transaction, TransactionEventStatus eventStatus) {
		this.eventStatus = eventStatus;
		this.transaction = transaction;
	}
	
	
	public Transaction getTransaction() {
		return transaction;
	}
	public TransactionEventStatus getEventStatus() {
		return eventStatus;
	}
	private Transaction transaction;
	private TransactionEventStatus eventStatus;
}
