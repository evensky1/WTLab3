package com.poit.archive.dao.impl;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.poit.archive.criteria.Criteria;
import com.poit.archive.dao.DossierDAO;
import com.poit.archive.dao.exception.DAOException;
import com.poit.archive.dao.wrapper.DossierWrapper;
import com.poit.archive.entity.Dossier;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DossierDAOImpl implements DossierDAO {

    private static final String PATH
        = "C:\\Users\\fromt\\IdeaSpace\\WTLab3\\src\\main\\resources\\datafolder\\dossier_db.xml";

    @Override
    public List<Dossier> find(Criteria criteria) throws DAOException {
        try (var fileInputStream = new FileInputStream(PATH)) {

            var mapper = new XmlMapper();
            var xmlString = new String(fileInputStream.readAllBytes());
            var users = mapper.readValue(xmlString, DossierWrapper.class).getDossiers();

            var criteriaMap = criteria.getCriteriaMap();
            var result = new ArrayList<Dossier>();

            if (criteriaMap.isEmpty()) {
                return users;
            } else {
                criteriaMap.forEach((key, value) -> result.addAll(
                    users.stream().filter(p -> {
                        try {
                            var field = p.getClass().getDeclaredField(key);
                            field.setAccessible(true);
                            return field.get(p).equals(value);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }).toList()
                ));
            }
            return result;
        } catch (IOException e) {
            throw new DAOException(e);
        }
    }

    public void save(Dossier dossier) throws DAOException {
        try (var fileOutputStream = new FileOutputStream(PATH);) {
            var mapper = new XmlMapper();
            var url = new File(PATH).toURI().toURL();
            var dossierWrapper = mapper.readValue(url, DossierWrapper.class);
            dossierWrapper.add(dossier);
            fileOutputStream.write(mapper.writeValueAsBytes(dossierWrapper));
        } catch (IOException e) {
            throw new DAOException(e.getMessage());
        }
    }
}
