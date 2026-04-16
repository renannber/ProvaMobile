package com.nan.provamobile;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;

public class ReportActivity extends AppCompatActivity {
    //Campo de texto onde os dados do banco serão exibidos
    private TextView textViewReport;

    //Metodo create
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Define o layout XML da tela relatório
        setContentView(R.layout.activity_report);
        textViewReport = findViewById(R.id.textViewReport);

        //Encontrar o Botão de voltar
        Button btnVoltar = findViewById(R.id.btnVoltar);
        //Retorna usando LAMBDA
        btnVoltar.setOnClickListener(v -> voltarParaCadastro());

        //Conexão como Banco de dados
        ProdutoDataBase db = Room.databaseBuilder(getApplicationContext(), ProdutoDataBase.class, "produto-database")
                .allowMainThreadQueries()
                .build();

        ProdutoDao produtoDao = db.produtoDao();

        //Recuperar todos os produtos cadastrados no banco e salvar em uma lista

        List<Produto> produtoList = produtoDao.getAllProduto();
        //Construir uma String dentro do laço de repetição
        StringBuilder report = new StringBuilder();

        //Loop "for-each" para percorrer cada objeto User dentro da lista
        for (Produto product : produtoList) {
            report.append("Nome: ").append(product.getName()).append("\n").append("Codigo: ").append(product.getCode()).append("\n").append("Preço: ").append(product.getPrice());
        }
        //Exibe o relatório final montado na TextView da tela
        textViewReport.setText(report.toString());

    }

    private void voltarParaCadastro() {
        Intent intent = new Intent(ReportActivity.this, MainActivity.class);
        startActivity(intent);
        finish();}}