package project.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.DateUtils;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class FileService {

    private Map<String, String> pathDownload = new HashMap<>();

    public String zipFileFolders(List<File> srcfile, File zipfile) {
        byte[] buf = new byte[1024];
        ZipOutputStream out = null;
        try {
            // ZipOutputStream class: Complete the compression of files or folders
            out = new ZipOutputStream(new FileOutputStream(zipfile));
            for (int i = 0; i < srcfile.size(); i++) {
                FileInputStream in = new FileInputStream(srcfile.get(i));
                String filePath="";
                if (filePath == null)
                    filePath = "";
                else
                    filePath += "/";
                out.putNextEntry(new ZipEntry(filePath + srcfile.get(i).getName()));
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.closeEntry();
                in.close();
            }
            out.close();
            return zipfile.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Ошибка";
    }

    public String createZIP(MultipartFile[] filesThis) throws IOException {
        List<File> files=new ArrayList<>();
        for (MultipartFile m:filesThis) {
            File file = new File("D:\\ProjectIntellijIDEA\\lastKurs\\LabServApplication_1\\src\\main\\resources\\filesZIP", m.getOriginalFilename());
            file.setWritable(true, false);
            m.transferTo(file);
            files.add(file);
        }
        String currentTimeUID = String.valueOf(System.currentTimeMillis());
        File zip = new File("D:\\ProjectIntellijIDEA\\lastKurs\\LabServApplication_1\\src\\main\\resources\\filesZIP\\"+ currentTimeUID + ".zip");
        zip.setWritable(true, false);
        zip.createNewFile();
        String fileFolders = zipFileFolders(files, zip);

        return currentTimeUID; //возвращает Uid архива
    }

}
