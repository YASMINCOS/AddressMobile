package com.yasmin.agendacep;

import static com.yasmin.agendacep.ConstantesActivies.CHAVE_ADDRESS;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yasmin.agendacep.api.API;
import com.yasmin.agendacep.database.AddressDatabase;
import com.yasmin.agendacep.database.dao.RoomAddressDAO;
import com.yasmin.agendacep.databinding.ActivityFormAddressBinding;
import com.yasmin.agendacep.model.Address;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FormAddress extends AppCompatActivity {

    ActivityFormAddressBinding binding;
    private static final String TITULO_APPBAR_NOVO_CEP = "Novo cep";
    private static final String TITULO_APPBAR_EDITA_CEP = "Edita cep";
    private EditText fieldCodeZip;
    private EditText fieldPublicPlace;
    private EditText fieldDistrict;
    private EditText fieldCity;
    private EditText fieldState;
    //ADD
    private RoomAddressDAO dao;
    private Address address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AddressDatabase database = AddressDatabase.getInstance(this);
        dao = database.getRoomAddressDao();
        startFields();
        loadCodeZip();

        //configurando retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //iniciando retrofit


        binding.btSeacrhCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cep = binding.editCodeZip.getText().toString();


                if (cep.isEmpty()) {
                    Toast.makeText(FormAddress.this, "Preencha o campo do cep", Toast.LENGTH_SHORT).show();

                } else {
                    API api = retrofit.create(API.class);
                    Call<Address> call = api.getAddress(cep);

                    call.enqueue(new Callback<Address>() {
                        @Override
                        public void onResponse(Call<Address> call, Response<Address> response) {

                            if (response.code() == 200) {

                                String publicPlace = response.body().getLogradouro().toString();
                                String district = response.body().getBairro().toString();
                                String city = response.body().getLocalidade().toString();
                                String uf = response.body().getUf().toString();
                                Form(publicPlace, district, city, uf);
                            } else {
                                Toast.makeText(FormAddress.this, "Cep Inválido!", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<Address> call, Throwable t) {

                            Toast.makeText(FormAddress.this, "Ocorreu um erro inesperado", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater()
                .inflate(R.menu.form_address_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_formulario_aluno_menu_salvar) {
            finishForm();
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadCodeZip() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_ADDRESS)) {
            setTitle(TITULO_APPBAR_EDITA_CEP);
            //address = (Address) dados.getSerializableExtra(CHAVE_ADDRESS);
            //fillField();
        } else {
            setTitle(TITULO_APPBAR_NOVO_CEP);
            address = new Address();
        }
    }

//    private void fillField() {
//        fieldCodeZip.setText(address.getCodeZip());
//        fieldPublicPlace.setText(address.getLogradouro());
//        fieldDistrict.setText(address.getBairro());
//        fieldCity.setText(address.getLocalidade());
//        fieldState.setText(address.getUf());
//
//    }

    private void finishForm() {
        fillCodeZip();
        if (address.temIdValido()) {
            dao.edit(address);
        } else {
            dao.save(address);
        }
        finish();
    }

    private void startFields() {
        fieldCodeZip = findViewById(R.id.editCodeZip);
        fieldPublicPlace = findViewById(R.id.editPublicSpace);
        fieldDistrict = findViewById(R.id.editDistrict);
        fieldCity = findViewById(R.id.editCity);
        fieldState = findViewById(R.id.editState);

    }

    private void fillCodeZip() {
        int codeZip = Integer.parseInt(fieldCodeZip.getText().toString());
        String publicPlace = fieldPublicPlace.getText().toString();
        String district = fieldDistrict.getText().toString();
        String city = fieldCity.getText().toString();
        String uf = fieldState.getText().toString();

        address.setCodeZip(codeZip);
        address.setLogradouro(publicPlace);
        address.setBairro(district);
        address.setLocalidade(city);
        address.setUf(uf);
    }

    public void Form(String publicPlace, String district, String city, String uf) {
        binding.editPublicSpace.setText(publicPlace);
        binding.editDistrict.setText(district);
        binding.editCity.setText(city);
        binding.editState.setText(uf);

    }
}
