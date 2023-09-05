package com.kh.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // pom.xml에 롬복 가져오기
@NoArgsConstructor
@AllArgsConstructor
public class Person {
	
	private String name;
	private String message;

}
