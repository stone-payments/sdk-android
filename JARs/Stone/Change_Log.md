Change Log
=======================

Conteúdo:

<i><b>v2.0.1</b></i>
* [!] Hotfix no QR Code com Pinpads da Ingenico;
* [!] Pequenas correções na transação (update na coluna 'request_id' da transação);
* [+] Adicionada a função que o integrador poder enviar o ITK (identificador único da transação) pelo método 'setInitiatorTransactionKey(SEU_ITK_AQUI_STRING)' do objeto StoneTransaction;
* [#] Assinatura do objeto PrintProvider mudou, agora você precisa informar qual pinpad você está utilizando, se estiver conectado a somente um Pinpad, passe 'GlobalInformations.getPinpadFromListAt(0)'
