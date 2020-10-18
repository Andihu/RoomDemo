package com.hujian.roomdemo;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Word.class},version = 2,exportSchema = false)
public abstract class WordDataBase extends RoomDatabase {

    private static WordDataBase INSTANCE = null;

    static synchronized WordDataBase getDatabase(Context context){
        if (INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context,WordDataBase.class,"word_database")
                    .fallbackToDestructiveMigration() //破坏式的迁移 ，清空原数据构建新的数据库结构
                    .addMigrations(MIGRATION_1_2)
                    .build();
        }
        return INSTANCE;
    }
    public abstract WordDao getWordDao();
    //添加字段
    static final Migration MIGRATION_1_2 =new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE word ADD COLUMN 'add' TEXT");
        }
    };
    //删除字段
    static final Migration MIGRATION_2_3 =new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            //创建新表
            database.execSQL("create table word_temp( id Integer primary Key NOT NUll , english_word Text,chinese_Meaning text)");
            //导入数据
            database.execSQL("insert into word_temp( id , english_word ,chinese_Meaning) select id,english_word,chinese_Meaning ");
            //删除原表
            database.execSQL("drop table word");
            //更名
            database.execSQL("alter Table word_temp RENAME to word");
        }
    };


}
