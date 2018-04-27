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
This, however, can be replaced with any RuntimeException, that has a constructor that takes a String.
```java
CheckMate.check()
    .notNull(object).withException(SomeAppSpecificException.class)
    .validate();
```
The message in the exception can also be changed.
```java
CheckMate.check()
    .notEmpty(map).withMessage("The map should not be empty")
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