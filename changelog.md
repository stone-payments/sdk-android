# Change Log

### v2.4.7
* Adicionando o status `WITH_ERROR` para transações com erro e que precisam ser canceladas (ex: timeout)
* Adicionando o `ReversalProvider` para varrer o banco de transações e cancelar as transações com o status `WITH_ERROR`
* Correção no cancelamento de transações

> exemplo do ReversalProvider 
```java
ReversalProvider reversalProvider = new ReversalProvider(this);
reversalProvider.setDialogMessage("Cancelando transação com erro");
reversalProvider.isDefaultUI();
reversalProvider.setConnectionCallback(new StoneCallbackInterface() {
      @Override
      public void onSuccess() {
        // code code code
      }

      @Override
      public void onError() {
        // code code code
      }
});
```

### v2.4.6
* Downgrade da targetSdkVersion de 26 para 25 devido a problemas de compatibilidade com APIs antigas
* URL de ativação do ambiente `CERTIFICATION` alterada
* Corrigido bug ao executar a migration de versões anteriores à 2.3.0
* Downgrade da XStream para 1.4.7 devido a problemas com Java8 nas versões mais novas
* Remoção da permissão `READ_PHONE_STATE` da SDK!

### v2.4.5
* Localização do `BluetoothConnectionProvider` corrigida.

### v2.4.4
* Melhoria e update das dependências
* Personalização das mensagens exibidas no Pinpad. No `TransactionProvider`, dois novos métodos foram implementados

###### Novos Métodos:
- `setPinpadFeedbackMessage(PinpadFeedback key, String message)` para customizar uma mensagem específica
- `setPinpadFeedbackMessages(Map<PinpadFeedback, String> pinpadFeedbackMessages)` para personalizar todas as mensagens de uma vez

##### Mensagens disponíveis para personalização e suas mensagens default (em comentário):
```java
PinpadFeedback.DENIED //"TRANSAC NEGADA"
PinpadFeedback.PROCESSING //"PROCESSANDO.."
PinpadFeedback.APPROVED //"TRANSAC APROVADA"
PinpadFeedback.CARD_REMOVE //"RETIRE O CARTAO"
PinpadFeedback.CARD_WITH_PROBLEMS //"ICC COM PROBLEMAS"
PinpadFeedback.DENIED_BY_NET //"TRANSAC NEGADA PELA REDE"
PinpadFeedback.CARD_INVALIDATED //"CARTAO ESTA INVALIDADO"
PinpadFeedback.DENIED_BY_CARD //"TRANSAC NEGADA PELO CARTAO"
```


### v2.4.2
* Melhorias de perfomance

### v2.4.1
* Adicionando suporte para conexões via cabo (ethernet)
* Campo `signature` em caso de necessidade de armazenar a assinatura da transação.
* Melhorias de perfomance

### v2.4.0
* Agora a SDK está em no artifactory! siga as instruções de instalação no readme.
* Possibilidade de escolher o uso de Pinpads Elavon (que não possuem chaves Stone)
* Todos os Providers recebem `Context` em vez de `Activity` no construtor
* Nova interface `StoneActionCallback` com um método `void onStatusChanged(Action action)` para receber eventos adicionais do provider. Esta interface herda de StoneCallbackInterface, então seu uso é opcional. Por enquanto existem somente eventos para transação, mas em breve terá eventos para outros providers.

###### Eventos disponíveis:
- `TRANSACTION_WAITING_CARD` Esperando cartão ser inserido/passado
- `TRANSACTION_WAITING_PASSWORD` Esperando senha do cartão (se houver)
- `TRANSACTION_SENDING` Enviando transação para o servidor
- `TRANSACTION_WAITING_REMOVE` Esperando a remoção do cartão do pinpad (se for chip)

### v2.3.2
* Agora você pode desativar a SDK usando `deactivate()` em `ActiveApplicationProvider`
* Método `execute()` do `ActiveApplicationProvider` descontinuado. Em vez disso, use o método `activate(List<String> stoneCodes)`
* Corrigido bug em `LoadTablesProvider` no qual pedia um objeto desnecessário no construtor.
* Novo campo `entryMode` no model da transação informando se a transação foi efetuada com chip ou tarja.
* Campos e métodos descontinuados.

