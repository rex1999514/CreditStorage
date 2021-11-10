package com.example.creditstoragebackend.FileDownload;

import com.example.creditstoragebackend.appuser.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class FileDownLoadController {
    String folderPath = "";

    private final UserService userService;

    @Autowired
    public FileDownLoadController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping("/download")
    public String showFiles(Model model) {
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        model.addAttribute("files", listOfFiles);
        return "showFiles";
    }
    @RequestMapping("/file/{fileName}")
    @ResponseBody
    public void show(@PathVariable("fileName") String fileName, HttpServletResponse response)
    {
        if(fileName.indexOf(".doc")>-1) response.setContentType("application/msword");
        if(fileName.indexOf(".docx")>-1) response.setContentType("application/msword");
        if(fileName.indexOf(".xls")>-1) response.setContentType("application/vnd.ms-excel");
        if(fileName.indexOf(".csv")>-1) response.setContentType("application/vnd.ms-excel");
        if(fileName.indexOf(".ppt")>-1) response.setContentType("application/ppt");
        if(fileName.indexOf(".pdf")>-1) response.setContentType("application/pdf");
        if(fileName.indexOf(".zip")>-1) response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment: filename =" +fileName);
        response.setHeader("Content-Transfer-Encoding", "binary");
        try{
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            FileInputStream fis = new FileInputStream(folderPath+fileName);
            int len;
            byte[] buf = new byte[1024];
            while((len = fis.read(buf)) > 0){
                bos.write(buf,0,len);
            }

            //TODO: Find way to get filesize in here
            //userService.updateUserStorageCapacity({fileSize}); need a way to get storagecapacity here


            bos.close();
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
