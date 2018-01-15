package org.nantic.ole.impl;

import org.nantic.entites.Transaction;
import org.nantic.ole.ITransactionListener;
import org.nantic.ole.ITransactionObserver;

import java.util.ArrayList;
import java.util.List;

public class TransactionObserver implements ITransactionObserver {

	public TransactionObserver() {
		this.listeners = new ArrayList<ITransactionListener>();
	}
	
	@Override
	public void addTransactionListener(ITransactionListener newListener) {
		if(newListener == null)
			return;
		if(this.listeners ==null)
			this.listeners = new ArrayList<ITransactionListener>();
		if(this.listeners.contains(newListener))
			return;
		this.listeners.add(newListener);
	}

	@Override
	public void removeTransactionListener(ITransactionListener oldListener) {
		if(oldListener == null || this.listeners == null || !this.listeners.contains(oldListener))
			return;
		this.listeners.remove(oldListener);
	}

	@Override
	public void notifyTransactionListener(ArrayList<Transaction> transactions) {
		if(transactions == null || this.listeners == null || this.listeners.isEmpty())
			return;
		List<ITransactionListener> tmpListeners;
		synchronized (this.listeners) {
			tmpListeners = new ArrayList<ITransactionListener>(this.listeners);
		}
		for(ITransactionListener listener : tmpListeners){
			listener.performedTransactionEvent(transactions);
		}
	}
	
	private List<ITransactionListener> listeners;
	
}
