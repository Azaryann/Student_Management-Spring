package am.azaryan.security;

import am.azaryan.entity.User;
import lombok.Getter;
import org.springframework.security.core.authority.AuthorityUtils;
@Getter
public class SpringUser extends org.springframework.security.core.userdetails.User {

    private final User user;

    public  SpringUser(User user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getUserType().name()));
        this.user = user;
    }
}