package ru.makarov.recipesapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.makarov.recipesapp.model.Ingredient;
import ru.makarov.recipesapp.services.FileService;

import java.io.*;
import java.nio.file.Path;

@RestController
@RequestMapping("/files")
@Tag(name = "Файлы", description = "Работа с файлами")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/export/{nameOfType}")
    @Operation(
            summary = "Получение файла.",
            description = "Передаём название типа файла и скачиваем его"
    )
    @Parameters({
            @Parameter(
                    name = "nameOfType",
                    description = "Тип файла может принимать два занчени recipe или ingredient"
            )
    }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл получен.",
                    content = {
                            @Content(
                                    mediaType = ""
                            )
                    }
            )
    }
    )
    public ResponseEntity<InputStreamResource> downloadDataFile(@PathVariable String nameOfType) throws FileNotFoundException {

        File file = fileService.getFile(nameOfType);

        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = " + nameOfType + ".json")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadDataFile(@RequestParam MultipartFile file, @RequestParam String nameOfType) {

        Path path = fileService.getPath(nameOfType);

        fileService.cleanDataFile(path);
        File dataFile = fileService.getFile(nameOfType);

        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
