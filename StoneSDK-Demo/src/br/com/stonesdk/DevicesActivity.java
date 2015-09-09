package br.com.stonesdk;

import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import stone.application.interfaces.StoneCallbackInterface;
import stone.providers.BluetoothConnectionProvider;
import stone.utils.PinpadObject;

public class DevicesActivity extends ActionBarActivity implements OnItemClickListener{

	ListView listView;
	static BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	static boolean btConnected = false;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_devices);
		
		listView = (ListView) findViewById(R.id.listDevicesActivity);
		listView.setOnItemClickListener(this);
		turnBluetoothOn();
		listBluetoothDevices();
	}

	public void listBluetoothDevices() {
		
		// lista de Pinpads para passar para o BluetoothConnectionProvider
		ArrayAdapter<String> btArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

		BluetoothAdapter bluetoothAdapter  = BluetoothAdapter.getDefaultAdapter();
		Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

		// lista todos os dispositivos pareados
		if (pairedDevices.size() > 0) {
			for (BluetoothDevice device : pairedDevices) {
				btArrayAdapter.add(String.format("%s_%s", device.getName(), device.getAddress()));
			}
		}
		
		// exibe todos os dispositivos da lista
		listView.setAdapter(btArrayAdapter);
	 }
	
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void turnBluetoothOn() {
		try {
			mBluetoothAdapter.enable();
			do {} while (mBluetoothAdapter.isEnabled() == false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		// pega o pinpad selecionado do ListView
		String[] pinpadInfo = listView.getAdapter().getItem(position).toString().split("_");
		PinpadObject pinpadSelected = new PinpadObject(pinpadInfo[0], pinpadInfo[1], false);
		
		// passa o pinpad selectionado para o provider de conexao bluetooth
		BluetoothConnectionProvider bluetoothConnectionProvider = new BluetoothConnectionProvider(DevicesActivity.this, pinpadSelected);
		bluetoothConnectionProvider.setDialogMessage("Criando conexao com o pinpad selecionado"); // mensagem do dialog
		bluetoothConnectionProvider.setWorkInBackground(false); // diz que tera um feedback para o usuario
		bluetoothConnectionProvider.setConnectionCallback(new StoneCallbackInterface() { // chamada de retorno 
			
			public void onSuccess() {
				Toast.makeText(getApplicationContext(), "Pinpad conectado", 1).show();
				btConnected = true;
				finish();
			}
			
			public void onError() {
				Toast.makeText(getApplicationContext(), "Erro durante a conexao. Verifique a lista de erros do provider para mais informacoes", 1).show();
			}
		});
		bluetoothConnectionProvider.execute(); // manda executar
		
	}
}
