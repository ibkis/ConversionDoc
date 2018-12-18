package com.conversion.modeles;

import org.apache.poi.hslf.model.Fill;
import org.apache.poi.hslf.model.Picture;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.SlideMaster;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class ImageToPpt extends Converter {
    public ImageToPpt(InputStream inStream, OutputStream outStream, boolean showMessages, boolean closeStreamsWhenComplete) {
        super(inStream, outStream, showMessages, closeStreamsWhenComplete);
    }

    @Override
    public void convert() throws Exception {
        SlideShow ppt = new SlideShow();
        //add first slide
        Slide s1 = ppt.createSlide();
        SlideMaster master = ppt.getSlidesMasters()[0];
        Fill fill = master.getBackground().getFill();
        byte[] picture = IOUtils.toByteArray(inStream);
        int idx = ppt.addPicture(picture, Picture.PNG);
        fill.setFillType(Fill.FILL_PICTURE);
        fill.setPictureData(idx);
        //save changes in a file
        ppt.write(outStream);
        finished();
    }
}
