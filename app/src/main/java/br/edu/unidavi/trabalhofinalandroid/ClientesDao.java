package br.edu.unidavi.trabalhofinalandroid;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ClientesDao {

    @Query("SELECT * FROM cliente")
    List<Cliente> fetchClientes();

    @Query("SELECT * FROM cliente WHERE id=:id")
    Cliente findById(int id);

    @Insert
    void insert(Cliente cliente);

    @Update
    void update(Cliente cliente);

    @Delete
    void delete(Cliente cliente);

}
