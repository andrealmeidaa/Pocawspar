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
    private static final String ENDPOINT="https://rxb4icaagdmxpg5bo53ukx3kuq0gzwyd.lambda-url.us-east-1.on.aws/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnBuscar=findViewById(R.id.btnAPI);
        TextView txtResponse=findViewById(R.id.txtResponse);
        EditText txtNome=findViewById(R.id.txtNome);
        EditText txtSobrenome=findViewById(R.id.txtSobrenome);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit  retrofit = new Retrofit.Builder().baseUrl(ENDPOINT)
                        .addConverterFactory(GsonConverterFactory.create()).build();
                IAndroidSample servico=retrofit.create(IAndroidSample.class);
                Person person=new Person(txtNome.getText().toString(),txtSobrenome.getText().toString());
                Call<ResponseClass> execution=servico.getGreetings(person);
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
            }
        });

    }
}