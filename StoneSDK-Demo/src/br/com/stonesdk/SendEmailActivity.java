package br.com.stonesdk;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import stone.application.interfaces.StoneCallbackInterface;
import stone.database.transaction.TransactionObject;
import stone.providers.SendEmailProvider;
import stone.utils.EmailClient;

public class SendEmailActivity extends ActionBarActivity {

	TextView userText;
	TextView sendText;
	EditText userEditText;
	EditText sendEmailText;
	Button sendButton;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_email);
		userText = (TextView) findViewById(R.id.emailDescription);
		sendText = (TextView) findViewById(R.id.emailSendDescription);
		userEditText = (EditText) findViewById(R.id.emailUserText);
		sendEmailText = (EditText) findViewById(R.id.emailSendText);
		sendButton = (Button) findViewById(R.id.sendEmailButton);
		
		instanceEvents();
	}

	private void instanceEvents() {
		sendButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (isNetworkAvailable() == true) {

					// captura o texto inserido pelo usuario
					String emailTextSample = userEditText.getText().toString();
					String emailTo = sendEmailText.getText().toString().trim();

					EmailClient emailClient = new EmailClient("servidor smtp", 
															  "remetente do e-mail", 
															  "senha do e-mail",
															   emailTo, // o e-mail digitado pelo usuário
															  "assunto do e-mail");
					emailClient.setSport("587"); // S Port
					emailClient.setSmtpPport("587"); // SMTP P Port
					String receipt = emailTextSample; // texto digitado pelo usuario

					SendEmailProvider sendEmailProvider = new SendEmailProvider(SendEmailActivity.this, emailClient, receipt);
					sendEmailProvider.setWorkInBackground(false); // para dar feedback ao usuario ou nao.
					sendEmailProvider.setDialogMessage("Enviando comprovante..");
					sendEmailProvider.setConnectionCallback(new StoneCallbackInterface() { // chamada de retorno
						public void onSuccess() {
							Toast.makeText(getApplicationContext(), "Enviado com sucesso", Toast.LENGTH_LONG).show();
						}

						public void onError() {
							Toast.makeText(getApplicationContext(), "Nao enviado", Toast.LENGTH_LONG).show();
						}
					});
					sendEmailProvider.execute();

				} else {
					Toast.makeText(getApplicationContext(), "Não há conexão com internet ativada.", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	// checa se existe alguma conexao com a internet para enviar o email
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
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
}
