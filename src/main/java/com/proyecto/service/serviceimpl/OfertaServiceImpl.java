package com.proyecto.service.serviceimpl;

import com.proyecto.dao.OfertaDao;
import com.proyecto.domain.Oferta;
import com.proyecto.service.OfertaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OfertaServiceImpl implements OfertaService{
    
    @Autowired
    private OfertaDao ofertaDao;

    @Override
    public List<Oferta> getOfertas(boolean activo) {
        var ofertas = ofertaDao.findAll();
        
     if (activo) {
            ofertas.removeIf(e -> !e.getActivo());
        }
                return ofertas;
    }
  
    @Override
    @Transactional(readOnly = true)
    public Oferta getOferta(Oferta oferta) {
        return ofertaDao.findById(oferta.getIdOferta()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Oferta oferta) {
        ofertaDao.save(oferta);
    }

    @Override
    @Transactional
    public void delete(Oferta oferta) {
        ofertaDao.delete(oferta);
    } 
    
}
