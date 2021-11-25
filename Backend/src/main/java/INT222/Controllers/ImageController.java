package INT222.Controllers;

import INT222.Exceptions.NotFoundImageException;
import INT222.Exceptions.SameImageException;
import INT222.Models.Images;
import INT222.Repositories.ImageRepository;
import INT222.Services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://20.212.33.246/")
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    private final Path path = Paths.get("images");

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/get/{id:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable("id")String id) throws IOException {
        Path file = path.resolve(id);
        Resource resource = new UrlResource(file.toUri());
        Resource file1 = resource;
        System.out.println(id + " exist: "  + file1.exists());

        if(file1.exists() || file1.isReadable()){
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(file1);
        }else
            throw new NotFoundImageException(id);
    }


    @PostMapping("/add")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Object> fileUpload(@RequestParam("orange") MultipartFile file)throws IOException {
        Path file1 = path.resolve(file.getOriginalFilename());
        Resource resource = new UrlResource(file1.toUri());
        File myFile = new File(resource.getURI());
        if (myFile.exists()) {
            throw new SameImageException(file.getOriginalFilename());

        } else
            myFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(myFile);
        fos.write(file.getBytes());
        fos.close();
//        long idImage = imageRepository.findTopByOrderByIdDesc().getId()+1;
//        String source= file.getOriginalFilename();
//        String label = file.getName();
//        long productId = id;
//        Images image = new Images(idImage,source,label,productId);
//        imageRepository.save(image);
        return new ResponseEntity<Object>("The File Uploaded Successfully", HttpStatus.OK);


    }



    @PutMapping("/update/{id:.+}")
    @PreAuthorize("hasRole('Admin')")
    public void changeImage(@RequestParam("orange")MultipartFile file,@PathVariable("id")String id)throws IOException {
        boolean b = false;
        Path file1 = path.resolve(id);
        Path file2 = path.resolve(file.getOriginalFilename());
        File newFile = new File(file2.toUri());
        File oldFile = new File(file1.toUri());

        if(oldFile.exists()) {
            b = false;
            if(newFile.exists()) {
                b = true;
                if(oldFile.equals(newFile)){
                    b = false;
                }
            }
        }if(b == false){
            oldFile.delete();
            newFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(newFile);
            fos.write(file.getBytes());
            fos.close();
            System.out.println("Success to update file");
        }if(b == true){
            throw new SameImageException(id);
        }

    }

    @DeleteMapping("/delete/{id:.+}")
    @PreAuthorize("hasRole('Admin')")
    public void deleteImage(@PathVariable("id")String id) throws MalformedURLException {
        Path file = path.resolve(id);
        File myFile = new File(file.toUri());
        if(myFile.exists()) {
            myFile.delete();
        } else throw new NotFoundImageException(id);

        System.out.println(id + " exist: " + myFile.exists());
    }

    @PostMapping("/uploadMultipleFiles")
    @PreAuthorize("hasRole('Admin')")
    public List<Images> uploadMultipleFiles(@RequestParam("orange") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());

    }

    @PostMapping("/uploadFile")
    @PreAuthorize("hasRole('Admin')")
    public Images uploadFile(@RequestParam("orange") MultipartFile file) {

        String fileName = fileStorageService.storeFile(file);
        long id = imageRepository.findAll().size();
        return imageRepository.getById(id);
    }

}
