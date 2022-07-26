package br.com.cleo.AluraAPi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import br.com.cleo.AluraAPi.model.Language;

public interface LanguageRepository extends MongoRepository<Language, String> {

    public default Language findByIdOrThrow(String id) {
        return this.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(
                        "Não Foi Possível Encontrar A Linguagem, Verifique O ID Informado",
                        HttpStatus.NOT_FOUND,
                        "Linguagem não Encontrada", null, null, null));
    }

}
