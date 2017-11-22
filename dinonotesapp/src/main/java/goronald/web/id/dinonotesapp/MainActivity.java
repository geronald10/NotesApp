package goronald.web.id.dinonotesapp;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import goronald.web.id.dinonotesapp.adapter.DinoNotesAdapter;
import goronald.web.id.dinonotesapp.db.DatabaseContract;

import static goronald.web.id.dinonotesapp.db.DatabaseContract.CONTENT_URI;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor>,
        AdapterView.OnItemClickListener {

    private DinoNotesAdapter dinoNotesAdapter;
    private ListView lvNotes;

    private final int LOADER_NOTES_ID = 110;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Dino Notes");

        lvNotes = findViewById(R.id.lv_notes);
        dinoNotesAdapter = new DinoNotesAdapter(this, null, true);
        lvNotes.setAdapter(dinoNotesAdapter);
        lvNotes.setOnItemClickListener(this);

        getSupportLoaderManager().initLoader(LOADER_NOTES_ID, null, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().restartLoader(LOADER_NOTES_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        dinoNotesAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        dinoNotesAdapter.swapCursor(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getSupportLoaderManager().destroyLoader(LOADER_NOTES_ID);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            Intent intent = new Intent(MainActivity.this, FormActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Cursor cursor = (Cursor) dinoNotesAdapter.getItem(i);

        int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns._ID));
        Intent intent = new Intent(MainActivity.this, FormActivity.class);
        intent.setData(Uri.parse(CONTENT_URI + "/" + id));
        startActivity(intent);
    }
}
