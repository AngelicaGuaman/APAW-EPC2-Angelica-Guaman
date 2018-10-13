package es.upm.miw.apaw.api.daos.memory;

import es.upm.miw.apaw.api.daos.JuryDao;
import es.upm.miw.apaw.api.entities.Jury;

import java.util.HashMap;

public class JuryDaoMemory extends GenericDaoMemory<Jury> implements JuryDao {

    public JuryDaoMemory() {
        super(new HashMap<>());
    }

    @Override
    public String getId(Jury jury) {
        return jury.getId();
    }

    @Override
    public void setId(Jury jury, String id) {
        jury.setId(id);
    }
}
