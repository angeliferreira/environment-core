# environment  [![Build Status](https://travis-ci.org/angeliferreira/environment-core.png?branch=master)](https://travis-ci.org/angeliferreira/environment-core)

Environment is a basic project for running set ups before running each test method in a customized way than using @org.junit.Before and @org.junit.After of JUnit.


## Maven integration

To integrate *Environment Core* to your Maven project, you must declare the following dependency:

```xml
<dependency>
    <groupId>br.com.lemao</groupId>
    <artifactId>environment-core</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <scope>test</scope>
</dependency>
```

## Getting Started

## Basic Structure

### Creating your environment class

The abstract class *Environment* is the superclass for the Environments structure, where you gonna put your data creation calls. 

It supports 2 different basic usages, by class or by method.

### Structure of environment per class

The first implementation would provide one environment abstraction per class. You must implement the *run()* method only.

Your implementation, using the class Environment structure would be as follows:

```java
public class SampleEnvironment extends Environment {

   @Override
   public void run() {
      SampleUtil.createSample();
   }

}
```

### Structure of environment per method

The second implementation would provide one environment abstraction per method. You must implement different methods as you will. In this case you must create an Environment class which comprises the related methods.

Your implementation, using the method Environment structure would be as follows:

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

### @GivenEnvironment annotation

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

### Anotação @IgnoreEnvironment

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

## Criando sua estrutura Environments de maneira hierárquica

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
