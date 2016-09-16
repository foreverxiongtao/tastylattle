package com.desperado.tastylattle.mvp.model.base;

import com.desperado.tastylattle.global.GlobalService;
import com.desperado.tastylattle.global.LocalConstant;
import com.desperado.tastylattlelib.mvp.model.BaseModel;

/**
 * Created by Administrator on 2016/9/16.
 */
public class CustomerModel extends BaseModel<GlobalService> {
    @Override
    public String provideBaseurl() {
        return LocalConstant.BASE_URL;
    }

    @Override
    public Class<GlobalService> provoideInstance() {
        return GlobalService.class;
    }
}
