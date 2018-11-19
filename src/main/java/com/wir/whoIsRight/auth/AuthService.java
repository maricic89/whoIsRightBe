package com.wir.whoIsRight.auth;

import java.net.URI;

public interface AuthService {

    String authenticateUser(Login login);

    URI registerUser(SignUp signUp);

}
