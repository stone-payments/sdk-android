# Change Log

**v2.2.4**
* Melhorias nas transações com tarja
* Correções no `BluetoothConnectionProvider`
* Adição do erro `PINPAD_ALREADY_CONNECTED` quando se tenta criar conexão com um pinpad já conectado.
* Outras pequenas correções

**v2.2.3**
* Correções do ambiente de staging para integradores
* Correções no email com transações parceladas


**v2.2.2**
* Atualização do endpoint de teste


**v2.2.1**
* Pequenas correções
* Melhoria de desempenho no DB


**v2.2.0**
* Correções no `SendEmailProvider`
* Adição do Provider `SendEmailTransactionProvider` para enviar comprovantes por email

```java

// exemplo de uma Activity
TransactionObject transactionObject = new TransactionDAO(this).findTransactionWithId(idTransaction);

SendEmailTransactionProvider emailTransactionProvider = new SendEmailTransactionProvider(this, GlobalInformations.getUserModel(0), transactionObject);
emailTransactionProvider.setWorkInBackground(false);
emailTransactionProvider.setEmailToSent("email@dominio.com");
emailTransactionProvider.setDialogMessage("Enviando e-mail...");
emailTransactionProvider.execute();
```


**v2.1.4**
* Homologações com o novo protocolo de conexão
* Pequenas correções
* Alteração da Gson 1.7.2 para Gson 2.3
* Adição do APK no repositório
* Adição do método de troca para o modo desenvolvedor
```java
  GlobalInformations.developerMode();
```

**v2.1.3**
* Atualizações no banco de transações;
* Correções na impressão (QR Code com PAX);
* Remoção da logo da Stone do PrintProvider;
* Alteração do ambiente de homologação;
* Correções em geral;
* Adição do método isConnectedToPinpad() na GlobalInformations;

**Método de um parceiro:**
```java
  // este método apenas verifica se a lista está ou não vazia,
  // para testar a sua conexão com o Pinpad, aconselhamos que use o DisplayMessageProvider
  public static boolean isConnectedToPinpad() {
        return GlobalInformations.getPinpadListSize() != null &&
               GlobalInformations.getPinpadListSize() > 0     &&
               GlobalInformations.getPinpadFromListAt(0) != null;
  }

```

**v2.1.2**
* Melhorias no banco de transações (TransactionDAO) e Pinpads (PinpadDAO);
* Múltiplas conexões com Pinpads (1 device android para N Pinpads);
* Documentação atualizada;
* Melhorias gerais no gerenciamento de conexões Bluetooth;
* Alterações em alguns construtores


**v2.1.1**
* Corrigida a Exception que era dada quando não havia conexão com um Pinpad no DisplayMessageProvider


**v2.1.0**
* PrintReceiptProvider (imprime comprovante no padrão da Stone);
* ValidateTransactionByCardProvider (captura um cartão e retorna todas as transações passadas com ele);
* Ativação com multiplos Stone Codes;
* Corrigido o erro com Xamain;
* Corrigido problemas com impressões da Ingênico;
* Alguns Providers tiveram suas assinaturas modificados;


**v2.0.3**
* Hotfix nos tipos de conexão;
* Correções e melhorias na Demo;
* Documentação na pasta [/doc/](https://github.com/stone-pagamentos/sdk-android-V2/tree/master/doc);
* Demo utilizando o Android Studio;
* Demo - exemplo do DisplayMessageProvider;


**v2.0.2**
* Hotfix na impressão com Ingenico (Logo da Stone);
* Melhores tratamentos no bluetooth;
* Melhorias na impressão (tratamentos para status diferente de 00);
* Correções na Demo (Extra)


**v2.0.1**
* Hotfix no QR Code com Pinpads da Ingenico;
* Pequenas correções na transação (update na coluna 'request_id' da transação);
* Adicionada a função que o integrador poder enviar o ITK (identificador único da transação) pelo método 'setInitiatorTransactionKey(SEU_ITK_AQUI_STRING)' do objeto StoneTransaction;
* Assinatura do objeto PrintProvider mudou, agora você precisa informar qual pinpad você está utilizando, se estiver conectado a somente um Pinpad, passe 'GlobalInformations.getPinpadFromListAt(0)'
