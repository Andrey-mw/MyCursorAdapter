package com.example.android.mycursoradapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Android on 06.11.2015.
 */
public class MyCursorAdapter extends CursorAdapter {
    public MyCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.single_row, parent, false);
        TextView txtName = (TextView) view.findViewById(R.id.txtName);
        TextView txtDexcription = (TextView) view.findViewById(R.id.txtDescription);
        view.setTag(new ViewHolder(txtName, txtDexcription));
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();

        TextView txtName = holder.getTxtName();
        TextView txtDescription = holder.getTxtDescription();

        txtName.setText(cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.NAME_COLUMN)));
        txtDescription.setText(cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.NUMBER_COLUMN)));

    }

    private static class ViewHolder {
        public final TextView txtName;
        public final TextView txtDescription;

        public TextView getTxtName() {
            return txtName;
        }

        public TextView getTxtDescription() {
            return txtDescription;
        }

        private ViewHolder(TextView txtName, TextView txtDescription) {
            this.txtName = txtName;
            this.txtDescription = txtDescription;
        }
    }
}
