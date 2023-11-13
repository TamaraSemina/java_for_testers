package ru.stqa.mantis.model;

public record MantisUserData(String username, String password, String realname, String email) {
    public MantisUserData()  {
        this("", "", "", "");
    }

    public MantisUserData withUsername(String username) {
        return new MantisUserData(username, this.password, this.realname, this.email);
    }

    public MantisUserData withPassword(String password) {
        return new MantisUserData(this.username, password, this.realname, this.email);
    }

    public MantisUserData withRealName(String realname) {
        return new MantisUserData(this.username, this.password, realname, this.email);
    }
    public MantisUserData withEmail(String email) {
        return new MantisUserData(this.username, this.password, this.realname, email);
    }
}
