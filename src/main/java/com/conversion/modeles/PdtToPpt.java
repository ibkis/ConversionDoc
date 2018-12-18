package com.conversion.modeles;

import com.conversion.modeles.ppt.PdfToPpt;
import com.conversion.modeles.ppt.ProcessorFactory;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public class PdtToPpt extends Converter  {
    File input,output;
    public PdtToPpt(InputStream inStream, OutputStream outStream, boolean showMessages, boolean closeStreamsWhenComplete) {
        super(inStream, outStream, showMessages, closeStreamsWhenComplete);
    }
    public PdtToPpt(File in,File out) {
        super(null, null, true, true);
        this.input=in;
        this.output=out;
    }

    @Override
    public void convert() throws Exception {
        ProcessorFactory factory = new PdfToPpt();
        factory.prepare(input,output).execute();

    }
}
