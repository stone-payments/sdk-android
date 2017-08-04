<p align="center">
  <img src="http://www.stone.com.br/images/logo-big.png" alt="Stone Logo"/>
</p>

# SDK Android

### Instalação
Primeiro, adicione o nosso repositório no `build.gradle` do seu projeto:
```groovy
repositories {
  maven { url "https://packagecloud.io/stone/sdk-android/maven2" }
}
```

depois, adicione como dependência no módulo desejado:
```groovy
dependencies {
  compile "br.com.stone:stone-sdk:$stone_sdk_version"
}
```

### Demo
Você pode encontrar uma demo da SDK [nesse repositório](https://github.com/stone-pagamentos/demo-sdk-android).

### Changelog
Você pode acompanhar as alterações da SDK [aqui](https://github.com/stone-pagamentos/sdk-android-V2/blob/master/changelog.md).
