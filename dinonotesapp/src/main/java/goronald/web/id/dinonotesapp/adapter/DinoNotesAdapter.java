package goronald.web.id.dinonotesapp.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import goronald.web.id.dinonotesapp.R;

import static goronald.web.id.dinonotesapp.db.DatabaseContract.NoteColumns.DATE;
import static goronald.web.id.dinonotesapp.db.DatabaseContract.NoteColumns.DESCRIPTION;
import static goronald.web.id.dinonotesapp.db.DatabaseContract.NoteColumns.TITLE;
import static goronald.web.id.dinonotesapp.db.DatabaseContract.getColumnString;

public class DinoNotesAdapter extends CursorAdapter {

    public DinoNotesAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dino_note, viewGroup, false);
        return view;
    }

    @Override
    public Cursor getCursor() {
        return super.getCursor();
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        if (cursor != null) {
            TextView tvTitle = view.findViewById(R.id.tv_item_title);
            TextView tvDate = view.findViewById(R.id.tv_item_date);
            TextView tvDescription = view.findViewById(R.id.tv_item_description);

            tvTitle.setText(getColumnString(cursor, TITLE));
            tvDescription.setText(getColumnString(cursor, DESCRIPTION));
            tvDate.setText(getColumnString(cursor, DATE));
        }
    }
}
