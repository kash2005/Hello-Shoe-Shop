package lk.ijse.helloshoeshop.service.IMPL;

import lk.ijse.helloshoeshop.conversion.ConversionData;
import lk.ijse.helloshoeshop.dto.GenderDTO;
import lk.ijse.helloshoeshop.exception.NotFoundException;
import lk.ijse.helloshoeshop.repository.GenderServiceDAO;
import lk.ijse.helloshoeshop.service.GenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GenderServiceImpl implements GenderService {
    final private GenderServiceDAO genderServiceDAO;
    final private ConversionData conversionData;
    @Override
    public void saveGender(GenderDTO genderDTO) {
        genderServiceDAO.save(conversionData.covertToGenderEntity(genderDTO));
    }

    @Override
    public  List<GenderDTO> genderGetAll() {
        return conversionData.covertToGenderDTOList(genderServiceDAO.findAll());
    }

    @Override
    public void updateGender(String id, GenderDTO genderDTO) {
        if(genderServiceDAO.existsById(id)) throw new NotFoundException("Gender not found");
        genderServiceDAO.save(conversionData.covertToGenderEntity(genderDTO));
    }

    @Override
    public void deleteGender(String id) {
        genderServiceDAO.deleteById(id);
    }
}
