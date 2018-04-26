#!/usr/bin/env bash

set -e

if [ ! -z "$TRAVIS_TAG" ]
then
    echo "REAL branch $REAL_BRANCH"

    echo "on a tag -> set pom.xml <version> to $TRAVIS_TAG"
    if [ ! -z "$TRAVIS" -a -f "$HOME/.gnupg" ]; then
        echo "Removing gpg dir"
        shred -v ~/.gnupg/*
        rm -rf ~/.gnupg
    fi

    echo "Creating gpg key"
    source .travis/gpg.sh

    mvn -B org.codehaus.mojo:versions-maven-plugin:2.5:set -DnewVersion=$TRAVIS_TAG

    mvn -B deploy --settings .travis/settings.xml -DskipTests=true --update-snapshots -Prelease


    if [ ! -z "$TRAVIS" ]; then
        shred -v ~/.gnupg/*
        rm -rf ~/.gnupg
    fi
else
    echo "not on a tag -> keep snapshot version in pom.xml"
fi