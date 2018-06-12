package in.co.trapps.superhero.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Akash Patra
 */
public class Urls {

    @SerializedName("type")
    private String type;
    @SerializedName("url")
    private String url;

    public Urls() {
    }

    public Urls(String type, String url) {
        this.type = type;
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
