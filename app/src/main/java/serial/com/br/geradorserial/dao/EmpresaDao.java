package serial.com.br.geradorserial.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import serial.com.br.geradorserial.models.Empresa;

@Dao
public interface EmpresaDao {

    @Insert
    void inserir(Empresa empresa);

    @Update
    void alterar(Empresa empresa);

    @Delete
    void deletar(Empresa empresa);

    @Query("SELECT * FROM empresa")
    List<Empresa> buscaEmpresas();
}
