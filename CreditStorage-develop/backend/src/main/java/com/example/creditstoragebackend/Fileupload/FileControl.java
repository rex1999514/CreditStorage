package com.example.creditstoragebackend.Fileupload;

import com.example.creditstoragebackend.appuser.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
    @Controller
    public class FileControl {
        public static String uploadDirectory = System.getProperty("user.dir") +"/uploads";

        private final UserService userService;

        @Autowired
        public FileControl(UserService userService) {
            this.userService = userService;
        }


        @RequestMapping("/select")
        public String uploadPage(Model model) {
            return "uploadview";
        }
        @RequestMapping("/uploadlocal")
        public String upload(Model model, @RequestParam("files")MultipartFile[] files) throws IOException {
            StringBuilder fileNames = new StringBuilder();
            for(MultipartFile file:files)
            {
                Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
                fileNames.append(file.getOriginalFilename() + " ");
                Files.write(fileNameAndPath, file.getBytes());
                userService.updateUserStorageCapacity((double) file.getSize()); //updates user capacity, no idea if it works
            }
            model.addAttribute("msg", "Successfully uploaded files " +fileNames);
            return "uploadstatusview";
        }

    }
