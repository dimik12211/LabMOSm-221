package project.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipFile;

@Controller
public class DownloadController {

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    private String uid;

    @PostMapping(value = "/download")
    public ResponseEntity download(@RequestParam String uid) throws IOException {
        Path path = Paths.get("D:\\ProjectIntellijIDEA\\lastKurs\\LabServApplication_1\\src\\main\\resources\\filesZIP\\" + uid + ".zip");
        Resource resource = new UrlResource(path.toUri());
        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment;filename=" + uid + ".zip")
                .body(resource);
    }

    @GetMapping(value = "/download")
    public ModelAndView download() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("download.html");
        modelAndView.addObject("uid", uid);
        return modelAndView;
    }
}
