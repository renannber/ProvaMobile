package com.nan.provamobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import java.util.List;

public class ReportActivity extends AppCompatActivity {

    // Campo de texto onde os dados do banco serao exibidos
    private TextView textViewReport;

    // Guardamos o dao e a lista para usar nos métodos de editar/excluir
    private UserDao userDao;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Define o layout XML dessa tela de relatorio
        setContentView(R.layout.activity_report);

        // Mapeamento do textView do XML para o Java
        textViewReport = findViewById(R.id.textViewReport);

        // Encontra o botao e define o clique para voltar
        Button btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(v -> voltarParaCadastro());

        // Encontra os botoes de editar e excluir
        Button btnEditar  = findViewById(R.id.btnEditar);
        Button btnExcluir = findViewById(R.id.btnExcluir);
        btnEditar.setOnClickListener(v -> editarUsuario());
        btnExcluir.setOnClickListener(v -> excluirUsuarios());

        /*
        Conexao com o banco de dados
        1 - Cria uma instancia do banco "user-database"
        2 - .allowMainThreadQueries(): libera operacoes em threads da UI
        */
        UserDataBase db = Room.databaseBuilder(
                getApplicationContext(),
                UserDataBase.class,
                "user-database"
        ).allowMainThreadQueries().build();

        // Obtem o DAO que contem as queries SQL
        userDao = db.userDao();

        // Recupera todos os usuarios e exibe na tela
        userList = userDao.getAllUsers();
        exibirRelatorio();
    }

    // Monta e exibe o relatorio na TextView
    //SE PRECISAR MOSTRAR MAIS DO QUE 2 DADOS NA TELA INSERIR AQUI
    private void exibirRelatorio() {
        userList = userDao.getAllUsers();
        StringBuilder report = new StringBuilder();
        for (User user : userList) {
            report.append("Nome: ").append(user.getName())
                    .append("\n")
                    .append("CPF: ").append(user.getCpf())
                    .append("\n\n");
        }
        textViewReport.setText(report.toString());
    }

    // Metodo do botao Editar — abre a tela de cadastro para editar
    public void editarUsuario() {
        if (userList.isEmpty()) {
            Toast.makeText(this, "Nenhum usuario para editar", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(ReportActivity.this, MainActivity.class);
        intent.putExtra("modo", "editar");
        startActivity(intent);
    }

    // Metodo do botao Excluir — remove todos os usuarios do banco
    public void excluirUsuarios() {
        if (userList.isEmpty()) {
            Toast.makeText(this, "Nenhum usuario para excluir", Toast.LENGTH_SHORT).show();
            return;
        }
        for (User user : userList) {
            userDao.delete(user);
        }
        exibirRelatorio(); // atualiza a tela depois de excluir
        Toast.makeText(this, "Usuarios excluidos!", Toast.LENGTH_SHORT).show();
    }

    // Metodo responsavel pela navegacao entre as telas do app
    public void voltarParaCadastro() {
        Intent intent = new Intent(ReportActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // fecha a tela para nao acumular na pilha
    }
}