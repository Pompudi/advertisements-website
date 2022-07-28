package our.replacement.store.util;

import our.replacement.store.dto.ImageDto;
import our.replacement.store.model.Image;

public class ImageMapper {

    public static ImageDto convertToDto(Image image) {
        ImageDto imageDto = new ImageDto();

        imageDto.setImageId(image.getImageId());
        imageDto.setUrl(image.getImageUrl());

        return imageDto;
    }
}
