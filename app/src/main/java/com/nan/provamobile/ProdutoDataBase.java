package com.nan.provamobile;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Produto.class}, version = 1)
public abstract class ProdutoDataBase extends RoomDatabase {

    private static ProdutoDataBase instance;

    //Metodo abstrado implementado pela ROOM para acessar as operações do DAO
    public abstract ProdutoDao produtoDao();

    //Retornar instância do BD
    public static synchronized ProdutoDataBase getInstance(Context context)
    {
        if(instance == null){
            //Então, cria a instância no banco
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ProdutoDataBase.class,"produto-database").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return instance;
    }
}