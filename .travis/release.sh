#!/usr/bin/env bash

set -e

if [ ! -z "$TRAVIS_TAG" ]
then

    echo "on a tag -> set pom.xml <version> to $TRAVIS_TAG"
    if [ ! -z "$TRAVIS" -a -f "$HOME/.gnupg" ]; then
        echo "Removing gpg dir"
        shred -v ~/.gnupg/*
        rm -rf ~/.gnupg
    fi

    echo "Creating gpg key"
    source .travis/gpg.sh

    mvn -B --settings .travis/settings.xml -Prelease release:prepare -DreleaseVersion=$TRAVIS_TAG -DskipTests=true

    mvn -B --settings .travis/settings.xml -Prelease release:perform -DskipTests=true


    if [ ! -z "$TRAVIS" ]; then
        shred -v ~/.gnupg/*
        rm -rf ~/.gnupg
    fi
else
    echo "not on a tag -> keep snapshot version in pom.xml"
fi