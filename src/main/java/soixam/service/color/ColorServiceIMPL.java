package soixam.service.color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soixam.model.Color;
import soixam.repository.IColorRepository;

import java.util.List;
import java.util.Optional;
@Service
public class ColorServiceIMPL implements IColorService{
    @Autowired
    private IColorRepository colorRepository;
    @Override
    public List<Color> findAll() {
        return colorRepository.findAll();
    }

    @Override
    public Optional<Color> findById(Long id) {
        return colorRepository.findById(id);
    }

    @Override
    public Color save(Color color) {
        return colorRepository.save(color);
    }

    @Override
    public void deleteById(Long id) {
        colorRepository.deleteById(id);
    }
}
