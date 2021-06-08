package com.example.kidszonea4arctic3.repositories;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import javax.inject.Named;
import java.io.*;

@Named
public class PictureRepository implements IPictureRepository {

    @Override
    public void save(InputStream inputStream, File file) throws IOException {
        // we are preparing an output stream so that we can write data to the specified file.
        OutputStream output = new FileOutputStream(file);

        // copy the input stream to the output location.
        IOUtils.copy(inputStream, output);
    }
}
