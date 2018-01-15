package org.nantic.ole;

import org.nantic.entites.Transaction;

import java.util.ArrayList;

public interface ITransactionObserver {
	void addTransactionListener(ITransactionListener newListener);
	void removeTransactionListener(ITransactionListener oldListener);
	void notifyTransactionListener(ArrayList<Transaction> transactions);
}
