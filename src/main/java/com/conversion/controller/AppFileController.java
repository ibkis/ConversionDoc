package com.conversion.controller;

import com.aspose.pdf.Document;
import com.aspose.pdf.SaveFormat;
import com.conversion.dao.ConversionFileDao;
import com.conversion.dao.UploadFileResponseDao;
import com.conversion.dao.UsersDao;
import com.conversion.modeles.ConversionFile;
import com.conversion.modeles.StaticVariables;
import com.conversion.modeles.Traitement;
import com.conversion.modeles.Users;
import com.conversion.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

import com.conversion.payload.UploadFileResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class AppFileController {
    String dir="uploads/";
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);


    private TreeMap<String,String> img;
    private TreeMap<String,String> FormatOutput;
    private Thread t;
    @Autowired
    private void init(){
        img=new TreeMap();
        img.put("JPEG","fa fa-file-image-o");
        img.put("JPG","fa fa-file-image-o");
        img.put("PNG","fa fa-file-image-o");
        img.put("DOC","fa fa-file-word-o");
        img.put("DOCX","fa fa-file-word-o");
        img.put("PPTX","fa fa-file-word-o");
        img.put("PPT","fa fa-file-word-o");
        img.put("PDF","fa fa-file-pdf-o");
        FormatOutput=new TreeMap();
        FormatOutput.put(StaticVariables.PPT,StaticVariables.PPT);
        FormatOutput.put(StaticVariables.PPTX,StaticVariables.PPTX);
        FormatOutput.put(StaticVariables.JPEG,StaticVariables.JPEG);
        FormatOutput.put(StaticVariables.PNG,StaticVariables.PNG);
        FormatOutput.put(StaticVariables.JPG,StaticVariables.JPG);
        FormatOutput.put(StaticVariables.PDF,StaticVariables.PDF);


    }


    Traitement traitement=new Traitement();
    Traitement traitement1=new Traitement();
    @Autowired
    FileStorageService fileStorageService;
    @Autowired
    UsersDao usersDao;
    @Autowired
    UploadFileResponseDao uploadFileResponseDao;
    @Autowired
    ConversionFileDao fileAttentConversion;
    @Autowired
    private void Doconversion(){
         t=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(fileAttentConversion.count()==0) {
                        traitement.waitin();
                    }

                    List<ConversionFile> listup=fileAttentConversion.findAll();
                    fileAttentConversion.deleteAll(listup);
                    while (listup.size()!=0) {
                        try {
                            Thread.sleep(5000);

                            ConversionFile filecon = listup.remove(0);
                            UploadFileResponse uploadFileResponse = uploadFileResponseDao.findById(filecon.getId()).get();
                            uploadFileResponse.setStatus(StaticVariables.ENCOURS);
                            uploadFileResponse.setEncours(new Date().getTime());
                            uploadFileResponse.getT_attente();
                            uploadFileResponseDao.save(uploadFileResponse);

                            // load the file to be converted
                            System.out.println(fileStorageService.getFileStorageLocation().toFile().getName()+"/"+filecon.getUserId()+"/"+filecon.getFileName().toLowerCase());

                            Thread t2=new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    String nam=filecon.getFileName().split("\\.")[0];
                                    try {
                                    switch (filecon.getFilteTypeOut().toUpperCase().trim()){
                                        case StaticVariables.PDF :
                                                StaticVariables.OutputPDF(fileStorageService.getFileStorageLocation().toFile().getName()+"/"+filecon.getUserId()+"/"
                                                        + filecon.getFileName().toLowerCase(), fileStorageService.getFileStorageLocation().toFile().getName()+"/"+filecon.getUserId()+"/"
                                                        + nam+"."+uploadFileResponse.getFilteTypeOut().toLowerCase(),uploadFileResponse.getFileType());
                                            break;
                                        case StaticVariables.PNG:
                                            StaticVariables.OutputImage(fileStorageService.getFileStorageLocation().toFile().getName()+"/"+filecon.getUserId(),fileStorageService.getFileStorageLocation().toFile().getName()+"/"+filecon.getUserId()+"/"
                                                    + filecon.getFileName().toLowerCase(), fileStorageService.getFileStorageLocation().toFile().getName()+"/"+filecon.getUserId()+"/"
                                                    + nam+"."+getExtensionOutput(uploadFileResponse.getFilteTypeOut()),uploadFileResponse.getFileType(),StaticVariables.PNG.toLowerCase());
                                            break;
                                        case StaticVariables.JPEG:
                                            StaticVariables.OutputImage(fileStorageService.getFileStorageLocation().toFile().getName()+"/"+filecon.getUserId(),fileStorageService.getFileStorageLocation().toFile().getName()+"/"+filecon.getUserId()+"/"
                                                    + filecon.getFileName().toLowerCase(), fileStorageService.getFileStorageLocation().toFile().getName()+"/"+filecon.getUserId()+"/"
                                                    + nam+"."+getExtensionOutput(uploadFileResponse.getFilteTypeOut()),uploadFileResponse.getFileType(),StaticVariables.JPEG.toLowerCase());

                                            break;
                                        case StaticVariables.JPG:
                                            StaticVariables.OutputImage(fileStorageService.getFileStorageLocation().toFile().getName()+"/"+filecon.getUserId(),fileStorageService.getFileStorageLocation().toFile().getName()+"/"+filecon.getUserId()+"/"
                                                    + filecon.getFileName().toLowerCase(), fileStorageService.getFileStorageLocation().toFile().getName()+"/"+filecon.getUserId()+"/"
                                                    + nam+"."+getExtensionOutput(uploadFileResponse.getFilteTypeOut()),uploadFileResponse.getFileType(),StaticVariables.JPG.toLowerCase());
                                            break;
                                        case StaticVariables.PPT:
                                            StaticVariables.OutputPPT(fileStorageService.getFileStorageLocation().toFile().getName()+"/"+filecon.getUserId()+"/"
                                                    + filecon.getFileName().toLowerCase(), fileStorageService.getFileStorageLocation().toFile().getName()+"/"+filecon.getUserId()+"/"
                                                    + nam+"."+uploadFileResponse.getFilteTypeOut().toLowerCase(),uploadFileResponse.getFileType(),0);
                                            break;
                                        case StaticVariables.PPTX:
                                            StaticVariables.OutputPPT(fileStorageService.getFileStorageLocation().toFile().getName()+"/"+filecon.getUserId()+"/"
                                                    + filecon.getFileName().toLowerCase(), fileStorageService.getFileStorageLocation().toFile().getName()+"/"+filecon.getUserId()+"/"
                                                    + nam+"."+uploadFileResponse.getFilteTypeOut().toLowerCase(),uploadFileResponse.getFileType(),1);
                                            break;
                                    }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            t2.start();
                            t2.join();

                            //document.
                            //send mail

                                    Users user=usersDao.findById(uploadFileResponse.getIduser()).get();
                                    if (!user.getEmail().isEmpty()){
                                        String u=    StaticVariables.getURL()+uploadFileResponse.getFileDownloadUri();
                                        StaticVariables.send(user.getEmail(),StaticVariables.msformat1(StaticVariables.TERMINER, u),
                                                StaticVariables.Doc);
                                    }
                                    System.err.println("Trerminer Conversion");
                                    uploadFileResponse.setStatus(StaticVariables.TERMINER);
                                    uploadFileResponse.setTerminer(new Date().getTime());
                                    uploadFileResponse.getT_encour();
                                    uploadFileResponseDao.save(uploadFileResponse);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.err.println("notification");
                    traitement1.notification();
                }
            }
        });
         t.start();
    }
    @Autowired
    private void DocDelete(){
        t=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(uploadFileResponseDao.count()==0) {
                        traitement1.waitin();
                    }

                    List<UploadFileResponse> deletefile= uploadFileResponseDao.filetodelete(System.currentTimeMillis());
                    while (deletefile.size()!=0) {
                        try {
                            UploadFileResponse de=deletefile.remove(0);

                            File[]f= new File(fileStorageService.getFileStorageLocation().toFile().getName()+"/"+de.getIduser()).listFiles();
                            boolean tr=false;
                            if (f != null) {
                                for (File fil : f) {
                                        tr=fil.delete();
                                        System.err.println(fil.getName()+" /"+ tr);

                                }
                            }
                            if (tr) {
                                Files.delete(Paths.get(fileStorageService.getFileStorageLocation().toFile().getName() + "/" + de.getIduser()));
                            }
                            uploadFileResponseDao.delete(de);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();

    }

    @RequestMapping("/")
    public String classements(Model model, HttpServletRequest request) {

        model.addAttribute("formats", FormatOutput.values());
        StaticVariables.setURL(request.getRequestURL().toString());
        return "index";
    }
    @RequestMapping("/status/{id}")
    public String status(Model model, @PathVariable int id) {
        model.addAttribute("files", uploadFileResponseDao.finByIdUser(id));
        return "status";
    }

    public UploadFileResponse uploadFile(MultipartFile file,String outtype,int iduser) {
        String fileName =fileStorageService.storeFile(file,""+iduser);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileStorageService.getFileStorageLocation().toFile().getName()+"/"+iduser+"/"+fileName.split("\\.")[0]+"."+getExtensionOutput(outtype.toUpperCase()))
                .toUriString();

        return new UploadFileResponse(fileName,fileDownloadUri,fileName.split("\\.")[1],outtype,file.getSize(), iduser,new Date());
    }
    public UploadFileResponse uploadFile1(MultipartFile file,String outtype,int iduser) {

        return new UploadFileResponse(" "," ", StringUtils.cleanPath(file.getOriginalFilename()).split("\\.")[1],outtype,file.getSize(), iduser,new Date());
    }
    @PostMapping("/uploadDoc")
    @ResponseBody
    public String uploadDoc(@RequestParam("files") MultipartFile[] files,@RequestParam("email") String email,
                            @RequestParam("typefileout") String[] typefileout) {

        Users users=usersDao.save(new Users(email));
        for (int i=0;i<files.length;i++){//verification format
            UploadFileResponse uploadFileResponse=uploadFile1(files[i],typefileout[i],users.getId());
            if(!ExtensionIsConforme(uploadFileResponse.getFileType().toUpperCase(),uploadFileResponse.getFilteTypeOut())) {
            return StaticVariables.mesPop(StaticVariables.errFormat,StaticVariables.danger);
            }
        }

        for (int i=0;i<files.length;i++){
            UploadFileResponse uploadFileResponse=uploadFile(files[i],typefileout[i],users.getId());
            uploadFileResponse.setClassout(img.get(uploadFileResponse.getFilteTypeOut()));
            String tyin=uploadFileResponse.getFileType();
            tyin=tyin.toUpperCase();
                tyin = img.get(tyin);
                uploadFileResponse.setClassint(tyin == null ? img.get("DOC") : tyin);
                uploadFileResponseDao.save(uploadFileResponse);
                fileAttentConversion.save(new ConversionFile(uploadFileResponse));
        }
        traitement.notification();
        String u=    StaticVariables.getURL()+"status/"+users.getId();
        //Users
        if (!email.isEmpty()){
            StaticVariables.send(email,StaticVariables.msformat(StaticVariables.ATTENTE, u),
                    StaticVariables.Doc);
        }
        return StaticVariables.mesPop(StaticVariables.UpSuccess+StaticVariables.Visite+StaticVariables.link(u),StaticVariables.info);
    }

    @GetMapping("/downloadFile/{upload}/{id}/{fileName}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable String upload,@PathVariable String id,@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(upload+"/"+id,fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    public boolean  ExtensionIsConforme(String input,String output){
        if (input.equals(StaticVariables.PNG)||input.equals(StaticVariables.JPEG)||input.equals(StaticVariables.JPG)){
            if(output.equals(StaticVariables.PNG)||output.equals(StaticVariables.JPEG)||output.equals(StaticVariables.JPG)) return false;
        }

        return !input.equals(output)&&img.containsKey(input)&& FormatOutput.containsKey(output) ;
    }
    private String getExtensionOutput(String outputextension){
        System.err.println(outputextension);
        return outputextension.equals(StaticVariables.JPEG)||outputextension.equals(StaticVariables.JPG)||outputextension.equals(StaticVariables.PNG)?"zip":outputextension.toLowerCase();
    }
}
