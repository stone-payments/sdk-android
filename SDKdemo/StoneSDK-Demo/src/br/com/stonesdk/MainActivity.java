package br.com.stonesdk;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import br.com.stonesdk.R;
import br.com.stonesdk.R.id;
import br.com.stonesdk.R.layout;
import br.com.stonesdk.R.menu;
import stone.application.interfaces.StoneCallbackInterface;
import stone.cache.ApplicationCache;
import stone.providers.DownloadTablesProvider;
import stone.utils.GlobalInformations;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	ListView listView;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
        listView = (ListView) findViewById(R.id.listMainActivity);
        
        String[] options = new String[] { "Dispositivos pareados", 
                                          "Fazer uma transação",
                                          "Listar transações",
                                          "Envio de e-mail"
                                        };
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, options);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
        	 
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            	int itemPosition = position;
                
             	// Para cada nova opção na lista, um novo "case" precisa ser inserido aqui.
             	switch (itemPosition) {
				case 0:
					Intent devicesIntent = new Intent(MainActivity.this, DevicesActivity.class);
					MainActivity.this.startActivity(devicesIntent);
					break;
				case 1:
					Intent transactionIntent = new Intent(MainActivity.this, TransactionActivity.class);
					MainActivity.this.startActivity(transactionIntent);
					break;
				case 2:
					Intent transactionListIntent = new Intent(MainActivity.this, TransactionListActivity.class);
					MainActivity.this.startActivity(transactionListIntent);
					break;
				case 3:
					Intent sendEmailIntent = new Intent(MainActivity.this, SendEmailActivity.class);
					MainActivity.this.startActivity(sendEmailIntent);
					break;
				default:
					break;
				}
            }
       }); 
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
	
	protected void onResume() {
		super.onResume();
		if (GlobalInformations.isDeveloper() == true) {
			Toast.makeText(getApplicationContext(), "Modo desenvolvedor", 1).show();
		}
		
		// IMPORTANTE: Mantenha esse provider na sua main, pois
		// ele ira baixar as tabelas AIDs e CAPKs dos servidores
		// da Stone e sera utilizada quando necessario.
		ApplicationCache applicationCache = new ApplicationCache(getApplicationContext());
		if (applicationCache.checkIfHasTables() == false) {
			
			// realiza todo processo de download das tabelas
			DownloadTablesProvider downloadTablesProvider = new DownloadTablesProvider(MainActivity.this);
			downloadTablesProvider.setDialogMessage("Baixando as tabelas, por favor aguarde");
			downloadTablesProvider.setWorkInBackground(false); // para dar feedback ao usuario ou nao.
			downloadTablesProvider.setConnectionCallback(new StoneCallbackInterface() { // chamada de retorno.
				public void onSuccess() {
					Toast.makeText(getApplicationContext(), "Tabelas baixadas com sucesso", 1).show();
				}
				public void onError() {
					Toast.makeText(getApplicationContext(), "Erro no download das tabelas", 1).show();
				}
			});
			downloadTablesProvider.execute();
		}
		
	}
}
