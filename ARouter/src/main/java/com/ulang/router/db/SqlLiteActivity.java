package com.ulang.router.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ulang.router.R;

import java.util.Random;

public class SqlLiteActivity extends AppCompatActivity {

    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_lite);

        SQLiteDbHelper helper = new SQLiteDbHelper(this);
        database = helper.getWritableDatabase();

    }


    public void insertStudents() {
        for (int i = 0; i < 5; i++) {
            ContentValues values = studentToContentValues(mockStudent(i));
            database.insert(SQLiteDbHelper.TABLE_STUDENT, null, values);
        }
    }

    // 构建 student 对象
    private Student mockStudent(int i) {
        Student student = new Student();
        student.id = i;
        student.name = "user-" + i;
        student.tel_no = String.valueOf(new Random().nextInt(200000));
        student.cls_id = new Random().nextInt(5);
        return student;
    }

    // 将 student 对象的值存储到 ContentValues 中
    private ContentValues studentToContentValues(Student student) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", student.id);
        contentValues.put("name", student.name);
        contentValues.put("tel_no", student.tel_no);
        contentValues.put("cls_id", student.cls_id);
        return contentValues;
    }


    private void queryStudents() {
database.beginTransaction();
        // 相当于 select * from students 语句
        Cursor cursor = database.query(SQLiteDbHelper.TABLE_STUDENT, null,
                "cls_id > ? and id >= 1", new String[]{"3"},
                null, null, null, null);

        // 不断移动光标获取值
        while (cursor.moveToNext()) {
            // 直接通过索引获取字段值
            int stuId = cursor.getInt(0);

            // 先获取 name 的索引值，然后再通过索引获取字段值
            String stuName = cursor.getString(cursor.getColumnIndex("name"));
            Log.e("", "id: " + stuId + " name: " + stuName);
        }
        // 关闭光标
        cursor.close();
    }

    private void deleteStudents() {
        database.delete(SQLiteDbHelper.TABLE_STUDENT,
                "cls = ?", new String[]{"3"});
    }

    private void updateStudents() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "Jerry");
        database.update(SQLiteDbHelper.TABLE_STUDENT,
                contentValues, "cls_id = ?", new String[]{"3"});
    }

}
