package com.BerryAcid.notas.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.BerryAcid.notas.db.entity.NotaEntity;

import java.util.List;

@Dao
public interface NotaDao {
    @Insert
    void insert(NotaEntity nota);

    @Update
    void update(NotaEntity nota);

    @Query("DELETE FROM notas")
    void deleteAll();

    @Query("DELETE FROM notas WHERE id = :idNota")
    void deleteById(int idNota);

    @Query("SELECT * FROM notas order by titulo ASC")
    LiveData<List<NotaEntity>> getAll();

    @Query("SELECT * FROM notas WHERE favorita LIKE 1")
    LiveData<List<NotaEntity>> getAllFavoritas();

}
