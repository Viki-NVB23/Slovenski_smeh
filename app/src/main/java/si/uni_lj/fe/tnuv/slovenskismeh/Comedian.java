package si.uni_lj.fe.tnuv.slovenskismeh;

public class Comedian {
    private String name;
    private String surname;
    private String imageUrl;
    private String quote;

    public Comedian() {}

    public Comedian(String name, String surname, String imageUrl, String quote) {
        this.name = name;
        this.surname = surname;
        this.imageUrl = imageUrl;
        this.quote = quote;
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

    public String getFullName() {
        return name + " " + surname;
    }
}
