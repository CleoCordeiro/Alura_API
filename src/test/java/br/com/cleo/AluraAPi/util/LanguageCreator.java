package br.com.cleo.AluraAPi.util;

import br.com.cleo.AluraAPi.form.LanguageForm;
import br.com.cleo.AluraAPi.model.Language;

public class LanguageCreator {

    public static LanguageForm LanguageToBeSave() {
        LanguageForm language = new LanguageForm();
        language.setName("Java");
        language.setDescription(
                "Java is a programming language");
        language.setImage("https://www.w3schools.com/images/java.jpg");
        language.setRanking(1);
        return language;
    }

    public static Language LanguageValid() {
        Language language = new Language();
        language.setId("1");
        language.setName("Java");
        language.setDescription(
                "Java is a programming language");
        language.setImage("https://www.w3schools.com/images/java.jpg");
        language.setRanking(1);
        return language;
    }

    public static Language LanguageToBeUpdate() {
        Language language = new Language();
        language.setId("1");
        language.setName("Java1");
        language.setDescription(
                "Java is a programming language");
        language.setImage("https://www.w3schools.com/images/java.jpg");
        language.setRanking(1);
        return language;
    }
}
