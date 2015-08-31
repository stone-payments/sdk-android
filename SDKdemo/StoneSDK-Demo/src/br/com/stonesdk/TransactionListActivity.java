package br.com.stonesdk;

import android.support.v7.app.ActionBarActivity;

import java.util.List;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import stone.application.interfaces.StoneCallbackInterface;
import stone.database.transaction.TransactionDAO;
import stone.database.transaction.TransactionObject;
import stone.providers.CancellationProvider;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class TransactionListActivity extends ActionBarActivity implements OnItemClickListener {

	ListView listView;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction_list);
		
		listView = (ListView) findViewById(R.id.listTransactionActivity);
        
		// acessa todas as transacoes do banco de dados
		TransactionDAO transactionDAO = new TransactionDAO(getApplicationContext());
		// cria uma lista com todas as transacoes
		List<TransactionObject> transactionObjects = transactionDAO.getAllTransactionsOrderByIdDesc();	
		
		// exibe todas as transações (neste caso valor e status) para o usuario
		String[] rowOfList = new String[transactionObjects.size()];
		for (int i = 0; i < transactionObjects.size(); i++) {
			rowOfList[i] = String.format("%s=%s\n%s", transactionObjects.get(i).getIdFromBase(), transactionObjects.get(i).getAmount(), transactionObjects.get(i).getTransactionStatus());
		}
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, rowOfList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
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

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		String[] arrayIdAtPositionZero = listView.getAdapter().getItem(position).toString().split("=");
		final int transacionId = Integer.parseInt(arrayIdAtPositionZero[0].trim());
		
		final CancellationProvider cancellationProvider = new CancellationProvider(TransactionListActivity.this, transacionId);
		cancellationProvider.setWorkInBackground(false);
		cancellationProvider.setDialogMessage("Cancelando...");
		cancellationProvider.setConnectionCallback(new StoneCallbackInterface() {
			
			public void onSuccess() {
				Toast.makeText(getApplicationContext(), cancellationProvider.getMessageFromAuthorize(), 1).show();
				finish();
			}
			
			public void onError() {
				Toast.makeText(getApplicationContext(), "Um erro ocorreu durante o cancelamento com a transacao de id:" + transacionId, 1).show();
			}
		});
		cancellationProvider.execute();
	}
}
