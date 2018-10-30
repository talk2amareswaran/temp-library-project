package com.example.librarydemo.transaction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Document(collection = "transactions")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Transaction {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	@NonNull
	private String isbn;
	@NonNull
	private String userName;
	private Integer renewalCount;
}
