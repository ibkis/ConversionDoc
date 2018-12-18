package com.conversion.modeles.ppt;

import java.io.File;

public interface ConversionProcessor {

    public void execute();

    public int getPageCount();

    public void setConversionCallback(ConversionCallback cb);

    public void setOutputFile(File file);
}
