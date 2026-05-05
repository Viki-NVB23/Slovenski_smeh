package si.uni_lj.fe.tnuv.slovenskismeh;

public class Event {
    private String title;
    private String date;
    private String time;
    private String venue;
    private String address;
    private String category;
    private String description;
    private String doorsOpen;
    private String imageUrl;

    public Event() {}

    public Event(String title, String date, String time, String venue, String address,
                 String category, String description, String doorsOpen, String imageUrl) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.address = address;
        this.category = category;
        this.description = description;
        this.doorsOpen = doorsOpen;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDoorsOpen() {
        return doorsOpen;
    }

    public void setDoorsOpen(String doorsOpen) {
        this.doorsOpen = doorsOpen;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

