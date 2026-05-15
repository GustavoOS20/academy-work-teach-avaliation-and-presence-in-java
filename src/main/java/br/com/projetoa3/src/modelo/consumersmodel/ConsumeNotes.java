package br.com.projetoa3.src.modelo.consumersmodel;

import br.com.projetoa3.src.modelo.interfaces.INotes;
import br.com.projetoa3.src.modelo.records.Notes;

public class ConsumeNotes {
    INotes iNotes;
    public ConsumeNotes(INotes iNotes) {
        this.iNotes = iNotes;
    }

    public void consumeAddNotes(String ra, Notes notes){
        this.iNotes.addNotes(ra, notes);
    }
}
