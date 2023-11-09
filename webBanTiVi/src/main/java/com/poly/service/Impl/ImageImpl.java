package com.poly.service.Impl;

import com.poly.entity.Image;
import com.poly.repository.ImageRepo;
import com.poly.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageImpl implements ImageService {

    @Autowired
    private ImageRepo imageRepo;

    @Override
    public Image add(Image image) {
        return imageRepo.save(image);
    }
}
