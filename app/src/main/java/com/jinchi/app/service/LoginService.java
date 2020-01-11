package com.jinchi.app.service;

import com.jinchi.app.domain.BasicAppInfo;
import com.jinchi.app.dto.LoginAuthDTO;
import com.jinchi.app.dto.LoginDTO;
import com.jinchi.app.dto.PasswordChangeDTO;

public interface LoginService {

    LoginAuthDTO login(LoginDTO loginDTO,String url);

    BasicAppInfo versionInfo(String version);

    String pwdChange(PasswordChangeDTO passwordChangeDTO,String url);
}
