package a2works.com.br.aprendendosqlite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import a2works.com.br.aprendendosqlite.model.Livro;
import a2works.com.br.aprendendosqlite.util.BancoController;
import a2works.com.br.aprendendosqlite.util.CriaBanco;

public class AlterarActivity extends AppCompatActivity {

    EditText editLivro;
    EditText autor;
    EditText editora;
    Button alterar;
    Button deletar;
    Cursor cursor;
    BancoController crud;
    String codigo;

    private Livro livro = new Livro();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        codigo = this.getIntent().getStringExtra("codigo");
        crud = new BancoController(getBaseContext());

        editLivro = (EditText)findViewById(R.id.editText4);
        autor = (EditText)findViewById(R.id.editText5);
        editora = (EditText)findViewById(R.id.editText6);

        alterar = (Button)findViewById(R.id.button2);
        cursor = crud.carregaDadoById(Integer.parseInt(codigo));

        Log.d("TESTE","Alterar "+cursor.getCount());

        Log.d("TESTE","Alterar cursor "+cursor.toString());

        editLivro.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.TITULO)));
        autor.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.AUTOR)));
        editora.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.EDITORA)));

        /*livro.setTitulo(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.TITULO)));
        livro.setAutor(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.AUTOR)));
        livro.setEditora(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.EDITORA)));*/

        Log.d("TESTE","Alterar livro edit"+editLivro.getText().toString() +".");



        //listar();

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                livro.setTitulo(editLivro.getText().toString());
                livro.setEditora(editora.getText().toString());
                livro.setAutor(autor.getText().toString());

                Log.d("TESTE","Alterar livro "+livro.toString());

                crud.alteraRegistro(Integer.parseInt(codigo), livro /*editLivro.getText().toString(),
                        autor.getText().toString(), editora.getText().toString()*/);

                //Log.d("TESTE", "No botão"+ livro.toString());
                Intent intent = new Intent(AlterarActivity.this,ConsultaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        deletar = (Button)findViewById(R.id.button3);
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.deletaRegistro(Integer.parseInt(codigo));
                Intent intent = new Intent(AlterarActivity.this,ConsultaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void listar(){

        editLivro.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.TITULO)));
        autor.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.AUTOR)));
        editora.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.EDITORA)));

        cursor = crud.carregaDadoById(Integer.parseInt(codigo));

        Log.d("TESTE","Alterar"+cursor.getCount());
    }

}
