package org.mattyb.checkmate.checker;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mattyb.checkmate.checker.NotEmptyCheckers.collection;

class NotEmptyCheckersTest {

    @Test
    public void testCollection_Null() {
        assertThat(collection.test(null), is(true));
    }

    @Test
    public void testCollection_ListEmpty() {
        assertThat(collection.test(Collections.emptyList()), is(true));
    }

    @Test
    public void testCollection_SetSingle() {
        assertThat(collection.test(Collections.singleton("Hey")), is(false));
    }

    @Test
    public void testCollection_Message() {
        assertThat(collection.getExceptionMessage(Collections.emptyList()), is("The validated collection is empty"));
    }
}