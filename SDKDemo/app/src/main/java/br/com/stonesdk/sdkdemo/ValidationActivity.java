package br.com.stonesdk.sdkdemo;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import stone.application.StoneStart;
import stone.application.interfaces.StoneCallbackInterface;
import stone.providers.ActiveApplicationProvider;
import stone.user.Partner;
import stone.user.UserModel;
import stone.utils.GlobalInformations;

public class ValidationActivity extends ActionBarActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3500;
    private final String STONE_PRODUCTION_KEY = "846873720";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_validation);

		/* Este deve ser, obrigatoriamente, o primeiro metodo
		 * a ser chamado. E um metodo que trabalha com sessao.
		 */
        UserModel user = StoneStart.startHere(this);

        // se retornar nulo, voce provavelmente nao ativou a SDK
        // ou as informacoes da Stone SDK foram excluidas
        if (user == null) {

            ActiveApplicationProvider activeApplicationProvider = new ActiveApplicationProvider(this);
            activeApplicationProvider.setStoneCode(STONE_PRODUCTION_KEY); // voce deve colocar o seu StoneCode aqui
            activeApplicationProvider.setDialogMessage("Ativando o aplicativo...");
            activeApplicationProvider.setDialogTitle("Aguarde");
            activeApplicationProvider.setActivity(ValidationActivity.this);
            activeApplicationProvider.setWorkInBackground(false); // informa se este provider ira rodar em background ou nao
            activeApplicationProvider.setConnectionCallback(new StoneCallbackInterface() {

				/* Sempre que utilizar um provider, intancie esta Interface.
				 * Ela ira lhe informar se o provider foi executado com sucesso ou nao
				 */

                /* Metodo chamado se for executado sem erros */
                public void onSuccess() {
                    Toast.makeText(getApplicationContext(), "Ativado com sucesso, iniciando o aplicativo", 1).show();
                    continueApplication();
                }

                /* metodo chamado caso ocorra alguma excecao */
                public void onError() {
                    Toast.makeText(getApplicationContext(), "Erro na ativacao do aplicativo, verifique a lista de erros do provider", 1).show();
					/* Chame o metodo abaixo para verificar a lista de erros. Para mais detalhes, leia a documentacao:
					   activeApplicationProvider.getListOfErrors(); */
                }
            });
            activeApplicationProvider.execute();
        } else {

			/* caso ja tenha as informacoes da SDK e chamado o ActiveApplicationProvider anteriormente
			   sua aplicacao podera seguir o fluxo normal */
            continueApplication();

        }
    }

    private void continueApplication(){

        new Handler().postDelayed(new Runnable() {
            public void run() {
                // As próximas duas linhas são utilizadas apenas para que
                // a aplicacao reconheca o usuario como desenvolvedor.
//				Partner partner = new Partner(GlobalInformations.getUserModel());
//				GlobalInformations.sessionApplication.setUserModel(partner);


                Intent mainIntent = new Intent(ValidationActivity.this, MainActivity.class);
                ValidationActivity.this.startActivity(mainIntent);
                ValidationActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
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

