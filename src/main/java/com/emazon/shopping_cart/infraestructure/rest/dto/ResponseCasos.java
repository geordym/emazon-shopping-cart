package com.emazon.shopping_cart.infraestructure.rest.dto;

import com.emazon.shopping_cart.infraestructure.entities.TesterClassEntity;

import java.util.List;
import java.util.Map;

public class ResponseCasos {
    private Map<String, List<TesterClassEntity>> casosPorMessage;
    private Map<String, Integer> casosConteoPorMessage;


    private Integer casosSatisfactorios;
    private Integer casosFallidos;


    public ResponseCasos() {
    }


    public ResponseCasos(Map<String, List<TesterClassEntity>> casosPorMessage, Map<String, Integer> casosConteoPorMessage, Integer casosSatisfactorios, Integer casosFallidos) {
        this.casosPorMessage = casosPorMessage;
        this.casosConteoPorMessage = casosConteoPorMessage;
        this.casosSatisfactorios = casosSatisfactorios;
        this.casosFallidos = casosFallidos;
    }

    public Map<String, Integer> getCasosConteoPorMessage() {
        return casosConteoPorMessage;
    }

    public void setCasosConteoPorMessage(Map<String, Integer> casosConteoPorMessage) {
        this.casosConteoPorMessage = casosConteoPorMessage;
    }

    public Map<String, List<TesterClassEntity>> getCasosPorMessage() {
        return casosPorMessage;
    }

    public void setCasosPorMessage(Map<String, List<TesterClassEntity>> casosPorMessage) {
        this.casosPorMessage = casosPorMessage;
    }

    public Integer getCasosSatisfactorios() {
        return casosSatisfactorios;
    }

    public void setCasosSatisfactorios(Integer casosSatisfactorios) {
        this.casosSatisfactorios = casosSatisfactorios;
    }

    public Integer getCasosFallidos() {
        return casosFallidos;
    }

    public void setCasosFallidos(Integer casosFallidos) {
        this.casosFallidos = casosFallidos;
    }
}
