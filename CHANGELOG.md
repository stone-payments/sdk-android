# CHANGELOG

### v2.6.0
:brazil:
- Corrigido bug onde informações novas vindas de qualquer request travava a SDK, não retornando um status de erro.
- Novos campos no `StoneTransaction` para editar as informações do lojista que está efetuando a transação (modelo subadquirente):
```java
stoneTransaction.setSubMerchantCity("city") //Cidade do sub-merchant
stoneTransaction.setSubMerchantPostalAddress("00000000") //CEP do sub-merchant (Apenas números)
stoneTransaction.setSubMerchantRegisteredIdentifier("00000000") // Identificador do sub-merchant
stoneTransaction.setSubMerchantTaxIdentificationNumber("33368443000199") // CNPJ do sub-merchant (apenas números)
```

:us:
- Fixed bug where new information coming from any request was freezing the SDK, not returning any error status.
- New fields in `StoneTransaction` to change merchant info that is doing the transaction (subacquirer model):
```java
stoneTransaction.setSubMerchantCity("city") // sub-merchant city
stoneTransaction.setSubMerchantPostalAddress("00000000") // sub-merchant postal code (only numbers)
stoneTransaction.setSubMerchantRegisteredIdentifier("00000000") // sub-merchant identifier
stoneTransaction.setSubMerchantTaxIdentificationNumber("33368443000199") // sub-merchant Tax Identification (only numbers)
```

---

### v2.5.9 (05/04/2018)
:brazil:
- Agora não é mais necessário usar o `LoadTablesProvider` para efetuar a carga de tabelas no Pinpad, todo o gerenciamento será feito internamente pela SDK.
- Enum `TABLES_NOT_FOUND` depreciado por não ser mais disparado.
- Enum `NEED_LOAD_TABLES` depreciado por não ser mais disparado.
- Provider `DownloadTablesProvider` removido por não ter mais utilidade.
- Corrigido bug onde o enum `PINPAD_ALREADY_CONNECTED` não era chamado ao conectar com um pinpad já conectado.
- Corrigido bug no `BluetoothConnectionProvider` que causava crash no app se ocorresse um erro desconhecido.
- Melhoria na detecção das bandeiras.
- Corrigido problema na migration do banco de pinpads quando atualizado de versões muito antigas da SDK.

:us:
- Is no longer required to use `LoadTablesProvider` to load tables on pinpad, the SDK will handle everything internally.
- Enum `TABLES_NOT_FOUND` deprecated for not being triggered anymore.
- Enum `NEED_LOAD_TABLES` deprecated for not being triggered anymore.
- Provider `DownloadTablesProvider` removed because it is no longer useful.
- Fixed bug where enum `PINPAD_ALREADY_CONNECTED` was not called when connecting with a pinpad already connected.
- Fixed bug in `BluetoothConnectionProvider` which caused crash in the app if an unknown error occurred.
- Improvement on card brands detection.
- Fixed Pinpad database migration problem when upgrading from older SDK versions.

---

### v2.5.8 (05/04/2018)
:brazil:
- Timeout das requests http aumentado para 1 minuto
- Corrigido bug da SDK deixar 2 ou mais stone codes iguais ficarem ativos no `ActiveApplicationProvider`
- Adicionado stacktrace das exceptions no `BluetoothConnectionProvider`

:us:
- Http requests timeout increased to 1 minute
- Fixed bug when the SDK accepts 2 or more identical stone codes be activated on `ActiveApplicationProvider`
- Added exceptions stacktrace on `BluetoothConnectionProvider`

---

### v2.5.7 (23/03/2018)
:brazil:
- Provider `CancellationProvider` agora retorna o actionCode do cancelamento pelo método `cancellationProvider.getActionCode()` e o status do cancelamento pelo `cancellationProvider.getResponseCodeEnum()`.
- Métodos `getTransactionStatus()` e `getStatusAsString()` do `CancellationProvider` foram removidos em prol dos novos métodos citados acima.
- Corrigido bug onde a mensagem de "Transação Aprovada" não aparecia no pinpad em algumas transações
- Novo método `activate(String stoneCode)` para ativar e adicionar um novo stone code na lista de stone codes ativos.
- Novo método `deactivate(String stoneCode)` para desativar somente um stone code da lista de ativos
- Corrigido bug onde versões anteriores da SDK recebiam NPE em pinpads já salvos sem o campo novo `acqidx` do `PinpadObject`.

:us:
- Provider `CancellationProvider` now returns the cancellation actionCode via `cancellationProvider.getActionCode()` and cancellation status via `cancellationProvider.getResponseCodeEnum()`.
- Methods `getTransactionStatus()` and `getStatusAsString()` from `CancellationProvider` were removed for the use of the methods mentioned above.
- Fixed bug where "Transação Aprovada" message did not appear on the pinpad in some transactions.
- New method `activate(String stoneCode)` to activate and add a new stone code in the list of active stone codes.
- New method `deactivate(String stoneCode)` to deactivate only one stone code from active list.
- Fixed bug where earlier SDK versions was getting NPE on pinpads already stored without the new field `acqidx` on `PinpadObject`.

