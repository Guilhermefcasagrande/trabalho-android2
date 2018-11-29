package br.edu.unidavi.trabalhofinalandroid;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = Cliente.class, version = 1)
public abstract class ClientesStore extends RoomDatabase{

    public abstract ClientesDao getClientesDao();
    private static ClientesStore instance = null;

    public static ClientesStore getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, ClientesStore.class, "clientes.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
