package com.shopping.hee.controller;

import com.shopping.hee.utils.MyPath;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileController {
    @PostMapping("img")
    public String img(@ModelAttribute("pic") MultipartFile pic) {
        String imgName = pic.getOriginalFilename();
        System.out.println("imgName = " + imgName);

        Path imgPath = Paths.get(MyPath.imgPath + imgName);

        try {
            Files.write(imgPath, pic.getBytes());
        } catch (Exception e) {

        }

        return "fileResult";
    }

    @GetMapping("/file")
    public String fileEx(Model model) {
        return "file";
    }

    @GetMapping("/result")
    public String fileResult(Model model) {
        return "fileResult";
    }
}
