package com.example.gates.Services;

import com.example.gates.Models.Gates;
import com.example.gates.Models.Gates_type;
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
        gatesRepository.save(gates);
    }
    public List<Gates> findAll(){
        return gatesRepository.findAll();
    }
    public Optional<Gates> findById(int id){
        return gatesRepository.findById(id);
    }

    public Gates_type findTypeById(int id){return gatesTypeRepository.findById(id).orElse(null);}

    public void delete(int id){gatesRepository.deleteById(id);}
    public List<Gates_type> findAllGateTypes(){
        return gatesTypeRepository.findAll();
    }



}
