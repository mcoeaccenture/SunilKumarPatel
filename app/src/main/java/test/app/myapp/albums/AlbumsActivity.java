package test.app.myapp.albums;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import test.app.myapp.R;
import test.app.myapp.data.Album;
import test.app.myapp.data.NetResponse;

public class AlbumsActivity extends AppCompatActivity {
    //private static String TAG = AlbumsActivity.class.getSimpleName();

    //UI members
    private RecyclerView recyclerView;

    private ProgressBar progressBar;
    private TextView emptyView;
    private Button getAlbums;


    //Data members
    private AlbumsViewModel albumsViewModel;

    private boolean dataRequested;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            dataRequested = savedInstanceState.getBoolean("REQUEST");
        }
        setContentView(R.layout.activity_albums);

        recyclerView = findViewById(R.id.albumsList);
        Toolbar toolbar = findViewById(R.id.toolbar);
        progressBar = findViewById(R.id.progressbar);
        emptyView = findViewById(R.id.emptyView);
        getAlbums = findViewById(R.id.get_album);

        setSupportActionBar(toolbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);


        //Data member initialise
        albumsViewModel = ViewModelProviders.of(this).get(AlbumsViewModel.class);


        //subscribe to data on click.
        getAlbums.setOnClickListener(v -> {
            dataRequested = true;
            getAlbumData();

        });
        //Check if subscribed to data.
        if (dataRequested) {
            getAlbumData();
        }
    }


    private void getAlbumData() {
        albumsViewModel.getAlbumList().observe(this, albumData -> {
            if (albumData != null)
                setAlbumUI(albumData);
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("REQUEST", dataRequested);
    }


    private void setAlbumUI(NetResponse<List<Album>> albumData) {
        switch (albumData.getStatus()) {
            case PROGRESS:
                progressBar.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                emptyView.setVisibility(View.GONE);
                getAlbums.setEnabled(false);
                break;

            case ERROR:
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                emptyView.setVisibility(View.VISIBLE);
                // emptyView.setText(albumData.message);
                getAlbums.setEnabled(true);
                break;

            case SUCCESS:
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                emptyView.setVisibility(View.GONE);
                getAlbums.setEnabled(true);

                //Update the recycler view.
                Collections.sort(albumData.getData());
                AlbumListAdapter albumListAdapter = new AlbumListAdapter(this, albumData.getData());
                recyclerView.setAdapter(albumListAdapter);
                break;
            default:
        }
    }

}
