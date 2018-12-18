package com.conversion;

import com.conversion.modeles.PdfToImage;
import com.conversion.modeles.StaticVariables;
import com.conversion.modeles.ppt.ConversionProcessor;
import com.conversion.modeles.ppt.PdfToPpt;
import com.conversion.modeles.ppt.ProcessorFactory;
import com.itextpdf.text.*;

import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;


import org.apache.commons.io.FileDeleteStrategy;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;

public class ConversionApplication {

    public static void main(String[] args) throws Exception {
        //createPDF();
        //createPDF();
       // StaticVariables.DocToPDF("uploads/1/rapporttp2.docx","uploads/1/rapporttp3.pdf");

        long startTime = System.currentTimeMillis();
//        StaticVariables.ConvertToHTML("uploads/Ch5BisGSPN.doc","uploads/Ch5BisGSPN.html");

  //      System.out.println( "Generate DocxBig.htm with " + ( System.currentTimeMillis() - startTime ) + " ms." );

       // Document document = new Document();
        String input = "C1-Introduction-2_2.pdf"; // .gif and .jpg are ok too!
        String output = "C1-Introduction-2_2.ppt";
        String in="uploads/Ch5BisGSPN.doc";
        String out="uploads/Ch5BisGSPN.doc";

        File[]f= new File("uploads/1").listFiles();

        if (f != null) {
            for (File fil : f) {
                System.gc();
                Thread.sleep(2000);
                FileDeleteStrategy.FORCE.delete(fil);

                    System.err.println(fil.getName()+" /"+ fil.delete());

            }
        }

    }

}
