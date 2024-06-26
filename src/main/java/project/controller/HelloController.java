package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import project.service.FileService;

import java.io.IOException;

@Controller
public class HelloController {

    @Autowired
    private FileService fileService;

    @Autowired
    private DownloadController downloadController;


    @PostMapping(value = "/postDownloadFile")
    public ModelAndView postDownloadFile(@RequestParam("file") MultipartFile[] file) throws IOException {

        String uid = fileService.createZIP(file);
        downloadController.setUid(uid);

        ModelAndView modelAndView = new ModelAndView(new RedirectView("download"));
        modelAndView.addObject("uid", uid);

        return modelAndView;
    }

    @GetMapping(value = "/getDownloadFile")

    public ModelAndView getDownloadFile() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello.html");
        return modelAndView;
    }

}
