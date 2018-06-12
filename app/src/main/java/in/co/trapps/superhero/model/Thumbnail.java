package in.co.trapps.superhero.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Akash Patra
 */
public class Thumbnail {

    @SerializedName("path")
    private String path;
    @SerializedName("extension")
    private String extension;

    public Thumbnail() {
    }

    public Thumbnail(String path, String extension) {
        this.extension = extension;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

}
