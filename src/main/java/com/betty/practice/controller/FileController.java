package com.betty.practice.controller;

import com.betty.core.entity.Result;
import com.betty.practice.exception.PracticeException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author Betty
 * @date 2021年06月15日
 */
@Slf4j
@RestController
@RequestMapping("storage")
@ConfigurationProperties(prefix = "upload")
@SuppressWarnings("rawtypes")
public class FileController {

	private String path;

	public String getPath() {
		return path;
	}
	public void setPath(String path){
		this.path = path;
	}

	@SneakyThrows
	@GetMapping("{filename}")
	public void download(@PathVariable("filename") String filename, HttpServletResponse response) {
		String fileName = new String(filename.getBytes(StandardCharsets.UTF_8), "ISO8859-1");
		InputStream in = new FileInputStream(path + filename);
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		OutputStream out = response.getOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		//循环取出流中的数据
		while ((len = in.read(buffer)) != -1) {
			out.write(buffer, 0, len);
		}
		out.close();
	}

	@PostMapping("upload")
	@SneakyThrows
	@SuppressWarnings("ConstantConditions")
	public Result testFile(@RequestParam("file") MultipartFile file) {
		if (file == null) {
			throw new PracticeException("文件为空");
		}
		String filename = file.getOriginalFilename();
		String fileAbsolutePath = path + filename;
		File file1 = new File(fileAbsolutePath);
		if (file1.exists()) {
			throw new PracticeException("文件已存在，请不要重复上传");
		}
		file.transferTo(file1);
		String url = "http://localhost:88/storage/" + URLEncoder.encode(filename, "UTF-8");
		return Result.data(url);
	}

	@GetMapping("remove/{filename}")
	public Result remove(@PathVariable("filename") String filename) {
		File file = new File(path + filename);
		if (!file.exists()) {
			throw new PracticeException("文件不存在");
		}
		return Result.status(file.delete());
	}

}
