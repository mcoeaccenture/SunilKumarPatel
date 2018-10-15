package test.app.myapp.albums;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import test.app.myapp.R;
import test.app.myapp.data.Album;

public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListAdapter.AlbumViewHolder> {
    private final List<Album> albumList;
    private final Context context;

    public AlbumListAdapter(Context context, List<Album> albumList) {
        this.context = context;
        this.albumList = albumList;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View albumItem = layoutInflater.inflate(R.layout.album_item, viewGroup, false);
        return new AlbumViewHolder(albumItem);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder albumViewHolder, int i) {
        Album album = albumList.get(i);
        albumViewHolder.title.setText(album.title);
        albumViewHolder.albumId.setText(String.valueOf(album.id));
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public static class AlbumViewHolder extends RecyclerView.ViewHolder {
        final TextView title;
        final TextView albumId;

        AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            albumId = itemView.findViewById(R.id.albumid);
        }
    }
}