---

### v2.5.6 (27/02/2018)
:brazil:
- Nova dependência OkHttp para substituir o `HttpUrlConnection` nas requests da SDK para nossos servidores;
- Suporte ao TLS >= 1.2 em versões do android <= 4.4 (KitKat);
- Novo Provider `CaptureTransactionProvider` para capturar transações cuja requisição foi feita com captura posterior (setando `stoneTransaction.capture = false`);
- Correção na efetuação de transações da bandeira `SODEXO`;
- Melhoria na captura do `CVM` do pinpad, forçando o retorno;
- Gerenciando o uso da chave Elavon/Stone internamente. Não é mais necessário setar `Stone.setAcquirer(Acquirer acquirer)`. Se o Pinpad não tiver nenhuma das duas chaves, a SDK retornará o erro `ErrorsEnum.PINPAD_WITHOUT_KEY` durante a conexão do pinpad no `BluetoothConnectionProvider`;
- Novo ambiente `INTERNAL_CERTIFICATION` para validação do app pelo time de integrações da Stone;

:us:
- New dependency OkHttp to replace `HttpUrlConnection` requests to our servers;
- Supporting TLS >= 1.2 on androids <= 4.4 (KitKat);
- New provider `CaptureTransactionProvider` to capture transactions which request was mad without capture (setting `stoneTransaction.capture = false`);
- Fix in transactions with capture of brand `SODEXO`;
- `CVM` capture from pinpad improved, forcing the return;
- Managing the use of the Elavon/Stone key internally. It is no longer necessary to set `Stone.setAcquirer (Acquirer acquirer)`. If the Pinpad doesn't have any of the two keys, the SDK will return the `ErrorsEnum.PINPAD_WITHOUT_KEY` error while connecting the pinpad on` BluetoothConnectionProvider`;
- New environment `INTERNAL_CERTIFICATION` for app validation by Stone integrations team;

---

### v2.5.5 (05/02/2018)
- Adicionado campo `subMerchantAddress` no `TransactionObject` para editar o endereço do lojista que está efetuando a transação;
- Adicionado campo `subMerchantCategoryCode` no `TransactionObject` para editar o mcc do lojista que está efetuando a transação;
- Adicionado campo `shortName` no `TransactionObject` para armazenar em banco opção setada no campo `shortName` do `StoneTransaction`;
- Adicionado campo `capture` no `TransactionObject` para armazenar em banco opção setada no campo `capture` do `StoneTransaction`;
- Construtor `CancellationProvider(Context context, int idFromTransactionInBase, UserModel userModel)` depreciado. Em vez dele, use `CancellationProvider(Context context, TransactionObject transaction)`
- Correção no migration da tabela de `Transaction` onde algumas colunas não estavam sendo inseridas quando atualizadas de versões muito antigas da SDK;

---

### v2.5.4-1 (24/01/2018)
- Corrigido bug onde a transação era efetuada mas não era salva no banco

---

### v2.5.4 
- Correção na leitura de informações de cartões ELO.
- Enum `GENERIC_ERROR` utilizado pelo `ActiveApplicationProvider` para quando o stone code não é reconhecido foi alterado para `INVALID_STONE_CODE_OR_UNKNOWN`.
- Melhoria na estrutura interna e na performance do `TransactionProvider`.
- Correção no envio do comprovante por email, colocando campo "assinatura" quando não era necessário.
- Corrigido bug onde eventualmente o método `getListOfErrors()` retornava `null`

---

### v2.5.3 (29/12/2017)
- Corrigido bug quando o `BluetoothConnectionProvider` disparava o evento `onSuccess` duplicado.
- Corrigido bug da SDK baixando tabelas toda vez que abria o app.
- Support Library atualizado para `27.0.2`.

---

