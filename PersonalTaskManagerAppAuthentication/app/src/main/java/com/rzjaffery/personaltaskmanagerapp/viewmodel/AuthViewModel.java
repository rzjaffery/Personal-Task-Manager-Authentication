package com.rzjaffery.personaltaskmanagerapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;
import com.rzjaffery.personaltaskmanagerapp.repository.FirebaseRepository;

public class AuthViewModel extends ViewModel {
    private final FirebaseRepository repository = new FirebaseRepository();
    private final MutableLiveData<FirebaseUser> userLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();

    public void register(String name, String email, String password) {
        repository.registerUser(name, email, password, task -> {
            if (task.isSuccessful()) {
                userLiveData.setValue(repository.getCurrentUser());
            } else {
                errorLiveData.setValue(task.getException().getMessage());
            }
        });
    }

    public void login(String email, String password) {
        repository.loginUser(email, password, task -> {
            if (task.isSuccessful()) {
                userLiveData.setValue(repository.getCurrentUser());
            } else {
                errorLiveData.setValue(task.getException().getMessage());
            }
        });
    }

    public LiveData<FirebaseUser> getUser() {
        return userLiveData;
    }

    public LiveData<String> getError() {
        return errorLiveData;
    }
}
