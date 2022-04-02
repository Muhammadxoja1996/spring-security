package uz.ilm.security.service;

import org.springframework.stereotype.Component;
import uz.ilm.security.model.Roles;


@Component("AccessHandler")
public class AccessHandler {

    public boolean hasPermission(Roles role) {
        return 5 > 3;
    }
}
