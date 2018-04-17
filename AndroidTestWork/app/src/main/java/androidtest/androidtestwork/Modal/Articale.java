package androidtest.androidtestwork.Modal;

/**
 * Created by Aru on 02-04-2018.
 */

public class Articale {
    private String Author;
    private String Description;
    private String Title;
    private String urlToimage;
    private String PublishedAt;
    private int id;

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getUrlToimage() {
        return urlToimage;
    }

    public void setUrlToimage(String urlToimage) {
        this.urlToimage = urlToimage;
    }

    public String getPublishedAt() {
        return PublishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        PublishedAt = publishedAt;
    }

    public void setId(int id) {
        this.id = id;
    }
}
