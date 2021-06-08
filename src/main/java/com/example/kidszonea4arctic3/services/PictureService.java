package com.example.kidszonea4arctic3.services;

import com.example.kidszonea4arctic3.models.Picture;
import com.example.kidszonea4arctic3.repositories.PictureRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Named
public class PictureService {

    @Inject
    PictureRepository pictureRepository;

    public void savePhoto(Picture picture, InputStream inputStream) throws IOException {
        System.out.println("savePhoto method executed");
        File directory = new File("/images");
        //String uniqueImageName = getUniqueImageName();
        File file = new File(directory, "image.jpg");
        pictureRepository.save(inputStream, file);


        //jmsBean.submit(file.toString());

        //File thumbnailDirectory = new File("/git/PlantPlaces/WebContent/resources/thumbnails");
        //File thumbnail = new File(thumbnailDirectory, uniqueImageName);

        //Thumbnails.of(file).size(100, 100).toFile(thumbnail);

        //photo.setUri(uniqueImageName);
        // eventually, save the photo to the database.

    }
}
