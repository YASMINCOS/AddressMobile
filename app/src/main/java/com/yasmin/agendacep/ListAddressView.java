package com.yasmin.agendacep;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yasmin.agendacep.adapter.ListAddressAdapter;
import com.yasmin.agendacep.database.AddressDatabase;
import com.yasmin.agendacep.database.dao.RoomAddressDAO;
import com.yasmin.agendacep.model.Address;

public class ListAddressView {
    private final ListAddressAdapter adapter;
    private final RoomAddressDAO dao ;
    private final Context context;

    public ListAddressView(Context context) {
        this.context = context;
        this.adapter = new ListAddressAdapter(this.context);
        dao = AddressDatabase.getInstance(context)
                .getRoomAddressDao();

    }

    public void checkRemove(final MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle("Removendo endereço")
                .setMessage("Tem certeza que desejar remover o endereço?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo =
                            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Address addressChosen = adapter.getItem(menuInfo.position);
                    remove(addressChosen);
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void updateAddress() {
        adapter.update(dao.all());
    }

    private void remove(Address address) {
        dao.remove(address);
        adapter.remove(address);
    }

    public void tosetUpAdapter(ListView listAddress) {
        listAddress.setAdapter(adapter);
    }
}
