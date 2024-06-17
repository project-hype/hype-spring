package com.devjeans.hype.event.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Criteria {
	
	private int page;
	private int amount;
	
	public Criteria() {
		this(1, 10);
	}
	
}
