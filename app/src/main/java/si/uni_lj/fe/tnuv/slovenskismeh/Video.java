package si.uni_lj.fe.tnuv.slovenskismeh;

public class Video {
    private String title;
    private String youtubeUrl;
    private String thumbnailUrl;
    private String videoId;

    public Video() {}

    public Video(String title, String youtubeUrl, String videoId) {
        this.title = title;
        this.youtubeUrl = youtubeUrl;
        this.videoId = videoId;
        this.thumbnailUrl = generateThumbnailUrl(videoId);
    }

    private String generateThumbnailUrl(String videoId) {
        if (videoId != null && !videoId.isEmpty()) {
            return "https://img.youtube.com/vi/" + videoId + "/maxresdefault.jpg";
        }
        return "";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
        this.thumbnailUrl = generateThumbnailUrl(videoId);
    }
}

