package test.app.myapp.data;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Album implements Comparable<Album> {
    @SerializedName("id")
    public int id;

    @SuppressWarnings({"SpellCheckingInspection", "WeakerAccess"})
    @SerializedName("userid")
    public int userId;

    @SerializedName("title")
    public String title;

    @Override
    public int compareTo(@NonNull Album album) {
        return title.compareTo(album.title);
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                '}';
    }
}
