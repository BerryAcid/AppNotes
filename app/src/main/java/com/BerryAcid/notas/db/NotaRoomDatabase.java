package com.BerryAcid.notas.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.BerryAcid.notas.db.dao.NotaDao;
import com.BerryAcid.notas.db.entity.NotaEntity;

@Database(entities = {NotaEntity.class}, version = 1)
public abstract class NotaRoomDatabase extends RoomDatabase {
    public abstract NotaDao notaDao();
    private  static volatile  NotaRoomDatabase INSTANCE;

    public static NotaRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (NotaRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    NotaRoomDatabase.class, "notas_database")
                    .build();
                }
            }
        }
        return INSTANCE;
    }
}
