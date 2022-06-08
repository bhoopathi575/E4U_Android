package com.example.myapplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.manipulation.Ordering;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import com.google.firebase.database.core.Context;

import net.bytebuddy.implementation.Implementation;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void userLogin(){

    }


}


//@RunWith(MockitoJUnitRunner.class)
//public class ExampleUnitTest {
//
//    private static final String FAKE_STRING = "Login was successful";
//
//    @Mock
//    Context MockContext;
//
//    @Test
//    public void readStringFromContext_LocalizedString() {
//
//        LoginActivity myObjectUnderTest = new LoginActivity(MockContext);
//
//        // ...when the string is returned from the object under test...
//        String result = myObjectUnderTest.validate("user","user");
//
//        // ...then the result should be the expected one.
//        assertThat(result, is(FAKE_STRING));
//    }
//
//}