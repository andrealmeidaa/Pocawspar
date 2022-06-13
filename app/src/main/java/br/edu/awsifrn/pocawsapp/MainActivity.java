package br.edu.awsifrn.pocawsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.edu.awsifrn.pojo.IAndroidSample;
import br.edu.awsifrn.pojo.Person;
import br.edu.awsifrn.pojo.ResponseClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private static final String ENDPOINT="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnBuscar=findViewById(R.id.btnAPI);
        TextView txtResponse=findViewById(R.id.txtResponse);
        EditText txtNome=findViewById(R.id.txtNome);
        EditText txtSobrenome=findViewById(R.id.txtSobrenome);
        btnBuscar.setOnClickListener(view -> {
            //Criar uma instânica do cilente do Retrofit(https://square.github.io/retrofit/)
            Retrofit  retrofit = new Retrofit.Builder().baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create()).build();//Usa o covnersar GSon para transformar objetos JSN em Java
            //Cria o o serviço que representa a interface da API
            IAndroidSample servico=retrofit.create(IAndroidSample.class);

            Person person=new Person(txtNome.getText().toString(),txtSobrenome.getText().toString());
            Call<ResponseClass> execution=servico.getGreetings(person);//Prepara chamada de um serviço específico
           //Coloca na fila de execução e trata a resposta baseada em eventos
            execution.enqueue(new Callback<ResponseClass>() {
               @Override
               public void onResponse(Call<ResponseClass> call, Response<ResponseClass> response) {
                   ResponseClass greetings=response.body();
                   txtResponse.setText(greetings.getGreetings());
               }

               @Override
               public void onFailure(Call<ResponseClass> call, Throwable t) {
                   Log.e("Lambda", "Erro ao realizar chamada:" + t.getMessage());
               }
           });
        });

    }
}