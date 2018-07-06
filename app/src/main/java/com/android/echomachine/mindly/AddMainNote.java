package com.android.echomachine.mindly;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.echomachine.mindly.database.NoteDatabase;
import com.android.echomachine.mindly.modle.NoteModle;

import java.util.Calendar;

public class AddMainNote extends AppCompatActivity {

    private static final String KEY_TITLE = "title";
    private static final String KEY_TEXT = "text";


    private EditText mTitleText;
    private EditText mNoteText;
    private String mTitle, mText, mDate, mTime;
    private int mYear, mMonth, mHour, mMinute, mDay;
    private Calendar mCalendar;
    private View mView;
    private int i = 0;


    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, AddMainNote.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_main_note);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mView = findViewById(android.R.id.content);
        mTitleText = findViewById(R.id.title_edit_text);
        mNoteText = findViewById(R.id.text_edit_text);

        mCalendar = Calendar.getInstance();
        mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        mMinute = mCalendar.get(Calendar.MINUTE);

        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH) + 1;
        mDay = mCalendar.get(Calendar.DATE);

        mDate = mDay + "/" + mMonth + "/" + mYear;
        if(mMinute < 10 & mHour < 10) {
            mTime = "0" + mHour + ":0" + mMinute;
        } else {
            mTime = mHour + ":" + mMinute;
        }

        mTitleText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTitle = charSequence.toString().trim();
                mTitleText.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        if(savedInstanceState != null) {
            String titleString = savedInstanceState.getString(KEY_TITLE);
            mTitleText.setText(titleString);
            mTitle = titleString;

            String textString = savedInstanceState.getString(KEY_TEXT);
            mNoteText.setText(textString);
            mText = textString;
        }
    }

    public void saveNote() {
        NoteDatabase noteDatabase = new NoteDatabase(this);
        noteDatabase.addMainNote(new NoteModle(mTitle, mDate, mText, mTime));


//        mCalendar.set(Calendar.MONTH, --mMonth);
//        mCalendar.set(Calendar.YEAR, mYear);
//        mCalendar.set(Calendar.DAY_OF_MONTH, mDay);
//        mCalendar.set(Calendar.HOUR_OF_DAY, mHour);
//        mCalendar.set(Calendar.MINUTE, mMinute);
//        mCalendar.set(Calendar.SECOND, 0);

        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.save_note:
                mTitleText.setText(mTitle);
                if (mTitleText.getText().toString().length() == 0) {
                    mTitleText.setError("Note Title cannot be blank!");
                } else {
                    saveNote();
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putCharSequence(KEY_TITLE, mTitleText.getText());
        outState.putCharSequence(KEY_TEXT, mNoteText.getText());
    }
}
