package br.edu.unidavi.trabalhofinalandroid;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "cliente")
public class Cliente {

    @PrimaryKey(autoGenerate = true)
    private final Integer id;

    private final String nome;

    @Ignore
    public Cliente(String nome){
        this.id = null;
        this.nome = nome;
    }

    public Cliente(Integer id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
