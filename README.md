# CheckMate [![Build Status](https://travis-ci.org/mattyb678/checkmate.svg?branch=master)](https://travis-ci.org/mattyb678/checkmate) [![Coveralls github](https://img.shields.io/coveralls/github/mattyb678/checkmate.svg)](https://github.com/mattyb678/checkmate) [![GitHub license](https://img.shields.io/github/license/mattyb678/checkmate.svg)](https://github.com/mattyb678/checkmate/blob/master/LICENSE)

A flexible, fluent validation library for Java

---

### Usage


```java
CheckMate.check()
    .notEmpty("").withException(SomeAppSpecificException.class)
    .validate();
```