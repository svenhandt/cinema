package org.cinema.integrationtest.creator;

import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.servicelayer.model.ModelService;

public enum IntegrationTestLanguageModelCreator {
    INSTANCE;

    private LanguageModel languageModelGerman;

    private ModelService modelService;

    private IntegrationTestLanguageModelCreator() {
        modelService = Registry.getApplicationContext().getBean(ModelService.class);
    }

    public void createLanguageModelGerman() {
        if(languageModelGerman == null) {
            languageModelGerman = modelService.create(LanguageModel.class);
            languageModelGerman.setIsocode("de");
            languageModelGerman.setName("Deutsch");
            modelService.save(languageModelGerman);
        }
    }

}
