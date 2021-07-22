package com.betty.practice;

import org.junit.jupiter.api.Test;

/**
 * @author Betty
 * @date 2021年07月22日
 */
public class RegexTest {

	@Test
	void test() {
		String fileName = "upload.png";
		int i = fileName.lastIndexOf(".");
		String fileType = fileName.substring(i);
		String regex = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";
		boolean b = fileName.matches(regex);
		System.out.println(fileType);
		System.out.println(b);
	}

}
