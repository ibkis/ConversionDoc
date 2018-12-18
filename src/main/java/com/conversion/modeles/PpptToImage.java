package com.conversion.modeles;

import com.itextpdf.text.Document;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.usermodel.SlideShow;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class PpptToImage extends Converter  {

    private String outputextention,dir;

    public PpptToImage(InputStream inStream, OutputStream outStream, boolean showMessages, boolean closeStreamsWhenComplete) {
        super(inStream, outStream, showMessages, closeStreamsWhenComplete);
    }
    public PpptToImage(String extention,String dir,InputStream inStream, OutputStream outStream){
        super(inStream, outStream, true, true);
        outputextention=extention;
        this.dir=dir;
    }
    @Override
    public void convert() throws Exception {
        SlideShow ppt = new SlideShow(inStream);
        //getting the dimensions and size of the slide

        Dimension pgsize = ppt.getPageSize();
        List<Slide> slide = Arrays.asList(ppt.getSlides());
        BufferedImage img=null;
        Document doc=new Document();
        try {
            ZipOutputStream zos = new ZipOutputStream(outStream);
            doc.open();
            for (int i = 0; i < slide.size(); i++) {
                img = new BufferedImage(pgsize.width, pgsize.height,BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics = img.createGraphics();

                //clear the drawing area
                graphics.setPaint(Color.white);
                graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));

                //render
                slide.get(i).draw(graphics);
                FileOutputStream out = new FileOutputStream(dir+"/"+i+"."+outputextention);
                javax.imageio.ImageIO.write(img, outputextention, out);
                ppt.write(out);
                com.itextpdf.text.Image image =com.itextpdf.text.Image.getInstance(dir+"/"+i+"."+outputextention);
                doc.setPageSize(new com.itextpdf.text.Rectangle(image.getScaledWidth(), image.getScaledHeight()));
                doc.newPage();
                image.setAbsolutePosition(0, 0);
                doc.add(image);

                System.out.println("Image successfully created");
                out.close();
            }
            doc.close();
            for (int i = 0; i < slide.size(); i++) {
                StaticVariables.addToZipFile(dir+"/"+i+"."+outputextention,zos);
            }
            zos.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
