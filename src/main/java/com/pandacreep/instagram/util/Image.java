package com.pandacreep.instagram.util;

import lombok.Builder;
import lombok.Data;
import org.bson.types.Binary;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Data
@Builder
public class Image {
    private Binary image;

    public static Image getImage(MultipartFile file) {
        byte[] data = new byte[0];
        try {
            data = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (data.length == 0) {
            return null;
        }

        Binary bData = new Binary(data);
        Image image = Image.builder().image(bData).build();

        return image;
    }
}
