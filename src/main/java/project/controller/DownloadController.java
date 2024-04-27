package project.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Controller
public class DownloadController {

    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @GetMapping(value = "/download")
    public ModelAndView download() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("download.html");
        modelAndView.addObject("uid", uid);
        return modelAndView;
    }

    @PostMapping(value = "/download")
    public ResponseEntity download(@RequestParam String uid) throws IOException {
        File file = new File("D:\\ProjectIntellijIDEA\\lastKurs\\LabServApplication_1\\src\\main\\resources\\filesZIP\\", uid + ".zip");
        
        ZipFile zipFile = new ZipFile("D:\\ProjectIntellijIDEA\\lastKurs\\LabServApplication_1\\src\\main\\resources\\filesZIP\\" + uid + ".zip");

        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        InputStream stream = null;
        while(entries.hasMoreElements()){
            ZipEntry entry = entries.nextElement();
            stream = zipFile.getInputStream(entry);
        }


        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment;filename=" + uid + ".zip")
                .contentType(MediaType.valueOf("application/zip"))
                .body("sdfgsgszf");
    }
}