### v2.5.2 (22/12/2017)
- Adicionado campo `cancellationDate` no `TransactionObject` para armazenar a data do cancelamento;
- Adicionado campo `lastConnectionAt` no `PinpadObject` para armazenar a data da última vez que houve conexão com o pinpad;
- Todos os erros durante a conexão com o device bluetooth que retornavam em dialog. Agora os erros são adicionados no array de erros do provider. Segue exemplo de uso:
```Java8
bluetoothConnectionProvider.setConnectionCallback(new StoneCallbackInterface() {

    public void onSuccess() {
        //handle success
    }

    public void onError() {
        List<ErrorsEnum> listOfErrors = bluetoothConnectionProvider.getListOfErrors();
        if (listOfErrors.contains(ErrorsEnum.PINPAD_ALREADY_CONNECTED)) {
            //Do something
        } else if (listOfErrors.contains(ErrorsEnum.TIME_OUT)) {
            //Do something
        } else if (listOfErrors.contains(ErrorsEnum.DEVICE_NOT_COMPATIBLE)) {
            //Do something
        } else if (listOfErrors.contains(ErrorsEnum.IO_ERROR_WITH_PINPAD)) {
            //Do something
        }
    }
});
```
- O método `Stone.getPinpadListSize()` não mais retorna `null` quando a lista de pinpads estiver vazia
- Refatorado o gerenciamento das versões das tabelas, corrigindo o problema de carregar tabelas a cada transação;
- Construtores `LoadTablesProvider(Context, GcrRequestCommand, PinpadObject)` e `LoadTablesProvider(Context, String, PinpadObject)` depreciados. Use `LoadTablesProvider(Context context, PinpadObject)` em vez disso;

---

### v2.5.1 (11/12/2017)
- SendEmailProvider depreciado em prol do uso do `SendEmailTransactionProvider`
- Adicionado flag `merchantReceipt` (default false) pra informar se é pra enviar a via do cliente ou do estabelecimento no `SendEmailTransactionProvider`
- Método `setEmailToSent` e `setEmailsToSent` da classe `SendEmailTransactionProvider` depreciado, usar `addTo()`/`setTo()` para setar o destinatário do email
- Possibilidade de definir o remetente do email no SendEmailTransactionProvider no método `setFrom`
- Adicionado campo `balance` no TransactionObject pra transações voucher (SODEXO, Ticket, etc...)
- Bug de cartões (principalmente HIPER) retornando `UNKNOWN` fixed
- Novos métodos (`findTransactionWithAuthorizationCode()`, `findTransactionWithInitiatorTransactionKey()` e `findTransactionByFilter()` ) no TransactionDAO para busca das transações no banco local da SDK.

---

### v2.5.0 (25/10/2017)
- Permissões `android.permission.VIBRATE` e `android.permission.ACCESS_WIFI_STATE` removidas da SDK
- Novos campos `cvm` e `serviceCode` no `TransactionObject`
- appcompat-v7 atualizado para `26.1.0`
- Enum de feedback `CARD_REMOVE` _deprecated_ por não ser uma mensagem alterável.
- Enum de feedback `REVERSAL` _deprecated_ pois não mandamos nenhuma mensagem para o pinpad
- Revertendo transações quando negado pelo cartão
- Bug de mostrar mensagem `RETIRE O CARTÃO` em cartões mágnéticos fixed
- gson atualizado de `2.8.1` para `2.8.2`
- Várias melhorias de performance

---

### v2.4.8
* Adicionando `REVERSING_TRANSACTION_WITH_ERROR` ao enum `Action`
* Revertendo transações automaticamente, quando ocorrer um erro
> As transações que não foram processadas por um erro de conexão devem ter canceladas utilizando o `ReversalProvider`

---

### v2.4.7 (28/09/2017)
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

---

### v2.4.6 (28/09/2017)
* Downgrade da targetSdkVersion de 26 para 25 devido a problemas de compatibilidade com APIs antigas
* URL de ativação do ambiente `CERTIFICATION` alterada
* Corrigido bug ao executar a migration de versões anteriores à 2.3.0
* Downgrade da XStream para 1.4.7 devido a problemas com Java8 nas versões mais novas
* Remoção da permissão `READ_PHONE_STATE` da SDK!

---

### v2.4.5 (12/09/2017)
* Localização do `BluetoothConnectionProvider` corrigida.

---

### v2.4.4 (08/09/2017)
* Melhoria e update das dependências
* Personalização das mensagens exibidas no Pinpad. No `TransactionProvider`, dois novos métodos foram implementados

---

###### Novos Métodos:
- `setPinpadFeedbackMessage(PinpadFeedback key, String message)` para customizar uma mensagem específica
- `setPinpadFeedbackMessages(Map<PinpadFeedback, String> pinpadFeedbackMessages)` para personalizar todas as mensagens de uma vez

---

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

---

### v2.4.2 (22/08/2017)
* Melhorias de perfomance

---

### v2.4.1 (14/08/2017)
* Adicionando suporte para conexões via cabo (ethernet)
* Campo `signature` em caso de necessidade de armazenar a assinatura da transação.
* Melhorias de perfomance

---

### v2.4.0 (04/08/2017)
* Agora a SDK está em no artifactory! siga as instruções de instalação no readme.
* Possibilidade de escolher o uso de Pinpads Elavon (que não possuem chaves Stone)
* Todos os Providers recebem `Context` em vez de `Activity` no construtor
* Nova interface `StoneActionCallback` com um método `void onStatusChanged(Action action)` para receber eventos adicionais do provider. Esta interface herda de StoneCallbackInterface, então seu uso é opcional. Por enquanto existem somente eventos para transação, mas em breve terá eventos para outros providers.

