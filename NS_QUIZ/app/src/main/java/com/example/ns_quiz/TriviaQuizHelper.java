package com.example.ns_quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TriviaQuizHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DB_NAME = "TQuiz.db";

    private static final int DB_VERSION = 4;
    private static final String TABLE_NAME = "TQ";
    private static final String UID = "_UID";
    private static final String QUESTION = "QUESTION";
    private static final String OPTA = "OPTA";
    private static final String OPTB = "OPTB";
    private static final String OPTC = "OPTC";
    private static final String OPTD = "OPTD";
    private static final String ANSWER = "ANSWER";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + QUESTION + " VARCHAR(255), " + OPTA + " VARCHAR(255), " + OPTB + " VARCHAR(255), " + OPTC + " VARCHAR(255), " + OPTD + " VARCHAR(255), " + ANSWER + " VARCHAR(255));";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    TriviaQuizHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    void allQuestion() {
        ArrayList<TriviaQuestion> arrayList = new ArrayList<>();

        arrayList.add(new TriviaQuestion("Galileo was an Italian astronomer who developed?", "Telescope", "Aeroplane", "Electricity", "Train", "Telescope"));
        arrayList.add(new TriviaQuestion("Who is the father of Geometry ?", "Aristotle", "Euclid", "Pythagoras", "Kepler", "Euclid"));
        arrayList.add(new TriviaQuestion("Who was known as Iron man of India ?", "Bhagat Singh", "Jawaharlal Nehru", "Subhash Chandra Bose", "Sardar Patel", "Sardar Patel"));

        this.addAllQuestions(arrayList);
    }

    private void addAllQuestions(ArrayList<TriviaQuestion> allQuestions) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();

        try{
            ContentValues values = new ContentValues();
            for (TriviaQuestion question : allQuestions) {
                values.put(QUESTION, question.getQuestion());
                values.put(OPTA, question.getOpta());
                values.put(OPTB, question.getOptb());
                values.put(OPTC, question.getOptc());
                values.put(OPTD, question.getOptd());
                values.put(ANSWER, question.getAnswer());
                db.insert(TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    List<TriviaQuestion> getAllOfTheQuestions() {
        List<TriviaQuestion> questionList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        String[] column = {UID, QUESTION, OPTA, OPTB, OPTC, OPTD, ANSWER};
        Cursor cursor = db.query(TABLE_NAME, column, null,null,null, null, null, null);

        while (cursor.moveToNext()) {
            TriviaQuestion question = new TriviaQuestion();
            question.setId(cursor.getInt(0));
            question.setQuestion(cursor.getString(1));
            question.setOpta(cursor.getString(2));
            question.setOptb(cursor.getString(3));
            question.setOptc(cursor.getString(4));
            question.setOptd(cursor.getString(5));
            question.setAnswer(cursor.getString(6));
            questionList.add(question);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        return questionList;
    }
}
