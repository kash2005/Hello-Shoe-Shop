package lk.ijse.helloshoeshop.service.IMPL;

import jakarta.transaction.Transactional;
import lk.ijse.helloshoeshop.conversion.ConversionData;
import lk.ijse.helloshoeshop.dto.VarietyDTO;
import lk.ijse.helloshoeshop.exception.NotFoundException;
import lk.ijse.helloshoeshop.repository.VarietyServiceDAO;
import lk.ijse.helloshoeshop.service.VarietyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VarietyServiceImpl implements VarietyService {
    final private VarietyServiceDAO varietyServiceDAO;
    final private ConversionData convertData;
    @Override
    public void saveVariety(VarietyDTO varietyDTO) {
        varietyServiceDAO.save(convertData.convertToVarietyEntity(varietyDTO));
    }

    @Override
    public void updateVariety(String id, VarietyDTO varietyDTO) {
        if(!varietyServiceDAO.existsById(id)){throw new NotFoundException("Variety Not Found");}
        varietyServiceDAO.save(convertData.convertToVarietyEntity(varietyDTO));
    }

    @Override
    public void deleteVariety(String id) {
        varietyServiceDAO.deleteById(id);
    }

    @Override
    public VarietyDTO getVariety(String id) {
        return null;
    }

    @Override
    public List<VarietyDTO> getAllVariety() {
        return convertData.convertToVarietyDTOList(varietyServiceDAO.findAll());
    }
}
