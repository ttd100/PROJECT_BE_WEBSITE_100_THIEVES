package soixam.service.size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soixam.model.Size;
import soixam.repository.ISizeRepository;

import java.util.List;
import java.util.Optional;
@Service
public class SizeServiceIMPL implements ISizeService{
    @Autowired
    private ISizeRepository sizeRepository;
    @Override
    public List<Size> findAll() {
        return sizeRepository.findAll();
    }

    @Override
    public Optional<Size> findById(Long id) {
        return sizeRepository.findById(id);
    }

    @Override
    public Size save(Size size) {
        return sizeRepository.save(size);
    }

    @Override
    public void deleteById(Long id) {
        sizeRepository.deleteById(id);
    }
}
