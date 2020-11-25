package com.BerryAcid.notas.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.BerryAcid.notas.NotaRepository;
import com.BerryAcid.notas.db.entity.NotaEntity;

import java.util.List;

public class NuevaNotaDialogViewModel extends AndroidViewModel {
    private LiveData<List<NotaEntity>> allNotas;
    private NotaRepository notaRepository;

    public NuevaNotaDialogViewModel(Application application){
        super(application);

        notaRepository = new NotaRepository(application);
        allNotas = notaRepository.getALL();
    }

    // El fragmento que necesita recibir la nueva lista de datos.
    public LiveData<List<NotaEntity>> getAllNotas() { return allNotas; }

    //El fragment que inserte una nueva nota, deberá comunicarlo a este ViewModel
    public void insertarNota(NotaEntity nuevaNotaEntity) {
        notaRepository.insert(nuevaNotaEntity);
    }

    public void updateNota(NotaEntity actualizaNotaEntity) {
        notaRepository.update(actualizaNotaEntity);
    }

    public void deleteNota(NotaEntity eliminaNotaEntity) {
        notaRepository.deleteOne(eliminaNotaEntity);
    }
}