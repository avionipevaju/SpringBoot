package org.nantic.ole;

import org.nantic.entites.Transaction;

import java.util.ArrayList;

public interface ITransactionListener {
	void performedTransactionEvent(ArrayList<Transaction> transactions);
}