### v2.3.1
* Corrigido problema na seleção do ambiente de `SANDBOX`

### v2.3.0
* Nova forma de instalação da lib, utilizando aar (instruções detalhadas no README), em breve teremos um repositório para distribuir nossas bibliotecas!
* Agora você pode definir qual ambiente você quer usar em runtime, usando
```java
// para integração
Stone.setEnvironment(Environment.SANDBOX)

// produção
Stone.setEnvironment(Environment.PRODUCTION)
```
* `Stone.developerMode()` deprecated em prol do novo modo de escolha de ambiente mencionado acima.
* Suporte para novas bandeiras: Elo, Alelo. (Verifique disponibilidade da bandeira no seu stone code com o time de integrações)
* Correções no envio de algumas transações para stone.
* Bug ao desconectar o pinpad fixed

### v2.2.10
* Hot fix do endpoint do TMS
* Correções de erro na ativação e download das tabelas AID e CAPK

### v2.2.9
* Correção do ambiente de homologação

### v2.2.8
* Suportando a bandeira Ticket

### v2.2.7
* Correções do erro `ClassNotFoundException` em algumas classes da SDK

### v2.2.6
* Opção de enviar uma transação não capturada

```java
StoneTransaction stoneTransaction = new StoneTransaction();
// set dos valores da transação
stoneTransaction.disableCapture(); // desabilita a captura da transação

```
> Por padrão a transação é capturada

### v2.2.5
* Transações com tarja sem senha e com senha
* Correções nas conexões com a internet

### v2.2.4
* Melhorias nas transações com tarja
* Correções no `BluetoothConnectionProvider`
* Adição do erro `PINPAD_ALREADY_CONNECTED` quando se tenta criar conexão com um pinpad já conectado.
* Outras pequenas correções

### v2.2.3
* Correções do ambiente de staging para integradores
* Correções no email com transações parceladas


### v2.2.2
* Atualização do endpoint de teste


### v2.2.1
* Pequenas correções
* Melhoria de desempenho no DB


### v2.2.0
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

### v2.1.4
* Homologações com o novo protocolo de conexão
* Pequenas correções
* Alteração da Gson 1.7.2 para Gson 2.3
* Adição do APK no repositório
* Adição do método de troca para o modo desenvolvedor
```java
  GlobalInformations.developerMode();
```

### v2.1.3
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

### v2.1.2
* Melhorias no banco de transações (TransactionDAO) e Pinpads (PinpadDAO);
* Múltiplas conexões com Pinpads (1 device android para N Pinpads);
* Documentação atualizada;
* Melhorias gerais no gerenciamento de conexões Bluetooth;
* Alterações em alguns construtores


### v2.1.1
* Corrigida a Exception que era dada quando não havia conexão com um Pinpad no DisplayMessageProvider


### v2.1.0
* PrintReceiptProvider (imprime comprovante no padrão da Stone);
* ValidateTransactionByCardProvider (captura um cartão e retorna todas as transações passadas com ele);
* Ativação com multiplos Stone Codes;
* Corrigido o erro com Xamain;
* Corrigido problemas com impressões da Ingênico;
* Alguns Providers tiveram suas assinaturas modificados;


### v2.0.3
* Hotfix nos tipos de conexão;
* Correções e melhorias na Demo;
* Documentação na pasta [/doc/](https://github.com/stone-pagamentos/sdk-android-V2/tree/master/doc);
* Demo utilizando o Android Studio;
* Demo - exemplo do DisplayMessageProvider;


### v2.0.2
* Hotfix na impressão com Ingenico (Logo da Stone);
* Melhores tratamentos no bluetooth;
* Melhorias na impressão (tratamentos para status diferente de 00);
* Correções na Demo (Extra)


### v2.0.1
* Hotfix no QR Code com Pinpads da Ingenico;
* Pequenas correções na transação (update na coluna 'request_id' da transação);
* Adicionada a função que o integrador poder enviar o ITK (identificador único da transação) pelo método 'setInitiatorTransactionKey(SEU_ITK_AQUI_STRING)' do objeto StoneTransaction;
* Assinatura do objeto PrintProvider mudou, agora você precisa informar qual pinpad você está utilizando, se estiver conectado a somente um Pinpad, passe 'GlobalInformations.getPinpadFromListAt(0)'
