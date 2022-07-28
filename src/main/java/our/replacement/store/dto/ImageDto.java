package our.replacement.store.dto;

public class ImageDto {

    private Long imageId;

    private String url;

    public ImageDto() {
    }

    public ImageDto(Long imageId, String url) {
        this.imageId = imageId;
        this.url = url;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
