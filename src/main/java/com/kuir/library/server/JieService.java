package com.kuir.library.server;

import com.kuir.library.bean.Jieshu;
import com.kuir.library.dao.Jie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JieService {
    @Autowired
    Jie jie;
    public Jieshu save(Jieshu jieshu){
        return jie.save(jieshu);
    }
}
