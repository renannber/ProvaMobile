package com.nan.provamobile;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProdutoDao {

    @Insert
    void Insert(Produto produto);

    //Metodo para buscar todos os produtos no BD
    @Query("SELECT * FROM produto")
    //Salvar o que foi selecionado em uma lista

    List<Produto> getAllProduto();
}
