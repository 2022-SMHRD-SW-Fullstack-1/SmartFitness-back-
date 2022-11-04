package com.smartfitness.demo.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("file")
@RestController
public class FileController {
	
	private static String UPLOAD_FOLDER = "C://Temp/upload";
	
	@PostMapping("/up")
	public ModelAndView showUpload() {
		return new ModelAndView("upload");
	}
	
	@PostMapping("/up")
	public ModelAndView fileUpload(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return new ModelAndView("status", "message", "Please select a file and try again");
		}
		try {
			//read and write the file to the selected location-
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOAD_FOLDER+file.getOriginalFilename());
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ModelAndView("status", "message","File Uploaded successfully");
	}

}
