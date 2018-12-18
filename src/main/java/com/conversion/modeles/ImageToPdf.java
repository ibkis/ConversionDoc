package com.conversion.modeles;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ImageToPdf extends Converter {
   private  String input;
    public ImageToPdf(InputStream inStream, OutputStream outStream, boolean showMessages, boolean closeStreamsWhenComplete) {
        super(inStream, outStream, showMessages, closeStreamsWhenComplete);
    }

    public ImageToPdf(String inStream, OutputStream outStream){
        super(null,outStream,true,true);
        input=inStream;
    }
    @Override
    public void convert() throws Exception {
        Image image=Image.getInstance(input );

        Rectangle A4 = PageSize.A4;

        float scalePortrait = Math.min(A4.getWidth() / image.getWidth(),
                A4.getHeight() / image.getHeight());

        float scaleLandscape = Math.min(A4.getHeight() / image.getWidth(),
                A4.getWidth() / image.getHeight());
        boolean isLandscape = scaleLandscape > scalePortrait;

        float w;
        float h;
        if (isLandscape) {
            A4 = A4.rotate();
            w = image.getWidth() * scaleLandscape;
            h = image.getHeight() * scaleLandscape;
        } else {
            w = image.getWidth() * scalePortrait;
            h = image.getHeight() * scalePortrait;
        }

        Document document = new Document(A4, 10, 10, 10, 10);

        try {
            PdfWriter.getInstance(document, outStream);
        } catch (DocumentException e) {
            throw new IOException(e);
        }
        document.open();
        try {
            image.scaleAbsolute(w, h);
            float posH = (A4.getHeight() - h) / 2;
            float posW = (A4.getWidth() - w) / 2;

            image.setAbsolutePosition(posW, posH);
            image.setBorder(Image.NO_BORDER);
            image.setBorderWidth(0);

            try {
                document.newPage();
                document.add(image);
            } catch (DocumentException de) {
                throw new IOException(de);
            }
        } finally {
            document.close();
        }
        finished();
    }
}
