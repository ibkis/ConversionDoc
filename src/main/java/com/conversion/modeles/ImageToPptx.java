package com.conversion.modeles;

import org.apache.poi.util.IOUtils;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import java.io.*;

public class ImageToPptx extends Converter {
    public ImageToPptx(InputStream inStream, OutputStream outStream, boolean showMessages, boolean closeStreamsWhenComplete) {
        super(inStream, outStream, showMessages, closeStreamsWhenComplete);
    }

    @Override
    public void convert() throws Exception {
        //creating a presentation
        XMLSlideShow ppt = new XMLSlideShow();
        //creating a slide in it
        XSLFSlide slide = ppt.createSlide();
        //reading an imageconverting it into a byte array
        byte[] picture = IOUtils.toByteArray(inStream);
        //adding the image to the presentation
        int idx = ppt.addPicture(picture, XSLFPictureData.PICTURE_TYPE_PNG);
        //creating a slide with given picture on it
        slide.createPicture(idx);
        //creating a file objectsaving the changes to a file
        ppt.write(outStream);
        finished();
    }
}
