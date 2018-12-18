package com.conversion.modeles.ppt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface ProcessorFactory {

	public ConversionProcessor prepare(File input, File output) throws IOException;
}
