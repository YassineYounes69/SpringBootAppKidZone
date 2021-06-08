package com.example.kidszonea4arctic3.repositories;

import com.example.kidszonea4arctic3.models.Picture;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface IPictureRepository {
    void save(InputStream inputStream, File file) throws IOException;


}
