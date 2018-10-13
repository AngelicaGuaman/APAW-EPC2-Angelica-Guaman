package es.upm.miw.apaw.api.businessController;

import es.upm.miw.apaw.api.daos.DaoFactory;
import es.upm.miw.apaw.api.dtos.JuryDto;
import es.upm.miw.apaw.api.entities.Jury;

public class JuryBusinessController {

    public String create(JuryDto juryDto) {
        Jury jury = new Jury(juryDto.getNick(), null);
        DaoFactory.getFactory().getJuryDao().save(jury);
        return jury.getId();
    }
}
