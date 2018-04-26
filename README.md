# CheckMate [![Build Status](https://travis-ci.org/mattyb678/checkmate.svg?branch=master)](https://travis-ci.org/mattyb678/checkmate) [![Coverage Status](https://coveralls.io/repos/github/mattyb678/checkmate/badge.svg?branch=master&service=github)](https://coveralls.io/github/mattyb678/checkmate?branch=master) [![GitHub license](https://img.shields.io/github/license/mattyb678/checkmate.svg)](https://github.com/mattyb678/checkmate/blob/master/LICENSE)

A flexible, fluent validation library for Java

---

### Usage


```java
CheckMate.check()
    .notEmpty("").withException(SomeAppSpecificException.class)
    .validate();
```