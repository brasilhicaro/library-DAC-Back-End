package br.edu.ifpb.hicarobrasil.dac.library.business.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.hicarobrasil.dac.library.business.dto.ReserveDTO;
import br.edu.ifpb.hicarobrasil.dac.library.business.services.ConvertService;
import br.edu.ifpb.hicarobrasil.dac.library.business.services.ReserveService;
import br.edu.ifpb.hicarobrasil.dac.library.model.entity.Reserve;
import br.edu.ifpb.hicarobrasil.dac.library.model.repository.ReserveRepository;

@Service
public class ReserveServiceImpl implements ReserveService{

    @Autowired
    private ReserveRepository reserveRepository;
    private ConvertService convertService;
    @Override
    public ReserveDTO save(ReserveDTO reserveDTO) {
        
       return convertService.convertToReserveDTO(reserveRepository.save(convertService.convertToReserve(reserveDTO)));
    }

    @Override
    public ReserveDTO update(ReserveDTO reserveDTO, Long id) {
        Reserve reserve = reserveRepository.findById(id).get();
        return new ReserveDTO(reserve.getName(), reserve.getLoan(), reserve.getDevolution(), reserve.getBookID());
    }

    @Override
    public ReserveDTO delete(ReserveDTO reserveDTO) {
        reserveRepository.delete(convertService.convertToReserve(reserveDTO));
        return reserveDTO;
    }

    @Override
    public ReserveDTO deleteById(Long id) {
        Reserve reserve = reserveRepository.findById(id).get();
        reserveRepository.deleteById(id);
        return convertService.convertToReserveDTO(reserve);
    }

    @Override
    public ReserveDTO findByID(Long id) {
        Reserve reserve = reserveRepository.findById(id).get();
        return new ReserveDTO(reserve.getName(), reserve.getLoan(), reserve.getDevolution(), reserve.getBookID());
    }

    @Override
    public List<ReserveDTO> list() {
        List<Reserve> reserves = reserveRepository.findAll();
        return convertService.convertToReserveDTOList(reserves);
    }
    
}