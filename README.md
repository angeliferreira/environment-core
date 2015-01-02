<p align="center">
  <img src="https://github.com/angeliferreira/environment-core/blob/master/environment.jpg?raw=true"/>
</p>

# environment  [![Build Status](https://travis-ci.org/angeliferreira/environment-core.png?branch=master)](https://travis-ci.org/angeliferreira/environment-core)

Environment is a basic project for running set ups before running each test method in a customized way than using @org.junit.Before and @org.junit.After of JUnit.


## Maven integration

To integrate *Environment Core* to your Maven project, you must declare the following dependency (Not in maven repository yet, must install it local):

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

You have two auxiliary methods *beforeRun()* and *afterRun()* that are intended to provide creation and dispose of resources 
before and after executing the *run()* method, implementing these methods is optional, and the *afterRun()* method will be 
executed even if *run()* throws exception.

Your implementation, using the class Environment structure would be as follows:

```java
public class SampleEnvironment extends Environment {

   @Override
   public void run() {
      SampleUtil.createSample();
   }

}
```

Your implementation using *beforeRun()* and *afterRun()* would be as follows:

```java
public class SampleEnvironment extends Environment {

    @Override
    public void run() {
        SampleUtil.createSample();
    }

    @Override
    public void afterRun() {
        SampleUtil.afterCreateSample();
    }

    @Override
    public void beforeRun() {
        SampleUtil.beforeCreateSample();
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

The use of the Environment is through *@GivenEnvironment* annotation. Its name was not given lightly, it was thought to lead naturally to the concept of *_[BDD](http://en.wikipedia.org/wiki/Behavior-driven_development)_* (_behavior driven development_) in which case it would be the abstraction of what you need to have as initial structure for your test.

The annotation @GivenEnvironment tells which structure will be executed before the test method. In the case of the annotation be in a test class, all tests that are NOT annotated with *@GivenEnvironment* or *@IgnoreEnvironment* will be executed after the execution of the Environment structure.

The GivenEnvironment annotation supports two basic uses.

The first use you only have to pass as parameter the Environment class which has a run() method, structure Environment per class.

Its use in a test case would be as follows:

```java
public class Sample {

   @Test
   @GivenEnvironment(EnvironmentSample.class)
   public void method() {
      org.junit.Assert.assertFalse(SampleUtil.findAll().isEmpty());
   }

}
```

or 

```java
@GivenEnvironment(EnvironmentSample.class)
public class Sample {

   @Test
   public void method() {
      org.junit.Assert.assertFalse(SampleUtil.findAll().isEmpty());
   }

}
```

The second use you have to pass as parameter the Environment class and the Environment method, structure Environment per method.

Its use in a test case would be as follows:

```java
public class Sample {

   @Test
   @GivenEnvironment(value=EnvironmentSample.class, environmentName="myEnvironmentMethodNameOneSample")
   public void method() {
      org.junit.Assert.assertFalse(SampleUtil.findAll().isEmpty());
   }

}
```

or

```java
@GivenEnvironment(value=EnvironmentSample.class, environmentName="myEnvironmentMethodNameOneSample")
public class Sample {

   @Test
   public void method() {
      org.junit.Assert.assertFalse(SampleUtil.findAll().isEmpty());
   }

}
```

### @IgnoreEnvironment annotation

The *IgnoreEnvironment* annotation tells the test runner that it should ignore the Environment annotated in the implementing class.

Its use in a test case would be as follows:

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

## Creating Environments hierarchically

It is possible to build a structure of hierarchical environments. You only need annotate the Environment method with @GivenEnvironment passing the Environment you want as a parent.

Its use in a Structure of environment per method would be as follows:

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

Its use in a Structure of environment per class would be as follows:

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

## Custom structure

It is often necessary to do some settings before running each Environment. Now what?

No problem, you can customize a execution before each environment and after the test execution. But how can I do this customization?

It is only necessary to create a *Rule* and a *Statement* extending *EnvironmentStatement*.

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
      // I call what I want to do before running the Environment here!
      super.before(); // This call executes the environment
   }

   @Override
   protected void after() {
      // I call what I want to do after running the Test here!
   }

}
```

Its use in a test case would be as follows:

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

In this case the execution flow is as follows:

```java
br.com.lemao.environment.MyStatement.before()
br.com.lemao.environment.SampleEnvironment.run()
br.com.lemao.environment.Sample.sampleTest()
br.com.lemao.environment.MyStatement.after()
```

## You must also see

* *_[SimulaTest - Simulatest Test Harness Framework](https://github.com/gabrielsuch/simulatest)_*
* *_[Fixture Factory](https://github.com/six2six/fixture-factory)_*
* *_[DbUnit](http://dbunit.sourceforge.net/)_*