---

###### Eventos disponíveis:
- `TRANSACTION_WAITING_CARD` Esperando cartão ser inserido/passado
- `TRANSACTION_WAITING_PASSWORD` Esperando senha do cartão (se houver)
- `TRANSACTION_SENDING` Enviando transação para o servidor
- `TRANSACTION_WAITING_REMOVE` Esperando a remoção do cartão do pinpad (se for chip)

---

### v2.3.2 (28/07/2017)
* Agora você pode desativar a SDK usando `deactivate()` em `ActiveApplicationProvider`
* Método `execute()` do `ActiveApplicationProvider` descontinuado. Em vez disso, use o método `activate(List<String> stoneCodes)`
* Corrigido bug em `LoadTablesProvider` no qual pedia um objeto desnecessário no construtor.
* Novo campo `entryMode` no model da transação informando se a transação foi efetuada com chip ou tarja.
* Campos e métodos descontinuados.

---

### v2.3.1 (21/07/2017)
* Corrigido problema na seleção do ambiente de `SANDBOX`

---

### v2.3.0 (06/07/2017)
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

---

### v2.2.10
* Hot fix do endpoint do TMS
* Correções de erro na ativação e download das tabelas AID e CAPK

---

### v2.2.9
* Correção do ambiente de homologação

---

### v2.2.8
* Suportando a bandeira Ticket

---

### v2.2.7
* Correções do erro `ClassNotFoundException` em algumas classes da SDK

---

### v2.2.6
* Opção de enviar uma transação não capturada

```java
StoneTransaction stoneTransaction = new StoneTransaction();
// set dos valores da transação
stoneTransaction.disableCapture(); // desabilita a captura da transação

```
> Por padrão a transação é capturada

---

### v2.2.5
* Transações com tarja sem senha e com senha
* Correções nas conexões com a internet

---

### v2.2.4
* Melhorias nas transações com tarja
* Correções no `BluetoothConnectionProvider`
* Adição do erro `PINPAD_ALREADY_CONNECTED` quando se tenta criar conexão com um pinpad já conectado.
* Outras pequenas correções

---

### v2.2.3
* Correções do ambiente de staging para integradores
* Correções no email com transações parceladas

---

### v2.2.2
* Atualização do endpoint de teste

---

### v2.2.1
* Pequenas correções
* Melhoria de desempenho no DB

---

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

---

### v2.1.4
* Homologações com o novo protocolo de conexão
* Pequenas correções
* Alteração da Gson 1.7.2 para Gson 2.3
* Adição do APK no repositório
* Adição do método de troca para o modo desenvolvedor
```java
  GlobalInformations.developerMode();
```

---

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

---

### v2.1.2
* Melhorias no banco de transações (TransactionDAO) e Pinpads (PinpadDAO);
* Múltiplas conexões com Pinpads (1 device android para N Pinpads);
* Documentação atualizada;
* Melhorias gerais no gerenciamento de conexões Bluetooth;
* Alterações em alguns construtores

---

### v2.1.1
* Corrigida a Exception que era dada quando não havia conexão com um Pinpad no DisplayMessageProvider

---

### v2.1.0
* PrintReceiptProvider (imprime comprovante no padrão da Stone);
* ValidateTransactionByCardProvider (captura um cartão e retorna todas as transações passadas com ele);
* Ativação com multiplos Stone Codes;
* Corrigido o erro com Xamain;
* Corrigido problemas com impressões da Ingênico;
* Alguns Providers tiveram suas assinaturas modificados;

---

### v2.0.3
* Hotfix nos tipos de conexão;
* Correções e melhorias na Demo;
* Documentação na pasta [/doc/](https://github.com/stone-pagamentos/sdk-android-V2/tree/master/doc);
* Demo utilizando o Android Studio;
* Demo - exemplo do DisplayMessageProvider;

---

### v2.0.2
* Hotfix na impressão com Ingenico (Logo da Stone);
* Melhores tratamentos no bluetooth;
* Melhorias na impressão (tratamentos para status diferente de 00);
* Correções na Demo (Extra)

---

### v2.0.1
* Hotfix no QR Code com Pinpads da Ingenico;
* Pequenas correções na transação (update na coluna 'request_id' da transação);
* Adicionada a função que o integrador poder enviar o ITK (identificador único da transação) pelo método 'setInitiatorTransactionKey(SEU_ITK_AQUI_STRING)' do objeto StoneTransaction;
* Assinatura do objeto PrintProvider mudou, agora você precisa informar qual pinpad você está utilizando, se estiver conectado a somente um Pinpad, passe 'GlobalInformations.getPinpadFromListAt(0)'
