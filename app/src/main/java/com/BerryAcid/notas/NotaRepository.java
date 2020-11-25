package com.BerryAcid.notas;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.BerryAcid.notas.db.NotaRoomDatabase;
import com.BerryAcid.notas.db.dao.NotaDao;
import com.BerryAcid.notas.db.entity.NotaEntity;

import java.util.List;

public class NotaRepository {
    private NotaDao notaDao;
    private LiveData<List<NotaEntity>> allNotas;
    private LiveData<List<NotaEntity>> allNotasFavoritas;

    public NotaRepository(Application application){
        NotaRoomDatabase db = NotaRoomDatabase.getDatabase(application);
        notaDao = db.notaDao();
        allNotas = notaDao.getAll();
        allNotasFavoritas = notaDao.getAllFavoritas();
    }

    public LiveData<List<NotaEntity>> getALL() { return allNotas; }

    public LiveData<List<NotaEntity>> getAllFavs() { return allNotasFavoritas; }

    public void insert (NotaEntity nota) {
        new insertAsyncTask(notaDao).execute(nota);
    }

    public static class insertAsyncTask extends AsyncTask<NotaEntity, Void, Void>{
        private NotaDao notaDaoAsynctask;

        insertAsyncTask(NotaDao dao){
            notaDaoAsynctask = dao;
        }

        @Override
        protected Void doInBackground(NotaEntity... notaEntities) {
            notaDaoAsynctask.insert(notaEntities[0]);
            return null;
        }
    }

    public void update (NotaEntity nota) {
        new updatetAsyncTask(notaDao).execute(nota);
    }

    public static class updatetAsyncTask extends AsyncTask<NotaEntity, Void, Void>{
        private NotaDao notaDaoAsynctask;

        updatetAsyncTask(NotaDao dao)
        { notaDaoAsynctask = dao;
        }

        @Override
        protected Void doInBackground(NotaEntity... notaEntities) {
            notaDaoAsynctask.update(notaEntities[0]);
            return null;
        }
    }

    public void deleteOne (NotaEntity idNota) {
        new deleteAsyncTask(notaDao).execute(idNota);
    }

    public static class deleteAsyncTask extends AsyncTask<NotaEntity, Void, Void>{
        private NotaDao notaDaoAsynctask;

        deleteAsyncTask(NotaDao dao)
        { notaDaoAsynctask = dao;
        }

        @Override
        protected Void doInBackground(NotaEntity... notaEntities) {
            notaDaoAsynctask.deleteById(notaEntities[0].getId());
            return null;
        }
    }
}
