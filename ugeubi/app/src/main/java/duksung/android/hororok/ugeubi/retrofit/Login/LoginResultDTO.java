package duksung.android.hororok.ugeubi.retrofit.Login;

import com.google.gson.annotations.SerializedName;

public class LoginResultDTO {

    @SerializedName("tokens")
    public TokenDTO tokens;

    @SerializedName("user")
    public UserDTO user;
}
