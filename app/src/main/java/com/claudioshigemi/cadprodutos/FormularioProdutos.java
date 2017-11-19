package com.claudioshigemi.cadprodutos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.claudioshigemi.cadprodutos.BDHelper.ProdutosBd;
import com.claudioshigemi.cadprodutos.model.Produtos;

public class FormularioProdutos extends AppCompatActivity {

    EditText editText_NomeProduto, editText_Descricao, editText_Quantidade;
    Button btn_polimorfismo;
    Produtos editarProduto , produto;
    ProdutosBd bdHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_produtos);

        bdHelper = new ProdutosBd(FormularioProdutos.this);

        Intent intent = getIntent();
        editarProduto = (Produtos) intent.getSerializableExtra("produto-escolhido");

        editText_NomeProduto = (EditText) findViewById(R.id.editText_NomeProduto);
        editText_Descricao = (EditText) findViewById(R.id.editText_Descricao);
        editText_Quantidade = (EditText) findViewById(R.id.editText_Quantidade);

        btn_polimorfismo = (Button) findViewById(R.id.btn_polimorfismo);

        if (editarProduto != null){
            btn_polimorfismo.setText("Modificar");
        }else{
            btn_polimorfismo.setText("Cadastrar");
        }

        btn_polimorfismo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                produto.setNomeProduto(editText_NomeProduto.getText().toString() );
                produto.setDescricao(editText_Descricao.getText().toString() );
                produto.setQuantidade(Integer.parseInt(editText_Quantidade.getText().toString() ));

                if(btn_polimorfismo.getText().toString().equals("Cadastrar")){
                    bdHelper.salvarProduto(produto);
                    bdHelper.close();
                }
            }
        });
    }
}












