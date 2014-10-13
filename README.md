# environment  [![Build Status](https://travis-ci.org/mauricioborges/environment-core.png?branch=master)](https://travis-ci.org/mauricioborges/environment-core) 

Environment is a basic project for running set ups before running each test method in a customized way than using @org.junit.Before and @org.junit.After of JUnit.


## Environment Core

### Overview

*Environment Core* é um projeto básico para a execução de _set ups_ antes da execução de cada método de teste de maneira mais customizada do que o uso com @org.junit.Before e @org.junit.After do JUnit.

### Documentação do Projeto

* Integração com o Maven
* Getting Started
** Estrutura Básica
** Estrutura Customizada

## Integração com o Maven


Para integrar o *Environment Core* ao seu projeto Maven, basta declarar a seguinte dependência em seu arquivo POM.

```xml
<dependency>
    <groupId>br.com.lemao</groupId>
    <artifactId>environment</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

## Getting Started

### Estrutura Básica

#### Criando sua classe de Environment

A classe abstrata *Environment* é a superclasse da estrutura de Environments, é nela que deve ficar a chamada da criação dos dados.

Ela suporta duas implementações básicas para o seu uso, por classe ou por métodos.

#### Com estrutura de Environment por classe

A primeira implementação é utilizada quando se quer usar uma estrutura de Environments por classe, implementando o método *run()*. Nesse caso a abstração é ter uma classe por Environment.

Sua implementação em uma estrutura de Environments por classe ficaria da seguinte forma:

```java
public class SampleEnvironment extends Environment {

   @Override
   public void run() {
      SampleUtil.createSample();
   }

}
```

#### Com estrutura de Environment por método

A segunda implementação é utilizada quando se quer usar uma estrutura de Environments por métodos, implementando métodos que desejar. Nesse caso a abstração é ter Environment por métodos em uma mesma classe.

Sua implementação em uma estrutura de Environments por métodos ficaria da seguinte forma:

```java
public class SampleEnvironment extends Environment {

   public void myEnvironmentMethodNameOneSample() {
      SampleUtil.createSample();
   }

   public void myEnvironmentMethodName2Samples() {
      SampleUtil.create2Samples();
   }

}
```

#### Anotação @GivenEnvironment

A utilização do Environment se dá pelo uso da anotação *@GivenEnvironment*. Seu nome não foi dado levianamente, ele foi pensado para levar naturalmente para um conceito de *_[BDD](http://en.wikipedia.org/wiki/Behavior-driven_development)_* (_behavior driven development_) onde nesse caso seria a abstração do que você precisaria ter como estrutura inicial para o seu teste.{color}

A anotação GivenEnvironment diz qual estrutura será executada antes do método de teste. No caso da anotação estar em uma classe de teste, todos os casos de teste que NÃO estiverem anotadas com *@GivenEnvironment* ou&nbsp;*@IgnoreEnvironment* terão sua execução realizada após a execução da estrutura do Environment.

A anotação GivenEnvironment suporta duas utilizações básicas.

A primeira utilização se dá passando como parâmetro somente a classe de Environment que possui um método run(), estrutura de Environment por classe.

Sua utilização em um caso de teste ficaria da seguinte forma:

```java
public class Sample {

   @Test
   @GivenEnvironment(EnvironmentSample.class)
   public void method() {
      org.junit.Assert.assertFalse(SampleUtil.findAll().isEmpty());
   }

}
```

A segunda utilização se dá passando como parâmetro a classe de Environment e o nome do método do Environment,&nbsp;estrutura de Environment por método.

Sua utilização em um caso de teste ficaria da seguinte forma:

```java
public class Sample {

   @Test
   @GivenEnvironment(value=EnvironmentSample.class, environmentName="myEnvironmentMethodNameOneSample")
   public void method() {
      org.junit.Assert.assertFalse(SampleUtil.findAll().isEmpty());
   }

}
```

Sua utilização em uma classe de teste ficaria da seguinte forma:

```java
@GivenEnvironment(EnvironmentSample.class)
public class Sample {

   @Test
   public void method() {
      org.junit.Assert.assertFalse(SampleUtil.findAll().isEmpty());
   }

}
```

ou

```java
@GivenEnvironment(value=EnvironmentSample.class, environmentName="myEnvironmentMethodNameOneSample")
public class Sample {

   @Test
   public void method() {
      org.junit.Assert.assertFalse(SampleUtil.findAll().isEmpty());
   }

}
```

#### Anotação @IgnoreEnvironment

A anotação *IgnoreEnvironment* diz ao executor de testes que o mesmo deve ignorar a execução do Environment anotado na classe.

Sua utilização em um caso de teste ficaria da seguinte forma:

```java
@GivenEnvironment(EnvironmentSample.class)
public class Sample {

   @Test
   @IgnoreEnvironment
   public void method() {
      org.junit.Assert.assertTrue(SampleUtil.findAll().isEmpty());
   }

}
```

### Criando sua estrutura Environments de maneira hierárquica

É possivel construir uma estrutura de environments hieráquicos. Para isso é só anotar o método do Environment com @GivenEnvironment passando o Environment que se deseja como pai.

Sua utilização em uma estrutura de Environment por métodos ficaria da seguinte forma:

```java
public class SampleEnvironment extends Environment {

   public void myEnvironmentMethodNameOneSample() {
      SampleUtil.createSample();
   }

   @GivenEnvironment(value=EnvironmentSample.class, environmentName="myEnvironmentMethodNameOneSample")
   public void myEnvironmentMethodName3Samples() {
      SampleUtil.create2Samples();
   }

}
```

Sua utilização em uma estrutura de Environment por classe ficaria da seguinte forma:

```java
public class SampleEnvironmentOneSample extends Environment {

   @Override
   public void run() {
      SampleUtil.createSample();
   }

}

public class SampleEnvironment3Samples extends Environment {

   @Override
   @GivenEnvironment(SampleEnvironmentOneSample.class)
   public void run() {
      SampleUtil.create2Samples();
   }

}
```

## Estrutura Customizada

Muitas vezes é necessários fazer algumas configurações antes de rodar cada Environment. E agora?

Não tem problema, é possível customizar uma execução antes ou depois da execução de cada environment. Mas como posso fazer essa customização?

Para isso é necessário somente criar uma *Rule* e um *Statement* que estende de *EnvironmentStatement*.

```java
public class MyRule implements TestRule {

   @Override
   public Statement apply(Statement base, Description description) {
      return new MyStatement(base, description);
   }

}
```

```java
public class MyStatement extends EnvironmentStatement {

   public MyStatement(Statement statement, Description description) {
      super(statement, description);
   }

   @Override
   protected void before() {
      // Chamo que eu eu quero fazer antes da execução do Environment aqui!
      super.before(); // Essa chamada faz com que o environment seja executado
   }

   @Override
   protected void after() {
      // Chamo que eu eu quero fazer depois da execução do Environment aqui!
   }

}
```

E o uso na minha classe de teste fica assim:

```java
public class Sample {

   @Rule
   public MyRule myRule = new MyRule();

   @Test
   @GivenEnvironment(SampleEnvironment.class)
   public void sampleTest() {
      org.junit.Assert.assertTrue(SampleUtil.findAll().isEmpty());
   }

}
```

Nesse caso o fluxo de execução é o seguinte:

```java
br.com.lemao.environment.MyStatement.before()
br.com.lemao.environment.SampleEnvironment.run()
br.com.lemao.environment.Sample.sampleTest()
br.com.lemao.environment.MyStatement.after()
```
