package com.example.gates.Services;

import com.example.gates.Models.Gates;
import com.example.gates.Models.gatesType;
import com.example.gates.Repositories.GatesRepository;
import com.example.gates.Repositories.Gates_typeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GatesService{
    GatesRepository gatesRepository;
    Gates_typeRepository gatesTypeRepository;

    public void save(Gates gates){
        gates.setStatus(true);
        gatesRepository.save(gates);
    }
    public void save(gatesType gatesType){
        gatesTypeRepository.save(gatesType);
    }

    public List<Gates> findAll(){
        return gatesRepository.findAll();
    }
    public List<Gates> findActive(){return gatesRepository.findGatesByStatus(true);}
    public void setDead(int id){
        Gates gates = gatesRepository.findById(id).get();
        gates.setStatus(false);
        gatesRepository.save(gates);
    }
    public Optional<Gates> findById(int id){
        return gatesRepository.findById(id);
    }

    public gatesType findTypeById(int id){return gatesTypeRepository.findById(id).orElse(null);}

    public void delete(int id){gatesRepository.deleteById(id);}
    public List<gatesType> findAllGateTypes(){
        return gatesTypeRepository.findAll();
    }



}
