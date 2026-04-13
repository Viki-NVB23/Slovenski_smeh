package si.uni_lj.fe.tnuv.slovenskismeh;

import java.util.List;

public class Comedian {
    private String name;
    private String surname;
    private String imageUrl;
    private String quote;
    private String biography;
    private String style;
    private List<String> youtubeLinks;

    public Comedian() {}

    public Comedian(String name, String surname, String imageUrl, String quote, String biography, String style, List<String> youtubeLinks) {
        this.name = name;
        this.surname = surname;
        this.imageUrl = imageUrl;
        this.quote = quote;
        this.biography = biography;
        this.style = style;
        this.youtubeLinks = youtubeLinks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public List<String> getYoutubeLinks() {
        return youtubeLinks;
    }

    public void setYoutubeLinks(List<String> youtubeLinks) {
        this.youtubeLinks = youtubeLinks;
    }

    public String getFullName() {
        return name + " " + surname;
    }
}
