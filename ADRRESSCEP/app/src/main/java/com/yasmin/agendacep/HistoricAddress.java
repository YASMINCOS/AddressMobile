package com.yasmin.agendacep;

import static com.yasmin.agendacep.ConstantesActivies.CHAVE_ADDRESS;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yasmin.agendacep.model.Address;

public class HistoricAddress extends AppCompatActivity {


    private static final String TITULO_APPBAR = "Lista de endereços";
    private  ListAddressView listAddressView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic_address);
        setTitle(TITULO_APPBAR);
        listAddressView = new ListAddressView(this);
        toSetUpFabNewAddress();
        toSetUpList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater()
                .inflate(R.menu.list_address_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_alunos_menu_remover) {
            listAddressView.confirmaRemocao(item);
        }

        return super.onContextItemSelected(item);
    }

    private void toSetUpFabNewAddress() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(view -> openFormInsertAddress());
    }

    private void openFormInsertAddress() {
        startActivity(new Intent(this, FormAddress.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        listAddressView.updateAddress();
    }

    private void toSetUpList() {
        ListView listAddress = findViewById(R.id.activity_list_address_listview);
        listAddressView.tosetUpAdapter(listAddress);
        toSetUpListenerClick(listAddress);
        registerForContextMenu(listAddress);
    }

    private void toSetUpListenerClick(ListView listAddress) {
        listAddress.setOnItemClickListener((adapterView, view, posicao, id) -> {
            Address addressChosen = (Address) adapterView.getItemAtPosition(posicao);
            openFormEditAddress(addressChosen);
        });
    }

    private void openFormEditAddress(Address address) {
        Intent goFromFormActivity = new Intent(HistoricAddress.this, FormAddress.class);
        goFromFormActivity.putExtra(CHAVE_ADDRESS, String.valueOf(address));
        startActivity(goFromFormActivity);
    }
}