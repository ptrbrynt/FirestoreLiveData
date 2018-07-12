package com.ptrbrynt.firestorelivedata;

import android.support.test.runner.AndroidJUnit4;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;

@RunWith(AndroidJUnit4.class)
public class FirebaseAuthLiveDataTest {

    private final static FirebaseOptions options = new FirebaseOptions.Builder()
            .setApplicationId("com.ptrbrynt.firestorelivedata")
            .setProjectId("firestorelivedatatest")
            .setApiKey("AIzaSyDYF4ehojGr5nDWOvYRdAShUq06aIFwItU")
            .build();

    @BeforeClass
    public static void initializeFirebase() {
        FirebaseApp.initializeApp(getTargetContext(), options);
    }

    @Test
    public void initialiseFirebaseAuthLiveData() {
        FirebaseAuthLiveData currentUser = new FirebaseAuthLiveData(FirebaseAuth.getInstance());

        currentUser.onActive();
    }

}
