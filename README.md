# CheckMate [![Build Status](https://travis-ci.org/mattyb678/checkmate.svg?branch=master)](https://travis-ci.org/mattyb678/checkmate) [![Coveralls github](https://img.shields.io/coveralls/github/mattyb678/checkmate.svg)](https://coveralls.io/github/mattyb678/checkmate) [![Maven Central](https://img.shields.io/maven-central/v/xyz.mattyb/checkmate-core.svg)](https://github.com/mattyb678/checkmate) [![GitHub license](https://img.shields.io/github/license/mattyb678/checkmate.svg)](https://github.com/mattyb678/checkmate/blob/master/LICENSE)

A flexible, fluent validation library for Java

---

### Motivation
A replacement for Apache commons-lang3 Validate, Guava Preconditions, and Spring Assert that uses a fluent API and is more flexible.

### Usage
By default most checks will throw an IllegalArgumentException. 
```java
CheckMate.check()
    .notEmpty("")
    .validate();
```
You can also specify what the default exception should be, it must be a RuntimeException, that has a constructor that takes a String.
```java
CheckMate.checkWithDefault(SomeAppSpecificException.class)
    .notEmpty(map)
    .notBlank(str)
    .validate();
```
An exception can be overridden on a check by check basis.
```java
CheckMate.check()
    .notNull(object).withException(SomeAppSpecificException.class)
    .notEmpty(map).withException(OtherAppException.class)
    .validate();
```
Overridden exceptions take priority over the default exception.
```java
CheckMate.checkWithDefault(SomeAppSpecificException.class)
    .notEmpty(map)
    .notBlank(str).withException(OtherAppException.class)
    .validate()
```
The message in the exception can also be changed.
```java
CheckMate.check()
    .notEmpty(map).withMessage("The map should not be empty")
    .notBlank(str)
    .validate();
```
Sometimes, though, you don't want to throw an exception.
So, there are two methods `anyInvalid` and `allInvalid` that will let you know if any or all of the checks fail. 
```java
boolean anyInvalid = CheckMate.check()
    .notNull(object)
    .notEmpty(someString)
    .anyInvalid();
```
```java
boolean allInvalid = CheckMate.check()
    .notNull(object)
    .notEmpty(someString)
    .allInvalid();
```

CheckMate has the ability to validate indexes for Strings, Arrays, and Collections
```java
CheckMate.check()
    .isIndex(3).validIn(Arrays.asList("one", "two", "three", "four", "five", "six"))
    .validate();
```

You can also check if a number is in a range, by default start inclusive and end exclusive.
```java
CheckMate.check()
    .intValue(7).between(5).and(100)
    .validate();
```

But there is also a way to specify that the end should be inclusive
```java
CheckMate.check()
    .intValue(100).between(5).and(100).inclusive()
    .validate();
```

You can also check if an expression is true or false
```java
CheckMate.check()
    .is(value > 0).truthy()
    .is(length == 0).falsy()
    .validate();
```

"But what if I liked how commons-lang Validate throws NullPointerExceptions when things are null?"
Fear not, CheckMate supports that!!
```java
CheckMate.check(CheckMate.Option.THROW_NPE)
    .notEmpty(null)
    .notBlank(str)
    .validate();
```
This will throw an NPE, instead of an IllegalArgumentException.

### Installation
The library is available at [Maven Central](https://search.maven.org/#search%7Cga%7C1%7Ca%3A%22checkmate-core%22), simply add this to your `pom.xml`
```xml
<dependency>
    <groupId>xyz.mattyb</groupId>
    <artifactId>checkmate-core</artifactId>
    <version>0.0.4</version>
</dependency>
```
