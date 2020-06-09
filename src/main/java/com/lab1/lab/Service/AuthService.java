package com.lab1.lab.Service;

import com.lab1.lab.Model.User;

public interface AuthService {
    User getCurrentUser();
    String getCurrentUserId();
}
