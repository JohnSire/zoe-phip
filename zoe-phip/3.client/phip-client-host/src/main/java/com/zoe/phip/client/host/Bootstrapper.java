package com.zoe.phip.client.host;

import com.zoe.phip.infrastructure.bean.IBootstrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zengjiyang on 2016/4/25.
 */
@Repository("Bootstrapper")
public class Bootstrapper {

    @Autowired(required = true)
    private List<IBootstrapper> bootstrappers;

    public void start() {
        if (bootstrappers == null) {
            bootstrappers = new ArrayList<>();
            bootstrappers.add(new com.zoe.phip.web.bootstrapper.Bootstrapper());
            bootstrappers.add(new com.zoe.phip.register.bootstrapper.Bootstrapper());
        }

        Collections.sort(bootstrappers, (a, b) -> a.getExecutionOrder() - b.getExecutionOrder());
        bootstrappers.forEach(e -> {
            e.startService();
        });
    }

}
