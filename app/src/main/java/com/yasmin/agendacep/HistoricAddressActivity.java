package com.yasmin.agendacep;

import static com.yasmin.agendacep.ConstantesActivies.CHAVE_ADDRESS;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yasmin.agendacep.databinding.ActivityFormAddressBinding;
import com.yasmin.agendacep.databinding.ActivityHistoricAddressBinding;
import com.yasmin.agendacep.model.Address;

public class HistoricAddressActivity extends AppCompatActivity {


    ActivityHistoricAddressBinding binding;
    private static final String TITULO_APPBAR = "Lista de endereços";
    private ListAddressView listAddressView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoricAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());;
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
            listAddressView.checkRemove(item);
        }

        return super.onContextItemSelected(item);
    }

    private void toSetUpFabNewAddress() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(view -> openFormInsertAddress());
    }

    private void openFormInsertAddress() {
        startActivity(new Intent(this, FormAddressActivity.class));
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
        Intent goFromFormActivity = new Intent(HistoricAddressActivity.this, FormAddressActivity.class);
        goFromFormActivity.putExtra(CHAVE_ADDRESS, String.valueOf(address));
        startActivity(goFromFormActivity);
    }
}