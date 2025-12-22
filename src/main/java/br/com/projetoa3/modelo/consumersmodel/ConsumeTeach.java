package br.com.projetoa3.modelo.consumersmodel;

import br.com.projetoa3.modelo.interfaces.ITeach;
import br.com.projetoa3.modelo.records.Teach;

public class ConsumeTeach {
    ITeach iTeach;
    public ConsumeTeach(ITeach iTeach){
        this.iTeach = iTeach;
    }

    public void consumeAddTeach(Teach teach){
        this.iTeach.addTeach(teach);
    }
}
