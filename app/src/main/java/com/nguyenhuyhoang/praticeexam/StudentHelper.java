package com.nguyenhuyhoang.praticeexam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class StudentHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "Student.db";

    public StudentHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists Students (id integer primary key autoincrement, name, region, birth)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists Students");
    }

    public Boolean insertData(String name, String region, String birth)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("region", region);
        contentValues.put("birth", birth);

        long result = MyDB.insert("Students", null, contentValues);
        if(result == 1) return false;
        else return true;
    }

    public Student getStudentFromDB(String id)
    {
        Student student = new Student(id, "","", "");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Students where id = ?",new String[]{id});
        if(cursor.moveToFirst())
        {
            student.setName(cursor.getString(1));
            student.setRegion(cursor.getString(2));
            student.setBirth(cursor.getString(3));
        }
        return student;
    }

    public Boolean deleteStudent(String id)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        int result = MyDB.delete("Students", " id = ?", new String[]{id});
        return result == 1;
    }

    public List<Student> getAllStudentFromDB()
    {
        List<Student> studentList = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Students", null);
        while (cursor.moveToNext()) {
            String studentId = cursor.getString(0);
            String studentName = cursor.getString(1);
            String studentRegion = cursor.getString(2);
            String studentBirth = cursor.getString(3);
            Student student = new Student(studentId, studentName, studentRegion, studentBirth);
            studentList.add(student);
        }
        return studentList;
    }

    public void update(String id,String name, String region, String birth)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("region", region);
        values.put("birth", birth);
        MyDB.update("Students", values, "id = ?", new String[]{id});
    }
}
