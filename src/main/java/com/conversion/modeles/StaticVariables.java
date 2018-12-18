package com.conversion.modeles;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

public class StaticVariables {
    public static String ENCOURS = "ENCOURS";
    public static String ATTENTE = "ATTENTE";
    public static String TERMINER = "TERMINER";
    public static String Doc = "Conversion Document";
    public static String UpSuccess = "Upload Success";
    public static String errFormat = "Format de document insupportable";
    public static String Visite = "Visitez :";
    public static String URL;
    public static String danger = "danger", info = "info";
    public static final String DOC="DOC",DOCX="DOCX",PPT="PPT",PPTX="PPTX",ODT="ODT",PNG="PNG",JPG="JPG",JPEG="JPEG",PDF="PDF";
    public static String getURL() {
        return URL;
    }

    public static void setURL(String URL) {
        if (StaticVariables.URL == null) {
            StaticVariables.URL = URL;
        }
    }

    public static String msformat(String status, String url) {
        return "<h1>Conversion Document</h1><br/><br/>" +
                "<p>Traitement Document: <b>" + status + "</b> pour plus d'information visitez le lien ci-dessous<p/>" +
                link(url)
                ;
    }

    public static String msformat1(String status, String url) {
        return "<h1>Conversion Document</h1><br/><br/>" +
                "<p>Traitement Document: <b>" + status + "</b> lien de telecharger<p/>" +
                link(url)
                ;
    }

    public static String link(String lin) {
        return "<a href=\"" + lin + "\" type=\"button\" class=\"btn btn-round btn-primary\">Cliquer ici<a/>";
    }

    public static void send(String email, String ms, String sub) {
        String to = email;//change accordingly
        String from = "karimouseyni.com";
        //Get the session object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("ibrahimkarimouseyni56@gmail.com", "fatidosso");
                    }
                });
        //compose the message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setContent(ms, "text/html");
            // Send message
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public static String mesPop(String ms, String tyoe) {

        return "<div class=\"alert alert-" + tyoe + " alert-dismissible fade in\" role=\"alert\">\n" +
                " <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span>\n" +
                " </button>\n" +
                " " + ms + "\n" +
                " </div>";
    }

    public static InputStream getInFileStream(String inputFilePath) throws FileNotFoundException {
        File inFile = new File(inputFilePath);
        FileInputStream iStream = new FileInputStream(inFile);
        return iStream;
    }

    public static OutputStream getOutFileStream(String outputFilePath) throws IOException {
        File outFile = new File(outputFilePath);
       // outFile.createNewFile();
        FileOutputStream oStream = new FileOutputStream(outFile);
        return oStream;
    }

    public static void OutputImage (String dir,String inPath, String outPath,String typeIn,String out) throws Exception {
        Converter converter=null;
        InputStream inStream = getInFileStream(inPath);
        OutputStream outStream = getOutFileStream(outPath);
        switch (typeIn.toUpperCase()){
            case DOC:
                converter=new DocToPDFConverter(inStream, outStream, true, true);
                break;
            case DOCX :
                converter=new DocxToPDFConverter(inStream, outStream, true, true);
                break;
            case  PPT:
                converter=new PpptToImage(dir,out,inStream, outStream);;
                break;
            case PPTX:
                converter=new PpptxToImage(dir,out,inStream, outStream);
                break;
            case PDF:
                converter=new PdfToImage(out,inStream, outStream);
                break;
        }
        converter.convert();

    }
    public static void OutputPDF(String inPath, String pdfPath,String typeIn) throws Exception {
        Converter converter=null;
        InputStream inStream = getInFileStream(inPath);
        OutputStream outStream = getOutFileStream(pdfPath);
        switch (typeIn.toUpperCase()){
            case DOC:
                converter=new DocToPDFConverter(inStream, outStream, true, true);
                break;
            case DOCX :
                converter=new DocxToPDFConverter(inStream, outStream, true, true);
                break;
            case  PPT:
                converter=new PptToPDFConverter(inStream, outStream, true, true);
                break;
            case PPTX:
                converter=new PptxToPDFConverter(inStream, outStream, true, true);
                break;
            case JPG:
                converter=new ImageToPdf(inPath, outStream);
                break;
            case JPEG:
                converter=new ImageToPdf(inPath, outStream);
                break;
            case PNG:
                converter=new ImageToPdf(inPath, outStream);
                break;
        }
        converter.convert();

    }
    public static void OutputPPT(String inPath, String outPath,String typeIn,int typ) throws Exception {
        Converter converter=null;
        InputStream inStream = getInFileStream(inPath);
        OutputStream outStream = getOutFileStream(outPath);
        switch (typeIn.toUpperCase()){
            case DOC:
                converter=new DocToPDFConverter(inStream, outStream, true, true);
                break;
            case DOCX :
                converter=new DocxToPDFConverter(inStream, outStream, true, true);
                break;
            case PDF :
                converter=new PdtToPpt(new File(inPath),new File(outPath));
                break;
            case JPG:
                if(typ==0)
                    converter=new ImageToPpt(inStream, outStream, true, true);
                else
                    converter=new ImageToPptx(inStream, outStream, true, true);
                break;
            case JPEG:
                if(typ==0)
                    converter=new ImageToPpt(inStream, outStream, true, true);
                else
                    converter=new ImageToPptx(inStream, outStream, true, true);
                break;
            case PNG:
                if(typ==0)
                    converter=new ImageToPpt(inStream, outStream, true, true);
                else
                    converter=new ImageToPptx(inStream, outStream, true, true);
                break;
        }
        converter.convert();

    }
    public static void ConvertToHTML(String docPath, String htmlPath) {
        try {
            InputStream doc = new FileInputStream(new File(docPath));
            XWPFDocument document = new XWPFDocument(doc);
            XHTMLOptions options = XHTMLOptions.create();
            OutputStream out = new FileOutputStream(new File(htmlPath));
            XHTMLConverter.getInstance().convert(document, out, options);
        } catch (FileNotFoundException ex) {
           // Logger.getLogger(Convert.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //Logger.getLogger(Convert.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void addToZipFile(String fileName, ZipOutputStream zos) throws FileNotFoundException, IOException {

        System.out.println("Writing '" + fileName + "' to zip file");

        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zos.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zos.write(bytes, 0, length);
        }

        zos.closeEntry();
        fis.close();
        file.delete();
    }


}
