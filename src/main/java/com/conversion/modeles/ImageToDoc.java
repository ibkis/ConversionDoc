package com.conversion.modeles;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ImageToDoc extends Converter {
   private  String input;
    public ImageToDoc(InputStream inStream, OutputStream outStream, boolean showMessages, boolean closeStreamsWhenComplete) {
        super(inStream, outStream, showMessages, closeStreamsWhenComplete);
    }

    public ImageToDoc(String inStream, OutputStream outStream){
        super(null,outStream,true,true);
        input=inStream;
    }
    @Override
    public void convert() throws Exception {

        finished();
    }
}
