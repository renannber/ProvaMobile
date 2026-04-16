package com.nan.provamobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {

    //Declaração dos campos de entrada de dados
    private EditText editTextName, editTextCode, editTextAmount, editTextprice;

    //Objeto para interagir com o banco de dados(DAO)
    private ProdutoDao produtoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Iniciar os campos de entrada de dados do Layout
        editTextName = findViewById(R.id.editTextName);
        editTextCode = findViewById(R.id.editTextCode);
        editTextprice = findViewById(R.id.editTextPrice);
        editTextAmount = findViewById(R.id.editTextAmount);

        //Iniciar botões do Layout
        Button buttonSave = findViewById(R.id.buttonSave);
        Button buttonReport = findViewById(R.id.buttonReport);

        //Configuração do banco de dados ROOM
        ProdutoDataBase db = Room.databaseBuilder(getApplicationContext(),
                ProdutoDataBase.class, "produto-database").allowMainThreadQueries().build();
        produtoDao = db.produtoDao();  //Obter a instância da DAO

        //Configuração do Botão Salvar
        buttonSave.setOnClickListener(v->{
            //Confirmar click
            Log.d("MainActivity", "Botão Cadastrar produto clicado");

            //Obter valores digitados
            String name = editTextName.getText().toString();
            String codigo = editTextCode.getText().toString();
            String priceText = editTextprice.getText().toString();
            String amountText = editTextAmount.getText().toString();

            //Verificar se os valores estão sendo capturados corretamente
            Log.d("MainActivity","Nome: " +name+ ", Codigo: " +codigo+", Preço: " +priceText+", Quantidade: "+amountText);

            //Verificar se campos obrigatórios foram preenchidos
            if(!name.isEmpty() && !codigo.isEmpty() && !priceText.isEmpty() &&!amountText.isEmpty()){
                //convertendo texto para double
                double price = Double.parseDouble(priceText);
                //convertendo texto para int
                int amount = Integer.parseInt(amountText);

                //Cria um novo objeto e insere no banco
                Produto produto = new Produto(name,codigo,price,amount);
                produtoDao.Insert(produto);


                // Confirma a inserção
                Log.d("MainActivity", "Produto inserido no banco de dados.");

                // Exibe uma mensagem confirmando o cadastro
                Toast.makeText(MainActivity.this, "Produto cadastrado!", Toast.LENGTH_SHORT).show();
            } else {
                //Mostrar erro se os campos estiverem vazio
                Log.d("MainActivity", "Erro: Campos obrigatórios vazios!");
                // Exibe uma mensagem de erro se os campos obrigatórios não forem preenchidos
                Toast.makeText(MainActivity.this, "Preencha os campos obrigatórios!", Toast.LENGTH_SHORT).show();
            }
        });
        // Configurar o botão de relatório para abrir a tela de relatório
        buttonReport.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ReportActivity.class))
        );

    }
}

