package INT222.Controllers;


import INT222.Exceptions.*;
import INT222.Models.Colors;
import INT222.Repositories.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://20.212.33.246/")
@RequestMapping("/color")
public class ColorController {
    @Autowired
    private ColorRepository colorRepository;


    @GetMapping("/list")
    public List<Colors> getColor(){
        return colorRepository.findAll();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('Admin')")
    public Colors addColor(@RequestBody Colors colors) {


        if (colorRepository.existsColorsByLabel(colors.getLabel())) {
            throw new SameColorNameException(colors.getLabel());

        }if(colorRepository.existsColorsByHexCode(colors.getHexCode())){
            throw new SameColorHexException(colors.getHexCode());
        }else if(colorRepository.findTopByOrderByIdDesc() == null){
            colors.setId(1);
            colorRepository.save(colors);
            return colors;
        }else

            colors.setId(colorRepository.findTopByOrderByIdDesc().getId()+1);
            colorRepository.save(colors);
        return colors;


    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('Admin')")
    public void deleteProduct(@PathVariable long id) {
        if (this.colorRepository.existsById(id)) {
            this.colorRepository.deleteById(id);
        } else
            throw new NotFoundColorIdException(id);
    }


}
