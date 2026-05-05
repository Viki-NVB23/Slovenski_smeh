package si.uni_lj.fe.tnuv.slovenskismeh.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import si.uni_lj.fe.tnuv.slovenskismeh.Video;

public class YouTubeUtil {

    /**
     * Extracts YouTube video ID from various YouTube URL formats
     */
    public static String extractVideoId(String url) {
        if (url == null || url.isEmpty()) {
            return null;
        }

        String videoId = null;

        // Pattern for youtube.com/watch?v=xxxxx
        Pattern pattern1 = Pattern.compile("(?<=watch\\?v=)[^&\n?#]+");
        Matcher matcher1 = pattern1.matcher(url);
        if (matcher1.find()) {
            videoId = matcher1.group();
        }

        // Pattern for youtu.be/xxxxx
        if (videoId == null) {
            Pattern pattern2 = Pattern.compile("(?:youtube\\.com\\/watch\\?v=|youtu\\.be\\/)([^&\\n?#]+)");
            Matcher matcher2 = pattern2.matcher(url);
            if (matcher2.find()) {
                videoId = matcher2.group(1);
            }
        }

        // Pattern for youtube.com/embed/xxxxx
        if (videoId == null) {
            Pattern pattern3 = Pattern.compile("(?:youtube\\.com\\/embed\\/)([^&\\n?#]+)");
            Matcher matcher3 = pattern3.matcher(url);
            if (matcher3.find()) {
                videoId = matcher3.group(1);
            }
        }

        return videoId;
    }

    /**
     * Generates a proper YouTube URL from a video ID
     */
    public static String generateYouTubeUrl(String videoId) {
        if (videoId == null || videoId.isEmpty()) {
            return null;
        }
        return "https://www.youtube.com/watch?v=" + videoId;
    }

    /**
     * Converts a list of YouTube URLs to Video objects
     */
    public static List<Video> parseYouTubeLinks(List<String> youtubeLinks) {
        List<Video> videos = new ArrayList<>();

        if (youtubeLinks == null || youtubeLinks.isEmpty()) {
            return videos;
        }

        for (int i = 0; i < youtubeLinks.size(); i++) {
            String link = youtubeLinks.get(i);
            if (link != null && !link.trim().isEmpty()) {
                String videoId = extractVideoId(link);
                if (videoId != null) {
                    String title = "Video " + (i + 1);
                    Video video = new Video(title, link, videoId);
                    videos.add(video);
                }
            }
        }

        return videos;
    }

    /**
     * Gets thumbnail URL for a video ID
     */
    public static String getThumbnailUrl(String videoId) {
        if (videoId == null || videoId.isEmpty()) {
            return null;
        }
        return "https://img.youtube.com/vi/" + videoId + "/maxresdefault.jpg";
    }
}


