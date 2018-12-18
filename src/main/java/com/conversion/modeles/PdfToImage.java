package com.conversion.modeles;

import com.itextpdf.text.Document;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class PdfToImage extends Converter{
    private String outputextention;

    public PdfToImage(InputStream inStream, OutputStream outStream, boolean showMessages, boolean closeStreamsWhenComplete) {
        super(inStream, outStream, showMessages, closeStreamsWhenComplete);
    }
    public PdfToImage(String extention, InputStream inStream, OutputStream outStream){
        super(inStream, outStream, true, true);
        outputextention=extention;

    }
    @Override
    public void convert() throws Exception {
        PDDocument docpdf=PDDocument.load(inStream);
        List<PDPage> pages = docpdf.getDocumentCatalog().getAllPages();
        int count = 0;
        ZipOutputStream zos=new ZipOutputStream(outStream);
        for (PDPage page : pages) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedImage bi = page.convertToImage(BufferedImage.TYPE_INT_ARGB, 600);

            ImageIO.write(bi, outputextention, baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();

            ZipEntry zipEntry = new ZipEntry(count+++"."+outputextention);
            zos.putNextEntry(zipEntry);
                zos.write(baos.toByteArray());
            zos.closeEntry();
           // count++;
        }

            zos.close();

    }
}
