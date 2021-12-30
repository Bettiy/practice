package com.betty.practice;

import com.betty.practice.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Betty
 * @date 2021年07月29日
 */
@SpringBootTest(classes = PracticeApplication.class)
public class MybatisTest {

	@Resource
	private UserMapper userMapper;

	@Test
	void Test1() {
		List<Map<String, String>> mapList = userMapper.findUser();
		for (Map<?, ?> map : mapList) {
			map.forEach((k, v) -> {
				System.out.println(k + "=" + v);
			});
		}
	}

}
