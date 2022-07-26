package br.com.cleo.AluraAPi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cleo.AluraAPi.config.validacao.ErroDetails;
import br.com.cleo.AluraAPi.config.validacao.LanguageErroDetails;
import br.com.cleo.AluraAPi.form.LanguageForm;
import br.com.cleo.AluraAPi.model.Language;
import br.com.cleo.AluraAPi.repository.LanguageRepository;
import br.com.cleo.AluraAPi.utils.UpdateObjectValues;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/linguagens")

@Tag(name = "Linguages De Programação", description = "Consulte, Cadastre, Edite ou Exclua Linguages De Programação")
public class LanguageController {

    @Autowired
    LanguageRepository languageRepository;

    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso", content = @Content(schema = @Schema(implementation = Language.class)))
    @ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content(schema = @Schema(hidden = true)))
    @Operation(summary = "Listar Linguagens", description = "Lista Todas As Linguagens", method = "GET")
    @GetMapping
    public ResponseEntity<List<Language>> listAllLanguage() {
        return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin", "*")
                .body(languageRepository.findAll(Sort.by("ranking")));
    }

    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso", content = @Content(schema = @Schema(implementation = Language.class)))
    @ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "404", description = "Linguagem não encontrada", content = @Content(schema = @Schema(implementation = LanguageErroDetails.class)))
    @Operation(summary = "Buscar Lingagem Por ID", description = "Busca uma Linguagem Por ID", method = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<Object> findLanguageById(@PathVariable String id) {
        return ResponseEntity.ok(languageRepository.findByIdOrThrow(id));
    }

    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso", content = @Content(schema = @Schema(implementation = Language.class)))
    @ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content(schema = @Schema(implementation = ErroDetails.class)))
    @Operation(summary = "Cadastrar Lingagem", description = "Cadastra uma Linguagem", method = "POST")
    @PostMapping
    public ResponseEntity<Language> createLanguage(@RequestBody LanguageForm languagefForm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(languageRepository.save(languagefForm.toLanguage()));
    }

    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso", content = @Content(schema = @Schema(implementation = Language.class)))
    @ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content(schema = @Schema(implementation = ErroDetails.class)))
    @ApiResponse(responseCode = "404", description = "Linguagem não encontrada", content = @Content(schema = @Schema(implementation = LanguageErroDetails.class)))
    @Operation(summary = "Atualizar Lingagem", description = "Atualiza uma Linguagem", method = "PUT")
    @PutMapping("/{id}")
    public ResponseEntity<Language> updateLanguage(@PathVariable String id,
            @Valid @RequestBody LanguageForm languagefForm) {
        Language language = languageRepository.findByIdOrThrow(id);
        return ResponseEntity.status(HttpStatus.OK).body(languageRepository.save(language));
    }

    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso", content = @Content(schema = @Schema(implementation = Language.class)))
    @ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content(schema = @Schema(implementation = ErroDetails.class)))
    @ApiResponse(responseCode = "404", description = "Linguagem não encontrada", content = @Content(schema = @Schema(implementation = LanguageErroDetails.class)))
    @Operation(summary = "Atualizar Parcialmente", description = "Atualiza Parcialmente Uma Linguagem", method = "PATCH")
    @PatchMapping("/{id}")
    public ResponseEntity<Object> updatePartialLanguage(@PathVariable String id,
            @RequestBody LanguageForm languagefForm) {
        Language language = languageRepository.findByIdOrThrow(id);
        UpdateObjectValues updateObjectValues = new UpdateObjectValues();
        updateObjectValues.updateObject(languagefForm, language);
        return ResponseEntity.status(HttpStatus.OK).body(languageRepository.save(language));

    }

    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso", content = @Content(schema = @Schema(example = "Linguagem deletada com sucesso!", type = "string")))
    @ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content(schema = @Schema(implementation = ErroDetails.class)))
    @ApiResponse(responseCode = "404", description = "Linguagem não encontrada", content = @Content(schema = @Schema(implementation = LanguageErroDetails.class)))
    @Operation(summary = "Deletar Lingagem", description = "Deleta uma Linguagem", method = "DELETE")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletLanguage(@PathVariable String id) {
        Language language = languageRepository.findByIdOrThrow(id);
        languageRepository.delete(language);
        return ResponseEntity.status(HttpStatus.OK).body("Linguagem deletada com sucesso!");
    }
}
