package com.example.librarydemo.transaction;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface TransactionRepository extends PagingAndSortingRepository<Transaction, String> {
	Transaction findByIsbnAndUserName(String isbn, String userName);
}
