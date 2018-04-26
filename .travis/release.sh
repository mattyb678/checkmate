#!/usr/bin/env bash

set -e

if [ ! -z "$TRAVIS_TAG" ]
then
    echo "on a tag -> set pom.xml <version> to $TRAVIS_TAG"
    mvn -B --settings .travis/settings.xml -Prelease release:prepare -DreleaseVersion=$TRAVIS_TAG

    if [ ! -z "$TRAVIS" -a -f "$HOME/.gnupg" ]; then
        shred -v ~/.gnupg/*
        rm -rf ~/.gnupg
    fi

    source .travis/gpg.sh

    mvn -B --settings .travis/settings.xml -Prelease release:perform


    if [ ! -z "$TRAVIS" ]; then
        shred -v ~/.gnupg/*
        rm -rf ~/.gnupg
    fi
else
    echo "not on a tag -> keep snapshot version in pom.xml"
fi