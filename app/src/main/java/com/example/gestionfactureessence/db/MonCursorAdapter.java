package com.example.gestionfactureessence.db;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.gestionfactureessence.R;

public class MonCursorAdapter extends CursorAdapter {
    public MonCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_liste,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // va chercher le montant
        double montant = cursor.getDouble(cursor.getColumnIndex(DBFactureHelper.COL_MONTANT));
        //Afficher le resultat name
        TextView tv = view.findViewById(R.id.tv);
        tv.setText(String.valueOf(montant));
    }
}
