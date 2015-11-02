package com.anagrande.epilepsy;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;


public class AddSeizure extends AppCompatActivity {

    private SQLiteDatabase db;
    private SeizureDatabase mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_seizure);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        try {
            db = SQLiteDatabase.openDatabase("/data/data/com.anagrande.epilepsy/databases/seizure.db", null,
                    SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLiteException e) {
            mDbHelper = new SeizureDatabase(this);
            db = mDbHelper.getWritableDatabase();
        }

    }


    public void addSeizure() {
        LayoutInflater inflater = getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.seizure_data, null);

        Dialog builder = new Dialog(this);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE); //before

        builder.addContentView(dialoglayout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        Button dialogButton = (Button) builder.findViewById(R.id.bSave);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSeizureData(v);
            }
        });

        builder.show();




        /*ContentValues values = new ContentValues();
        values.put(SeizureDatabase.COLUMN_ID, 1 );
        values.put(SeizureDatabase.COLUMN_DURATION, 40);
        values.put(SeizureDatabase.COLUMN_DATE, "01/10/2004");

        db.insertWithOnConflict(SeizureDatabase.TABLE_SEIZURES, null, values, SQLiteDatabase.CONFLICT_REPLACE);

        show();*/

    }

    public void saveSeizureData(View v) {
        EditText etDuration = (EditText) ((View)v.getParent()).findViewById(R.id.etDuration);
        EditText etDescription = (EditText) ((View) v.getParent()).findViewById(R.id.etDescription);
        Spinner spType = (Spinner) ((View) v.getParent()).findViewById(R.id.spType);
        DatePicker date = (DatePicker) ((View) v.getParent()).findViewById(R.id.datePicker);

        Log.d("type", spType.getSelectedItem().toString());
        Log.d("date", String.valueOf(date.getYear()));


    }

    public void show() {
        SeizureDatabase mDbHelperR = new SeizureDatabase(this);
        SQLiteDatabase dbR = mDbHelperR.getReadableDatabase();

        String attack = "1";
        String[] arg = new String[] {attack};

        String[] projection = {
                SeizureDatabase.COLUMN_ID,
                SeizureDatabase.COLUMN_DURATION,
                SeizureDatabase.COLUMN_DATE
        };

        Cursor c = dbR.query(
                SeizureDatabase.TABLE_SEIZURES,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        c.moveToFirst();

        Log.d("firs", c.getString(2));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_seizure, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {

            case R.id.action_settings:
                return true;
            case R.id.action_seizure:
                addSeizure();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
